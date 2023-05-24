<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}-发布物品</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/product_detail.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
  <#include "../common/left_menu.ftl"/>
   <div class="container">
        <div class="main center clearfix">
            <div class="ershou-details">
                <div class="ershou-photos-wr">
	            		 <!-- 收藏功能
	                    <a class="ershou-favorite" href="javascript:void(0);" style="background-image: url('heart.png');" onclick="favorites();">0</a> -->
                       <!-- 轮播大图-->
                        <div class="bigger-photo-box">
                        	<a class="bigger-photo hide show" rel="img_group" href="">
                        		<img class="bigger" src="/photo/view?filename=${goods.photo}"  alt="${goods.name}">
                        	</a>
                        </div>
                </div>
                <div class="ershou-info">
                    <div class="ershou-hd">
                        <p class="ershou-title">${goods.name}</p>
                        <div class=" discount">
                        	<span class="buy-price">原价：${goods.buyPrice}</span>
                        	<span class="ershou-price">二手街价：${goods.sellPrice}</span>
                        </div>
                        <p class="bro-counts">浏览了<span>${goods.viewNumber}</span>次</p>
                    </div>
                    <ul class="ershou-detail">
                    	<li class="ershou-place" id="pid">
                    		<div class="name">
                    			<span>商品编号</span>
                    		</div>
                    		<div class="value">
                    			<span id="pid">${goods.id}</span>
                    		</div>
                        </li>
                        <li class="ershou-time">
                        	<div class="name">
                        		<span>发布时间</span>
                        	</div>
                        	<div class="value">
                        		<span class="real-time"id="creat_time">${goods.createTime!""}</span>
                        	</div>
                        </li>
                        <li class="ershou-place">
                        	<div class="name">
                        		<span>交易地点</span>
                        	</div>
                        	<div class="value">${goods.student.school!"该用户未填写学校信息"}</div>
                        </li>
                        <li class="ershou-cert">
                        	<div class="name">
                        		<span>学院</span>
                        	</div>
                        	<div class="value">
                        		<span id="user_college">${goods.student.academy!"该用户未填写学院信息"}</span>
                        		<span id="user_grade">${goods.student.grade!"该用户未填写年级信息"}</span>
                        	</div>
                        </li>
                        <li class="ershou-seller">
                        	<div class="name">
                        		<span>卖家</span>
                        	</div>
                        	<div class="value">
                        		<span class="value-name" id="userid">${goods.student.nickname!goods.student.sn}</span>
                        	</div>
                        </li>
                    </ul>
                    <div id="buy-button" >
            			<a style="color: white;cursor:pointer;" > 联系卖家</a>
       	 			</div>
                    <div class="complain">
                    	<a href="javascript:void(0);" onclick="report(${goods.id});">•&nbsp;&nbsp;&nbsp;&nbsp;举报&nbsp;&nbsp;&nbsp;&nbsp;•</a>
                    </div>
                </div>
            </div>
            <div class="ershou-desc">
            	<div class="desc clearfix">
            		<a href=""target="_top">
            			<#if goods.student.headPic??>
            			<img id="user_ph" src="/photo/view?filename=${goods.student.headPic}">
            			<#else>
            			<img id="user_ph" src="/home/imgs/avatar1.png">
            			</#if>
            		</a>
            		<p id="user_cmt">${goods.content}</p>
            	</div>
            </div>
            <!--物品评论开始-->
            <div class="comments want-comments" style="width:98.5%">
				<div class="comments-wr" style="border-left:0px;">
					<div class="comment-wr">
						<div class="post-comment clearfix">
								<!--已登录状态下-->
								<#if ylrc_student??>
								<#if ylrc_student.headPic??>
								<img class="avatar" src="/photo/view?filename=${ylrc_student.headPic}" alt="头像"/>
								<#else>
								<img class="avatar" src="/home/imgs/avatar1.png" alt="头像"/>
								</#if>
								<div class="commenting want-commenting clearfix" >
									<div class="comment-input-wr-wr" style="margin-right:56px;">
										<div class="comment-input-wr" >
											<textarea class="comment-input-wr" name="comment-input" id="comment-content"></textarea>
										</div>
									</div>
									<button class="sub-comment" type="button" id="submit-comment-btn" data-reply="0" data-reply-content="0">评论</button>
								</div>
								<#else>
								<!--未登录状态下-->
								<img class="avatar" src="/home/imgs/avatar-unlogin.png" alt="头像"/>
								<div class="commenting-unlogin want-commenting clearfix">
									<div class="comment-input-wr-wr">
										<div class="comment-input-wr">
											<span class="tips">评论总要登录留个名吧</span>
										</div>
									</div>
									<button class="comment-login" data-type="l" id="to-login-btn">登录</button>
								</div>
								</#if>
								<!--已有的评论列表-->
								<#if commentList??>
								<div class="comments want-comments" style="width:98.5%;margin-top:0px;padding-top:0px;">
									<div class="comments-wr" style="border:0px;">
										<div class="comment-wr">
											<#list commentList as comment>
											<div class="comment" style="margin-top:25px;">
												<#if comment.student.headPic??>
												<img class="avatar" src="/photo/view?filename=${comment.student.headPic}" alt="头像"/>
												<#else>
												<img class="avatar" src="/home/imgs/avatar1.png" alt="头像"/>
												</#if>
												<div class="commentator" style="padding-left:55px;padding-bottom:5px;color:rgb(75, 192, 165);border-bottom: 1px dashed rgb(75, 192, 165);">
													<b>${comment.student.nickname!comment.student.sn}</b>
													<#if comment.replyTo??><span class="rpy-to">${comment.replyTo}</span></#if>
												</div>
												<p class="comment" style="padding-left:55px;padding-bottom:5px;padding-top:5px;">${comment.content}<font style="float:right;">${comment.createTime}</font></p>
												<div class="man" style="padding-left:55px;padding-bottom:5px;">
													<a class="rpl" href="#comment-content" data-reply="${comment.content}" data-name="${comment.student.nickname!comment.student.sn}">回复</a>
												</div>
											</div>
											</#list>
										</div>
									</div>
								</div>
								</#if>
						</div>
					</div>
				</div>
			</div>
            <!--物品评论结束-->
        </div>
    </div>
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/add.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#buy-button").click(function(){
		<#if ylrc_student??>
		alert("请您联系卖家购买商品。"+"\n"+"电话:${goods.student.mobile!""}\n"+"QQ:${goods.student.qq!""}");
		<#else>
		alert("请您先登录");
		window.location.href="/home/index/login";
		</#if>
	})
   $("#to-login-btn").click(function(){
   		window.location.href="/home/index/login";
   });

	$("#submit-comment-btn").click(function(){
		var content = $("#comment-content").val();
		if(content == ''){
			alert('请输入评论内容！');g
			return;
		}
		var data = {'goods.id':${goods.id},content:content};
		if($("#submit-comment-btn").attr('data-reply') != '0'){
			data.replyTo = $("#submit-comment-btn").attr('data-reply');
			data.content = data.content.replace('回复：' + data.replyTo + ':','')
			data.content = '回复：“' + $("#submit-comment-btn").attr('data-reply-content') + '”<br>' + data.content;
		}
		ajaxRequest('/home/student/comment','post',data,function(){
			alert('评论成功');
			window.location.reload();
		});
	});
	
	$(".rpl").click(function(){
		$("#comment-content").val('回复：'+$(this).attr('data-name') + ':')
		$("#submit-comment-btn").attr('data-reply',$(this).attr('data-name'))
		$("#submit-comment-btn").attr('data-reply-content',$(this).attr('data-reply'))
	});
});
function report(id){
	var content = prompt("请输入举报原因");
	if(content == null || content == ''){
		alert('请填写举报原因');
		return;
	}
	ajaxRequest('/home/student/report_goods','post',{'goods.id':id,content:content},function(){
		alert('举报成功');
	});
}
</script>	
</html>