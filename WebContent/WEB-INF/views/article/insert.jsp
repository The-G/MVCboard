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
	<form action="insertAction" method="post">
		<table>
			<caption>게시물 입력</caption>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="name" required="required"
					autofocus="autofocus" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" required="required" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="40" rows="5" name="content"
						required="required"></textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" /></td>
			</tr>
		</table>
		<input type="submit" value="완료" />
	</form>
</body>
</html>