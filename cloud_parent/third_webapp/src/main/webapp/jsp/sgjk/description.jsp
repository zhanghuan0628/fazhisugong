<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/jsp/common/meta.jsp"%>
        <title>21天健康打卡计划</title>
        <%@include file="/jsp/common/css.jsp"%>
        <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgjk/css/global.css?v=1" />
    </head>
    <body>
        
        <div class="signIn">
            <div class="signScroll">
                <div class="signInBg1">
                    <img src="${base}/jsp/sgjk/images/signInBg1.png" alt="">
                </div>

                <div class="signInBg2">
                    <img src="${base}/jsp/sgjk/images/signInBg2.png" alt="">
                </div>

                <img class="bgImg3" src="${base}/jsp/sgjk/images/bgImg3.png" alt="" srcset="">
                <img class="bgImg4" src="${base}/jsp/sgjk/images/bgImg4.png" alt="" srcset="">
                <img class="bgImg5" src="${base}/jsp/sgjk/images/bgImg5.png" alt="" srcset="">
                
                <img class="homeTitle" src="${base}/jsp/sgjk/images/homeTitle.png" alt="" srcset="">
                <img class="rule" src="${base}/jsp/sgjk/images/rule.png?v=20180419" alt="">

            </div>
            
            
        </div>
        <script src="${base}/jsp/sgjk/js/jquery.min.js"></script>
        <script src="${base}/jsp/sgjk/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <script src="${base}/jsp/sgjk/js/index.js?v=11" type="text/javascript" charset="utf-8"></script>
        <script>
            // 判断是否注册
            function isLoginUser(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectLoginUser",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.code == "1"){
                            $(".signScroll").append("<img class='hintCont' src='${base}/jsp/sgjk/images/hintCont.png' alt='' />")
                        }
                    }
                });
            }

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";
            $(function(){
                // var oauth = "${oauth}";
				// var openId = '';
				// if (oauth == 'true') {
                //     openId = "${openId}";
                //     setCookie("openId",openId);
				// } else {
				// 	if (getCookie("openId") != null &&getCookie("openId") != "") {
				// 		openId = getCookie("openId");
				// 	} else {
				// 		wxAuthorization("sgjk/description.jsp");
				// 	}
                // }

                // isLoginUser(openId);

                locationUrl = apiUrl+"/jsp/sgjk/description.jsp";
                wxtitle = "21天健康打卡计划——5980服务工程";
                wximgUrl = apiUrl+"/jsp/sgjk/images/sgLogo.png";
                wxdesc = "怎么开启计划？开启计划之后，会有啥？——计划规则";
                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
            })
        </script>
    </body>
</html>