<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/7
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">

<section class="content-header">
    <h1>
        <span>机构迁移</span>
        <%--<small>机构迁移</small>--%>
    </h1>
    <%--<ol class="breadcrumb">--%>
        <%--<li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>--%>
        <%--<li><a href="#">系统管理</a></li>--%>
        <%--<li class="active">用户管理</li>--%>
    <%--</ol>--%>
</section>
<section class="content">

        <%--<div class="box-header">--%>
            <%--<h3 class="box-title">Hover Data Table</h3>--%>
        <%--</div>--%>
        <!-- /.box-header -->
        <div class="box box-body ">
            <%--<div class="box box-info">--%>
            <form class="form-horizontal" id="org-move-form">
            <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                <div class="row">
                    <div class="col-sm-6"></div>
                    <div class="col-sm-6"></div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <input type="hidden" name="oldCode" id="oldCode" value="${org.code}"/>
                        <table id="user_table" class="table table-border table-hover text-center" style="font-size:13px; ">
                        <%--<table id="example2" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">--%>
                            <thead>
                            <tr role="row">
                                <th class="col-sm-3" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending"></th>
                                <th class="col-sm-3" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending">当前机构信息</th>
                                <th class="col-sm-5" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">迁移后机构信息</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr role="row" class="odd ">
                                <td class="sorting_1"><strong>机构名称：</strong></td>
                                <td>${org.name}</td>
                                <td >
                                    <%--<div class="input-group col-sm-6">--%>
                                        <div class="input-group col-sm-4">
                                            <input type="hidden" name="id" id="id"/>
                                            <input type="hidden" name="code" id="orgCode">
                                            <input type="text" class="form-control" name="name" id="orgName" placeholder="所在部门">
                                        </div>
                                        <div class="input-group col-sm-2">
                                            <button type="button" class="btn btn-info" data-flag="org"><i
                                                    class="fa fa-sitemap"></i>&nbsp;选择
                                            </button>
                                        </div>
                                    <%--</div>--%>
                                </td>

                            </tr>
                            <tr role="row" class="even">
                                <td class=""><strong>负责人姓名：</strong></td>
                                <td>${org.user.realname}</td>
                                <td>
                                    <%--<div class="input-group col-sm-6">--%>
                                    <%--<input type="text" class="form-control" id="username" name="username" placeholder="负责人姓名">--%>
                                    <%--</div>--%>
                                        <div class="input-group col-sm-6">
                                            <select name="manager" id="manager" data-src="/user/getAll.do" data-flag="urlSelector"
                                                    data-placeholder="请选负责人" class="form-control select2" data-text="realname"
                                                    data-value="code" style="width:100%"
                                                    data-blank="true">
                                            </select>
                                        </div>


                                </td>

                            </tr>
                            <tr role="row" class="even">
                                <td class="sorting_1"><strong>员工工号：</strong></td>
                                <td>${org.user.code}</td>
                                <td>
                                    <div class="input-group col-sm-6">
                                        <input type="text" class="form-control" id="usercode" name="usercode" placeholder="员工工号">
                                    </div>
                                </td>
                            </tr>
                            <tr role="row" class="even">
                                <td class="sorting_1"><strong>负责人手机号：</strong></td>
                                <td>${org.user.mobile}</td>
                                <td>
                                    <div class="input-group col-sm-6">
                                        <input type="text" class="form-control" id="usermobile" name="usermobile" placeholder="负责人手机号">
                                    </div>
                                </td>
                            </tr>
                            <tr role="row" class="even">
                                <td class="sorting_1"><strong>机构编码：</strong></td>
                                <td>${org.code}</td>
                                <td>
                                    <div class="input-group col-sm-6">
                                        <input type="text" class="form-control" id="code" name="code" placeholder="机构编码">
                                    </div>
                                </td>
                            </tr>
                            <tr role="row" class="even">
                                <td class="sorting_1"><strong>机构别名：</strong></td>
                                <td>${org.shortName}</td>
                                <td>
                                    <div class="input-group col-sm-6">
                                        <input type="text" class="form-control" id="shortName" name="shortName" placeholder="机构别名">
                                    </div>
                                </td>
                            </tr>

                            <tr role="row" class="even">
                                <td class="sorting_1"><strong>机构类别：</strong></td>
                                <td>${org.orgCategoryNames}</td>
                                <td>
                                    <%--<div class="input-group col-sm-6">--%>
                                        <div class="input-group col-sm-4" id="category_div">
                                            <input type="hidden" name="orgCategoryCodes" id="orgCategoryCodes">
                                            <input type="text" class="form-control" id="orgCategoryNames" name="orgCategoryNames" placeholder="机构类别">
                                        </div>
                                        <div class="input-group col-sm-2" id="choose">
                                            <button type="button" class="btn btn-info" data-flag="category"><i
                                                    class="fa fa-sitemap"></i>&nbsp;选择
                                            </button>
                                        </div>
                                    <%--</div>--%>
                                </td>
                            </tr>
                            <tr role="row" class="even">
                                <td class="sorting_1"><strong>人数上限：</strong></td>
                                <td>${org.maxuser == -1 ? '无上限' : org.maxuser}</td>
                                <td>
                                    <div class="input-group col-sm-6">
                                        <input type="text" class="form-control" id="maxuser" name="maxuser" placeholder="人数上限">
                                    </div>
                                </td>
                            </tr>

                            <tr role="row" class="odd">
                                <td class="sorting_1"><strong>立即生效：</strong></td>
                                <td></td>
                                <td>
                                    <div class="input-group date col-sm-6">
                                        <div class="col-sm-6">
                                            <label class="control-label">
                                                <input type="radio" name="isEffect" class="square-green" checked="checked" value="1"> 是
                                            </label> &nbsp;&nbsp;&nbsp;
                                            <label class="control-label">
                                                <input type="radio" name="isEffect" class="square-green" value="0"> 否
                                            </label>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                            <tr role="row" class="odd" id="effectiveDate_div" style="display: none">
                                <td class="sorting_1"><strong>生效日期：</strong></td>
                                <td><fmt:formatDate value="${org.effectiveDate}" pattern="yyyy-MM-dd" /></td>
                                <td>
                                    <div class="input-group date col-sm-6" id="dateDiv">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text" class="form-control" data-flag="datepicker"
                                               data-format="yyyy-mm-dd"
                                               id="effectiveDate" name="effectiveDate"
                                               placeholder="生效日期" >
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
                </div>

                <div class="box-footer text-right col-sm-10">
                    <!--以下两种方式提交验证,根据所需选择-->
                    <button type="button" class="btn btn-primary" data-btn-type="cancel">取消</button>
                    <button type="submit" class="btn btn-primary" data-btn-type="save">提交</button>
                </div>

            </div>
            </form>
        </div>
        <!-- /.box-body -->
</section>
</body>

<%@ include file="../../common_js.jsp" %>

<script>
var form = null;
$(function () {
    //初始化控件
    form = $("#org-move-form").form();

    $("#org-move-form").bootstrapValidator({
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, orgform, submitButton) {
            modals.confirm('确认保存？', function () {
                var params = self.form.getFormSimpleData();
                console.log("--------params: " + params);
                ajaxPost(basePath + '/org/move.do', params, function (data, status) {
                    if (data.success) {
                        if (data.success) {
                            gotolist();
                        }
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
            }
        }
    });
    //form.initComponent();

    //选择迁移后的机构暂时不可编辑
//    formReadonly();

    //初始化组织机构选择器
    $("button[data-flag='org']").org({
        idField: $("#orgCode"),
        nameField: $("#orgName"),
        title:'选择部门',
        levels:1,
        isFill:true
    })

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

    //cancel
    $("[data-btn-type='cancel']").click(function () {
        gotolist();
    })

    $("#dateDiv").removeAttr("style");

    $("input:radio[name='isEffect']").click(function(){
        if($(this).val() == 0) {
            $("#effectiveDate_div").show();
        } else {
            $("#effectiveDate_div").hide();
            $("#effectiveDate").val("");
        }
    });

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

//    $("#manager").select2({
//        minimumResultsForSearch: Infinity,
//        placeholder: "请选择", //默认提示语
//        templateSelection: formatState
//    }).val("00005").trigger("change");

    //负责人选择更新用户编号及手机
//    $("#manager").select2({
//        templateSelection: formatState
//    }).trigger("change");

})

//更新用户信息
function formatState (state) {
    console.log(state.id);
    console.log(state.text);
    if (!state.id) {
        return state.text;
    }

    ajaxPost(basePath + "/user/get", {code: state.id}, function (data) {
        $("#usercode").val(data.code);
        $("#usermobile").val(data.mobile);
    })

//    var markup = "<div>"+state.text+"</div>";
    return state.text;
};

function gotolist(id) {
    window.loadPage(basePath + "/org/tree.jhtml");
}

function formReadonly() {

    //所有文本框只读
    $("input[name],textarea[name]").attr("readonly", "readonly");

    $("#orgName").removeAttr("readonly");
    $("#orgCategoryNames").removeAttr("readonly");
    $("#effectiveDate").removeAttr("readonly");
    //还原校验框
    if ($("#org-move-form").data('bootstrapValidator'))
        $("#org-move-form").data('bootstrapValidator').resetForm();
}

</script>

