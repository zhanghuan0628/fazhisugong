/**
 * 字典项管理
 */
var Dict = {
    id: "dictTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
};

/*字典项-增加*/
Dict.openAddDict=function (){
	var index = layer.open({
        type: 2,
        title: '添加字典',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys_dict/dict_add'
    });
    this.layerIndex = index;
}


Dict.operation = function(url,tip,opt){
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

/*字典项-删除(单)*/
Dict.single_del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sys_dict/del?ids="+id, function (data) {
        	if(data.code ==2000){
        		Dict.table.draw();
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
 * 获取选中数据
 */
Dict.getSelIds = function(){
	var  isCheck =false;
	Dict.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	Dict.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*字典项-删除(批量)*/
Dict.multi_del=function (obj,id){
	
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sys_dict/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		Dict.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(Dict.getSelIds()){
    	var selIds = Dict.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
	
}

/*字典项-编辑*/
Dict.dict_edit=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}

/**
 * 初始化表格的列
 * 
 */
Dict.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:"10px",  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '名称', data: 'name'},
        {title: '详情', data: 'dictName'},
        {title: '备注', data: 'tips',render: function(data, type, row, meta){
        	if(data != null && data != ''){
        		return data;
        	}else{
        		return "--";
        	}
        }},
        {title:'操作', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="Dict.dict_edit(\'字典项编辑\',\'/sys_dict/dict_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i>'
			+'</a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
Dict.dataTables = function (columns) {
	    var options ={
	    		option:{
					
				},
	    		columns : columns,
	    		others : {
	    			selector : '#'+Dict.id,
	    			url : Feng.ctxPath +"/sys_dict/list",
	    			param : ["name"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
Dict.search = function () {
	Dict.table.draw();
   
}

$(function () {
    var defaultColunms = Dict.initColumn();
    var options = Dict.dataTables(defaultColunms);    
    Dict.table = defDataTables(options);
    Feng.selectMultiRow(Dict.id,Dict);
});
