/**
 * Created by Liuzhilai on 2017/10/19.
 * 组织机构选择器
 */
(function ($, window, document, undefined) {
    'use strict';

    var pluginName = 'role';

    $.fn[pluginName] = function (options) {
        var self = $(this);
        if (this == null)
            return null;
        var data = this.data(pluginName);
        if (!data) {
            data = new BaseRole(this, $.extend(true, {}, options));
            self.data(pluginName, data);
        }
    }

    var BaseRole = function (element, options) {
        this.element = element;
        this.options = $.extend(true, {}, this.default, options);
        this.data = this.getRoleData();
        this.roleId = this.options.roleId || "roleDirWin";

        var self = this;
        $(this.element).unbind("click");
        $(this.element).click(function () {
            modals.openWin({
                winId: 'roleDirWin',
                width: '400px',
                url: false,
                loadContent: $.proxy(self.loadContent, self)
            });
        })
    };

    //加载组织结构内容结构
    BaseRole.prototype.loadContent = function () {
        this.treeId = this.roleId + "_tree";
        //header 和 content
        $("#" + this.roleId).find("div.modal-content").append('<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><li class="fa fa-remove"></li></button>' +
            '<h5 class="modal-title"><i class="fa fa-edit"></i>&nbsp;' + this.options.title + '</h5></div><div class="modal-body"><div id="' + this.treeId + '"></div></div>');
        if (this.options.minHeight)
            $("#" + this.roleId).find("div.modal-body").css("min-height", this.options.minHeight + "px");
        $("#" + this.roleId).find("div.modal-header").css("padding", "10px");
        //footer
        $("#" + this.roleId).find("div.modal-content").append('<div class="modal-footer"></div>');
        $("#" + this.roleId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="selectRole">确定</button>')
        $("#" + this.roleId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="cancelRole">取消</button>')
        $("#" + this.roleId).find("div.modal-footer").append('<button class="btn btn-primary" type="button" data-btn-type="clearRole">清除</button>')
        this.initRoleTree();
        var self = this;
        $("button[data-btn-type='selectRole']").click(function () {
            self.selectRole();
        })

        $("button[data-btn-type='cancelRole']").click(function () {
            self.cancelRole();
        })

        $("button[data-btn-type='clearRole']").click(function () {
            self.clearRole();
        })
    }


    //加载内容
    BaseRole.prototype.initRoleTree = function () {
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
                //console.log("###########nodes[i]: " + nodes[i].code);
                if (nodes[i].id == selectId) {
                    $("#" + this.treeId).data("treeview").selectNode(nodes[i]);
                    break;
                }
            }
        }
    }

    //确定选择组织机构
    BaseRole.prototype.selectRole = function () {
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

        modals.hideWin(this.roleId);

    }

    //清除后关闭
    BaseRole.prototype.clearRole = function () {
        var selectedNode = $("#" + this.treeId).data("treeview").getSelected();
        for (var i = 0; i < selectedNode.length; i++)
            $("#" + this.treeId).data("treeview").unselectNode(selectedNode[i]);
        if (this.options.idField)
            this.options.idField.val("");
        if (this.options.nameField)
            this.options.nameField.val("");
        modals.hideWin(this.roleId);
    }
    //取消，关闭窗口
    BaseRole.prototype.cancelRole = function () {
        modals.hideWin(this.roleId);
    }


    //角色组目录选择器默认配置
    BaseRole.prototype.default = {
        roleId: "roleDirWin",
        title: '角色组目录选择器',
        levels: 2,
        minHeight: 300
    }

    BaseRole.prototype.getRoleData = function (roleType) {
        var treeData;
        ajaxPost(basePath + "/role/treeData.do", null, function (data) {
            treeData = data;
        });
        return treeData;
    }



})(jQuery, window, document)