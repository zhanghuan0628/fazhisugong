$(function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    $("#btn_close").bind("click", function () {
        parent.layer.close(index);
    });

    $("#btn_save").bind("click", function () {
        var ids = Feng.zTreeCheckedNodes("zTree");
        var ajax = new $ax(Feng.ctxPath + "/sys_role/setAuthority", function (data) {
            Feng.success("分配角色成功!");
            parent.layer.close(index);
        }, function (data) {
            Feng.error("分配角色失败!"
                + data.responseJSON.message + "!");
        });
        ajax.set("roleId", $("#roleId").val());
        ajax.set("ids", ids);
        ajax.start();
    });

    initZtree();
});

function initZtree() {
	console.log("sssssssssssssssssssssss:"+0);
    var setting = {
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var ztree = new $ZTree("zTree", "/sys_menu/menuTreeListByRoleId?roleId="+$("#roleId").val());
    ztree.setSettings(setting);
    ztree.init();
}
