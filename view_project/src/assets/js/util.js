import axios from 'axios';
import qs from 'qs';

export default {
    wxShare: function(locationUrl,wxtitle,wximgUrl,wxdesc){
        axios.post(location.protocol+'//pre-third.feifanxinli.com/third/auth/jssdkconfig/wx***************',qs.stringify({
            requestUrl:location.href.split('#')[0]
        }))
        .then(res=>{
            let data = res.data;
            if (data.code == "2000") {
                let appId = data.data.appId;
                let timestamp = data.data.timestamp;
                let nonceStr = data.data.nonceStr;
                let signature = data.data.signature;

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
                        success: function () {
                            wxShareCount(wxsourceC,wxsourceE,2)
                        },
                        cancel: function () {}
                    });
                    //分享给朋友
                    wx.onMenuShareAppMessage({
                        title: wxtitle, // 分享标题
                        imgUrl: wximgUrl, // 分享图标
                        desc: wxdesc, // 分享描述
                        link:locationUrl,
                        success: function () {
                            wxShareCount(wxsourceC,wxsourceE,1)
                        },
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
            }
        })
        .catch(err=>{
            console.log(err);
        });
        
    },
    cookie: {
        set: function(name, value){
        // 缓存过期时间
            let Days = 7
            let exp = new Date()
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000)
            document.cookie = name + '=' + escape(value) + ';expires=' + exp.toGMTString() + ';path=/'
        },
        get: function(name){
            let arr = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
            let reg = arr
            arr = document.cookie.match(reg)
            if (arr) {
                return unescape(arr[2])
            }else{
                return null
            }
        },
        del: function(name){
            let exp = new Date()
            exp.setTime(exp.getTime() - 1)
            let cval = this.cookie.get(name)
            if (cval != null) {
                document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString() + ';path=/'
            }
        }
    }
}
