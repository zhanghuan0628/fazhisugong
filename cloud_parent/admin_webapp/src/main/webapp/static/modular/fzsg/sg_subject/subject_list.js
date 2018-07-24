/**
 * 法官题目
 */
var subject = {
    id: "subjectTable",//表格id
    seItem: [],		//选中的条目
    table: null,        //table
    layerIndex: -1
};

/**
 * 获取选中数据
 */
subject.getSelIds = function(){
	var  isCheck =false;
	subject.seItem=[];
	$('.iCheck').each(function () {
        if($(this).is(':checked')){
        	isCheck = true;
        	subject.seItem.push($(this).val());
        }
    });

    if(!isCheck) {
    	Feng.error("请至少选择一条数据!");
    }
    return isCheck;
}

/*法官题目-删除(批量)*/
subject.del_subject=function (){
	var operation = function(){
        var ajax = new $ax(Feng.ctxPath +"/sg_subject/del_subject?ids="+selIds, function (data) {
        	if(data.code ==2000){
        		subject.table.draw();
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
    if(subject.getSelIds()){
    	var selIds = subject.seItem; 
    	Feng.confirm('确认要删除吗？',operation);
    }
}

/*法官题目-增加*/
subject.add_subject=function (title,url,w,h){
	layer_show(title,url,w,h);
}
/*法官题目-编辑*/
subject.edit_subject=function (title,url,id,w,h){
	layer_show(title,Feng.ctxPath +url+"?id="+id,w,h);
}
/*法官题目-查看*/
subject.detail=function (title,url,id,name){
	var index = layer.open({
		type: 2,
		title: title,
		content: Feng.ctxPath +url+"?id="+id+"&title="+name,
	});
	layer.full(index);
}

/**
 * 初始化表格的列
 * 
 */
subject.initColumn = function () {
    var columns = [
        {title: '<input type="checkbox" name="checkall" id="checkall">', data:"id",width:'10px',  render: function(data, type, row, meta) { return '<input type="checkbox" name="checklist" value="'+data+'" class="iCheck">';}},
        {title: '题目',width:'300px', data: 'qstn'},
        {title: '选项数',width:'60px', data: 'num'},
        {title: '正确答案',width:'300px', data: 'result'},
        {title:'操作',width:'60px', render: function(data, type, row, meta){
        	var msg = "";
        	msg+='<a title="编辑" href="javascript:;" onclick="subject.edit_subject(\'编辑法官题目\',\'/sg_subject/subject_edit\','
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
subject.dataTables = function (columns) {
	    var options ={
	    		columns : columns,
	    		others : {
	    			selector : '#'+subject.id,
	    			url : Feng.ctxPath +"/sg_subject/subject_pageList",
	    			param : ["qstn"]
	    		}	
	    }
	    return options;
	};

/**
 * 搜索
 */
subject.search = function () {
	subject.table.draw();
   
}

$(function () {
    var defaultColunms = subject.initColumn();
    var options = subject.dataTables(defaultColunms);    
    subject.table = defDataTables(options);
    Feng.selectMultiRow(subject.id,subject);
    Feng.checkAll();
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	subject.search();
        }
    });
});
