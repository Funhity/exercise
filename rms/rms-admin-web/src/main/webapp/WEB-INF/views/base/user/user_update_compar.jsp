<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/12/19
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common.jsp" %>

<body class="skin-blue sidebar-mini fixed sidebar-collapse" style="height: auto; background-color: #ecf0f5;">

<section class="content-header">
    <h1>
        <span>变更记录</span>
        <%--<small>机构迁移</small>--%>
    </h1>
    <%--<ol class="breadcrumb">--%>
    <%--<li><a href="${basePath}"><i class="fa fa-dashboard"></i> 首页</a></li>--%>
    <%--<li><a href="#">系统管理</a></li>--%>
    <%--<li class="active">用户管理</li>--%>
    <%--</ol>--%>
</section>
<section class="content">

    <div class="box">
        <div class="box-header">
            <h3 class="box-title">操作人信息：</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <table class="table table-bordered" style="font-size:13px; ">
                <tbody>
                <tr>
                    <td>操作人员：</td>
                    <td>${updateUser.realname}</td>
                    <td>操作人所属机构：</td>
                    <td>${updateUser.orgName}</td>
                </tr>
                <tr>
                    <td>操作人手机号:</td>
                    <td>${updateUser.mobile}</td>
                    <td>岗位：</td>
                    <td>${updateUser.userDetail.dutyName}</td>
                </tr>
                <tr>
                    <td>操作时间：</td>
                    <td><fmt:formatDate value="${user.userDetail.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>生效时间：</td>
                    <td><fmt:formatDate value="${user.effectiveDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                </tbody></table>
        </div>

    </div>


    <div class="box">
        <div class="box-header">
            <h3 class="box-title">变更详情：</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap"><div class="row"><div class="col-sm-3"></div><div class="col-sm-9"></div></div><div class="row"><div class="col-sm-12"><table id="example2" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
                <thead>
                <tr role="row">
                    <th class="" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" >人员信息</th>
                    <th class="" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" >变更前</th>
                    <th class="" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" >变更后</th>
                </tr>
                </thead>
                <tbody style="font-size:13px; ">
                <tr role="row" class="odd">
                    <td class="sorting_1">员工工号</td>
                    <td>${record.code}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.code ne record.code}"><span style='color:red;'>${user.code}</span></c:when>
                            <c:otherwise>${user.code}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="even">
                    <td class="sorting_1">姓名</td>
                    <td>${record.realname}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.realname ne record.realname}"><span style='color:red;'>${user.realname}</span></c:when>
                            <c:otherwise>${user.realname}</c:otherwise>
                        </c:choose>

                    </td>
                </tr><tr role="row" class="odd">
                    <td class="sorting_1">用户名</td>
                    <td>${record.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.name ne record.name}"><span style='color:red;'>${user.name}</span></c:when>
                            <c:otherwise>${user.name}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="even">
                    <td class="sorting_1">手机号</td>
                    <td>${record.mobile}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.mobile ne record.mobile}"><span style='color:red;'>${user.mobile}</span></c:when>
                            <c:otherwise>${user.mobile}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="odd">
                    <td class="sorting_1">邮箱</td>
                    <td>${record.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.email ne record.email}"><span style='color:red;'>${user.email}</span></c:when>
                            <c:otherwise>${user.email}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="even">
                    <td class="sorting_1">角色</td>
                    <td>${record.roleNames}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.roleNames ne record.roleNames}"><span style='color:red;'>${user.roleNames}</span></c:when>
                            <c:otherwise>${user.roleNames}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="odd">
                    <td class="sorting_1">员工类型</td>
                    <td>${record.userType == 1 ? '正式' : '非正式'}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.userType != record.userType}">
                                <span style='color:red;'>
                                        ${user.userType == 1 ? '正式' : '非正式'}
                                </span>
                            </c:when>
                            <c:otherwise>${user.userType == 1 ? '正式' : '非正式'}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="odd">
                    <td class="sorting_1">岗位</td>
                    <td>${record.dutyName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.userDetail.dutyName ne record.dutyName}"><span style='color:red;'>${user.userDetail.dutyName}</span></c:when>
                            <c:otherwise>${user.userDetail.dutyName}</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr role="row" class="even">
                    <td class="sorting_1">所属机构</td>
                    <td>${record.orgName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.orgName ne record.orgName}"><span style='color:red;'>${user.orgName}</span></c:when>
                            <c:otherwise>${user.orgName}</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr role="row" class="even">
                    <td class="sorting_1">直属上级</td>
                    <td>${record.orgName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.orgName ne record.orgName}"><span style='color:red;'>${user.orgName}</span></c:when>
                            <c:otherwise>${user.orgName}</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr role="row" class="even">
                    <td class="sorting_1">入职日期</td>
                    <td><fmt:formatDate value="${record.joininDate}" pattern="yyyy-MM-dd" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.userDetail.joininDate ne record.joininDate}">
                                <span style='color:red;'><fmt:formatDate value="${user.userDetail.joininDate}" pattern="yyyy-MM-dd" /></span>
                            </c:when>
                            <c:otherwise><fmt:formatDate value="${user.userDetail.joininDate}" pattern="yyyy-MM-dd" /></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr role="row" class="even">
                    <td class="sorting_1">员工状态</td>
                    <td>
                        <c:choose>
                            <c:when test="${record.empStatus == 1 }">在职</c:when>
                            <c:when test="${record.empStatus == 2 }">休假</c:when>
                            <c:otherwise>离职</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.empStatus != record.empStatus}">
                                <span style='color:red;'>
                                    <c:choose>
                                        <c:when test="${user.empStatus == 1 }">在职</c:when>
                                        <c:when test="${user.empStatus == 2 }">休假</c:when>
                                        <c:otherwise>离职</c:otherwise>
                                    </c:choose>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${user.empStatus == 1 }">在职</c:when>
                                    <c:when test="${user.empStatus == 2 }">休假</c:when>
                                    <c:otherwise>离职</c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr role="row" class="even">
                    <td class="sorting_1">账号状态</td>
                    <td>
                        <c:choose>
                            <c:when test="${record.accountStatus == 1 }">正常</c:when>
                            <c:when test="${record.accountStatus == 2 }">锁定</c:when>
                            <c:otherwise>关闭</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${record.accountStatus != record.accountStatus}">
                                <span style='color:red;'>
                                    <c:choose>
                                        <c:when test="${user.accountStatus == 1 }">正常</c:when>
                                        <c:when test="${user.accountStatus == 2 }">锁定</c:when>
                                        <c:otherwise>关闭</c:otherwise>
                                    </c:choose>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${user.accountStatus == 1 }">正常</c:when>
                                    <c:when test="${user.accountStatus == 2 }">锁定</c:when>
                                    <c:otherwise>关闭</c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>


                    </td>
                </tr><tr role="row" class="even">
                    <td class="sorting_1">标签</td>
                    <td>${record.label}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.label ne record.label}"><span style='color:red;'>${user.label}</span></c:when>
                            <c:otherwise>${user.label}</c:otherwise>
                        </c:choose>
                    </td>
                </tr><tr role="row" class="even">
                    <td class="sorting_1">上级理财师</td>
                    <td>${record.fplannerName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.financialPlannerName ne record.fplannerName}"><span style='color:red;'>${user.financialPlannerName}</span></c:when>
                            <c:otherwise>${user.fplannerName}</c:otherwise>
                        </c:choose>
                    </td>

                </tr><tr role="row" class="even">
                    <td class="sorting_1">备注</td>
                    <td>${record.memo}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.userDetail.memo ne record.memo}"><span style='color:red;'>${user.userDetail.memo}</span></c:when>
                            <c:otherwise>${user.userDetail.memo}</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </table></div></div>

            </div>
        </div>
        <!-- /.box-body -->

        <!-- /.box-body -->
        <div class="box-footer text-right">
            <!--以下两种方式提交验证,根据所需选择-->
            <button type="button" class="btn btn-primary" data-btn-type="cancel">关闭</button>
        </div>
    </div>


    <!-- /.box-body -->
</section>
</body>

<%@ include file="../../common_js.jsp" %>

<script>
    var form = null;
    var id = '${user.id}';
    $(function () {
        //cancel
        $("[data-btn-type='cancel']").click(function () {
            gotolist(id);
        })
    })

    function gotolist(id) {
        modals.closeWin("userUpdateWin");
        //刷新当前页面
//        window.parent.refreshTab();
        // 使详情页面可滚动，modals 开启时关闭了body的滚动效果
        $(".skin-green-light").removeClass("modal-open");

        //window.loadPage(basePath + "/user/detail?id=" + id);
    }

</script>


