<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/5
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>
    <h5 class="modal-title">新增机构类别</h5>
</div>

<div class="modal-body">

    <form id="category-form" name="category-form" class="form-horizontal">
        <input type="hidden" name="id">
        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
        <div class="box-body">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="code" class="col-sm-3 control-label">编码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="code" name="code" placeholder="编码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">机构类别</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name" placeholder="机构类别">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">状态</label>
                    <div class="col-sm-8">
                        <label class="control-label">
                            <input type="radio" name="status" data-flag="icheck" class="square-green" value="1" checked> 启用
                        </label> &nbsp;
                        <label class="control-label">
                            <input type="radio" name="status" data-flag="icheck" class="square-green" value="0"> 禁用
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="memo" class="col-sm-3 control-label">描述</label>

                    <div class="col-sm-8">
                        <textarea class="form-control" id="memo" name="memo" placeholder="描述"></textarea>
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

<script>
    //tableId,queryId,conditionContainer
    var form =null;
    var id="${id }";
    $(function() {
        //初始化控件
        form=$("#category-form").form();
        //数据校验
        $("#category-form").bootstrapValidator({
            message : '请输入有效值',
            feedbackIcons : {
                valid : 'glyphicon glyphicon-ok',
                invalid : 'glyphicon glyphicon-remove',
                validating : 'glyphicon glyphicon-refresh'
            },
            submitHandler : function(validator, userform, submitButton) {
                modals.confirm('确认保存？', function() {

                    var params = form.getFormSimpleData();
                    ajaxPost(basePath+'/category/save', params, function(data, status) {
                        if(data.success){
                            if(id != "0"){//更新
                                modals.hideWin(winId);
                                categoryTable.reloadRowData(id);
                            }else{//新增
                                //modals.info("数据保存成功");
                                modals.hideWin(winId);
                                categoryTable.reloadData();
                            }
                        }
                    });
                });
            }
        });
        form.initComponent();
        //回填id
        if(id != "0"){
            ajaxPost(basePath+"/category/get",{id:id},function(data){
                form.initFormData(data);
            })
        }
    });


    function resetForm(){
        form.clearForm();
        $("#category-form").data('bootstrapValidator').resetForm();
    }
</script>

