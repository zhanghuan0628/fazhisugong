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
var current_editor = UE.getEditor('editor',{
	initialFrameHeight:900,
	open_editor: true
});

/**
 * 启用本地上传转oss
 * @param action
 * @returns
 */
UE.Editor.prototype._bkGetActionUrl=UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl=function(action){
	if (action == 'uploadimage' ||action== 'uploadscrawl' || action == 'uploadimage') {
		//上传图片，涂鸦，截图工具
		return Feng.ctxPath +'/common/ueditor_upload';
	} else if(action =='uploadvideo') {
		return Feng.ctxPath +'/common/ueditor_upload';
	}else if(action =='uploadfile') {
		return Feng.ctxPath +'/common/ueditor_upload';
	}  else if(action =='listfile') {
		// 列出指定目录下的文件
		return this._bkGetActionUrl.call(this, action);
	}  else if(action == 'listimage'){
		//列出指定目录下的图片
		return this._bkGetActionUrl.call(this, action);
	} else{
		return this._bkGetActionUrl.call(this, action);
	}
}

$(function(){
	  
	 //文件上传
	 var oss = new $OssUpload("imgUrl","img","FaZhiSuSong/lawInformation/",true);
	 oss.ossUpdate();
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