<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 탈퇴 팝업 창</title>
	<style type="text/css">
	    body {
	        width: 800px;
	        max-width: 80%;
	        height: 300px;
	        margin: -8% auto;
	    }
	
	    #certify_email_whole {
	        margin-top: 100px;
	        border: solid 3px #9966FF;
	        padding: 10px 5%;
	    }
	    
        #certi_email_head {
	        font-family: Impact;
	        color: #9966FF;
	        font-size: 2em;
	        font-weight: 700;
	        text-align: left;
	        border-bottom: solid 3px #9966FF;
	        padding-bottom: 0 px;
	        height: 50px;
	        margin: auto 0;
    	}
    	
   	    #show_message {
	        background-color: #faf5f5;
	        font-family: Arial;
	        font-size: 1.3em;
	        font-weight: normal;
	        margin: 20px 0;
	        padding-left: 20px;
	        padding-top: 20px;
	   }
	   
       #buttons {
	        text-align: right;
    	}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		function delete_Btn() {
			let close = 0;
			document.deleteMember.action="${contextPath}/mypage/delteMember?id=${user.id}";
			document.deleteMember.method="post";
			alert("회원 탈퇴가 완료되었습니다!");
			document.deleteMember.submit(); 
		}
	</script>
</head>
<body>
<form action="${contextPath}/mypage/delteMember" method="post" name="deleteMember"></form>
    <div id="certify_email_whole">
       <div id="certi_email_head">
           회원 탈퇴
       </div>
       <div id="show_message">
           탈퇴 시 계정 정보 및 개인정보가 파기되며 <br>
           복구 하실 수 없습니다. <br>
           탈퇴를 하실려면 확인을 누르시고 안하실거면 취소를 누르세요.
       </div>
       <div id="buttons">
       		<input type="button" name="delBtn" id="delBtn" class="buttons" onclick="delete_Btn()" value="확인">
       		<input type="hidden" name="id" value="${user.id}">
           <!-- <button name="delBtn" class="buttons" onclick="delete_Btn()">확인</button> -->
           <button class="buttons" onclick="window.close()">취소</button>
       </div>
   </div>
</body>
</html>