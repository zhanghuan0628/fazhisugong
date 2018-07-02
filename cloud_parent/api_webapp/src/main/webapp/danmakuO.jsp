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
			<template v-for="(to,index) in list">
				<li v-bind:class="{'status-my':userId==to.userBaseId, 'status-counselor':to.roleName=='counselor', 'status-parent':to.roleName=='parent', 'status-teacher':to.roleName=='teacher', 'status-student':to.roleName=='student' }">
					<div class="danmaku-portrait"><img :src="to.headImg.indexOf('http')==-1?'assets/images/headImg/'+to.headImg+'.png':to.headImg"/></div><div class="danmaku-video" v-bind:class="{'danmaku-lentht-1':to.voiceLength<10, 'danmaku-lentht-2':to.voiceLength>=10 && to.voiceLength<20, 'danmaku-lentht-3':to.voiceLength>=20}">
						<input class="danmaku-video-checkbox" type="checkbox" :value="to.voiceSrc" />
						<div class="danmaku-video-icon"><span class="danmaku-video-lenght">{{ to.voiceLength }}</span>s</div>
						<img class="danmaku-video-my" src="assets/images/fxf-audio-right.png"/>
					</div>
				</li>
			</template>
		</ul>
<!-- 		<input type="button" @click="addData()" name="" id="" value="真数据+1" style="width: 100%; height: 40px; background-color: #007AFF; color: #fff; font-size: 18px; margin: 20px 0;" /> -->
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
	
	//点击播放弹幕的停止滚动
	$(function(){
		var qId = getQueryString("questionId");
		if (qId!=null){
			getBg(qId);
		} else {
			$("#danmaku").css("background-image","url(assets/images/fxf-bg.png)");
		}
		
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
	
	var savedata=[];
	var fakedata=[];
	var fakedataH=1;
	
	var loadData=[];
	var dataLen =0;
	var sua = new Vue({
	    el: '#danmaku',
		data:{
			list:[],
			userId:''
		},
		created:function(){
			var _this=this
			_this.userId=myId=getQueryString("userBaseId")
			
			$.ajax({
	            type:'POST',
	            url:baseUrl+"/fxf_question/get_dm_questions",
	            data:{id:getQueryString("questionId")},
	            success:function(result){
	            	fakedata=result.data;
	            	loadData = result.data;
	            	dataLen = fakedata.length;
	            	//fakedata=[];
	            	//取到数据并赋予list
//	            	console.log(JSON.stringify(fakedata, null,2))
					if (dataLen!=0) {
						$(".danmaku-main .nodanmu,#layer").hide();
					} else {
						$(".danmaku-main .nodanmu,#layer").show();
					}
	            	
	            	setTimeout(function(){
                        _this.list=_this.list.concat(fakedata.slice(0, 1)) //选定第一个数据放到list
                        savedata=_this.list.concat(fakedata.slice(0, 1)) //记录第一个值
                        fakedata.splice(0,1)//清除第一个值
                    },getRandNum(0,2888))
                    setTimeout(function(){
                        _this.list=_this.list.concat(fakedata.slice(0, 1))
                        savedata=_this.list.concat(fakedata.slice(0, 1))
                        fakedata.splice(0,1)
                    },getRandNum(0,2888))
                    setTimeout(function(){
                        _this.list=_this.list.concat(fakedata.slice(0, 1))
                        savedata=_this.list.concat(fakedata.slice(0, 1))
                        fakedata.splice(0,1)
                    },getRandNum(0,2888))
                    setTimeout(function(){
                        _this.list=_this.list.concat(fakedata.slice(0, 1))
                        savedata=_this.list.concat(fakedata.slice(0, 1))
                        fakedata.splice(0,1)
                    },getRandNum(0,2888))
                    setInterval(function(){
                        fakedataH=fakedata.length
                      console.log("fakedataH------------------"+fakedataH)
                      console.log("savedata------------------"+savedata.length)
                        if(fakedataH==0){
//                          console.log(fakedata)
                            fakedata=fakedata.concat(savedata)
                            //fakedata=savedata
                            savedata=[]
                          console.log("sss--------------"+savedata.length)
                        }else{
                            setTimeout(function(){
                                _this.list=_this.list.concat(fakedata.slice(0, 1))
                                //savedata=_this.list.concat(fakedata.slice(0, 1))
                                savedata=_this.list.slice(0, dataLen)
                                fakedata.splice(0,1)
                            },getRandNum(0,2888))
                            setTimeout(function(){
                                _this.list=_this.list.concat(fakedata.slice(0, 1))
                                //savedata=_this.list.concat(fakedata.slice(0, 1))
                              savedata=_this.list.slice(0, dataLen)
                                fakedata.splice(0,1)
                            },getRandNum(0,2888))
                            setTimeout(function(){
                                _this.list=_this.list.concat(fakedata.slice(0, 1))
                                //savedata=_this.list.concat(fakedata.slice(0, 1))
                               savedata=_this.list.slice(0, dataLen)
                                fakedata.splice(0,1)
                            },getRandNum(0,2888))
                            setTimeout(function(){
                                _this.list=_this.list.concat(fakedata.slice(0, 1))
                                //savedata=_this.list.concat(fakedata.slice(0, 1))
                               savedata=_this.list.slice(0, dataLen)
                                fakedata.splice(0,1)
                            },getRandNum(0,2888))
                        }
                        
                    },5000)
	            	
	            	var webSocket =null;
	            	//webSocket监听实时弹幕
					var startWebSocket = new WebSocket('ws${urlBasePath}webSocketController'); 
					webSocket = startWebSocket;
                    var sid=null;
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
						var sid = getQueryString("userBaseId");
						if(sid == "" || sid == null)
							 sid = new Date().getTime();
						
						console.log(sid)
                          webSocket.send(sid);
                          heartCheck.start();
					};
					//监听到数据的时候
					webSocket.onmessage = function(event) {
//						console.log("收到新数据："+JSON.stringify(JSON.parse(event.data), null,2))
						console.log("有新数据啦！")
						//fakedata = [];
						console.log(JSON.parse(event.data))
						console.log(getQueryString("userBaseId"))
                        if(JSON.parse(event.data).voiceSrc != undefined && JSON.parse(event.data) != getQueryString("userBaseId"))
                            fakedata.push(JSON.parse(event.data));
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
                            sid = getQueryString("userBaseId");
                            if(sid == "" || sid == null)
                                  sid = new Date().getTime();
                            console.log(sid) 
                            reconnectWebSocket.send(sid);
                              heartCheck.start();
                        };
                        //监听到数据的时候
                        reconnectWebSocket.onmessage = function(event) {
//                        console.log("收到新数据："+JSON.stringify(JSON.parse(event.data), null,2))
                            console.log("有新数据啦！")
                            //fakedata = [];
                            if(JSON.parse(event.data).voiceSrc != undefined)
                                fakedata.push(JSON.parse(event.data));
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
                                	var sid = getQueryString("userBaseId");
                                	if(sid == "" || sid == null || sid == 'null')
                                        sid = new Date().getTime();
                                	console.log(sid)
                                    webSocket.send(sid);
                                }, this.timeout)
                            }
                        }
	            	
	            }
	        })
			
			
        },
        methods: {
        	addData:function(){
        		$.ajax({
		            type:'POST',
		            url:baseUrl+"/fxf_question/save_dm",
		            data:{
		            	userBaseId:"2fbe1d1d478e45fab1ea59325eb556a3",
		            	roleName:"parent",
		            	headImg:"parentF",
		            	voiceSrc:"http://www.w3school.com.cn/i/horse.ogg",
		            	voiceLength:"002"
		            	
		            },
		            success:function(result){
		            	//取到数据并赋予list
		            	console.log("发送成功")
		            }
		        })
        	}
		}
	})
	</script>
</body>
</html>
