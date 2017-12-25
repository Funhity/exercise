<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/25
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<div id="mainDiv">
<section class="content-header">
    <h1>角色管理</h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">角色管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">

    <div class="row">

        <!-- /.col -->
        <div class="col-md-7">
            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入名称" name="name" class="form-control" type="search" likeOption="true" />
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" 	data-btn-type="search">查询</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="add">新增</button>
                        <button type="button" class="btn btn-default" data-btn-type="edit" >编辑</button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">删除</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="role_table" class="table table-bordered table-striped table-hover">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <div class="col-md-5">
            <!-- Profile Image -->
            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv_userRole">
                    <input type="hidden"  name="roleId" value="-1" id="roleId"/>
                    <input placeholder="请输入用户名" name="user.name" class="form-control" type="search" likeOption="true" />
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" 	data-btn-type="search">查询</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="selectUserRole">选择</button>
                        <button type="button" class="btn btn-default" data-btn-type="deleteUserRole">删除</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="userRole_table" class="table table-bordered table-striped table-hover">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
    <!-- /.row -->

</section>
</div>

<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var roleTable,userRoleTable;
    var winId="roleWin";
    $(function() {
        //init table and fill data
        var role_config={
            rowClick:function(row,isSelected){
                $("#roleId").val(isSelected?row.id:"-1");
                $("#roleName").remove();
                if(isSelected)
                    $("#searchDiv_userRole").prepend("<h5 id='roleName' class='pull-left'>【"+row.name+"】</h5>");
                userRoleTable.reloadData();
            }
        }
        roleTable = new CommonTable("role_table", "role_list", "searchDiv",role_config);

        var config={
            lengthChange:false,
            pagingType:'simple_numbers'
        }
        //init userrole table
        userRoleTable=new CommonTable("userRole_table","userRole_selected_list","searchDiv_userRole",config);

        //默认选中第一行
        setTimeout(function(){roleTable.selectFirstRow(true)},10);
        //make right table button on the same row

        //button event
        $('button[data-btn-type]').click(function() {
            var action = $(this).attr('data-btn-type');
            var rowId=roleTable.getSelectedRowId();
            switch (action) {
                case 'add':
                    modals.openWin({
                        winId:winId,
                        title:'新增角色',
                        width:'600px',
                        url:basePath+"/role/edit"
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
                        title:'编辑角色【'+roleTable.getSelectedRowData().name+'】',
                        width:'600px',
                        url:basePath+"/role/edit?id="+rowId
                    });
                    break;
                case 'delete':
                    if(!rowId){
                        modals.info('请选择要删除的行');
                        return false;
                    }
                    modals.confirm("是否要删除该行数据？",function(){
                        ajaxPost(basePath+"/role/delete/"+rowId,null,function(data){
                            if(data.success){
                                //modals.correct("已删除该数据");
                                roleTable.reloadData();
                            }else{
                                //setTimeout(function(){modals.info(data.message)},2000);
                                modals.info(data.message);
                            }
                        });
                    })
                    break;
                case 'selectUserRole':
                    if(!rowId){
                        modals.info('请选择角色');
                        return;
                    }
                    modals.openWin({
                        winId:'userRoleWin',
                        width:1000,
                        title:'角色【'+roleTable.getSelectedRowData().name+'】绑定用户',
                        url:basePath+'/userrole/select?roleId='+rowId,
                        hideFunc:function(){userRoleTable.reloadData();}
                    })
                    break;
                case 'deleteUserRole':
                    var rowId_ur=userRoleTable.getSelectedRowId();
                    if(!rowId_ur){
                        modals.info("请选择要删除的用户");
                        return false;
                    }
                    modals.confirm("是否要删除该行数据",function(){
                        ajaxPost(basePath+"/userrole/delete",{ids:rowId_ur},function(data){
                            if(data.success){
                                userRoleTable.reloadData();
                            }else{
                                modals.info(data.message);
                            }
                        })
                    });
                    break;
            }

        });
        //form_init();
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

    function fnRenderSys(value) {
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

