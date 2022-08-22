<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="plist" value="${plist}" />
<c:set var="wlist" value="${wlist}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>제주도 메인페이지</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/tourist/travler.css">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0a9924a1f6188f938003ae8f12bf5ea6&libraries=services"></script>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
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
	
	function click_seoul() {
		document.getElementById("maps").src="${contextPath}/resources/images/tourist/map2.png";
	}
	
	function click_jeju() {
		document.getElementById("maps").src="${contextPath}/resources/images/tourist/map.png";
	}
	
	/*  */
	</script>
</head>
<body>

	<jsp:include page="/header/header.jsp" flush="false" />
	
	<!-- 헤더 -->
	<header>
		<div class="title">
			<h2 style="display: inline;"><strong><a href="${contextPath}/tourist/travel_page">제주도</a></strong></h2>&nbsp;
			<h3 style="display: inline;">jeju</h3>
		</div>
		<nav class="tourismenu">
			<ul>
				<li><a href="${contextPath}/tourist/travel_page"><strong>홈</strong></a></li>
				<li><a href="${contextPath}/tourist/tourist_PageList"><strong>여행지</strong></a></li>
				<li><a href="${contextPath}/tourist/festival_PageList"><strong>축제</strong></a></li>
				<li><a href="${contextPath}/tourist/exhibition_PageList"><strong>전시관</strong></a></li>
			</ul>
		</nav>
	</header>
	<br/><br/><br/>
	
	<div class="slideshow-container" >
	<c:forEach var="mainlist" items="${mainlist}">
        <div class="mySlides fade">
            <a href="${contextPath}/tourist/tourist_View?contentsid=${mainlist.contentsid}"><img src="${mainlist.imgpath}" style="width: 100%;"></a>            
  		</div>
	</c:forEach>
        <div style="text-align: center">
            <span class="dot" onclick="currentSlide(0)"></span>
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
        </div>
	</div>
	<div id="table_div">
		<table id="weather_table" >
			<tr>
				<th><h3 style="text-align: center; margin-left: 70px; white-space: nowrap; font-size: 25px;">오늘의 날씨</h3></th>
			</tr>
			<tr>
				<td><h3 style="text-align: left; margin-left: 70px; white-space: nowrap; font-size: 18px;">제주시 아라동 기준</h3></td>							
			</tr>
			<tr>
				<td style="text-align: center;"><p style="margin-left:65px; margin-bottom: 15px;"><strong>${wlist.baseTime}시 기준</strong><p>
				<p style="font-size: 36px; position: absolute; margin-top: 5px; margin-left: 170px;"><strong>${wlist.TMP}º</strong></p>
				<c:if test="${wlist.SKY == 1}">
					<img src="${contextPath}/resources/images/tourist/맑음.png" class="weather" style="width: 140px; height: 140px; margin-left: 55px;" />
					<p style="font-size: 17px; margin-left:45px;"><strong>맑음</strong></p>
				</c:if>
				<c:if test="${wlist.SKY == 2}">
					<img src="${contextPath}/resources/images/tourist/구름조금.png" class="weather" style="width: 140px; height: 140px; margin-left: 55px;" />
					<p style="font-size: 17px; margin-left:45px;"><strong>구름조금</strong></p>
				</c:if>
				<c:if test="${wlist.SKY == 3}">
					<img src="${contextPath}/resources/images/tourist/구름많음.png" class="weather" style="width: 140px; height: 140px; margin-left: 55px;" />
					<p style="font-size: 17px; margin-left:45px;"><strong>구름많음</strong></p>
				</c:if>
				<c:if test="${wlist.SKY == 4}">
					<img src="${contextPath}/resources/images/tourist/흐림1.png" class="weather" style="width: 140px; height: 140px; margin-left: 55px;" />
					<p style="font-size: 17px; margin-left:45px;"><strong>흐림</strong></p>
				</c:if>
				<c:if test="${wlist.PTY == 1}">
					<img src="${contextPath}/resources/images/tourist/비.jpg" class="weather" style="width: 140px; height: 140px; margin-left: 55px;" />
					<p style="font-size: 17px; margin-left:45px;"><strong>비</strong></p>
				</c:if>
				<c:if test="${wlist.PTY == 3}">
					<img src="${contextPath}/resources/images/tourist/눈.jpg" class="weather" style="width: 140px; height: 140px; margin-left: 55px;" />
					<p style="font-size: 17px; margin-left:45px;"><strong>눈</strong></p>
				</c:if>
					<p style="font-size: 17px; margin-left:50px;"><strong>강수확률 ${wlist.POP}%</strong></p>
				</td>
				<td><div id="map" style="margin-bottom: 50px; height: 300px; margin-left: 100px;"></div></td>				
			</tr>
		</table>		
	</div>
	
</body>

<script type="text/javascript">
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 11 //지도의 레벨(확대, 축소 정도)
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴	
</script>
</html>