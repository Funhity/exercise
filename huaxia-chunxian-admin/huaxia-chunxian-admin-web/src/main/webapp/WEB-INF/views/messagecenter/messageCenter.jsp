<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>
<%@ include file="../layouts/ue_lead_in.jsp" %>

<div class="wrapper">
    <section class="content">
        <div class="Tabmain">
            <div class="layui-row cttBox2" style="margin-top: 20px;">
                <div class="layui-col-xs12">
                    <div class="layui-tab layui-tab-brief" lay-filter="messageCenterTab">
                        <ul class="layui-tab-title">
                            <li class="layui-this">系统公告</li>
                            <li>个人消息</li>
                            <button id="addNoticeBtn" class="layui-btn layui-btn-sm"
                                    style="display: block;float:right;margin-bottom:10px;margin-right:40px"
                                    data-type="addNotice">添加公告
                            </button>
                            <button id="addMessageBtn" class="layui-btn layui-btn-sm"
                                    style="display: none;float:right;margin-bottom:10px;margin-right:40px"
                                    data-type="addMessage">添加个人消息
                            </button>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <!--主框架-->
        <div id="mainFrame" class="layui-row cttBox2">
            <div class="layui-col-xs12">
                <div class="layui-tab-content">
                    <!--系统公告-->
                    <div class="layui-tab-item layui-show">
                        <div class="box box-primary" style="border-top-color: #009688;">
                            <div class="box-header">
                                <h3 class="box-title"></h3>
                            </div>
                            <form id="searchNoticeForm" action="" class="layui-form">
                                <div class="layui-row">
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <input type="text" id="title" name="title" value="" lay-verify=""
                                                       placeholder="请填写公告标题" autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <select id="sendStatus" name="sendStatus" lay-verify="" class="input-mini">
                                                    <option value="" selected>请选择状态</option>
                                                    <option value="0">待发送</option>
                                                    <option value="1">已发送</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-input-inline">
                                                <input type="text" class="layui-input" id="sendTimeStart"
                                                       placeholder="请选择发送开始时间">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-input-inline">
                                                <input type="text" class="layui-input" id="sendTimeEnd"
                                                       placeholder="请选择发送结束时间">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="layui-row">
                                <div class="layui-inline">
                                    <button class="layui-btn" data-type="searchNotice">搜索</button>
                                </div>
                                <div class="layui-inline">
                                    <button class="layui-btn layui-btn-primary"  data-type="resetSearchNotice">重置</button>
                                </div>
                            </div>
                            <hr>
                            <table class="layui-table" id="LAY_table_notice" lay-filter="test"></table>
                            <script type="text/html" id="barNotice">
								{{# if(d.sendStatus == '0'){ }}
                                		<a class="layui-btn layui-btn-xs" lay-event="editNotice">编辑</a>
								{{# }else if(d.sendStatus == '1'){ }}
                                		<a class="layui-btn layui-btn-xs" lay-event="viewNotice">查看</a>
								{{# } }}
                            </script>
                        </div>

                    </div>

                    <!--个人消息-->
                    <div class="layui-tab-item">
                        <div id="messageMain" class="box box-primary" style="border-top-color: #009688;">
                            <div class="box-header">
                                <h3 class="box-title"></h3>
                            </div>
                            <form id="messageSearchForm" action="" class="layui-form">
                                <div class="layui-row">
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <input type="text" id="nameSearch" name="nameSearch" value="" lay-verify=""
                                                       placeholder="请填写姓名" autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <input type="text" id="phoneSearch" name="phoneSearch" value="" lay-verify=""
                                                       placeholder="请填写手机号" autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <select id="stateSearch" name="stateSearch" lay-verify="required" class="input-mini">
                                                    <option value="" selected>请选择状态</option>
                                                    <option value="1">已读</option>
                                                    <option value="0">未读</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-input-inline">
                                                <input type="text" class="layui-input" id="startTime1"
                                                       placeholder="请选择发送开始时间">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-xs2">
                                        <div class="layui-form-item">
                                            <div class="layui-input-inline">
                                                <input type="text" class="layui-input" id="endTime1"
                                                       placeholder="请选择发送结束时间">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="layui-row">
                                <div class="layui-inline">
                                    <button class="layui-btn" data-type="messageSearch">搜索</button>
                                </div>
                                <div class="layui-inline">
                                    <button class="layui-btn layui-btn-primary" style="border:none" data-type="resetMessageSearchForm">重置</button>
                                </div>
                            </div>
                            <hr>
                            <table class="layui-table" id="LAY_table_message" lay-filter="test1"></table>
                            <script type="text/html" id="barDemo1">
                                <a class="layui-btn layui-btn-xs" lay-event="edit">查看</a>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--公告弹出层-->
        <form id="noticeDetailForm" class="notice-detail-form layui-form pad30" style="display: none;"
              autocomplete="off">
            <input type="hidden" id="noticeId" name="id" class="noticeId">
            <div class="layui-row">
                <div class="layui-col-12">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="float:left">公告标题：<font color="red">*</font></label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" id="noticeTitle" name="title" value="" lay-verify="required" placeholder="这是公告标题"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="float:left">公告内容：<font color="red">*</font></label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <script type="text/plain" id="noticeEditor" name="content" lay-verify="required">
                            </script>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label style="float:left">发送时间：<font color="red">*</font></label>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <select id="noticeSendType" lay-filter="noticeSendType">
                                    <option value="" selected>请选择发送方式</option>
                                    <option value="0">即时</option>
                                    <option value="1">固定时间</option>
                                </select>
                            </div>
                            <div class="layui-inline">
                                <input type="text" class="layui-input" id="noticeSendTimeEdit" name="sendTime" placeholder="请选择发送时间" lay-verify="required" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-small noticeDetailFormSave" lay-submit="" lay-filter="noticeDetailFormSave" style="display: none;"></button>
                        <button class="layui-btn layui-btn-small noticeDetailFormAdd" lay-submit="" lay-filter="noticeDetailFormAdd" style="display: none;"></button>
                    </div>
                </div>
            </div>
        </form>

        <script>
            layui.use(['element','form', 'table', 'layer', 'jquery', 'laydate'], function () {
                var element = layui.element,
                    $ = layui.jquery,
                    form = layui.form,
                    table = layui.table;
                $('.layui-btn').on('click', function () {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
                var $ = layui.$,
                    active = {
                        addNotice: function () { //获取选中数据
                            clearNoticeForm();
                            addNoticeForm();
                        },
                        addMessage: function () { //获取选中数据
                            window.parent.addTabs({id:'huacai300051',title: '个人消息',close: true,url: '${ctx}/messagecenter/addMessage'});
                        },
                        searchNotice: function () {
                            table.render();
                            var wheres = {};
                            wheres.title = $("#title").val();
                            wheres.sendTimeStart = $("#sendTimeStart").val();
                            wheres.sendTimeEnd = $("#sendTimeEnd").val();
                            wheres.sendStatus = $("#sendStatus").val();
                            table.reload('testTab', {
                                where: wheres
                            });
                        },
                        resetSearchNotice: function () {
                            var formBox=$("#searchNoticeForm");
                            formBox[0].reset();
                            var wheres = {};
                            wheres.title = $("#title").val();
                            wheres.sendTimeStart = $("#sendTimeStart").val();
                            wheres.sendTimeEnd = $("#sendTimeEnd").val();
                            wheres.sendStatus = $("#sendStatus").val();
                            table.reload('testTab', {
                                where: wheres
                            });
                        },
                        messageSearch: function () {
                            var wheres = {};
                            wheres.name = $("#nameSearch").val();
                            wheres.phone = $("#phoneSearch").val();
                            wheres.state = $("#stateSearch").val();
                            wheres.sendTimeStart = $("#sendTime1").val();
                            wheres.sendTimeEnd = $("#endTime1").val();
                            table.reload('testTable', {
                                where: wheres
                            });
                            table.render();
                        },
                        resetMessageSearchForm: function () {
                            var formBox=$("#messageSearchForm");
                            formBox[0].reset();
                            var wheres = {};
                            wheres.name = $("#nameSearch").val();
                            wheres.phone = $("#phoneSearch").val();
                            wheres.state = $("#stateSearch").val();
                            wheres.sendTimeStart = $("#sendTime1").val();
                            wheres.sendTimeEnd = $("#endTime1").val();
                            table.reload('testTable', {
                                where: wheres
                            });
                        }
                    };
                element.on('tab(messageCenterTab)', function (data) {
                    if (data.index === 0) { //系统公告
                        $("#addNoticeBtn").show();
                        $("#addMessageBtn").hide();
                        $(".layui-tab-content .layui-tab-item:first-child").addClass("layui-show");
                        $(".layui-tab-content .layui-tab-item:last-child").removeClass("layui-show");
                    }
                    if (data.index === 1) { //个人消息
                        $("#addNoticeBtn").hide();
                        $("#addMessageBtn").show();
                        $(".layui-tab-content .layui-tab-item:first-child").removeClass("layui-show");
                        $(".layui-tab-content .layui-tab-item:last-child").addClass("layui-show");
                        loadMessageTable(); //个人信息表格加载
                    }
                });

                var baseNoticeUrl = "${ctx}/messagecenter/notices";
                table.render({
                    elem: "#LAY_table_notice",
                    url: baseNoticeUrl,
                    cols: [
                        [{
                            field: "id",
                            title: "公告id",
                            minWidth: 40
                        }, {
                            field: "title",
                            title: "公告标题"
                        }, {
                            field: "sendStatus",
                            title: "状态",
                            disabled: true,
                            selected: true,
                            jsontext: "[{ 'text': '待发送', 'value': '0'},{ 'text': '已发送', 'value': '1'}]"
                        }, {
                            field: "updateUser",
                            title: "编辑人"
                        }, {
                            field: "updateTime",
                            title: "编辑时间",
                            datetimeformat: true
                        }, {
                            field: "sendTime",
                            title: "发送时间",
                            datetimeformat: true
                        }, {
                            title: "操作",
                            fixed: 'right',
                            align: 'center',
                            minWidth: 60,
                            toolbar: '#barNotice'
                        }]
                    ],
                    id: "testTab",
                    //cellMinWidth: 120,
                    even: true,
                    page: true,
                    limits: [10, 15, 20],
                    limit: 10,
                    height: "full-290"
                });
                table.on('tool(test)', function (obj) {
                    var data = obj.data;
                    if (obj.event === 'viewNotice') {
                    	clearNoticeForm();
                    	viewNoticeForm();
                        data.sendTime=new Date(data.sendTime).format("yyyy-MM-dd hh:mm:ss");
                        $('.notice-detail-form').initForm({jsonValue:data});
                        setTimeout(function(){
                            ue.setContent(data.content);
                        }, 666);
                    }
                    if (obj.event === 'editNotice') {
                    	clearNoticeForm();
	                    editNoticeForm();
	                    data.sendTime=new Date(data.sendTime).format("yyyy-MM-dd hh:mm:ss");
                        $('.notice-detail-form').initForm({jsonValue:data});
                        setTimeout(function(){
                            ue.setContent(data.content);
                        }, 666);
                	}
                });

                function viewNoticeForm() {
                    $('input,select,textarea',$('#noticeDetailForm')).attr('readonly',true);
                    layer.open({
                        type: 1,
                        title: ['查看公告', 'font-size:18px;text-align:center'],
                        area: ['700px', '800px'],
                        maxHeight: 800,
                        content: $('#noticeDetailForm'),
                        shift: 2,
                        closeBtn: 2,
                        btnAlign: 'c',
                        offset: '20px',
                        cancel: function (index, layero) {
                            clearNoticeForm();
                            layer.close(index);
                            return false;
                        }
                    });
                }
                
                function editNoticeForm() {
                    layer.open({
                        type: 1,
                        title: ['编辑公告', 'font-size:18px;text-align:center'],
                        area: ['700px', '800px'],
                        maxHeight: 800,
                        content: $('#noticeDetailForm'),
                        shift: 2,
                        closeBtn: 2,
                        btn: ['更新', '删除'],
                        btnAlign: 'c',
                        offset: '20px',
                        yes: function (index, layero) {
                            layero.find('.noticeDetailFormSave').click();
                        },
                        btn2: function (index, layero) {
                            layer.confirm('是否删除这条数据?', {
                                icon: 3,
                                title: '提示'
                            }, function (index) {
                                $.ajax({
                                    type: 'delete',
                                    url: baseNoticeUrl + "/" + $('#noticeId').val(),
                                    contentType: 'application/json; charset=utf-8',
                                    dataType: "json",
                                    success: function (data) {
                                    	clearNoticeForm();
                                        if (data.code == '0000') {
                                            layer.msg("删除成功！")
                                            table.reload('testTab')
                                        } else {
                                            //权限403
                                            if(data.code === 0) {
                                                window.location.href="${ctx}/main/login";
                                                return false;
                                            } else {
                                                layer.msg("删除失败！" + data.msg);
                                            }
                                        }
                                        layer.close(index);
                                    },
                                    error: function (data) {
                                        console.log(data);
                                        layer.alert('删除失败');
                                    }
                                });
                            });
                        },
                        cancel: function (index, layero) {
                            clearNoticeForm();
                            layer.close(index);
                            return false;
                        }
                    });
                }

                function addNoticeForm() {
                    layer.open({
                        type: 1,
                        title: ['添加公告', 'font-size:18px;text-align:center'],
                        area: ['700px', '800px'],
                        maxHeight: 800,
                        content: $('#noticeDetailForm'),
                        shift: 2,
                        closeBtn: 2,
                        btn: '添加',
                        btnAlign: 'c',
                        offset: '20px',
                        yes: function (index, layero) {
                            layero.find('.noticeDetailFormAdd').click();
                        }
                    });
                }

                /***编辑方法***/
                form.on('submit(noticeDetailFormSave)', function(data) {
                    var noticeDetailJson = $("#noticeDetailForm").serializeObject();
                    $.ajax({
                        type: 'put',
                        url: baseNoticeUrl + "/" + $('#noticeId').val(),
                        contentType: 'application/json; charset=utf-8',
                        data: JSON.stringify(noticeDetailJson),
                        dataType: "json",
                        success: function (data) {
                            if (data.code == '0000') {
                                layer.msg('修改成功');
                                clearNoticeForm();
                                layer.closeAll();
                                table.reload('testTab');
                            } else {
                                //权限403
                                if(data.code === 0) {
                                    window.location.href="${ctx}/main/login";
                                    return false;
                                } else {
                                    layer.msg('修改失败!' + data.msg);
                                }
                            }
                        },
                        error: function (data) {
                            console.log(data);
                            layer.alert('修改失败');
                        }
                    });
                    return false;
                });

                /***新增方法***/
                form.on('submit(noticeDetailFormAdd)', function(data) {
                    var noticeDetailJson = $("#noticeDetailForm").serializeObject();
                    $.ajax({
                        type: 'post',
                        url: baseNoticeUrl,
                        contentType: 'application/json; charset=utf-8',
                        data: JSON.stringify(noticeDetailJson),
                        dataType: "json",
                        success: function (data) {
                            if (data.code == '0000') {
                                layer.msg('新增成功');
                                table.reload('testTab')
                            } else {
                                //权限403
                                if(data.code === 0) {
                                    window.location.href="${ctx}/main/login";
                                    return false;
                                } else {
                                    layer.alert('新增失败！' + data.msg)
                                }
                            }
                            layer.closeAll();
                        },
                        error: function (data) {
                            console.log(data);
                            layer.alert('新增失败');
                        }
                    });
                    return false;
                });

                function clearNoticeForm() {
                    var formBox = $('.notice-detail-form');
                    formBox[0].reset();
                    $("[name='id']").val('');
                    formBox.find('input').removeClass('right');
                    formBox.find('.prompt').removeClass('false');
                    formBox.find('.prompt').removeClass('right');
                    $('input,select,textarea',$('#noticeDetailForm')).attr('readonly',false);
                    setTimeout(function(){
                        ue.setContent('');
                    }, 666);
                }

                var listMessageUrl = "${ctx}/messagecenter/usermessages";
                function loadMessageTable() {
                    table.render({
                        elem: "#LAY_table_message",
                        url: listMessageUrl,
                        cols: [
                            [{
                                field: "id",
                                title: "消息id",
                                minWidth: 40
                            }, {
                                field: "userName",
                                title: "姓名"
                            }, {
                                field: "phone",
                                title: "手机"
                            }, {
                                field: "title",
                                title: "标题"
                            }, {
                                field: "state",
                                title: "状态",
                                disabled: true,
                                selected: true,
                                jsontext: "[{ 'text': '未读', 'value': '0'},{ 'text': '已读', 'value': '1'}]"
                            }, {
                                field: "updateUser",
                                title: "编辑人"
                            }, {
                                field: "updateTime",
                                title: "编辑时间",
                                datetimeformat: true
                            }, {
                                field: "sendTime",
                                title: "发送时间",
                                datetimeformat: true
                            }, {
                                title: "操作",
                                fixed: 'right',
                                align: 'center',
                                toolbar: '#barDemo1'
                            }]
                        ],
                        id: "testTable",
                        cellMinWidth: 120,
                        even: true,
                        page: true,
                        limits: [10, 15, 20],
                        limit: 10,
                        height: "full-200"
                    });

                }

                function rmLoadMessageTable() {
                    $("#LAY_table_message").remove();
                }

                table.on('tool(test1)', function (obj) {
                    var data = obj.data;
                    console.log(data);
                    if (obj.event === 'edit') {
                        //跳转页面
                        window.parent.addTabs({id:'huacai300051',title: '个人消息',close: true,url: '${ctx}/messagecenter/addMessage?messageId='+data.id});
                    }
                });


                var laydate = layui.laydate;
                //时间选择器
                laydate.render({
                    elem: '#sendTimeStart',
                    type: 'datetime'
                });
                laydate.render({
                    elem: '#sendTimeEnd',
                    type: 'datetime'
                });
                laydate.render({
                    elem: '#startTime1',
                    type: 'datetime'
                });
                laydate.render({
                    elem: '#endTime1',
                    type: 'datetime'
                });
                laydate.render({
                    elem: '#noticeSendTimeEdit',
                    type: 'datetime'
                });

                var ue = UE.getEditor('noticeEditor', {
                    initialFrameHeight: 300,
                    initialFrameWidth: null
                });

                form.on('select(noticeSendType)', function(data) {
                    if(data.value == '0') {
                        $("#noticeSendTimeEdit").val(new Date().format("yyyy-MM-dd hhh:mm:ss"));
                    }

                    if(data.value == '1') {
                        $("#noticeSendTimeEdit").val('');
                    }
                });
            });
        </script>
    </section>
</div>
