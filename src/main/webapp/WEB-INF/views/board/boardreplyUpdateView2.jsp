<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
	<div id="comment-count" style=" text-align: center; margin-top: 30px; font-size: 24px; color: #9966ff"><strong>댓글 수정하기</strong></div>
	<hr align="left" style="border: solid 3px #D8D8D8;  width: 100%;">
	<div style=" text-align: center; font-size: 20px;"><strong>글 번호 : </strong>${replyUpdate.post_num}</div>	
		<section id="container" style=" text-align: center; margin-top: 15px;">
				<form name="updateForm2" role="form" method="post" action="${contextPath}/board/boardreplyUpdate2">
					<input type="hidden" name="post_num" value="${replyUpdate.post_num}" readonly="readonly"/>
					<input type="hidden" id="reply_number" name="com_num" value="${replyUpdate.com_num}" />				
						<label for="replytext" style="font-size: 20px; position:relative; resize: none;"><strong>댓글 내용 : </strong></label><br><textarea rows="content" name="com_content" id="comment_input" style="outline: none; text-align: left; padding-left:10px; height: 100px; resize: none; width: 400px; ">${replyUpdate.com_content}</textarea><div id="textarea-cnt">(0 / 200)</div>				
					<div>
						<button type="submit" class="update_btn" style="cursor: pointer; margin-top:15px; border: 1px solid #9966ff; color: #9966ff; width: 80px; height: 50px; background-color: white; font-size:16px;"><strong>저장</strong></button>
						<button type="button" class="cancel_btn" style="cursor: pointer; margin-top:15px; border: 1px solid #9966ff; color: #9966ff; width:  80px; height: 50px; background-color: white; font-size:16px;"><strong>취소</strong></button>
					</div>
				</form>
		</section>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function() {
				location.href = "${contextPath}/board/community_detail2.do?post_num=${replyUpdate.post_num}";
			})
		})
	</script>
	<script>
		$(document).ready(function() {
			$(".update_btn").on("click", function() {
				alert("저장이 완료되었습니다.");
			})
		})
	</script>
	<script type="text/javascript">
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
</html>