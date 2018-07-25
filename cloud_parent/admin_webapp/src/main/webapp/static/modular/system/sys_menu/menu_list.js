/**
 * 菜单管理的单例
 */
var sysMenu = {
    id: "menuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
sysMenu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', align: 'center', valign: 'middle', sortable: true, width: '8%'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '17%'},
        {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true, width: '12%'},
        {title: '菜单父编号', field: 'pcode', align: 'center', valign: 'middle', sortable: true},
        {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true, width: '17%'},
        {title: '排序', field: 'num', align: 'center', valign: 'middle', sortable: true,width: '8%'},
        {title: '层级', field: 'levels', align: 'center', valign: 'middle', sortable: true,width: '8%'},
        {title: '是否是菜单', field: 'menuName',width: '8%'},
        {title: '状态', field: 'state',width: '8%'}]
    return columns;
};


/**
 * 检查是否选中
 */
sysMenu.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	sysMenu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
sysMenu.openAddMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['830px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys_menu/menu_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
sysMenu.openChangeMenu = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys_menu/menu_edit?menuId=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
sysMenu.delMenu = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sys_menu/remove", function (data) {
                Feng.success("删除成功!");
                sysMenu.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("menuId", sysMenu.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该菜单?", operation);
    }
};

/**
 * 搜索
 */
sysMenu.search = function () {
    var queryData = {};

    queryData['name'] = $("#menuName").val();
    queryData['levels'] = $("#levels").val();

    sysMenu.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = sysMenu.initColumn();
    var table = new BSTreeTable(sysMenu.id, "/sys_menu/menu_pageList", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("pcode");
    table.setExpandAll(true);
    table.init();
    sysMenu.table = table;
    Feng.selectSingleRow(sysMenu.id,sysMenu);
    $('.enter').bind('keypress',function(event){//监听sim卡回车事件
        if(event.keyCode == "13")    
        {  
        	sysMenu.search();
        }
    });
});
