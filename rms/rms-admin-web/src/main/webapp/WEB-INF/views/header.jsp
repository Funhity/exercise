<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/16
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<!--NAVBAR-->
<!--===================================================-->
<header id="navbar">
    <div id="navbar-container" class="boxed">

        <!--Brand logo & name-->
        <!--================================-->
        <div class="navbar-header">
            <a href="${social_path}/index/index.jhtml" class="navbar-brand">

                <div class="brand-title">
                    <span class="brand-text">业务管理后台</span>
                </div>
            </a>
        </div>
        <!--================================-->
        <!--End brand logo & name-->


        <!--Navbar Dropdown-->
        <!--================================-->
        <div class="navbar-content clearfix">
            <ul class="nav navbar-top-links pull-left">

                <li class="tgl-menu-btn">
                    <a class="mainnav-toggle" href="pages-blank.html#">
                        <i class="fa fa-navicon fa-lg"></i>
                    </a>
                </li>
                <li class="dropdown">

                    <%--##$control.setTemplate("notify/feedback.vm")--%>

                </li>
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                <!--End notifications dropdown-->


                <!--Mega dropdown-->
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                <li class="mega-dropdown">
                    <a href="#" class="mega-dropdown-toggle">
                        <i class="fa fa-th-large fa-lg"></i>#*<i class="fa fa-key fa-lg"></i>*#
                    </a>

                    <div class="dropdown-menu mega-dropdown-menu" style="    max-width: 700px;">
                        <div class="clearfix">
                            <div class="col-sm-12 col-md-6">
                                <div class="panel widget">
                                    <div class="widget-header bg-primary"></div>
                                    <div class="widget-body text-center">
                                        <img alt="Profile Picture" class="widget-img img-circle img-border-light"
                                             src="res/media/image/av2.png")">
                                        <h4 class="mar-no">${session.getAttribute("_u_n_") }</h4>
                                        <%--#*<p class="text-muted mar-btm">Administrator</p>

                                        <div class="pad-ver">
                                            <button class="btn btn-primary">Follow</button>
                                            <button class="btn btn-success">Message</button>
                                        </div>*#--%>
                                    </div>
                                </div>
                                <!--Mega menu widget-->
                                #*<div class="text-center bg-primary pad-all">
                                <h3 class="text-thin mar-no">Weekend shopping</h3>

                                <div class="pad-ver box-inline">
												<span class="icon-wrap icon-wrap-lg icon-circle bg-trans-light">
													<i class="fa fa-shopping-cart fa-4x"></i>
												</span>
                                </div>
                                <p class="pad-btm">
                                    Members get <span class="text-lg text-bold">50%</span> more points. Lorem
                                    ipsum dolor sit amet!
                                </p>
                                <a href="pages-blank.html#" class="btn btn-purple">Learn More...</a>
                            </div>*#

                            </div>
                            #* <div class="col-sm-12 col-md-6">

                            <!--Mega menu list-->
                            <ul class="list-unstyled">
                                <li class="dropdown-header">Pages</li>
                                <li><a href="pages-blank.html#">Profile</a></li>
                                <li><a href="pages-blank.html#">Search Result</a></li>
                                <li><a href="pages-blank.html#">FAQ</a></li>
                                <li><a href="pages-blank.html#">Sreen Lock</a></li>
                                <li><a href="pages-blank.html#" class="disabled">Disabled</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">Icons</li>
                                <li><a href="#"><span class="pull-right badge badge-purple">479</span> Font Awesome</a></li>
                                <li><a href="pages-blank.html#">Skycons</a></li>
                            </ul>

                        </div>*#
                            #*<div class="col-sm-4 col-md-4">

                            <!--Mega menu list-->
                            <ul class="list-unstyled">
                                <li class="dropdown-header">Mailbox</li>
                                <li><a href="pages-blank.html#"><span
                                        class="pull-right label label-danger">Hot</span>Indox</a></li>
                                <li><a href="pages-blank.html#">Read Message</a></li>
                                <li><a href="pages-blank.html#">Compose</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">Featured</li>
                                <li><a href="pages-blank.html#">Smart navigation</a></li>
                                <li><a href="pages-blank.html#"><span
                                        class="pull-right badge badge-success">6</span>Exclusive plugins</a>
                                </li>
                                <li><a href="pages-blank.html#">Lot of themes</a></li>
                                <li><a href="pages-blank.html#">Transition effects</a></li>
                            </ul>

                        </div>*#
                            <div class="col-sm-12 col-md-6">

                                <style>
                                    .change-pwd input {
                                        margin: 5px 0;
                                    }
                                </style>
                                <!--Mega menu list-->
                                <ul class="list-unstyled">
                                    <li>
                                        <div  class="form">
                                            <div class="form-group change-pwd">
                                                <label class="dropdown-header" >修改密码</label>
                                                <input type="hidden" class="head-account-id" value="${session.getAttribute("login_user_id") }">
                                                <input id="old-password" type="password"
                                                       placeholder="旧密码" class="form-control">
                                                <input id="new-password" type="password"
                                                       placeholder="新密码" class="form-control">
                                                <input id="re-password" type="password"
                                                       placeholder="确认密码" class="form-control">
                                            </div>
                                            <button class="btn btn-primary btn-block change-password-btn">修改密码</button>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </li>
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                <!--End mega dropdown-->

            </ul>
            <ul class="nav navbar-top-links pull-right">

                <!--Language selector-->
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                #*<li class="dropdown">
                <a id="demo-lang-switch" class="lang-selector dropdown-toggle" href="pages-blank.html#"
                   data-toggle="dropdown">
								<span class="lang-selected">
									<img class="lang-flag" src="${assetsContent.getURI("images/flags/united-kingdom.png") }" alt="English">
									<span class="lang-id">EN</span>
									<span class="lang-name">English</span>
								</span>
                </a>

                <!--Language selector menu-->
                <ul class="head-list dropdown-menu with-arrow">
                    <li>
                        <!--English-->
                        <a href="pages-blank.html#" class="active">
                            <img class="lang-flag" src="${assetsContent.getURI("images/flags/united-kingdom.png")}" alt="English">
                            <span class="lang-id">EN</span>
                            <span class="lang-name">English</span>
                        </a>
                    </li>
                    <li>
                        <!--France-->
                        <a href="pages-blank.html#">
                            <img class="lang-flag" src="${assetsContent.getURI("images/flags/france.png") }" alt="France">
                            <span class="lang-id">FR</span>
                            <span class="lang-name">Fran&ccedil;ais</span>
                        </a>
                    </li>
                    <li>
                        <!--Germany-->
                        <a href="pages-blank.html#">
                            <img class="lang-flag" src="${assetsContent.getURI("images/flags/germany.png") }" alt="Germany">
                            <span class="lang-id">DE</span>
                            <span class="lang-name">Deutsch</span>
                        </a>
                    </li>
                    <li>
                        <!--Italy-->
                        <a href="pages-blank.html#">
                            <img class="lang-flag" src="${assetsContent.getURI("images/flags/italy.png") }" alt="Italy">
                            <span class="lang-id">IT</span>
                            <span class="lang-name">Italiano</span>
                        </a>
                    </li>
                    <li>
                        <!--Spain-->
                        <a href="pages-blank.html#">
                            <img class="lang-flag" src="${assetsContent.getURI("images/flags/spain.png")}" alt="Spain">
                            <span class="lang-id">ES</span>
                            <span class="lang-name">Espa&ntilde;ol</span>
                        </a>
                    </li>
                </ul>
            </li>*#
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                <!--End language selector-->


                <!--User dropdown-->
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                <li>
                    <style>
                        .head-account-mall {

                        }
                        .head-account-mall a {
                            border: 0!important;
                            padding: 4px 12px!important;
                        }

                        .head-account-mall.select2-container-active a {
                            border: 0!important;
                            -webkit-box-shadow: none!important;
                            box-shadow: none!important;
                        }
                        .head-account-mall.select2-dropdown-open a {
                            border: 1px solid #5897fb!important;
                        }

                    </style>
                    <%--#*<div class="username hidden-xs">
                    <input type="hidden" class="head-account-mall"
                           data-mall="$!rundata.request.session.getAttribute("_u_m_")">
                </div>*#--%>
                </li>
                <li id="dropdown-user" class="dropdown">
                    <a href="pages-blank.html#" data-toggle="dropdown" class="dropdown-toggle text-right">
								<span class="pull-right">
									<img class="img-circle img-user media-object" src="res/media/image/av1.png"
                                         alt="Profile Picture">
								</span>
                        <div class="username hidden-xs">
                            $!session.getAttribute("_u_n_")</div>
                    </a>


                    <div class="dropdown-menu dropdown-menu-md dropdown-menu-right with-arrow panel-default">

                        <!-- Dropdown heading  -->
                        #*<div class="pad-all bord-btm">
                        <p class="text-lg text-muted text-thin mar-btm">750Gb of 1,000Gb Used</p>

                        <div class="progress progress-sm">
                            <div class="progress-bar" style="width: 70%;">
                                <span class="sr-only">70%</span>
                            </div>
                        </div>
                    </div>*#


                        <!-- User dropdown menu -->
                        <ul class="head-list">
                            <li>
                                <a href="$!social_path/account/profile.jhtml">
                                    <i class="fa fa-user fa-fw fa-lg"></i> 资料
                                </a>
                            </li>
                            <li>
                                <a href="pages-blank.html#">
                                    <span class="badge badge-danger pull-right">9</span>
                                    <i class="fa fa-envelope fa-fw fa-lg"></i> 消息
                                </a>
                            </li>
                            <li>
                                <a href="pages-blank.html#">
                                    <span class="label label-success pull-right">New</span>
                                    <i class="fa fa-gear fa-fw fa-lg"></i> 设置
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" target="_blank">
                                    <i class="fa fa-question-circle fa-fw fa-lg"></i> 帮助
                                </a>
                            </li>
                            #*<li>
                            <a href="pages-blank.html#">
                                <i class="fa fa-lock fa-fw fa-lg"></i> Lock screen
                            </a>
                        </li>*#
                        </ul>

                        <!-- Dropdown footer -->
                        <div class="pad-all text-right">
                            <a href="$!social_path/logout.json" class="btn btn-primary">
                                <i class="fa fa-sign-out fa-fw"></i> 退出
                            </a>
                        </div>
                    </div>
                </li>
                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                <!--End user dropdown-->

            </ul>
        </div>
        <!--================================-->
        <!--End Navbar Dropdown-->

    </div>
</header>
<!--===================================================-->
<!--END NAVBAR-->