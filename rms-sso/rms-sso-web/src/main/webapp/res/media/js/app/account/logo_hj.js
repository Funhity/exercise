$(function() {
	init();
});

var countdown = 60;

function init() {

    /**
     * 背景图片切换
     */
	var $imgHolder 	= $('#demo-bg-list');
    var $bgBtn 		= $imgHolder.find('.demo-chg-bg');
    var $target 	= $('#bg-overlay');

    $bgBtn.on('click', function(e){
        e.preventDefault();
        e.stopPropagation();

        var $el = $(this);
        if ($el.hasClass('active') || $imgHolder.hasClass('disabled'))return;
        if ($el.hasClass('bg-trans')) {
            $target.css('background-image','none');
            $imgHolder.removeClass('disabled');
            $bgBtn.removeClass('active');
            $el.addClass('active');

            return;
        }

        $imgHolder.addClass('disabled');

        var url = $el.attr('src').replace('/thumbs','');
        $('<img/>').attr('src' , url).load(function(){
            $target.css('background-image', 'url("' + url + '")');
            $imgHolder.removeClass('disabled');
            $bgBtn.removeClass('active');
            $el.addClass('active');

            $(this).remove();
        })

    });

    $('#btn').click(function() {
		var mobile = $("#cLoginForm input.tel").val();
		if (mobile == ""){
			inform("手机号码不能为空", 2000);
			return;
		}
		if (telCheck(mobile)) {
			$(".grey_block").addClass("showUp");
			$(this).css({
				background : '#ccc',
				color : '#999'
				});
				var tips = $(".tips ul li.current");
				tips.show().siblings('li').hide();
				setTimeout(function() {
					tips.hide();
				}, 2000)
		}
		settime();
	});
	$('.sure_a').click(function() {
		var checkCode = $("input[name='checkCode']").val()
		var mobile = $("#cLoginForm input.tel").val();
		if (checkCode == ""){
			inform("图形验证码不能为空", 2000);
			return;
		}
		
		var url = "getValidateCode.jhtml?";
		var title = $('title').text();
		if(title=='忘记密码') {
			url = "getFpValidateCode.jhtml?";
			mobile = $("#fgLoginForm input.tel").val();
		}
			
		$.post(url + $.param({
			sysid: $("input[name='sysid']").val(),
			mobile : mobile,
			checkCode:checkCode
		}), function(data) {
			inform(data.msg,2000);
			if(data.fail) {
				countdown = 0;
			} else {
				$(".grey_block").removeClass("showUp");
			}
		
		});
		
	});
	$('.cancel_a').click(function() {
		$(".grey_block").removeClass("showUp");
	});
	
	$('#fgvBtn').click(function() {
		var mobile = $("#fgLoginForm input.tel").val();
		if (mobile == ""){
			inform("手机号码不能为空", 2000);
			return;
		}
		if (telCheck(mobile)) {
			$(".grey_block").addClass("showUp");
			$(this).css({
				background : '#ccc',
				color : '#999'
			});
			var tips = $(".tips ul li.current");
			tips.show().siblings('li').hide();
			setTimeout(function() {
				tips.hide();
			}, 2000)
		
			fgvsettime();
		}
	});
	function fgvsettime() {
		if (countdown == 0) {
			$('#fgvBtn').removeAttr("disabled").val("获取验证码");
			$('#fgvBtn').css({
				background : '#FF583F',
				color : '#fff'
			});
			countdown = 60;
			return;
		} else {
			$('#fgvBtn').attr("disabled", true).val(countdown + "重新获取");
			countdown--;
		}
		setTimeout(function() {fgvsettime() } ,1000);
	}
	
	function settime() {
		if (countdown == 0) {
			$('#btn').removeAttr("disabled").val("获取验证码");
			$('.btn1').css({
				background : '#FF583F',
				color : '#fff'
			});
			countdown = 60;
			return;
		} else {
			$('#btn').attr("disabled", true).val(countdown + "重新获取");
			countdown--;
		}
		setTimeout(function() {settime() } ,1000);
	}
	
	$('.eyes').click(function(){
		if($("input[name='sysid']").val()=="1001") {
			if( $('.pwd').attr("type") == "email" ){
				$('.eyes').attr("src",sso_web_static_path+"/user/images/eye1-64x64.png");
				$('.pwd').attr("type","password");			
			}else if ($('.pwd').attr("type") == "password" ){
				$('.eyes').attr("src",sso_web_static_path+"/user/images/eye2-64x64.png");
				$('.pwd').attr("type","email");
			}
		} else {
			if( $('.pwd').attr("type") == "email" ){
				$('.eyes').attr("src",sso_web_static_path+"/user/hj-login/images/eye1-64x64.png");
				$('.pwd').attr("type","password");
			}else if ($('.pwd').attr("type") == "password" ){
				$('.eyes').attr("src",sso_web_static_path+"/user/hj-login/images/eye2-64x64.png");
				$('.pwd').attr("type","email");
			}
		}
	});
	$("input").on('focus',function(){
		var inputType=$(this).attr('type');
		if(inputType!='button'){
			if($(this).hasClass('special_input')){
				$(this).parent().parent('.put').addClass('redborder');
			}
			else{
				$(this).addClass('redborder');
			}
		}
	});
   $('input.tel').on('blur',function(){
	   if($(this).hasClass('special_input')){
		   $(this).parent().parent('.put').removeClass('redborder');
	   }
	   else{
		   $(this).removeClass('redborder');
	   }
   // 	telCheck($(this).val());
    }); 
    $('input.yzm').on('blur',function(){
    	$(this).removeClass('redborder');
  //  	yzmCheck($(this).val());
   });
	$('input.pwd').on('blur',function(event){
   	$(this).removeClass('redborder');
   // 	pwdCheck($(this).val());
	});

	$("#pBtn").click(function(){
		login();
	});
	$("#cBtn").click(function(){logincode();});
	$("#fgBtn").click(function(){loginfgcode();});
}

var loginSubmit = true;
function login() {
	console.log("login");
	var mobile=$("input[name='username']").val();
	var passwd=$("input[name='password']").val()

    if (mobile == ""){
        alert("请输入用户名");
        return;
    }
	if(passwd == "") {
        alert("请输入密码");
        return;
	}

	if(!loginSubmit) return;
	loginSubmit =false;
    //$("#pBtn").attr('disabled','disabled');
    $.post("login_submit_json.jhtml?" + $.param({
        username:mobile,
        password: passwd,
        type: $("input[name='type']").val(),
        loginType: IosorAndroid(),
        sysid: $("input[name='sysid']").val(),
        redirect: $("input[name='redirect']").val()
    }), function (data) {
        if (data.success) {
            window.location.href =  data.redirect;
        }else {
            alert(data.message);
        }
        //$("#pBtn").removeAttr('disabled');
    });

	setTimeout("timeout('loginSubmit')",1000);
	//loginSubmit =true;
}


var logincodeSubmit = true;
function logincode(){
	console.log("logincode");
	var mobile=$("input[name='mobile']").val();
	var yzm=$("input[name='validatecode']").val()
	if (mobile == ""){
		inform("手机号码不能为空", 2000);
		return;
	}
	if(!telCheck(mobile)){
		return;
	}
	if(yzm=="") {
		inform("请输入收到的验证码",2000);
		return;
	};
	if(!logincodeSubmit) return;
	logincodeSubmit =false;
	//if(telCheck(mobile)&&yzmCheck(yzm)){	
		//$("#cBtn").attr('disabled','disabled');
		$.post("login_code_submit_json.jhtml?" + $.param({
			username: mobile,
			validatecode:yzm,
			type: $("input[name='type']").val(),
			loginType: IosorAndroid(),
			sysid: $("input[name='sysid']").val(),
			redirect: $("input[name='redirect']").val()
		}), function (data) {
			$('#tipPhone').display = "none";
			$('#tipCode').display = "none";
			if (data.success) {
				window.location.href =  data.redirect;
			}else {
				/*var  code = data.code;
				if (code && (code == 100000 || code == 100001 || code == 100005 || code == 100006)) {
					$('#tipPhone').html(data.message);
					$('#tipPhone').css('display','block');
				} else if (code && (code == 100002 || code == 100004 || code == 100003 || code == 100007)) {
					$('#tipCode').html(data.message);
					$('#tipCode').css('display','block');
				}*/
				inform(data.message,2000);
			}
			//$("#cBtn").removeAttr('disabled');
		});
	//}
	setTimeout("timeout('logincodeSubmit')",1000); 
	//logincodeSubmit =true;
}
function timeout(type) {
	if(type=="logincodeSubmit") {
		logincodeSubmit =true;
	} else if(type=="loginfgcodeSubmit") {
		loginfgcodeSubmit =true;
	} else if(type=="loginSubmit") {
		loginSubmit =true;
	}
}
var loginfgcodeSubmit = true;
function loginfgcode(){
	console.log("fglogincode");
	var mobile=$("input[name='mobile']").val();
	var yzm=$("input[name='validatecode']").val();
	var newPasswd=$("input[name='newPasswd']").val();
	if (mobile == ""){
		inform("手机号码不能为空", 2000);
		return;
	}
	if(!telCheck(mobile)){
		return;
	}
	if (checkPass(newPasswd)){
		inform("密码需为6-20位字母数字或符号组合", 2000);
		return;
	}
	if(yzm=="") {
		inform("请输入收到的验证码",2000);
		return;
	};
	if(!loginfgcodeSubmit) return;
	loginfgcodeSubmit =false;
	//if(telCheck(mobile)&&yzmCheck(yzm)){	
		//$("#cBtn").attr('disabled','disabled');
		$.post("login_code_fg_submit_json.jhtml?" + $.param({
			username: mobile,
			validatecode:yzm,
			newPasswd:newPasswd,
			type: $("input[name='type']").val(),
			loginType: IosorAndroid(),
			sysid: $("input[name='sysid']").val(),
			redirect: $("input[name='redirect']").val()
		}), function (data) {
			$('#tipPhone').display = "none";
			$('#tipCode').display = "none";
			if (data.success) {
				window.location.href =  data.redirect;
			}else {
				/*var  code = data.code;
				if (code && (code == 100000 || code == 100001 || code == 100005 || code == 100006)) {
					$('#tipPhone').html(data.message);
					$('#tipPhone').css('display','block');
				} else if (code && (code == 100007)) {
					$('#tipCode').html(data.message);
					$('#tipCode').css('display', 'block');
				} else if (code && (code == 100002 || code == 100004 || code == 100003)) {
					$('#tipPwd').html(data.message);
					$('#tipPwd').css('display', 'block');
				}*/
				inform(data.message,2000);
			}
		});
	//}
	setTimeout("timeout('loginfgcodeSubmit')",1000); 
	//loginfgcodeSubmit =true;
}
function telCheck(mobile) {
	mobile = $.trim(mobile);
	if (mobile && /^[a-zA-Z0-9_-]{6,20}$/.test(mobile)) {
		return true;
	} else {
		alert("请输入正确的用户名");
		// inform("请输入正确的用户名",2000);
		return false;
	}
}
function yzmCheck(yzm) {
	yzm = $.trim(yzm);
	if (yzm && /^\d{4}$/.test(yzm)) {
		return true;
	} else {
		inform("验证码不正确",2000);
		return false;
	}
}
function pwdCheck(passwd) {
	passwd = $.trim(passwd);
	if (passwd && passwd>6) {
		return true;
	} else {
		var tips = $('.tips ul li.error_pwd');
		tips.show().siblings('li').hide();
		setTimeout(function() {
			tips.hide();
		}, 2000);
		return false;
	}
}

function checkPass(val) {
	var reg = /[\u4E00-\u9FFF]+/;
	if (reg.test(val)) {
		return true;//正确
	} else {
		return false;
	}
}


$('body').on('focus','.put select',function(){
     //$('.put select').click(function(event) {
		
     	$('.put select').css({
     		'color': '#333'
     		
     	});
     	 $('.quhao').css({
     		'z-index': '99999'
     		
     	});
     });
	 
	 $('body').on('click','.put select',function(){
     //$('.put select').click(function(event) {
		
     	$('.put select').css({
     		'color': '#333'
     		
     	});
     	 $('.quhao').css({
     		'z-index': '99999'
     		
     	});
     });


	$('body').on('change','.put select',function(){	
     //$(".put select").change(function(event) {
		 
     	$('.quhao').html($(this).val());
     	$('.quhao').css({
     		'z-index': '99995'
     		
     	});
     	$('.put select').css({"color": "transparent"});
     });
	
	$('body').on('blur','.put select',function(){	
     //$(".put select").change(function(event) {
		
     	$('.quhao').css({
     		'z-index': '99995'
     		
     	});
     	$('.put select').css({"color": "transparent"});
     });	




	// 按住按钮
	$('.btn2').on('touchstart',function(){
		event?event.stopPropagation():event.cancelBubble = true;
		$(this).css('background', '#FF8D7D');

	})
	$('.btn2').on('touchend',function(){
		event?event.stopPropagation():event.cancelBubble = true;
		$(this).css('background', '#FF583F');
	})
 
 	$('.btn2').on('touchcancel',function(){
		event?event.stopPropagation():event.cancelBubble = true;
		$(this).css('background', '#FF583F');
	})

function IosorAndroid(){
	var browser = {
		versions: function() {
			var u = navigator.userAgent,
				app = navigator.appVersion;
			return { //移动终端浏览器版本信息
				trident: u.indexOf('Trident') > -1, //IE内核
				presto: u.indexOf('Presto') > -1, //opera内核
				webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
				gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
				mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
				ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
				android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
				iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
				iPad: u.indexOf('iPad') > -1, //是否iPad
				webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
			};
		}(),
		language: (navigator.browserLanguage || navigator.language).toLowerCase()
	}
	//判断：
	var and=browser.versions.android;//android
	var ios=browser.versions.ios;//ios
	if(and){
		return 0;
	}
	else if(ios){
		return 1;
	}
}


