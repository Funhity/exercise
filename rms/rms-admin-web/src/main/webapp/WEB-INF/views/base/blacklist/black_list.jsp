<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/19
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入登录名" name="username" class="form-control" type="search" likeOption="true" /> <input
                        placeholder="请输入IP" name="ipAddress" class="form-control" type="search" likeOption="true" />
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" 	data-btn-type="search">查询</button>
                        <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="add">新增</button>
                        <button type="button" class="btn btn-default" data-btn-type="edit" >编辑</button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">删除</button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="black_table" class="table table-bordered table-striped table-hover" style="font-size:13px; ">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
</body>
<%@ include file="../../common_js.jsp" %>

<script>
    //tableId,queryId,conditionContainer
    var blackTable;
    var winId = "blackWin";
    $(function() {
        //init table and fill data
        blackTable = new CommonTable("black_table", "black_list", "searchDiv");

        //button event
        $('button[data-btn-type]').click(function() {
            var action = $(this).attr('data-btn-type');
            var rowId=blackTable.getSelectedRowId();
            switch (action) {
                case 'add':
                    modals.openWin({
                        winId:winId,
                        title:'新增黑名单用户',
                        width:'900px',
                        url:basePath+"/black/edit"
                    });
                    break;
                case 'edit':
                    if(!rowId){
                        modals.info('请选择要编辑的行');
                        return false;
                    }
                    modals.openWin({
                        winId:winId,
                        title:'编辑用户【'+blackTable.getSelectedRowData().name+'】',
                        width:'900px',
                        url:basePath+"/black/edit?id="+rowId,
                    });
                    break;
                case 'delete':
                    if(!rowId){
                        modals.info('请选择要删除的行');
                        return false;
                    }
                    modals.confirm("是否要删除该行数据？",function(){
                        ajaxPost(basePath+"/black/delete/" + rowId, null, function(data){
                            if(data.success){
                                //modals.correct("已删除该数据");
                                blackTable.reloadRowData();
                            }else{
                                modals.error("用户数据被引用，不可删除！");
                            }
                        });
                    })
                    break;
            }

        });
        //form_init();
    })

</script>
