package kr.co.intrip.mypage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.intrip.mypage.dao.MyPageDAO;
import kr.co.intrip.mypage.dto.MyPageDTO;

@Service("mypageService")
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO mypageDAO;

	@Override
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws Exception {
		return mypageDAO.MyPage(mypageDTO, session);
	}

	@Override
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws Exception {
		mypageDAO.update_MyPage_Pw(mypageDTO);
		
	}

	@Override
	public void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws Exception {
		mypageDAO.update_MyPage_nick_nm(mypageDTO);
		
	}

	// 닉네임 검사
	@Override
	public int selectNickChk(MyPageDTO mypageDTO) throws Exception {
		int result = mypageDAO.selectNickChk(mypageDTO);
		return result;
	}

	// 회원 탈퇴
	@Override
	public void deleteMember(MyPageDTO myPageDTO) throws Exception {
		mypageDAO.deleteMember(myPageDTO);
		
	}




}
