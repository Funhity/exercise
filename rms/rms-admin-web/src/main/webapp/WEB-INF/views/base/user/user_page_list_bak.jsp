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
<section class="content-header">
    <h1>
        用户管理
        <small>列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">用户管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <%--<div class="col-md-3">--%>

            <%--<!-- Profile Image -->--%>
            <%--<div class="box box-primary">--%>
                <%--<div class="box-body box-profile">--%>
                    <%--<div id="tree"></div>--%>
                <%--</div>--%>
                <%--<!-- /.box-body -->--%>
            <%--</div>--%>
            <%--<!-- /.box -->--%>
        <%--</div>--%>

        <div class="col-xs-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入姓名" name="realname" class="form-control" type="search" />
                    <%--<input placeholder="请输入登录名" name="name" class="form-control" type="search" likeOption="true"/>--%>
                    <input placeholder="请输入登录名" name="name" class="form-control" type="search"/>
                    <%--<input name="departmentCode" class="form-control" type="hidden" id="departmentCode" value="001003003001"/>--%>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                        <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="add">新增</button>
                        <button type="button" class="btn btn-default" data-btn-type="edit">编辑</button>
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
    //tableId,queryId,conditionContainer
    var userTable;
    var winId = "userWin";
    var selectId = "${selectId }";
    var deptId = "${deptId }";
    $(function () {

        //初始化菜单树
        //initTree(0);

        //init table and fill data
        userTable = new CommonTable("user_table", "user_list", "searchDiv");

        //button event
        $('button[data-btn-type]').click(function () {
            var action = $(this).attr('data-btn-type');
            var rowId = userTable.getSelectedRowId();
            console.log("******************rowId: " + rowId)
            switch (action) {
                case 'add':
                    window.loadPage(basePath + "/user/page/edit");
                    break;
                case 'edit':
                    if (!rowId) {
                        modals.info('请选择要编辑的行');
                        return false;
                    }
                    window.loadPage(basePath + "/user/page/edit?id=" + rowId);
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
        //form_init();

        //填充加载部分人原
        function fillOrgUser(node) {

            alert(userTable);
            //userTable.clearSearchDiv("searchDiv");
            $("#orgCode").val("001003003002");
            //userTable.initTable("user_table", "user_list", "searchDiv");

        }

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
                levels: 2,
                onNodeSelected: function (event, data) {
                    console.log("--------data.code: " +  data.code);
                    //alert(data.nodeId);
                    //fillOrgUser(data);
                    //self.formReadonly();
                }
            });
            if (treeData.length == 0)
                return;
            //默认选中第一个节点
            selectNodeId = selectNodeId || 0;
            $("#tree").data('treeview').selectNode(selectNodeId);
            $("#tree").data('treeview').expandNode(selectNodeId);
            $("#tree").data('treeview').revealNode(selectNodeId);
        }

    })

    function fnRenderDept(value) {
        if (value) {
            var ret=value;
            ajaxPost(basePath + "/org/show/"+value +".do", null, function (data) {
                ret = data.data;
            })
            return ret.name;
        } else
            return value;
    }

</script>
