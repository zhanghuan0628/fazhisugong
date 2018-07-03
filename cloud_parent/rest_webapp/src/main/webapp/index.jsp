<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/jsp/common/meta.jsp"%>
	    <title>第三方授权</title>
	    <%@include file="/jsp/common/css.jsp"%>
	</head>
	<body>
		用户授权
	</body>
		<%@include file="/jsp/common/js.jsp"%>
		<script type="text/javascript">
		    //var appid ="wx83b0015d4e272b79"; //睡眠 
			//var appid ="wx4759a5a2453c629a"; //遇见 gh_3f9b69573979
			var appid ="wx52c241bcd09bc71c"; //飞凡心理 gh_4b98efce033d
			$(function(){
				$.ajax({
			        //url: "${base}/user/snsapi_userinfo/"+appid, //非静默授权
			        url: "${base}/user/snsapi_base/"+appid, //静默授权
			        type: "post",
			        dataType: "json",
			        async:false,
			        success: function(data) {
			        	if(data.success){
			                if(data.code == "2000"){
			                	var data = data.data;
			                	var appId = data.appId;
			                	var scope = data.scope;
			                	var componentAppId = data.componentAppId;
			                	var state ="success.jsp";
			                	var baseUrl = "${base}";
			                	var redirectUri =encodeURI("http://"+window.location.host+"/third/user/redirect/"+appId+"/sgVideo", "UTF-8");
			                	//var redirectUri =encodeURI("http://pre-third.feifanxinli.com/third/user/redirect/"+appId+"/sgVideo", "UTF-8");
			                	var url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUri+"&response_type=code"+"&scope="+scope+"&state="+state+"&component_appid="+componentAppId+"#wechat_redirect";
								window.location.href=url;	
			                }else{

			                }
			            }
			        }
			    });	
			});

		</script>
</html>
