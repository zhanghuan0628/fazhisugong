/**
 * 专业管理
 */
var sgMajor = {
    id: "sgMajorTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/*专业管理-编辑*/
sgMajor.edit_major=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*专业管理-新增*/
sgMajor.add_sgMajor=function (title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*专业管理-删除(批量)*/
sgMajor.del_sgMajor=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_major/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		sgMajor.table.draw();
				Feng.success("已删除!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(sgMajor.getSelIds()){
    	var selIds = sgMajor.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/**
 * 初始化表格的列
 * 
 */
sgMajor.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},           
        {title: '题目',width:'300px', data: 'name'},
        {title:'操作',width:'300px',render: function(data, type, row, meta){
        	var msg = "";
    		msg+='<a title="编辑" href="javascript:;" onclick="sgMajor.edit_major(\'编辑详情\',\'/sg_major/edit_major\','
    			+ "'"
    			+row.id
    			+ "'"
    			+',\'600\',\'400\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i>'
    			+'</a>'
    			+'</td>';
        	
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
sgMajor.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+sgMajor.id,
	    			url : Feng.ctxPath +"/sg_major/major_pageList",
	    			param : ["name"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
sgMajor.search = function () {
	sgMajor.table.draw();
   
}

$(function () {
    var defaultColunms = sgMajor.initColumn();
    var options = sgMajor.dataTables(defaultColunms);    
    sgMajor.table = defDataTables(options);
    Feng.selectMultiRow(sgMajor.id,sgMajor);
});
