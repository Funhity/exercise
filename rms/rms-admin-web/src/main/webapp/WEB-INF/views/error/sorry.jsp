<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/11/16
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<link rel="stylesheet" type="text/css" href="${ctx}/static/common/css/error/sorry.css">
<head>
    <title>页面出错啦</title>
</head>
<body>
<!--内容 开始-->
<div class="bjr_content">
    <div class="bjr_msg">
        <img src="${ctx}/static/common/images/error/img1.png" alt="">
        <div class="bjr_msg_p">
            <p>Um...</p>
            <p>页面出错啦！</p>
            <div class="bjr_btn">
                <a href="${ctx}/index.jhtml"><i></i>返回</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>