/**
 * 苏供法治讲堂
 */
var lawHall = {
    id: "lawHallTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};



/**
 * 获取选中数据
 */
lawHall.getSelIds = function(){
	var  isCheck =false;
	lawHall.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	lawHall.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*法治讲堂-删除(批量)*/
lawHall.del_lawHall=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_hall/del_law_Hall?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		lawHall.table.draw();
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
    if(lawHall.getSelIds()){
    	var selIds = lawHall.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*法治讲堂-上下架(批量)*/
lawHall.push_lawHall=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_hall/updateStatus?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		lawHall.table.draw();
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
    if(lawHall.getSelIds()){
    	var selIds = lawHall.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}
/*法治讲堂-增加*/
lawHall.add_lawHall=function (title,url,w,h){
	layer_show(title,url,w,h);
}
/*法治讲堂-编辑*/
lawHall.edit_lawHall=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*法治讲堂-查看*/
lawHall.detail=function (title,url,id,name){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url+"?id="+id+"&title="+name,
	});
	layer.full(index);
}
/*法治讲堂-上移*/
lawHall.pushUp=function (obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_hall/push?id="+id+"&state=up&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawHall.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/*法治讲堂-下移*/
lawHall.pushDown = function(obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_hall/push?id="+id+"&state=down&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawHall.table.draw();
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
lawHall.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '标题',width:'300px', data: 'title'},
        {title: '发布时间',width:'200px', data: 'createTime'},
        {title: '类型',width:'100px', data: 'type',render: function(data, type, row, meta){
        	if(data=='text'){
        		return "图文";
        	}else{
        		return "视频";
        	}
        }},
        {title: '状态',width:'100px', data: 'status',render: function(data, type, row, meta){
        	if(data=='publish')return '<span class="labels labels-success radius">上架</span>';	
        	else if(data=='no_publish')return '<span class="labels labels-default radius">下架</span>';
        	else return '<span class="labels labels-default radius">未发布</span>';
        }},
        {title: '收藏人数',width:'100px', data: 'count'},
        {title:'操作',width:'100px', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="lawHall.edit_lawHall(\'编辑法治讲堂\',\'/sg_law_hall/law_hall_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'1000\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe6df;</i>'
			+'</a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
lawHall.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawHall.id,
	    			url : Feng.ctxPath +"/sg_law_hall/law_hall_List",
	    			param : ["title","categoryCode"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawHall.search = function () {
	lawHall.table.draw();
   
}

$(function () {
    var defaultColunms = lawHall.initColumn();
    var options = lawHall.dataTables(defaultColunms);    
    lawHall.table = defDataTables(options);
    Feng.selectMultiRow(lawHall.id,lawHall);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	lawHall.search();
        }
    });
});
