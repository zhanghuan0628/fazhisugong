/**
 * 奖品
 */
var award = {
    id: "awardTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};
/**
 * 获取选中数据
 */
award.getSelIds = function(){
	var  isCheck =false;
	award.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	award.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}
/*奖品-编辑*/
award.edit_award=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*奖品-新增*/
award.add_award=function (title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*奖品-删除(批量)*/
award.del_award=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_award_set/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		award.table.draw();
				Feng.success("已删除!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(award.getSelIds()){
    	var selIds = award.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/**
 * 初始化表格的列
 * 
 */
award.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},           
        {title: '名称',width:'300px', data: 'name'},
        {title: '中奖几率',width:'300px', data: 'tips'},
        {title:'操作',width:'300px',render: function(data, type, row, meta){
        	var msg = "";
    		msg+='<a title="编辑" href="javascript:;" onclick="award.edit_award(\'编辑详情\',\'/sg_award_set/edit_award\','
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
award.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+award.id,
	    			url : Feng.ctxPath +"/sg_award_set/award_pageList",
	    			param : ["name"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
award.search = function () {
	award.table.draw();
   
}

$(function () {
    var defaultColunms = award.initColumn();
    var options = award.dataTables(defaultColunms);    
    award.table = defDataTables(options);
});
