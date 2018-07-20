/**
 * 
 */
var lawHall = {
    id: "lawHallTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 获取选中数据
 */
lawHall.getSelIds = function(){
	var  isCheck =false;
	lawHall.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	lawHall.seItem.push($(this).val());
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
lawHall.initColumn = function () {
	var columns = [
	               {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="radio" name="checklist" value="'+data+'" class="iCheck">';}},
	               {title: '标题',width:'300px', data: 'title'},
	               {title: '发布时间',width:'400px', data: 'createTime'},
	               {title: '类型',width:'200px', data: 'type',render: function(data, type, row, meta){
	               	if(data=='text'){
	               		return "图文";
	               	}else{
	               		return "视频";
	               	}
	               }},
	               {title: '状态',width:'300px', data: 'status',render: function(data, type, row, meta){
	               	if(data=='publish')return '<span class="labels labels-success radius">上架</span>';	
	               	else if(data=='no_publish')return '<span class="labels labels-default radius">下架</span>';
	               	else return '<span class="labels labels-default radius">未发布</span>';
	               }},
	               {title: '收藏人数',width:'300px', data: 'count'}];
    return columns;
};

/**
 * 初始化表格参数
 */
lawHall.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawHall.id,
	    			url : Feng.ctxPath +"/sg_law_hall/law_hall_List?status=publish",
	    			param : ["title"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawHall.search = function () {
	lawHall.table.draw();
}
/**
 * 保存
 */
lawHall.save = function(){
	    if(lawHall.getSelIds()){
	    	var selIds = lawHall.seItem; 
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
					var info = "hall";
					parent.getValue(id,title,info);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
	    }
}
$(function () {
    var defaultColunms = lawHall.initColumn();
    var options = lawHall.dataTables(defaultColunms);    
    lawHall.table = defDataTables(options);
    Feng.selectSingleRow(lawHall.id,lawHall);
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	lawHall.search();
        }
    });
});
