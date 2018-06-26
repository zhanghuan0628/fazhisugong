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
		<link rel="stylesheet" type="text/css" href="css/global.css?v=14"/>
	</head>
	<body style="background-color: #f2f2f2;">
		<div class="floor">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
		<div class="originalLetterLink">
			原信：<span></span>
			<em></em>
		</div>
		
		<div class="replyLetter">
			<div class="replyLetterContent">
				<div class="rlc-Font"></div>
				<div class="replyLetterName">
					<img src=""/>
					<p></p>
					<span></span>
				</div>
			</div>
			<div class="replyLetterBtn">
				<a class="rlb-left" href="#" onclick="more()">给Ta写信</a>
				<a class="rlb-right" href="#" onclick="more()">答谢Ta</a>
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
		                		$(".originalLetterLink span").text(data.title);
		                		
		                		var content = "";
		                		var regCont = new RegExp("\n", 'g');
		                		content = data.reContent.replace(regCont,"<br/>");
		                		
		                		$(".replyLetterContent .rlc-Font").html(content);
		                		
		                		var notepaperImg = ""
		                		if (data.notepaperImg!=null) {
		                			notepaperImg = data.notepaperImg;
		                		} else {
		                			notepaperImg = "images/letterBg.jpg";
		                		}
		                		
		                		$(".replyLetter").css("background-image","url("+notepaperImg+")");
		                		
		                		var getHeadImg = "";
		                		if (data.getHeadImg!=null) {
		                			getHeadImg = data.getHeadImg;
		                		} else{
		                			getHeadImg = "images/anonymous.png";
		                		}
		                		$(".replyLetterName img").attr("src",getHeadImg);
		                		$(".replyLetterName p").text(data.getNickname);
		                		
		                		var time = ""
		                		var obj = new Date(data.reDate);
		                		var oYear = obj.getFullYear();
		                		var oMonth = obj.getMonth()+1;
		                		var oDate = obj.getDate();
		                		time = oYear+"/"+getzf(oMonth)+"/"+getzf(oDate);
		                		$(".replyLetterName span").text(time);
		                		
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
    			
//  			查看原信
    			$(".originalLetterLink").click(function(){
    				location.href="originalLetter.jsp?mailId="+mailId;
    			})
			})
		</script>
	</body>
</html>