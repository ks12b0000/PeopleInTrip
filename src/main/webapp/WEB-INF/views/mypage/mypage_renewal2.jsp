<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- <c:set var="board" value="${myboardsList.board }" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        body {
            width: 1200px;
            max-width: 1060px;
            margin: 0px auto;            
        }
        .cls1 {
        	position: relative;
            font-size: 25px;
            font-weight: 500;
            padding: 15px;
            border-bottom: solid 2px black;
            /* border: solid 1px red; */
        }
        
        .cls1left {
        	position: absolute;
        	margin-top: -30px;
        	margin-left: 980px;
        }
        .cls2 {
            display: flex;
        }
        .cls3 {
            display: flex;
            justify-content: flex-end;
            flex-grow: 1;
            padding: 20px 0;
            border-bottom: solid 1px #bfbfbf;
            /* border: solid 1px purple; */
        }
        
        .wait {
            border: solid 1px red;
        }
        .img_box {
            display: flex;
            justify-content: flex-end;
            flex-grow: 1;
        }

        .cls3 a {
            padding: 3px 12px;
            text-decoration: none;
            background-color: #9966ff;
            color: white;
            border-radius: 5px;
            
        }
        .articles td {
            height: 28px;
            padding: 4px 7px;
            border-bottom: 1px solid #bfbfbf;
        }

        .search {
            display: flex;
            justify-content: center;
        }

        .search select, .search input {
            margin: 4px;
        }

        .search_btn {
            padding: 3px 12px;
            border-radius: 5px;
            background-color: #9966ff;
            color: white;
            border: none;
        }

        .category {
            display: flex;
            justify-content: flex-end;
            flex-grow: 1; 
        }

        .like td{
            padding: 10px 35px;
        }

        .title {
            position: relative;
            bottom: 21px;
            background-color: black;
            color: white;
            opacity: 0.8;

        }

        .test li{
        	display: inline;
        	background-color: #9966ff;
        	color: white;
        	border-radius: 5px;
        	text-decoration: none;
        	padding: 3px 12px;
        }

    </style>
	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<script type="text/javascript">
	$(function(){
		  $('#searchBtn').click(function() {
		   self.location = "${contextPath}/mypage/mypage_renewal2?id=${user.id}&"
		     + '${pageMaker.makeQuery(1)}'
		     + "&searchType="
		     + $("select option:selected").val()
		     + "&keyword="
		     + encodeURIComponent($('#keywordInput').val());
		    });
		 });  
	</script>

</head>
<body>
    <p class="cls1">마이페이지</p>
    <hr/><br/><br/>
    <div class="cls2">
        <table>
            <tr>
                <td>아이디: </td>
                <td>${user.id }</td>
            </tr>
            <tr>
            	<td>이름: </td>
            	<td>${user.name }</td>
            </tr>
            <tr>
                <td>닉네임: </td>
                <td>${user.nick_nm }</td>
            </tr>
            <tr>
                <td>이메일: </td>
                <td>${user.email }</td>
            </tr>
        </table>
    </div>
    <div class="cls3">
        <div>
        	<a href="${contextPath}/mypage/mypage_steamed_jeju?id=${user.id}">내가 찜한 글</a>
            <a href="${contextPath}/mypage/modify_info?id=${user.id}">내 정보 수정</a>
            <a href="${contextPath}/mypage/member_delete.do?id=${user.id}">회원 탈퇴</a>
        </div>
    </div>
    <br/><br/>
    <p class="cls1">내가 쓴 글</p><!-- <b class="cls1left" onclick="return myboardListClick()">조회하기</b> -->
    	<br/>
   	<ul class="test">
		<li><a href="${contextPath}/mypage/mypage_renewal?id=${user.id}">동행</a></li>
		<li>정보</li>
	</ul>
	<br/>
    <hr/>
    <div class="article_box">
    	 <form action="${contextPath }/mypage/mypage_renewal?id=${user.id}" method="POST" name="show_My_boards_List"> 
	        <table align="center" class="articles">
	            <tr align="center">
	                <td width="5%">번호</td>
	                <td width="45%">제목</td>
	                <td width="10%">작성자</td>
	                <td width="10%">작성일</td>
	                <td width="5%">추천</td>
	                <td width="5%">조회수</td>
	            </tr>
	            <c:forEach var="myboardsList2" items="${myboardsList2}" begin="0" end="9" varStatus="myboardsListNum">
	            	<tr id="information" class="information" align="center" >
        				<td>${myboardsListNum.count }</td>
		                <td><a 
		                	href="${contextPath}/board/community_detail2.do?post_num=${myboardsList2.post_num}">
		                	${myboardsList2.post_title }</a></td>
		                <td>${myboardsList2.id }</td>
		                <td>${myboardsList2.post_date }</td>
		                <td>${myboardsList2.likehit }</td>
		                <td>${myboardsList2.visitcount }</td>
	            	</tr>
	            </c:forEach>
	        </table>
    	</form>
			<div style="text-align: center; font-size: 18px;">		
				 <ul>
				 <!-- << -->
				  <a href="${contextPath}/mypage/mypage_renewal2?page=1&id=${user.id}" style="color: #9966ff; font-size: 25px;">&laquo;</a> 
				  <c:if test="${pageMaker.prev}">
				   <a href="${contextPath}/mypage/mypage_renewal2${pageMaker.makeSearch(pageMaker.startPage - 1)}&id=${user.id}">이전</a>
				  </c:if> 
				  
				  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx"> &nbsp;
				   <a href="${contextPath}/mypage/mypage_renewal2${pageMaker.makeSearch(idx)}&id=${user.id}">${idx}</a> &nbsp;
				  </c:forEach>
				  
				  <!-- >> -->   
				  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				   <a href="${contextPath}/mypage/mypage_renewal2${pageMaker.makeSearch(pageMaker.endPage + 1)}&id=${user.id}">다음</a> 
				  </c:if>
				  
				 <c:choose>
				   <c:when test= "${pageMaker.displayPageNum % 2 == 1 }">
				   <a href="${contextPath}/mypage/mypage_renewal2${pageMaker.makeSearch(pageMaker.totalCount/10 ) }&id=${user.id}" style="color: #9966ff; font-size: 25px;">&raquo;</a>
					</c:when>
					<c:when test= "${pageMaker.displayPageNum % 2 == 0 }">
					 <a href="${contextPath}/mypage/mypage_renewal2${pageMaker.makeSearch(pageMaker.totalCount/10 +1  ) }&id=${user.id}" style="color: #9966ff; font-size: 25px;">&raquo;</a>
					</c:when>
					</c:choose>
				 </ul>
			</div>
        <div class="search">
			<select name="searchType">
				<option value="n"
					<c:out value="${scri2.searchType == null ? 'selected' : ''}"/>>-----</option>
				<option value="t"
					<c:out value="${scri2.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
				<option value="c"
					<c:out value="${scri2.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
				<option value="w"
					<c:out value="${scri2.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
			</select> <input type="text" name="keyword" id="keywordInput"
				value="${scri2.keyword}" />
	
			<button id="searchBtn" type="button">검색</button>
        </div>
    </div>
</body>
</html>