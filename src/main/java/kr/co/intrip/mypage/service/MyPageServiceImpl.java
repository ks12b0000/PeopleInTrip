package kr.co.intrip.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountException;
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
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.Tourlist_SteamedDTO;

@Service("mypageService")
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO mypageDAO;

	// 회원정보 수정
	@Override
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws Exception {
		return mypageDAO.MyPage(mypageDTO, session);
	}

	// 비밀번호 변경
	@Override
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws Exception {
		mypageDAO.update_MyPage_Pw(mypageDTO);		
	}

	// 닉네임 변경
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
		
	// 내가 쓴 동행글 페이징
	@Override
	public List<BoardDTO> listfindcompany(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.listfindcompany(pagingDTO);
	}
	
	// 내가 쓴 동행글 갯수
	@Override
	public int findlistCompanyCount(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.findlistCompanyCount(pagingDTO);
	}
		
	// 내가 쓴 정보글
	@Override
	public List<MyBoardDTO> listfindinformation(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.listfindinformation(pagingDTO);
	}
	
	// 내가 쓴 정보글 갯수
	@Override
	public int findlistInfoCount(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.findlistInfoCount(pagingDTO);
	}
		
	// 내가 찜한 제주 여행지 카운트
	@Override
	public int getTotalSteamedCount(PagingDTO pagingDTO) throws Exception {
		 return mypageDAO.getTotalSteamedCount(pagingDTO);
	}
	
	// 내가 찜한 제주 여행지 페이징
	@Override
	public List<ApiDTO> mySteamedJeju(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.mySteamedJeju(pagingDTO);
	}
		
	// 내가 찜한 제주 페스티벌 페이징
	@Override
	public List<ApiDTO> mySteamedJejuFestival(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.mySteamedJejuFestival(pagingDTO);
	}
	
	// 내가 찜한 제주 페스티벌 총 개수
	@Override
	public int getTotalSteamedFestivalCount(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.getTotalSteamedFestivalCount(pagingDTO);
	}	
	
	// 내가 찜한 제주 전시회 총 개수
	@Override
	public int getTotalSteamedExhibitionCount(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.getTotalSteamedExhibitionCount(pagingDTO);
	}
	
	// 내가 찜한 제주 전시회 페이징
	@Override
	public List<ApiDTO> mySteamedJejuExhibition(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.mySteamedJejuExhibition(pagingDTO);
	}

	// 내가 찜한 부산 여행지 페이징
	@Override
	public List<BusanApiDTO> mySteamedBusanTravel(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.mySteamedBusanTravel(pagingDTO);
	}
	
	// 내가 찜한 부산 여행지 개수
	@Override
	public int getTotalSteamedCountBusanTravel(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.getTotalSteamedCountBusanTravel(pagingDTO);
	}
	
	// 내가 찜한 부산 체험지 페이징
	@Override
	public List<BusanApiDTO> mySteamedBusanExperience(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.mySteamedBusanExperience(pagingDTO);
	}
	
	// 내가 찜한 부산 체험지 개수
	@Override
	public int getTotalSteamedCountBusanExperience(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.getTotalSteamedCountBusanExperience(pagingDTO);
	}
	
	// 내가 찜한 부산 축제 페이징
	@Override
	public List<BusanApiDTO> mySteamedBusanFestival(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.mySteamedBusanFestival(pagingDTO);
	}
	
	// 내가 찜한 부산 축제 개수
	@Override
	public int getTotalSteamedCountBusanFestival(PagingDTO pagingDTO) throws Exception {
		return mypageDAO.getTotalSteamedCountBusanFestival(pagingDTO);
	}
}
