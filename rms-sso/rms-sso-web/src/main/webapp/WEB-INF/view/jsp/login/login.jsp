<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <link href="${ctx}/res/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/2.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/fonts/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/select2.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="${ctx}/res/media/css/default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/login/custom.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/nifty.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res/media/css/nifty-test.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="favicon.ico"/>
</head>

<!-- BEGIN BODY -->
<body class="login">

<div id="container" class="cls-container">
    <!-- BEGIN LOGO -->
    <div class="logo">
        <!--<a href="index.html">
        <img src="$static_path/res/media/image/admin/logo-big.png" alt=""/>
        </a>-->
    </div>
    <!-- END LOGO -->
    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
    <div class="menu-toggler sidebar-toggler">
    </div>
    <div id="bg-overlay" class="bg-img img-balloon"></div>
    <!-- END SIDEBAR TOGGLER BUTTON -->
    <!-- BEGIN LOGIN -->
    <div class="content">
        <!-- BEGIN LOGIN FORM -->
        <%--<form class="login-form" action="" method="post">--%>
            <input name="redirect" type="hidden" value="${redirect }"/>
            <input name="sysid" type="hidden" value="${sysid }"/>

            <h3 class="form-title">登录</h3>
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span>Enter any username and password.</span>
            </div>
            <div class="form-group">
                <%--<input type="hidden" name="redirect" value="${redirect }" />--%>
                <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                <label class="control-label visible-ie8 visible-ie9">用户名</label>
                <div class="input-icon">
                    <i class="fa fa-user"></i>
                    <input id="username" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <div class="input-icon">
                    <i class="fa fa-lock"></i>
                    <input id="password" class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" />
                </div>
            </div>
            <div class="form-actions clearfix">
                <label class="checkbox" style="color: #000;">
                    <input type="checkbox" name="remember" value="1" /> 记住密码 </label>
                <%--<a href="javascript:;" id="pBtn2">--%>
                <%--<span class="btn green pull-right">--%>
                    <%--登录 <i class="m-icon-swapright m-icon-white"></i>--%>
                <%--</span>--%>
                <%--</a>--%>
                <%--<input class="btn green pull-right" id="pBtn" type="submit" value="提交" />--%>
                <button class="btn green pull-right" id="pBtn">登录 <i class="m-icon-swapright m-icon-white"></i></button>
            </div>
            <div class="login-options">
                <h4>其他方式登录方式</h4>
                <ul class="social-icons">
                    <li>
                        <a class="facebook" data-original-title="facebook" href="#">
                        </a>
                    </li>
                    <li>
                        <a class="twitter" data-original-title="Twitter" href="#">
                        </a>
                    </li>
                    <li>
                        <a class="googleplus" data-original-title="Goole Plus" href="#">
                        </a>
                    </li>
                    <li>
                        <a class="linkedin" data-original-title="Linkedin" href="#">
                        </a>
                    </li>
                </ul>
            </div>
        <%--</form>--%>
        <div class="copyright">
            &#0169;  2017 huaxia.com
        </div>

        <div class="demo-bg">
            <div id="demo-bg-list">
                <div class="demo-loading"><i class="fa fa-refresh"></i></div>
                <img class="demo-chg-bg bg-trans" src="${ctx}/res/media/image/bg-img/thumbs/bg-trns.jpg" alt="Background Image">
                <img class="demo-chg-bg" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-1.jpg" alt="Background Image">
                <img class="demo-chg-bg active" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-2.jpg" alt="Background Image">
                <img class="demo-chg-bg" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-3.jpg" alt="Background Image">
                <img class="demo-chg-bg" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-4.jpg" alt="Background Image">
                <img class="demo-chg-bg" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-5.jpg" alt="Background Image">
                <img class="demo-chg-bg" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-6.jpg" alt="Background Image">
                <img class="demo-chg-bg" src="${ctx}/res/media/image/bg-img/thumbs/bg-img-7.jpg" alt="Background Image">
            </div>
        </div>
    </div>
</div>
</body>

</html>


<script type="text/javascript" src="${ctx}/res/media/js/jquery-1.10.1.min.js"></script>

<script type="text/javascript" src="${ctx}/res/media/js/app/account/logo_hj.js"></script>


<script>
    $(document).ready(function(){
//        $("#doSubmit").click(function(){
//            if($("#username").val() == 'admin' && $("#password").val() == 'admin') {
//                window.location.href="/platform/menu1.html";
//            } else {
//                alert("用户名或密码错误！");
//            }
//
//        })

    });
</script>
