<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>视频详情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/video-js.css"/>
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
	</head>
	<body style="padding-bottom: 4.9rem;">
		<div id="layer"></div>
		<div class="floor">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>

		<div class="video">
			
		</div>
		
		<div class="videoInfo">
			<h1></h1>
			<div class="videoIntro">
				<p class="oneLine"></p>
				<em></em>
			</div>
			
			<div class="videoData">
				<span class="collectNum"></span>
				<span class="playNum"></span>
			</div>
		</div>
		
		<div class="upInfo">
			<img src=""/>
			<p></p>
			<a class="subscribe" onclick="more()">订阅</a>
		</div>
		
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
		
		
		<div class="commentInp">
			<div class="commentInp1">
				<p><input type="text" name="" id="" value="" placeholder="写下我的评论..." /></p>
			</div>
			<div class="commentInp2" onclick="more()">
				<img src="images/commentImg1.png"/>
				<span></span>
			</div>
			<div class="commentInp3" onclick="more()">
				<img src="images/commentImg2.png"/>
				<span></span>
			</div>
			<div class="commentInp4">
				<img src="images/commentImg3.png"/>
			</div>
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="js/video.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			视频信息
			function getVideo(id){
				$.ajax({
			        url: apiUrl+"/fxf_video/getVideoList",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data.dataList[0];
			                	
			                	$(".video").append("<video id='video' class='video-js' controls preload='auto' poster='"+data.videoImg+"'>"+
									"<source src='"+data.videoSrc+"' type='video/mp4'>"+
								"</video>")
								videojs(video).ready(function(){
								});
			                	
			                	
			                	$(".videoInfo h1").text(data.title);
			                	$(".videoInfo .videoIntro p").text(data.summary);
			                	$(".videoInfo .videoData .collectNum").text(data.allPraises);
			                	$(".videoInfo .videoData .playNum").text(data.allLooks);
			                	
			                	var headImg = "";
			                	if (data.headImg!=null) {
			                		headImg = data.headImg;
			                	} else {
			                		headImg = "${base}/jsp/fxf/share/images/anonymous.png"
			                	}
			                	$(".upInfo img").attr("src",headImg);
			                	$(".upInfo p").text(data.nickname);
			                	
			                	$(".commentInp2 span").text(data.allPraises);
			                	$(".commentInp3 span").text(data.answers);
			                	
			                	locationUrl = apiUrl+"/jsp/fxf/share/videoDetail.jsp?id="+id;
								wxtitle = data.title;
			                	wximgUrl= data.videoImg;
			                	wxdesc = data.summary;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
//			视频评论
			function getVideoComment(questionId){
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
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
				var id = "";
				var Url = location.search;
				if (Url.indexOf("%3F")!=-1) {
					var pos = Url.indexOf("%3F");
					var str = Url.substr(pos+3);
					id = str.split("%3D")[1];
				} else {
					id = getQueryString('id');
				}
				
				getVideo(id);
				getVideoComment(id);
				
				
	//			授权
//				var userId = "";
//				if (isWeChat()) {
//					if (oauth == 'true') {
//						userId = "${user.id}";
//						headUrl = "${user.headImg}";
//						nickName = "${user.username}";
//						role = "${user.role}";
//						localStorage.setItem("fxfWXuserId",userId);
//						localStorage.setItem("fxfWXheadImg",headUrl);
//						localStorage.setItem("fxfWXnickName",nickName);
//						if (role!=null) {
//							localStorage.setItem("fxfWXrole",role);
//						}
//						console.log("授权成功："+nickName);
//					} else {
//						if (localStorage.getItem("fxfWXuserId") != null &&localStorage.getItem("fxfWXuserId") != "") {
//							userId = localStorage.getItem("fxfWXuserId");
//							headUrl = localStorage.getItem("fxfWXheadImg");
//							nickName = localStorage.getItem("fxfWXnickName");
//							role = localStorage.getItem("fxfWXrole");
//						} else {
//							wxAuthorization("/fxf/share/videoDetail.jsp?id="+id);
//						}
//					}
//					
//					
//					
//					
//				} else{
//					if (localStorage.getItem("fxfQQuserId")==null) {
//						$(".commentInp2").click(function(){
//							QC.Login.showPopup({
//								appId:"101391991",
//								redirectURI:apiUrl+"/jsp/fxf/share/callBack.html?page=videoDetail&id="+id
//							});
//						})
//					} else{
//						userId = localStorage.getItem("fxfQQuserId");
//						$(".commentInp2").click(function(){
//						})
//					}
//				}
				
				
				$(".videoIntro em").click(function(){
					if ($(".videoIntro p").hasClass("oneLine")) {
						$(".videoIntro p").removeClass("oneLine");
						$(this).css("transform","rotate(180deg)");
					} else{
						$(".videoIntro p").addClass("oneLine");
						$(this).css("transform","rotate(0deg)");
					}
				});
				
				
//				$(".commentInp1 input").focus(function(){
//					$(".commentInp2,.commentInp3,.commentInp4").hide();
//				})
				
				$(".commentInp1 input").focus(function(){
					more();
				})
				
				$(".commentInp4").click(function(){
					$("#layer").show();
					$("body").append("<img class='share' src='images/share.png' />")
				})
				
				$("body").on("click","#layer,.share",function(){
					$("#layer").hide();
					$(".share").remove();
				})
			})
		</script>
	</body>
</html>
