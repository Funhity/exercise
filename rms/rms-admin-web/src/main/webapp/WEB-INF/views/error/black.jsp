<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/11/16
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/css/error/black.css">

<head>
    <title>标准业务操作系统平台</title>
</head>

<body style="background:#ffffff">
    <div class="una">
        <img src="${ctx}/static/common/images/error/img_03.png" alt=""/>

        <p>您登录的账号有非法操作或所在地存在异常。</p>

        <p>请联系华夏信财，客服热线：xxx-xxx-xxx。</p>
    </div>
</body>
</html>
