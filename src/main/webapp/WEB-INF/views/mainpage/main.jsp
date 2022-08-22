<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" href="/favicon.ico" type="../WEB-INF/views/images/main/mainimg1.jpg">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="${contextPath}/resources/css/main/main.css">
<meta charset="UTF-8">
<title>People in Trip</title>
</head>
<body>
<form action="${contextPath}/mainpage/main" method="get">
	<header>
		<jsp:include page="/header/header.jsp" flush="false" />
	</header>
	
	<h3 id="title1">인기 여행지</h3>
	<div class="slideshow-container" >
	<c:forEach var="mainlist" items="${mainlist}">
        <div class="mySlides fade">
            <a href="${contextPath}/tourist/tourist_View?contentsid=${mainlist.contentsid}"><img src="${mainlist.imgpath}" style="width: 100%;"></a> 
        </div>
	</c:forEach>
	<c:forEach var="mainlist5" items="${mainlist5}">
        <div class="mySlides fade">
            <a href="${contextPath}/tourist/busantourist_View?UC_SEQ=${mainlist5.UC_SEQ}"><img src="${mainlist5.MAIN_IMG_NORMAL}" style="width: 100%;"></a>            
        </div>
	</c:forEach>
        <div style="text-align: center">
            <span class="dot" onclick="currentSlide(0)"></span>
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
            <span class="dot" onclick="currentSlide(4)"></span>
            <span class="dot" onclick="currentSlide(5)"></span>
            <span class="dot" onclick="currentSlide(6)"></span>
            <span class="dot" onclick="currentSlide(7)"></span>
        </div>
	</div>
	
	<br/>
	
	<h3 id="title2">인기 축제/전시/체험</h3>
	
	<table class="besttable" bgcolor="#f8f8f8" align="center" id="table1" >
		<tr style="border:#9966ff;">
			<td rowspan="2" width="50" height="30">
				<div class="prev"><img src="${contextPath}/resources/images/main/left.png" width="50" height="50"></div>
			</td>
			<td rowspan="3">	
                <div class="slide_wrapper">               
                    <ul class="slides">     
                     <c:forEach var="festivalmain" items="${festivalmain}">          
                        <li><a href="${contextPath}/tourist/tourist_View?contentsid=${festivalmain.contentsid}"><img id="bestImg1" src="${festivalmain.imgpath}" width="180" height="200"></a></li>                       
                     </c:forEach>
                     
                      <c:forEach var="festivalmain2" items="${festivalmain2}">          
                        <li><a href="${contextPath}/tourist/busanfestival_View?UC_SEQ=${festivalmain2.UC_SEQ}"><img id="bestImg1" src="${festivalmain2.MAIN_IMG_NORMAL}" width="180" height="200"></a></li>
                     </c:forEach>
                     
                     <c:forEach var="exhibitionmain" items="${exhibitionmain}">          
                        <li><a href="${contextPath}/tourist/tourist_View?contentsid=${exhibitionmain.contentsid}"><img id="bestImg1" src="${exhibitionmain.imgpath}" width="180" height="200"></a></li>
                     </c:forEach>
                     
                     <c:forEach var="experiencemain" items="${experiencemain}">          
                        <li><a href="${contextPath}/tourist/busanexperience_View?UC_SEQ=${experiencemain.UC_SEQ}"><img id="bestImg1" src="${experiencemain.MAIN_IMG_NORMAL}" width="180" height="200"></a></li>
                     </c:forEach>
                    </ul>            
                </div>
            </td>
			<td colspan="2" width="50" height="30">
				<div class="next"><img src="${contextPath}/resources/images/main/right.png" width="50" height="50"></div>
			</td>
		</tr>
	</table>
	
	<br/>
	
	<h3 id="title3">게시판</h3>
    <div class="boardmain" style="width: 1000px; margin-left: 30px;">
        <div class="titlefont board">

            <img src="${contextPath}/resources/images/main/Loudspeaker.png" width="30" height="30" align="left" style="padding-bottom: 5px;"/>
            <span class="main3">여행 정보 공유해요~</span>
            <span class="sub1"><a href="${contextPath}/board/community-info" style="text-decoration: none;">더보기 +</a></span>
            <div>
                <table class="tableboard">
                    <thead>
                        <tr>
                            <th style="width:80%">제목</th>
                            <th style="width:20%">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
	                    <c:forEach var="main1" items="${mainsList1}" begin="0" end="3">
	                    	<tr>
		                    	<td><a href="${contextPath}//board/community_detail2.do?post_num=${main1.post_num}" style="color:#9966ff; text-decoration: none;">${main1.post_title}</a></td>
		                    	<td>${main1.visitcount }</td>
	                    	</tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="titlefont board">
            <img src="${contextPath}/resources/images/main/people.png" width="30" height="30" align="left" style="padding-bottom: 5px; margin-left: 10px;"/>

            <span class="main4">동행구해요!</span>

            <span class="sub2"><a href="${contextPath}/board/community-acco" style="text-decoration: none;">더보기 +</a></span>
            <div>
                <table class="tableboard" style="margin-left: 5px;">
                    <thead>
                        <tr>
                            <th style="width:80%">제목</th>
                            <th style="width:20%">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="main" items="${mainsList}" begin="0" end="3">
                    	<tr>
	                    	<td><a href="${contextPath}/board/community_detail.do?post_num=${main.post_num}" style="color:#9966ff; text-decoration: none;">${main.post_title}</a></td>
	                    	<td>${main.visitcount }</td>
                    	</tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

	<script type="text/javascript" defer>
		var slideIndex = 0; //slide index
	
		// HTML 로드가 끝난 후 동작
		window.onload=function() {
			showSlides(slideIndex);

			// Auto Move Slide
			var sec = 3000;
			setInterval(function() {
				slideIndex++;
				showSlides(slideIndex);
			}, sec);
		}

		// Thumbnail image controls
		function currentSlide(n) {
			slideIndex = n;
			showSlides(slideIndex);
		}

		function showSlides(n) {
			var slides = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			var size = slides.length;

			if((n+1) > size) {
				slideIndex = 0; n = 0;
			} else if(n < 0) {
				slideIndex = (size-1);
				n = (size-1);
			}

			for(i = 0; i< slides.length; i++) {
				slides[i].style.display = "none";
			}

			for(i = 0; i< dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}

			slides[n].style.display = "block";
			dots[n].className += " active";
		}

		var slides = document.querySelector('.slides') ,
			slide = document.querySelectorAll('.slides li') ,
			currentIdx = 0 ,
			slideCount = slide.length ,
			slideWidth = 180 ,
			slideMargin = 10 ,
			prevBtn = document.querySelector('.prev') ,
			netxBtn = document.querySelector('.next');
	
			slides.style.width = (slideWidth + slideMargin) * slideCount - slideMargin + 'px';

			function moveSlide(num) {
				slides.style.left = -num * 190 + 'px';
				currentIdx = num;
			}
			netxBtn.addEventListener('click', function(){
				if(currentIdx < slideCount - 3) {
					moveSlide(currentIdx + 1);
				} else {
					moveSlide(0);
				}
			});

			prevBtn.addEventListener('click', function(){
				if(currentIdx > 0) {
					moveSlide(currentIdx - 1);
				} else {
					moveSlide(slideCount - 3);
				}
				
			});
	</script>
</form>
</body>
</html>