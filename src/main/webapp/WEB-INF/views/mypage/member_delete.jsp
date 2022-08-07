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
			let id = document.getElementById('delete_id').value;
			let pass = document.getElementById('delete_pw').value;
			if(id == "") {
				alert("아이디를 입력해주세요");
				document.deleteMember.pwd.focus();
				return false;
			} else if (pass == "") {
				alert("비밀번호를 입력해주세요");
				document.deleteMember.pwd.focus();
				return false;
			} else {
				alert("그동안 이용해 주셔서 감사합니다!");
			}
			
		}
	</script>
</head>
<body>
	<div class="delete_form">
		<h1>People in Trip</h1>
		<form action="${contextPath}/mypage/delteMember" name="deleteMember" method="post">
			<div>
				<input type="text" placeholder="아이디" id="delete_id" name="id" onfocus="this.placeholder='';" onblur="this.placeholder='아이디'" /><br>
				<input type="password" placeholder="비밀번호" id="delete_pw" name="pwd" onfocus="this.placeholder='';" onblur="this.placeholder='비밀번호'" /><br>
				<button type="submit" id="delete_bt" onclick="delete_Btn()"><strong>회원탈퇴</strong></button>
<%-- 				 <c:if test="${message == 'error'}">
				 <div style="color:red;"> 아이디 또는 비밀번호가 일치하지 않습니다.</div>
				</c:if> --%>
			</div>
		</form>
	</div>
</body>
</html>