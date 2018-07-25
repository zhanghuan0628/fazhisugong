/**
 * 业务日志
 */
var operationLog = {
    id: "operationLogTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
};


operationLog.operation = function(url,tip,opt){
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
operationLog.getSelIds = function(){
	var  isCheck =false;
	operationLog.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	operationLog.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*登录日志-删除(批量)*/
operationLog.multi_del=function (obj,id){
	
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/operation_log/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		operationLog.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        	$("#checkall").prop("checked", false);
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(operationLog.getSelIds()){
    	var selIds = operationLog.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
	
}

/**
 * 初始化表格的列
 * 
 */
operationLog.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id", width:"10px", render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" value="'+data+'" class="iCheck">';}},
        {title: '日志名称',width:"100px", data: 'logName'},
        {title: '用户名称',width:"100px", data: 'loginName', render: function(data, type, row, meta){
        	if(data != null && data != ''){
        		return data;
        	}else{
        		return "--";
        	}
        }},
        {title: '类名',width:"200px", data: 'className'},
        {title: '方法名',width:"100px", data: 'methodName'},
        {title: '时间',width:"100px", data: 'createDate'},
        {title: '具体消息', width:"400px",data: 'message', render: function(data, type, row, meta){
        	if(data != null && data != ''){
        		return data;
        	}else{
        		return "--";
        	}
        }},
        ];
    return columns;
};

/**
 * 初始化表格参数
 */
operationLog.dataTables = function (columns) {
	    var options ={
	    		option:{
					
				},
	    		columns : columns,
	    		others : {
	    			selector : '#'+operationLog.id,
	    			url : Feng.ctxPath +"/operation_log/list",
	    			param : ["logName","datemax","datemin"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
operationLog.search = function () {
	operationLog.table.draw();
   
}

$(function () {
    var defaultColunms = operationLog.initColumn();
    var options = operationLog.dataTables(defaultColunms);    
    operationLog.table = defDataTables(options);
    Feng.selectMultiRow(operationLog.id,operationLog);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	operationLog.search();
        }
    });
});
