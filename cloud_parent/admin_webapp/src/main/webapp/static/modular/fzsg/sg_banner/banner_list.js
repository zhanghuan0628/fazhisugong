/**
 * banner
 */
var banner = {
    id: "bannerTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};
/**
 * 获取选中数据
 */
banner.getSelIds = function(){
	var  isCheck =false;
	banner.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	banner.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}
/*banner-编辑*/
banner.edit_banner=function (title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url+"?id="+id,
	});
	layer.full(index);
}
/*banner-新增*/
banner.add_banner=function (title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url,
	});
	layer.full(index);
}
/*banner-删除(批量)*/
banner.del_banner=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_banner/del?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		banner.table.draw();
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
    if(banner.getSelIds()){
    	var selIds = banner.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}
/*banner-上移*/
banner.pushUp=function (obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_banner/push?id="+id+"&state=down&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					banner.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/*banner-下移*/
banner.pushDown = function(obj,id,sort){
	console.log("id:"+id + "       sort:" + sort);
		$.ajax({
			type: 'POST',
			url: Feng.ctxPath +"/sg_banner/push?id="+id+"&state=up&sort="+sort,
			dataType: 'json',
			success: function(data){
				if(data.code =='2000'){
					banner.table.draw();
				}else{
					Feng.error(data.message);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
}

/*上下架(批量)*/
banner.push_banner=function (state){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_banner/updateStatus?ids="+selIds+"&state="+state, function (data) {
        	if(data.code ==2000){
        		banner.table.draw();
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
    if(banner.getSelIds()){
    	var selIds = banner.seItem; 
    	Feng.confirm('确认要操作吗？',operation);
    }
}

/**
 * 初始化表格的列
 * 
 */
banner.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" id = "'+data+'" onclick="Feng.ck(\''+data+'\')" name="checklist" value="'+data+'" class="iCheck">';}},           
        {title: '图片',width:'300px', data: 'bannerImg',render: function(data, type, row, meta){
        	var msg = "";
        	msg = "<img src='"+data+"' height='30px' onclick='clickThisImg(\""+data+"\")'>";
        	return msg;
        }},
        {title: '标题',width:'300px', data: 'title'},
        {title: '状态',width:'100px', data: 'status',render: function(data, type, row, meta){
        	if(data=='publish')return '<span class="labels labels-success radius">上架</span>';	
        	else if(data=='no_publish')return '<span class="labels labels-default radius">下架</span>';
        	else return '<span class="labels labels-default radius">未发布</span>';
        }},
        {title: '排序',width:'50px', data: 'sortNum'},
        {title:'操作',width:'300px',render: function(data, type, row, meta){
        	var msg = "";
    		msg+='<a title="编辑" href="javascript:;" onclick="banner.edit_banner(\'编辑详情\',\'/sg_banner/edit_banner\','
    			+ "'"
    			+row.id
    			+ "'"
    			+',\'600\',\'400\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe6df;</i>'
    			+'</a><a title="下移" href="javascript:;" onclick="banner.pushUp(this,'
    			+ "'"
    			+row.id
    			+ "'"
    			+ ",'"
    			+row.sortNum
    			+ "'"
    			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe674;</i></a> <a title="上移" href="javascript:;" onclick="banner.pushDown(this,'
    			+ "'"
    			+row.id
    			+ "'"
    			+ ",'"
    			+row.sortNum
    			+ "'"
    			+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="color:#429c84">&#xe679;</i></a>'
    			+'</td>';
            	return msg;
        }}];
    return columns;
};
function clickThisImg(img) {
	$(".img-popup-img").attr("src",img)
	$(".img-popup").removeClass("active")
}
$(document).on("click", ".img-popup", function(){
	$(".img-popup").addClass("active")
})
/**
 * 初始化表格参数
 */
banner.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+banner.id,
	    			url : Feng.ctxPath +"/sg_banner/banner_pageList",
	    			param : ["title"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
banner.search = function () {
	banner.table.draw();
   
}

$(function () {
    var defaultColunms = banner.initColumn();
    var options = banner.dataTables(defaultColunms);    
    banner.table = defDataTables(options);
    Feng.selectMultiRow(banner.id,banner);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	banner.search();
        }
    });
});
