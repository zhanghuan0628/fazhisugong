/**
 * 虚拟用户列表
 */
var sgUser = {
    id: "sgUserTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 获取选中数据
 */
sgUser.getSelIds = function(){
	var  isCheck =false;
	sgUser.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	sgUser.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请选择一条数据!");
    }
    return isCheck;
}
/*查看*/
sgUser.user_detail=function (title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url+"?id="+id,
	});
	layer.full(index);
}
sgUser.add_dummy_user=function (title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*初始化密码*/
sgUser.updatePassword=function (obj,id){
	layer.confirm('确认要初始化密码吗？',function(index){
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_user/updatePassword?id="+id,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					sgUser.table.draw();
					Feng.success("已初始化密码!");
					
				}else{
					Feng.error('初始化密码失败!');
				}
				layer.close(index);
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
var id = "";
sgUser.check=function(){
	var num = 0;
	id = "";
	var idArray = $(".iCheck");
	for (var i = 0; i < idArray.length; i++) {
		if(idArray[i].checked == true){
			num++;
		}
    }
	for (var i = 0; i < idArray.length; i++) {
        if(num==1){
        	if(idArray[i].checked == true){
                id += idArray[i].value;
                return true;
            }
        }else{
        	Feng.error("请选择一条数据!");
            return false;
        }
        
    }
}
/**
 * 初始化表格的列
 * 
 */
sgUser.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="radio" name="checklists" value="'+data+'" class="iCheck" onclick="sgUser.check()">';}},
        {title: '协同账号', data: 'loginName'},
        {title: '账户名称', data: 'userName'},
        {title:'操作',data:'loginName', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="查看" href="javascript:;" onclick="sgUser.user_detail(\'查看用户详情\',\'/sg_user/user_detail\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe695;</i>'
			+'</a> <a title="初始化密码" href="javascript:;" onclick="sgUser.updatePassword(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe605;</i></a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
sgUser.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+sgUser.id,
	    			url : Feng.ctxPath +"/sg_user/dummy_user_list",
	    			param : ["userName","loginName"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
sgUser.search = function () {
	sgUser.table.draw();
}
/**
 * 保存
 */
sgUser.save = function(){
		var operation = function(){
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : Feng.ctxPath+"/sg_ask/queryDummName",
				data :{"id":id},
				success : function(result) {
					var data = result.data;
					console.log(data.userName);
					console.log(data.id);
					var userId = data.id;
					var userName = data.userName;
					parent.getValue(userId,userName);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
			console.log(id);
	    };
	    if(sgUser.check()){
	    	var selIds = sgUser.seItem; 
	    	Feng.confirm('确认要添加吗？',operation);
	    }
}
$(function () {
    var defaultColunms = sgUser.initColumn();
    var options = sgUser.dataTables(defaultColunms);    
    sgUser.table = defDataTables(options);
    Feng.selectSingleRow(sgUser.id,sgUser);
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	sgUser.search();
        }
    });
});
