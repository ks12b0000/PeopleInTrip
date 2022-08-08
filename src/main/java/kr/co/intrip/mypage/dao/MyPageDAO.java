package kr.co.intrip.mypage.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.mypage.dto.MyPageDTO;

public interface MyPageDAO {

	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws DataAccessException;
	
	// 비밀번호 변경
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 닉네임 변경
	public void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 닉네임 검사
	public int selectNickChk(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 회원 탈퇴
	public void deleteMember(MyPageDTO mypageDTO) throws DataAccessException;
	
	
}
