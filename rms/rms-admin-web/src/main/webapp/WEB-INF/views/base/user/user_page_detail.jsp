<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/18
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>


<section class="content-header">
    <h1>
        <span>用户管理</span>
        <small>详情</small>
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
                <form id="user-detail-form" name="user-detail-form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <%--<input type="hidden" name="version">--%>
                    <input type='hidden' value='${CSRFToken}' id='csrftoken'>
                    <div class="box-body">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="code" class="col-sm-3 control-label">员工工号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="员工工号" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="realname" class="col-sm-3 control-label">姓名</label>
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

                                <div class="col-sm-6" id="org_div">
                                    <input type="hidden" name="orgCode" id="orgCode">
                                    <input type="text" class="form-control" id="orgName" name="orgName" placeholder="所在部门">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-info" data-flag="org" id="org_btn"><i
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

                            <div class="form-group">
                                <label class="col-sm-3 control-label">上级理财师</label>
                                <div class="col-sm-6" id="planner_div">
                                    <input type="hidden" id="financialPlanner" name="financialPlanner">
                                    <input type="text" class="form-control" id="financialPlannerName"  name="financialPlannerName" readonly="readonly"
                                           placeholder="请选择理财师">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary" data-btn-type="selectPlanner" id="planner_btn"><i
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

                            <div class="form-group">
                                <label for="label" class="col-sm-3 control-label">标签</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="label" name="label" placeholder="标签">
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

                            <%--<div class="form-group">--%>
                                <%--<label for="password" class="col-sm-3 control-label">密码</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input type="text" class="form-control" id="password" name="password" placeholder="${id != null ? '不修改密码无需填写': '密码' }">--%>
                                <%--</div>--%>
                            <%--</div>--%>

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
                    <%--<div class="box-footer text-right">--%>
                        <%--<!--以下两种方式提交验证,根据所需选择-->--%>
                        <%--<button type="button" class="btn btn-primary" data-btn-type="cancel">取消</button>--%>
                        <%--<button type="submit" class="btn btn-primary" data-btn-type="save">提交</button>--%>
                    <%--</div>--%>
                    <!-- /.box-footer -->
                </form>
            </div>
        </div>
    </div>

    <h3><span>变更记录</span></h3>

    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <%--<input placeholder="请输入登录名" name="name" class="form-control" type="search" likeOption="true"/>--%>
                    <input placeholder="请输入操作员名" name="updateUser" id="updateUser" class="form-control" type="search"/>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-btn-type="search">查询</button>
                        <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="user_update_table" class="table table-border table-hover" style="font-size:13px; ">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.col -->
    </div>
</section>



<%@ include file="../../common_js.jsp" %>
<script>

    var userUpdateTable;
    var form = null;
//    var winId = "userUpdateWin";
    var id = "${id }";
//    console.log("*********id: " + id);
    $(function () {
        //初始化控件
        form = $("#user-detail-form").form();
        //数据校验
        form.initComponent();

        //回填id
        if (id != null && id != "") {
            ajaxPost(basePath + "/user/get", {id: id}, function (data) {
                //获取角色ID
                if (data.roles) {
                    data.roles = data.roles.split(",");
                }

                form.initFormData(data);
                $(".content-header h1 small").html("用户【" + data.realname + "】详情");
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
        }

        formReadonly();
        initSelectCss();

        userUpdateTable = new CommonTable("user_update_table", "user_update_list", "searchDiv");

    });

    //修改select样式
    function initSelectCss () {
        //修改选择button样式
        $(".btn-info").css("background-color","#3c8dbc");
        //修改select默认边框样式
//        $(".select2-container--default .select2-selection--multiple").css("border","1px solid #ccc");
//        $(".select2-container--default .select2-selection--multiple").css("border-radius","0");
//        $(".select2-container--default .select2-selection--multiple").css("height","34px");
//        $(".select2-selection__rendered").css("padding","2px 5px");
//        $(".select2-container .select2-selection--multiple").css("height","34px");
//        $(".select2-container--default .select2-selection--multiple .select2-selection__choice").css("background-color","#3c8dbc");
    }
    //设置form为只读
    function formReadonly() {
        //所有文本框只读
        $("input[name],textarea[name]").attr("readonly", "readonly");
        $("input[type='radio']").attr("disabled","disabled");

        $("#roles").attr("disabled","disabled");
        $("#userDetail\\.joininDate").attr("disabled","true");
        $("#effectiveDate").attr("disabled","true");

        $("#org_btn").hide();//机构类别选择按钮隐藏
        $("#planner_btn").hide();//机构类别选择按钮隐藏
        $("#org_div").attr("class", "col-sm-8");
        $("#planner_div").attr("class", "col-sm-8");

        //查询输入框不需要有readonly
        $("#updateUser").removeAttr("readonly");
    }


    function gotolist(id) {
        window.loadPage(basePath + "/user/page/list?id="+id+"&orgcode="+orgcode);
    }

    function fnRecordLink(value) {
//        var link =  "<a href=" + basePath + "/user/page/compar?rid=" + value + ">查看</a>";
        var link =  "<a href='javascript:void(0);' onclick='recordCompar("+value+")'>查看</a>";
        return link;

    }

    function recordCompar(value) {
//        window.loadPage(basePath + "/user/page/compar?rid="+value+"&uid="+id);
        modals.openWin({
            winId:'userUpdateWin',
            title:'变更记录对比',
            width:'800px',
            //height:'600px',
            url:basePath+"/user/compar?rid="+value+"&uid="+id
        });
    }


</script>


