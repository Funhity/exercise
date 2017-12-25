<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%@ include file="../layouts/form_lead_in.jsp"%>
<script src="${ctx}/static/publish/dist/echarts/echarts.min.js"></script>
<script src="${ctx}/static/publish/dist/echarts/echarts.common.min.js"></script>

<script>
    $(function() {
        var myDate = new Date();
        var day = myDate.getDate();
        var year = myDate.getFullYear();
        var month = myDate.getMonth() + 1;
        var nowdate = year + "年" + month + "月" + day + "日";
        var date7 = new Date(myDate.getTime() - 7 * 24 * 3600 * 1000);
        var day7 = date7.getDate();
        var yesdate = year + "年" + month + "月" + day7 + "日";
        $("#timearea").val(yesdate + "-" + nowdate);
    })
</script>

<div class="wrapper">
    <section class="content">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
            <legend>注册详情</legend>
        </fieldset>

        <div class="layui-row">
            <div class="layui-row layui-col-space1">
                <div class="layui-col-md2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>用户ID</label>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name='id' style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value=<%=request.getAttribute( "cateList")%> autocomplete="left" class="layui-input">
                        </div>
                    </div>

                </div>
                <div class="layui-col-md2">

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>注册手机</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="phone" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" value=<%=request.getAttribute( "phone")%> autocomplete="left" class="layui-input">.
                        </div>
                    </div>
                </div>
                <div class="layui-col-md2">

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>注册渠道</label>
                        </div>
                    </div>
                    <input type="text" name="channelV" value=<%=request.getAttribute( "RegisterChannel")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                </div>
                <div class="layui-col-md2">

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>注册时间</label>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <div class="layui-form-item">
                                <input type="text" name="channelDate" value=<%=request.getAttribute( "ctime")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                            </div>

                        </div>
                    </div>

                </div>

                <div class="layui-col-md2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>注册设备</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="channelDevice" value=<%=request.getAttribute( "ddetype")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-row layui-col-space1">
                <div class="layui-col-md2">

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>注册机型</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="channelType" value=<%=request.getAttribute( "DeviceModelRegister")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>是否实名</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="isRealName" value='是' autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>姓名</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="name" value=<%=request.getAttribute( "realname")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-col-md2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>身份证</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="cardNo" value=<%=request.getAttribute( "IdCard")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-col-md2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label>性别</label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" name="sex" value=<%=request.getAttribute( "sex")%> autocomplete="off" style="border: 0px; background: rgba(0, 0, 0, 0); text-align: left;" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            layui.use(['laypage', 'layer', 'laydate', 'table',
                'jquery'
            ], function() {
                var table = layui.table;
                var $ = layui.jquery,
                    active = {
                    };
                $('.layui-btn').on('click', function() {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
                $("#showChannlday").change(
                    function() {
                        var ss = $(this)
                            .children('option:selected').val();
                        var myDate = new Date();
                        var day = myDate.getDate();
                        var year = myDate.getFullYear();
                        var month = myDate.getMonth() + 1;
                        var nowdate = year + "年" + month + "月" + day + "日";
                        if (ss == "0") {
                            var date7 = new Date(myDate.getTime() - 7 * 24 * 3600 * 1000);
                            var day7 = date7.getDate();
                            var yesdate = year + "年" + month + "月" + day7 + "日";
                            $("#timearea").val(
                                yesdate + "-" + nowdate);
                        } else if (ss == "1") {
                            var yesdate = getDatemony(15);
                            var day = yesdate.getDate();
                            var year = yesdate.getFullYear();
                            var month = yesdate.getMonth() + 1;
                            var yesdate = year + "年" + month + "月" + day + "日";
                            $("#timearea").val(
                                yesdate + "-" + nowdate);
                        } else if (ss == "2") {
                            var nowdate1 = year + "-" + month + "-" + day;
                            var yesdate = getPreMonth(nowdate1)
                            $("#timearea").val(
                                yesdate + "-" + nowdate);
                        }
                    });
                var getDatemony = function(n) {
                    var now = new Date;
                    now.setDate(now.getDate() - n);
                    return now;
                }

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
                }
            });
        </script>
    </section>
</div>