<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!--UE JS AND STYLE-->
<link href="${ctx}/static/publish/dist/ueditor/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${ctx}/static/publish/dist/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/publish/dist/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/publish/dist/ueditor/ueditor.parse.js"></script>
<script type="text/javascript" src="${ctx}/static/publish/dist/ueditor/lang/zh-cn/zh-cn.js"></script>
<!--UE JS AND STYLE-->
<script>
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {
            return '${ctx}/upload/ueImageUp';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>