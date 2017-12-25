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
        <%--日志管理--%>
        <%--<small>列表</small>--%>
    <%--</h1>--%>
    <%--<ol class="breadcrumb">--%>
        <%--<li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>--%>
        <%--<li><a href="#">系统管理</a></li>--%>
        <%--<li class="active">日志管理</li>--%>
    <%--</ol>--%>
<%--</section>--%>

<!-- Main content -->
<section class="content">
    <div class="row">

        <div class="col-md-12">
            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入姓名" name="username" class="form-control" type="search" />
                    <%--<input placeholder="请输入登录名" name="name" class="form-control" type="search" likeOption="true"/>--%>
                    <input placeholder="请输入方法" name="method" class="form-control" type="search"/>
                    <%--<input name="id" class="form-control" type="hidden" id="orgCode"/>--%>
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
                    <table id="log_table" class="table table-border table-hover" style="font-size:13px; ">
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
    var logTable;
    var selectId = "${selectId }";

    $(function () {

        //init table and fill data
        logTable = new CommonTable("log_table", "log_list", "searchDiv");

        //button event
        $('button[data-btn-type]').click(function () {
            var action = $(this).attr('data-btn-type');
            var rowId = logTable.getSelectedRowId();
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
                    window.loadPage(basePath + "/user/page/edit?id=" + rowId+"&orgcode="+ $("#orgCode").val());
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
                                logTable.reloadRowData();
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
                logTable.selectRow(selectId);
            }, 100);
        }
        //form_init();

    })


</script>
