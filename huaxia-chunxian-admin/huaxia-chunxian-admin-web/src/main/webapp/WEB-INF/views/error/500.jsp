<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%response.setStatus(200);%>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>

<body>
<div style="padding: 50px 0; height: 600px; text-align: center;">
	<h1>
		<i class="layui-icon" style="line-height: 500px; font-size: 500px; color: #393D50;">&#xe69c;</i>
	</h1>
	<p style="font-size: 20px; font-weight: 300; color: #999;">----------宝宝心里苦，可是宝宝不说！----------</p>
</div>

</body>
</html>
