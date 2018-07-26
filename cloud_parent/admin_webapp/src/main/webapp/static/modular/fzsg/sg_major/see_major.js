/**
 * 苏供法律风险列表
 */
var lawRisk = {
    id: "sgMajorTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 初始化表格的列
 * 
 */
lawRisk.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '标题',width:'300px', data: 'title'},
        {title: '专业',width:'300px', data: 'name'},
        {title: '状态',width:'300px', data: 'status',render: function(data, type, row, meta){
        	if(data=='publish')return '<span class="labels labels-success radius">上架</span>';	
        	else if(data=='no_publish')return '<span class="labels labels-default radius">下架</span>';
        	else return '<span class="labels labels-default radius">未发布</span>';
        }},
        {title: '排序',width:'50px', data: 'num'}];
    return columns;
};

/**
 * 初始化表格参数
 */
lawRisk.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawRisk.id,
	    			url : Feng.ctxPath +"/sg_law_risk/law_risk_pageList",
	    			param : ["title","categoryCode"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawRisk.search = function () {
	lawRisk.table.draw();
   
}

$(function () {
    var defaultColunms = lawRisk.initColumn();
    var options = lawRisk.dataTables(defaultColunms);    
    lawRisk.table = defDataTables(options);
    Feng.selectMultiRow(lawRisk.id,lawRisk);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	lawRisk.search();
        }
    });
});
