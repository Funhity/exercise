<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/19
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<!-- Content Header (Page header) -->
<%--<section class="content-header">--%>
    <%--<h1>--%>
        <%--用户管理 <small>列表</small>--%>
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
        <div class="col-xs-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入姓名" name="name" class="form-control" type="search" likeOption="true" /> <input
                        placeholder="请输入登录名" name="loginName" class="form-control" type="search" likeOption="true" />
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" 	data-btn-type="search">查询</button>
                        <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="add">新增</button>
                        <button type="button" class="btn btn-default" data-btn-type="edit" >编辑</button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">删除</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="user_table" class="table table-bordered table-striped table-hover">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var userTable;
    var winId="userWin";
    $(function() {
        //init table and fill data
        userTable = new CommonTable("user_table", "user_list", "searchDiv");

        //button event
        $('button[data-btn-type]').click(function() {
            var action = $(this).attr('data-btn-type');
            var rowId=userTable.getSelectedRowId();
            switch (action) {
                case 'add':
                    modals.openWin({
                        winId:winId,
                        title:'新增用户',
                        width:'900px',
                        url:basePath+"/user/edit"
                        /*, hideFunc:function(){
                            modals.info("hide me");
                        },
                        showFunc:function(){
                            modals.info("show me");
                        } */
                    });
                    break;
                case 'edit':
                    if(!rowId){
                        modals.info('请选择要编辑的行');
                        return false;
                    }
                    modals.openWin({
                        winId:winId,
                        title:'编辑用户【'+userTable.getSelectedRowData().name+'】',
                        width:'900px',
                        url:basePath+"/user/edit?id="+rowId,
                    });
                    break;
                case 'delete':
                    if(!rowId){
                        modals.info('请选择要删除的行');
                        return false;
                    }
                    modals.confirm("是否要删除该行数据？",function(){
                        ajaxPost(basePath+"/user/delete/" + rowId, null, function(data){
                            if(data.success){
                                //modals.correct("已删除该数据");
                                userTable.reloadRowData();
                            }else{
                                modals.error("用户数据被引用，不可删除！");
                            }
                        });
                    })
                    break;
            }

        });
        //form_init();
    })

    function fnRenderDept(value) {
        if (value) {
            var ret=value;
            ajaxPost(basePath + "/org/show/"+value, null, function (data) {
                ret=data.data;
            })
            return ret;
        } else
            return value;
    }
</script>
