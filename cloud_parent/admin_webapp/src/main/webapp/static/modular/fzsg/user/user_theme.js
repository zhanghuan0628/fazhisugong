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
 * 初始化表格的列
 * 
 */
theme.initColumn = function () {
    var columns = [
        {title: '期数', data: 'stage'},
        {title: '答对题数', data: 'rightNum'},
        {title: '分数', data: 'score'},
        {title: '中奖情况', data: 'name'},
        {title: '答题时间', data: 'createTime'},
        ];
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
	    			url : Feng.ctxPath +"/sg_user/user_theme_list?userId="+$("#userId").val(),
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
theme.search = function () {
	theme.table.draw();
   
}

$(function () {
    var defaultColunms = theme.initColumn();
    var options = theme.dataTables(defaultColunms);    
    theme.table = defDataTables(options);
});
