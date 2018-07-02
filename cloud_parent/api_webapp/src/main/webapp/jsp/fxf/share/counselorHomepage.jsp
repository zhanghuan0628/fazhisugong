<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${base}/jsp/fxf/share/css/global.css?v=20180330"/>
	</head>
	<body style="padding-bottom: 5.5rem;">
		<div id="layer"></div>
		<!--更多介绍-->
		<div class="introDetail">
			<h1 class="counselorTitle">介绍</h1>
			<div>
			</div>
			<a></a>
		</div>
		
		
		<div class="counselorInfo">
			<div class="counselorBg"></div>
			<div class="counselorLayer"></div>
			<div class="counselorData">
				<div class="cd-left">
					<p>粉丝/<span></span></p>
				</div>
				<div class="cd-center">
					<div class="cd-img">
						<img class="cd-headImg" src=""/>
						<img class="cd-sex" src=""/>
						
					</div>
					<p>咨询/<span></span></p>
				</div>
				<div class="cd-right">
					<p>礼物/<span></span></p>
				</div>
			</div>
			
			<div class="beGoodAt">
				<ul>
				</ul>
			</div>
		</div>
		
		
		<div class="counselorNavi">
			<ul>
				<li><img src="${base}/jsp/fxf/share/images/counselor_on.png"/>介绍</li>
				<li><img src="${base}/jsp/fxf/share/images/counselor_on.png"/>评价</li>
				<li><img src="${base}/jsp/fxf/share/images/counselor_on.png"/>信箱</li>
				<li><img src="${base}/jsp/fxf/share/images/counselor_on.png"/>问答</li>
			</ul>
		</div>
		
		<div class="counselorDetail">
			<dl>
				<dd class="counselorIntro">
					<h1 class="counselorTitle">介绍</h1>
					<div></div>
					
					<a class="checkMore">查看全部介绍></a>
					
					<em class="leftLink"></em>
					<em class="rightLink"></em>
				</dd>
				
				<dd class="counselorEvaluate">
					<h1 class="counselorTitle">评价</h1>
					<!--<p class="noData">还没有人评价</p>-->
					<!--<div class="counselorTag">
						<span>有耐心(7)</span>
						<span>一针见血(7)</span>
						<span>回复很快(7)</span>
					</div>
					
					<ul>
						<li>
							<div class="evaluateHead">
								<img class="evaluateHeadImg" src=""/>
								<span>神奇的小镇</span>
								<p><img src="images/star.png"/><img src="images/star.png"/><img src="images/star.png"/><img src="images/star.png"/><img src="images/star.png"/></p>
							</div>
							<p class="evaluateCont">在高校担任专职心理咨询师超过5年，接触过大量与自信心建立和个人成长相关议题的青年个案。</p>
						</li>
						
						<li>
							<div class="evaluateHead">
								<img class="evaluateHeadImg" src=""/>
								<span>神奇的小镇</span>
								<p><img src="images/star.png"/><img src="images/star.png"/><img src="images/star.png"/><img src="images/star.png"/></p>
							</div>
							<p class="evaluateCont">在高校担任专职心理咨询师超过5年。</p>
						</li>
					</ul>-->
					
					<em class="leftLink"></em>
					<em class="rightLink"></em>
				</dd>
				
				<dd class="counselorMail">
					<h1 class="counselorTitle">信箱</h1>
					<em class="leftLink"></em>
					<em class="rightLink"></em>
				</dd>
				
				<dd class="counselorQA">
					<h1 class="counselorTitle">问答</h1>
					
					<!--<ul class="answerList">
		        		<li>
		        			<div class="whoAnswer">
		        				<img src=""/>
		        				<p>奔跑的小绵羊</p>
		        				<span>回答了</span>
		        			</div>
		        			<p class="ansCont">我爸妈天天晚上都特别晚回家，我一个人在家怕，是不知道和谁说，他们好像一点都不关心我在家怕，可是不知道和谁说，他们好像一点都不关心。</p>
		        			<div class="ansData clearfix">
		        				<p>#学习压力#</p>
		        				<span class="ansPraise"><img src="images/cl-praise.png"/> 2</span>
		        				<span class="ansGift"><img src="images/cl-gift.png"/> 2</span>
		        			</div>
		        		</li>
		        		<li>
		        			<div class="whoAnswer">
		        				<img src=""/>
		        				<p>奔跑的小绵羊</p>
		        				<span>回答了</span>
		        			</div>
		        			<p class="ansCont">我爸妈天天晚上都特别晚回家，我一个人在家怕，是不知道和谁说，他们好像一点都不关心我在家怕，可是不知道和谁说，他们好像一点都不关心。</p>
		        			<div class="ansData clearfix">
		        				<p>#学习压力#</p>
		        				<span class="ansPraise"><img src="images/cl-praise.png"/> 2</span>
		        				<span class="ansGift"><img src="images/cl-gift.png"/> 2</span>
		        			</div>
		        		</li>
		        		<li>
		        			<div class="whoAnswer">
		        				<img src=""/>
		        				<p>奔跑的小绵羊</p>
		        				<span>回答了</span>
		        			</div>
		        			<p class="ansCont">我爸妈天天晚上都特别晚回家，我一个人在家怕，是不知道和谁说，他们好像一点都不关心我在家怕，可是不知道和谁说，他们好像一点都不关心。</p>
		        			<div class="ansData clearfix">
		        				<p>#学习压力#</p>
		        				<span class="ansPraise"><img src="images/cl-praise.png"/> 2</span>
		        				<span class="ansGift"><img src="images/cl-gift.png"/> 2</span>
		        			</div>
		        		</li>
		        		<li>
		        			<div class="whoAnswer">
		        				<img src=""/>
		        				<p>奔跑的小绵羊</p>
		        				<span>回答了</span>
		        			</div>
		        			<p class="ansCont">我爸妈天天晚上都特别晚回家，我一个人在家怕，是不知道和谁说，他们好像一点都不关心我在家怕，可是不知道和谁说，他们好像一点都不关心。</p>
		        			<div class="ansData clearfix">
		        				<p>#学习压力#</p>
		        				<span class="ansPraise"><img src="images/cl-praise.png"/> 2</span>
		        				<span class="ansGift"><img src="images/cl-gift.png"/> 2</span>
		        			</div>
		        		</li>
		        	</ul>-->
        	
				</dd>
			</dl>
		</div>
		
		<div class="counselorBottom">
			<div class="counselorOper">
				
			</div>
			<div class="counselorOrder">
				
			</div>
		</div>
		
		<script src="${base}/jsp/fxf/share/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script src="${base}/jsp/fxf/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/fxf/share/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			var apiUrl = location.protocol+"//192.168.0.134:8080/ffyyapi_webapp";
			
			function getCounselorById(id){
				$.ajax({
			        url: apiUrl+"/fxf_counselor/getCounselorById",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		var counselor = data.counselor;
		                		var tagList = data.tagList;
		                		var commentList = data.commentList;
		                		var mailList = data.mailList;
		                		
		                		document.title = counselor.name;
		                		
//								是否咨询师
								if (counselor.status == "pass") {
									$(".counselorIntro .counselorTitle").after("<img class='counselorGuarantee' src='${base}/jsp/fxf/share/images/counselorGuarantee.jpg'/>");
		                			$(".counselorOper").append("<a class='counselorFollow'><img src='${base}/jsp/fxf/share/images/counselorFollow.png'/>关注</a><a class='counselorWrite'><img src='${base}/jsp/fxf/share/images/counselorWrite.png'/>给TA写信</a>")
									if (!isWeChat()) {
			                			$(".cd-img").append("<h1 class='counselorName'>"+counselor.name+"<img src='${base}/jsp/fxf/share/images/counselorTag1.png'/></h1>")
			                		}
								} else {
									$(".counselorOper").append("<p>给TA写信</p>");
									if (!isWeChat()) {
			                			$(".cd-img").append("<h1 class='counselorName'>"+counselor.name+"<img src='${base}/jsp/fxf/share/images/counselorTag2.png'/></h1>")
			                		}
								}
		                		
		                		
		                		$(".counselorData .cd-center .cd-img .cd-headImg").attr("src",counselor.headUrl);
		                		$(".counselorBg").css("background-image","url("+counselor.headUrl+")");
		                		
		                		if (counselor.sex == "F") {
		                			$(".counselorData .cd-center .cd-img .cd-sex").attr("src","${base}/jsp/fxf/share/images/femaleIcon.png");
		                		} else {
		                			$(".counselorData .cd-center .cd-img .cd-sex").attr("src","${base}/jsp/fxf/share/images/maleIcon.png");
		                		}
		                		
		                		$(".counselorData .cd-left p span").text(data.fans);
		                		$(".counselorData .cd-center p span").text(data.orders);
		                		$(".counselorData .cd-right p span").text(data.gifts);
		                		
//		                		擅长领域
								var skillName = "";
								if (counselor.skillNameList!= null) {
									$.each(counselor.skillNameList, function(i,item) {
										if (i<6) {
											skillName += "<li><p>"+item+"</p></li>"
										}
									});
								}
								$(".beGoodAt ul").html(skillName);
								
								
								
//								介绍
								$(".counselorIntro div").html(counselor.profieListModel.personalExperience);
								
		                		var regCont = new RegExp("\n", 'g');
		                		
		                		var personalWord = "";
								var shareWord = "";
								var style = "";
								var method = "";
								var mentalPosition = "";
								var personalExperience = "";
								
								if (counselor.profieListModel.personalExperience != null) {
									personalWord = "<p>"+counselor.profieListModel.personalExperience+"</p>";
								}
								
								if (counselor.sendWord != null) {
									shareWord = "<h2>咨询师寄语</h2><p>"+counselor.sendWord+"</p>";
								}
								
								if (counselor.profieListModel.style != null) {
									style = "<h2>咨询风格</h2><p>"+counselor.profieListModel.style+"</p>";
								}
								
								if (counselor.profieListModel.method != null) {
									method = "<h2>咨询方法</h2><p>"+counselor.profieListModel.method+"</p>";
								}
								
								
								if (counselor.profieListModel.mentalPosition != null) {
									mentalPosition = "<h2>心理相关职位</h2><p>"+counselor.profieListModel.mentalPosition.replace(regCont,"<br/>")+"</p>";
								}
								
								var experienceList = "";
								if (counselor.experienceList!= null) {
									$.each(counselor.experienceList, function(i,item) {
										experienceList += "<li><h3>"+item.startDate+"~"+item.endDate+"</h3><p>"+item.content+"</p></li>"
									});
									personalExperience = "<h2>培训经历</h2><ul>"+experienceList+"</ul>";
								}
								
								
								$(".introDetail div").html(personalWord + shareWord + style + method + mentalPosition + personalExperience)
								
								
//								评价
								var evaluate = "";
								var tag = "";
								var comment = "";
								if (tagList.length != 0) {
									var tList = "";
									$.each(tagList, function(i,item) {
										tList += "<span>"+item.content+"("+item.chooses+")</span>"
									});
									
									tag = "<div class='counselorTag'>"+tList+"</div>";
								}
								if (commentList.length != 0) {
									var cList = "";
									$.each(commentList, function(i,item) {
										var starsNum;
										
										if (item.demandService >= item.professiona) {
											starsNum = item.demandService;
										} else{
											starsNum = item.professiona;
										}
										
										if (starsNum < item.serviceAttitude) {
											starsNum = item.serviceAttitude
										}
										
										var stars = "";
										for (var j=0;j<starsNum;j++) {
											stars += "<img src='${base}/jsp/fxf/share/images/star.png'/>";
										}
										cList += "<li>"+
											"<div class='evaluateHead'>"+
												"<img class='evaluateHeadImg' src='"+item.headImg+"'/>"+
												"<span>"+item.nickname+"</span>"+
												"<p>"+stars+"</p>"+
											"</div>"+
											"<p class='evaluateCont'>"+item.content+"</p>"+
										"</li>"
									});
									
									comment = "<ul>"+cList+"</ul>";
								}
								
								if (tag == "" && comment == "") {
									evaluate = "<p class='noData'>还没有人评价</p>";
								} else {
									evaluate = tag + comment + "<a class='checkMore'>查看全部评价></a>";
								}
								
								$(".counselorEvaluate .counselorTitle").after(evaluate);
								
								
//								邮箱
								var mail = "";
								if (mailList.length != 0) {
									mail = "<div class='cm-letter' style='background-image: url("+mailList[0].envelopeImg+");'>"+
										"<em class='zip'></em>"+
										"<div class='stamp'>"+
											"<img src='"+mailList[0].stampImg+"'/>"+
											"<em class='post'></em>"+
										"</div>"+
										"<div class='sender'>"+
											"<img src='"+mailList[0].headImg+"'/>"+
											"<span>"+mailList[0].nickname+"</span>"+
										"</div>"+
										
										"<div class='letterDetail'>"+
											"<h2>"+mailList[0].title+"</h2>"+
											"<p>"+mailList[0].content+"</p>"+
										"</div>"+
									"</div>"+
									"<a class='checkMore'>查看全部信件></a>";
								} else{
									mail = "<p class='noData'>TA的信箱是空的</p>"
								}
								
								$(".counselorMail .counselorTitle").after(mail);
								
								ajaxCount++;
								
								
								locationUrl = apiUrl+"/jsp/fxf/share/counselorHomepage.jsp?id="+id;
				    			wxtitle = counselor.name;
								wximgUrl = counselor.headUrl;
								wxdesc = counselor.sendWord;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);

			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			问答列表
			function getHomeRecListByPage(answerUserId){
				$.ajax({
			        url: apiUrl+"/fxf_question/getHomeRecListByPage",
			        type: "post",
			        data: {answerUserId:answerUserId,pageSize:10},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		
		                		var QA = ""
		                		if (data.length != 0) {
		                			var loadingText = "";
		                			
		                			if (data.length == 10) {
		                				hasNext = true;
		                				pageNo = 2;
		                				loadingText = "<i class='loadingText'>加载中...<i>";
		                			}
		                			
		                			var QAList = "";
		                			$.each(data, function(i,item) {
		                				var questionTag = "";
		                				if (item.questionTag!=null) {
		                					if (item.questionTag.indexOf(",")!=-1){
					                			questionTag = item.questionTag.split(",")[0];
					                		} else {
					                			questionTag = item.questionTag;
					                		}
		                				}
					                		
				                		
				                		var headImg = "";
					                	if (item.headImg!=null) {
					                		headImg = item.headImg;
					                	} else {
					                		headImg = "${base}/jsp/fxf/share/images/anonymous.png"
					                	}
				                	
		                				QAList += "<li data-id='"+item.id+"'>"+
		                					"<h2>"+item.questionContent+"</h2>"+
						        			"<p class='ansCont'>"+item.content+"</p>"+
						        			"<div class='ansData clearfix'>"+
						        				"<p>#"+questionTag+"#</p>"+
						        				"<span class='ansPraise'><img src='${base}/jsp/fxf/share/images/cl-praise.png'/>"+item.praises+"</span>"+
						        				"<span class='ansGift'><img src='${base}/jsp/fxf/share/images/cl-gift.png'/>"+item.giftUsers+"</span>"+
						        			"</div>"+
						        		"</li>"
		                			});
		                			
		                			QA = "<ul class='answerList'>"+QAList+loadingText+"</ul>"
		                		} else{
		                			QA = "<p class='noData'>TA还没有回答过问题</p>";
		                		}
		                		
		                		$(".counselorQA .counselorTitle").after(QA);
		                		
//		                		ajaxCount++;

			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			排班
			function getCounselorSheet(counselorId){
				$.ajax({
			        url: apiUrl+"/fxf_order/getCounselorSheet",
			        type: "post",
			        data: {counselorId:counselorId},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		if (data == null) {
		                			$(".counselorOrder").remove();
		                			return false;
		                		}
		                		if (data.orders >= 6) {
		                			$(".counselorOrder").css("background-color","#bdbdbd").append("<p class='expiry'>已约满</p>")
		                		} else {
		                			var appointDate = "";
		                			var appointTime = "";
		                			if (data.weeks == "Monday") {
		                				appointDate = "周一";
		                			} else if (data.weeks == "Tuesday") {
		                				appointDate = "周二";
		                			} else if (data.weeks == "Wednesday") {
		                				appointDate = "周三";
		                			} else if (data.weeks == "Thursday") {
		                				appointDate = "周四";
		                			} else if (data.weeks == "Friday") {
		                				appointDate = "周五";
		                			} else if (data.weeks == "Saturday") {
		                				appointDate = "周六";
		                			} else if (data.weeks == "Sunday") {
		                				appointDate = "周日";
		                			}
		                			
		                			var appointTimeS = data.times + ":00";
		                			var appointTimeE = parseInt(data.times) + 1 + ":00";
		                			appointTime = appointTimeS + "-" + appointTimeE;
		                			$(".counselorOrder").append("<p class='appoint'>立即预约（"+data.price+"元/60分钟）</p><span>在线时间：每"+appointDate+appointTime+"</span>")
		                		}
		                		
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			var htmlPx = $("html").css("font-size")
			var remTurn = htmlPx.substr(0,htmlPx.length-2)
			
			var anchor = [];
			
			var ajaxCount = 0;
			
			var timer;
			
			var hasNext = false;
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
				var id = getQueryString("id");
				getCounselorById(id);
				getHomeRecListByPage(id);
				getCounselorSheet(id);
				
				
				timer = setInterval(function(){
					if (ajaxCount==1) {
						clearInterval(timer);
						anchor.push(Math.ceil($(".counselorIntro").offset().top-4*remTurn))
						anchor.push(Math.ceil($(".counselorEvaluate").offset().top-4*remTurn))
						anchor.push(Math.ceil($(".counselorMail").offset().top-4*remTurn))
						anchor.push(Math.ceil($(".counselorQA").offset().top-4*remTurn))
						
						
						$(window).scroll(function(){
							if ($(window).scrollTop() >= anchor[0]) {
								$(".counselorNavi").show();
								$(".counselorNavi li").removeClass("counselor_on");
								$(".counselorNavi li").eq(0).addClass("counselor_on");
								if ($(window).scrollTop() >= anchor[1] && $(window).scrollTop() < anchor[2]) {
									$(".counselorNavi li").removeClass("counselor_on");
									$(".counselorNavi li").eq(1).addClass("counselor_on");
								} else if ($(window).scrollTop() >= anchor[2] && $(window).scrollTop() < anchor[3]) {
									$(".counselorNavi li").removeClass("counselor_on");
									$(".counselorNavi li").eq(2).addClass("counselor_on");
								} else if ($(window).scrollTop() >= anchor[3]) {
									$(".counselorNavi li").removeClass("counselor_on");
									$(".counselorNavi li").eq(3).addClass("counselor_on");
								}
							} else {
								$(".counselorNavi").hide();
							}
						})
						
					}
				},100);
				
					
				$(".counselorNavi li").click(function(){
					var naviIndex = $(this).index();
					if (naviIndex == 0) {
						$("html,body").animate({scrollTop:anchor[0]}, 500);
					} else if (naviIndex == 1) {
						$("html,body").animate({scrollTop:anchor[1]}, 500);
					} else if (naviIndex == 2) {
						$("html,body").animate({scrollTop:anchor[2]}, 500);
					} else {
						$("html,body").animate({scrollTop:anchor[3]}, 500);
					}
				})
				
				
				$(".counselorIntro").on("click",".checkMore",function(){
					$("#layer,.introDetail").show();
				})
				
				$(".introDetail a").click(function(){
					$("#layer,.introDetail").hide();
				})
				
				
				$(window).scroll(function(){
				　　var scrollTop = $(this).scrollTop();
				　　var scrollHeight = $(document).height();
				　　var windowHeight = $(this).height();
				　　if(scrollTop + windowHeight == scrollHeight&&hasNext){
				　　 　　upajaxload(id,pageNo);
						pageNo++;
				　　}
				});
				
				
				$("body").on("click",".checkMore,.ansPraise,.ansGift,.counselorOper,.counselorOrder",function(){
					if ($(this).parent().attr("class") == "counselorIntro") {
						return false;
					}
					more();
				})
				
			})
			
			
			function upajaxload(answerUserId,pageNo){
				$.ajax({
			        url: apiUrl+"/fxf_question/getHomeRecListByPage",
			        type: "post",
			        data: {answerUserId:answerUserId,pageSize:10,pageNo:pageNo},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		
		                		if (data.length != 10) {
		                			hasNext = false;
		                			$(".loadingText").remove();
		                		}
		                		
		                		if (data.length != 0) {
		                			var html = "";
		                			$.each(data, function(i,item) {
		                				var questionTag = "";
		                				if (item.questionTag!=null) {
		                					if (item.questionTag.indexOf(",")!=-1){
					                			questionTag = item.questionTag.split(",")[0];
					                		} else {
					                			questionTag = item.questionTag;
					                		}
		                				}
					                		
				                		
				                		var headImg = "";
					                	if (item.headImg!=null) {
					                		headImg = item.headImg;
					                	} else {
					                		headImg = "${base}/jsp/fxf/share/images/anonymous.png"
					                	}
				                	
		                				html += "<li data-id='"+item.id+"'>"+
		                					"<h2>"+item.questionContent+"</h2>"+
						        			"<p class='ansCont'>"+item.content+"</p>"+
						        			"<div class='ansData clearfix'>"+
						        				"<p>#"+questionTag+"#</p>"+
						        				"<span class='ansPraise'><img src='${base}/jsp/fxf/share/images/cl-praise.png'/>"+item.praises+"</span>"+
						        				"<span class='ansGift'><img src='${base}/jsp/fxf/share/images/cl-gift.png'/>"+item.giftUsers+"</span>"+
						        			"</div>"+
						        		"</li>"
		                			});
		                			
		                			$(".answerList").append(html);
		                		} else{
		                			
		                		}
		                		
		                		
		                		
		                		
//		                		ajaxCount++;

			                }else{
			                	
			                }
			            }
			        }
			    });
			}
		</script>
	</body>
</html>