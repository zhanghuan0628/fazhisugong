<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>好友主页-飞小凡</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link rel="stylesheet" type="text/css" href="${base}/jsp/fxf/share/css/global.css" />
</head>
<body style="padding-bottom: 5rem;">
	<div id="layer"></div>
	
	<div class="floor">
		<!--<div class="fclose"><img src="../resources/fxf/news-cou-icon1.png"></div>-->
		<div class="flogo"><img src="${base}/jsp/fxf/share/images/108.png"></div>
		<div class="yanzi">最懂你的青少年心理交流社区</div>
		<a href="#" onclick="more()">下载App </a>
	</div>
	<div class="personal-info">
		<!--<p class="tag" id="role"></p>-->
		<div class="head-img">
			<img src="" id="headImg" />
		</div>
		<p class="nickName" id="nickname"></p>
		<p class="data-info">
			<span>关注 <i id="attens"></i><em>|</em></span>
			<span>粉丝 <i id="fans"></i><em>|</em></span>
			<span>获赞 <i id="answerPraise"></i></span>
		</p>
	</div>
	<ul class="other-info">
		<li>
			<p id="magicCards"></p> <span>魔卡</span>
		</li>
		<li>
			<p id="answers"></p> <span>问答</span>
		</li>
		<li>
			<p id="topics"></p> <span>圈子</span>
		</li>
		<li>
			<p id="beans"></p> <span>我的凡豆</span>
		</li>
	</ul>
	<div class="dynamic">
		<h1 class="title">TA的动态</h1>
		<ul id="dynamicUl">
			<!--<li>
				<div class="dynamicUser">
					<div>
						<img src="images/userhead.png" />
						<p>
							奔跑的小绵羊<span>关注了</span>
						</p>
					</div>
				</div>
				<div class="dynamicContent">
					<div>
						<img src="images/108.png" />
					</div>
					<p>我爸妈天天晚上都特别晚回家，我一个人在家有点害怕，可是不知道和谁说，他们好像一点都不关心我。</p>
				</div>
				<div class="dynamicData clearfix">
					<p>2017.06.29</p>
					<div>
						<a class="sympathy">同感<em>15</em></a> <a class="answer">回答<em>99+</em></a>
					</div>
				</div>
			</li>

			<li>
				<div class="dynamicUser">
					<div>
						<img src="images/userhead.png" />
						<p>
							奔跑的小绵羊<span>关注了</span>
						</p>
					</div>
				</div>
				<div class="dynamicContent">
					<p>我爸妈天天晚上都特别晚回家，我一个人在家有点害怕，可是不知道和谁说，他们好像一点都不关心我。</p>
				</div>
				<div class="dynamicData clearfix">
					<p>2017.06.29</p>
					<div>
						<a class="sympathy">同感<em>15</em></a> <a class="answer">回答<em>99+</em></a>
					</div>
				</div>
			</li>

			<li>
				<div class="dynamicUser">
					<div>
						<img src="images/userhead.png" />
						<p>
							奔跑的小绵羊<span>关注了</span>
						</p>
					</div>
				</div>
				<div class="dynamicContent">
					<p>我爸妈天天晚上都特别晚回家，我一个人在家有点害怕，可是不知道和谁说，他们好像一点都不关心我。</p>
				</div>
				<div class="dynamicAnswer">
					回答回答回答回答回答回答回答回答回答回答回回回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答。</div>
				<div class="dynamicData clearfix">
					<p>2017.06.29</p>
					<div>
						<a class="sympathy">同感<em>15</em></a> <a class="answer">回答<em>99+</em></a>
					</div>
				</div>
			</li>

			<li class="follow">
				<div class="dynamicUser">
					<div>
						<img src="images/userhead.png" />
						<p>
							奔跑的小绵羊<span>关注了</span>
						</p>
					</div>
					<p>2017.06.29</p>
				</div>
				<div class="followFrame">
					<div class="followUser">
						<img src="images/followuser.png" />
						<p>小绵羊</p>
						<a>已关注</a>
					</div>
				</div>
			</li>-->
		</ul>

		<p class="pullDown">已显示全部内容</p>
	</div>

	<div class="operation-btn">
		<a class="gifts"><em>送礼物</em></a>
		<a class="followHim" onclick="more()"><em>关注TA</em></a>
	</div>
	
	
	<div class="present">
		<div class="presentTitile">
			<h1>送礼物给 <span></span></h1>
			<em></em>
		</div>
		<ul class="presentList">
		</ul>
		<div class="presentNum">
			<label>礼物数量</label>
			<div class="numCase">
				<span class="minus">-</span>
				<p>1</p>
				<span class="plus">+</span>
			</div>
			
			<p class="presentResult">=2凡豆</p>
		</div>
		<button class="sendPresent">赠送</button>
	</div>
	
	<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
	<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
	<script src="${base}/jsp/fxf/share/js/jquery.min.js"></script>
	<script src="${base}/jsp/fxf/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
	<script src="${base}/jsp/fxf/share/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		
		function getUserHomeInfo(userId){
			$.ajax({
				url : apiUrl+"/fxf_share/getUserHomeInfo",
				data : {userId:userId},
				async : false,
				success : function(res) {
					var data = res.data;
					nickname = data.nickName;
					headImg = formatImg(data.headImg);
					$("#answerPraise").html(data.answerPraise);
					$("#answers").html(data.answers);
					$("#attens").html(data.attens);
					$("#beans").html(data.beans);
					$("#fans").html(data.fans);
//					$("#questionPraise").html(data.questionPraise);
					$("#magicCards").html(data.magics);
					$("#topics").html(data.topics);
					$("#headImg").attr("src", formatImg(data.headImg));
					$("#nickname").html(data.nickName);
//					$("#role").html(formatRole(data.role));
					
					locationUrl = apiUrl+"/jsp/fxf/share/friendHomepage.jsp?userId="+userId,
					wxtitle = data.nickName+"-飞小凡玩家";
					wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
					wxdesc = "获"+data.answerPraise+"个赞，"+data.questionPraise+"个同感"
					wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
					
					$(".presentTitile h1 span").text(data.nickName);
				}
			})
		}
		
			
		function getTread(userId){
			$.ajax({
				url : apiUrl+"/fxf_share/getTread",
				data : {userId:userId},
				async : false,
				success : function(res) {
					console.log(res)
					var datas = res.data;
					var html = ""
					for (var i = 0; i < datas.length; i++) {
						var data = datas[i];
						console.log(data.type)
						if (data.type == "question") {
							html = html
									+ '<li><div class="dynamicUser"><div><img src="'+headImg+'" />'
									+ '<p>'
									+ nickname
									+ '<span>提问了</span></p></div></div><div class="dynamicContent">';
							if (data.img != null) {
								html = html
										+ '<div><img src="'+data.img.split(",")[0]+'" /></div>';
							}
	
							html = html
									+ '<p>'
									+ formatContent(data.content)
									+ '</p>'
									+ '</div><div class="dynamicData clearfix"><p>'
									+ data.adate.split(" ")[0]+ '</p><div>'
									+ '<a class="sympathy">同感<em>'
									+ data.pras
									+ '</em></a> <a class="answer">回答<em>'
									+ formatAns(data.ans) + '</em></a>'
									+ '</div></div></li>'
						} else if (data.type == "quesPraise") {
							html = html
									+ '<li><div class="dynamicUser"><div><img src="'+headImg+'" />'
									+ '<p>'
									+ nickname
									+ '<span>同感了</span></p></div></div><div class="dynamicContent">';
							if (data.img != null) {
								html = html
										+ '<div><img src="'+data.img.split(",")[0]+'" /></div>';
							}
	
							html = html
									+ '<p>'
									+ formatContent(data.content)
									+ '</p>'
									+ '</div><div class="dynamicData clearfix"><p>'
									+ data.adate.split(" ")[0] + '</p><div>'
									+ '<a class="sympathy">同感<em>'
									+ data.pras
									+ '</em></a> <a class="answer">回答<em>'
									+ formatAns(data.ans) + '</em></a>'
									+ '</div></div></li>'
						} else if (data.type == "praise") {
							html = html
									+ '<li><div class="dynamicUser"><div><img src="'+headImg+'" />'
									+ '<p>'
									+ nickname
									+ '<span>同感了</span></p></div></div><div class="dynamicContent">';
							if (data.img != null) {
								html = html
										+ '<div><img src="'+data.img.split(",")[0]+'" /></div>';
							}
	
							html = html
									+ '<p>'
									+ formatContent(data.content)
									+ '</p></div><div class="dynamicAnswer">'
									+ formatContent(data.answerContent)
									+ '</div>'
									+ '<div class="dynamicData clearfix"><p>'
									+ data.adate.split(" ")[0] + '</p><div>'
									+ '<a class="sympathy">同感<em>'
									+ data.pras
									+ '</em></a> <a class="answer">回答<em>'
									+ formatAns(data.ans) + '</em></a>'
									+ '</div></div></li>'
						} else if (data.type == "attention") {
							if(data.ans != undefined){
								var dheadImgs = data.ans.split(",");
								var dpras = data.pras.split(",");
							}
							
	// 								for(var x = 0 ; x<dheadImgs.length;x++){
								html = html
								+ '<li class="follow"><div class="dynamicUser"><div>'
								+ '<img src="'+headImg+'" /><p>'
								+ nickname
								+ '<span>关注了</span>'
								+ '</p></div><p>'
								+ data.adate.split(" ")[0]
								+ '</p></div><div class="followFrame"><div class="followUser">'
								+ '<img src="'
								+ formatImg(dheadImgs[0]) + '" /><p>'
								+ dpras[0]
								+ '</p><a>已关注</a></div></div></li>'
	// 								}
							
						}
	
					}
					$("#dynamicUl").html(html);
				}
			})
		}
		
		
		
//		礼物列表
		function geGifts(){
			$.ajax({
		        url: apiUrl+"/fxf_question/geGifts",
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
		                		html += "<li data-presentid = '"+item.id+"'>"+
		                			"<div class='presentPic'>"+
										"<img src='"+item.img+"'/>"+
									"</div>"+
									"<p><img src='images/beans.png'/> <span>"+item.beans+"</span></p>"+
									"<h2>"+item.name+"</h2>"+
								"</li>"
		                	});
		                	
		                	$(".present .presentList").html(html);
		                	
		                	$(".present .presentList li").eq(0).addClass("present_sel");
		                }else{
		                	
		                }
		            }
		        }
		    });
		}

//		赠送礼物
		function sendGift(userId,userGiftId,giftName,giftImg,quantity,beans){
			$.ajax({
		        url: apiUrl+"/fxf_own/sendGift",
		        type: "post",
		        data: {userId:userId,userGiftId:userGiftId,giftName:giftName,giftImg:giftImg,quantity:quantity,beans:beans},
		        dataType: "json",
		        async:false,
		        success: function(data) {
		        	if(data.success){
		                if(data.code == "2000"){
		                	var data = data.data;
		                	toast("赠送成功！")
		                }else{
		                	toast(data.message)
		                }
		            } else {
		            	if(data.code == "5000"){
		            		toast(data.message)
		                } else {
		               		toast(data.message)
		                }
		            }
		        }
		    });
		}
		

		function formatImg(src) {
			src = src + "";
			if (src.indexOf("http") == -1) {
				return "../resources/fxf/headImg/" + src + ".png"
			} else {
				return src;
			}

		}

		function formatAns(ans) {
			ans = parseInt(ans)
			if (ans > 99) {
				return "99+";
			} else if (ans > 999) {
				return "999+"
			} else {
				return ans
			}
		}

		function formatContent(content) {
			content = content + "";
			if (content.length > 44)
				return content.substr(0, 44) + "...";
			else {
				return content;
			}
		}

//		function formatRole(role) {
//			if (role == 'student') {
//				return "学生"
//			} else if (role == 'teacher') {
//				return "教师";
//			} else if (role == 'parent') {
//				return "家长";
//			} else if (role == 'counselor') {
//				return "咨询师";
//			}
//
//		}
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
		var nickname = '';
		var headImg = '';
		
		var presentNum = 1;
		var beans = 2;
		
		$(function(){
			
			var id = "";
			var Url = location.search;
			if (Url.indexOf("%3F")!=-1) {
				var pos = Url.indexOf("%3F");
				var str = Url.substr(pos+3);
				id = str.split("%3D")[1];
			} else {
				id = getQueryString('userId');
			}
			
			
			getUserHomeInfo(id);
			getTread(id);
			geGifts();
			
			
//			授权
			var userId = "";
			if (isWeChat()) {
				var oauth = "${oauth}";
				var headUrl = '';
				var nickName = '';
				var role = '';
				if (oauth == 'true') {
					userId = "${user.id}";
					headUrl = "${user.headImg}";
					nickName = "${user.username}";
					role = "${user.role}";
					localStorage.setItem("fxfWXuserId",userId);
					localStorage.setItem("fxfWXheadImg",headUrl);
					localStorage.setItem("fxfWXnickName",nickName);
					if (role!=null) {
						localStorage.setItem("fxfWXrole",role);
					}
					console.log("授权成功："+nickName);
				} else {
					if (localStorage.getItem("fxfWXuserId") != null &&localStorage.getItem("fxfWXuserId") != "") {
						userId = localStorage.getItem("fxfWXuserId");
						headUrl = localStorage.getItem("fxfWXheadImg");
						nickName = localStorage.getItem("fxfWXnickName");
						role = localStorage.getItem("fxfWXrole");
					} else {
						wxAuthorization("/fxf/share/friendHomepage.jsp?userId="+id);
					}
				}
				
				$(".gifts").click(function(){
					if (role != null && role != "") {
						$("#layer").show();
						$(".present").animate({"bottom":0});
					} else {
						location.href="${base}/jsp/fxf/share/editInfo.jsp?from=WX&page=friendHomepage&userId="+id
					}
				})
				
				
			} else{
				if (localStorage.getItem("fxfQQuserId")==null) {
					$(".gifts").click(function(){
						QC.Login.showPopup({
							appId:"101391991",
							redirectURI:apiUrl+"/jsp/fxf/share/callBack.html?page=friendHomepage&userId="+id
						});
					})
				} else{
					userId = localStorage.getItem("fxfQQuserId");
					role = localStorage.getItem("fxfQQrole");
//					礼物
					$(".gifts").click(function(){
						if (role != null) {
							$("#layer").show();
							$(".present").animate({"bottom":0});
						} else {
							location.href="${base}/jsp/fxf/share/editInfo.jsp?from=QQ&page=friendHomepage&userId="+id
						}
					})
				}
			}
			
			
			$(".presentTitile em,#layer").click(function(){
				$("#layer").hide();
				$(".present").animate({"bottom":"-46.6rem"});
			})
			
//			选择礼物
			$(".presentList").on("click","li",function(){
				$(this).addClass("present_sel").siblings().removeClass("present_sel");
				beans = parseInt($(this).find("p span").text());
				$(".presentResult").text("="+presentNum*beans+"凡豆");
			})
			
//			礼物数量加减
			$(".plus").click(function(){
				++presentNum;
				$(".numCase p").text(presentNum);
				$(".presentResult").text("="+presentNum*beans+"凡豆");
			})
			
			$(".minus").click(function(){
				if (presentNum==1) {
					return false;
				}
				--presentNum;
				$(".numCase p").text(presentNum);
				$(".presentResult").text("="+presentNum*beans+"凡豆");
			})
			
			
//			赠送礼物
			$(".sendPresent").click(function(){
				var giftName = $(".present_sel").find("h2").text();
				var giftImg = $(".present_sel").find(".presentPic img").attr("src");
				sendGift(userId,id,giftName,giftImg,presentNum,beans);
			})
			
			
		})
	</script>
</body>
</html>
