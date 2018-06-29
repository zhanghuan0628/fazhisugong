/**
 * 咨询师回复（可用于添加和修改对话框）
 */
var SysUserInfoDlg = {
	formId : "counselorBackForm", //form表单id
	table : parent.sgUserAsk.table,
    infoData: {
    	topicId: $("#topicId").val(),
    	content: $("#content").val(),
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
	if(SysUserInfoDlg.infoData.id ==null ||SysUserInfoDlg.infoData.id =="" ){
		Feng.initValidator(SysUserInfoDlg.formId, SysUserInfoDlg.validate,SysUserInfoDlg.table,"/sg_user/add_counselor_back"); //新增
	}else{
		Feng.initValidator(SysUserInfoDlg.formId, SysUserInfoDlg.validate,SysUserInfoDlg.table,"/sg_user/edit_counselor_back"); //编辑
	}
	
	
});