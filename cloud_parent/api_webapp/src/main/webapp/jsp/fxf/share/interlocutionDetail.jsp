<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>问答详情-飞小凡</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${base}/jsp/fxf/share/css/global.css?v=2" />
	</head>
	<body style="padding-bottom: 6rem;">
		<div class="floor">
			<div class="flogo"><img src="${base}/jsp/fxf/share/images/108.png"></div>
			<div class="yanzi">最懂你的青少年心理交流社区</div>
			<a href="#" onclick="more()">下载App </a>
		</div>
		<div class="question">
			<div class="questionTags"></div>
			
			<h1 class="questionTitle"></h1>
			
			<h2 class="questionData"><span class="questionTime"></span><i></i><span class="questionName"></span></h2>
			<div class="questionContent">
				<p></p>
			</div>
			<div class="questionImg"></div>
			
			<div class="othersOpinion">
				<img src="${base}/jsp/fxf/share/images/opinion.png" />
				<img src="${base}/jsp/fxf/share/images/opinion.png" />
				<img src="${base}/jsp/fxf/share/images/opinion.png" /> 
				<img src="${base}/jsp/fxf/share/images/opinion.png" />
				<img src="${base}/jsp/fxf/share/images/opinion.png" />
				<span>15人有同感</span> 
				<span>15人已关注</span>
			</div>
			<div class="question-btn">
				<a class="sympathy-btn"><em>我有同感</em></a> <a class="followed-btn"><em>已关注</em></a>
			</div>
		</div>
	
		
		<div class="answerDiv" id="newest">
			<h1 class="al-title">最新回答 (<span></span>)</h1>
			<ul class="answerList">
        		<!--<li>
        			<div class="whoAnswer">
        				<img src=""/>
        				<p>奔跑的小绵羊</p>
        				<span>回答了</span>
        			</div>
        			<p class="ansCont">我爸妈天天晚上都特别晚回家，我一个人在家怕，是不知道和谁说，他们好像一点都不关心我在家怕，可是不知道和谁说，他们好像一点都不关心。</p>
        			<div class="ansData clearfix">
        				<p>#学习压力#</p>
        				<span class="ansPraise"><img src="images/cl-praise.png"/> 2</span>
        				<span class="ansGift"><img src="images/cl-gift.png"/> 2</span>
        			</div>
        		</li>-->
        	</ul>
		</div>
	
		<div class="wannaSay">
			<div>
				<input type="text" name="" id="" value="" placeholder="我想对ta说" />
			</div>
			<a>发送</a>
		</div>
		<script src="${base}/jsp/fxf/share/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${base}/jsp/fxf/share/js/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" ></script>
		<script src="${base}/jsp/fxf/share/js/index.js?v=1" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function getQuestionsAnswers(questionId){
				$.ajax({
					url : apiUrl+"/fxf_question/get_questions_answers",
					data : {questionId:questionId},
					async : false,
					success : function(data) {
						if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
								var question = data.question;
								var answers = data.answers;
								
								if (question.del == 1) {
									location.href="${base}/jsp/fxf/share/deleted.jsp"
								}
								
								var tag = "";
								if (question.tag.indexOf(",")!=-1) {
									var tagArr = question.tag.split(",");
									for (var i = 0;i < tagArr.length; i++) {
										tag += "<i class='questionTag'>"+tagArr[i]+"</i>";
									}
								} else {
									tag = "<i class='questionTag'>"+question.tag+"</i>";
								}
								
								$(".questionTags").html(tag);
								
								$(".questionTitle").text(question.title);
								
								var time = ""
		                		var differTime = new Date().getTime()-question.askTime;
		                		if (differTime<=86400000) {
		                			var hours = parseInt(differTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
									var minutes = parseInt(differTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
									
									if (hours!=0) {
										time = hours+"小时前";
			                		} else if (minutes!=0) {
			                			time = minutes+"分钟前";
			                		} else {
			                			time = "1分钟前"
			                		}
		                		} else {
		                			var obj = new Date(question.askTime);
			                		var oYear = obj.getFullYear();
			                		var oMonth = obj.getMonth()+1;
			                		var oDate = obj.getDate();
			                		var oHour = obj.getHours();
			                		var oMinute = obj.getMinutes();
			                		
			                		time = oYear+"."+getzf(oMonth)+"."+getzf(oDate)+" "+getzf(oHour)+":"+getzf(oMinute);
		                		}
								$(".questionTime").text(time);
								$(".questionName").text(question.nickName);
								
								
								
								if (question.content!=null) {
									$(".questionContent p").text(question.content);
									var rowNum = Math.round($(".questionContent p").height()/parseFloat($(".questionContent p").css('line-height')));
			                		if (rowNum>3) {
			                			$(".questionContent").addClass('qc-off').append("<a>展开更多</a>");
			                		}
								}
		                		
		                		var questionImg = "";
		                		if (question.img!=null) {
		                			questionImg = "<img src='"+question.img+"'/>";
		                		}
		                		
		                		$(".questionImg").html(questionImg);
								
								$("#qans").html(question.answers);
								var praiseList = question.praiseList;
								
								var phtml = "";
								
								$.each(praiseList, function(i,item) {
									if (i < 5) {
										var pheadImg = "";
										if (item.headImg!=null) {
											pheadImg = item.headImg
										} else {
											pheadImg = "${base}/jsp/fxf/share/images/anonymous.png"
										}
										phtml += "<img src='"+pheadImg+"'/>"
									}
								});
								$(".othersOpinion").html(phtml+"<span>"+question.praises+"人有同感</span><span>"+question.attentions+"人已关注</span>");
								
								$("#newest .al-title span").text(answers.length)
								var html = ""
								$.each(answers, function(i,item) {
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
					        			"<p class='ansCont'>"+item.content+"</p>"+
					        			"<div class='ansData clearfix'>"+
					        				"<p>#"+questionTag+"#</p>"+
					        				"<span class='ansPraise'><img src='${base}/jsp/fxf/share/images/cl-praise.png'/>"+item.praises+"</span>"+
					        				"<span class='ansGift'><img src='${base}/jsp/fxf/share/images/cl-gift.png'/>"+item.giftUsers+"</span>"+
					        			"</div>"+
					        		"</li>"
			                	});
								$("#newest ul").html(html);
								
								
								locationUrl = apiUrl+"/jsp/fxf/share/interlocutionDetail.jsp?questionId="+questionId;
				    			wxtitle = question.content;
								wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
								wxdesc = "飞小凡-00后的心事聚集地";
								wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
			                }
			        	} else {
			        		if (data.code=="5000") {
			        			location.href="${base}/jsp/fxf/share/deleted.jsp"
			        		}
			        	}
					}
				})
			}
			
			
//			精华回答
			function getBestAnswersByTag(questionId){
				$.ajax({
			        url: apiUrl+"/fxf_question/getBestAnswersByTag",
			        type: "post",
			        data: {questionId:questionId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	var html = "";
			                	
			                	if (data.length!=0) {
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
						        			"<p class='ansCont'>"+item.content+"</p>"+
						        			"<div class='ansData clearfix'>"+
						        				"<p>#"+questionTag+"#</p>"+
						        				"<span class='ansPraise'><img src='${base}/jsp/fxf/share/images/cl-praise.png'/>"+item.praises+"</span>"+
						        				"<span class='ansGift'><img src='${base}/jsp/fxf/share/images/cl-gift.png'/>"+item.giftUsers+"</span>"+
						        			"</div>"+
						        		"</li>"
				                	});
				                	
				                	$("#newest").before("<div class='answerDiv' id='elite'>"+
										"<h1 class='al-title'>精华回答 (<span>"+data.length+"</span>)</h1>"+
										"<ul class='answerList'>"+html+
										"</ul>"+
									"</div>");
			                	}
			                	
			                }else{
			                	
			                }
			            }
			        }
			    });
			}
			
			
			
	//		赞同
			function praise(userBaseId,questionId){
				$.ajax({
			        url: apiUrl+"/fxf_question/praiseQuestionOrNo",
			        type: "post",
			        data: {userBaseId:userBaseId,questionId:questionId},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	getQuestionsAnswers(questionId)
			                }else{
			                	
			                }
			            } else {
		            		toast(data.message)
			            }
			        }
			    });
			}
			
			
//			回答
			function saveAnswer(userBaseId,questionId,content){
				$.ajax({
			        url: apiUrl+"/fxf_question/save_answer",
			        type: "post",
			        data: {userBaseId:userBaseId,questionId:questionId,content:content,see:"1",doing:"0",type:"1"},
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	getQuestionsAnswers(questionId);
			                	$(".wannaSay input").val("");
			                }else{
			                	
			                }
			            } else {
		            		toast(data.message)
			            }
			        }
			    });
			}
			
			
	
			function aa(th) {
				var txt = $(this).parent().siblings("input[name='txt']").val();
				console.log(txt)
				$(this).parent().text(txt);
			}
			
			
			function formatImg(src) {
				src = src + "";
				if (src.indexOf("http") == -1) {
					return "../resources/fxf/headImg/" + src + ".png"
				} else {
					return src;
				}
	
			}
	
			function formatData(date1) {
				var date = new Date(date1)
				return date.getFullYear() + "." + (date.getMonth() + 1) + "."
						+ date.getDate() + " " + date.getHours() + ":"
						+ date.getMinutes();
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
			
			$(function() {
				
				var questionId = "";
				var Url = location.search;
				if (Url.indexOf("%3F")!=-1) {
					var pos = Url.indexOf("%3F");
					var str = Url.substr(pos+3);
					questionId = str.split("%3D")[1];
				} else {
					questionId = getQueryString("questionId")
				}
				
				getQuestionsAnswers(questionId);
				getBestAnswersByTag(questionId);
				
				var userId = "";
				
				$(".wannaSay input").focus(function(e){
				    var winobj = $(window),
				        scope = this,
				        agent = navigator.userAgent.toLowerCase();
				    setTimeout(function () {
				        if (agent.indexOf('safari') != -1 && agent.indexOf('mqqbrowser') == -1
				            && agent.indexOf('coast') == -1 && agent.indexOf('android') == -1
				            && agent.indexOf('linux') == -1 && agent.indexOf('firefox') == -1) {//safra浏览器
				            window.scrollTo(0, 1000000);//先滚动到最底部，再用scrollY得到当前的值，必须延迟 否则拿到的就是1000000
				            setTimeout(function(){
				                window.scrollTo(0, window.scrollY - 45);//45像素 所有浏览器都是这么高
				            }, 50)
				        } else {//其他浏览器
				            window.scrollTo(0, 1000000);
				            // window.scrollTo(0, ++this.scrollNum);
				        }
				    }, 200);
				})
				
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
							wxAuthorization("/fxf/share/interlocutionDetail.jsp?questionId="+questionId);
						}
					}
					
					$(".sympathy-btn").click(function(){
						if (role != null && role != "") {
							praise(userId,questionId);
						} else {
							location.href="${base}/jsp/fxf/share/interlocutionDetail.jsp?from=WX&page=interlocutionDetail&questionId="+questionId
						}
						
					})
					
	//				回答
					$(".wannaSay a").click(function(){
						var content = $(".wannaSay input").val();
						if (content.trim()==""){
							return false;
						}
						saveAnswer(userId,questionId,content);
					})					
				} else {
					if (localStorage.getItem("fxfQQuserId")==null) {
						$(".sympathy-btn,.wannaSay a").click(function(){
							QC.Login.showPopup({
								appId:"101391991",
								redirectURI:apiUrl+"/jsp/fxf/share/callBack.html?page=interlocutionDetail&questionId="+questionId
							});
						})
						
					} else{
						userId = localStorage.getItem("fxfQQuserId");
						role = localStorage.getItem("fxfQQrole");
						
						$(".sympathy-btn").click(function(){
							if (role != null) {
								praise(userId,questionId);
							} else {
								location.href="${base}/jsp/fxf/share/interlocutionDetail.jsp?from=QQ&page=interlocutionDetail&questionId="+questionId
							}
							
						})
		//				回答
						$(".wannaSay a").click(function(){
							var content = $(".wannaSay input").val();
							if (content.trim()==""){
								return false;
							}
							saveAnswer(userId,questionId,content);
						})
					}
				}
				
				
//			    展开收起回答
				$(".questionContent").on("click","a",function(){
					if ($(this).parent().hasClass("qc-off")) {
						$(this).text("收起").parent().removeClass("qc-off");
					} else {
						$(this).text("展开更多").parent().addClass("qc-off");
					}
				});
				
				
				
//				跳转回答详情
				$(".answerDiv").on("click","li",function(){
					var answerId = $(this).data("id");
					location.href="${base}/jsp/fxf/share/answer.jsp?id="+answerId;
				})
				
			})
			
		</script>
	</body>
</html>
