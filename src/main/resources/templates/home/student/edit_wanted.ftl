<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}-编辑求购物品</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/release_product.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
   <div class="container">
            <div class="main center">
                <img class="release-icon-main" src="/home/imgs/release-icon.png" alt="">
                <div class="wave-fluid"></div>
                <div class="wantInfo-release-title">编辑求购物品</div>
                <form action="publish" id="publish-form" method="post">
                <input type="hidden" name="id" value="${wantedGoods.id}">
                <div class="form-wr">
                    <div class="form-must-wr">
                        <div class="form-item l goods-title">
                            <div class="form-key">
                                <span>商品名称</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input id="name" name="name" placeholder="最多18个字" maxlength="18" value="${wantedGoods.name}" type="text" class="required" tips="请填写商品名称"></div>
                            </div>
                        </div>
                        
                        <div class="form-item xl goods-desc">
                            <div class="form-key">
                                <span>商品详情</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <textarea name="content" id="desc" placeholder="建议填写物品用途、新旧程度、原价等信息，至少15个字" class="required" tips="请填写详情描述">${wantedGoods.content}</textarea>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="form-item l goods-title">
                            <div class="form-key">
                                <span>期望交易地点</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="required" id="tradePlace" name="tradePlace" value="${wantedGoods.tradePlace}" type="text" tips="请填写期望交易地点"></div>
                            </div>
                        </div>
                        <div class="form-item l goods-price">
                            <div class="form-key">
                                <span>期望价格</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="price required" id="sellPrice" name="sellPrice" value="${wantedGoods.sellPrice}" type="number" tips="请填写期望价格"></div>
                            </div>
                        </div>
                    </div>
                   	<input class="form-submit" id="submit-btn" type="button" value="发布" />
                  </div>
                  </form>
            </div>
        </div>
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/add.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#submit-btn").click(function(){
   		var flag = true;
   		$(".required").each(function(i,e){
   			if($(e).val() == ''){
   				alert($(e).attr('tips'));
   				flag = false;
   				return false;
   			}
   		});
   		if(flag){
   			if($("#desc").val().length < 15){
	   			alert('详情描述不能少于15个字！');
	   			return;
	   		}
	   		if(parseFloat($("#sellPrice").val()) == 'NaN'){
	   			alert('期望价格只能输入数字！');
	   			return;
	   		}
	   		//全部符合，准备提交表单
	   		ajaxRequest('edit_wanted_goods','post',$("#publish-form").serialize(),function(){
	   			window.location.href="index";
	   		});
   		}
   });

});

</script>	
</html>