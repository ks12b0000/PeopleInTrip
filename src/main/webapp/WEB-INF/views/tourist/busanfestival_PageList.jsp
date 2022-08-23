<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${contextPath}/resources/css/tourist/tourist_PageList.css">
	<script type="text/javascript" src="${contextPath}/resources/js/tourist/tourist_PageList.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@600&display=swap" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet'>    
<meta charset="UTF-8">
<title>부산 축제 리스트</title>
</head>
<body>
	<jsp:include page="/header/header.jsp" flush="false" />
	
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
		<div id="tourPage_div">		
			<form action="${contextPath}/tourist/busanfestival_PageList_Sorting" method="get">
				<div name="tour_div1" id="tour_div1">
				<Strong>정렬 </Strong> &nbsp;
					<select id="tour_allign" name="value" onchange="Change(1)">
						<option value="basic" selected>기본순↓</option>
						<option value="lookup" <c:if test="${value.equals('lookup')}">selected="selected"</c:if>>조회순↓</option>
						<option value="steamed" <c:if test="${value.equals('steamed')}">selected="selected"</c:if>>찜순↓</option>
						<option value="suggestion" <c:if test="${value.equals('suggestion')}">selected="selected"</c:if>>추천순↓</option>
					</select>
					<input type="submit" value="검색" style="background-color: #9966ff; border:1px solid #9966ff; width: 60px; height: 30px; font-size: 16px; cursor: pointer; color: white;  " />
				</div>	
			</form>
			<div name="tour_div2" id="tour_div2">
			<c:forEach var="plist" items="${plist}">
				<table>
					<tr>
						<td class="tb_td1"><a href="${contextPath}/tourist/busanfestival_View?UC_SEQ=${plist.UC_SEQ}"><img src="${plist.MAIN_IMG_NORMAL}"/></a></td>
						<td class="tb_td2">
							<span class="tourpost_title"><a href="${contextPath}/tourist/busanfestival_View?UC_SEQ=${plist.UC_SEQ}">${plist.PLACE}</a></span> <br> <strong>🧡 ${plist.steamedhit}개 👍️ ${plist.suggestionhit}개 👀 ${plist.viewcount}회</strong> <br/>
							<span class="tourpost_place">${plist.ADDR1}</span>
						</td>
					</tr>					
				</table>
			</c:forEach>
			</div>
			<div name="tour_div3" id="tour_div3">
				<c:if test="${pagingDTO.curPage > 1 }">
					<a href="${contextPath}/tourist/busanfestival_PageList?value=${value}&curPage=1" style="color: #9966ff; font-size: 25px;">&laquo;</a>
					<a href="${contextPath}/tourist/busanfestival_PageList?value=${value}&curPage=${pagingDTO.curPage-1 }" style="color: #9966ff; font-size: 25px;">&lt;</a>
				</c:if>
					<c:forEach begin="${pagingDTO.firstPage }"  end="${pagingDTO.lastPage }" var="i"> &nbsp;
	   					<a href="${contextPath}/tourist/busanfestival_PageList?value=${value}&curPage=${i }" style="font-size: 18px;"  >  
	   						<c:if test="${i eq pagingDTO.curPage }">  <span style="color: red">  ${i } </span> </c:if>
	   						<c:if test="${i ne pagingDTO.curPage }">  ${i } </c:if> 
	   					</a>
					</c:forEach>&nbsp;
				<c:if test="${pagingDTO.curPage < pagingDTO.totalPageCount }">
					<a href="${contextPath}/tourist/busanfestival_PageList?value=${value}&curPage=${pagingDTO.curPage+1 }" style="color: #9966ff; font-size: 25px;">&gt;</a>
					<a href="${contextPath}/tourist/busanfestival_PageList?value=${value}&curPage=${pagingDTO.totalPageCount }" style="color: #9966ff; font-size: 25px;">&raquo;</a>
				</c:if>
			</div>
		</div>
				<div class="search">
 <select name="searchType">
  <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
 </select>
 
 <input type="text" name="keyword" id="keywordInput" value="${scri.keyword}"/>

 <button type="button" id="searchBtn">검색</button>
 
 <script>
 $(function(){
  $('#searchBtn').click(function() {
   self.location = "${contextPath}/tourist/busanfestival_PageList11"
     + '${pageMaker.makeQuery(1)}'
     + "&searchType=t"
     + "&keyword="
     + encodeURIComponent($('#keywordInput').val());
    });
 });   
 </script>
</div>
</body>
</html>