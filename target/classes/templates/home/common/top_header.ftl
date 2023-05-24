<style>
.loginandrigist {
    margin-left: 1000px;
    margin-top: -66px;
}

#person_info {
    height: 100%;
    cursor: pointer;
    position: relative;
}
.clearfix {
    zoom: 1;
}
</style>
<header class="ease2">
	    <a href="/home/index/index">
	        <#if siteSetting.logo1??>
	        <img class="logo ease2" src="/photo/view?filename=${siteSetting.logo1}" alt="${siteSetting.siteName}">
	        <#else>
	        <img class="logo ease2" src="/home/imgs/index_logo.png" alt="${siteSetting.siteName}">
	    	</#if>
	    </a>
	    <div class="header-main center ease2">
	        <a href="/home/index/index" class="slogan">
	            <h1 class="s-main"></h1>
	            <div class="s-submain"></div>
	            <#if siteSetting.logo1??>
		        <img src="/photo/view?filename=${siteSetting.logo2}" alt="${siteSetting.siteName!""}">
		        <#else>
	            <img src="/home/imgs/2shoujie_web_title.png" alt="${siteSetting.siteName!""}">
	        	</#if>
	        </a>
	        <div class="search-box-wr ease2">
	            <div class="search-box center" >
	               <button class="search-submit" id="search-button" onclick="searchPro()">搜索</button> 
	                <div class="input-wr">
	                    <img class="search-icon" src="/home/imgs/search-icon.png">
	                    <div class="search-input">
	                    <input name="keyword" id="keyword" x-webkit-speech="" placeholder="搜索你想要的商品" value="" type="text">
	                    </div>
	                </div>
	            </div>

	            <div class="search-hots center ease2">
	                <span>热门：</span>
	                <#list goodsCategorys as hotGoodsCategory>
			        <#if hotGoodsCategory.parent??>
			        <#else>
	                <a class="hots" href="/home/goods/list?cid=${hotGoodsCategory.id}" target="_top">${hotGoodsCategory.name}</a>
	                </#if>
	                <#if hotGoodsCategory_index == 20>
	                <#break>
    				</#if>
	                </#list>
	            </div>
	        </div>
	        
	        <div class="ease2 log-reg" id="have-not-login">
	       		<#if ylrc_student??>
		        <div id="have_login" class="clearfix">
		        	<div id="person_info" class="clearfix">
		        		<a href="../student/index">
		        			<#if ylrc_student.headPic??>
		        			<img  class="avatar"  style="height:48px;width:48px;" src="/photo/view?filename=${ylrc_student.headPic}">
		        			<#else>
		        			<img  class="avatar"  style="height:48px;width:48px;" src="/home/imgs/avatar1.png">
		        			</#if>
		        		</a>
			        	<div  style="display:inline;"  class="person_name">
			        		<a href="../student/index" id="id-btn">Hi：${ylrc_student.nickname!ylrc_student.sn}</a>
			        	</div>
			        	<div  style="display:inline;"  class="person_name">
			        		<a href="../index/logout" id="log-btn">&nbsp;&nbsp;&nbsp;&nbsp;退出</a>
			        	</div>
			        </div>
		        </div>
		        <#else>
	       		<!-- loginandrigist -->
	            <div class="button" ><a href="../index/login">登录/注册</a></div>
		        </#if>
	        </div>
	    </div>
	</header>
<script type="text/javascript">
function searchPro(){
	var keyword=$("#keyword").val();
	window.location.href="/home/index/index?name="+keyword;
}
</script>