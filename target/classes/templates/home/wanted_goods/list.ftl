<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/want_list.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
	<#include "../common/left_menu.ftl"/> 
    <div class="container">
            <div class="main center clearfix">
                <div class="want-title"></div>
                <div class="wrap-want-list">
                    <ul class="want-list" id="want-list">
                        <#if pageBean.content??>
                        <#list pageBean.content as wantedGoods>
                        <li class="want-item">
                        	<div class="want-li clearfix">
                        		<div class="left">
                        			<a href="">
                        				<#if wantedGoods.student.headPic??>
                        				<img src="/photo/view?filename=${wantedGoods.student.headPic}" alt="头像">
                        				<#else>
                        				<img src="/home/imgs/avatar1.png" alt="头像">
                        				</#if>
                        			</a>
                        		</div>
                        		<div class="right">
                        			<h4 class="want-name">【求购】
                        				<span>${wantedGoods.name}</span>
                        			</h4>
                        			<p class="want-detail">${wantedGoods.content}</p>
                        			<p class="want-attr">
                        				<span>期望价格</span>
                        				<span class="want-price">¥${wantedGoods.sellPrice}</span>
                        				<span>期望交易地点：</span>
                        				<span class="want-add">${wantedGoods.tradePlace}</span>
                        				<span>${wantedGoods.createTime}</span>
                        			</p>
                        			<p class="want-contact">
                        				<p class="want-person">${wantedGoods.student.nickname!""}</p>
                        				<span class="mr10">TEL：${wantedGoods.student.mobile!""}</span>
                        				<span>QQ：${wantedGoods.student.qq!""}</span>
                        			</p>
                        		</div>
                        	</div>
                        </li>
                        </#list>
                        </#if>
                    </ul>
                </div>
                <#if pageBean.total gt 0>
		        <!-- 分页 开始 -->
		        <div class="pages">
		            <#if pageBean.currentPage == 1>
		            <a class="page-arrow arrow-left" href="javascript:void(0)">首页</a>
		            <#else>
		            <a class="page-arrow arrow-left" href="list?currentPage=1">首页</a>
		            </#if>
		            <#list pageBean.currentShowPage as showPage>
			        <#if pageBean.currentPage == showPage>
			        <a class="page-num cur" href="javascript:void(0)">${showPage}</a>
			        <#else>
		            <a class="page-num " href="list?currentPage=${showPage}">${showPage}</a>
		           	</#if>
		           	</#list>
		           	<#if pageBean.currentPage == pageBean.totalPage>
		            <a class="page-arrow arrow-right" href="javascript:void(0)">尾页</a>
		            <#else>
			        <a class="page-arrow arrow-right" href="list?currentPage=${pageBean.totalPage}">尾页</a>
			        </#if>
		        </div>
		        <!-- 分页 结束 -->
		        </#if>
            </div>
        </div>
<div class="return-to-top"><a href="#"></a></div><!--返回顶部-->
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/index.js"></script>
</html>