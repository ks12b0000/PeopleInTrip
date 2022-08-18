package kr.co.intrip.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.login_signup.dto.MemberDTO;
import kr.co.intrip.mypage.dao.MyPageDAO;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.Tourlist_SteamedDTO;

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
	public void deleteMember(MemberDTO memberDTO) throws Exception {
		mypageDAO.deleteMember(memberDTO);
		
	}
	
	// 내가 쓴 동행글
	@Override
	public List<MyBoardDTO> listfindcompany(SearchCriteria scri) throws Exception {
		List<MyBoardDTO> boardsList = mypageDAO.listfindcompany(scri);
		return boardsList;
	}
	// 내가 쓴 정보글
	@Override
	public List<MyBoardDTO> listfindinformation(SearchCriteria scri) throws Exception {
		List<MyBoardDTO> boardsList2 = mypageDAO.listfindinformation(scri);
		return boardsList2;
	}
	// 내가 쓴 동행글 검색어 갯수
	@Override
	public int findlistCompanyCount(SearchCriteria scri) throws Exception {
		return mypageDAO.findlistCompanyCount(scri);
	}
	// 내가 쓴 정보글 검색어 갯수
	@Override
	public int findlistInfoCount(SearchCriteria scri2) throws Exception {
		return mypageDAO.findlistInfoCount(scri2);
	}

	@Override
	public List<ApiDTO> listMyTour(String id) throws Exception {
		List<ApiDTO> boardsTour = mypageDAO.selectMyTour(id);
		return boardsTour;
	}

	@Override
	public int getTotalSteamedCount(SearchCriteria scri, String id) throws Exception {
		System.out.println("들어온 totalCount : " + scri + "들어온 id : " + id);
		 return mypageDAO.getTotalSteamedCount(scri, id);
	}
	@Override
	public Tourlist_SteamedDTO getTotalSteamedId(String id) throws Exception {
		return mypageDAO.getTotalSteamedId(id);
		
	}
//	@Override
//	public HashMap<String, Object> getTotalSteamedCount(String id) throws Exception {
//		return mypageDAO.getTotalSteamedCount(id);
//	}


	@Override
	public List<ApiDTO> mySteamedJeju(SearchCriteria scri) throws Exception {
		return mypageDAO.mySteamedJeju(scri);
	}















}
