/**
 * 系统管理--用户管理的单例对象
 */
var sysRole = {
    id: "sysRoleTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
};

/*角色-增加*/
sysRole.role_add=function (title,url,w,h){
	layer_show(title,url,w,h);
}
sysRole.operation = function(url,tip,opt){
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

/*角色-删除(单)*/
sysRole.single_del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sys_role/del?ids="+id, function (data) {
        	if(data.code ==2000){
        		sysRole.table.draw();
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
sysRole.getSelIds = function(){
	var  isCheck =false;
	sysRole.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	sysRole.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*角色-删除(批量)*/
sysRole.multi_del=function (obj,id){
	
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sys_role/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		sysRole.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(sysRole.getSelIds()){
    	var selIds = sysRole.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
	
}

/*角色-编辑*/
sysRole.role_edit=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*角色-授权*/
sysRole.power_add=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*角色-停用*/
sysRole.role_stop=function (obj,id){
	var operation = function(){
        var ajax = new $ax(
         Feng.ctxPath + "/sys_role/stop?id="+id, 
         function (data) {
        	if(data.code ==2000){
        		sysRole.table.draw();
				Feng.success("停用成功!");
			}else{
				Feng.error(data.message);
			}
        }, 
        function (data) {
            Feng.error("停用失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
	Feng.confirm('确认要停用吗？',operation);
	
}

/*角色-启用*/
sysRole.role_start = function(obj,id){
	var operation = function(){
        var ajax = new $ax(
         Feng.ctxPath + "/sys_role/start?id="+id, 
         function (data) {
        	if(data.code ==2000){
        		sysRole.table.draw();
				Feng.success("启用成功!");
			}else{
				Feng.error(data.message);
			}
        }, 
        function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
	Feng.confirm('确认要启用吗？',operation);
}

/**
 * 初始化表格的列
 * 
 */
sysRole.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:"10px",render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '角色名称', data: 'name'},
        {title: '上级角色', data: 'fatherName'},
        {title: '备注', data: 'tips'},
        {title:'操作',data:'loginName', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="sysRole.role_edit(\'角色编辑\',\'/sys_role/role_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i>'
			+'</a> <a title="授权" href="javascript:;" onclick="sysRole.power_add(\'角色授权\',\'/sys_role/power_add\','
			+ "'"
			+row.id
			+ "'"
			+',\'300\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe60d;</i>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
sysRole.dataTables = function (columns) {
	    var options ={
	    		option:{
					
				},
	    		columns : columns,
	    		others : {
	    			selector : '#'+sysRole.id,
	    			url : Feng.ctxPath +"/sys_role/role_pageList",
	    			param : ["name"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
sysRole.search = function () {
	sysRole.table.draw();
   
}

$(function () {
    var defaultColunms = sysRole.initColumn();
    var options = sysRole.dataTables(defaultColunms);    
    sysRole.table = defDataTables(options);
    Feng.selectMultiRow(sysRole.id,sysRole);
});
