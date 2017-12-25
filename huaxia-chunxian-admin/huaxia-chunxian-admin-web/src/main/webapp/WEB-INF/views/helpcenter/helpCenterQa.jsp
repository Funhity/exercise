<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>
<%@ include file="../layouts/ue_lead_in.jsp" %>

<div class="wrapper">
    <section class="content">
        <div class="Tabmain">
            <div class="layui-row cttBox2" style="margin-top: 20px;">
                <div class="layui-col-xs12">
                    <div class="layui-tab layui-tab-brief" lay-filter="helpCenterTab">
                        <ul class="layui-tab-title">
                            <li class="layui-this">热门问题</li>
                            <button id="addHot" class="layui-btn layui-btn-sm"
                                    style="display: block;float:right;margin-bottom:10px;margin-right:40px"
                                    data-type="addHot">添加问题
                            </button>
                        </ul>
                    </div>
                </div>
            </div>

            <!--主框架-->
            <div id="mainFrame" class="layui-row cttBox2">
                <div class="layui-col-xs12">
                    <div class="layui-tab-content">
                        <!--类别-->
                        <div class="layui-tab-item layui-show">
                            <div class="box box-primary" style="border-top-color: #009688;">
                                <div class="box-header">
                                    <h3 class="box-title"></h3>
                                </div>
                                <form id="hotSearchForm" class="layui-form">
                                    <div class="layui-row">
                                        <div class="layui-col-xs2">
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <input type="text" id="questionSearch" name="question" value=""
                                                           lay-verify=""
                                                           placeholder="请填写问题标题" autocomplete="off" class="layui-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs2">
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <select id="visibleSearch" name="visible" lay-verify=""
                                                            class="input-mini">
                                                        <option value="" selected>请选择状态</option>
                                                        <option value="1">启用</option>
                                                        <option value="0">禁用</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="layui-row">
                                    <div class="layui-inline">
                                        <button class="layui-btn" data-type="searchHot">搜索</button>
                                    </div>
                                    <div class="layui-inline">
                                        <button class="layui-btn layui-btn-primary" 
                                                data-type="clearHotSearch">重置
                                        </button>
                                    </div>
                                </div>
                                <hr>
                                <table class="layui-table" id="LAY_table_hot" lay-filter="test"></table>
                                <script type="text/html" id="barDemo">
                                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                                </script>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!--类别弹出层-->
            <form id="hotDetailForm" class="hot-detail-form layui-form pad30" style="display: none"
                  autocomplete="off">
                <input type="hidden" id="hotId" name="id" class="hotId">
                <input type="hidden" id="typeId" name="typeId" class="typeId" value="${requestScope.typeId}">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label style="float:left">问题标题：<font color="red">*</font></label>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <input type="text" id="question" name="question" value="" lay-verify="" placeholder=""
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label style="float:left">答案：<font color="red">*</font></label>
                    </div>
                </div>
                <script type="text/plain" id="hotEditor" name="answer">
                </script>
                <div class="layui-form-item">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="float:left">排序：<font color="red">*</font></label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-inline">
                            <input type="text" id="sort" name="sort" class="layui-input"
                                   lay-verify="required">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="float:left">状态：<font color="red">*</font></label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <select id="visible" name="visible" lay-verify="required">
                                <option value="1">启用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-small hotDetailFormSave" lay-submit="" lay-filter="hotDetailFormSave" style="display: none;"></button>
                    <button class="layui-btn layui-btn-small hotDetailFormAdd" lay-submit="" lay-filter="hotDetailFormAdd" style="display: none;"></button>
                </div>
            </form>

            <script>
                layui.use(['element', 'table', 'layer', 'jquery', 'laydate'], function () {
                    var element = layui.element,
                        $ = layui.jquery,
                        table = layui.table,
                        layer = layui.layer;
                    $('.layui-btn').on('click', function () {
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';
                    });
                    var $ = layui.$,
                        active = {
                            addHot: function () { //获取选中数据
                                clearHotForm();
                                addHotForm();
                            },
                            searchHot: function () {
                                var wheres = {};
                                wheres.question = $("#questionSearch").val();
                                wheres.visible = $("#visibleSearch").val();
                                table.reload('test', {
                                    where: wheres
                                });
                            },
                            clearHotSearch: function () {
                                var formBox=$("#hotSearchForm");
                                formBox[0].reset();
                            }
                        };
                    var baseQaUrl = "${ctx}/helpcenter/qas?typeId=${requestScope.typeId}";
                    table.render({
                        elem: "#LAY_table_hot",
                        url: baseQaUrl,
                        cols: [
                            [{
                                title: '排序',
                                type: 'numbers'
                            }, {
                                field: "question",
                                title: "问题标题",
                                sort: true
                            }, {
                                field: "visible",
                                title: "状态",
                                disabled: true,
                                selected: true,
                                jsontext: "[{ 'text': '启用', 'value': '1'},{ 'text': '禁用', 'value': '0'}]",
                                sort: true
                            }, {
                                field: "updateUser",
                                title: "编辑人",
                                sort: true
                            }, {
                                field: "updateTime",
                                title: "编辑时间",
                                datetimeformat: true,
                                sort: true
                            }, {
                                title: "操作",
                                fixed: 'right',
                                align: 'center',
                                toolbar: '#barDemo'
                            }]
                        ],
                        id: "test",
                        cellMinWidth: 120,
                        even: true,
                        page: true,
                        limits: [10, 15, 20],
                        limit: 10,
                        height: 'full-200'
                    });
                    table.on('tool(test)', function (obj) {
                        var data = obj.data;
                        if (obj.event === 'edit') {
                            clearHotForm();
                            editHotForm();
                            $('.hot-detail-form').initForm({jsonValue:data});
                            ue.setContent($("#answer").val());
                        }
                    });

                    function editHotForm() {
                        layer.open({
                            type: 1,
                            title: ['编辑问题', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            maxHeight: 800,
                            content: $('.hot-detail-form'),
                            shift: 2,
                            closeBtn: 2,
                            btn: ['修改', '删除'],
                            btnAlign: 'c',
                            offset: '20px',
                            yes: function (index, layero) {
                                layero.find('.hotDetailFormSave').click();
                            },
                            btn2: function (index, layero) {
                                layer.confirm('是否删除这条数据?', {
                                    icon: 3,
                                    title: '提示'
                                }, function (index) {
                                    $.ajax({
                                        type: 'delete',
                                        url: baseQaUrl + "/" + $('#id').val(),
                                        contentType: 'application/json; charset=utf-8',
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.code == '0000') {
                                                layer.msg("删除成功！");
                                                table.reload('test');
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
                                clearHotForm();
                                layer.close(index);
                                return false;
                            }
                        });
                    }

                    function clearHotForm() {
                        var formBox = $('#hotDetailForm');
                        formBox[0].reset();
                        $("[name='id']").val('');
                        formBox.find('input').removeClass('right');
                        formBox.find('.prompt').removeClass('false');
                        formBox.find('.prompt').removeClass('right');
                        setTimeout(function(){
                            ue.setContent('');
                        }, 666);
                    }

                    function addHotForm() {
                        layer.open({
                            type: 1,
                            title: ['添加问题', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            maxHeight: 800,
                            content: $('.hot-detail-form'),
                            shift: 2,
                            closeBtn: 2,
                            btn: '添加',
                            btnAlign: 'c',
                            offset: '20px',
                            yes: function (index, layero) {
                                layero.find('.hotDetailFormAdd').click();
                            }
                        });
                    }

                    /***编辑方法***/
                    form.on('submit(noticeDetailFormSave)', function(data) {
                        //form校验
                        var hotDetailJson = $("#hotDetailForm").serializeObject();
                        $.ajax({
                            type: 'put',
                            url: baseQaUrl + "/" + $('#id').val(),
                            contentType: 'application/json; charset=utf-8',
                            data: JSON.stringify(hotDetailJson),
                            dataType: "json",
                            success: function (data) {
                                if (data.code == '0000') {
                                    layer.msg('修改成功');
                                    clearHotForm();
                                    layer.close(index);
                                    table.reload('test');
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
                        //layui校验
                        var hotDetailJson = $("#hotDetailForm").serializeObject();
                        if(hotDetailJson.typeId == null || hotDetailJson == '') {
                            hotDetailJson.typeId = '1';
                        }
                        $.ajax({
                            type: 'post',
                            url: baseQaUrl,
                            contentType: 'application/json; charset=utf-8',
                            data: JSON.stringify(hotDetailJson),
                            dataType: "json",
                            success: function (data) {
                                if (data.code == '0000') {
                                    layer.msg('新增成功');
                                    table.reload('test');
                                } else {
                                    //权限403
                                    if(data.code === 0) {
                                        window.location.href="${ctx}/main/login";
                                        return false;
                                    } else {
                                        layer.alert('新增失败！' + data.msg);
                                    }
                                }
                                layer.close(index);
                            },
                            error: function (data) {
                                console.log(data);
                                layer.alert('新增失败');
                            }
                        });
                        return false;
                    });

                    //实例化编辑器
                    var ue = UE.getEditor('hotEditor', {
                        initialFrameHeight: 300,
                        initialFrameWidth: null
                    });
                });
            </script>
        </div>
    </section>
</div>