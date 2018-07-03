<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("base", request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>问题已删除</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/global.css?v=1"/>
    </head>
    <body style="background-color: #f5f5f5;position: relative;height: 100vh;">
    	<div class="deleted">
    		<img src="images/deleted.png"/>
    		<p>该问题已被删除</p>
    	</div>
 	</body>
</html>