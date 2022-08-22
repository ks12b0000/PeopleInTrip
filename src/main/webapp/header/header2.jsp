<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<script type="text/javascript">
	$(document).ready(function() { 
		$(".submenuitem").hide()
	    $("div.menuitem").bind('click', function () {
	    	if ($(this).next().css('display') == 'none') {
	        	$('.submenuitem').slideUp();
			}
	        $(this).next().slideDown()
		})
	}) 
	
	$(function(){
  $('#searchBtn').click(function() {
	  var result = $('#selectBox option:selected').val();
   if(result == "j"){
	  location.href = "${contextPath}/tourist/tourist_PageList11?"
     + '${pageMaker.makeQuery(1)}'
     + "&searchType=j"
     + "&keyword="
     + encodeURIComponent($('#keywordInput').val());
   }if(result == "b"){
	   location.href = "${contextPath}/tourist/busanfestival_PageList11?"
		     + '${pageMaker.makeQuery(1)}'
		     + "&searchType=b"
		     + "&keyword="
		     + encodeURIComponent($('#keywordInput').val());
   }
    });
 });   
	</script>
 	<link rel="stylesheet" href="../resources/css/header/header2.css?ver123" />
    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet'>
</head>
<body>
    <header>
        <div class="logo">
            <a class="to_index" href="${contextPath}/mainpage/main">
                People in Trip&nbsp;&nbsp;&nbsp;
            </a>
        </div>

        <div class="container1">
            <div class="menu" style="z-index: 1">
                <div class="menuitem">
                    <p>관광지</p>&nbsp;&nbsp;&nbsp;
                </div>
                <ul class="submenuitem">
                    <li><a href="${contextPath}/tourist/busantravel_page">부산</a></li>
                    <li><a href="${contextPath}/tourist/travel_page">제주도</a></li>
                </ul>
            </div> 
            
            <div class="menu" style="z-index: 1">
                <div class="menuitem">
                    <p>커뮤니티</p>&nbsp;&nbsp;&nbsp;
                </div>
                <ul class="submenuitem">  
                    <li><a href="${contextPath}/board/community-info">정보게시판</a></li>
                    <li><a href="${contextPath}/board/community-acco">동행구해요</a></li>
                </ul>
            </div>
    </div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
		<div class="search">
 <select name="searchType" id="selectBox">
  <option value="j"<c:out value="${scri2.searchType eq 'j' ? 'selected' : ''}"/>>제주</option>
   <option value="b"<c:out value="${scri2.searchType eq 'b' ? 'selected' : ''}"/>>부산</option>
 </select>
 
 <input type="text" name="keyword" id="keywordInput" value="${scri2.keyword}"/>

 <button type="button" id="searchBtn">검색</button>
</div>		
		<c:choose>           
        	<c:when test="${isLogIn==null}">
		        <div class="container2">
		            <div class="login"><a href="${contextPath}/login_signup/login">로그인</a></div>
		            <div class="join"><a href="${contextPath}/login_signup/signup_input">회원가입</a></div>
		        </div>
        	</c:when>
        	<c:otherwise>
		        <div class="container2">
		        	<c:if test="${user.grade  == '일반회원' }">
        			<div class="login"><a href="${contextPath}/mypage/mypage_renewal?id=${user.id}">마이페이지</a></div>
        			</c:if>
        			<c:if test="${user.grade  == '관리자' }">
        			</div>
			<div class="container1">
				<div class="menu" style="z-index: 1">
					<div class="menuitem">
						<p>게시판 관리</p>
					</div>
					<ul class="submenuitem">
						<li><a href="${contextPath}/board/community-infomaster">정보게시판</a></li>
						<li><a href="${contextPath}/board/community-accomaster">동행구해요</a></li>
					</ul>
				</div>
			</div>
		</c:if>
        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="login"><a href="${contextPath}/logout">로그아웃</a></div>
        		</div>
        	</c:otherwise>
        </c:choose>
    </header>
</body>
</html>