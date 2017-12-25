<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/15
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../../common.jsp" %>--%>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        <li class="fa fa-remove"></li>
    </button>
    <h5 class="modal-title"></h5>
</div>
<div class="modal-body" data-margin="top">
    <div  style="margin-top:-10px;margin-bottom: 10px;">
        <input type="hidden" id="userIds">
        <input type="text" readonly id="userNames" class="form-control" placeholder="已选用户">
    </div>
    <div class="box box-primary">
        <!--隐藏域保存选中的用户-->
        <!--<input type="hidden" name="userIds" id="userIds" style="width: 100%"/>-->
        <div class="dataTables_filter" id="searchDiv_user_select" style="display: none">
            <input placeholder="请输入用户名" name="name" class="form-control" type="search" likeOption="true"/>
            <div class="btn-group">
                <button type="button" class="btn btn-default" data-btn-type="search">查询</button>
                <button type="button" class="btn btn-primary" data-btn-type="select" id="selectUser">选择</button>
            </div>
        </div>
        <div class="box-body">
            <table id="user_select_table" class="table table-bordered table-stripped table-hover" style="font-size:13px; ">
            </table>
        </div>
    </div>
</div>
</body>

<%--<%@ include file="../../common_js.jsp" %>--%>
<script>
    var userSelectTable;
    var ids = "${ids}";//回填ids
    ids = ids == 0 ? '' : ids;
    var multiple = "${multiple}";//默认单选
    //var callback="${callback}";//选择后的回调函数
    //用户选择控制器
    var userSelectCtrl = {
        initTable: function () {
            jQuery("#userIds").val(ids);
            this.updateSelectedUserNames();
            var self = this;
            userSelectTable = new CommonTable("user_select_table", "user_select_list", "searchDiv_user_select");
            //回调选中
            userSelectTable.serverCallback = function () {
                self.setCheckBoxState();
            }
        },
        //查询 换页选择框回填
        setCheckBoxState: function () {
            var selectUserIds = jQuery("#userIds").val();
            if (selectUserIds) {
                var userIdArr = selectUserIds.split(",");
                //选中增加的用户
                jQuery.each(userIdArr, function (index, userId) {
                    if (userSelectTable.table.$("#" + userId).length > 0) {
                        userSelectTable.table.$("#" + userId).find(":checkbox.checkbox_user").prop("checked", true);
                    }
                });
                //删除已经选中的
                userSelectTable.table.$("tr").find(":checkbox.checkbox_user:checked").each(function () {
                    var curUserId = jQuery(this).parents("tr").attr("id");
                    //找不到，已经被删除
                    if (selectUserIds.indexOf(curUserId) == -1) {
                        userSelectTable.table.$("#" + curUserId).find(":checkbox.checkbox_user").prop("checked", false);
                    }
                });
            } else {
                jQuery(":checkbox.checkbox_user").prop("checked", false);
            }
        },
        //绑定用户选择事件
        bindSelectUserEvent: function () {
            jQuery("#selectUser").click(function () {
                var uids = jQuery("#userIds").val();
                var unames = jQuery("#userNames").val();
                ${callback}(uids, unames);
                modals.hideWin("userSelectWin");
            })
        },
        selectThis: function (obj) {
            var isChecked = jQuery(obj).is(":checked");
            //单选
            var userIds = jQuery("#userIds").val();
            if (userIds && userIds.split(',').length == 1 && multiple == 0 && isChecked) {
                alert("只能选择一个用户");
                jQuery(obj).attr("checked", false);
                return;
            }
            var value = jQuery(obj).parents("tr").eq(0).attr("id");
            var userArr = this.getSelectedUserArr(userIds, value, isChecked);
            jQuery("#userIds").val(userArr.join(","));
            this.updateSelectedUserNames();
        },
        updateSelectedUserNames: function () {
            var userIds = jQuery("#userIds").val();
            if (userIds == 0 || !userIds) {
                jQuery("#userNames").val("");
            } else {
                ajaxPost(basePath + "/user/names.do", {ids: userIds}, function (map) {
                    jQuery("#userNames").val(map.name);
                });
            }
        },
        getSelectedUserArr: function (userIdss, curValue, isChecked) {
            var userArr = [];
            if (userIdss)
                userArr = userIdss.split(",");
            if (isChecked) {
                var flag = true;
                for (var i = 0; i < userArr.length; i++) {
                    if (userArr[i] == curValue) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    userArr.push(curValue);
            } else {
                for (var i = 0; i < userArr.length; i++) {
                    var userIdValue = userArr[i];
                    if (userIdValue == curValue) {
                        userArr.splice(i, 1);
                        break;
                    }
                }
            }
            return userArr;
        }
    }

    function fnRenderSelectUser(value) {
        return "<input type='checkbox' value='1' class='checkbox_user' onchange='userSelectCtrl.selectThis(this)'>";
    }
    //方法入口
    jQuery(function () {
        userSelectCtrl.initTable();
        userSelectCtrl.bindSelectUserEvent();
    })
</script>
