<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>正念放松</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=1"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
		<div class="relaxBg"></div>
		<div id="downLoadLine">
			<img src="images/wxLogo.png"/>
			<a onclick="more()">下载APP</a>
		</div>
		<div class="relax">
			<h1 class="relaxTitle"><span></span></h1>
			
			<div class="relaxContent"></div>
		</div>
		
		<div class="relaxAudio">
			<div class="progressBar">
				<div>
					<em></em>
				</div>
			</div>
			<p class="currentTime"></p>
			<p class="totalTime"></p>
			<em class="relaxPlay"></em>
		</div>
		
		<audio id="audio" src=""></audio>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			var apiUrl = "http://"+window.location.host+"/sleep";
			function relax(id){
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
			                	$(".relaxTitle span").text(data.title);
			                	$(".relaxContent").html(data.content);
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
			                	
			                	
			                	locationUrl = apiUrl+"/jsp/ffxl/share/relax.jsp?id="+id;
								wxtitle = "我在飞凡心理APP里正在进行正念放松";
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
//				var id = "00ac73e7757948cabb3c190e0032c504"
				relax(id);
				
				var timer = "";
				$(".relaxAudio>em").click(function(){
					if (play) {
						if ($(this).hasClass("relaxPlay")) {
							$(this).removeClass("relaxPlay").addClass("relaxStop");
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
							$(this).removeClass("relaxStop").addClass("relaxPlay");
							audioObj.pause();
							clearInterval(timer);
						}
					}
					
				})
				
				
				$("#audio").on("ended",function(){
					$(".relaxAudio>em").removeClass("relaxStop").addClass("relaxPlay");
					
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
