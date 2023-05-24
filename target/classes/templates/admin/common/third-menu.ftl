<div class="toolbar-btn-action">
<#if userThirdMenus??>
	<#list userThirdMenus as userThirdMenu>
	<#if userThirdMenu.show == true>
	<#if userThirdMenu.button == true>
	<a class="btn btn-primary m-r-5" href="javascript:void(0)" onClick="${userThirdMenu.url}"><i class="mdi ${userThirdMenu.icon}"></i>${userThirdMenu.name}</a>
	<#else>
	<a class="btn btn-primary m-r-5" href="${userThirdMenu.url}"><i class="mdi ${userThirdMenu.icon}"></i>${userThirdMenu.name}</a>
	</#if>
	</#if>
	</#list>
</#if>
</div>