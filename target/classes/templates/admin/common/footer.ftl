<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<!--对话框-->
<script src="/admin/js/jconfirm/jquery-confirm.min.js"></script>
<script src="/admin/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var currentUrl = window.location.pathname;
	var curs = currentUrl.split("/");
	currentUrl = curs[2];
	$(".second-menu").each(function(i,e){
		var url = $(e).children("a").attr('href').split("/");
		if(url[2] == currentUrl){
			$(e).addClass('active');
			$(e).parent("ul").parent("li").addClass("active open")
		}
	});
});
</script>
