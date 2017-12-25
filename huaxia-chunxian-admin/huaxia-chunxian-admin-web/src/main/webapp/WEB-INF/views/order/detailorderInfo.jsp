<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%@ page import="java.util.Map"%>
<%@ include file="../layouts/form_lead_in.jsp"%>
<link rel="stylesheet" href="${ctx}/static/publish/dist/css/progress.css">
<script src="${ctx}/static/publish/dist/js/progress.js"></script>

<script>
    $(function() {

        var orderInfoBase = $("#consumerinfolist").val();
        var obj = JSON.parse(orderInfoBase);
        $.each(obj, function(i, v) {
            console.log(obj['phone']);
            $("#orderNo").val(obj['orderNo']);
            $("#appType").val(obj['appType']);
            $("#userId").val(obj['userId']);
            var nDate = new Date(obj['createTime']);
            $("#createTime").val(nDate.toLocaleString());
            $("#loanAmount").val(obj['loanAmount']);
            $("#loanTerm").val(obj['loanTerm']);
            $("#productNo").val(obj['productNo']);
            $("#realName").val(obj['realName']);
            $("#status").val(obj['status']);
            $("#phone").val(obj['phone']);
            $("#registerChannel").val(obj['registerChannel']);
            $("#loanType").val(obj['loanType']);

        });

        Date.prototype.toLocaleString = function() {
            return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + "/ " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
        }

        var orderInfoPersonal = $("#personalvalue").val();
        if(orderInfoPersonal != 'null') {
            var objPersonal = JSON.parse(orderInfoPersonal);
            $.each(objPersonal, function(i, v) {

                $("#education").val(objPersonal["education"]);
                $("#marriage").val(objPersonal["marriage"]);
                $("#isLocal").val(objPersonal["isLocal"]);
                $("#moveInDate").val(objPersonal["moveInDate"]);
                $("#rent").val(objPersonal["rent"]);
                $("#liveProvince").val(objPersonal["liveProvince"]);
                $("#liveType").val(objPersonal["liveType"]);
                var nliveDate = new Date(objPersonal["liveDate"]);
                $("#liveDate").val(nliveDate.toLocaleString());
                $("#carState").val(objPersonal["carState"]);
                var ncarState = new Date(objPersonal["carState"]);
                $("#carState").val(ncarState.toLocaleString());

                var ncarSafeState = new Date(objPersonal["carSafeState"]);
                $("#carSafeState").val(ncarSafeState.toLocaleString());

            });
        }

        var orderInfoWork = $("#workvalue").val();
        if(orderInfoWork != 'null') {
            var objWork = JSON.parse(orderInfoWork);
            $.each(objWork, function(i, v) {
                $("#unitName").val(objWork["unitName"]);
                $("#unitNature").val(objWork["unitNature"]);
                $("#unitPhone").val(objWork["unitPhone"]);
                var nentryDate = new Date(objWork["entryDate"]);
                $("#entryDate").val(nentryDate.toLocaleString());
                $("#unitProvince").val(objWork["unitProvince"]);

            });
        }
        var stext = {};
        var resultstate = "";
        //progress 进度条
        $.ajax({
            type: 'GET',
            url: '${ctx}/order/statusProssInfo',
            data: {
                orderNo: $("#orderNo").val()
            },
            success: function(msg) {

                /* 	var subtime=v.submitTime;//提交时间
                    var checkPassTime=v.checkPassTime;//审核通过时间
                    var checkRejectTime=v.checkRejectTime;//审核拒绝时间
                    var checkCancleTime=v.checkCancleTime;//审核取消时间
                    var confirmTime=v.confirmTime;//确认签约时间
                    var moneyTime=v.moneyTime;//放款时间 */
                for(var i = 0; i < msg.data.orderStatus.length; i++) {
                    if(i + 1 == msg.data.orderStatus.length) {
                        resultstate = msg.data.orderStatus[i].state;
                    }
                }
                stext = msg.data.orderStatus;
                stext.state = resultstate;
                $("#proB").progress({
                    "textList": stext,
                    "state": resultstate, //0-3 四种状态
                });
            },
            error: function(e) {
                console.log(e);
            }
        });

    })
</script>

<div class="wrapper">
	<section class="content">

		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 2px;">
			<legend>订单进度</legend>
		</fieldset>

		<div id="proB"></div>

		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 2px;">
			<legend>订单详情</legend>
		</fieldset>

		<div style="width: auto; height: 200px;" class="layui-form">
			<blockquote class="layui-elem-quote layui-text">
				申请基本信息</blockquote>

			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<input type="hidden" id="consumerinfolist" value=<%=request.getAttribute( "value")%> /> <input type="hidden" id="personalvalue" value=<%=request.getAttribute( "personalvalue")%> /> <input type="hidden" id="workvalue" value=<%=request.getAttribute( "workvalue")%> />

					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">进件编号</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">进件时间</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">注册手机</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">注册渠道</label>

					</div>

					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">申请渠道</label>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-xs2">
					<input type="text" id="orderNo" name='orderNo' size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">
					<input type="hidden" id="userId" name='userId' size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">
				</div>
				<div class="layui-col-xs2">
					<input type="text" name="createTime" id="createTime" size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">
				</div>
				<div class="layui-col-xs2">
					<input type="text" name="phone" id="phone" value="" autocomplete="off" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
				</div>
				<div class="layui-col-xs2">
					<select name="registerChannel" id="registerChannel" lay-filter="aihao">
						<option value="0" selected="">请选择用户注册渠道</option>
						<option value="1">花财</option>
						<option value="2">摩尔龙</option>
					</select>
				</div>
				<div class="layui-col-xs2">
					<select name="appType" id="appType" lay-filter="aihao" readonly="value">
						<option value="0" selected="">申请渠道</option>
						<option value="1">花财</option>
						<option value="2">摩尔龙</option>
					</select>
				</div>
			</div>

			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">申请产品</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">申请金额(元)</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">申请期数</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">借款用途</label>
					</div>

				</div>
			</div>
			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">
						<input type="text" name='productNo' id="productNo" size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="off" autocomplete="left" class="layui-input">

					</div>
					<div class="layui-col-xs2">
						<input type="text" name="loanAmount" id="loanAmount" size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="off" autocomplete="left" class="layui-input">
					</div>
					<div class="layui-col-xs2">

						<input type="text" name="loanTerm" id="loanTerm" value="" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
					</div>
					<div class="layui-col-xs2">

						<input type="text" name="loanType" id="loanType" value="" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
					</div>

				</div>
			</div>
			<blockquote class="layui-elem-quote layui-text">
				个人信息</blockquote>

			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;">学历</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">婚姻状态</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">是否本地户籍</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">来本市时间</label>

					</div>

					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">居住类型</label>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">
						<select name="education" id="education" lay-filter="aihao" readonly="value">
							<option value="1">硕士及以上</option>
							<option value="2">本科</option>
							<option value="3">大专</option>
							<option value="4">中专</option>
							<option value="5">高中</option>
							<option value="6">初中及以下</option>
						</select>
					</div>
					<div class="layui-col-xs2">
						<select name="marriage" id="marriage" lay-filter="aihao" readonly="value">
							<option value="1">未婚</option>
							<option value="2">已婚有子女</option>
							<option value="3">已婚无子女</option>
							<option value="4">离异</option>
							<option value="5">再婚</option>
							<option value="6">丧偶</option>
						</select>
					</div>
					<div class="layui-col-xs2">
						<input type="text" name="isLocal" id="isLocal" value="" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
					</div>
					<div class="layui-col-xs2">

						<input type="text" name="moveInDate" id="moveInDate" value="" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
					</div>

					<div class="layui-col-xs2">

						<select name="liveType" id="liveType" lay-filter="aihao" readonly="value">
							<option value="1">有房有贷款</option>
							<option value="2">有房无贷款</option>
							<option value="3">与父母同住</option>
							<option value="4">临时住宅</option>
							<option value="5">单位宿舍</option>
							<option value="6">租房</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">居住房租(元/月)</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">居住地址</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">居住开始日</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">车辆情况</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">保险情况</label>

					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">

						<input type="text" name='rent' id='rent' size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">
					</div>
					<div class="layui-col-xs2">

						<input type="text" name="liveProvince" id="liveProvince" size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">
					</div>
					<div class="layui-col-xs2">

						<input type="text" name="liveDate" id="liveDate" value="" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
					</div>
					<div class="layui-col-xs2">

						<select name="carState" id="carState" lay-filter="aihao" readonly="value">
							<option value="1">有车有贷款</option>
							<option value="2">有车无贷款</option>
							<option value="3">无车</option>

						</select>
					</div>
					<div class="layui-col-xs2">
						<select name="carSafeState" id="carSafeState" lay-filter="aihao" readonly="value">
							<option value="1">购买人寿保险</option>
							<option value="2">购买财产险</option>
							<option value="3">无保险</option>

						</select>
					</div>
				</div>
			</div>
			<blockquote class="layui-elem-quote layui-text">
				工作信息</blockquote>

			<div class="layui-row">
				<div class="layui-row layui-col-space1">
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">单位名称</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">单位性质</label>
					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">单位电话</label>

					</div>
					<div class="layui-col-xs2">
						<label class="layui-form-label labelBd">单位地址</label>

					</div>
				</div>

				<div class="layui-row">
					<div class="layui-row layui-col-space1">
						<div class="layui-col-xs2">

							<input type="text" name='unitName' id='unitName' style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">

						</div>
						<div class="layui-col-xs2">

							<select name="unitNature" id="unitNature" lay-filter="aihao" readonly="value">
								<option value="00">政府机关</option>
								<option value="10">事业单位</option>
								<option value="20">国企</option>
								<option value="30">外企</option>
								<option value="40">合资</option>
								<option value="50">民营</option>
								<option value="60">私营</option>
								<option value="70">个体</option>
							</select>
						</div>
						<div class="layui-col-xs2">

							<input type="text" name="unitPhone" id="unitPhone" value="" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
						</div>
						<div class="layui-col-xs2">

							<input type="text" name="unitProvince" id="unitProvince" value="" autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
						</div>
					</div>

					<div class="layui-row">
						<div class="layui-row layui-col-space1">
							<div class="layui-col-xs2">
								<label class="layui-form-label labelBd" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;">入职时间</label>
							</div>
						</div>
					</div>

					<div class="layui-row">
						<div class="layui-row layui-col-space1">
							<div class="layui-col-xs2">
								<input type="text" name='entryDate' id="entryDate" size="5" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value="" autocomplete="left" class="layui-input">
							</div>
						</div>
					</div>

					<blockquote class="layui-elem-quote layui-text">
						资格认证</blockquote>

					<table class="layui-table" id="certTable"></table>

				</div>

			</div>
		</div>
	</section>
</div>

<script>
    layui.use(['laypage', 'layer', 'laydate', 'form', 'table', 'jquery'], function() {
        var table = layui.table,
            form = layui.form;
        table.render({
            elem: '#certTable',
            url: '${ctx}/order/orderAuth' + "?userId" + "=" + $("#userId").val(),
            cols: [
                [{
                    field: 'cpfRealName',

                    title: '认证项目'

                }, {
                    field: 'id',
                    disabled: true,
                    selected: true,
                    title: '是否认证',
                    jsontext: "[{ 'text': '未认证', 'value': '0'},{ 'text': '已认证', 'value': '1'}]"
                }, {
                    field: 'createTime',
                    datetimeformat: true,
                    title: '认证时间'
                }, {
                    field: '',

                    title: '备注'
                }]
            ],
            page: false,
            height: "310px",
            even: true,
            limits: [10, 15, 20],
            limit: 10
        });

    });
</script>