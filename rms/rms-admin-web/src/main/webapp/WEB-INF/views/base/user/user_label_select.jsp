<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/20
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../../common.jsp" %>--%>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">


<%--<div class="form-group">--%>
    <%--<label for="label_name">新增标签</label>--%>
    <%--<input name="label_name" class="form-control" type="text" placeholder="请输入标签名"/>--%>
    <%--<button type="submit" class="btn btn-primary" id="submitBtn">新增</button>--%>
<%--</div>--%>



<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        <li class="fa fa-remove"></li>
    </button>
    <h5 class="modal-title"></h5>
</div>

<!-- 新增标签 -->
<div class="modal-header input-group input-group-sm">
    <input type="text" class="form-control" name="label_name" id="label_name" placeholder="新增新标签">
    <span class="input-group-btn">
      <button type="button" class="btn btn-info btn-flat" id="insertBtn">新增</button>
    </span>
</div>

<div class="modal-body" data-margin="top">
    <div  style="margin-top:-10px;margin-bottom: 10px;">
        <input type="hidden" id="labelIds">
        <input type="text" readonly id="labelNames" class="form-control" placeholder="已选标签">
    </div>
    <div class="box box-primary">
        <!--隐藏域保存选中的标签-->
        <!--<input type="hidden" name="labelIds" id="labelIds" style="width: 100%"/>-->
        <div class="dataTables_filter" id="searchDiv_label_select" style="display: none">
            <input placeholder="请输入标签名" name="name" class="form-control" type="search" likeOption="true"/>
            <div class="btn-group">
                <button type="button" class="btn btn-default" data-btn-type="search">查询</button>
                <button type="button" class="btn btn-primary" data-btn-type="select" id="selectLabel">选择</button>
            </div>
        </div>
        <div class="box-body">
            <table id="label_select_table" class="table table-bordered table-stripped table-hover" style="font-size:13px; ">
            </table>
        </div>
    </div>
</div>

</body>

<%--<%@ include file="../../common_js.jsp" %>--%>

<script>
    var labelSelectTable;
    var ids = "${ids}";//回填ids
    ids = ids == 0 ? '' : ids;
    var multiple = "${multiple}";//默认单选
    //var callback="${callback}";//选择后的回调函数
    //标签选择控制器
    var labelSelectCtrl = {
        initTable: function () {
            jQuery("#labelIds").val(ids);
            this.updateSelectedLabelNames();
            var self = this;
            labelSelectTable = new CommonTable("label_select_table", "label_select_list", "searchDiv_label_select");
            //回调选中
            labelSelectTable.serverCallback = function () {
                self.setCheckBoxState();
            }
        },
        //查询 换页选择框回填
        setCheckBoxState: function () {
            var selectLabelIds = jQuery("#labelIds").val();
            if (selectLabelIds) {
                var labelIdArr = selectLabelIds.split(",");
                //选中增加的标签
                jQuery.each(labelIdArr, function (index, labelId) {
                    if (labelSelectTable.table.$("#" + labelId).length > 0) {
                        labelSelectTable.table.$("#" + labelId).find(":checkbox.checkbox_label").prop("checked", true);
                    }
                });
                //删除已经选中的
                labelSelectTable.table.$("tr").find(":checkbox.checkbox_label:checked").each(function () {
                    var curLabelId = jQuery(this).parents("tr").attr("id");
                    //找不到，已经被删除
                    if (selectLabelIds.indexOf(curLabelId) == -1) {
                        labelSelectTable.table.$("#" + curLabelId).find(":checkbox.checkbox_label").prop("checked", false);
                    }
                });
            } else {
                jQuery(":checkbox.checkbox_label").prop("checked", false);
            }
        },
        //绑定标签选择事件
        bindSelectLabelEvent: function () {
            jQuery("#selectLabel").click(function () {
                var lids = jQuery("#labelIds").val();
                var lnames = jQuery("#labelNames").val();
                ${callback}(lids, lnames);
                modals.hideWin("labelSelectWin");
            })
        },
        selectThis: function (obj) {
            var isChecked = jQuery(obj).is(":checked");
            //单选
            var labelIds = jQuery("#labelIds").val();
            if (labelIds && labelIds.split(',').length == 1 && multiple == 0 && isChecked) {
                alert("只能选择一个标签");
                jQuery(obj).attr("checked", false);
                return;
            }
            var value = jQuery(obj).parents("tr").eq(0).attr("id");
            var labelArr = this.getSelectedLabelArr(labelIds, value, isChecked);
            jQuery("#labelIds").val(labelArr.join(","));
            this.updateSelectedLabelNames();
        },
        updateSelectedLabelNames: function () {
            var labelIds = jQuery("#labelIds").val();
            if (labelIds == 0 || !labelIds) {
                jQuery("#labelNames").val("");
            } else {
                ajaxPost(basePath + "/user/label/names.do", {ids: labelIds}, function (map) {
                    jQuery("#labelNames").val(map.name);
                });
            }
        },
        getSelectedLabelArr: function (labelIdss, curValue, isChecked) {
            var labelArr = [];
            if (labelIdss)
                labelArr = labelIdss.split(",");
            if (isChecked) {
                var flag = true;
                for (var i = 0; i < labelArr.length; i++) {
                    if (labelArr[i] == curValue) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    labelArr.push(curValue);
            } else {
                for (var i = 0; i < labelArr.length; i++) {
                    var labelIdValue = labelArr[i];
                    if (labelIdValue == curValue) {
                        labelArr.splice(i, 1);
                        break;
                    }
                }
            }
            return labelArr;
        }
    }

    function fnRenderSelectLabel(value) {
        return "<input type='checkbox' value='1' class='checkbox_label' onchange='labelSelectCtrl.selectThis(this)'>";
    }
    //方法入口
    jQuery(function () {
        labelSelectCtrl.initTable();
        labelSelectCtrl.bindSelectLabelEvent();

        jQuery("#insertBtn").click(function () {
            var labelName = jQuery("#label_name").val();

            ajaxPost(basePath + "/user/saveLabel.do", {labelName: labelName}, function (map) {
                if(map.isSucc == 1) {
                    //添加成功，刷新列表
                    labelSelectTable.reloadData();
                    jQuery("#label_name").val("");
                } else {
                    modals.info(map.message);
                }
            });
        })

    })
</script>
