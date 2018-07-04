/**
 * 我是法官详情对话框（可用于添加和修改对话框）
 */

var themeInfoDlg = {
	formId : "themeInfoForm", //form表单id
	table : parent.theme.table,
    infoData: {
    	id: $("#id").val(),
    	
    },
    //验证方式
    validate: {
	    rules: {
	    	title:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			startDate:{
				required:true,
			},
			endDate:{
				required:true,
			},
			content:{
				required:true,
				minlength:1,
				maxlength:500,
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
	if(themeInfoDlg.infoData.id ==null ||themeInfoDlg.infoData.id =="" ){
		Feng.initValidator(themeInfoDlg.formId, themeInfoDlg.validate,themeInfoDlg.table,"/sg_theme/add"); //新增
	}else{
		Feng.initValidator(themeInfoDlg.formId, themeInfoDlg.validate,themeInfoDlg.table,"/sg_theme/edit"); //编辑
	}
	
});