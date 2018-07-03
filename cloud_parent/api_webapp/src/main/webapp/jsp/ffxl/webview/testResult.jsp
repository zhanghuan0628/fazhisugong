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
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
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
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js?v=20180423" type="text/javascript" charset="utf-8"></script>
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
		                		
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			var dmnsName = ["整体结果"];

			var fScore = "";
			var fResultDesc = "";
			var fResultTitle = "";
			
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
				
				
    
			})
		</script>
	</body>
</html>
