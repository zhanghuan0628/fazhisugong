/*常量*/
var CONSTANT = {
	DATA_TABLES : {
		DEFAULT_OPTION : { // DataTables初始化选项
			language : {
				"sProcessing" : "处理中...",
				"sLengthMenu" : "每页 _MENU_ 项",
				"sZeroRecords" : "没有匹配结果",
				"sInfo" : "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
				"sInfoEmpty" : "当前显示第 0 至 0 项，共 0 项",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索:",
				"sUrl" : "",
				"sEmptyTable" : "表中数据为空",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "<<",
					"sPrevious" : "<",
					"sNext" : ">",
					"sLast" : ">>",
					"sJump" : "跳转"
				},
				"oAria" : {
					"sSortAscending" : ": 以升序排列此列",
					"sSortDescending" : ": 以降序排列此列"
				}
			},
			autoWidth : false, // 禁用自动调整列宽
			stripeClasses : [ "odd", "even" ],// 为奇偶行加上样式，兼容不支持CSS伪类的场合
			ordering : false, // 取消默认排序查询
			processing : false, // 隐藏加载提示,自行处理
			serverSide : true, // 启用服务器端分页
			searching : false, // 禁用原生搜索
			lengthChange : false, // 改变每页显示数据数量
			displayLength : 20
		},
		COLUMN : {
			CHECKBOX : { // 复选框单元格
				className : "td-checkbox",
				orderable : false,
				width : "30px",
				data : "id",
				render : function(data, type, row, meta) {
					return '<input type="checkbox" name="checklist" value="'
							+ data + '" class="iCheck">';
				}
			}
		},
		RENDER : { // 常用render可以抽取出来，如日期时间、头像等
			ELLIPSIS : function(data, type, row, meta) {
				data = data || "";
				return '<span  data-toggle="tooltip" data-placement="top" title="'
						+ data + '">' + data + '</span>';
			}
		}
	},
	OTHERS : {
		selector : '#dataTable'
	}
};

var selector;

/**
 * pageOptions参数说明 columns : 显示字段 others ：自定义参数 selector ：查询器id url : 查询方法 param
 * ：查询条件
 */
function defDataTables(pageOptions) {
	var emp = {};
	$.extend(emp, CONSTANT.DATA_TABLES.DEFAULT_OPTION, pageOptions.option);

	var others = {};
	$.extend(others, CONSTANT.OTHERS, pageOptions.others);

	var columns = {};
	columns = pageOptions.columns;
	this.opts = emp;
	this.columns = columns;
	this.others = others;
	var dt = this;
	selector = dt.others.selector; // 选择器

	var oTable = $(selector).dataTable($.extend({}, dt.opts, {
		ajax : function(data, callback, settings) {// ajax配置为function,手动调用异步查询
			// 封装请求参数
			// alert(JSON.stringify(dt.others.param));
			var param = queryCondition(dt.others.param);
			// alert(eval("(" + param + ")"));
			var objectParm = "";
			if (param != -1) {
				var args = param.split(",");
				for (var i = 0; i < args.length; i++) {
					objectParm = eval("(" + args[i] + ")");
					data = $.extend(true, data, objectParm);
				}

			}
			// var objectParm =eval("(" + param + ")");
			// alert(JSON.stringify($.extend(true,data,objectParm)));
			console.log(JSON.stringify(data))
			$.ajax({
				type : "GET",
				url : dt.others.url,
				cache : false, // 禁用缓存
				data : data, // 传入已封装的参数
				dataType : "json",
				success : function(result) {
					// 调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
					// 此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕

					callback(result.data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("查询失败");
				}
			});
		},
		columns : dt.columns,
		createdRow : function(row, data, index) {
			// 行渲染回调,在这里可以对该行dom元素进行任何操作
			// 给当前行加样式
			if (data.role) {
				$(row).addClass("info");
			}
		}
	})).api();// 此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

	return oTable;
}

function queryCondition(paramArray) {

	// var prarmMap=new Map();
	var param = "";
	if (paramArray == "" || paramArray.length == 0) {
		return -1;
	}
	for (var i = 1; i <= paramArray.length; i++) {
		param += "{'"
		param += paramArray[i - 1];
		param += "':'";
		// param +=$("#"+paramArray[i-1]).val();
		param += $("[name='" + paramArray[i - 1] + "']").val();
		param += "'";
		param += "}";
		if (i < paramArray.length) {

			param += ",";
		}
	}
	return param;
}
