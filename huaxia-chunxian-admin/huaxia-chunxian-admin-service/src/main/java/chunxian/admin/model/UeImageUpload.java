/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: UeImageUpload 版本号：1.0
 * @创建日期: 2017年12月21日 18:07
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import com.huaxia.common.core.common.BaseEntity;

/**
 * 说明: UE图片上传返回JSON
 * @author xiaogui
 * @version 1.0
 *
 * {
 * "state": "SUCCESS",
 * "original": "80px - \u526f\u672c (2).jpg",
 * "size": "13252",
 * "title": "1465731377326075274.jpg",
 * "type": ".jpg",
 * "url": "/ueditor/jsp/upload/image/20160612/1465731377326075274.jpg"
 * }
 */
public class UeImageUpload extends BaseEntity {
    private String state;
    private String original;
    private String size;
    private String title;
    private String type;
    private String url;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
