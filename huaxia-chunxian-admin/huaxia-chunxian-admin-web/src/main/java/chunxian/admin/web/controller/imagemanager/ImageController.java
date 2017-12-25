/**
 * @项目名称:
 * @文件名称: ImageController 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.imagemanager;

import chunxian.admin.model.UeImageUpload;
import chunxian.admin.utils.FastJsonUtil;
import chunxian.admin.utils.ImgUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author gaoyouli
 * @Title: ImageController
 * @Description: ImageController
 */
@Controller
@RequestMapping(value = "upload")
public class ImageController {
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    public static final long MAX_UPLOAD_SIZE = 10240;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${image.url.root}")
    private String imageUrlPrefix;

    @RequestMapping(value = "imageUp", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object imageUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");

        return ImgUtils.uploadImage(file, MAX_UPLOAD_SIZE, uploadDir, imageUrlPrefix);
    }

    @RequestMapping(value = "ueImageUp", method = RequestMethod.POST)
    public void ueImageUpload(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");

        Map<String, Object> imageMap = ImgUtils.uploadImage(file, MAX_UPLOAD_SIZE, uploadDir, imageUrlPrefix);
        UeImageUpload iu = (UeImageUpload) imageMap.get("UeImageUpload");

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String userJson = FastJsonUtil.toJSONString(iu);
        try {
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.flush();
        } catch (IOException ioe) {
            logger.error("图片预览失败", ioe);
        }

    }
}
