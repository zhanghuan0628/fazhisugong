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
        <link rel="stylesheet" type="text/css" media="screen" href="${base}/jsp/sgjk/css/global.css?v=27" />
    </head>
    <body>
        <div id="layer"></div>
        <div class="loadingCover">
            <p>计划开启中.</p>
        </div>

        <div class="healthClock" id="pageType1" style="display: none;">
            <img class="homeTitle" src="${base}/jsp/sgjk/images/homeTitle.png" alt="" srcset="">
            <a href="${base}/jsp/sgjk/index.jsp" class="rankBtn"></a>
            <img class="bgImg1" src="${base}/jsp/sgjk/images/bgImg1.png" alt="" srcset="">
            <img class="bgImg2" src="${base}/jsp/sgjk/images/bgImg2.png" alt="" srcset="">
            <h2 class="expiryDate">活动截止时间2018年5月10日17点</h2>
            <div class="healthLegend personalPage">
                <div class="waterFrame">
                    <div class="water">
                        <div class="myPage">
                            <img class="headImg" src="" alt="">
                            <img class="personalTip" src="${base}/jsp/sgjk/images/personalTip.png" alt="">
                            <div class="healthTip">
                                <img class="tipTitle" src="${base}/jsp/sgjk/images/healthTipTitle.png" alt="">
                                <p class="myTips"></p>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="healthValueFrame">
                    <em class="leftRope1"></em>
                    <em class="leftRope2"></em>
                    <div class="healthValue">
                        <div class="hvLeft">
                            <h3>健康值</h3>
                            <p></p>
                        </div>
                        <div class="hvRight">
                            <h3>排名</h3>
                            <p></p>
                        </div>
                        
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
        </div>


        <div id="pageType2" style="display: none;">
         
            <img class="headImg" src="" alt="">
            <ul class="personalInfo">
                <li>
                    <div>
                        <p></p>
                        <span>排名</span>
                    </div>
                </li>
                <li class="cumulativeDays">
                    <div>
                        <p></p>
                        <span>累计打卡天数</span>
                    </div>
                </li>
                <li>
                    <div>
                        <p></p>
                        <span>健康值</span>
                    </div>
                </li>
            </ul>
            <div class="healthTip">
                <img class="healthTipTitle" src="${base}/jsp/sgjk/images/healthTipTitle.png" alt="" />
                <p class="myTips"></p>
            </div>
            <img class='QRImg' src='' />
            <p class='helpScan'>扫码为我助力</p>
            <p class="QRCont">21天健康打卡计划</p>
            <img class="QRhidden" src="" alt="">
        </div>

        <div class="makePicZone">
            <h1 class="saveHint">长按图片保存</h1>

        </div>

        <script src="${base}/jsp/sgjk/js/jquery.min.js"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <script src="${base}/jsp/sgjk/js/aliyun-oss-sdk.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${base}/jsp/sgjk/js/html2canvas.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${base}/jsp/sgjk/js/fastclick.js"></script>
        <script src="${base}/jsp/sgjk/js/index.js?v=15"></script>
        <script>

            function toBlob(urlData,fileType){
		        var bytes=window.atob(urlData),
		            n=bytes.length,
		            u8arr=new Uint8Array(n);
		        while(n--){
		            u8arr[n]=bytes.charCodeAt(n);
		        }
		        return new Blob([u8arr],{type:fileType});
            }

            function getBase64Image(img) {  
				var canvas = document.createElement("canvas");  
				canvas.width = img.width;  
				canvas.height = img.height;  
				var ctx = canvas.getContext("2d");  
				ctx.drawImage(img, 0, 0, img.width, img.height);  
				var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();  
				var dataURL = canvas.toDataURL("image/"+ext);  
				return dataURL;  
            }
            
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
                            LoginHref = "<a href='${base}/jsp/sgjk/index.jsp'>我的计划</a>";
                        } else if(data.code == "0") {
                            LoginHref = "<a href='${base}/jsp/sgjk/signInPage.jsp'>我要开启</a>";
                        }
                    }
                });
            }

            // 个人信息
            function pensonalInfo(openId,beOpenId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectAllWater",
                    type: "post",
                    data: {openId:openId,beOpenId:beOpenId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;

                                if (beOpenId == undefined || openId == beOpenId) {
                                    $("#pageType2").show();
                                    makePic = true;
                                    var qrImg = new Image();  
                                    qrImg.setAttribute('crossOrigin', 'anonymous');
                                    qrImg.src = data.qrImg+"?"+new Date().getTime();
                                    qrImg.onload = function(){  
                                        var base64 = getBase64Image(qrImg);
                                        $(".QRImg").attr("src",base64);
                                    }  
                                    
                                } else {
                                    $("#pageType1").show();
                                    var helpPraise = ""
                                    if (data.isPrise == 1) {
                                        helpPraise = "<a class='helpPraise hasPraise'>已助力</a>";
                                    } else {
                                        helpPraise = "<a class='helpPraise'>给TA助力</a>";
                                    }
                                    $(".healthTip").append(helpPraise+"<p class='myPlan'>"+LoginHref+"</p>")
                                    $(".loadingCover").remove();
                                }

                                var headUrl = new Image();  
								headUrl.setAttribute('crossOrigin', 'anonymous');
                                if (data.wechatHeadUrl.indexOf("?")!=-1) {
                                    headUrl.src = data.wechatHeadUrl.split("?")[0]+"?"+new Date().getTime();
                                } else {
                                    headUrl.src = data.wechatHeadUrl+"?"+new Date().getTime();
                                }
                                
								headUrl.onload = function(){  
                                    var base64 = getBase64Image(headUrl);
									$(".headImg").attr("src",base64);
								}  

                                var waterMl = 0;
                                if (data.waterMl!=null) {
                                    waterMl = data.waterMl;
                                }
                                $(".healthValue .hvLeft p").text(waterMl);

                                $(".healthValue .hvRight p").text(data.rowNo);
                                
                                $(".personalInfo li").eq(0).find("p").text(data.rowNo);
                                $(".personalInfo li").eq(1).find("p").text(data.clickDay);
                                $(".personalInfo li").eq(2).find("p").text(waterMl);
                                var clockDay = data.clickDay.toString();
                                if (clockDay.length < 2) {
                                    $("#pageType1 .totalDays p i").eq(0).text("0");
                                    $("#pageType1 .totalDays p i").eq(1).text(clockDay);
                                    // $("#pageType2 .totalDays p i").eq(0).text("0");
                                    // $("#pageType2 .totalDays p i").eq(1).text(clockDay);
                                } else {
                                    var clock1 = clockDay.split("")[0];
                                    var clock2 = clockDay.split("")[1];
                                    $("#pageType1 .totalDays p i").eq(0).text(clock1);
                                    $("#pageType1 .totalDays p i").eq(1).text(clock2);
                                    // $("#pageType2 .totalDays p i").eq(0).text(clock1);
                                    // $("#pageType2 .totalDays p i").eq(1).text(clock2);
                                }


                                var shareId = "";
                                if (beOpenId == undefined) {
                                    shareId = openId;
                                } else {
                                    shareId = beOpenId;
                                }
                                
                                locationUrl = apiUrl+"/jsp/sgjk/personalPage.jsp?userId="+shareId;
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

            // 随机Tip
            function randomTips(){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectRandomTips",
                    type: "post",
                    data: {},
                    dataType: "json",
                    // async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;

                                $(".healthTip .myTips").text(data.content);
                                

                            }else{
                                
                            }
                        }
                    }
                });
            }


            // 助力
            function praiseUser(openId,beOpenId){
                $.ajax({
                    url: apiUrl+"/SgHealtherController/selectPraiseUser",
                    type: "post",
                    data: {openId:openId,beOpenId:beOpenId},
                    dataType: "json",
                    async:false,
                    success: function(data) {
                        if(data.success){
                            if(data.code == "2000"){
                                var data = data.data;
                                $(".helpPraise").addClass("hasPraise").text("已助力");
                                pensonalInfo(beOpenId,openId);

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


            var LoginHref = "";
            var makePic = false;

            var htmlPx = $("html").css("font-size")
			var remTurn = htmlPx.substr(0,htmlPx.length-2)

            var locationUrl = "";
            var wxtitle = "";
            var wximgUrl = "";
            var wxdesc = "";

            var loadingNth = 2;
            
            $(function() {
                hasEnd();

                var QRLeft = 4.7*remTurn + $(window).height()*0.2;

                $("#pageType2 .helpScan,#pageType2 .QRCont").css("left",QRLeft+"px")

				setInterval(function(){
					if (loadingNth == 7) {
						loadingNth = 2
					}
                    
                    if (loadingNth == 2) {
                        $(".loadingCover p").text("计划开启中..")
                    } else if (loadingNth == 3) {
                        $(".loadingCover p").text("计划开启中...")
                    } else if (loadingNth == 4) {
                        $(".loadingCover p").text("计划开启中....")
                    } else if (loadingNth == 5) {
                        $(".loadingCover p").text("计划开启中.....")
                    } else if (loadingNth == 6) {
                        $(".loadingCover p").text("计划开启中......")
                    }
                    
                    loadingNth++;
				},1000)
                
                var Url = location.search;
				if (Url.indexOf("?")!=-1){
                    var userId = "";
                    var type = ""
					
					if (Url.indexOf("%3F")!=-1) {
                        var Request = new getAuthQueryString();
                        userId = Request.userId;
                        if (userId.indexOf("&appid") != -1) {
                            var appInd = userId.indexOf("&appid");
                            userId = userId.substr(0,appInd)
                        }
                        type = Request.userId;
                        if (type.indexOf("&appid") != -1) {
                            var appInd = type.indexOf("&appid");
                            type = type.substr(0,appInd)
                        }
					} else {
                        userId = getQueryString("userId");
                        type = getQueryString("type");
                    }
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
                        if (type != undefined && type != null) {
                            wxAuthorization("sgjk/personalPage.jsp?userId="+userId+"@type="+type);
                        } else {
                            if (userId != undefined && userId != null) {
                                wxAuthorization("sgjk/personalPage.jsp?userId="+userId);
                            } else {
                                wxAuthorization("sgjk/personalPage.jsp");
                            }
                            
                        }
						
					}
                }
                
                if (openId == userId) {
                    location.href = "${base}/jsp/sgjk/index.jsp";
                    return false;
                }

                isLoginUser(openId);
                pensonalInfo(openId,userId);
                randomTips();

                $("body").on("click",".helpPraise",function(){
                    if(!$(this).hasClass("hasPraise")) {
                        hasEnd();
                        praiseUser(userId,openId)
                    }
                });


                if (makePic) {
                    
                    var i = 0;
                    $("#pageType2 .headImg,#pageType2 .QRImg").load(function(){
                        i++;
                        if (i == 2) {
                            html2canvas(document.querySelector("#pageType2")).then(function(canvas) {
                                var dataUrl = canvas.toDataURL();
                                $(".loadingCover").remove();
                                $(".makePicZone").show().append("<img src='"+dataUrl+"'>");

                                // var base64 = dataUrl.split(',')[1];
                                // var fileType = dataUrl.split(';')[0].split(':')[1];
                                
                                // var blob = toBlob(base64,fileType);
                                
                                // var reader = new FileReader();
                                // reader.readAsArrayBuffer(blob);
                                // reader.onload = function (event) {
                                //     //				oss配置
                                //     var ossClient = new OSS.Wrapper({
                                //         region: "oss-cn-shanghai",
                                //         accessKeyId: "LTAICG7rs8rsGNj4",
                                //         accessKeySecret: "FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm",
                                //         bucket: "ffxl"
                                //     });
                                    
                                //     // 文件名
                                //     var date = new Date();
                                //     var storeAs = 'wechat/21days/'+date.getTime()+'.'+blob.type.split('/')[1];
                                    
                                //     // arrayBuffer转Buffer
                                //     var buffer = new OSS.Buffer(event.target.result);
                                    
                                //     // 上传
                                //     ossClient.put(storeAs, buffer).then(function(result){
                                //         $(".loadingCover").remove();
                                //         $(".makePicZone").show().append("<img src='"+result.url+"'>");
                                //     }).catch(function(err){
                                //         if (userId != undefined && userId != null) {
                                //             location.href = apiUrl + "/jsp/sgjk/personalPage.jsp?userId="+userId;
                                //         } else {
                                //             location.href = apiUrl + "/jsp/sgjk/personalPage.jsp";
                                //         }
                                //         console.log(err);
                                //     });
                                // }
                            });
                        }
                            
                    })
                }
                

            })
        </script>
    </body>
</html>