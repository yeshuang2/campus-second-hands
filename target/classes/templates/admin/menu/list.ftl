<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|菜单管理-${title!""}</title>
<#include "../common/header.ftl"/>
<style>
td{
	vertical-align:middle;
}
</style>
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
              <div class="card-toolbar clearfix">
                <!--
                <form class="pull-right search-bar" method="get" action="#!" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <input type="hidden" name="search_field" id="search-field" value="title">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                      名称 <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">名称</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="" name="keyword" placeholder="请输入名称">
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" type="button">搜索</button>
                    </span>
                  </div>
                </form>
                -->
                <#include "../common/third-menu.ftl"/>
              </div>
              <div class="card-body">
                
                <div class="table-responsive">
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th>
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" id="check-all"><span></span>
                          </label>
                        </th>
                        <th>菜单名称</th>
                        <th>菜单url</th>
                        <th>菜单icon</th>
                        <th>菜单排序</th>
                        <th>是否按钮</th>
                        <th>是否显示</th>
                        <th>添加时间</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if topMenus??>
                      <#list topMenus as topMenu>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${topMenu.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;"><b>${topMenu.name}</b></td>
                        <td style="vertical-align:middle;">${topMenu.url}</td>
                        <td align="center"><i style="font-size:22px;" class="mdi ${topMenu.icon}" title="${topMenu.icon}" data-toggle="tooltip"></i></td>
                        <td align="center" style="vertical-align:middle;">${topMenu.sort}</td>
                        <td style="vertical-align:middle;"><#if topMenu.button == true><font class="text-success">是</font><#else><font class="text-info">否</font></#if></td>
                        <td style="vertical-align:middle;"><#if topMenu.show == true><font class="text-success">是</font><#else><font class="text-info">否</font></#if></td>
                        <td style="vertical-align:middle;"><font class="text-success">${topMenu.createTime}</font></td>
                      </tr>
					   <#if secondMenus??>
					   <#list secondMenus as secondMenu>
					   <#if secondMenu.parent.id == topMenu.id>
					   <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${secondMenu.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">|-----${secondMenu.name}</td>
                        <td style="vertical-align:middle;">${secondMenu.url}</td>
                        <td align="center"><i style="font-size:22px;" class="mdi ${secondMenu.icon}" title="${secondMenu.icon}" data-toggle="tooltip"></i></td>
                        <td align="center" style="vertical-align:middle;">${secondMenu.sort}</td>
                        <td style="vertical-align:middle;"><#if secondMenu.button == true><font class="text-success">是</font><#else><font class="text-info">否</font></#if></td>
                        <td style="vertical-align:middle;"><#if secondMenu.show == true><font class="text-success">是</font><#else><font class="text-info">否</font></#if></td>
                        <td style="vertical-align:middle;"><font class="text-success">${secondMenu.createTime}</font></td>
                      </tr>
                      <#if thirdMenus??>
                      <#list thirdMenus as thirdMenu>
                      	<#if thirdMenu.parent.id == secondMenu.id>
					    <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${thirdMenu.id}"><span></span>
                          </label>
                        </td>
                        <td style="padding-left:45px;vertical-align:middle;">|-----${thirdMenu.name}</td>
                        <td style="vertical-align:middle;">${thirdMenu.url}</td>
                        <td align="center"><i style="font-size:22px;" class="mdi ${thirdMenu.icon}" title="${thirdMenu.icon}" data-toggle="tooltip"></i></td>
                        <td align="center" style="vertical-align:middle;">${thirdMenu.sort}</td>
                        <td style="vertical-align:middle;"><#if thirdMenu.button == true><font class="text-success">是</font><#else><font class="text-info">否</font></#if></td>
                        <td style="vertical-align:middle;"><#if thirdMenu.show == true><font class="text-success">是</font><#else><font class="text-info">否</font></#if></td>
                        <td style="vertical-align:middle;"><font class="text-success">${thirdMenu.createTime}</font></td>
                      </tr>
                      </#if>
                      </#list>
                      </#if>
                      </#if>
                      </#list>
                      </#if>
                    </#list>
					</#if>                      
                    </tbody>
                  </table>
                </div>
                <!--
                <ul class="pagination">
                  <li class="disabled"><span>«</span></li>
                  <li class="active"><span>1</span></li>
                  <li><a href="#1">2</a></li>
                  <li><a href="#1">3</a></li>
                  <li><a href="#1">4</a></li>
                  <li><a href="#1">5</a></li>
                  <li><a href="#!">»</a></li>
                </ul>
       			-->
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
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
function del(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('请选择一条数据进行删除！');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	$.confirm({
        title: '确定删除？',
        content: '删除后数据不可恢复，请慎重！',
        buttons: {
            confirm: {
                text: '确认',
                action: function(){
                    deleteReq(id,url);
                }
            },
            cancel: {
                text: '关闭',
                action: function(){
                    
                }
            }
        }
    });
}
//打开编辑页面
function edit(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('请选择一条数据进行编辑！');
		return;
	}
	window.location.href = url + '?id=' + $("input[type='checkbox']:checked").val();
}
//调用删除方法
function deleteReq(id,url){
	$.ajax({
		url:url,
		type:'POST',
		data:{id:id},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('菜单删除成功!',function(){
					$("input[type='checkbox']:checked").parents("tr").remove();
				})
			}else{
				showErrorMsg(data.msg);
			}
		},
		error:function(data){
			alert('网络错误!');
		}
	});
}
</script>
</body>
</html>