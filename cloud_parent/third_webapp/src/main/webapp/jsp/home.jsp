<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/jsp/common/meta.jsp"%>
	    <title>Hi测评</title>
	    <%@include file="/jsp/common/css.jsp"%>
	</head>
	<body>
		<a href="${url}">去授权吧</a>
	</body>
</html>
