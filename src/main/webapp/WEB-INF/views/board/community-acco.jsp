<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.search12 {
	text-align: center;
	margin-top: 2%;
}

<<<<<<< HEAD
 .seacrh_container {
     align-items: center;
     width: 300px;
     height: 40px;
     margin-left: 10px;
 }

 .seacrh_container > input {
     width: 100%;
     height: 100%;
     font-size: 15px;
     color: #a6a6a6;
     border: solid 1px #bfbfbf;
     border-radius: 10px;
     padding: 0px 24px;
 }

#searchBtn {
=======
#searchBtn12 {
>>>>>>> 8b2bf3b7af5e6a3a6941b5c5b2bfde26ff243223
	background-color: #9966ff;
	width: 60px;
	height: 30px;
	border-radius: 14px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0; rgba (0, 0,
	0, 0.19);
	color: white;
	border: 2px solid #9966ff;
	outline: none;
	cursor: pointer;
	font-size: 15px;
}

</style>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script type="text/javascript">
	$(function() {
		$('#searchBtn12').click(
				function() {
					self.location = "${contextPath}/board/community-acco"
							+ '${pageMaker.makeQuery(1)}' + "&searchType="
							+ $("select option:selected").val() + "&keyword="
							+ encodeURIComponent($('#keywordInput').val());
				});
	});
	function logingo() {
		alert("로그인 후 사용이 가능합니다.");
		location.href = "${contextPath}/login_signup/login";
	}
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="UTF-8">


<title>Community</title>

<link rel="stylesheet" href="css/bootstrap.css">
<style type="text/css">
.table-hover thead tr:hover th, .table-hover tbody tr:hover td {
	background-color: #9966ff;
	color: white;
}
</style>
</head>
<header>
		<jsp:include page="/header/header2.jsp" flush="false" />
	</header>
<body>
	
	<div class="container">
		<div>
			<br> <br>
			<tr>
				<th><h4>
						<strong>동행구해요</strong>
					</h4></th>

				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<!-- 상단바 -->
			</table>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>추천</td>
					<td>조회수</td>
				</tr>
			</thead>
			<!-- 테이블 상단바 -->
			<c:choose>
				<c:when test="${empty boardsList }">
					<tr height="10">
						<td colspan="6">
							<p>
								<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>

				<c:when test="${!empty boardsList }">
					<c:forEach var="boards" items="${boardsList}" varStatus="boardsNum">
						<tbody>
							<tr>
								<td>${boards.post_num }</td>
								<td><a
									href="${contextPath}/board/community_detail.do?post_num=${boards.post_num}">
										<c:out value="${boards.post_title }"></c:out>
								</a></td>
								<td>${boards.id }</td>
								<td><fmt:formatDate value="${boards.post_date }" /></td>
								<td>${boards.likehit }</td>
								<td>${boards.visitcount }</td>
							</tr>
						</tbody>
					</c:forEach>
				</c:when>
			</c:choose>


		</table>
		<hr />
		<div class="text-lg-end text-end">
			<c:choose>
				<c:when test="${!empty user.id}">
					<button type="button" class="btn text-white"
						style="background-color: #9966ff;"
						onclick="location.href='${contextPath}/board/community_writeWith.do'">글쓰기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn text-white"
						style="background-color: #9966ff;" onclick="logingo()">글쓰기</button>
				</c:otherwise>
			</c:choose>
		</div>
			
		<div style="text-align: center; font-size: 18px;">
         <ul>
            <c:if test="${pageMaker.cri.page > 1 }">
            <a href="${contextPath}/board/community-acco?page=1&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}"
               style="color: #9966ff; font-size: 25px;">&laquo;</a>   
               <a href="${contextPath}/board/community-acco?&page=${pageMaker.cri.page-1 }&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}" style="color: #9966ff; font-size: 25px;">&lt;</a>
               
            </c:if>      

            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
               var="idx"> &nbsp;
                
                  <a href="${contextPath}/board/community-acco${pageMaker.makeSearch(idx)}" style="text-decoration: none;"><c:if
                     test="${idx == pageMaker.cri.page }">
                     <span style="text-decoration:none; color: red;"> ${idx} </span>
                  </c:if></a>
               <a href="${contextPath}/board/community-acco${pageMaker.makeSearch(idx)}" style="text-decoration: none;"><c:if
                     test="${idx != pageMaker.cri.page }">
                     <span style=" text-decoration:none; color: black"> ${idx} </span>
                  </c:if></a>&nbsp;

            </c:forEach>

            <c:if test="${pageMaker.cri.page < pageMaker.endPage}">
               <a href="${contextPath}/board/community-acco?&page=${pageMaker.cri.page+1 }&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}" style="color: #9966ff; font-size: 25px;">&gt;</a>
               <a href="${contextPath}/board/community-acco?&page=${pageMaker.endPage}&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}" style="color: #9966ff; font-size: 25px;">&raquo;</a>
            </c:if>
         </ul>
      </div>

	<form action="${contextPath}/board/community-acco">
		<div class="search12">
			<select name="searchType">
				<option value="n"
					<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
				<option value="t"
					<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
				<option value="c"
					<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
				<option value="w"
					<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
			</select> <input type="text" name="keyword" id="keywordInput"
				value="${scri.keyword}" />

			<button id="searchBtn12" type="submit">검색</button>
	</form>

	
</body>
</html>