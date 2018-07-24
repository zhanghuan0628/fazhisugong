/**
 * 苏供法律风险
 */
var lawRisk = {
    id: "lawRiskTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/*法律风险-增加*/
lawRisk.admin_add=function (title,url,w,h){
	layer_show(title,url,w,h);
}


/**
 * 获取选中数据
 */
lawRisk.getSelIds = function(){
	var  isCheck =false;
	lawRisk.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	lawRisk.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*法律风险-删除(批量)*/
lawRisk.del_lawRisk=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_risk/delLawRisk?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		lawRisk.table.draw();
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
    if(lawRisk.getSelIds()){
    	var selIds = lawRisk.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*法律风险-上下架(批量)*/
lawRisk.push_lawRisk=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_risk/updateStatus?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		lawRisk.table.draw();
				Feng.success("操作成功");
			}else{
				Feng.error(data.message);
			}
        	$("#checkall").prop("checked", false);
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(lawRisk.getSelIds()){
    	var selIds = lawRisk.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}
/*法律风险-增加*/
lawRisk.add_lawRisk=function (title,url,w,h){
	layer_show(title,url,w,h);
}
/*法律风险-编辑*/
lawRisk.edit_lawRisk=function (title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url+"?id="+id,
	});
	layer.full(index);
}
/*法律风险-上移*/
lawRisk.pushUp=function (obj,id,sort){
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_risk/push?id="+id+"&state=up&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawRisk.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/*法律风险-下移*/
lawRisk.pushDown = function(obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_risk/push?id="+id+"&state=down&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawRisk.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/**
 * 初始化表格的列
 * 
 */
lawRisk.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '标题',width:'300px', data: 'title'},
        {title: '专业',width:'300px', data: 'name'},
        {title: '状态',width:'300px', data: 'status',render: function(data, type, row, meta){
        	if(data=='publish')return '<span class="labels labels-success radius">上架</span>';	
        	else if(data=='no_publish')return '<span class="labels labels-default radius">下架</span>';
        	else return '<span class="labels labels-default radius">未发布</span>';
        }},
        {title: '排序',width:'50px', data: 'num'},
        {title:'操作',width:'300px', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="lawRisk.edit_lawRisk(\'编辑法律风险\',\'/sg_law_risk/law_risk_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'600\',\'200\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe6df;</i>'
			+'</a> <a title="下移" href="javascript:;" onclick="lawRisk.pushUp(this,'
			+ "'"
			+row.id
			+ "'"
			+ ",'"
			+row.num
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe674;</i></a> <a title="上移" href="javascript:;" onclick="lawRisk.pushDown(this,'
			+ "'"
			+row.id
			+ "'"
			+ ",'"
			+row.num
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe679;</i></a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
lawRisk.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawRisk.id,
	    			url : Feng.ctxPath +"/sg_law_risk/law_risk_pageList",
	    			param : ["title","categoryCode"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawRisk.search = function () {
	lawRisk.table.draw();
   
}

$(function () {
    var defaultColunms = lawRisk.initColumn();
    var options = lawRisk.dataTables(defaultColunms);    
    lawRisk.table = defDataTables(options);
    Feng.selectMultiRow(lawRisk.id,lawRisk);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	lawRisk.search();
        }
    });
});
