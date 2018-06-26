<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<htm lang="zh-cmn-Hans">
    <head>
        <%@include file="/jsp/common/meta.jsp"%>
        <title>It's up to you</title>
        <%@include file="/jsp/common/css.jsp"%>
       <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgjz/css/global.css?v=14" />
    </head>
    <body>
        <div class="ruleImg">
            <img src="${base}/jsp/sgjz/images/ruleImg1.jpg?v=2" alt="">
            <img src="${base}/jsp/sgjz/images/ruleImg2.jpg?v=2" alt="">
            <img src="${base}/jsp/sgjz/images/ruleImg3.jpg?v=2" alt="">
        </div>

        <%@include file="/jsp/common/js.jsp"%>
        <!-- <script src="${base}/jsp/sgwk/js/fastclick.js"></script> -->
        <script src="${base}/jsp/sgjz/js/index.js?v=1"></script>
        <script>

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";


            var endTime = 1526202000000;

            
            $(function() {

                // wScrollFix(document.querySelector(".ruleImg"), false);

                
                
                locationUrl = apiUrl+"/jsp/sgjz/rulePage.jsp";
                wxtitle = "国网苏州供电公司健走俱乐部名称和logo征集活动";
                wximgUrl = apiUrl+"/jsp/sgjz/images/sgLogo.png";
                wxdesc = "快来为你喜欢的名称和logo投票吧！";
                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
            })
        </script>
    </body>
</html>