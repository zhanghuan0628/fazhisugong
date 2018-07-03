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
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/share/css/global.css?v=1"/>
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/share/css/index.css"/>
	</head>
	<body>
		
		<div class="teamShareBg">
			<div class="teamSharePerson">
				<img class="teamShareHead" src="" alt="">
				<p class="teamShareName"></p>
				<a class="cheerBtn1">给TA加油</a>
				<ul>
					<li class="teamShareData1">
						<i>累计总数：</i>
						<span></span>
					</li>
					<li class="teamShareData2">
						<i>答题帮排名：</i>
						<span></span>
					</li>
					<li class="teamShareData3">
						<i>人气榜排名：</i>
						<span></span>
					</li>
				</ul>
			</div>



			<div class="cheerUp">
				<div class="cheerTeam">
					<h1 class="cheerTitle"><span>给TA的队伍加油</span></h1>
					<h2></h2>
					<a class="cheerBtn2">加油</a>

					<div class="teamData">
						<div class="tdL">
							<p class="td1">答题榜排名：<span></span></p>
							<p class="td2">累计答对题数：<span></span></p>
						</div>
						<div class="tdR">
							<p class="td1">人气榜排名：<span></span></p>
							<p class="td2">累计人气：<span></span></p>
						</div>
					</div>
				</div>


				<div class="cheerTeamMate">
					<h1 class="cheerTitle"><span>给TA的队友加油</span></h1>

					<ul>
						<!-- <li>
							<img src="" />
							<p class="teamMateName">姓名</p>
							<div class="qNum">
								<p>120</p>
								<h2>答对题数</h2>
							</div>
							<div class="pNum">
								<p>120</p>
								<h2>人气值</h2>
							</div>
							<a class="cheerBtn2">加油</a>
						</li> -->
					</ul>
				</div>
			</div>
			
		</div>
		
		<script src="${base}/jsp/ffxl/share/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/ffxl/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript" src="https://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="${base}/jsp/ffxl/share/js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">

			function TeamInfo(userId,actBannerId,ckPraiseUserId){
				$.ajax({
			        url: apiUrl+"/ans_tem/share_team",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId,ckPraiseUserId:ckPraiseUserId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								var data = data.data;
								teamId = data.teamId;
								$(".teamSharePerson .teamShareHead").attr("src",data.headUrl);
								$(".teamSharePerson .teamShareName").text(data.name);
								
								if (data.proRqPraise == 1) {
									$(".cheerBtn1").addClass("hasCheer1").text("已加油");
								}

								$(".teamSharePerson ul .teamShareData1 span").text(data.proAnsCount+"分");
								var proAnsRank = data.proAnsRank;
								if (data.proAnsRank != "未上榜") {
									proAnsRank = "第"+data.proAnsRank+"名";
								}
								$(".teamSharePerson ul .teamShareData2 span").text(proAnsRank);
								var proRqRank = data.proRqRank;
								if (data.proRqRank != "未上榜") {
									proRqRank = "第"+data.proRqRank+"名";
								}
								$(".teamSharePerson ul .teamShareData3 span").text(proRqRank);
									
								$(".cheerTeam h2").text(data.teamName)

								var ansRank = data.ansRank;
								if (data.ansRank != "未上榜") {
									ansRank = "第"+data.ansRank+"名";
								}
								$(".cheerTeam .teamData .tdL .td1 span").text(ansRank);
								$(".cheerTeam .teamData .tdL .td2 span").text(data.ansCount);

								var praiseRank = data.praiseRank;
								if (data.praiseRank != "未上榜") {
									praiseRank = "第"+data.praiseRank+"名";
								}
								$(".cheerTeam .teamData .tdR .td1 span").text(praiseRank);
								$(".cheerTeam .teamData .tdR .td2 span").text(data.praiseCount);

								if (data.teamExtPraise == 1) {
									$(".cheerTeam .cheerBtn2").addClass("hasCheer2").text("已加油");
								}

								var html = "";
								$.each(data.memberList, function(i,item) {
									if (item.uId != userId) {
										var cheerBtn = "";
										if (item.extPraise == 0) {
											cheerBtn = "<a class='cheerBtn2'>加油</a>";
										} else {
											cheerBtn = "<a class='cheerBtn2 hasCheer2'>已加油</a>";
										}
										html += "<li data-id='"+item.uId+"'>"+
											"<img src='"+item.uImg+"' />"+
											"<p class='teamMateName'>"+item.uName+"</p>"+
											"<div class='qNum'>"+
												"<p>"+item.ansCount+"</p>"+
												"<h2>答对题数</h2>"+
											"</div>"+
											"<div class='pNum'>"+
												"<p>"+item.pCount+"</p>"+
												"<h2>人气值</h2>"+
											"</div>"+cheerBtn+
										"</li>";
									}
									
								})

								$(".cheerTeamMate ul").html(html);


								// 分享信息
								var bannerInfo = data.banner;


								locationUrl = apiUrl+"/jsp/ffxl/share/teamShare.jsp?userId="+userId+"&actBannerId="+actBannerId;
								wxtitle = "我正在参加《"+bannerInfo.name+"》，快来为我加油吧";
								wximgUrl= bannerInfo.img;

								var beginDateStr = ""
			                		
								var obj1 = new Date(bannerInfo.beginDate);
								var oMonth1 = obj1.getMonth()+1;
								var oDate1 = obj1.getDate();
								
								beginDateStr = oMonth1+"月"+oDate1+"日";
				
								var endDateStr = ""
													
								var obj2 = new Date(bannerInfo.endDate);
								var oMonth2 = obj2.getMonth()+1;
								var oDate2 = obj2.getDate();
								
								endDateStr = oMonth2+"月"+oDate2+"日";
								wxdesc = "活动时间："+beginDateStr+"~"+endDateStr;

								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);


			                }else{
			                	
			                }
			            }
			        }
			    });
			}



			// 点赞个人
			function praisePerson(userId,beUserId,actBannerId,trueUserId,_this){
				$.ajax({
			        url: apiUrl+"/ans_tem/praise",
			        type: "post",
			        data: {userId:userId,beUserId:beUserId,actBannerId:actBannerId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								
								// TeamInfo(trueUserId,actBannerId,sceId);
								if (_this.hasClass("cheerBtn1")) {
									_this.addClass("hasCheer1").text("已加油");
								} else {
									_this.addClass("hasCheer2").text("已加油");
									var pNum = parseInt(_this.siblings(".pNum").find("p").text());
									_this.siblings(".pNum").find("p").text(pNum+1)
								}
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 点赞团队
			function praiseTeam(userId,praiseTeamId,actBannerId,beUserId,_this){
				$.ajax({
			        url: apiUrl+"/ans_tem/praise",
			        type: "post",
			        data: {userId:userId,teamId:praiseTeamId,actBannerId:actBannerId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								// TeamInfo(beUserId,actBannerId,sceId);
								_this.addClass("hasCheer2").text("已加油");
								var pNum = parseInt($(".teamData .tdR .td2 span").text());
								$(".teamData .tdR .td2 span").text(pNum+1);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}

			var teamId = "";

			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
				var userId = "";
				var actBannerId = "";
				var Url = location.search;
				if (Url.indexOf("%3F")!=-1) {
					var Request = new getAuthQueryString();
					userId = Request.userId;
					actBannerId = Request.actBannerId;
				} else {
					userId = getQueryString("userId");
					actBannerId = getQueryString("actBannerId");
				}

//				判断授权
				var ckPraiseUserId = '';
				
				if (isWeChat()) {
					var oauth = "${oauth}";
					var headUrl = '';
					var nickName = '';
					if (oauth == 'true') {
						ckPraiseUserId = "${user.id}";
						headUrl = "${user.headUrl}";
						nickName = "${user.name}";
						localStorage.setItem("ffxlWXuserId",ckPraiseUserId);
						localStorage.setItem("ffxlWXheadImg",headUrl);
						localStorage.setItem("ffxlWXnickName",nickName);
						console.log("授权成功："+nickName);
					} else {
						if (localStorage.getItem("ffxlWXuserId") != null &&localStorage.getItem("ffxlWXuserId") != "") {
							ckPraiseUserId = localStorage.getItem("ffxlWXuserId");
							headUrl = localStorage.getItem("ffxlWXheadImg");
							nickName = localStorage.getItem("ffxlWXnickName");
						} else {
							ffxlwxAuthorization("/ffxl/share/teamShare.jsp?userId="+userId+"@actBannerId="+actBannerId);
						}
					}

					
				} else{
					if (localStorage.getItem("ffxlQQuserId")==null) {
						QC.Login.showPopup({
							appId:"101443687",
							redirectURI:apiUrl+"/jsp/ffxl/share/callBack.html?page=teamShare&userId="+userId+"&actBannerId="+actBannerId
						});
						
					} else{
					}
						ckPraiseUserId = localStorage.getItem("ffxlQQuserId");
						
				}

				TeamInfo(userId,actBannerId,ckPraiseUserId);

				$(".cheerBtn1").click(function(){
					var _this = $(this);
					if (!$(this).hasClass("hasCheer1")) {
						praisePerson(ckPraiseUserId,userId,actBannerId,userId,_this)
					}
				})

				$(".cheerTeam").on("click",".cheerBtn2",function(){
					if (!$(this).hasClass("hasCheer2")) {
						var _this = $(this);
						praiseTeam(ckPraiseUserId,teamId,actBannerId,userId,_this)
					}
				})
				
				$(".cheerTeamMate").on("click",".cheerBtn2",function(){
					if (!$(this).hasClass("hasCheer2")) {
						var _this = $(this);
						var bePraiseId = $(this).parents("li").data("id");
						praisePerson(ckPraiseUserId,bePraiseId,actBannerId,userId,_this)
					}
				})
			})
		</script>
	</body>
</html>
