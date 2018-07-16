/**
 * 系统管理--用户管理的单例对象
 */
var SysUser = {
    id: "sysUserTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
};

/*管理员-增加*/
SysUser.admin_add=function (title,url,w,h){
	layer_show(title,url,w,h);
}


SysUser.operation = function(url,tip,opt){
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

/*管理员-删除(单)*/
SysUser.single_del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sys_user/del?ids="+id, function (data) {
        	if(data.code ==2000){
        		SysUser.table.draw();
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
SysUser.getSelIds = function(){
	var  isCheck =false;
	SysUser.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	SysUser.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*管理员-删除(批量)*/
SysUser.multi_del=function (obj,id){
	
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sys_user/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		SysUser.table.draw();
				Feng.success("删除成功!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(SysUser.getSelIds()){
    	var selIds = SysUser.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
	
}

/*管理员-编辑*/
SysUser.admin_edit=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}

/*角色-增加*/
SysUser.role_add=function (title,url,w,h){
	if(SysUser.getSelIds()){
    	var selIds = SysUser.seItem; 
    	layer_show(title,Feng.ctxPath +url+"?id="+selIds,w,h);
    }
	
}

/*管理员-停用*/
SysUser.admin_stop=function (obj,id){
	var operation = function(){
        var ajax = new $ax(
         Feng.ctxPath + "/sys_user/stop?id="+id, 
         function (data) {
        	if(data.code ==2000){
        		SysUser.table.draw();
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
	
//	layer.confirm('确认要停用吗？',function(index){
//		$.ajax({
//			type: 'POST',
//			url: Feng.ctxPath +"/sys_user/stop?id="+id,
//			dataType: 'json',
//			success: function(data){
//				if(data.code ==2000){
//					SysUser.table.draw();
//					Feng.success("停用成功!");
//				}else{
//					Feng.error(data.message);
//				}
//			},
//			error:function(data) {
//				console.log(data.msg);
//			},
//		});		
//	});
}

/*管理员-启用*/
SysUser.admin_start = function(obj,id){
	var operation = function(){
        var ajax = new $ax(
         Feng.ctxPath + "/sys_user/start?id="+id, 
         function (data) {
        	if(data.code ==2000){
        		SysUser.table.draw();
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
/*初始化密码*/
SysUser.update_pwd=function (obj,id){
	layer.confirm('确认要初始化密码吗？',function(index){
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sys_user/reset?id="+id,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					SysUser.table.draw();
					Feng.success("已初始化密码!");
					layer.close(index);
				}else{
					Feng.error('初始化密码失败!');
					layer.close(index);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
/**
 * 初始化表格的列
 * 
 */
SysUser.initColumn = function () {
    var columns = [
        {title: '', data:"id",  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '账号', data: 'loginName'},
        {title: '姓名', data: 'userName'},
        {title: '性别', data: 'sex'},
        {title: '角色', data: 'roles'},
        {title: '邮箱', data: 'email'},
        {title: '电话', data: 'cellphone'},
        {title: '状态', data: 'status',render: function(data, type, row, meta){
        	if(data==1)return '<span class="label label-success radius">已启用</span>';	
        	if(data==2)return '<span class="label label-default radius">已停用</span>';
        	if(data==3)return '<span class="label label-default radius">已删除</span>';
        	else return '<span class="label label-default radius">未知状态</span>';
        }},
        {title:'操作',data:'loginName', render: function(data, type, row, meta){
        	var msg = "";
        	if(row.status==1){
        		 msg+='<td class="td-manage"><a style="text-decoration:none" onClick="SysUser.admin_stop(this,'
        				+ "'"
        				+row.id
        				+ "'"
        				+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>';
        	}else if(row.status==2){
        		 msg+='<td class="td-manage"><a style="text-decoration:none" onClick="SysUser.admin_start(this,'
        				+ "'"
        				+row.id
        				+ "'"
        				+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe615;</i></a>';
        	}	
        	msg+='<a title="编辑" href="javascript:;" onclick="SysUser.admin_edit(\'管理员编辑\',\'/sys_user/user_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i>'
			+'</a> <a title="删除" href="javascript:;" onclick="SysUser.single_del(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a> <a title="初始化密码" href="javascript:;" onclick="SysUser.update_pwd(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
SysUser.dataTables = function (columns) {
	    var options ={
	    		option:{
					
				},
	    		columns : columns,
	    		others : {
	    			selector : '#'+SysUser.id,
	    			url : Feng.ctxPath +"/sys_user/list",
	    			param : ["userName","realName"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
SysUser.search = function () {
	SysUser.table.draw();
   
}

$(function () {
    var defaultColunms = SysUser.initColumn();
    var options = SysUser.dataTables(defaultColunms);    
    SysUser.table = defDataTables(options);
    
    Feng.selectSingleRow(SysUser.id,SysUser);
    /*Feng.selectMultiRow(SysUser.id,SysUser);*/
});
