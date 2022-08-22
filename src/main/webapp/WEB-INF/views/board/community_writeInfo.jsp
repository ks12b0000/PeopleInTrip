<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link rel="stylesheet"
	href="../resources/css/community/community_write.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#textarea-box').on('keyup', function() {
			$('#textarea-cnt').html("(" + $(this).val().length + " / 200)");

			if ($(this).val().length > 200) {
				$(this).val($(this).val().substring(0, 200));
				$('#textarea-cnt').html("(200 / 200)");
			}
		});
	});

	function readURL(input, index) {
		if (input.files && input.files[0]) {
			let reader = new FileReader()
			reader.onload = function(e) {
				$('#preview0').attr('src', e.target.result)
			}
			reader.readAsDataURL(input.files[0])
		}
	}

	let cnt = 1
	function fn_addFile() {
		cnt++;
		let innerHtml = "";

		innerHtml += '<tr width=100% align=center>'

		innerHtml += '<td>' + "<input type=file name='file" + cnt
				+ "' onchange='readURL(this, " + cnt + ")' />" + '</td>'

		innerHtml += '</tr>'
		$("#tb_newImage").append(innerHtml)
	}
</script>
<style type="text/css">
input[type=file]::file-selector-button {
	background-color: #9966ff;
	width: 80px;
	height: 25px;
	border-radius: 5px;
	color: white;
	border: 2px solid #ffffff;
	outline: none;
	cursor: pointer;
	font-size: 10px;
}
</style>
</head>
<body>
	<!-- 헤더 -->
	<div id="header-jeh">
		<header>
			<jsp:include page="/header/header.jsp" flush="false" />
		</header>
	</div>
	<form action="${contextPath}/board/community_writeWith.do"
		method="post" enctype="multipart/form-data">
		<div class="wrapper">
			<!-- 왼쪽 메뉴바 -->
			<div>
				<div class="left-menu">
					<ul class="left-menu-ul">
						<li class="menu-list" style="background-color: #9966ff;"><a
							href="${contextPath }/board/community-info"><i
								class="fa-solid fa-bullhorn fa-lg"></i>정보게시판</a></li>
						<li class="menu-list"><a
							href="${contextPath }/board/community-acco"><i
								class="fa-solid fa-people-robbery fa-lg"></i>동행구해요</a></li>

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


						<span class="small-title"><b>제목</b></span> <input class="text-box"
							type="text" placeholder="제목을 입력해 주세요!" name="post_title" />

						<textarea id="textarea-box" class="textarea-box"
							placeholder="내용을 입력해 주세요!" name="post_content"></textarea>
						<div id="textarea-cnt">(0 / 200)</div>

						<div class="bottom-btn">
							<input type="button" value="작성취소"
								onclick="location.href='${contextPath}/board/community-info.do'" />
							<input type="submit" value="작성완료" />
						</div>

						<div>
							<input type="button" value="파일 추가하기" onclick="fn_addFile()"
								style="background-color: #9966ff; width: 80px; height: 25px; border-radius: 5px; color: white; border: 2px solid #9966ff; outline: none; cursor: pointer; font-size: 11px;" />
							<div id="tb_newImage" />

						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>