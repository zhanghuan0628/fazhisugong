<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>催眠曲</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body class="lullaby">
		<div class="lullabyBg"></div>
		<div class="lullabyMusic">
			<div class="musicFrame">
				<div class="fmWrap"><div class="musicFm"></div></div>
				<div class="musicCover">
					<em class="play"></em>
				</div>
			</div>
			
			<h1></h1>
		</div>
		<audio class="fmUrl" src=""></audio>
		<div class="lullabyBtn">
			<a class="lullabyDownLoad" onclick="more()">下载飞凡心理APP，聆听更多</a>
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function fmDetail(id){
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
			                	
			                	var imgUrl = "";
			                	if (data.backUrl!=null && data.backUrl!="") {
			                		imgUrl = data.backUrl;
			                	} else {
			                		imgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/ffxl/share/share_video.png"
			                	}
			                	
			                	$(".lullabyBg").css("background-image","url("+imgUrl+")");
			                	$(".musicFm").css("background-image","url("+imgUrl+")");
								$(".lullabyMusic h1").text(data.title);
								$(".fmUrl").attr("src",data.fmUrl);
								audioObj = $(".fmUrl")[0];
								
								locationUrl = apiUrl+"/jsp/ffxl/share/lullaby.jsp?id="+id;
								wxtitle = data.title;
								wximgUrl = imgUrl;
								wxdesc = "";
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			var audioObj;
			var play = false;
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			
			var isPlaying = false;

			var container = document.querySelector('.fmWrap');
			var image = container.querySelector('.musicFm');
				
			function pauseMusic(){
				audioObj.pause();
				if (audioObj.paused) {
					isPlaying = false;
					var iTransform = getComputedStyle(image).transform;
					var cTransform = getComputedStyle(container).transform;
					container.style.transform = cTransform === 'none'
				     ? iTransform
				     : iTransform.concat(' ', cTransform);
					image.classList.remove('lullabyPlay');
					$(".lullabyMusic em").removeClass("stop").addClass("play");
				}
					
			}
			
			function playMusic(){
				audioObj.play();
				if (audioObj.played) {
					isPlaying = true;
					image.classList.add('lullabyPlay');
					$(".lullabyMusic em").removeClass("play").addClass("stop");
				}
					
			}
			
			$(function(){
				var id = getQueryString("id");
//				var id = "065c0460b98a426eb699749c86c302c3";
				fmDetail(id);
				
//				$(".lullabyMusic em").click(function(){
//					if ($(this).hasClass("play")) {
//						audioObj.play();
//						if (audioObj.played) {
//							$(this).removeClass("play").addClass("stop");
//							$(".musicFm").addClass("lullabyPlay");
//						}
//					} else {
//						audioObj.pause();
//						if (audioObj.paused) {
//							$(this).removeClass("stop").addClass("play");
//							$(".musicFm").removeClass("lullabyPlay");
//						}
//					}
//				});
				
				
				
				$(".lullabyMusic em").click(function(){
					isPlaying?pauseMusic():playMusic();
				});
				
				
				
				$(".fmUrl").on("ended",function(){
					$(".lullabyMusic em").removeClass("stop").addClass("play");
					$(".musicFm").removeClass("lullabyPlay");
				});
				
				
			})
			
			
		</script>
			
	</body>
</html>
