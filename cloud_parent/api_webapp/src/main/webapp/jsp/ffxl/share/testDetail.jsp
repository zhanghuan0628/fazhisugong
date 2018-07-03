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
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/share/css/global.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/jsp/ffxl/share/css/test.css"/>
	</head>
	<body style="padding-bottom: 6rem;">
		<div id="downLoadLine">
			<img src="${base}/jsp/ffxl/share/images/wxLogo.png"/>
			<a onclick="more()">下载APP</a>
		</div>
		
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
					<img src="${base}/jsp/ffxl/share/images/test1.png"/>
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
		
		<a class="testNow">立即测试</a>
		<script src="${base}/jsp/ffxl/share/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/ffxl/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript" src="https://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="${base}/jsp/ffxl/share/js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function evaluationDetail(userId,sclId){
				$.ajax({
			        url: apiUrl+"/ffxl_scl/scl_detail",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
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
											"<img src='${base}/jsp/ffxl/share/images/test2.png'/>"+
											"<span>温馨提示</span>"+
										"</h1>"+
										"<div>"+prompt+"</div>"+
									"</div>";
									hintFlag = true;
			                	} else {
			                		hintFlag = false;
			                	}
			                	
				                $(".testDesc").append(hint);
				                
				                
				                if (data.pay) {
				                	hasPay = true;
				                } else{
				                	hasPay = false;
				                }
				                
				                
				                if (data.discountPrice == 0) {
				                	isFree = true;
				                } else {
				                	isFree = false;
				                }
								
								locationUrl = apiUrl+"/jsp/ffxl/share/testDetail.jsp?sclId="+sclId;
								wxtitle = data.sclName;
			                	wximgUrl= data.coverImg;
			                	wxdesc = data.summary;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
								
								dmnsType = data.dmnsType;
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
//			QQ支付
//			function alipay(userId,sclId){
//				$.ajax({
//			        url: apiUrl+"/h5/alipay/wapPay",
//			        type: "post",
//			        data: {userId:userId,sclId:sclId},
//			        dataType: "json",
//			        async:false,
//			        success: function(data) {
//			        	if(data.success){
//			                if(data.code == "2000"){
//			                	var data = data.data;
////			                	location.href="${base}/jsp/ffxl/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
//			                }else{
//			                	
//			                }
//			            }
//			        }
//			    });
//			}


//			微信支付
			function wechatpay(userId,sclId){
				$.ajax({
			        url: apiUrl+"/h5/wechatpay/wapPay",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
		                		var appId = data.appId;
			                    var timeStamp = data.timeStamp;
			                    var nonceStr = data.nonceStr;
			                    var packAge = "prepay_id=" + data.prepayId;
			                    var signType = data.signType;
			                    var paySign = data.paySign;
			                	onBridgeReady(appId,timeStamp,nonceStr,packAge,signType,paySign,sclId,userId);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}

//			微信支付
			function onBridgeReady(appId,timeStamp,nonceStr,packAge,signType,paySign,sclId,userId){
				WeixinJSBridge.invoke(
					'getBrandWCPayRequest', {
						"appId" : appId,     // 公众号名称，由商户传入     
						"timeStamp": timeStamp,         //时间戳，自1970年以来的秒数     
						"nonceStr" : nonceStr, //随机串     
						"package" : packAge,     
						"signType" : signType,         //微信签名方式:     
						"paySign" : paySign    // 微信签名 
					},
					function(res){     
						//alert(res.err_msg)
						if(res.err_msg == "get_brand_wcpay_request:ok") {
							alert("支付成功")
							window.location.href= apiUrl+"/jsp/ffxl/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
						} else {
						    if (res.err_msg == "get_brand_wcpay_request:fail") {
						    	alert("支付失败")
						    	$(".testNow").removeAttr("disabled");
							} else {
								$(".testNow").removeAttr("disabled");
							}
						}
						
						
					}
				); 
			}
			
			
//			免费
			function freeOrder(userId,sclId){
				$.ajax({
			        url: apiUrl+"/h5/wechatpay/create_free_order",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	location.href="${base}/jsp/ffxl/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			


			
			var dmnsType = "";
			
			var hasPay = false;
			var isFree = false;
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
//				localStorage.removeItem("ffxlQQuserId");
//          	localStorage.removeItem("ffxlQQnickName");
//          	localStorage.removeItem("ffxlQQheadImg");
//				localStorage.removeItem("ffxlWXuserId");
//          	localStorage.removeItem("ffxlWXnickName");
//          	localStorage.removeItem("ffxlWXheadImg");
            	
				var sclId = "";
				var Url = location.search;
				if (Url.indexOf("%3F")!=-1) {
					var Request = new getAuthQueryString();
					sclId = Request.sclId;
				} else if (Url.indexOf("code")!=-1) {
					sclId = getQueryStringAuth("sclId")
				} else {
					sclId = getQueryString("sclId");
				}
				
//				判断授权
				var userId = '';
				
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
						console.log("授权成功："+nickName);
					} else {
						if (localStorage.getItem("ffxlWXuserId") != null &&localStorage.getItem("ffxlWXuserId") != "") {
							userId = localStorage.getItem("ffxlWXuserId");
							headUrl = localStorage.getItem("ffxlWXheadImg");
							nickName = localStorage.getItem("ffxlWXnickName");
						} else {
							ffxlwxAuthorization("/ffxl/share/testDetail.jsp?sclId="+sclId);
//							wxAuthorization("/ffxl/share/testDetail.jsp?sclId="+sclId+"@test=ivan@aaa=123213");
						}
					}
					
					$(".testNow").click(function(){
						if (isFree) {
							freeOrder(userId,sclId);
						} else if (hasPay) {
							location.href="${base}/jsp/ffxl/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
						} else {
							$(".testNow").attr("disabled","disabled");
							wechatpay(userId,sclId);
						}
					})
					
					
				} else{
//					userId = "1"
					if (localStorage.getItem("ffxlQQuserId")==null) {
						$(".testNow").click(function(){
							QC.Login.showPopup({
								appId:"101443687",
								redirectURI:apiUrl+"/jsp/ffxl/share/callBack.html?page=testDetail&sclId="+sclId
							});
						})
						
					} else{
						userId = localStorage.getItem("ffxlQQuserId");
						//				立即测试
						$(".testNow").click(function(){
							if (isFree) {
								freeOrder(userId,sclId);
							} else if (hasPay) {
								location.href="${base}/jsp/ffxl/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
							} else {
								location.href = apiUrl+"/h5/alipay/wapPay?userId="+userId+"&sclId="+sclId;
							}
						})
						
					}
				}
				

				
//				var sclId = "09407849e0514b22a7cec314b12fb3a6";
				evaluationDetail(userId,sclId)
					
				
			})
		</script>
	</body>
</html>
