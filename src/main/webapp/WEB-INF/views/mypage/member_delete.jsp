<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 탈퇴 팝업 창</title>
	<link rel="stylesheet" href="../resources/css/mypage/delete_member.css" />
	<link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		function delete_Btn() {
			var form = document.deleteMember;
			if(!form.delete_id.value) {
				alert("아이디를 입력해주세요");
				form.delete_id.focus();
				
			} else if (!form.delete_pw.value) {
				alert("비밀번호를 입력해주세요");
				form.delete_pw.focus();			
			} 		
		}
	</script>
</head>
<body>
	<div class="delete_form">
		<h1><a href="${contextPath}/mainpage/main" style="text-decoration: none; color:#9966ff">People in Trip</a></h1>
		<form action="${contextPath}/mypage/delteMember" name="deleteMember" method="post">
			<div>
				<input type="text" placeholder="아이디" id="delete_id" name="id" onfocus="this.placeholder='';" onblur="this.placeholder='아이디'" /><br>
				<input type="password" placeholder="비밀번호" id="delete_pw" name="pwd" onfocus="this.placeholder='';" onblur="this.placeholder='비밀번호'" /><br>
				<button type="submit" id="delete_bt" onclick="delete_Btn()"><strong>회원탈퇴</strong></button>
				<br/>
				<br/>
				<b>즐거운 여행 하셨나요? <br/> 피플 인 트립을 이용해 주셔서 감사합니다 여행의 설렘을 찾고 싶으시면 또 와주세요.</b>
			</div>
		</form>
		<div>
			<c:if test="${msg == false }">
				<span style="color:red">아이디 또는 비밀번호가 맞지 않습니다.</span>
			</c:if>
		</div>
	</div>
</body>
</html>