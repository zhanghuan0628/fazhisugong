var Feng = {
    ctxPath: "",
    addCtx: function (ctx) {
        if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
    },
    confirm: function (tip, ensure) {//询问框
        parent.layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            parent.layer.close(index);
        }, function (index) {
            parent.layer.close(index);
        });
    },
    log: function (info) {
        console.log(info);
    },
    alert: function (info, iconIndex) {
        parent.layer.msg(info, {
            icon: iconIndex
        });
    },
    info: function (info) {
        Feng.alert(info, 0);
    },
    success: function (info) {
        Feng.alert(info, 1);
    },
    error: function (info) {
        Feng.alert(info, 2);
    },
    infoDetail: function (title, info) {
        var display = "";
        if (typeof info == "string") {
            display = info;
        } else {
            if (info instanceof Array) {
                for (var x in info) {
                    display = display + info[x] + "<br/>";
                }
            } else {
                display = info;
            }
        }
        parent.layer.open({
            title: title,
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['950px', '600px'], //宽高
            content: '<div style="padding: 20px;">' + display + '</div>'
        });
    },
    writeObj: function (obj) {
        var description = "";
        for (var i in obj) {
            var property = obj[i];
            description += i + " = " + property + ",";
        }
        layer.alert(description, {
            skin: 'layui-layer-molv',
            closeBtn: 0
        });
    },
    showInputTree: function (inputId, inputTreeContentId, leftOffset, rightOffset) {
        var onBodyDown = function (event) {
            if (!(event.target.id == "menuBtn" || event.target.id == inputTreeContentId || $(event.target).parents("#" + inputTreeContentId).length > 0)) {
                $("#" + inputTreeContentId).fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
            }
        };

        if(leftOffset == undefined && rightOffset == undefined){
            var inputDiv = $("#" + inputId);
            var inputDivOffset = $("#" + inputId).offset();
            $("#" + inputTreeContentId).css({
                left: inputDivOffset.left + "px",
                top: inputDivOffset.top + inputDiv.outerHeight() + "px"
            }).slideDown("fast");
        }else{
            $("#" + inputTreeContentId).css({
                left: leftOffset + "px",
                top: rightOffset + "px"
            }).slideDown("fast");
        }

        $("body").bind("mousedown", onBodyDown);
    },
    baseAjax: function (url, tip) {
        var ajax = new $ax(Feng.ctxPath + url, function (data) {
            Feng.success(tip + "成功!");
        }, function (data) {
            Feng.error(tip + "失败!" + data.responseJSON.message + "!");
        });
        return ajax;
    },
    changeAjax: function (url) {
        return Feng.baseAjax(url, "修改");
    },
    zTreeCheckedNodes: function (zTreeId) {
        var zTree = $.fn.zTree.getZTreeObj(zTreeId);
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        return ids.substring(1);
    },
    eventParseObject: function (event) {//获取点击事件的源对象
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        return $(obj);
    },
    sessionTimeoutRegistry: function () {
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            complete: function (XMLHttpRequest, textStatus) {
                //通过XMLHttpRequest取得响应头，sessionstatus，
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                if (sessionstatus == "timeout") {
                    //如果超时就处理 ，指定要跳转的页面
                    window.location = Feng.ctxPath + "/global/sessionError";
                }
            }
        });
    },
    initValidator: function(formId,validate,table,url){
        $('#' + formId).validate({
    		rules:validate.rules,
    		messages :validate.messages,
    		onkeyup:false,
    		focusCleanup:true,
    		success:"valid",
    		submitHandler:function(form){
    			var options = {
    					dataType : 'json',
    					type : 'POST',
    					url: Feng.ctxPath +url,
    					async : false,
    					beforeSubmit : function() {
    						
    					},
    					success : function(data) {
    						if (data.code == 2000) {
    							Feng.success("添加成功!");
    							var index = parent.layer.getFrameIndex(window.name);
    							console.log("fffffffffffffffffffffff:"+formId);
    							if(formId!='userInfoForm'){
    								if(formId=='menuInfoForm'){
        								table.refresh();
        							}else{
        								table.draw();
        							}
    							}
    							parent.layer.close(index);
    						} else if(data.code ==6002){
    							Feng.error(data.message);
    						}
    					},
    					error : function(msg) {
    							Feng.error('系统异常，请联系管理员!');
    					},
    				};
    				$(form).ajaxSubmit(options);
    		}
    	});
    },
    underLineToCamel: function (str) {
        var strArr = str.split('_');
        for (var i = 1; i < strArr.length; i++) {
            strArr[i] = strArr[i].charAt(0).toUpperCase() + strArr[i].substring(1);
        }
        var result = strArr.join('');
        return result.charAt(0).toUpperCase() + result.substring(1);
    },
    //table行单选
	selectSingleRow : function(id,tab) {
		
	    $('#'+id).on('click', 'tr', function () {
	    	
	    	
	    	var check = $(this).find(".iCheck").prop("checked");
	        if(check && check==true){
	        	 //先全部取消
		    	 $(".iCheck").prop("checked", false);
	             $(this).find('.iCheck').prop("checked",false);
	        }else{
	        	 //先全部取消
		    	 $(".iCheck").prop("checked", false);
	             $(this).find('.iCheck').prop("checked",true);
	        }
	        if ($(this).hasClass('selected')&&check ) {
	           $(this).removeClass('selected');
	        } else {
	        	if(id!="menuTable"){
	        		tab.table.$('tr.selected').removeClass('selected');
	        	}else{
	        		$('tr.selected').removeClass('selected');
	        	}
	            $(this).addClass('selected'); 
	        }
	    })
	},
	 //table行多选
	selectMultiRow : function(id) {
	    $('#'+id).on('click', 'tr', function () {
	    	var check = $(this).find(".iCheck").prop("checked");
	        if(check && check==true){
	             $(this).find('.iCheck').prop("checked",false);
	             $(this).removeClass('selected');
	             $("#checkall").prop("checked", false);
	        }else{
	             $(this).find('.iCheck').prop("checked",true);
	             $(this).addClass('selected'); 
	        }
	    })
	    
	},
	ck : function(id){
		var check = $("#"+id).prop("checked");
	    if(check && check==true){
	    	$("#"+id).prop("checked",false);
	    }else{
	    	$("#"+id).prop("checked",true);
	    }
	},
	//复选框全选/取消全选
	checkAll: function(){
		$("#checkall").click(function () {
	           var check = $(this).prop("checked");
	           $(".iCheck").prop("checked", check);
	           if(check==true){
	        	   $("tr").addClass('selected'); 
	           }else{
	        	   $("tr").removeClass('selected');
	           }
	     });
	}
	     
};
