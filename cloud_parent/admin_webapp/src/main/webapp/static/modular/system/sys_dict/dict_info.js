/**
 * 初始化字典详情对话框
 */
var DictInfoDlg = {
    count: $("#itemSize").val(),
    dictName: '',			//字典的名称
    tips:'',
    mutiString: '',		//拼接字符串内容(拼接字典条目)
    itemTemplate: $("#itemTemplate").html()
};

/**
 * item获取新的id
 */
DictInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 关闭此对话框
 */
DictInfoDlg.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
};

/**
 * 添加条目
 */
DictInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#dictItem").attr("id", this.newId());
};

/**
 * 删除item
 */
DictInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
DictInfoDlg.clearNullDom = function(){
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        if(num == '' || name == ''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */

function getJson(){
    var ms = new Array();
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        var json = {"num":num,"name":name};
        ms.push(json);
    }); 	   
	console.log("ccccccccccccccccc:"+ms);
	return ms;
}

/**
 * 提交添加字典
 */
DictInfoDlg.addSubmit = function () {
	var j = getJson();
    $.ajax({
        url:Feng.ctxPath + "/sys_dict/add",
        data:{
        	"dictName":$("#dictName").val(),
        	"tips":$("#tips").val(),
        	"dictValues":JSON.stringify(j),
        	},
        type:"post",
        dataType:"json",
        success:function(data){
            if (data.code == true) {
            	Feng.success("添加成功!");
            	parent.Dict.table.draw();
            	var index = parent.layer.getFrameIndex(window.name);
          	    parent.layer.close(index);
            } else{
                Feng.info("操作失败");
            }                           
        }
    }) 
};

/**
 * 提交修改
 */
DictInfoDlg.editSubmit = function () {
    var j = getJson();
    $.ajax({
        url:Feng.ctxPath + "/sys_dict/update",
        data:{"dictId":$("#dictId").val(),"dictName":$("#dictName").val(),"dictValues":JSON.stringify(j)},
        type:"post",
        dataType:"json",
        success:function(data){
            if (data == true) {
            	Feng.success("修改成功!");
            	parent.Dict.table.draw();
            	var index = parent.layer.getFrameIndex(window.name);
          	    parent.layer.close(index);
            } else{
                Feng.info("操作失败");
            }                           
        }
    }) 
};
