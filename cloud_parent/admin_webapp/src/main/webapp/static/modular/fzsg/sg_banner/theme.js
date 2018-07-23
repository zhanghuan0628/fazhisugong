/**
 * 
 */
var theme = {
    id: "themeTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 获取选中数据
 */
theme.getSelIds = function(){
	var  isCheck =false;
	theme.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	theme.seItem.push($(this).val());
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
theme.initColumn = function () {
	var columns = [
	               {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="radio" name="checklist" value="'+data+'" class="iCheck">';}},
	               {title: '题目',width:'300px', data: 'stage'},
	               {title: '主题',width:'300px', data: 'title'},
	               {title: '开始时间',width:'300px', data: 'startDate'},
	               {title: '结束时间',width:'300px', data: 'endDate'},
	               {title: '状态',width:'300px', data: 'state',render: function(data, type, row, meta){
	               	if(data=='1'){
	               		return "已结束";
	               	}
	               	else if(data=='2'){
	               		return "未开始";
	               	}else{
	               		return "进行中";
	               	}
	               }}];
	           return columns;
};

/**
 * 初始化表格参数
 */
theme.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+theme.id,
	    			url : Feng.ctxPath +"/sg_theme/theme_list_pageList",
	    			param : ["title4"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
theme.search = function () {
	theme.table.destroy();
	var defaultColunms = theme.initColumn();
    var options = theme.dataTables(defaultColunms);    
    theme.table = defDataTables(options);
}
/**
 * 保存
 */
theme.save = function(){
	    if(theme.getSelIds()){
	    	var selIds = theme.seItem; 
	    	console.log("sssssssssssssssssssssss:"+selIds);
	    	$.ajax({
				dataType : 'json',
				type : 'POST',
				url : Feng.ctxPath+"/sg_banner/queryThemeTitle?ids="+selIds,
				data :null,
				success : function(result) {
					var data = result.data;
					var id = data.id;
					var title = data.title;
					var info = "theme";
					parent.getValue(id,title,info);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
	    }
}
$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var defaultColunms = theme.initColumn();
    var options = theme.dataTables(defaultColunms);    
    theme.table = defDataTables(options);
    Feng.selectSingleRow(theme.id,theme);
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	theme.search();
        }
    });
});
