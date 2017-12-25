/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: UeditorController 版本号：1.0
 * @创建日期: 2017年12月13日 17:48
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.ueditor;

import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 说明: ueditor上传配置
 * @author xiaogui
 * @version
 */
@Controller
@RequestMapping(value = "upload")
public class UeditorController {
    private Logger logger = LoggerFactory.getLogger(UeditorController.class);

    @RequestMapping(value = "config")
    @ResponseBody
    public void ueUpload(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");

        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("上传失败", e);
        }
    }
}
