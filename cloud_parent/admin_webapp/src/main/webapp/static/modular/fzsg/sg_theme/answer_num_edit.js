/**
 * 我是法官
 */
var answerNum = {
    id: "answerNumTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 初始化表格的列
 * 
 */
answerNum.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '姓名',width:'300px', data: 'userName'},
        {title: '正确率',width:'300px', data: 'num'},
        {title: '获奖情况',width:'300px', data: 'code'}
        ];
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
	    			url : Feng.ctxPath +"/sg_theme/answerPerson_awardList",
	    			param : ["userName","themeId","code"]
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
});
