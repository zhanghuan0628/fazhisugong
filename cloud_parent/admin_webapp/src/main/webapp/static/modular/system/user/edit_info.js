/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var SysUserInfoDlg = {
	formId : "userInfoForm", //form表单id
	table : '',
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

var ossClient = new OSS.Wrapper({
    region: "oss-cn-shanghai",
    accessKeyId: "LTAICG7rs8rsGNj4",
    accessKeySecret: "FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm",
    bucket: "ffxl"
});
var newUrl = "";
$("#file").bind("change", function(e) {
   for (var i = 0; i < e.target.files.length; i++) {
       var file = e.target.files[i];
       
       var geshiStr = $("#file").val();
       if (geshiStr.indexOf(".jpg") < 0  && geshiStr.indexOf(".png") < 0 && geshiStr.indexOf(".JPG") < 0  && geshiStr.indexOf(".PNG") < 0 ) {
           alert("格式不正确！请上传jpg,png格式！");
           return false;
       }
       var fileSplits = file.name.split(".");
      /* $("#progressWindow").window('center');
       $("#progressWindow").window({
           "modal" : true
       });*/
       /*$("#progressWindow").window('open');*/
       var ossFileName = genOssFileName("image", "storelayout", fileSplits[fileSplits.length - 1]);
       console.log("22222"+ossFileName);
       ossClient.multipartUpload(ossFileName, file,{progress: function* (p) {
           /*$('#progress').progressbar('setValue', p.toFixed(2)*100);*/
           
       }}).then(function (result) {
          /* $("#progressWindow").window('close');*/
    	   Feng.info("上传成功!");
           var url = "http://ffxl.oss-cn-shanghai.aliyuncs.com/" + ossFileName;
           newUrl = url;
           console.log("3333"+newUrl);
           $("#headImg").val(newUrl);
           $("#url").attr("src",newUrl);
         })
   }
});
function genOssFileName(fileType, entityType, suffix) {
    var date = new Date().getTime(); 
    var fileName = "fzsg/major/"+date +"." + suffix;
    return fileName;
}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(SysUserInfoDlg.infoData.id ==null ||SysUserInfoDlg.infoData.id =="" ){
		Feng.initValidator(SysUserInfoDlg.formId, SysUserInfoDlg.validate,SysUserInfoDlg.table,"/sys_user/add"); //新增
	}else{
		Feng.initValidator(SysUserInfoDlg.formId, SysUserInfoDlg.validate,SysUserInfoDlg.table,"/sys_user/edit"); //编辑
	}
	
	
});