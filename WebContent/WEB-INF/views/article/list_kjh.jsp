<%@page import="article.models.ArticleDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl과 stadard .jar 넣어서 사용가능해짐!! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
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
	<table class="type08">
		<caption>게시물 리스트</caption>
		<thead>
			<tr>
				<th scope="cols">번호</th>
				<th scope="cols">제목</th>
				<th scope="cols">작성자</th>
				<th scope="cols">작성일</th>
				<th scope="cols">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td>${vo.no}</td>
					<td><a href="detail?no=${vo.no}">${vo.title}</a></td>
					<td>${vo.name}</td>
					<td>${vo.regdate}</td>
					<td>${vo.viewcount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5">page block</td>
			</tr>
		</tbody>
	</table>

	<tr>
		<td><a href="list">First</a></td>
		<td><c:choose>
				<c:when test="${startPage == 1}">
					       	Prev
					    </c:when>
				<c:otherwise>
					<a href="list?pg=${startPage-1}">Prev</a>
				</c:otherwise>
			</c:choose> <c:forEach var="page_num" begin="${startPage}" end="${endPage}">
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
		<td><a href="list?pg=${pageCount}">Last</a></td>
	</tr>

	<div style="margin-top: 20px">
		<a href="insert">글쓰기</a><br />
	</div>
</body>
</html>