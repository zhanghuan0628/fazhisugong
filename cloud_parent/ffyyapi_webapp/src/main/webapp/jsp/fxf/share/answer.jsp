<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>回答详情-飞小凡</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${base}/jsp/fxf/share/css/global.css"/>
	</head>
	<body style="padding-bottom: 5rem;">
		<div id="layer"></div>
		
		<div class="floor">
			<!--<div class="fclose"><img src="../resources/fxf/news-cou-icon1.png"></div>-->
			<div class="flogo"><img src="${base}/jsp/fxf/share/images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
		<div class="questionDetail">
			<p id="qusc"></p>
			<a href=""><span id="ansconts"></span>个回答&gt</a>
		</div>
		<div class="answerDetail">
			<div class="questionPerson clearfix">
				<img src="" id="anhead"/>
				<div>
					<p id="annick"></p>
					<span id="andate"></span>
				</div>
				<a class="attention">关注</a>
			</div>
			<div class="ad-content" id="ancontent"></div>
			<div class="othersOpinion">
				
			</div>
		</div>
		
		<div class="commentList">
			<h1 class="title">评论 (<span id="cdconts"></span>)</h1>
			<ul id="conthtml">
				<!--<li>
					<img class="commentHead" src="images/headurl.png"/>
					<div class="commentContent">
						<h2></h2>
						<div class="commentData clearfix">
							<p class="cd-time">2017.01.05</p>
							<a class="thumbUp">15</a>
						</div>
						<p class="reply"><span>张三：</span>这是一条张三的评论。</p>
						<p class="cc-detail">我觉得这个世界美好无比。晴时满树花开，雨天一湖涟漪，阳光席卷城市，微风穿越指间。我觉得这个世界美好无比。晴时满树花开，雨天一湖涟漪，阳光席卷城市，微风穿越指间。我觉得这个世界美好无比。晴时满树花开，雨天一湖涟飞</p>
					</div>
				</li>
				
				<li>
					<img class="commentHead" src="images/headurl.png"/>
					<div class="commentContent">
						<h2>花开_半夏</h2>
						<div class="commentData clearfix">
							<p class="cd-time">2017.01.05</p>
							<a class="thumbUp">15</a>
						</div>
						<p class="cc-detail">我觉得这个世界美好无比。晴时满树花开，雨天一湖涟漪，阳光席卷城市，微风穿越指间。我觉得这个世界美好无比。晴时满树花开，雨天一湖涟漪，阳光席卷城市，微风穿越指间。我觉得这个世界美好无比。晴时满树花开，雨天一湖涟飞</p>
					</div>
				</li>-->
			</ul>
		</div>
		
		<div class="myOperation">
			<a class="endorse-btn">
				<img src="${base}/jsp/fxf/share/images/endorse.png"/>
				赞同
			</a>
			<a class="grateful-btn">
				<img src="${base}/jsp/fxf/share/images/grateful.png"/>
				感谢
			</a>
			<a class="comment-btn" href="#" onclick="more()">
				<img src="${base}/jsp/fxf/share/images/comment1.png"/>
				评论
			</a>
		</div>
		
		
		<div class="present">
			<div class="presentTitile">
				<h1>送礼物给 <span></span></h1>
				<em></em>
			</div>
			<ul class="presentList">
			</ul>
			<div class="presentNum">
				<label>礼物数量</label>
				<div class="numCase">
					<span class="minus">-</span>
					<p>1</p>
					<span class="plus">+</span>
				</div>
				
				<p class="presentResult">=2凡豆</p>
			</div>
			<button class="sendPresent">赠送</button>
		</div>
		
		
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="${base}/jsp/fxf/share/js/jquery.min.js"></script>
		<script src="${base}/jsp/fxf/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/fxf/share/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		
		
		function getQuestionByAnswerId(id){
			$.ajax({
				url : apiUrl+"/fxf_share/getQuestionByAnswerId",
				data : {id:id},
				async : false,
				success : function(res) {
					var data = res.data;
					if (data.del == 1) {
						location.href="${base}/jsp/fxf/share/deleted.jsp"
					}
					$("#qusc").html(data.title);
					$("#ansconts").html(data.answers);
					$(".questionDetail a").attr("href","${base}/jsp/fxf/share/interlocutionDetail.jsp?questionId="+data.id);
				}
			})
		}
		
		function viewAnswerDetail(id){
			$.ajax({
				url : apiUrl+"/fxf_share/viewAnswerDetail",
				data : {id:id},
				async : false,
				success : function(res) {
					if(res.success){
		                if(res.code == "2000"){
							var data = res.data[0];
							$("#ancontent").html(data.content);
							$("#anhead").attr("src",formatImg(data.headImg));
							$("#andate").html(formatData(data.answerDate));
							$("#annick").html(data.nickName);
							var praiseList = data.praiseList;
							var phtml = "";
							$.each(praiseList, function(i,item) {
								if (i < 5) {
									var pheadImg = "";
									if (item.headImg!=null) {
										pheadImg = item.headImg
									} else {
										pheadImg = "${base}/jsp/fxf/share/images/anonymous.png"
									}
									phtml += "<img src='"+pheadImg+"'/>"
								}
							});
							$(".othersOpinion").html(phtml+"<span>"+data.praises+"人已赞</span>");
							
							locationUrl = apiUrl+"/jsp/fxf/share/answer.jsp?id="+id,
							wxtitle = data.nickName+"回答了"+data.questionContent;
							wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
							wxdesc = data.content;
							wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
							
							$(".presentTitile h1 span").text(data.nickName)
							userGiftId = data.userBaseId;
						} else {
		        			if (res.code=="5000") {
		        				location.href="${base}/jsp/fxf/share/deleted.jsp"
		        			}
		        		}
		        	}
				}
			})
		}
		
		function getAnswerAnswer(doingId){
			$.ajax({
				url : apiUrl+"/fxf_share/getAnswerAnswer",
				data : {doingId:doingId},
				async : false,
				success : function(res) {
					console.log(res)
					var data = res.data;
					$("#cdconts").html(data.counts)
					var answers = data.answers;
					var html = "";
					for(var i=0;i<answers.length;i++){
						html = html + '<li><img class="commentHead" src="'+formatImg(answers[i].headImg)+'"/>'+
						'<div class="commentContent"><h2>'+formatNickname(answers[i].nickname)+'</h2><div class="commentData clearfix">'+
						'<p class="cd-time">'+formatData(answers[i].date)+'</p><a class="thumbUp">'+answers[i].praises+'</a></div>';
						if(answers[i].answers.length>0){
							html = html +'<p class="reply"><span>'+formatNickname(answers[i].answers[0].nickname)+'：</span>'+answers[i].answers[0].content+'</p>';
						}
						
						html = html +'<p class="cc-detail">'+answers[i].content+'</p></div></li>'
					}
					$("#conthtml").html(html);
					
				}
			})
		}
		
		
//		赞同
		function praise(userId,answerId){
			$.ajax({
		        url: apiUrl+"/fxf_question/praise_answer_or_no",
		        type: "post",
		        data: {userId:userId,answerId:answerId},
		        dataType: "json",
		        async:false,
		        success: function(data) {
		        	if(data.success){
		                if(data.code == "2000"){
		                	viewAnswerDetail(answerId)
		                }else{
		                	
		                }
		            } else {
	            		toast(data.message)
		            }
		        }
		    });
		}
		
		
		
//		礼物列表
		function geGifts(){
			$.ajax({
		        url: apiUrl+"/fxf_question/geGifts",
		        type: "post",
		        data: {},
		        dataType: "json",
		        async:false,
		        success: function(data) {
		        	if(data.success){
		                if(data.code == "2000"){
		                	var data = data.data;
		                	var html = "";
		                	$.each(data, function(i,item) {
		                		html += "<li data-presentid = '"+item.id+"'>"+
		                			"<div class='presentPic'>"+
										"<img src='"+item.img+"'/>"+
									"</div>"+
									"<p><img src='images/beans.png'/> <span>"+item.beans+"</span></p>"+
									"<h2>"+item.name+"</h2>"+
								"</li>"
		                	});
		                	
		                	$(".present .presentList").html(html);
		                	
		                	$(".present .presentList li").eq(0).addClass("present_sel");
		                }else{
		                	
		                }
		            }
		        }
		    });
		}

//		赠送礼物
		function sendGift(userId,userGiftId,giftName,giftImg,quantity,beans,answerId){
			$.ajax({
		        url: apiUrl+"/fxf_own/sendGift",
		        type: "post",
		        data: {userId:userId,userGiftId:userGiftId,giftName:giftName,giftImg:giftImg,quantity:quantity,beans:beans,answerId:answerId,type:"answer"},
		        dataType: "json",
		        async:false,
		        success: function(data) {
		        	if(data.success){
		                if(data.code == "2000"){
		                	var data = data.data;
		                	toast("赠送成功！")
		                }else{
		                	toast(data.message)
		                }
		            } else {
		            	if(data.code == "5000"){
		            		toast(data.message)
		                } else {
		               		toast(data.message)
		                }
		            }
		        }
		    });
		}

			
		
		function formatImg(src){
			src = src+"";
			if(src.indexOf("http") == -1){
				return "../resources/fxf/headImg/"+src+".png"
			}else{
				return src;
			}
			
		}
		
		function formatNickname(name){
			if(name != undefined && name != null){
				return name;
			}else{
				return "匿名用户";
			}
		}
		
		function formatData(date1) {
			var date = new Date(date1)
			return date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate();
		}

		//跳转咨询师主页
		function showDetail() {
			location.href = "../jsp/treatment/shareCounselor/details.jsp?id="
					+ counselorId;
		}
		
		var locationUrl = "";
		var wxtitle = "";
		var wximgUrl = "";
		var wxdesc = "";
		var counselorId = "";
		
		var userGiftId = "";
		var presentNum = 1;
		var beans = 2;
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
			
			
			getQuestionByAnswerId(id);
			viewAnswerDetail(id);
			getAnswerAnswer(id);
			
			geGifts();
			
//			授权
			var userId = "";
			if (isWeChat()) {
				var oauth = "${oauth}";
				var headUrl = '';
				var nickName = '';
				var role = '';
				if (oauth == 'true') {
					userId = "${user.id}";
					headUrl = "${user.headImg}";
					nickName = "${user.username}";
					role = "${user.role}";
					localStorage.setItem("fxfWXuserId",userId);
					localStorage.setItem("fxfWXheadImg",headUrl);
					localStorage.setItem("fxfWXnickName",nickName);
					if (role!=null) {
						localStorage.setItem("fxfWXrole",role);
					}
					console.log("授权成功："+nickName);
				} else {
					if (localStorage.getItem("fxfWXuserId") != null &&localStorage.getItem("fxfWXuserId") != "") {
						userId = localStorage.getItem("fxfWXuserId");
						headUrl = localStorage.getItem("fxfWXheadImg");
						nickName = localStorage.getItem("fxfWXnickName");
						role = localStorage.getItem("fxfWXrole");
					} else {
						wxAuthorization("/fxf/share/answer.jsp?id="+id);
					}
				}
				
//					赞同
				$(".endorse-btn").click(function(){
					if (role != null && role != "") {
						praise(userId,id);
					} else {
						location.href="${base}/jsp/fxf/share/editInfo.jsp?from=WX&page=answer&id="+id
					}
					
				})
//					礼物
				$(".grateful-btn").click(function(){
					if (role != null && role != "") {
						$("#layer").show();
						$(".present").animate({"bottom":0});
					} else {
						location.href="${base}/jsp/fxf/share/editInfo.jsp?from=WX&page=answer&id="+id
					}
					
				})
				
			} else{
				if (localStorage.getItem("fxfQQuserId")==null) {
					$(".grateful-btn,.endorse-btn").click(function(){
						QC.Login.showPopup({
							appId:"101391991",
							redirectURI:apiUrl+"/jsp/fxf/share/callBack.html?page=answer&id="+id
						});
					})
				} else{
					userId = localStorage.getItem("fxfQQuserId");
					role = localStorage.getItem("fxfQQrole");
//					赞同
					$(".endorse-btn").click(function(){
						if (role != null) {
							praise(userId,id);
						} else {
							location.href="${base}/jsp/fxf/share/editInfo.jsp?from=QQ&page=answer&id="+id
						}
						
					})
//					礼物
					$(".grateful-btn").click(function(){
						if (role != null) {
							$("#layer").show();
							$(".present").animate({"bottom":0});
						} else {
							location.href="${base}/jsp/fxf/share/editInfo.jsp?from=QQ&page=answer&id="+id
						}
						
					})
				}
			}
			
			
			
			$(".presentTitile em,#layer").click(function(){
				$("#layer").hide();
				$(".present").animate({"bottom":"-46.6rem"});
			})
			
//			选择礼物
			$(".presentList").on("click","li",function(){
				$(this).addClass("present_sel").siblings().removeClass("present_sel");
				beans = parseInt($(this).find("p span").text());
				$(".presentResult").text("="+presentNum*beans+"凡豆");
			})
			
//			礼物数量加减
			$(".plus").click(function(){
				++presentNum;
				$(".numCase p").text(presentNum);
				$(".presentResult").text("="+presentNum*beans+"凡豆");
			})
			
			$(".minus").click(function(){
				if (presentNum==1) {
					return false;
				}
				--presentNum;
				$(".numCase p").text(presentNum);
				$(".presentResult").text("="+presentNum*beans+"凡豆");
			})
			
			
//			赠送礼物
			$(".sendPresent").click(function(){
				var giftName = $(".present_sel").find("h2").text();
				var giftImg = $(".present_sel").find(".presentPic img").attr("src");
				sendGift(userId,userGiftId,giftName,giftImg,presentNum,beans,id);
			})
			
		})

	</script>
	</body>
</html>
