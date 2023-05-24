var zb_cookie = {
    setCookie:function(name,value,iTime){
        if(arguments.length==2){
            var iTime = 300;
        }
        var oDate = new Date();
        //设置cookie过期时间
        oDate.setTime(oDate.getTime()+iTime*24*3600*1000);
        document.cookie = name+'='+value+';path=/;expires='+oDate.toGMTString();
    },
    getCookie:function(name){
        //cookie中的数据都是以分号加空格区分开
        var arr = document.cookie.split("; ");
        for(var i=0; i<arr.length; i++){
            if(arr[i].split("=")[0] == name){
                return arr[i].split("=")[1];
            }
        }
        return '';
    },
    removeCookie:function(name){
        //调用setCookie方法，把时间设置为-1
        if(name.indexOf(",")==-1){
            this.setCookie(name,1,-1);
        }else{
            var arr = name.split(",");
            for(var i=0; i<arr.length; i++){
                setCookie(arr[i],1,-1);
            }
        }
    }
};
String.prototype.right = function(i) { //为String对象增加一个Right方法
    return this.slice(this.length - i,this.length);
};
/*登录注册初始化*/
//var login_init=function(){
//    if($(".login-cover").size()==0) {
//        var login_area = '<div class="login-cover"><div class="login-wr"><div class="login-header"><img class="login-logo" src="imgs/logo.png" alt="logo"/>' +
//            '<p class="site-name">武科大步行街</p><div class="share-buttons" style="display:none;"><div id="renren-button">人人网登陆</div><div id="qq-button">微信登陆</div></div></div>' +
//            '<div class="login-form-container"><form action="">'+
//            '<p class="login-p"><label>邮箱&nbsp;</label><input class="login-input" type="text" id="user-name"/></p>'+
//            '<p class="login-p"><label>密码&nbsp;</label><input class="login-input" type="password" id="pw"/></p>'+
//            '<p class="ex clearfix"><label class="rem-wr" for="rem"><input id="rem" name="rem" type="checkbox"/><span>记住我</span></label><a class="forget" href="/login/forget">忘记密码</a>' +
//            '</p><button class="submit" type="button">登录二手街</button></form><a class="reg" href="javascript:void(0);">马上注册</a></div></div></div>' +
//            '<div class="reg-cover"><div class="reg-wr"><div class="reg-header"><img class="login-logo" src="/resource/image/logo.png" alt="logo"/>' +
//            '<p class="site-name">校园二手街</p></div><div class="reg-form-container"><form action=""><input class="input-class" type="text" id="uname" name="uname" placeholder="昵称"/>' +
//            '<input class="input-class" type="email" id="email" name="email" placeholder="邮箱"/>' +
//            '<!--[if lte IE 9]><input class="input-class pw-text" type="text"/><![endif]--><input class="input-class pw-input" type="password" id="passw" name="passw" placeholder="密码"/>' +
//            '<!--[if lte IE 9]><input class="input-class pw-text" type="text"/><![endif]--><input class="input-class pw-input" type="password" id="cpassw" name="cpassw" placeholder="确认密码" />' +
//            '<button type="button" class="submit">注册二手街</button></form><a class="log" href="javascript:void(0);">立即登录</a></div></div></div>';
//        $("body").append($(login_area));
//    }
//};
(function() {
    var windowScroll = function() {//页面收缩方法
        var state = false,
            header = $("header"),
            nav = $("nav");
        function _dealScroll() {
            //alert(this.scrollY);
            var scroll_top;
            if(this.scrollY !== undefined){
                scroll_top = this.scrollY;
            }else{
                scroll_top = document.documentElement.scrollTop;
            }
            if(!state && scroll_top > 0) {
                state = true;
                $(header).addClass("scroll");
                $(nav).addClass("scroll");

                    $(".nav-icons").addClass("hidden");

            }
            else if(state && scroll_top <= 0) {
                state = false;
                $(header).removeClass("scroll");
                $(nav).removeClass("scroll");
                setTimeout(function(){
                    $(".nav-icons").removeClass("hidden");
                },150);
            }
        }
        function _bind() {
            $(window).on("scroll", _dealScroll);
        }
        return {
            bind: _bind
        };
    }();

    var searchBox = function() { //搜索方法
        var form = $(".search-box"),
            input = $("#keyword");

        function _validInput() {
            if($(input).val() == "")
                return false;
            return true;
        }

        function _bind() {
            $(form).on("submit", function(e) {
                return _validInput();
            });
        }

        return {
            bind: _bind
        };
    }();

    function validText(value) {
        if( value == "" || value == null ) return 0;
        return 1;
    }

    function validEmail(value) {
        if(value == "" || value == null) return 0;
        return /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value);
    }

    function validQQ(value) {
        if(value == "" || value == null) return 0;
        return /[0-9]{6,11}/.test(value);
    }

    function formValid(type, value) {
        switch(type) {
            case "text":
            case "password":
                return validText(value);
            case "email":
                return validEmail(value);
            case "tel":
                return validTel(value);
            default: return false;
        }
    }
    //login_init();

    var lr = function() {
        var el = $(".log-reg"),
            state = "n",
            log = $(".login-cover"),
            reg = $(".reg-cover"),
            alog = $(".reg-cover a.log"),
            areg = $(".login-cover a.reg"),
            lc = $(".login-cover a.close"),
            rc = $(".reg-cover a.close"),
            lInputWrs = $(".login-form-container .input-name"),
            rInputWrs = $(".reg-form-container .input-name"),
            lsub = $(".login-form-container .submit"),
            rsub = $(".reg-form-container .submit"),
            inputL = [];

        function placeholder(el) {
            this.el = el;
            this.input = $(this.el).children("input").get(0);
            this.inputFocus = false;
            this.parent = $(this.el).parent().get(0);
            this.state = 0;  //0代表无状态， 1代表输入正确， -1代表输入错误
        }
        placeholder.prototype.binded = [];
        placeholder.prototype.className = "input-name";
        placeholder.prototype.fire = function() {
            $(this.input).on("focus", this.handleFocus);
            var p = this.parent;
            if( $.inArray(p, this.binded) >= 0 ) return ;
            this.binded.push(p);
            $(p).on("click", "." + this.className, this.handleClick);
        };
        placeholder.prototype.handleFocus = function(e) {
            var that = inputL[ $.inArray( e.currentTarget, $(inputL).map(function(index, el) {
                return el.input;
            }) ) ];
            that.inputFocus = true;
            that.dealInputF();
        };
        placeholder.prototype.handleBlur = function(e) {
            var that = e.data.that;
            that.dealInputB.call(this, that);
        }
        placeholder.prototype.handleClick = function(e) {
            var that = inputL[ $.inArray( e.currentTarget, $(inputL).map(function(index, el) {
                return el.el;
            }) ) ];
            that.dealInputF();
        };
        placeholder.prototype.dealInputF = function() {
            if( !this.inputFocus ) {
                $(this.input).focus();
                this.inputFocus = true;
            }
            $(this.el).addClass("focus");
            $(this.input).one("blur", {that: this}, this.handleBlur);
        };
        placeholder.prototype.dealInputB = function(that) {
            var note = formValid(this.type, this.value);
            that.inputFocus = false;
            if( note === 0 ) {
                $(that.el).removeClass("focus");
                that.state = 0;
            }
            else if( !note ) {
                alert( $(that.el).data("value") + "格式不正确");
                that.state = -1;
            } else {
                that.state = 1;
            }
        }

        function firePlaceholder(arr) {
            inputL = $(arr).map(function(index, el) {
                return new placeholder(el);
            });
            $(inputL).each(function(index, el) {
                el.fire();
            });
        }

        function _show(type) {
            if( type === "l" ) {
                state = "l";
                $(log).fadeIn();
                firePlaceholder(lInputWrs);
                $(log).on("click", function(e) {
                    if(e.target === log.get(0))
                    _hide("l");
                });
                $(areg).one("click", function(e) {
                    _hide("l");
                    _show("r");
                });
            }
            else {
                state = "r";
                $(reg).fadeIn();
                firePlaceholder(rInputWrs);
                $(reg).on("click", function(e) {
                    if(e.target === reg.get(0))
                        _hide("r");
                });
                $(alog).one("click", function(e) {
                    _hide("r");
                    _show("l");
                });
            }
        }

        function _hide(type) {
            type === "l" ? $(log).fadeOut() : $(reg).fadeOut();
            state = "n";
        }

        function _togglePopup(type) {
            state === "n" ? _show(type) : _hide(type);
        }

        function _bind() {
            $(el).on("click", ".button", function() {
                zb_cookie.removeCookie("cur_url");
                _togglePopup( $(this).data("type") );
            });
        }

        return {
            bind: _bind
        };
    }();
    lr.bind();
    windowScroll.bind();
    searchBox.bind();
    //获取已出售商品总数
    ajaxRequest('/home/goods/get_sold_total','post',{},function(rst){
    	$("#order-count").text(rst.data);
	});
})();

function is_empty(str){
    return $.trim(str).length == 0;
}

//登录
function login(){
    var username = $("#user-name").val();
    var password = $("#pw").val();

    if (username.length == 0
        || password.length == 0)
    {
        alert("账号和密码不能为空");
        return;
    }
    $.post(
        '/login',
        {username : username, password : password},
        function(res) {
            res = $.parseJSON(res);
            if (res.code == 0) {
                if(zb_cookie.getCookie("cur_url")==''){
                    window.location.reload();
                }else{
                    window.location.href=zb_cookie.getCookie("cur_url");
                }
            } else {
                alert(res.msg);
            }
        }
    );
}

function register(){//注册
    var email = $("#email").val();
    var nickname = $("#uname").val();
    var password = $("#passw").val();
    var password_repeat = $("#cpassw").val();

    if (is_empty(email) || is_empty(nickname)
        || is_empty(password) || is_empty(password_repeat))
    {
        alert('所有选项必须填写');
        return;
    }
    $(".load-tip").text('注册中');
    $("#circular-loading").removeClass('hidden');
    $.post(
        '/login/register',
        {email : email, nickname : nickname, password : password,
            password_repeat : password_repeat},
        function(res) {
            $("#circular-loading").addClass('hidden');
            res = $.parseJSON(res);
            if (res.code == 0) {
                //location.reload();
                $(".reg-cover").css("display","none");
                var school_area='<div id="department-container" class="hidden"><div id="department-wrapper"><div class="sel-head"><h2>选择你的学校</h2></div>'+
                    '<div id="sel-d2" class="sel-d2 hidden"><div class="sel-school"><div class="sel-hd"><h3>学校</h3><input type="text" value="" class="department-item" disabled="true">'+
                    '<a href="javascript:;"><img src="http://www.2shoujie.com/resource/image/arr-right.png"></a></div>'+
                    '<div class="sel-content"><div class="school-search"><input placeholder="请输入学校名称" type="text" value="" class=""><ul class="search-down hidden"></ul></div>'+
                    '<ul class="sel-items"></ul></div></div><div class="sel-academy"><div class="sel-hd"><h3>院系</h3><input type="text" value="" class="department-item" disabled="true">'+
                    '<a href="javascript:;"><img src="http://www.2shoujie.com/resource/image/arr-right.png"></a></div><div class="sel-content">'+
                    '<ul class="sel-items"></ul></div></div><div class="sel-adyear"><div class="sel-hd"><h3>入学时间</h3><input type="text" value="" class="department-item" disabled="true">'+
                    '<a href="javascript:;"><img src="http://www.2shoujie.com/resource/image/arr-right.png"></a></div><div class="sel-content"><ul class="sel-items">';
                var years = "";
                var cur_year = parseInt(new Date().getFullYear(),10);
                for(var i=0; i<10; i++){
                    years+='<li><a value="'+(cur_year-i)+'" href="javascript:;">'+(cur_year-i)+'</a></li>';
                }
                school_area+=years;
                school_area+='</ul></div></div><div class="optpk-container hidden"><div class="optpk-btn"><a href="javascript:;"></a></div></div></div></div></div>';
                $("body").append($(school_area));
                $.ajax({
                    url:"/school",
                    type:"get",
                    success:function(data){
                        var data = $.parseJSON(data);
                        if(data.code==0){
                            var schools = data.data.current;
                            var hot_schools = data.data.hot;
                            $(".sel-school").attr("pk",schools.school_id);
                            $(".sel-school .sel-hd").children("input").val(schools.school_name);
                            var hots = "";
                            for(var i=0; i<hot_schools.length; i++){
                                hots+='<li><a href="javascript:;" value="'+hot_schools[i].school_id+'">'+hot_schools[i].school_name+'</a></li>';
                            }
                            $(".sel-school .sel-items").append($(hots));
                            $("#sel-d2").removeClass("hidden");
                            $("#department-container").removeClass("hidden");
                            selSchool();
                        }else{
                            alert(data.msg);
                        }
                    }
                });

            } else {
                alert(res.msg);
            }
        }
    );
}

function enter_login(e)
{
    var key = window.event ? e.keyCode : e.which;
    if (key == 13) {
        login();
    }   
}

$(".login-form-container").find(".submit").on("click", login);
$(".reg-form-container").find(".submit").on("click", register);

function favorites(){//加喜爱商品
    var goods_id = $("#goods_id").val(),
        favorites_num = parseInt($(".ershou-favorite").text()),
        background_image = '';
    $.post('/goods/favorites', {goods_id : goods_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
            return;
        }
        if (res.data.action == 1) {
            favorites_num++;
            $(".ershou-favorite").css('background-image', 'url(/resource/image/heart_full.png)');
        } else {
            favorites_num--;
            $(".ershou-favorite").css('background-image', 'url(/resource/image/heart.png)');
        }
        $(".ershou-favorite").text(favorites_num);
    });
}

function cancel_favorites(goods_id){//取消喜爱商品
    $.post('/goods/cancel_favorites', {goods_id : goods_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
            return;
        }
        $("#goods"+goods_id).remove();
    });
}

function stu_cert()
{
    var param1 = $("#i1").attr("param"),
        value1 = $("#i1").val(),
        param2 = $("#i2").attr("param"),
        value2 = $("#i2").val();
    $.post(
        '/user/stu_cert', 
        {param1:param1, param2:param2, value1:value1, value2:value2},
        function(res){
            res = $.parseJSON(res);
            if (res.code != 0) {
                $("#not_match").text(res.msg);
                return;
            }
            $(".std_id_box").hide();
            $("#id_succeed").show();
            setTimeout(function(){
               location.reload();
            }, 2000);
        }
    );
}

function off_shelf(pid){
    if (!confirm('下架后商品别人将看不到，是否确认下架')) {
        return;
    }
    $.post('productedit', 
    		{"pid" : pid,"flag":1},
    		function(res){
    			res = $.parseJSON(res);
		        if (res.code != 0) {
		        	//失败
		            alert(res.msg);
		        } else {
		            location.reload();
		        }
    });
}

function on_shelf(goods_id)
{
    $.post('/goods/on_shelf', {goods_id : goods_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            location.reload();
        }
    });
}

function sold(goods_id){
	
	
    if (!confirm('商品卖出后可点此下架商品并获取积分，其他用户将看不到商品，请问是否确认售出？')) {
    	alert("取消");
        return;
    }
    $.post('/goods/sold', {goods_id : goods_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            location.reload();
        }
    });   
}

function read(message_id, url)
{
    var message_num = parseInt($(".person_message").text());
    $.post('/message/read', {message_id:message_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            $("#message"+message_id).remove();
            if (message_num-1 <= 0) {
                $(".person_message").remove();
            } else {
                $(".person_message").text(message_num-1);
            }
            if (url) {
                window.location.href = url;
            }
        }
    });
}
//统一ajax请求
function ajaxRequest(url,requestType,data,callback){
	$.ajax({
		url:url,
		type:requestType,
		data:data,
		dataType:'json',
		success:function(rst){
			if(rst.code == 0){
				callback(rst);
			}else{
				alert(rst.msg);
			}
		},
		error:function(data){
			alert('网络错误!');
		}
	});
}