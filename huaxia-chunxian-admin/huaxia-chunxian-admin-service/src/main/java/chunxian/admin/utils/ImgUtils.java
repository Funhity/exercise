/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: ImgUtils 版本号：1.0
 * @创建日期: 2017年12月20日 16:09
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.utils;

import chunxian.admin.model.UeImageUpload;
import com.huaxia.common.core.common.BaseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 说明: 图片上传
 *
 * @author xiaogui
 * @version 1.0
 */
public class ImgUtils {
    private ImgUtils() {
    }

    /**
     *  * "state": "SUCCESS",
     * "original": "80px - \u526f\u672c (2).jpg",
     * "size": "13252",
     * "title": "1465731377326075274.jpg",
     * "type": ".jpg",
     * "url": "/ueditor/jsp/upload/image/20160612/1465731377326075274.jpg"
     * @param file
     * @param maxFileSize
     * @param uploadDir
     * @param imageUrlPrefix
     * @return
     */
    public static Map<String, Object> uploadImage(MultipartFile file, long maxFileSize,
                                                  String uploadDir, String imageUrlPrefix) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf('.'));
                // 对文件重命名
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyyMMddHHmmss");
                Random ran = new Random();
                String newFileName = formatter.format(new Date())
                        + String.valueOf(ran.nextInt(100000)) + suffix;
                Integer fileSize = (int) file.getSize() / 1024;
                if (fileSize <= maxFileSize) {
                    File filePath = new File(uploadDir);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    file.transferTo(new File(filePath.getAbsolutePath() + File.separator
                            + newFileName));
                } else {
                    throw new BaseException("文件大小超出限制");
                }

                String fileUrl = imageUrlPrefix + newFileName;

                UeImageUpload ueImageUpload = new UeImageUpload();
                ueImageUpload.setOriginal(fileName);
                ueImageUpload.setSize(String.valueOf(fileSize));
                ueImageUpload.setState("SUCCESS");
                ueImageUpload.setTitle(newFileName);
                ueImageUpload.setType(suffix);
                ueImageUpload.setUrl(fileUrl);

                Map<String, Object> map = new HashMap<>(3);
                // 图片url
                map.put("src", fileUrl);
                // 图片名称，这个会显示在输入框里
                map.put("title", newFileName);
                map.put("UeImageUpload", ueImageUpload);

                return map;
            }
        } catch (Exception e) {
            throw new BaseException(e);
        }

        return null;
    }
}
