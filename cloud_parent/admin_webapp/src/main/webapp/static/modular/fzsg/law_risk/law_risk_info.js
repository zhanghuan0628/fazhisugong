/**
 * 法律风险详情对话框（可用于添加和修改对话框）
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
 editor.config.uploadImgUrl = Feng.ctxPath + '/common/img_upload';

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

var sglawRiskInfoDlg = {
	formId : "lawRiskInfoForm", //form表单id
	table : parent.lawRisk.table,
    infoData: {
    	id: $("#id").val(),
    	title:$("#title").val(),
    },
    //验证方式
    validate: {
	    rules: {
	    	title:{
				required:true,
				minlength:1,
				maxlength:16,
				remote : {
					url : Feng.ctxPath +"/sg_law_risk/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"title" : function() {
							if (sglawRiskInfoDlg.infoData.title != $("#title").val())
								return $("#title").val();
						},
    					"category":"law_risk"
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
$(function(){
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(sglawRiskInfoDlg.infoData.id ==null ||sglawRiskInfoDlg.infoData.id =="" ){
		Feng.initValidator(sglawRiskInfoDlg.formId, sglawRiskInfoDlg.validate,sglawRiskInfoDlg.table,"/sg_law_risk/add"); //新增
	}else{
		Feng.initValidator(sglawRiskInfoDlg.formId, sglawRiskInfoDlg.validate,sglawRiskInfoDlg.table,"/sg_law_risk/edit"); //编辑
	}
	
});

/**
 * 评论
 */
var sgRisk = {
    id: "sgRiskTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};
/**
 * 获取选中数据
 */
sgRisk.getSelIds = function(){
	var  isCheck =false;
	sgRisk.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	sgRisk.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}
/*查看*/
sgRisk.user_ask_detail=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/**
 * 回复
 */
sgRisk.back=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*删除(批量)*/
sgRisk.del_sgRisk=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_risk/delComment?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		sgRisk.table.draw();
				Feng.success("已删除!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(sgRisk.getSelIds()){
    	var selIds = sgRisk.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*删除(单)*/
sgRisk.del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sg_law_risk/delComment?id="+id, function (data) {
        	if(data.code ==2000){
        		sgRisk.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
	Feng.confirm('确认要删除吗？',operation);
}
/*上下架(批量)*/
sgRisk.push_lawRisk=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_risk/updateState?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		sgRisk.table.draw();
				Feng.success("操作成功");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(sgRisk.getSelIds()){
    	var selIds = sgRisk.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}
/**
 * 初始化表格的列
 * 
 */
sgRisk.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},         
        {title: '风险文章', data: 'title'},
        {title: '评论者昵称', data: 'userName'},
        {title: '评论内容', data: 'content'},
        {title: '评论时间', data: 'createTime'},
        {title: '状态', data: 'state',render: function(data, type, row, meta){
        	if(data=='pass'){
        		return "已审核";
        	}else{
        		return "审核未通过";
        	}
        }},
        {title:'操作', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="回复" href="javascript:;" onclick="sgRisk.back(\'咨询师回复\',\'/sg_law_risk/counselor_back\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe692;</i></a> <a title="删除" href="javascript:;" onclick="sgRisk.del(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe609;</i></a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
sgRisk.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+sgRisk.id,
	    			url : Feng.ctxPath +"/sg_law_risk/law_risk_comment",
	    			param : ["userName"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
sgRisk.search = function () {
	sgRisk.table.draw();
   
}

$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var defaultColunms = sgRisk.initColumn();
    var options = sgRisk.dataTables(defaultColunms);    
    sgRisk.table = defDataTables(options);
});
