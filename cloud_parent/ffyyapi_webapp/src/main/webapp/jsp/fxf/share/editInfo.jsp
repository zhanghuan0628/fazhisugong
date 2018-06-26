<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>编辑资料</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=1"/>
    </head>
    <body>
    	<div class="editInfo">
    		<img id="role" src="images/m1_on.png"/>
    		<p class="nickName"></p>
    		
    		<div class="sexSel">
    			<p class="male sex_on"><img src="images/male.png"/><span>男</span></p>
    			<p class="female"><img src="images/female.png"/><span>女</span></p>
    		</div>
    		
    		<div class="roleSel">
    			<h1>角色选择 <span>(角色选择后不可修改)</span></h1>
    			<ul class="maleRole">
    				<li class="role_on">
    					<img src="images/m1_on.png"/>
    					<p>学生</p>
    				</li>
    				<li>
    					<img src="images/m2.png"/>
    					<p>大人</p>
    				</li>
    				<li>
    					<img src="images/m3.png"/>
    					<p>老师</p>
    				</li>
    			</ul>
    			
    			<ul class="femaleRole">
    				<li class="role_on">
    					<img src="images/f1_on.png"/>
    					<p>学生</p>
    				</li>
    				<li>
    					<img src="images/f2.png"/>
    					<p>大人</p>
    				</li>
    				<li>
    					<img src="images/f3.png"/>
    					<p>老师</p>
    				</li>
    			</ul>
    		</div>
    		
    		<a class="infoSubmit">完成</a>
    	</div>
    	<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    	<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
    	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
    	<script type="text/javascript">
    		
//			信息编辑
			function selectRole(id,role,nickname,headImg,sex,from,hrefUrl){
				$.ajax({
			        url: apiUrl+"/fxf_login/selectRole",
			        type: "post",
			        data: {id:id,role:role,nickname:nickname,headImg:headImg,sex:sex},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	if (from == "QQ") {
			                		localStorage.setItem("fxfQQrole",role);
			                	} else{
			                		localStorage.setItem("fxfWXrole",role);
			                	}
			                	location.assign(hrefUrl);
			                }
			            }
			        }
			    });
			}
			
    		
    		$(function(){
    			var from = getQueryString("from");
    			var page = getQueryString("page");
    			var hrefUrl = "";
    			switch (page){
            		case "answer":
						var id = getQueryString("id");
						hrefUrl=apiUrl+"/jsp/fxf/share/answer.jsp?id="+id
						break;
					case "friendHomepage":
						var userId = getQueryString("userId");
						hrefUrl=apiUrl+"/jsp/fxf/share/answer.jsp?userId="+userId
						break;
					case "interlocutionDetail":
						var questionId = getQueryString("questionId");
						hrefUrl=apiUrl+"/jsp/fxf/share/interlocutionDetail.jsp?questionId="+questionId
						break;
					case "testDetail":
						var sclId = getQueryString("sclId");
						hrefUrl=apiUrl+"/jsp/fxf/share/testDetail.jsp?sclId="+sclId
						break;
					case "videoDetail":
						var id = getQueryString("id");
						hrefUrl=apiUrl+"/jsp/fxf/share/videoDetail.jsp?id="+id
						break;
					default:
						break;
				}
    			
    			var id = "";
    			var nickname = "";
    			var headImg = "";
    			if (from == "QQ") {
    				id = localStorage.getItem("fxfQQuserId");
    				nickname = localStorage.getItem("fxfQQnickName");
    				headImg = localStorage.getItem("fxfQQheadImg");
    			} else{
    				id = localStorage.getItem("fxfWXuserId");
    				nickname = localStorage.getItem("fxfWXnickName");
    				headImg = localStorage.getItem("fxfWXheadImg");
    			}
    			
    			$(".editInfo .nickName").text(nickname);
    			
    			$(".sexSel p").click(function(){
    				if ($(this).hasClass("male")) {
    					var img = $(".maleRole").find(".role_on img").attr("src");
    					$("#role").attr("src",img);
    					$(this).addClass("sex_on").siblings().removeClass("sex_on");
    					$(".roleSel").find(".maleRole").css("display","flex").siblings("ul").hide();
    				} else{
    					var img = $(".femaleRole").find(".role_on img").attr("src");
    					$("#role").attr("src",img);
    					$(this).addClass("sex_on").siblings().removeClass("sex_on");
    					$(".roleSel").find(".femaleRole").css("display","flex").siblings("ul").hide();
    				}
    			})
    			
    			$(".roleSel ul li").click(function(){
    				var num = $(this).index()+1;
    				$(this).addClass("role_on").siblings().removeClass("role_on");
    				if ($(this).parent().hasClass("maleRole")) {
    					$(this).siblings().each(function(){
    						var num2 = $(this).index()+1;
    						$(this).find("img").attr("src","images/m"+num2+".png");
    					})
    					$(this).find("img").attr("src","images/m"+num+"_on.png");
    					$("#role").attr("src","images/m"+num+"_on.png");
    				} else{
    					$(this).siblings().each(function(){
    						var num2 = $(this).index()+1;
    						$(this).find("img").attr("src","images/f"+num2+".png");
    					})
    					$(this).find("img").attr("src","images/f"+num+"_on.png");
    					$("#role").attr("src","images/f"+num+"_on.png");
    				}
    			})
    			
    			
//  			提交信息
				$(".infoSubmit").click(function(){
					var sex = $(".sex_on span").text();
					var role = "";
					if (sex == "男") {
						var roleIndex = $(".maleRole .role_on").index();
						if (roleIndex == 0) {
							role = "student";
						} else if (roleIndex == 1) {
							role = "parent";
						} else {
							role = "teacher";
						}
					} else {
						var roleIndex = $(".femaleRole .role_on").index();
						if (roleIndex == 0) {
							role = "student";
						} else if (roleIndex == 1) {
							role = "parent";
						} else {
							role = "teacher";
						}
					}
					
					selectRole(id,role,nickname,headImg,sex,from,hrefUrl);
				})
    		})
    	</script>
 	</body>
</html>