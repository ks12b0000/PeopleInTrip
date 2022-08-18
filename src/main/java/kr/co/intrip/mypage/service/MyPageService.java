package kr.co.intrip.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.Tourlist_SteamedDTO;

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
	
	// 내가 쓴 동행글
	public List<MyBoardDTO> listfindcompany(SearchCriteria scri) throws Exception;
	// 내가 쓴 정보글
	public List<MyBoardDTO> listfindinformation(SearchCriteria scri) throws Exception;
	// 내가 쓴 동행글 검색어 갯수
	public int findlistCompanyCount(SearchCriteria scri) throws Exception;
	// 내가 쓴 정보글 검색어 갯수
	public int findlistInfoCount(SearchCriteria scri2) throws Exception;
	
	public List<ApiDTO> listMyTour(String id) throws Exception;
	
	// 내가 찜한 글 총 개수
	public int getTotalSteamedCount(SearchCriteria scri, String id) throws Exception;
	public Tourlist_SteamedDTO getTotalSteamedId(String id) throws Exception;
//	public HashMap<String, Object> getTotalSteamedCount(String id) throws Exception;
	
	// 내가 찜한 제주여행지 페이징
	public List<ApiDTO> mySteamedJeju(SearchCriteria scri) throws Exception;
	
} 
