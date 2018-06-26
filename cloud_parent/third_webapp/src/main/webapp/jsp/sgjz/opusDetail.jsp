<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/jsp/common/meta.jsp"%>
        <title>It's up to you</title>
        <%@include file="/jsp/common/css.jsp"%>
        <link rel="stylesheet" type="text/css" href="${base}/jsp/sgjz/css/global.css?v=27" />
    </head>
    <body style="background-color: #f1ffe6;">
        <div id="layer"></div>
        <div class="votePop">
            <p>每天最多投票3次</p>
            <p>请明天再来吧！</p>
            <a>OK</a>
        </div>

        <div class="opusFrame">
            <div class="opusDetail">
                <div class="opusDetailLogo">
                    <!-- <img src="" alt=""> -->
                    <i class="opusDetailCode"></i>
                </div>

                <div class="opusInfo">
                    <div class="opusInfo1"><img src="${base}/jsp/sgjz/images/opusInfo1.png" /><p></p></div>
                    <!-- <div class="opusInfo2"><img src="${base}/jsp/sgjz/images/opusInfo2.png" /><p></p></div> -->
                    <div class="opusInfo3"><img src="${base}/jsp/sgjz/images/opusInfo3.png" /><p></p></div>
                </div>
            </div>
        </div>

        <div class="opusVote">
            <em></em>
            <p>给TA投票</p>
            <a class="goRank" href="${base}/jsp/sgjz/index.jsp">查看排行榜</a>
        </div>
        

        <%@include file="/jsp/common/js.jsp"%>
        <script src="${base}/jsp/sgjz/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>

        <script>

            // 作品详情
            function opusDetail(openId,Id){
                $.ajax({
                    url: apiUrl+"/SgLogorController/selectLogoDetail",
                    type: "post",
                    data: {openId:openId,Id:Id},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                $(".opusDetailLogo").css("background-image","url("+data.logoImg+")");
                                // $(".opusDetailLogo img").attr("src",data.logoImg);
                                $(".opusDetailLogo .opusDetailCode").text(data.number);

                                $(".opusInfo .opusInfo1 p").text(data.title);
                                // $(".opusInfo .opusInfo2 p").text(data.department+" "+data.lecturer);
                                var regExp = new RegExp("br", 'g');
                                var detail = data.detail.replace(regExp,"<br />");
                                console.log(detail)
                                $(".opusInfo .opusInfo3 p").html(detail);

                                if (data.vote == "1") {
                                    $(".opusVote em").addClass("hasVote");
                                    $(".opusVote p").text("已投");

                                }

                                if ($(".opusDetail").height() > $(".opusFrame").height()) {
                                    $(".opusVote").prepend("<a class='readMore'>查看更多</a>");
                                    $(".opusFrame").append("<div id='blur'></div>");
                                }

                                locationUrl = apiUrl+"/jsp/sgjz/opusDetail.jsp?logoId="+Id;
                                wxtitle = data.number+"号作品需要你的支持——健步走俱乐部名称和logo征集活动";
                                wximgUrl = apiUrl+"/jsp/sgjz/images/sgLogo.png";
                                wxdesc = "你的每一票对我来说都很关键~";
                                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
                                
                            }else{
                                
                            }
                        }
                    }
                });
            }

            // 点赞
            function vote(openId,logoId){
                $.ajax({
                    url: apiUrl+"/SgLogorController/insertLogoVote",
                    type: "post",
                    data: {openId:openId,logoId:logoId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                $(".opusVote em").addClass("hasVote");
                                $(".opusVote p").text("已投");
                            }else{
                                
                            }
                        } else {
                            if (data.code == "5005") {
                                $(".votePop,#layer").show();
                            }
                        }
                    }
                });
            }


            // var endTime = 1526202000000;
            var endTime = 1527400800000;
            var isEnd = false;


            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";

            $(function(){
                if (endTime-new Date().getTime()<=0) {
					isEnd = true;
				} else {
                    // $(".vd-ballot").after("<a class='vd-vote'><span>投票</span><img src='${base}/jsp/sgwk/images/microClassPraise.png' /></a>")
                }

                var Url = location.search;
				if (Url.indexOf("?")!=-1){
                    var logoId = "";
					
					if (Url.indexOf("%3F")!=-1) {
                        var Request = new getAuthQueryString();
                        logoId = Request.logoId;
                        if (logoId.indexOf("&appid") != -1) {
                            var appInd = logoId.indexOf("&appid");
                            logoId = logoId.substr(0,appInd)
                        }
					} else {
                        logoId = getQueryString("logoId");
                    }
                }

                var oauth = "${oauth}";
				var openId = '';
				if (oauth == 'true') {
                    openId = "${openId}";
                    setCookie("jzOpenId",openId);
				} else {
					if (getCookie("jzOpenId") != null &&getCookie("jzOpenId") != "") {
						openId = getCookie("jzOpenId");
					} else {
						wxAuthorization("sgjz/opusDetail.jsp?logoId="+logoId);
					}
                }

                opusDetail(openId,logoId);



                // 点赞
                $(".opusVote em").click(function(){
                    if (!$(this).hasClass("hasVote")) {
                        if (endTime-new Date().getTime()<=0) {
                            toast("活动已经结束！");
                            return false;
                        }
                        vote(openId,logoId)
                    }
                })

                $(".votePop a,#layer").click(function(){
                    $(".votePop,#layer").hide();
                })


                // 查看更多
                $(".opusVote").on("click",".readMore",function(){
                    $(".opusFrame").css("height","auto");
                    $("#blur").remove();
                    $(this).addClass("hasPull").text("收起");
                })

                $(".opusVote").on("click",".hasPull",function(){
                    $(".opusFrame").css("height","60vh").append("<div id='blur'></div>");
                    $(this).removeClass("hasPull").text("查看更多");
                })
            })
        </script>
    </body>
</html>