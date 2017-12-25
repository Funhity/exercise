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
                    <div class="box-body">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="code" class="col-sm-3 control-label">员工工号<span style="color:red">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="员工工号" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="realname" class="col-sm-3 control-label">姓名<span style="color:red">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="realname" name="realname" placeholder="姓名">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label">登录名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="登录名" readonly="readonly">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">员工类型</label>
                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="userType" data-flag="icheck" class="square-green" value="1" checked> 正式
                                    </label> &nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="userType" data-flag="icheck" class="square-green" value="0"> 非正式
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="orgName" class="col-sm-3 control-label">所属机构</label>

                                <div class="col-sm-6">
                                    <input type="hidden" name="orgCode" id="orgCode">
                                    <input type="text" class="form-control" id="orgName" name="orgName" placeholder="所在部门">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-info" data-flag="org"><i
                                            class="fa fa-user"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userDetail.joininDate" class="col-sm-3 control-label">入职日期</label>
                                <div class="input-group date col-sm-8">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" class="form-control" data-flag="datepicker"
                                           data-format="yyyy-mm-dd"
                                           id="userDetail.joininDate" name="userDetail.joininDate"
                                           placeholder="入职日期">
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label for="realname" class="col-sm-3 control-label">上级理财师</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input type="text" class="form-control" id="financialPlanner" name="financialPlanner" placeholder="上级理财师">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上级理财师<span style="color:red">*</span></label>
                                <div class="col-sm-6">
                                    <input type="hidden" id="financialPlanner" name="financialPlanner">
                                    <input type="text" class="form-control" id="financialPlannerName"  name="financialPlannerName" readonly="readonly"
                                           placeholder="请选择理财师">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary" data-btn-type="selectPlanner"><i
                                            class="fa fa-user"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>



                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号状态</label>
                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="accountStatus" data-flag="icheck" class="square-green" value="1" checked> 正常
                                    </label> &nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="accountStatus" data-flag="icheck" class="square-green" value="0"> 关闭
                                    </label>
                                    <label class="control-label">
                                        <input type="radio" name="accountStatus" data-flag="icheck" class="square-green" value="2"> 锁定
                                    </label>
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label for="birthday" class="col-sm-3 control-label">出生日期</label>--%>
                                <%--<div class="input-group col-sm-8">--%>
                                    <%--<span class="input-group-addon"><i class="fa fa-calendar"></i></span>--%>
                                    <%--<input type="text" class="form-control" data-flag="datepicker" data-format="yyyy-mm-dd" name="birthday" id="birthday"--%>
                                           <%--placeholder="出生日期">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label for="label" class="col-sm-3 control-label">标签</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input type="text" class="form-control" id="label" name="label" placeholder="标签">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标签</label>
                                <div class="col-sm-6">
                                    <input type="hidden" id="labelIds" name="labelIds">
                                    <input type="text" class="form-control" id="label"  name="label" readonly="readonly"
                                           placeholder="请选择标签">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary" data-btn-type="selectLabel"><i
                                            class="fa fa-user"></i>&nbsp;选择
                                    </button>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="effectiveDate" class="col-sm-3 control-label">生效日期</label>
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
                                <label for="email" class="col-sm-3 control-label">邮箱</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="password" name="password" placeholder="${id != null ? '不修改密码无需填写': '密码' }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mobile" class="col-sm-3 control-label">手机</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别</label>

                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="sex" data-flag="icheck" class="square-green" value="0" checked> 男
                                    </label> &nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="sex" data-flag="icheck" class="square-green" value="1"> 女
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色</label>
                                <%--<input type="hidden" id="roleNames" name="roleNames">--%>
                                <div class="col-sm-8">
                                    <select name="roles" id="roles" data-src="/role/getAll" data-flag="urlSelector"
                                            data-placeholder="请选择角色" class="form-control select2" style="width:100%"
                                            data-blank="true" multiple></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userDetail.dutyName" class="col-sm-3 control-label">岗位</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="userDetail.dutyName" name="userDetail.dutyName" placeholder="岗位">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="manager" class="col-sm-3 control-label">直属上级</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="manager" name="manager" placeholder="直属上级" readonly="readonly">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">员工状态</label>
                                <div class="col-sm-8">
                                    <label class="control-label">
                                        <input type="radio" name="empStatus" data-flag="icheck" class="square-green" value="1" checked> 在职
                                    </label> &nbsp;
                                    <label class="control-label">
                                        <input type="radio" name="empStatus" data-flag="icheck" class="square-green" value="0"> 离职
                                    </label>
                                    <label class="control-label">
                                        <input type="radio" name="empStatus" data-flag="icheck" class="square-green" value="2"> 休假
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userDetail.memo" class="col-sm-3 control-label">备注</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="userDetail.memo" name="userDetail.memo" placeholder="备注"></textarea>
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label for="userFrom" class="col-sm-3 control-label">用户来源</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input type="text" class="form-control" id="userFrom" name="userFrom" placeholder="用户来源">--%>
                                <%--</div>--%>
                            <%--</div>--%>
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

    var currid = "${currid }";
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

                    //拼装角色列表名称
//                    var roleNames="";
//                    $("select[name='roles']").next().find("li[class='select2-selection__choice']").each(function(i,o){
//                        roleNames += $(o).attr('title') + ",";
//                    })
//                    if(roleNames.length > 0) {
//                        roleNames = roleNames.substring(0, roleNames.length - 1);
//                        console.log("roleNames：" + roleNames);
//                        $("#roleNames").val(roleNames);
//                    }

                    //Save Data，对应'submit-提交'
                    var params = form.getFormSimpleData();

                    ajaxPostStr(basePath + '/user/save', params, function (data, status) {
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
                        stringLength: {
//                            min: 11,
                            max: 50,
                            message: '编号最多不能超过50个字符'
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
                        },
                        stringLength: {
//                            min: 11,
                            max: 50,
                            message: '姓名不能超过25个字'
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
//                name: {
//                    validators: {
//                        notEmpty: {
//                            message: '请输入用户名'
//                        },
//                        remote: {
//                            url: basePath + "/user/checkNameUnique.do",
//                            data: function (validator) {
//                                return {
//                                    code: $('#name').val(),
//                                    id: id
//                                };
//                            },
//                            message: '该用户名已被使用'
//                        }
//                    }
//                },
                mobile: {
                    validators: {
                        notEmpty: {
                            message: '手机号码不能为空'
                        },
                        regexp: {
                            regexp: /^1[3|4|5|7|8]{1}[0-9]{9}$/,
                            message: '请输入正确的手机号码'
                        },
                        remote: {
                            url: basePath + "/user/checkMobileUnique.do",
                            data: function (validator) {
                                return {
                                    code: $('#mobile').val(),
                                    id: id
                                };
                            },
                            message: '该号码已被使用'
                        }
                    }
                },
                email: {
                    validators: {
//                        notEmpty: {
//                            message: '请输入邮件',
//                        },
                        stringLength: {
                            max: 50,
                            message: '邮箱最多不能超过50个字符'
                        },
                        emailAddress: {
                            message: '非法的邮件格式',
                        }
                    }
                },
                "userDetail.dutyName": {
                    validators: {
                        notEmpty: {
                            message: '请输入岗位名称',
                        },
                        stringLength: {
                            max: 50,
                            message: '岗位不能超过50个字符'
                        }
                    }
                },
                "userDetail.joininDate": {
                    validators: {
                        notEmpty: {
                            message: '请输入入职日期',
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
            levels:1

        })
        //回填id
        if (id != null && id != "") {
            ajaxPost(basePath + "/user/get", {id: id}, function (data) {

                //获取角色ID
                if (data.roles) {
                    data.roles = data.roles.split(",");
                }

                form.initFormData(data);
                $(".content-header h1 small").html("编辑用户【" + data.realname + "】");
                //头像回填
//                ajaxPost(basePath + "/user/getAvatar", {userId: id}, function (result) {
//                    setAvatar(result.id, result.src, false);
//                })
                //组织机构回填
                if(data.orgCode){
                    ajaxPost(basePath + "/org/get/"+data.orgCode+".do", null, function (ret) {
                        //$("#orgName").val(ret.name);
                        //负责人信息
                        if(ret.user) {
                            $("#manager").val(ret.user.realname);
                        }
                    })
                }
                //理财师数据回填
                if(data.financialPlanner && data.financialPlanner != 0){
                    ajaxPost(basePath + "/user/get", {id: data.financialPlanner}, function (ret) {
                        $("#financialPlannerName").val(ret.realname);
                    })
                }

            })
        } else {
            //新增的话，自动获取用户编码，取库中最大ID值+1,前面补0
            ajaxPost(basePath + "/user/getMaxCode", null, function (data) {
                $("#code").val(data);
            })
        }

        //隐藏密码
        $('#password').val("");

        //cancel
        $("[data-btn-type='cancel']").click(function () {
            gotolist();
        })

        $("[data-btn-type='upload']").click(function () {
            uploadAvatar();
        })

        /**
         * 输入姓名自动生产登陆名
         */
        $("#realname").blur(function(){
            console.log($("#realname").val());
            ajaxPost(basePath + "/user/getName.do", {"realname": $("#realname").val()}, function (data) {
                $("#name").val(data);
            })
        });

        //选择理财师
        $("button[data-btn-type='selectPlanner']").click(function () {
            var ids = $("#financialPlanner").val() || 0;
            modals.openWin({
                winId: 'userSelectWin',
                url: basePath + '/user/select/0/' + ids + '/setPlanner',
                width: '850px',
                title: "选择理财师"
            })
        });

        //选择标签
        $("button[data-btn-type='selectLabel']").click(function () {
            var ids = $("#labelIds").val() || 0;
            modals.openWin({
                winId: 'labelSelectWin',
                url: basePath + '/user/label/1/' + ids + '/setLabel',
                width: '850px',
                title: "选择理财师"
            })
        });

        initSelectCss();

        //生效时间不能小于当前时间
        $('#effectiveDate').datepicker({
            todayBtn : "linked",
            language: 'zh-CN',
            autoclose : true,
            todayHighlight : true,
            startDate: '+1',
            endDate : new Date()
        }).on('changeDate',function(e){
            var startTime = e.date;
            if(startTime < new Date()) {
                modals.info("生效时间不可小于当前时间");
                $('#effectiveDate').val("");
            }
        });

    });

    //修改select样式
    function initSelectCss () {
        //修改选择button样式
        $(".btn-info").css("background-color","#3c8dbc");
    }

    function gotolist(id) {
        window.loadPage(basePath + "/user/page/list?id="+id+"&orgcode="+orgcode);
    }

    //理财师数据回填
    function setPlanner(uid, uname) {
//        if ($("#financialPlanner").val() && userId == $("#financialPlanner").val()) {
        if (uid == currid) {
            modals.warn("理财师人不能是自己");
            return;
        }
        $("#financialPlanner").val(uid);
        $("#financialPlannerName").val(uname);
    }

    //理财师数据回填
    function setLabel(lid, lname) {
        $("#labelIds").val(lid);
        $("#label").val(lname);
    }

//    var avatarWin = "avatarWin";
//    function uploadAvatar() {
//        modals.openWin({
//            winId: avatarWin,
//            title: '上传头像',
//            width: '700px',
//            url: basePath + "/user/avatar?userId=" + id
//        });
//    }


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

