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

		.title a {
			vlink: white;
		}

    </style>
	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<script type="text/javascript">
/* 	$(function(){
		  $('#searchBtn').click(function() {
		   self.location = "${contextPath}/mypage/mypage_renewal?id=${user.id}&"
		     + '${pageMaker.makeQuery(1)}'
		     + "&searchType="
		     + $("select option:selected").val()
		     + "&keyword="
		     + encodeURIComponent($('#keywordInput').val());
		    });
		 });   */
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
            <a href="${contextPath}/mypage/modify_info?id=${user.id}">내 정보 수정</a>
            <a href="${contextPath}/mypage/member_delete.do?id=${user.id}">회원 탈퇴</a>
        </div>
    </div>
    <br/><br/>
    <p class="cls1">찜한 내역(제주)</p>
    <hr/>
    <form action="${contextPath}/mypage/mypage_steamed_jeju?id=${user.id}" method="post" name="show_MyTour_Steamed">
	    <div>
	        <div class="category">
	            <select>
	                <option>전체</option>
	                <option>관광지</option>
	                <option>축제</option>
	                <option>공연</option>
	                <option>전시</option>
	            </select>
	        </div>
	        <div align="center">
	            <table align="center" class="like">
	                <tr align="center">
	                	<c:forEach var="boardsTour" items="${boardsTour}" begin="0" end="3">
		                    <td>
		                        <div>
		                        	<a href="${contextPath}/tourist/tourist_View?contentsid=${boardsTour.contentsid}">
		                        		<img src="${boardsTour.imgpath }" width="200px" height="120px">
		                        	</a>
		                        </div>
		                        <div class="title">
		                        	<a class="link" href="${contextPath}/tourist/tourist_View?contentsid=${boardsTour.contentsid}" >
		                        		${boardsTour.title }
		                        	</a>
		                        </div>
		                    </td>
	                    </c:forEach>
	               </tr>
	               <tr align="center">
    	                <c:forEach var="boardsTour" items="${boardsTour}" begin="4" end="7">
		                    <td>
		                        <div>
		                        	<a href="${contextPath}/tourist/tourist_View?contentsid=${boardsTour.contentsid}">
		                        		<img src="${boardsTour.imgpath }" width="200px" height="120px">
		                        	</a>
		                        </div>
		                        <div class="title">
		                        	<a class="link" href="${contextPath}/tourist/tourist_View?contentsid=${boardsTour.contentsid}" >
		                        		${boardsTour.title }
		                        	</a>
		                        </div>
		                    </td>
	                    </c:forEach>
  				   </tr>
	               <tr align="center">
    	                <c:forEach var="boardsTour" items="${boardsTour}" begin="8" end="11">
		                    <td>
		                        <div>
		                        	<a href="${contextPath}/tourist/tourist_View?contentsid=${boardsTour.contentsid}">
		                        		<img src="${boardsTour.imgpath }" width="200px" height="120px">
		                        	</a>
		                        </div>
		                        <div class="title">
		                        	<a class="link" href="${contextPath}/tourist/tourist_View?contentsid=${boardsTour.contentsid}" >
		                        		${boardsTour.title }
		                        	</a>
		                        </div>
		                    </td>
	                    </c:forEach>
  				   </tr>
	            </table>
	        </div>
	    </div>
    </form>
    			<div style="text-align: center; font-size: 18px;">		
				 <ul>
				 <!-- << -->
				  <a href="${contextPath}/mypage/mypage_steamed_jeju?page=1&id=${user.id}" style="color: #9966ff; font-size: 25px;">&laquo;</a> 
				  <c:if test="${pageMaker.prev}">
				   <a href="${contextPath}/mypage/mypage_steamed_jeju${pageMaker.makeSearch(pageMaker.startPage - 1)}&id=${user.id}">이전</a>
				  </c:if> 
				  
				  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx"> &nbsp;
				   <a href="${contextPath}/mypage/mypage_steamed_jeju${pageMaker.makeSearch(idx)}&id=${user.id}">${idx}</a> &nbsp;
				  </c:forEach>
				  
				  <!-- >> -->   
				  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				   <a href="${contextPath}/mypage/mypage_steamed_jejul${pageMaker.makeSearch(pageMaker.endPage + 1)}&id=${user.id}">다음</a> 
				  </c:if>
				  
				 <c:choose>
				   <c:when test= "${pageMaker.displayPageNum % 2 == 1 }">
				   <a href="${contextPath}/mypage/mypage_steamed_jeju${pageMaker.makeSearch(pageMaker.totalCount/10 ) }&id=${user.id}" style="color: #9966ff; font-size: 25px;">&raquo;</a>
					</c:when>
					<c:when test= "${pageMaker.displayPageNum % 2 == 0 }">
					 <a href="${contextPath}/mypage/mypage_steamed_jeju${pageMaker.makeSearch(pageMaker.totalCount/10 +1  ) }&id=${user.id}" style="color: #9966ff; font-size: 25px;">&raquo;</a>
					</c:when>
					</c:choose>
				 </ul>
			</div>
        <div class="search">
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
	
			<button id="searchBtn" type="button">검색</button>
        </div>
</body>
</html>