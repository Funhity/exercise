package com.huaxia.generator.util;

import com.huaxia.generator.param.GeneratorParam;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成类
 */
public class MybatisGeneratorUtil {

	private static final int BUFFER = 8192;

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	public static void genService(String path, String servicePackageName, String modelPackageName, String[] modelNames) throws Exception {

		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = new StringBuilder(path).append("/")
				.append(servicePackageName.replaceAll("\\.", "/")).toString();
		String serviceImplPath = servicePath + "/impl";
		for (int i = 0; i < modelNames.length; i++) {
			String model = modelNames[i];
			String service = servicePath + "/" + model + "Service.java";
			String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
			// Service模板路径
			String service_vm = "/template/Service.vm";
			// ServiceImpl模板路径
			String serviceImpl_vm = "/template/ServiceImpl.vm";
			String absolutePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
			if(StringUtils.endsWith(absolutePath, "/")) {
				absolutePath = absolutePath.substring(0,absolutePath.lastIndexOf("/"));
			}
			// 生成service
			File serviceFile = new File(service);
			File fileParent = serviceFile.getParentFile();
			if(!fileParent.exists()){
				fileParent.mkdirs();
			}
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("service_path", servicePackageName);
				context.put("model_path", modelPackageName);
				context.put("model", model);
				context.put("ctime", ctime);
				service_vm = absolutePath + service_vm;
				VelocityUtil.generate(service_vm, service, context);
			}

			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			File fileParent1 = serviceImplFile.getParentFile();
			if(!fileParent1.exists()){
				fileParent1.mkdirs();
			}
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("service_path", servicePackageName);
				context.put("model_path", modelPackageName);
				context.put("model", model);
				context.put("namespace", org.apache.commons.lang.StringUtils.lowerCase(model));
				context.put("ctime", ctime);
				serviceImpl_vm = absolutePath + serviceImpl_vm;
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
			}
		}
	}

	public static String exceptionToString(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));

		return sw.toString();
	}

	public static void fixConfig(Configuration config, GeneratorParam param){
		if(param == null || StringUtils.isBlank(param.getConnection()))
			return;

		boolean flag = false;
		String prefix = param.getTablePrefix();
		if(StringUtils.isNotBlank(prefix)) {
			flag = true; //根据前缀，推量产生
		}

		File dirFile = new File(param.getBuildPath());
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		Context context = config.getContexts().get(0);
		//配置数据库属性
		JDBCConnectionConfiguration jdbcConnectionConfiguration = context.getJdbcConnectionConfiguration();
		String connection = "jdbc:mysql://" + param.getConnection() + ":" + param.getPort() + "/" + param.getDataBase();
		jdbcConnectionConfiguration.setConnectionURL(connection);
		jdbcConnectionConfiguration.setUserId(param.getUserId());
		jdbcConnectionConfiguration.setPassword(param.getUserPass());
		//配置模型的包名
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = context.getJavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage(param.getModelPath());
		javaModelGeneratorConfiguration.setTargetProject(param.getBuildPath());
		//DAO的包名
		//JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = context.getJavaClientGeneratorConfiguration();
		//javaClientGeneratorConfiguration.setTargetPackage(param.getDaoPath());
		//javaClientGeneratorConfiguration.setTargetProject(param.getBuildPath());
		//映射文件的包名
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = context.getSqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage(param.getMappingPath());
		sqlMapGeneratorConfiguration.setTargetProject(param.getBuildPath());
		//表集合
		List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
		tableConfigurations.clear();
		if(flag) {
			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(JDBC_DRIVER, connection, param.getUserId(), param.getUserPass());
			String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME LIKE ?";
			try {
				List sqlParams = new ArrayList();
				sqlParams.add(param.getDataBase());
				sqlParams.add(param.getTablePrefix() + "_%");
				List<Map> result = jdbcUtil.selectByParams(sql, sqlParams);
				if(result != null && !result.isEmpty()) {
//					List<String> tableNamesList = new ArrayList<String>();
					List<String> modelNameList = new ArrayList<String>();
					for (Map map : result) {
						String tableName = ObjectUtils.toString(map.get("TABLE_NAME"));

						String modelName = tableName;
						if (StringUtils.isBlank(tableName))
							continue;

						if (StringUtils.isNotBlank(prefix)) {
							modelName = tableName.substring(prefix.length(), tableName.length());
							modelName = StringUtil.lineToHump(modelName);
						}

//						tableNamesList.add(tableName);
						modelNameList.add(modelName);

						TableConfiguration tableConfiguration = new TableConfiguration(context);
						tableConfiguration.setTableName(tableName);
						tableConfiguration.setDomainObjectName(modelName);
						tableConfiguration.setCountByExampleStatementEnabled(false);
						tableConfiguration.setDeleteByExampleStatementEnabled(false);
						tableConfiguration.setSelectByExampleStatementEnabled(false);
						tableConfiguration.setUpdateByExampleStatementEnabled(false);

						//处理TEXT为VARCHAR
						String queryTextSql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND DATA_TYPE LIKE ?";
						List textParams = new ArrayList();
						textParams.add(tableName);
						textParams.add("%text");
						List<Map> queryTextResult = jdbcUtil.selectByParams(queryTextSql, textParams);
						if(queryTextResult != null && !queryTextResult.isEmpty()) {
							for(Map m : queryTextResult) {
								String textColumn = ObjectUtils.toString(m.get("COLUMN_NAME"));
								List columnOverrides = tableConfiguration.getColumnOverrides();
								ColumnOverride columnOverride = new ColumnOverride(textColumn);
								columnOverride.setJdbcType("VARCHAR");
								columnOverride.setJavaType("java.lang.String");
								columnOverrides.add(columnOverride);
							}
						}

						//模型是否驼峰命名，为0则为驼峰
						if (!"0".equals(param.getIsHump())) {
							tableConfiguration.getProperties().setProperty("useActualColumnNames", "true");
						}
						tableConfigurations.add(tableConfiguration);
					}

//					String[] tableNames = new String[tableNamesList.size()];
//					tableNamesList.toArray(tableNames);
//					param.setTableNames(tableNames);
					String[] modelNames = new String[modelNameList.size()];
					modelNameList.toArray(modelNames);
					param.setModelNames(modelNames);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbcUtil.release();
			}

		} else {
			JdbcUtil jdbcUtil = new JdbcUtil(JDBC_DRIVER, connection, param.getUserId(), param.getUserPass());
			try {
				for (int i = 0; i < param.getTableNames().length; i++) {
					if (StringUtils.isNotBlank(param.getTableNames()[i]) && StringUtils.isNotBlank(param.getModelNames()[i])) {
						TableConfiguration tableConfiguration = new TableConfiguration(context);
						tableConfiguration.setTableName(param.getTableNames()[i]);
						tableConfiguration.setDomainObjectName(param.getModelNames()[i]);
						tableConfiguration.setCountByExampleStatementEnabled(false);
						tableConfiguration.setDeleteByExampleStatementEnabled(false);
						tableConfiguration.setSelectByExampleStatementEnabled(false);
						tableConfiguration.setUpdateByExampleStatementEnabled(false);

						//处理TEXT为VARCHAR
						String queryTextSql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND DATA_TYPE LIKE ?";
						List textParams = new ArrayList();
						textParams.add(param.getTableNames()[i]);
						textParams.add("%text");
						List<Map> queryTextResult = jdbcUtil.selectByParams(queryTextSql, textParams);
						if (queryTextResult != null && !queryTextResult.isEmpty()) {
							for (Map m : queryTextResult) {
								String textColumn = ObjectUtils.toString(m.get("COLUMN_NAME"));
								List columnOverrides = tableConfiguration.getColumnOverrides();
								ColumnOverride columnOverride = new ColumnOverride(textColumn);
								columnOverride.setJdbcType("VARCHAR");
								columnOverride.setJavaType("java.lang.String");
								columnOverrides.add(columnOverride);
							}
						}

						//模型是否驼峰命名，为0则为驼峰
						if (!"0".equals(param.getIsHump())) {
							tableConfiguration.getProperties().setProperty("useActualColumnNames", "true");
						}
						tableConfigurations.add(tableConfiguration);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbcUtil.release();
			}
		}
	}

	/**
	 * 执行压缩操作
	 * @param sourceFilePath 被压缩的文件/文件夹
	 * @param zipFilePath 压缩后路径
	 * @param fileName 文件名
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
		boolean flag = false;
		File file = new File(sourceFilePath);
		if (!file.exists()){
			throw new RuntimeException(sourceFilePath + "不存在！");
		}
		try {
			File baseZipPath = new File(zipFilePath);
			if(!baseZipPath.exists()){
				baseZipPath.mkdirs();
			}
			File zipFile = new File(zipFilePath + "/" + fileName +".zip");
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compressByType(file, out, basedir);
			out.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(exceptionToString(e));
			throw new RuntimeException(e);
		}
		return flag;
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 *                 If a deletion fails, the method stops attempting to
	 *                 delete and returns "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 判断是目录还是文件，根据类型（文件/文件夹）执行不同的压缩方法
	 * @param file
	 * @param out
	 * @param basedir
	 */
	public static void compressByType(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
		if (file.isDirectory()) {
			compressDirectory(file, out, basedir);
		} else {
			compressFile(file, out, basedir);
		}
	}

	/**
	 * 压缩一个目录
	 * @param dir
	 * @param out
	 * @param basedir
	 */
	public static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists()){
			return;
		}

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
            /* 递归 */
			compressByType(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/**
	 * 压缩一个文件
	 * @param file
	 * @param out
	 * @param basedir
	 */
	public static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			String[] arr = basedir.split("/");
			String dirStr = "src/";
			if(arr[0].contains("src")){
				for(int i = 1;i < arr.length; i++){
					dirStr  +=arr[i] + "/";
				}
			}
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(dirStr + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
