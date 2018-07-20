/**
 * 登录日志
 */
var loginLog = {
    id: "loginLogTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
};


loginLog.operation = function(url,tip,opt){
    var ajax = new $ax(Feng.ctxPath + url, function (data) {
    	if(data.code ==2000){
    		opt;
			Feng.success(tip+"成功!");
		}else{
			Feng.error(data.message);
		}
    }, function (data) {
        Feng.error(tip+"失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
};


/**
 * 获取选中数据
 */
loginLog.getSelIds = function(){
	var  isCheck =false;
	loginLog.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	loginLog.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*登录日志-删除(批量)*/
loginLog.multi_del=function (obj,id){
	
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/login_log/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		loginLog.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(loginLog.getSelIds()){
    	var selIds = loginLog.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
	
}

/**
 * 初始化表格的列
 * 
 */
loginLog.initColumn = function () {
    var columns = [
        {title: '', data:"id",  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '日志名称', data: 'logName'},
        {title: '用户名称', data: 'name', render: function(data, type, row, meta){
        	if(data != null && data != ''){
        		return data;
        	}else{
        		return "--";
        	}
        }},
        {title: '时间', data: 'createDate'},
        {title: '具体消息', data: 'message', render: function(data, type, row, meta){
        	if(data != null && data != ''){
        		return data;
        	}else{
        		return "--";
        	}
        }},
        {title: 'ip', data: 'ip'},
        ];
    return columns;
};

/**
 * 初始化表格参数
 */
loginLog.dataTables = function (columns) {
	    var options ={
	    		option:{
					
				},
	    		columns : columns,
	    		others : {
	    			selector : '#'+loginLog.id,
	    			url : Feng.ctxPath +"/login_log/list",
	    			param : ["logName","datemax","datemin"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
loginLog.search = function () {
	loginLog.table.draw();
   
}

$(function () {
    var defaultColunms = loginLog.initColumn();
    var options = loginLog.dataTables(defaultColunms);    
    loginLog.table = defDataTables(options);
    Feng.selectMultiRow(loginLog.id,loginLog);
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	loginLog.search();
        }
    });
});
