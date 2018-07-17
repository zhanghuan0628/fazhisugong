/**
 * 法治动态详情对话框（可用于添加和修改对话框）
 */
var editor = new wangEditor('editor');
// 仅仅想移除某几个菜单，例如想移除『插入代码』和『位置』菜单：
// 其中的 wangEditor.config.menus 可获取默认情况下的菜单配置
editor.config.menus = $.map(wangEditor.config.menus,
        function(item, key) {
            if (item === 'insertcode') {
                return null;
            }
            if (item === 'location') {
                return null;
            }
            if (item === 'fullscreen') {
                return null;
            }

            return item;
        });
// 关闭菜单栏fixed
editor.config.menuFixed = false;
editor.config.menuFixed = 60;
// 取消粘贴过滤,方便直接拷贝样式
editor.config.pasteFilter = false;

// 上传图片（举例）
editor.config.uploadImgUrl = Feng.ctxPath+'/common/img_upload';

// 配置自定义参数（举例）
editor.config.uploadParams = {
    catalog : 'article'
};
// 设置 headers（举例）
editor.config.uploadHeaders = {
    'Accept' : 'text/x-json'
};

// 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
editor.config.hideLinkImg = true;

editor.create();



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


function getContent(){
	var html = editor.$txt.html();
    console.log(html);
    $("#content").val(html);
    
}

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
           var url = "http://ffxl.oss-cn-shanghai.aliyuncs.com/" + ossFileName;
           newUrl = url;
           console.log("3333"+newUrl);
           $("#videoUrl").val(newUrl);
         })
   }
});
function genOssFileName(fileType, entityType, suffix) {
    var date = new Date().getTime(); 
    var fileName = "fzsg/lawInformation/"+date +"." + suffix;
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
	           var url = "http://ffxl.oss-cn-shanghai.aliyuncs.com/" + ossFileName;
	           newUrl = url;
	           console.log("3333"+newUrl);
	           $("#imgUrl").val(newUrl);
	           $("#img").attr("src",newUrl);
	         })
	   }
	});



$(function(){
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