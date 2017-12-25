<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../layouts/main_lead_in.jsp" %>
<%@ include file="../layouts/form_lead_in.jsp" %>

<div class="wrapper">
    <section class="content">
        <div class="Tabmain">
            <div class="layui-row cttBox2" style="margin-top: 20px;">
                <div class="layui-col-xs12">
                    <div class="layui-tab layui-tab-brief" lay-filter="helpCenterTab">
                        <ul class="layui-tab-title">
                            <li class="layui-this">类别</li>
                            <li>封面图</li>
                            <button id="addTypeBtn" class="layui-btn layui-btn-sm"
                                    style="display: block;float:right;margin-bottom:10px;margin-right:40px"
                                    data-type="addType">添加类别
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
                        <!--类别-->
                        <div class="layui-tab-item layui-show">
                            <div class="box box-primary" style="border-top-color: #009688;">
                                <div class="box-header">
                                    <h3 class="box-title"></h3>
                                </div>
                                <form id="typeSearchForm" class="layui-form">
                                    <div class="layui-row">
                                        <div class="layui-col-xs2">
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <input type="text" id="titleSearch" name="title" value="" lay-verify=""
                                                           placeholder="请填写类别名称" autocomplete="off" class="layui-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs2">
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <select id="visibleSearch" name="visible" lay-verify="" class="input-mini">
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
                                        <button class="layui-btn " data-type="searchType">搜索</button>
                                    </div>
                                    <div class="layui-inline">
                                        <button class="layui-btn layui-btn-primary"  data-type="clearType">重置</button>
                                    </div>
                                </div>
                                <hr>
                                <table class="layui-table" id="LAY_table_type" lay-filter="test"></table>
                                <script type="text/html" id="barDemo">
                                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                                    <span>&nbsp; </span>
                                    <span style="color:#999999;">|</span>
                                    <span>&nbsp; </span>
                                    <a class="layui-btn layui-btn-xs" lay-event="manageDetail">明细管理</a>
                                </script>
                                <script type="text/html" id="picTpl">
                                    <div id="layer-photos-demo" class="layer-photos-demo">
                                        <img src="{{d.image}}" layer-src="{{d.image}}" style="width:auto; height:29px"/>
                                    </div>
                                </script>
                            </div>

                        </div>

                        <!--封面图-->
                        <div class="layui-tab-item">
                            <div id="messageMain" class="box box-primary" style="border-top-color: #009688;">
                                <div class="box-header">
                                    <h3 class="box-title"></h3>
                                </div>
                                <form action="" class="layui-form">
                                    <input type="hidden" id="tCoverImage" name="tCoverImage">
                                    <!--上传封面图-->
                                    <div class="layui-form-item">
                                        <div class="layui-inline">
                                            <label style="float:left">封面图(尺寸：500*300)：</label>
                                        </div>
                                        <div class="layui-form-item">
                                            <div class="layui-upload">
                                                <div class="layui-upload-list">
                                                    <div class="layui-form-item">
                                                        <div class="layui-upload-drag" id="testCover">
                                                            <img class="layui-upload-img" id="coverImage" name="coverImage" style="display: none;width: 500px;height: 300px;">
                                                            <i class="layui-icon"></i>
                                                            <p>点击上传，或将文件拖拽到此处</p>
                                                        </div>
                                                        <div class="layui-inline" style="float:bottom;vertical-align:bottom">
                                                            <p id="coverText"></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <button class="layui-btn layui-btn-normal" data-type="addCover">提交</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <form id="typeDetailForm" class="add-type-detail-form edit-type-detail-form layui-form"
                  style="display: none;padding:30px"
                  autocomplete="off">
                <input type="hidden" id="typeId" name="id">
                <input type="hidden" id="typeImage" name="typeImage">
                <div class="layui-row">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="float:left">名称：<font color="red">*</font></label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input type="text" id="title" name="title" value="" lay-verify="" placeholder=""
                                   autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="float:left">图标(尺寸:100*50)：<font color="red">*</font></label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-upload">
                            <div class="layui-upload-list">
                                <div class="layui-form-item">
                                    <div class="layui-upload-drag" id="testImage">
                                        <img class="layui-upload-img" id="image" name="image" style="display: none;width: 300px;height: 150px;">
                                        <i class="layui-icon"></i>
                                        <p>点击上传，或将文件拖拽到此处</p>
                                    </div>
                                    <div class="layui-inline" style="float:bottom;vertical-align:bottom">
                                        <p id="imageText"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                </div>
            </form>

            <script>
                layui.use(['element', 'table', 'layer', 'jquery', 'laydate', 'upload'], function () {
                    var element = layui.element,
                        $ = layui.jquery,
                        table = layui.table,
                        layer = layui.layer,
                        upload = layui.upload;
                    $('.layui-btn').on('click', function () {
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';
                    });
                    var jumpUrl = "${ctx}/appsetting/jumpimgconfs";
                    var $ = layui.$,
                        active = {
                            addType: function () { //获取选中数据
                                clearTypeForm();
                                addTypeForm();
                            },
                            searchType : function() {
                                var wheres = {};
                                wheres.title = $("#titleSearch").val();
                                wheres.visible = $("#visibleSearch").val();
                                table.reload('test', {
                                    where : wheres
                                });
                            },
                            clearType : function() {
                                var formBox=$("#typeSearchForm");
                                formBox[0].reset();
                            },
                            addCover: function() {
                                var jumpJson = {};
                                jumpJson["image"] = $("#tCoverImage").val();
                                jumpJson["type"] = "5";
                                jumpJson["title"] = "帮助中心封面图";
                                $.ajax({
                                    type: 'post',
                                    url: jumpUrl + "/cover",
                                    contentType: 'application/json; charset=utf-8',
                                    data: JSON.stringify(jumpJson),
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == '0000') {
                                            layer.msg('新增成功');
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
                                        return false;
                                    },
                                    error: function (data) {
                                        console.log(data);
                                        layer.alert('新增失败');
                                        return false;
                                    }
                                });
                            }
                        };
                    element.on('tab(helpCenterTab)', function (data) {
                        if (data.index === 0) { //类别
                            $("#addTypeBtn").show();
                            $(".layui-tab-content .layui-tab-item:first-child").addClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:last-child").removeClass("layui-show");
                        }
                        if (data.index === 1) { //封面图
                            $("#addTypeBtn").hide();
                            $(".layui-tab-content .layui-tab-item:first-child").removeClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:last-child").addClass("layui-show");
                            loadCover();
                        }
                    });
                    var baseTypeUrl = "${ctx}/helpcenter/types";
                    table.render({
                        elem: "#LAY_table_type",
                        url: baseTypeUrl,
                        cols: [
                            [{
                                title: '排序',
                                type: 'numbers'
                            }, {
                                field: "image",
                                title: "类别图标",
                                align: 'center',
                                templet: '#picTpl',
                                width: 180
                            }, {
                                field: "title",
                                title: "类别名称",
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
                        page: true,
                        even: true,
                        limits: [10, 15, 20],
                        limit: 10,
                        height: 'full-200'
                    });
                    table.on('tool(test)', function (obj) {
                        var data = obj.data;
                        if (obj.event === 'edit') {
                            clearTypeForm()
                            editTypeForm();
                            $('.edit-type-detail-form').initForm({jsonValue:data});
                            var imgValue = $("#typeImage").val();
                            if(!(imgValue == undefined || imgValue == null)) {
                                $('#image').attr('src', imgValue);
                                $("#image").show();
                                $("#testImage i, #testImage p").hide();
                            }
                        } else if (obj.event === 'manageDetail') {
                            window.location.href = '${ctx}/helpcenter/qa?typeId='+data.id;
                        }
                    });
                    layer.photos({
                        photos: '.layer-photos-demo',
                        anim: 5
                    });
                    //拖拽上传
                    var typeUploadInst = upload.render({
                        elem: '#testImage',
                        url: '${ctx}/upload/imageUp',
                        field: 'upfile',
                        before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#image').attr('src', result); //图片链接（base64）
                            });
                        },
                        done: function (res) {
                            if (res.code === '0000') {
                                //上传成功
                                $('#typeImage').val(res.data.src);
                                $('#image').attr('src', res.data.src);
                                $("#image").show();
                                $("#testImage i, #testImage p").hide();
                                var imageText = $('#imageText');
                                imageText.html('<a class="layui-btn layui-btn-mini type-reload">删除</a>');
                                imageText.find('.type-reload').on('click', function () {
                                    $('#image').attr('src', '');
                                    $("#image").hide();
                                    $("#testImage i, #testImage p").show();
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
                        error: function () {
                            //演示失败状态，并实现重传
                            var imageText = $('#imageText');
                            imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini type-reload">重试</a>');
                            imageText.find('.type-reload').on('click', function () {
                                typeUploadInst.upload();
                            });
                        }
                    });

                    var coverUploadInst = upload.render({
                        elem: '#testCover',
                        url: '${ctx}/upload/imageUp',
                        field: 'upfile',
                        before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#coverImage').attr('src', result); //图片链接（base64）
                            });
                        },
                        done: function (res) {
                            if (res.code === '0000') {
                                //上传成功
                                $("#tCoverImage").val(res.data.src);
                                $('#coverImage').attr('src', res.data.src);
                                $("#coverImage").show();
                                $("#testCover i, #testCover p").hide();
                                var imageText = $('#coverText');
                                imageText.html('<a class="layui-btn layui-btn-mini cover-reload">重新上传</a>');
                                imageText.find('.cover-reload').on('click', function () {
                                    $('#coverImage').attr('src', '');
                                    $("#coverImage").hide();
                                    $("#testCover i, #testCover p").show();
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
                        error: function () {
                            //演示失败状态，并实现重传
                            var imageText = $('#coverText');
                            imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini cover-reload">重新上传</a>');
                            imageText.find('.cover-reload').on('click', function () {
                                coverUploadInst.upload();
                            });
                        }
                    });

                    function editTypeForm() {
                        layer.open({
                            type: 1,
                            title: ['编辑类别', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            maxHeight: 800,
                            content: $('.edit-type-detail-form'),
                            shift: 2,
                            closeBtn: 2,
                            btn: ['修改', '删除'],
                            btnAlign: 'c',
                            offset: '20px',
                            yes: function (index, layero) {
                                //form校验
                                $.ajax({
                                    type: 'put',
                                    url: baseTypeUrl + "/" + $('#typeId').val(),
                                    contentType: 'application/json; charset=utf-8',
                                    data: JSON.stringify($("#typeDetailForm").serializeObject()),
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == '0000') {
                                            layer.msg('修改成功');
                                            clearTypeForm();
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
                            },
                            btn2: function (index, layero) {
                                layer.confirm('是否删除这条数据?', {
                                    icon: 3,
                                    title: '提示'
                                }, function (index) {
                                    $.ajax({
                                        type: 'delete',
                                        url: baseTypeUrl + "/" + $('#typeId').val(),
                                        contentType: 'application/json; charset=utf-8',
                                        dataType: "json",
                                        success: function (data) {
                                            console.log(data);
                                            if (data.code == '0000') {
                                                layer.msg("删除成功！")
                                                table.reload('test')
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
                                clearTypeForm();
                                layer.close(index);
                                return false;
                            }
                        });
                    }

                    function clearTypeForm() {
                        var formBox = $('#typeDetailForm');
                        formBox[0].reset();
                        $("[name='id']").val('');
                        formBox.resetForm();
                        $('#image').attr('src', '');
                        $("#image").hide();
                        $("#testImage i, #testImage p").show();
                        formBox.find('input').removeClass('right');
                        formBox.find('.prompt').removeClass('false');
                        formBox.find('.prompt').removeClass('right');
                    }

                    function addTypeForm() {
                        layer.open({
                            type: 1,
                            title: ['添加类别', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            maxHeight: 800,
                            content: $('.add-type-detail-form'),
                            shift: 2,
                            closeBtn: 2,
                            btn: '添加',
                            btnAlign: 'c',
                            offset: '20px',
                            yes: function (index, layero) {
                                //layui校验
                                $.ajax({
                                    type: 'post',
                                    url: baseTypeUrl,
                                    contentType: 'application/json; charset=utf-8',
                                    data: JSON.stringify($("#typeDetailForm").serializeObject()),
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == '0000') {
                                            //clearTypeForm()
                                            layer.msg('新增成功');
                                            table.reload('test')
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
                            }
                        });
                    }
                    
                    
                    function loadCover() {
                        $.ajax({
                            type: 'get',
                            url: jumpUrl + "?type=5",
                            contentType: 'application/json; charset=utf-8',
                            dataType: "json",
                            success: function(data) {
                                if(data == "" || data == undefined || data == null){
                                }else{
                                    if(data.data[0].image == undefined || data.data[0].image == null) {
                                        $('#testCover #coverImage').attr('src',  '');
                                        $("#testCover #coverImage").hide();
                                        $("#testCover i, #testCover p").show();
                                    } else {
                                        $('#testCover #coverImage').attr('src',  data.data[0].image);
                                        $("#testCover #coverImage").show();
                                        $("#testCover i, #testCover p").hide();
                                        var imageText = $('#coverText');
                                        imageText.html('<a class="layui-btn layui-btn-mini cover-reload">重新上传</a>');
                                        imageText.find('.cover-reload').on('click', function () {
                                            $('#coverImage').attr('src', '');
                                            $("#coverImage").hide();
                                            $("#testCover i, #testCover p").show();
                                            imageText.html('');
                                        });
                                    }
                                }
                            },
                            error: function(data) {
                                console.log(data);
                                layer.alert('获取帮助中心封面图失败');
                            }
                        });
                    }
                });

            </script>
    </section>
</div>