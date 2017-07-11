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
<form action="updateAction" method="post">
<table>
	<caption>게시물 수정</caption>
<tr>
	<th>글 번호</th>
	<td>${articleVO.no}
		<input type="hidden" name="no" value="${articleVO.no}" />
	</td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="name" required="required" autofocus="autofocus" value="${articleVO.name}" /></td>
</tr>
<tr>
	<th>제목</th>
	<td><input type="text" name="title" required="required" value="${articleVO.title}" /></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea cols="40" rows="5" name="content" required="required" >${articleVO.content}</textarea></td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="pwd" /></td>
		<div style="color : red"> *처음 글 등록시 입력한 패스워드를 재 입력하세요!!*</div>
</tr>
</table>
<input type="submit" value="완료" />
</form>
<a href="list">리스트</a>
</body>
</html>