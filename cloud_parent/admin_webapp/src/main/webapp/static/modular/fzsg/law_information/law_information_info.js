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
				maxlength:50,
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
			author:{
				required:true,
				minlength:1,
				maxlength:16,
			},
			createTime:{
				required:true
			},
			
		},
		messages : {
			"title" : {
				remote : "此标题已经存在"
			}
		}
		
    }
    
	 
	
};


function getContent(){
	var content =current_editor.getContent();
	content = getOssContent("Feng.ctxPath",content)
	$("#content").val(content)
    
}

var ossClient = new OSS.Wrapper({
    region: "oss-cn-shanghai",
    accessKeyId: "LTAICG7rs8rsGNj4",
    accessKeySecret: "FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm",
    bucket: "sugong"
});
var newUrl = "";
$("#file").bind("change", function(e) {
   for (var i = 0; i < e.target.files.length; i++) {
       var file = e.target.files[i];
       
       var geshiStr = $("#file").val();
       if (geshiStr.indexOf(".mp4") < 0  && geshiStr.indexOf(".MP4") < 0 ) {
           alert("格式不正确！请上传mp4,mp4格式！");
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
           var url = "http://sugong.oss-cn-shanghai.aliyuncs.com/" + ossFileName;
           newUrl = url;
           console.log("3333"+newUrl);
           $("#videoUrl").val(newUrl);
         })
   }
});
function genOssFileName(fileType, entityType, suffix) {
    var date = new Date().getTime(); 
    var fileName = "FaZhiSuSong/lawInformation/"+date +"." + suffix;
    return fileName;
}

$("#imgFile").bind("change", function(e) {
	   for (var i = 0; i < e.target.files.length; i++) {
	       var file = e.target.files[i];
	       
	       var geshiStr = $("#imgFile").val();
	       if (geshiStr.indexOf(".jpg") < 0  && geshiStr.indexOf(".png") < 0 && geshiStr.indexOf(".JPG") < 0  && geshiStr.indexOf(".PNG") < 0 ) {
	           alert("格式不正确！请上传mp4,mp4格式！");
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
	           var url = "http://sugong.oss-cn-shanghai.aliyuncs.com/" + ossFileName;
	           newUrl = url;
	           console.log("3333"+newUrl);
	           $("#imgUrl").val(newUrl);
	           $("#img").attr("src",newUrl);
	         })
	   }
	});


var current_editor = UE.getEditor('editor',{
	initialFrameHeight:900,
	open_editor: true
});
$(function(){
	current_editor.ready(function(){
		current_editor.setContent($("#content").val())
	})
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if($("#type").val()=="text"){
		$("#text").show();
		$("#video").hide();
	}else{
		$("#video").show();
		$("#text").hide();
	}
	$("#type").change(function(){
		if($(this).val()=="text"){
			$("#text").show();
			$("#video").hide();
		}else{
			$("#video").show();
			$("#text").hide();
		}
	})
	if(sgLawInformationInfoDlg.infoData.id ==null ||sgLawInformationInfoDlg.infoData.id =="" ){
		Feng.initValidator(sgLawInformationInfoDlg.formId, sgLawInformationInfoDlg.validate,sgLawInformationInfoDlg.table,"/sg_law_information/add"); //新增
	}else{
		Feng.initValidator(sgLawInformationInfoDlg.formId, sgLawInformationInfoDlg.validate,sgLawInformationInfoDlg.table,"/sg_law_information/edit"); //编辑
	}
	
});