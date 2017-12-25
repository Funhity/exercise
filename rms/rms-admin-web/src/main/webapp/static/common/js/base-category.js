/**
 * Created by Liuzhilai on 2017/12/06.
 * 机构类别选择器
 */
(function ($, window, document, undefined) {
    'use strict';

    var pluginName = 'orgCategory';

    $.fn[pluginName] = function (options) {
        var self = $(this);
        if (this == null)
            return null;
        var data = this.data(pluginName);
        if (!data) {
            data = new BaseCategory(this, $.extend(true, {}, options));
            self.data(pluginName, data);
        }
    }

    var BaseCategory = function (element, options) {
        this.element = element;
        this.options = $.extend(true, {}, this.default, options);
        this.data = this.getCategoryData();
        this.categoryId = this.options.categoryId || "categoryWin";
        var self = this;
        $(this.element).unbind("click");
        $(this.element).click(function () {
            modals.openWin({
                winId: 'categoryWin',
                width: '400px',
                url: false,
                loadContent: $.proxy(self.loadContent, self)
            });
        })
    };

    //加载组织结构内容结构
    BaseCategory.prototype.loadContent = function () {
        this.treeId = this.categoryId + "_tree";
        //header 和 content
        $("#" + this.categoryId).find("div.modal-content").append('<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>' +
            '<h5 class="modal-title"><i class="fa fa-edit"></i>&nbsp;' + this.options.title + '</h5></div><div class="modal-body"><div id="' + this.treeId + '"></div></div>');
        if (this.options.minHeight)
            $("#" + this.categoryId).find("div.modal-body").css("min-height", this.options.minHeight + "px");
        $("#" + this.categoryId).find("div.modal-header").css("padding", "10px");
        //footer
        $("#" + this.categoryId).find("div.modal-content").append('<div class="modal-footer"></div>');
        $("#" + this.categoryId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="selectCategory">确定</button>')
        $("#" + this.categoryId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="cancelCategory">取消</button>')
        $("#" + this.categoryId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="clearCategory">清除</button>')
        this.initCategoryTree();
        var self = this;
        $("button[data-btn-type='selectCategory']").click(function () {
            self.selectCategory();
        })

        $("button[data-btn-type='cancelCategory']").click(function () {
            self.cancelCategory();
        })

        $("button[data-btn-type='clearCategory']").click(function () {
            self.clearCategory();
        })
    }


    //加载内容
    BaseCategory.prototype.initCategoryTree = function () {
        var self = this;
        $("#" + this.treeId).treeview({
            data: self.data,
            showBorder: true,
            levels: self.options.levels,
            //multiSelect: true,
            showCheckbox: true,
            showIcon: false
        })
        //回填选中
        var selectId = this.options.idField.val();
        var selectedNode = $("#" + this.treeId).data("treeview").getSelected();
        if (!selectId) {
            if (selectedNode.length > 0) {
                for (var i = 0; i < selectedNode.length; i++)
                    $("#" + this.treeId).data("treeview").unselectNode(selectedNode[i]);
            }
        } else {
            if (selectedNode.length > 0) {
                for (var i = 0; i < selectedNode.length; i++)
                    $("#" + this.treeId).data("treeview").unselectNode(selectedNode[i]);
            }
            var nodes = $("#" + this.treeId).data("treeview").getUnselected();
            for (var i = 0; i < nodes.length; i++) {
                console.log("###########nodes[i]: " + nodes[i].code);
                if (nodes[i].id == selectId) {
                    $("#" + this.treeId).data("treeview").selectNode(nodes[i]);
                    break;
                }
            }
        }
    }

    //确定选择组织机构
    BaseCategory.prototype.selectCategory = function () {
        var selectedNode = $("#" + this.treeId).data("treeview").getChecked();
        var codes='';
        var names='';
        for (var i = 0; i < selectedNode.length; i++) {
            var curNode = selectedNode[i];
            if(i > 0) {
                codes += ",";
                names += ",";
            }
            codes += curNode.code;
            names += curNode.text;
        }
        this.options.idField.val(codes);
        this.options.nameField.val(names);

        // var selectedNode = $("#" + this.treeId).data("treeview").getSelected();
        // if (selectedNode.length > 0) {
        //     if (this.options.idField)
        //         this.options.idField.val(selectedNode[0].code);
        //     if (this.options.nameField)
        //         this.options.nameField.val(selectedNode[0].text);
        // } else {
        //     if (this.options.idField)
        //         this.options.idField.val("");
        //     if (this.options.nameField)
        //         this.options.nameField.val("");
        // }
        modals.hideWin(this.categoryId);

    }

    //清除后关闭
    BaseCategory.prototype.clearCategory = function () {
        var selectedNode = $("#" + this.treeId).data("treeview").getSelected();
        for (var i = 0; i < selectedNode.length; i++)
            $("#" + this.treeId).data("treeview").unselectNode(selectedNode[i]);
        if (this.options.idField)
            this.options.idField.val("");
        if (this.options.nameField)
            this.options.nameField.val("");
        modals.hideWin(this.categoryId);
    }
    //取消，关闭窗口
    BaseCategory.prototype.cancelCategory = function () {
        modals.hideWin(this.categoryId);
    }


    //组织机构选择器默认配置
    BaseCategory.prototype.default = {
        categoryId: "categoryWin",
        title: '机构类别选择器',
        levels: 1,
        minHeight: 300
    }

    BaseCategory.prototype.getCategoryData = function (categoryType) {
        var treeData;
        ajaxPost(basePath + "/category/treeData.do", null, function (data) {
            treeData = data;
        });
        return treeData;
    }



})(jQuery, window, document)