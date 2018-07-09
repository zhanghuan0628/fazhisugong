/**
 * 
 */
var userTheme = {
    id: "userThemeTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};


/**
 * 初始化表格的列
 * 
 */
userTheme.initColumn = function () {
	var columns = [
	               {title: '期数',width:'300px', data: 'stage'},
	               {title: '答对题数',width:'300px', data: 'rightNum'},
	               {title: '分数',width:'300px', data: 'score'},
	               {title: '中奖情况',width:'300px', data: 'name'},
	               {title: '答题时间',width:'300px', data: 'createTime'}/*,
	               {title: '领奖情况',width:'300px', data: 'receive',render: function(data, type, row, meta){
	            	   if(data == true){
	            		   return "已领";
	            	   }else{
	            		   return "未领"
	            	   }
	               }}*/
	               ];
    return columns;
};

/**
 * 初始化表格参数
 */
userTheme.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+userTheme.id,
	    			url : Feng.ctxPath +"/sg_user/user_theme_list",
	    			param : ["userId"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
userTheme.search = function () {
	userTheme.table.draw();
}

$(function () {
	
    var defaultColunms = userTheme.initColumn();
    var options = userTheme.dataTables(defaultColunms);    
    userTheme.table = defDataTables(options);
   
});
