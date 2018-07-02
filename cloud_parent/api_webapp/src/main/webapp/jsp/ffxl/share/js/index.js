var apiUrl = location.protocol+"//"+window.location.host+"/ffyy_api";

//	用户微信授权、分享等api
//function wxAuthorization(state){
//	$.ajax({
//      url: apiUrl+"/auth/get_user_scope",
//      type: "post",
//      data: {},
//      dataType: "json",
//      async:false,
//      success: function(data) {
//      	if(data.success){
//              if(data.code == "2000"){
//              	var data = data.data;
//              	console.log("获取appid成功");
//					//授权
//					var appId = data.appId;
//					var scope = data.scope;
////					var scope = "snsapi_userinfo";
////					var redirectUri =encodeURI(apiUrl+"/auth/ffxl_user", "UTF-8");
//					var redirectUri =encodeURI("http://fxfapp.feifanxinli.com/ffyy_api/auth/ffxl_user", "UTF-8");
//					var url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUri+"&response_type=code"+"&scope="+scope+"&state="+state+"#wechat_redirect";
//					window.location.href=url;	
//              }else{
//              	
//              }
//          }
//      }
//  });
//}


function ffxlwxAuthorization(state){
	$.ajax({
        url: apiUrl+"/auth/get_user_scope",
        type: "post",
        data: {},
        dataType: "json",
        async:false,
        success: function(data) {
        	if(data.success){
                if(data.code == "2000"){
                	var data = data.data;
                	console.log("获取appid成功");
					//授权
					var appId = data.appId;
					var scope = data.scope;
//					var scope = "snsapi_userinfo";
					var redirectUri =encodeURI(apiUrl+"/auth/ffxl_user", "UTF-8");

                    // var url ="http://pre-api.feifanxinli.com/ffyy_api/get-weixin-code.html?appid="+appId+"&scope="+scope+"&state="+state+"&redirect_uri="+redirectUri;
					var url ="http://fxfapp.feifanxinli.com/ffyy_api/get-weixin-code.html?appid="+appId+"&scope="+scope+"&state="+state+"&redirect_uri="+redirectUri;
					window.location.href=url;	
                }else{
                	
                }
            }
        }
    });
}


//			微信分享
function wxShare(locationUrl,wxtitle,wximgUrl,wxdesc){
	 //分享配置
    $.ajax({
        type: "POST",
        url : apiUrl+"/jssdk/get_config_param",
        data: {requesUrl:location.href.split('#')[0]},
        dataType:'json',
        async:false,
        success: function(data){
            if(data.code == '2000'){
                console.log(data.data.appId);
                var appId = data.data.appId;
                var timestamp = data.data.timestamp;
                var nonceStr = data.data.nonceStr;
                var signature = data.data.signature;
    			console.log(appId)
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


function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}


var chnNumChar = ["零","一","二","三","四","五","六","七","八","九"];
var chnUnitSection = ["","万","亿","万亿","亿亿"];
var chnUnitChar = ["","十","百","千"];

function SectionToChinese(section){
    var strIns = '', chnStr = '';
    var unitPos = 0;
    var zero = true;
    while(section > 0){
        var v = section % 10;
        if(v === 0){
            if(!zero){
                zero = true;
                chnStr = chnNumChar[v] + chnStr;
            }
        }else{
            zero = false;
            strIns = chnNumChar[v];
            strIns += chnUnitChar[unitPos];
            if (v == 1 && strIns == "一十") {
                strIns = "十";
            }
            chnStr = strIns + chnStr;
        }
        unitPos++;
        section = Math.floor(section / 10);
    }
    return chnStr;
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
 
function toast(cont){
	if (typeof($(".toast")[0])!="undefined") {
		return false;
	}
	$("body").append("<p class='toast'>"+cont+"</p>");
	setTimeout(function(){
		$(".toast").remove();
	},1000)
}

//function getAuthQueryString(name) {
//	var reg = new RegExp("(^|[%40])" + name + "%3D([^%40]*)(%40|$)", "i"); 
//	var pos = window.location.search.indexOf("%3F");
//	var r = window.location.search.substr(pos+3).match(reg); 
//	if (r != null) return unescape(r[2]); return null; 
//}

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

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}


function getQueryStringAuth(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var str = window.location.search.substr(1);
	var num = str.indexOf("?"); 
	var r = str.substr(num+1).match(reg);
	if (r != null) return unescape(r[2]); return null; 
}
//判断是否是IOS
function isIos(){
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){  //判断iPhone|iPad|iPod|iOS
	    return true
	}
	else{
		return false; 
	}
}
//判断是否是Android
function isAndroid(){
	if (/(Android)/i.test(navigator.userAgent)){   //判断Android
	    return true
	}
	else{
		return false; 
	}
}
//判断是否是微信
function isWeChat(){ 
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
		return true; 
	}
	else{
		return false; 
	} 
}
 function isQQ(){ 
      var ua = window.navigator.userAgent.toLowerCase(); 
      if(ua.match(/QQ/i) == 'qq'){ 
          return true; 
      }
      else{
          return false; 
      } 
  }
 function isWeibo(){ 
       var ua = window.navigator.userAgent.toLowerCase(); 
       if(ua.match(/WeiBo/i) == 'qq'){ 
           return true; 
       }
       else{
           return false; 
       } 
   }
 function more(){
 	if (isIos()) {
   		window.location.href="FFYYalipayUrl://";
   		setTimeout(function(){
   			window.location.href="https://itunes.apple.com/us/app/fei-fan-xin-li/id1193113613"
   		},2000)
 	} else if (isAndroid()) {
 		window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.feifanxinli#opened"
 	}
//	 if(isWeChat()){
//			window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.feifanxinli#opened"
//		}
// 	else{
// 		if(isIos()){
//				window.location.href="https://itunes.apple.com/us/app/fei-fan-xin-li/id1193113613"
//			}
//		    else if(isAndroid()){
//				window.location.href ="http://ffyy.feifanxinli.com/assets/app/ffxl-1.1.apk"
//			}
//			else{
//				window.location.href ="http://ffyy.feifanxinli.com/assets/app/ffxl-1.1.apk"
//			}
// 	}	
}
 
 

$(function(){
	FastClick.attach(document.body); 
	
	var notNeed = FastClick.notNeeded(document.body);
	$.fn.triggerFastClick=function(){
	    this.trigger("click");
	        if(!notNeed){
	        this.trigger("click");
	    }
	}
	

})