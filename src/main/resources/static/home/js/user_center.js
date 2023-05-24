function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); 
            return null; //返回参数值
        }
var userid = getUrlParam('id');

$(document).ready(function(){
	//inituser(userid);
	//inituserpros(userid);
})

function inituser(id){
	
}


function inituserpros(uid){
	$.ajax({
		type:"POST",
		url:"userServlet",
		dataType:"json",
		data:{"uid":uid,"flag":2},
		success:function(resp){
		var count = resp.length
		for(var i = 0;i<resp.length;i++){
			if(resp[i].state==1){
			var mypro1 = $('<div class="enshr_each" id="prolist">  ' +
					'<div class="enshr_info"><h2><a href="product_detail.jsp?pid='+resp[i].pid+'" '+
					'title="坚果pro">'+resp[i].pname+'</a></h2><p>'+resp[i].pdesc+'</p>'+
					'<div class="enshr_state"><span id="prostate">状态：正在出售'+resp[i].state+'</span>' +
					'&nbsp;&nbsp;<span id="prostate">上架日期：'+resp[i].creatTime+'</span>'+
					'<span class="enshrine_it" onclick="sellout('+resp[i].pid+');">确认售出</span>'+
					'<span class="enshrine_it make_edition" onclick="offshelf('+resp[i].pid+');">下架</span>'+
					'<span class="enshrine_it make_edition" onclick="refresh('+resp[i].pid+');">擦亮</span>'+
	                '<a href="product_release.jsp?pid='+resp[i].pid+'" target="_top"><span class="enshrine_it  make_edition">编辑</span></a> '+
	                '</div></div><a href="product_detail.jsp?pid='+resp[i].pid+'">'+
	                '<img class="enshr_ph" src="'+resp[i].pimage+'" alt="'+resp[i].pname+'"></a></div>')
			}
	        if(resp[i].state==0){
			var mypro1 = $('<div class="enshr_each" id="prolist">  ' +
					'<div class="enshr_info"><h2><a href="product_detail.jsp?pid='+resp[i].pid+'" '+
					'title="坚果pro">'+resp[i].pname+'</a></h2><p>'+resp[i].pdesc+'</p>'+
					'<div class="enshr_state"><span id="prostate">状态：已售出'+resp[i].state+'</span>' +
					'&nbsp;&nbsp;<span id="prostate">上架日期：'+resp[i].creatTime+'</span>'+
					'<span class="enshrine_it" style="color:yellow" );">已售出</span>'+
					'<span class="enshrine_it make_edition" style="color:red"  onclick="offshelf('+resp[i].pid+');">删除</span>'+
	                '</div></div><a href="product_detail.jsp?pid='+resp[i].pid+'">'+
	                '<img class="enshr_ph" src="'+resp[i].pimage+'" alt="'+resp[i].pname+'"></a></div>')
			}
	       
	                
	                
			$("#onsale_pro").append(mypro1);
		}
		$("#procount").html('<p>'+count+'</p>')
		
		}
	});
}

function offshelf(id){
    if (!confirm('下架后商品别人将看不到，请是否确认下架？')) {
        return;
    }
    ajaxRequest('update_status','post',{"id" : id,"status":2},function(){
    	alert("恭喜！您已成功下架商品！");
        location.reload();
	});
}
function onshelf(id){
    ajaxRequest('update_status','post',{"id" : id,"status":1},function(){
    	alert("恭喜！您已成功上架商品，所有人可以看到您的商品啦！");
        location.reload();
	});
}
function refresh(id,flag){
    
	ajaxRequest('update_flag','post',{"id" : id,"flag":flag},function(){
			if(flag == 1){
				alert("恭喜！您已擦亮商品！");
			}else{
				alert("您已取消擦亮商品！");
			}
	        location.reload();
		});
}


function sellout(id){
    if (!confirm('确认售出？')) {
        return;
    }
    ajaxRequest('update_status','post',{"id" : id,"status":3},function(){
    	alert("恭喜！您又售出一个商品！");
        location.reload();
	});
}

