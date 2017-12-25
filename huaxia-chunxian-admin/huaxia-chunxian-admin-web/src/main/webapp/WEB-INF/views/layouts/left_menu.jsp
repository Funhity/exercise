<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<%
    String basePath = request.getScheme()+"://"+
            request.getServerName()+":"+
            request.getServerPort()+
            request.getContextPath();
%>
<c:set var="basePath" value="<%=basePath%>"/>
<aside class="main-sidebar">
    <section class="sidebar">
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="button" name="search" id="search-btn"
                        class="btn btn-flat" onclick="search_menu()">
                    <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <ul class="sidebar-menu">
        </ul>
    </section>
</aside>
<script type="text/javascript">
    $(function () {
        var head = {
            id: "huacai1000",
            text: "花财后台管理系统",
            icon: "",
            isHeader: true
        };
        var workbench = {
            id: "huacai1001",
            text: "工作台",
            url: "${basePath}/main/index",
            targetType: "iframe-tab",
            icon: "fa fa-archive"
        };
        var authority = {
            id: "huacai2000",
            text: "权限管理",
            icon: "fa fa-institution",
            isOpen: true
        };
        var module = {
            id: "huacai3000",
            text: "功能模块",
            icon: "fa fa-laptop",
            isOpen: true,
            children: [
                {
                    id: "huacai30001",
                    text: "APP设置",
                    url: "${basePath}/appsetting/main",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                },  {
                    id: "huacai30002",
                    text: "产品管理",
                    url: "${basePath}/productmanage/productinfo",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }, {
                    id: "huacai30003",
                    text: "订单管理",
                    url: "${basePath}/order/orderInfo",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }, {
                    id: "huacai30004",
                    text: "用户管理",
                    url: "${basePath}/consumer/consumerInfo",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }, {
                    id: "huacai30005",
                    text: "消息中心",
                    url: "${basePath}/messagecenter/main",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }, {
                    id: "huacai30006",
                    text: "帮助中心",
                    url: "${basePath}/helpcenter/type",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }, {
                    id: "huacai30007",
                    text: "用户反馈",
                    url: "${basePath}/feedback/feedback",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }, {
                    id: "huacai30008",
                    text: "渠道管理",
                    url: "${basePath}/channel/channelInfo",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }

            ]
        };
        var menuArray = [];
        menuArray.push(head);
        menuArray.push(workbench);
        authority = eval("[" + '${menus}' + "]"); //从rms拉权限管理
        if(!(authority === undefined || authority.length == 0)) {
            menuArray.push(authority[0]);
        }
        menuArray.push(module);
        $('.sidebar-menu').sidebarMenu({data: menuArray});
    });

    /**
     * 本地搜索菜单
     */
    function search_menu() {
        //要搜索的值
        var text = $('input[name=q]').val();
        var $ul = $('.sidebar-menu');
        $ul.find("a.nav-link").each(function () {
            var $a = $(this).css("border", "");
            //判断是否含有要搜索的字符串
            if ($a.children("span.menu-text").text().indexOf(text) >= 0) {
                //如果a标签的父级是隐藏的就展开
                $ul = $a.parents("ul");
                if ($ul.is(":hidden")) {
                    $a.parents("ul").prev().click();
                }
                //点击该菜单
                $a.click().css("border", "1px solid");
            }
        });
    }
</script>