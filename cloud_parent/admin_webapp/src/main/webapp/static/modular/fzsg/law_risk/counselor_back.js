/**
 * 咨询师回复（可用于添加和修改对话框）
 */
var userInfoDig = {
	formId : "counselorBackForm", //form表单id
	table : parent.sgRisk.table,
    infoData: {
    	replyId: $("#replyId").val(),
    },
    //验证方式
    validate: {
	    rules: {
	    	replyContent:{
				required:true,
				minlength:1,
				maxlength:500
			},
		},
    }
};



$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	Feng.initValidator(userInfoDig.formId, userInfoDig.validate,userInfoDig.table,"/sg_law_risk/edit_counselor_back"); //编辑
	
	
	
});