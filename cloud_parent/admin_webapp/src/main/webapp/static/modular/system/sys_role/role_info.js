/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var RolInfoDlg = {
	formId : "roleInfoForm", //form表单id
	table : parent.sysRole.table,
	pNameZtree: null,
    infoData: {
    	id: $("#id").val(),
    	name: $("#name").val(),
    },
    //验证方式
    validate: {
	    rules: {
			name:{
				required:true,
				minlength:2,
				maxlength:16,
				remote : {
					url : Feng.ctxPath +"/sys_role/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"name" : function() {
							if (RolInfoDlg.infoData.name != $("#name").val())
								return $("#name").val();
						}
					}
				},
			},
			fName:{
				required:true,
				minlength:2,
				maxlength:16
			},
			tips:{
				maxlength:200
			}
			
		},
		messages : {
			"name" : {
				remote : "角色名称已经存在"
			}
		}
    }
};
/**
 * 清除数据
 */
RolInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.set = function (key, val) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RolInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};
/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickPName = function (e, treeId, treeNode) {
    $("#fName").attr("value", RolInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
    $("#pNameContent").hide();
};
/**
 * 显示父级菜单的树
 *
 * @returns
 */
RolInfoDlg.showPNameSelectTree = function () {
	Feng.showInputTree("fName", "pNameContent");
};
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	var pNameTree = new $ZTree("pNameTree", "/sys_role/roleTreeList");
    pNameTree.bindOnClick(RolInfoDlg.onClickPName);
    pNameTree.init();
    RolInfoDlg.pNameZtree = pNameTree;
	if(RolInfoDlg.infoData.id ==null ||RolInfoDlg.infoData.id =="" ){
		Feng.initValidator(RolInfoDlg.formId, RolInfoDlg.validate,RolInfoDlg.table,"/sys_role/add"); //新增
	}else{
		Feng.initValidator(RolInfoDlg.formId, RolInfoDlg.validate,RolInfoDlg.table,"/sys_role/edit"); //编辑
	}
	
	
});