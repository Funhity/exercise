<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%@ include file="../layouts/form_lead_in.jsp"%>
<script src="${ctx}/static/publish/dist/echarts/echarts.min.js"></script>
<script src="${ctx}/static/publish/dist/echarts/echarts.common.min.js"></script>

<script>
	
	function getPreMonth(date) {
		var arr = date.split('-');
		var year = arr[0]; //获取当前日期的年份
		var month = arr[1]; //获取当前日期的月份
		var day = arr[2]; //获取当前日期的日
		var days = new Date(year, month, 0);
		days = days.getDate(); //获取当前日期中月的天数
		var year2 = year;
		var month2 = parseInt(month) - 1;
		if (month2 == 0) {
			year2 = parseInt(year2) - 1;
			month2 = 12;
		}
		var day2 = day;
		var days2 = new Date(year2, month2, 0);
		days2 = days2.getDate();
		if (day2 > days2) {
			day2 = days2;
		}
		if (month2 < 10) {
			month2 = '0' + month2;
		}
		var t2 = year2 + '年' + month2 + '月' + day2 + "日";

		return t2;
	};
	
	
	function getNowMonth(date) {
		var arr = date.split('-');
		var year = arr[0]; //获取当前日期的年份
		var month = arr[1]; //获取当前日期的月份
		var day = arr[2]; //获取当前日期的日
		
		var t1 = year + '年' + month + '月' + day + "日";

		return t1;
	};
	
	
	function changeChart() {

		var sdate = [], sdata = [];
		$("#main div:first-child").attr({
			"cursor" : "ew-resize"
		})
		$.ajax({
			type : 'GET',
			url : '${ctx}/order/countorderInfo',
			data : {
				paramV : $("#timearea").val()
			},
			success : function(msg) {
				$.each(msg.data.value, function(i, v) {
					sdate.push(v.loginName);
					sdata.push(v.loginNum);
				})
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
                layer.alert("获取订单失败!");
            }
		});
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		var base = "";//起始时间+new Date(2017, 8, 30)		
		var oneDay = 24 * 3600 * 1000;//一天
		var date = [];
		var arr = [];
		var value = $("#timearea").val();
		arr = value.split("-");
		base = +new Date(arr[0].replace('年', ',').replace('月', ',').replace(
				'日', ','));//重新设置起始时间

		//得到天数
		var arrDate, objDate1, objDate2, intDays;
		var startday = arr[0].replace('年', '-').replace('月', '-').replace('日',
				'');
		var endday = arr[1].replace('年', '-').replace('月', '-')
				.replace('日', '');
		startday = startday.replace(/-/g, "/");
		endday = endday.replace(/-/g, "/");
		var date1 = Date.parse(startday);
		var date2 = Date.parse(endday);
		var days = Math.ceil((date2 - date1) / (24 * 60 * 60 * 1000));
		var data = [ Math.random() * 300 ];
		for (var i = 1; i < days; i++) {//制造随机数
			var now = new Date(base += oneDay);
			date.push([ now.getFullYear(), now.getMonth() + 1, now.getDate() ]
					.join('-'));
		}

		//假数据接口
		data = [ 1690, 1070, 2294, 2898, 2771, 2765, 4110, 4490, 4571, 4723,
				15400, 15137, 15682, 16057, 5200, 5528, 7087, 7540, 8097, 8625,
				9595, 11192, 11793, 12923, 12637, 13005, 14174, 14430, 15741,
				15559, 15638, 15400, 15137, 15682, 16057 ]

		option = {
			tooltip : {//悬浮提示层
				trigger : 'axis',
				position : function(pt) {
					return [ pt[0], '30%' ];
				}
			},
			title : {
				left : 'center',
				text : '',
			},
			toolbox : {//是否显示工具栏
				feature : {
					dataZoom : {
						yAxisIndex : 'none'
					},
					restore : {},
					saveAsImage : {}
				}
			},
			xAxis : {//X轴 坐标
				type : 'category',
				boundaryGap : false,
				data : sdate
			},
			yAxis : {//y轴 坐标
				type : 'value',
				boundaryGap : false
			},
			dataZoom : [ /* {//底部控制条
				type : 'slider',//滑块控制器  起始和结束
				start : 0,
				end : 21,
				handleIcon : 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
				handleSize : '80%',//相对于 dataZoom 组件宽度的百分比
				handleStyle : {
					color : '#fff',
					shadowBlur : 3,
					shadowColor : 'rgba(0,0,0, 0.6)',
					shadowOffsetX : 2,
					shadowOffsetY : 2
				},
				fontSize : 12,
				realtime : false
			//只在拖拽结束的时候更新。
			//				,labelFormatter: function (value) {
			//				    return '花财APP' + value + '注册量';
			//				}
			} */ ],
			series : [ {
				name : '单日订单量',
				type : 'line',
				smooth : true,
				symbol : 'circle',
				sampling : 'average',
				show : false,
				itemStyle : {
					normal : {
						color : 'rgb(33, 155, 236)'
					}
				},
				areaStyle : {
					normal : {
						color : new echarts.graphic.LinearGradient(0, 0, 0, 1,
								[ {
									offset : 0,
									color : 'rgb(88, 191, 254)'//0% 处的颜色
								}, {
									offset : 1,
									color : 'rgb(232, 245, 253)'//100% 处的颜色
								} ])
					}
				},

				data : sdata
			} ]
		};
	};
	function getValue(ss) {
		var myDate = new Date();
		var day = myDate.getDate();
		var year = myDate.getFullYear();
		var month = myDate.getMonth() + 1;
		var nowdate = year + "年" + month + "月" + day + "日";
		if (ss == "0") {
			var date7 = new Date(myDate.getTime() - 7 * 24 * 3600 * 1000);
			var day7 = date7.getDate();
			var yesdate = year + "年" + month + "月" + day7 + "日";
			$("#timearea").val(yesdate + "-" + nowdate);
		} else if (ss == "1") {
			var yesdate = getDatemony(15);
			var day = yesdate.getDate();
			var year = yesdate.getFullYear();
			var month = yesdate.getMonth() + 1;
			var yesdate = year + "年" + month + "月" + day + "日";
			$("#timearea").val(yesdate + "-" + nowdate);
		} else if (ss == "2") {
			var nowdate1 = year + "-" + month + "-" + day;
			var yesdate = getPreMonth(nowdate1)
			$("#timearea").val(yesdate + "-" + nowdate);
		}
	}

	$(function() {
		var optionsValue = $("#channelDay option:selected").val();
		getValue(optionsValue);
		changeChart();
	});
</script>



<div class="wrapper">
	<section class="content">
		<div>
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 1px;">
				<legend>订单情况</legend>
			</fieldset>
			<div>
			
				<div class="layui-row">
				<div  class="layui-form" >
					<div class="layui-col-xs2">
					<div class="layui-inline">
							<div class="layui-input-inline">
								<select name="channelDay" id="channelDay" lay-filter="channelDay">
									<option value="0">最近7天</option>
									<option value="1">最近15天</option>
									<option value="2" selected="">最近30天</option>
								</select>
							</div>
						
						</div>
					</div>

					<div class="layui-col-xs3">
						<div class="layui-form-item">
							<input type="text" id="timearea" name="timearea" value=""
								lay-verify="timearea" autocomplete="off" class="layui-input">
						</div>
					</div>

				</div>

				<div class="layui-row">
					<div  class="layui-form">
						<div class="layui-col-xs2">
						<div class="layui-inline">
							<div class="layui-input-inline">
								<select name="zdySelect" id="zdySelect" lay-filter="zdySelect">
									<option value="0" >自定义</option>
									<option value="1">最近15天</option>
									<option value="2" selected="">最近30天</option>
								</select>
							</div>
						</div>
                    </div>
						<div class="layui-col-xs2">
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="zdystartdate"
										placeholder="请选择开始时间">
								</div>
							</div>
						</div>

						<div class="layui-col-xs2">
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="zdyenddate"
										placeholder="请选择结束时间">
								</div>
							</div>
						</div>
					</div>


				</div>
					<hr class="layui-bg-green">
				 </div>
			</div>

		</div>


		<div id="main" style="width: 800px; height: 220px;"></div>
		<div class="box box-primary">
			<div class="layui-progress-bar layui-bg-green" lay-percent="40%"></div>
		</div>

		<div class="box-body">
		
			<div  class="layui-form">
			
			<div class="layui-row">

				<div class="layui-col-xs2">
				<div class="layui-inline">
					<div>
						<input type="text" id="orderNo" name="orderNo" value=""
							placeholder="请填写进件编号" autocomplete="off" class="layui-input">
					</div>
					</div>
				</div>

				<div class="layui-col-xs2">
				<div class="layui-inline">
					<div>
						<input type="text" id="realName" name="realName" value=""
							placeholder="请填写姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				</div>


				<div class="layui-col-xs2">
					<div class="layui-inline">
					<div>
						<input type="text" id="cardId" name="cardId" value=""
							placeholder="请填写身份证号" autocomplete="off" class="layui-input">
					</div>
					</div>
				</div>

				<div class="layui-col-xs2">
	           <div class="layui-inline">
	 				<div>
						<input type="text" id="phone" name="phone" value=""
							placeholder="请填写手机号" autocomplete="off" class="layui-input">
					</div>
               </div>
				</div>

				<div class="layui-col-xs2">
                 <div class="layui-inline">
					<div class="layui-input-inline">
						<select name="status" id ="status" lay-filter="aihao">
							<option value="0" selected="">请选择订单状态</option>
								<!-- 订单状态：0.未提交、1.审核中、2.审核通过、3.审核未通过、4.需补件、5.已取消、6.等待放款、7.已放款、8.补件完成、9.签约完成、10.银行处理中 -->
							<option value="1">提交申请</option>
							<option value="2">审核通过</option>
							<option value="3">审核未通过</option>
							<option value="4">需补件</option>
							<option value="5">已取消</option>
							<option value="6">等待放款</option>
							<option value="7">已放款</option>
							<option value="8">补件完成</option>
							<option value="9">签约完成</option>
							<option value="10">银行处理中</option>
						</select>
	             </div>
					</div>
				</div>
			</div>

				<hr class="layui-bg-gray">


			<div class="layui-row">

				<div class="layui-col-xs2">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="editstartdate"
								placeholder="请选择注册开始时间">
						</div>
					</div>
				</div>

				<div class="layui-col-xs2">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="editenddate"
								placeholder="请选择注册结束时间">
						</div>
					</div>
				</div>

				<div class="layui-col-xs2">
					<div class="layui-form-item">
					<div class="layui-inline">
							<select name="registerChannel" id="registerChannel" lay-filter="aihao">
								<option value="0" selected="">请选择用户注册渠道</option>
								<option value="1">花财</option>
								<option value="2">摩尔龙</option>
							</select>
						
						</div>
					</div>
				</div>


				<div class="layui-col-xs2">
					<div class="layui-form-item">
					<div class="layui-inline">
							<select name="registerOrderChannel" id="registerOrderChannel" lay-filter="aihao">
								<option value="0" selected="">请选择用户进件渠道</option>
								<option value="1">花财</option>
								<option value="2">摩尔龙</option>
							</select>
						
						</div>
					</div>
				</div>

				<div class="layui-col-xs2">
					<div class="layui-form-item">
						<div class="layui-btn-group">
						
							<button class="layui-btn" data-type="searchTable">搜索</button>
						</div>
						<div class="layui-btn-group">
							<button class="layui-btn layui-btn-primary" data-type="clearTable">重置</button>
						</div>
					</div>
				</div>
			</div>
				<hr class="layui-bg-green">
			</div>
		</div>
		
		<div style="width: auto; height: 200px;">
			<table class="layui-table" id="listTable"></table>
		</div>
	</section>
</div>




<script>
	layui.use([ 'laypage', 'layer', 'laydate', 'table', 'form','jquery' ], function() {
		var table = layui.table,
		  form = layui.form;
		table.render({
			elem : '#listTable',
			url : '${ctx}/order/orderList',
			cellMinWidth : 150, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			cols : [ [ {
				field : 'orderNo',
				title : '进件编号',
				templet : '#idTpl'
			}, {
				field : 'realName',

				title : '姓名'
			}, {
				field : 'phone',

				title : '手机'
			}, {
				field : 'loanAmount',

				title : '申请金额(元)'
			}, {
				field : 'loanTerm',
				title : '申请期数'
			}, {
				field : 'productNo',
				title : '申请产品'
			}, {
				field : 'createTime',
				  datetimeformat: true,
				title : '申请时间'
			}, {
				field : 'registerChannel',
				title : '申请渠道'
			}, {
				field : 'appType',
				title : '进件渠道',
				disabled : true,
				selected : true,
				//1:花财 2:摩尔龙 3:借吧
				jsontext : "[{ 'text': '花财', 'value': '1'},{ 'text': '摩尔', 'value': '2'},{ 'text': '借吧', 'value': '3'}]"
			}, {
				field : 'status',
				title : '状态',
				disabled : true,
				selected : true,
				//  //订单状态：0.未提交、1.审核中、2.审核通过、3.审核未通过、4.需补件、5.已取消、6.等待放款、7.已放款、8.补件完成、9.签约完成、10.银行处理中
				jsontext : "[{ 'text': '提交申请', 'value': '1'},{ 'text': '审核通过', 'value': '2'},{ 'text': '等待放款', 'value': '6'},{ 'text': '审核未通过', 'value': '3'},{ 'text': '已取消', 'value': '5'},{ 'text': '已放款', 'value': '7'},{ 'text': '补件完成', 'value': '8'} ,{ 'text': '签约完成', 'value': '9'},{ 'text': '银行处理中', 'value': '10'}]"} ] ],
			page : true,
			height : "310px",
			even : true,
			limits : [ 10, 15, 20 ],
			limit : 10
		});

		var laydate = layui.laydate;
		//时间选择器
		laydate.render({
			elem : '#zdystartdate',
			type : 'date',
		    done: function(value, date, endDate){
		    	 var value=$("#zdyenddate").val();
		    	 var value1=$("#zdystartdate").val();
				 var resultvalue2=getNowMonth(value);
				 var resultvalue= getNowMonth(value1);
		    	 if(value!=""){
		    		 $("#timearea").val(resultvalue + "-" + resultvalue2);
		    		 changeChart();
		    	 }
		    }
		});

		//时间选择器
		laydate.render({
			elem : '#zdyenddate',
			done: function(value, date, endDate){
			 var value1=$("#zdystartdate").val();
			 var resultvalue2=getNowMonth(value);
			 var resultvalue= getNowMonth(value1);
			 if(value1==""){
					var myDate = new Date();
					var day = myDate.getDate();
					var year = myDate.getFullYear();
					var month = myDate.getMonth() + 1;
					var nowdate = year + "年" + month + "月" + day + "日";	
					resultvalue=nowdate;
					$("#zdystartdate").val(year + "-" + month + "-" + day);
			 }
			 $("#timearea").val(resultvalue + "-" + resultvalue2);
			 changeChart();
			 $("#zdySelect").val(0);
			 
			 form.render('select');
			
			}
		});
		//时间选择器
		laydate.render({
			elem : '#editstartdate',
			type : 'date'
		});

		//时间选择器
		laydate.render({
			elem : '#editenddate',
			type : 'date'
		});

		var $ = layui.jquery, active = {
			searchTable : function() {
				var wheres = {};
				if ($("#orderNo").val() != '') {
					wheres.orderNo = $("#orderNo").val();
				}
				if ($("#realName").val() != '') {
					wheres.realName = $("#realName").val();
				}
				if ($("#phone").val() != '') {
					wheres.phone = $("#phone").val();
				}
				if ($("#status").val() != '' && $("#status").val() != '0' ) {
					wheres.status = $("#status").val();
				}
				if ($("#registerChannel").val() != '' && $("#registerChannel").val() != '0') {
					wheres.registerChannel = $("#registerChannel").val();
				}
				if ($("#registerOrderChannel").val() != '' && $("#registerOrderChannel").val() != '0') {
					wheres.appType = $("#registerOrderChannel").val();
				}
				if ($("#deviceModelLogin").val() != '') {
					wheres.deviceModelLogin = $("#deviceModelLogin").val();
				}
				if ($("#editstartdate").val() != '') {
					wheres.editstartdate = $("#editstartdate").val();
				}
				if ($("#editenddate").val() != '') {
					wheres.editenddate = $("#editenddate").val();
				}
				table.reload('listTable', {
					where : wheres
				});
				return false;
			},
			clearTable : function() {
				$("#orderNo").val("");
				$("#realName").val("");
				$("#phone").val("");
				$("#status").val('0');
				$("#registerChannel").val("");
				$("#registerOrderChannel").val("");
				
				$("#deviceModelLogin").val("");
				$("#editstartdate").val("");
				$("#editenddate").val("");
				form.render('select');
			}
		};
		$('.layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

		
		   form.on('select(channelDay)', function(data){
			   
		  // alert(data.value);
		//$("#channelDay").change(function() {
			var ss = data.value;
			$("#zdystartdate").val("");
			$("#zdyenddate").val("");
			 $("#zdySelect").val(0);
			var myDate = new Date();
			var day = myDate.getDate();
			var year = myDate.getFullYear();
			var month = myDate.getMonth() + 1;
			var nowdate = year + "年" + month + "月" + day + "日";
			if (ss == "0") {
				var date7 = new Date(myDate.getTime() - 7 * 24 * 3600 * 1000);
				var day7 = date7.getDate();
				var yesdate = year + "年" + month + "月" + day7 + "日";
				$("#timearea").val(yesdate + "-" + nowdate);
			} else if (ss == "1") {
				var yesdate = getDatemony(15);
				var day = yesdate.getDate();
				var year = yesdate.getFullYear();
				var month = yesdate.getMonth() + 1;
				var yesdate = year + "年" + month + "月" + day + "日";
				$("#timearea").val(yesdate + "-" + nowdate);
			} else if (ss == "2") {
				var nowdate1 = year + "-" + month + "-" + day;
				var yesdate = getPreMonth(nowdate1)
				$("#timearea").val(yesdate + "-" + nowdate);
			}
			 changeChart();
			 form.render('select');
		});
		
		 form.on('select(zdySelect)', function(data){
		//$("#zdySelect").change(function() {
			var sst = data.value;
			 $("#channelDay").val("");
			 form.render('select');
			//var sst = $(this).children('option:selected').val();
			var myDate = new Date();
			var day = myDate.getDate();
			var year = myDate.getFullYear();
			var month = myDate.getMonth() + 1;
			var nowdate = year + "年" + month + "月" + day + "日";
			var nowdateValue = year + "-" + month + "-" + day ;
			if (sst == "0") {
				$("#zdystartdate").val("");
				$("#zdyenddate").val("");
				
			} else if (sst == "1") {
			
				var yesdate = getDatemony(15);
				var day = yesdate.getDate();
				var year = yesdate.getFullYear();
				var month = yesdate.getMonth() + 1;
				var yesdate = year + "年" + month + "月" + day + "日";
				var yesdateValue = year + "-" + month + "-" + day ;
				$("#zdystartdate").val(yesdateValue);
				$("#zdyenddate").val(nowdateValue);
				$("#timearea").val(yesdate + "-" + nowdate);
			} else if (sst == "2") {
				var nowdate1 = year + "-" + month + "-" + day;
				var yesdate = getPreMonth(nowdate1);
				$("#zdystartdate").val(yesdate.replace('年', '-').replace('月', '-').replace(
						'日', ''));
				$("#zdyenddate").val(nowdateValue);
				$("#timearea").val(yesdate + "-" + nowdate);
			}
			changeChart();
		});
		
		
		

		var getDatemony = function(n) {
			var now = new Date;
			now.setDate(now.getDate() - n);
			return now;
		}

	});
</script>



<script type="text/html" id="idTpl">
     <a style="color:#01AAED" onclick="window.parent.addTabs({id:'huacai3000',title: '订单详情',close: true,url: '${ctx}/order/detailorderInfo?orderNo={{d.orderNo}}&userId={{d.userId}}'});">{{d.orderNo}}</a>
 </script>

