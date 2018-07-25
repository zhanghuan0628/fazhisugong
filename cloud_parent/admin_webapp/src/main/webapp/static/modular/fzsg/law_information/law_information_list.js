/**
 * 苏供法治动态
 */
var lawInformation = {
    id: "lawInformationTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};



/**
 * 获取选中数据
 */
lawInformation.getSelIds = function(){
	var  isCheck =false;
	lawInformation.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	lawInformation.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*法治动态-删除(批量)*/
lawInformation.del_lawInformation=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_information/del_law_information?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		lawInformation.table.draw();
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
    if(lawInformation.getSelIds()){
    	var selIds = lawInformation.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*法治动态-上下架(批量)*/
lawInformation.push_lawInformation=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_information/updateStatus?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		lawInformation.table.draw();
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
    if(lawInformation.getSelIds()){
    	var selIds = lawInformation.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}
/*法治动态-增加*/
lawInformation.add_lawInformation=function (title,url,w,h){
	layer_show(title,url,w,h);
}
/*法治动态-编辑*/
lawInformation.edit_lawInformation=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*法治动态-查看*/
lawInformation.detail=function (title,url,id,name){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url+"?id="+id+"&title="+name,
	});
	layer.full(index);
}
/*法治动态-上移*/
lawInformation.pushUp=function (obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_information/push?id="+id+"&state=up&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawInformation.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/*法治动态-下移*/
lawInformation.pushDown = function(obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_information/push?id="+id+"&state=down&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawInformation.table.draw();
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
lawInformation.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { 
        	return '<input type="checkbox" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" name="checklist" value="'+data+'" class="iCheck">';
        	}
        },
        {title: '标题',width:'300px', data: 'title'},
        {title: '发布时间',width:'100px', data: 'createTime'},
        {title: '状态',width:'100px', data: 'status',render: function(data, type, row, meta){
        	if(data=='publish')return '<span class="labels labels-success radius">上架</span>';	
        	else if(data=='no_publish')return '<span class="labels labels-default radius">下架</span>';
        	else return '<span class="labels labels-default radius">未发布</span>';
        }},
        {title: '收藏人数',width:'100px', data: 'count'},
        {title:'操作',width:'100px', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="lawInformation.edit_lawInformation(\'编辑法治动态\',\'/sg_law_information/law_information_edit\','
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
lawInformation.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawInformation.id,
	    			url : Feng.ctxPath +"/sg_law_information/law_information_pageList",
	    			param : ["title","categoryCode"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawInformation.search = function () {
	lawInformation.table.draw();
   
}

$(function () {
    var defaultColunms = lawInformation.initColumn();
    var options = lawInformation.dataTables(defaultColunms);    
    lawInformation.table = defDataTables(options);
    Feng.selectMultiRow(lawInformation.id,lawInformation);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	lawInformation.search();
        }
    });
});
