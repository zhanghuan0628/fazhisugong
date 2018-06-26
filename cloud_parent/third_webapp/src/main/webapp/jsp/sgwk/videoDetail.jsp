<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/jsp/common/meta.jsp"%>
        <title>品质苏供—班组微讲堂大赛</title>
        <%@include file="/jsp/common/css.jsp"%>
        <link rel="stylesheet" type="text/css" href="${base}/jsp/sgwk/css/video-js.css">
        <link rel="stylesheet" type="text/css" href="${base}/jsp/sgwk/css/global.css?v=10" />
    </head>
    <body>
        <div id="layer"></div>
        <div class="microClassBg">
            <div class="microClassScroll">
                <div class='adjacent'>
                    
                </div>
                <div class="vd-info">
                    <h1 class="speakerCode"></h1>
                    <p class="speakerDepartment"></p>
                    <p class="speakerName"></p>
                </div>

                <div class="microClassVideo">
                    <!-- <video id="microClassVideo" class="video-js" controls preload="auto" poster="https://rails365.oss-cn-shenzhen.aliyuncs.com/uploads/movie/image/192/2018/5b67c1080783d1717858e9b6a4ba8d86.png">
                        <source src="https://screen-videos.oss-cn-shenzhen.aliyuncs.com/uploads/videos/Reduxcrud/reduxcrud%2016%20%E4%BF%AE%E6%94%B9%E8%AE%B0%E5%BD%95%E6%8F%90%E4%BA%A4%E6%95%B0%E6%8D%AE%201080p.mp4" type="video/mp4">
                    </video> -->

                    <h1 class="microClassTitle"></h1>
                    <div class="microClassSummary"></div>

                </div>
                
                <div class="rankInfo">
                    <div class="vd-ballot">
                        <h2>已获</h2>
                        <p></p>
                    </div>

                    

                    <div class="vd-rank">
                        <h2>当前排名</h2>
                        <p></p>
                    </div>
                </div>

                <div class="goHome"><a href="${base}/jsp/sgwk/index.jsp">排行榜</a></div>
                

                <!-- <div class="adjacent">
                </div> -->
            </div>
        </div>

        <script src="${base}/jsp/sgwk/js/jquery.min.js"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <!-- <script src="${base}/jsp/sgwk/js/fastclick.js"></script> -->
        <script src="${base}/jsp/sgwk/js/video.js"></script>
        <script src="${base}/jsp/sgwk/js/wScrollFix.js"></script>
        <script src="${base}/jsp/sgwk/js/index.js?v=1"></script>
        <script>
            // 视频列表
            function videoDetailById(openId,id){
                $.ajax({
                    url: "${base}/video_match/detail",
                    type: "post",
                    data: {openId:openId,id:id},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                $(".speakerCode").text(data.number);
                                $(".speakerDepartment").text(data.uGroup);
                                $(".speakerName").text("主讲："+data.lecturer);

                                if ($("#microClassVideo").length==0) {
                                    $(".microClassVideo").prepend("<video id='microClassVideo' class='video-js' controls preload='auto' poster='"+data.videoImg+"'>"+
                                        "<source src='"+data.videoUrl+"' type='video/mp4'>"+
                                    "</video>");
                                }

                                

                                videojs(microClassVideo).ready(function(){});

                                $(".microClassTitle").text("课题："+data.title);
                                $(".microClassSummary p").remove();
                                $(".microClassSummary").append("<p>"+data.detail+"</p>");
                                var content = $(".microClassSummary p");
                                var rowNum = Math.round(content.height()/parseFloat(content.css('line-height')));
                                if (rowNum>2) {
                                    $(".microClassSummary").addClass('summary-off').append("<a>更多详情</a>");
                                }


                                $(".vd-ballot p").text(data.ballot+"票");
                                $(".vd-rank p").text("第"+data.ranking+"名");


                                if (data.extVote == "1") {
                                    $(".vd-vote").addClass("hasVote").find("span").text("已投");
                                }

                                var adjacentL = "";
                                var adjacentR = "";

                                var beforeStr = "";
                                var nextStr = "";


                                if (parseInt(data.number) != 1) {
                                    var beforeNum = parseInt(data.number) - 1;
                                    if (beforeNum.toString().length == 1) {
                                        beforeStr = "00" + beforeNum.toString();
                                    } else if (beforeNum.toString().length == 2) {
                                        beforeStr = "0" + beforeNum.toString();
                                    } else {
                                        beforeStr = beforeNum.toString();
                                    }

                                    adjacentL = "<a class='adjacentL' href='${base}/jsp/sgwk/videoDetail.jsp?number="+beforeStr+"'>"+beforeStr+"</a>";
                                } else {
                                    adjacentL = "<a class='noneL'>无</a>";
                                }

                                if (parseInt(data.number) != 60) {
                                    var nextNum = parseInt(data.number) + 1;
                                    if (nextNum.toString().length == 1) {
                                        nextStr = "00" + nextNum.toString();
                                    } else if (nextNum.toString().length == 2) {
                                        nextStr = "0" + nextNum.toString();
                                    } else {
                                        nextStr = nextNum.toString();
                                    }

                                    adjacentR = "<a class='adjacentR' href='${base}/jsp/sgwk/videoDetail.jsp?number="+nextStr+"'>"+nextStr+"</a>";
                                } else {
                                    adjacentR = "<a class='noneR'>无</a>";
                                }


                                $(".adjacent").append(adjacentL+adjacentR);

                                userId = data.id;

                                locationUrl = apiUrl+"/jsp/sgwk/videoDetail.jsp?id="+userId;
                                wxtitle = data.number+"号#"+data.title+"#";
                                wximgUrl = apiUrl+"/jsp/sgwk/images/sgLogo.png";
                                wxdesc = "快来围观，为我投票吧！";
                                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)

                            }else{
                                
                            }
                        }
                    }
                });
            }


            function videoDetailByNumber(openId,number){
                $.ajax({
                    url: "${base}/video_match/detail",
                    type: "post",
                    data: {openId:openId,number:number},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                $(".speakerCode").text(data.number);
                                $(".speakerDepartment").text(data.uGroup);
                                $(".speakerName").text("主讲："+data.lecturer);

                                if ($("#microClassVideo").length==0) {
                                    $(".microClassVideo").prepend("<video id='microClassVideo' class='video-js' controls preload='auto' poster='"+data.videoImg+"'>"+
                                        "<source src='"+data.videoUrl+"' type='video/mp4'>"+
                                    "</video>");
                                }
                                

                                videojs(microClassVideo).ready(function(){});

                                $(".microClassTitle").text("课题："+data.title);
                                $(".microClassSummary p").remove();
                                $(".microClassSummary").append("<p>"+data.detail+"</p>");
                                var content = $(".microClassSummary p");
                                var rowNum = Math.round(content.height()/parseFloat(content.css('line-height')));
                                if (rowNum>2) {
                                    $(".microClassSummary").addClass('summary-off').append("<a>更多详情</a>");
                                }


                                $(".vd-ballot p").text(data.ballot+"票");
                                $(".vd-rank p").text("第"+data.ranking+"名");


                                if (data.extVote == "1") {
                                    $(".vd-vote").addClass("hasVote").find("span").text("已投");
                                }

                                var adjacentL = "";
                                var adjacentR = "";

                                var beforeStr = "";
                                var nextStr = "";


                                if (parseInt(data.number) != 1) {
                                    var beforeNum = parseInt(data.number) - 1;
                                    if (beforeNum.toString().length == 1) {
                                        beforeStr = "00" + beforeNum.toString();
                                    } else if (beforeNum.toString().length == 2) {
                                        beforeStr = "0" + beforeNum.toString();
                                    } else {
                                        beforeStr = beforeNum.toString();
                                    }

                                    adjacentL = "<a class='adjacentL' href='${base}/jsp/sgwk/videoDetail.jsp?number="+beforeStr+"'>"+beforeStr+"</a>";
                                } else {
                                    adjacentL = "<a class='noneL'>无</a>";
                                }

                                if (parseInt(data.number) != 60) {
                                    var nextNum = parseInt(data.number) + 1;
                                    if (nextNum.toString().length == 1) {
                                        nextStr = "00" + nextNum.toString();
                                    } else if (nextNum.toString().length == 2) {
                                        nextStr = "0" + nextNum.toString();
                                    } else {
                                        nextStr = nextNum.toString();
                                    }

                                    adjacentR = "<a class='adjacentR' href='${base}/jsp/sgwk/videoDetail.jsp?number="+nextStr+"'>"+nextStr+"</a>";
                                } else {
                                    adjacentR = "<a class='noneR'>无</a>";
                                }


                                $(".adjacent").empty();
                                $(".adjacent").append(adjacentL+adjacentR);
                                
                                userId = data.id;

                                locationUrl = apiUrl+"/jsp/sgwk/videoDetail.jsp?id="+userId;
                                wxtitle = data.number+"号#"+data.title+"#";
                                wximgUrl = apiUrl+"/jsp/sgwk/images/sgLogo.png";
                                wxdesc = "快快快！这么赞的课程，肯定要支持的啊！";
                                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
                            }else{
                                
                            }
                        }
                    }
                });
            }



            function vote(openId,videoId){
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
                                $(".vd-vote").find("img").addClass("voting");
                                $("body").append("<div class='voteAnimate'>"+
                                    "<img class='voteAnimate1' src='${base}/jsp/sgwk/images/voteAnimate1.png' />"+
                                    "<img class='voteAnimate2' src='${base}/jsp/sgwk/images/voteAnimate2.png' />"+
                                "</div>");

                                setTimeout(function(){
                                    $(".vd-vote").addClass("hasVote").find("span").text("已投");
                                    videoDetailById(openId,videoId);
                                    $(".voteAnimate").remove();
                                }, 2000);
                                
                                
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

            var userId = "";

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";

            var endTime = 1526202000000;
            var isEnd = false;
            
            $(function() {
                // FastClick.attach(document.body); 
        
                // var notNeed = FastClick.notNeeded(document.body);
                // $.fn.triggerFastClick=function(){
                //     this.trigger("click");
                //         if(!notNeed){
                //         this.trigger("click");
                //     }
                // }
                wScrollFix(document.querySelector(".microClassBg"), true);

                if (endTime-new Date().getTime()<=0) {
					isEnd = true;
				} else {
                    $(".vd-ballot").after("<a class='vd-vote'><span>投票</span><img src='${base}/jsp/sgwk/images/microClassPraise.png' /></a>")
                }

                var Url = location.search;
				if (Url.indexOf("?")!=-1){
                    var id = "";
                    var number = "";
                    var source = "";
					
					if (Url.indexOf("%3F")!=-1) {
						var Request = new getAuthQueryString();
                        id = Request.id;
                        if (id !=undefined && id != null && id.indexOf("&appid") != -1) {
                            var appInd = id.indexOf("&appid");
                            id = id.substr(0,appInd)
                        }
                        number = Request.number;
                        if (number !=undefined && number != null && number.indexOf("&appid") != -1) {
                            var appInd = number.indexOf("&appid");
                            number = number.substr(0,appInd)
                        }
					} else {
                        id = getQueryString("id");
                        number = getQueryString("number");
                        source = getQueryString("source");
                    }
                }

                if (source == "share") {
                    $("#layer").show();
                    $("body").append("<img class='sharePic' style='position:fixed;z-index:100;right:0;top:0;width:17.8rem;' src='${base}/jsp/sgwk/images/sharePic.png' />");

                    $("body").on("click","#layer,.sharePic",function(){
                        $("#layer").hide();
                        $(".sharePic").remove();
                    })
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
                        if (id != undefined && id != null) {
                            wxAuthorization("sgwk/videoDetail.jsp?id="+id);
                        } else {
                            wxAuthorization("sgwk/videoDetail.jsp?number="+number);
                        }
					}
                }



                if (id != undefined && id != null) {
                    videoDetailById(openId,id);
                } else {
                    videoDetailByNumber(openId,number);
                }

                
                // 投票
                $(".rankInfo").on("click",".vd-vote",function(){
                    if (!$(this).hasClass("hasVote")) {
                        if (endTime-new Date().getTime()<=0){
                            location.href = apiUrl + "/jsp/sgwk/index.jsp";
                        } else {
                            vote(openId,userId);
                        }
                    }
                    
                })


                $("#layer").click(function(){
                    if ($(".voteToast").length != 0) {
                        $("#layer").hide();
                        $(".voteToast").remove();
                    }
                })


                $(".microClassSummary").on("click","a",function(){
					if ($(this).parent().hasClass("summary-off")) {
						$(this).text("收起").parent().removeClass("summary-off");
					} else {
						$(this).text("更多详情").parent().addClass("summary-off");
					}
				});
                
            })
        </script>
    </body>
</html>