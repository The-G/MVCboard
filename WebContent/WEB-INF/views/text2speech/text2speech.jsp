<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value="/resource/css/bootstrap.css" />">
<script src="<c:url value="/resource/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resource/js/bootstrap.js" />"></script>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#speak').click(function() {
			$('#myaudio').stop();
			$('#myaudio').attr('src', '../Speaker?&' + $('#myform').serialize()); // serialize(), 직렬화
			$('#myaudio').attr('autoplay', 'autoplay'); // serialize(), 직렬화
		});
	});
</script>
</head>
<body>

	<form id="myform" method="post">
		<div class="container">
			<div class="jumbotron">
				<h2>text2speech sample example</h2>
				<br />
				<p>텍스트의 내용은 선택한 보이스의 언어와 일치해야 합니다. 혼합언어(예:영어 텍스트와 일본 여성음성)는 유효한
					결과를 생성하지 않습니다. 한글은 조만간 지원됩니다.</p>
			</div>

			<div class="row">
				<div class="col-lg-12 col-sm-12">
					<textarea rows="7" name="statement" class="form-control">
There's a lady who's sure
All that glitters is gold
And she's buying a stairway to heaven

When she gets there she knows
If the stores are all closed, with a word
she can get what she came for
Ooh ooh, and she's buying
a stairway to heaven

There's a sign on the wall
But she wants to be sure
Cause you know sometimes
words have two meanings

In a tree by the brook
There's a songbird who sings
Sometimes all of our thoughts
are misgiven

Ooh, it makes me wonder

There's a feeling I get
When I look to the west
And my spirit is crying for leaving

In my thoughts I have seen
rings of smoke through the trees
And the voices of those
who standing looking

Ooh, it makes me wonder
Ooh, it really makes me wonder

And it's whispered that soon
If we all call the tune
Then the piper will lead us to reason

And a new day will dawn
for those who stand long
And the forests will echo with laughter

If there's a bustle in your hedgerow
Don't be alarmed now
It's just a spring clean
for the May queen

Yes, there are two paths you can go by
But in the long run there's still time
to change the road you're on

And it makes me wonder

Your head is humming and it won't go
in case you don't know,
The piper's calling you to join him

Dear lady, can you hear the wind blow
And did you know, your stairway
lies on the whispering wind

And as we wind on down the road
Our shadows taller than our soul
There walks a lady we all know
who shines white light
and wants to show

How everything still turns to gold
And if you listen very hard
The tune will come to you at last
When all are one and one is all
to be a rock and not to roll

And she's buying a stairway to heaven
			</textarea>
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-lg-10 col-sm-10">
					<select name="voice" class="form-control">
						<c:forEach items="${voices}" var="voice">
							<option value="${voice.name}">${voice.language}:
								${voice.description}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2 col-sm-2">
					<input type="button" id="speak" value="읽기"
						class="btn btn-default btn-success btn-block" /><br />
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<audio id="myaudio" style="width: 100%;" controls="controls"
						preload="auto" />
				</div>
			</div>
		</div>
	</form>

</body>
</html>