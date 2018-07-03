/**
 * 法治动态详情对话框（可用于添加和修改对话框）
 */

var sgLawInformationInfoDlg = {
	formId : "lawInformationInfoForm", //form表单id
	table : parent.lawInformation.table,
    infoData: {
    	id: $("#id").val(),
    	title: $("#title").val(),
    	category: $("#category").val(),
    	
    },
    //验证方式
    validate: {
	    rules: {
	    	title:{
				required:true,
				minlength:1,
				maxlength:16,
				remote : {
					url : Feng.ctxPath +"/sg_law_information/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"title" : function() {
							if (sgLawInformationInfoDlg.infoData.title != $("#title").val())
								return $("#title").val();
						},
    					"category":"law_information"
					}
				},
			},
			content:{
				required:true,
				minlength:1,
				maxlength:10000,
			},
			num:{
				required:true,
				minlength:1,
				maxlength:10,
			},
			
		},
		messages : {
			"title" : {
				remote : "此标题已经存在"
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
	if(sgLawInformationInfoDlg.infoData.id ==null ||sgLawInformationInfoDlg.infoData.id =="" ){
		Feng.initValidator(sgLawInformationInfoDlg.formId, sgLawInformationInfoDlg.validate,sgLawInformationInfoDlg.table,"/sg_law_information/add"); //新增
	}else{
		Feng.initValidator(sgLawInformationInfoDlg.formId, sgLawInformationInfoDlg.validate,sgLawInformationInfoDlg.table,"/sg_law_information/edit"); //编辑
	}
	
});