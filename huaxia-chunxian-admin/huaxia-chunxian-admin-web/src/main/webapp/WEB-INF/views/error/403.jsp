<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
	<title>403 - 用户权限不足</title>
</head>

<body>
	<div style="padding: 50px 0; height: 600px; text-align: center;">
		<h1>
			<i class="layui-icon" style="line-height: 500px; font-size: 500px; color: #393D50;">&#xe6af;</i>
		</h1>
		<p style="font-size: 20px; font-weight: 300; color: #999;">----------世界那么大，我想去看看！----------</p>
		<%--403 - 用户权限不足--%>
	</div>
</body>
</html>