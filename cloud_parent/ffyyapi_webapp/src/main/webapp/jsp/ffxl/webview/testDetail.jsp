<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>心理测试</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/webview/css/global.css?v=1"/>
	</head>
	<body>
		<div class="testInfo">
			<div class="testCover"></div>
			<div class="testTitle">
				<h1></h1>
				<div class="testData">
					<p class="testPrice"></p>
					<p class="hasTest">已测：<span></span></p>
				</div>
				
			</div>
		</div>
		
		<div class="testDesc">
			<div class="testWhy">
				<h1 class="titleIcon">
					<img src="${base}/jsp/ffxl/webview/images/test1.png"/>
					<span>为什么要测</span>
				</h1>
				<div></div>
			</div>
				
			<!--<div class="testHint">
				<h1 class="titleIcon">
					<img src="images/test2.png"/>
					<span>温馨提示</span>
				</h1>
				<div>
					1.测试题：45题，测试时间：约10分钟<br />
					2.支付成功后，本测评可以重复测试，每次答题结束后会形成一份专业的测评报告。
				</div>
			</div>-->
		</div>
		
		<script src="${base}/jsp/ffxl/webview/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/ffxl/webview/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/ffxl/webview/js/index.js?v=20180423" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
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
			                	
			                	
			                	$(".testInfo .testCover").css("background-image","url("+data.detailImg+")");
			                	$(".testInfo .testTitle h1").text(data.sclName);
			                	$(".testInfo .testTitle .testData .testPrice").html("￥"+data.discountPrice.toFixed(2)+" <span>"+data.orginalPrice.toFixed(2)+"</span>")
			                	
			                	$(".testInfo .testTitle .testData .hasTest span").text(data.testTimes);
			                	
			                	var regCont = new RegExp("\n", 'g');
			                	var str1 = data.details;
			                	var str2 = data.prompt;
			                	var detail = str1.replace(regCont,"<br/>");
			                	$(".testDesc .testWhy div").html(detail);
			                	
			                	var hint = "";
			                	if (str2!=""&&str2!=null&&str2!="<p><br></p>") {
			                		var prompt = str2.replace(regCont,"<br/>");
			                		hint = "<div class='testHint'>"+
										"<h1 class='titleIcon'>"+
											"<img src='${base}/jsp/ffxl/webview/images/test2.png'/>"+
											"<span>温馨提示</span>"+
										"</h1>"+
										"<div>"+prompt+"</div>"+
									"</div>";
									hintFlag = true;
			                	} else {
			                		hintFlag = false;
			                	}
			                	
				                $(".testDesc").append(hint);
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			$(function(){
				var sclId =  getQueryString("sclId");
				
//				var sclId = "09407849e0514b22a7cec314b12fb3a6";
				evaluationDetail(sclId)
					
				
			})
		</script>
	</body>
</html>
