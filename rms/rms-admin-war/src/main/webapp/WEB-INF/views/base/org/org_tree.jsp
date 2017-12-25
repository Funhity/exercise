<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/17
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>组织机构管理</h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">组织机构管理</li>
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
                            <li class="fa fa-plus">&nbsp;新增根机构</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="add">
                            <li class="fa fa-plus">&nbsp;新增下级机构</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="edit">
                            <li class="fa fa-edit">&nbsp;编辑当前机构</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">
                            <li class="fa fa-remove">&nbsp;删除当前机构</li>
                        </button>
                    </div>
                    <!-- /.box-tools -->
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <form class="form-horizontal" id="org-form">
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
                        <%--<div class="form-group">
                            <label class="col-sm-2 control-label">机构类型</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="layer" data-flag="icheck" class="square-blue" checked="checked"value="1">
                                    一级部门
                                </label>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="layer" data-flag="icheck" class="square-blue" value="2">
                                    二级部门
                                </label>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="layer" data-flag="icheck" class="square-blue" value="3">
                                    三级部门
                                </label>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="layer" data-flag="icheck" class="square-blue" value="4">
                                    四级部门
                                </label>
                                </label>
                            </div>
                        </div>--%>
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
                            <label for="manager" class="col-sm-2 control-label">负责人</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="manager" name="manager" placeholder="负责人">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="outerPhone" class="col-sm-2 control-label">电话</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="outerPhone" name="outerPhone" placeholder="电话">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="fax" class="col-sm-2 control-label">传真</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="fax" name="fax" placeholder="传真">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="city" class="col-sm-2 control-label">城市</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="city" name="city" placeholder="城市">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="address" class="col-sm-2 control-label">地址</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="address" name="address" placeholder="地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="homepage" class="col-sm-2 control-label">主页</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="homepage" name="homepage" placeholder="主页">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="promotionCode" class="col-sm-2 control-label">推广编号</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="promotionCode" name="promotionCode" placeholder="推广编号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="urgentScale" class="col-sm-2 control-label">门店加急比例</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="urgentScale" name="urgentScale" placeholder="门店加急比例">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否营业</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="isBusiness" class="square-green" value="1"> 是
                                </label> &nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="isBusiness" class="square-green" value="0" checked="checked" >否
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否虚拟机构</label>
                            <div class="col-sm-9">
                                <label class="control-label">
                                    <input type="radio" name="isVisual" class="square-green" value="1"> 是
                                </label> &nbsp;&nbsp;&nbsp;
                                <label class="control-label">
                                    <input type="radio" name="isVisual" class="square-green" value="0" checked="checked" > 否
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
    var orgCtrl = {
        form: null,
        initForm: function () {
            var self = this;
            //this.vacationForm = $("#vacation_form").form({baseEntity: false});
            this.form = $("#org-form").form();
            //this.form.initBaseEntity();
            //初始化菜单树
            this.initTree(0);
            $("#org-form").bootstrapValidator({
                message: '请输入有效值',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                submitHandler: function (validator, orgform, submitButton) {
                    modals.confirm('确认保存？', function () {
                        //Save Data，对应'submit-提交'

                        var params = self.form.getFormSimpleData();
                        ajaxPost(basePath + '/org/save.do', params, function (data, status) {
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
                                url: basePath + "/org/checkUnique.do",
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
                this.fillOrgForm(selectedNode);
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
                this.fillOrgForm(selectedNode);
            this.formReadonly();
            $(".box-header button[data-btn-type='delete']").removeClass("btn-default").addClass("btn-primary");
            if (selectedNode.nodes) {
                modals.info('该节点含有子节点，请先删除子节点');
                return false;
            }
            modals.confirm('是否删除该节点', function () {
                ajaxPost(basePath + "/org/delete/" + selectedNode.code+".do", null, function (data) {
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
                this.fillOrgForm(selectedNode);
            this.formReadonly();
        },
        initTree: function (selectNodeId) {
            var self = this;
            var treeData = null;
            ajaxPost(basePath + "/org/treeData.do", null, function (data) {
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
                    self.fillOrgForm(data);
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

            $("input[name='deleteMark'][value='1']").prop("checked", "checked");
            if (selectedNode) {
                $("input[name='parentName']").val(selectedNode ? selectedNode.text : '');
                $("input[name='parentCode']").val(selectedNode.code);
                var nodes = selectedNode.nodes;
                /**
                 * 获取最近创建的code，
                 * 从DB中获取，业务上只显示未删除的组织结构
                 */
//                var code = nodes ? nodes[nodes.length - 1].code : null;
                var code;
                ajaxPost(basePath + "/org/getMaxCode/" + selectedNode.code+".do", null, function (data) {
                    code = data;
                })
                console.log("-----------code: " + code);
                console.log("-----------selectedNode.nodeId: " + selectedNode.nodeId);
                $("input[name='code']").val(getNextCode(selectedNode.code, code, 3));
                $("input[name='layer']").val(parseInt(selectedNode.layer) + 1);

            } else {
                $("input[name='parentName']").val(selectedNode ? selectedNode.parentName : '');
                var parentNode = $("#tree").data("treeview").getNode(0);
                var layer = "0";
                if (parentNode) {
                    var brothers = $("#tree").data("treeview").getSiblings(0);
                    layer = brothers[brothers.length - 1].layer;
                }
                $("input[name='code']").val(getNextCode("", layer, 3));
                $("input[name='layer']").val(1);
            }
        },
        //填充form
        fillOrgForm: function (node) {
            //console.log("----node.code: " + node.code);
            var self = this;
            this.form.clearForm();
            ajaxPost(basePath + "/org/get/" + node.code+".do", null, function (data) {
                //console.log("-------data: " + data)
                self.form.initFormData(data);
            })
        },
        //设置form为只读
        formReadonly: function () {
            //所有文本框只读
            $("input[name],textarea[name]").attr("readonly", "readonly");
            //隐藏取消、保存按钮
            $("#org-form .box-footer").hide();
            //还原新增、编辑、删除按钮样式
            $(".box-header button").removeClass("btn-primary").addClass("btn-default");
            //还原校验框
            if ($("#org-form").data('bootstrapValidator'))
                $("#org-form").data('bootstrapValidator').resetForm();
        },
        formWritable: function (action) {
            $("input[name],textarea[name]").removeAttr("readonly");
            $("#org-form .box-footer").show();
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
        orgCtrl.initForm();
        //初始化按钮事件
        orgCtrl.initBtnEvent();
    })


</script>
