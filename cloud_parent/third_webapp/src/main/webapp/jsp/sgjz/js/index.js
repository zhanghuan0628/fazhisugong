var apiUrl= location.protocol+"//"+location.host+"/third";
// var apiUrl = "http://192.168.0.126:8080/third_webapp";
var appid ="wx52c241bcd09bc71c";

//	用户微信授权、分享等api
function wxAuthorization(state){
	$.ajax({
        //url: "${base}/user/snsapi_userinfo/"+appid, //非静默授权
        url: apiUrl+"/user/snsapi_base/"+appid, //静默授权
        type: "post",
        dataType: "json",
        async:false,
        success: function(data) {
            if(data.success){
                if(data.code == "2000"){
                    var data = data.data;
                    var appId = data.appId;
                    var scope = data.scope;
                    var componentAppId = data.componentAppId;
                    var redirectUri =encodeURI("http://"+window.location.host+"/third/user/get_openid/"+appId+"/sgLogo", "UTF-8");
                    var url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUri+"&response_type=code"+"&scope="+scope+"&state="+state+"&component_appid="+componentAppId+"#wechat_redirect";
                    window.location.href=url;	
                }else{

                }
            }
        }
    });	
	
}



//分享配置
function wxShare(locationUrl,wxtitle,wximgUrl,wxdesc){
				 
    $.ajax({
        type: "POST",
        url : apiUrl+"/auth/jssdkconfig/"+appid+"?t="+new Date().getTime(),
        data: {requestUrl:location.href.split('#')[0]},
        dataType:'json',
        async:false,
        success: function(data){
            if(data.code == '2000'){
                console.log(data.data.appId);
                var appId = data.data.appId;
                var timestamp = data.data.timestamp;
                var nonceStr = data.data.nonceStr;
                var signature = data.data.signature;
    
                wx.config({
                    debug: false,  
                    appId: appId,  
                    timestamp: timestamp,  
                    nonceStr: nonceStr,  
                    signature: signature,  
                    jsApiList: ['onMenuShareTimeline',
                                'onMenuShareAppMessage',
                                'onMenuShareQQ',
                                'onMenuShareQZone']  
                });  
                wx.ready(function () {
                    wx.checkJsApi({
                        jsApiList: ['onMenuShareTimeline',
                                    'onMenuShareAppMessage',
                                    'onMenuShareQQ',
                                    'onMenuShareQZone']  , 
                        success: function(res) {
                            console.log(JSON.stringify(res));
                        }
                    });
                    //分享到朋友圈
                    wx.onMenuShareTimeline({
                        title: wxtitle, // 分享标题
                        imgUrl: wximgUrl, // 分享图标
                        desc: wxdesc, // 分享描述
                        link:locationUrl,
                        success: function () {},
                        cancel: function () {}
                    });
                    //分享给朋友
                    wx.onMenuShareAppMessage({
                        title: wxtitle, // 分享标题
                        imgUrl: wximgUrl, // 分享图标
                        desc: wxdesc, // 分享描述
                        link:locationUrl,
                        success: function () {},
                        cancel: function () {}
                    });
                    //分享到QQ
                    wx.onMenuShareQQ({
                        title: wxtitle, // 分享标题
                        imgUrl: wximgUrl, // 分享图标
                        desc: wxdesc, // 分享描述
                        link:locationUrl,
                        success: function () {},
                        cancel: function () {}
                    });
                    //分享到QQ空间
                    wx.onMenuShareQZone({
                        title: wxtitle, // 分享标题
                        imgUrl: wximgUrl, // 分享图标
                        desc: wxdesc, // 分享描述
                        link:locationUrl,
                        success: function () {},
                        cancel: function () {}
                    });
                    wx.error(function(res){
                        //alert("errorMSG:"+res);
                    });
                });
            }else{
                alert(data.message);
            }
        },
        error:function(response, status, err){
            //alert("系统异常");
        }
    }); 
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}

//授权后取值
function getAuthQueryString() {
	var name,value; 
	var str=location.href.search();
	var num=window.location.search.indexOf("%3F");
	str=window.location.search.substr(num+3) //取得所有参数   stringvar.substr(start [, length ]

	var arr=str.split("%40"); //各个参数放到数组里
	for(var i=0;i < arr.length;i++){ 
	num=arr[i].indexOf("%3D"); 
	if(num>0){ 
		name=arr[i].substring(0,num);
		value=arr[i].substr(num+3);
		this[name]=value;
		} 
    } 
}

//提示框
function toast(txt){
    if ($(".toast").length == 0) {
        var txtLen = txt.length;
        var toastCase = "<div class='toast' style='width:"+txtLen*1.4+"rem'>"+txt+"</div>";
        $("body").append(toastCase);
        setTimeout(function(){
            $(".toast").remove();
        },1000);
    }
}





function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

function isIos(){
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){  //判断iPhone|iPad|iPod|iOS
	    return true
	}
	else{
		return false; 
	}
}
/**
 * 判断是否是Android
 * @returns {Boolean}
 */
function isAndroid(){
	if (/(Android)/i.test(navigator.userAgent)){   //判断Android
	    return true
	}
	else{
		return false; 
	}
}
/**
 * 判断是否是微信
 * @returns {Boolean}
 */
function isWeChat(){ 
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
		return true; 
	}
	else{
		return false; 
	} 
}




/**
 * 设置cookie
 */
function setCookie(name, value){
  var cookieValue = encodeURI(value, "UTF-8");
  var oDate=new Date();
  //oDate.setDate(oDate.getDate()+iDay); //用来设置过期时间用的，获取当前时间加上传进来的iDay就是过期时间
  oDate.setDate(oDate.getDate()+15);
  document.cookie=name+'='+cookieValue+'; path=/;expires='+oDate;
};
/**
 * 获取cookie
 * @param name
 * @returns
 */
 function getCookie(name){
 	var strCookie=document.cookie;
 	var arrCookie=strCookie.split("; ");
 	for(var i=0;i<arrCookie.length;i++){
 	var arr=arrCookie[i].split("=");
 	if(arr[0]==name)
 		return decodeURI(arr[1], "UTF-8"); 
 	}
 	return "";
 	}

$(function(){
	
	// FastClick.attach(document.body); 
	
	// var notNeed = FastClick.notNeeded(document.body);
	// $.fn.triggerFastClick=function(){
	//     this.trigger("click");
	//         if(!notNeed){
	//         this.trigger("click");
	//     }
	// }

	

})


