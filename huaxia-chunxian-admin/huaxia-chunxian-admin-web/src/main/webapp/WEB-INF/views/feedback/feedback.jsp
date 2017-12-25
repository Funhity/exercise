<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>

<div class="wrapper">
    <section class="content">
        <div class="box box-primary">
           
            
            <div class="box-body">
                <div class="layui-row">
                 <form id="searchForm" class="layui-form" action="">
                        <div class="layui-input-inline">
                        <div class="layui-form-item">
                    	</div>
                        <div class="layui-inline">
							<div class="layui-input-inline">
								<input id="searchName" type="text" id="type" name="searchName" value="" lay-verify="type"
                                       placeholder="请填写姓名" autocomplete="off" class="layui-input" maxlength="10">
							</div>
						</div>
						
						<div class="layui-inline">
							<div class="layui-input-inline">
								<input id="searchPhone" type="text" id="type" name="searchPhone" value="" lay-verify="type"
                                       placeholder="请填写手机号" autocomplete="off" class="layui-input" maxlength="11">
							</div>
						</div>
						
						<div class="layui-inline">
							<div class="layui-input-inline">
								<select id="searchFeedbackType" name="searchFeedbackType" lay-filter="searchFeedbackType" id="searchFeedbackType">
								<option value="">请选择类别</option>
								</select>
							</div>
						</div>
                        
                        <div class="layui-inline">
							<div class="layui-input-inline">
								<input type="text" name="startDate" id="searchStartDate" lay-verify="date" 
								placeholder="请填写提交开始时间" autocomplete="off" class="layui-input" readonly="readonly">
							</div>
						</div>
						
						<div class="layui-inline">
							<div class="layui-input-inline">
								<input type="text" name="endDate" id="searchEndDate" lay-verify="date" 
								placeholder="请填写提交结束时间" autocomplete="off" class="layui-input" readonly="readonly">
							</div>
						</div>
                    </div>
                    <div class="layui-form-item">
                    </div>
                    </form>
                    <div class="layui-form-item">
                            <div class="layui-input-inline">
                                <div class="layui-btn-group">
                                    <button class="layui-btn" data-type="searchTable">搜索</button>
                                </div>
                                <button class="layui-btn layui-btn-primary" id="searchBtn" type="button" data-type="clearTable">重置</button>
                            </div>
					</div>
                </div>
            </div>
        
        	
        </div>
        <!--//#region --------------搜索---------------- -->

        <table class="layui-table" id="LAY_table_feedback" lay-filter="feedbackTable"></table>
        <div id="page" class="layui-page"></div>

    </section>
</div>

<script>
    layui.use(['laypage', 'layer', 'laydate', 'table', 'jquery', 'form'], function () {
        var $ = layui.jquery,
            table = layui.table,
            form = layui.form,
            laydate = layui.laydate;
        var baseurl = "${ctx}/feedback/feedbacks";
        
        
        //日期
        var nowTime = new Date().valueOf();
        var searchStartDate = laydate.render({
            elem: '#searchStartDate',
            max: nowTime,
            btns: ['clear', 'confirm'],
            done: function(value, date){
            	searchEndDate.config.min = date;////开始日选好后，重置结束日的最小日期
            	searchEndDate.config.min.month = date.month -1;
            }
        });
        var searchEndDate = laydate.render({
            elem: '#searchEndDate',
            max: nowTime,
            btns: ['clear', 'confirm'],
            done: function(value, date){
            	if($.trim(value) == ''){
            		searchStartDate.config.max = max;
                    return;
                }
            	searchStartDate.config.max = date;//结束日选好后，重置开始日的最大日期
            	searchStartDate.config.max.month = date.month -1;
            }
        }); 
        
        $.ajax({
        	url: '${ctx}/feedback/feedbackType',
        	type: 'get',
        	dataType: 'json',
        	contentType: 'application/json;charset=utf-8',
        	success: function (data) {
        		if (data.code == '0000') {
        			var html = '';
        			for (var i=0; i<data.data.length;i++){
        				html+='<option value='+data.data[i].id+'>'+data.data[i].title+'</option>';
        			}
        			$("#searchFeedbackType").append(html);
        			form.render("select");
        		} else {
                    //权限403
                    if(data.code === 0) {
                        window.location.href="${ctx}/main/login";
                        return false;
                    } else {
                        layer.msg(data.msg);
                    }
				}
        	},
        	});
        
        
        table.render({
            elem: "#LAY_table_feedback",
            url: baseurl,
            cols: [
                [
                {
                    field: "name",
                    title: "姓名"
                },
                {
                    field: "phone",
                    title: "手机"
                },
                {
                    field: "feedbackType",
                    title: "类别"
                },
                {
                    field: "content",
                    title: "内容"
                },
                {
                    field: "createTime",
                    title: "提交时间",
                    datetimeformat:true
                }]
            ],
            cellMinWidth: 50,            
            page: true,
            limits: [10, 15, 20],
            limit: 10,
            even: true,
            id: "feedbackTable"
        });
        
        
        var $ = layui.jquery,
        active = {
            searchTable: function () {
                var wheres = {};
                
                    wheres.searchName = $("#searchName").val();
                    wheres.searchPhone = $("#searchPhone").val();
                    wheres.searchFeedbackType = $("#searchFeedbackType").val();
				if ($("#searchStartDate").val() != '') {
                    wheres.searchStartDate = $("#searchStartDate").val()+" 00:00:00";
				}else{
					wheres.searchStartDate = "";
				}
				if ($("#searchEndDate").val() != '') {
                    wheres.searchEndDate = $("#searchEndDate").val()+" 23:59:59"; 
				}else{
					wheres.searchEndDate = "";
				}
                table.reload('feedbackTable', {
                    where: wheres
                });
            },
            clearTable : function() {
                var formBox=$("#searchForm");
                formBox[0].reset();
            }
        };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    })

</script>