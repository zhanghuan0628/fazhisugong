/**
 * 系统管理--用户管理的单例对象
 */
var SysUser = {
    id: "sysUserTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/*管理员-增加*/
SysUser.admin_add=function (title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除(单)*/
SysUser.single_del=function (obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sys_user/del?ids="+id,
			dataType: 'json',
			success: function(data){
				if(data.code ==2000){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					SysUser.table.draw();
					layer.msg(data.message, {icon: 2,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/**
 * 获取选中数据
 */
SysUser.getSelIds = function(){
	var  isCheck =false;
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
	var selIds = SysUser.seItem; 
	if(SysUser.getSelIds()){
		layer.confirm('确认要删除吗？',function(index){
			$.ajax({
				type: 'POST',
				url: Feng.ctxPath +"/sys_user/del?ids="+selIds,
				dataType: 'json',
				success: function(data){
					if(data.code ==2000){
						$(obj).parents("tr").remove();
						layer.msg('已删除!',{icon:1,time:1000});
					}else{
						layer.msg(data.message, {icon: 2,time:1000});
					}
				},
				error:function(data) {
					console.log(data.msg);
				},
			});		
		});
	}
}

/*管理员-编辑*/
SysUser.admin_edit=function (title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
SysUser.admin_stop=function (obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sys_user/stop?id="+id,
			dataType: 'json',
			success: function(data){
				if(data.code ==2000){
					SysUser.table.draw();
					layer.msg('已停用!',{icon: 5,time:1000});
				}else{
					layer.msg(data.message, {icon: 2,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*管理员-启用*/
SysUser.admin_start = function(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sys_user/start?id="+id,
			dataType: 'json',
			success: function(data){
				if(data.code ==2000){
					SysUser.table.draw();
					layer.msg('已启用!', {icon: 6,time:1000});
				}else{
					layer.msg(data.message, {icon: 2,time:1000});
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
        {title: '角色', data: 'roleId'},
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
        	msg+='<a title="编辑" href="javascript:;" onclick="SysUser.admin_edit(\'管理员编辑\',\'admin-add.html\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i>'
			+'</a> <a title="删除" href="javascript:;" onclick="SysUser.single_del(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>'
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
});
