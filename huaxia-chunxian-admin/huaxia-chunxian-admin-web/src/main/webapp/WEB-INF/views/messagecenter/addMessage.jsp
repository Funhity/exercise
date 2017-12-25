<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>
<%@ include file="../layouts/ue_lead_in.jsp" %>

<div class="wrapper">
    <section class="content">
        <div class="Tabmain">
            <div class="layui-row cttBox2" style="margin-top: 20px;">
                <div class="layui-col-xs12">
                    <div class="layui-tab layui-tab-brief" lay-filter="">
                        <ul class="layui-tab-title">
                            <li class="layui-this">个人消息</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <!--主框架-->
        <div id="mainFrame" class="layui-row cttBox2">
            <div class="layui-col-xs12">
                <div class="layui-tab-content">
                    <!--个人消息-->
                    <div class="layui-tab-item layui-show">
                        <!--新增个人消息-->
                        <div id="addMessageMain" class="box box-primary" style="border-top-color: #009688;"
                             autocomplete="off">
                            <div class="box-header">
                                <h3 class="box-title">接收人</h3>
                            </div>
                            <div class="layui-row">
                                <div class="layui-col-xs12">
                                    <div class="layui-form-item">
                                        <div class="layui-row"
                                             style="background:url(${ctx}/static/publish/dist/images/lineborder.png) repeat-x bottom;padding:0 0 30px 0; margin-bottom: 20px;">
                                            <form id="receiverSearchForm" action="" class="layui-form">
                                            <div class="layui-col-xs4">
                                                <div class="layui-inline">
                                                    <input type="text" id="nameSearch" name="name" value="${requestScope.userMessage.name}"
                                                           lay-verify=""
                                                           placeholder="请输入姓名" autocomplete="off" class="layui-input">
                                                </div>
                                                <div class="layui-inline">
                                                    <input type="text" id="phoneSearch" name="phone" value="${requestScope.userMessage.phone}"
                                                           lay-verify=""
                                                           placeholder="请输入手机号" autocomplete="off" class="layui-input">
                                                </div>
                                            </div>
                                            </form>
                                            <div class="layui-col-xs4">
                                                <div class="layui-inline">
                                                    <button class="layui-btn" data-type="searchReceiver">搜索</button>
                                                </div>
                                                <div class="layui-inline">
                                                    <button class="layui-btn layui-btn-primary" style="border:none" data-type="resetSearchReceiver">重置
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="layui-table" id="LAY_table_messageReceiver"
                                               lay-filter="testMessageReceiver"></table>
                                        <script type="text/html" id="phoneTpl">
                                            <span>{{phoneHide(d.phone)}}</span>
                                        </script>
                                        <br><br>
                                        <div class="box box-primary">
                                            <div class="box-header">
                                                <h3 class="box-title">消息内容</h3>
                                            </div>
                                            <form id="addMessageForm" action="" class="layui-form">
                                                <input type="hidden" id="userIdJson">
                                            <div class="layui-row">
                                                <div class="layui-col-12">
                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <label style="float:left">消息标题：</label>
                                                        </div>
                                                    </div>
                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <input type="text" id="title" name="title" value="${requestScope.userMessage.title}" lay-verify=""
                                                                   placeholder="" autocomplete="off" lay-verify="required"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>
                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <label style="float:left">消息内容：</label>
                                                        </div>
                                                    </div>
                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <script type="text/plain" id="messageEditor" name="content" lay-verify="required">
                                                            </script>
                                                        </div>
                                                    </div>
                                                    <div class="layui-form-item">
                                                        <div class="layui-form-item">
                                                            <div class="layui-inline">
                                                                <label style="float:left">设置发送时间：</label>
                                                            </div>
                                                        </div>
                                                        <div class="layui-form-item">
                                                            <div class="layui-inline">
                                                                <select id="messageSendType" lay-verify="required" lay-filter="messageSendType">
                                                                    <option value="" selected>请选择</option>
                                                                    <option value="0">即刻发送</option>
                                                                    <option value="1">定时发送</option>
                                                                </select>
                                                            </div>
                                                            <div class="layui-inline">
                                                                <input type="text" class="layui-input"
                                                                       id="messageSendTime" placeholder="请选择时间"
                                                                value="" lay-verify="required">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </form>
                                            <div class="layui-form-item">
                                                <div class="layui-inline"
                                                     style="display: table; width: auto;margin-left: auto;margin-right: auto;border:none">
                                                    <button id="addMessage" class="layui-btn layui-btn-bg"
                                                            lay-submit lay-filter="addMessageSave"
                                                            data-type="addMessageSubmit">新增
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function phoneHide(phone) {
                console.log(phone)
                var tel = phone;
                var reg = /^(\d{3})\d{4}(\d{4})$/;
                tel = tel.replace(reg, "$1****$2");

                return tel;
            }
            layui.use(['form','element', 'table', 'layer', 'jquery', 'laydate'], function () {
                var element = layui.element,
                    $ = layui.jquery,
                    table = layui.table,
                    form = layui.form;
                $('.layui-btn').on('click', function () {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
                $(function(){
                    $("#addMessage").val("返回");
                    $("#content").val('${requestScope.userMessage.content}');
                    setTimeout(function(){
                        ueMessage.setContent('${requestScope.userMessage.content}');
                    }, 666);
                    $("#title").val('${requestScope.userMessage.title}');
                    $("#messageSendTime").val('${requestScope.userMessage.sendTime}');
                });

                var baseMessageUrl = "${ctx}/messagecenter/messageinfos";
                var baseReceiverUrl = "${ctx}/messagecenter/receivers";

                function clearMessageForm() {
                    var formBox=$("#addMessageForm");
                    formBox[0].reset();
                    $("[name='id']").val('');
                    setTimeout(function(){
                        ueMessage.setContent('');
                    }, 666);
                    table.reload("LAY_table_messageReceiver");
                }

                //接收人
                table.render({
                    elem: "#LAY_table_messageReceiver",
                    url: baseReceiverUrl+'?phone=${requestScope.userMessage.phone}',
                    cols: [
                        [{
                            type: 'checkbox'
                        }, {
                            field: "loginName",
                            title: "姓名"
                        }, {
                            field: "phone",
                            title: "手机号",
                            templet: '#phoneTpl',
                        }, {
                            field: "registerChannel",
                            title: "注册渠道"
                        }, {
                            field: "createTime",
                            title: "注册时间",
                            datetimeformat: true
                        }]
                    ],
                    id: "testMessageReceiver",
                    cellMinWidth: 120,
                    even: true,
                    page: true,
                    limits: [10, 15, 20],
                    limit: 10,
                    height: "400"
                });
                table.on('checkbox(testMessageReceiver)', function(obj){
                    //console.log(obj.checked); //当前是否选中状态
                    //console.log(obj.data); //选中行的相关数据
                    //console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
                });

                var $ = layui.$,
                    active = {
                        addMessageSubmit: function () { //获取选中数据
                            var checkStatus = table.checkStatus('testMessageReceiver')
                                ,data = checkStatus.data;
                            $("#content").val(ueMessage.getContent());
                            var messageJson = $("#addMessageForm").serializeObject();
                            form.on('submit(addMessageSave)', function(data) {
                                for(var i in data) {
                                messageJson["userId"] = data[i].userId;
                                $.ajax({
                                    type: 'post',
                                    url: baseMessageUrl,
                                    contentType: 'application/json; charset=utf-8',
                                    data: JSON.stringify(messageJson),
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == '0000') {
                                            clearMessageForm();
                                            layer.msg('新增成功');
                                            window.parent.addTabs({id:'huacai30005',title: '消息中心',close: true,url: '${ctx}/messagecenter/main'});
                                        } else {
                                            //权限403
                                            if(data.code === 0) {
                                                window.location.href="${ctx}/main/login";
                                                return false;
                                            } else {
                                                layer.alert('新增失败！' + data.msg);
                                            }
                                        }
                                    },
                                    error: function (data) {
                                        console.log(data);
                                        layer.alert('新增失败');
                                    }
                                });
                            }
                            });
                        },
                        searchReceiver: function () {
                            var wheres = {};
                            wheres.loginName = $("#nameSearch").val();
                            wheres.phone = $("#phoneSearch").val();
                            table.reload('testMessageReceiver', {
                                where: wheres
                            });
                        },
                        resetSearchReceiver: function () {
                            var formBox=$("#receiverSearchForm");
                            formBox[0].reset();
                            var wheres = {};
                            wheres.loginName = $("#nameSearch").val();
                            wheres.phone = $("#phoneSearch").val();
                            table.reload('testMessageReceiver', {
                                where: wheres
                            });
                        }
                    };



                var laydate = layui.laydate;
                //时间选择器
                laydate.render({
                    elem: '#messageSendTime',
                    type: 'datetime'
                });

                //实例化编辑器
                var ueMessage = UE.getEditor('messageEditor', {
                    initialFrameHeight: 300,
                    initialFrameWidth: null
                });



                form.on('select(messageSendType)', function(data) {
                    if(data.value == '0') {
                        $("#messageSendTime").val(new Date().format("yyyy-MM-dd hhh:mm:ss"));
                    }

                    if(data.value == '1') {
                        $("#messageSendTime").val('');
                    }
                });
            });
        </script>
    </section>
</div>