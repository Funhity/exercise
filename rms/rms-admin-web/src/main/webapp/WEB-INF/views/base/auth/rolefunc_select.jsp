<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/26
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<section class="content-header">
<%--<div class="modal-header">--%>
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        <li class="fa fa-remove"></li>
    </button>
    <h5 class="modal-title">角色赋权</h5>
<%--</div>--%>
</section>

<%--<div class="modal-body" style="height:500px;">--%>
<section class="content">
    <div class="row">
        <!-- /.col -->
        <div class="col-md-4">
            <div class="box box-primary" style="height: 477px;overflow-y:auto">
                <div class="box-body box-profile">
                    <div id="res_tree"></div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <div class="col-md-8">
            <!-- Profile Image -->
            <div class="box box-primary">
                <div class="box-header with-border" style="padding:0px;" id="searchDiv_ff">
                    <h5 id='funcName' style='font-size:14px' class='pull-left'>【配置数据权限】</h5>
                    <input name="roleId" id="rId" type="hidden" value="${roleId}"/>
                    <input name="resourceId" type="hidden" id="resId" value="-1"/>
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-primary btn-sm" data-btn-type="add_ff">
                            <i class="fa fa-plus">&nbsp;新增</i></button>
                        <button type="button" class="btn btn-default btn-sm" data-btn-type="update_ff">
                            <i class="fa fa-pencil">&nbsp;修改</i></button>
                        <button type="button" class="btn btn-default btn-sm" data-btn-type="delete_ff">
                            <i class="fa fa-remove">&nbsp;删除</i></button>
                    </div>
                </div>
                <div class="box-body" style="min-height: 420px">
                    <table id="ff_table" class="table table-bordered table-striped table-hover" style="font-size: 13px;">
                    </table>
                </div>
                <div class="box-footer text-center">
                    <button class="btn btn-primary" data-btn-type="save"><i class="fa fa-save">&nbsp;保存</i></button>
                    <button class="btn btn-default" data-btn-type="cancel" data-dismiss="modal"><i class="fa fa-remove">
                        &nbsp;关闭</i></button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>

<%--</div>--%>
</section>
</body>

<!-- dataTable -->
<script>
    var ffTable;
    var roleId = "${roleId}";
    //alert("roleId: " + roleId);
    $(function () {
        ffTable = new CommonTable("ff_table", "ff_list", "searchDiv_ff", null);
        initResTree(0);
        //表格操作工具
        $("button[data-btn-type]").click(function () {
            var action_ff = $(this).attr('data-btn-type');
            var rowId_ff = ffTable.getSelectedRowId();
            var resourceId = $("#resId").val();
            switch (action_ff) {
                case "add_ff":
                    modals.openWin({
                        winId: "ffWin",
                        width: 600,
                        title: "新增数据权限",
                        url: basePath + '/rolefunc/ff/edit/' + roleId + '/' + resourceId + '/0'
                    });
                    break;
                case "update_ff":
                    if (!rowId_ff) {
                        modals.info('请选择要编辑的行');
                        return false;
                    }
                    modals.openWin({
                        winId: "ffWin",
                        width: 600,
                        title: "编辑【"+$('#funcName').val()+"】数据权限",
                        url: basePath + '/rolefunc/ff/edit/' + roleId + '/' + resourceId + '/' + rowId_ff
                    });
                    break;
                case "delete_ff":
                    if (!rowId_ff) {
                        modals.info("请选择要删除的行");
                        return false;
                    }
                    ajaxPost(basePath + "/rolefunc/ff/delete/"+rowId_ff, null, function (data) {
                        if (data.success) {
                            ffTable.reloadData();
                        }
                    });
                    break;
                case "save":
                    var nodes = $("#res_tree").data("treeview").getChecked();
                    var rflist = [];
                    for (var i = 0; i < nodes.length; i++) {
                        var curNode = nodes[i];
                        var obj = {};
                        obj.resourceId = curNode.id;
                        obj.roleId = roleId;
                        console.log("----roleId: resourceId = " + obj.resourceId + ": "+ obj.roleId);
                        rflist.push(obj);
                    }
                    //批量保存选中节点
                    ajaxPost(basePath + "/rolefunc/save_batch", {
                        roleId: roleId,
                        rflist: JSON.stringify(rflist)
                    }, function (data, status) {
                        modals.hideWin("roleFuncWin");
                    });
                    break;
                default:
                    break;
            }
        });
    });

    function initResTree(selectNodeId) {
        var treeData = null;
        //加载所有的资源
        //ajaxPost(basePath + "/res/treeData.do", null, function (data) {
        //加载当前用户有权限的资源
        ajaxPost(basePath + "/res/userTreeData.do", null, function (data) {
            treeData = data;
            console.log(JSON.stringify(treeData));
        });
        $("#res_tree").treeview({
            data: treeData,
            showBorder: true,
            /*expandIcon : "glyphicon glyphicon-stop",
             collapseIcon : "glyphicon glyphicon-unchecked",*/
            levels: 2,
            showCheckbox: true,
            showIcon: false,
            onNodeSelected: function (event, data) {
                console.log("-------data.state: " + data.state);
                if (data.state.checked) {
                    setCheckedNodeMsg(data);
                }

            },
            onNodeChecked: function (event, data) {
                if (!data.parentCode) {
                    return;
                }
                setCheckedNodeMsg(data);
                //点击同时选中
                $("#res_tree").data('treeview').selectNode(data.nodeId, {silent: true});
                var pnode = $('#res_tree').data('treeview').getParent(data.nodeId);
                while (pnode) {
                    $('#res_tree').treeview('checkNode', [pnode.nodeId, {silent: true}]);
                    pnode = $("#res_tree").treeview('getParent', pnode.nodeId);
                }
            },
            onNodeUnchecked: function (event, data) {
                if (!data.parentCode) {
                    return false;
                }
                disableButtons();
            }
        });
        if (treeData.length == 0)
            return;
        //默认选中第一个节点
        selectNodeId = selectNodeId || 0;
        $("#res_tree").data('treeview').selectNode(selectNodeId);
        $("#res_tree").data('treeview').expandNode(selectNodeId);
        $("#res_tree").data('treeview').revealNode(selectNodeId);
    }

    function disableButtons() {
        $("button[data-btn-type='add_ff']").attr("disabled", true);
        $("button[data-btn-type='update_ff']").attr("disabled", true);
        $("button[data-btn-type='delete_ff']").attr("disabled", true);
    }
    function enableButtons() {
        $("button[data-btn-type='add_ff']").attr("disabled", false);
        $("button[data-btn-type='update_ff']").attr("disabled", false);
        $("button[data-btn-type='delete_ff']").attr("disabled", false);
    }

    function setCheckedNodeMsg(data) {
        enableButtons();
        $("#resId").val(data.id);
        ffTable.reloadData();
        $("#funcName").remove();
        $("#searchDiv_ff").prepend("<h5 id='funcName' style='font-size:14px' class='pull-left'>【" + data.text + "】</h5>");
    }

</script>
