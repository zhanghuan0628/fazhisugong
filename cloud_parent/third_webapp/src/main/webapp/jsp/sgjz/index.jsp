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
        <link rel="stylesheet" type="text/css" href="${base}/jsp/sgjz/css/mescroll.min.css"/>
        <link rel="stylesheet" type="text/css" href="${base}/jsp/sgjz/css/global.css?v=12" />
    </head>
    <body>
        <div id="layer"></div>
        <div class="votePop">
            <p>每天最多投票3次</p>
            <p>请明天再来吧！</p>
            <a>OK</a>
        </div>
            
        <div class="mescroll" id="mescroll">

        
            <div class="jzTop">
                <img src="${base}/jsp/sgjz/images/jzTop.png" alt="">
                <a class="rulesLink" href="${base}/jsp/sgjz/rulePage.jsp">活动介绍</a>
            </div>

            <div class="countDown">
                <p class="countDownNum clearfix">
                    <span class="day clearfix"><i></i><i></i></span>
                    <span class="hour clearfix"><i></i><i></i></span>
                    <span class="second clearfix"><i></i><i></i></span>
                    <span class="minute clearfix"><i></i><i></i></span>
                </p>
            </div>

            <div class="searchFrame">
                <div class="searchBar">
                    <em></em>
                    <form id="searchForm" action="#"><input type="search" placeholder="请输入编号或作品名称" /></form>
                </div>
            </div>
            
            <div class="opusList" id="opusList">
                <ul id="dataList">
                    <!-- <li>
                        <h1 class="opusCode">136</h1>
                        <div class="opusLogo"></div>
                        <h2 class="opusName">01-走起俱乐部</h2>
                        <p class="opusAuthor">部门 多多</p>
                        <div class="vote">
                            <em></em>
                            <p>1992</p>
                        </div>
                    </li> -->
                    
                </ul>
            </div>
        </div>

        <%@include file="/jsp/common/js.jsp"%>
        <script src="${base}/jsp/sgjz/js/mescroll.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${base}/jsp/sgjz/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>

        <script>

            //			倒计时
			function countDown(endTime){
				var nowDate = new Date().getTime();
				var cdDate = endTime-nowDate;
				var days = parseInt(cdDate / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
				var hours = parseInt(cdDate / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
				var minutes = parseInt(cdDate / 1000 / 60 % 60, 10);//计算剩余的分钟 
                var seconds = parseInt(cdDate / 1000 % 60, 10);//计算剩余的秒数
				days = checkTime(days); 
				hours = checkTime(hours); 
				minutes = checkTime(minutes); 
				seconds = checkTime(seconds); 
				$(".countDown p").html("<span class='day clearfix'><i>"+days.substr(0,1)+"</i><i>"+days.substr(1,2)+"</i></span>"+
					"<span class='hour clearfix'><i>"+hours.substr(0,1)+"</i><i>"+hours.substr(1,2)+"</i></span>"+
					"<span class='second clearfix'><i>"+minutes.substr(0,1)+"</i><i>"+minutes.substr(1,2)+"</i></span>"+
					"<span class='minute clearfix'><i>"+seconds.substr(0,1)+"</i><i>"+seconds.substr(1,2)+"</i></span>");
				var timer = setInterval(function(){
					var nowDate = new Date().getTime();
					var cdDate = endTime-nowDate;
                    if (cdDate <= 0) {
                        clearInterval(timer);
                        $(".countDown p").html("<span class='day clearfix'><i>0</i><i>0</i></span>"+
                            "<span class='hour clearfix'><i>0</i><i>0</i></span>"+
                            "<span class='second clearfix'><i>0</i><i>0</i></span>"+
                            "<span class='minute clearfix'><i>0</i><i>0</i></span>");
                        return false;
                    }
					var days = parseInt(cdDate / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
					var hours = parseInt(cdDate / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
					var minutes = parseInt(cdDate / 1000 / 60 % 60, 10);//计算剩余的分钟 
					var seconds = parseInt(cdDate / 1000 % 60, 10);//计算剩余的秒数
					days = checkTime(days); 
					hours = checkTime(hours); 
					minutes = checkTime(minutes); 
					seconds = checkTime(seconds); 
					
					$(".countDown p").html("<span class='day'><i>"+days.substr(0,1)+"</i><i>"+days.substr(1,2)+"</i></span>"+
					"<span class='hour'><i>"+hours.substr(0,1)+"</i><i>"+hours.substr(1,2)+"</i></span>"+
					"<span class='second'><i>"+minutes.substr(0,1)+"</i><i>"+minutes.substr(1,2)+"</i></span>"+
					"<span class='minute'><i>"+seconds.substr(0,1)+"</i><i>"+seconds.substr(1,2)+"</i></span>");
					nowDate--;
				},1000)
            }
            
            function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
				if(i<10) { 
					i = "0" + i; 
				}
				return i.toString(); 
			} 

            // 作品列表
            function opusList(openId,serachCode,pageSize,page){
                $.ajax({
                    url: apiUrl+"/SgLogorController/selectAllLogoInfo",
                    type: "post",
                    data: {openId:openId,serachCode:serachCode,pageSize:pageSize,pageNo:page.num},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){

                                var data = data.data.dataList;

                                opusMescroll.endByPage(data.length, data.totalPage);
                                
                                var html = "";
                                $.each(data,function(i,item){
                                    var rankImg = "";
                                    if (item.rowNo == "1") {
                                        rankImg = "<img class='rankImg' src='${base}/jsp/sgjz/images/rank1.png' />";
                                    } else if (item.rowNo == "2") {
                                        rankImg = "<img class='rankImg' src='${base}/jsp/sgjz/images/rank2.png' />";
                                    } else if (item.rowNo == "3") {
                                        rankImg = "<img class='rankImg' src='${base}/jsp/sgjz/images/rank3.png' />";
                                    }

                                    var hasVote = "";
                                    if (item.vote == "1") {
                                        hasVote = " hasVote";
                                    }

                                    var logoImg = "";
                                    if (item.logoImg != null && item.logoImg != "") {
                                        // logoImg = "background-image:url("+item.logoImg+")";
                                        logoImg = item.logoImg;
                                    }
                                    html += "<li data-logoid='"+item.id+"'>"+rankImg+
                                        "<h1 class='opusCode'>"+item.rowNo+"</h1>"+
                                        "<div class='opusLogo'><img src='"+logoImg+"' /></div>"+
                                        "<h2 class='opusName'>"+item.number+" "+item.title+"</h2>"+
                                        "<div class='vote"+hasVote+"'>"+
                                            "<em></em>"+
                                            "<p>"+item.ballot+"</p>"+
                                        "</div>"+
                                    "</li>"
                                });

                                // if (page.num == 1) {
								// 	$(".opusList ul").empty();
								// }
                                $(".opusList ul").append(html);

                                

                                   
                                
                            }else{
                                
                            }
                        }
                    }
                });
            }


            // 点赞
            function vote(openId,logoId,_this){
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
                                var voteNum = parseInt(_this.find("p").text()) + 1;
                                _this.addClass("hasVote");
                                _this.find("p").text(voteNum);
                                
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

            var htmlPx = $("html").css("font-size");
            var remTurn = htmlPx.substr(0,htmlPx.length-2);

            var pageSize = 10;
            var serachCode = "";

            var opusMescroll;

            // var endTime = 1526202000000;
            var endTime = 1527400800000;
            var isEnd = false;


            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";

            $(function(){
                // if (isAndroid()) {
                //     $(".countDownNum").css("line-height","4rem")
                // }

                if (endTime-new Date().getTime()<=0) {
					isEnd = true;
					$(".countDown p").html("<span class='day clearfix'><i>0</i><i>0</i></span>"+
					"<span class='hour clearfix'><i>0</i><i>0</i></span>"+
					"<span class='second clearfix'><i>0</i><i>0</i></span>"+
					"<span class='minute clearfix'><i>0</i><i>0</i></span>");
				} else {
					countDown(endTime);
				}

                // var timer = setInterval(function() {
                //     if ($(".jzTop img").get(0).complete) {
                //         clearInterval(timer);
                //         var rankT = $(".jzTop").height() + parseInt(remTurn) + $(".countDown").height();
                //         $("#mescroll").scroll(function(){
                //             var clockT = $("#mescroll").scrollTop();
                //             if (clockT >= rankT) {
                //                 $(".searchFrame").css({"position":"fixed"});
                //                 $(".opusList").css("margin-top","7.2rem");
                //             } else {
                //                 $(".searchFrame").css({"position":"unset"});
                //                 $(".opusList").css("margin-top","2rem");
                //             }
                //         })
                //     }
                // }, 50)

                var oauth = "${oauth}";
				var openId = '';
				if (oauth == 'true') {
                    openId = "${openId}";
                    setCookie("jzOpenId",openId);
				} else {
					if (getCookie("jzOpenId") != null &&getCookie("jzOpenId") != "") {
						openId = getCookie("jzOpenId");
					} else {
						wxAuthorization("sgjz/index.jsp");
					}
                }


                var Url = location.search;
				if (Url.indexOf("?")!=-1){
                    var rT = "";
                    var pN = "";
					
					if (Url.indexOf("%3F")!=-1) {
						var Request = new getAuthQueryString();
                        rT = Request.rT;
                        if (rT !=undefined && rT != null && rT.indexOf("&appid") != -1) {
                            var appInd = rT.indexOf("&appid");
                            rT = rT.substr(0,appInd)
                        }
                        pN = Request.pN;
                        if (pN !=undefined && pN != null && pN.indexOf("&appid") != -1) {
                            var appInd = pN.indexOf("&appid");
                            pN = pN.substr(0,appInd)
                        }
					} else {
                        rT = getQueryString("rT");
                        pN = getQueryString("pN");
                    }
                }
                

                if (pN !=undefined && pN != null) {
                    pageSize = pageSize * parseInt(pN);
                }


                // var openId = "1";
                

                opusMescroll = new MeScroll("mescroll", { 
					down: {
						callback: function (mescroll) { 
                            $(".searchBar input").val("");
                            serachCode = "";
							mescroll.resetUpScroll(); 
						}
					},
					up: {
                        auto:false,
                        clearEmptyId: "dataList",
                        callback: function (page) { opusList(openId,serachCode,pageSize,page) }, 
                        isBounce: false,
                        empty:{
                            warpId : "opusList" , 
                            tip:"未搜索到相关作品"
                        }
                        // htmlNodata:'<p class="upwarp-nodata">-- END --</p>'
					}
                });


                
                if (rT !=undefined && rT != null) {
                    var listListen = setInterval(function(){
                        if ($("#dataList li").length != 0) {
                            opusMescroll.setPageNum(pageSize/10 + 1);
                            pageSize = 10;
                            clearInterval(listListen);
                            opusMescroll.scrollTo(rT,0);
                        }
                    },50)
                }

                $(".searchBar em").click(function(){
                    serachCode = $(".searchBar input").val().trim();
                    opusMescroll.resetUpScroll(); 
                });

                $(".searchBar").on('submit', '#searchForm', function(event){
                    event.preventDefault();
                    serachCode = $(".searchBar input").val().trim();
                    opusMescroll.resetUpScroll(); 
                })
                
                // 点赞
                $(".opusList ul").on("click",".vote",function(e){
                    e.stopPropagation();
                    if (!$(this).hasClass("hasVote")) {
                        if (endTime-new Date().getTime()<=0) {
                            toast("活动已经结束！");
                            return false;
                        }
                        var logoId = $(this).parents("li").data("logoid");
                        var _this = $(this);
                        vote(openId,logoId,_this);
                    }
                })


                $(".votePop a,#layer").click(function(){
                    $(".votePop,#layer").hide();
                })

                $(".opusList ul").on("click","li",function(){
                    if (!$(event.target).is('.vote')){
                        var rT = opusMescroll.getScrollTop();
                        var pN = Math.ceil(parseInt($(this).find(".opusCode").text())/10);

                        history.pushState(null,null,apiUrl+'/jsp/sgjz/index.jsp?rT='+rT+'&pN='+pN);

                        var logoId = $(this).data("logoid");
                        location.href="${base}/jsp/sgjz/opusDetail.jsp?logoId="+logoId;
                    }
                })


                locationUrl = apiUrl+"/jsp/sgjz/index.jsp";
                wxtitle = "国网苏州供电公司健走俱乐部名称和logo征集活动";
                wximgUrl = apiUrl+"/jsp/sgjz/images/sgLogo.png";
                wxdesc = "快来为你喜欢的名称和logo投票吧！";
                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
            })
        </script>
    </body>
</html>