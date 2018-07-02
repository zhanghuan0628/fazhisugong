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
		<link rel="stylesheet" type="text/css" href="css/global.css?v=20180221"/>
	</head>
	<body style="background-color: #f2f2f2;">
		<div class="floor">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
		<div class="originalLetter">
			<div class="originalLetterTitle">
				To：<span></span>
			</div>
			<div class="originalLetterContent"></div>
			<div class="originalLetterName">
				<img src=""/>
				<p></p>
				<span></span>
			</div>
		</div>
		
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			apiUrl = "http://"+window.location.host+"/sleep";
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
		                		document.title=data.title;
		                		$(".originalLetterTitle span").text(data.getNickname);
		                		$(".originalLetter .originalLetterContent").text(data.content);
		                		
		                		var notepaperImg = ""
		                		if (data.notepaperImg!=null) {
		                			notepaperImg = data.notepaperImg;
		                		} else {
		                			notepaperImg = "images/letterBg.jpg";
		                		}
		                		
		                		$(".originalLetter").css("background-image","url("+notepaperImg+")");
		                		
		                		var headImg = "";
		                		var nickname = "";
		                		if (data.see==1) {
	                				nickname = data.nickname;
	                				headImg = data.headImg;
	                			} else{
	                				nickname = "匿名用户";
	                				headImg = "images/anonymous.png";
	                			}
		                		
		                		$(".originalLetter .originalLetterName img").attr("src",headImg);
		                		$(".originalLetter .originalLetterName p").text(nickname);
		                		
		                		var time = ""
		                		var obj = new Date(data.createDate);
		                		var oYear = obj.getFullYear();
		                		var oMonth = obj.getMonth()+1;
		                		var oDate = obj.getDate();
		                		time = oYear+"/"+getzf(oMonth)+"/"+getzf(oDate);
		                		$(".originalLetter .originalLetterName span").text(time);
		                		
		                		locationUrl = apiUrl+"/jsp/fxf/share/originalLetter.jsp?mailId="+mailId;
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
			
			function getzf(num){
			    if(parseInt(num) < 10){
			        num = '0'+num;
			    }
			    return num;
			}
			
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			$(function(){
    			var mailId = getQueryString("mailId");
    			openMail(mailId);
			})
		</script>
	</body>
</html>