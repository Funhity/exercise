<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/20
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<section class="content-header">
    <h1>
        <span>用户管理</span>
        <small>新增</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">用户管理</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-info">
                <form id="user-form" name="user-form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <%--<input type="hidden" name="version">--%>
                    <input type='hidden' value='${CSRFToken}' id='csrftoken'>
                    <%--<input type="hidden" id="avatarId" name="avatarId">--%>
                    <div class="box-header">
                        <div class="col-xs-12 text-center">
                            <div class="avatar-container text-center">
                                <img src="${ctx}/resources/common/images/avatar.jpg" id="avatarImg"
                                     class="avatar-img"/>
                            </div>
                            <div>
                                <button type="button" class="btn btn-sm btn-camera" data-btn-type="upload"><i
                                        class="fa fa-camera">&nbsp;上传/更改头像</i></button>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="code" class="col-sm-3 control-label">编号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="编号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="realname" class="col-sm-3 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="realname" name="realname" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthday" class="col-sm-3 control-label">出生日期</label>
                                <div class="input-group date col-sm-8">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" class="form-control" data-flag="datepicker"
                                           data-format="yyyy-mm-dd"
                                           id="birthday" name="birthday"
                                           placeholder="出生日期">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="orgName" class="col-sm-3 control-label">部门</label>

                                <div class="col-sm-6">
                                    <input type="hidden" name="orgCode" id="orgCode">
                                    <input type="text" class="form-control" id="orgName" placeholder="所在部门">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-info" data-flag="org"><i
                                            class="fa fa-sitemap"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label for="telphone" class="col-sm-3 control-label">座机</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input type="text" class="form-control" id="telphone" name="telphone"--%>
                                           <%--placeholder="座机">--%>
                                <%--</div>--%>
                            <%--</div>--%>
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
                                    <label class="control-label"> <input type="radio" name="sex" data-flag="icheck"
                                                                         class="square-green" value="0"> 男
                                    </label> &nbsp; <label class="control-label"> <input type="radio" name="sex"
                                                                                         data-flag="icheck"
                                                                                         class="square-green"
                                                                                         value="1"> 女
                                </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label">登录名</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="登录名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="password" name="password" placeholder="密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mobile" class="col-sm-3 control-label">手机</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userFrom" class="col-sm-3 control-label">用户来源</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="userFrom" name="userFrom" placeholder="用户来源">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-right">
                        <!--以下两种方式提交验证,根据所需选择-->
                        <button type="button" class="btn btn-primary" data-btn-type="cancel">取消</button>
                        <button type="submit" class="btn btn-primary" data-btn-type="save">提交</button>
                    </div>
                    <!-- /.box-footer -->
                </form>
            </div>
        </div>
    </div>
</section>
</div>

<%@ include file="../../common_js.jsp" %>
<script>

    var form = null;
    var id = "${id }";
    var orgcode = "${orgcode }";
    console.log("*********id: " + id);
    $(function () {
        //初始化控件
        form = $("#user-form").form();
        //数据校验
        $("#user-form").bootstrapValidator({
            message: '请输入有效值',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitHandler: function (validator, userform, submitButton) {
                modals.confirm('确认保存？', function () {
                    //Save Data，对应'submit-提交'
                    var params = form.getFormSimpleData();
                    ajaxPost(basePath + '/user/save', params, function (data, status) {
                        console.log("-----data: " + data);
                        if (data.success) {
                            if (id != "0") {//更新
                                gotolist(id);
                            } else {//新增
                                //modals.info("数据保存成功");
                                gotolist();
                            }
                        }
                    });
                });
            },
            fields: {
                code: {
                    validators: {
                        notEmpty: {
                            message: '请输入编号'
                        },
                        remote: {
                            url: basePath + "/user/checkCodeUnique.do",
                            data: function (validator) {
                                return {
                                    code: $('#code').val(),
                                    id: id
                                };
                            },
                            message: '该编码已被使用'
                        }
                    }
                },

                realname: {
                    validators: {
                        notEmpty: {
                            message: '请输入姓名'
                        }
                    }
                },
                sex: {
                    validators: {
                        notEmpty: {
                            message: '请选择性别'
                        }
                    }
                },
                birthday: {
                    validators: {
                        notEmpty: {
                            message: '请输入出生日期'
                        },
                        date: {
                            format: $(this).data("format"),
                            message: '请输入有效日期'
                        }
                    }
                },
                name: {
                    validators: {
                        notEmpty: {
                            message: '请输入用户名'
                        },
                        remote: {
                            url: basePath + "/user/checkNameUnique.do",
                            data: function (validator) {
                                return {
                                    code: $('#name').val(),
                                    id: id
                                };
                            },
                            message: '该用户名已被使用'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '请输入邮件',
                        },
                        emailAddress: {
                            message: '非法的邮件格式',
                        }

                    }
                }
            }
        });
        form.initComponent();
        //初始化组织机构选择器
        $("button[data-flag='org']").org({
            idField: $("#orgCode"),
            nameField: $("#orgName"),
            title:'选择部门',
            levels:4

        })
        //回填id
        if (id != null && id != "") {
            ajaxPost(basePath + "/user/get", {id: id}, function (data) {
                form.initFormData(data);
                $(".content-header h1 small").html("编辑用户【" + data.name + "】");
                //头像回填
//                ajaxPost(basePath + "/user/getAvatar", {userId: id}, function (result) {
//                    setAvatar(result.id, result.src, false);
//                })
                //组织机构回填
                if(data.orgCode){
                    ajaxPost(basePath + "/org/show/"+data.orgCode+".do", null, function (ret) {
                        $("#orgName").val(ret.data.name);
                    })
                }
            })
        }


        //cancel
        $("[data-btn-type='cancel']").click(function () {
            gotolist();
        })

        $("[data-btn-type='upload']").click(function () {
            uploadAvatar();
        })

        $('#password').val("");
    });

    function gotolist(id) {
        window.loadPage(basePath + "/user/page/list?id="+id+"&orgcode="+orgcode);
    }

    var avatarWin = "avatarWin";
    function uploadAvatar() {
        modals.openWin({
            winId: avatarWin,
            title: '上传头像',
            width: '700px',
            url: basePath + "/user/avatar?userId=" + id
        });
    }


    function resetForm() {
        form.clearForm();
        $("#user-form").data('bootstrapValidator').resetForm();
    }

    function setAvatar(avatar_id, avatar_url, isAdd) {
        $("#avatarImg").attr("src", basePath + avatar_url);
        //如果是新增 绑定用户
        if (isAdd) {
            $("#avatarId").val(avatar_id);
        } else {
            $("#avatarId").val(null);
        }
    }
</script>

