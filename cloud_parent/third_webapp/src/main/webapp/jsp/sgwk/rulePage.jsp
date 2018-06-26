<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<htm lang="zh-cmn-Hans">
    <head>
        <%@include file="/jsp/common/meta.jsp"%>
        <title>品质苏供—班组微讲堂大赛</title>
        <%@include file="/jsp/common/css.jsp"%>
       <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgwk/css/global.css?v=14" />
    </head>
    <body>
        <div class="ruleImg">
            <img src="${base}/jsp/sgwk/images/ruleImg1.jpg?v=2" alt="">
            <img src="${base}/jsp/sgwk/images/ruleImg2.jpg?v=2" alt="">
            <img src="${base}/jsp/sgwk/images/ruleImg3.jpg?v=2" alt="">
            <img src="${base}/jsp/sgwk/images/ruleImg4.jpg?v=2" alt="">
        </div>

        <script src="${base}/jsp/sgwk/js/jquery.min.js"></script>
        <script src="${base}/jsp/sgwk/js/wScrollFix.js"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <!-- <script src="${base}/jsp/sgwk/js/fastclick.js"></script> -->
        <script src="${base}/jsp/sgwk/js/index.js?v=1"></script>
        <script>

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";


            var endTime = 1526202000000;

            
            $(function() {

                wScrollFix(document.querySelector(".ruleImg"), false);

                
                
                locationUrl = apiUrl+"/jsp/sgwk/rulePage.jsp";
                wxtitle = "国网苏州供电公司——班组微讲堂大赛";
                wximgUrl = apiUrl+"/jsp/sgwk/images/sgLogo.png";
                wxdesc = "百堂微课，为你喜欢的课程投票吧~";
                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
            })
        </script>
    </body>
</html>