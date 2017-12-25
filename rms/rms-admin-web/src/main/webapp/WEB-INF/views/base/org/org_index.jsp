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
<%--<section class="content-header">--%>
<%--<h1>组织机构管理</h1>--%>
<%--<ol class="breadcrumb">--%>
<%--<li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>--%>
<%--<li><a href="#">系统管理</a></li>--%>
<%--<li class="active">组织机构管理</li>--%>
<%--</ol>--%>
<%--</section>--%>
<div id="mainDiv" style="height: auto;">
<!-- Main content -->
<section class="content">

    <div class="row">
        <div class="col-md-3">
            <div class="box box-primary">
                <!-- 搜索框 -->
                <div class="box-body box-profile">
                    <div >
                        <div class="input-group input-group-sm">
                            <%--<label for="input-search" class="sr-only">Search Tree:</label>--%>
                            <input type="input" class="form-control" id="input-search" placeholder="Type to search..." value="">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-info btn-flat" id="btn-search">Search</button>
                            </span>
                        </div>
                    </div>
                </div>
                <!-- 树状结构 -->
                <div class="box-body box-profile">
                    <div id="tree"></div>
                </div>
            </div>
        </div>

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
                        <button type="button" class="btn btn-default" data-btn-type="move">
                            <li class="fa fa-share">&nbsp;整体迁移</li>
                        </button>
                    </div>
                    <!-- /.box-tools -->
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <form class="form-horizontal" id="org-form">
                        <div class="col-md-6">
                            <input type="hidden" name="parentCode" id="parentCode"/>
                            <input type="hidden" name="id" id="id"/>
                            <input type="hidden" name="user.code" id="user.code"/>
                            <div class="form-group">
                                <label for="parentName" class="col-sm-4 control-label">上级</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" disabled="disabled" id="parentName"
                                           name="parentName" placeholder="上级">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">机构名称<span style="color:red">&nbsp;*</span></label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="机构名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="code" class="col-sm-4 control-label">编码</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="编码">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="layer" class="col-sm-4 control-label">机构层级</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="layer" name="layer"
                                           placeholder="机构层级">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="shortName" class="col-sm-4 control-label">机构别名</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="shortName" name="shortName" placeholder="机构别名">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">负责人</label>
                                <div class="col-sm-8">
                                    <select name="manager" id="manager" data-src="/user/getAll.do" data-flag="urlSelector"
                                            data-placeholder="请选负责人" class="form-control select2" data-text="realname"
                                            data-value="code" style="width:100%"
                                            data-blank="true">
                                    </select>
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label for="user.mobile" class="col-sm-4 control-label">负责人电话</label>--%>

                                <%--<div class="col-sm-8">--%>
                                    <%--<input type="text" class="form-control" id="user.mobile" name="user.mobile" placeholder="负责人电话">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label for="outerPhone" class="col-sm-4 control-label">电话</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="outerPhone" name="outerPhone" placeholder="电话">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">是否可用</label>
                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="deleteMark" class="square-green" checked="checked" value="1"> 启用
                                    </label> &nbsp;&nbsp;&nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="deleteMark" class="square-green" value="0"> 禁用
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="maxuser" class="col-sm-4 control-label">人数上限</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="maxuser" name="maxuser" placeholder="人数上限">
                                </div>
                            </div>


                            <div class="form-group" id="isEffect_div" style="display: none">
                                <label class="col-sm-4 control-label">立即生效</label>
                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="isEffect" class="square-green" checked="checked" value="1"> 是
                                    </label> &nbsp;&nbsp;&nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="isEffect" class="square-green" value="0"> 否
                                    </label>
                                </div>
                            </div>

                            <div class="form-group" id="effectiveDate_div">
                                <label for="effectiveDate" class="col-sm-4 control-label">生效日期</label>
                                <div class="input-group date col-sm-8">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" class="form-control" data-flag="datepicker"
                                           data-format="yyyy-mm-dd"
                                           id="effectiveDate" name="effectiveDate"
                                           placeholder="生效日期">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="city" class="col-sm-4 control-label">城市</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="city" name="city" placeholder="城市">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address" class="col-sm-4 control-label">地址</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="address" name="address" placeholder="地址">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="fax" class="col-sm-4 control-label">传真</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="fax" name="fax" placeholder="传真">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="homepage" class="col-sm-4 control-label">主页</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="homepage" name="homepage" placeholder="主页">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="promotionCode" class="col-sm-4 control-label">推广编号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="promotionCode" name="promotionCode" placeholder="推广编号">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="urgentScale" class="col-sm-4 control-label">门店加急比例</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="urgentScale" name="urgentScale" placeholder="门店加急比例">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="orgCategoryNames" class="col-sm-4 control-label">机构类别</label>
                                <div class="col-sm-6" id="category_div">
                                    <input type="hidden" name="orgCategoryCodes" id="orgCategoryCodes">
                                    <input type="text" class="form-control" id="orgCategoryNames" name="orgCategoryNames" placeholder="机构类别">
                                </div>
                                <div class="col-sm-2" id="choose">
                                    <button type="button" class="btn btn-info" data-flag="category"><i
                                            class="fa fa-sitemap"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label class="col-sm-4 control-label">是否营业</label>--%>
                            <%--<div class="col-sm-8">--%>
                            <%--<label class="control-label">--%>
                            <%--<input type="radio" name="isBusiness" class="square-green" value="1"> 是--%>
                            <%--</label> &nbsp;&nbsp;&nbsp;--%>
                            <%--<label class="control-label">--%>
                            <%--<input type="radio" name="isBusiness" class="square-green" value="0" checked="checked" >否--%>
                            <%--</label>--%>
                            <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">是否虚拟机构</label>
                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="isVisual" class="square-green" value="1"> 是
                                    </label> &nbsp;&nbsp;&nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="isVisual" class="square-green" checked="checked" value="0"> 否
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="memo" class="col-sm-4 control-label">说明</label>

                                <div class="col-sm-8">
                                    <textarea class="form-control" id="memo" name="memo" placeholder="说明"></textarea>
                                </div>
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

</section>
</div>
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
                        ajaxPostStr(basePath + '/org/save.do', params, function (data, status) {
                            if (data.success) {
                                //var id=$("input[name='id']").val();
                                var selectedArr = $("#tree").data("treeview").getSelected();

                                var selectedNodeId = selectedArr.length > 0 ? selectedArr[0].nodeId : 0;
                                var selectedNodeId = selectedArr.length > 0 ? selectedArr[0].nodeId : 0;
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
                            },
                            remote: {
                                url: basePath + "/org/checkName.do",
                                data: function (validator) {
                                    return {
                                        code: $('#name').val(),
                                        id: $('#id').val()
                                    };
                                },
                                message: '该名称已被使用'
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
                var selectedArr = $("#tree").data("treeview").getSelected();
                var selectedNode = selectedArr.length > 0 ? selectedArr[0] : null;
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
                    case 'move':
                        self.move(selectedNode);
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
        move: function (selectedNode) {
            var self = this;
            if (!selectedNode) {
                modals.info('请先选择要迁移的节点');
                return false;
            }
            if (this.btntype == 'add')
                this.fillOrgForm(selectedNode);
            this.formReadonly();
            $(".box-header button[data-btn-type='move']").removeClass("btn-default").addClass("btn-primary");
            modals.confirm('是否迁移该节点', function () {
                window.loadPage(basePath + "/org/move.jhtml?code=" + selectedNode.code);
            });
        },
        initTree: function (selectNodeId) {
            var self = this;
            treeData = null;
            ajaxPost(basePath + "/org/treeData.do", null, function (data) {
                treeData = data;
            });

            $searchableTree = $("#tree").treeview({
                data: treeData,
                showBorder: true,
                expandIcon: "glyphicon glyphicon-stop",
                collapseIcon: "glyphicon glyphicon-unchecked",
                levels: 1,
                onNodeSelected: function (event, data) {
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
            //生效按钮，
            $("#isEffect_div").show();
            $("input[name='isEffect'][value='1']").prop("checked", "checked");
            $("#effectiveDate_div").hide();
            //$("input[name='parentName']").val(selectedNode ? selectedNode.text : '');
            //如何把parentName放入selectedNode 中；

            $("input[name='deleteMark'][value='1']").prop("checked", "checked");
            $("input[name='isVisual'][value='0']").prop("checked", "checked");
            //时间选择框不可点击

//            data-flag="datepicker"
//            $("#effectiveDate").attr("data-flag", "datepicker");

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
                ajaxPost(basePath + "/org/getMaxCode?code=" + selectedNode.code, null, function (data) {
                    code = data;
                })
                $("input[name='code']").val(getNextCode(selectedNode.code, code, 2));
                $("input[name='layer']").val(parseInt(selectedNode.layer) + 1);

            } else {
                $("input[name='parentName']").val(selectedNode ? selectedNode.parentName : '');
                var parentNode = $("#tree").data("treeview").getNode(0);
                if (parentNode) {
                    var brothers = $("#tree").data("treeview").getSiblings(0);
                    layer = brothers[brothers.length - 1].layer;
                }

                var code;
                ajaxPost(basePath + "/org/getMaxCode?isroot=true", null, function (data) {
                    code = data;
                })
                console.log("code:" + code);
                $("input[name='code']").val(getNextCode("", code, 2));
                $("input[name='layer']").val(1);
            }
        },
        //填充form
        fillOrgForm: function (node) {
            var self = this;
            this.form.clearForm();
            ajaxPost(basePath + "/org/get/" + node.code+".do", null, function (data) {
                self.form.initFormData(data);
                if($("#maxuser").val() == -1) {
                    $("#maxuser").val("");
                }
                //重新设置select的val值
                //获取所有option
                if(data.user != undefined) {
                    var code = data.user.code;
                    if(code != null && code != '') {
                        var userName = $("#manager option[value='"+ code +"']").html();
                        $("#select2-manager-container").html(userName);
                    } else {
                        $("#select2-manager-container").html("");
                    }
                } else {
                    $("#select2-manager-container").html("");
                }

//                $("#manager").select2({
//                    minimumResultsForSearch: Infinity
//                }).val(userName);

                self.initSelectCss();
            })
        },
        //设置form为只读
        formReadonly: function () {

            //$("#effectiveDate").attr("data-flag", "datepicker");
            //$("#effectiveDate").datepicker('disable');
            //$('#effectiveDate').datepicker('enable');
            //$("#effectiveDate").datepicker("option", "disabled", true);
            $("#manager").attr("disabled","disabled");
            $("#effectiveDate").attr("disabled","true");

            //所有文本框只读
            $("input[name],textarea[name]").attr("readonly", "readonly");
            //隐藏取消、保存按钮
            $("#org-form .box-footer").hide();

            $("#category_div").attr("class", "col-sm-8");
            $("#choose").hide();//机构类别选择按钮隐藏

            //还原新增、编辑、删除按钮样式
            $(".box-header button").removeClass("btn-primary").addClass("btn-default");
            //还原校验框
            if ($("#org-form").data('bootstrapValidator'))
                $("#org-form").data('bootstrapValidator').resetForm();
        },
        formWritable: function (action) {
            //生效按钮，
            $("#isEffect_div").show();
            $("input[name='isEffect'][value='1']").prop("checked", "checked");
            $("#effectiveDate_div").hide();
            $("#effectiveDate").removeAttr("disabled");
            $("#effectiveDate").val("");

            $("#manager").removeAttr("disabled");
            $("input[name],textarea[name]").removeAttr("readonly");
            $("#choose").show();//机构类别选择按钮显示
            $("#category_div").attr("class", "col-sm-6");
            $("#org-form .box-footer").show();
            $(".box-header button").removeClass("btn-primary").addClass("btn-default");
            if (action) {
                $(".box-header button[data-btn-type='" + action + "']").removeClass("btn-default").addClass("btn-primary");
                if(action = "edit") {
                    $("#code").attr("disabled", "disabled");
                }
            }
        },
        search: function(e) {
            var self = this;
            var options = {
                //ignoreCase: $('#chk-ignore-case').is(':checked'),
                //exactMatch: $('#chk-exact-match').is(':checked'),
                //revealResults: $('#chk-reveal-results').is(':checked')
            };
            var pattern = $('#input-search').val();
            var results;
            if(pattern) {
                results = $searchableTree.treeview('search', [ pattern, options ]);
                if(results == "") {
                    $('#tree').html("<p>未匹配到任何结果</p>");
                    return;
                }
            } else {
                results = treeData;
            }

            $searchableTree.treeview({
                data: results,
                showBorder: true,
                expandIcon: "glyphicon glyphicon-stop",
                collapseIcon: "glyphicon glyphicon-unchecked",
                levels: 1,
                onNodeSelected: function (event, data) {
                    self.fillOrgForm(data);
                    self.formReadonly();
                }
            });
        },
        initSelectCss: function () {
            //修改select默认边框样式
            $(".select2-container--default .select2-selection--single").css("border","1px solid #ccc");
            $(".select2-container--default .select2-selection--single").css("border-radius","0");
            $(".select2-container--default .select2-selection--single").css("height","34px");
            $(".select2-selection__rendered").css("padding-left","0");
            $(".select2-selection__arrow").css("height","34px");
        }

    }

    $(function () {

        //初始化表单及校验
        orgCtrl.initForm();
        //初始化按钮事件
        orgCtrl.initBtnEvent();
        orgCtrl.initSelectCss();

        //初始化组织机构选择器
        $("button[data-flag='category']").orgCategory({
            idField: $("#orgCategoryCodes"),
            nameField: $("#orgCategoryNames"),
            title:'选择类别',
            //multiSelect: true,
            showBorder: true,
            showCheckbox: true,
            levels:1
        })

        //var nodes = $("#res_tree").data("treeview").getChecked();
        //$('#btn-search').on('click', orgCtrl.search());
        //$('#input-search').on('keyup', orgCtrl.search());

        $("#input-search").bind("keyup",function(){
            orgCtrl.search()
        });

        $('#btn-search').on('click', function (e) {
            orgCtrl.search()
        });

//        $('input[name="isEffect"]:checked').val();
        $("input:radio[name='isEffect']").click(function(){
            if($(this).val() == 0) {
                $("#effectiveDate_div").show();
            } else {
                $("#effectiveDate_div").hide();
            }
        });

    })



</script>
