/**
 * Created by Liu zhilai on 2017/10/25.
 * 组织机构选择器
 */
(function ($, window, document, undefined) {
    'use strict';

    var pluginName = 'res';

    $.fn[pluginName] = function (options) {
        var self = $(this);
        if (this == null)
            return null;
        var data = this.data(pluginName);
        if (!data) {
            data = new BaseRes(this, $.extend(true, {}, options));
            self.data(pluginName, data);
        }
    }

    var BaseRes = function (element, options) {
        this.element = element;
        this.options = $.extend(true, {}, this.default, options);
        this.data = this.getResData();
        this.resId = this.options.resId || "resWin";
        var self = this;
        $(this.element).unbind("click");
        $(this.element).click(function () {
            modals.openWin({
                winId: 'resWin',
                width: '400px',
                url: false,
                loadContent: $.proxy(self.loadContent, self)
            });
        })
    };

    //加载组织结构内容结构
    BaseRes.prototype.loadContent = function () {
        this.treeId = this.resId + "_tree";
        //header 和 content
        $("#" + this.resId).find("div.modal-content").append('<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>' +
            '<h5 class="modal-title"><i class="fa fa-edit"></i>&nbsp;' + this.options.title + '</h5></div><div class="modal-body"><div id="' + this.treeId + '"></div></div>');
        if (this.options.minHeight)
            $("#" + this.resId).find("div.modal-body").css("min-height", this.options.minHeight + "px");
        $("#" + this.resId).find("div.modal-header").css("padding", "10px");
        //footer
        $("#" + this.resId).find("div.modal-content").append('<div class="modal-footer"></div>');
        $("#" + this.resId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="selectRes">确定</button>')
        $("#" + this.resId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="cancelRes">取消</button>')
        $("#" + this.resId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="clearRes">清除</button>')
        this.initResTree();
        var self = this;
        $("button[data-btn-type='selectRes']").click(function () {
            self.selectRes();
        })

        $("button[data-btn-type='cancelRes']").click(function () {
            self.cancelRes();
        })

        $("button[data-btn-type='clearRes']").click(function () {
            self.clearRes();
        })
    }


    //加载内容
    BaseRes.prototype.initResTree = function () {
        var self = this;
        $("#" + this.treeId).treeview({
            data: self.data,
            showBorder: true,
            levels: self.options.levels,
            multiSelect: false,
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
    BaseRes.prototype.selectRes = function () {
        var selectedNode = $("#" + this.treeId).data("treeview").getSelected();
        if (selectedNode.length > 0) {
            if (this.options.idField)
                this.options.idField.val(selectedNode[0].code);
            if (this.options.nameField)
                this.options.nameField.val(selectedNode[0].text);
        } else {
            if (this.options.idField)
                this.options.idField.val("");
            if (this.options.nameField)
                this.options.nameField.val("");
        }
        modals.hideWin(this.resId);

    }

    //清除后关闭
    BaseRes.prototype.clearRes = function () {
        var selectedNode = $("#" + this.treeId).data("treeview").getSelected();
        for (var i = 0; i < selectedNode.length; i++)
            $("#" + this.treeId).data("treeview").unselectNode(selectedNode[i]);
        if (this.options.idField)
            this.options.idField.val("");
        if (this.options.nameField)
            this.options.nameField.val("");
        modals.hideWin(this.resId);
    }
    //取消，关闭窗口
    BaseRes.prototype.cancelRes = function () {
        modals.hideWin(this.resId);
    }


    //组织机构选择器默认配置
    BaseRes.prototype.default = {
        resId: "resWin",
        title: '组织机构选择器',
        levels: 2,
        minHeight: 300
    }

    BaseRes.prototype.getResData = function (resType) {
        var treeData;
        ajaxPost(basePath + "/res/treeData.do", null, function (data) {
            treeData = data;
        });
        return treeData;
    }



})(jQuery, window, document)