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
       <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgwk/css/global.css?v=23" />
    </head>
    <body>
        <div id="layer"></div>

        

        <div class="microClassBg">
            <div class="microClassScroll" style="padding-bottom: 1rem;">
                <div class="participants">
                    <p>参与人数</p>
                    <ul>
                        <li>0</li>
                        <li>0</li>
                        <li>0</li>
                        <li>0</li>
                        <li>0</li>
                        <li>0</li>
                    </ul>
                </div>

                <a href="${base}/jsp/sgwk/rulePage.jsp" class="ruleBtn">活动规则 ></a>

                <img class="homeTitle" src="${base}/jsp/sgwk/images/homeTitle.png" alt="">

                <div class="countDown">
                    <p class="countDownNum clearfix">
                        <span class="day clearfix"><i></i><i></i></span>
                        <span class="hour clearfix"><i></i><i></i></span>
                        <span class="second clearfix"><i></i><i></i></span>
                        <span class="minute clearfix"><i></i><i></i></span>
                    </p>
                </div>

                <div class="searchFrame">
                    <h1>人人上讲台 人人当讲师</h1>
                    <div class="searchBar">
                        <em></em>
                        <form id="searchForm" action="#"><input type="search" placeholder="搜索视频编号/主讲人/课题/专业" /></form>
                    </div>
                </div>

                <div class="rankingList">
                    <ul>
                        <!-- <li>
                            <h2 class="rankingNum">1<img src="${base}/jsp/sgwk/images/microClassNo1.png" alt="" srcset=""></h2>
                            <div class="speakerHead">
                                <div class="headImg" style="background-image: url(${base}/jsp/sgwk/images/head.jpg)">
                                    <em></em>
                                </div>
                            </div>
                            <div class="speakerInfo">
                                <p>编号：020</p>
                                <p>专业：变电维修</p>
                                <p>主讲：吴秀波</p>
                                <p>课题：苏州供电微讲堂大赛微讲堂大赛</p>
                                
                                <h2 class="ballot">4918票</h2>

                                <a class="vote">投票</a>
                                <a class="share">分享</a>
                            </div>
                        </li>

                        <li>
                            <h2 class="rankingNum">2<img src="${base}/jsp/sgwk/images/microClassNo2.png" alt="" srcset=""></h2>
                            <div class="speakerHead">
                                <div class="headImg" style="background-image: url(${base}/jsp/sgwk/images/head.jpg)">
                                    <em></em>
                                </div>
                            </div>
                            <div class="speakerInfo">
                                <p>编号：020</p>
                                <p>专业：变电维修</p>
                                <p>主讲：吴秀波</p>
                                <p>课题：苏州供电微讲堂大赛微讲堂大赛</p>

                                <h2 class="ballot">4918票</h2>

                                <a class="vote">投票<img src="${base}/jsp/sgwk/images/microClassPraise.png" alt="" srcset=""></a>
                                <a class="share">分享</a>
                            </div>
                        </li>

                        <li>
                            <h2 class="rankingNum">3<img src="${base}/jsp/sgwk/images/microClassNo3.png" alt="" srcset=""></h2>
                            <div class="speakerHead">
                                <div class="headImg" style="background-image: url(${base}/jsp/sgwk/images/head.jpg)">
                                    <em></em>
                                </div>
                            </div>
                            <div class="speakerInfo">
                                <p>编号：020</p>
                                <p>专业：变电维修</p>
                                <p>主讲：吴秀波</p>
                                <p>课题：苏州供电微讲堂大赛微讲堂大赛</p>

                                <h2 class="ballot">4918票</h2>

                                <a class="vote">投票</a>
                                <a class="share">分享</a>
                            </div>
                        </li> -->
                    </ul>

                    <p class="BottomTxt">加载中...</p>
                </div>

                <a class="returnTop"></a>
            </div>
            
            
        </div>

        <script src="${base}/jsp/sgwk/js/jquery.min.js"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <!-- <script src="${base}/jsp/sgwk/js/fastclick.js"></script> -->
        <script src="${base}/jsp/sgwk/js/wScrollFix.js"></script>
        <script src="${base}/jsp/sgwk/js/index.js?v=1"></script>
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
				setInterval(function(){
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


            // 视频列表
            function videoList(openId,serachCode,pageSize,pageNo){
                $.ajax({
                    url: apiUrl+"/video_match/list",
                    type: "post",
                    data: {openId:openId,serachCode:serachCode,pageSize:pageSize,pageNo:pageNo},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){

                                var totalPeople = data.module.toString();
                                var totalPeopleLen = totalPeople.length;

                                if (totalPeopleLen == 1) {
                                    $(".participants ul li").eq(0).text("0");
                                    $(".participants ul li").eq(1).text("0");
                                    $(".participants ul li").eq(2).text("0");
                                    $(".participants ul li").eq(3).text("0");
                                    $(".participants ul li").eq(4).text("0");
                                    $(".participants ul li").eq(5).text(totalPeople);
                                } else {
                                    totalPeopleArr = totalPeople.split("");
                                    if (totalPeopleLen == 2) {
                                        $(".participants ul li").eq(0).text("0");
                                        $(".participants ul li").eq(1).text("0");
                                        $(".participants ul li").eq(2).text("0");
                                        $(".participants ul li").eq(3).text("0");
                                        $(".participants ul li").eq(4).text(totalPeopleArr[0]);
                                        $(".participants ul li").eq(5).text(totalPeopleArr[1]);
                                    } else if (totalPeopleLen == 3) {
                                        $(".participants ul li").eq(0).text("0");
                                        $(".participants ul li").eq(1).text("0");
                                        $(".participants ul li").eq(2).text("0");
                                        $(".participants ul li").eq(3).text(totalPeopleArr[0]);
                                        $(".participants ul li").eq(4).text(totalPeopleArr[1]);
                                        $(".participants ul li").eq(5).text(totalPeopleArr[2]);
                                    } else if (totalPeopleLen == 4) {
                                        $(".participants ul li").eq(0).text("0");
                                        $(".participants ul li").eq(1).text("0");
                                        $(".participants ul li").eq(2).text(totalPeopleArr[0]);
                                        $(".participants ul li").eq(3).text(totalPeopleArr[1]);
                                        $(".participants ul li").eq(4).text(totalPeopleArr[2]);
                                        $(".participants ul li").eq(5).text(totalPeopleArr[3]);
                                    } else if (totalPeopleLen == 5) {
                                        $(".participants ul li").eq(0).text("0");
                                        $(".participants ul li").eq(1).text(totalPeopleArr[0]);
                                        $(".participants ul li").eq(2).text(totalPeopleArr[1]);
                                        $(".participants ul li").eq(3).text(totalPeopleArr[2]);
                                        $(".participants ul li").eq(4).text(totalPeopleArr[3]);
                                        $(".participants ul li").eq(5).text(totalPeopleArr[4]);
                                    } else if (totalPeopleLen == 6) {
                                        $(".participants ul li").eq(0).text(totalPeopleArr[0]);
                                        $(".participants ul li").eq(1).text(totalPeopleArr[1]);
                                        $(".participants ul li").eq(2).text(totalPeopleArr[2]);
                                        $(".participants ul li").eq(3).text(totalPeopleArr[3]);
                                        $(".participants ul li").eq(4).text(totalPeopleArr[4]);
                                        $(".participants ul li").eq(5).text(totalPeopleArr[5]);
                                    }
                                }



                                var data = data.data;

                                

                                pageNum = 1;
                                pageSize = 10;
                                $(".noResult").remove();
                                $(".BottomTxt").show();
                                if (data.length == 0) {
                                    $(".rankingList ul").empty();
                                    $(".rankingList").append("<p class='noResult' style='line-height:8rem;text-align:center;padding-bottom:6rem;color:#FFFFFF;'>未搜到相关课程</p>")
                                    $(".BottomTxt").hide();
                                } else {
                                    if (data.length == 60) {
                                        $(".BottomTxt").text("本次投票所有作品已全部展示");
                                        hasNext = false;
                                    } else if (data.length <60 && data.length >10)  {
                                        pageNum = data.length/10 + 1;
                                        hasNext = true;
                                        $(".BottomTxt").text("加载中...");
                                    }else if (data.length == 10) {
                                        hasNext = true;
                                        pageNum++;
                                        $(".BottomTxt").text("加载中...");
                                    } else {
                                        hasNext = false;
                                        if (serachCode != "") {
                                            $(".BottomTxt").text("已呈现所有搜索结果");
                                        }
                                    }
                                    var html = "";
                                    $.each(data,function(i,item){
                                        var rankNum = "";
                                        if (item.ranking == 1) {
                                            rankNum = "1<img src='${base}/jsp/sgwk/images/microClassNo1.png'>"
                                        } else if (item.ranking == 2) {
                                            rankNum = "2<img src='${base}/jsp/sgwk/images/microClassNo2.png'>"
                                        } else if (item.ranking == 3) {
                                            rankNum = "3<img src='${base}/jsp/sgwk/images/microClassNo3.png'>"
                                        } else {
                                            rankNum = item.ranking;
                                        }

                                        var hasVote = "";
                                        if (!isEnd) {
                                            if (item.extVote == "1") {
                                                hasVote = "<a class='vote hasVote'><span>已投</span><img src='${base}/jsp/sgwk/images/microClassPraise.png' alt=''></a>"
                                            } else {
                                                hasVote = "<a class='vote'><span>投票</span><img src='${base}/jsp/sgwk/images/microClassPraise.png' alt=''></a>"
                                            }
                                        }
                                        

                                    
                                        html += "<li data-userid='"+item.id+"'>"+
                                                "<h2 class='rankingNum'>"+rankNum+"</h2>"+
                                                "<div class='speakerHead'>"+
                                                    "<img class='videoCover' src='${base}/jsp/sgwk/images/videoCover.png'>"+
                                                    "<div class='headImg' style='background-image: url("+item.videoImg+")'>"+
                                                        "<em></em>"+
                                                    "</div>"+
                                                "</div>"+
                                                "<div class='speakerInfo'>"+
                                                    "<p>编号："+item.number+"</p>"+
                                                    "<p>专业："+item.major+"</p>"+
                                                    "<p>主讲："+item.lecturer+"</p>"+
                                                    "<p>课题："+item.title+"</p>"+
                                                    
                                                    "<h2 class='ballot'><span>"+item.ballot+"</span>票</h2>"+hasVote+
                                                    "<a class='share'>分享</a>"+
                                                "</div>"+
                                            "</li>";
                                    
                                    });
                                    $(".rankingList ul").html(html);

                                    if (isAndroid()) {
                                        $(".rankingNum").css("line-height","5.5rem")
                                    }
                                }

                                
                            }else{
                                
                            }
                        }
                    }
                });
            }


            function vote(openId,videoId,_this){
                $.ajax({
                    url: apiUrl+"/video_match/ballot",
                    type: "post",
                    data: {openId:openId,videoId:videoId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                _this.find("img").addClass("voting");
                                $("body").append("<div class='voteAnimate'>"+
                                    "<img class='voteAnimate1' src='${base}/jsp/sgwk/images/voteAnimate1.png' />"+
                                    "<img class='voteAnimate2' src='${base}/jsp/sgwk/images/voteAnimate2.png' />"+
                                "</div>");
                                setTimeout(function(){
                                    _this.addClass("hasVote").find("span").text("已投");
                                    var ballotNew = parseInt(_this.siblings(".ballot").find("span").text()) + 1;
                                    _this.siblings(".ballot").find("span").text(ballotNew);
                                    $(".voteAnimate").remove();
                                }, 2000);
                            }else{
                                
                            }
                        } else {
                            if(data.code == "3016"){
                                $("#layer").show();
                                $("body").append("<div class='voteToast'>"+
                                    "<p>你今日已为10个作品投票</p>"+
                                    "<p>明天可以再来哦</p>"+
                                "</div>");

                                setTimeout(function(){
                                    $("#layer").hide();
                                    $(".voteToast").remove();
                                }, 2000);
                            }
                        }
                    }
                });
            }


            var serachCode = "";

            var hasNext = false;
            var pageNum = 1;
            var pageSize = 10;

            var htmlPx = $("html").css("font-size");
            var remTurn = htmlPx.substr(0,htmlPx.length-2);

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";


            var endTime = 1526202000000;
            var isEnd = false;

            // $.ajaxSetup({ cache: false })
            
            $(function() {

                wScrollFix(document.querySelector(".microClassBg"), true);

                var conW = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                $(".homeTitle").css("height",conW);
                

                if (endTime-new Date().getTime()<=0) {
					isEnd = true;
					$(".rl-title").text("活动已结束");
					$(".countDown p").html("<span class='day clearfix'><i>0</i><i>0</i></span>"+
					"<span class='hour clearfix'><i>0</i><i>0</i></span>"+
					"<span class='second clearfix'><i>0</i><i>0</i></span>"+
					"<span class='minute clearfix'><i>0</i><i>0</i></span>");
				} else {
					countDown(endTime);
				}

                var oauth = "${oauth}";
				var openId = '';
				if (oauth == 'true') {
                    openId = "${openId}";
                    setCookie("openId",openId);
				} else {
					if (getCookie("openId") != null &&getCookie("openId") != "") {
						openId = getCookie("openId");
					} else {
						wxAuthorization("sgwk/index.jsp");
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


                videoList(openId,"",pageSize,pageNum);

                if (rT !=undefined && rT != null) {
                    $(".microClassBg").scrollTop(rT);
                }

                
                var rankT = $(".countDown").height() + 3.1 * remTurn + conW;
                $(".microClassBg").scroll(function(){
                    var clockT = $(".microClassBg").scrollTop();
                    if (clockT >= rankT) {
                        $(".searchFrame").css({"position":"fixed","background-color":"#000000"});
                        $(".rankingList").css("padding-top","9.5rem");
                    } else {
                        $(".searchFrame").css({"position":"unset","background-color":"unset"});
                        $(".rankingList").css("padding-top","0");
                    }
                })
                
                


                $(".searchBar em").click(function(){
                    serachCode = $(".searchBar input").val().trim();
                    videoList(openId,serachCode,10,1);
                });

                $(".searchBar").on('submit', '#searchForm', function(event){
                    event.preventDefault();
                    serachCode = $(".searchBar input").val().trim();
                    videoList(openId,serachCode,10,1);
                })

                $(".rankingList ul").on("click","li",function(){
                    if (!$(event.target).is('.vote') && !$(event.target).is('.share')){
                        var rT = $(".microClassBg").scrollTop();
                        var pN = Math.ceil(parseInt($(this).find(".rankingNum").text())/10);

                        history.pushState(null,null,apiUrl+'/jsp/sgwk/index.jsp?rT='+rT+'&pN='+pN);
                        var id = $(this).data("userid");
                        location.href="${base}/jsp/sgwk/videoDetail.jsp?id="+id;
                    }
                })


                // 投票
                $(".rankingList ul").on("click","li .vote",function(e){
                    e.stopPropagation();
                    if (!$(this).hasClass("hasVote")) {
                        if (endTime-new Date().getTime()<=0){
                            location.href = apiUrl + "/jsp/sgwk/index.jsp";
                        } else {
                            var id = $(this).parents("li").data("userid");
                            var _this = $(this);
                            vote(openId,id,_this)
                        }
                        
                    }
                    
                });


                $(".rankingList ul").on("click","li .share",function(e){
                    e.stopPropagation();
                    var rT = $(".microClassBg").scrollTop();
                    var pN = Math.ceil(parseInt($(this).parents("li").find(".rankingNum").text())/10);

                    history.pushState(null,null,apiUrl+'/jsp/sgwk/index.jsp?rT='+rT+'&pN='+pN);
                    var id = $(this).parents("li").data("userid");
                    location.href="${base}/jsp/sgwk/videoDetail.jsp?id="+id+"&source=share";
                    
                });


                // 返回顶部
                $(".returnTop").click(function(){
                    $(".microClassBg").stop().animate({scrollTop:0}, 500);
                })

                $(".microClassBg").scroll(function(){
				　　var scrollTop = $(this).scrollTop();
				　　var scrollHeight = $(".microClassScroll").height();
                　　var windowHeight = $(this).height();

                    console.log(scrollTop)
                    console.log(scrollHeight)
                    console.log(windowHeight)
                
				　　if(scrollTop + windowHeight >= scrollHeight && hasNext){
                　　　　 ajaxUpLoad(openId,serachCode,pageNum);
				　　}
                });


                $("#layer").click(function(){
                    if ($(".voteToast").length != 0) {
                        $("#layer").hide();
                        $(".voteToast").remove();
                    }
                })
                
                locationUrl = apiUrl+"/jsp/sgwk/index.jsp";
                wxtitle = "班组微讲堂大赛——国网苏州供电公司";
                wximgUrl = apiUrl+"/jsp/sgwk/images/sgLogo.png";
                wxdesc = "精品微课，快来为你喜欢的课程投票吧！";
                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
            })


            function ajaxUpLoad(openId,serachCode,pageNo){
                $.ajax({
                    url: apiUrl+"/video_match/list",
                    type: "post",
                    data: {openId:openId,serachCode:serachCode,pageSize:10,pageNo:pageNo},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){

                                var data = data.data;

                            
                                if (data.length == 0) {
                                    hasNext = false;
                                    if (serachCode != "") {
                                        $(".BottomTxt").text("已呈现所有搜索结果");
                                    } else {
                                        $(".BottomTxt").text("本次投票所有作品已全部展示");
                                    }
                                    
                                } else {
                                    if (data.length == 10) {
                                        hasNext = true;
                                        pageNum++;
                                        $(".BottomTxt").text("加载中...");
                                    } else {
                                        hasNext = false;
                                        if (serachCode != "") {
                                            $(".BottomTxt").text("已呈现所有搜索结果");
                                        } else {
                                            $(".BottomTxt").text("本次投票所有作品已全部展示");
                                        }
                                        
                                    }

                                    var html = "";
                                    $.each(data,function(i,item){
                                        rankNum = item.ranking;

                                        var hasVote = "";
                                        if (!isEnd) {
                                            if (item.extVote == "1") {
                                                hasVote = "<a class='vote hasVote'><span>已投</span><img src='${base}/jsp/sgwk/images/microClassPraise.png' alt=''></a>"
                                            } else {
                                                hasVote = "<a class='vote'><span>投票</span><img src='${base}/jsp/sgwk/images/microClassPraise.png' alt=''></a>"
                                            }
                                        }
                                    
                                        html += "<li data-userid='"+item.id+"'>"+
                                                "<h2 class='rankingNum'>"+rankNum+"</h2>"+
                                                "<div class='speakerHead'>"+
                                                    "<img class='videoCover' src='${base}/jsp/sgwk/images/videoCover.png'>"+
                                                    "<div class='headImg' style='background-image: url("+item.videoImg+")'>"+
                                                        "<em></em>"+
                                                    "</div>"+
                                                "</div>"+
                                                "<div class='speakerInfo'>"+
                                                    "<p>编号："+item.number+"</p>"+
                                                    "<p>专业："+item.major+"</p>"+
                                                    "<p>主讲："+item.lecturer+"</p>"+
                                                    "<p>课题："+item.title+"</p>"+
                                                    
                                                    "<h2 class='ballot'><span>"+item.ballot+"</span>票</h2>"+hasVote+
                                                    "<a class='share'>分享</a>"+
                                                "</div>"+
                                            "</li>";
                                    
                                    });
                                    $(".rankingList ul").append(html);
                                }

                                
                            }else{
                                
                            }
                        }
                    }
                });
            }
        </script>
    </body>
</html>