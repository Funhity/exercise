<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../layouts/main_lead_in.jsp"%>
<%@ include file="../layouts/form_lead_in.jsp"%>

<div class="wrapper">
	<section class="content">
		<div class="box box-primary">
			<div class="Tabmain">
				<div class="layui-row cttBox2 " style="margin-top: 20px;">
					<div class="layui-col-xs12">
						<div class="layui-tab layui-tab-brief" lay-filter="appSettingTab">
							<ul class="layui-tab-title">
								<li class="layui-this">APP启动广告</li>
								<li>首页Banner</li>
								<li>首页推广icon</li>
								<button id="addBannerBtn" class="layui-btn layui-btn-sm"
									style="display: none; float: right; margin-bottom: 10px; margin-right: 40px"
									data-type="addBanner">添加Banner</button>
								<button id="addIconBtn" class="layui-btn layui-btn-sm"
									style="display: none; float: right; margin-bottom: 10px; margin-right: 40px"
									data-type="addIcon">添加推广icon</button>
							</ul>
						</div>
					</div>
				</div>

				<div class="layui-row cttBox2">
					<div class="layui-col-xs12">
						<div class="layui-tab-content">
							<div class="layui-tab-item layui-show">
								<div class="box box-primary" style="border-top-color: #009688;">
									<div class="box-header with-border">
										<h3 class="box-title"></h3>
									</div>
									<div id="addAppAdMain" class="layui-form">
										<div class="layui-upload-list" id="startAd"
											style="text-align: center; height: 480px; border: none;">
											<div class="layui-col-xs12">
												<img src="${ctx}/static/publish/dist/images/u162.svg" alt=""
													class="layui-upload-img" style="vertical-align: middle;"
													height="280" width="auto">
											</div>
											<div id="appAdDescription" class="layui-col-xs12">
												<span>您暂时还没有设置启动广告页信息...</span>
											</div>
											<div class="layui-col-xs12">
												<div class="layui-inline" style="padding-top: 40px;">
													<button class="layui-btn " data-type="addAppAd">添加
													</button>
												</div>
											</div>
										</div>
									</div>
									<form id="adform"
										class="add-ad-form  edit-ad-form layui-form  pad30"
										style="display: none" autocomplete="off">
										<input type="hidden" id="adId" name="id" class="id"> <input
											type="hidden" id="adType" name="type" value="1"> <input
											type="hidden" id="adImage" name="image" value="" lay-verify="required">
										<div class="layui-row">
											<div class="layui-form-item">
												<div class="layui-inline">
													<label style="float: left">广告停留时间(秒)：</label>
												</div>
											</div>

											<div class="layui-form-item">
												<div class="layui-inline">
													<select name="appStartImageTime" lay-verify="required">
														<option value="3" selected>3</option>
														<option value="4" selected>4</option>
														<option value="5" selected>5</option>
														<option value="6" selected>6</option>
														<option value="7" selected>7</option>
														<option value="8" selected>8</option>
														<option value="9" selected>9</option>
													</select>
												</div>
											</div>
											<div class="layui-form-item" style="border: none;">
												<div class="layui-inline">
													<label style="float: left">广告跳转：</label>
												</div>
											</div>
											<div class="layui-form-item">
												<div class="layui-input-inline">
													<select id="linkJump" class="layui-select"
														lay-filter="linkJump" lay-verify="required">
														<option value="">请问是否链接跳转</option>
														<option value="0">链接跳转</option>
														<option value="1">不跳转</option>
													</select>
												</div>
												<div class="layui-input-inline" style="display: none;">
													<select id="linkJumpType" name="jumpType"
														lay-filter="linkJumpType">
														<option value="">请选择跳转方式</option>
														<option value="1">H5跳转</option>
														<option value="2">原生跳转</option>
													</select>
												</div>
												<div class="layui-input-inline" style="display: none;">
													<input type="text" id="linkJumpAddress" name="jumpPageUrl"
														value="" lay-verify="url" placeholder="请输入链接地址"
														autocomplete="off" class="layui-input">
												</div>
											</div>
											<div class="layui-form-item">
												<div class="layui-inline">
													<label style="float: left">图标(尺寸:100*50)：<font
														color="red">*</font></label>
												</div>
											</div>
											<div class="layui-form-item">
												<div class="layui-upload">
													<div class="layui-upload-list">
														<div class="layui-form-item">
															<div class="layui-upload-drag" id="adUploadImage">
																<img class="layui-upload-img" id="adImagePreview"
																	style="display: none; width: 300px; height: 150px;">
																<i class="layui-icon"></i>
																<p>点击上传，或将文件拖拽到此处</p>
															</div>
															<div class="layui-inline"
																style="float: bottom; vertical-align: bottom">
																<p id="adImageText"></p>
															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
										
											<div class="layui-form-item">
						<button class="layui-btn addBannerSave" lay-submit=""
							lay-filter="adSave" id="adSave"
							style="display: none;"></button>
					</div>
									</form>
								</div>

								<div id="adFormBtn">
									<div class="layui-form-item">
										<div class="layui-inline">
											<button class="layui-btn layui-btn-normal"
												data-type="addApForm">提交</button>
										</div>
										<div class="layui-inline">
											<button class="layui-btn layui-btn-danger"
												data-type="delAppAdDetail">删除</button>
											<span>删除表示不需要启动广告</span>
										</div>
									</div>
								</div>
							</div>
							<div class="layui-tab-item">
								<div class="box box-primary" style="border-top-color: #009688;">
									<div class="box-header with-border">
										<h3 class="box-title"></h3>
									</div>
									<table class="layui-table" id="LAY_table_banner"
										lay-filter="test"></table>
									<script type="text/html" id="picTpl">
                                    <div id="layer-photos-banner" class="layer-photos-banner">
                                        <img src="{{d.image}}" layer-src="{{d.image}}" style="width:auto; height:29px"/>
                                    </div>
                                </script>
									<script type="text/html" id="barDemo">
                                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                                </script>
								</div>
							</div>
							<div class="layui-tab-item">
								<div class="box box-primary" style="border-top-color: #009688;">
									<div class="box-header with-border">
										<h3 class="box-title"></h3>
									</div>
									<table class="layui-table" id="LAY_table_icon"
										lay-filter="testIcon"></table>
									<script type="text/html" id="iconPicTpl">
                                    <div id="layer-photos-icon" class="layer-photos-icon">
                                        <img src="{{d.image}}" layer-src="{{d.image}}" style="width:auto; height:29px"/>
                                    </div>
                                </script>
									<script type="text/html" id="barIcon">
                                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                                </script>
								</div>
							</div>

						</div>
					</div>
				</div>

				<form id="bannerform"
					class="add-banner-form  edit-banner-form layui-form  pad30"
					style="display: none" autocomplete="off">
					<input type="hidden" id="bannerId" name="id" class="id" > <input
						type="hidden" id="type" name="type" value="2"> <input
						type="hidden" id="bannerImage" name="image" value="" >
					<div class="layui-form-item">
						<label class="layui-form-label">排序：<font color="red">*</font></label>
						<div class="layui-input-inline">
							<input type="text" name="sort" class="layui-input"
								lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">跳转方式：<font color="red">*</font></label>
						<div class="layui-input-inline">
							<select id="bannerJumpType" name="jumpType"
									lay-filter="bannerJumpType">
								<option value="">请选择跳转方式</option>
								<option value="1">H5跳转</option>
								<option value="2">原生跳转</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">跳转链接：<font color="red">*</font></label>
						<div class="layui-input-inline">
							<input type="text" name="jumpPageUrl" class="layui-input"
								lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label style="float: left">图标(尺寸:100*50)：<font
								color="red">*</font></label>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-upload">
							<div class="layui-upload-list">
								<div class="layui-form-item">
									<div class="layui-upload-drag" id="testImage">
										<img class="layui-upload-img" id="bannerImagePreview"
											name="bannerImagePreview"
											style="display: none; width: 300px; height: 150px;"> <i
											class="layui-icon"></i>
										<p>点击上传，或将文件拖拽到此处</p>
									</div>
									<div class="layui-inline"
										style="float: bottom; vertical-align: bottom">
										<p id="imageText"></p>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<button class="layui-btn addBannerSave" lay-submit=""
							lay-filter="addBannerSave" id="addBannerSave"
							style="display: none;"></button>
					</div>
				</form>

				<form id="iconForm"
					class="add-icon-form  edit-icon-form layui-form  pad30"
					autocomplete="off" style="display: none">
					<input type="hidden" id="iconId" name="id" class="id" > <input
						type="hidden" id="iconType" name="type" value="3"> <input
						type="hidden" id="iconImage" name="image" value="">
					<div class="layui-form-item">
						<label class="layui-form-label">排序：<font color="red">*</font></label>
						<div class="layui-input-inline">
							<input type="text" name="sort" class="layui-input"
								lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">跳转方式：<font color="red">*</font></label>
						<div class="layui-input-inline">
							<select id="iconJumpType" name="jumpType"
									lay-filter="iconJumpType">
								<option value="">请选择跳转方式</option>
								<option value="1">H5跳转</option>
								<option value="2">原生跳转</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">跳转链接：<font color="red">*</font></label>
						<div class="layui-input-inline">
							<input type="text" name="jumpPageUrl" class="layui-input"
								lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">图标(尺寸:100*50)：<font
							color="red">*</font></label>
						<div class="layui-input-inline">
							<div class="layui-upload">
								<div class="layui-upload-list">
									<div class="layui-form-item">
										<div class="layui-upload-drag" id="testIcon">
											<img class="layui-upload-img" id="iconImagePreview"
												name="iconImagePreview"
												style="display: none; width: 100px; height: 50px;"> <i
												class="layui-icon"></i>
											<p>点击上传，或将文件拖拽到此处</p>
										</div>
										<div class="layui-inline"
											style="float: bottom; vertical-align: bottom">
											<p id="demoText"></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<button class="layui-btn iconFormSave" lay-submit=""
							lay-filter="iconFormSave" id="iconFormSave"
							style="display: none;"></button>
					</div>
				</form>

				<script type="text/html" id="herfTpl">
                {{# if(d.jumpPageUrl !== null){ }}
                <a href="{{d.jumpPageUrl}}" class="layui-table-link" target="_blank">{{d.jumpPageUrl}}
                </a> {{# } }}
            </script>
				<script>
                layui.use(['form', 'element', 'table', 'layer', 'jquery', 'laydate', 'upload'], function () {
                    var baseurl = "${ctx}/appsetting/jumpimgconfs";
                    var form = layui.form,
                        element = layui.element,
                        $ = layui.jquery,
                        table = layui.table,
                        layer = layui.layer,
                        upload = layui.upload;
                    $('.layui-btn,.layui-select').on('click', function () {
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';
                    });
                    var $ = layui.$,
                        active = {
                            addAppAd: function () { //获取选中数据
                                $("#addAppAdMain").hide();
                                $("#adform").show();
                                $('#adFormBtn').show();
                            },
                            addApForm: function () { //提交
                            	
                            	$('#adSave').click();
                              
                            },
                            delAppAdDetail: function () { //删除
                                layer.confirm('是否删除？', function (index) {
                                    layer.close(index);
                                    $("#addAppAdMain").show();
                                    $("#addAppAdDetail").hide();
                                });
                            },
                            addBanner: function () { //提交
                                clearBannerForm();
                                addBannerForm();
                            },
                            addIcon: function () { //提交
                                clearIconForm();
                                addIconForm();
                            }
                        };

                    loadAppAdMain();

                    form.on('select(linkJump)', function (data) {
                        if (data.value === '0') {
                            $("#linkJumpType").parent().show();
                            $("#linkJumpAddress").parent().show();
                        } else {
                            $("#linkJumpType").parent().hide();
                            $("#linkJumpAddress").parent().hide();
                        }
                    });
                    element.on('tab(appSettingTab)', function (data) {
                        if (data.index === 0) { //APP启动广告
                            $("#addBannerBtn").hide();
                            $("#addIconBtn").hide();
                            $(".layui-tab-content .layui-tab-item:first-child").addClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:nth-child(2)").removeClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:last-child").removeClass("layui-show");
                            //loadAppAdMain();
                        }
                        if (data.index === 1) { //首页Banner
                            $("#addBannerBtn").show();
                            $("#addIconBtn").hide();
                            $(".layui-tab-content .layui-tab-item:first-child").removeClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:nth-child(2)").addClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:last-child").removeClass("layui-show");
                            loadBannerTable();
                        }
                        if (data.index === 2) { //首页推广icon
                            $("#addBannerBtn").hide();
                            $("#addIconBtn").show();
                            $(".layui-tab-content .layui-tab-item:first-child").removeClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:nth-child(2)").removeClass("layui-show");
                            $(".layui-tab-content .layui-tab-item:last-child").addClass("layui-show");
                            loadIconTable();
                        }
                    });
                    layer.photos({
                        photos: '.layer-photos-banner',
                        anim: 5
                    });
                    layer.photos({
                        photos: '.layer-photos-icon',
                        anim: 5
                    });

                    //拖拽上传
                    var adUploadInst = upload.render({
                        elem: '#adUploadImage',
                        url: '${ctx}/upload/imageUp',
                        field: 'upfile',
                        before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#adImagePreview').attr('src', result); //图片链接（base64）
                            });
                        },
                        done: function (res) {
                            if (res.code === '0000') {
                                //上传成功
                                $('#adImage').val(res.data.src);
                                $('#adImagePreview').attr('src', res.data.src);
                                $("#adImagePreview").show();
                                $("#adUploadImage i, #adUploadImage p").hide();
                                var imageText = $('#adImageText');
                                imageText.html('<a class="layui-btn layui-btn-mini ad-reload">删除</a>');
                                imageText.find('.ad-reload').on('click', function () {
                                    $('#adImagePreview').attr('src', '');
                                    $("#adImagePreview").hide();
                                    $("#adUploadImage i, #adUploadImage p").show();
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
                            var imageText = $('#adImageText');
                            imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini ad-reload">重试</a>');
                            imageText.find('.ad-reload').on('click', function () {
                                adUploadInst.upload();
                            });
                        }
                    });

                    //拖拽上传
                    var bannerUploadInst = upload.render({
                        elem: '#testImage',
                        url: '${ctx}/upload/imageUp',
                        field: 'upfile',
                        before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#bannerImage').attr('src', result); //图片链接（base64）
                            });
                        },
                        done: function (res) {
                            if (res.code === '0000') {
                                //上传成功
                                $('#bannerImage').val(res.data.src);
                                $('#bannerImagePreview').attr('src', res.data.src);
                                $("#bannerImagePreview").show();
                                $("#testImage i, #testImage p").hide();
                                var imageText = $('#imageText');
                                imageText.html('<a class="layui-btn layui-btn-mini type-reload">删除</a>');
                                imageText.find('.type-reload').on('click', function () {
                                    $('#bannerImagePreview').attr('src', '');
                                    $("#bannerImagePreview").hide();
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
                                bannerUploadInst.upload();
                            });
                        }
                    });

                    var iconUploadInst = upload.render({
                        elem: '#testIcon',
                        url: '${ctx}/upload/imageUp',
                        field: 'upfile',
                        before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#iconImage').attr('src', result); //图片链接（base64）
                            });
                        },
                        done: function (res) {
                            if (res.code === '0000') {
                                //上传成功
                                $('#iconImage').val(res.data.src);
                                $('#iconImagePreview').attr('src', res.data.src);
                                $("#iconImagePreview").show();
                                $("#testIcon i, #testIcon p").hide();
                                var imageText = $('#demoText');
                                imageText.html('<a class="layui-btn layui-btn-mini icon-reload">删除</a>');
                                imageText.find('.icon-reload').on('click', function () {
                                    $('#iconImagePreview').attr('src', '');
                                    $("#iconImagePreview").hide();
                                    $("#testIcon i, #testIcon p").show();
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
                        error: function (data) {
                            console.log(JSON.stringify(data))
                            //演示失败状态，并实现重传
                            var imageText = $('#demoText');
                            imageText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini icon-reload">重试</a>');
                            imageText.find('.icon-reload').on('click', function () {
                                iconUploadInst.upload();
                            });
                        }
                    });

                    function loadAppAdMain() {
                        $('#adFormBtn').hide();
                        $.ajax({
                            type: 'get',
                            url: baseurl + "?type=1",
                            contentType: 'application/json; charset=utf-8',
                            dataType: "json",
                            success: function (obj) {
                                var data = obj.data;
                                if (data == "" || data == undefined || data == null || data.length < 1) {
                                    $('#startAd').find('img').attr('src', '${ctx}/static/publish/dist/images/u162.svg');
                                    $('#appAdDescription').show();
                                } else {
                                    var img = data[0].image;
                                    $('#adId').val(data[0].id);
                                    if (img == "" || img == undefined || img == null) {
                                        $('#startAd').find('img').attr('src', '${ctx}/static/publish/dist/images/u162.svg');
                                        $('#appAdDescription').show();
                                    } else {
                                        $('#adImage').val(img);
                                        $('#startAd').find('img').attr('src', img);
                                        $('#appAdDescription').hide();
                                    }
                                }
                            },
                            error: function (data) {
                                console.log(data);
                                layer.alert('获取APP启动广告失败');
                            }
                        });
                    }

                    function loadBannerTable() {
                        table.render({
                            elem: "#LAY_table_banner",
                            url: baseurl + "?type=2",
                            cols: [
                                [{
                                    field: "sort",
                                    title: '排序'
                                }, {
                                    field: "image",
                                    title: "缩略图",
                                    align: 'center',
                                    templet: '#picTpl',
                                    width: 180
                                }, {
                                    field: "jumpType",
                                    title: "跳转方式",
                                    selected: true,
                                    disabled: true,
                                    jsontext: [{
                                        'text': 'H5',
                                        'value': '1'
                                    }, {
                                        'text': '原生',
                                        'value': '2'
                                    }]
                                }, {
                                    field: "jumpPageUrl",
                                    title: "跳转链接",
                                    templet: '#herfTpl'
                                }, {
                                    field: "updateUser",
                                    title: "编辑人"
                                }, {
                                    field: "updateTime",
                                    title: "编辑时间",
                                    datetimeformat: true
                                }, {
                                    title: '操作',
                                    fixed: 'right',
                                    align: 'center',
                                    toolbar: '#barDemo',
                                    minWidth: 200
                                }]
                            ],
                            id: "banner",
                            cellMinWidth: 120,
                            even: true,
                            page: true,
                            limits: [10, 15, 20],
                            limit: 10,
                            height: "full-188"
                        });
                    }

                    table.on('tool(test)', function (obj) {
                        var data = obj.data;
                        if (obj.event === 'edit') {
                            editBannerForm();
                            $.each(data, function (key, value) {
                                if (key == 'image') {
                                    $('.edit-banner-form').find('img').attr('src', data.image)
                                } else {
                                    $('.edit-banner-form').find('input[name = ' + key + ']').val(value);
                                }
                            })
                        }
                    });

                    function loadIconTable() {
                        table.render({
                            elem: "#LAY_table_icon",
                            url: baseurl + "?type=3",
                            cols: [
                                [{
                                    field: "sort",
                                    title: '排序'
                                }, {
                                    field: "image",
                                    title: "缩略图",
                                    align: 'center',
                                    templet: '#iconPicTpl',
                                    width: 180
                                }, {
                                    field: "jumpType",
                                    title: "跳转方式",
                                    selected: true,
                                    disabled: true,
                                    jsontext: [{
                                        'text': 'H5',
                                        'value': '1'
                                    }, {
                                        'text': '原生',
                                        'value': '2'
                                    }]
                                }, {
                                    field: "jumpPageUrl",
                                    title: "跳转链接",
                                    templet: '#herfTpl'
                                }, {
                                    field: "updateUser",
                                    title: "编辑人"
                                }, {
                                    field: "updateTime",
                                    title: "编辑时间",
                                    datetimeformat: true,
                                }, {
                                    title: '操作',
                                    fixed: 'right',
                                    align: 'center',
                                    toolbar: '#barIcon',
                                    minWidth: 200
                                }]
                            ],
                            id: "icon",
                            cellMinWidth: 120,
                            even: true,
                            page: true,
                            limits: [10, 15, 20],
                            limit: 10,
                            height: "full-188"
                        });
                    }

                    table.on('tool(testIcon)', function (obj) {
                        var data = obj.data;
                        if (obj.event === 'edit') {
                            eidtIconForm();
                            $.each(data, function (key, value) {
                                if (key == 'image') {
                                    $('.edit-icon-form').find('img').attr('src', data.image)
                                } else {
                                    $('.edit-icon-form').find('input[name = ' + key + ']').val(value);
                                }
                            })
                        }
                    });

                    function editAdForm() {
                        var adFormJson = $("#adform").serializeObject();
                        $.ajax({
                            type: 'put',
                            url: baseurl + "/" + $('#adId').val(),
                            contentType: 'application/json; charset=utf-8',
                            data: JSON.stringify(adFormJson),
                            dataType: "json",
                            success: function (data) {
                                if (data.code == '0000') {
                                    layer.msg('添加APP启动广告成功');
                                    $('#adImage').val(adFormJson.image);
                                    $('#startAd').find('img').attr('src', adFormJson.image);
                                    $('#appAdDescription').hide();
                                    $("#addAppAdMain").show();
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
                            error: function (data) {
                                console.log(JSON.stringify(data));
                                layer.alert('添加APP启动广告失败');
                            }
                        });

                    }

                    function addBannerForm() {
                        layer.open({
                            type: 1,
                            title: ['添加Banner', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            content: $('.edit-banner-form'),
                            shift: 2,
                            closeBtn: 1,
                            btn: ['添加'],
                            btnAlign: 'c',
                            offset: '20px',
                            yes: function (index, layero) {
                            	//加校验
	                       	$('#addBannerSave').click();
	            
                            }
                        });
                    }

                    function editBannerForm() {
                        layer.open({
                            type: 1,
                            title: ['编辑类别', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            content: $('.edit-banner-form'),
                            shift: 2,
                            closeBtn: 1,
                            btn: ['保存', '删除'],
                            btnAlign: 'c',
                            yes: function (index, layero) {
                            	//加校验
                             	$('#addBannerSave').click();
                           
                            },
                            btn2: function (index, layero) {
                                layer.confirm('是否删除这条数据?', {
                                    icon: 3,
                                    title: '提示'
                                }, function (index) {
                                    $.ajax({
                                        type: 'delete',
                                        url: baseurl + "/" + $('#id').val(),
                                        contentType: 'application/json; charset=utf-8',
                                        data: JSON.stringify($("#bannerform").serializeObject()),
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.code == '0000') {
                                                layer.msg("删除成功！")
                                                table.reload('test')
                                            } else {
                                                //权限403
                                                if(data.code === 0) {
                                                    window.location.href="${ctx}/main/login";
                                                    return false;
                                                } else {
                                                    layer.msg("删除失败！" + data.msg)
                                                }
                                            }
                                            layer.close(index);
                                        },
                                        error: function (data) {
                                            console.log(JSON.stringify(data));
                                            layer.alert('删除失败');
                                        }
                                    });
                                });
                            },
                            cancel: function (index, layero) {
                                clearBannerForm();
                                layer.close(index);
                                return false;
                            }
                        });
                    }

                    function clearBannerForm() {
                        var formBox = $('#bannerform');
                        formBox[0].reset();
                        formBox.find('input').removeClass('right');
                        formBox.find('.prompt').removeClass('false');
                        formBox.find('.prompt').removeClass('right');
                    }

                    function addIconForm() {
                        layer.open({
                            type: 1,
                            maxHeight: 800,
                            title: ['添加推广icon', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            content: $('.edit-icon-form'),
                            shift: 2,
                            closeBtn: 1,
                            btn: ['添加'],
                            btnAlign: 'c',
                            yes: function (index, layero) {
                            	$('#iconFormSave').click();
                            }
                        });
                    }

                    function eidtIconForm() {
                        layer.open({
                            type: 1,
                            title: ['编辑推广icon', 'font-size:18px;text-align:center'],
                            area: ['700px', 'auto'],
                            content: $('.edit-icon-form'),
                            shift: 2,
                            closeBtn: 1,
                            btn: ['保存', '删除'],
                            btnAlign: 'c',
                            yes: function (index, layero) {
                            	$('#iconFormSave').click();
                            },
                            btn2: function (index, layero) {
                                layer.confirm('是否删除这条数据?', {
                                    icon: 3,
                                    title: '提示'
                                }, function (index) {
                                    $.ajax({
                                        type: 'delete',
                                        url: baseurl + "/" + $('#iconId').val(),
                                        contentType: 'application/json; charset=utf-8',
                                        data: JSON.stringify($("#iconForm").serializeObject()),
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.code == '0000') {
                                                layer.msg("删除成功！")
                                                table.reload('test')
                                            } else {
                                                //权限403
                                                if(data.code === 0) {
                                                    window.location.href="${ctx}/main/login";
                                                    return false;
                                                } else {
                                                    layer.alert("删除失败！" + data.msg);
                                                }
                                            }
                                            layer.close(index);
                                        },
                                        error: function (data) {
                                            console.log(JSON.stringify(data));
                                            layer.alert('删除失败');
                                        }
                                    });
                                });
                            },
                            cancel: function (index, layero) {
                                clearIconForm();
                                layer.close(index);
                                return false;
                            }
                        });
                    }
                    
                    form.on('submit(adSave)', function(data) {
                    	  editAdForm();
                          $("#addAppAdMain").show();
                          $("#adform").hide();
                          $('#adFormBtn').hide();
                    });
                    
                    
               	 form.on('submit(iconFormSave)', function(data) {
                     $.ajax({
                         type: 'post',
                         url: baseurl,
                         contentType: 'application/json; charset=utf-8',
                         data: JSON.stringify($("#iconForm").serializeObject()),
                         dataType: "json",
                         success: function (data) {
                             if (data.code == '0000') {
                                 layer.msg('添加推广icon成功');
                                 table.reload('icon');
                             } else {
                                 //权限403
                                 if(data.code === 0) {
                                     window.location.href="${ctx}/main/login";
                                     return false;
                                 } else {
                                     layer.msg(data.msg);
                                 }
                             }
                             layer.closeAll();
                         },
                         error: function (data) {
                             console.log(JSON.stringify(data));
                             layer.alert('添加失败');
                         }
                     });
                     return false;
                 	  });
               	 
              	 form.on('submit(addBannerSave)', function(data) {
                     $.ajax({
                         type: 'put',
                         url: baseurl + "/" + $('#bannerId').val(),
                         contentType: 'application/json; charset=utf-8',
                         data: JSON.stringify($("#bannerform").serializeObject()),
                         dataType: "json",
                         success: function (data) {
                             if (data.code == '0000') {
                                 layer.msg('修改Banner成功');
                                 clearBannerForm();
								 layer.closeAll();
                                 table.reload('banner');
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
                         error: function (data) {
                             console.log(JSON.stringify(data));
                             layer.alert('保存失败');
                         }
                     });
                     return false;
                  	});
              	 
                    
                    function clearIconForm() {
                        var formBox = $('#iconForm');
                        formBox[0].reset();
                        formBox.find('input').removeClass('right');
                        formBox.find('.prompt').removeClass('false');
                        formBox.find('.prompt').removeClass('right');
                    }

                });
            </script>

			</div>
		</div>
	</section>
</div>
