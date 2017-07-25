<%@page import="article.models.ArticleDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- jstl과 stadard .jar 넣어서 사용가능해짐!! -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value="/resource/css/bootstrap.css" />">
<script src="<c:url value="/resource/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resource/js/bootstrap.js" />"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>

<style type="text/css">
table.type08 {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	border-left: 1px solid #ccc;
	margin: 20px 10px;
}

table.type08 thead th {
	padding: 10px;
	font-weight: bold;
	border-top: 1px solid #ccc;
	border-right: 1px solid #ccc;
	border-bottom: 2px solid #c00;
	background: #dcdcd1;
}

table.type08 tbody th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	background: #ececec;
}

table.type08 td {
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
}
</style>
</head>
<body>
	<div class="container">
		<table class="table">
			<h1 class="text-center">게시물 리스트</h1>
			<thead class="thead-inverse"> <!-- 이거 왜 안먹어?? -->
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="vo">
					<tr>
						<td>${vo.no}</td>
						<td><a href="detail?no=${vo.no}">${vo.title}</a></td>
						<td>${vo.name}</td>
						<td><fmt:formatDate value="${vo.regdate}" type="both"
								dateStyle="short" timeStyle="short" /></td>
						<td>${vo.viewcount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="center">
			${pageNation.display}<%-- <a href="list">First</a></td>
			<td><c:choose>
					<c:when test="${startPage == 1}">
						       	Prev
						    </c:when>
					<c:otherwise>
						<a href="list?pg=${startPage-1}">Prev</a>
					</c:otherwise>
				</c:choose> 
				<c:forEach var="page_num" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${pg == page_num}">
							<strong>${page_num} </strong>
						</c:when>
						<c:otherwise>
							<a href="list?pg=${page_num}">${page_num} </a>
						</c:otherwise>
					</c:choose>
				</c:forEach> <c:choose>
					<c:when test="${endPage == pageCount}">
						       	Next
						    </c:when>
					<c:otherwise>
						<a href="list?pg=${endPage+1}">Next</a>
					</c:otherwise>
				</c:choose></td>
			<td><a href="list?pg=${pageCount}">Last</a> --%>
		</div>
		<div align="right">
			<button type="button" class="btn btn-primary"><a href="insert" style="color:white"><strong>글쓰기</strong></a></button>			
		</div>
	</div>
</body>
</html>