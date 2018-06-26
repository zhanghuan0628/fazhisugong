<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>暖心回信</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=1"/>
	</head>
	<body style="background:url(images/envelopeBg.jpg) no-repeat;background-size: cover;">
		<!--<div class="floor">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">更多免费测试，等你来玩</div>
			<a href="#" onclick="more()">下载App </a>
		</div>-->
		<div class="envelope">
			<em class="openLetter"></em>
			<div class="flip">
				<div class="flipF"></div>
				<div class="flipB"></div>
			</div>
		</div>
		
		
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function openMail(mailId){
				$.ajax({
			        url: apiUrl+"/fxf_mail/openMail",
			        type: "post",
			        data: {userId:"1",mailId:mailId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		
		                		locationUrl = apiUrl+"/jsp/fxf/share/envelope.jsp?mailId="+mailId;
				    			wxtitle = "解忧信箱 | "+data.title;
								wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/mail_share.jpg";
								wxdesc = data.content;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			$(function(){
				var mailId = getQueryString("mailId");
				openMail(mailId);
				$(".openLetter").click(function(){
					$(this).hide();
					$(".flip").addClass("flipAnimation");
					setTimeout(function(){
						location.href="replyLetter.jsp?mailId="+mailId;
					},1000)
					
				})
				
			})
		</script>
	</body>
</html>