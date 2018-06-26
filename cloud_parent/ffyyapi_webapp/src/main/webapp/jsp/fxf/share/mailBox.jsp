<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>解忧信箱</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/swiper-3.4.2.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/global.css?v=12"/>
	</head>
	<body>
		<div class="floor">
			<div class="flogo"><img src="images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
		
		<div id="layer"></div>
		
		<div class="warmIntroPop">
			<h2>暖心师是谁？</h2>
			<p>暖心师是一群善于治愈悲伤，传递温暖的人。</p>
			<h2>想要成为暖心师？</h2>
			<p>添加微信客服feifanxinli02，<br />备注：暖心师。通过我们的审核，就可以成为暖心师啦。</p>
			<a>知道了</a>
		</div>
		
		
		<div class="noTimes">想要看更多的信件，请下载青少年心理社区app——<br/>飞小凡。</div>
		
		<div class="warmHeart">
			<h1 class="titleR">暖心师<img class="warmIntro" src="images/warmIntro.png" alt="" /></h1>
			<div class="swiper-container" id="warmHeart">
				<div class="swiper-wrapper">
					<!--<div class="swiper-slide">
						<img src="images/headimg.png"/>
						<p class="wh-name">用户昵称</p>
						<p class="wh-info">我愿意做一个树洞，倾听你的声音</p>
						<a href="">给Ta写信</a>
					</div>
					<div class="swiper-slide">
						<img src="images/headimg.png"/>
						<p class="wh-name">用户昵称</p>
						<p class="wh-info">我愿意做一个树洞，倾听你的声音</p>
						<a href="">给Ta写信</a>
					</div>
					<div class="swiper-slide">
						<img src="images/headimg.png"/>
						<p class="wh-name">用户昵称</p>
						<p class="wh-info">我愿意做一个树洞，倾听你的声音</p>
						<a href="">给Ta写信</a>
					</div>
					<div class="swiper-slide">
						<img src="images/headimg.png"/>
						<p class="wh-name">用户昵称</p>
						<p class="wh-info">我愿意做一个树洞，倾听你的声音</p>
						<a href="">给Ta写信</a>
					</div>-->
				</div>
			</div>
		</div>
		
		<div class="newestLetter">
			<h1 class="titleR">最新回信</h1>
			<ul>
				<!--<li style="background-image: url(images/mail.jpg);">
					<em class="zip"></em>
					<div class="stamp">
						<img src="images/stampImg.jpg"/>
						<em class="post"></em>
					</div>
					<div class="sender">
						<img src="images/headimg.png"/>
						<span>小旋风时代</span>
					</div>
					
					<div class="letterDetail">
						<h2>世界上有友情吗？世界上有友情吗？</h2>
						<p>世界上有友情吗发多少古阿斯发电公司的按到高大上个的还记</p>
					</div>
				</li>-->
			</ul>
		</div>
		
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script src="js/swiper-3.4.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			apiUrl = "http://"+window.location.host+"/sleep";
			function getProHelper(){
				$.ajax({
			        url: apiUrl+"/fxf_mail/getProHelper",
			        type: "post",
			        data: {},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		var html = "";
		                		$.each(data, function(i,item) {
		                			var content = "";
		                			if (item.content!=null) {
		                				content = item.content;
		                			}
		                			if (i!=0) {
		                				html += "<div class='swiper-slide' data-id='"+item.userId+"'>"+
											"<img src='"+item.headImg+"'/>"+
											"<p class='wh-name'>"+item.nickname+"</p>"+
											"<p class='wh-info'>"+content+"</p>"+
											"<a class='aa'>给Ta写信</a>"+
										"</div>"
		                			}
		                		});
		                		
		                		$("#warmHeart .swiper-wrapper").html(html);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			function getAllReidMail(){
				$.ajax({
			        url: apiUrl+"/fxf_mail/getAllReidMail",
			        type: "post",
			        data: {},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
		                		var data = data.data;
		                		var html = "";
		                		$.each(data, function(i,item) {
		                			var envelopeImg = "";
		                			var stampImg = "";
		                			var headImg = "";
		                			var nickname = "";
		                			if (item.envelopeImg!=null&&item.envelopeImg!="") {
		                				envelopeImg = item.envelopeImg;
		                			} else {
		                				envelopeImg = "images/mail.jpg"
		                			}
		                			
		                			if (item.stampImg!=null&&item.stampImg!="") {
		                				stampImg = item.stampImg;
		                			} else {
		                				stampImg = "images/stampImg.png"
		                			}
		                			
		                			if (item.see==1) {
		                				nickname = item.nickname;
		                				headImg = item.headImg;
		                			} else{
		                				nickname = "匿名用户";
		                				headImg = "images/anonymous.png";
		                			}
		                			
	                				html += "<li style='background-image: url("+envelopeImg+");' data-mailid='"+item.id+"'>"+
										"<em class='zip'></em>"+
										"<div class='stamp'>"+
											"<img src='"+stampImg+"'/>"+
											"<em class='post'></em>"+
										"</div>"+
										"<div class='sender'>"+
											"<img src='"+headImg+"'/>"+
											"<span>"+nickname+"</span>"+
										"</div>"+
										
										"<div class='letterDetail'>"+
											"<h2>"+item.title+"</h2>"+
											"<p>"+item.content+"</p>"+
										"</div>"+
									"</li>"
		                		});
		                		
		                		$(".newestLetter ul").html(html);
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
				getProHelper();
				getAllReidMail()
				var swiper = new Swiper('#warmHeart', {
			        slidesPerView: 2.5,
			        spaceBetween: 10,
			        freeMode: true
			    });
			    
			    $(".warmIntro").click(function(){
			    	$("#layer,.warmIntroPop").show();
			    });
			    
			    $(".warmIntroPop a").click(function(){
			    	$("#layer,.warmIntroPop").hide();
			    })
			    
			    $("body").on("click","#warmHeart .swiper-slide",function(e){
			    	var counselorId = $(this).data("id");
			    	location.href = apiUrl+"/jsp/fxf/share/counselorHomepage.jsp?id="+counselorId;
			    })
			    
			    $("body").on("click","#warmHeart .swiper-slide a",function(e){
			    	e.preventDefault();
			    	more();
			    })
			    
			    $("body").on("click",".newestLetter ul li",function(){
			    	var mailId = $(this).data("mailid");
			    	
			    	$.ajax({
				        url: apiUrl+"/fxf_mail/openMail",
				        type: "post",
				        data: {userId:"1",mailId:mailId},
				        dataType: "json",
				        async:false,
				        success: function(data) {
				        	if(data.success){
				                if(data.code == "2000"){
			                		location.href="replyLetter.jsp?mailId="+mailId;
				                }else {
				                	
				                }
				            } else {
				            	if(data.code == "5000"){
				                	$(".noTimes").show();
				                	setTimeout(function(){
				                		more();
				                	},3000)
				                }
				            }
				        }
				    });
			    	
			    	
			    })
    			
    			locationUrl = apiUrl+"/jsp/fxf/share/mailBox.jsp";
    			wxtitle = "一个藏着很多青少年心事的解忧信箱 ";
				wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
				wxdesc = "有些话不知道对谁说，就寄封信到解忧信箱吧！"
				wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			})
		</script>
	</body>
</html>