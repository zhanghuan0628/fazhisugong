/**
 * 用户列表
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
    	Feng.error("请至少选择一条数据!");
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
sgUser.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '协同账号', data: 'loginName'},
        {title: '账户名称', data: 'userName'},
        {title:'操作',data:'loginName', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="查看" href="javascript:;" onclick="sgUser.user_detail(\'查看用户详情\',\'/sg_user/user_detail\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe695;</i>'
			+'</a> <a title="初始化密码" href="javascript:;" onclick="sgUser.updatePassword(this,'
			+ "'"
			+row.id
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe605;</i></a>'
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
	    			url : Feng.ctxPath +"/sg_user/userlist",
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

$(function () {
    var defaultColunms = sgUser.initColumn();
    var options = sgUser.dataTables(defaultColunms);    
    sgUser.table = defDataTables(options);
    Feng.selectMultiRow(sgUser.id,sgUser);
});
