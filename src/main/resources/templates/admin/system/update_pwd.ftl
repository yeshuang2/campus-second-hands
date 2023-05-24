<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}后台管理系统主页</title>
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
              <div class="card-body">
                
                <form method="post" action="update_pwd" class="site-form" id="edit-pwd-form">
                  <div class="form-group">
                    <label for="old-password">旧密码</label>
                    <input type="password" class="form-control required" name="oldpwd" id="old-password" placeholder="输入账号的原登录密码" tips="请填写旧密码">
                  </div>
                  <div class="form-group">
                    <label for="new-password">新密码</label>
                    <input type="password" class="form-control required" name="newpwd" id="new-password" placeholder="输入新的密码" tips="请填写新密码">
                  </div>
                  <div class="form-group">
                    <label for="confirm-password">确认新密码</label>
                    <input type="password" class="form-control required" name="confirmpwd" id="confirm-password" placeholder="请确认新密码" tips="请再次填写新密码">
                  </div>
                  <button type="button" class="btn btn-primary" id="submit-btn">修改密码</button>
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
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//监听上传图片按钮
	$("#submit-btn").click(function(){
		if(!checkForm("edit-pwd-form")){
			return;
		}
		var oldPwd = $("#old-password").val();
		var newPwd = $("#new-password").val();
		var reNewPwd = $("#confirm-password").val();
		if(newPwd != reNewPwd){
			showErrorMsg('两次密码输入不一致！');
			return;
		}
		//向后台发送请求
		var data = {oldPwd:oldPwd,newPwd:newPwd};
		ajaxRequest('/system/update_pwd','post',data,function(rst){
			showSuccessMsg('密码修改成功！',function(){});
		});
	});
});

</script>
</body>
</html>