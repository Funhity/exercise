<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/25
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>系统资源管理</h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">资源管理</a></li>
        <li class="active">资源管理</li>
    </ol>
</section>

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
        <!-- /.col -->
        <div class="col-md-9">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="addRoot">
                            <li class="fa fa-plus">&nbsp;新增系统</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="add">
                            <li class="fa fa-plus">&nbsp;新增菜单</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="edit">
                            <li class="fa fa-edit">&nbsp;编辑当前资源</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">
                            <li class="fa fa-remove">&nbsp;删除当前资源</li>
                        </button>
                    </div>
                    <!-- /.box-tools -->
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <form class="form-horizontal" id="res-form">
                        <input type="hidden" name="parentCode" id="parentCode"/>
                        <input type="hidden" name="id" id="id"/>
                        <div class="form-group">
                            <label for="parentName" class="col-sm-2 control-label">上级</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" disabled="disabled" id="parentName"
                                       name="parentName" placeholder="上级">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">名称</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="name" name="name" placeholder="名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="code" class="col-sm-2 control-label">编码</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="code" name="code" placeholder="编码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资源类型</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="restype" data-flag="icheck" class="square-blue" checked="checked"value="0">
                                    系统
                                </label>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="restype" data-flag="icheck" class="square-blue" value="1">
                                    目录
                                </label>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="restype" data-flag="icheck" class="square-blue" value="2">
                                    菜单
                                </label>
                                <label class="control-label">
                                    <input type="radio" name="restype" data-flag="icheck" class="square-blue" value="3">
                                    按钮
                                </label>

                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="layer" class="col-sm-2 control-label">层级编码</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="layer" name="layer"
                                       placeholder="层级编码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="shortName" class="col-sm-2 control-label">简称</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="shortName" name="shortName" placeholder="简称">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">链接</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="url" name="url" placeholder="链接">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="iconIndex" class="col-sm-2 control-label">图标</label>
                            <div class="col-sm-7">
                                <i data-bv-icon-for="iconIndex" id="icon_i" class="form-control-feedback fa fa-circle-o" style="right:15px"></i>
                                <input type="text" class="form-control" id="iconIndex" name="iconIndex" placeholder="图标">
                            </div>
                            <div class="col-sm-2">
                                <button type="button" id="selectIcon" class="btn btn-primary disabled" data-btn-type="selectIcon">
                                    <i class="fa fa-hand-pointer-o">&nbsp;选择图标</i>
                                </button>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否公开</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="isPublic" class="square-green" checked="checked" value="1"> 公开
                                </label> &nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="isPublic" class="square-green" value="0"> 关闭
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否控制权限</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="isControl" class="square-green" checked="checked" value="1">控制
                                </label> &nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="isControl" class="square-green" value="0"> 不控制
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否可用</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="deleteMark" class="square-green" checked="checked" value="1"> 启用
                                </label> &nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="deleteMark" class="square-green" value="0"> 禁用
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="memo" class="col-sm-2 control-label">说明</label>

                            <div class="col-sm-9">
                                <textarea class="form-control" id="memo" name="memo" placeholder="说明"></textarea>
                            </div>
                        </div>
                        <div class="box-footer" style="display:none">
                            <div class="text-center">
                                <button type="button" class="btn btn-default" data-btn-type="cancel">
                                    <i class="fa fa-reply">&nbsp;取消</i>
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    <i class="fa fa-save">&nbsp;保存</i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /. box -->
        </div>
    </div>
    <!-- /.row -->

</section>
</body>

<%@ include file="../../common_js.jsp" %>

<script>
    //封装改写
    var resCtrl = {
        form: null,
        initForm: function () {
            var self = this;
            //this.vacationForm = $("#vacation_form").form({baseEntity: false});
            this.form = $("#res-form").form();
            //this.form.initBaseEntity();
            //初始化菜单树
            this.initTree(0);
            $("#res-form").bootstrapValidator({
                message: '请输入有效值',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                submitHandler: function (validator, resform, submitButton) {
                    modals.confirm('确认保存？', function () {
                        //Save Data，对应'submit-提交'

                        var params = self.form.getFormSimpleData();
                        ajaxPost(basePath + '/res/save.do', params, function (data, status) {
                            if (data.success) {
                                //var id=$("input[name='id']").val();
                                var selectedArr = $("#tree").data("treeview").getSelected();
                                console.log("------------selectedArr: " + selectedArr);
                                console.log("------------selectedArr.length: " + selectedArr.length);

                                var selectedNodeId = selectedArr.length > 0 ? selectedArr[0].nodeId : 0;
                                var selectedNodeId = selectedArr.length > 0 ? selectedArr[0].nodeId : 0;
                                console.log("------------selectedNodeId: " + selectedNodeId);
                                self.initTree(selectedNodeId);
                            }
                        });
                    });
                },
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '请输入名称'
                            }
                        }
                    },
                    code: {
                        validators: {
                            notEmpty: {
                                message: '请输入编码'
                            },
                            remote: {
                                url: basePath + "/res/checkUnique.do",
                                data: function (validator) {
                                    return {
                                        code: $('#code').val(),
                                        id: $('#id').val()
                                    };
                                },
                                message: '该编码已被使用'
                            }
                        }
                    },
                    layer: {
                        validators: {
                            notEmpty: {
                                message: '请输入层级编码'
                            }
                        }
                    },
                    deleteMark: {
                        validators: {
                            notEmpty: {
                                message: '请选择是否可用'
                            }
                        }
                    }
                }
            });
            this.form.initComponent();
        },


        btntype: null,

        initBtnEvent: function () {
            var self = this;
            $('button[data-btn-type]').click(function () {
                var action = $(this).attr('data-btn-type');
                console.log("-----action: " + action);
                var selectedArr = $("#tree").data("treeview").getSelected();
                console.log("-----selectedArr: " + selectedArr.length);
                var selectedNode = selectedArr.length > 0 ? selectedArr[0] : null;
                console.log("-----selectedNode: " + selectedNode.code);
                console.log("-----selectedNode: " + selectedNode.nodes);
                switch (action) {
                    case 'addRoot':
                        self.addRoot(action);
                        break;
                    case 'add':
                        self.add(action, selectedNode);
                        break;
                    case 'edit':
                        self.edit(action, selectedNode);
                        break;
                    case 'delete':
                        self.delete(selectedNode);
                        break;
                    case 'cancel':
                        self.cancel(selectedNode);
                        break;
                }
            });
        },


        addRoot: function (action) {
            this.formWritable(action);
            this.form.clearForm();
            //填充上级机构和层级编码
            this.fillParentAndLevelCode(null);
            this.btntype = 'add';
        },
        add: function (action, selectedNode) {
            if (!selectedNode) {
                modals.info('请先选择上级机构');
                return false;
            }
            this.formWritable(action);
            this.form.clearForm();
            //填充上级机构和层级编码
            this.fillParentAndLevelCode(selectedNode);
            this.btntype = 'add';
        },
        edit: function (action, selectedNode) {
            if (!selectedNode) {
                modals.info('请先选择要编辑的节点');
                return false;
            }
            if (this.btntype == 'add') {
                this.fillResForm(selectedNode);
            }
            this.formWritable(action);
            this.btntype = 'edit';
        },
        delete: function (selectedNode) {
            var self = this;
            if (!selectedNode) {
                modals.info('请先选择要删除的节点');
                return false;
            }
            if (this.btntype == 'add')
                this.fillResForm(selectedNode);
            this.formReadonly();
            $(".box-header button[data-btn-type='delete']").removeClass("btn-default").addClass("btn-primary");
            if (selectedNode.nodes) {
                modals.info('该节点含有子节点，请先删除子节点');
                return false;
            }
            modals.confirm('是否删除该节点', function () {
                ajaxPost(basePath + "/res/delete/" + selectedNode.code+".do", null, function (data) {
                    if (data.success) {
                        modals.correct('删除成功');
                    } else {
                        modals.info(data.message);
                    }
                    //定位
                    var brothers = $("#tree").data("treeview").getSiblings(selectedNode);
                    if (brothers.length > 0)
                        self.initTree(brothers[brothers.length - 1].nodeId);
                    else {
                        var parent = $("#tree").data("treeview").getParent(selectedNode);
                        self.initTree(parent ? parent.nodeId : 0);
                    }
                });
            });
        },
        cancel: function (selectedNode) {
            if (this.btntype == 'add')
                this.fillResForm(selectedNode);
            this.formReadonly();
        },
        initTree: function (selectNodeId) {
            var self = this;
            var treeData = null;
            ajaxPost(basePath + "/res/treeData.do", null, function (data) {
                treeData = data;
            });
            console.log("----------treeData: " + treeData);

            $("#tree").treeview({
                data: treeData,
                showBorder: true,
                expandIcon: "glyphicon glyphicon-stop",
                collapseIcon: "glyphicon glyphicon-unchecked",
                levels: 1,
                onNodeSelected: function (event, data) {
                    //alert(data.nodeId);
                    self.fillResForm(data);
                    self.formReadonly();
                }
            });
            if (treeData.length == 0)
                return;
            //默认选中第一个节点
            selectNodeId = selectNodeId || 0;
            $("#tree").data('treeview').selectNode(selectNodeId);
            $("#tree").data('treeview').expandNode(selectNodeId);
            $("#tree").data('treeview').revealNode(selectNodeId);
        },
        //新增时，带入父级机构名称id,自动生成code
        fillParentAndLevelCode: function (selectedNode) {

            //$("input[name='parentName']").val(selectedNode ? selectedNode.text : '');
            //如何把parentName放入selectedNode 中；
            $("input[name='isPublic'][value='1']").prop("checked", "checked");
            $("input[name='isControl'][value='1']").prop("checked", "checked");
            $("input[name='deleteMark'][value='1']").prop("checked", "checked");

            if (selectedNode) {
                $("input[name='parentName']").val(selectedNode ? selectedNode.text : '');
                $("input[name='parentCode']").val(selectedNode.code);
                var nodes = selectedNode.nodes;
                console.log("-----------nodes: " + nodes);
                console.log("-----------selectedNode.code: " + selectedNode.code);

                /**
                 * 获取最近创建的code，
                 * 从DB中获取，业务上只显示未删除的组织结构
                 */
//                var code = nodes ? nodes[nodes.length - 1].code : null;
                var code;
                ajaxPost(basePath + "/res/getMaxCode/" + selectedNode.code+".do", null, function (data) {
                    code = data;
                })
                console.log("-----------code: " + code);
                console.log("-----------selectedNode.nodeId: " + selectedNode.nodeId);
                $("input[name='code']").val(getNextCode(selectedNode.code, code, 2));
                $("input[name='layer']").val(parseInt(selectedNode.layer) + 1);

            } else {
                $("input[name='parentName']").val(selectedNode ? selectedNode.parentName : '');
                var parentNode = $("#tree").data("treeview").getNode(0);
                var layer = "0";
                if (parentNode) {
                    var brothers = $("#tree").data("treeview").getSiblings(0);
                    layer = brothers[brothers.length - 1].layer;
                }
                $("input[name='code']").val(getNextCode("", layer, 2));
                $("input[name='layer']").val(1);
            }
        },
        //填充form
        fillResForm: function (node) {
            //console.log("----node.code: " + node.code);
            var self = this;
            this.form.clearForm();
            ajaxPost(basePath + "/res/get/" + node.code+".do", null, function (data) {
                //console.log("-------data: " + data)
                self.form.initFormData(data);
            })
        },
        //设置form为只读
        formReadonly: function () {
            //所有文本框只读
            $("input[name],textarea[name]").attr("readonly", "readonly");
            //隐藏取消、保存按钮
            $("#res-form .box-footer").hide();
            //还原新增、编辑、删除按钮样式
            $(".box-header button").removeClass("btn-primary").addClass("btn-default");
            //还原校验框
            if ($("#res-form").data('bootstrapValidator'))
                $("#res-form").data('bootstrapValidator').resetForm();
        },
        formWritable: function (action) {
            $("input[name],textarea[name]").removeAttr("readonly");
            $("#res-form .box-footer").show();
            $(".box-header button").removeClass("btn-primary").addClass("btn-default");
            if (action) {
                $(".box-header button[data-btn-type='" + action + "']").removeClass("btn-default").addClass("btn-primary");
                if(action = "edit") {
                    $("#code").attr("disabled", "disabled");
                }
            }


        }
    }

    $(function () {

        //初始化表单及校验
        resCtrl.initForm();
        //初始化按钮事件
        resCtrl.initBtnEvent();
    })


</script>
