/**
 * 用户列表发布资讯
 */
var sgUserAsk = {
    id: "sgUserAskTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/*查看*/
sgUserAsk.user_ask_detail=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
sgUserAsk.back=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*删除(单)*/
sgUserAsk.del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sg_user/del?id="+id, function (data) {
        	if(data.code ==2000){
        		sgUserAsk.table.draw();
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
/**
 * 初始化表格的列
 * 
 */
sgUserAsk.initColumn = function () {
    var columns = [
        {title: '咨询标题', data: 'title'},
        {title: '发布时间', data: 'createTime'},
        {title:'操作', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="查看" href="javascript:;" onclick="sgUserAsk.user_ask_detail(\'查看详情\',\'/sg_user/user_ask_detail\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe695;</i>'
			+'</a> <a title="回复" href="javascript:;" onclick="sgUserAsk.back(\'咨询师回复\',\'/sg_user/counselor_back\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe692;</i></a> <a title="删除" href="javascript:;" onclick="sgUserAsk.del(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe609;</i></a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
sgUserAsk.dataTables = function (columns) {
	    var userOptions ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+sgUserAsk.id,
	    			url : Feng.ctxPath +"/sg_user/user_ask_list",
	    			param : ["title","userId"]
	    		}	
	    }
	    return userOptions;
	};

/**
 * 搜索
 */
sgUserAsk.search = function () {
	sgUserAsk.table.destroy();
	var defaultColunms = sgUserAsk.initColumn();
    var options = sgUserAsk.dataTables(defaultColunms);    
    sgUserAsk.table = defDataTables(options);
	//sgUserAsk.table.draw();
}

$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var userDetailColunms = sgUserAsk.initColumn();
    var userDetailOptions = sgUserAsk.dataTables(userDetailColunms);    
    sgUserAsk.table = defDataTables(userDetailOptions);
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	sgUserAsk.search();
        }
    });
});