<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
            font-size: 25px;
            font-weight: 500;
            padding: 15px;
            border-bottom: solid 2px black;
            /* border: solid 1px red; */
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

        

    </style>

</head>
<body>
<form action="${contextPath }/mypage/mypage_renewal_test?id=${user.id}" method="POST">
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
    <p class="cls1">내가 쓴 글</p>
    <hr/>
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
	            <c:forEach var="myboardsList" items="${myboardsList}" begin="0" end="4">
	            <tr align="center">
	                <td>${myboardsList.post_num }</td>
	                <td>${myboardsList.post_title }</td>
	                <td>${myboardsList.id }</td>
	                <td>${myboardsList.post_date }</td>
	                <td>${myboardsList.post_cate }</td>
	                <td>${myboardsList.visitcount }</td>
	            </tr>
	            </c:forEach>
	        </table>
        <div align="center">페이징</div>
        <div class="search">
            <select>
                <option>제목</option>
                <option>내용</option>
                <option>제목+내용</option>
            </select>
            <input type="text" />
            <input type="submit" value="검색" class="search_btn"/>
        </div>
    </div>
    <br/><br/>
    <p class="cls1">찜한 내역</p>
    <hr/>
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
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>    
                </tr>  
                <tr align="center">
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>    
                </tr>  
                <tr align="center">
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>
                    <td>
                        <div>
                            <img src="apple.jfif" width="200px" height="120px">
                        </div>
                        <div class="title">
                            [사과] 사과입니다.
                        </div>
                    </td>    
                </tr>  
            </table>
        </div>
        <div align="center">페이징</div>
    </div>
        </form>
</body>
</html>