/**
 * 专业详情对话框（可用于添加和修改对话框）
 */
var awardInfoDlg = {
	formId : "awardInfoForm", //form表单id
	table : parent.award.table,
    infoData: {
    	id: $("#id").val(),
    },
    //验证方式
    validate: {
	    rules: {
	    	name:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			tips:{
				required:true,
				minlength:1,
				maxlength:10,
			},
		},
		messages : {
			"name" : {
				remote : "此名称已经存在"
			}
		}
    }
};



$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(awardInfoDlg.infoData.id ==null ||awardInfoDlg.infoData.id =="" ){
		Feng.initValidator(awardInfoDlg.formId, awardInfoDlg.validate,awardInfoDlg.table,"/sg_award_set/add"); //新增
	}else{
		Feng.initValidator(awardInfoDlg.formId, awardInfoDlg.validate,awardInfoDlg.table,"/sg_award_set/edit"); //编辑
	}
	
	
});