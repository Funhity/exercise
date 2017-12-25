<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>
<script src="${ctx}/static/publish/dist/echarts/echarts.min.js"></script>
<script src="${ctx}/static/publish/dist/echarts/echarts.common.min.js"></script>


<script>
	$(function() {
		show('${ctx}/consumer/countconsumerInfo', 'consumeMain','单日注册量');
		show('${ctx}/order/countorderInfo', 'main','单日订单量');
	});
	
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
	
	function show(url, divId,echartname) {
		var sdate = [], sdata = [];
		$("#" + divId + " div:first-child").attr({
			"cursor" : "ew-resize"
		})
		var myDate = new Date();
		var myDate = new Date();
		var day = myDate.getDate();
		var year = myDate.getFullYear();
		var month = myDate.getMonth() + 1;
		var nowdate = year + "年" + month + "月" + day + "日";
		var nowdateNext = year + "-" + month + "-" + day;
		var yesdate = getPreMonth(nowdateNext)
		$("#timearea").val(yesdate + "-" + nowdate);
		//console.log($("#timearea").val()+"=========");
		var myChart = echarts.init(document.getElementById(divId));
		$.ajax({
			type : 'GET',
			url : url,//'${ctx}/demo/countconsumerInfo',
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
				console.log("----@@@@@@@@@@@-");
			}
		});
		// 基于准备好的dom，初始化echarts实例
		
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



		var option = {
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
			}  */],
			series : [ {
				name : echartname,
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
	}


</script>

<div class="wrapper">
    <section class="content">

            <br><br>
            <fieldset class="layui-elem-field layui-field-title"
                      style="margin-top: 1px;">
                <legend>注册情况</legend>
            </fieldset>
            <div style="float:right">
                <a href="#" onclick="window.parent.addTabs({id:'huacai30004',title: '用户管理',close: true,url: '${ctx}/consumer/consumerInfo'});">
                    查看更多
                </a>
            </div>

        <div>
            <input type="hidden" id="timearea" name="timearea" value=""
                   lay-verify="timearea" autocomplete="off" class="layui-input">
        </div>

        <div id="consumeMain" style="width: 800px; height: 300px;"></div>
        <fieldset class="layui-elem-field layui-field-title"
                  style="margin-top: 1px;">
            <legend>订单情况</legend>
        </fieldset>
            <div style="float:right">
                <a href="#" onclick="window.parent.addTabs({id:'huacai30003',title: '订单管理',close: true,url: '${ctx}/order/orderInfo'});">
                    查看更多
                </a>
            </div>


        <div id="main" style="width: 800px; height: 300px;"></div>

         </section>

</div>