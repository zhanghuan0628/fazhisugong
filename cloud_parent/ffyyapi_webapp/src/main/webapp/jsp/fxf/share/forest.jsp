<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>小凡森林</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/swiper-3.4.2.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/forest.css?v=20180410"/>
	</head>
	<body>
		<div id="layer"></div>
		<div class="floor" style="position: absolute;top: 0;left: 0;">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
		
		<div class="forest">
			
			
			<!--背景-->
			<img class="forestBg" src=""/>
			<!--左边-->
			<div class="treeExp">
				<p class="treeCode"></p>
				<p class="treeExpNum"></p>
				<div class="treeExpBar">
					<em></em>
				</div>
				<i>种树</i>
				
				<input type="hidden" name="treeId" id="treeId" value="" />
				<input type="hidden" name="treeExp" id="treeExp" value="" />
				<input type="hidden" name="treeSize" id="treeSize" value="" />
				<input type="hidden" name="treeQid" id="treeQid" value="" />
			</div>
			
			<div class="treesList">
				<ul>
					<li class="tree5list">
						<img class="treeListImg" src=""/>
						<p class="treeListNum"></p>
						
						<div class="swiper-container" id="trees5List">
						    <div class="swiper-wrapper">
						        <!--<div class="swiper-slide">
						        	<img src="images/seed1.png"/>
						        	<p>001</p>
						        </div>
						        <div class="swiper-slide">
						        	<img src="images/seed1.png"/>
						        	<p>002</p>
						        </div>
						        <div class="swiper-slide">
						        	<img src="images/seed1.png"/>
						        	<p>001</p>
						        </div>-->
						    </div>
						</div>
					</li>
					
					<li class="tree4list">
						<img class="treeListImg" src=""/>
						<p class="treeListNum"></p>
						
						<div class="swiper-container" id="trees4List">
						    <div class="swiper-wrapper">
						    </div>
						</div>
					</li>
					
					<li class="tree3list">
						<img class="treeListImg" src=""/>
						<p class="treeListNum"></p>
						
						<div class="swiper-container" id="trees3List">
						    <div class="swiper-wrapper">
						    </div>
						</div>
					</li>
					
					<li class="tree2list">
						<img class="treeListImg" src=""/>
						<p class="treeListNum"></p>
						
						<div class="swiper-container" id="trees2List">
						    <div class="swiper-wrapper">
						    </div>
						</div>
					</li>
					
					<li class="tree1list">
						<img class="treeListImg" src=""/>
						<p class="treeListNum"></p>
						
						<div class="swiper-container" id="trees1List">
						    <div class="swiper-wrapper">
						    </div>
						</div>
					</li>
					
				</ul>
				
			</div>
			
			
			<!--右上-->
			<a class="sunshinePool"></a>
			<a class="dropSunshine"></a>
			
			
			<!--右下-->
			<a class="trends"></a>
			<a class="strategy"></a>
			
			
			<div class="noTree">
				<p>你还没有自己的树哦，</p>
				<p>快去提出你的第一个问题，</p>
				<p>种下自己的第一棵树吧！</p>
			</div>
			
			<!--树-->
			<img class="tree tree1" src="images/tree1.png"/>
			<img class="tree tree2" src="images/tree2.png"/>
			<img class="tree tree3" src="images/tree3.png"/>
			<img class="tree tree4" src="images/tree4.png"/>
			<img class="tree tree5" src="images/tree5.png"/>
			<!--问题-->
			<div class="otherZone">
				<p></p>
				<a>回答</a>
			</div>
			
			<!--树问候-->
			<div class="treeWords">
				<p>我是沙漠里的植被之王</p>
				<span><em></em></span>
			</div>
			
			
			
			
			
			
			<!--底部背景-->
			<img class="forestBottom" src=""/>
			
			<a class="quizSeeding">提问播种</a>
			
			
			
		</div>
		
		
		<div class="treeRank">
			<div class="seedTitle clearfix">
				<h1>排行榜</h1>
				<a>周榜每周一00:00更新</a>
			</div>
			
			<div class="swiper-container" id="treeRank">
				<div class="certificate">
					<p>证书</p>
					<p class="certNum"></p>
				</div>
				<div class="swiper-pagination"></div>
			    <div class="swiper-wrapper">
			        <div class="swiper-slide" id="treeRankWeek">
			        	<!--无排名-->
			        	<div class="noRank">
			        		<img src="images/noRank.png"/>
			        		<p>暂无排名，快去给你的树撒阳光，</p>
			        		<p>抢先上榜～</p>
			        	</div>
			        	
			        	
			        	<div class="treeTopThree">
				        </div>
				        <div class="treeRankList">
			        		<ul>
			        			<!--<li>
			        				<i>4</i>
			        				<img src="images/sunshine.png"/>
			        				<h2>啊啊啊</h2>
			        				<span>获采纳10次</span>
			        				
			        				<p>8278.2m</p>
			        			</li>-->
			        		</ul>
			        		<a class="readMoreTree">查看更多好友></a>
			        	</div>
			    	</div>
			    
			    	<div class="swiper-slide" id="treeRankTotal">
			    		
			    		<!--无排名-->
			        	<div class="noRank">
			        		<img src="images/noRank.png"/>
			        		<p>暂无排名，快去给你的树撒阳光，</p>
			        		<p>抢先上榜～</p>
			        	</div>
				        	
			        	<div class="treeTopThree">
			        	</div>
		        	 	<div class="treeRankList">
			        		<ul>
			        			<!--<li>
			        				<i>4</i>
			        				<img src="images/sunshine.png"/>
			        				<h2>啊啊啊</h2>
			        				<span>获采纳10次</span>
			        				
			        				<p>8278.2m</p>
			        			</li>-->
			        		</ul>
			        		<a class="readMoreTree">查看更多好友></a>
			        	</div>
			        </div>
			       
			    
				</div>
			</div>
		
			<div class="treeTips">你每养成一棵树，我们就为你种下一棵真树</div>
		</div>
		
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/swiper-3.4.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			
//			获取用户有没有种树
			function getUserTreeOrNo(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getUserTreeOrNo",
			        type: "post",
			        data: {userId:userId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var nickName = data.module;
			                	var data = data.data;
			                	
			                	deleteTrees = data;
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			获得用户树
			function getAllStateTrees(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getAllStateTrees",
			        type: "post",
			        data: {userId:userId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
//			                	种子
			                	var seeds = data.seeds;
			                	var seedsNum = seeds.length;
//			                	树苗
			                	var plants = data.plants;
			                	var plantsNum = plants.length;
//			                	小树
			                	var tupelos = data.tupelos;
			                	var tupelosNum = tupelos.length;
//			                	中树
			                	var mids = data.mids;
			                	var midsNum = mids.length;
//			                	大树
			                	var groweds = data.groweds;
			                	var growedsNum = groweds.length;
			                	treeNum = seedsNum + plantsNum + tupelosNum + midsNum + growedsNum;
			                	
			                	
			                	$(".tree5list").find(".treeListNum").text("x"+growedsNum);
			                	if (growedsNum == 0) {
			                		$(".tree5list").addClass("treeNone").find(".treeListImg").attr("src","images/tree5listnone.png");
			                	} else{
			                		$(".tree5list").find("img").attr("src","images/tree5list.png");
			                		
			                		var html = "";
			                		$.each(groweds, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed5.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees5List .swiper-wrapper").html(html);
			                		
		                			biggestTree = true;
			                		var treeId = "";
			                		var treeCode = "";
			                		var exp = -2;
			                		$("#trees5List .swiper-wrapper .swiper-slide").each(function(){
			                			if ($(this).data("exp")>exp) {
			                				exp = $(this).data("exp");
			                				treeCode = $(this).find("p").text();
			                				treeId = $(this).data("treeid");
			                				questionId = $(this).data("questionid");
			                			}
			                		});
			                		
			                		$(".tree5").show();
			                		$(".forestBottom").attr("src","images/forestBottom2.png");
			                		$(".treeExp .treeCode").text(treeCode+"号树苗");
			                		$(".treeExp .treeExpNum").text(exp+"/5200");
			                		$(".treeExp .treeExpBar em").css("width",exp/52+"%");
			                		$(".treeExp #treeId").val(treeId);
			                		$(".treeExp #treeExp").val(exp);
			                		$(".treeExp #treeSize").val(4)
			                	}
			                	
			                	
			                	
			                	$(".tree4list").find(".treeListNum").text("x"+midsNum);
			                	if (midsNum == 0) {
			                		$(".tree4list").addClass("treeNone").find(".treeListImg").attr("src","images/tree4listnone.png");
			                	} else{
			                		$(".tree4list").find("img").attr("src","images/tree4list.png");
			                		
			                		var html = "";
			                		$.each(mids, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed4.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees4List .swiper-wrapper").html(html);
			                		
			                		if (!biggestTree) {
			                			biggestTree = true;
				                		var treeId = "";
				                		var treeCode = "";
				                		var exp = -2;
				                		$("#trees4List .swiper-wrapper .swiper-slide").each(function(){
				                			if ($(this).data("exp")>exp) {
				                				exp = $(this).data("exp");
				                				treeCode = $(this).find("p").text();
				                				treeId = $(this).data("treeid");
				                				questionId = $(this).data("questionid");
				                			}
				                		});
				                		
				                		$(".tree4").show();
				                		$(".forestBottom").attr("src","images/forestBottom2.png");
				                		$(".treeExp .treeCode").text(treeCode+"号树苗");
				                		$(".treeExp .treeExpNum").text(exp+"/5200");
				                		$(".treeExp .treeExpBar em").css("width",exp/52+"%");
				                		$(".treeExp #treeId").val(treeId);
				                		$(".treeExp #treeExp").val(exp);
				                		$(".treeExp #treeSize").val(3)
			                		}
				                		
			                	}
			                	
			                	
			                	
			                	$(".tree3list").find(".treeListNum").text("x"+tupelosNum);
			                	if (tupelosNum == 0) {
			                		$(".tree3list").addClass("treeNone").find(".treeListImg").attr("src","images/tree3listnone.png");
			                	} else{
			                		$(".tree3list").find("img").attr("src","images/tree3list.png");
			                		
			                		var html = "";
			                		$.each(tupelos, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed3.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees3List .swiper-wrapper").html(html);
			                		
			                		if (!biggestTree) {
			                			biggestTree = true;
				                		var treeId = "";
				                		var treeCode = "";
				                		var exp = -2;
				                		$("#trees3List .swiper-wrapper .swiper-slide").each(function(){
				                			if ($(this).data("exp")>exp) {
				                				exp = $(this).data("exp");
				                				treeCode = $(this).find("p").text();
				                				treeId = $(this).data("treeid");
				                				questionId = $(this).data("questionid");
				                			}
				                		});
				                		
				                		$(".tree3").show();
				                		$(".forestBottom").attr("src","images/forestBottom2.png");
				                		$(".treeExp .treeCode").text(treeCode+"号树苗");
				                		$(".treeExp .treeExpNum").text(exp+"/5200");
				                		$(".treeExp .treeExpBar em").css("width",exp/52+"%");
				                		$(".treeExp #treeId").val(treeId);
				                		$(".treeExp #treeExp").val(exp);
				                		$(".treeExp #treeSize").val(2)
			                		}
			                	}
			                	
			                	
			                	$(".tree2list").find(".treeListNum").text("x"+plantsNum);
			                	if (plantsNum == 0) {
			                		$(".tree2list").addClass("treeNone").find(".treeListImg").attr("src","images/tree2listnone.png");
			                	} else{
			                		$(".tree2list").find("img").attr("src","images/tree2list.png");
			                		
			                		var html = "";
			                		$.each(plants, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed2.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees2List .swiper-wrapper").html(html);
			                		
			                		if (!biggestTree) {
			                			biggestTree = true;
				                		var treeId = "";
				                		var treeCode = "";
				                		var exp = -2;
				                		$("#trees2List .swiper-wrapper .swiper-slide").each(function(){
				                			if ($(this).data("exp")>exp) {
				                				exp = $(this).data("exp");
				                				treeCode = $(this).find("p").text();
				                				treeId = $(this).data("treeid");
				                				questionId = $(this).data("questionid");
				                			}
				                		});
				                		
				                		$(".tree2").show();
				                		$(".forestBottom").attr("src","images/forestBottom2.png");
				                		$(".treeExp .treeCode").text(treeCode+"号树苗");
				                		$(".treeExp .treeExpNum").text(exp+"/5200");
				                		$(".treeExp .treeExpBar em").css("width",exp/52+"%");
				                		$(".treeExp #treeId").val(treeId);
				                		$(".treeExp #treeExp").val(exp);
				                		$(".treeExp #treeSize").val(1)
			                		}
			                	}
			                	
			                	
			                	$(".tree1list").find(".treeListNum").text("x"+seedsNum);
			                	
								if (seedsNum == 0) {
			                		$(".tree1list").addClass("treeNone").find(".treeListImg").attr("src","images/tree1listnone.png");
			                	} else{
			                		$(".tree1list").find("img").attr("src","images/tree1list.png");
			                		var html = "";
			                		$.each(seeds, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '0' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed1.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees1List .swiper-wrapper").html(html);
			                		if (!biggestTree) {
			                			biggestTree = true;
				                		var treeId = "";
				                		var treeCode = "";
				                		var exp = -2;
				                		if ($("#trees1List .swiper-wrapper .swiper-slide").length>0) {
				                			$("#trees1List .swiper-wrapper .swiper-slide").each(function(){
					                			if ($(this).data("exp")>exp) {
					                				exp = $(this).data("exp");
					                				treeCode = $(this).find("p").text();
					                				treeId = $(this).data("treeid");
					                				questionId = $(this).data("questionid");
					                			}
					                		});
				                		}
				                		
				                		
				                		$(".tree1").show();
				                		$(".forestBottom").attr("src","images/forestBottom1.png");
				                		$(".treeExp .treeCode").text(treeCode+"号树苗");
				                		$(".treeExp .treeExpNum").text("0/5200");
				                		$(".treeExp .treeExpBar em").css("width",0);
				                		$(".treeExp #treeId").val(treeId);
				                		$(".treeExp #treeExp").val(0);
				                		$(".treeExp #treeSize").val(0);
				                		
			                		}
			                	}
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
			
			
//			选中树
			function treeSelected(){
				$(".treesList .swiper-slide").removeClass("tree_on")
				var treeId = $("#treeId").val();
				$(".treesList .swiper-slide").each(function(){
					var treeSelId = $(this).data("treeid");
					if (treeSelId == treeId) {
						$(this).addClass("tree_on");
					}
				})
			}
			
			
//			刷新树列表
			function refreshTreeList(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getAllStateTrees",
			        type: "post",
			        data: {userId:userId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
//			                	种子
			                	var seeds = data.seeds;
			                	var seedsNum = seeds.length;
//			                	树苗
			                	var plants = data.plants;
			                	var plantsNum = plants.length;
//			                	小树
			                	var tupelos = data.tupelos;
			                	var tupelosNum = tupelos.length;
//			                	中树
			                	var mids = data.mids;
			                	var midsNum = mids.length;
//			                	大树
			                	var groweds = data.groweds;
			                	var growedsNum = groweds.length;
			                	treeNum = seedsNum + plantsNum + tupelosNum + midsNum + growedsNum;
			                	
			                	
			                	$(".tree5list").find(".treeListNum").text("x"+growedsNum);
			                	if (growedsNum == 0) {
			                		$(".tree5list").addClass("treeNone").find(".treeListImg").attr("src","images/tree5listnone.png");
			                	} else{
			                		$(".tree5list").find("img").attr("src","images/tree5list.png");
			                		
			                		var html = "";
			                		$.each(groweds, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed5.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees5List .swiper-wrapper").html(html);
			                	}
			                	
			                	
			                	
			                	$(".tree4list").find(".treeListNum").text("x"+midsNum);
			                	if (midsNum == 0) {
			                		$(".tree4list").addClass("treeNone").find(".treeListImg").attr("src","images/tree4listnone.png");
			                	} else{
			                		$(".tree4list").find("img").attr("src","images/tree4list.png");
			                		
			                		var html = "";
			                		$.each(mids, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed4.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees4List .swiper-wrapper").html(html);
			                	}
			                	
			                	
			                	
			                	$(".tree3list").find(".treeListNum").text("x"+tupelosNum);
			                	if (tupelosNum == 0) {
			                		$(".tree3list").addClass("treeNone").find(".treeListImg").attr("src","images/tree3listnone.png");
			                	} else{
			                		$(".tree3list").find("img").attr("src","images/tree3list.png");
			                		
			                		var html = "";
			                		$.each(tupelos, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed3.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees3List .swiper-wrapper").html(html);
			                	}
			                	
			                	
			                	$(".tree2list").find(".treeListNum").text("x"+plantsNum);
			                	if (plantsNum == 0) {
			                		$(".tree2list").addClass("treeNone").find(".treeListImg").attr("src","images/tree2listnone.png");
			                	} else{
			                		$(".tree2list").find("img").attr("src","images/tree2list.png");
			                		
			                		var html = "";
			                		$.each(plants, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '"+item.exp+"' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed2.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees2List .swiper-wrapper").html(html);
			                	}
			                	
			                	
			                	$(".tree1list").find(".treeListNum").text("x"+seedsNum);
			                	
								if (seedsNum == 0) {
			                		$(".tree1list").addClass("treeNone").find(".treeListImg").attr("src","images/tree1listnone.png");
			                	} else{
			                		$(".tree1list").find("img").attr("src","images/tree1list.png");
			                		var html = "";
			                		$.each(seeds, function(i,item) {
			                			html += "<div class='swiper-slide' data-treeid = '"+item.id+"' data-exp = '0' data-questionid = '"+item.questionId+"'>"+
								        	"<img src='images/seed1.png'/>"+
								        	"<p>"+item.code+"</p>"+
								        "</div>"
			                		});
			                		
			                		$("#trees1List .swiper-wrapper").html(html);
			                		
			                	}
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			    
			    treeSelected()
			}
			
			
//			获取树的经验阶段
			function getSunRule(){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getSunRule",
			        type: "post",
			        data: {},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	expStage.little = data[3].exp;
			                	expStage.mid = data[4].exp;
			                	expStage.large = data[5].exp;
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
//			申请种树（现实）
			function applyForTree(userId,treeId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/applyForTree",
			        type: "post",
			        data: {userId:userId,treeId:treeId},
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
			
			
			
//			等待发芽的种子
			function getWaitTree(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getWaitTree",
			        type: "post",
			        data: {userId:userId,pageSize:10},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	var html = "";
			                	if (data.length == 0) {
			                		$(".treeRank").css("padding-top","4rem");
			                	} else {
			                		$.each(data, function(i,item) {
				                		var headImg = "";
				                		if (item.headImg == null || item.headImg.indexOf("http") == -1) {
				                			headImg = "images/defaultHead.jpg"
				                		} else{
				                			headImg = item.headImg
				                		}
				                		html += "<div class='swiper-slide' data-plantid='"+item.userId+"'>"+
								        	"<img src='"+headImg+"'/>"+
								        	"<p>"+item.nickname+"</p>"+
								        "</div>"	
				                	});
				                	
				                	
				                	$(".forest").after("<div class='otherSeeds'>"+
										"<div class='seedTitle clearfix'>"+
											"<h1>等待发芽的种子</h1>"+
											"<a>查看更多></a>"+
										"</div>"+
										
										"<div class='swiper-container' id='seedList'>"+
										    "<div class='swiper-wrapper'>"+html+"</div>"+
										"</div>"+
									"</div>")
			                	}
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			待收取的阳光
			function getWaitSunshine(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getWaitSunshine",
			        type: "post",
			        data: {userId:userId,pageSize:999},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	sunshineList = data;
			                	var sunshineNum = data.length;
			                	sunshineTotalRounds = Math.ceil(sunshineNum/5);
		                		sunshineListFunc();
								
//								for (var i = 0;i < sunshineNum;i++) {
//									if (i>0) {
//										overlapping()
//									} else{
//										var randX = rand(xMin,xMax);
//										var randY = rand(yMin,yMax);
//										
//										$(".sunshineZone").append("<div class='sunshinePoint' style='left: "+randX+"px;top:"+randY+"px;'>"+
//											"<img src='images/sunshine.png'/>"+
//											"<p>x10</p>"+
//											"<p>回答获赞</p>"+
//										"</div>");
//										
//			//							var guide2L = parseFloat(randX)+parseFloat(remTurn*2.8);
//			//							var guide2T = parseFloat(randY)+parseFloat(remTurn*2.3);
//			//							$(".sunshineZone").css("z-index","110").find("ul").append("<a class='guide2' style='left:"+guide2L+"px;top:"+guide2T+"px;'></a>");
//			//							$(".sunshineZone").append("<div class='guide2Content'>点击“阳光”，将阳光收集到阳光池里</div>")
//			//							xArr.push(randX);
//			//							yArr.push(randY);
//										arr.push([randX,randY])
//									}
//									
//								}
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
//			待收取阳光列表
			function sunshineListFunc(){
				if (sunshineRounds>sunshineTotalRounds) {
            		return false;
            	}
				
				
				$.each(sunshineList, function(i,item) {
					if (i>=sunshineRounds*5 && i<(sunshineRounds+1)*5) {
						if (i==0) {
							var randX = rand(xMin,xMax);
							var randY = rand(yMin,yMax);
							
							$(".forest").append("<div class='sunshinePoint' data-sunshineid = '"+item.id+"' data-sunnum = '"+item.suns+"' style='left: "+randX+"px;top:"+randY+"px;'>"+
								"<img src='images/sunshine.png'/>"+
								"<p>x"+item.suns+"</p>"+
								"<p>"+item.source+"</p>"+
							"</div>");
							
//							xArr.push(randX);
//							yArr.push(randY);
							arr.push([randX,randY])
						} else{
							var randX = rand(xMin,xMax);
							var randY = rand(yMin,yMax);
							
							$(".forest").append("<div class='sunshinePoint' data-sunshineid = '"+item.id+"' data-sunnum = '"+item.suns+"' style='left: "+randX+"px;top:"+randY+"px;'>"+
								"<img src='images/sunshine.png'/>"+
								"<p>x"+item.suns+"</p>"+
								"<p>"+item.source+"</p>"+
							"</div>");
						}
					}
				});
			}
			
			
//			重新取值
//			function reFresh(randX,randY){
//				var fX;
//				var fY;
//				for (var i = 0;i < arr.length;i++) {
//					if (randX <= arr[i][0]-sunshineW || randX >= arr[i][0]+sunshineW) {
//						randX = rand(xMin,xMax);
//						reFresh(randX,randY);
//					}
//					if (randY <= arr[i][1]-sunshineH || randY >= arr[i][1]+sunshineH) {
//						randY = rand(yMin,yMax);
//						reFresh(randX,randY);
//					}
//				}
//				return {
//					fX:randX,
//					fY:randY
//				}
//			}
			
			
			
//			用户阳光池阳光数量、今天获得的数量
			function getUserSuns(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getUserSuns",
			        type: "post",
			        data: {userId:userId},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	sunsNum = data.suns;
			                	$(".sunshinePool").text(data.suns);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
			
//			证书
			function getMyCert(userId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getMyCert",
			        type: "post",
			        data: {userId:userId},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	certNum = data.length;
			                	if(certNum==0){
			                		$(".certificate").addClass("noCert");
			                	}
			                	$(".certificate .certNum").text("x"+certNum);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			周榜日榜
			function getSunsBillWeek(){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getSunsBill",
			        type: "post",
			        data: {week:1},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
			                	if (data.length==0) {
			                		$("#treeRankWeek .noRank").show();
			                		return false;
			                	}
			                	
			                	$("#treeRankWeek .treeTopThree").css("display","flex");
			                	$("#treeRankWeek .readMoreTree").css("display","block");
			                	
			                	var second = "";
			                	var third = "";
			                	
			                	var secondPlantId = "";
			                	var thirdPlantId = "";
			                	
			                	var headImg1 = "";
			                	var headImg2 = "";
			                	var headImg3 = "";
			                	
			                	if (data[0].headImg.indexOf("http") == -1) {
		                			headImg1 = "images/defaultHead.jpg"
		                		} else{
		                			headImg1 = data[0].headImg
		                		}
			                	
			                	if (data.length > 1) {
			                		if (data[1].headImg.indexOf("http") == -1) {
			                			headImg2 = "images/defaultHead.jpg"
			                		} else{
			                			headImg2 = data[1].headImg
			                		}
				                	second = "<div>"+
										"<img src='"+headImg2+"' />"+
										"<p>第2名</p>"+
									"</div>"+
									"<h2>"+data[1].nickname+"</h2>"+
									"<p>累计播撒"+data[1].totalSuns+"m</p>"+
									"<span>获采纳"+data[1].adopts+"次</span>";
									secondPlantId = data[1].userId;
			                	} 
			                	if (data.length > 2) {
			                		if (data[2].headImg.indexOf("http") == -1) {
			                			headImg3 = "images/defaultHead.jpg"
			                		} else{
			                			headImg3 = data[2].headImg
			                		}
			                		third = "<div data-plantid='"+data[2].userId+"'>"+
										"<img src='"+headImg3+"' />"+
										"<p>第3名</p>"+
									"</div>"+
									"<h2>"+data[2].nickname+"</h2>"+
									"<p>累计播撒"+data[2].totalSuns+"m</p>"+
									"<span>获采纳"+data[2].adopts+"次</span>";
									thirdPlantId = data[2].userId;
			                	}
			                	
			                	
			                	$("#treeRankWeek .treeTopThree").html("<div class='tree-second' data-plantid='"+secondPlantId+"'>"+second+"</div>"+
								
								"<div class='tree-first' data-plantid='"+data[0].userId+"'>"+
									"<div>"+
										"<img src='"+headImg1+"' />"+
										"<p>第1名</p>"+
									"</div>"+
									"<h2>"+data[0].nickname+"</h2>"+
									"<p>累计播撒"+data[0].totalSuns+"m</p>"+
									"<span>获采纳"+data[0].adopts+"次</span>"+
								"</div>"+
								
								"<div class='tree-third' data-plantid='"+thirdPlantId+"'>"+third+"</div>")
			                	
			                	
			                	var html = "";
			                	if (data.length>=3) {
			                		$.each(data, function(i,item) {
				                		if (i>2 && i<10) {
				                			var rankNum = i+1;
				                			var headImg = "";
				                			if (item.headImg == null || item.headImg.indexOf("http") == -1) {
					                			headImg = "images/defaultHead.jpg"
					                		} else{
					                			headImg = item.headImg
					                		}
				                			html += "<li data-plantid='"+item.userId+"'>"+
						        				"<i>"+rankNum+"</i>"+
						        				"<img src='"+headImg+"'/>"+
						        				"<h2>"+item.nickname+"</h2>"+
						        				"<span>获采纳"+item.adopts+"次</span>"+
						        				
						        				"<p>累计播撒"+item.totalSuns+"m</p>"+
						        			"</li>"
				                		}
				                	});
			                	}
			                	
			                	$("#treeRankWeek .treeRankList ul").html(html);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			function getSunsBillTotal(){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getSunsBill",
			        type: "post",
			        data: {week:0},
			        dataType: "json",
//			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
			                	if (data.length==0) {
			                		$("#treeRankTotal .noRank").show();
			                		return false;
			                	}
			                	
			                	$("#treeRankTotal .treeTopThree").css("display","flex");
			                	$("#treeRankTotal .readMoreTree").css("display","block");
			                	var second = "";
			                	var third = "";
			                	
			                	var secondPlantId = "";
			                	var thirdPlantId = "";
			                	
			                	var headImg1 = "";
			                	var headImg2 = "";
			                	var headImg3 = "";
			                	
			                	if (data[0].headImg.indexOf("http") == -1) {
		                			headImg1 = "images/defaultHead.jpg"
		                		} else{
		                			headImg1 = data[0].headImg
		                		}
			                	
			                	if (data.length > 1) {
			                		if (data[1].headImg.indexOf("http") == -1) {
			                			headImg2 = "images/defaultHead.jpg"
			                		} else{
			                			headImg2 = data[1].headImg
			                		}
				                	second = "<div>"+
										"<img src='"+headImg2+"' />"+
										"<p>第2名</p>"+
									"</div>"+
									"<h2>"+data[1].nickname+"</h2>"+
									"<p>累计播撒"+data[1].totalSuns+"m</p>"+
									"<span>获采纳"+data[1].adopts+"次</span>";
									secondPlantId = data[1].userId;
			                	} 
			                	if (data.length > 2) {
			                		if (data[2].headImg.indexOf("http") == -1) {
			                			headImg3 = "images/defaultHead.jpg"
			                		} else{
			                			headImg3 = data[2].headImg
			                		}
			                		third = "<div data-plantid='"+data[2].userId+"'>"+
										"<img src='"+headImg3+"' />"+
										"<p>第3名</p>"+
									"</div>"+
									"<h2>"+data[2].nickname+"</h2>"+
									"<p>累计播撒"+data[2].totalSuns+"m</p>"+
									"<span>获采纳"+data[2].adopts+"次</span>";
									thirdPlantId = data[2].userId;
			                	}
			                	
			                	
			                	$("#treeRankTotal .treeTopThree").html("<div class='tree-second' data-plantid='"+secondPlantId+"'>"+second+"</div>"+
								
								"<div class='tree-first' data-plantid='"+data[0].userId+"'>"+
									"<div>"+
										"<img src='"+headImg1+"' />"+
										"<p>第1名</p>"+
									"</div>"+
									"<h2>"+data[0].nickname+"</h2>"+
									"<p>累计播撒"+data[0].totalSuns+"m</p>"+
									"<span>获采纳"+data[0].adopts+"次</span>"+
								"</div>"+
								
								"<div class='tree-third' data-plantid='"+thirdPlantId+"'>"+third+"</div>")
			                	
			                	
			                	var html = "";
			                	if (data.length>=3) {
			                		$.each(data, function(i,item) {
				                		if (i>2 && i<10) {
				                			var rankNum = i+1;
				                			var headImg = "";
				                			if (item.headImg == null || item.headImg.indexOf("http") == -1) {
					                			headImg = "images/defaultHead.jpg"
					                		} else{
					                			headImg = item.headImg
					                		}
				                			html += "<li data-plantid='"+item.userId+"'>"+
						        				"<i>"+rankNum+"</i>"+
						        				"<img src='"+headImg+"'/>"+
						        				"<h2>"+item.nickname+"</h2>"+
						        				"<span>获采纳"+item.adopts+"次</span>"+
						        				
						        				"<p>累计播撒"+item.totalSuns+"m</p>"+
						        			"</li>"
				                		}
				                	});
			                	}
			                	
			                	$("#treeRankTotal .treeRankList ul").html(html);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			树详情
			function getTreeById(treeId,treeId){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getTreeById",
			        type: "post",
			        data: {treeId:treeId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	$("#treeQid").val(data.questionId);
		                		treeWords.shift();
		                		treeWords.unshift(data.questionContent)
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			function rand(min,max) {
				var randNum = parseInt(Math.random() * (max - min + 1) + min);
				return randNum;
			}
			
			
			
			
//			树语
			function getTreeHello(){
				$.ajax({
			        url: apiUrl+"/fxf_tree/getTreeHello",
			        type: "post",
			        data: {},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	$.each(data, function(i,item) {
			                		treeWords.push(item.content)
			                	});
			                	treeWordsNum = treeWords.length;
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
			
			var htmlPx = $("html").css("font-size")
			var remTurn = htmlPx.substr(0,htmlPx.length-2)
			
			var sunshineW = remTurn * 4.2;
			var sunshineH = remTurn * 6.2;
			
			var xMin = "";
			var xMax = "";
			var yMin = "";
			var yMax = "";
			
//			var xArr = [];
//			var yArr = [];
			
			var arr = [];
			
			var biggestTree = false;
			
			
			var sunshineTotalRounds = 0;
			var sunshineRounds = 0;
			
			var sunshinePoolL = "";
			var sunshinePoolT = "";
			
			var sunshineList = [];
			
			var questionId = "";
			
			var deleteTrees = 0;
			
			var treeNum = 0;
			var seedNum = 0;
			var saplingNum = 0;
			
			var sunsNum = 0;
			
			var certNum = 0;
			
			var expStage = {};
			
			var treeWords = [];
			var treeWordsNum = 0;
			var treeWordsNth = 0;
			var treeWordsTime;
			
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
				var userId = getQueryString("userId");
				
				
				getUserTreeOrNo(userId);
				
				getAllStateTrees(userId);
				
				treeSelected();
				
				getMyCert(userId);
				
				if (treeNum == 0) {
	//				没有树
	        		$(".forestBg").attr("src","images/forestBg.jpg");
					$(".forestBg").load(function(){
						$(".forestBottom").attr("src","images/forestBottom1.png");
						var forestBgH = $(".forestBg").height();
						$(".forest").css("height",forestBgH+"px");
						
						if (certNum != 0) {
							$(".noTree").html("<p>你已经养成"+certNum+"棵大树啦，</p><p>快去提问种下一棵新树吧！</p>")
						} else if (deleteTrees > 0) {
							$(".noTree").html("<p>抱歉，</p><p>这片森林的树已经不存在了</p><p>（因为森林的主人删除了<br/>Ta的问题）</p>")
						}
						
						
						
						$(".noTree").show();
						
						
						$(".forest").append("<a class='guideQuiz'></a>");
					})
	        	} else {
	        		$(".forestBg").attr("src","images/forestBg.jpg");
					$(".forestBg").load(function(){
						xMin = parseInt(remTurn * 4.3);
						xMax = parseInt($(".forestBg").width() - sunshineW);
						yMin = parseInt(remTurn * 12);
						yMax = parseInt($(".forestBg").height() - sunshineH - remTurn * 10);
						$(".treesList,.treeExp,.sunshinePool,.dropSunshine").show();
						$(".sunshinePool").show();
						$(".treeExp i").show();
						getWaitSunshine(userId);
						var forestBgH = $(".forestBg").height();
						$(".forest").css("height",forestBgH+"px");
						
						sunshinePoolL = $(".sunshinePool").offset().left;
						sunshinePoolT = remTurn * 7.1;
						
					})
	        	}
				
				
				if (treeNum != 0) {
					var treeId = $(".treeExp #treeId").val();
                	if (treeId!="") {
						getTreeById(userId,treeId);
					}
                	getTreeHello();
					$(".treeWords p").text(treeWords[treeWordsNth]);
					treeWordsNth++;
					var treeSize = $(".treeExp #treeSize").val();
					if (treeSize == 0) {
                		$(".treeWords").css({"bottom":"15rem"})
                	} else if (treeSize == 1) {
                		$(".treeWords").css({"bottom":"18rem"})
                	} else if (treeSize == 2) {
                		$(".treeWords").css({"bottom":"26.2rem"})
                	} else if (treeSize == 3) {
                		$(".treeWords").css({"bottom":"26.4rem"})
                	} else if (treeSize == 4) {
                		$(".treeWords").css({"bottom":"29.6rem"})
                	}
                	$(".treeWords").show();
                	treeWordsTime = setTimeout(function(){
                		$(".treeWords").hide();
                	},3000)
                	
				}
	                	
				
				
				getSunRule()
					
				getUserSuns(userId);
				
				getWaitTree(userId);
				
				
				getSunsBillWeek();
				getSunsBillTotal();
				
				
				
//				收集阳光
				$(".forest").on("click",".sunshinePoint",function(){
					more();
				})
				
				
				var seedSwiper = new Swiper('#seedList', {
			        slidesPerView: "auto",
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
			        freeMode: true
			    });
			    
			    
//			    点树颤抖
				$(".tree").click(function(){
					var _this = $(this);
					_this.addClass("treeShake")
					setTimeout(function(){
						_this.removeClass("treeShake")
					},1000)
					
					$(".treeWords p").text(treeWords[treeWordsNth]);
					treeWordsNth++;
					if (treeWordsNth >= treeWordsNum) {
						treeWordsNth = 0;
					}
					var treeSize = $(".treeExp #treeSize").val();
					if (treeSize == 0) {
                		$(".treeWords").css({"bottom":"15rem"})
                	} else if (treeSize == 1) {
                		$(".treeWords").css({"bottom":"18rem"})
                	} else if (treeSize == 2) {
                		$(".treeWords").css({"bottom":"26.2rem"})
                	} else if (treeSize == 3) {
                		$(".treeWords").css({"bottom":"26.4rem"})
                	} else if (treeSize == 4) {
                		$(".treeWords").css({"bottom":"29.6rem"})
                	}
                	$(".treeWords").show();
                	clearTimeout(treeWordsTime);
                	treeWordsTime = setTimeout(function(){
                		$(".treeWords").hide();
                	},3000)
						
				})
			    
			    
//			    树列表
				$(".treesList li .treeListImg").click(function(){
					if ($(this).parent().hasClass("treeNone")) {
						return false;
					}
					
					
					if ($(".guide1").length!=0) {
						$("#layer").hide();
						$(".treesList").css("z-index","12");
						$(".guide1,.guide1Content").remove();
					}
					
					var _this = $(this);
					
					var n = 0;
					_this.parent().siblings().each(function(){
						if ($(this).find(".swiper-container").css("display")=="block") {
							n++;
							$(this).find(".swiper-container").stop().animate({"width":"toggle"},function(){
								_this.siblings(".swiper-container").stop().animate({"width":"toggle"});
							});
						}
					})
					
					if (n==0){
						_this.siblings(".swiper-container").stop().animate({"width":"toggle"});
					}
					
					
//					$(this).siblings(".swiper-container").stop().animate({"width":"toggle"});
//					$(this).parent().siblings().each(function(){
//						if ($(this).find(".swiper-container").css("display")=="block") {
//							$(this).find(".swiper-container").stop().animate({"width":"toggle"});
//						}
//					})
				})
			    
			    var treesSwiper = new Swiper('#trees1List', {
			    	slidesPerView: "auto",
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
			        freeMode: true
			    });
			    
			    var treesSwiper = new Swiper('#trees2List', {
			    	slidesPerView: "auto",
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
			        freeMode: true
			    });
			    
			    var treesSwiper = new Swiper('#trees3List', {
			    	slidesPerView: "auto",
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
			        freeMode: true
			    });
			    
			    var treesSwiper = new Swiper('#trees4List', {
			    	slidesPerView: "auto",
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
			        freeMode: true
			    });
			    
			    var treesSwiper = new Swiper('#trees5List', {
			    	slidesPerView: "auto",
					observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true,
			        freeMode: true
			    });
			    
			    
			    
//			    切换树
				$(".treesList ul").on("click",".swiper-slide",function(){
					var _this = $(this);
					setTimeout(function(){
						_this.parents(".swiper-container").stop().animate({"width":"toggle"})
					},500)
					
					var treePId = $(this).parents(".swiper-container").attr("id")
					var treeId = $(this).data("treeid");
					var exp = $(this).data("exp");
					var treeCode = $(this).find("p").text();
					
					$(".treeExp .treeCode").text(treeCode+"号树苗");
	        		$(".treeExp .treeExpNum").text(exp+"/5200");
	        		$(".treeExp .treeExpBar em").css("width",exp/52+"%");
	        		$(".treeExp #treeExp").val(exp);
	        		$(".treeExp #treeId").val(treeId);
	        		
	        		treeSelected()
	        		
	        		if (treePId == "trees1List") {
	        			$(".treeExp #treeSize").val(0)
	        		} else if (treePId == "trees2List") {
	        			$(".treeExp #treeSize").val(1)
	        		} else if (treePId == "trees3List") {
	        			$(".treeExp #treeSize").val(2)
	        		} else if (treePId == "trees4List") {
	        			$(".treeExp #treeSize").val(3)
	        		} else if (treePId == "trees5List") {
	        			$(".treeExp #treeSize").val(4)
	        		}
	        		
	        		var treeSize = $(".treeExp #treeSize").val();
	        		
	        		questionId = $(this).data("questionid");
	        		
					$(".tree").hide();
					if ($(this).parents("li").attr("class")=="tree1list") {
						$(".tree1").show();
						$(".forestBottom").attr("src","images/forestBottom1.png");
					} else if ($(this).parents("li").attr("class")=="tree2list") {
						$(".tree2").show();
						$(".forestBottom").attr("src","images/forestBottom2.png");
					} else if ($(this).parents("li").attr("class")=="tree3list") {
						$(".tree3").show();
						$(".forestBottom").attr("src","images/forestBottom2.png");
					} else if ($(this).parents("li").attr("class")=="tree4list") {
						$(".tree4").show();
						$(".forestBottom").attr("src","images/forestBottom2.png");
					} else if ($(this).parents("li").attr("class")=="tree5list") {
						$(".tree5").show();
						$(".forestBottom").attr("src","images/forestBottom2.png");
					}
					
					treeWordsNth = 0;
					
					getTreeById(userId,treeId);
                	getTreeHello();
					$(".treeWords p").text(treeWords[treeWordsNth]);
					treeWordsNth++;
					
					var treeSize = $(".treeExp #treeSize").val();
					if (treeSize == 0) {
                		$(".treeWords").css({"bottom":"15rem"})
                	} else if (treeSize == 1) {
                		$(".treeWords").css({"bottom":"18rem"})
                	} else if (treeSize == 2) {
                		$(".treeWords").css({"bottom":"26.2rem"})
                	} else if (treeSize == 3) {
                		$(".treeWords").css({"bottom":"26.4rem"})
                	} else if (treeSize == 4) {
                		$(".treeWords").css({"bottom":"29.6rem"})
                	}
                	$(".treeWords").show();
                	clearTimeout(treeWordsTime);
                	treeWordsTime = setTimeout(function(){
                		$(".treeWords").hide();
                	},3000)
					
				})
			    
			    
			    var treeRankSwiper = new Swiper('#treeRank', {
			        pagination: '.swiper-pagination',
			        paginationClickable: true,
			        paginationBulletRender: function (swiper, index, className) {
			        	var pagination = "";
			        	if (index==0) {
			        		pagination = '<span class="' + className + '">周榜</span>';
			        	} else if (index==1){
			        		pagination = '<span class="' + className + '">总榜</span>';
			        	}
			            return pagination;
			        }
			    });
			    
				
//				撒阳光
				$(".dropSunshine").click(function(){
					more();
				})
				
				
				
//				种树
				$(".treeExp i").click(function(){
					more();
				})
				
				
//				阳光池
				$(".sunshinePool").click(function(){
					more();
				})
				
//				攻略
				$(".strategy").click(function(){
					location.href="treeStrategy.html"
				})
				
//				动态
				$(".trends").click(function(){
					more();
				})
				
//				提问播种
				$(".quizSeeding").click(function(){
					more();
				})
				
//				等待发芽的种子(查看更多)
				$(".otherSeeds .seedTitle a").click(function(){
					more();
				})
				
//				等待发芽的种子(跳转其他人页面)
				$("#seedList").on("click",".swiper-slide",function(){
					more();
				})
				
				$("#treeRank").on("click",".tree-first,.tree-second,.tree-third,.treeRankList li",function(){
					more();
				})
				
				
//				证书
				$(".certificate").click(function(){
					more();
				})
				
//				周榜查看更多
				$("#treeRankWeek .readMoreTree").click(function(){
					more();
				})

//				总榜查看更多
				$("#treeRankTotal .readMoreTree").click(function(){
					more();
				})


//				回答问题
				$(".otherZone a").click(function(){
					more();
				})
				
				
				locationUrl = apiUrl+"/jsp/fxf/share/forest.jsp?userId="+userId;
				wxtitle = "一有烦恼，就来种树吧！为沙漠送去一片绿洲！";
            	wximgUrl= apiUrl+"/jsp/fxf/share/images/treeShare.jpg";
            	wxdesc = "每一个烦恼都是给沙漠带去希望的种子。";
				wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);

			})
		</script>
	</body>
</html>