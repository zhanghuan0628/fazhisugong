<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>心理FM</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
	</head>
	<body style="padding-bottom: 5rem;">
		
		<div class="floor">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
			
		<div class="FMchannel">
			<img src=""/>
			<p></p>
			<a>订阅</a>
		</div>
		
		<div class="FMcontent">
			<div class="FMpic"></div>
			<div class="FMcontrol">
				<div class="progressBar">
					<div>
						<em></em>
					</div>
				</div>
				
				<p class="currentTime"></p>
				<p class="totalTime"></p>
				
				<em class="FMplay"></em>
				
				<a class="FMcollect"></a>
				<a class="FMshare"></a>
			</div>
		</div>
		
		<audio id="audio" src=""></audio>
		
		<div class="comment-list">
			<h1></h1>
			<ul>
				<!--<li>
					<h2><img src=""/> <span>张筱雨</span></h2>
					<div class="comment-content">
						<p class="commentMain">孤独感不是年轻人独有的，但青年时期更容易产生孤独情绪。</p>
						<ul class="commentReply">
							<li><span>@陈思：</span>你的孤独虽败犹荣你的孤独虽败犹荣你的孤独犹荣</li>
						</ul>
					</div>
					
					<div class="comment-data clearfix">
						<p>2017.10.22 8:22</p>
						<div>
							<span><img src="images/cl-comment.png"/><i>2</i></span>
							<span><img src="images/cl-praise.png"/><i>2</i></span>
						</div>
					</div>
				</li>
				
				<li>
					<h2><img src=""/> <span>张筱雨</span></h2>
					<div class="comment-content">
						<p class="commentMain">孤独感不是年轻人独有的，但青年时期更容易产生孤独情绪。</p>
					</div>
					
					<div class="comment-data clearfix">
						<p>2017.10.22 8:22</p>
						<div>
							<span><img src="images/cl-comment.png"/><i>2</i></span>
							<span><img src="images/cl-praise.png"/><i>2</i></span>
						</div>
					</div>
				</li>-->
			</ul>
		</div>
		
		
		<div class="reviewBox">
			<p><input type="text" name="" id="" value="" placeholder="发表评论..." /></p>
		</div>
		
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			
			function getStationById(id){
				$.ajax({
			        url: apiUrl+"/fxf_station/getStationById",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	$(".FMchannel img").attr("src",data.headImg);
			                	$(".FMchannel p").text(data.nickname);
			                	$(".FMcontent .FMpic").css("background-image","url("+data.cover+")");
			                	
			                	$("#audio").attr("src",data.mediaSrc);
			                	
			                	audioObj = $("#audio")[0];
			                	if (isWeChat()) {
			                		document.addEventListener("WeixinJSBridgeReady", function () { 
				                		audioObj.play();
				                		audioObj.pause();
								        getTime();
								    }, false); 
			                	} else {
			                		getTime();
			                	}
			                	
			                	
			                	locationUrl = apiUrl+"/jsp/fxf/share/psyFM.jsp?id="+id;
								wxtitle = data.title;
				            	wximgUrl= data.cover;
				            	wxdesc = data.content;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			评论
			function getFMComment(questionId){
				$.ajax({
			        url: apiUrl+"/fxf_question/getAnswerAnswer",
			        type: "post",
			        data: {questionId:questionId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	if (data.counts!=0){
			                		$(".comment-list>h1").text("全部评论("+data.counts+")");
			                	} else {
			                		$(".comment-list>h1").text("全部评论");
			                	}
			                	
			                	var html = "";
			                	$.each(data.answers, function(i,item) {
			                		
			                		var reply = "";
			                		if (item.answers.length!=0) {
			                			var replylist = ""
			                			for (var j=0;j<item.answers.length;j++) {
			                				replylist += "<li><span>@"+item.answers[j].nickname+"：</span>"+item.answers[j].content+"</li>"
			                			}
			                			reply = "<ul class='commentReply'>"+replylist+"</ul>"
			                		}
			                		
			                		var time = ""
				                	var obj = new Date(item.date);
			                		var oYear = obj.getFullYear();
			                		var oMonth = obj.getMonth()+1;
			                		var oDate = obj.getDate();
			                		var oHour = obj.getHours();
		                			var oMinute = obj.getMinutes();
			                		time = oYear+"."+getzf(oMonth)+"."+getzf(oDate)+" "+getzf(oHour)+":"+getzf(oMinute);
			                		
			                		
			                		html += "<li>"+
										"<h2><img src='"+item.headImg+"'/> <span>"+item.nickname+"</span></h2>"+
										"<div class='comment-content'>"+
											"<p class='commentMain'>"+item.content+"</p>"+reply+
										"</div>"+
										
										"<div class='comment-data clearfix'>"+
											"<p>"+time+"</p>"+
											"<div>"+
												"<span><img src='images/cl-comment.png'/><i>"+item.answers.length+"</i></span>"+
												"<span><img src='images/cl-praise.png'/><i>"+item.praises+"</i></span>"+
											"</div>"+
										"</div>"+
									"</li>"
			                	});
			                	
			                	$(".comment-list ul").html(html);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			function formatSeconds(value) {  
			    var theTime = parseInt(value);// 秒  
			    var theTime1 = 0;// 分  
				var result = "";
			    if(theTime > 60) {  
			        theTime1 = parseInt(theTime/60);  
			        theTime = parseInt(theTime%60);  
		            if(theTime1 > 60) {  
		            	theTime1 = parseInt(theTime1%60);  
		            }  
			    }  
				if (parseInt(theTime)<10) {
					result = "00:0"+parseInt(theTime); 
				} else{
					result = "00:"+parseInt(theTime); 
				}
		        
		        if(theTime1 > 0) {
					if (theTime1<10) {
						if (parseInt(theTime)<10) {
							result = "0"+parseInt(theTime1)+":0"+parseInt(theTime); 
						} else{
							result = "0"+parseInt(theTime1)+":"+parseInt(theTime); 
						}
					} else{
						if (parseInt(theTime)<10) {
							result = parseInt(theTime1)+":0"+parseInt(theTime); 
						} else{
							result = parseInt(theTime1)+":"+parseInt(theTime); 
						}
					}
		        }  
			    return result;  
			}  
			
			
			function getTime() {
	            setTimeout(function () {  
	                totalTime = audioObj.duration;
	                if(isNaN(totalTime)){
	                	play = false;
	                    getTime();  
	                }  
	                else{  
	                    play = true;
	                    
					    var totalTimeF =  audioObj.duration.toFixed(0);
					    $(".currentTime").text(formatSeconds(currentTime));
						$(".totalTime").text(formatSeconds(totalTimeF)); 
						
	                }  
	            }, 10);  
	        }  
	        
	        
	        var totalTime = "";
			var currentTime = 0;
			var audioObj;
			var play = false;
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
				var id = getQueryString("id");
				getStationById(id);
				getFMComment(id);
				
				var timer = "";
				$(".FMcontrol>em").click(function(){
					if (play) {
						if ($(this).hasClass("FMplay")) {
							$(this).removeClass("FMplay").addClass("FMstop");
							audioObj.play();
							timer = setInterval(function(){
								if (currentTime == totalTime) {
									clearInterval(timer);
									$(".currentTime").text(formatSeconds(totalTime));
									return false;
								}
								$(".currentTime").text(formatSeconds(currentTime.toFixed(0)));
								$(".progressBar div").css("width",currentTime*100/totalTime+"%");
								
							},500)
						} else {
							$(this).removeClass("FMstop").addClass("FMplay");
							audioObj.pause();
							clearInterval(timer);
						}
					}
					
				})
				
				
				$("#audio").on("ended",function(){
					$(".FMcontrol>em").removeClass("FMstop").addClass("FMplay");
					
				});
				
				
				$("#audio").on("timeupdate",function(){
					currentTime = audioObj.currentTime;
				})
				
				
				$(".progressBar").click(function(e){
					var progressBarW = $(this).width();
					var offsetL = $(this).offset().left;
					var distance = e.pageX-offsetL;
					var percent = distance/progressBarW
					audioObj.currentTime = totalTime*percent;
					var currentTimeChange = totalTime*percent;
					$(".currentTime").text(formatSeconds(currentTimeChange.toFixed(0)));
					$(".progressBar div").css("width",currentTimeChange*100/totalTime+"%");
				})
				
				$(".reviewBox input").focus(function(){
					more();
				})
			})
		</script>
	</body>
</html>