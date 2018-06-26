<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>测试题</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=1"/>
	</head>
	<body style="padding-bottom: 6.5rem;background-color: #F0F0F0;">
		
		<div class="testRate">
			<div class="clearfix">
				<a class="pre-test">&lt; 上一题</a>
				<div class="testNum">
					<span class="which">1</span><i>/</i><span class="total"></span>
				</div>
			</div>
			<div class="progressBar">
				<em></em>
			</div>
		</div>
		<div id="questionDetail">
		</div>
		
		
			
		
		<a class='sub-btn'>提交</a>
		
		<div id="testData" style="height: 0;visibility: hidden;">
			
		</div>
		
		<div id="subjectiveData" style="height: 0;visibility: hidden;">
			
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js?v=20180423" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			
//			数字转换英文
			function getChar(num){
		        return (num+10).toString(36).toUpperCase();
		    }
			
			function questionList1(userId,sclId){
				var qList = [];
				$.ajax({
			        url: apiUrl+"/ffxl_scl/qstn_detail",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	
			                	var data = data.data;
			                	var qNum = data.length;
			                	$(".testRate .total").text(qNum);
			                	
			                	for (var i=0;i<qNum;i++) {
			                		var oList = "";
			                		var hasImg = false;
			                		$.each(data[i].optnList, function(j,item) {
			                			var oImg = "";
			                			if (item.optnImg!=""&&item.optnImg!=null) {
				                			oImg = "<img src='"+item.optnImg+"'/>";
				                			hasImg = true;
				                			oList += "<li data-option='"+item.id+"' data-optnscore='"+item.optnScore+"'>"+oImg+"<p><span>"+getChar(j)+"."+item.optnContent+"</span></p></li>";
				                		} else {
				                			oList += "<li data-option='"+item.id+"' data-optnscore='"+item.optnScore+"'>"+getChar(j)+"."+item.optnContent+"</li>";
				                		}
			                			
			                		});
			                		
			                		var qImg = "";
			                		if (data[i].qstnImg!=""&&data[i].qstnImg!=null) {
			                			qImg = "<img src='"+data[i].qstnImg+"'/>";;
			                		}
			                		
			                		if (data[i].subjectivity) {
			                			qList[i] = "<div data-question='"+data[i].id+"' class='testDescription subjective'><i>问</i>："+data[i].qstnContent+qImg+"</div>"+
										"<div class='testChoice'>"+
											"<h2>回答：</h2>"+
											"<textarea class='subjectiveAnswer'></textarea>"+
										"</div>";
			                		} else{
			                			if (hasImg) {
				                			qList[i] = "<div data-question='"+data[i].id+"' class='testDescription'>"+data[i].qstnContent+qImg+"</div>"+
											"<div class='testChoice'>"+
												"<ul class='hasImg'>"+oList+"</ul>"+
											"</div>";
				                		} else{
				                			qList[i] = "<div data-question='"+data[i].id+"' class='testDescription'>"+data[i].qstnContent+qImg+"</div>"+
											"<div class='testChoice'>"+
												"<ul>"+oList+"</ul>"+
											"</div>";
				                		}
			                		}
				                		
			                	}
			                }else{
			                	
			                }
			            }
			        }
			    });
			    return qList;
			}
			
			function questionList2(userId,sclId){
				var qList = [];
				var qstnId = "";
				var html = "";
				$.ajax({
			        url: apiUrl+"/ffxl_scl/qstn_detail",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	
			                	
			                	var data = data.data;
			                	var qNum = data.length;
			                	for (var i=0;i<qNum;i++) {
			                		var oList = "";
			                		var hasImg = false;
			                		$.each(data[i].optnList, function(j,item) {
			                			var oImg = "";
			                			
			                			if (item.optnImg!=""&&item.optnImg!=null) {
				                			oImg = "<img src='"+item.optnImg+"'/>";
				                			hasImg = true;
			                				oList += "<li data-option='"+item.id+"' data-outtype='"+item.outType+"' data-outid='"+item.outId+"'>"+oImg+"<p><span>"+getChar(j)+"."+item.optnContent+"</span></p></li>";
				                		} else {
				                			oList += "<li data-option='"+item.id+"' data-outtype='"+item.outType+"' data-outid='"+item.outId+"'>"+getChar(j)+"."+item.optnContent+"</li>";
				                		}
			                			
			                		});
			                		
									qstnId = data[i].id;
									
									var qImg = "";
			                		if (data[i].qstnImg!=""&&data[i].qstnImg!=null) {
			                			qImg = "<img src='"+data[i].qstnImg+"'/>";;
			                		}
									
									if (hasImg) {
			                			html = "<div data-question='"+data[i].id+"' class='testDescription'>"+data[i].qstnContent+qImg+"</div>"+
										"<div class='testChoice'>"+
											"<ul class='hasImg'>"+oList+"</ul>"+
										"</div>";
			                		} else{
			                			html = "<div data-question='"+data[i].id+"' class='testDescription'>"+data[i].qstnContent+qImg+"</div>"+
										"<div class='testChoice'>"+
											"<ul>"+oList+"</ul>"+
										"</div>";
			                		}
										
									qList.push({"qstnId":qstnId,"html":html});
			                	}
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
				return qList;
			}
			
			
			function evaluationDetail(sclId){
				$.ajax({
			        url: apiUrl+"/ffxl_scl/scl_detail",
			        type: "post",
			        data: {sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	dmnsType = data.dmnsType;
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
//			提交答案
			function answer(userId,jsonData,testDuration){
				$.ajax({
			        url: apiUrl+"/ffxl_scl/save_answer",
			        type: "post",
			        data: {userId:userId,type:"free",testDuration:testDuration,answerJson:JSON.stringify(jsonData)},
			        dataType: "json",
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var reportId = data.data.id;
			                	
			                	if (isAndroid()) {
			                		window.android.toTest(data.code,data.message,reportId);
			                	} else {
			                		toTest(data.code,data.message,reportId);
			                	}
			                }else{
			                	if (isAndroid()) {
			                		window.android.toTest(data.code,data.message,"");
			                	} else {
			                		toTest(data.code,data.message,"");
			                	}
			                	
			                }
			            } else {
			            	if (isAndroid()) {
		                		window.android.toTest(data.code,data.message,"");
		                	} else {
		                		toTest(data.code,data.message,"");
		                	}
			            }
			        }
			    });
			}
			
			
			
			
//			提交主观题
//			function saveSubjectivity(sclId,optnId,userId,subjectivityContent,totalTest){
//				$.ajax({
//			        url: "${base}/scl/save_optn_subjectivity",
//			        type: "post",
//			        data: {sclId:sclId,optnId:optnId,userId:userId,subjectivityContent:subjectivityContent},
//			        dataType: "json",
//			        async:false,
//			        success: function(data) {
//			        	if(data.success){
//			                if(data.code == "2000"){
//			                	var whichTest = parseInt($(".testRate .which").text());
//						
//								if ($("#subjectiveData input").length==0) {
//									$("#subjectiveData").append("<input type='hidden' name='"+optnId+"' value='"+subjectivityContent+"' />")
//								} else {
//									var flag = true;
//									$("#subjectiveData").find("input").each(function(){
//										if ($(this).attr("name")==optnId) {
//											flag = false;
//											var val = $(this).val();
//											$(this).val(subjectivityContent);
//											return false;
//										} 
//									})
//									if (flag) {
//										$("#testData").append("<input type='hidden' name='"+optnId+"' value='"+subjectivityContent+"' />")
//									}
//								}
//								
//								setTimeout(function(){
//									$("#questionDetail").html(question[whichTest]);
//									var hasImgW = $(".hasImg li").width();
//									$(".hasImg li img").css("height",hasImgW+"px");
//									
//									$(".subjectiveBtn").remove();
//									if ($(".testDescription").hasClass("subjective")) {
//										$("body").append("<a class='subjectiveBtn'>提交答案</a>")
//									}
//									
//									
//									if (whichTest<totalTest) {
//										$(".testRate .which").text(whichTest+1);
//									}
//									
//									
//									if ($(".sub-btn").css("display")!="none") {
//										$(".sub-btn").addClass("sub-btn-active");
//									}
//									
//									if (totalTest!=1) {
//										$(".pre-test").show();
//									}
//									
//									
//									if (whichTest+1 == totalTest) {
//										$(".sub-btn").show();
//		//								$("body").append("<a class='sub-btn'>提交</a>");
//									} else if (whichTest == totalTest) {
//										$(".sub-btn").addClass("sub-btn-active").show();
//		//								$("body").append("<a class='sub-btn'>提交</a>");
//									} else {
//										$(".sub-btn").removeClass("sub-btn-active").hide();
//									}
//								},400)
//			                	
//								
//			                }else{
//			                	
//			                }
//			            }
//			        }
//			    });
//			}
			
			
			var dmnsType = "";
			var startTime = new Date().getTime();
			
			$(function(){
				var userId = getQueryString("userId");
				var sclId = getQueryString("sclId");
				evaluationDetail(sclId)
				
				if (dmnsType!="single2") {
					$(".testRate i,.progressBar").show();
//					不是单维度跳题
					question = questionList1(userId,sclId);
					var totalTest = parseInt($(".testRate .total").text());
					var progress = 1*100/totalTest+"%";
					$(".progressBar em").css("width",progress)
					
					$("#questionDetail").on("click",".testChoice ul li",function(){
						
						
						$(this).addClass("tc_on").siblings().removeClass("tc_on");
						var whichTest = parseInt($(".testRate .which").text());
	//					记录题目相关
						var qstnId = $(".testDescription").data("question");
						var optnId = $(this).data("option");
						var optnScore = $(this).data("optnscore");
						var ansDate = new Date().getTime(); 
						if ($("#testData input").length==0) {
							$("#testData").append("<input type='hidden' name='"+qstnId+"' value='"+optnId+","+optnScore+",0,"+ansDate+"' />")
						} else {
							var flag = true;
							$("#testData").find("input").each(function(){
								if ($(this).attr("name")==qstnId) {
									flag = false;
									var val = $(this).val();
									var changeTimes = parseInt(val.split(",")[2])+1;
									$(this).val(optnId+","+optnScore+","+changeTimes+","+ansDate);
									return false;
								} 
							})
							
							if (flag) {
								$("#testData").append("<input type='hidden' name='"+qstnId+"' value='"+optnId+","+optnScore+",0,"+ansDate+"' />")
							}
						}
						
						setTimeout(function(){
							$("#questionDetail").html(question[whichTest]);
							
							$(".subjectiveBtn").remove();
							if ($(".testDescription").hasClass("subjective")) {
								$("body").append("<a class='subjectiveBtn'>提交答案</a>")
							}
							
							var hasImgW = $(".hasImg li").width();
							$(".hasImg li img").css("height",hasImgW+"px");
							
							
							if (whichTest<totalTest) {
								$(".testRate .which").text(whichTest+1);
								progress = (whichTest+1)*100/totalTest+"%";
								$(".progressBar em").css("width",progress);
							}
							
							
							
							if (totalTest!=1) {
								$(".pre-test").show();
							}
							
							
							if (whichTest == totalTest) {
								$(".sub-btn").addClass("sub-btn-active");
							} else {
								$(".sub-btn").removeClass("sub-btn-active");
							}
						},400)
						
	
					})
					
					$("#questionDetail").html(question[0]);
					
					if ($(".testDescription").hasClass("subjective")) {
						$("body").append("<a class='subjectiveBtn'>提交答案</a>")
					}
					
					
					var hasImgW = $(".hasImg li").width();
					$(".hasImg li img").css("height",hasImgW+"px");
					
					
					var subFlag1 = true;
					
					$("body").on("click",".sub-btn",function(){
						if (!$(this).hasClass("sub-btn-active")){
//							alert("请答完所有问题");
							return false;
						}
						
						if (subFlag1) {
							subFlag1 = false;
							var jsonData = [];
						
	//						提交答案
							for (var i=0;i<$("#testData input").length;i++) {
								var qstnId = $("#testData input").eq(i).attr("name");
								var qsArr = $("#testData input").eq(i).val().split(",");
								var optnId = qsArr[0];
								var score = qsArr[1];
								var changeTimes = qsArr[2];
								var ansDate = qsArr[3];
								jsonData.push({"sclId":sclId,"qstnId":qstnId,"optnId":optnId,"score":score,"changeTimes":changeTimes,"ansDate":ansDate});
							}
							
							var testDuration = new Date().getTime()-startTime;
							
							answer(userId,jsonData,testDuration);
//							console.log(JSON.stringify(jsonData));
						}
						
					})
					
//					主观题提交
					$("body").on("click",".subjectiveBtn",function(){
						var optnId = $(".subjective").data("question");
						var subjectivityContent = $(".subjectiveAnswer").val();
						if (subjectivityContent==""){
							$("#layer").show();
							$("body").append("<div class='subjectiveHint'>"+
								"<div>"+
									"<img src='${base}/assets/images/subjectiveHint.png'/>"+
									"<p>回答完问题才能提交哦</p>"+
								"</div>"+
								"<a>OK</a>"+
							"</div>");
							return false;
						}
						
						saveSubjectivity(sclId,optnId,userId,subjectivityContent,totalTest)
					})
					
					
//					上一题
					$(".pre-test").click(function(){
						$(".subjectiveBtn").remove();
						$(".sub-btn").removeClass("sub-btn-active");
						var whichTest = parseInt($(".testRate .which").text());
						if (whichTest>2) {
							$(".testRate .which").text(whichTest-1);
							progress = (whichTest-1)*100/totalTest+"%";
							$(".progressBar em").css("width",progress);
							
							$("#questionDetail").html(question[whichTest-2]);
							var hasImgW = $(".hasImg li").width();
							$(".hasImg li img").css("height",hasImgW+"px");
						} else {
							$(".testRate .which").text("1");
							progress = 1*100/totalTest+"%";
							$(".progressBar em").css("width",progress);
								
							$("#questionDetail").html(question[0]);
							var hasImgW = $(".hasImg li").width();
							$(".hasImg li img").css("height",hasImgW+"px");
							$(".pre-test").hide();
						}
							
							
						if ($(".testDescription").hasClass("subjective")) {
							$("body").append("<a class='subjectiveBtn'>提交答案</a>");
							var preId1 = $("#questionDetail .testDescription").data("question");
							$("#subjectiveData").find("input").each(function(){
								if ($(this).attr("name")==preId1) {
									var subjectiveVal = $(this).val();
									$(".subjectiveAnswer").val(subjectiveVal);
								} 
							})
						} else {
							var preId1 = $("#questionDetail .testDescription").data("question");
							$("#testData").find("input").each(function(){
								if ($(this).attr("name")==preId1) {
									var val1 = $(this).val();
									var sel1 = val1.split(",")[0];
									$("#questionDetail .testChoice").find("li").each(function(){
										if ($(this).data("option")==sel1) {
											$(this).addClass("tc_on");
										}
									})
								} 
							})
						}
						
					})
					
					
					
				} else {
//					单维度跳题
					question = questionList2(userId,sclId);
					$("#questionDetail").on("click",".testChoice ul li",function(){
						$(this).addClass("tc_on").siblings().removeClass("tc_on");
						var whichTest = parseInt($(".testRate .which").text());
	//					记录题目相关
						var qstnId = $(".testDescription").data("question");
						var optnId = $(this).data("option");
						var outType = $(this).data("outtype");
						var outId = $(this).data("outid");
						var ansDate = new Date().getTime(); 
						
						var $this=$(this);
						setTimeout(function(){
							for (var i=0;i<question.length;i++) {
								if (question[i].qstnId==outId) {
									$("#questionDetail").html(question[i].html);
									var hasImgW = $(".hasImg li").width();
									$(".hasImg li img").css("height",hasImgW+"px");
								}
							}
							
							
							if ($this.data("outtype")==2) {
								$(".sub-btn").addClass("sub-btn-active")
							} else{
								$(".sub-btn").removeClass("sub-btn-active")
								$(".testRate .which").text(whichTest+1);
							}
							
							$(".pre-test").show();
						},400)
						
						
						if ($("#testData input").length==0) {
							$("#testData").append("<input type='hidden' name='"+qstnId+"' value='"+optnId+","+outType+","+outId+",0,"+ansDate+"' />")
						} else {
							var flag = true;
							$("#testData").find("input").each(function(){
								if ($(this).attr("name")==qstnId) {
									flag = false;
									var val = $(this).val();
									var changeTimes = parseInt(val.split(",")[3])+1;
									$(this).val(optnId+","+outType+","+outId+",0,"+ansDate);
									return false;
								} 
							})
							
							if (flag) {
								$("#testData").append("<input type='hidden' name='"+qstnId+"' value='"+optnId+","+outType+","+outId+",0,"+ansDate+"' />")
							}
						}
						
//						if ($(this).data("outtype")==2) {
//							$(".test-btn").prepend("<a class='sub-btn'>提交</a>");
//							return false;
//						} else{
//							$(".testRate .which").text(whichTest+1);
//						}
//						
//						$(".test-btn").show();
						
					})
					
					$("#questionDetail").html(question[0].html);
					
					
					var hasImgW = $(".hasImg li").width();
					$(".hasImg li img").css("height",hasImgW+"px");
					
					
					var subFlag2 = true;
					
					$("body").on("click",".sub-btn",function(){
						if (!$(this).hasClass("sub-btn-active")){
//							alert("请答完所有问题");
							return false;
						}
						
						if (subFlag2) {
							subFlag2 =false;
							var jsonData = [];
	//						提交答案
							for (var i=0;i<$("#testData input").length;i++) {
								var qstnId = $("#testData input").eq(i).attr("name");
								var qsArr = $("#testData input").eq(i).val().split(",");
								var optnId = qsArr[0];
								var outType = qsArr[1];
								var outId = qsArr[2];
								var changeTimes = qsArr[3];
								var ansDate = qsArr[5];
								jsonData.push({"sclId":sclId,"qstnId":qstnId,"optnId":optnId,"outType":outType,"outId":outId,"changeTimes":changeTimes,"ansDate":ansDate});
							}
							
							var testDuration = new Date().getTime()-startTime;
							
							answer(userId,jsonData,testDuration);
//							console.log(JSON.stringify(jsonData));
						}
						
						
					})
					
					
					
					
//					上一题
					$(".pre-test").click(function(){
						var whichTest = parseInt($(".testRate .which").text());
						$(".sub-btn").removeClass("sub-btn-active")
						if (whichTest==$("#testData input").length) {
							var lastQs = $("#testData input").length-1;
							var preIdNum = $("#testData input").length-2;
							var preId = $("#testData input").eq(preIdNum).attr("name");
							
							$(".testRate .which").text(whichTest-1);
							for (var i=0;i<question.length;i++) {
								if (question[i].qstnId==preId) {
									$("#questionDetail").html(question[i].html);
									var hasImgW = $(".hasImg li").width();
									$(".hasImg li img").css("height",hasImgW+"px");
								}
							}
							
							
							var preId2 = $("#questionDetail .testDescription").data("question");
							$("#testData").find("input").each(function(){
								if ($(this).attr("name")==preId2) {
									var val2 = $(this).val();
									var sel2 = val2.split(",")[0];
									$("#questionDetail .testChoice").find("li").each(function(){
										if ($(this).data("option")==sel2) {
											$(this).addClass("tc_on");
										}
									})
								} 
							})
							
							$("#testData input").eq(lastQs).remove();
							$("#testData input").eq(lastQs-1).remove();
						} else if (whichTest>2) {
							
							var lastQs = $("#testData input").length-1;
							var preId = $("#testData input").eq(lastQs).attr("name");
							
							$(".testRate .which").text(whichTest-1);
							for (var i=0;i<question.length;i++) {
								if (question[i].qstnId==preId) {
									$("#questionDetail").html(question[i].html);
									var hasImgW = $(".hasImg li").width();
									$(".hasImg li img").css("height",hasImgW+"px");
								}
							}
							
							
							var preId2 = $("#questionDetail .testDescription").data("question");
							$("#testData").find("input").each(function(){
								if ($(this).attr("name")==preId2) {
									var val2 = $(this).val();
									var sel2 = val2.split(",")[0];
									$("#questionDetail .testChoice").find("li").each(function(){
										if ($(this).data("option")==sel2) {
											$(this).addClass("tc_on");
										}
									})
								} 
							})
							
							$("#testData input").eq(lastQs).remove();
						} else {
							$(".testRate .which").text("1");
							$("#questionDetail").html(question[0].html);
							var hasImgW = $(".hasImg li").width();
							$(".hasImg li img").css("height",hasImgW+"px");
							$(".pre-test").hide();
							
							var preId2 = $("#questionDetail .testDescription").data("question");
							$("#testData").find("input").each(function(){
								if ($(this).attr("name")==preId2) {
									var val2 = $(this).val();
									var sel2 = val2.split(",")[0];
									$("#questionDetail .testChoice").find("li").each(function(){
										if ($(this).data("option")==sel2) {
											$(this).addClass("tc_on");
										}
									})
								} 
							})
						}
						
						
					})
				}
				
				
				$("body").on("click",".subjectiveHint a",function(){
					$("#layer").hide();
					$(".subjectiveHint").remove();
				})

			})
		</script>
	</body>
</html>
