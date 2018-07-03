<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<title>文章详情-飞小凡</title>
	<link rel="stylesheet" href="css/index.css"/>
</head>
<body>
	<div class="floor">
		<!--<div class="fclose"><img src="images/news-cou-icon1.png"></div>-->
		<div class="flogo"><img src="images/108.png"></div>
		<div class="yanzi">最懂你的青少年心理交流社区</div>
		<a href="#" onclick="more()">下载App </a>
	</div>
	<div class="content_div">
		<div class="atile">人之所以成功，在于懂得适当的贪心</div>
		<div class="arddd">
			<span id="riqq">2017-12-15</span><span>作者:</span><span id="author"></span><span><img class="gks" src="images/yds.png">阅读数：</span><span id="pagev"></span>
		</div>
	</div>
	<div class="content_div">
		<div id="contentt" class="contentt">
		</div>
	</div>
	<div class="counselor"  onclick="showDetail()">
		<div class="ched">
			<img id="counselorSrc" src="images/headurl.png">
		</div>
		<div class="ccont">
			<div><span class="ccname">周吉</span><span id="job"></span></div>
			<div class="cbitq"><span>亲自焦炉</span><span>亲自焦炉</span></div>
		</div>
		<div class="abst">咨询师介绍</div>
	</div>
	
	<div class="content_div">
		<div class="pinlun" style="border-left-color:#fdf187">精彩评论</div>
		<div id="ans">
			<div class="answeror">
		        <div class="aor">
		            <img src="images/headurl.png">
		        </div>
		        <div class="adate">
		            <div>花开半夏</div>
		            <div>2017.5</div>
		        </div>
		        <div class="xzz">
		            <img src="images/xzan.png" class="i1">
		            <span  class="i2">123</span>
		            <img src="images/xxin.png"  class="i3">
		            <span class="i4">123</span>
		        </div>
		        
		    </div>
		    <div class="aconts">
		            从你的全世界路过
		    </div>
		    <div class="beansc">
		        <div><span>楼主</span>回复<span>花开_半夏</span>：只要最后是你，就好。</div>
		        <div><span>楼主</span>回复<span>花开_半夏</span>：只要最后是你，就好。</div>
		    </div>
		</div>
		<div class="more" onclick="more()"><a href="#" >更多</a></div>
	</div>
	<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="https://qzonestyle.gtimg.cn/qzone/qzact/common/share/share.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script src="js/fastclick.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="js/index.js?v=1"></script>
	<script type="text/javascript">
		document.documentElement.style.fontSize = window.innerWidth/7.5 + 'px';
	
		function getInfos(id){
			$.ajax({
				url : apiUrl+"/fxf_share/get_infos",
				data : {id:id},
				async : false,
				success : function(res) {
					var data = eval(res.data);
					var info = data.info; //文章详情
					var answerList = data.answerList;//评论
					var counselor = data.counselor;//咨询师
					
					if (counselor!=null) {
						$(".counselor").show();
						counselorId = counselor.id;
						//咨询师头像
						$("#counselorSrc").attr('src',counselor.headUrl); 
						$("#job").html(counselor.jobName);
						$(".ccname").html(counselor.name);
						var skillHtml='';   
						counselor.skillNameList.forEach(function(skillName,index){
							if(index <3){
								skillHtml+='<span>'+skillName+'</span>'
							}		
						})
						$(".cbitq").html(skillHtml);
					}
					
					
					$(".atile").html(info.title); //文章标题
					$("#author").html(info.author); //作者
					$("#riqq").html(formatData(info.createDate));
					$('#pagev').html(info.pageView) //阅读数
					$(".contentt").html(info.content);
					
					
					//评论及回答
					var answers='';
						
					$.each(answerList, function(i,item) {
						var replyList = item.childList;
					  	var reply = "";
					  	$.each(replyList, function(j,point) {
					  		reply += "<div><span>"+point.nickName+"</span>回复<span>"+point.dotoName+"</span>："+point.content+"</div>"
					  	});
					  	
					  	var replyH = "";
					  	if (replyList.length>0) {
					  		replyH = "<div class='beansc'>"+reply+"</div>";
					  	}
					  	
					  	var headImg = "";
					  	if (item.headImg!=null||item.headImg!="") {
					  		headImg=item.headImg
					  	} else{
					  		headImg="http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/roleHead/default.png";
					  	}
					  	
						answers += "<div class='answeror'>"+
							"<div class='aor'>"+
								"<img src='"+headImg+"'>"+
							"</div>"+
							"<div class='adate'>"+
								"<div>"+item.nickName+"</div>"+
								"<div>"+formatData(item.answerDate)+"</div>"+
							"</div>"+
							"<div class='xzz'>"+
								"<img src='images/xzan.png' class='i1'>"+
								"<span class='i2'>"+item.praises+"</span>"+
								"<img src='images/xxin.png'  class='i3'>"+
								"<span class='i4'>"+item.childList.length+"</span>"+
							"</div>"+
						"</div>"+
						"<div class='aconts'>"+item.content+"</div>"+replyH;
					});
			
					$("#ans").html(answers);
					
					locationUrl = apiUrl+"/jsp/fxf/share/article.jsp?id="+id,
					wxtitle = info.title;
					if ($("#contentt img").length!=0) {
						wximgUrl = $("#contentt img").eq(0).attr("src");
					} else{
						wximgUrl = "http://ffxl.oss-cn-shanghai.aliyuncs.com/fxf/app/108.png";
					}
					var reTag = /<(?:.|\s)*?>/g;
					wxdesc = info.content.replace(reTag,"").replace(/&nbsp;/ig,"");
					wxShare(locationUrl,wxtitle,wximgUrl,wxdesc);
				}
			})
		}
	
			
	
	
		function formatData(date1) {
			var date = new Date(date1)
			return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
					+ date.getDate();
		}
	
	
	//跳转咨询师主页
		function showDetail(){
			location.href = "counselorHomepage.jsp?id=" + counselorId;
		}
	 
	 
		var locationUrl = "";
		var wxtitle = "";
		var wximgUrl = "";
		var wxdesc = "";
		var counselorId ="";
		
		$(function(){
			var id = getQueryString('id');
			getInfos(id);
		})
	 
	
	
	</script>
</body>
</html>