<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
	<#include "../common/left_menu.ftl"/> 
    <div class="container">
   		<div class="main center">
        <div class="label-wr center clearfix">
            <div id="nav-labels">
                <button id="new_pro" class="labels" style="background:rgb(75, 192, 165);color:white;width:100%;padding-left: 8px;padding-right: 8px;" >所选分类：${gc.name}</button>
            </div>
           
        </div>
        
        <div class="item-list">
            <ul class="items clearfix">
            
            	<#if pageBean.content??>
            	<#list pageBean.content as goods>
            	<li class="item">
                    <a href="../goods/detail?id=${goods.id}" class="img" target="_top">
                    	<img src="/photo/view?filename=${goods.photo}" alt="${goods.name}"></a>
                    <div class="info">
                        <div class="price">${goods.sellPrice}</div>
                        <div class="name">
                            <a href="../goods/detail?id=${goods.id}" target="_top">${goods.name}</a>
                        </div>
                        <div class="department"><span>${goods.student.academy}</span></div>
                        <div class="place"><span>${goods.student.school}</span></div>
                        <#if goods.recommend == 1>
                        <div class="school"><span>推荐</span></div>
                        </#if>
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
            <a class="page-arrow arrow-left" href="list?cid=${gc.id!""}&currentPage=1">首页</a>
            </#if>
            <#list pageBean.currentShowPage as showPage>
	        <#if pageBean.currentPage == showPage>
	        <a class="page-num cur" href="javascript:void(0)">${showPage}</a>
	        <#else>
            <a class="page-num " href="list?cid=${gc.id!""}&currentPage=${showPage}">${showPage}</a>
           	</#if>
           	</#list>
           	<#if pageBean.currentPage == pageBean.totalPage>
            <a class="page-arrow arrow-right" href="javascript:void(0)">尾页</a>
            <#else>
	        <a class="page-arrow arrow-right" href="list?cid=${gc.id!""}&currentPage=${pageBean.totalPage}">尾页</a>
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