/**
 * banner管理详情对话框（可用于添加和修改对话框）
 */

var bannerInfoDlg = {
	formId : "bannerInfoForm", //form表单id
	table : parent.banner.table,
    infoData: {
    	id: $("#id").val(),
    	
    },
    //验证方式
    validate: {
	    rules: {
	    	title:{
				required:true,
				minlength:1,
				maxlength:50,
			},
			name:{
				required:true,
			}
		},
		
    }
};
function getValue(id,name,info){
	$('#sourceId').val(id);
	$('#name').val(name);
	$('#type').val(info);
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
           var url = "http://sugong.oss-cn-shanghai.aliyuncs.com/" + ossFileName;
           newUrl = url;
           console.log("3333"+newUrl);
           $("#bannerImg").val(newUrl);
           $("#img").attr("src",newUrl);
         })
   }
});
function genOssFileName(fileType, entityType, suffix) {
    var date = new Date().getTime(); 
    var fileName = "fzsg/banner/"+date +"." + suffix;
    return fileName;
}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#name").click(function(){
		layer_show('添加banner',Feng.ctxPath+'/sg_banner/add_bannerPanel','800','500');
	})
	if(bannerInfoDlg.infoData.id ==null ||bannerInfoDlg.infoData.id =="" ){
		Feng.initValidator(bannerInfoDlg.formId, bannerInfoDlg.validate,bannerInfoDlg.table,"/sg_banner/add"); //新增
	}else{
		Feng.initValidator(bannerInfoDlg.formId, bannerInfoDlg.validate,bannerInfoDlg.table,"/sg_banner/edit"); //编辑
	}
	
});