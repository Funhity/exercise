<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/25
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>
    <h5 class="modal-title">新增角色</h5>
</div>

<div class="modal-body">

    <form id="role-form" name="role-form" class="form-horizontal">
        <input type="hidden" name="id" id="id">
        <%--<input type="hidden" name="version">--%>
        <input type="hidden" name="isSuperAdmin">
        <input type="hidden" name="createDateTime" data-flag="date" data-format="yyyy-mm-dd hh:ii:ss">
        <input type="hidden" name="deleted">
        <div class="box-body">
            <div class="col-md-12">
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
                    <label for="parentCode" class="col-sm-2 control-label">父级编码</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="parentCode" name="parentCode" placeholder="编码">
                    </div>
                </div>

                <div class="form-group">
                    <label for="orgName" class="col-sm-2 control-label">部门</label>

                    <div class="col-sm-7">
                        <input type="hidden" name="orgCode" id="orgCode">
                        <input type="text" class="form-control" id="orgName" placeholder="所在部门">
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-info" data-flag="org"><i
                                class="fa fa-sitemap"></i>&nbsp;选择
                        </button>
                    </div>
                </div>
                <%--<div class="form-group">
                    <label for="sysName" class="col-sm-3 control-label">系统</label>

                    <div class="col-sm-6">
                        <input type="hidden" name="sysCode" id="sysCode">
                        <input type="text" class="form-control" id="sysName" placeholder="所属系统">
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-info" data-flag="sys"><i
                                class="fa fa-sitemap"></i>&nbsp;选择
                        </button>
                    </div>
                </div>--%>

                <div class="form-group">
                    <label for="sortCode" class="col-sm-2 control-label">排序</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="sortCode" name="sortCode" placeholder="排序">
                    </div>
                </div>
                <div class="form-group">
                    <label for="deleteMark" class="col-sm-2 control-label">是否可用</label>
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
            </div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer text-right">
            <button type="button" class="btn btn-default" data-btn-type="cancel" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-primary" data-btn-type="save">提交</button>
        </div>
        <!-- /.box-footer -->
    </form>

</div>
</body>

<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var form = null;
    var id="${id }";
    $(function() {
        //初始化控件
        form = $("#role-form").form();
        //数据校验
        $("#role-form").bootstrapValidator({
            message : '请输入有效值',
            feedbackIcons : {
                valid : 'glyphicon glyphicon-ok',
                invalid : 'glyphicon glyphicon-remove',
                validating : 'glyphicon glyphicon-refresh'
            },
            submitHandler : function(validator,roleform, submitButton) {
                modals.confirm('确认保存？', function() {
                    //Save Data，对应'submit-提交'
                    var params = form.getFormSimpleData();
                    ajaxPost(basePath+'/role/save', params, function(data, status) {
                        if(data.success){
                            if(id != "0"){//更新
                                console.log("-------更新: " + id);
                                modals.closeWin(winId);
                                roleTable.reloadRowData(id);
                            }else{//新增
                                console.log("-------新增: " + id);
                                //modals.info("数据保存成功");
                                modals.closeWin(winId);
                                roleTable.reloadData();
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
                code : {
                    validators : {
                        notEmpty : {
                            message : '请输入编码'
                        },
                        remote:{
                            url:basePath+"/role/checkCodeUnique",
                            data: function(validator) {
                                return {
                                    code:$('#code').val(),
                                    id:$('#id').val()
                                };
                            },
                            message:'该编码已被使用'
                        }
                    }
                },
                sort : {
                    validators : {
                        notEmpty : {
                            message : '请输入排序'
                        },
                        integer:{
                            message:'请输入整数'
                        },
                        greaterThan:{
                            value:0,
                            inclusive:true,
                            message:'请输入大于0的整数'
                        }
                    }
                }
            }
        });
        form.initComponent();
        //回填id
        if(id != "0"){
            console.log("----id; " + id);
            ajaxPost(basePath+"/role/get",{id:id},function(data){
                form.initFormData(data);

                //组织机构回填
                if(data.orgCode){
                    ajaxPost(basePath + "/org/show/"+data.orgCode+".do", null, function (ret) {
                        $("#orgName").val(ret.data.name);
                    })
                }

            })
        }

        //初始化组织机构选择器
        $("button[data-flag='org']").org({
            idField: $("#orgCode"),
            nameField: $("#orgName"),
            title:'选择部门',
            levels:4

        })

//        //初始化组织机构选择器
//        $("button[data-flag='sys']").sys({
//            idField: $("#orgCode"),
//            nameField: $("#orgName"),
//            title:'选择系统',
//            levels:4
//
//        })

    });


    function resetForm(){
        form.clearForm();
        $("#role-form").data('bootstrapValidator').resetForm();
    }
</script>
