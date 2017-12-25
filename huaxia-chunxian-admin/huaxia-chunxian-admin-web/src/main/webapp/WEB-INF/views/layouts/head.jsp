<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<header class="main-header">
  <!-- Logo -->
  <a href="${ctx}/main/login" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini">花财</span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><b>花财后台</b></span>
  </a>
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">切换导航</span>
    </a>

    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <li class="dropdown user user-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img src="${ctx}/static/publish/dist/33.png" class="user-image" alt="User Image">
            <span class="hidden-xs">${username}</span>
          </a>
        </li>
        <!-- Control Sidebar Toggle Button -->
        <li>
          <a href="${ctx}/main/logout" class="glyphicon glyphicon-off">
          </a>
        </li>
      </ul>
    </div>
  </nav>
</header>
<div class="control-sidebar-bg"></div>