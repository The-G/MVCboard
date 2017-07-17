<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#speak').click(function(){
		$('#myaudio').stop();
		$('#myaudio').attr('src','../Speaker?&' + $('#myform').serialize()); // serialize(), 직렬화
		$('#myaudio').attr('autoplay', 'autoplay'); // serialize(), 직렬화
	});
});

</script>
</head>
<body>
	<form id="myform" method="post">
		<h1>text2speech sample example</h1>
		<textarea rows="7" , cols="40" name="statement">
Turn it up
Somebody save your soul 'cause you've been sinning in this city, I know
Too many troubles, all these lovers got you losing control
You're like a drug to me, a luxury, my sugar and gold
I want the good life, every good night you're a hard one to hold
		</textarea>
		<br /> <select name="voice">
			<c:forEach items="${voices}" var="voice">
				<option value="${voice.name}">${voice.language}: ${voice.description}</option>
			</c:forEach>
		</select> <br /> 
		<input type="button" id="speak" value="읽기" />
		<audio id="myaudio" controls="controls" preload="auto" />
	</form>
</body>
</html>