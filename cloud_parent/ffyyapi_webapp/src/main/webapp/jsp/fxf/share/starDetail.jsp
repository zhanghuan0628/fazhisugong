<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
		<title>星球详情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/swiper.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/global.css?v=2"/>
    </head>
    <body>
    	<div class="starInfo">
    		<div class="starTitle">
    			<img src=""/>
    			<h1></h1>
    			<p></p>
    			<a class="join" onclick="more()">+ 加入</a>
    		</div>
    		<!--<div class="starResident">
				<ul>
					<li><img src=""/></li>
					<li><img src=""/></li>
					<li><img src=""/></li>
					<li><img src=""/></li>
					<li><img src=""/></li>
				</ul>
				
				<a onclick="more()">星球居民</a>
    		</div>-->
    	</div>
    	
    	<!--<div class="starStick">
    		<ul>
    			<li>
    				<span>置顶</span>【每周话题】高考真的能改变一个人的一生高考真的能改变一个人的一生
    			</li>
    			<li>
    				<span>置顶</span>【每周话题】高考真的能改变一个人的一生高考真的能改变一个人的一生
    			</li>
    			<li>
    				<span>置顶</span>【每周话题】高考真的能改变一个人的一生高考真的能改变一个人的一生
    			</li>
    		</ul>
    	</div>-->
    	
    	<div class="swiper-container" id="doors">
			<div class="swiper-pagination"></div>
		    <div class="swiper-wrapper">
		        <div class="swiper-slide" id="elite">
		        	<ul class="answerList">
		        		<!--<li>
		        			<div class="whoAnswer">
		        				<img src=""/>
		        				<p>奔跑的小绵羊</p>
		        				<span>回答了</span>
		        			</div>
		        			<h1>爸妈不关心我怎么办？</h1>
		        			<p class="ansCont">我爸妈天天晚上都特别晚回家，我一个人在家怕，是不知道和谁说，他们好像一点都不关心我在家怕，可是不知道和谁说，他们好像一点都不关心。</p>
		        			<div class="ansData clearfix">
		        				<p>#学习压力#</p>
		        				<span class="ansPraise"><img src="images/cl-praise.png"/> 2</span>
		        				<span class="ansGift"><img src="images/cl-gift.png"/> 2</span>
		        			</div>
		        		</li>-->
		        		
		        	</ul>
		        </div>
		        <div class="swiper-slide" id="newest">
		        	<ul>
		        		<!--<li>
		        			<h1><img src=""/><span>哈哈哈</span></h1>
		        			<div>
		        				<h2>父母不理解我我觉得我很自卑，什么都差父母也得我很</h2>
			        			<p>我觉得我很自卑，什吗么都差，父母也不理的解我的烦恼很感动的我觉得我很自卑，什吗么都差，父母也不理的解我的烦恼很感动的</p>
		        			</div>
		        		</li>-->
		        	</ul>
		        </div>
		    </div>
		</div>
    	
    	<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/swiper.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/clamp.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			星球信息
			function getTags(id){
				$.ajax({
			        url: apiUrl+"/fxf_admin/getTags",
			        type: "post",
			        data: {id:id},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data[0];
								$(".starTitle img").attr("src",data.headImg);
								$(".starTitle h1").text(data.title);
								$(".starTitle p").text(data.summary);
								
								locationUrl = apiUrl+"/jsp/fxf/share/starDetail.jsp?id="+id;
								wxtitle = data.title;
			                	wximgUrl= "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
			                	wxdesc = data.summary;
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			星球居民
			function getTagUsers(tagId){
				$.ajax({
			        url: apiUrl+"/fxf_admin/getTagUsers",
			        type: "post",
			        data: {tagId:tagId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	if (data.length!=0) {
			                		var html = "";
				                	$.each(data, function(i,item) {
				                		if (i<5) {
				                			html += "<li><img src='"+item.headImg+"'/></li>"
				                		}
				                	});
				                	
				                	$(".starInfo").append("<div class='starResident'><ul>"+html+"</ul><a onclick='more()'>星球居民</a></div>")
			                	}
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
//			置顶信息
			function getTagTopQuestions(tagId){
				$.ajax({
			        url: apiUrl+"/fxf_question/getTagTopQuestions",
			        type: "post",
			        data: {tagId:tagId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	if (data.length!=0) {
			                		var html = "";
				                	$.each(data, function(i,item) {
				                		html += "<li><span>置顶</span>"+item.title+"</li>"
				                	});
				                	
				                	$(".starInfo").after("<div class='starStick'><ul>"+html+"</ul></div>")
			                	}
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
			
//			精华传送门
			function getBestAnswersByTag(questionTagId){
				$.ajax({
			        url: apiUrl+"/fxf_question/getBestAnswersByTag",
			        type: "post",
			        data: {questionTagId:questionTagId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	var html = "";
			                	$.each(data, function(i,item) {
			                		var questionTag = "";
			                		if (item.questionTag.indexOf(",")!=-1){
			                			questionTag = item.questionTag.split(",")[0];
			                		} else {
			                			questionTag = item.questionTag;
			                		}
			                		
			                		var headImg = "";
				                	if (item.headImg!=null) {
				                		headImg = item.headImg;
				                	} else {
				                		headImg = "${base}/jsp/fxf/share/images/anonymous.png"
				                	}
			                		
			                		html += "<li data-id='"+item.id+"'>"+
					        			"<div class='whoAnswer'>"+
					        				"<img src='"+headImg+"'/>"+
					        				"<p>"+item.nickName+"</p>"+
					        				"<span>回答了</span>"+
					        			"</div>"+
					        			"<h1>"+item.questionContent+"</h1>"+
					        			"<p class='ansCont'>"+item.content+"</p>"+
					        			"<div class='ansData clearfix'>"+
					        				"<p>#"+questionTag+"#</p>"+
					        				"<span class='ansPraise'><img src='images/cl-praise.png'/>"+item.praises+"</span>"+
					        				"<span class='ansGift'><img src='images/cl-gift.png'/>"+item.giftUsers+"</span>"+
					        			"</div>"+
					        		"</li>"
			                	});
			                	
			                	$("#elite ul").html(html);
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
//			最新心事门
			function getAllQuestions(tagId){
				$.ajax({
			        url: apiUrl+"/fxf_question/get_all_questions",
			        type: "post",
			        data: {tagId,tagId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	var html = "";
			                	
			                	$.each(data, function(i,item) {
			                		var headImg = "";
				                	if (item.headImg!=null) {
				                		headImg = item.headImg;
				                	} else {
				                		headImg = "${base}/jsp/fxf/share/images/anonymous.png"
				                	}
			                		
			                		var content = "";
				                	if (item.img!=null) {
				                		content = "<div style='background-image:url("+item.img+")'>";
				                	} else {
				                		content = "<p>"+item.content+"</p>";
				                	}
				                	
			                		html += "<li data-id='"+item.id+"'>"+
					        			"<h1><img src='"+headImg+"'/><span>"+item.nickName+"</span></h1>"+
					        			"<div class='worryCont'>"+
					        				"<h2>"+item.title+"</h2>"+content+
					        			"</div>"+
					        		"</li>"	
			                	});
			                	
			                	$("#newest ul").html(html);
					        		
				        		$("#newest ul li").each(function(){
				        			if ($(this).find("p").length!=0) {
				        				var titleH = parseFloat($(this).find("h2").css('height'))
										var lineH = parseFloat($(this).find("p").css('line-height'))
										var divH = parseFloat($(this).find(".worryCont").height())
										
										var contH = divH-titleH-lineH;
										var contL = parseInt(contH/lineH);
										var camp = $(this).find("p")[0];
										$clamp(camp, {clamp: contL});
				        			}
			                	})
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
				
				var id = getQueryString("id");
				
				getTags(id);
				getTagUsers(id);
				getTagTopQuestions(id)
				getBestAnswersByTag(id);
				getAllQuestions(id);
				
				var swiper = new Swiper('#doors', {
			        pagination: '.swiper-pagination',
			        paginationClickable: true,
			        paginationBulletRender: function (swiper, index, className) {
			        	var pagination = "";
			        	if (index==0) {
			        		pagination = '<span class="' + className + '"><em></em><i>精华传送门</i></span>';
			        	} else if (index==1){
			        		pagination = '<span class="' + className + '"><em></em><i>最新心事门</i></span>';
			        	}
			            return pagination;
			        }
			    });
			    
			    var headW = $(".starResident ul li").eq(0).find("img").width();
			    $(".starResident ul li").each(function(){
			    	$(this).find("img").css("height",headW);
			    })
			    
			    $(window).resize(function(){
			    	headW = $(".starResident ul li").eq(0).find("img").width();
				    $(".starResident ul li").each(function(){
				    	$(this).find("img").css("height",headW);
				    })
			    })
			    
//				固定导航栏
				var cateTop = $(".swiper-pagination").offset().top;
				$(window).scroll(function(){
					if (cateTop-$(window).scrollTop()<=0) {
						$(".swiper-pagination").css("position","fixed");
					} else {
						$(".swiper-pagination").css("position","absolute");
					}
				})
			    
			    
//			    传送门跳转
				$("#elite").on("click","li",function(){
					var answerId = $(this).data("id");
					location.href="${base}/jsp/fxf/share/answer.jsp?id="+answerId;
				})
			    
			    
//			    心事门跳转
				$("#newest").on("click","li",function(){
					var questionId = $(this).data("id");
					location.href="${base}/jsp/fxf/share/interlocutionDetail.jsp?questionId="+questionId;
				})
			   
			})
		</script>
 	</body>
</html>