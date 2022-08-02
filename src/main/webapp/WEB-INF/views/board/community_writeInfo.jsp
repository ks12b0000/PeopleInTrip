<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("UTF-8");
%> 
 <c:set var="board" value="${boardMap.board }" />   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>정보게시판 > 글쓰기</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="../resources/css/community/community_write.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
			$('#textarea-box').on('keyup', function() {
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
   	<div id="header-jeh">
       <header>
		<jsp:include page="/header_lhj/header.jsp" flush="false" />
	</header>
    </div>
    <form action="${contextPath}/board/community_writeInfo.do"  method="post" enctype="multipart/form-data"  >
    <div class="wrapper">
        <!-- 왼쪽 메뉴바 -->
        <div>
            <div class="left-menu">
                <ul class="left-menu-ul">
                    <li class="menu-list"><a href=""><i class="fa-solid fa-bullhorn fa-lg"></i>정보게시판</a></li>
                    <li class="menu-list"><a href=""><i class="fa-solid fa-people-robbery fa-lg"></i>동행구해요</a></li>
                   
                </ul>
            </div>

            <!-- 정보게시판 본문 -->
            <div class="main-board">
                <!-- 정보게시판 > 글쓰기 -->
                <div>
                    <section class="content-first">
                            <p class="write-detail">정보게시판 > 글쓰기</p>
                    </section>
                </div>
                <!-- 정보게시판 제목입력, 내용입력 -->
                <div class="dropdown-top">
                    <select class="option-box">
                        <option selected disabled>선택</option>
                        <option>자유</option>
                        <option>질문</option>
                        <option>정보</option>
                    </select>
			
                    <span class="small-title"><b>제목</b></span>
					
                    <input class="text-box" type="text" placeholder="제목을 입력해 주세요!" name="post_title"  />

                    <textarea id="textarea-box" class="textarea-box" placeholder="내용을 입력해 주세요!" name="post_content"></textarea>
	                <div id="textarea-cnt">(0 / 200)</div>
	
	                <div class="bottom-btn">
                        <a href=""><input type="button" value="작성취소" /></a>
                        <input type="submit" value="작성완료" name="post_cate"/>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    </form>
</body>
</html>