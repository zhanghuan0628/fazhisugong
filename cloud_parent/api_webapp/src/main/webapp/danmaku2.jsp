<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setAttribute("base",  request.getContextPath());%>
<%
	String basePath = request.getContextPath();
	String urlBasePath = "://" + request.getServerName() + ":" + request.getServerPort()
			+ basePath + "/";
	request.setAttribute("urlBasePath",  urlBasePath);
%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<title>弹幕</title>
	<link rel="stylesheet" href="assets/css/jquery-weui.min.css"/>
	
	<style type="text/css">
		* {
		  box-sizing: border-box;
		  -webkit-text-size-adjust: none;
		  margin: 0px;
		  padding: 0px;
		}
		
		html {
		  font-size: 20px;
		  -webkit-tap-highlight-color: transparent;
		  -webkit-font-smoothing: antialiased;
		}
		
		@media only screen and (min-width: 400px) {
		  html {
		    font-size: 21.33px !important;
		  }
		}
		@media only screen and (min-width: 414px) {
		  html {
		    font-size: 22.08px !important;
		  }
		}
		@media only screen and (min-width: 480px) {
		  html {
		    font-size: 25.6px !important;
		  }
		}
		body {
		  font-family: 'Arial', 'Microsoft YaHei', sans-seri;
		  color: #333;
		  font-size: 16px;
		  line-height: 1.5;
		}
		
		article,
		aside,
		details,
		figcaption,
		figure,
		footer,
		header,
		hgroup,
		nav,
		section,
		summary {
		  display: block;
		}
		
		i,
		b {
		  font-weight: normal;
		  font-style: normal;
		}
		
		a {
		  text-decoration: none;
		}
		
		img {
		  vertical-align: middle;
		  border: 0;
		}
		
		ul,
		ol {
		  list-style: none;
		}
		
		table {
		  border-collapse: collapse;
		  border-spacing: 0;
		}
		
		textarea,
		select,
		button,
		input {
		  border: 0;
		  background: none;
		}
		
		textarea,
		select,
		button,
		input,
		input[type="button"],
		input[type="reset"],
		input[type="submit"] {
		  -webkit-appearance: none;
		}
		
		textarea {
		  resize: none;
		}
		
		input:disabled {
		  opacity: .9;
		}
		
		input[type="button"]:active {
		  opacity: .8;
		}
		
		.fl {
		  float: left;
		}
		
		.fr {
		  float: right;
		}
		
		.clear {
		  clear: both;
		}
		
		.none {
		  display: none;
		}
		
		.clearfix:after {
		  content: "\200B";
		  display: block;
		  height: 0;
		  clear: both;
		}
		
		.clearfix {
		  *zoom: 1;
		}
		
		.scroll-touch {
		  -webkit-overflow-scrolling: touch;
		}
		.scroll-touch::-webkit-scrollbar {
		  display: none;
		}
		
		[v-cloak] {
		  display: none;
		}
		
		.danmaku-main {
		  height: 216px;
		  background-position: center;
		  background-size: cover;
		  /*background-image: url(../images/fxf-bg.png);*/
		  position: relative;
		}
		
		#layer {position: absolute;top: 0;left: 0;right:0;bottom:0;z-index:10;background-color:#000000;-moz-opacity: 0.5;opacity:.50;filter: alpha(opacity=50);display: none;}
		
		.danmaku-main .nodanmu {
			position: absolute;
			color: #FFFFFF;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			text-align: center;
			z-index: 20;
			display: none;
		}
		
		.danmaku-main .nodanmu p {
			line-height: 26px;
		}
		
		.danmaku-main .nodanmu p:first-child {
			margin-top: 80px;
		}
		
		.danmaku-list {
		  position: relative;
		  overflow: hidden;
		  width: 100%;
		  height: 100%;
		}
		.danmaku-list li {
		  overflow: hidden;
		  vertical-align: top;
		  display: inline-block;
		  white-space: nowrap;
		  overflow: hidden;
		  line-height: 0;
		  position: absolute;
		  left: 100vw;
		  z-index: 1;
		  height: 28px;
		}
		.danmaku-list .move {
			-webkit-animation: danmaku 20s linear 0s forwards;
          	animation: danmaku 20s linear 0s forwards;
		}
		
		.danmaku-list li.status-my {
		  z-index: 5;
		}
		.danmaku-list li:nth-child(4n+1) {
		  top: 20px;
		}
		.danmaku-list li:nth-child(4n+2) {
		  /*-webkit-animation: danmaku 18s linear 0s forwards;
		          animation: danmaku 18s linear 0s forwards;*/
		  top: 68px;
		}
		.danmaku-list li:nth-child(4n+3) {
		  top: 116px;
		}
		.danmaku-list li:nth-child(4n+4) {
		  top: 164px;
		}
		.danmaku-list li.status-counselor .danmaku-video {
		  background-color: #acd4eb;
		}
		.danmaku-list li.status-counselor .danmaku-video:before {
		  border-right: 7px solid #acd4eb;
		}
		.danmaku-list li.status-counselor .danmaku-video-icon {
		  background-image: url(../images/fxf-audio-2.png);
		  color: #FFFFFF;
		}
		.danmaku-list li.status-parent .danmaku-video {
		  background-color: #4ab689;
		}
		.danmaku-list li.status-parent .danmaku-video:before {
		  border-right: 7px solid #4ab689;
		}
		.danmaku-list li.status-parent .danmaku-video-icon {
		  background-image: url(../images/fxf-audio-2.png);
		  color: #FFFFFF;
		}
		.danmaku-list li.status-teacher .danmaku-video {
		  background-color: #ffd9d9;
		}
		.danmaku-list li.status-teacher .danmaku-video:before {
		  border-right: 7px solid #ffd9d9;
		}
		.danmaku-list li.status-teacher .danmaku-video-icon {
		  background-image: url(../images/fxf-audio-1.png);
		  color: #452609;
		}
		.danmaku-list li.status-student .danmaku-video {
		  background-color: #fcf187;
		}
		.danmaku-list li.status-student .danmaku-video:before {
		  border-right: 7px solid #fcf187;
		}
		.danmaku-list li.status-student .danmaku-video-icon {
		  background-image: url(../images/fxf-audio-1.png);
		  color: #452609;
		}
		.danmaku-list li.pauseAnimation {
		  -webkit-animation-play-state: paused;
		          animation-play-state: paused;
		  z-index: 6;
		}
		.danmaku-list li.pauseAnimation.status-teacher .danmaku-video-icon, .danmaku-list li.pauseAnimation.status-student .danmaku-video-icon {
		  background-image: url(../images/fxf-audio-1s.gif);
		}
		.danmaku-list li.pauseAnimation.status-counselor .danmaku-video-icon,.danmaku-list li.pauseAnimation.status-parent .danmaku-video-icon {
		  background-image: url(../images/fxf-audio-2s.gif);
		}
		
		@-webkit-keyframes danmaku {
		  100% {
		    left: -100vw;
		  }
		}
		
		@keyframes danmaku {
		  100% {
		    left: -100vw;
		  }
		}
		.danmaku-portrait {
		  position: relative;
		  display: inline-block;
		  height: 28px;
		}
		.danmaku-portrait img {
		  width: 28px;
		  height: 28px;
		  border-radius: 50%;
		}
		
		.status-my .danmaku-portrait:after {
		  border: 2px solid #fe3664;
		  content: '';
		  position: absolute;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 28px;
		  border-radius: 100%;
		  z-index: 2;
		  box-sizing: border-box;
		}
		.status-my .danmaku-video:after {
		  border: 2px solid #fe3664;
		  content: '';
		  position: absolute;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 28px;
		  border-radius: 4px;
		  z-index: 2;
		  box-sizing: border-box;
		}
		.status-my .danmaku-video-my {
		  display: block;
		}
		
		.danmaku-video {
		  position: relative;
		  display: inline-block;
		  margin-left: 10px;
		  border-radius: 4px;
		  padding: 0 10px;
		  height: 28px;
		  line-height: 28px;
		}
		.danmaku-video.danmaku-lentht-1 {
		  width: 70px;
		}
		.danmaku-video.danmaku-lentht-2 {
		  width: 80px;
		}
		.danmaku-video.danmaku-lentht-3 {
		  width: 90px;
		}
		.danmaku-video:before {
		  content: '';
		  position: absolute;
		  z-index: 3;
		  top: 50%;
		  margin-top: -4px;
		  left: -5px;
		  width: 0;
		  height: 0;
		  border-top: 4px solid transparent;
		  border-right: 7px solid red;
		  border-bottom: 4px solid transparent;
		}
		
		.danmaku-video-checkbox {
		  position: absolute;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 28px;
		  opacity: 0;
		  z-index: 10;
		  border-radius: 4px;
		}
		
		.danmaku-video-icon {
		  background-position: 0px 9px;
		  background-size: 8px;
		  background-repeat: no-repeat;
		  margin-right: 5px;
		  font-size: 12px;
		  padding-left: 15px;
		  line-height: 28px;
		}
		
		.danmaku-video-my {
		  position: absolute;
		  z-index: 8;
		  top: 50%;
		  width: 7px;
		  margin-top: -4px;
		  left: -5px;
		  display: none;
		}
	</style>
</head>
<body>
	<div id="danmaku" class="danmaku-main">
		<div id="layer">
		</div>
		<div class="nodanmu">
			<p>每天早上六点</p>
			<p>抢发语音弹幕上首页</p>
		</div>
		<ul class="danmaku-list">
		</ul>
		<audio id="myVideo">
			<source src="" type="video/mp3"></source>
		</audio> 
	</div>
	<script src="assets/js/jquery/jquery.min.js"></script>
	<script src="assets/js/vue/vue.min.js"></script>
	<script src="assets/js/weui/jquery-weui.min.js"></script>
	<script>
	
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	}
	//全局变量
	var baseUrl='${base}'//接口路径
	
	//获取随机数
	function getRandNum(min,max){
		return (Math.floor(Math.random()*(max-min+1)+min));
	}
	
	
//	获取背景图
	function getBg(qId){
		$.ajax({
	        url: baseUrl+"/fxf_question/getDmById",
	        type: "post",
	        data: {id:qId},
	        dataType: "json",
	        async:false,
	        success: function(data) {
	        	if(data.success){
	                if(data.code == "2000"){
	                	var data = data.data[0];
	                	var img = "";
	                	if (data.img!=null&&data.img!="") {
	                		img = data.img;
	                	} else {
	                		img = "assets/images/fxf-bg.png";
	                	}
	                	$("#danmaku").css("background-image","url("+img+")");
	                }else{
	                	
	                }
	            }
	        }
		})
	}
	
	function loopList(uId,data){
		$.each(data, function(i,item) {
			var liClass = "";
			if (uId == item.userBaseId) {
				liClass = "status-my";
			} else if (item.roleName == "counselor") {
				liClass = "status-counselor";
			} else if (item.roleName == "parent") {
				liClass = "status-parent";
			} else if (item.roleName == "teacher") {
				liClass = "status-teacher";
			} else if (item.roleName == "student") {
				liClass = "status-student";
			}
			
			var headImg = "";
			if (item.headImg.indexOf('http')==-1) {
				headImg = "assets/images/headImg/"+item.headImg+".png";
			} else {
				headImg = item.headImg;
			}
			
			var videoClass = "";
			if (item.voiceLength < 10) {
				videoClass = "danmaku-lentht-1";
			} else if (item.voiceLength >= 10 && item.voiceLength < 20) {
				videoClass = "danmaku-lentht-2";
			} else {
				videoClass = "danmaku-lentht-3";
			}
			
			$(".danmaku-list").append("<li class='"+liClass+"'>"+
				"<div class='danmaku-portrait'><img src= '"+headImg+"'/></div>"+
				"<div class='danmaku-video' class='"+videoClass+"'>"+
					"<input class='danmaku-video-checkbox' type='checkbox' value='"+item.voiceSrc+"' />"+
					"<div class='danmaku-video-icon'><span class='danmaku-video-lenght'>"+item.voiceLength+"</span>s</div>"+
					"<img class='danmaku-video-my' src='assets/images/fxf-audio-right.png'/>"+
				"</div>"+
				"<input class='dataCode' type='hidden' value='"+dataCode+"' />"+
			"</li>");
			dataCode++;
		});
		
		$(".danmaku-list li").each(function(){
			var nth = $(this).index();
			var _this = $(this);
			if (nth<=3) {
				setTimeout(function(){
					_this.addClass("move");
                },getRandNum(0,2888))
			};
		})
		setInterval(function(){
			loop();
		},1000)
	}
	
	
	
	function loop(){
		
		$(".danmaku-list li").each(function(){
			var nth = parseInt($(this).find(".dataCode").val());
			var _left = $(this).css("left");
			var _this = $(this);
			var oRight = WinW-_this.offset().left-_this.width();
			
			if ($(".danmaku-list li").eq(0).css("left")== "-"+WinW+"px" && $(".danmaku-list li").eq(1).css("left")== "-"+WinW+"px" && $(".danmaku-list li").eq(2).css("left")== "-"+WinW+"px" && $(".danmaku-list li").eq(3).css("left")== "-"+WinW+"px"){
				$(".danmaku-list li").eq(0).remove();
				$(".danmaku-list li").eq(1).remove();
				$(".danmaku-list li").eq(2).remove();
				$(".danmaku-list li").eq(3).remove();
			}
			
			if (oRight>=50) {
				move(nth);
			};
		})
			
	}
	function move(nth){
		setTimeout(function(){
			$(".danmaku-list li").each(function(){
			console.log(nth+4)
				if ($(this).find(".dataCode").val()==nth+4) {
					$(this).addClass("move");
				}
			})
		},getRandNum(0,2888));
	}
	



	function getdanmaku(qId,uId){
		$.ajax({
	        url: baseUrl+"/fxf_question/get_dm_questions",
	        type: "post",
	        data: {id:qId},
	        dataType: "json",
	        async:false,
	        success: function(data) {
	        	if(data.success){
	                if(data.code == "2000"){
	                	dataList = data.data;
		            	dataLen = data.data.length;
		            	
		            	if (dataLen!=0) {
							$(".danmaku-main .nodanmu,#layer").hide();
						} else {
							$(".danmaku-main .nodanmu,#layer").show();
						}
						
						loopList(uId,dataList);
						
						setInterval(function(){
							if ($(".danmaku-list li").length == $(".danmaku-list .move").length) {
								loopList(uId,dataList);
							}
						},10000)
						
						
						
						
		            	
		            	var webSocket =null;
		            	//webSocket监听实时弹幕
//						var startWebSocket = new WebSocket('ws${urlBasePath}webSocketController'); 
						var startWebSocket = new WebSocket('ws://101.132.143.75:8081/api/webSocketController'); 
						webSocket = startWebSocket;
	                  //关闭
	                    webSocket.onclose = function () {
	                        //重连
	                        reconnect();
	                    };
						//发生错误的时候
						webSocket.onerror = function(event) {
							console.log("错误啦："+event.data);
						};
						//打开链接成功的时候
						webSocket.onopen = function(event) {
							console.log("链接成功："+event)
							if(uId == "" || uId == null)
								 uId = new Date().getTime();
							
	                          webSocket.send(uId);
	                          heartCheck.start();
						};
						//监听到数据的时候
						webSocket.onmessage = function(event) {
	//						console.log("收到新数据："+JSON.stringify(JSON.parse(event.data), null,2))
							console.log("有新数据啦！")
							//fakedata = [];
							console.log(JSON.parse(event.data))
							if(JSON.parse(event.data).voiceSrc != undefined && JSON.parse(event.data) != uId) {
								var insert = "";
								for (var i = 0;i < $(".danmaku-list li").length;i++) {
									if (!$(".danmaku-list li").eq(i).hasClass("move")){
										insert = i;
										break;
									}
								}
								
								var liClass = "";
								if (uId == JSON.parse(event.data).userBaseId) {
									liClass = "status-my";
								} else if (JSON.parse(event.data).roleName == "counselor") {
									liClass = "status-counselor";
								} else if (JSON.parse(event.data).roleName == "parent") {
									liClass = "status-parent";
								} else if (JSON.parse(event.data).roleName == "teacher") {
									liClass = "status-teacher";
								} else if (JSON.parse(event.data).roleName == "student") {
									liClass = "status-student";
								}
								
								var headImg = "";
								if (JSON.parse(event.data).headImg.indexOf('http')==-1) {
									headImg = "assets/images/headImg/"+item.headImg+".png";
								} else {
									headImg = JSON.parse(event.data).headImg;
								}
								
								var videoClass = "";
								if (JSON.parse(event.data).voiceLength < 10) {
									videoClass = "danmaku-lentht-1";
								} else if (JSON.parse(event.data).voiceLength >= 10 && JSON.parse(event.data).voiceLength < 20) {
									videoClass = "danmaku-lentht-2";
								} else {
									videoClass = "danmaku-lentht-3";
								}
								
								var insertCode = parseInt($(".danmaku-list li").eq(insert).find(".dataCode").val());
								$(".danmaku-list li").eq(insertCode-1).after("<li class='"+liClass+"'>"+
									"<div class='danmaku-portrait'><img src= '"+headImg+"'/></div>"+
									"<div class='danmaku-video' class='"+videoClass+"'>"+
										"<input class='danmaku-video-checkbox' type='checkbox' value='"+JSON.parse(event.data).voiceSrc+"' />"+
										"<div class='danmaku-video-icon'><span class='danmaku-video-lenght'>"+JSON.parse(event.data).voiceLength+"</span>s</div>"+
										"<img class='danmaku-video-my' src='assets/images/fxf-audio-right.png'/>"+
									"</div>"+
									"<input class='dataCode' type='hidden' value='"+insertCode+"' />"+
								"</li>");
								dataList.push(JSON.parse(event.data));
								
								dataCode = dataCode+1;
								for (var i = insert+1;i < $(".danmaku-list li").length;i++) {
									var codePlus = parseInt($(".danmaku-list li").eq(i).find(".dataCode").val())+1;
									$(".danmaku-list li").eq(i).find(".dataCode").val(codePlus);
								}
								
		                        
		                        heartCheck.reset();
		                        if ($(".nodanmu").css("display")=="block") {
									$(".danmaku-main .nodanmu,#layer").hide();
		                        } 
							}
								
						};
						//重连
	                    function reconnect(){
	                        //
	                        var reconnectWebSocket = new WebSocket('ws${urlBasePath}webSocketController');
	                      //关闭
	                        reconnectWebSocket.onclose = function () {
	                            //重连
	                            reconnect();
	                        };
	                        //发生错误的时候
	                        reconnectWebSocket.onerror = function(event) {
	                            console.log("错误啦："+event.data);
	                        };
	                        //打开链接成功的时候
	                        reconnectWebSocket.onopen = function(event) {
	                            console.log("链接成功："+event)
	                            if(uId == "" || uId == null)
	                                  uId = new Date().getTime();
	                            reconnectWebSocket.send(uId);
	                              heartCheck.start();
	                        };
	                        //监听到数据的时候
	                        reconnectWebSocket.onmessage = function(event) {
	//                        console.log("收到新数据："+JSON.stringify(JSON.parse(event.data), null,2))
	                            console.log("有新数据啦！")
	                            //fakedata = [];
	                            if(JSON.parse(event.data).voiceSrc != undefined && JSON.parse(event.data) != uId) {
								var insert = "";
								for (var i = 0;i < $(".danmaku-list li").length;i++) {
									if (!$(".danmaku-list li").eq(i).hasClass("move")){
										insert = i;
										break;
									}
								}
								
								var liClass = "";
								if (uId == JSON.parse(event.data).userBaseId) {
									liClass = "status-my";
								} else if (JSON.parse(event.data).roleName == "counselor") {
									liClass = "status-counselor";
								} else if (JSON.parse(event.data).roleName == "parent") {
									liClass = "status-parent";
								} else if (JSON.parse(event.data).roleName == "teacher") {
									liClass = "status-teacher";
								} else if (JSON.parse(event.data).roleName == "student") {
									liClass = "status-student";
								}
								
								var headImg = "";
								if (JSON.parse(event.data).headImg.indexOf('http')==-1) {
									headImg = "assets/images/headImg/"+item.headImg+".png";
								} else {
									headImg = JSON.parse(event.data).headImg;
								}
								
								var videoClass = "";
								if (JSON.parse(event.data).voiceLength < 10) {
									videoClass = "danmaku-lentht-1";
								} else if (JSON.parse(event.data).voiceLength >= 10 && JSON.parse(event.data).voiceLength < 20) {
									videoClass = "danmaku-lentht-2";
								} else {
									videoClass = "danmaku-lentht-3";
								}
								
								var insertCode = parseInt($(".danmaku-list li").eq(insert).find(".dataCode").val());
								$(".danmaku-list li").eq(insertCode-1).after("<li class='"+liClass+"'>"+
									"<div class='danmaku-portrait'><img src= '"+headImg+"'/></div>"+
									"<div class='danmaku-video' class='"+videoClass+"'>"+
										"<input class='danmaku-video-checkbox' type='checkbox' value='"+JSON.parse(event.data).voiceSrc+"' />"+
										"<div class='danmaku-video-icon'><span class='danmaku-video-lenght'>"+JSON.parse(event.data).voiceLength+"</span>s</div>"+
										"<img class='danmaku-video-my' src='assets/images/fxf-audio-right.png'/>"+
									"</div>"+
									"<input class='dataCode' type='hidden' value='"+insertCode+"' />"+
								"</li>");
								dataList.push(JSON.parse(event.data));
								
								dataCode = dataCode+1;
								for (var i = insert+1;i < $(".danmaku-list li").length;i++) {
									var codePlus = parseInt($(".danmaku-list li").eq(i).find(".dataCode").val())+1;
									$(".danmaku-list li").eq(i).find(".dataCode").val(codePlus);
								}
								
		                        
		                        heartCheck.reset();
		                        if ($(".nodanmu").css("display")=="block") {
									$(".danmaku-main .nodanmu,#layer").hide();
		                        } 
							}
	                        };
	                        webSocket = reconnectWebSocket;
	                    }
	                    //心跳
	                    var heartCheck = {
	                            timeout: 6000,//60ms
	                            timeoutObj: null,
	                            serverTimeoutObj: null,
	                            reset: function(){
	                                clearTimeout(this.timeoutObj);
	                                clearTimeout(this.serverTimeoutObj);
	                                this.start();
	                            },
	                            start: function(){
	                                this.timeoutObj = setTimeout(function(){
	                                	if(uId == "" || uId == null || uId == 'null')
	                                        uId = new Date().getTime();
	                                    webSocket.send(uId);
	                                }, this.timeout)
	                            }
                        }
						
	                }else{
	                	
	                }
	            }
	        }
		})
	}
	
	//点击播放弹幕的停止滚动
	
	var dataList = [];
	var saveData = [];
	var dataLen = 0;
	
	var dataCode = 1;
	
	var WinW = Winw = $(window).width();
	$(window).resize(function(){
		Winw = $(window).width();
	});
	
	$(function(){
	
//		var qId = getQueryString("questionId");
//		var qId="9e48fcbf18b84c59ad2916f8ccb3e0cc";
		var qId="48c715ec49d048afa68bbc98b4743665";
		var uId = getQueryString("userBaseId");
		if (qId!=null){
			getBg(qId);
		} else {
			$("#danmaku").css("background-image","url(assets/images/fxf-bg.png)");
		}
		
		getdanmaku(qId,uId);
		
		$(document).on("click", ".danmaku-list li", function(){
			if($(this).hasClass("pauseAnimation")){
				$(this).removeClass("pauseAnimation")
				$("#myVideo").get(0).pause();
			}
			else{
				var s=$(this).find("input").val()
				$("#myVideo").attr("src",s)
				$("#myVideo").get(0).play();
				$("#myVideo")[0].addEventListener('ended',function(){
					$(".danmaku-list li").removeClass("pauseAnimation")
	    		});
				$(".danmaku-list li").removeClass("pauseAnimation")
				$(this).addClass("pauseAnimation")
			}
		})
		
		
	})
	
	</script>
</body>
</html>
