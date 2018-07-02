<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>活动详情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="UTF-8" name="apple-itunes-app" content="app-id=1193113613, affiliate-data=myAffiliateData, app-argument=FFYYalipayUrl://">
		<link rel="stylesheet" type="text/css" href="css/swiper-4.2.2.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/global.css?v=20180117"/>
		<link rel="stylesheet" type="text/css" href="css/index.css?v=4"/>
	</head>
	<body style="padding-bottom: 6.5rem;">
		<div id="layer"></div>
		<div id="downLoadLine">
			<img src="images/wxLogo.png"/>
			<a onclick="more()">下载APP</a>
		</div>
		<div class="swiper-container" id="welfareBanner">
		    <div class="swiper-wrapper">
		        <!--<div class="swiper-slide" style="background-image: url(images/img.jpg);"></div>-->
		    </div>
		    <div class="swiper-pagination"></div>
		</div>
		
		<div class="welfareInfo">
			<h1><i></i><span></span></h1>
		</div>
		
		
		<div class="welfareNotice">
			<ul>
				<li>
					<img src="images/time.png"/>
					<div class="time">
						<p></p>
					</div>
				</li>
				<li>
					<img src="images/address.png"/>
					<div class="address">
						<p></p>
					</div>
				</li>
				<li>
					<img src="images/amount.png"/>
					<div class="amount">
						<p></p>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="welfareIntro">
			<h1 class="welfareTitle">活动介绍</h1>
			<div class="wi-content"></div>
			<div class="wi-detailBtn">
				<a><span>展开图文详情</span> <img src="images/pullDown.png"/></a>
			</div>
			
		</div>


		<div class="welfarePPT">
			<h1 class="welfareTitle">课程PPT</h1>
			<div class="swiper-container" id="welfarePPT">
				<div class="swiper-wrapper">
				</div>
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-pagination"></div>
			</div>
		</div>


		<div class="welfareStrategy">
			<h1 class="welfareTitle">相关攻略<a>更多攻略 ></a></h1>

			<div class="strategyInfo">
				<div></div>
				<h1></h1>
				<p class="strategyPraise"></p>
				<p class="strategyNum"></p>
			</div>
		</div>



		<div class="welfareFollow">
			<h1>122人关注</h1>
			<ul>
				<li></li>
			</ul>
		</div>


		
		<div class="welfareKnows">
			<h1 class="welfareTitle">活动须知</h1>
			<div class="wk-content">
				
			</div>
			
		</div>


		<div class="welfareNearby">
			<h1 class="welfareTitle">这个活动附近还有</h1>
			<ul>
				<!-- <li>
					<div></div>
					<h1>萨达是发的撒的发生萨达</h1>
					<p class="nearbyAddress">k11购物艺术中心</p>
					<p class="nearbyDate">3天后结束</p>
				</li> -->
			</ul>
		</div>


		<div class="welfareComment">
			<h1 class="welfareTitle">课程评论<a>评论</a></h1>
			<ul>
				<!-- <li>
					<img src="" alt="">
					<h1>潇潇</h1>
					<span>12小时前</span>
					<p>苟富贵杜莎夫人好地方发送到</p>
					<a>28</a>
				</li> -->
			</ul>
		</div>
		
		
		<div class="welfareNavi">
			<a class="wnBtn1" onclick="more()">
				<img src="images/customService.png"/>
				<p>联系客服</p>
			</a>
			<a class="wnBtn2">
				<img src="images/share.png"/>
				<p>分享</p>
			</a>
			<a class="wnBtn3" onclick="more()">
				<img src="images/collect.png"/>
				<p>收藏</p>
			</a>
			<div>
				<a class="enrollBtn" onclick="more()">立即报名</a>
			</div>
			
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/swiper-4.2.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function detail(id){
				$.ajax({
			        url: apiUrl+"/ffxl_active_v202/detail",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
		                		if (data.activeImg1!=null && data.activeImg1!="") {
		                			$("#welfareBanner .swiper-wrapper").append("<div class='swiper-slide' style='background-image: url("+data.activeImg1+");'></div>")
		                		}
		                		if (data.activeImg2!=null && data.activeImg2!="") {
		                			$("#welfareBanner .swiper-wrapper").append("<div class='swiper-slide' style='background-image: url("+data.activeImg2+");'></div>")
		                		}
		                		if (data.activeImg3!=null && data.activeImg3!="") {
		                			$("#welfareBanner .swiper-wrapper").append("<div class='swiper-slide' style='background-image: url("+data.activeImg3+");'></div>")
		                		}
		                		
		                		$(".welfareInfo h1 i").text(data.tag);
		                		
		                		$(".welfareInfo h1 span").text(data.activeName);
		                		
//		                		线上活动       		
		                		if (data.line == "on-line") {
									$(".address p").text("线上活动");
		                		} else {
									if (data.address !="" && data.address != null) {
										$(".address p").text(data.address);
									} else {
										$(".welfareNotice ul li").eq(1).hide();
									}
		                			
		                		}


								// ppt
								if (data.pptUrl != "" && data.pptUrl != null) {
									var ppt = "";
									if (data.pptUrl.indexOf(",") != -1) {
										var pptArr = data.pptUrl.split(",");
										$.each(pptArr, function(i,item) {
											ppt += "<div class='swiper-slide' style='background-image: url("+item+");'></div>";
										});
									} else {
										ppt = "<div class='swiper-slide' style='background-image: url("+data.pptUrl+");'></div>";
									}

									$("#welfarePPT .swiper-wrapper").html(ppt);
								} else {
									$(".welfarePPT").hide();
								}
		                		
//								价格是否待定                		
								if (!data.substitute) {
									$(".amount p").text("待定");
									$(".welfareNavi div").remove();
								} else {
//									状态
									if (data.enrollType == "crowdfunding"){
//										众筹
										$("#welfareBanner").append("<i class='welfareTag'>众筹</i>");
//										成功与否
										if (data.showCrowdfunding) {
											crowdfundingLogList(id);
											var fundingBar = "";
											if (parseInt(data.percentumSchedule)>1) {
												fundingBar = "100%";
											} else {
												fundingBar = data.percentumSchedule*100+"%";
											}
											
											$(".welfareInfo").after("<div class='crowdFunding'>"+
												"<p class='hasFunding'>已支持 "+data.nowCrowdfundingPrice+"</p>"+
												"<p class='perFunding'>"+data.percentumSchedule*100+"%</p>"+
												"<div class='fundingBar'>"+
													"<em style='width:"+fundingBar+"'></em>"+
												"</div>"+
												"<p class='fundingTarget'>目标金额（￥）"+data.crowdfundingPrice+"</p>"+
												"<p class='fundingsurplus'>剩余天数 "+data.remainingDays+"</p>"+
											"</div>"+
											"<div class='fundingFinished'>"+
												"<ul>"+
													"<li>"+
														"<h2>"+data.yichuPrice+"元</h2>"+
														"<p>超额完成</p>"+
													"</li>"+
													"<li>"+
														"<h2 style='font-size: 2.1rem;color: #da4547;'>"+data.cashbackPrice+"元</h2>"+
														"<p>预计人均返现</p>"+
													"</li>"+
													"<li>"+
														"<h2>"+data.nowOrderCount+"</h2>"+
														"<p>支持人数</p>"+
													"</li>"+
												"</ul>"+
											"</div>");
											
											$(".amount p").text("￥"+data.price+"/人").after("<p style='line-height: 2.4rem;'>注意：超出目标金额的部分，将于报名截止后返还报名者</p>");
										} else {
											var fundingBar = "";
											if (parseInt(data.percentumSchedule)>1) {
												fundingBar = "100%";
											} else {
												fundingBar = data.percentumSchedule*100+"%";
											}
											
											$(".welfareInfo").after("<div class='crowdFunding'>"+
												"<p class='hasFunding'>已支持 "+data.nowCrowdfundingPrice+"</p>"+
												"<p class='perFunding'>"+data.percentumSchedule*100+"%</p>"+
												"<div class='fundingBar'>"+
													"<em style='width:"+fundingBar+"'></em>"+
												"</div>"+
												"<p class='fundingTarget'>目标金额（￥）"+data.crowdfundingPrice+"</p>"+
												"<p class='fundingsurplus'>剩余天数 "+data.remainingDays+"</p>"+
											"</div>");
											
											$(".amount p").text("￥"+data.price+"/人");
										}
									} else if (data.enrollType == "presell"){
										$("#welfareBanner").append("<i class='welfareTag'>预售</i>");
//										预售
										var fundingBar = "";
										if (parseInt(data.percentumSchedule)>1) {
											fundingBar = "100%";
										} else {
											fundingBar = data.percentumSchedule*100+"%";
										}
										
										$(".welfareInfo").after("<div class='crowdFunding'>"+
											"<p class='hasFunding'>已报名 "+data.alreadyNumber+"人</p>"+
											"<p class='perFunding'>"+data.percentumSchedule*100+"%</p>"+
											"<div class='fundingBar'>"+
												"<em style='width:"+fundingBar+"'></em>"+
											"</div>"+
											"<p class='fundingTarget'>目标人数（个）"+data.startNumber+"</p>"+
											"<p class='fundingsurplus'>剩余天数 "+data.remainingDays+"</p>"+
										"</div>");
										
										$(".amount p").text("￥"+data.price+"/人");
									} else if (data.enrollType == "gratis"){
										$("#welfareBanner").append("<i class='welfareTag'>免费</i>");
//										免费
										$(".amount p").html("免费<span style='color: #555555;'>（需先支付定金"+data.price+"元，现场签到后退还）</span>");
									} else {
//										普通
										$(".amount p").text("￥"+data.price+"起");
									}
								}
		                		
		                		
//		                		活动须知
								var activeDesc = "";
								if (data.activeDesc!=null) {
									activeDesc = data.activeDesc;
								}
								$(".welfareIntro .wi-content").html(activeDesc);
		                		
		                		
		                		var countDown = "";
		                		var nowDate = new Date().getTime();
		                		
								
		                		var time = "";
		                		
		                		var obj1 = new Date(data.beginTime);
		                		var oMonth1 = obj1.getMonth()+1;
		                		var oDate1 = obj1.getDate();
		                		var oHour1 = obj1.getHours();
		                		var oMinute1 = obj1.getMinutes();
		                		
		                		var obj2 = new Date(data.endTime);
		                		var oMonth2 = obj2.getMonth()+1;
		                		var oDate2 = obj2.getDate();
		                		var oHour2 = obj2.getHours();
		                		var oMinute2 = obj2.getMinutes();
		                		
		                		time = oMonth1+"月"+getzf(oDate1)+"日"+getzf(oHour1)+":"+getzf(oMinute1)+" 至 "+oMonth2+"月"+getzf(oDate2)+"日"+getzf(oHour2)+":"+getzf(oMinute2);
		                		
		                		$(".welfareNotice .time p").text(time);
		                		
		                		
		                		var ruleDesc = "";
		                		var regCont = new RegExp("\n", 'g');
								if (data.ruleDesc != null) {
									ruleDesc = data.ruleDesc.replace(regCont,"<br/>");
								}
		                		
		                		$(".welfareKnows .wk-content").html(ruleDesc)
		                		
//		                		var EnrollCount = "";
//		                		if (data.toplimitNumber!=null && data.toplimitNumber!=0) {
//		                			EnrollCount = data.nowEnrollCount+"人/"+data.toplimitNumber+"人";
//		                		} else {
//		                			EnrollCount = data.nowEnrollCount+"人/不限制人数";
//		                		}
//		                		
//		                		$(".welfareNotice .number p").text(EnrollCount);

								if (data.headUrlList != "" && data.headUrlList != null && data.headUrlList.length != 0) {
									$(".welfareFollow h1").text(data.favoriteCount+"人关注");
									var headUrlList = "";
									$.each(data.headUrlList, function(i,item) {
										headUrlList += "<li><img src='"+item.headUrl+"' /></li>";
									});
									$(".welfareFollow ul").html(headUrlList)
								} else {
									$(".welfareFollow").hide();
								}
								
								

								nearbyList(data.lat,data.lng);
		                		
		                		locationUrl = apiUrl+"/jsp/ffxl/share/welfare.jsp?id="+id;
								wxtitle = "我在万心社看到一个有趣的活动："+data.activeName;
								wximgUrl = data.activeImg1;
								wxdesc = data.shortActiveDesc;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			众筹成功
			function crowdfundingLogList(activeId){
				$.ajax({
			        url: apiUrl+"/ffxl_active_v202/crowdfunding_log_list",
			        type: "post",
			        data: {activeId:activeId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 相关攻略
			function relevanceArticle(activeId){
				$.ajax({
			        url: apiUrl+"/ffxl_active/relevance_article",
			        type: "post",
			        data: {activeId:activeId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	$(".welfareStrategy .strategyInfo div").css("background-image","url("+data.imgUrl+")");
								$(".welfareStrategy .strategyInfo h1").text(data.title);
								$(".welfareStrategy .strategyInfo .strategyPraise").text(data.supportCount);
								$(".welfareStrategy .strategyInfo .strategyNum").text(data.readCount);
			                }else{
			                	
			                }
			            } else {
							$(".welfareStrategy").hide();
						}
			        }
			    });
			}


			// 附近活动
			function nearbyList(userLat,userLng){
				$.ajax({
			        url: apiUrl+"/ffxl_active/nearby_list_202",
			        type: "post",
			        data: {userLat:userLat,userLng:userLng},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
								var html = "";

								$.each(data, function(i,item) {

									var cdDate = item.endTime - new Date().getTime();
									var endDay = parseInt(cdDate / 1000 / 60 / 60 / 24 , 10);

									if (endDay > 0) {
										html+="<li>"+
											"<div style='background-image:url("+item.activeImg1+")'></div>"+
											"<h1>"+item.activeName+"</h1>"+
											"<p class='nearbyAddress'>"+item.address+"</p>"+
											"<p class='nearbyDate'>"+endDay+"天后结束</p>"+
										"</li>";
									}
									
								});

								$(".welfareNearby ul").html(html);

								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 评论
			function commentList(sourceId){
				$.ajax({
			        url: apiUrl+"/ambitus/active_estimate_list",
			        type: "post",
			        data: {sourceId:sourceId,sourceType:"active"},
			        dataType: "json",
			        // async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
								
								var html = "";
								$.each(data, function(i,item) {

									var time = ""
									var differTime = new Date().getTime()-item.estimateDatetime;
									if (differTime<=86400000) {
										var hours = parseInt(differTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
										var minutes = parseInt(differTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
										
										if (hours!=0) {
											time = hours+"小时前";
										} else if (minutes!=0) {
											time = minutes+"分钟前";
										} else {
											time = "1分钟前"
										}
									} else {
										var obj = new Date(item.estimateDatetime);
										// var oYear = obj.getFullYear();
										// var oMonth = obj.getMonth()+1;
										// var oDate = obj.getDate();
										var oHour = obj.getHours();
										var oMinute = obj.getMinutes();
										
										// time = oYear+"."+getzf(oMonth)+"."+getzf(oDate)+" "+getzf(oHour)+":"+getzf(oMinute);
										time = getzf(oHour)+":"+getzf(oMinute);
									}

									var headUrl = "";
									var name = "";
									if (item.see) {
										headUrl = apiUrl+"/jsp/ffxl/share/images/anonymous.png";
										name = "匿名用户"
									} else {
										headUrl = item.uHeadUrl;
										name = item.uName;
									}
						
									html += "<li>"+
										"<img class='commentHead' src='"+headUrl+"' />"+
										"<p class='commentName'>"+name+"</p>"+
										"<p class='commentTime'>"+time+"</p>"+
										"<p class='praiseNum'>"+item.pCount+"</p>"+
										"<div class='commentCont'>"+
											"<p>"+item.content+"</p>"+
										"</div>"+
									"</li>";
								});

								$(".welfareComment ul").html(html);
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
				var id = getQueryString("id");
//				var id = "3a501a96fcae40519982a06e437a86a6"
				detail(id);
				relevanceArticle(id);
				commentList(id)
				
				var bannerSwiper = new Swiper('#welfareBanner',{
				    pagination: {
						el: '.swiper-pagination',
					}
				})    

				var pptSwiper = new Swiper('#welfarePPT',{
					loop : true,
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
					navigation: {
						nextEl: '.swiper-button-next',
						prevEl: '.swiper-button-prev',
					},
					pagination: {
						el: '.swiper-pagination',
						type: 'fraction'
					},
				})   
				

				var followW =  $(".welfareFollow ul li").width();
				$(".welfareFollow ul li").css("height",followW)
				
//				展开图文详情
				$(".wi-detailBtn a").click(function(){
					if (!$(".wi-content").hasClass("detail")) {
						$(".wi-detailBtn a span").text("收起图文详情");
						$(".wi-detailBtn img").css("transform","rotate(180deg)");
						$(".wi-content").addClass("detail");
					} else{
						$(".wi-detailBtn a span").text("展开图文详情");
						$(".wi-detailBtn img").css("transform","rotate(0deg)");
						$(".wi-content").removeClass("detail");
					}
				})
				
				
				$(".wnBtn2").click(function(){
					$("#layer").show();
					$("body").append("<img class='welfareShare' src='images/welfareShare.png' />")
				})
				
				$("body").on("click","#layer,.welfareShare",function(){
					$("#layer").hide();
					$(".welfareShare").remove();
				})
				
				$("body").on("click",".praiseNum,.welfareStrategy .welfareTitle a,.welfareStrategy .strategyInfo,.welfareComment .welfareTitle a,.welfareNearby ul li",function(){
					more();
				})
				
			})
		</script>
	</body>
</html>
