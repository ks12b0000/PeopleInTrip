<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${contextPath}/resources/css/tourist/tourist_PageList.css">
<script type="text/javascript"
	src="${contextPath}/resources/js/tourist/tourist_PageList.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Raleway:wght@600&display=swap"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet'>
<meta charset="UTF-8">
<title>제주도 여행지 리스트</title>
</head>
<body>
	<jsp:include page="/header/header.jsp" flush="false" />

	<header>
		<div class="title">
			<h2 style="display: inline;">
				<strong><a href="${contextPath}/tourist/travel_page">제주도</a></strong>
			</h2>
			&nbsp;
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
	<br>
	<br>
	<div id="tourPage_div">
		<div name="tour_div2" id="tour_div2">
			<c:forEach var="plist" items="${plist}">
				<table>
					<tr>
						<td class="tb_td1"><a
							href="${contextPath}/tourist/tourist_View?contentsid=${plist.contentsid}"><img
								src="${plist.imgpath}" /></a></td>
						<td class="tb_td2"><span class="tourpost_title"><a
								href="${contextPath}/tourist/tourist_View?contentsid=${plist.contentsid}">${plist.title}</a></span>
							<br> <strong>🧡 ${plist.steamedhit}개  👍️ ${plist.suggestionhit}개 👀
								${plist.viewcount}회</strong> <br /> <span class="tourpost_place">${plist.address }</span>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<div style="text-align: center; font-size: 18px;">
			<ul>
				<c:if test="${pageMaker.cri.page > 1 }">
					<a
						href="${contextPath}/tourist/tourist_PageList11?page=1&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}"
						style="color: #9966ff; font-size: 25px;">&laquo;</a>
					<a
						href="${contextPath}/tourist/tourist_PageList11?&page=${pageMaker.cri.page-1 }&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}"
						style="color: #9966ff; font-size: 25px;">&lt;</a>

				</c:if>

				<c:forEach begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}" var="idx"> &nbsp;
                
                  <a
						href="${contextPath}/tourist/tourist_PageList11${pageMaker.makeSearch(idx)}"
						style="text-decoration: none;"><c:if
							test="${idx == pageMaker.cri.page }">
							<span style="text-decoration: none; color: red;"> ${idx} </span>
						</c:if></a>
					<a
						href="${contextPath}/tourist/tourist_PageList11${pageMaker.makeSearch(idx)}"
						style="text-decoration: none;"><c:if
							test="${idx != pageMaker.cri.page }">
							<span style="text-decoration: none; color: black"> ${idx}
							</span>
						</c:if></a>&nbsp;

            </c:forEach>

				<c:if test="${pageMaker.cri.page < pageMaker.endPage}">
					<a
						href="${contextPath}/tourist/tourist_PageList11?&page=${pageMaker.cri.page+1 }&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}"
						style="color: #9966ff; font-size: 25px;">&gt;</a>
					<a
						href="${contextPath}/tourist/tourist_PageList11?&page=${pageMaker.endPage}&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}"
						style="color: #9966ff; font-size: 25px;">&raquo;</a>
				</c:if>
			</ul>
		</div>



	</div>