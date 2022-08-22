<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="board" value="${boardMap.board }" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판(글쓰기)</title>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link rel="stylesheet"
	href="../resources/css/mypage/myview_detail.css" />
<style type="text/css">

.btn313{
	background-color: #9966ff;
	width: 70px;
	height: 25px;
	border-radius: 7px;
	color: white;
	border: 2px solid #9966ff;
	outline: none;
	cursor: pointer;
	font-size: 12px;

}
.btn31{
	background-color: #AB64AB;
	width: 70px;
	height: 25px;
	border-radius: 7px;
	color: white;
	border: 2px solid #AB64AB;
	outline: none;
	cursor: pointer;
	font-size: 12px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function singo() {
		alert("댓글이 신고되었습니다");
	}
	
	function btnbtn() {
		if ($("#comment_input").val() == "") {
			alert("내용을 입력해 주세요");
			$("#comment_input").focus();
			return false;
		}
		else{
			alert("댓글이 등록되었습니다");
		}
	}
		
	function updateLike(){ 
	     $.ajax({
	            type : 'post',  
	            url : "/intrip/board/updateLike",   
	            dataType : "json",
	            data : {"post_num" : ${board.post_num}, "id" : "${user.id}" }, 
	            error : function(){
		               alert("통신 에러");
		            },
		            success : function(likeCheck) {
	                    if(likeCheck == 0){
	                    	alert("추천완료.");
	                    	
	                    }
	                    else if (likeCheck == 1){
	                     alert("추천취소");

	                    
	                }
	            }
	        });
	 }
	
	function updatesin(){ 
	     $.ajax({
	            type : 'post',  
	            url : "/intrip/board/updatesin",   
	            dataType : "json",
	            data : {"post_num" : ${board.post_num}, "id" : "${user.id}" }, 
	            error : function(){
		               alert("통신 에러");
		            },
		            success : function(sinCheck) {
	                    if(sinCheck == 0){
	                    	alert("신고완료.");
	                    	
	                    }
	                    else if (sinCheck == 1){
	                     alert("신고취소");

	                    
	                }
	            }
	        });
	 }
	
    </script>
</head>
<body>

	<!-- 헤더 -->
	<div id="page">
		<div id="header-jeh">
			<header>
				<jsp:include page="/header/header.jsp" flush="false" />
				<input type="hidden" name="post_num" value="${board.post_num }">
			</header>
		</div>
		<!-- 왼쪽 메뉴바 -->
		<div class="left-menu">
			<ul class="left-menu-ul">
				<li class="menu-list"><a href=""><i
						class="fa-solid fa-bullhorn fa-lg"></i>정보게시판</a></li>
				<li class="menu-list" ><a href=""><i
						class="fa-solid fa-people-robbery fa-lg"></i>동행구해요</a></li>
			</ul>
		</div>
		<!-- 정보게시판 본문 -->
		<section class="content">
			<div>
				<p class="write-detail">마이페이지 > 내가쓴글</p>
			</div>
			<div class="write-title">
				<div class="write-titlemain" style="font-size: 17.5px;">${board.post_title }</div>
				<div class="write-titlesub">
					${board.id } |
					<fmt:formatDate value="${board.post_date }" />
					| 👍️ : ${board.likehit} | 👀 : ${board.visitcount} | 🚨 : ${board.sinhit}
				</div>
			</div>
			<div>
				<p class="write-file">첨부파일 : XX.xxx</p>
			</div>
			<div>
				<p class="write-content">${board.post_content }</p>
			</div>
			<div>
				<p class="write-comment1">작성된 댓글( X 개)</p>
			</div>
			<div class="write-button">
				<c:if test="${user.id != null }">
					<button id="write-recommand" class="write-recommand" style="outline: none; cursor: pointer; background-color :  #9966ff;
					border-radius: 7px; border: 2px solid #FFFFFF; 
						onclick="updateLike()">👍️</button>
					<button class="write-declaration" onclick="updatesin()" style="outline: none; cursor: pointer; background-color :  #9966ff;
					 border-radius: 7px; border: 2px solid #FFFFFF;">🚨</button>
				</c:if>
			</div>
			<div class="write-comment22">
				<textarea class="write-comment2"
					placeholder="&#13;&#10; - 최대 300자까지 작성할 수 있습니다(띄어쓰기 포함).&#13;&#10; ※ 욕설, 영업에 방해되는 글은 관리자에 의해 삭제될 수 있습니다."></textarea>
				<button class="write-comment3">등록</button>
			</div>
			<div>

				<p class="write-id">
					<span class="write-id2">${board.id }</span> <span
						class="write-date">| <fmt:formatDate
							value="${board.post_date }" /></span>
					<button class="write-edit">수정</button>
					<button class="write-delete">삭제</button>
				</p>
				<p class="write-comment4">테스트 댓글</p>
				<p class="write-declaration2" onclick="singo()">
					<button>신고</button>
				</p>
			</div>
			<div class="write-form">
				<button class="btn313"
					onclick="location.href='${contextPath}/board/community-acco'">목록보기</button>
				<c:if test="${user.id == board.id }">
					<button type="button" class="btn313"
						onclick="location.href='${contextPath}/board/modBoard.do?post_num=${board.post_num }'">수정</button>
					<button type="button" class="btn31"
						onclick="location.href='${contextPath}/board/removeBoard.do?post_num=${board.post_num }'">삭제</button>
				</c:if>
			</div>
		</section>
	</div>
	
	

</body>
</html>