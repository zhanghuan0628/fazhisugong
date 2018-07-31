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
				maxlength:50,
			},
			pass:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			good:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			fine:{
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
				maxlength:1000,
			},
		},
		
    }
    
	 
	
};

$(function(){
	$("#subjectCounts").blur(function(){
		if(Number($("#subjectCounts").val())>Number($("#count").val())){
			Feng.info("本欺题数不得大于总题库数"+$("#count").val());
		}
	})
	$("#pass").blur(function(){
		if(Number($("#pass").val())>Number($("#subjectCounts").val())){
			Feng.info("及格题数不得大于本欺题数"+$("#subjectCounts").val());
		}
	})
	$("#good").blur(function(){
		if(Number($("#good").val())>Number($("#subjectCounts").val())){
			Feng.info("良好题数不得大于本欺题数"+$("#subjectCounts").val());
		}
	})
	$("#fine").blur(function(){
		if(Number($("#fine").val())>Number($("#subjectCounts").val())){
			Feng.info("优秀题数不得大于本欺题数"+$("#subjectCounts").val());
		}
	})
	
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