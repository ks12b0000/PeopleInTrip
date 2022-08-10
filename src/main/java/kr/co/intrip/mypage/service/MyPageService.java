package kr.co.intrip.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;

public interface MyPageService {

	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws Exception;
	
	// 비밀번호 변겅
	void update_MyPage_Pw(MyPageDTO mypageDTO) throws Exception;
	
	// 닉네임 변경
	void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws Exception;
	
	// 닉네임 검사
	public int selectNickChk(MyPageDTO mypageDTO) throws Exception;
	
	// 회원 탈퇴
	public void deleteMember(MyPageDTO myPageDTO) throws Exception;
	
	// 내가 쓴 글
	public List<MyBoardDTO> listArticles(String id) throws Exception;
	
	// 내가 쓴 글 보기1
	public Map<String, Object> showMyBoard1(int post_num) throws Exception;
	
	// 내가 쓴 글 보기2
	public Map<String, Object> showMyBoard2(int post_num) throws Exception;
	
	public List<MyBoardDTO> list(Criteria cri) throws Exception;
	
	public int listCount() throws Exception;
} 
