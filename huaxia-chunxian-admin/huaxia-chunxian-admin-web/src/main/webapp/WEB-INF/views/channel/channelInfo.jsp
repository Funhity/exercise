<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%@ include file="../layouts/form_lead_in.jsp"%>
<script>
  function getChangeCode(){
		$.ajax({
			 type : 'GET',
			 url : '${ctx}/channel/creatChannelCode',
			 success : function(msg) {	
					$("#channelCode").val(msg.data.value);
			 },
			 error : function(e) {
			      console.log(e);
			 }
			 });

  }
</script>
<script>
		$(function() {
			getChangeCode();
	 }); 
</script>
<div class="wrapper">
	<section class="content">
		<div class="box box-primary">
		<div class="box-body">
			<div class="layui-row">
				<div class="layui-col-xs12" style="text-align: right;">
					<div class="layui-form-item">
						<div class="layui-btn-group">
							<button class="layui-btn" data-type="addTable">添加渠道</button>
						</div>
					</div>
				</div>
				
				<div class="layui-form">
				
					<div class="layui-row">
						<div class="layui-col-xs2">
							<div class="layui-inline">
								<div>
									<input type="text" id="channelName" name="channelName" value=""
										lay-verify="required" placeholder="请填写渠道名称" autocomplete="off"
										class="layui-input">
								</div>
							</div>
						</div>

						<div class="layui-col-xs2">
							<div class="layui-inline">
								<select id="channelformStatus" name="channelformStatus"
									lay-verify="required" class="input-mini">
									<option value="0" selected="">请选择状态</option>
									<option value="1">启用</option>
									<option value="2">禁用</option>
								</select>
							</div>
						</div>


						<div class="layui-col-xs2">
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="editstartdate"
										placeholder="请选择编辑开始时间">
								</div>
							</div>
						</div>

						<div class="layui-col-xs2">
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="editenddate"
										placeholder="请选择编辑结束时间">
								</div>
							</div>
						</div>

					</div>

<hr>

					<div class="layui-row">
						<div class="layui-col-xs3">
							<div class="layui-form-item">
								<div class="layui-btn-group">
									<button class="layui-btn"
										data-type="searchTable">搜索</button>
								</div>
								<div class="layui-btn-group">
									<button class="layui-btn layui-btn-primary"
										data-type="clearTable">重置</button>
								</div>
							</div>
						</div>


					</div>
			</div>
			
			</form>
			<table class="layui-table" id="listTable" lay-filter="test"></table>
			<script type="text/html" id="barDemo">
              <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
          </script>
			<div id="page" class="layui-page"></div>

		</div>

		<form id="formValid" class="add-form  edit-form layui-form pad30"
			autocomplete="off">
			<input type="hidden" name="id" class="id">
			<div class="layui-form-item">
				<label class="layui-form-label"><strong>渠道编码：</strong></label> <input
					type="text" lay-verify="required"  name="channelCode" id="channelCode" readonly="value"
					class="chaanelCode layui-input">
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"><strong>渠道名称：<font
						color="red">*</font></strong></label> <input type="text" name="channelName"
					id="channelName" lay-verify="required"  class="channelName layui-input">

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><strong>渠道描述：<font
						color="red">*</font></strong></label>

				<textarea name="channelDesc" lay-verify="required"  placeholder="请输入内容" id="channelDesc"
					class="layui-textarea"></textarea>

			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" style="float: left"><strong>状态：<font
						color="red">*</font></strong></label> <select name="channelStatus" lay-verify="required" 
					id="channelStatus" >
					<option value="0" selected="">请选择状态</option>
					<option value="1">启用</option>
					<option value="2">禁用</option>
				</select>

			</div>
		<div class="layui-form-item layui-hide">
			<button class="layui-btn" lay-submit="" lay-filter="submitForm" id="submitForm">提交</button>
		</div>
		<div class="layui-form-item layui-hide">
			<button class="layui-btn" lay-submit="" lay-filter="addForm" id="addForm">提交</button>
		</div>
		</form>


		</div></div></section>
</div>



<script>
	layui
			.use(
					[ 'laypage', 'layer', 'laydate', 'table', 'jquery' ,'form'],
					function() {
						var table = layui.table,
						$ = layui.jquery,
							form = layui.form;
						table
								.render({
									elem : '#listTable',
									url : '${ctx}/channel/channellist',
									cellMinWidth : 150,
									cols : [ [
											{
												field : 'channelCode',
												title : '渠道编码'
											},
											{
												field : 'channelName',
												title : '渠道名称'
											},
											{
												field : 'channelDesc',
												title : '渠道描述'
											},
											{
												field : 'channelStatus',
												title : '状态',
												disabled : true,
												selected : true,
												jsontext : "[{ 'text': '启用', 'value': '1'},{ 'text': '禁用', 'value': '2'}]"
											}, {
												field : 'channelPath',
												title : '引流链接'
											}, {
												field : 'createBy',
												title : '编辑人'
											}, {
												field : 'updateTime',
												  datetimeformat: true,
												title : '编辑时间'
											}, {
												fixed : 'right',
												align : 'center',
												title : '操作',
												toolbar : '#barDemo'
											}

									] ],
									page : true,
									height : "400px",
									even : true,
									limits : [ 10, 15, 20 ],
									limit : 10
								});

						//监听工具条
						table.on('tool(test)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
							var data = obj.data //获得当前行数据
							, layEvent = obj.event; //获得 lay-event 对应的值
							if (obj.event === 'edit') {
								editForm();
								$.each(data, function(key, value) {
									
									$('.edit-form').find('input[name = ' + key + ']').val(value);
									$("#channelDesc").text(data["channelDesc"]);
									//$(".channelStatus").find("option[text=' + key + ']").attr("selected",true);
									//var s=$("#channelStatus").find("option[value='222']").attr("selected",true);
									$("#channelStatus").val(data['channelStatus']);       
									 form.render('select');
								//	$("#channelStatus option[text='2']").attr("selected", true);
								/* 	$("#channelStatus").val(data["channelStatus"]);
									 $("#channelStatus option[text='" + data["channelStatus"] + "']").attr("selected", true); */
									
								})
							}
						});

						var laydate = layui.laydate;
						//时间选择器
						laydate.render({
							elem : '#editstartdate',
							type : 'datetime'
						});

						//时间选择器
						laydate.render({
							elem : '#editenddate',
							type : 'datetime'
						});

						//编辑表单function
						function clearForm() {
							//console.log("clear")
							var formBox = $('#formValid');
							formBox.find('input[type="text"]').val('');
							formBox.find('input').removeClass('right');
							formBox.find('.prompt').removeClass('false');
							formBox.find('.prompt').removeClass('right');

						}

						function editForm() {
						
							layer
									.open({
										type : 1,
										title :  '<div align="center" style="font-size:20px" ><strong>编辑渠道</strong></div>',
										shadeClose : false,
										area : [ '500px', 'auto' ],
										content : $('.edit-form'),
										shift : 2,
										closeBtn : 0,
										 btnAlign: 'c',
										btn : [ '修改', '取消' ],
										
										yes : function(index, layero) {
											$('#submitForm').click();
									
										},
										btn2 : function(index, layero) {
												clearForm();
												layer.close(index)
											return false;
										},
										cancel : function(index, layero) {
											if (layer.confirm('确定要关闭么')) {
												clearForm()
												layer.close(index)
											}
											return false;
										}

									});
						}

						var $ = layui.jquery, active = {
							searchTable : function() {
								var wheres = {};
								if ($("#channelName").val() != '') {
									wheres.channelName = $("#channelName")
											.val();
								}
								if ($("#channelformStatus").val() != ''
										&& $("#channelformStatus").val() != "0") {
									wheres.channelStatus = $("#channelformStatus")
											.val();
								}
								if ($("#editstartdate").val() != '') {
									wheres.editstartdate = $("#editstartdate")
											.val();
								}
								if ($("#editenddate").val() != '') {
									wheres.editenddate = $("#editenddate")
											.val();
								}
								table.reload('listTable', {
									where : wheres
								});
							},
							clearTable : function() {
								$("#channelName").val("");
								$("#channelformStatus").val("");
								$("#editstartdate").val("");
								$("#editenddate").val("");
								form.render('select');
							},

							addTable : function() {
								getChangeCode();
								layer
										.open({
											type : 1,
											title : '<div align="center" style="font-size:20px" ><strong>添加渠道</strong></div>',
											shadeClose : false,
											area : [ '500px', 'auto' ],
											content : $('.add-form'),
											shift : 2,
											closeBtn : 2,
											 btnAlign: 'c',
											btn : [ '添加' ],
											 success: function(layero, index){
								                    $('#formValid')[0].reset();
								                	$("#channelDesc").val("");
								               },
											yes : function(index, layero) {
												$('#addForm').click();
											},
											btn2 : function(index, layero) {
												layer.close(index)
												return false;
											},
											cancel : function(index, layero) {
												layer.close(index)
												return false;
											}
										});
							}
						};
						$('.layui-btn').on('click', function() {
							var type = $(this).data('type');
							active[type] ? active[type].call(this) : '';
						});

						
						form.verify({
							channelCode: function(value, item){ //value：表单的值、item：表单的DOM对象
								//return '必填项不能为空';
							},
							channelName: function(value, item){
								if ( $.trim(value) == '') {
							    	return '必填项不能为空';
							    }
							},
							channelStatus: function(value, item){
								
								if ( $.trim(value) == '0') {
							    	return '请选择状态';
							    }
							},
							channelDesc: function(value, item){
								
								if ($.trim(value) == '') {
							    	return '必填项不能为空';
							    }
							}
						});
						//监听提交
						form.on('submit(submitForm)', function(data){
							var fields = data.field;
								channelArr = [];
								$
								.ajax({
									type : 'put',
									url : "${ctx}/channel/addChannel"
											+ "/"
											+ $('.id')
													.val(),
									contentType : 'application/json; charset=utf-8',
									data : JSON
											.stringify($(
													"#formValid")
													.serializeObject()),
									dataType : "json",
									success : function(data) {
										if (data.code == '0000') {
											layer
													.msg('修改成功');
											clearForm();
											layer.closeAll('page');
											table
													.reload('listTable');
										} else {
                                            //权限403
                                            if(data.code === 0) {
                                                window.location.href="${ctx}/main/login";
                                                return false;
                                            } else {
                                                layer
                                                    .msg(data.msg);
                                            }
										}
									}
								});
							return false;
						});
						
						
						form.on('submit(addForm)',function(data){
						var formBox = $('#formValid');
						var vals = formBox.find(
								'input[type="text"]')
								.val();
						if (vals != '') {
							$
									.ajax({
										type : 'post',
										url : '${ctx}/channel/addChannel',
										data : $(
												"#formValid")
												.serialize(),
										success : function(
												data) {
											if (data.code == '0000') {
												clearForm();
												layer
														.alert('新增成功');
												layer.closeAll('page');
												table
														.reload('listTable');
												
											} else {
                                                //权限403
                                                if(data.code === 0) {
                                                    window.location.href="${ctx}/main/login";
                                                    return false;
                                                } else {
                                                    layer
                                                        .msg(data.msg);
                                                }
											}
											layer
													.close(index);
										}
									});
						} else {
							layer.msg("请填写新增内容！")
						}
						return false;
					});
					});
</script>




