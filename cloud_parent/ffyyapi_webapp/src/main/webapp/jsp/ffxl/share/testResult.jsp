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
		<link rel="stylesheet" type="text/css" href="css/swiper-4.2.2.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/global.css?v=2"/>
		<link rel="stylesheet" type="text/css" href="css/test.css"/>
	</head>
	<body style="background-color: #f3f3f3;">
		<div id="layer"></div>
		<!--<h1 class="dmnsName"><span>整体情况</span></h1>-->
		<div class="testResult" id="testResult">
			<div class="swiper-container" id="testResultNavi">
				<div class="swiper-wrapper">
					<!--<div class="swiper-slide trn_on">整体结果<img src="images/trn_on.png"/></div>
					<div class="swiper-slide">维度一<img src="images/trn_on.png"/></div>
					<div class="swiper-slide">维度二<img src="images/trn_on.png"/></div>
					<div class="swiper-slide">维度三<img src="images/trn_on.png"/></div>
					<div class="swiper-slide">维度四<img src="images/trn_on.png"/></div>
					<div class="swiper-slide">维度五<img src="images/trn_on.png"/></div>
					<div class="swiper-slide">维度六<img src="images/trn_on.png"/></div>-->
				</div>
			</div>
			<div class="resultFrame">
				<div id="magazine">
					<!--<div><div class="trContent"></div></div>-->
				</div>
			</div>
				
		</div>
		
		<div class="trBtn">
			<a href="" class="trLeft">再测一次</a>
			<a class="trRight">邀好友来测</a>
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/turn.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/swiper-4.2.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function testResult(id){
				$.ajax({
			        url: apiUrl+"/ffxl_scl/report",
			        type: "post",
			        data: {id,id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	document.title=data.sclName;
			                	
			                	var result = data.resultList[0];
			                	var html = "";
			                	var menuanchor = "";
			                	
//			                	模块
								$.each(result.modelDescList, function(i,item) {
									var model = "";
									if (i==0){
										
										$.each(data.resultList, function(j,dim) {
											var modelCont = "";
											modelCont = dim.modelDescList[i].modelDesc;
											if (j==0) {
												
												if (dim.score!=""&&dim.score!=null&&data.resultShow=="2") {
					                				fScore = "<p class='score'>您的总分值为："+result.score+"</p>";
						                		}
												if (dim.resultTitle!=""&&dim.resultTitle!=null) {
													fResultTitle = "<h2 class='resultTitle'>"+result.resultTitle+"</h2>";
												}
												
												model += "<div class='dim'>"+fScore+fResultTitle+
													"<div class='reportDetail'>"+modelCont+"</div>"+
												"</div>"
											} else {
												var score = "";
												var resultPic = "";
												var intervalArr = "";
						                		var intervalIndex = "";
						                		var intervalMax = "";
						                		var interval = "";
						                		var resultTitle = "";
												
												if (dim.score!=""&&dim.score!=null&&data.resultShow=="2") {
					                				score = "<p class='score'>本项分值："+dim.score+"</p>";
						                		}
												
												if (dim.resultPic!=null&&dim.resultPic!="") {
					                				resultDesc = "<div class='reportDetail'>"+dim.resultDesc+"</div>";
						                		}
												
												
												if (data.resultSetMap.length!=0&&data.resultSetMap!=null&&data.resultSetMap!=""&&data.resultShow=="2") {
						                			for (var n = 0;n < data.resultSetMap.length;n++) {
						                				if (data.resultSetMap[n].dmnsName == dim.dmnsName) {
						                					intervalArr = data.resultSetMap[n].qujian.split(",");
						                					intervalIndex = intervalArr.length-1;
						                					intervalMax = parseInt(intervalArr[intervalIndex])-1
						                					interval = "<p class='score'>总分区间：0-"+intervalMax+"</p>";
						                					break;
						                				}
						                			}
						                			
						                		}
												
												if (dim.resultTitle!=""&&dim.resultTitle!=null) {
					                				resultTitle = "<h2 class='resultTitle'>"+dim.resultTitle+"</h2>";
						                		}
												
												
												model += "<div class='dim'>"+
													"<h1 class='multipleTitle'>"+j+"."+dim.dmnsName+"</h1>"+resultPic+score+interval+resultTitle+
													"<div class='reportDetail'>"+modelCont+"</div>"+
												"</div>";
											}
											
												
										})
										
										
										html += "<div class='trLi'><div class='trContent'><h1 class='resultCate'><span>"+item.modelTitle+"</span></h1><div class='allDims'>"+model+"</div></div></div>";
									} else {
										
										$.each(data.resultList, function(j,dim) {
											var modelCont = "";
											modelCont = dim.modelDescList[i].modelDesc;
											if (j==0) {
												if (dim.score!=""&&dim.score!=null&&data.resultShow=="2") {
					                				fScore = "<p class='score'>您的总分值为："+result.score+"</p>";
						                		}
												if (dim.resultTitle!=""&&dim.resultTitle!=null) {
													fResultTitle = "<h2 class='resultTitle'>"+result.resultTitle+"</h2>";
												}
												
												model += "<div class='dim'>"+
													"<div class='reportDetail'>"+modelCont+"</div>"+
												"</div>"
											} else {
												model += "<div class='dim'>"+
													"<h1 class='multipleTitle'>"+j+"."+dim.dmnsName+"</h1>"+
													"<div class='reportDetail'>"+modelCont+"</div>"+
												"</div>";
											}
											
										})
										
										html += "<div class='trLi'><div class='trContent'><h1 class='resultCate'><span>"+item.modelTitle+"</span></h1><div class='allDims'>"+model+"</div></div></div>";
										
									}
									
									menuanchor += "<div class='swiper-slide'><p>"+item.shortTitle+"</p><img src='images/trn_on.png'/></div>"
								});
								
								$("#testResultNavi .swiper-wrapper").html(menuanchor);
								$("#testResultNavi .swiper-slide").eq(0).addClass("trn_on")
			                	$("#magazine").html(html);

								$(".trLeft").attr("href","${base}/jsp/ffxl/share/testDetail.jsp?sclId="+data.sclId);
		                		
		                		locationUrl = apiUrl+"/jsp/ffxl/share/testResult.jsp?reportId="+id;
								wxtitle = data.sclName;
								wximgUrl = data.coverImg;
								wxdesc = data.summary;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			function testResult(id){
//				$.ajax({
//			        url: apiUrl+"/ffxl_scl/report",
//			        type: "post",
//			        data: {id,id},
//			        dataType: "json",
//			        async:false,
//			        success: function(data) {
//			        	if(data.success){
//			                if(data.code == "2000"){
//			                	var data = data.data;
//			                	document.title=data.sclName;
//			                	
//			                	var html = "";
//			                	$.each(data.resultList, function(i,item) {
//			                		var resultPic = "";
//			                		var resultDesc = "";
//			                		
//			                		var score = "";
//			                		var resultTitle = "";
//			                		
//			                		if (i!=0 && item.resultType!="total"){
//			                			dmnsName.push(item.dmnsName);
//			                		}
//			                		
//			                		if (item.resultPic!=""&&item.resultPic!=null) {
//			                			resultPic = "<img src='"+item.resultPic+"'/>";
//			                		}
//			                		
//			                		if (item.resultDesc!=null&&item.resultDesc!=""&&item.resultDesc!="<p><br></p>") {
//			                			resultDesc = "<div class='reportDetail'>"+item.resultDesc+"</div>";
//			                		}
//			                		
//			                		if (item.score!=""&&item.score!=null&&data.channelType=="major") {
//			                			if (item.resultType=="total") {
//			                				score = "<p class='score'>您的总分值为："+item.score+"</p>";
//			                			} else{
//			                				score = "<p class='score'>本项分值："+item.score+"</p>";
//			                			}
//			                		}
//			                		
//			                		if (item.resultTitle!=""&&item.resultTitle!=null) {
//		                				resultTitle = "<h2 class='resultTitle'>"+item.resultTitle+"</h2>";
//			                		}
//			                		
//			                		
//			                		html += "<div class='trLi'><div class='trContent'>"+resultPic+score+resultTitle+resultDesc+"</div></div>"
//			                	});
//			                	
//		                		
//		                		var dmns = "";
//			                	for (var i=0;i<dmnsName.length;i++) {
//			                		dmns += "<div class='swiper-slide'><p>"+dmnsName[i]+"</p><img src='images/trn_on.png'/></div>"
//			                	}
//			                	$("#testResultNavi .swiper-wrapper").html(dmns);
//			                	$("#testResultNavi .swiper-slide").eq(0).addClass("trn_on")
//		                		
//		                		$("#magazine").html(html);
//		                		
//		                		
//		                		$(".trLeft").attr("href","${base}/jsp/ffxl/share/testDetail.jsp?sclId="+data.sclId);
//		                		
//		                		locationUrl = apiUrl+"/jsp/ffxl/share/testResult.jsp?reportId="+id;
//								wxtitle = data.sclName;
//								wximgUrl = data.coverImg;
//								wxdesc = data.summary;
//								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
//		                		
//			                }else{
//			                	
//			                }
//			            }
//			        }
//			    });
//			}
			
			
			var dmnsName = ["整体结果"];
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			var fScore = "";
			var fResultDesc = "";
			var fResultTitle = ""
			
			$(function(){
				var reportId = getQueryString("reportId");
				testResult(reportId)
				

				var swiper = new Swiper('#testResultNavi',{
					slidesPerView: 4.5,
			        spaceBetween: 0,
			        freeMode: true,
			        observer:true, //修改swiper自己或子元素时，自动初始化swiper
					observeParents:true
				})   
				 
				
				if ($("#magazine .trLi").length>1) {
					$('#magazine').turn({
						display: 'single',
						acceleration: true,
						// gradients: !$.isTouch,
						gradients: true,
						elevation:50,
						when: {
							turned: function(e, page) {
								$("#testResultNavi .swiper-slide").eq(page-1).addClass("trn_on").siblings().removeClass("trn_on");
								if (page==1) { 
									$(this).turn('peel', 'tr');
								} else if (page==$(this).turn("pages")) {
									$(this).turn('peel', 'tl');
								}
							}
						}
					});
				}

				
				
				$("#testResultNavi").on("click",".swiper-slide",function(){
					$(this).addClass("trn_on").siblings().removeClass("trn_on");
					var page = $(this).index()+1;
					$('#magazine').turn("page",page)
				})
				
				
				$(".trRight").click(function(){
					$("#layer").show();
					$("body").append("<img class='share' src='images/shareImg.png' />")
				})
				
				$("body").on("click","#layer,.share",function(){
					$("#layer").hide();
					$(".share").remove();
				})
				
				
    
			})
		</script>
	</body>
</html>
