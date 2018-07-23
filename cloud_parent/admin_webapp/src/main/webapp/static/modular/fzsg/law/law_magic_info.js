/**
 * 苏供法宝详情对话框（可用于添加和修改对话框）
 */
var sgLawMagicInfoDlg = {
	formId : "lawMagicInfoForm", //form表单id
	table : parent.lawMagic.table,
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
				maxlength:50,
				remote : {
					url : Feng.ctxPath +"/sg_law_magic/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"title" : function() {
							if (sgLawMagicInfoDlg.infoData.title != $("#title").val())
								return $("#title").val();
						},
						"category":"law_magic"
					}
				},
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
	if(sgLawMagicInfoDlg.infoData.id ==null ||sgLawMagicInfoDlg.infoData.id =="" ){
		Feng.initValidator(sgLawMagicInfoDlg.formId, sgLawMagicInfoDlg.validate,sgLawMagicInfoDlg.table,"/sg_law_magic/add"); //新增
	}else{
		Feng.initValidator(sgLawMagicInfoDlg.formId, sgLawMagicInfoDlg.validate,sgLawMagicInfoDlg.table,"/sg_law_magic/edit"); //编辑
	}
	
	
});