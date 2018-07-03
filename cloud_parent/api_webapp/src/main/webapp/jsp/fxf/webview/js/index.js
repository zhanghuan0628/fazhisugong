//var apiUrl = location.protocol+"//"+window.location.host+"/api"; //测试
var apiUrl = location.protocol+"//"+window.location.host+"/ffyy_api";



function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
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


function toast(cont){
	if (typeof($(".toast")[0])!="undefined") {
		return false;
	}
	$("body").append("<p class='toast'>"+cont+"</p>");
	setTimeout(function(){
		$(".toast").remove();
	},1000)
}

function toastCheck(cont){
	if (typeof($(".toastCheck")[0])!="undefined") {
		return false;
	}
	$("body").append("<p class='toastCheck'>"+cont+"</p>");
	setTimeout(function(){
		$(".toastCheck").remove();
	},1000)
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


