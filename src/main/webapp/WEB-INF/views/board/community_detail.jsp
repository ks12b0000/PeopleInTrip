<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="board" value="${boardMap.board }" />
<c:set var="imageFileList" value="${boardMap.imageFileList }" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


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
	href="../resources/css/community/community_detail.css" />
<style type="text/css">
.btn313 {
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

.btn31 {
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

#comment_input {
	width: 60%;
	height: 3em;
	margin-top: 2.5%;
	margin-left: 200px;
	resize: none;
	text-align: center;
	padding-top: 23px;
	border: solid 2px #D8D8D8;
}

.submit {
	margin-top: 25px;
	margin-left: 1.5%;
	width: 5.5em;
	height: 4.6em;
	font-size: 15px;
	font-weight: bold;
	position: absolute;
	background-color: white;
	border: 2px solid #D8D8D8;
	cursor: pointer;
}

#comment-count {
	font-size: 25px;
	margin-bottom: 20px;
	margin-top: 20px;
}

#form-commentInfo {
	width: 100%;
	margin-bottom: 5px;
}

#outter {
	text-align: center;
}

.list {
	position: relative;
	height: auto;
	margin-top: -30px;
	padding-top: 5px;
	padding-left: 2%;
	border: solid 1px #D8D8D8;
	width: 775px;
	margin-left: 270px;
}

.list>.SBTN2 {
	text-align: center;
	display: flex;
	margin-left: 650px;
	margin-top: -30px;
	width: 50px;
	height: 30px;
	background-color: white;
	border: 2px solid #D8D8D8;
	cursor: pointer;
	padding-left: 9px;
	padding-top: 3px;
}

.list>#deleteForm>.SBTN3 {
	text-align: center;
	display: flex;
	margin-left: 715px;
	margin-top: -30px;
	width: 50px;
	height: 30px;
	background-color: white;
	border: 2px solid #D8D8D8;
	cursor: pointer;
	padding-left: 9px;
	padding-top: 3px;
}


</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	
	
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
	                    	location.href = "${contextPath}/board/community_detail.do?post_num=${board.post_num}";
	                    }
	                    else if (likeCheck == 1){
	                     alert("추천취소");
	                     location.href = "${contextPath}/board/community_detail.do?post_num=${board.post_num}";
	                    
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
	                    	location.href = "${contextPath}/board/community_detail.do?post_num=${board.post_num}";
	                    }
	                    else if (sinCheck == 1){
	                     alert("신고취소");
	                     location.href = "${contextPath}/board/community_detail.do?post_num=${board.post_num}";
	                    
	                }
	            }
	        });
	 }
	
	$(function () {
		   createReply();
		})
		      
		function createReply() {
		   $(".submit").on("click", function() {
		      var formObj = $("form[name='boardreplyForm']");
		      formObj.attr("action", "${contextPath}/board/boardreplyWrite");
		      formObj.submit();
		   });
		}
	
	$(function () {
		   updateReply();
		   deleteReply();
		})

		function updateReply() {
		   $(".SBTN2").on("click", function(){
		      location.href = "${contextPath}/board/boardreplyUpdateView?post_num=${board.post_num}"
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
		         formObj.attr("action", "${contextPath}/board/boardreplyDelete");
		         formObj.submit();
		      }
		      
		   });
		   
		}
		
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
				<li class="menu-list"><a href="${contextPath }/board/community-info"><i
						class="fa-solid fa-bullhorn fa-lg"></i>정보게시판</a></li>
				<li class="menu-list" style="background-color: #9966ff;"><a
					href="${contextPath }/board/community-acco"><i class="fa-solid fa-people-robbery fa-lg"></i>동행구해요</a></li>
			</ul>
		</div>
		<!-- 정보게시판 본문 -->
		<section class="content">
			<div>
				<p class="write-detail">동행구해요 > 상세보기</p>
			</div>
			<div class="write-title">
				<div class="write-titlemain">${board.post_title }</div>
				<div class="write-titlesub">
					${board.id } |
					<fmt:formatDate value="${board.post_date }" />
					| 👍️ : ${board.likehit} | 👀 : ${board.visitcount} | 🚨 :
					${board.sinhit}
				</div>
			</div>
			<div>
				<p class="write-file" style="padding-left: 265px;" > <c:set var="img_index" />
			<c:choose>
				<c:when test="${not empty imageFileList && imageFileList != 'null' }">
					<c:forEach var="item" items="${imageFileList }" >					
	<img alt="이미지" src="${contextPath}/download.do?imageFileName=${item.imageFileName}&post_num=${item.post_num}"
									id="preview${status.index }" width="250px" height="250px" ><br />
					</c:forEach>
				</c:when>
				
			</c:choose>	</p>
			</div>
			<div>
				<p class="write-content">${board.post_content }</p>
			</div>



			<div class="write-button">
				<c:if test="${user.id != null }">
					<button id="write-recommand" class="write-recommand"
						onclick="updateLike()"
						style="outline: none; cursor: pointer; background-color: #9966ff; border-radius: 7px; border: 2px solid #FFFFFF;">👍️</button>
					<button class="write-declaration" onclick="updatesin()"
						style="outline: none; cursor: pointer; background-color: #9966ff; border-radius: 7px; border: 2px solid #FFFFFF;">🚨</button>
				</c:if>
			</div>

			<!-- 댓글창 -->
			<div id="outter">
				<div id="form-commentInfo">
					<div id="comment-count" style="margin-left: 250px;">
						<strong style="font-size: 20px;">작성된 댓글<span id="count">
								[${commentpagingDTO.totalRowCount}]개</span></strong>
					</div>
					<div id="css1">
						<hr align="left" style="border: solid 3px #D8D8D8; width: 100%;">
					</div>
				</div>
				<br>
				<br>
				<div class="list">
					<c:forEach items="${replyList}" var="replyList">
						<p class="name"
							style="word-break: break-all; font-size: 20px; display: inline-block; margin-left: -600px; ">
							<strong>${replyList.id}</strong>
						</p>
						<p class="wdate" style="font-size: 10px; display: inline-block">
							<strong><fmt:formatDate value="${replyList.com_date}"
									pattern="yyyy-MM-dd HH:mm:ss" /></strong>
						</p>
						<br>
						<br>
						<hr align="left"
							style="border: solid 1px #D8D8D8; width: 100%; margin-top: -15px;">
						<p
							style="font-size: 15px; margin-top: 10px; word-break: break-all; width: 500px; text-align: left;">${replyList.com_content }</p>
						<br>

						<c:if test="${replyList.id eq user.id || user.grade == '관리자'}">
							<button type="button" class="SBTN2"
								data-com_num="${replyList.com_num}">
								<strong>수정</strong>
							</button>
							<form action="${contextPath}/board/boardreplyDelete"
								method="post" name="deleteForm" id="deleteForm">
								<input type="hidden" name="post_num" value="${board.post_num }" />
								<input type="hidden" name="com_num"
									value="${replyList.com_num }" />
								<button type="submit" class="SBTN3" name="com_num"
									data-com_num="${replyList.com_num}">
									<strong>삭제</strong>
								</button>
							</form>
						</c:if>
						

					</c:forEach>
				</div>
				<form action="${contextPath}/board/boardreplyWrite" method="post">
					<input type="hidden" name="post_num" value="${board.post_num }" />
					<input type="hidden" name="id" value="${user.id }" />
					<c:choose>
						<c:when test="${!empty user.id}">
							<textarea rows="content" name="com_content" id="comment_input"
								placeholder="댓글을 입력해주세요." onfocus="this.placeholder=''"
								onblur="this.placeholder='댓글을 입력해주세요.'"
								style="outline: none; text-align: left; padding-left: 10px;"></textarea>
							<button type="submit" onClick="btnbtn()" class="submit">등록</button>
							<div id="textarea-cnt" style="margin-left: 250px;">(0 /
								200)</div>
						</c:when>
						<c:otherwise>
							<textarea rows="content" name="com_content" id="comment_input"
								placeholder="로그인을 해주세요." disabled
								style="outline: none; text-align: left; padding-left: 10px;"></textarea>
							<button type="submit" onClick="btnbtn()" class="submit" disabled>등록</button>
							<div id="textarea-cnt" style="margin-left: 250px;">(0 /
								200)</div>
						</c:otherwise>
					</c:choose>
				</form>
				<div name="tour_div3" id="tour_div3" style="margin-left: 250px;">
					<c:if test="${commentpagingDTO.curPage > 1 }">
						<a
							href="${contextPath}/board/community_detail.do?post_num=${board.post_num}&curPage=1"
							style="color: #9966ff; font-size: 25px; text-decoration: none;">&laquo;</a>
						<a
							href="${contextPath}/board/community_detail.do?post_num=${board.post_num}&curPage=${commentpagingDTO.curPage-1 }"
							style="color: #9966ff; font-size: 25px; text-decoration: none;">&lt;</a>
					</c:if>
					<c:forEach begin="${commentpagingDTO.firstPage }"
						end="${commentpagingDTO.lastPage }" var="i"> &nbsp;
                     <a
							href="${contextPath}/board/community_detail.do?post_num=${board.post_num}&curPage=${i }"
							style="font-size: 18px; color: black; margin-left: 15px; text-decoration: none;">
							<c:if test="${i eq commentpagingDTO.curPage }">
								<span style="color: red"> ${i } </span>
							</c:if> <c:if test="${i ne commentpagingDTO.curPage }">  ${i } </c:if>
						</a>
					</c:forEach>
					&nbsp;
					<c:if
						test="${commentpagingDTO.curPage < commentpagingDTO.totalPageCount }">
						<a
							href="${contextPath}/board/community_detail.do?post_num=${board.post_num}&curPage=${commentpagingDTO.curPage+1 }"
							style="color: #9966ff; font-size: 25px; text-decoration: none;">&gt;</a>
						<a
							href="${contextPath}/board/community_detail.do?post_num=${board.post_num}&curPage=${commentpagingDTO.totalPageCount }"
							style="color: #9966ff; font-size: 25px; text-decoration: none;">&raquo;</a>
					</c:if>
				</div>
				<br>
				<hr align="left" style="border: solid 3px #D8D8D8; width: 100%;">
				<br>
				<br>
			</div>
	</div>
	<div class="write-form">
		<button class="btn313"
			onclick="location.href='${contextPath}/board/community-acco'">목록보기</button>
		<c:if test="${user.id == board.id || user.grade == '관리자'}">
			<button type="button" class="btn313"
				onclick="location.href='${contextPath}/board/modBoard.do?post_num=${board.post_num }'">수정</button>
			</c:if>
			<c:if test="${user.id == board.id and user.grade == '일반회원'}">
			<button type="button" class="btn31"
				onclick="location.href='${contextPath}/board/removeBoard.do?post_num=${board.post_num }'">삭제</button>
			</c:if>
			<c:if test="${user.grade == '관리자'}">
			<button type="button" class="btn31"
				onclick="location.href='${contextPath}/board/removeBoard2.do?post_num=${board.post_num }'">삭제</button>
			</c:if>
	</div>
	</section>
	</div>



</body>
</html>