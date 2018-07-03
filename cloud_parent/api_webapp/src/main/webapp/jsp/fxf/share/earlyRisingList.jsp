<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>早起排行榜-飞小凡</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
</head>
<body>
	<div class="floor">
		<!--<div class="fclose"><img src="../resources/fxf/news-cou-icon1.png"></div>-->
		<div class="flogo"><img src="images/108.png"></div>
		<div class="yanzi">最懂你的青少年心理交流社区</div>
		<a href="#" onclick="more()">下载App </a>
	</div>
	<div class="topThree">
		<div class="tt-second">
			<div>
				<img src="images/headimg.png" />
				<p>30天</p>
			</div>
			<span>累积坚持</span>
			<h2>犀牛快跑</h2>
			<p>上海第一中学</p>
		</div>

		<div class="tt-first">
			<div>
				<img src="images/headimg.png" />
				<p>35天</p>
			</div>
			<span>累积坚持</span>
			<h2>犀牛快跑</h2>
			<p>上海第一中学</p>
		</div>

		<div class="tt-third">
			<div>
				<img src="images/headimg.png" />
				<p>28天</p>
			</div>
			<span>累积坚持</span>
			<h2>犀牛快跑</h2>
			<p>上海第一中学</p>
		</div>
	</div>

	<ul class="rankingList">
		<!--<li class="clearfix">
			<div class="rl-left clearfix">
				<h1>No.4</h1>
				<img src="images/userhead.png" />
				<div>
					<p>犀牛快跑</p>
					<span>上海第一中学</span>
				</div>
			</div>
			<p class="rl-right">
				累积坚持<span>25</span>天
			</p>
		</li>

		<li class="clearfix">
			<div class="rl-left clearfix">
				<h1>No.5</h1>
				<img src="images/userhead.png" />
				<div>
					<p>犀牛快跑</p>
					<span>上海第一中学</span>
				</div>
			</div>
			<p class="rl-right">
				累积坚持<span>25</span>天
			</p>
		</li>

		<li class="clearfix">
			<div class="rl-left clearfix">
				<h1>No.6</h1>
				<img src="images/userhead.png" />
				<div>
					<p>犀牛快跑</p>
					<span>上海第一中学</span>
				</div>
			</div>
			<p class="rl-right">
				累积坚持<span>25</span>天
			</p>
		</li>

		<li class="clearfix">
			<div class="rl-left clearfix">
				<h1>No.7</h1>
				<img src="images/userhead.png" />
				<div>
					<p>犀牛快跑</p>
					<span>上海第一中学</span>
				</div>
			</div>
			<p class="rl-right">
				累积坚持<span>25</span>天
			</p>
		</li>

		<li class="clearfix">
			<div class="rl-left clearfix">
				<h1>No.8</h1>
				<img src="images/userhead.png" />
				<div>
					<p>犀牛快跑</p>
					<span>上海第一中学</span>
				</div>
			</div>
			<p class="rl-right">
				累积坚持<span>25</span>天
			</p>
		</li>-->
	</ul>
	<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		
		function viewSignUpRanking(){
			$.ajax({
				url : apiUrl+"/fxf_share/viewSignUpRanking",
				data : {},
				success : function(res) {
					var data = res.data;
					var nhtml = "";
					for(var i = 0; i <　data.length ; i++){
						var html = '<div><img src="'+formatImg(data[i].headImg)+'" /><p>'+data[i].signs+'天</p>'+
						'</div><span>累积坚持</span><h2>'+data[i].nickname+'</h2><p>'+data[i].school+'</p>'
						if(i == 0){
							$(".tt-first").html(html);
						}else if(i == 1){
							$(".tt-second").html(html);
						}else if(i == 2){
							$(".tt-third").html(html);
						}else{
							nhtml = nhtml + '<li class="clearfix"><div class="rl-left clearfix">'+
							'<h1>No.'+(i+1)+'</h1><img src="'+formatImg(data[i].headImg)+'" /><div><p>'+data[i].nickname+'</p><span>'+data[i].school+'</span></div>'+
							'</div><p class="rl-right">累积坚持<span>'+data[i].signs+'</span>天</p></li>';
							
						}
					}
					$(".rankingList").html(nhtml);
				}
			})
		}
		
		function formatImg(src){
			src = src+"";
			if(src.indexOf("http") == -1){
				return "../resources/fxf/headImg/"+src+".png"
			}else{
				return src;
			}
			
		}
		
		function formatData(date1) {
			var date = new Date(date1)
			return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
					+ date.getDate();
		}

		//跳转咨询师主页
		function showDetail() {
			location.href = "../jsp/treatment/shareCounselor/details.jsp?id="
					+ counselorId;
		}
		
		var locationUrl = "";
		var wxtitle = "";
		var wximgUrl = "";
		var wxdesc = "";
		var counselorId = "";
		
		$(function(){
			viewSignUpRanking();
			
			locationUrl = apiUrl+"/jsp/fxf/share/earlyRisingList.jsp",
			wxtitle = "早起排行榜-飞小凡";
			wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
			wxdesc = "";
			wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
		})

	</script>
</body>
</html>
