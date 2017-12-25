<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/19
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<section class="content-header">
    <h1>
        用户管理
        <small>列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i>首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">用户管理</li>
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

        <div class="col-xs-9">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs pull-right">
                    <li><a href="#tab-content-edit" data-toggle="tab" id="nav-tab-edit"><i class="fa fa-edit"></i></a></li>
                    <li class="active"><a href="#tab-content-list" data-toggle="tab" id="nav-tab-list"><i class="fa fa-list-ul"></i></a></li>
                    <li class="pull-left header"><i class="fa fa-user"></i><small>用户列表</small></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab-content-list">
                        <div class="box">
                            <!-- /.box-header -->
                            <div class="dataTables_filter" id="searchDiv">
                                <input placeholder="请输入姓名" name="name" class="form-control" type="search" likeOption="true" /> <input
                                    placeholder="请输入登录名" name="loginName" class="form-control" type="search" likeOption="true" />
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                                    <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" data-btn-type="add">新增</button>
                                    <button type="button" class="btn btn-default" data-btn-type="edit" >编辑</button>
                                    <button type="button" class="btn btn-default" data-btn-type="delete">删除</button>
                                </div>
                            </div>
                            <div class="box-body">
                                <table id="user_table" class="table table-border">
                                </table>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                    <!-- /.tab-pane -->
                    <div class="tab-pane" id="tab-content-edit">
                        <div class="box box-info">
                            <form id="user-form" name="user-form" class="form-horizontal">
                                <input type="hidden" name="id">
                                <%--<input type="hidden" name="version">--%>
                                <input type="hidden" name="isSuperAdmin">
                                <input type="hidden" name="createDateTime" data-flag="date" data-format="yyyy-mm-dd hh:ii:ss">
                                <input type="hidden" name="deleted">
                                <div class="box-body">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="name" class="col-sm-3 control-label">姓名</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="name" name="name" placeholder="姓名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="birthday" class="col-sm-3 control-label">出生日期</label>
                                            <div class="input-group date col-sm-8">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control" data-flag="datepicker" data-format="yyyy-mm-dd" id="birthday" name="birthday"
                                                       placeholder="出生日期">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="telphone" class="col-sm-3 control-label">座机</label>

                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="telphone" name="telphone" placeholder="座机">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email" class="col-sm-3 control-label">Email</label>

                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">性别</label>

                                            <div class="col-sm-8">
                                                <label class="control-label"> <input type="radio" name="sex" data-flag="icheck" class="square-green" value="1"> 男
                                                </label> &nbsp; <label class="control-label"> <input type="radio" name="sex" data-flag="icheck" class="square-green" value="0"> 女
                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="loginName" class="col-sm-3 control-label">登录名</label>

                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="loginName" name="loginName" placeholder="登录名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="mobile" class="col-sm-3 control-label">手机</label>

                                            <div class="col-sm-8">
                                                <!--<input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机"
                                                                                   data-inputmask='"mask": "9999999999999"' data-mask>-->
                                                <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="qq" class="col-sm-3 control-label">QQ</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="qq" name="qq" placeholder="QQ">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer text-right" style="height:50px;">
                                    <!--以下两种方式提交验证,根据所需选择-->
                                    <button type="button" class="btn btn-default" data-btn-type="cancel">取消</button>
                                    <button type="submit" class="btn btn-primary" data-btn-type="save">提交</button>
                                </div>
                                <!-- /.box-footer -->
                            </form>
                        </div>
                        <!-- /.box -->
                    </div>
                    <!-- /.tab-pane -->
                </div>
                <!-- /.tab-content -->
            </div>
            <!-- nav-tabs-custom -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>

<%@ include file="../../common_js.jsp" %>

<script type="text/javascript">
    var userTable;
    var winId="userWin";
    var form=null;
    $(function() {

        form=$("#user-form").form();
        //初始化菜单树
        this.initTree(0);

        //init table and fill data
        userTable = new CommonTable("user_table", "user_list", "searchDiv");

        $("#user-form").bootstrapValidator({
            message : '请输入有效值',
            feedbackIcons : {
                valid : 'glyphicon glyphicon-ok',
                invalid : 'glyphicon glyphicon-remove',
                validating : 'glyphicon glyphicon-refresh'
            },
            submitHandler : function(validator, userform, submitButton) {
                modals.confirm('确认保存？', function() {
                    //Save Data，对应'submit-提交'
                    var params = form.getFormSimpleData();
                    ajaxPost(basePath+'/user/save', params, function(data, status) {
                        if(data.success){
                            if(userTable.getSelectedRowId()){//更新
                                userTable.reloadRowData(userTable.getSelectedRowId());
                            }else{//新增
                                userTable.reloadData();
                            }
                            $("#nav-tab-list").click();
                        }
                    });
                });
            },
            fields : {
                name : {
                    validators : {
                        notEmpty : {
                            message : '请输入姓名'
                        }
                    }
                },
                sex : {
                    validators : {
                        notEmpty : {
                            message : '请选择性别'
                        }
                    }
                },
                birthday : {
                    validators : {
                        notEmpty : {
                            message : '请输入出生日期'
                        },
                        date : {
                            format : $(this).data("format"),
                            message : '请输入有效日期'
                        }
                    }
                },
                loginName : {
                    validators : {
                        notEmpty : {
                            message : '请输入登录名'
                        }
                    }
                },
                email:{
                    validators:{
                        notEmpty:{
                            message:'请输入邮件',
                        },
                        emailAddress:{
                            message:'非法的邮件格式',
                        }

                    }
                }
            }
        });

        //when user click nav-tab-list tab, then set default title
        $("#nav-tab-list").on("click",function(){
            setTitle("用户列表");
        })

        form.initComponent();

        $('button[data-btn-type]').click(function() {
            var action = $(this).attr('data-btn-type');
            var rowId=userTable.getSelectedRowId();
            switch (action) {
                case 'add':
                    form.clearForm();
                    setTitle("新增用户");
                    $("#nav-tab-edit").click();
                    break;
                case 'edit':
                    if(!rowId){
                        modals.info('请选择要编辑的行');
                        return false;
                    }
                    form.clearForm();
                    setTitle("编辑用户【"+userTable.getSelectedRowData().name+"】")
                    $("#nav-tab-edit").click();
                    ajaxPost(basePath+"/user/get",{id:rowId},function(data){
                        form.initFormData(data);
                    });
                    break;
                case 'delete':
                    if(!rowId){
                        modals.info('请选择要删除的行');
                        return false;
                    }
                    modals.confirm("是否要删除该行数据？",function(){
                        ajaxPost(basePath+"/user/delete/"+rowId,null,function(data){
                            if(data.success){
                                //modals.correct("已删除该数据");
                                userTable.reloadRowData();
                            }else{
                                modals.error("用户数据被引用，不可删除！");
                            }
                        });
                    })
                    break;
                case 'cancel':
                    $("#nav-tab-list").click();
                    break;
            }

        });


    });

    function initTree(selectNodeId) {
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
    }

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

    //set title for current tab
    function setTitle(title){
        $("ul.nav-tabs li.header small").text(title);
    }
</script>


