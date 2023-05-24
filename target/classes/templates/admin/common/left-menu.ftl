<nav class="sidebar-main">
  <ul class="nav nav-drawer">
    <li class="nav-item"> <a href="/system/index"><i class="mdi mdi-home"></i> 后台首页</a> </li>
    <#if userTopMenus??>
    <#list userTopMenus as userTopMenu>
    	<li class="nav-item nav-item-has-subnav">
	      <a href="javascript:void(0)"><i class="mdi ${userTopMenu.icon}"></i>${userTopMenu.name}</a>
	      <ul class="nav nav-subnav">
	        <#if userSecondMenus??>
	        <#list userSecondMenus as userSecondMenu>
	        <#if userTopMenu.id == userSecondMenu.parent.id>
	        <li class="second-menu"><a href="${userSecondMenu.url}"><i class="mdi ${userSecondMenu.icon}"></i> ${userSecondMenu.name}</a> </li>
	        </#if>
	        </#list>
	        </#if>
	      </ul>
	    </li>
    </#list>
    </#if>
  </ul>
</nav>