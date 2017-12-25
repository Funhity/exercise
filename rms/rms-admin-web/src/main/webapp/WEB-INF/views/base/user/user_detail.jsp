<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/4
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<section class="content-header">
    <h1>
        <span>员工详情</span>
        <%--<small>新增</small>--%>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">用户管理</li>
        <li class="">员工详情</li>
    </ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-info">
                <div class="box-body">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">员工工号</label>
                            <div class="col-sm-8">
                                <span>${user.code}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">姓名</label>
                            <div class="col-sm-8">
                                <span>${user.realname}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">登录名</label>
                            <div class="col-sm-8">
                                <span>${user.name}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">员工类型</label>
                            <div class="col-sm-8">
                                <span>${user.userType}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">所属机构</label>
                            <div class="col-sm-8">
                                <span>${user.orgName}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">入职日期</label>
                            <div class="col-sm-8">
                                <span>${user.userDetail.joininDate}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">理财师</label>
                            <div class="col-sm-8">
                                <span>${user.financialPlannerName}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">账号状态</label>
                            <div class="col-sm-8">
                                ${user.accountStatus}
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">标签</label>
                            <div class="input-group date col-sm-8">
                                <span>${user.label}</span>
                            </div>
                        </div>


                    </div>
                    <!-- 第二列 -->
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">邮箱</label>
                            <div class="col-sm-8">
                                ${user.email}
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">密码</label>
                            <div class="col-sm-8">

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">手机</label>
                            <div class="col-sm-8">
                                ${user.mobile}
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">性别</label>
                            <div class="col-sm-8">
                                <span>${user.sex}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">角色</label>
                            <div class="col-sm-8">

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">岗位</label>

                            <div class="col-sm-8">
                                <span>${user.userDetail.dutyName}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">直属上级</label>
                            <div class="col-sm-8">

                                <span>${user.roleNames}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">员工状态</label>
                            <div class="col-sm-8">
                                <span>${user.empStatus}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-3 control-label">备注</label>
                            <div class="col-sm-8">
                                <span>${user.userDetail.memo}</span>
                            </div>
                        </div>

                    </div>
                </div>


            </div>
        </div>
    </div>
</section>

<section class="content">
    <h1><span>变更记录</span></h1>

    <div class="box box-info">

    </div>
</section>




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
            levels:2

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
        } else {
            //新增的话，自动获取用户编码，取库中最大ID值+1,前面补0
            ajaxPost(basePath + "/user/getMaxCode", null, function (data) {
                $("#code").val(data);
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

