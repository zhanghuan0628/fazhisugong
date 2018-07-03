<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>文章详情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=20180111"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body style="padding-bottom: 6rem;">
		<div id="downLoadLine">
			<img src="images/wxLogo.png"/>
			<a onclick="more()">下载APP</a>
		</div>
		
		<div class="article">
			
			<div class="articleTitle">
				<h1></h1>
				<span></span>
			</div>
			<div class="articleContent">
				
			</div>
			
		</div>
		
		<div class="articleBottom">
			<a onclick="more()"><img src="images/article1.png"/><span></span></a>
			<a onclick="more()"><img src="images/article2.png"/><span></span></a>
		</div>
		
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/index.js?v=21" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			
			function detail(id){
				$.ajax({
			        url: apiUrl+"/api_article/detail",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	
			                	if (data.articleType=="serialize") {
//			                		$(".article").prepend("<h1 class='serializeTitle'><span>第五章</span></h1>");
			                		var serializeList = "";
			                		for (var i=0;i<2;i++) {
			                			if (i==0) {
			                				if (data.serializeList[0].tag=="not") {
			                					serializeList += "<li class='serialize1 noArticle'>前一章<span>已经是第一章</span></li>"
			                				} else {
			                					serializeList += "<li class='serialize1' data-serialize='"+data.serializeList[0].id+"'>前一章<span>"+data.serializeList[0].title+"</span></li>"
			                				}
			                			} else {
			                				if (data.serializeList[1].tag=="not") {
			                					serializeList += "<li class='serialize2 noArticle'>后一章<span>即将更新</span></li>"
			                				} else {
			                					serializeList += "<li class='serialize2' data-serialize='"+data.serializeList[1].id+"'>后一章<span>"+data.serializeList[1].title+"</span></li>"
			                				}
			                			}
			                		}
			                		
			                		$(".article").append("<div class='serializeList'>"+
										"<h1 class='welfareTitle'>连载列表<a class='serializeIcon' onclick='more()'></a></h1>"+
										"<ul>"+serializeList+"</ul>"+
									"</div>")
			                		
			                	}
			                	
			                	
			                	
			                	$(".article .articleTitle h1").text(data.title);
			                	
			                	var time = ""
			                		
		                		var obj = new Date(data.createDate);
		                		var oYear = obj.getFullYear();
		                		var oMonth = obj.getMonth()+1;
		                		var oDate = obj.getDate();
		                		
	                			time = oYear+"."+getzf(oMonth)+"."+getzf(oDate);
			                	
			                	$(".article .articleTitle span").text(time);
			                	
			                	var imgUrl = "";
			                	if (data.imgUrl!=null && data.imgUrl!="") {
			                		imgUrl = "<img class='articleImg' src='"+data.imgUrl+"'>";
			                	}
			                	
			                	$(".article .articleContent").html(imgUrl+data.content);
			                	
			                	if (data.counselorList!=null && data.counselorList.length!=0) {
			                		var counselorList = "";
				                	$.each(data.counselorList, function(i,item) {
				                		var skillName = item.skillName.split(" ").join("、");
				                		counselorList += "<li data-counselorid='"+item.counselorId+"' onclick='more()'>"+
											"<img src='"+item.headUrl+"'/>"+
											"<h1>"+item.name+" <span>"+item.jobName+"</span></h1>"+
											"<p class='consultSkill'>"+skillName+"</p>"+
											"<p class='consultData'><span class='location'>"+item.cityName+"</span><span class='consultCount'>"+item.consultCount+"人咨询过</span><span class='praiseCount'>"+item.praiseCount+"%好评</span></p>"+
										"</li>"
				                	});
				                	
				                	$(".article").append("<div class='counselorList'>"+
										"<h1 class='welfareTitle'>相关咨询师推荐</h1>"+
										"<ul>"+counselorList+"</ul>"+
									"</div>");
			                	}
				                	
			                	
			                	$(".articleBottom a").eq(0).find("span").text(data.readCount);
			                	$(".articleBottom a").eq(1).find("span").text(data.supportCount);
			                	
			                	var reTag = /<(?:.|\s)*?>/g;
			                	var content = data.content.replace(reTag,"").replace(/&nbsp;/ig,"");
			                	
			                	locationUrl = apiUrl+"/jsp/ffxl/share/articleDetail.jsp?id="+id;
								wxtitle = data.title;
								wximgUrl = data.imgUrl;
								wxdesc = content;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
								
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
				if(i<10) { 
					i = "0" + i; 
				}
				return i.toString(); 
			} 
			
			
			var locationUrl = "";
			var wxtitle = "";
			var wximgUrl = "";
			var wxdesc = "";
			
			$(function(){
				var id = getQueryString("id");
//				var id = "00ac73e7757948cabb3c190e0032c504"
				detail(id);
				
				$(".article").on("click",".serializeList ul li",function(){
					if (!$(this).hasClass("noArticle")) {
						var serializeId = $(this).data("serialize");
						location.href="articleDetail.html?id="+serializeId;
					}
				})
				
				
			})
		</script>
	</body>
</html>
