/**
 * 咨询管理详情对话框（可用于添加和修改对话框）
 */

var askInfoDlg = {
	formId : "askInfoForm", //form表单id
	table : parent.ask.table,
    infoData: {
    	id: $("#id").val(),
    	
    },
    //验证方式
    validate: {
	    rules: {
	    	userName:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			see:{
				required:true,
			},
			title:{
				required:true,
				minlength:1,
				maxlength:50,
			},
			content:{
				required:true,
				minlength:1,
				maxlength:1000,
			},
		},
		
    }
};
function getValue(userId,userName){
	$('#userId').val(userId);
	$('#userName').val(userName);
}
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#userName").click(function(){
		layer_show('添加虚拟用户',Feng.ctxPath+'/sg_ask/add_xnUser','600','400');
	})
	if(askInfoDlg.infoData.id ==null ||askInfoDlg.infoData.id =="" ){
		Feng.initValidator(askInfoDlg.formId, askInfoDlg.validate,askInfoDlg.table,"/sg_ask/add"); //新增
	}else{
		Feng.initValidator(askInfoDlg.formId, askInfoDlg.validate,askInfoDlg.table,"/sg_ask/edit"); //编辑
	}
	
});