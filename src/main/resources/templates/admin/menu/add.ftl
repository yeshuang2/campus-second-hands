<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|菜单管理-添加菜单</title>
<#include "../common/header.ftl"/>

</head>
  
<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <aside class="lyear-layout-sidebar">
      
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll"> 
        <#include "../common/left-menu.ftl"/>
      </div>
      
    </aside>
    <!--End 左侧导航-->
    
    <#include "../common/header-menu.ftl"/>
    
     <!--页面主要内容-->
    <main class="lyear-layout-content">
      
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>添加菜单</h4></div>
              <div class="card-body">
                <form action="add" id="menu-add-form" method="post" class="row">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">上级分类</span>
                    <select name="parent.id" class="form-control" id="type">
                        <option value="">请选择上级分类</option>
                        <#if topMenus??>
                        	<#list topMenus as topMenu>
                        		<option value="${topMenu.id}" style="font-weight:bold;">${topMenu.name}</option>
                        		<#if secondMenus??>
                        			<#list secondMenus as secondMenu>
                        				<#if secondMenu.parent.id == topMenu.id>
                        				<option value="${secondMenu.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${secondMenu.name}</option>
                        				</#if>
                        			</#list>
                        		</#if>
                        	</#list>
                        </#if>
                      </select>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">菜单名称</span>
                    <input type="text" class="form-control required" id="name" name="name" value="" placeholder="请输入菜单名称" tips="请填写菜单名称" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">菜单URL</span>
                    <input type="text" class="form-control" id="url" name="url" value="" placeholder="请填写菜单url" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">菜单icon</span>
                    <input type="text" readonly="readonly" class="form-control required" id="icon" name="icon" value="" placeholder="请输入菜单icon" tips="请选择菜单icon" />
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" id="show-icons-btn" data-toggle="modal" data-target="#icons-panel" type="button">点击选择</button>
                    </span>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">排序</span>
                    <input type="number" class="form-control" id="sort" name="sort" value="0" />
                  </div>
                  <div class="input-group m-b-10">
                    是否按钮：
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="button" value="true" >
                    <span>是</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="button" value="false" checked="">
                    <span>否</span>
                  </label>
                  </div>
                  <div class="input-group m-b-10">
                    是否显示：
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="show" value="true" checked="">
                    <span>是</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="show" value="false" >
                    <span>否</span>
                  </label>
                  </div>
                  <div class="form-group col-md-12">
                    <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">确 定</button>
                    <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</button>
                  </div>
                </form>
       
              </div>
            </div>
          </div>
          
        </div>
        
      </div>
      
    </main>
    <!--End 页面主要内容-->
  </div>
</div>
<#include "../common/footer.ftl"/>
<#include "../common/icons.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//图标icon选择后的确认按钮事件
	$("#confirm-icon-btn").click(function(){
		getSelectedIcon();
	});
	//提交按钮监听事件
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("menu-add-form")){
			return;
		}
		var data = $("#menu-add-form").serialize();
		$.ajax({
			url:'add',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('菜单添加成功!',function(){
						window.location.href = 'list';
					})
				}else{
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('网络错误!');
			}
		});
	});
});
function getSelectedIcon(){
	$("#icon").val($(".selected-icon").attr('val'));
	$("#icons-panel").modal('hide');
}
</script>
</body>
</html>