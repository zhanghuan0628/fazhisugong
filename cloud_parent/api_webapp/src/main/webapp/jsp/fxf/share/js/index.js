//var apiUrl = location.protocol+"//"+window.location.host+"/api"; //测试
var apiUrl = location.protocol+"//"+window.location.host+"/ffyy_api";

function wxAuthorization(state){
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
					var redirectUri =encodeURI(apiUrl+"/auth/fxf_user", "UTF-8");
					var url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUri+"&response_type=code"+"&scope="+scope+"&state="+state+"#wechat_redirect";
					window.location.href=url;	
                }else{
                	
                }
            }
        }
    });
	
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}

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
    	 if(isWeChat()){
             window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.feixiaofan"
         }else if(isQQ()){
             window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.feixiaofan"
         } else{
             if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){  //判断iPhone|iPad|iPod|iOS
                 window.location.href="https://itunes.apple.com/cn/app/id1226876880?l=zh&ls=1&mt=8"
             }
             else if (/(Android)/i.test(navigator.userAgent)){   //判断Android
                 //window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.feixiaofan"//应用宝
                 window.location.href="http://www.wandoujia.com/apps/com.feixiaofan"//豌豆荚
                // window.location.href ="../../assets/app/fxf/fxf-1.1.apk"
             }
             else{
                 //window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.feixiaofan"//应用宝
                 window.location.href="http://www.wandoujia.com/apps/com.feixiaofan"//豌豆荚
                 //window.location.href ="../../assets/app/fxf/fxf-1.1.apk"
             }
         }	
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
 

//分享配置
function wxShare(locationUrl,wxtitle,wximgUrl,wxdesc){
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


                // QQ分享
                setShareInfo({
                    title:wxtitle,
                    summary:wxdesc,
                    pic:wximgUrl,
                    url:locationUrl,
                    WXconfig:{
                        swapTitleInWX: true,
                        appId:appId,
                        timestamp:timestamp,
                        nonceStr:nonceStr,
                        signature:signature
                    }
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


function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
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
