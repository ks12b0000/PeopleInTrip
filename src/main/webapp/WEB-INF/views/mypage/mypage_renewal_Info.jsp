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
        
        a {
        	text-decoration: none;
        }
        
        h1 > a {
			color: #9966ff;
			font-family: Pacifico;
			font-size: 45px;
			margin-bottom: 10px;
		}

    </style>
	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' />
	<script type="text/javascript">

	</script>

</head>
<body>
	<h1 style="text-align: center;">
		<a href="${contextPath}/mainpage/main" style="text-decoration: none;">People in Trip</a>
	</h1>
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
    <form action="${contextPath }/mypage/mypage_renewal_Info?id=${user.id}" method="POST" name="show_My_boards_List"> 
    <div class="article_box">	
	        <table align="center" class="articles">
	            <tr align="center">
	                <td width="5%">번호</td>
	                <td width="45%">제목</td>
	                <td width="10%">작성자</td>
	                <td width="10%">작성일</td>
	                <td width="5%">추천</td>
	                <td width="5%">조회수</td>
	            </tr>
	            <c:forEach var="myboardsList" items="${myboardsList}" begin="0" end="9" varStatus="myboardsListNum">
	            	<tr id="information" class="information" align="center" >
        				<td>${myboardsListNum.count }</td>
		                <td><a 
		                	href="${contextPath}/board/community_detail2.do?post_num=${myboardsList.post_num}">
		                	${myboardsList.post_title }</a></td>
		                <td>${myboardsList.id }</td>
		                <td>${myboardsList.post_date }</td>
		                <td>${myboardsList.likehit }</td>
		                <td>${myboardsList.visitcount }</td>
	            	</tr>
	            </c:forEach>
	        </table>
	      </div>
    	</form>
    	
  	<div style="text-align: center; font-size: 18px;">		
		<c:if test="${pagingDTO.curPage > 1 }">
			<a href="${contextPath}/mypage/mypage_renewal_Info?id=${pagingDTO.id}&curPage=1" style="color: #9966ff; font-size: 25px;">&laquo;</a>
			<a href="${contextPath}/mypage/mypage_renewal_Info?id=${pagingDTO.id}&curPage=${pagingDTO.curPage-1 }" style="color: #9966ff; font-size: 25px;">&lt;</a>
		</c:if>
		<c:forEach begin="${pagingDTO.firstPage }"  end="${pagingDTO.lastPage }" var="i"> &nbsp;
	   		<a href="${contextPath}/mypage/mypage_renewal_Info?id=${pagingDTO.id}&curPage=${i }" style="font-size: 18px;">   
	   			<c:if test="${i eq pagingDTO.curPage }">  <span style="color: red">  ${i } </span> </c:if>
	   			<c:if test="${i ne pagingDTO.curPage }">  ${i } </c:if> 
	   		</a>
		</c:forEach>&nbsp;
		<c:if test="${pagingDTO.curPage < pagingDTO.totalPageCount }">
			<a href="${contextPath}/mypage/mypage_renewal_Info?id=${pagingDTO.id}&curPage=${pagingDTO.curPage+1 }" style="color: #9966ff; font-size: 25px;">&gt;</a>
			<a href="${contextPath}/mypage/mypage_renewal_Info?id=${pagingDTO.id}&curPage=${pagingDTO.totalPageCount }" style="color: #9966ff; font-size: 25px;">&raquo;</a>
		</c:if>
	</div>
 	
</body>
</html>