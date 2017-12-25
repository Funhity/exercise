<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/19
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>
    <h5 class="modal-title">新增用户</h5>
</div>

<div class="modal-body">

    <form id="user-form" name="user-form" class="form-horizontal">
        <input type="hidden" name="id">
        <%--<input type="hidden" name="version">--%>
        <input type="hidden" name="isSuperAdmin">
        <input type="hidden" name="createDateTime" data-flag="date" data-format="yyyy-mm-dd hh:ii:ss">
        <input type="hidden" name="deleted">
        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
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
                    <div class="input-group col-sm-8">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input type="text" class="form-control" data-flag="datepicker" data-format="yyyy-mm-dd" name="birthday" id="birthday"
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
        <div class="box-footer text-right">
            <!--以下两种方式提交验证,根据所需选择-->
            <button type="button" class="btn btn-default" data-btn-type="cancel" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-primary" data-btn-type="save">提交</button>
        </div>
        <!-- /.box-footer -->
    </form>

</div>

<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var form =null;
    var id="${id }";
    $(function() {
        //初始化控件
        form=$("#user-form").form();
        //数据校验
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

                    ajaxPost(basePath+'/user/save.do', params, function(data, status) {
                        alert(data);
                        if(data.success){
                            if(id!="0"){//更新
                                modals.hideWin(winId);
                                userTable.reloadRowData(id);
                            }else{//新增
                                //modals.info("数据保存成功");
                                modals.hideWin(winId);
                                userTable.reloadData();
                            }
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
        form.initComponent();
        //回填id
        if(id!="0"){
            ajaxPost(basePath+"/user/get",{id:id},function(data){
                form.initFormData(data);
            })
        }
    });


    function resetForm(){
        form.clearForm();
        $("#user-form").data('bootstrapValidator').resetForm();
    }
</script>
