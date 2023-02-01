![header](https://user-images.githubusercontent.com/102012155/215965633-4ae17335-2cdb-4f27-8f1f-1f1addddbd3d.png)
# 프로젝트 People in Trip
 ## 국내 여행지 관광 명소, 축제, 전시, 체험 등을 추천해주고 사용자들이 여행 정보를 공유할 수 있는 서비스입니다.

---

# 📆   작업 기간 2022.05.30 ~ 2022.08.24

# 👩‍💻   팀원 구성
### 박현준, 김진하, 김민석

# 🎯 기술 스택
## Front-end 
<div>
<img alt="HTML5" src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white"/>
<img alt="JavaScript" src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E"/>
<img alt="jQuery" src="https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white"/>
<img alt="CSS3" src ="https://img.shields.io/badge/CSS-1572B6.svg?&style=for-the-badge&logo=CSS3&logoColor=white"/>
</div>

## Back-end 
 <div>
 <img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/>
 <img alt="Spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
 </div>


## Communication Tools
<div>
<img alt="GitHub" src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/>   
<img alt="Gather" src="https://img.shields.io/badge/Gather-%23121011.svg?style=for-the-badge&logo=Gather&logoColor=white"/>
<img alt="Notion" src="https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white"/>
</div>


## 📕 ERD
<img width="793" alt="스크린샷 2023-02-01 오후 3 45 58" src="https://user-images.githubusercontent.com/102012155/215971228-fbf95f2c-f616-4641-a111-03db81af5b40.png">



# 📄 기능 구현 사항
## 1. 메인 페이지
  * (1). 인기 여행지(찜 순) 배너 8개 슬라이드 형식 (이미지 클릭 시 상세페이지로 이동)
  * (2). 인기 축제,전시,체험(찜 순) 슬라이드 형식 (이미지 클릭 시 상세페이지로 이동)
  * (3). 게시판 조회순 5개씩 출력 (게시글 클릭 시 상세페이지로 이동, 더보기 클릭 시 게시판 으로 이동)
  * (4). 관광지 카테고리 (제주, 부산) 페이지로 이동
  * (5). 게시판 카테고리 (정보, 동행) 페이지로 이동
  * (6). 제주, 부산 카테고리 별 제목으로 검색 가능
  * (7). 로그인 페이지로 이동
  * (8). 회원가입 페이지로 이동
<img width="844" alt="메인페이지 전체 " src="https://user-images.githubusercontent.com/102012155/215995376-d524853b-f1bc-45e0-baaa-785973126bab.png">


## 2. 관광지 카테고리(제주, 부산)
  * (1). 여행지, 축제, 체험(제주는 전시로 변경) 카테고리 페이지로 이동 
  * (2). 제주, 부산 찜 순 배너 4개 슬라이드 형식 (이미지 클릭 시 상세페이지로 이동)
  * (3). 오늘의 날씨 09시 기준, 14시 기준, 20시 기준 현재 시간 대비로 출력
  * (4). 부산 지점 지도 출력
<img width="805" alt="부산 홈" src="https://user-images.githubusercontent.com/102012155/215997446-0eff7fde-f396-4472-b766-3082e8a8e0c4.png">


## 3. 관광지 카테고리 내부 여행지, 축제, 체험(제주는 전시로 변경) 카테고리
  * (1). 조회순, 기본순, 찜, 추천순 정렬 기능
  * (2). 썸네일 제목 주소 찜, 조회, 추천 개수 출력 (이미지, 제목 클릭 시 상세페이지로 이동)
  * (3). 게시글 10개 출력 페이징 처리(번호, 화살표 클릭 시 원하는 페이지 번호로 이동)
<img width="857" alt="부산 카테고리1" src="https://user-images.githubusercontent.com/102012155/215998688-d0e1e2e5-38c9-4500-a3d6-2c855ef70728.png">
<img width="793" alt="부산 카테고리2" src="https://user-images.githubusercontent.com/102012155/215998756-2676c317-db2b-4a21-bd4f-55e3cf7dcec8.png">



## 4. 관광지 상세페이지
  * (1). 좋아요, 추천 기능
  * (2). 썸네일, 카테고리, 주소, 전화번호, 태그, 상세정보, 위치 정보 출력
  * (3). 댓글 기능(등록, 수정,삭제 가능)
<img width="875" alt="부산 상세페이지1" src="https://user-images.githubusercontent.com/102012155/215999513-fc9a7174-0b3b-46c2-a69b-9ecfedca37c2.png">
<img width="836" alt="부산 상세페이지2" src="https://user-images.githubusercontent.com/102012155/215999526-dbc35bfa-8462-403a-a29b-2f751178a0e9.png">


## 5. 로그인
  * (1). 일반, 소셜 로그인 가능
  * (2). 아이디 찾기, 비밀번호 찾기, 회원가입 페이지로 이동 버튼
<img width="309" alt="로그인" src="https://user-images.githubusercontent.com/102012155/216000046-0157de3b-8e2c-4198-b989-4735141ec6bd.png">



## 6. 아이디, 비밀번호 찾기
  * (1). 아이디 찾기는 이름, 이메일 입력 후 인증번호 확인 시 아이디 출력
  * (2). 비밀번호 찾기는 아이디, 이메일 입력 후 인증번호 확인 시 새로운 비밀번호로 변경 가능
<img width="316" alt="아이디 찾기" src="https://user-images.githubusercontent.com/102012155/216000551-b0a1c27f-6d7c-4347-9261-28d8d0a9a685.png">
<img width="327" alt="비밀번호 찾기" src="https://user-images.githubusercontent.com/102012155/216000545-6db18111-ce08-4e6a-9fb8-7865d4ceb2f9.png">


## 7. 회원가입
  * (1). 아이디, 이름, 비밀번호, 비밀번호 확인, 닉네임, 이메일 입력
  * (2). 아이디, 닉네임은 중복확인 해야 함
  * (3). 이메일은 인증 성공 해야 함
  * (4). 위 내용들 완료 시 회원가입 성공
  <img width="818" alt="회원가입" src="https://user-images.githubusercontent.com/102012155/216001682-f1d11b36-867e-4570-bb00-33d848a48282.png">

  
## 8. 게시판
  * (1). 게시판 내에 제목, 제목 + 작성자로 검색 가능
  * (2). 제목 클릭 시 상세페이지로 이동
  <img width="1044" alt="게시판 1" src="https://user-images.githubusercontent.com/102012155/216002229-c872c105-5b52-464c-8c1f-b09d5ab52996.png">

 ## 9. 게시판 상세페이지 
  * (1). 찜, 추천 기능
  * (2). 본문 출력
  * (3). 댓글 기능(등록, 수정,삭제 가능)
  <img width="1186" alt="게시글 상세페이지" src="https://user-images.githubusercontent.com/102012155/216002479-fe3fae68-0e9d-4c2a-8372-e77fc8d28a01.png">

 ## 10. 게시판 글 쓰기
  * (1). 제목, 본문, 이미지 작성 가능
  <img width="836" alt="글쓰기" src="https://user-images.githubusercontent.com/102012155/216002703-a9739440-2064-409b-9b61-082867a9dddb.png">

  
 ## 11. 마이페이지
  * (1). 내가 쓴 글, 찜 누른 글 출력
  * (2). 정보수정, 회원탈퇴 가능
<img width="880" alt="마이페이지 내가쓴글" src="https://user-images.githubusercontent.com/102012155/216002978-23742c9c-026c-4ee8-8b9a-19e935bccd32.png">
<img width="906" alt="마이페이지 좋아요 누른 글" src="https://user-images.githubusercontent.com/102012155/216002987-b1800d86-d97f-44ab-aaa9-b698b1e01629.png">

  
 ## 12. 정보수정
  * (1). 비밀번호, 닉네임 변경 가능
  <img width="850" alt="정보 수정" src="https://user-images.githubusercontent.com/102012155/216003308-600e0365-238d-4907-b160-f8cc72784845.png">

  
  
  
  
  
  
  
  
  
  
