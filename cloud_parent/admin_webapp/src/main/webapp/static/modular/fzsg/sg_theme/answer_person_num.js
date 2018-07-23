/**
 * 回答人数管理
 */
var answerNum = {
    id: "answerNumTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/*回答人数管理-查看*/
answerNum.edit_answerNum=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?themeId="+id,w,h);
}

/**
 * 初始化表格的列
 * 
 */
answerNum.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '题目',width:'300px', data: 'stage'},
        {title: '答题人数',width:'300px', data: 'personNum'},
        {title:'操作',width:'300px', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="查看" href="javascript:;" onclick="answerNum.edit_answerNum(\'查看详情\',\'/sg_theme/answer_num_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'1000\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe695;</i>'
			+'</a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
answerNum.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+answerNum.id,
	    			url : Feng.ctxPath +"/sg_theme/answer_num_pageList",
	    			param : ["stage"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
answerNum.search = function () {
	answerNum.table.draw();
   
}

$(function () {
    var defaultColunms = answerNum.initColumn();
    var options = answerNum.dataTables(defaultColunms);    
    answerNum.table = defDataTables(options);
    Feng.selectMultiRow(answerNum.id,answerNum);
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	answerNum.search();
        }
    });
});
