$(function () {

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

        $("#btn_close").bind("click", function () {
            parent.layer.close(index);
        });

        $("#btn_save").bind("click", function () {
            var ids = Feng.zTreeCheckedNodes("zTree");
            var ajax = new $ax(Feng.ctxPath + "/sys_user/setRole", function (data) {
                Feng.success("分配角色成功!");
                parent.layer.close(index);
            }, function (data) {
                Feng.error("分配角色失败!" + data.responseJSON.message + "!");
            });
            ajax.set("roleIds", ids);
            ajax.set("userId", $("#userId").val());
            ajax.start();
        });

        initZtree();
    });

    function initZtree() {
        var setting = {
            check: {
                enable: true,
                chkboxType: {
                    "Y": "",
                    "N": ""
                }
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var ztree = new $ZTree("zTree", "/sys_role/roleTreeListByUserId?userId="+$("#userId").val());
        ztree.setSettings(setting);
        ztree.init();
    }