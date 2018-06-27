/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var SysUserInfoDlg = {
	formId : "userInfoForm", //form表单id
    infoData: {
    	id: $("#id").val(),
    	loginName: $("#loginName").val(),
    },
    //验证方式
    validate: {
	    rules: {
			loginName:{
				required:true,
				minlength:4,
				maxlength:16,
				remote : {
					url : Feng.ctxPath +"/sys_user/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"loginName" : function() {
							if (SysUserInfoDlg.infoData.loginName != $("#loginName").val())
								return $("#loginName").val();
						}
					}
				},
			},
			userName:{
				required:true,
				minlength:2,
				maxlength:16
			},
			sex:{
				required:true,
			},
			cellphone:{
				required:true,
				isPhone:true,
			},
			email:{
				required:true,
				email:true,
			},
			roleId:{
				required:true,
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
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(SysUserInfoDlg.infoData.id ==null ||SysUserInfoDlg.infoData.id =="" ){
		Feng.initValidator(SysUserInfoDlg.formId, SysUserInfoDlg.validate,"/sys_user/add"); //新增
	}else{
		Feng.initValidator(SysUserInfoDlg.formId, SysUserInfoDlg.validate,"/sys_user/edit"); //编辑
	}
	
	
});