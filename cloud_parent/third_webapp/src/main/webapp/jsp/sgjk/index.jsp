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
        <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgjk/css/global.css?v=20" />
    </head>
    <body>
        <div id="layer"></div>
        <div class="answerPop">
            <a class="closeBtn"></a>
            <h1></h1>

            <div class="QACont">
                <p class="question"></p>
                <ul>
                    <li></li>
                    <li></li>
                </ul>
                <input type="hidden" name="" id="rightAns" />
                <input type="hidden" name="" id="sort" />
            </div>
        </div>

        <div class="clockList">
            <a class="closeBtn"></a>
            <img class="clockTitle" src="${base}/jsp/sgjk/images/clockTitle.png" alt="" />
            <ul>
                <li data-code="04-20"><div>4月20</div></li>
                <li data-code="04-21"><div>4月21</div></li>
                <li data-code="04-22"><div>4月22</div></li>
                <li data-code="04-23"><div>4月23</div></li>
                <li data-code="04-24"><div>4月24</div></li>
                <li data-code="04-25"><div>4月25</div></li>
                <li data-code="04-26"><div>4月26</div></li>
                <li data-code="04-27"><div>4月27</div></li>
                <li data-code="04-28"><div>4月28</div></li>
                <li data-code="04-29"><div>4月29</div></li>
                <li data-code="04-30"><div>4月30</div></li>
                <li data-code="05-01"><div>5月1</div></li>
                <li data-code="05-02"><div>5月2</div></li>
                <li data-code="05-03"><div>5月3</div></li>
                <li data-code="05-04"><div>5月4</div></li>
                <li data-code="05-05"><div>5月5</div></li>
                <li data-code="05-06"><div>5月6</div></li>
                <li data-code="05-07"><div>5月7</div></li>
                <li data-code="05-08"><div>5月8</div></li>
                <li data-code="05-09"><div>5月9</div></li>
                <li data-code="05-10"><div>5月10</div></li>
            </ul>
            <img class="clockTip" src="${base}/jsp/sgjk/images/clockTip.gif" alt="">
        </div>

        <div class="healthClock">
            <img class="homeTitle" src="${base}/jsp/sgjk/images/homeTitle.png" alt="" srcset="">
            <img class="bgImg1" src="${base}/jsp/sgjk/images/bgImg1.png" alt="" srcset="">
            <img class="bgImg2" src="${base}/jsp/sgjk/images/bgImg2.png" alt="" srcset="">
            <!-- <h1 class="clockNum"></h1> -->
            <h2 class="expiryDate">活动截止时间2018年5月10日17点</h2>

            <a href="${base}/jsp/sgjk/description.jsp" class="hintBtn"></a>

            <div class="healthLegend" style="display: none;">
                <div class="waterFrame">
                    <div class="water">
                        <div class="drinking">
                            <h1>点击每杯水，开启健康每一天</h1>
                            <ul class="waterList">
                                <li class="waterListL">
                                    <a class="waterBtn"><img src="${base}/jsp/sgjk/images/water1.png" alt=""></a>
                                    <a class="waterBtn"><img src="${base}/jsp/sgjk/images/water1.png" alt=""></a>
                                    <a class="waterBtn"><img src="${base}/jsp/sgjk/images/water1.png" alt=""></a>
                                </li>
                                <li class="waterListR">
                                    <a class="waterBtn"><img src="${base}/jsp/sgjk/images/water1.png" alt=""></a>
                                    <a class="waterBtn"><img src="${base}/jsp/sgjk/images/water1.png" alt=""></a>
                                    <a class="waterBtn"><img src="${base}/jsp/sgjk/images/water1.png" alt=""></a>
                                </li>
                            </ul>
                            <img class="drinkStatus" src="${base}/jsp/sgjk/images/drinkStatus1.png" alt="">
                            <a class="shareForAass" href="${base}/jsp/sgjk/personalPage.jsp">分享获助力</a>
                        </div>
                        <div class="finished">
                            <h1 class="finishedTitle">
                                今日健康饮水量<br />
                                已达标
                            </h1>

                            <img class="personalTip" src="${base}/jsp/sgjk/images/personalTip.png" alt="">
                            <div class="healthTip">
                                <img class="tipTitle" src="${base}/jsp/sgjk/images/healthTipTitle.png" alt="">
                                <p class="tipContent"></p>
                                <a class="shareBtn" href="${base}/jsp/sgjk/personalPage.jsp">分享获助力</a>
                            </div>
                        </div>
                        
                    </div>
                </div>

                <div class="healthValueFrame">
                    <em class="leftRope1"></em>
                    <em class="leftRope2"></em>
                    <div class="healthValue">
                        <h3>健康值</h3>
                        <p></p>
                    </div>
                </div>

                <div class="totalDaysFrame">
                    <em class="rightRope1"></em>
                    <em class="rightRope2"></em>
                    <div class="totalDays">
                        <h3>累计打卡</h3>
                        <p><i></i><i></i>天</p>
                    </div>
                </div>
            </div>

            <a class="joinBtn" href="${base}/jsp/sgjk/signInPage.jsp" style="display: none;">我要开启</a>

            <div class="activityEnd">
                <a class="endBtn">活动已结束</a>
                <p>健康的生活习惯需要持之以恒的坚持，21天以后的日子里要继续努力哦！获奖名单将会在3-7个工作日内发布，愿大家每天都能收获健康与快乐！</p>
            </div>


            <div class="rankingList">
                <div class="rankTop">
                    <h1 class="rankTitle">排行榜</h1>
                    <div class="searchFrame">
                        <div class="searchBar">
                            <em></em>
                            <input type="text" placeholder="搜索部门/姓名" />
                        </div>
                        <a class="searchTxt">搜索</a>
                    </div>
                    
                </div>

                <div class="myRank" style="display: none;">
                    <i class="rankNum"></i>
                    <img class="headImg" src="" alt="" srcset="">
                    <div class="rankInfo">
                        <h2></h2>
                        <p></p>
                    </div>
                    <p class="assistanceNum"></p>
                </div>

                <ul>
                    <!-- <li>
                        <i class="rankNum"><img src="${base}/jsp/sgjk/images/first.png"></i>
                        <img class="headImg" src="" alt="" srcset="">
                        <div class="rankInfo">
                            <h2>张筱雨</h2>
                            <p>张家港市供电公司</p>
                        </div>
                        <p class="assistanceNum">1736</p>
                        <a class="assistance"></a>
                    </li>
                    <li>
                        <i class="rankNum"><img src="${base}/jsp/sgjk/images/second.png"></i>
                        <img class="headImg" src="" alt="" srcset="">
                        <div class="rankInfo">
                            <h2>张筱雨</h2>
                            <p>张家港市供电公司</p>
                        </div>
                        <p class="assistanceNum">1736</p>
                        <a class="assistance"></a>
                    </li>
                    <li>
                        <i class="rankNum"><img src="${base}/jsp/sgjk/images/third.png"></i>
                        <img class="headImg" src="" alt="" srcset="">
                        <div class="rankInfo">
                            <h2>张筱雨</h2>
                            <p>张家港市供电公司</p>
                        </div>
                        <p class="assistanceNum">1736</p>
                        <a class="assistance"></a>
                    </li>
                    <li>
                        <i class="rankNum">4</i>
                        <img class="headImg" src="" alt="" srcset="">
                        <div class="rankInfo">
                            <h2>张筱雨</h2>
                            <p>张家港市供电公司</p>
                        </div>
                        <p class="assistanceNum">1736</p>
                        <a class="assistance"></a>
                    </li> -->
                
                </ul>
            </div>
        </div>

        <script src="${base}/jsp/sgjk/js/jquery.min.js"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <script src="${base}/jsp/sgjk/js/fastclick.js"></script>
        <script src="${base}/jsp/sgjk/js/index.js?v=20"></script>
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
                        // $(".clockNum").text("已有"+data.data+"人开启打卡");
                        if (isEnd) {
                            if(data.code == "1"){
                                isLogin = true;
                                $(".myRank").show();
                                pensonalInfo(openId,openId);
                            } else {
                                locationUrl = apiUrl+"/jsp/sgjk/index.jsp";
                                wxtitle = "#21天健康打卡计划#今天的排行榜，有你吗？";
                                wximgUrl = apiUrl+"/jsp/sgjk/images/sgLogo.png";
                                wxdesc = "快看！大家都有多健康！——5980服务工程";
                                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
                            }

                            $(".activityEnd").show();
                        } else {
                            if(data.code == "1"){
                                isLogin = true;
                                $(".healthLegend,.myRank").show();
                                isClock(openId);
                                QTlist(openId);
                                pensonalInfo(openId,openId);
                            } else if(data.code == "0") {
                                locationUrl = apiUrl+"/jsp/sgjk/index.jsp";
                                wxtitle = "#21天健康打卡计划#今天的排行榜，有你吗？";
                                wximgUrl = apiUrl+"/jsp/sgjk/images/sgLogo.png";
                                wxdesc = "快看！大家都有多健康！——5980服务工程";
                                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)
                                if (data.data == 0) {
                                    $(".expiryDate").css("margin-bottom","3.2rem");
                                    $(".rankingList").append("<div class='noOne'><p>快来！还没人开启计划！</p><p>你将会是排行榜第一名！！！</p><p>神秘大奖在投向你的怀抱！</p><a href='${base}/jsp/sgjk/signInPage.jsp'>发起健康计划</a></div>")
                                } else {
                                    $(".joinBtn").show();
                                }
                                
                            }
                        }
                        

                        
                        
                    }
                });
            }

            // 判断是否签到
            function isClock(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectClockDate",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.code == "0"){
                            clockDay(openId);
                        }else{
                            
                        }
                    }
                });
            }

            function pensonalInfo(openId,openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectAllWater",
                    type: "post",
                    data: {openId:openId,beOpenId:openId},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                var clockDay = data.clickDay.toString();
                                if (clockDay.length < 2) {
                                    $(".totalDays p i").eq(0).text("0");
                                    $(".totalDays p i").eq(1).text(clockDay);
                                } else {
                                    var clock1 = clockDay.split("")[0];
                                    var clock2 = clockDay.split("")[1];
                                    $(".totalDays p i").eq(0).text(clock1);
                                    $(".totalDays p i").eq(1).text(clock2);
                                }
                            
                                var rankNum = "";
                                if (data.rowNo == 1) {
                                    rankNum = "<img src='images/first.png'>"
                                } else if (data.rowNo == 2) {
                                    rankNum = "<img src='images/second.png'>"
                                } else if (data.rowNo == 2) {
                                    rankNum = "<img src='images/third.png'>"
                                } else {
                                    rankNum = data.rowNo;
                                }

                                $(".myRank .rankNum").empty();
                                $(".myRank .rankNum").prepend(rankNum);
                                $(".myRank .headImg").attr("src",data.wechatHeadUrl);
                                $(".myRank .rankInfo h2").text(data.nickName);
                                $(".myRank .rankInfo p").text(data.department);

                                var waterMl = 0;
                                if (data.waterMl!=null) {
                                    waterMl = data.waterMl;
                                }
                                $(".healthValue p").text(waterMl);
                                $(".myRank .assistanceNum").text(waterMl);
                                
                                var assistance = "";
                                if (data.isPrise == 1) {
                                    assistance = "<a class='assistance hasAssistance myRankBtn'></a>";
                                } else {
                                    assistance = "<a class='assistance myRankBtn'></a>";
                                }
                                
                                $(".myRank .assistance").remove();
                                $(".myRank").append(assistance);

                                locationUrl = apiUrl+"/jsp/sgjk/personalPage.jsp?userId="+openId;
                                wxtitle = "@"+data.nickName+"的21天健康打卡计划#第"+data.clickDay+"天#，快快快助力我的坚持！";
                                wximgUrl = apiUrl+"/jsp/sgjk/images/sgLogo.png";
                                wxdesc = "每天做5个健康问答，喝掉6杯水，学健康知识";
                                wxShare(locationUrl,wxtitle,wximgUrl,wxdesc)

                            }else{
                                
                            }
                        }
                    }
                });
            }


            // 打卡列表
            function clockDay(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/insertClickDay",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                
                                hasClock = true;

                                var data = data.data;

                                $(".clockList ul li").each(function(){
                                    var clockDate = $(this).data("code");
                                    for (var i = 0; i < data.length; i++) {
                                        var dateArr = data[i].cDate.split("-");
                                        var subDate = dateArr[1] + "-" + dateArr[2]
                                        if (clockDate == subDate) {
                                            $(this).find("div").css("background-color","#79ad4d");
                                            $(this).append("<p>+20</p>");
                                        }
                                        
                                    }
                                });

                                $(".clockList,#layer").show();

                                pensonalInfo(openId,openId);
                                healthRank("",openId,1);


                            }else{
                                
                            }
                        }
                    }
                });
            }

            // 查询喝水列表
            function QTlist(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectAnswerQuestion",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;

                                if (data.length != 6) {
                                    var sortNum = data.length + 1;
                                    $("#sort").val(sortNum);
                                   
                                    $(".drinkStatus").attr("src","${base}/jsp/sgjk/images/drinkStatus"+sortNum+".png")
                                        
                                    $(".drinking").show();
                                    if (data.length != 0) {
                                        $.each(data,function(i,item){
                                            if (item.sort == "waterListL0") {
                                                $(".waterListL a").eq(0).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                            } else if (item.sort == "waterListL1") {
                                                $(".waterListL a").eq(1).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                            } else if (item.sort == "waterListL2") {
                                                $(".waterListL a").eq(2).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                            } else if (item.sort == "waterListR0") {
                                                $(".waterListR a").eq(0).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                            } else if (item.sort == "waterListR1") {
                                                $(".waterListR a").eq(1).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                            } else if (item.sort == "waterListR2") {
                                                $(".waterListR a").eq(2).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                            }
                                        });
                                    }
                                } else {
                                    QandT(openId);
                                    $(".finished").show();
                                }

                                

                            }else{
                                
                            }
                        }
                    }
                });
            }


            // 喝水
            function drinkWater(openId,questionNum,sort,_this){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/updateUserWaterMl",
                    type: "post",
                    data: {openId:openId,questionNum:questionNum,sort:sort,water:40},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                var _thisL = _this.offset().left;
                                var _thisT = _this.offset().top - 10;

                                $("body").append("<img class='healthToast40' src='${base}/jsp/sgjk/images/health40.png' />");

                                setTimeout(function(){
                                    $(".healthToast40").remove();
                                }, 2000);

                                if (sort == "waterListL0") {
                                    $(".waterListL a").eq(0).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                } else if (sort == "waterListL1") {
                                    $(".waterListL a").eq(1).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                } else if (sort == "waterListL2") {
                                    $(".waterListL a").eq(2).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                } else if (sort == "waterListR0") {
                                    $(".waterListR a").eq(0).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                } else if (sort == "waterListR1") {
                                    $(".waterListR a").eq(1).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                } else if (sort == "waterListR2") {
                                    $(".waterListR a").eq(2).removeClass("waterBtn").find("img").attr("src","${base}/jsp/sgjk/images/water2.png")
                                }

                                var sortNum = parseInt($("#sort").val()) + 1;

                                $("#sort").val(sortNum);

                                $(".drinkStatus").attr("src","${base}/jsp/sgjk/images/drinkStatus"+sortNum+".png")
                                
                                pensonalInfo(openId,openId);
                                healthRank("",openId,1);

                                QandT(openId);
                            }else{
                                
                            }
                        }
                    }
                });
            }

            // 问题/TIPS
            function QandT(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectQuestionAndTips",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data[0];

                                if (data.type == "question") {
                                    $(".answerPop h1").text("今日问答/"+data.sort);
                                    $(".answerPop .QACont .question").text(data.content);
                                    $(".answerPop .QACont ul li").eq(0).text(data.answer1);
                                    $(".answerPop .QACont ul li").eq(1).text(data.answer2);
                                    $("#rightAns").val(parseInt(data.correctAnswer)-1);
                                    $(".answerPop,#layer").show();
                                } else {
                                    $(".finished .healthTip p").text(data.content);
                                    $(".drinking").hide();
                                    $(".finished").show();
                                }
                            }else{
                                
                            }
                        }
                    }
                });
            }


            // 提交答案
            function answerQuestion(openId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/updateAnswerQuestion",
                    type: "post",
                    data: {openId:openId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;

                                $("body").append("<img class='healthyToast10' src='${base}/jsp/sgjk/images/healthy10.png' />");

                                setTimeout(function(){
                                    $(".healthyToast10").remove();
                                }, 2000);

                                setTimeout(function(){
                                    $(".answerPop,#layer").hide();
                                    $(".answerPop .QACont ul li").removeClass("rightAns");
                                    $(".answerPop .QACont ul li").removeClass("wrongAns");
                                    $(".answerPop .QACont ul li img").remove();
                                    pensonalInfo(openId,openId);
                                    healthRank("",openId,1);
                                }, 1000);
                                

                            }else{
                                
                            }
                        }
                    }
                });
            }



            // 排行榜
            function healthRank(serachCode,openId,pageNo){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectAllUserRank",
                    type: "post",
                    data: {serachCode:serachCode,openId:openId,pageSize:10,pageNo:pageNo},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data.dataList;
                                pageNum = 1;
                                $(".noResult").remove();
                                if (data.length == 0 && isLogin) {
                                    $(".rankingList ul").empty();
                                    $(".rankingList").append("<p class='noResult' style='line-height:8rem;text-align:center;'>未搜到相关计划</p>")

                                } else {

                                    if (data.length == 10) {
                                        hasNext = true;
                                        pageNum++;
                                    } else {
                                        hasNext = false;
                                    }

                                    var html = "";
                                    $.each(data,function(i,item){
                                        var rankNum = "";
                                        if (item.rowNo == 1) {
                                            rankNum = "<img src='${base}/jsp/sgjk/images/first.png'>"
                                        } else if (item.rowNo == 2) {
                                            rankNum = "<img src='${base}/jsp/sgjk/images/second.png'>"
                                        } else if (item.rowNo == 3) {
                                            rankNum = "<img src='${base}/jsp/sgjk/images/third.png'>"
                                        } else {
                                            rankNum = item.rowNo;
                                        }

                                        var waterMl = 0;
                                        if (item.waterMl!=null) {
                                            waterMl = item.waterMl;
                                        }
                                        
                                        var assistance = "";
                                        if (item.isPrise == 1) {
                                            assistance = "<a class='assistance hasAssistance'></a>";
                                        } else {
                                            assistance = "<a class='assistance'></a>";
                                        }

                                    
                                        html += "<li data-userid='"+item.openId+"'>"+
                                            "<i class='rankNum'>"+rankNum+"</i>"+
                                            "<img class='headImg' src='"+item.wechatHeadUrl+"' />"+
                                            "<div class='rankInfo'>"+
                                                "<h2>"+item.nickName+"</h2>"+
                                                "<p>"+item.department+"</p>"+
                                            "</div>"+
                                            "<p class='assistanceNum'>"+waterMl+"</p>"+assistance+
                                        "</li>";
                                    
                                    });
                                    $(".rankingList ul").html(html);
                                }

                                
                                
                            }else{
                                
                            }
                        }
                    }
                });
            }


            // 助力
            function praiseUser(openId,beOpenId,_this){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectPraiseUser",
                    type: "post",
                    data: {openId:openId,beOpenId:beOpenId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data[0];
                                var waterNum = data.water;
                                var _thisL = _this.offset().left - 10;
                                var _thisT = _this.offset().top - 10;

                                waterToast(waterNum,_thisL,_thisT);

                                _this.addClass("hasAssistance");
                                pensonalInfo(beOpenId,beOpenId);
                                
                                if (_this.hasClass("myRankBtn")) {
                                    healthRank(searchCont,openId,1);
                                } else {
                                    var assistanceNum = parseInt(_this.siblings(".assistanceNum").text())+waterNum; 
                                    _this.siblings(".assistanceNum").text(assistanceNum);
                                }
                                
                            }else{
                                
                            }
                        } else {
                            if(data.code == "0"){
                                $("#layer").show();
                                praiseToast();
                            }
                        }
                    }
                });
            }


            var hasClock = false;

            var isLogin = false;

            var hasNext = false;
            var pageNum = 1;
            var searchCont = "";

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";

            var isEnd = false;
            
            
            $(function() {

                if (endTime-new Date().getTime()<=0) {
                    $(".rankingList").addClass("hasEnd");
					isEnd = true;
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
						wxAuthorization("sgjk/index.jsp");
					}
                }
                
                isLoginUser(openId)
                
                healthRank("",openId,1);

                var rankT = $(".rankingList").offset().top;
                $(window).scroll(function(){
                    var clockT = $(window).scrollTop();
                    if (clockT >= rankT) {
                        $(".rankTop").css("position","fixed");
                        $(".rankingList").css("padding-top","8.3rem");
                    } else {
                        $(".rankTop").css("position","unset");
                        $(".rankingList").css("padding-top","0");
                    }
                })


                // 喝水答题
                $(".waterList .waterBtn").click(function(){
                    if ($(this).hasClass("waterBtn")) {
                        hasEnd();
                        var _this = $(this);
                        var sort = $(this).parent().attr("class") + $(this).index();
                        var questionNum = $("#sort").val();
                        drinkWater(openId,questionNum,sort,_this);
                    }
                    
                })
                
                $(".answerPop .QACont ul li").click(function(){
                    hasEnd();
                    var ansInd = $(this).index();
                    var rightAns = $("#rightAns").val();
                    if (!$(".answerPop .QACont ul li").hasClass("rightAns") && !$(".answerPop .QACont ul li").hasClass("wrongAns")) {
                        if (ansInd == rightAns) {
                            $(this).addClass("rightAns");
                            $(this).append("<img src='${base}/jsp/sgjk/images/rightAns.png' />");
                        } else {
                            $(this).addClass("wrongAns");
                            $(this).siblings().append("<img src='${base}/jsp/sgjk/images/rightAns.png' />");
                        }

                        var questionNum = $("#sort").val();
                        var sort = $("#waterPos").val();

                        answerQuestion(openId);
                    }
                    
                })

                $(".answerPop .closeBtn").click(function(){
                    $(".answerPop,#layer").hide();
                })


                // 打卡列表
                $(".totalDays").click(function(){
                    if (hasClock) {
                        $(".clockList,#layer").show();
                    } else {
                        clockDay(openId);
                    }
                })

                $(".clockList .closeBtn").click(function(){
                    $(".clockList,#layer").hide();
                })


                // 助力
                $(".rankingList ul").on("click","li .assistance",function(e){
                    hasEnd();
                    e.stopPropagation();
                    if (!$(this).hasClass("hasAssistance")) {
                        var userId = $(this).parents("li").data("userid");
                        var _this = $(this);
                        praiseUser(userId,openId,_this);
                    }
                    
                });


                $(".myRank").on("click",".assistance",function(e){
                    hasEnd();
                    e.stopPropagation();
                    if (!$(this).hasClass("hasAssistance")) {
                        var _this = $(this);
                        praiseUser(openId,openId,_this);
                        
                    }
                    
                });
                


                // 搜索
                $(".searchBar em,.searchTxt").click(function(){
                    searchCont = $(".searchBar input").val();
                    if (searchCont.trim() == "") {
                        if (isLogin) {
                            $(".myRank").show();
                        }
                        
                    } else {
                        $(".myRank").hide();
                    }
                    

                    healthRank(searchCont,openId,1);
                })


                $(".rankingList ul").on("click","li",function(){
                    if (!$(event.target).is('.assistance')){
                        if (!$(".rankingList").hasClass("hasEnd")) {
                            var userId = $(this).data("userid");
                            location.href="${base}/jsp/sgjk/personalPage.jsp?userId="+userId;
                        }
                        
                    }
                    
                });


                $(window).scroll(function(){
				　　var scrollTop = $(this).scrollTop();
				　　var scrollHeight = $(document).height();
				　　var windowHeight = $(this).height();
				　　if(scrollTop + windowHeight == scrollHeight&&hasNext){
                　　　　 ajaxUpLoad(searchCont,openId,pageNum);
				　　}
				});

            })


            function ajaxUpLoad(serachCode,openId,pageNo){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectAllUserRank",
                    type: "post",
                    data: {serachCode:serachCode,openId:openId,pageSize:10,pageNo:pageNo},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data.dataList;
                                if (data.length == 0) {
                                    hasNext = false;
                                } else {
                                    if (data.length == 10) {
                                        hasNext = true;
                                        pageNum++;
                                    } else {
                                        hasNext = false;
                                    }

                                    var html = "";
                                    $.each(data,function(i,item){
                                        var rankNum = item.rowNo;

                                        var waterMl = 0;
                                        if (item.waterMl!=null) {
                                            waterMl = item.waterMl;
                                        }
                                        
                                        var assistance = "";
                                        if (item.isPrise == 1) {
                                            assistance = "<a class='assistance hasAssistance'></a>";
                                        } else {
                                            assistance = "<a class='assistance'></a>";
                                        }

                                    
                                        html += "<li data-userid='"+item.openId+"'>"+
                                            "<i class='rankNum'>"+rankNum+"</i>"+
                                            "<img class='headImg' src='"+item.wechatHeadUrl+"' />"+
                                            "<div class='rankInfo'>"+
                                                "<h2>"+item.nickName+"</h2>"+
                                                "<p>"+item.department+"</p>"+
                                            "</div>"+
                                            "<p class='assistanceNum'>"+waterMl+"</p>"+assistance+
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