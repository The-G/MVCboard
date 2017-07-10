<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<table>
	<caption>게시물 상세보기</caption>
<tr>
	<th>글번호</th>
	<td>${articleVO.no}</td>
</tr>
<tr>
	<th>작성자</th>
	<td>${articleVO.name}</td>
</tr>
<tr>
	<th>제목</th>
	<td>${articleVO.title}</td>
</tr>
<tr>
	<th>내용</th>
	<td>${articleVO.content}</td>
</tr>
<tr>
	<th>날짜</th>
	<td>${articleVO.regdate}</td>
</tr>
<tr>
	<th>조회수</th>
	<td>${articleVO.viewcount}</td>
</tr>
</table><br/>
<a href="list">리스트</a>
<a href="update?no=${articleVO.no}">수정</a>
<a href="delete?no=${articleVO.no}">삭제</a>
</form>
</body>
</html>