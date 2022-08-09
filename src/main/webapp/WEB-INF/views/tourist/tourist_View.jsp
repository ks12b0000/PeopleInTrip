<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="plist" value="${plist}" />
<c:set var="replyList" value="${replyList}" />
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0a9924a1f6188f938003ae8f12bf5ea6"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/tourist/tourist_View.css?ver=123"/>
	<script type="text/javascript" src="${contextPath}/resources/js/tourist/tourist_View.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<meta charset="UTF-8">
	<title>${plist.title} 상세페이지</title>
</head>
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg tr {text-align: center; border-bottom: 1px solid #eaeaea; border-top: 1px solid #eaeaea;}
.tg td{font-family:Arial, sans-serif;font-size:16px;
  overflow:hidden;padding:17px;}
.tg th{font-family:Arial, sans-serif;font-size:14px;  background-color:#f8f8f8;
  font-weight:normal;overflow:hidden;padding:17px 20px;word-break:normal; white-space: nowrap;}
</style>
<body>
	<jsp:include page="/header_lhj/header.jsp" flush="false" />
	
	<!-- 헤더 -->
	<header>
		<div class="title">
			<h2 style="display: inline;"><strong><a href="${contextPath}/tourist/travel_page_kms">제주도</a></strong></h2>&nbsp;
			<h3 style="display: inline;">jeju</h3>
		</div>
		<nav class="tourismenu">
			<ul>
				<li><a href="${contextPath}/tourist/travel_page_kms"><strong>홈</strong></a></li>
				<li><a href="${contextPath}/tourist/tourist_PageList"><strong>여행지</strong></a></li>
				<li><a href="${contextPath}/tourist/festival_PageList"><strong>축제</strong></a></li>
				<li><a href="${contextPath}/tourist/exhibition_PageList"><strong>전시관</strong></a></li>
			</ul>
		</nav>
	</header>
	<br/><br/><br/>

	<!-- 상세보기 화면 -->
    <div class="title_text">
        <span><strong>${plist.title}</strong></span>
    </div>
    
    <div class="title_heart">
        <span>조회수 : [${plist.viewcount}]&nbsp;&nbsp;</span>
        <a> <img alt="찜" src="https://cdn-icons-png.flaticon.com/512/6704/6704230.png" width="20" height="auto"> </a>
    </div> 
    <div class="img_big">
    	<img src="${plist.imgpath}">
    </div>
    
    <table class="tg">
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>카테고리</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.label}</td>
	  </tr>	
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>주소</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.address}</td>
	  </tr>
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>전화번호</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.phoneno}</td>
	  </tr>
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>태그</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.tag}</td>
	  </tr>
	</table>
    <div>
        <div class="detaile_info">
            <span><strong>상세정보</strong></span>
        </div>
        <div class="detail_text">
            <p>${plist.introduction}</p>
        </div>
    </div>
    <div id="map" style="width:100%; height:500px;"></div>
    
    <!-- 댓글창 -->
    <div id="outter">	 
		<div id="form-commentInfo">		 
	      	<div id="comment-count" style="margin-left:17px;"><strong>작성된 댓글<span id="count"></span></strong></div>
	        <div id="css1">
	        <hr align="left" style="border: solid 3px #D8D8D8;  width: 100%;"></div>		
	    </div><br><br>
	    <div class="list">
	    <c:forEach items="${replyList}" var="replyList">
	    	<p class="name" style="word-break: normal; font-size: 20px; display: inline-block;"><strong>${replyList.id}</strong></p>	
	   		<p class="wdate" style="font-size: 10px;  display: inline-block"><strong><fmt:formatDate value="${replyList.com_date}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></p><br>
	   		<br><hr align="left" style="border: solid 1px #D8D8D8; width: 100%; margin-top: -15px; ">		   			
	   		<p style="font-size: 15px; margin-top: 10px; word-break:break-all; width: 800px; " >${replyList.com_content }</p><br>   															 			  
	   		
		   		<c:if test="${replyList.id eq user.id}">
			   		<button type="button" class="SBTN2" data-com_num="${replyList.com_num}"><strong>수정</strong></button>
			   		<input type="hidden" name="contentid" value="${replyDelete.contentid}"/>
			   		<input type="hidden" id="reply_number" name="com_num" value="${replyDelete.com_num}" />
					<button type="button" class="SBTN3" name="com_num" data-com_num="${replyList.com_num}"><strong>삭제</strong></button>	
				</c:if>
					<button type="button" class="SBTN4"><strong>신고</strong></button>
			
		</c:forEach>
    	</div>        		
    	<form action="${contextPath}/tourist/jejureplyWrite" method="post">
			<input type="hidden" name="contentsid" value="${plist.contentsid }"/>
			<input type="hidden" name="id" value="${user.id }"/>
			<c:choose>
				<c:when test="${!empty user.id}">
					<textarea rows="content" name="com_content" id="comment_input" placeholder="댓글을 입력해주세요." onfocus="this.placeholder=''" onblur="this.placeholder='댓글을 입력해주세요.'"  style="outline: none; text-align: left; padding-left:10px;"></textarea>			
			        <button type="submit" onClick="btnbtn()" class="submit">등록</button>
			        <div id="textarea-cnt">(0 / 200)</div>
		        </c:when>
		        <c:otherwise>
			        <textarea rows="content" name="com_content" id="comment_input" placeholder="로그인을 해주세요." disabled style="outline: none; text-align: left; padding-left:10px;"></textarea>			
			        <button type="submit" onClick="btnbtn()" class="submit" disabled>등록</button>
			        <div id="textarea-cnt">(0 / 200)</div>
		        </c:otherwise>
	        </c:choose>
		</form>	
		<br><hr align="left" style="border: solid 3px #D8D8D8; width: 100%;"><br><br> 
  </div>
</body>
<script type="text/javascript">
//지도 설정
var mapContainer = document.getElementById('map'),
	mapOption = { 
	    center: new kakao.maps.LatLng(${plist.latitude},${plist.longitude}),	// 지도의 중심 좌표(임의 설정)
	    level: 9					// 지도의 확대 레벨(임의 설정)
	};
    
//설정한 지도 생성
var map = new kakao.maps.Map(mapContainer, mapOption);

var position  = new kakao.maps.LatLng(${plist.latitude},${plist.longitude}); 

//마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: position
});

marker.setMap(map);

var iwContent = '<div style="padding:20px; text-align: center; white-space: nowrap;">'+"<strong>${plist.title}</strong>"+'<br>'+'<strong>위치 : </strong>'+"<strong>${plist.address}</strong>"+'</div>';
//인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent
});

//마커에 마우스오버 이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'mouseover', function() {
  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);
});

// 마커에 마우스아웃 이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'mouseout', function() {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    infowindow.close();
});
</script>
<!-- 제주도 댓글 작성 -->
<script type="text/javascript">
$(function () {
	createReply();
})
		
function createReply() {
	$(".submit").on("click", function() {
		var formObj = $("form[name='jejureplyForm']");
		formObj.attr("action", "${contextPath}/tourist/jejureplyWrite");
		formObj.submit();
	});
}
</script>
<script type="text/javascript">
$(function () {
	updateReply();
	deleteReply();
})

function updateReply() {
	$(".SBTN2").on("click", function(){
		location.href = "${contextPath}/tourist/jejureplyUpdateView?contentsid=${plist.contentsid}"
						+ "&com_num="+$(this).attr("data-com_num");
	});
}

function deleteReply() {	
	$(".SBTN3").on("click", function() {
		var formObj = $("form[name='deleteForm']");
		if(!confirm("댓글을 삭제하시겠습니까?")){		
		}
		else {
			formObj.attr("action", "${contextPath}/tourist/jejureplyDelete");
			formObj.submit();
		}
		
	});
	
}
</script>

<script type="text/javascript">
    	$(document).ready(function() {
			$('#comment_input').on('keyup', function() {
				$('#textarea-cnt').html("(" + $(this).val().length + " / 200)");
				
				if($(this).val().length > 200) {
					$(this).val($(this).val().substring(0, 200));
					$('#textarea-cnt').html("(200 / 200)");
				}
			});
		});   	
    </script>
</html>