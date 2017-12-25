<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/26
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>
    <h5 class="modal-title">新增数据权限</h5>
</div>

<div class="modal-body">

    <form id="ff-form" name="ff-form" class="form-horizontal">
        <input type="hidden" name="id" id="id">
        <%--<input type="hidden" name="version">--%>
        <input type="hidden" name="createDateTime" data-flag="date" data-format="yyyy-mm-dd hh:ii:ss">
        <input type="hidden" name="roleId" value="${roleId_ff}">
        <input type="hidden" name="resourceId" value="${resourceId_ff}">
        <div class="box-body">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="funcKey" class="col-sm-2 control-label">条件名称</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="funcKey" name="funcKey" placeholder="条件名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="funcOperator" class="col-sm-2 control-label">操作符</label>
                    <div class="col-sm-9">
                        <select id="funcOperator" name="funcOperator" class="form-control">
                            <option selected="selected" value="eq">eq</option>
                            <option selected="selected" value="like">like</option>
                            <option selected="selected" value="not_eq">not_eq</option>
                            <option selected="selected" value="gt">gt</option>
                            <option selected="selected" value="ge">ge</option>
                            <option selected="selected" value="lt">lt</option>
                            <option selected="selected" value="le">le</option>
                            <option selected="selected" value="in">in</option>
                            <option selected="selected" value="not_in">not_in</option>
                            <option selected="selected" value="between">between</option>
                            <option selected="selected" value="null">null</option>
                            <option selected="selected" value="not_null">not_null</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="funcValue" class="col-sm-2 control-label">值</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="funcValue" name="funcValue" placeholder="值">
                    </div>
                </div>
                <div class="form-group">
                    <label for="classType" class="col-sm-2 control-label">类型</label>
                    <div class="col-sm-9">
                        <select id="classType" name="classType" class="form-control">
                            <option selected="selected" value="java.lang.String">java.lang.String</option>
                            <option selected="selected" value="java.util.Date">java.util.Date</option>
                            <option selected="selected" value="java.lang.Integer">java.lang.Integer</option>
                            <option selected="selected" value="java.lang.Double">java.lang.Double</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sortCode" class="col-sm-2 control-label">排序</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="sortCode" name="sortCode" placeholder="排序">
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">是否可用</label>
                    <div class="col-sm-9">
                        <label class="control-label">
                            <input type="radio" name="deleteMark" class="square-green" checked="checked"
                                   value="0"> 启用
                        </label> &nbsp;&nbsp;&nbsp;
                        <label class="control-label">
                            <input type="radio" name="deleteMark" class="square-green"
                                   value="1"> 禁用
                        </label>
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
        form=$("#ff-form").form();
        //数据校验
        $("#ff-form").bootstrapValidator({
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
                    ajaxPost(basePath+'/rolefunc/ff/save', params, function(data, status) {
                        if(data.success){
                            if(id!="0"){//更新
                                modals.closeWin("ffWin");
                                ffTable.reloadRowData(id);
                            }else{//新增
                                //modals.info("数据保存成功");
                                modals.closeWin("ffWin");
                                ffTable.reloadData();
                            }
                        }
                    });
                });
            },
            fields : {
                key : {
                    validators : {
                        notEmpty : {
                            message : '请输入条件名称'
                        }
                    }
                },
                operator : {
                    validators : {
                        notEmpty : {
                            message : '请选择操作符'
                        }
                    }
                },
                value : {
                    validators : {
                        notEmpty : {
                            message : '请输入值'
                        }
                    }
                },
                classType : {
                    validators : {
                        notEmpty : {
                            message : '请选择类型'
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
        if(id!="0"){
            ajaxPost(basePath+"/rolefunc/ff/get/"+id,null,function(data){
                form.initFormData(data);
            })
        }
    });


    function resetForm(){
        form.clearForm();
        $("#ff-form").data('bootstrapValidator').resetForm();
    }
</script>

