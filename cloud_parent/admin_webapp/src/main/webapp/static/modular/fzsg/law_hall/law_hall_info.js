/**
 * 法治讲堂详情对话框（可用于添加和修改对话框）
 */
/**
 * 图文
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

//上传图片（举例）
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
 

var sgLawHallInfoDlg = {
	formId : "lawHallInfoForm", //form表单id
	table : parent.lawHall.table,
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
					url : Feng.ctxPath +"/sg_law_hall/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"title" : function() {
							if (sgLawHallInfoDlg.infoData.title != $("#title").val())
								return $("#title").val();
						},
    					"category":"law_lecture_room"
					}
				},
			},
			content:{
				required:true,
				minlength:1,
				maxlength:10000,
			},
			videoUrl:{
				required:true,
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

$(function(){
		var m =false;
		$("#imgUrlBtnId").click(function(){
			if(m){
				return;
			}
			//文件上传
			var oss = new $OssUpload("imgUrl","img","FaZhiSuSong/lawHall/",true);
			oss.ossUpdate();
			m=true;
			
		})
		var n =false;
		$("#videoUrlBtnId").click(function(){
			if(n){
				return;
			}
			//文件上传
			var oss1 = new $OssUpload("videoUrl","video","FaZhiSuSong/lawHall/",true);
			oss1.ossUpdate();
			n = true;
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
	if(sgLawHallInfoDlg.infoData.id ==null ||sgLawHallInfoDlg.infoData.id =="" ){
		Feng.initValidator(sgLawHallInfoDlg.formId, sgLawHallInfoDlg.validate,sgLawHallInfoDlg.table,"/sg_law_hall/add"); //新增
	}else{
		Feng.initValidator(sgLawHallInfoDlg.formId, sgLawHallInfoDlg.validate,sgLawHallInfoDlg.table,"/sg_law_hall/edit"); //编辑
	}
	
});