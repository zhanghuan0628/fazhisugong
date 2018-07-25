/**
 * oss-upload 工具类
 * 
 * 约定：
 * 上传按钮的id = 图片隐藏域id + 'BtnId'
 * 图片预览框的id = 图片隐藏域id + 'PreId'
 * type: img 图片类型
 * 		 audio 音频文件
 *       video 视频文件
 *       all   不限类型  默认
 * 
 * ossPath oss存储路径  例如 sg/img  默认路径为 default+所传类型
 * uploadBar  true : 有上传提醒，  false：无上传提醒
 * @author jiawei
 */
var OssInfo = {
		updateType : "",
		ossPath : "",
		pictureId : "",
		preUrl : "http://sugong.oss-cn-shanghai.aliyuncs.com/",
		uploadBtnId : "",
		uploadPreId : "",
		fileSizeLimit : 100 * 1024 * 1024,
		picWidth : 800,
		picHeight : 800,
		uploadBar : false,
};
    
(function() {
	var ossClient = new OSS.Wrapper({
	    region: "oss-cn-shanghai",
	    accessKeyId: "LTAICG7rs8rsGNj4",
	    accessKeySecret: "FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm",
	    bucket: "sugong"
	});
	
	var $OssUpload = function(pictureId,type,ossPath,uploadBar) {
		OssInfo.updateType = type==undefined?'all':type;
		OssInfo.ossPath = ossPath==undefined?OssInfo.updateType:ossPath;
		OssInfo.pictureId = pictureId;
		OssInfo.uploadBar = uploadBar==undefined?OssInfo.uploadBar:uploadBar;
		
		OssInfo.uploadBtnId = pictureId + "BtnId";
		OssInfo.uploadPreId = pictureId + "PreId";
		

		OssInfo.fileSizeLimit = 100 * 1024 * 1024;
		OssInfo.picWidth = 800;
		OssInfo.picHeight = 800;
		OssInfo.uploadBarId = null;
	};

	$OssUpload.prototype = {
		
		ossUpdate : function(){
			$("#"+OssInfo.uploadBtnId).bind("change", function(e) {
				   for (var i = 0; i < e.target.files.length; i++) {
				       var file = e.target.files[i];
				       
				       var geshiStr = $("#"+OssInfo.uploadBtnId).val();
				       if(OssInfo.updateType == 'img'){
				    	   if (geshiStr.indexOf(".jpg") < 0  && geshiStr.indexOf(".png") < 0 && geshiStr.indexOf(".JPG") < 0  && geshiStr.indexOf(".PNG") < 0
				    			   && geshiStr.indexOf(".gif") < 0 && geshiStr.indexOf(".GIF") < 0  && geshiStr.indexOf(".jpeg") < 0 && geshiStr.indexOf(".JPEG") < 0
				    			   && geshiStr.indexOf(".bmp") < 0 && geshiStr.indexOf(".BMP") < 0 ) {
				    		   Feng.error("图片格式仅支持.jpg/.gif/.jpeg/.bmp/.png,请确认后重新上传");
					           return false;
					       } 
				       }
				       if(OssInfo.updateType == 'audio'){
				    	   if (geshiStr.indexOf(".mp3") < 0  && geshiStr.indexOf(".MP3") < 0) {
				    		   Feng.error("图片格式仅支持.mp3,请确认后重新上传");
					           return false;
					       } 
				       }
				       if(OssInfo.updateType == 'video'){
				    	   if (geshiStr.indexOf(".mp4") < 0  && geshiStr.indexOf(".MP$") < 0) {
				    		   Feng.error("图片格式仅支持.mp4,请确认后重新上传");
					           return false;
					       } 
				       }
				       var fileSplits = file.name.split(".");
				       var date = new Date().getTime(); 
				       console.log("文件类型"+fileSplits);
				       var ossFileName = OssInfo.ossPath + date +"." + fileSplits[fileSplits.length - 1];
				       ossClient.multipartUpload(ossFileName, file,{progress: function* (p) {
				    	   if(OssInfo.uploadBar){
				    		   Feng.info("正在上传");
				    	   }
				       }}).then(function (result) {
				          /* $("#progressWindow").window('close');*/
				    	   Feng.info("上传成功!");
				           var url = OssInfo.preUrl + ossFileName;
				           newUrl = url;
				           $("#"+OssInfo.pictureId).val(newUrl);
				           $("#"+OssInfo.uploadPreId).attr("src",newUrl);
				         })
				   }
				});
			},
	};

	window.$OssUpload = $OssUpload;

}());