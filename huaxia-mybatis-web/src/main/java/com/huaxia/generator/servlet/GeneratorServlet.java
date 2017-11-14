package com.huaxia.generator.servlet;

import java.io.*;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.huaxia.generator.util.MybatisGeneratorUtil;
import com.huaxia.generator.util.StringUtil;
import com.huaxia.generator.util.VelocityUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.huaxia.generator.param.GeneratorParam;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import static com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl.PACKAGE_NAME;

public class GeneratorServlet extends HttpServlet {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;




    /**
     * Constructor of the object.
     */
    public GeneratorServlet() {
        super();
    }
    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        super.init();
    }

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		GeneratorParam param = new GeneratorParam();
    		param.setConnection(request.getParameter("connection"));
    		param.setPort(request.getParameter("port"));
    		param.setDataBase(request.getParameter("dataBase"));
    		param.setUserId(request.getParameter("userId"));
    		param.setUserPass(request.getParameter("userPass"));
			param.setServicePath(request.getParameter("servicePath"));
    		param.setModelPath(request.getParameter("modelPath"));
    		param.setDaoPath(request.getParameter("daoPath"));
    		param.setMappingPath(request.getParameter("mappingPath"));
    		param.setTableNames(request.getParameterValues("tableNames"));
    		param.setModelNames(request.getParameterValues("modelNames"));
    		param.setIsHump(request.getParameter("isHump"));
			param.setTablePrefix(request.getParameter("prefix"));
    		// 信息缓存
    		List<String> warnings = new ArrayList<String>();
    		// 覆盖已有的重名文件
    		boolean overwrite = true;
    		// 准备 配置文件
    		final String path = request.getSession().getServletContext().getRealPath(File.separator);
    		final String srcPath  = "/src" + new Date().getTime();
    		String targetPath = path + srcPath;
    		param.setBuildPath(targetPath);

    		String result = "000000";
    		try {
                String config_path = "/WEB-INF/classes/runtimecfg/generatorConfig.xml";
                File configFile = new File(path + config_path);
                // 1.创建 配置解析器
                ConfigurationParser parser = new ConfigurationParser(warnings);
                // 2.获取 配置信息
                Configuration config = parser.parseConfiguration(configFile);
                MybatisGeneratorUtil.fixConfig(config, param);/** 封装参数*/
                // 3.创建 默认命令解释调回器
                DefaultShellCallback callback = new DefaultShellCallback(overwrite);
                // 4.创建 mybatis的生成器
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                // 5.执行，关闭生成器
				myBatisGenerator.generate(null);
				MybatisGeneratorUtil.genService(targetPath, param.getServicePath(), param.getModelPath(), param.getModelNames());

    		} catch (CommunicationsException e) {
				e.printStackTrace();
    			result = "000001";
    		} catch (ConnectException e) {
				e.printStackTrace();
    			result = "000002";
    		} catch (MySQLSyntaxErrorException e) {
				e.printStackTrace();
    			result = "000003";
    		} catch (SQLException e) {
				e.printStackTrace();
    			result = "000004";
    		} catch (Exception e) {
    			e.printStackTrace();
    			result = "000005" + e.getLocalizedMessage();
    		}
			MybatisGeneratorUtil.fileToZip(param.getBuildPath(), path + "/tmp", srcPath);/** 打包操作*/
    		this.responseJson(response, result, srcPath + ".zip");
    		new Thread(new Runnable() { /** 执行完毕后删除冗余文件*/
    		    @Override
    		    public void run() {
    		    	try {
    					Thread.sleep(60000);
    					File dirFile = new File(path + srcPath);
    					File zipFile = new File(path + "/tmp" + "/" + srcPath +".zip");
						MybatisGeneratorUtil.deleteDir(dirFile);
						MybatisGeneratorUtil.deleteDir(zipFile);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    		    }
    		}).start();
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(exceptionToString(e));
		}

    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }

    protected void responseJson(HttpServletResponse response,String responseCode,String zipName) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		Map<String, String> map = new HashMap<String, String>();
		map.put("rspCode", responseCode);
		map.put("zipName", zipName);
		String userJson = JSON.toJSONString(map);

		out.write(new String(userJson.getBytes("UTF-8")));
		out.flush();
	}

}
