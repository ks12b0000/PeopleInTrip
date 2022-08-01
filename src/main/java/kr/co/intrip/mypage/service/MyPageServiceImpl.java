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

//	@Override
//	public List<MyPageDTO> listMember() throws Exception {
//		List<MyPageDTO> memberList = mypageDAO.selectMemberMyPage();
//		return memberList;
//	}
	


}
