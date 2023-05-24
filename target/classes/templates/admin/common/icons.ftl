<style>
.select-icon{
	display: inline-block;
    width: 32px;
    height: 24px;
    text-align: center;
    vertical-align: middle;
    cursor: pointer;
    line-height: 24px;
    font-size:28px;
}
.selected-icon{
    border: 1px green solid;
}
</style>
<div class="modal fade" id="icons-panel" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4 class="modal-title" id="myLargeModalLabel">icons选择面板</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
				
				<div class="table-responsive">
                  <table class="table table-bordered table-striped">
                    <tbody id="icons-tbody">
                     
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>          
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" id="confirm-icon-btn" class="btn btn-primary">确认</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="/admin/js/icons.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//js调用图表常量库进行展示
	showIcons();
});
$(document).on('click','.select-icon',function() {
	$(".select-icon").removeClass('selected-icon');
	$(this).addClass('selected-icon');
});
$(document).on('dblclick','.select-icon',function() {
	getSelectedIcon();
});
function showIcons(){
	var html = '';
	for(var i = 0; i < all_system_icons.length; i++){
		var td = '';
		if(i == 0){
			td += '<tr align="center">';
		}
		td += '<td><i class="mdi ' + all_system_icons[i] + ' select-icon" val="' + all_system_icons[i] + '"></i></td>';
		if((i+1) % 15 == 0){
			td += '</tr><tr align="center">'
		}
		 html += td;
	}
	html += '</tr>';
	$("#icons-tbody").append(html);
}

</script>