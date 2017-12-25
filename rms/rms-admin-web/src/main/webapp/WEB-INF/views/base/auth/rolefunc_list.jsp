<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/26
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<%--<body class="hold-transition  sidebar-mini fixed">--%>
<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">

<!-- Main content -->
<section class="content">

    <div class="row">
        <!-- /.col -->
        <div>

            <div class="col-md-7">
                <div class="box box-primary">
                    <!-- /.box-header -->
                    <div class="dataTables_filter" id="searchDiv">
                        <input placeholder="请输入名称" name="name" class="form-control" type="search" likeOption="true"/>
                        <input name="parentCode" class="form-control" type="hidden" id="parentCode"/>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                        </div>
                    </div>
                    <div class="box-body">
                        <table id="role_table" class="table table-bordered table-striped table-hover" style="font-size:13px; ">
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </div>

        <div>
            <div class="col-md-5">
                <!-- Profile Image -->
                <div class="box box-primary">
                    <!-- /.box-header -->
                    <div class="dataTables_filter" id="searchDiv_roleFunc">
                        <input type="hidden" name="roleId" value="-1" id="roleId"/>
                        <input placeholder="请输入功能名" name="name" class="form-control" type="search" />
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" data-btn-type="selectRoleFunc">授权</button>
                            <button type="button" class="btn btn-default" data-btn-type="deleteRoleFunc">删除</button>
                        </div>
                    </div>
                    <div class="box-body">
                        <table id="roleFunc_table" class="table table-bordered table-striped table-hover" style="font-size:13px; ">
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </div>
    <!-- /.row -->

</section>
<%--</div>--%>

</body>
<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var roleTable, roleFuncTable;
    $(function () {

        //init table and fill data
        var role_config = {
            rowClick: function (row, isSelected) {
                $("#roleId").val(isSelected ? row.id : "-1");
                //alert($("#roleId").val());
                $("#roleName").remove();
                if (isSelected)
                    $("#searchDiv_roleFunc").prepend("<h5 id='roleName' class='pull-left'>【" + row.name + "】</h5>");
                roleFuncTable.reloadData();
            }
        }
        roleTable = new CommonTable("role_table", "role_list", "searchDiv", role_config);

        //init userrole table
        roleFuncTable = new CommonTable("roleFunc_table", "roleFunc_selected_list", "searchDiv_roleFunc");

        //默认选中第一行
        setTimeout(function () {
            roleTable.selectFirstRow(true);
        }, 100);

        //button event
        $('button[data-btn-type]').click(function () {
            var action = $(this).attr('data-btn-type');
            var rowId = roleTable.getSelectedRowId();
            switch (action) {
                case 'selectRoleFunc':
                    if (!rowId) {
                        modals.info('请选择角色');
                        return;
                    }
                    modals.openWin({
                        winId: 'roleFuncWin',
                        width: 900,
                        title: '角色【' + roleTable.getSelectedRowData().name + '】绑定功能',
                        url: basePath + '/rolefunc/select/'+rowId,
                        hideFunc: function () {
                            roleFuncTable.reloadData();
                        }
                    });
                    break;
                case 'deleteRoleFunc':
                    var rowId_ur = roleFuncTable.getSelectedRowId();
                    if (!rowId_ur) {
                        modals.info("请选择要解绑的功能");
                        return false;
                    }
                    modals.confirm("是否要删除该行数据", function () {
                        ajaxPost(basePath + "/rolefunc/delete/"+rowId_ur,null, function (data) {
                            if (data.success) {
                                roleFuncTable.reloadData();
                            } else {
                                modals.info(data.message);
                            }
                        })
                    });
                    break;
                default:
                    break;
            }

        });
        //form_init();
    })

    //填充加载目录下的角色
    function fillRoleUser(node) {
        $("#parentCode").val(node.code);
        console.log("--------parentCode.val: " +  $("#parentCode").val());
        //userTable = new CommonTable("user_table", "user_list", "searchDiv");
        //刷新页面，根据orgCode重新加载用户数据
        roleTable.reloadData();

    }


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

    //    function fnRenderSys(value) {
    //        if (value) {
    //            var ret=value;
    //            ajaxPost(basePath + "/org/show/"+value +".do", null, function (data) {
    //                ret = data.data;
    //            })
    //            return ret.name;
    //        } else
    //            return value;
    //    }

</script>
