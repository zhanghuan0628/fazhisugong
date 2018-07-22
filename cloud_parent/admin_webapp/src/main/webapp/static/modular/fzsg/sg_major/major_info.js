/**
 * 专业详情对话框（可用于添加和修改对话框）
 */
var majorInfoDlg = {
	formId : "majorInfoForm", //form表单id
	table : parent.sgMajor.table,
    infoData: {
    	id: $("#id").val(),
    	name: $("#name").val(),
    },
    //验证方式
    validate: {
	    rules: {
	    	name:{
				required:true,
				minlength:1,
				maxlength:50,
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
       if (geshiStr.indexOf(".png") < 0 && geshiStr.indexOf(".PNG") < 0 ) {
           alert("格式不正确！请上传png格式！");
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
           $("#img").val(newUrl);
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
	if(majorInfoDlg.infoData.id ==null ||majorInfoDlg.infoData.id =="" ){
		Feng.initValidator(majorInfoDlg.formId, majorInfoDlg.validate,majorInfoDlg.table,"/sg_major/add"); //新增
	}else{
		Feng.initValidator(majorInfoDlg.formId, majorInfoDlg.validate,majorInfoDlg.table,"/sg_major/edit"); //编辑
	}
	
	
});