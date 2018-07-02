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
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/test.css"/>
	</head>
	<body style="padding-bottom: 6rem;">
		
		<div class="testInfo">
			<div class="testCover"></div>
			<div class="testTitle">
				
			</div>
		</div>
		
		<div class="testDesc">
			<div class="testWhy">
				<h1 class="titleIcon">
					<img src="images/test1.png"/>
					<span>为什么要测</span>
				</h1>
				<div></div>
			</div>
				
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			var apiUrl = "http://192.168.0.129:8080/ffyyapi_webapp";
			function evaluationDetail(sclId){
				$.ajax({
			        url: apiUrl+"/fxf_test/scl_detail",
			        type: "post",
			        data: {sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	
			                	
			                	var data = data.data.detail;
			                	
			                	$(".testInfo .testCover").css("background-image","url("+data.detailImg+")");
			                	$(".testInfo .testTitle").text(data.sclName);
			                	
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
											"<img src='images/test2.png'/>"+
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
				var sclId = getQueryString("sclId");
				
//				var sclId = "f92e123c2b5d48079d25f29dae2e2b0e"
//				var sclId = "093e44c17c4e427f8cd058260a244996"
//				var userId = '00a25049f539401199f5af9f72943bf6';
				
				evaluationDetail(sclId)
				
				
			})
		</script>
	</body>
</html>
