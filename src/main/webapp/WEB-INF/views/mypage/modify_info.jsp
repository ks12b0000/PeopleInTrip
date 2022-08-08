<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../resources/css/mypage/mypage.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@600&display=swap" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet'>
    <title>회원 정보 수정</title>
    <script type="text/javascript">
    
    	function change_pwd() {
    		let pass = document.getElementById('input_pass').value;
    		let pass2 = document.getElementById('input_pass2').value;
			if(document.modify_info._pwd.value == "") {
				alert("비밀번호를 입력해주세요");
				document.modify_info._pwd.focus();
				return false;
			}
			
			if(pass.length < 8 || pass.length>16) {
				alert('비밀번호는 8글자 이상, 16글자 이하만 사용 가능합니다.');
				document.modify_info._pwd.focus();
				return false;
			} 
			
			if(document.modify_info._pwd.value != document.modify_info.pwd.value) {
				alert("비밀번호를 다시 확인해주세요");
				document.modify_info.pwd.focus();
				return false;
			} else {
				alert("비밀번호변경 완료!");
				document.modify_info.action="${contextPath}/mypage/update_modify_info";
				document.modify_info.method="post";
				document.modify_info.submit();
			} 
		}
    	function checkNick_Nm() {
    		
     		let specialCheck = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
    		
    		// 닉네임 입력값을 가져온다
    		let nick_nm = document.getElementById('input_nick').value;
    		nick_nm = nick_nm.toUpperCase();
    		let nickLength = 0;
    		
    		// 한글, 영문 길이 2,1로 바꾸기
    		// 한글은 2, 영문은 1로 치환한다
    		for (var i = 0; i < nick_nm.length; i++) {
    			// chartAt은 String 타입 객체를 char타입으로 변환한다. 
    			nick = nick_nm.charAt(i);
    			
    			// escape() 함수는 charAt을 통해 받아온 char을 16진수로 바꿔주며 쿠키충돌을 피한다. 한글은 이 길이가 4 넘는다.
    			if(escape(nick).length > 4) {
    				nickLength += 2;
    			} else {
    				nickLength += 1;
    			}
    		}
    		
    		// 닉네임 필수 입력
    		if (nick_nm == null || nick_nm == "") {
    			alert("닉네임을 입력해 주세요.");
    			document.modify_info.focus();
    			return false;
    		} 
    		// 닉네임 빈칸 포함 안됨
    		else if (nick_nm.search(/\s/) != -1) {
    			alert("닉네임은 빈 칸을 포함 할 수 없습니다.");
    			return false;
    		}
    		// 닉네임 한글 3~10자, 영문 및 숫자 6 ~ 20자
    		else if (nickLength < 6 || nickLength > 20) {
    			alert("닉네임은 한글 3~10자, 영문 및 숫자 6~20자 까지 가능합니다.");
    			return false;
    		}
    		// 닉네임 특수문자 포함 안됨 test()는 정규식과 특정 문자열 사이의 일치에 대한 검색을 수행, 일치 true, 불일치 false 반환
    		else if (specialCheck.test(nick_nm)) {
    			alert("닉네임은 특수문자를 포함 할 수 없습니다.");
    			return false;
    		} else {
    			$.ajax({
    				url : "${contextPath}/mypage/selectNickChk" ,
    				type : "post" ,
    				dataType: "JSON" ,
    				data : {"nick_nm" : nick_nm} ,
    				success : function(result) {
    					if(result != 1) {
    						alert("사용 가능한 닉네임 입니다.");
    						chkNick_Btn.style.display = 'none';
    						sucNick_Btn.style.display = 'block';
    					} 
    					else if(result == 1) {
    						alert("중복된 닉네임입니다.");
    					}
    				} ,
    				error : function(err) {
    					alert("중복된 아이디입니다.");
    				}
    			});
    		}
    	}
    	
    	function successNick_Nm() {
			alert("닉네임 변경이 완료되었습니다!");
			document.modify_info.action="${contextPath}/mypage/update_mypage_nick_nm";
			document.modify_info.method="post";
			document.modify_info.submit();
		}
    	
    	function success_modify() {
    		alert("수정이 완료되었습니다!");
			document.modify_info.action="${contextPath}/mypage/mypage_renewal";
			document.modify_info.method="post";
			document.modify_info.submit();
		}
    	
    </script>
</head>
<body>
    <header>
        <p class="p1">회원 정보 수정</p>
    </header>
    <section>
        <form action="${contextPath}/mypage/update_modify_info", method="post" name="modify_info">
            <table id="main_table2" border="1">
                <tr>
                    <td class="table2_td1"><strong>아이디</strong></td>
                    <td class="table2_td2">
                        <input type="text" id="input_id" name="id" value="${user.id}"  />
                    </td>
                    <td class="table2_td3"></td>
                    <td class="table2_td4" colspan="2"><span>아이디는 변경할 수 없습니다.</span></td>
                </tr>

                <tr>
                    <td class="table2_td1"><strong>이름</strong></td>
                    <td class="table2_td2">
                    	<input type="text" id="input_name" name="username" value="${user.name}" />
                    </td>
                    <td class="table2_td3"></td>
                    <td class="table2_td4" id="table2_nameinput" colspan="3">
                        <span>주민등록상 실명을 입력해주세요.</span>
                    </td>
                </tr>

                <tr>
                    <td class="table2_td1"><strong>비밀번호</strong></td>
                    <td class="table2_td2">
                        <input type="password" id="input_pass" name="_pwd" />
                    </td>
                    <td class="table2_td3"></td>
                    <td class="table2_td4" id="table2_tr2_td4" colspan="3">
                        <span>영문, 숫자, 특수문자를 조합하여<br/> 8~20자 이내로 입력하세요.</span>
                    </td>
                </tr>

                <tr>
                    <td class="table2_td1"><strong>비밀번호 <br/> 확인</strong></td>
                    <td class="table2_td2">
                        <input type="password" id="input_pass2" name="pwd" />
                    </td>
                    <td class="table2_td3"><button class="t2_btn" type="button" onclick="change_pwd()">비밀번호 변경</button></td>
                    <td class="table2_td3"></td>
                    <td class="table2_td4" colspan="3"><span id="hiddenspan_pass2">&nbsp;</span></td>
                </tr>

                <tr>
                    <td class="table2_td1"><strong>닉네임</strong></td>
                    <td class="table2_td2">
                        <input type="text" id="input_nick" name="nick_nm" value="${user.nick_nm }" /><div id="hiddendiv_nick"></div>
                    </td>
                    <td class="table2_td3">
                    	<button class="t2_btn" type="button" onclick="checkNick_Nm()" id="chkNick_Btn">중복<br/>확인</button>
                    	<button class="t2_btn" type="button" onclick="successNick_Nm()" id="sucNick_Btn">닉네임<br/>변경</button>
                    </td>
                    <td class="table2_td4" id="input_not3" colspan="2">
                        <span>한글, 영문, 숫자 사용이 가능합니다. <br/> 6~20자 이내로 입력하세요. <br/> 한글은 최대 10자까지 입력 가능합니다.</span>
                    </td>
                </tr>

                <tr id="email_tr">
                    <td class="table2_td1"><strong>이메일</strong></td>
                    <td id="table2_mtd1">
                        <div class="hiddendiv_email_sub">&nbsp;</div>
                        <input class="mailinput" id="mailinput1" name="mailinput1" value="${user.email}" type="text"/>
                        <div class="hiddendiv_email">&nbsp;</div>
                    </td>

                </tr>
                <tr id="last_tr">
                    <td colspan="5">
                        <button class="t2_btn" id="submit_btn" type="button" onclick="success_modify()">수정하기</button>
                        <!-- <input type="reset" value="다시 작성" /> -->
                    </td>
                </tr>
            </table>
        </form>
    </section>

</body>
</html>