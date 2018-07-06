/**
 * 咨询师回复（可用于添加和修改对话框）
 */
var userInfoDig = {
	formId : "counselorBackForm", //form表单id
	table : parent.ask.table,
    infoData: {
    	id: $("#id").val(),
    },
    //验证方式
    validate: {
	    rules: {
	    	content:{
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
	if(userInfoDig.infoData.id == null ||userInfoDig.infoData.id == "" ){
		Feng.initValidator(userInfoDig.formId, userInfoDig.validate,userInfoDig.table,"/sg_user/add_counselor_back"); //新增
	}else{
		Feng.initValidator(userInfoDig.formId, userInfoDig.validate,userInfoDig.table,"/sg_user/edit_counselor_back"); //编辑
	}
	
	
});