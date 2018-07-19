/**
 * 
 */
var ask = {
    id: "askTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 获取选中数据
 */
ask.getSelIds = function(){
	var  isCheck =false;
	ask.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	ask.seItem.push($(this).val());
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
ask.initColumn = function () {
	var columns = [
	               {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="radio" name="checklist" value="'+data+'" class="iCheck">';}},
	               {title: '咨询标题', data: 'title'},
	               {title: '发布时间', data: 'createTime'},
	               {title: '提问者昵称', data: 'userName'},
	               {title: '是否匿名', data: 'see',render: function(data, type, row, meta){
	               	if(data==1){
	               		return "是";
	               	}else{
	               		return "否";
	               	}
	               }},
	               {title: '是否虚拟', data: 'dummy',render: function(data, type, row, meta){
	               	if(data==1){
	               		return "是";
	               	}else{
	               		return "否";
	               	}
	               }}];
    return columns;
};

/**
 * 初始化表格参数
 */
ask.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+ask.id,
	    			url : Feng.ctxPath +"/sg_ask/ask_pageList",
	    			param : ["title"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
ask.search = function () {
	ask.table.draw();
}
/**
 * 保存
 */
ask.save = function(){
	    if(ask.getSelIds()){
	    	var selIds = ask.seItem; 
	    	console.log("sssssssssssssssssssssss:"+selIds);
	    	$.ajax({
				dataType : 'json',
				type : 'POST',
				url : Feng.ctxPath+"/sg_banner/queryAskTitle?ids="+selIds,
				data :null,
				success : function(result) {
					var data = result.data;
					var id = data.id;
					var title = data.title;
					var info = "ask";
					parent.getValue(id,title,info);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
	    }
}
$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var defaultColunms = ask.initColumn();
    var options = ask.dataTables(defaultColunms);    
    ask.table = defDataTables(options);
    Feng.selectSingleRow(ask.id,ask);
});
