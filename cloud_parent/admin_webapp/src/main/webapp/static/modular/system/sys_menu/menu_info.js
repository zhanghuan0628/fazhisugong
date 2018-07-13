/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var MenuInfoDlg = {
	formId : "menuInfoForm", //form表单id
	table : parent.Menu.table,
	pNameZtree: null,
    infoData: {
    	id: $("#id").val(),
    	code: $("#code").val(),
    },
    //验证方式
    validate: {
	    rules: {
	    	code:{
				required:true,
				minlength:1,
				maxlength:16,
				remote : {
					url : Feng.ctxPath +"/sys_menu/check", //设置后台处理程序
					type : "post", //数据发送方式
					dataType : "json", //接受数据格式   
					data : { //要传递的数据
						"code" : function() {
							if (MenuInfoDlg.infoData.code != $("#code").val())
								return $("#code").val();
						}
					}
				},
			},
			name:{
				required:true,
				minlength:1,
				maxlength:16
			},
			pcodeName:{
				required:true,
				minlength:1,
				maxlength:16
			},
			tips:{
				maxlength:200
			}
			
		},
		messages : {
			"code" : {
				remote : "菜单编号已经存在"
			}
		}
    }
};
/**
 * 清除数据
 */
MenuInfoDlg.clearData = function () {
    this.menuInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.set = function (key, val) {
    this.menuInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
MenuInfoDlg.close = function () {
    parent.layer.close(window.parent.menu.layerIndex);
};
/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
MenuInfoDlg.onClickPName = function (e, treeId, treeNode) {
    $("#pcodeName").attr("value", MenuInfoDlg.pNameZtree.getSelectedVal());
    $("#pcode").attr("value", treeNode.id);
};
/**
 * 显示父级菜单的树
 *
 * @returns
 */
MenuInfoDlg.showMenuSelectTree = function () {
	Feng.showInputTree("pcodeName", "pNameContent");
};
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	var pNameTree = new $ZTree("pNameTree", "/sys_menu/selectMenuTreeList");
    pNameTree.bindOnClick(MenuInfoDlg.onClickPName);
    pNameTree.init();
    MenuInfoDlg.pNameZtree = pNameTree;
	if(MenuInfoDlg.infoData.id ==null ||MenuInfoDlg.infoData.id =="" ){
		Feng.initValidator(MenuInfoDlg.formId, MenuInfoDlg.validate,MenuInfoDlg.table,"/sys_menu/add"); //新增
	}else{
		Feng.initValidator(MenuInfoDlg.formId, MenuInfoDlg.validate,MenuInfoDlg.table,"/sys_menu/edit"); //编辑
	}
	
	
});