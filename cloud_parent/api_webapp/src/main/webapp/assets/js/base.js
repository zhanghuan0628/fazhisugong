document.body.addEventListener('touchstart', function(){});//active
/*
 * 调用的接口
 * */
//配置
var apiUrl="http://ffyy.feifanxinli.com:8080/ffyy_api"//生成环境api地址
var wechatUrl="http://ffxlsleep.feifanxinli.com/ffyy"//生成环境api地址
//var apiUrl="http://192.168.0.100:8080/ffyy_api"//生成环境api地址

//接口详情
var apiCounselorDetail=apiUrl+"/api_counselor/detail"//咨询师详情


/*
 *通用的函数
 * */
//取url地址后面的参数，例：getQueryString("id")
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}