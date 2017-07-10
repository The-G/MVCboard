<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
	<form action="deleteAction" method="post">
		<table>
			<caption>게시물 삭제</caption>
			<tr>
				<th>글 번호</th>
				<td>${articleVO.no}<input type="hidden" name="no"
					value="${articleVO.no}" />
				</td>
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