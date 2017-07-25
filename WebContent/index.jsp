<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	response.sendRedirect("article/list");
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />  
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<ol>
	<li><a href="${context}/article/list">게시판</a></li>
	<li><a href="${context}/member/regist">회원가입</a></li>
	<li><a href="${context}/watson/text2speech">watson, text to speech</a></li>
</ol>
</body>
</html>