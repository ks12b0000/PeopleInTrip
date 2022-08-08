package kr.co.intrip.mypage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.intrip.mypage.dto.MyPageDTO;

public interface MyPageService {

	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws Exception;
	
	// 비밀번호 변겅
	void update_MyPage_Pw(MyPageDTO mypageDTO) throws Exception;
	
	// 닉네임 변경
	void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws Exception;
	
	// 닉네임 검사
	public int selectNickChk(MyPageDTO mypageDTO) throws Exception;
	

	public void deleteMember(MyPageDTO myPageDTO) throws Exception;
}
