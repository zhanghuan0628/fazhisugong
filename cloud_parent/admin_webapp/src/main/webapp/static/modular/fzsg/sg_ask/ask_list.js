/**
 * 资讯
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
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}
/*查看*/
ask.ask_detail=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*新增*/
ask.ask_add=function (title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*
 * 回复
 */
ask.back=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*删除(单)*/
ask.del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sg_ask/del?ids="+id, function (data) {
        	if(data.code ==2000){
        		ask.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
	Feng.confirm('确认要删除吗？',operation);
}
/*删除(批量)*/
ask.del_ask=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_ask/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		ask.table.draw();
				Feng.success("已删除!");
			}else{
				Feng.error(data.message);
			}
        	$("#checkall").prop("checked", false);
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(ask.getSelIds()){
    	var selIds = ask.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/**
 * 初始化表格的列
 * 
 */
ask.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
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
        }},
        {title:'操作', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="查看" href="javascript:;" onclick="ask.ask_detail(\'查看详情\',\'/sg_ask/ask_detail\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe695;</i>'
			+'</a> <a title="回复" href="javascript:;" onclick="ask.back(\'回复\',\'/sg_ask/counselor_back\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe692;</i></a> <a title="删除" href="javascript:;" onclick="ask.del(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe609;</i></a>'
			+'</td>';
        	return msg;
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
	    			param : ["title","userName","see","dummy"]
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

$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var defaultColunms = ask.initColumn();
    var options = ask.dataTables(defaultColunms);    
    ask.table = defDataTables(options);
    Feng.selectMultiRow(ask.id,ask);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	ask.search();
        }
    });
});