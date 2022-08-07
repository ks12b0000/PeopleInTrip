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
</head>
<body>
<form action="${contextPath}/mypage/member_delete_sc" method="get">
    <div id="certify_email_whole">
       <div id="certi_email_head">
           감사합니다
       </div>
       <div id="show_message">
			피플 인 트립을 이용해 주셔서 감사합니다.
       </div>
       <div id="buttons">
           <button class="buttons" onclick="window.close()">닫기</button>
       </div>
   </div>
</form>
</body>
</html>