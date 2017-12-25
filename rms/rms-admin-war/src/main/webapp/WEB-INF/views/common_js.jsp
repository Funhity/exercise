<%--
  Created by IntelliJ IDEA.
  User: liuzhilai
  Date: 2017/10/19
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<!-- ./wrapper -->
<script type="text/javascript">
    var basePath="${ctx}";//给外部js文件传递路径参数
</script>
<!-- jQuery 2.2.0 -->
<%--<script src="${ctx}/resources/adminlte/plugins/jQuery/jQuery-2.2.0.min.js"></script>--%>
<script src="${ctx}/resources/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>

<!--JSON2-->
<script src="${ctx}/resources/common/json/json2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${ctx}/resources/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${ctx}/resources/adminlte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/resources/adminlte/dist/js/app.js"></script>

<!-- dataTable -->
<script src="${ctx}/resources/adminlte/plugins/datatables/jquery.dataTables.js"></script>
<script	src="${ctx}/resources/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctx}/resources/common/js/dataTables.js"></script>
<!-- form -->
<script src="${ctx}/resources/adminlte/plugins/bootstrap-validator/dist/js/bootstrap-validator.js"></script>
<script src="${ctx}/resources/adminlte/plugins/iCheck/icheck.min.js"></script>

<!--daterangepicker-->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${ctx}/resources/adminlte/plugins/daterangepicker/daterangepicker.js"></script>-->

<script src="${ctx}/resources/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/resources/adminlte/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<!-- treeview -->
<script src="${ctx}/resources/adminlte/plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="${ctx}/resources/adminlte/dist/js/demo.js"></script>
<!--select2-->
<script src="${ctx}/resources/adminlte/plugins/select2/select2.full.min.js"></script>


<script src="${ctx}/resources/adminlte/plugins/ie9/respond.min.js"></script>
<script src="${ctx}/resources/adminlte/plugins/ie9/html5shiv.min.js"></script>


<script type="text/javascript" src="${ctx}/resources/common/js/base.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/base-modal.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/base-form.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/base-datasource.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/base-org.js"></script>

<script type="text/javascript" src="${ctx}/resources/common/js/app_iframe.js"></script>
<%--<script type="text/javascript" src="${ctx}/resources/common/js/bootstrap-tab.js"></script>--%>

<!-- 加入页面的的脚本
<script>
    //加载菜单
    ajaxPost(basePath+"/function/all",null,function(data){
        var $li,$menu_f_ul;
        $.each(data,function(index,item){
            if(item.levelCode.length==6){
                $li=$('<li class="treeview"></li>');
                var $menu_f=$('<a href="#">\n'+
                    '<i class="'+item.icon+'"></i> <span>'+item.name+'</span>\n'+
                    ' <span class="pull-right-container">\n'+
                    '<i class="fa fa-angle-left pull-right"></i>\n'+
                    '</span></a>');
                $li.append($menu_f);
                $menu_f_ul=$('<ul class="treeview-menu"></ul>');
                $li.append($menu_f_ul);
                $("ul.sidebar-menu").append($li);
            }
            else if(item.levelCode.length==12){
                $menu_s=$('<li><a href="#" data-url="/AdminEAP-web'+item.url+'"><i class="'+item.icon+'"></i>'+item.name+'</a></li>');
                $menu_f_ul.append($menu_s);
            }
        });
    });

    $(function(){
        //首页默认加载
        loadPage("/AdminEAP-web/homePage");
        //loadPage("/AdminEAP-web/activiti/task/todo/list");
        //loadPage("/AdminEAP-web/activiti/delegate/list");
        //loadPage("/AdminEAP-web/markdown/preview?id=402880e958ecdff60158ed0c62ba0001")
        $("a[data-url]").click(function(evt){
            loadPage($(this).data("url"));
            $("ul.treeview-menu li").removeClass("active");
            $(this).parent().addClass("active");
        });
    });

</script>
-->
