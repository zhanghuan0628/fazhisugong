/**
 * 评论
 */
var sgRisk = {
    id: "sgRiskTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};
/**
 * 获取选中数据
 */
sgRisk.getSelIds = function(){
	var  isCheck =false;
	sgRisk.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	sgRisk.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}
/*查看*/
sgRisk.user_ask_detail=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/**
 * 回复
 */
sgRisk.back=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*删除(批量)*/
sgRisk.del_sgRisk=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_risk/delComment?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		sgRisk.table.draw();
				Feng.success("已删除!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(sgRisk.getSelIds()){
    	var selIds = sgRisk.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*删除(单)*/
sgRisk.del=function (obj,id){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/sg_law_risk/delComment?ids="+id, function (data) {
        	if(data.code ==2000){
        		sgRisk.table.draw();
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
/*上下架(批量)*/
sgRisk.push_lawRisk=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_risk/updateState?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		sgRisk.table.draw();
				Feng.success("操作成功");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(sgRisk.getSelIds()){
    	var selIds = sgRisk.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}
/**
 * 初始化表格的列
 * 
 */
sgRisk.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { 
        	return '<input type="checkbox" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" name="checklist" value="'+data+'" class="iCheck">';
        	}
        },
        {title: '风险文章', data: 'title'},
        {title: '评论者昵称', data: 'userName'},
        {title: '评论内容', data: 'content'},
        {title: '评论时间', data: 'createTime'},
        {title: '状态', data: 'state',render: function(data, type, row, meta){
        	if(data=='pass'){
        		return "已审核";
        	}else{
        		return "审核未通过";
        	}
        }},
        {title:'操作', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="回复" href="javascript:;" onclick="sgRisk.back(\'咨询师回复\',\'/sg_law_risk/counselor_back\','
			+ "'"
			+row.id
			+ "'"
			+',\'800\',\'400\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe692;</i></a> <a title="删除" href="javascript:;" onclick="sgRisk.del(this,'
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
sgRisk.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+sgRisk.id,
	    			url : Feng.ctxPath +"/risk_talk/law_risk_comment",
	    			param : ["userName","title"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
sgRisk.search = function () {
	sgRisk.table.draw();
   
}

$(function () {
	$("#tab-system").Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
    var defaultColunms = sgRisk.initColumn();
    var options = sgRisk.dataTables(defaultColunms);    
    sgRisk.table = defDataTables(options);
    Feng.selectMultiRow(sgRisk.id,sgRisk);
    Feng.checkAll();
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	sgRisk.search();
        }
    });
});