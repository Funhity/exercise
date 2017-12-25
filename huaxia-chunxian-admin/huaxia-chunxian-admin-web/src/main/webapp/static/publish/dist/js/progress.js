(function($) {
	var def = {
		"textList": [{
			"title": "提交申请",
			"text": "2017-10-25 15:30:21<br />申请金额：30000元<br />申请期数：24期<br />"
		}, {
			"title": "回补退件",
			"text": "2017-10-25 16:30:21<br />影像不清晰<br /></span>"
		}, {
			"title": "审批通过",
			"text": "2017-10-25 15:30:21<br />申请金额：30000元<br />申请期数：24期<br />"
		}, {
			"title": "待付款",
			"text": ""
		}],
		"state": '3',
	};
	$.fn.progress = function(opt) {
		var opt = $.extend(def, opt);
		var lihtml = '';
		var sicon = '&#xe605;';//success icon
		var eicon = '&#xe65f;';//error icon
		var baseHTML = '<div class="prgressBox"><span class="prgressLine"></span><ul></ul></div>';
		var progress = '<span class="prgressSLine"></span>';
			$.each(opt.textList,function(i,v){
				if(opt.state==0){
					progress = '<span class="prgressSLine" style="width:0% !important"></span>'; 
					if(i<=0){
						lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle"><i class="layui-icon ">'+sicon+'</i></span><span class="preText">'+v.text+'</span></li>'
						return ;
					}
					lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle  preerror"><i class="layui-icon ">'+eicon+'</i></span><span class="preText">'+v.text+'</span></li>'
				}
				if(opt.state==1){
					progress = '<span class="prgressSLine" style="width:24.5% !important"></span>'; 
					if(i<=1){
						lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle"><i class="layui-icon ">'+sicon+'</i></span><span class="preText">'+v.text+'</span></li>'
						return ;
					}
					lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle  preerror"><i class="layui-icon ">'+eicon+'</i></span><span class="preText">'+v.text+'</span></li>'
				}
				if(opt.state==2){
					progress = '<span class="prgressSLine" style="width:52% !important"></span>'; 
					if(i<=2){
						lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle"><i class="layui-icon ">'+sicon+'</i></span><span class="preText">'+v.text+'</span></li>'
						return ;
					}
					lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle  preerror"><i class="layui-icon ">'+eicon+'</i></span><span class="preText">'+v.text+'</span></li>'
				}
				if(opt.state==3){
					progress = '<span class="prgressSLine" style="width:76% !important"></span>'; 
					lihtml += '<li><span class="preOne">'+v.title+'</span><span class="preCircle"><i class="layui-icon ">'+sicon+'</i></span><span class="preText">'+v.text+'</span></li>'
				}
				
			})
		baseHTML = '<div class="prgressBox"><span class="prgressLine"></span>'+ progress +'<ul>'+lihtml+'</ul></div>';	
		return this.append(baseHTML);
	}
})(jQuery);