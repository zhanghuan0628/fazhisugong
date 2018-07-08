/**
 * 虚拟用户列表
 */
var lawInfo = {
    id: "lawInfoTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 获取选中数据
 */
lawInfo.getSelIds = function(){
	var  isCheck =false;
	lawInfo.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	lawInfo.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请选择一条数据!");
    }
    return isCheck;
}

/**
 * 初始化表格的列
 * 
 */
lawInfo.initColumn = function () {
	var columns = [
	               {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
	               {title: '标题',width:'300px', data: 'title'},
	               {title: '发布时间',width:'300px', data: 'createTime'},
	               {title: '状态',width:'300px', data: 'status',render: function(data, type, row, meta){
	               	if(data=='publish')return '<span class="label label-success radius">上架</span>';	
	               	else if(data=='no_publish')return '<span class="label label-default radius">下架</span>';
	               	else return '<span class="label label-default radius">未发布</span>';
	               }}];
    return columns;
};

/**
 * 初始化表格参数
 */
lawInfo.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawInfo.id,
	    			url : Feng.ctxPath +"/sg_law_information/law_information_pageList?status=publish",
	    			param : ["title"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawInfo.search = function () {
	lawInfo.table.draw();
}
/**
 * 保存
 */
lawInfo.save = function(){
	    if(lawInfo.getSelIds()){
	    	var selIds = lawInfo.seItem; 
	    	console.log("sssssssssssssssssssssss:"+selIds);
	    	$.ajax({
				dataType : 'json',
				type : 'POST',
				url : Feng.ctxPath+"/sg_banner/queryLawTitle?ids="+selIds,
				data :null,
				success : function(result) {
					var data = result.data;
					var id = data.id;
					var title = data.title;
					var info = "info";
					parent.getValue(id,title,info);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
	    }
}
$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var defaultColunms = lawInfo.initColumn();
    var options = lawInfo.dataTables(defaultColunms);    
    lawInfo.table = defDataTables(options);
    Feng.selectSingleRow(lawInfo.id,lawInfo);
});
