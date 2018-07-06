/**
 * 专业详情对话框（可用于添加和修改对话框）
 */
var majorInfoDlg = {
	formId : "majorInfoForm", //form表单id
	table : parent.sgMajor.table,
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
				remote : {
					url : Feng.ctxPath +"/sg_major/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"name" : function() {
							if (majorInfoDlg.infoData.name != $("#name").val())
								return $("#name").val();
						},
						"pid":"4"
					}
				},
			},
			tips:{
				minlength:1,
				maxlength:200,
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
	if(majorInfoDlg.infoData.id ==null ||majorInfoDlg.infoData.id =="" ){
		Feng.initValidator(majorInfoDlg.formId, majorInfoDlg.validate,majorInfoDlg.table,"/sg_major/add"); //新增
	}else{
		Feng.initValidator(majorInfoDlg.formId, majorInfoDlg.validate,majorInfoDlg.table,"/sg_major/edit"); //编辑
	}
	
	
});