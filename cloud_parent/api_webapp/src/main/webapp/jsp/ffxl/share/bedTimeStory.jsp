<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>催眠故事</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=1"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
		<div id="downLoadLine">
			<img src="images/wxLogo.png"/>
			<a onclick="more()">下载APP</a>
		</div>
		
		<div class="bedTimeStoryAudio">
			<div class="progressBar">
				<div>
					<em></em>
				</div>
			</div>
			<p class="currentTime"></p>
			<p class="totalTime"></p>
			<em class="btsPlay"></em>
		</div>
		
		<audio id="audio" src=""></audio>
		
		<div class="bedTimeStory">
			<h1></h1>
			<div class="bedTimeStoryCont">
				
			</div>
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			var apiUrl = "http://"+window.location.host+"/sleep";
			function bedTimeStory(id){
				$.ajax({
			        url: apiUrl+"/api_fm/fm_detail",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	$(".bedTimeStoryAudio").css("background-image","url("+data.backUrl+")")
			                	$(".bedTimeStory h1").text(data.title);
			                	$(".bedTimeStory .bedTimeStoryCont").html(data.content);
			                	
			                	$("#audio").attr("src",data.fmUrl);
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
			                	
			                	var reTag = /<(?:.|\s)*?>/g;
			                	var content = data.content.replace(reTag,"");
			                	
			                	locationUrl = apiUrl+"/jsp/ffxl/share/bedTimeStory.jsp?id="+id;
								wxtitle = data.title;
								wximgUrl = apiUrl+"/jsp/ffxl/share/images/wxShare.png";
								wxdesc = content;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
								
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
				console.log(totalTime);
	            setTimeout(function () {  
	                totalTime = audioObj.duration;
	                if(isNaN(totalTime)){
	                	play = false;
	                    getTime();  
	                }  
	                else{  
	                    play = true;
	                    console.log(totalTime);
	                    
						$(".audioPlayer .uploadAudio").hide();
						$(".audioPlayer .haveAudio").show();
	                    
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
//				var id = "00ac73e7757948cabb3c190e0032c504"
				bedTimeStory(id);
				
				var timer = "";
				$(".bedTimeStoryAudio>em").click(function(){
					if (play) {
						if ($(this).hasClass("btsPlay")) {
							$(this).removeClass("btsPlay").addClass("btsStop");
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
							$(this).removeClass("btsStop").addClass("btsPlay");
							audioObj.pause();
							clearInterval(timer);
						}
					}
					
				})
				
				
				$("#audio").on("ended",function(){
					$(".bedTimeStoryAudio>em").removeClass("btsStop").addClass("btsPlay");
					
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
				
			})
		</script>
	</body>
</html>
