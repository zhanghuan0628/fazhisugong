
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的团体</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/share/css/swiper-4.2.2.min.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/share/css/team.css?v=21"/>
	</head>
	<body>


		<div id="layer"></div>
		

		<!-- 不符合活动展示 -->
		<div id="notInParty">
			<div class="swiper-container" id="exhiImg">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<div style="background-image: url(images/active1.png)"></div>
					</div>
					<div class="swiper-slide">
						<div style="background-image: url(images/commingSoon.png)"></div>
					</div>
				</div>
			</div>
			
			<div class="readyBtn">

			</div>
		</div>

		<div id="inParty">
			<div class="swiper-container" id="teamNavi">
				<div class="swiper-wrapper">
					<!-- <div class="swiper-slide">
						<div class="teamNaviContainer">
							<p>每天一分钟学党史</p>
							<em></em>
						</div>
					</div>
					<div class="swiper-slide">
						<div class="teamNaviContainer">
							<p>每天一分钟学党史</p>
							<em></em>
						</div>
					</div>
					<div class="swiper-slide">
						<div class="teamNaviContainer">
							<p>每天一分钟学党史</p>
							<em></em>
						</div> 
					</div>-->
				</div>
			</div>

			<div class="teamDetail">

				<div class="startCountDown">
					<h1>队伍集结中..</h1>
					<p></p>
				</div>

				<div class="swiper-container" id="teamMapContainer">
					<div class="swiper-wrapper">
						
					</div>
					<div class="swiper-button-prev"></div>
					<div class="swiper-button-next"></div>
				</div>


				<div class="swiper-container" id="answerJourney">
					<div class="swiper-wrapper">
						<!-- <div class="swiper-slide">
							<div class="answerJourneyContainer">
								<p class="stations">第五站</p>
								<p class="date">14/<span>4</span><i>月</i></p>
								<div class="description">
									<div class="coverImg">
										<img src="" alt="">
									</div>
									<h1>通道会议</h1>
									<p>在红军第五次反“围剿”失败和长征初期严重受挫的情况下，为了纠正博古“左”倾领导在军事指挥上的错误而召开的。</p>
								</div>

								<p class="answered">已有<span>1626人</span>答题</p>

								<p class="answeredScore">得分<span>12</span></p>

								<a class="journeyBtn" id="journeyBtn1">
									<p>去答题</p>
									<img src="images/journeyBtn1.png" alt="">
								</a>

								<a class="journeyBtn" id="journeyBtn2">
									<p>组队去参赛</p>
									<img src="images/journeyBtn2.png" alt="">
								</a>

								<a class="journeyBtn" id="journeyBtn3">
									<p>请先完成上一站答题</p>
									<img src="images/journeyBtn3.png" alt="">
								</a>

								<a class="journeyBtn" id="journeyBtn4">
									<p>当日解锁</p>
									<img src="images/journeyBtn4.png" alt="">
								</a>

								<a class="journeyBtn" id="journeyBtn5">
									<p>活动已结束</p>
									<img src="images/journeyBtn4.png" alt="">
								</a>
							</div>
						</div> -->
					</div>
				</div>

				<div class="swiper-container" id="ranking">
					<div class="swiper-pagination"></div>
					<div class="swiper-wrapper">
						<div class="swiper-slide" id="answerBoard">
							<div class="boardLeft">
								<!-- <div class="selfRank">
									<i>6</i>
									<h1>IT部</h1>
									<div class="boardInfo">
										<p>836</p>
										<span>答对题数</span>
									</div>
								</div> -->
								<ul class="rankingList">
									<!-- <li>
										<i>6</i>
										<h1>IT部</h1>
										<div class="boardInfo">
											<p>836</p>
											<span>答对题数</span>
										</div>
									</li> -->
									
								</ul>
							</div>
							<div class="boardRight">
								<!-- <div class="selfRank">
									<i>6</i>
									<div class="boardUser">
										<img src="images/headurl.png" alt="">
										<span>张筱雨</span>
									</div>
									<div class="boardInfo">
										<p>836</p>
										<span>答对题数</span>
									</div>
								</div> -->
								<ul class="rankingList">
									<!-- <li>
										<i>6</i>
										<div class="boardUser">
											<img src="images/headurl.png" alt="">
											<span>张筱雨</span>
										</div>
										<div class="boardInfo">
											<p>836</p>
											<span>答对题数</span>
										</div>
									</li> -->
									
								</ul>
							</div>

							<a class="moreRank">查看总榜 ></a>
						</div>
						<div class="swiper-slide" id="popularityBoard">
							<div class="boardLeft">
								<!-- <div class="selfRank">
									<i>6</i>
									<h1>IT部</h1>
									<div class="boardPopular">
										<em class="popularBtn"></em>
										<span>2635</span>
									</div>
								</div> -->
								<ul class="rankingList">
									<!-- <li>
										<i>6</i>
										<h1>IT部</h1>
										<div class="boardPopular">
											<em class="popularBtn"></em>
											<span>2635</span>
										</div>
									</li> -->
									
								</ul>
							</div>
							<div class="boardRight">
								<!-- <div class="selfRank">
									<i>6</i>
									<div class="boardUser">
										<img src="images/headurl.png" alt="">
										<span>张筱雨</span>
									</div>
									<div class="boardPopular">
										<em class="popularBtn"></em>
										<span>2635</span>
									</div>
								</div> -->
								<ul class="rankingList">
									<!-- <li>
										<i>6</i>
										<div class="boardUser">
											<img src="images/headurl.png" alt="">
											<span>张筱雨</span>
										</div>
										<div class="boardPopular">
											<em class="popularBtn"></em>
											<span>2635</span>
										</div>
									</li> -->
									
								</ul>
							</div>
							<a class="moreRank">查看总榜 ></a>
						</div>
						<div class="swiper-slide" id="myTeam">
							<div class="memberList">

								<div class="notStart">
									<div class="teamInfo">
										<h1 class="teamName"><span></span></h1>
										<h2></h2>

										<ul class="teamMembers">
											<!-- <li>
												<img class="tm-head" src="images/headurl.png" alt="">
												<img class="teamLeader" src="images/leader.png" alt="">
											</li>
											<li>
												<img class="tm-head" src="images/headurl.png" alt="">
											</li>
											<li>
												<img class="tm-head" src="images/headurl.png" alt="">
											</li>
											<li>
												<img class="tm-head" src="images/headurl.png" alt="">
											</li> -->
										</ul>

									</div>
								</div>

								<div class="hasStart">
									<div class="teamInfo">
										<h1 class="teamName"><span></span></h1>
										<h2></h2>
										<div class="teamData">
											<div class="td-left">
												<p>答题榜排名<span></span></p>
												<p>累计答对题数<span></span></p>
											</div>
											<div class="td-right">
												<p>人气榜排名<span></span></p>
												<p>累计人气<span></span></p>
											</div>
										</div>
									</div>
									<div class="teamlist">
										<div class="selfData">
											<!-- <div class="memberUser">
												<img src="images/headurl.png" alt="">
												<span>张筱雨</span>
											</div>

											<div class="unlocked swiper-container">
												<div class="swiper-wrapper">
													<div class="swiper-slide">
														<i>3</i>
													</div>
													<div class="swiper-slide">
														<i>4</i>
													</div>
													<div class="swiper-slide">
														<i>5</i>
													</div>
												</div>
											
											</div>

											<div class="memberInfo">
												<p>836</p>
												<span>答对题数</span>
											</div>

											<div class="memberPopular">
												<em class="popularBtn"></em>
												<span>2635</span>
											</div> -->
										</div>
										<ul class="membersCondition">
											<!-- <li>
												<div class="memberUser">
													<img src="images/headurl.png" alt="">
													<span>张筱雨</span>
												</div>
				
												
												<div class="finished">
													<img src="images/finished.png" alt="">
												</div>
				
												<div class="memberInfo">
													<p>836</p>
													<span>答对题数</span>
												</div>
				
												<div class="memberPopular">
													<em class="popularBtn"></em>
													<span>2635</span>
												</div>
											</li>

											<li>
												<div class="memberUser">
													<img src="images/headurl.png" alt="">
													<span>张筱雨</span>
												</div>
				
												<div class="unlocked swiper-container">
													<div class="swiper-wrapper">
														<div class="swiper-slide">
															<i>3</i>
														</div>
														<div class="swiper-slide">
															<i>4</i>
														</div>
														<div class="swiper-slide">
															<i>5</i>
														</div>
													</div>
												
												</div>
				
												<div class="memberInfo">
													<p>836</p>
													<span>答对题数</span>
												</div>
				
												<div class="memberPopular">
													<em class="popularBtn"></em>
													<span>2635</span>
												</div>
											</li> -->
										</ul>
									</div>
									
								</div>

								<div class="notJoin">
									<div class="teamChoice">
										<div class="chooseJoin">
											<img src="${base}/jsp/ffxl/share/images/chooseJoin.png" alt="">
											<h1>加入团队</h1>
											<p>有<span>70</span>支团队正在招募队员</p>
										</div>
										<div class="chooseOrg">
											<img src="${base}/jsp/ffxl/share/images/chooseOrg.png" alt="">
											<h1>组建团队</h1>
											<p>有<span>60</span>支团队已经集结完毕</p>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
		
		
		
		<script src="${base}/jsp/ffxl/share/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/ffxl/share/js/swiper-4.2.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/ffxl/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript" src="https://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="${base}/jsp/ffxl/share/js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			// 活动列表
			function bannerList(sceId){
				$.ajax({
			        url: apiUrl+"/sce/banner_list",
			        type: "post",
			        data: {sceId:sceId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
								var html = "";
								

								$.each(data, function(i,item) {
									var begin = "0";
									if (new Date().getTime() - item.beginDate > 0) {
										begin = "1"
									}
									var end = "0";
									var endDate = item.endDate + 86400000;
									if (new Date().getTime() - endDate > 0) {
										end = "1"
									}
									
									html += "<div class='swiper-slide' data-actid = '"+item.id+"' data-end = '"+end+"' data-begin = '"+begin+"' data-begindate = '"+item.beginDate+"' data-enddate = '"+item.endDate+"'>"+
										"<div class='teamNaviContainer' style='background-image:url("+item.img+")'>"+
											"<p>"+item.name+"</p>"+
											"<em></em>"+
										"</div>"+
									"</div>"
								})

								$("#teamNavi .swiper-wrapper").html(html);
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}

			// 地图
			function getMap(userId,actBannerId){
				$.ajax({
			        url: apiUrl+"/ans_tem/get_map",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	var html = "";

								$.each(data, function(i,item) {
									if (item.inMap) {
										inMapNum = i;
									}
									html += "<div class='swiper-slide' data-mapid='"+item.id+"'>"+
										"<div class='teamMap'>"+
											"<img src = '"+item.mapImg+"' />"+
											"<div class='mapHint'>"+
												"<p><i class='flag1' style='transform: translateY(-50%);top: 50%;left: .8rem;'>8</i>我的团队位置，且有8个团队已到此站</p>"+
												"<p><i class='flag2' style='transform: translateY(-50%);top: 50%;left: .8rem;'>8</i>有8个团队已到此站</p>"+
												"<p><img class='flag3' src='${base}/jsp/ffxl/share/images/flag3.png' style='transform: translateY(-50%);top: 50%;left: .8rem;' />该站还未解锁</p>"+
											"</div>"+
										"</div>"+
									"</div>"
								})

								$("#teamMapContainer .swiper-wrapper").html(html);
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}

			// 旗帜,题目
			function getMapFlag(actBannerId,mapId,userId){
				$.ajax({
			        url: apiUrl+"/ans_tem/get_map_flag",
			        type: "post",
			        data: {actBannerId:actBannerId,mapId:mapId,userId:userId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								teamStatus = data.module;
			                	var data = data.data;
								var flagList = "";
			                	var html = "";
								

								$.each(data, function(i,item) {
									var position = eval("("+item.flagPosition+")");
									var positionX = position.x+"%";
									var positionY = position.y+"%";

									
									if (item.open == "1") {
										var finishCount = 0;
										if (item.finishCount != null) {
											finishCount = item.finishCount;
										}
										if (item.myTeamOnHere == true) {
											flagList = "<i class='flag1' style='bottom: "+positionY+";right: "+positionX+";'>"+finishCount+"</i>";
										} else {
											flagList += "<i class='flag2' style='bottom: "+positionY+";right: "+positionX+";'>"+finishCount+"</i>";
										}
										
									} else {
										flagList += "<img class='flag3' src='${base}/jsp/ffxl/share/images/flag3.png' style='bottom: "+positionY+";right: "+positionX+";' />"
									}
									



									var dataObj = new Date(item.pushDate);
			                		var unlockedMonth = dataObj.getMonth()+1;
			                		var unlockedDate = dataObj.getDate();

									if (hasEnd) {
										stationPosition = data.length - 1;
									}

									var stationStatus = "";

									if (teamStatus == "onTeam") {
										if (!hasEnd) {
											if (item.open == "1") {
												if (item.extAns == "1") {
													stationStatus = "<p class='answeredScore'>得分<span>"+item.uScore+"</span></p>";
												} else {
													if (item.beForeQstn == "1") {
														stationPosition = i;
														stationStatus = "<a class='journeyBtn journeyBtn1'>"+
															"<p>去答题</p>"+
															"<img src='${base}/jsp/ffxl/share/images/journeyBtn1.png' />"+
														"</a>"
													} else {
														stationStatus = "<a class='journeyBtn journeyBtn3'>"+
															"<p>请先完成上一站答题</p>"+
															"<img src='${base}/jsp/ffxl/share/images/journeyBtn3.png' />"+
														"</a>"
													}
												}
												
											} else {
												stationStatus = "<a class='journeyBtn journeyBtn4'>"+
													"<p>当日解锁</p>"+
													"<img src='${base}/jsp/ffxl/share/images/journeyBtn4.png' />"+
												"</a>"
											}
										} else {
											if (item.extAns == "1") {
												stationStatus = "<p class='answeredScore'>得分<span>"+item.uScore+"</span></p>";
											} else {
												stationStatus = "<a class='journeyBtn journeyBtn5'>"+
													"<p>活动已结束</p>"+
													"<img src='${base}/jsp/ffxl/share/images/journeyBtn4.png' />"+
												"</a>"
											}
											
										}
									} else if (teamStatus == "unTeam") {
										if (!hasEnd) {
											stationStatus = "<a class='journeyBtn journeyBtn2'>"+
												"<p>组队去参赛</p>"+
												"<img src='${base}/jsp/ffxl/share/images/journeyBtn2.png' />"+
											"</a>"
										} else {
											stationStatus = "<a class='journeyBtn journeyBtn5'>"+
												"<p>活动已结束</p>"+
												"<img src='${base}/jsp/ffxl/share/images/journeyBtn4.png' />"+
											"</a>"
										}
										
									} else {
										if (!hasEnd) {
											stationStatus = "<a class='journeyBtn journeyBtn6'>"+
												"<p>组队成功后才能答题</p>"+
												"<img src='${base}/jsp/ffxl/share/images/journeyBtn4.png' />"+
											"</a>"
										} else {
											stationStatus = "<a class='journeyBtn journeyBtn5'>"+
												"<p>活动已结束</p>"+
												"<img src='${base}/jsp/ffxl/share/images/journeyBtn4.png' />"+
											"</a>"
										}
										
									}


									html += "<div class='swiper-slide' data-flagid='"+item.flagId+"' data-sort='"+item.sort+"'>"+
										"<div class='answerJourneyContainer'>"+
											"<p class='stations'>第"+SectionToChinese(item.sort)+"站</p>"+
											"<p class='date'>"+unlockedDate+"/<span>"+unlockedMonth+"</span><i>月</i></p>"+
											"<div class='description'>"+
												"<div class='coverImg'>"+
													"<img src='"+item.flagImg+"' />"+
												"</div>"+
												"<h1>"+item.flagName+"</h1>"+
												"<p>"+item.flagSummary+"</p>"+
											"</div>"+

											"<p class='answered'>已有<span>"+item.ansCount+"人</span>答题</p>"+stationStatus+
										"</div>"+
									"</div>"
								})

								$("#teamMapContainer .swiper-slide").each(function(){
									if ($(this).data("mapid") == mapId) {
										$(this).children().append(flagList);
									}
								})

								$("#answerJourney .swiper-wrapper").html(html);

			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 团队答题榜
			function teamAnsph(userId,actBannerId){
				$.ajax({
			        url: apiUrl+"/ans_tem/team_ansph",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId,pageSize:10},
			        dataType: "json",
			        // async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								if (teamStatus != "unTeam") {
									if (data.module != null && data.module !="null") {
										var selfData = eval("("+data.module+")");
										var self = "";
										if (selfData.ranking == "未上榜") {
											self = "<div class='selfRank'>"+
												"<h1>"+selfData.tName+"</h1>"+
												"<p class='notIn'>未上榜</p>"+
											"</div>";
										} else {
											var rankNum = "";
											if (selfData.ranking == "1") {
												rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
											} else if (selfData.ranking == "2") {
												rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
											} else if (selfData.ranking == "3") {
												rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
											} else {
												rankNum = "<i>"+selfData.ranking+"</i>"
											}
											self = "<div class='selfRank'>"+rankNum+
												"<h1>"+selfData.tName+"</h1>"+
												"<div class='boardInfo'>"+
													"<p>"+selfData.ansCount+"</p>"+
													"<span>答对题数</span>"+
												"</div>"+
											"</div>";
										}

										$("#answerBoard .boardLeft .selfRank").remove();
										$("#answerBoard .boardLeft").prepend(self);
										if (hasBegin) {
											var ranking = selfData.ranking;
											if (selfData.ranking != "未上榜") {
												ranking = "第"+selfData.ranking+"名";
											}
											$("#myTeam .hasStart .teamInfo .teamData .td-left p").eq(0).find("span").text(ranking);
											var ansCount = 0;
											if (selfData.ansCount != "") {
												ansCount = selfData.ansCount;
											}
											$("#myTeam .hasStart .teamInfo .teamData .td-left p").eq(1).find("span").text(ansCount);
										}
									}
									
								}
						
								

			                	var data = data.data.dataList;
			                	var html = "";
								$.each(data, function(i,item) {
									var rankNum = "";
									if (item.ranking == "1") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
									} else if (item.ranking == "2") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
									} else if (item.ranking == "3") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
									} else {
										rankNum = "<i>"+item.ranking+"</i>"
									}
									html += "<li data-tid='"+item.tId+"'>"+rankNum+
										"<h1>"+item.tName+"</h1>"+
										"<div class='boardInfo'>"+
											"<p>"+item.ansCount+"</p>"+
											"<span>答对题数</span>"+
										"</div>"+
									"</li>"
								})

								$("#answerBoard .boardLeft .rankingList").html(html);

								rankAjaxNum++;
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 个人答题榜
			function preAnsph(userId,actBannerId){
				$.ajax({
			        url: apiUrl+"/ans_tem/pre_ansph",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId,pageSize:10},
			        dataType: "json",
			        // async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								if (teamStatus != "unTeam") {
									if (data.module != null && data.module !="null") {
										var selfData = eval("("+data.module+")");
										var self = "";
										if (selfData.ranking == "未上榜") {
											self = "<div class='selfRank'>"+
												"<div class='boardUser'>"+
													"<img src='"+selfData.tImg+"' />"+
													"<span>"+selfData.tName+"</span>"+
												"</div>"+
												"<p class='notIn'>未上榜</p>"+
											"</div>";
										} else {
											var rankNum = "";
											if (selfData.ranking == "1") {
												rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
											} else if (selfData.ranking == "2") {
												rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
											} else if (selfData.ranking == "3") {
												rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
											} else {
												rankNum = "<i>"+selfData.ranking+"</i>"
											}
											self = "<div class='selfRank'>"+rankNum+
												"<div class='boardUser'>"+
													"<img src='"+selfData.tImg+"' />"+
													"<span>"+selfData.tName+"</span>"+
												"</div>"+
												"<div class='boardInfo'>"+
													"<p>"+selfData.ansCount+"</p>"+
													"<span>答对题数</span>"+
												"</div>"+
											"</div>";
										}
										
										$("#answerBoard .boardRight .selfRank").remove();
										$("#answerBoard .boardRight").prepend(self);
									}
									
								}

			                	var data = data.data.dataList;
			                	var html = "";
								$.each(data, function(i,item) {
									var rankNum = "";
									if (item.ranking == "1") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
									} else if (item.ranking == "2") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
									} else if (item.ranking == "3") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
									} else {
										rankNum = "<i>"+item.ranking+"</i>"
									}
									html += "<li data-tid='"+item.tId+"'>"+rankNum+
										"<div class='boardUser'>"+
											"<img src='"+item.tImg+"' />"+
											"<span>"+item.tName+"</span>"+
										"</div>"+
										"<div class='boardInfo'>"+
											"<p>"+item.ansCount+"</p>"+
											"<span>答对题数</span>"+
										"</div>"+
									"</li>"
								})

								$("#answerBoard .boardRight .rankingList").html(html);

								rankAjaxNum++;
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 团队点赞榜
			function teamPraiseph(userId,actBannerId){
				$.ajax({
			        url: apiUrl+"/ans_tem/team_praiseph",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId,pageSize:10},
			        dataType: "json",
			        // async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								if (teamStatus != "unTeam") {
									if (data.module != null && data.module !="null") {
										var selfData = eval("("+data.module+")");
										var hasPraise = "";
										if (selfData.extPraise == "1") {
											hasPraise = " hasPraise";
										}
										var rankNum = "";
										if (selfData.ranking == "1") {
											rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
										} else if (selfData.ranking == "2") {
											rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
										} else if (selfData.ranking == "3") {
											rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
										} else if (selfData.ranking == "未上榜") {
											rankNum = "<i style='font-size:1rem'>"+selfData.ranking+"</i>"
										} else {
											rankNum = "<i>"+selfData.ranking+"</i>"
										}
										var self = "<div class='selfRank'>"+rankNum+
												"<h1>"+selfData.tName+"</h1>"+
												"<div class='boardPopular selfPopular'>"+
													"<em class='popularBtn"+hasPraise+"'><img src='${base}/jsp/ffxl/share/images/praiseLight.png' /></em>"+
													"<span>"+selfData.ansCount+"</span>"+
												"</div>"+
											"</div>";
										$("#popularityBoard .boardLeft").prepend(self);
										if (hasBegin) {
											var ranking = selfData.ranking;
											if (selfData.ranking != "未上榜") {
												ranking = "第"+selfData.ranking+"名";
											}
											$("#myTeam .hasStart .teamInfo .teamData .td-right p").eq(0).find("span").text(ranking);
											$("#myTeam .hasStart .teamInfo .teamData .td-right p").eq(1).find("span").text(selfData.ansCount);
										}
									}
									
								}

								

			                	var data = data.data.dataList;
			                	var html = "";
								$.each(data, function(i,item) {
									var teamHasPraise = "";
									if (item.extPraise == "1") {
										teamHasPraise = " hasPraise";
									}
									var rankNum = "";
									if (item.ranking == "1") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
									} else if (item.ranking == "2") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
									} else if (item.ranking == "3") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
									} else {
										rankNum = "<i>"+item.ranking+"</i>"
									}
									html += "<li data-tid='"+item.tId+"'>"+rankNum+
										"<h1>"+item.tName+"</h1>"+
										"<div class='boardPopular'>"+
											"<em class='popularBtn"+teamHasPraise+"'><img src='${base}/jsp/ffxl/share/images/praiseLight.png' /></em>"+
											"<span>"+item.ansCount+"</span>"+
										"</div>"+
									"</li>"
								})

								$("#popularityBoard .boardLeft .rankingList").html(html);

								rankAjaxNum++;
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}



			// 个人点赞榜
			function prePraiseph(userId,actBannerId){
				$.ajax({
			        url: apiUrl+"/ans_tem/pre_praiseph",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId,pageSize:10},
			        dataType: "json",
			        // async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								if (teamStatus != "unTeam") {
									if (data.module != null && data.module !="null") {
										var selfData = eval("("+data.module+")");
										var hasPraise = "";
										if (selfData.extPraise == "1") {
											hasPraise = " hasPraise";
										}
										var rankNum = "";
										if (selfData.ranking == "1") {
											rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
										} else if (selfData.ranking == "2") {
											rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
										} else if (selfData.ranking == "3") {
											rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
										} else if (selfData.ranking == "未上榜") {
											rankNum = "<i style='font-size:1rem'>"+selfData.ranking+"</i>"
										} else {
											rankNum = "<i>"+selfData.ranking+"</i>"
										}

										var self = "<div class='selfRank'>"+rankNum+
												"<div class='boardUser'>"+
													"<img src='"+selfData.tImg+"' />"+
													"<span>"+selfData.tName+"</span>"+
												"</div>"+
												"<div class='boardPopular selfPopular'>"+
													"<em class='popularBtn"+hasPraise+"'><img src='${base}/jsp/ffxl/share/images/praiseLight.png' /></em>"+
													"<span>"+selfData.ansCount+"</span>"+
												"</div>"+
											"</div>";
										$("#popularityBoard .boardRight .selfRank").remove();
										$("#popularityBoard .boardRight").prepend(self);
									}
									
								}

			                	var data = data.data.dataList;
			                	var html = "";
								$.each(data, function(i,item) {
									var teamHasPraise = "";
									if (item.extPraise == "1") {
										teamHasPraise = " hasPraise";
									}
									var rankNum = "";
									if (item.ranking == "1") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank1.png' />"
									} else if (item.ranking == "2") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank2.png' />"
									} else if (item.ranking == "3") {
										rankNum = "<img class='rankImg' src='${base}/jsp/ffxl/share/images/rank3.png' />"
									} else {
										rankNum = "<i>"+item.ranking+"</i>"
									}

									html += "<li data-tid='"+item.tId+"'>"+rankNum+
										"<div class='boardUser'>"+
											"<img src='"+item.tImg+"' />"+
											"<span>"+item.tName+"</span>"+
										"</div>"+
										"<div class='boardPopular'>"+
											"<em class='popularBtn"+teamHasPraise+"'><img src='${base}/jsp/ffxl/share/images/praiseLight.png' /></em>"+
											"<span>"+item.ansCount+"</span>"+
										"</div>"+
									"</li>"
								})

								$("#popularityBoard .boardRight .rankingList").html(html);

								rankAjaxNum++;
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 我的团体
			function myTeamInfo(userId,actBannerId,sceId){
				$.ajax({
			        url: apiUrl+"/ans_tem/my_team_info",
			        type: "post",
			        data: {userId:userId,actBannerId:actBannerId,sceId:sceId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								var teamData = eval("("+data.module+")");
								teamId = teamData.id;
								teamCode = teamData.teamCode;
								$(".teamInfo .teamName span").text(teamData.teamName);
								$(".teamInfo h2").text("队伍编号："+teamData.teamCode);
								var data = data.data;
								if (teamStatus == "onTeam") {
									if (hasBegin) {
										var selfData = data[0];
										var self = "";
										var selflock = "";

										if (selfData.extFinish == "allfinish") {
											selflock = "<div class='finished'>"+
												"<img src='${base}/jsp/ffxl/share/images/finished.png' />"+
											"</div>"
										} else {
											var unselflock = "";
											if (selfData.extFinish.indexOf(";") != -1) {
												var unselflockArr = selfData.extFinish.split(";");
												for (var j = 0; j < unselflockArr.length; j++) {
													unselflock += "<div class='swiper-slide'>"+
														"<i>"+unselflockArr[j]+"</i>"+
													"</div>";
												}
											} else {
												unselflock = "<div class='swiper-slide'>"+
														"<i>"+selfData.extFinish+"</i>"+
													"</div>";
											}
											
											selflock = "<div class='unlocked swiper-container' data-firstflagid = '"+selfData.firstFlagId+"'>"+
												"<div class='swiper-wrapper'>"+unselflock+
												"</div>"+
											"</div>"
										}

										var hasPraise = "";
										if (selfData.extPraise == "1") {
											hasPraise = " hasPraise";
										}
										

										self = "<div class='memberUser'>"+
											"<img src='"+selfData.uImg+"' />"+
											"<span>"+selfData.uName+"</span>"+
										"</div>"+selflock+

										"<div class='memberInfo'>"+
											"<p>"+selfData.ansCount+"</p>"+
											"<span>答对题数</span>"+
										"</div>"+
		
										"<div class='memberPopular selfPopular'>"+
											"<em class='popularBtn"+hasPraise+"'><img src='${base}/jsp/ffxl/share/images/praiseLight.png' /></em>"+
											"<span>"+selfData.pCount+"</span>"+
										"</div>"

										$("#myTeam .memberList .hasStart .selfData").html(self);
										var html = "";
										$.each(data, function(i,item) {
											if (i > 0) {
												var lock = "";
												if (item.extFinish == "allfinish") {
													lock = "<div class='finished'>"+
														"<img src='${base}/jsp/ffxl/share/images/finished.png' />"+
													"</div>"
												} else {
													var unlockArr = item.extFinish.split(";");
													var unlock = "";
													for (var j = 0; j < unlockArr.length; j++) {
														unlock += "<div class='swiper-slide'>"+
															"<i>"+unlockArr[j]+"</i>"+
														"</div>";
													}
													lock = "<div class='unlocked swiper-container' data-firstflagid = '"+item.firstFlagId+"'>"+
														"<div class='swiper-wrapper'>"+unlock+
														"</div>"+
													"</div>"
												}

												var teamHasPraise = "";
												if (item.extPraise == "1") {
													teamHasPraise = " hasPraise";
												}

												html += "<li data-tid = '"+item.uId+"'>"+
													"<div class='memberUser'>"+
														"<img src='"+item.uImg+"' />"+
														"<span>"+item.uName+"</span>"+
													"</div>"+lock+

													"<div class='memberInfo'>"+
														"<p>"+item.ansCount+"</p>"+
														"<span>答对题数</span>"+
													"</div>"+
					
													"<div class='memberPopular'>"+
														"<em class='popularBtn"+teamHasPraise+"'><img src='${base}/jsp/ffxl/share/images/praiseLight.png' /></em>"+
														"<span>"+item.pCount+"</span>"+
													"</div>"+
												"</li>"
											}
											
										})

										$("#myTeam .memberList .hasStart .membersCondition").html(html);
										
									} else {
										if (teamData.full) {
											$(".notStart .teamInfo").append("<img class='teamFull' src='${base}/jsp/ffxl/share/images/teamFull.png' />");
										} else {
											$(".notStart .teamInfo").append("<a class='inviteBtn'><img src='${base}/jsp/ffxl/share/images/inviteBtn.png' />邀请好友</a>");
											$(".notStart .teamInfo").append("<p class='gapFull'>距离组队成功还差"+teamData.gapFull+"人</p>");
										}

										var html = "";
										$.each(data, function(i,item) {
											if (i > 0 && i < 5) {
												var leader = "";
												if (i == 1) {
													leader = "<img class='teamLeader' src='${base}/jsp/ffxl/share/images/leader.png' />"
												}
												html += "<li>"+
													"<img class='tm-head' src='"+item.uImg+"' />"+leader+
												"</li>"
											}
											
										})

										$("#myTeam .memberList .notStart .teamMembers").html(html);
									}
								} else {
									if (teamData.full) {
										$(".notStart .teamInfo").append("<img class='teamFull' src='${base}/jsp/ffxl/share/images/teamFull.png' />");
									} else {
										$(".notStart .teamInfo").append("<a class='inviteBtn'><img src='${base}/jsp/ffxl/share/images/inviteBtn.png' />邀请好友</a>");
										$(".notStart .teamInfo").append("<p class='gapFull'>距离组队成功还差"+teamData.gapFull+"人</p>");
									}

									var html = "";
									$.each(data, function(i,item) {
										if (i > 0 && i < 5) {
											var leader = "";
											if (i == 1) {
												leader = "<img class='teamLeader' src='${base}/jsp/ffxl/share/images/leader.png' />"
											}
											html += "<li>"+
												"<img class='tm-head' src='"+item.uImg+"' />"+leader+
											"</li>"
										}
										
									})

									$("#myTeam .memberList .notStart .teamMembers").html(html);
								}
								
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}



			// 点赞个人
			function praisePerson(userId,beUserId,sceId,actBannerId,_this,from){
				$.ajax({
			        url: apiUrl+"/ans_tem/praise",
			        type: "post",
			        data: {userId:userId,beUserId:beUserId,actBannerId:actBannerId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								var praiseNum = parseInt(_this.siblings("span").text()) + 1;
								_this.siblings("span").text(praiseNum);
								_this.addClass("hasPraise");
								_this.find("img").show();
								setTimeout(function(){
									_this.find("img").hide();
								}, 1000);

								if (from == "1") {
									if (userId == beUserId) {
										if (_this.parent().hasClass("selfPopular")) {
											$("#popularityBoard .boardRight .rankingList li").each(function(){
												if ($(this).data("tid") == userId) {
													$(this).find(".boardPopular span").text(praiseNum);
													$(this).find(".popularBtn").addClass("hasPraise");
													$(this).find(".popularBtn").find("img").show();
													var $this = $(this);
													setTimeout(function(){
														$this.find(".popularBtn").find("img").hide();
													}, 1000);
												}
											});
										} else {
											$("#popularityBoard .boardRight .selfRank .boardPopular span").text(praiseNum);
											$("#popularityBoard .boardRight .selfRank .boardPopular em").addClass("hasPraise");

											$("#popularityBoard .boardRight .selfRank .boardPopular em img").show();
											setTimeout(function(){
												$("#popularityBoard .boardRight .selfRank .boardPopular em img").hide();
											}, 1000);
										}

									}
									if (teamStatus == "onTeam") {
										myTeamInfo(userId,actBannerId,sceId);
									}
								} else {
									if (userId == beUserId) {
										if (_this.parent().hasClass("selfPopular")) {
											$("#myTeam .teamlist .membersCondition li").each(function(){
												if ($(this).data("tid") == userId) {
													$(this).find(".memberPopular span").text(praiseNum);
													$(this).find(".popularBtn").addClass("hasPraise");
													$(this).find(".popularBtn").find("img").show();
													var $this = $(this);
													setTimeout(function(){
														$this.find(".popularBtn").find("img").hide();
													}, 1000);
												}
											});
										} else {
											$("#myTeam .teamlist .selfData .memberPopular span").text(praiseNum);
											$("#myTeam .teamlist .selfData .memberPopular em").addClass("hasPraise");

											$("#myTeam .teamlist .selfData .memberPopular em img").show();
											setTimeout(function(){
												$("#myTeam .teamlist .selfData .memberPopular em img").hide();
											}, 1000);
										}
									}
									prePraiseph(userId,actBannerId);
								}
								

								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}


			// 点赞团队
			function praiseTeam(userId,praiseTeamId,sceId,actBannerId,_this){
				$.ajax({
			        url: apiUrl+"/ans_tem/praise",
			        type: "post",
			        data: {userId:userId,teamId:praiseTeamId,actBannerId:actBannerId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								var praiseNum = parseInt(_this.siblings("span").text()) + 1;
								_this.siblings("span").text(praiseNum);
								_this.addClass("hasPraise");
								_this.find("img").show();
								setTimeout(function(){
									_this.find("img").hide();
								}, 1000);

								if (teamId == praiseTeamId) {
									if (_this.parent().hasClass("selfPopular")) {
										$("#popularityBoard .boardLeft .rankingList li").each(function(){
											if ($(this).data("tid") == teamId) {
												$(this).find(".boardPopular span").text(praiseNum);
												$(this).find(".popularBtn").addClass("hasPraise");
												$(this).find(".popularBtn").find("img").show();
												var $this = $(this);
												setTimeout(function(){
													$this.find(".popularBtn").find("img").hide();
												}, 1000);
											}
										});
									} else {
										$("#popularityBoard .boardLeft .selfRank .boardPopular span").text(praiseNum);
										$("#popularityBoard .boardLeft .selfRank .boardPopular em").addClass("hasPraise");

										$("#popularityBoard .boardLeft .selfRank .boardPopular em img").show();

										setTimeout(function(){
											$("#popularityBoard .boardLeft .selfRank .boardPopular em img").hide();
										}, 1000);
									}

								}
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}



			// 组队情况
			function teamCount(actBannerId){
				$.ajax({
			        url: apiUrl+"/ans_tem/un_on_team_count",
			        type: "post",
			        data: {actBannerId:actBannerId},
			        dataType: "json",
			        // async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
								var data = data.data;
								$(".chooseJoin p span").text(data.unFullCount);
								$(".chooseOrg p span").text(data.fullCount);
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}



			// 地图初始化
			function mapInit(){
				getMap(userId,actBannerId);
				if (mapSwiper != undefined) {
					mapSwiper.destroy();
				}
				mapSwiper = new Swiper('#teamMapContainer',{
					// loop : true,
					initialSlide : inMapNum,
					autoHeight: true,
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
					navigation: {
						nextEl: '.swiper-button-next',
						prevEl: '.swiper-button-prev',
					},
					on: {
						init: function(){
							mapId = $("#teamMapContainer .swiper-slide").eq(this.activeIndex).data("mapid");
							flagInit();
							rankingInit();
						},
						slideChangeTransitionEnd: function(){
							mapId = $("#teamMapContainer .swiper-slide").eq(this.activeIndex).data("mapid");
							flagInit();
							rankingInit();
						}
					}
				})  
			}


			// 题站初始化
			function flagInit(){
				getMapFlag(actBannerId,mapId,userId);

				if (journeySwiper != undefined) {
					journeySwiper.destroy();
				}
				journeySwiper = new Swiper('#answerJourney',{
					initialSlide : stationPosition,
					slidesPerView: 'auto',
					centeredSlides: true,
					// loop : true,
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
					on: {
						init: function(){
							$("#answerJourney .swiper-slide .answerJourneyContainer").removeClass("scaleAnswer");
							$("#answerJourney .swiper-slide").eq(this.activeIndex).find(".answerJourneyContainer").addClass("scaleAnswer");
							if (this.activeIndex + 1 == this.slides.length) {
								flagEdgeR = this.translate;
							}
						},
						slideChangeTransitionEnd: function(){
							$("#answerJourney .swiper-slide .answerJourneyContainer").removeClass("scaleAnswer");
							$("#answerJourney .swiper-slide").eq(this.activeIndex).find(".answerJourneyContainer").addClass("scaleAnswer");
							if (this.activeIndex + 1 == this.slides.length) {
								flagEdgeR = this.translate;
							}
							
						},
						sliderMove: function(){
							if (this.activeIndex == 0) {
								flagEdgeLTranslate = this.translate;
								if (Math.abs(flagEdgeLTranslate) - 0 >= 60) {
									mapSwiper.slidePrev();
								}
							}
							if (this.activeIndex + 1 == this.slides.length) {
								flagEdgeRTranslate = this.translate;
								if (Math.abs(flagEdgeRTranslate) - Math.abs(flagEdgeR) >= 60) {
									mapSwiper.slideNext();
								}
							}
							
						},
					}
				})   
			}


			// 排行榜初始化
			function rankingInit(){
				$(".memberList>div,.startCountDown").hide();
				$("#answerBoard,#popularityBoard,.inviteBtn,.notStart .teamInfo .gapFull").remove();
				$("#ranking>.swiper-wrapper").prepend("<div class='swiper-slide' id='answerBoard'>"+
					"<div class='boardLeft'><ul class='rankingList'></ul></div>"+
					"<div class='boardRight'><ul class='rankingList'></ul></div>"+

					"<a class='moreRank'>查看总榜 ></a>"+
				"</div>"+
				"<div class='swiper-slide' id='popularityBoard'>"+
					"<div class='boardLeft'><ul class='rankingList'></ul></div>"+
					"<div class='boardRight'><ul class='rankingList'></ul></div>"+
					"<a class='moreRank'>查看总榜 ></a>"+
				"</div>")
				
				if (teamStatus == "onTeam") {
					
					myTeamInfo(userId,actBannerId,sceId);
					if (hasBegin) {
						teamAnsph(userId,actBannerId);
						preAnsph(userId,actBannerId);
						teamPraiseph(userId,actBannerId);
						prePraiseph(userId,actBannerId);
						$(".hasStart").show();

						var timer = setInterval(function(){
							if (rankAjaxNum == 4) {
								rankAjaxNum = 0;
								clearInterval(timer)
								if (rankSwiper != undefined) {
									rankSwiper.destroy();
								}
								rankSwiper = new Swiper('#ranking',{
									autoHeight: true,
									observer:true, //修改swiper自己或子元素时，自动初始化swiper
									observeParents:true,
									pagination: {
										el: '.swiper-pagination',
										clickable: true,
										renderBullet: function (index, className) {
											switch(index){
												case 0:text='答题榜';break;
												case 1:text='人气榜';break;
												case 2:text='我的团队';break;
											}
											return '<span class="' + className + '">' + text + '<em></em></span>';
										},
									}
								})  


								lockSwiper = new Swiper('.unlocked',{
									slidesPerView: 2.5,
									freeMode : true,
									observer:true, //修改swiper自己或子元素时，自动初始化swiper
									observeParents:true
								}) 
							}
						},100)
						
						
					} else {
						$("#answerBoard,#popularityBoard").remove();
						countDown(beginDate);
						$(".startCountDown").show();
						$(".notStart").show();

						if (rankSwiper != undefined) {
							rankSwiper.destroy();
						}
						rankSwiper = new Swiper('#ranking',{
							observer:true, //修改swiper自己或子元素时，自动初始化swiper
							observeParents:true,
							pagination: {
								el: '.swiper-pagination',
								clickable: true,
								renderBullet: function (index, className) {
									switch(index){
										case 0:text='我的团队';break;
									}
									return '<span class="' + className + '">' + text + '<em></em></span>';
								},
							}
						})  
					}
					
				} else if (teamStatus == "unFull") {
					myTeamInfo(userId,actBannerId,sceId);
					if (hasBegin) {
						teamAnsph(userId,actBannerId);
						preAnsph(userId,actBannerId);
						teamPraiseph(userId,actBannerId);
						prePraiseph(userId,actBannerId);
						$(".notStart").show();

						var timer = setInterval(function(){
							if (rankAjaxNum == 4) {
								rankAjaxNum = 0;
								clearInterval(timer)
								if (rankSwiper != undefined) {
									rankSwiper.destroy();
								}
								rankSwiper = new Swiper('#ranking',{
									autoHeight: true,
									observer:true, //修改swiper自己或子元素时，自动初始化swiper
									observeParents:true,
									pagination: {
										el: '.swiper-pagination',
										clickable: true,
										renderBullet: function (index, className) {
											switch(index){
												case 0:text='答题榜';break;
												case 1:text='人气榜';break;
												case 2:text='我的团队';break;
											}
											return '<span class="' + className + '">' + text + '<em></em></span>';
										},
									}
								})  
							}
						},100)
						

					} else {
						$("#answerBoard,#popularityBoard").remove();
						countDown(beginDate);
						$(".startCountDown").show();
						$(".notStart").show();

						if (rankSwiper != undefined) {
							rankSwiper.destroy();
						}
						rankSwiper = new Swiper('#ranking',{
							observer:true, //修改swiper自己或子元素时，自动初始化swiper
							observeParents:true,
							pagination: {
								el: '.swiper-pagination',
								clickable: true,
								renderBullet: function (index, className) {
									switch(index){
										case 0:text='我的团队';break;
									}
									return '<span class="' + className + '">' + text + '<em></em></span>';
								},
							}
						})  
					}
				} else if (teamStatus == "unTeam") {
					$(".notJoin").show();
					if (hasBegin) {
						teamAnsph(userId,actBannerId);
						preAnsph(userId,actBannerId);
						teamPraiseph(userId,actBannerId);
						prePraiseph(userId,actBannerId);
						teamCount(actBannerId);

						var timer = setInterval(function(){
							if (rankAjaxNum == 4) {
								rankAjaxNum = 0;
								clearInterval(timer)
								if (rankSwiper != undefined) {
									rankSwiper.destroy();
								}
								rankSwiper = new Swiper('#ranking',{
									autoHeight: true,
									observer:true, //修改swiper自己或子元素时，自动初始化swiper
									observeParents:true,
									pagination: {
										el: '.swiper-pagination',
										clickable: true,
										renderBullet: function (index, className) {
											switch(index){
												case 0:text='答题榜';break;
												case 1:text='人气榜';break;
												case 2:text='我的团队';break;
											}
											return '<span class="' + className + '">' + text + '<em></em></span>';
										},
									}
								})  
							}
						},100)
						
						
					} else {
						$("#answerBoard,#popularityBoard").remove();
						countDown(beginDate)
						$(".startCountDown").show();
						teamCount(actBannerId);
						if (rankSwiper != undefined) {
							rankSwiper.destroy();
						}
						rankSwiper = new Swiper('#ranking',{
							observer:true, //修改swiper自己或子元素时，自动初始化swiper
							observeParents:true,
							pagination: {
								el: '.swiper-pagination',
								clickable: true,
								renderBullet: function (index, className) {
									switch(index){
										case 0:text='我的团队';break;
									}
									return '<span class="' + className + '">' + text + '<em></em></span>';
								},
							}
						})  
					}
					
					

					
					
				} else {

				}
			}

			// 倒计时
			function countDown(beginDate){
				var nowDate = new Date().getTime();
				var cdDate = beginDate-nowDate;
				var days = parseInt(cdDate / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
				var hours = parseInt(cdDate / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
				var minutes = parseInt(cdDate / 1000 / 60 % 60, 10);//计算剩余的分钟 
                var seconds = parseInt(cdDate / 1000 % 60, 10);//计算剩余的秒数
				
				$(".startCountDown p").html("剩余"+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");
				setInterval(function(){
					if (nowDate == 0) {
						location.reload();
						return false;
					}
					var nowDate = new Date().getTime();
					var cdDate = beginDate-nowDate;
					var days = parseInt(cdDate / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
					var hours = parseInt(cdDate / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
					var minutes = parseInt(cdDate / 1000 / 60 % 60, 10);//计算剩余的分钟 
					var seconds = parseInt(cdDate / 1000 % 60, 10);//计算剩余的秒数
					
					$(".startCountDown p").html("剩余"+days+"天"+hours+"小时"+minutes+"分"+seconds+"秒");
					nowDate--;
				},1000)
            }


		




			var sceId = "";
			var userId = "";

			var mapId = "";
			var sceName = "";
			var bannerName = "";
			var inMapNum = 0;
			var beginDate = "";
			var endDate = "";
			var hasBegin = false;
			var hasEnd = false;
			var teamStatus = "";
			var stationPosition = 0;
			var actBannerId = "";
			var teamId = "";
			var teamCode = "";

			var ansLogId = "";
			var report;


			var mapSwiper;
			var journeySwiper;
			var rankSwiper;
			var lockSwiper;

			var flagEdgeL = 0;
			var flagEdgeLTranslate = 0;

			var flagEdgeR = 0;
			var flagEdgeRTranslate = 0;


			var rankAjaxNum = 0;


			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";

			$(function(){


				var Url = location.search;
				if (Url.indexOf("state")!=-1) {
					var Request = new getAuthQueryString();
					sceId = Request.sceId;
					console.log(Request.sceId)
				} else {
					sceId = getQueryString("sceId");
				}


				if (isWeChat()) {
					var oauth = "${oauth}";
					var headUrl = '';
					var nickName = '';
					if (oauth == 'true') {
						userId = "${user.id}";
						headUrl = "${user.headUrl}";
						nickName = "${user.name}";
						localStorage.setItem("ffxlWXuserId",userId);
						localStorage.setItem("ffxlWXheadImg",headUrl);
						localStorage.setItem("ffxlWXnickName",nickName);
						console.log("授权成功："+userId);
					} else {
						if (localStorage.getItem("ffxlWXuserId") != null &&localStorage.getItem("ffxlWXuserId") != "") {
							userId = localStorage.getItem("ffxlWXuserId");
							headUrl = localStorage.getItem("ffxlWXheadImg");
							nickName = localStorage.getItem("ffxlWXnickName");
						} else {
							ffxlwxAuthorization("/ffxl/share/myTeam.jsp?sceId="+sceId);
						}
					}

					
				} else{
					if (localStorage.getItem("ffxlQQuserId")==null) {
						QC.Login.showPopup({
							appId:"101443687",
							redirectURI:apiUrl+"/jsp/ffxl/share/callBack.html?page=myTeam&sceId="+sceId
						});
						
					} else{
						userId = localStorage.getItem("ffxlQQuserId");
						
					}
				}

				if (sceId == null || sceId == "") {
					$("#notInParty").show();
					var exhiSwiper = new Swiper('#exhiImg',{
						slidesPerView: 'auto',
						centeredSlides: true,
						observer:true, //修改swiper自己或子元素时，自动初始化swiper
						observeParents:true
					})   

					$(".readyBtn").append("<p><a class='readyBtn1'>加入团体</a></p><p><a class='readyBtn2'>业务咨询</a></p>")

					$("body").on("click",".readyBtn1",function(){
						if (isAndroid()) {
							window.android.toJoinSce();
						} else{
							window.webkit.messageHandlers.toJoinSce.postMessage(null);
						}
					})
					return false;
				} else {
					$("#inParty").show();
				}

	
				// sceId = "1";
				// userId = "0223a4fcc7ec4994bdefcda25cd6bbd4";

				bannerList(sceId);


				var naviSwiper = new Swiper('#teamNavi',{
					slidesPerView: 'auto',
					centeredSlides: true,
					// loop : true,
			        observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
					on: {
						init: function(){
							actBannerId = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("actid");
							var begin = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("begin");
							if (begin == "1") {
								hasBegin = true;
							} else {
								hasBegin = false;
							}
							var end = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("end");
							if (end == "1") {
								hasEnd = true;
							} else {
								hasEnd = false;
							}

							beginDate = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("begindate");
							endDate = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("enddate");
							
							bannerName = $("#teamNavi .swiper-slide").eq(this.activeIndex).find("p").text();

							mapInit();
						},
						slideChangeTransitionEnd: function(){
							actBannerId = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("actid");
							var begin = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("begin");
							if (begin == "1") {
								hasBegin = true;
							} else {
								hasBegin = false;
							}
							var end = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("end");
							if (end == "1") {
								hasEnd = true;
							} else {
								hasEnd = false;
							}

							beginDate = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("begindate");
							endDate = $("#teamNavi .swiper-slide").eq(this.activeIndex).data("enddate");
							
							bannerName = $("#teamNavi .swiper-slide").eq(this.activeIndex).find("p").text();

							mapInit();
						}
					}
				})    




				$("input[name='teamName']").on("input propertychange",function(){
					if ($(this).val().trim() != "") {
						$(".chooseOrgPop a").addClass("hasCont");
					} else {
						$(".chooseOrgPop a").removeClass("hasCont");
						$(this).val("");
					}
				});

				$("input[name='teamCode']").on("input propertychange",function(){
					if ($(this).val().trim() != "") {
						$(".chooseJoinPop a").addClass("hasCont");
					} else {
						$(".chooseJoinPop a").removeClass("hasCont");
						$(this).val("");
					}
				});


				// 点赞个人
				$("#ranking").on("click","#popularityBoard .boardRight .boardPopular",function(){
					if (!$(this).find(".popularBtn").hasClass("hasPraise")) {
						var _this = $(this).find(".popularBtn");
						if (_this.parent().hasClass("selfPopular")) {
							praisePerson(userId,userId,sceId,actBannerId,_this,"1");
						} else {
							var beUserId = _this.parents("li").data("tid");
							praisePerson(userId,beUserId,sceId,actBannerId,_this,"1");
						}

					}
				})


				$("#ranking").on("click","#myTeam .teamlist .memberPopular",function(){
					if (!$(this).find(".popularBtn").hasClass("hasPraise")) {
						var _this = $(this).find(".popularBtn");
						if (_this.parent().hasClass("selfPopular")) {
							praisePerson(userId,userId,sceId,actBannerId,_this,"2");
						} else {
							var beUserId = _this.parents("li").data("tid");
							praisePerson(userId,beUserId,sceId,actBannerId,_this,"2");
						}

					}
				})


				// 点赞团队
				$("#ranking").on("click","#popularityBoard .boardLeft .boardPopular",function(){
					if (!$(this).find(".popularBtn").hasClass("hasPraise")) {
						var _this = $(this).find(".popularBtn");
						if (_this.parent().hasClass("selfPopular")) {
							praiseTeam(userId,teamId,sceId,actBannerId,_this);
						} else {
							var praiseTeamId = _this.parents("li").data("tid");
							praiseTeam(userId,praiseTeamId,sceId,actBannerId,_this);
						}
					}
					
				})


				// 加入团队
				$(".chooseJoin").click(function(){
					more();
				})


				// 创建团队
				$(".chooseOrg").click(function(){
					more();
				})




				// 答题
				$("#answerJourney").on("click",".journeyBtn1",function(){
					more();
				})

				$(".memberList .hasStart .selfData").on("click",".unlocked",function(){
					if (teamStatus == "onTeam" && !hasEnd) {
						more();
					}
				});

				$(".memberList .hasStart .membersCondition").on("click",".unlocked",function(){
					if (teamStatus == "onTeam" && $(this).parents("li").data("tid") == userId) {
						more();
					}
				});


				$("#ranking").on("click","#answerBoard .moreRank",function(){
					more();
				})

				$("#ranking").on("click","#popularityBoard .moreRank",function(){
					more();
				})


				// 邀请好友
				$("body").on("click",".inviteBtn",function(){
					more();
				});



				locationUrl = apiUrl+"/jsp/ffxl/share/myTeam.jsp";
				wxtitle = sceName+"正在举办一个有趣的活动："+bannerName;
				wximgUrl= apiUrl+"/jsp/ffxl/share/images/wxShare.png";

				var beginDateStr = ""
			                		
				var obj1 = new Date(beginDate);
				var oMonth1 = obj1.getMonth()+1;
				var oDate1 = obj1.getDate();
				
				beginDateStr = oMonth1+"月"+oDate1+"日";

				var endDateStr = ""
			                		
				var obj2 = new Date(endDate);
				var oMonth2 = obj2.getMonth()+1;
				var oDate2 = obj2.getDate();
				
				endDateStr = oMonth2+"月"+oDate2+"日";
				wxdesc = beginDateStr+" - "+endDateStr;

				wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);

				
			})
		</script>
	</body>
</html>
