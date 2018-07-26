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

$(function(){
	//文件上传
	var oss = new $OssUpload("bannerImg","img","FaZhiSuSong/banner/",true);
	
	oss.ossUpdate();
	
	
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