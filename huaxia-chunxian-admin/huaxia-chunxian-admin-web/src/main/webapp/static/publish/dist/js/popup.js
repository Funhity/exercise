layui.use(['laypage', 'layer', 'table', 'jquery'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
    var baseurl = '',//基础的url
        popupKey = '',//查询方法
        title ='',//弹出层的标题
        formId="popupForm",//弹出层表单的id
        datapopup ={};//申明一个对象来接受上面几个参数

    //申明事件的委托
    $('.layui-form').on('click', '.layui-btn', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    //按钮事
    $('.layui-form').delegate(".layui-btn", "click", function () {
        var othis = $(this),
            method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    })

    //按钮事件的监听
    var active = {
        offset: function (othis) {
            var type = othis.data("type"),
                text = othis.text();
            var datapopup = eval('(' + othis.attr("data-popup") + ')');
            baseurl= datapopup.baseurl;
            title= datapopup.title;
            popupKey= datapopup.popupKey;
            // backkillId= datapopup.backkillId;
            $.ajax({
                url: baseurl+"/common/popup/meta?popupKey="+popupKey,
                type: "get",
                dataType: "html",
                success: function (data) {
                    var json = eval('(' + data + ')');
                    //创建填充容器
                    var pophtml = '<div id="'+formId+'" style="display:none;padding:20px"  autocomplete="off"></div>';
                    if (!$("#"+formId).length && $("#"+formId).length <= 0) {
                        //防止无限制的增加元素
                        $(".layui-form").append(pophtml)
                    }
                    //创建查询表单
                    createQueryForm(json)
                    //创建表头
                    createTableHead(json)
                    layer.open({
                        type: 1,
                        title: title,
                        offset: type,
                        id: 'layerDemo' + type,
                        content: $("#"+formId),
                        btn: ['关闭'],
                        area: ['1000px', '600px'],
                        btnAlign: 'r',
                        shade: 0, //不显示遮罩
                        yes: function (index, layero) {
                            layer.close(index)
                        },
                        cancel: function (index, layero) {
                            layer.close(index)
                        }
                    });
                },
                error: function (e) {
                    console.log("ajax报错")
                }
            })
        },
        serachTable: function (othis) {
            //查询页面生成完成&拼接查询条件
            var wheres = {};
            //循环div内的所有文本框，取值 塞入wheres内
            $(".layui-row input").each(function () {
                if ($(this).val() && $.trim($(this).val())) {
                    var ids = $(this).attr("id");
                    wheres[ids] = $(this).val();
                }
            });
            table.reload('testReload', {
                where: wheres
            });
        }
    }


    function createQueryForm(json) {
        if (json.data.metaData.length != 0) {
            var queryBox =
                '<div class="box-header with-border" id="queryBox">' +
                '<h3 class="box-title">查询</h3></div><div class="box-body"><div class="layui-row"><div class="layui-col-xs6 queryFormLeft"></div><div class="layui-col-xs6 queryFormRight"></div></div>';
            var queryFormLeft = '',
                queryFormRight = '',
                queryType = false;
            $.each(json.data.metaData, function (i, v) {
                if (v.isquery == true) {
                    queryType =true;
                    i = i + 1;
                    if (i % 2 == 1) {
                        //奇数的列
                        queryFormLeft += '<div class="layui-form-item"><label for="birthday" class="layui-form-label">' + v.title +
                            '：</label><div class="layui-input-block">' +
                            '<input type="text" id=' + v.field + ' name=' + v.field + ' value="" lay-verify=' + v.field +
                            ' placeholder="请输入搜索内容" autocomplete="off" class=" layui-input">' +
                            '</div></div>'
                    }
                    if (i % 2 == 0) {
                        //偶数的列
                        queryFormRight += '<div class="layui-form-item"><label for="birthday" class="layui-form-label">' + v.title +
                            '：</label><div class="layui-input-block">' +
                            '<input type="text" id=' + v.field + ' name=' + v.field + ' value="" lay-verify=' + v.field +
                            ' placeholder="请输入搜索内容" autocomplete="off" class=" layui-input">' +
                            '</div></div>'
                    }
                }
            })
            if(queryType){
                if (!$("#queryBox").length && $("#queryBox").length <= 0) {
                    //有查询条件，判断是否存在
                    //当有查询条件的时候，右边增加查询按钮
                    $("#"+formId).append($(queryBox));
                    queryFormRight +=
                        '<div class="layui-form-item"><div class="layui-form-label"></div><div class="layui-input-block"><button class="layui-btn" id="searchBtn" type="button" data-method="serachTable">查询</button></div></div>';
                    $(".queryFormLeft").append($(queryFormLeft))
                    $(".queryFormRight").append($(queryFormRight))
                }

            }


        } else {
            var nulldata = '<div class="layui-form-item"> 无查询配置 </div>'
            $("#"+formId).append($(nulldata))
        }
    }
    //创建表头和回填数据
    function createTableHead(json) {
        var cols = "",
            backfillField = new Array();//申明一个数组存储 多个填充的id
        // 初始循环出cols
        // 判断sort
        $.each(json.data.metaData, function (i, v) {
            if (v.isbackfill) {
                //判断是不是回填字段
                //循环列的时候 取出回填字段的field
                backfillField.push(v.field)
                // backfillField = v.field;
            }
            if (v.sort != null && v.checkbox != true) {
                //排序
                cols += "{field:'" + v.field + "',sort:true,title:'" + v.title + "'},"
                return;
            }
            if (v.checkbox == true) {
                //复选框
                cols += "{checkbox:" + v.checkbox + ",LAY_CHECKED:" + v.LAY_CHECKED + "},"
                return;
            }
            //常规的
            cols += "{field:'" + v.field + "',title:'" + v.title + "'},"
        })
        //回填按钮
        if (json.data.fixed == "right") {
            cols += "{fixed:'" + json.data.fixed + "',toolbar:'#" + json.data.toolbar + "',align:'" + json.data.align +
                "'},"
        }
        cols = "[[" + cols.substring(0, cols.length - 1) + "]]"
        var backfill =
            '<script type="text/html" id="backfill"><a class="layui-btn layui-btn-xs" lay-event="'+json.data.toolbar+'">选择</a><\/script>';
        var tablehtml = '<table class="layui-hide" id="test" lay-filter="'+json.data.layFilter+'"></table>'; //生成表格填充位置
        $("#"+formId).append($(tablehtml)) //填充到body内
        $("#"+formId).append($(backfill))
        var wheres = {};
        //构造表格
        table.render({
            elem: '#test',
            // method:"post",
            url: baseurl + json.data.url,
            data: eval(wheres),
            dataType: "json",
            cellMinWidth: json.data.cellMinWidth,
            cols: eval(cols),
            page: json.data.page,
            id: "testReload"
        });
        //回填按钮事件
        table.on('tool('+json.data.layFilter+')', function (obj) {
            var data = obj.data;
            $.each(backfillField,function(i,v){
               $("#" + backfillField[i]).val(data[backfillField[i]]).attr({
                    "data-id": data.id
                })
            })
            layer.closeAll();

        });
    }


});

