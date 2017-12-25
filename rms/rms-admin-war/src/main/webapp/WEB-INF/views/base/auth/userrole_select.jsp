<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/25
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>
    <h5 class="modal-title">新增用户</h5>
</div>

<div class="modal-body" style="height:580px;">
    <div class="row">
        <!-- /.col -->
        <div class="col-md-6">
            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="box-header with-border">
                    <h5 class="box-title" style="font-size:14px;">未绑定该角色的用户列表</h5>
                    <button type="button" id="btn_add_ur" class="btn btn-sm close" title="用户绑定角色" ><li class="fa fa-arrow-right"></li></button>
                </div>
                <div class="dataTables_filter" id="searchDiv_unselected">
                    <input type="hidden" value="${roleId}" name="roleId" isCondition="false"/>
                    <input placeholder="请输入用户名" name="name" class="form-control form-control-sm" type="search" likeOption="true" />
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-btn-type="search" >查询</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="userRole_unselected_table" class="table table-bordered table-striped table-hover">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>

        <div class="col-md-6">
            <!-- Profile Image -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h5 class="box-title" style="font-size:14px;float:right">已绑定该角色的用户列表</h5>
                    <button type="button" id="btn_remove_ur" class="btn btn-sm close" style="float:left" title="用户解绑角色" ><li class="fa fa-arrow-left"></li></button>
                </div>
                <div class="dataTables_filter" id="searchDiv_selected">
                    <input type="hidden" value="${roleId}" name="roleId"/>
                    <input placeholder="请输入用户名" name="user.name" class="form-control" type="search" likeOption="true" />
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="userRole_selected_table" class="table table-bordered table-striped table-hover">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>

</div>
</body>

<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var roleId="${roleId}";//role Id
    var unselectedTable,selectedTable;
    $(function() {
        //the table config of opened window
        /* var table_config=null; */
        var table_config={
            "scrollY":"370px",
            "scrollCollapse": true,
            "pagingType":"full",//simple numbers simple_numbers full full_numbers
            "singleSelect":false,
            "scrollXInner":"450px",
            "autoWidth":false
            //"lengthChange":false
        };
        //init table and fill data
        unselectedTable = new CommonTable("userRole_unselected_table", "userRole_unselected_list", "searchDiv_unselected",table_config);
        //setTimeout(function(){unselectedTable.table.columns.adjust();},100);
        //init userrole table

        var selected_config={
            "scrollY":"370px",
            "scrollCollapse": true,
            "singleSelect":false,
            "autoWidth":false
        };
        selectedTable=new CommonTable("userRole_selected_table","userRole_selected_list","searchDiv_selected",selected_config);
        //button event

        //绑定角色到用户
        $("#btn_add_ur").click(function(){
            var rows=unselectedTable.getSelectedRowsData();
            var urlist=[];
            //console.log(JSON.stringify(rows));
            if(!rows){
                modals.info("请选择要绑定该角色的用户");
                return;
            }
            $.each(rows,function(index,row){
                var urObj={};
                var user={};
                user.id=row.id;
                user.version=row.version;
                urObj.user=user;
                urObj.roleId=roleId;
                urlist.push(urObj);
            });
            ajaxPost(basePath+"/userrole/save",{"urlist":JSON.stringify(urlist)},function(data){
                if(data.success){
                    selectedTable.reloadData();
                    unselectedTable.reloadRowData();
                }
            });

        });

        //解绑用户
        $("#btn_remove_ur").click(function(){
            var rows=selectedTable.getSelectedRowsData();
            if(!rows){
                modals.info("请选择要解绑的用户");
                return;
            }
            var idArr=[];
            $.each(rows,function(index,row){
                idArr.push(row.id);
            })
            ajaxPost(basePath+"/userrole/delete",{"ids":idArr.join(",")},function(data){
                if(data.success){
                    unselectedTable.reloadRowData();
                    selectedTable.reloadData();
                }
            })
        })
    })


</script>

