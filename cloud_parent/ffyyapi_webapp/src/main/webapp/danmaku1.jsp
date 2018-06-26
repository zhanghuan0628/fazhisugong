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
	<link rel="stylesheet" href="assets/css/style1.css?v=1"/>
	
	<style type="text/css">
		
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
			"</li>")
			
			
			var num = $(".danmaku-list li").length-4;
			setTimeout(function(){
				for (var i = num-4;i<num;i++) {
					
				}
            },getRandNum(0,2888))
			
//			setTimeout(function(){
//				$(".danmaku-list").append("<li class='"+liClass+"'>"+
//					"<div class='danmaku-portrait'><img src= '"+headImg+"'/></div>"+
//					"<div class='danmaku-video' class='"+videoClass+"'>"+
//						"<input class='danmaku-video-checkbox' type='checkbox' value='"+item.voiceSrc+"' />"+
//						"<div class='danmaku-video-icon'><span class='danmaku-video-lenght'>"+item.voiceLength+"</span>s</div>"+
//						"<img class='danmaku-video-my' src='assets/images/fxf-audio-right.png'/>"+
//					"</div>"+
//				"</li>")
//          },getRandNum(0,2888))
		});
	}
	
	
//	获取弹幕
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
		            	dataLen = data.length;
		            	
		            	if (dataLen!=0) {
							$(".danmaku-main .nodanmu,#layer").hide();
						} else {
							$(".danmaku-main .nodanmu,#layer").show();
						}
						
						if (dataLen<=4) {
							loopList(uId,dataList);
							setInterval(function(){
								loopList(uId,dataList)
							},5000);
						} else {
							loopList(uId,dataList.slice(0,4));
							saveData = saveData.concat(dataList.slice(0,4));
							dataList.splice(0,4);
							setInterval(function(){
								if (dataList.length==0) {
									dataList = dataList.concat(saveData);
									saveData = [];
								}
								loopList(uId,dataList.slice(0,4));
								saveData = saveData.concat(dataList.slice(0,4));
								dataList.splice(0,4);
							},5000);
						}
						
						
						
		            	
		            	var webSocket =null;
		            	//webSocket监听实时弹幕
						var startWebSocket = new WebSocket('ws${urlBasePath}webSocketController'); 
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
	                        if(JSON.parse(event.data).voiceSrc != undefined && JSON.parse(event.data) != uId)
	                            dataList.unshift(JSON.parse(event.data));
	//                      _this.list=_this.list.concat(JSON.parse(event.data))
	                        heartCheck.reset();
	                        if ($(".nodanmu").css("display")=="block") {
								$(".danmaku-main .nodanmu,#layer").hide();
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
	                            if(JSON.parse(event.data).voiceSrc != undefined)
	                                dataList.unshift(JSON.parse(event.data));
	//                        _this.list=_this.list.concat(JSON.parse(event.data))
	                            heartCheck.reset();
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
	
	
	$(function(){
		var qId = getQueryString("questionId");
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
