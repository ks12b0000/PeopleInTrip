<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="board" value="${boardMap.board }" />
<c:set var="imageFileList" value="${boardMap.imageFileList }" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행구해요 > 수정하기</title>
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
				
				if($(this).val().length > 200) {
					$(this).val($(this).val().substring(0, 200));
					$('#textarea-cnt').html("(200 / 200)");
				}
			});
		});
    	
    	let pre_img_num = 0;			//기존 이미지 갯수 (수정 이전의 이미지 갯수)
		let img_index = 0;	
    	let isFirstAddImage = true
    	
		function fn_addModImage(_img_index) {
			
			if (isFirstAddImage == true) {
				pre_img_num = _img_index
				img_index = ++_img_index
				isFirstAddImage = false;
			}
			else {
				++img_index;
			}
			
			let innerHtml = "";
			
			innerHtml += '<tr width=200px align=center>'
			
			innerHtml += '<td>' +
								"<input type=file name='imageFileName"+img_index+"' onchange='readURL(this, "+img_index+")' />" +
						 '</td>'
			
			$("#td_addImage").append(innerHtml)		
			$("#added_img_num").val(img_index);		//추가된 이미지수를 hidden 속성의 태그에 저장해서 컨트롤러에 보냄
			console.log("here!")
		}
    	
    	function fn_removeModImage(_imageFileNO, post_num, _imageFileName) {
			
			$.ajax({
				type: "post",
				url: "${contextPath}/board/removeMod.do",
				dataType: "text",
				data: {imageFileNO: _imageFileNO, post_num: post_num, imageFileName: _imageFileName},
				success: function(result, textStatus) {
					if (result == 'success') {
						alert("이미지를 삭제했습니다.")
						location.href="${contextPath}/board/modBoard.do?removeCompleted=true&post_num=" + post_num;
						
					}
					else {
						alert("다시 시도해 주세요.")
					}
				},
				error: function(result, textStatus) {
					alert("에러가 발했습니다.")
				},
				complete: function(result, textStatus) {
				
				}
			})
		}
    	function readURL(input,index) {
			if (input.files && input.files[0]) {
				let reader = new FileReader()
				reader.onload = function(e) {
					$('#preview'+index).attr('src', e.target.result)
				}
				reader.readAsDataURL(input.files[0])
			}	
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
	<form action="${contextPath}/board/modBoard.do" method="post"
		enctype="multipart/form-data">
		<!-- 헤더 -->
		<div id="header-jeh">
			<header>
				<jsp:include page="/header/header.jsp" flush="false" />
			</header>
		</div>

		<div class="wrapper">
			<!-- 왼쪽 메뉴바 -->
			<div>
				<div class="left-menu">
					<ul class="left-menu-ul">
						<li class="menu-list"><a
							href="${contextPath }/board/community-info"><i
								class="fa-solid fa-bullhorn fa-lg"></i>정보게시판</a></li>
						<li class="menu-list" style="background-color: #9966ff;"><a
							href="${contextPath }/board/community-acco"><i
								class="fa-solid fa-people-robbery fa-lg"></i>동행구해요</a></li>
					</ul>
				</div>
				<input type="hidden" name="post_num" value="${board.post_num }">
				<input type="hidden" name="id" value="${board.id }">
				<!-- 동행구해요 본문 -->
				<div class="main-board">
					<!-- 동행구해요 > 글쓰기 -->
					<div>
						<section class="content-first">
							<p class="write-detail">동행구해요 > 수정하기</p>
						</section>
					</div>
					<!-- 동행구해요 제목입력, 내용입력 -->
					<div class="dropdown-top">
						<span class="small-title"><b>제목</b></span><br> <input
							class="text-box" type="text" placeholder="제목을 입력해 주세요!"
							name="post_title" value="${board.post_title }" />

						<textarea id="textarea-box" class="textarea-box"
							placeholder="내용을 입력해 주세요!" name="post_content">${board.post_content }</textarea>
						<div id="textarea-cnt">(0 / 200)</div>
						<br>
						<c:set var="img_index" />
						<div class="bottom-btn">
							<input type="button" value="취소"
								onclick="location.href='${contextPath}/board/community-acco.do'" />
							<input type="submit" value="수정하기" />
						</div>


						<c:set var="img_index" />
						<c:choose>
							<c:when
								test="${not empty imageFileList && imageFileList != 'null' }">
								<c:forEach var="item" items="${imageFileList }"
									varStatus="status">
									<div id="tr_${status.count }">

										<div>
											<!-- 이미지 수정시 미리 원래 이미지 파일이름을 저장함 -->
											<input type="hidden" name="oldFileName"
												value="${item.imageFileName }" /> <input type="hidden"
												name="imageFileNO" value="${item.imageFileNO }" /> <img
												alt="이미지"
												src="${contextPath}/download.do?imageFileName=${item.imageFileName}&post_num=${item.post_num}"
												id="preview${status.index }" width="100px" height="50px"><br />수정할
											이미지 선택<br>
										</div>
									</div>
									<div class="tr_modEable" id="tr_sub${status.count }">
										<br>

										<div>
											<input type="file" name="imageFileName${status.index }"
												id="imageFileName${status.index }"
												onchange="readURL(this, ${status.index})"><br>
											<input type="button" value="이미지 삭제하기"
												onclick="fn_removeModImage(${item.imageFileNO}, ${item.post_num }, '${item.imageFileName }')"
												style="background-color: #9966ff; width: 80px; height: 25px; border-radius: 5px; color: white; border: 2px solid #ffffff; outline: none; cursor: pointer; font-size: 10px;">
										</div>
									</div>
									<br>

									<c:if test="${status.last eq true}">
										<c:set var="img_index" value="${status.count }" />
										<input type="hidden" name="pre_img_num"
											value="${status.count }" />
										<!-- 기존의 이미지수 -->
										<input type="hidden" name="added_img_num" id="added_img_num"
											value="${status.count }" />
										<!-- 수정시 새로 추가된 이미지수 -->
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:set var="img_index" value="${0 }" />
								<input type="hidden" name="pre_img_num" value="${0 }" />
								<!-- 기존의 이미지수 -->
								<input type="hidden" name="added_img_num" id="added_img_num"
									value="${0 }" />
								<!-- 수정시 새로 추가된 이미지수 -->
							</c:otherwise>
						</c:choose>

						<div>
							<input type="button" value="이미지 추가"
								onclick="fn_addModImage(${img_index})"
								style="background-color: #9966ff; width: 80px; height: 25px; border-radius: 5px; color: white; border: 2px solid #ffffff; outline: none; cursor: pointer; font-size: 10px;" />
							<table id="td_addImage" align="center">
								<br>
							</table>
						</div>
					</div>
				</div>
			</div>
	</form>
</body>
</html>