/**
 * 苏供法宝详情（二级）
 */
var lawMagic = {
    id: "lawMagicTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/*法宝-增加*/
lawMagic.admin_add=function (title,url,w,h){
	layer_show(title,url,w,h);
}


/**
 * 获取选中数据
 */
lawMagic.getSelIds = function(){
	var  isCheck =false;
	lawMagic.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	lawMagic.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*法宝-删除(批量)*/
lawMagic.del_lawMagic=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_magic/delLawMagic?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		lawMagic.table.draw();
				Feng.success("已删除!");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(lawMagic.getSelIds()){
    	var selIds = lawMagic.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*法宝-上下架(批量)*/
lawMagic.push_lawMagic=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_law_magic/updateStatus?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		lawMagic.table.draw();
				Feng.success("操作成功");
			}else{
				Feng.error(data.message);
			}
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    };
    if(lawMagic.getSelIds()){
    	var selIds = lawMagic.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}
/*法宝-增加*/
lawMagic.add_lawMagic=function (title,url,w,h){
	layer_show(title,url+"?id="+$("#categoryCode").val(),w,h);
}
/*法宝-编辑*/
lawMagic.edit_lawMagic=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*法宝-查看*/
lawMagic.detail=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*法宝-上移*/
lawMagic.pushUp=function (obj,id,sort,code){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_magic/push?id="+id+"&state=up&sort="+sort+"&code="+code,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawMagic.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/*法宝-下移*/
lawMagic.pushDown = function(obj,id,sort,code){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_law_magic/push?id="+id+"&state=down&sort="+sort+"&code="+code,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					lawMagic.table.draw();
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
lawMagic.initColumn = function () {
    var columns = [
        {title: '', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '标题',width:'300px', data: 'title'},
        {title: '状态',width:'300px', data: 'status',render: function(data, type, row, meta){
        	if(data=='publish')return '<span class="label label-success radius">上架</span>';	
        	else if(data=='no_publish')return '<span class="label label-default radius">下架</span>';
        	else return '<span class="label label-default radius">未发布</span>';
        }},
        {title: '排序',width:'50px', data: 'num'},
        {title:'操作',width:'300px', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="lawMagic.edit_lawMagic(\'编辑法宝二级目录\',\'/sg_law_magic/law_magic_detail_edit\','
			+ "'"
			+row.id
			+ "'"
			+',\'1000\',\'500\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i>'
			+'</a> <a title="上移" href="javascript:;" onclick="lawMagic.pushUp(this,'
			+ "'"
			+row.id
			+ "'"
			+ ",'"
			+row.num
			+ "'"
			+ ",'"
			+row.categoryCode
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe679;</i></a> <a title="下移" href="javascript:;" onclick="lawMagic.pushDown(this,'
			+ "'"
			+row.id
			+ "'"
			+ ",'"
			+row.num
			+ "'"
			+ ",'"
			+row.categoryCode
			+ "'"
			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe674;</i></a>'
			+'</td>';
        	return msg;
        }}];
    return columns;
};

/**
 * 初始化表格参数
 */
lawMagic.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+lawMagic.id,
	    			url : Feng.ctxPath +"/sg_law_magic/lawMagicList",
	    			param : ["title","categoryCode"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
lawMagic.search = function () {
	lawMagic.table.draw();
   
}

$(function () {
	$("#categoryCode").change(function(){
		lawMagic.search();
	})
	
    var defaultColunms = lawMagic.initColumn();
    var options = lawMagic.dataTables(defaultColunms);    
    lawMagic.table = defDataTables(options);
    Feng.selectMultiRow(lawMagic.id,lawMagic);
    
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	lawMagic.search();
        }
    });
});
