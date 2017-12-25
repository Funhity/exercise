<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="path" value="${pageContext.request.contextPath}" />
<%-- <c:set var="resources" value="http://test.static.hangjia.com/hjb_app_web" /> --%>
<c:set var="resources" value="http://static.hangjia.com/hjb_app_web" />
<c:set var="g_contextpath" value="${ctx}" />
<c:set var="g_picturepath" value="http://static.hangjia.com/" />
<c:set var="social_path" value="http://localhost:8090/rms-admin-web" />
<c:set var="static_path" value="http://cdn.hangjiabao.com/bxj_web" />

<script>
	window.g_contextpath='${g_contextpath}';
	window.g_picturepath='${g_picturepath}';
	window.resources='${resources}';
    window.social_path='${social_path}';
    window.static_path='${static_path}';
</script>

<!--icon-->
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/common/favicon.ico" media="screen"/>
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${ctx}/static/adminlte/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/common/libs/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctx}/static/common/libs/ionicons/css/ionicons.min.css">

<!-- iCheck -->
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/iCheck/square/red.css">
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/bootstrap-validator/dist/css/bootstrap-validator.css"/>

<link rel="stylesheet" href="${ctx}/static/common/css/base.css">

<!-- table and edit -->
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/datatables/select.bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/iCheck/all.css">
<!-- daterangepicker -->
<!--<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/daterangepicker/daterangepicker.css">-->
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/select2/select2.min.css">
<%--<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/select2/select2.css">--%>
<!-- treeview -->
<link rel="stylesheet" href="${ctx}/static/adminlte/plugins/bootstrap-treeview/bootstrap-treeview.min.css" />
<!-- Theme style -->
<link rel="stylesheet" href="${ctx}/static/adminlte/dist/css/skins/_all-skins.css">

<!-- Theme style -->
<link rel="stylesheet" href="${ctx}/static/adminlte/dist/css/AdminLTE.css">


<!-- select2 -->
<%--<link rel="stylesheet" type="text/css" href="${ctx}/static/common/css/select2/select2.min.css" />--%>