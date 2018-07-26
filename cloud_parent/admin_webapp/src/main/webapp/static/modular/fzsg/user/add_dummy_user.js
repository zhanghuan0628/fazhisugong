/**
 * 虚拟用户详情对话框（可用于添加和修改对话框）
 */

var dummyUserInfoDlg = {
	formId : "dummyUserInfoForm", //form表单id
	table : parent.sgUser.table,
    infoData: {
    	id: $("#id").val(),
    	loginName:$("#loginName").val()
    },
    //验证方式
    validate: {
	    rules: {
	    	userName:{
				required:true,
				minlength:1,
				maxlength:12,
			},
			loginName:{
				required:true,
				minlength:4,
				maxlength:16,
				remote : {
					url : Feng.ctxPath +"/sg_user/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"loginName" : function() {
							if (dummyUserInfoDlg.infoData.loginName != $("#loginName").val())
								return $("#loginName").val();
						}
					}
				},
			},
		},
		messages : {
			"loginName" : {
				remote : "登录账号已经存在"
			}
		}
		
    }
    
	 
	
};
$(function(){
	//文件上传
	var oss = new $OssUpload("headUrl","img","FaZhiSuSong/user/",true);
	
	oss.ossUpdate();
	
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(dummyUserInfoDlg.infoData.id ==null ||dummyUserInfoDlg.infoData.id =="" ){
		Feng.initValidator(dummyUserInfoDlg.formId, dummyUserInfoDlg.validate,dummyUserInfoDlg.table,"/sg_user/add_dummy"); //新增
	}else{
		Feng.initValidator(dummyUserInfoDlg.formId, dummyUserInfoDlg.validate,dummyUserInfoDlg.table,"/sg_user/edit_dummy"); //编辑
	}
	
});