<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>
<%@ include file="../layouts/ue_lead_in.jsp" %>
<div class="wrapper">
	<section class="content">
		<div class="Tabmain">
			<div class="layui-row cttBox2 " style="margin-top: 20px;">
				<div class="layui-col-xs12">
					<div class="layui-tab layui-tab-brief">
						<ul class="layui-tab-title">
							<li class="layui-this">产品管理</li>
							<button class="layui-btn layui-btn-sm" id="productBId" style="float:right;margin-bottom:10px;margin-right:40px; display:block;" data-type="syncAnshuo">
								同步安硕产品
							</button>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-row cttBox2">
			<div class="layui-col-xs12">
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<div class="box box-primary" style="border-top-color: #009688;">
							<div class="box-header">
								<h3 class="box-title"></h3>
							</div>
							<div id="tableForm">
								<div class="layui-row">
									<form id="productSearchForm" class="layui-form" name="productSearchForm">
										<div class="layui-col-xs3">
											<div class="layui-form-item">
													<div class="layui-inline">
														<input type="text" id="searchProductName" name="searchProductName" value="" lay-verify="" placeholder="请填写产品名称" autocomplete="off" class="layui-input">
													</div>
											</div>
										</div>
										<div class="layui-col-xs3">
											<div class="layui-form-item">
												<div class="layui-inline">
													<select id="searchPublishStatus" name="searchPublishStatus" lay-verify="" class="layui-input">
														<option value="" selected>请选择产品状态</option>
														<option value="1">上架</option>
														<option value="0">下架</option>
													</select>
												</div>
											</div>
										</div>
										<div class="layui-col-xs3">
											<div class="layui-form-item">
												<div class="layui-btn-group">
													<button class="layui-btn" data-type="search">搜索</button>
												</div>
												<div class="layui-btn-group">
													<button class="layui-btn layui-btn-primary" data-type="clear">
														重置
													</button>
												</div>
											</div>
										</div>
										<hr/>
									</form>

								</div>
								<table class="layui-table" id="LAY_table_product" lay-filter="test"></table>
								<script type="text/html" id="barDemo">
									<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
								</script>
							</div>
							<!--产品明细-->
							<div id="productDetailForm" class="layui-form" style="display: none;">
								<input type="hidden" id="tproductLogoImage" name="contentLogo">
								<input type="hidden" id="tmoudleLogoImage" name="moudleContent">
								<input type="hidden" id="anshuoproductName" name="anshuoproductName">
								<div class="box-header with-border">
									<h3 class="box-title"><font size="6">安硕同步基本信息</font></h3>
								</div>
								<blockquote class="layui-elem-quote">产品基本信息</blockquote>
								<div class="shenqInfo cf">
									<div class="ListTitle">产品编号</div>
									<div class="ListTitle">产品名称</div>
									<div class="ListTitle">借款期限（期）</div>
									<div class="ListTitle">最小借款金额（元）</div>
									<div class="ListTitle">最高借款金额（元）</div>

									<div id="productNo" class="ListText"></div>
									<div id="productName" class="ListText"></div>
									<div id="term" class="ListText"></div>
									<div id="minAmcount" class="ListText"></div>
									<div id="maxAmcount" class="ListText"></div>

									<div class="ListTitle">同步时间</div>
									<div class="ListTitle"></div>
									<div class="ListTitle"></div>
									<div class="ListTitle"></div>
									<div class="ListTitle"></div>

									<div id="synTime" class="ListText">2017-10-25 15:33:22</div>
									<div class="ListText"></div>
									<div class="ListText"></div>
									<div class="ListText"></div>
									<div class="ListText"></div>
								</div>
								<blockquote class="layui-elem-quote">费率等级</blockquote>
								<div class="layui-form">
									<table class="layui-hide" id="feeClass" lay-filter="testFeeClass"></table>
								</div>
								<br><br>
								<div class="box box-primary" style="border-top-color: #009688;">
									<div class="box-header with-border">
										<h3 class="box-title"><font size="6">产品自定义信息</font></h3>
									</div>
									<form id="basicProductform" class="layui-form pad30" name="basicProductform">
										<blockquote class="layui-elem-quote">产品基础信息</blockquote>
										<input type="hidden" id="id" name="id" class="id">
										<input type="hidden" id="basicProductLogoImage" name="contentLogo">
										<div class="layui-form-item">
											<div class="layui-inline">
												<label style="float:left">产品名称：<font color="red">*</font></label>
											</div>
											<div class="layui-form-item">
												<div class="layui-inline">
													<select id="productSelectID" name="productSelectID" lay-filter="productSelectID" class="layui-input">
														<option value="0" selected>同安硕名称</option>
														<option value="1">自定义名称</option>
													</select>
												</div>
												<div class="layui-inline">
													<input type="text" class="layui-input" style="width: 400px;" id="productSelectName" lay-verify="required" name="productSelectName" value="" />
												</div>
											</div>
											<div class="layui-inline">
												<label style="float:left">产品封面图：<font color="red">*</font></label>
											</div>
											<div class="layui-upload">
												<div class="layui-upload-list">
													<div class="layui-form-item">
														<div class="layui-upload">
															<div class="layui-upload-list">
																<div class="layui-form-item">
																	<div class="layui-upload-drag" id="productLogo">
																		<img class="layui-upload-img" id="logoImage" name="logoImage" style="display: none;width: 365px;height: 300px;">
																		<i class="layui-icon"></i>
																		<p>点击上传，或将文件拖拽到此处</p>
																	</div>
																	<div class="layui-inline" style="float:bottom;vertical-align:bottom">
																		<p id="logoText"></p>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
										<div class="layui-form-item">
											<div class="layui-inline">
												<button id="basicSave" data-type="basicSave" class="layui-btn" type="button">
													保存
												</button>
											</div>
											<button class="layui-btn layui-hide" lay-submit="" lay-filter="submitForm" id="submitForm">提交
											</button>
										</div>
									</form>
									<blockquote class="layui-elem-quote">产品模块内容</blockquote>
									<form id="moduleProductform" class="layui-form pad30" name="moduleProductform">
										<div id="copy"></div>

										<div class="layui-form-item" id="modelBorder">
											<div class="layui-row">
												<div class="layui-col-xs5">&nbsp;</div>
												<div class="layui-col-xs2">
													<button class="layui-btn" type="button" data-type="addModel">添加模块
													</button>

												</div>
												<div class="layui-col-xs5">&nbsp;</div>
											</div>

										</div>

									</form>
									<!--分享信息-->

									<form id="shareProductform" class="layui-form pad30" name="shareProductform">
										<blockquote class="layui-elem-quote">分享信息</blockquote>
										<input type="hidden" id="sharemoudleLogoImage" name="shareIcon">
										<div class="layui-row">
											<div class="layui-col-xs6 ">
												<div class="layui-form-item">
													<div class="layui-inline">
														<label style="float:left">分享开关：<font
																color="red">*</font></label>
													</div>
												</div>

												<div class="layui-form-item">
													<div class="layui-inline">
														<select id="shareSwitch" class="layui-input">
															<option value="" selected>请选择分享开关</option>
															<option value="1">开</option>
															<option value="0">关</option>
														</select>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline">
														<label style="float:left">分享标题：<font
																color="red">*</font></label>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline" style="width:90%">
														<input type="text" id="shareTitle" name="shareTitle" value="" lay-verify="required" autocomplete="off" class="layui-input">
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline">
														<label style="float:left">分享内容：<font
																color="red">*</font></label>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline" style="width:90%">
														<textarea id="shareContent" name="shareContent" lay-verify="required" class="layui-textarea"></textarea>
													</div>
												</div>
											</div>
											<div class="layui-col-xs6 ">
												<div class="layui-form-item">
													<div class="layui-inline">
														<label style="float:left">APP分享提示：</label>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline" style="width:90%">
														<input type="text" id="shareTooltip" name="shareTooltip" lay-verify="required" value="" autocomplete="off" class="layui-input">
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline" style="width:90%">
														<label style="float:left">分享图标：<font
																color="red">*</font></label>
													</div>
												</div>

												<div class="layui-upload">
													<div class="layui-upload-list">
														<div class="layui-form-item">

															<div class="layui-form-item">
																<div class="layui-upload">
																	<div class="layui-upload-list">
																		<div class="layui-form-item">
																			<div class="layui-upload-drag" id="shareLogo">
																				<img class="layui-upload-img" id="shareImage" name="shareImage" style="display: none;width: 365px;height: 300px;">
																				<i class="layui-icon"></i>
																				<p>点击上传，或将文件拖拽到此处</p>
																			</div>
																			<div class="layui-inline" style="float:bottom;vertical-align:bottom">
																				<p id="shareText"></p>
																			</div>
																		</div>
																	</div>
																</div>
															</div>

														</div>
													</div>
												</div>

												<div class="layui-form-item">
													<div class="layui-inline" style="width:90%">
														<label style="float:left">分享链接：<font
																color="red">*</font></label>
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-inline">
														<select name="city" lay-verify="" class="layui-input">
															<option value="0" selected>产品默认链接</option>
															<option value="1">自定义链接</option>
														</select>
													</div>
													<div class="layui-inline">
														<input type="text" class="layui-input" id="shareUrl" name value="" />
													</div>
												</div>
											</div>

										</div>
										<div class="layui-form-item">
											<div class="layui-inline">
												<button id="shareSave" data-type="shareSave" class="layui-btn" type="button">
													保存
												</button>
											</div>
											<!-- <button class="layui-btn" lay-submit="" lay-filter="demo2">跳转式提交</button> -->
											<button class="layui-btn layui-hide" lay-submit="" lay-filter="shaeresubmitForm" id="shaeresubmitForm">提111交
											</button>
										</div>
									</form>
									<!--分享信息-->

									<!--首页营销信息-->
									<br>
									<div class="box-header with-border">
										<h3 class="box-title"><font size="6">首页营销信息</font></h3>
									</div>
									<div class="box box-primary">
										<blockquote class="layui-elem-quote">首页营销信息</blockquote>
										<input type="hidden" id="marketmoudleLogoImage" name="marketingIcon">
										<form id="marketingProductform" class="layui-form pad30" name="marketingProductform">
											<div style="display: none;" id="copyYxHTML">
												<div class="layui-row">
													<div class="layui-col-xs6 ">
														<div class="layui-form-item">
															<div class="layui-inline">
																<label style="float:left">模块开关：<font
																		color="red">*</font></label>
															</div>
														</div>



														<div class="layui-form-item">
															<div class="layui-inline">
																<input type="hidden" name="marketId" class="layui-input marketId">
																<select name="marketingSwitch" lay-verify="" class="layui-input moudleId" class="layui-input marketingSwitch">
																	<option value="" selected>请选择模块开关</option>
																	<option value="1">开</option>
																	<option value="0">关</option>
																</select>
															</div>
														</div>
														<div class="layui-form-item">
															<div class="layui-inline">
																<label style="float:left">模块名称：<font
																		color="red">*</font></label>
															</div>
														</div>
														<div class="layui-form-item">
															<div class="layui-inline" style="width:90%">
																<input type="text" name="marketingName" value="" lay-verify="" autocomplete="off" class="layui-input marketingName">
															</div>
														</div>
													</div>
													<div class="layui-col-xs6 ">
														<div class="layui-form-item">
															<div class="layui-inline">
																<label style="float:left">模块排序：<font
																		color="red">*</font></label>
															</div>
														</div>
														<div class="layui-form-item">
															<div class="layui-inline">
																<input type="text" name="marketingSort" value="" lay-verify="" autocomplete="off" class="layui-input marketingSort">
																<!--   <select name="marketingSort" lay-verify=""
                                                                        class="layui-input">
                                                                    <option value="1" selected>1</option>
                                                                    <option value="2">2</option>
                                                                    <option value="3">3</option>
                                                                    <option value="4">4</option>
                                                                    <option value="5">5</option>
                                                                    <option value="6">6</option>
                                                                    <option value="7">7</option>
                                                                    <option value="8">8</option>
                                                                    <option value="9">9</option>
                                                                </select> -->
															</div>
														</div>
														<div class="layui-form-item">
															<div class="layui-inline">
																<label style="float:left">模块概括语：<font
																		color="red">*</font></label>
															</div>
														</div>
														<div class="layui-form-item">
															<div class="layui-inline" style="width:90%">
																<input type="text" name="marketingSummary" value="" lay-verify="" autocomplete="off" class="layui-input marketingSummary">
															</div>
														</div>
													</div>
													<div class="layui-col-xs12">
														<div class="layui-inline">
															<label style="float:left">图标（100*100）：<font
																	color="red">*</font></label>
														</div>
														<div class="layui-upload">
															<div class="layui-upload-list">

																<div class="layui-form-item">
																	<div class="layui-upload">
																		<div class="layui-upload-list">
																			<div class="layui-form-item">
																				<div class="layui-upload-drag moudleLogo" id="moudleLogo">
																					<img class="layui-upload-img moudleImage" id="moudleImage" name="moudleImage" style="display: none;width: 365px;height: 300px;">
																					<i class="layui-icon"></i>
																					<p>点击上传，或将文件拖拽到此处</p>
																				</div>
																				<div class="layui-inline" style="float:bottom;vertical-align:bottom">
																					<p id="moudleText"></p>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>

												<div class="layui-inline">
													<button id="marketSave" data-type="marketSave" class="layui-btn" type="button"> 保存</button>
												</div>
											</div>
										</form>
									</div>
								</div>

								<div class="layui-form-item" id="modelmarketBorder">
									<div class="layui-row">
										<div class="layui-col-xs5">&nbsp;</div>
										<div class="layui-col-xs2">
											<button class="layui-btn" type="button" data-type="addmarketModel">添加模块
											</button>
										</div>
										<div class="layui-col-xs5">&nbsp;</div>
									</div>

									<!--首页营销信息-->
								</div>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>

		<div class="layui-hide">
			<script type="text/plain" id="moduleEditor"></script>
		</div>
	</section>
</div>
<script>
	function getHTML(index){
        var html = '<div class="copyhtml">'+
            '	<div class="layui-row" style="background:url(${ctx}/static/publish/dist/images/lineborder.png) repeat-x bottom;padding:0 0 30px 0; margin-bottom: 20px;">'+
            '		<div class="layui-col-xs6 ">'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline">'+
            '					<label style="float:left">模块开关：<font color="red">*</font></label>'+
            '				</div>'+
            '			</div>'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline">'+
            '					<input type="hidden" name="moudleId" class="layui-input moudleId">'+
            '					<select name="moudleSwitch" lay-verify="" class="layui-input moudleSwitch">'+
            '						<option value="" selected>请选择模块开关</option>'+
            '						<option value="1">开</option>'+
            '						<option value="0">关</option>'+
            '					</select>'+
            '				</div>'+
            '			</div>'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline">'+
            '					<label style="float:left">模块名称：<font color="red">*</font></label>'+
            '				</div>'+
            '			</div>'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline" style="width:90%">'+
            '					<input type="text" name="moudleName" value="" lay-verify="" placeholder="请输入搜索内容" autocomplete="off" class="layui-input moudleName">'+
            '				</div>'+
            '			</div>'+
            '		</div>'+
            '		<div class="layui-col-xs6 ">'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline">'+
            '					<label style="float:left">模块排序：<font color="red">*</font></label>'+
            '				</div>'+
            '			</div>'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline">'+
            '					<input type="text" name="sort" value="" lay-verify="" autocomplete="off" class="layui-input  sort">'+
            '				</div>'+
            '			</div>'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline">'+
            '					<label style="float:left">模块概括语：<font color="red">*</font></label>'+
            '				</div>'+
            '			</div>'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline" style="width:90%">'+
            '					<input type="text" name="moudleSummary" value="" lay-verify="" placeholder="请输入搜索内容" autocomplete="off" class="layui-input moudleSummary">'+
            '				</div>'+
            '			</div>'+
            '		</div>'+
            '		<div class="layui-col-xs12">'+
            '			<div class="layui-form-item">'+
            '				<div class="layui-inline"><label style="float:left">模块内容：<font color="red">*</font></label></div>'+
            '			</div>'+
            '			<script type="text/plain" id="moduleEditor'+index+'" name="moudleContent"><\/script>'+
            '		</div>'+
            '	</div>'+
            '	<div class="layui-inline">'+
            '		<button id="moduleSave" data-type="moduleSave" class="layui-btn" type="button">保存</button>'+
            '	</div>'+
            '	<button class="layui-btn layui-hide" lay-submit="" lay-filter="submitFormmoudle" id="submitFormmoudle">提交</button>'+
            '</div>';
	    return html;
	}

	layui.use(['element', 'form', 'table', 'layer', 'jquery', 'laydate', 'upload'], function() {
		var baseurl = "${ctx}/productmanage/";
		var element = layui.element,
			form = layui.form,
			$ = layui.jquery,
			upload = layui.upload,
			table = layui.table;
		$('.layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
		var $ = layui.$,
			active = {
				syncAnshuo: function() {
					layer.msg("同步安硕产品...");
					synchrnizeFromAnShou();
				},
				search: function() {
					var wheres = {};
					wheres.productName = $("#searchProductName").val();
					wheres.publishStatus = $("#searchPublishStatus").val();
					table.reload('test', {
						where: wheres
					});
				},
				clear: function() {
                    var formBox=$("#addMessageForm");
                    formBox[0].reset();
				},
				addModel: function() { //获取选中数据
					//得到copy容器外部的length
                    var length = $("#copy").find(".copyhtml").length+1;
                    $("#copy").append(getHTML(length))
                    form.render('select');
                    $("#copy .copyhtml:eq("+length+")").find('.layui-btn').attr('id', "Module" + productNo);
					UE.getEditor('moduleEditor'+length, {
						initialFrameHeight: 300,
						initialFrameWidth: null
					});
				},
				addmarketModel: function() { //获取选中数据
					var copyYxHTML = $("#copyYxHTML>div").clone(true);
					copyYxHTML.find('[name=marketingSwitch]').attr("disabled",false);
					$("#modelmarketBorder").before(copyYxHTML);
					form.render('select');
				},
				basicSave: function() { //保存基本信息
					$('#submitForm').click();
				},
				shareSave: function() { //保存分项基本信息
					$('#shaeresubmitForm').click();
				},
				moduleSave: function() { //保存模板基本信息
					//moudleContent  moudleSummary sort  moudleName moudleSwitch
					
					//var moudleContent = $(this).parent(".layui-inline").prev("div").find(".moudleContent").val(),
					var moudleContent = productUe.getContent(),
						moudleSummary = $(this).parent(".layui-inline").prev("div").find(".moudleSummary").val(),
						sort = $(this).parent(".layui-inline").prev("div").find(".sort").val(),
						moudleId = $(this).parent(".layui-inline").prev("div").find(".moudleId").val(),
						moudleName = $(this).parent(".layui-inline").prev("div").find(".moudleName").val(),
						productNo=$("#productNo").html(),
						
						moudleSwitch = $(this).parent(".layui-inline").prev("div").find(".moudleSwitch").val()
						
					editmoduleInfo(moudleContent, moudleSummary, sort, moudleName, moudleSwitch, moudleId,productNo);

				},
				marketSave: function() {//营销
					//marketingSummary  marketingSort  marketingName  marketingSwitch marketId
					var marketingSummary = $(this).parent(".layui-inline").prev("div").find(".marketingSummary").val(),
						marketingSort = $(this).parent(".layui-inline").prev("div").find(".marketingSort").val(),

						marketId = $(this).parent(".layui-inline").prev("div").find(".marketId").val(),
						marketingName = $(this).parent(".layui-inline").prev("div").find(".marketingName").val(),
						productNo=$("#productNo").html(),
						marketingSwitch = $(this).parent(".layui-inline").prev("div").find(".marketingSwitch").val()
					editmarketInfo(marketingSummary, marketingSort, marketingName, marketingSwitch, marketId,productNo);

				}
			};
		table.render({
			elem: "#LAY_table_product",
			url: baseurl + "productinfos",
			cols: [
				[{
					field: "productNo",
					title: "产品编号"
				}, {
					field: "productName",
					title: "产品名称"
				}, {
					field: "publishStatus",
					title: "状态",
					disabled: true,
					selected: true,
					jsontext: "[{'text':'上架','value':'1'},{'text':'下架','value':'0'}]"
				}, {
					field: "term",
					title: "借款期限（期）"
				}, {
					field: "maxAmcount",
					title: "最高借款金额（元）"
				}, {
					field: "minAmcount",
					title: "最低借款金额（元）"
				}, {
					title: "操作",
					fixed: 'right',
					align: 'center',
					toolbar: '#barDemo',
					minWidth: 60
				}]
			],
			id: "test",
			cellMinWidth: 120,
			page: true,
			even: true,
			limits: [10, 15, 20],
			limit: 10
		});

		table.on('tool(test)', function(obj) {
			var data = obj.data;
			if(obj.event === 'edit') {
				//显示产品明细

				//$("#productBId").addClass('layui-hide');
				$("#productBId").hide();
				$("#tableForm").hide();
				$("#productDetailForm").show();
				loadFeeClass(data);
				showProductModule();
				showProductMarketing();
			}
		});
		
		function dateFtt(fmt,date)   
		{ //author: meizz   
		  var o = {   
		    "M+" : date.getMonth()+1,                  
		    "d+" : date.getDate(),                     
		    "h+" : date.getHours(),                   
		    "m+" : date.getMinutes(),                  
		    "s+" : date.getSeconds(),                   
		    "q+" : Math.floor((date.getMonth()+3)/3),    
		    "S"  : date.getMilliseconds()                
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		} 
		
		//格式话日期
		function formatDate(val){
			var date = new Date(val)
			
			 return dateFtt("yyyy-MM-dd hh:mm:ss",date);
			
		};

		function loadFeeClass(data) {
			$("#productNo").html(data.productNo);
			$("#productName").html(data.productName);
			$("#anshuoproductName").val(data.anshuoproductName);
			$("#productSelectName").val(data.productName);
			$("#term").html(data.term);
			$("#minAmcount").html(data.minAmcount);
			$("#maxAmcount").html(data.maxAmcount);
			//var date = new Date(val);
			var valvalue=formatDate(data.anshuoSyncTime);
          //  return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
			$("#synTime").html(valvalue);
			//基本信息图片
			$("#basicProductLogoImage").val(data.contentLogo);
			$('#logoImage').attr('src', data.contentLogo);
			$("#logoImage").show();
			$("#productLogo i, #productLogo p").hide();
		
			//分享图片
			$("#sharemoudleLogoImage").val(data.shareIcon);
			$('#shareImage').attr('src', data.shareIcon);
			$("#shareImage").show();
			$("#shareLogo i, #shareLogo p").hide();
			//营销图片
		
			$("#marketmoudleLogoImage").val(data.marketingIcon);
			$('#moudleImage').attr('src', data.marketingIcon);
			$("#moudleImage").show();
			$("#moudleLogo i, #moudleLogo p").hide();
			
			//分享内容
			$("#shareTitle").val(data.shareTitle);
			$("#shareContent").html(data.shareContent);
			$("#shareUrl").val(data.shareUrl);
			$("#shareTooltip").val(data.shareTooltip);
			$("#shareSwitch").val(data.shareSwitch);
			$("#id").val(data.id);
			if(data.productName == data.anshuoproductName) {
				$("#productSelectID").val(0);
			} else {
				$("#productSelectID").val(1);
			}
			// shareTitle
			//  $("#shareSwitch")val(0);
			var reateLevels = data.rateLevel.split(",");
			var items = "[";
			$.each(reateLevels, function(key, value) {
				items += "{'term' : '";
				items += data.term;
				items += "','rateLevel' : '";
				items += value;
				items += "','xxxx' : ";
				items += "''},";
			})

			items = items.substring(0, items.length - 1);
			items += "]";

			var itemsJson = eval(items);

			table.render({
				elem: '#feeClass',
				data: itemsJson,
				cellMinWidth: 120,
				cols: [
					[{
						field: 'term',
						title: '支持期限（期）'
					}, {
						field: 'rateLevel',
						title: '风险等级'
					}, {
						field: 'xxxx',
						title: '月费率（%）'
					}]
				],
				skin: 'row',
				id: "test",
				even: true,
				page: true,
				limits: [5, 7, 10],
				limit: 5,
				height: "auto",
				request: {
					pageName: 'page',
					limitName: 'limit'
				}
			});
		}

		//产品图片
		var productUploadInst = upload.render({
			elem: '#productLogo',
			url: '${ctx}/upload/imageUp',
			field: 'upfile',
			before: function(obj) {
				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result) {
					$('#logoImage').attr('src', result); //图片链接（base64）
				});
			},
			done: function(res) {
				if(res.code === '0000') {
					//上传成功
					$("#basicProductLogoImage").val(res.data.src);
					$('#logoImage').attr('src', res.data.src);
					$("#logoImage").show();
					$("#productLogo i, #productLogo p").hide();
					var imageText = $('#logoText');
					imageText.html('<a class="layui-btn layui-btn-mini logo-reload">删除</a>');
					imageText.find('.logo-reload').on('click', function() {
						$('#logoImage').attr('src', '');
						$("#logoImage").hide();
						$("#productLogo i, #productLogo p").show();
						imageText.html('');
					});
				} else {
                    //权限403
                    if(res.code === 0) {
                        window.location.href="${ctx}/main/login";
                        return false;
                    } else {
                        console.log('上传失败:' + res.data);
                        return layer.alert('上传失败');
                    }
				}
			},
			error: function() {
				//演示失败状态，并实现重传
				var imageText = $('#logoText');
				imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini logo-reload">删除</a>');
				imageText.find('.logo-reload').on('click', function() {
					productUploadInst.upload();
				});
			}
		});

		//模块图片
		var mproductUploadInst = upload.render({
			elem: '#moudleLogo',
			url: '${ctx}/upload/imageUp',
			field: 'upfile',
			before: function(obj) {
				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result) {
					$('#moudleImage').attr('src', result); //图片链接（base64）
				});
			},
			done: function(res) {
				if(res.code === '0000') {
					//上传成功
					
					$("#marketmoudleLogoImage").val(res.data.src);
					$(".moudleImage").attr('src', res.data.src);
					//$('#moudleImage').attr('src', res.data.src);
					$(".moudleImage").show();
					$(".moudleLogo i, .moudleLogo p").hide();
					var imageText = $('#moudleText');
					imageText.html('<a class="layui-btn layui-btn-mini logo-reload">删除</a>');
					imageText.find('.logo-reload').on('click', function() {
						$('.moudleImage').attr('src', '');
						$(".moudleImage").hide();
						$(".moudleLogo i, .moudleLogo p").show();
						imageText.html('');
					});
				} else {
                    //权限403
                    if(res.code === 0) {
                        window.location.href="${ctx}/main/login";
                        return false;
                    } else {
                        console.log('上传失败:' + res.data);
                        return layer.alert('上传失败');
                    }
				}
			},
			error: function() {
				//演示失败状态，并实现重传
				var imageText = $('#moudleText');
				imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini logo-reload">删除</a>');
				imageText.find('.logo-reload').on('click', function() {
					mproductUploadInst.upload();
				});
			}
		});

		//分享图片
		var sproductUploadInst = upload.render({
			elem: '#shareLogo',
			url: '${ctx}/upload/imageUp',
			field: 'upfile',
			before: function(obj) {
				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result) {
					$('#shareImage').attr('src', result); //图片链接（base64）
				});
			},
			done: function(res) {
				if(res.code === '0000') {
					//上传成功
					$("#sharemoudleLogoImage").val(res.data.src);
					$('#shareImage').attr('src', res.data.src);
					$("#shareImage").show();
					$("#shareLogo i, #shareLogo p").hide();
					var imageText = $('#shareText');
					imageText.html('<a class="layui-btn layui-btn-mini logo-reload">删除</a>');
					imageText.find('.logo-reload').on('click', function() {
						$('#shareImage').attr('src', '');
						$("#shareImage").hide();
						$("#shareLogo i, #shareLogo p").show();
						imageText.html('');
					});
				} else {
                    //权限403
                    if(res.code === 0) {
                        window.location.href="${ctx}/main/login";
                        return false;
                    } else {
                        console.log('上传失败:' + res.data);
                        return layer.alert('上传失败');
                    }
				}
			},
			error: function() {
				//演示失败状态，并实现重传
				var imageText = $('#shareText');
				imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini logo-reload">删除</a>');
				imageText.find('.logo-reload').on('click', function() {
					sproductUploadInst.upload();
				});
			}
		});

		function showProductModule() {

			$.ajax({
				type: 'GET',
				url: '${ctx}/productmodulemanage/productmoudles',
				data: {
					productNo: $("#productNo").html()
				},
				success: function(data) {
                    var copyhtml = '';
					$.each(data.data, function(i, v) {
                        copyhtml += getHTML(i);
					})
                    $("#copy").html(copyhtml);
                    $.each(data.data, function(i, v) {
                        $("#copy .copyhtml:eq("+i+")").find('[name="moudleId"]').val(v.id);
                        $("#copy .copyhtml:eq("+i+")").find('[name=sort]').val(v.sort).attr("sort", v.sort);
                        $("#copy .copyhtml:eq("+i+")").find('[name=moudleName]').val(v.moudleName);
                        $("#copy .copyhtml:eq("+i+")").find('[name=moudleSwitch]').val(v.moudleSwitch);
                        $("#copy .copyhtml:eq("+i+")").find('[name=moudleSummary]').val(v.moudleSummary);
                        $("#copy .copyhtml:eq("+i+")").find('.layui-btn').attr('id', "Module" + v.productNo);
                        UE.getEditor('moduleEditor'+i, {
                            initialFrameHeight: 300,
                            initialFrameWidth: null
                        });
                        setTimeout(function(){
                            UE.getEditor('moduleEditor'+i).setContent(v.moudleContent);
                        }, 666);
                    })

					form.render('select');
				},
				error: function(e) {
                    layer.alert("系统繁忙,请稍后重试!");
					console.log(e);
				}
			});

		};

		//营销模块 marketingSummary  marketingSort  marketingName  marketingSwitch marketId
		function showProductMarketing() {
			$.ajax({
				type: 'GET',
				url: '${ctx}/productmarket/productmarkets',
				data: {
					productNo: $("#productNo").html()
				},
				success: function(data) {
					$.each(data.data, function(i, v) {
						var copyYxhtml = $("#copyYxHTML>div").clone(true);
						copyYxhtml.find('[name="marketId"]').val(v.id);
						// copyYxhtml.find('[name=sort]').val(v.sort).attr('sort', v.sort);
						copyYxhtml.find('[name=marketingSwitch]').val(v.marketingSwitch);
						copyYxhtml.find('[name=marketingName]').val(v.marketingName);
						copyYxhtml.find('[name=marketingSummary]').val(v.marketingSummary);

						$("#modelmarketBorder").before(copyYxhtml);
					})
					form.render('select');
				},
				error: function(e) {
					console.log(e);
				}
			});

		};

		//产品同步
		function synchrnizeFromAnShou() {
			$.ajax({
				type: 'post',
				url: baseurl + "synchronize",
				contentType: 'application/json; charset=utf-8',
				dataType: "json",
				success: function(data) {
					if(data.code == '0000') {
						layer.msg('同步安硕产品成功');
						table.reload('test');
						//$("#productDetailForm").hide();//不好用
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
				error: function(data) {
					console.log(JSON.stringify(data));
					layer.alert('同步安硕产品失败');
				}
			});
		};

		//产品基本信息修改
		function editProductInfo() {

			$.ajax({
				type: 'post',
				url: "${ctx}/productmanage/updateGetProductInfo",
				data: {
					id: $("#id").val(),
					//productNo : "A306"
					productName: $("#productSelectName").val(),
					shareTitle: $("#shareTitle").val(),
					contentLogo: $("#basicProductLogoImage").val(),
					shareContent: $("#shareContent").val(),
					shareTooltip: $("#shareTooltip").val(),
					shareUrl: $("#shareUrl").val(),
					shareIcon: $("#sharemoudleLogoImage").val(),
					shareSwitch: $("#shareSwitch").val()

				},
				dataType: "json",
				success: function(data) {
					if(data.code == '0000') {
						layer.msg('产品信息保存成功');
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
				error: function(data) {
					console.log(JSON.stringify(data));
					layer.alert('产品信息保存失败');
				}
			});
		};

		//产品模块信息修改
		function editmoduleInfo(moudleContent, moudleSummary, sort, moudleName, moudleSwitch, moudleId,productNo) {

			$.ajax({
				type: 'post',
				url: "${ctx}/productmodulemanage/updateModuleProductInfo",
				data: {
					id: moudleId,
					productNo:productNo,
					moudleSwitch: moudleSwitch,
					moudleContent: moudleContent,
					moudleName: moudleName,
					sort: sort,
					moudleSummary: moudleSummary
				},
				dataType: "json",
				success: function(data) {
					if(data.code == '0000') {
						layer.msg('产品模板信息保存成功');

						// table.reload('test');
						//$("#productDetailForm").hide();//不好用
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
				error: function(data) {
					console.log(JSON.stringify(data));
					layer.alert('产品模板信息保存失败');
				}
			});
		};
		
	

		///营销模块 marketingSummary  marketingSort  marketingName  marketingSwitch marketId
		function editmarketInfo(marketingSummary, marketingSort, marketingName, marketingSwitch, marketId,productNo) {
          //  alert($("#marketmoudleLogoImage").val());
			$.ajax({
				type: 'post',
				url: "${ctx}/productmarket/updateMarketProductInfo",
				data: {
					id: marketId,
					productId: $("#id").val(),
					productNo:productNo,
					marketingSwitch: marketingSwitch,
					marketingIcon:$("#marketmoudleLogoImage").val(),
					marketingName: marketingName,
					marketingSummary: marketingSummary
				},
				dataType: "json",
				success: function(data) {
					if(data.code == '0000') {
						layer.msg('产品营销信息保存成功');

						// table.reload('test');
						//$("#productDetailForm").hide();//不好用
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
				error: function(data) {
					console.log(JSON.stringify(data));
					layer.alert('产品营销信息保存成功');
				}
			});
		};

		//选择事件
		form.on('select(productSelectID)', function(data) {
			var ssvalue = data.value;
			if(ssvalue == "0") {
				$("#productSelectName").val($("#anshuoproductName").val());
			} else {
				$("#productSelectName").val($("#productName").val());
			}
		});

		//监听提交 lay-filter
		form.on('submit(submitForm)', function(data) {
			console.log(data)
			var fields = data.field;
			channelArr = [];
			editProductInfo();
			return false;
		});

		//监听提交
		form.on('submit(shaeresubmitForm)', function(data) {
			var fields = data.field;
			channelArr = [];
			editProductInfo();
			return false;
		});

	});
</script>