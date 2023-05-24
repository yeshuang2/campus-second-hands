$(document).ready(function(){
    //login_slider();
    // discriber();
    //login_close();
    //ershou_photo_slide();
    //wave_move();
    edit_info();
    change_photo();
    /*choose_sch();*/
    //student_id();
    if($("#user_msg").length>0){
        grade_value_slide();
    }
    if($("#intergral-wrapper").length>0){
        scoreRotate();
    }
    if($(".return-to-top").length>0){
        returnToTop();
    }
    $(".close-head-img,.btn-ok,.btn-cancel").click(function(){
    	$("#head-img-box").addClass('hide');
    })
    $("#upload-person-img").click(function(){
    	$("#selected-file").click();
    });
})
function uploadImg(){
	$('.loading').removeClass("hide").css({'margin-top':'0px'});
	uploadPhoto('origin_ph','');
}
//统一图片上传方法
function uploadPhoto(showPictureImg,input){
	//formdata
	var formData = new FormData();
	formData.append('photo',document.getElementById('selected-file').files[0]);
	$.ajax({
		url:'/home/upload/upload_photo',
		contentType:false,
		processData:false,
		data:formData,
		type:'POST',
		success:function(data){
				$('.loading').addClass("hide")
				if(data.code == 0){
					$("#head-img-box").addClass('hide');
					$(".pop-tip-txt").text('图片上传成功!');
					$(".pop-tip").removeClass('hide');
					$("#"+showPictureImg).attr('src','/photo/view?filename=' + data.data);
					$("#"+showPictureImg).attr('old-src','/photo/view?filename=' + data.data);
					//向后台发请求，修改数据库中的头像
					$.ajax({
						url:'update_head_pic',
						type:'post',
						dataType:'json',
						data:{headPic:data.data},
						success:function(rst){
							if(rst.code != 0){
								alert(rst.msg);
							}
						},
						error:function(rst){
							alert('网络错误！');
						}
					});
				}else{
					data = $.parseJSON(data);
					$(".pop-tip-txt").text(data.msg);
					$(".pop-tip").removeClass('hide');
				}
				setTimeout(function(){
					$(".pop-tip").addClass('hide');
				},2000);
			},
			error:function(data){
				alert('网络错误!');
			}
	});
}
function grade_value_slide(){
    if($("#need_value").length==0) return;
    var value_got = parseInt($("#value_box li:eq(1) span").html());
    var value_need = parseInt($("#value_box li:eq(2) span").html());
    var total_value = $("#need_value")[0];
    var total_width = parseInt(document.defaultView.getComputedStyle(total_value, null).width);
    var current_width = value_got/(value_got+value_need)*total_width;
    $("#got_value")[0].style.width = current_width + "px";
    $("#grade_value_btn")[0].style.left = current_width -15+ "px";
    $("#value_box")[0].style.left = current_width-70+ "px";
}
function student_id(){
    $("#student_id .id_it").bind('click',function(){
        $(".back_layer,.std_id_box").fadeIn();
    });
    $("#id_close").bind('click',function(){
        $(".back_layer,.std_id_box").fadeOut();
    });
}
function edit_info(){
    $("#edit_info").bind('click',function(){
        $(this).css({
            display: "none"
        })
        $("#save_info").css({
            display: "block"
        })
        $(".right_info span.baseinfo").css({
            display: "none"
        })
        $(".right_info input.baseinfo").css({
            display: "inline"
        })
    });
    $("#save_info").bind('click',function(){
        var nickname = $("#nickname").val(),
            mobile = $("#mobile").val(),
            qq = $("#qq").val(),
            academy=$("#academy").val(),
            grade=$("#grade").val(),
            school=$("#school").val()
        
            $.ajax({
				type: "POST" ,
				url: "edit_info",
				data: {
	            	nickname : nickname,
	                qq : qq,
	                mobile : mobile,
	                academy:academy,
	                grade:grade,
	                school:school
	            },
				dataType:'json',
				success: function(res) {
						//alert("res:"+res+"\n"+"resLength:"+res.length)
						if(res.code == 0){
							$("#qq_span").text(qq);
			                $("#phone_span").text(mobile);
			                $("#nickname_span").text(nickname);
			                $("#college_span").text(academy);
			                $("#grade_span").text(grade);
			                $("#area_span").text(school);
			                $("#save_info").css({
			                    display: "none"
			                });
			                $("#edit_info").css({
			                    display: "block"
			                });
			                $(".right_info input.baseinfo").css({
			                    display: "none"
			                });
			                $(".right_info span.baseinfo").css({
			                    display: "inline"
			                });
							return;
						}
						alert(res.msg);
						return false;
					}
			});
           
    });
}
function change_photo(){
    $("#user_photo").bind('mouseenter',function(){
    	$("#origin_ph").attr('src',$("#change_photo").attr('src'));
    });
    $("#user_photo").bind('mouseleave',function(){
    	$("#origin_ph").attr('src',$("#origin_ph").attr('old-src'));
    	$("#change_photo").css({
            display: "none"
        })
    });
}
function login_slider(){
    var in_person = 0;
    var in_slider = 0;
    $("#person_info").bind('mouseenter',function(){
        $(".login_border").css({
            display: "block"
        });
        if ($(".login_slider").css('display') != 'block') {
            $(".login_slider").slideToggle(200);
        }
        in_person = 1;
    });
    $("#person_info").bind('mouseleave', function(){
        setTimeout(function(){
            if (in_slider == 1) {
                return;
            }
            $(".login_border").css({
                display: "none"
            });
            $(".login_slider").hide();
        }, 200);
    });
    $(".login_slider").bind('mouseenter', function(){
        in_person = 0;
        in_slider = 1;
    });
    $(".login_slider").bind('mouseleave', function(){
        setTimeout(function(){
            if (in_person == 1) {
                return;
            }
            $(".login_border").css({
                display: "none"
            });
            $(".login_slider").hide();
        }, 200);
        in_slider = 0;
    });
}
function discriber(){
    var text_length=$("#user_cmt").text().length;
    var text_height=$("#user_cmt").outerHeight();
    if(text_length>45){
        $("#user_ph").css({
            float: "left"
        })
        $("#user_ph").css({
            marginBottom: text_height*0.7+"px"
        })
        $(".ershou-desc").css({
            textAlign: "left"
        })
    }
    else{
        $("#user_ph").css({
            float: "none"
        })
    }
}
function login_close(){
    $(".close").bind('click',function(){
        $(".login-cover").fadeOut();
    });
}
function wave_move(){
    if($('#commend').hasClass("cur")){
        $('#commend').mouseover(function(){
            $('#wave_w').animate({
                left: "32px"
            }, 300);
        });

        $('#new_pro').mouseover(function(){
            $('#wave_w').animate({
                left: "160px"
            }, 300);  
        });

        $('#new_pro').mouseleave(function(){
            $('#wave_w').animate({
                left: "32px"
            }, 300);  
        });
    }
    if($('#new_pro').hasClass("cur")){
        $("#wave_w").css({
            left: "160px"
        })
        $('#commend').mouseover(function(){
            $('#wave_w').animate({
                left: "32px"
            }, 300);
        });
        $('#new_pro').mouseover(function(){
            $('#wave_w').animate({
                left: "160px"
            }, 300);  
        });
        $('#commend').mouseleave(function(){
            $('#wave_w').animate({
                left: "160px"
            }, 300);  
        });
    }
}
/**************** 回到顶部 *********************/
function returnToTop(){
    $(window).scroll(function() {
        if ($(this).scrollTop() > 500) {
            $('.return-to-top').fadeIn(100);
        } else {
            $('.return-to-top').fadeOut(100);
        }
    });

    $('.return-to-top').click(function(event) {
        event.preventDefault();
        $(".return-to-top").addClass("move");
        $('html, body').animate({
            scrollTop: 0
        }, 200, function() {
            $(".return-to-top").hide();
            $(".return-to-top").removeClass("move");
        });
    });
}
/**********  Intergral Circle Function ****************/
function scoreRotate() {
    var score_total = parseInt($("[name=score-need]:input")[0].value);
    var score_got = parseInt($("[name=score-got]:input")[0].value);
    var score_get = parseInt($("[name=score-add]:input")[0].value);
    var angle = (score_got / score_total) * 360;
    var new_angle = ((score_got + score_get) / score_total) * 360;
    var ani_time=1000;

    initCircle();
    setTimeout(function(){
        $("#intergral-wrapper").addClass("opacity");
        scoreAni();
    },500);
    ani_time+=1000;
    function scoreAni(){
        setTransform($(".round-circle")[0], "rotate(" + angle + "deg)");
        if (angle < 180 && new_angle > 180) {
            var half_time = 1000 * (180 - angle) / (new_angle - angle);
            setTimeout(function () {
                $(".color-circle").addClass("color-change");
                $(".color-change").removeClass("color-circle");
                $(".round-circle")[0].style.zIndex = 4;
            }, half_time + 500);
        }
        if (new_angle >= 360) {
            setTimeout(function () {
                $(".round-circle").addClass("rotate");
                setTransform($(".round-circle")[0], "rotate(" + 360 + "deg)");
                setTimeout(function () {
                    setTransform($(".score-add")[0], "rotateY(" + 180 + "deg)");
                    setTransform($(".lv-role")[0], "rotateY(" + 0 + "deg)");
                    setTimeout(function () {
                        $(".score-desc").addClass("hidden");
                        $(".role-desc").removeClass("hidden");
                    }, 300);
                }, 1000);
            }, 500);
            ani_time += 1500;
        } else {
            setTimeout(function () {
                $(".round-circle").addClass("rotate");
                setTransform($(".round-circle")[0], "rotate(" + new_angle + "deg)");
            }, 500);
            ani_time += 500;
        }
        setTimeout(function(){
            $("#intergral-wrapper").removeClass("opacity");
            setTimeout(function(){
                /*$("#intergral-wrapper").removeClass("opacity");*/
                $("#intergral-wrapper").addClass("hidden");
            },600);
        },ani_time+800);
    }

    function initCircle() {
        var pattern = /pic\/\w+\./;
        var matches = $(".score-pic")[0].src.match(pattern);
        var wrapper = $("#intergral-wrapper")[0];
        if (matches) {
            var score_item = matches[0].slice(4, matches[0].length - 1);
            $(wrapper).addClass(score_item);
        }
        var level = $(".lv-num span").text();
        if (level) {
            switch (level) {
                case "LV2":
                case "LV3":
                case "LV4":
                case "LV5":
                    $(wrapper).addClass("role_lv2");
                    break;
                case "LV6":
                case "LV7":
                    $(wrapper).addClass("role_lv6");
                    break;
                case "LV8":
                case "LV9":
                case "LV10":
                    $(wrapper).addClass("role_lv8");
                    break;
                case "LV11":
                case "LV12":
                case "LV13":
                    $(wrapper).addClass("role_lv11");
                    break;
                case "LV14":
                    $(wrapper).addClass("role_boss");
                    break;
            }
        }
        if (angle >= 180) {
            $(".round-circle")[0].style.zIndex = 4;
            $(".color-circle").addClass("color-change");
            $(".color-change").removeClass("color-circle");
        }
    }
    function setTransform(element, func_str) {
        element.style.webkitTransform = func_str;
        element.style.msTransform = func_str;
        element.style.transform = func_str;
    }
}

/************ 学号认证  *********************/
function uploading(){
    $('.uploading').removeClass("hide");
}
function uploaded(){
    $('.uploading').addClass("hide");
}
function idtfSucceed(){
    if($.browser && $.browser.msie && ($.browser.version <= "8.0")) {
        $('.idtf-succeed')[0].style.border = "1px solid #c1c1c1";
    }
    $(".succeed-container").removeClass("hidden");
    setTimeout(function(){
        $(".succeed-container").addClass("hidden");
    },2500);
}

/***************** 选择学校  ***************/
function searchSchool(keyword)
{
    $.post('/school/search', {keyword:keyword}, function(res){
        res = $.parseJSON(res);
        schoolList = res.data.school_list;
        $(".search-down").empty();
        var len = schoolList.length;
        for (i = 0; i < len; i++) {
            li = '<li><a value="'+schoolList[i].school_id+'" href="javascript:;">'+schoolList[i].school_name+'</a></li>';
            $(".search-down").append(li);
        }
    });
}

/*选择学校信息*/
//选择学校
$(document).ready(function(){

}).on("click",".sel-content a",function(){
    var department = $(this).text();
    var sel_hd = $(this).closest(".sel-content").prev(".sel-hd");
    sel_hd.find(".department-item").val(department);
    if($(this).closest("ul").hasClass("search-down")){
        $(this).closest("ul").addClass("hidden");
        $(this).closest("ul").prev("input").val("");
    }
    _slideUp(sel_hd);
    $(this).closest(".sel-content").parent("div").attr("pk",$(this).attr("value"));
    if($(this).closest(".sel-content").parent("div").hasClass("sel-academy")){
        $(".sel-adyear .sel-content").addClass("slide-down");
    }else if($(this).closest(".sel-content").parent("div").hasClass("sel-school")){
        if($(this).closest(".sel-content").parent("div").parent("div").hasClass("sel-d2")){
            var id = $(".sel-d2 .sel-school").attr("pk");
            getAcademy(id,_slideDown($(".sel-academy .sel-hd")));
        }
    }
}).on("click","#hot-topic",function(){
    var user_id = zb_cookie.getCookie("user_id");
    if(user_id!=''){
        window.location.href="/want";
    }else{
        $(".log-reg").children().eq(0).click();
        zb_cookie.setCookie("cur_url","/want");
    }
}).on("click","#release-button",function(){
    var user_id = zb_cookie.getCookie("user_id");
    if(user_id!=''){
        window.location.href="/release";
    }else{
        $(".log-reg").children().eq(0).click();
        zb_cookie.setCookie("cur_url","/release");
    }
}).on("click","#fd_footer",function(){
    if($("body").children(".fade_back").size()==0){
        var feed_back = '<div class="fade_back"><h1><span class="fd_close">关闭</span>反馈给二手街</h1><div class="fade_area">'+
            '<div class="not_good"><h2>逛街时有什么不爽的，请告诉我们</h2><textarea id="feedback" placeholder="我给你们讲哦..."></textarea></div>'+
            '<div class="leave_name"><h2>英雄留个联系方式吧</h2><textarea id="contact" placeholder="邮箱、QQ、手机都可以..."></textarea></div>' +
            '<a class="fd_btn" id="commit_fd" href="javascript:void(0);"></a></div></div><div class="thanks_fd">感谢反馈，二手街团队已经收到，相信我们可以做的更好。</div>';
        $("body").append($(feed_back));
    }
    $(".fade_back").fadeIn();
}).on("click",".fd_close",function(){
    $(".fade_back").fadeOut();
}).on("focus",".fade_back textarea",function(){
    $(this).css({color: "#333"});
}).on("blur",".fade_back textarea",function(){
    $(this).css({color: "#858D8E"});
}).on("click","#commit_fd",function(){
    feedback();
}).on("click",".commenting-unlogin .comment-login",function(){
    $(".log-reg").children().eq(0).click();
    zb_cookie.setCookie("cur_url",window.location.href);
}).on("click","#renren-button",function(){
    window.location.href="/renn";
}).on("click","#qq-button",function(){
    window.location.href="/weixin/login";
}).on("click","#store-site",function(){
    var sURL = window.location.href;
    var sTitle = document.title;
    try{
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e){
        try{
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e){
            alert("该浏览不支持自动添加，请使用Ctrl+D进行添加");
        }
    }
}).on("click","#submit-link",function(){
    var name = $("#link-name").val();
    var address = $("#link-address").val();
    $.ajax({
        url:"/index/add_link",
        data:{link_url:address,link_name:name},
        type:"post",
        success:function(data){
            var data = $.parseJSON(data);
            if(data.code==0){
                $(".pop-tip").addClass("hide");
                window.location.reload(true);
            }else{
                alert(data.msg);
            }
        }
    });
}).on("click",".add-link",function(){
    $(".pop-tip").removeClass("hide");
});

function feedback()
{
    var contact  = $("#contact").val(),
        feedback = $("#feedback").val();
    if ($.trim(feedback).length == 0) {
        alert('反馈不能为空哦，亲');
        return;
    }
    $.post('/feedback', {contact:contact,feedback:feedback}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            $(".fade_back").hide();
            $(".thanks_fd").show();
            setTimeout(function(){
                $(".thanks_fd").hide();
            }, 2000);
        }
    });
}
function openUploadPanel(){
	$("#head-img-box").removeClass('hide');
}
function selSchool(){
    $(".school-search input").bind("keyup",function(){
        var keyword = $(this).val();
        if (keyword.length==0) {
            $(this).next().addClass("hidden");
        } else {
            searchSchool(keyword);
            $(this).next().removeClass("hidden");
        }
    })
    if($("#sel-d2").length > 0 && !$("#sel-d2").hasClass("hidden")){
        var schoolid = zb_cookie.getCookie("school_id");
        $(".sel-d2 .sel-school").attr("pk",schoolid);
        $(".sel-d2 .sel-school").find(".department-item").val($("#college").text());
        if($(".sel-d2 .sel-school").find(".department-item").val()!=""){
            getAcademy(schoolid,_slideDown($(".sel-academy .sel-hd")));
        }
        _optpkOpen();
        $(".sel-hd").bind("click",function(event){
            event.preventDefault();
            var slide_target = $(this).next(".sel-content");
            if(slide_target.hasClass("slide-down")){
                _slideUp($(this));
            }else{
                _slideDown($(this));
            }
        });
        $(".optpk-btn a").bind("click",function(){  //保存
            var school_id=$(".sel-d2 .sel-school").attr("pk");
            var department_id=$(".sel-d2 .sel-academy").attr("pk");
            var user_grade=$(".sel-d2 .sel-adyear").attr("pk");
            $.post(
                'user/first_insert_dep_grade',
                {
                    user_school_id:school_id,
                    user_department:department_id,
                    user_grade:user_grade
                },
                function(){
                    $("#department-container").addClass("hidden");
                    if(zb_cookie.getCookie("cur_url")==''){
                        window.location.reload(true);
                    }else{
                        window.location.href=zb_cookie.getCookie("cur_url");
                    }
                }
            );
        })
    }
}

function _slideDown(target){
    $(".sel-d2 .sel-content").removeClass("slide-down");
    $(".optpk-container").addClass("hidden");
    clearInterval(ani);
    var slide_target = target.next(".sel-content");
    slide_target.addClass("slide-down");
}

function _slideUp(target){
    var slide_target = target.next(".sel-content");
    slide_target.removeClass("slide-down");
    _optpkOpen();
}

function _optpkOpen(){
    var i,len = $(".department-item").length;
    for(i=0;i<len;i++){
        var val = $(".department-item").eq(i).val();
        if(val == ""){
            return;
        }
    }
    if($(".slide-down").length == 0){
        setTimeout(function(){
            $(".optpk-container").removeClass("hidden");
            ani = setInterval(_aniDots,400);
        },200);
    }
}
var color1="yellow";
var color2="red";
var ani;
function _aniDots(){
    var dots = $(".decorating-dots li");
    var len = dots.length;
    var color_tmp
    for(var i=0;i<len;i++){
        if(dots[i].className == color1){
            dots[i].className=color2;
            return;
        }
        if(i==len-1){
            color_tmp=color1;
            color1=color2;
            color2=color_tmp;
        }
    }
}

/********** 关闭弹窗  ************/
function closeContainer(){
    var closes = $(".close-ico");
    if(closes.length>0){
        closes.each(function(){
            $(this).bind("click",function(event){
                event.preventDefault();
                var cont = $(this).closest(".close-container");
                if(cont.length>0){
                    cont.addClass("hidden");
                }
            })
        })
    }
}
closeContainer();

function detectFlash(tips){
    var hasFlash = false;
    try{
        hasFlash = Boolean(new ActiveXObject('ShockwaveFlash.ShockwaveFlash'));
    }catch(exception){
        hasFlash = ('undefined'!=typeof navigator.mimeTypes['application/x-shockwave-flash']);
    }
    if(!hasFlash){
        alert(tips);
    }
}

if($(".upload-area").length){
    detectFlash("为不影响图片上传功能，请取消浏览器对Flash的禁用~ 例如IE下：设置->安全->ActiveX");
}

/********* IE compatibility **********/

function getAcademy(id,fn){
    $.ajax({
        url:"/school/department/"+id,
        type:"get",
        success:function(data){
            var data = $.parseJSON(data);
            if(data.code==0){  //数据返回成功
                adacadeFlag = false;
                $(".sel-academy .sel-items").empty();
                var arrs = data.data.department_list;
                var lis = "";
                for(var i=0; i<arrs.length; i++){
                    lis+='<li><a value="'+arrs[i].department_id+'" href="javascript:;">'+arrs[i].department_name+'</a></li>';
                }
                $(".sel-academy .sel-items").html("").append($(lis));
                fn;
            }else{
                alert(data.msg);
            }
        }
    });
}
