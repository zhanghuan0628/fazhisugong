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
		<link rel="stylesheet" type="text/css" href="${base}/jsp/fxf/share/css/global.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/jsp/fxf/share/css/test.css"/>
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
					<img src="${base}/jsp/fxf/share/images/test1.png"/>
					<span>为什么要测</span>
				</h1>
				<div></div>
			</div>
				
		</div>
		
		<button class="testNow">立即测试</button>
		<script src="${base}/jsp/fxf/share/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/fxf/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="${base}/jsp/fxf/share/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function evaluationDetail(userId,sclId){
				$.ajax({
			        url: apiUrl+"/fxf_test/scl_detail",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	if (data.data.isTest == 0) {
			                		isTest = false;
			                	} else {
			                		isTest = true;
			                	}
			                	
			                	var data = data.data.detail;
			                	
			                	var price = "";
								if (data.discountPrice != 0) {
									if (data.discountPrice*10<1) {
										price = "(1凡豆)";
									} else {
										price = "("+data.discountPrice*10+"凡豆)";
									}
								}
			                	
			                	
			                	if (!isTest) {
			                		$(".testNow").text("立即测试 "+price+")")
			                	}
			                	
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
											"<img src='${base}/jsp/fxf/share/images/test2.png'/>"+
											"<span>温馨提示</span>"+
										"</h1>"+
										"<div>"+prompt+"</div>"+
									"</div>";
									hintFlag = true;
			                	} else {
			                		hintFlag = false;
			                	}
			                	
				                $(".testDesc").append(hint);
				                
			                	$(".testNow").attr("href","test.jsp?userId="+userId+"&sclId="+data.id+"&dmnsType="+data.dmnsType);
								
								locationUrl = apiUrl+"/jsp/fxf/share/testDetail.jsp?sclId="+sclId;
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
			
//			支付
			function buyTest(userId,sclId,dmnsType){
				$.ajax({
			        url: apiUrl+"/fxf_test/buyTest",
			        type: "post",
			        data: {userId:userId,sclId:sclId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
								location.href="${base}/jsp/fxf/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
			                }else{
			                	$(".testNow").removeAttr("disabled");
			                }
			            } else {
			            	if (data.code == "5000") {
			            		toast("凡豆不足，请下载飞小凡APP");
			            		setTimeout(function(){
			            			more();
			            		},1000)
			            		$(".testNow").removeAttr("disabled")
			            	}
			            }
			        },
			        error:function(){
			        	$(".testNow").removeAttr("disabled");
			        }
			    });
			}
			
			
			var isTest = false;
			
			var dmnsType = "";
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
//				localStorage.removeItem("QQuserId")
				var sclId = "";
				var Url = location.search;
				if (Url.indexOf("%3F")!=-1) {
					var pos = Url.indexOf("%3F");
					var str = Url.substr(pos+3);
					sclId = str.split("%3D")[1];
				} else {
					sclId = getQueryString("sclId");
				}
				
				var userId = "";
				// var role = "";
				if (isWeChat()) {
					var oauth = "${oauth}";
					var headUrl = '';
					var nickName = '';
					// var role = '';
					if (oauth == 'true') {
						userId = "${user.id}";
						headUrl = "${user.headImg}";
						nickName = "${user.username}";
						// role = "${user.role}";
						localStorage.setItem("fxfWXuserId",userId);
						localStorage.setItem("fxfWXheadImg",headUrl);
						localStorage.setItem("fxfWXnickName",nickName);
						// if (role!=null) {
						// 	localStorage.setItem("fxfWXrole",role);
						// }
						console.log("授权成功："+nickName);
					} else {
						if (localStorage.getItem("fxfWXuserId") != null &&localStorage.getItem("fxfWXuserId") != "") {
							userId = localStorage.getItem("fxfWXuserId");
							headUrl = localStorage.getItem("fxfWXheadImg");
							nickName = localStorage.getItem("fxfWXnickName");
							// role = localStorage.getItem("fxfWXrole");
						} else {
							wxAuthorization("/fxf/share/testDetail.jsp?sclId="+sclId);
						}
					}
					
					
					$(".testNow").click(function(){
						// if (role != null && role != "") {
							if (!isTest) {
								$(this).attr("disabled","disabled");
								buyTest(userId,sclId,dmnsType)
							} else {
								location.href="${base}/jsp/fxf/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
							}
						// } else{
						// 	location.href="${base}/jsp/fxf/share/editInfo.jsp?from=WX&page=testDetail&sclId="+sclId
						// }
						
					})
					
				} else{
					if (localStorage.getItem("fxfQQuserId")==null) {
						$(".testNow").click(function(){
							QC.Login.showPopup({
								appId:"101391991",
								redirectURI:apiUrl+"/jsp/fxf/share/callBack.html?page=testDetail&sclId="+sclId
							});
						})
						
					} else{
						userId = localStorage.getItem("fxfQQuserId");
						// role = localStorage.getItem("fxfQQrole");
						//				立即测试
						$(".testNow").click(function(){
							// if (role != null) {
								if (!isTest) {
									$(this).attr("disabled","disabled");
									buyTest(userId,sclId,dmnsType)
								} else {
									location.href="${base}/jsp/fxf/share/test.jsp?userId="+userId+"&sclId="+sclId+"&dmnsType="+dmnsType;
								}
							// } else{
							// 	location.href="${base}/jsp/fxf/share/editInfo.jsp?from=QQ&page=testDetail&sclId="+sclId
							// }
							
						})
						
					}
				}
					
				
				
				evaluationDetail(userId,sclId);
				
				
				
			})
		</script>
	</body>
</html>
