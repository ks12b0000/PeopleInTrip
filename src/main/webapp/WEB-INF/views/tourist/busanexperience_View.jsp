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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<meta charset="UTF-8">
	<title>${plist.PLACE} 상세페이지</title>
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
	<jsp:include page="/header/header.jsp" flush="false" />
	
	<!-- 헤더 -->
	<header>
		<div class="title">
			<h2 style="display: inline;"><strong><a href="${contextPath}/tourist/busantravel_page">부산</a></strong></h2>&nbsp;
			<h3 style="display: inline;">Busan</h3>
		</div>
		<nav class="tourismenu">
			<ul>
				<li><a href="${contextPath}/tourist/busantravel_page"><strong>홈</strong></a></li>
				<li><a href="${contextPath}/tourist/busantourist_PageList"><strong>여행지</strong></a></li>
				<li><a href="${contextPath}/tourist/busanfestival_PageList"><strong>축제</strong></a></li>
				<li><a href="${contextPath}/tourist/busanexperience_PageList"><strong>체험</strong></a></li>
			</ul>
		</nav>
	</header>
	<br/><br/><br/>

	<!-- 상세보기 화면 -->
    <div class="title_text">
        <span><strong>${plist.PLACE}</strong></span>
    </div>
    
    <div class="title_heart">
        <span><strong>조회수 : [${plist.viewcount}]&nbsp;<span style="color:#9966ff;">|</span></strong>&nbsp;&nbsp;</span>
        <c:choose>
        	<c:when test="${user.id != null }">
        		<input type="hidden" name="UC_SEQ" value="${plist.UC_SEQ }"/>
        		<span><strong>찜하기 <button onclick="updateSteamed()" style="background-color: #9966ff;  border: 2px solid #9966ff; border-radius: 7px; width: 30px; cursor: pointer;">🧡</button> : [${plist.steamedhit}]&nbsp;<span style="color:#9966ff;">|</span></strong></span>&nbsp;&nbsp;
        	</c:when>
        	<c:otherwise>
        		<span><strong>찜하기 <button onclick="updateSteamed2()" class="updateSteamed2"  style="background-color: #9966ff;  border: 2px solid #9966ff; border-radius: 7px; width: 30px; cursor: pointer;">🧡</button> : [${plist.steamedhit}]&nbsp;<span style="color:#9966ff;">|</span></strong></span>&nbsp;&nbsp;        		
        	</c:otherwise>
        </c:choose>
        
        <c:choose>
        	<c:when test="${user.id != null }">
        		<input type="hidden" name="UC_SEQ" value="${plist.UC_SEQ }"/>
        		<span><strong> 추천하기 <button onclick="updateSuggestion()" style="background-color: #9966ff;  border: 2px solid #9966ff; width: 30px; cursor: pointer; border-radius: 7px; ">👍️</button> : [${plist.suggestionhit}]&nbsp;</strong></span>&nbsp;&nbsp;
        	</c:when>
        	<c:otherwise>
        		<span><strong> 추천하기 <button onclick="updateSuggestion2()" class="updateSuggestion2"  style="background-color: #9966ff; border: 2px solid #9966ff; width: 30px; cursor: pointer; border-radius: 7px; ">👍️</button> : [${plist.suggestionhit}]&nbsp;</strong></span>&nbsp;&nbsp;        		
        	</c:otherwise>
        </c:choose>   
        
    </div> 
    <div class="img_big">
    	<img src="${plist.MAIN_IMG_NORMAL}">
    </div>
    
    <table class="tg">
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>카테고리</strong></th>
	    <td class="tg-wo29" colspan="1">체험</td>
	  </tr>	
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>주소</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.ADDR1}</td>
	  </tr>
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>전화번호</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.CNTCT_TEL}</td>
	  </tr>
	  <tr>
	    <th class="tg-13ci" colspan="2"><strong>태그</strong></th>
	    <td class="tg-wo29" colspan="1">${plist.SUBTITLE}</td>
	  </tr>
	</table>
    <div>
        <div class="detaile_info">
            <span><strong>상세정보</strong></span>
        </div>
        <div class="detail_text">
            <p>${plist.ITEMCNTNTS}</p>
        </div>
    </div>
    <div id="map" style="width:100%; height:500px;"></div>
    
    <!-- 댓글창 -->
    <div id="outter">	 
		<div id="form-commentInfo">		 
	      	<div id="comment-count" style="margin-left:17px;"><strong>작성된 댓글<span id="count"> [${commentpagingDTO.totalRowCount}]개</span></strong></div>
	        <div id="css1">
	        <hr align="left" style="border: solid 3px #D8D8D8;  width: 100%;"></div>		
	    </div><br><br>
	    <div class="list">
	    <c:forEach items="${replyList}" var="replyList">
	    	<p class="name" style="word-break: normal; font-size: 20px; display: inline-block;"><strong>${replyList.id}</strong></p>	
	   		<p class="wdate" style="font-size: 10px;  display: inline-block"><strong><fmt:formatDate value="${replyList.com_date}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></p><br>
	   		<br><hr align="left" style="border: solid 1px #D8D8D8; width: 100%; margin-top: -15px; ">		   			
	   		<p style="font-size: 15px; margin-top: 10px; word-break:break-all; width: 800px; " >${replyList.com_content }</p><br>   															 			  
	   		
		   		<c:if test="${replyList.id eq user.id || user.grade == '관리자'}">
			   		<button type="button" class="SBTN2" data-com_num="${replyList.com_num}"><strong>수정</strong></button>
			   	<form action="${contextPath}/tourist/busanreplyDelete3" method="post" name="deleteForm" id="deleteForm">
			  	 	<input type="hidden" name="UC_SEQ" value="${plist.UC_SEQ }"/>
			  	 	<input type="hidden" name="com_num" value="${replyList.com_num }"/>
					<button type="submit" class="SBTN3" name="com_num" data-com_num="${replyList.com_num}"><strong>삭제</strong></button>	
				</form>	
				</c:if>	        		
		</c:forEach>
    	</div>        		
    	<form action="${contextPath}/tourist/busanreplyWrite3" method="post">
			<input type="hidden" name="UC_SEQ" value="${plist.UC_SEQ }"/>
			<input type="hidden" name="id" value="${user.id}"/>
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
		<div name="tour_div3" id="tour_div3" style="text-align: center;">
				<c:if test="${commentpagingDTO.curPage > 1 }">
					<a href="${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}&curPage=1" style="color: #9966ff; font-size: 25px;">&laquo;</a>
					<a href="${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}&curPage=${commentpagingDTO.curPage-1 }" style="color: #9966ff; font-size: 25px;">&lt;</a>
				</c:if>
					<c:forEach begin="${commentpagingDTO.firstPage }"  end="${commentpagingDTO.lastPage }" var="i"> &nbsp;
	   					<a href="${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}&curPage=${i }" style="font-size: 18px; color:black;"  >  
	   						<c:if test="${i eq commentpagingDTO.curPage }">  <span style="color: red">  ${i } </span> </c:if>
	   						<c:if test="${i ne commentpagingDTO.curPage }">  ${i } </c:if> 
	   					</a>
					</c:forEach>&nbsp;
				<c:if test="${commentpagingDTO.curPage < commentpagingDTO.totalPageCount }">
					<a href="${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}&curPage=${commentpagingDTO.curPage+1 }" style="color: #9966ff; font-size: 25px;">&gt;</a>
					<a href="${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}&curPage=${commentpagingDTO.totalPageCount }" style="color: #9966ff; font-size: 25px;">&raquo;</a>
				</c:if>
		</div>
		<br><hr align="left" style="border: solid 3px #D8D8D8; width: 100%;"><br><br> 
  </div>
</body>
<script type="text/javascript">
//지도 설정
var mapContainer = document.getElementById('map'),
	mapOption = { 
	    center: new kakao.maps.LatLng(${plist.LAT},${plist.LNG}),	// 지도의 중심 좌표(임의 설정)
	    level: 9					// 지도의 확대 레벨(임의 설정)
	};
    
//설정한 지도 생성
var map = new kakao.maps.Map(mapContainer, mapOption);

var position  = new kakao.maps.LatLng(${plist.LAT},${plist.LNG}); 

//마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: position
});

marker.setMap(map);

var iwContent = '<div style="padding:20px; text-align: center; white-space: nowrap;">'+"<strong>${plist.PLACE}</strong>"+'<br>'+'<strong>위치 : </strong>'+"<strong>${plist.ADDR1}</strong>"+'</div>';
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
		var formObj = $("form[name='busanreplyForm']");
		formObj.attr("action", "${contextPath}/tourist/busanreplyWrite3");
		formObj.submit();
		alert("댓글이 작성되었습니다.")
	});
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
	updateReply();
	deleteReply();
})

function updateReply() {
	$(".SBTN2").on("click", function(){
		location.href = "${contextPath}/tourist/busanreplyUpdateView3?UC_SEQ=${plist.UC_SEQ}"
						+ "&com_num="+$(this).attr("data-com_num");
	});
}

function deleteReply() {	
	$(".SBTN3").on("click", function() {
		var formObj = $("form[name='deleteForm']");
		if(!confirm("댓글을 삭제하시겠습니까?")){	
			return false;
		}
		else {
			formObj.attr("action", "${contextPath}/tourist/busanreplyDelete3");
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
<script type="text/javascript">
function updateSteamed(){ 
    $.ajax({
           type : 'post',  
           url : "/intrip/tourist/busanupdatesteamed3",   
           dataType : "json",
           data : {"UC_SEQ" : ${plist.UC_SEQ}, "id" : "${user.id}" }, 
           error : function(){
	               alert("통신 에러");
	            },
	            success : function(steamedCheck) {
                   if(steamedCheck == 0){
                   	alert("찜하기 완료.");
                   	location.href = "${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}";
                   }
                   else if (steamedCheck == 1){
                    alert("찜하기 취소"); 
                    location.href = "${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}";
               }
           }
       });
}
	$(".updateSteamed2").on("click", function(){
		alert("로그인 후 이용가능합니다!")
	});
</script>	
<script type="text/javascript">
	function updateSuggestion(){ 
	    $.ajax({
	           type : 'post',  
	           url : "/intrip/tourist/busanupdateSuggestion3",   
	           dataType : "json",
	           data : {"UC_SEQ" : ${plist.UC_SEQ}, "id" : "${user.id}" }, 
	           error : function(){
		               alert("통신 에러");
		            },
		            success : function(suggestionCheck) {
	                   if(suggestionCheck == 0){
	                   	alert("추천하기 완료.");
	                   	location.href = "${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}";
	                   }
	                   else if (suggestionCheck == 1){
	                    alert("추천하기 취소"); 
	                    location.href = "${contextPath}/tourist/busanexperience_View?UC_SEQ=${plist.UC_SEQ}";
	               }
	           }
	       });
	}
		$(".updateSuggestion2").on("click", function(){
			alert("로그인 후 이용가능합니다!")
		});
</script>	
</html>