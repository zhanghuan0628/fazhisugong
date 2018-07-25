/**
 * 我是法官
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
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}
/*我是法官-编辑*/
theme.edit_theme=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id+"&type=edit",w,h);
}
/*我是法官-查看*/
theme.see_theme=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id+"&type=see",w,h);
}
/*我是法官-新增*/
theme.add_theme=function (title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*我是法官-删除(批量)*/
theme.del_theme=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_theme/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		theme.table.draw();
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
    if(theme.getSelIds()){
    	var selIds = theme.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/**
 * 初始化表格的列
 * 
 */
theme.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" value="'+data+'" class="iCheck">';}},
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
        }},
        {title:'操作',width:'300px', data: 'state',render: function(data, type, row, meta){
        	var msg = "";
        	if(data=='1'){
        		msg+='<a title="查看" href="javascript:;" onclick="theme.see_theme(\'查看详情\',\'/sg_theme/edit_theme\','
        			+ "'"
        			+row.id
        			+ "'"
        			+',\'1000\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe695;</i>'
        			+'</a>'
        			+'</td>';
        	}
        	else if(data=='2'){
        		msg+='<a title="编辑" href="javascript:;" onclick="theme.edit_theme(\'编辑详情\',\'/sg_theme/edit_theme\','
        			+ "'"
        			+row.id
        			+ "'"
        			+',\'1000\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe6df;</i>'
        			+'</a>'
        			+'</td>';
        	}else{
        		msg+='<a title="查看" href="javascript:;" onclick="theme.see_theme(\'查看详情\',\'/sg_theme/edit_theme\','
        			+ "'"
        			+row.id
        			+ "'"
        			+',\'1000\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe695;</i>'
        			+'</a>'
        			+'</td>';
        	}
        	
        	return msg;
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
	    			param : ["num","title"]
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
    Feng.selectMultiRow(theme.id,theme);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	theme.search();
        }
    });
});
