<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>欢迎登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/home/css/login2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/home/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/home/js/login.js"></script>

</head>
<body>
<h1 style="color: #4BC0A5">${siteName!""}<sup></sup></h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;">
            </div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
               <div class="login-box">
			<div class="login_form">
				<div id="loginCue" class="cue">欢迎登录</div>
			
				<form action="login" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" 
                    method="post">
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="id">学号：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="login-sn" name="sn" class="inputstyle" value=""/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="pwd">密码：</label> 
               <div class="inputOuter" id="pArea">
                    
                    <input type="password" id="pwd" name="password" class="inputstyle"/>
                </div>
                </div>
               
                	<div style="padding-left:50px;margin-top:20px;">
                	<button id="login_button"type="button" value="登 录" style="width:150px;" class="button_blue" href="javascript::">登录按钮</button>
                		<!-- <input id="login_button"type="submit" value="登 录" style="width:150px;" class="button_blue"/> -->
                	</div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--登录end-->
  </div>

  <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
   
    <div class="web_login">
        <form name="form2" id="regForm" accept-charset="utf-8"  action="register" method="post">
        <ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue">快速注册请注意格式</div>
                <li>
                	
                    <label for="user"  class="input-tips2">学号：</label>
                    <div class="inputOuter2">
                        <input type="text" id="user-sn" name="sn" maxlength="12" class="inputstyle2"/>
                    </div>
                    
                </li>
                
                <li>
                <label for="passwd" class="input-tips2">密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="password"  name="password" maxlength="16" class="inputstyle2"/>
                    </div>
                    
                </li>
                <li>
                <label for="passwd2" class="input-tips2">确认密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="password2" maxlength="16" class="inputstyle2" />
                    </div>
                    
                </li>
                
                <li>
                 <label for="qq" class="input-tips2">QQ：</label>
                    <div class="inputOuter2">
                       
                        <input type="text" id="qq" name="qq" maxlength="10" class="inputstyle2"/>
                    </div>
                   
                </li>
                
                <li>
                    <div class="inputArea">
                        <input type="button" id="reg_button"  style="margin-top:10px;margin-left:85px;" class="button_blue" value="注册成为会员"/> 
                    </div>
                    
                </li><div class="cl"></div>
            </ul>
        </form>
           
    
    </div>
   
    
    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body>
</html>