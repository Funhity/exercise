<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/19
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<!-- Content Header (Page header) -->
<!-- Content Wrapper. Contains page content -->
<div id="mainDiv" style="height: auto;">
<%--<section class="content-header">--%>
    <%--<h1>--%>
        <%--用户管理--%>
        <%--<small>列表</small>--%>
    <%--</h1>--%>
    <%--<ol class="breadcrumb">--%>
        <%--<li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>--%>
        <%--<li><a href="#">系统管理</a></li>--%>
        <%--<li class="active">用户管理</li>--%>
    <%--</ol>--%>
<%--</section>--%>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-3">

            <!-- Profile Image -->
            <div class="box box-primary">
                <div class="box-body box-profile">
                    <div id="tree"></div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>

        <div class="col-md-9">
            <div class="box">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入姓名" name="realname" class="form-control" type="search" />
                    <%--<input placeholder="请输入登录名" name="name" class="form-control" type="search" likeOption="true"/>--%>
                    <input placeholder="请输入登录名" name="name" class="form-control" type="search"/>
                    <input name="orgCode" class="form-control" type="hidden" id="orgCode"/>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                        <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="select">查看</button>
                        <button type="button" class="btn btn-default" data-btn-type="add">新增</button>
                        <button type="button" class="btn btn-default" data-btn-type="edit">编辑</button>
                        <button type="button" class="btn btn-default" data-btn-type="batchUpdate">批量机构变更</button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">删除</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="user_table" class="table table-border table-hover" style="font-size:13px; ">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>

</div>

</body>
<!-- /.content-wrapper -->

<%@ include file="../../common_js.jsp" %>
<script>

    //封装改写
    //tableId,queryId,conditionContainer
    var userTable;
    var winId = "userWin";
    var selectId = "${selectId }";
    var orgcode = "${orgcode }";
    console.log("-------orgcode: " + orgcode);

    $(function () {

        //初始化菜单树
        initTree(0);

        //init table and fill data
        userTable = new CommonTable("user_table", "user_list", "searchDiv");

        //button event
        $('button[data-btn-type]').click(function () {
            var action = $(this).attr('data-btn-type');
            var rowId = userTable.getSelectedRowId();
            console.log("******************rowId: " + rowId)
            switch (action) {
                case 'select':
                    //window.parent.addTabs({id:'huacai30003',title: '订单管理',close: true,url: '/huaxia-chunxian-admin/order/orderInfo'});
                    window.parent.addTabs({
                        id: "user_id_"+ rowId,
                        title: '用户详情',
                        close: true,
                        url: basePath + "/user/detail?id=" + rowId
                    });
//                    window.loadPage(basePath + "/user/detail?id=" + rowId);
                    break;
                case 'add':
                    window.loadPage(basePath + "/user/page/edit");
                    break;
                case 'edit':
                    if (!rowId) {
                        modals.info('请选择要编辑的行');
                        return false;
                    }
                    window.loadPage(basePath + "/user/page/edit?id=" + rowId+"&orgcode="+ $("#orgCode").val());
                    break;
                case 'batchUpdate':
                    window.loadPage(basePath + "/user/org/update");
                    break;
                case 'delete':
                    if (!rowId) {
                        modals.info('请选择要删除的行');
                        return false;
                    }
                    modals.confirm("是否要删除该行数据？", function () {
                        ajaxPost(basePath + "/user/delete/" + rowId, null, function (data) {
                            if (data.success) {
                                //modals.correct("已删除该数据");
                                userTable.reloadRowData();
                            } else {
                                modals.error("用户数据被引用，不可删除！");
                            }
                        });
                    });
                    break;
            }

        });

        if (selectId != "0") {
            setTimeout(function () {
                userTable.selectRow(selectId);
            }, 100);
        }

    })

    //填充加载部门员工
    function fillOrgUser(node) {
        if(orgcode != '' && orgcode != null) {
            //alert("orgcode: " + orgcode);
            $("#orgCode").val(orgcode);
        } else {
            $("#orgCode").val(node.code);
        }

        console.log("--------orgCode.val: " +  $("#orgCode").val());
        //userTable = new CommonTable("user_table", "user_list", "searchDiv");
        //刷新页面，根据orgCode重新加载用户数据
        userTable.reloadData();

    }

    /**
     * 初始化组织机构列表
     * @param selectNodeId
     */
    function initTree(selectNodeId) {
        var self = this;
        var treeData = null;
        ajaxPost(basePath + "/org/treeData.do", null, function (data) {
            treeData = data;
        });

        $("#tree").treeview({
            data: treeData,
            showBorder: true,
            expandIcon: "glyphicon glyphicon-stop",
            collapseIcon: "glyphicon glyphicon-unchecked",
            levels: 1,
            onNodeSelected: function (event, data) {
                //更新机构代码，根据机构代码查询用户数据
                console.log("onNodeSelected.selectNodeId: "+ selectNodeId);
                fillOrgUser(data);
            }
        });
        if (treeData.length == 0)
            return;
        //默认选中第一个节点

//        alert("selectNodeId: "+ selectNodeId);
//        selectNodeId = selectNodeId || 0;
//        $("#tree").data('treeview').selectNode(selectNodeId);
//        $("#tree").data('treeview').expandNode(selectNodeId);
//        $("#tree").data('treeview').revealNode(selectNodeId);


    }

    function fnRenderDept(value) {
        console.log("value: " + value);
        if (value) {
            var ret=value;
            ajaxPost(basePath + "/org/show/"+value +".do", null, function (data) {
                ret = data.data;
            })
            //return ret.name;//单个部门名称
            return ret.parentName;//部门全称，包括以上所有父级

        } else
            return value;
    }

    function fnChangeColor(value) {
        if (value == '离职') {
            return "<span style='color: #ff0000;'>离职<span>";
        } else {
            return value;
        }
    }


</script>
