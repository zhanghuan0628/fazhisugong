$(function(){
	var anim = $('#iconModal').data('easein');
   	$('#iconModal').addClass(' animated '+anim)
	$('#iconModal').modal({
	      keyboard: true
	   })
	$(".sp").click(function(){
		parent.getValue($(this).text());
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	})
})