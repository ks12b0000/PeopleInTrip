package kr.co.intrip.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.login_signup.dto.MemberDTO;
import kr.co.intrip.mypage.dto.MyBoardDTO;

import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.Tourlist_SteamedDTO;


public interface MyPageDAO {

	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws DataAccessException;
	
	// 비밀번호 변경
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 닉네임 변경
	public void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 닉네임 검사
	public int selectNickChk(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 회원 탈퇴
	public void deleteMember(MemberDTO memberDTO) throws DataAccessException;
	
	
	// 내가 쓴 동행글
	public List<BoardDTO> listfindcompany(PagingDTO pagingDTO) throws DataAccessException;
	// 내가 쓴 동행글 검색어 갯수
	public int findlistCompanyCount(PagingDTO pagingDTO) throws DataAccessException;
	
	
	// 내가 쓴 정보글
	public List<MyBoardDTO> listfindinformation(PagingDTO pagingDTO) throws Exception;
	// 내가 쓴 정보글 검색어 갯수
	public int findlistInfoCount(PagingDTO pagingDTO) throws Exception;
	
	
	public List<MyBoardDTO> listfindInformationSearch(PagingDTO pagingDTO) throws Exception;
	public int findlistInfoSearchCount(SearchCriteria scri) throws Exception;
	
	
	// 내가 찜한 글 총 개수
	public int getTotalSteamedCount(PagingDTO pagingDTO) throws Exception;
	// 내가 찜한 제주여행지 페이징
	public List<ApiDTO> mySteamedJeju(PagingDTO pagingDTO) throws DataAccessException;
	
	
	// 내가 찜한 제주 축제 총 개수
	public int getTotalSteamedFestivalCount(PagingDTO pagingDTO) throws Exception;
	// 내가 찜한 제주 축제 페이징
	public List<ApiDTO> mySteamedJejuFestival(PagingDTO pagingDTO) throws DataAccessException;
	
	
	// 내가 찜한 제주 전시회 총 개수
	public int getTotalSteamedExhibitionCount(PagingDTO pagingDTO) throws Exception;
	// 내가 찜한 제주 전시회 페이징
	public List<ApiDTO> mySteamedJejuExhibition(PagingDTO pagingDTO) throws DataAccessException;
	
	
	// 내가 찜한 부산 여행지 개수
	public int getTotalSteamedCountBusanTravel(PagingDTO pagingDTO) throws DataAccessException;
	// 내가 찜한 부산 여행지 페이징
	public List<BusanApiDTO> mySteamedBusanTravel(PagingDTO pagingDTO) throws DataAccessException;
	
	
	// 내가 찜한 부산 체험지 개수
	public int getTotalSteamedCountBusanExperience(PagingDTO pagingDTO) throws DataAccessException;
	// 내가 찜한 부산 체험지 페이징
	public List<BusanApiDTO> mySteamedBusanExperience(PagingDTO pagingDTO) throws DataAccessException;
	

	// 내가 찜한 부산 축제 개수
	public int getTotalSteamedCountBusanFestival(PagingDTO pagingDTO) throws DataAccessException;
	// 내가 찜한 부산 축제 페이징
	public List<BusanApiDTO> mySteamedBusanFestival(PagingDTO pagingDTO) throws DataAccessException;

}
