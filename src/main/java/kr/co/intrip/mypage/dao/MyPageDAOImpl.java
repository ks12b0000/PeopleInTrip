package kr.co.intrip.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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

@Repository("mypageDAO")
public class MyPageDAOImpl implements MyPageDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectMemberMyPage", mypageDTO);
	}

	// 비밀번호 변경
	@Override
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws DataAccessException {
		sqlSession.update("mapper.mypage.update_MyPage_Pw", mypageDTO);
	}

	// 닉네임 변경
	@Override
	public void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws DataAccessException {
		sqlSession.update("mapper.mypage.update_MyPage_nick_nm", mypageDTO);
		
	}

	// 닉네임 검사
	@Override
	public int selectNickChk(MyPageDTO mypageDTO) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.mypage.selectNickChk", mypageDTO);
		return result;
	}

	// 회원 탈퇴
	public void deleteMember(MemberDTO memberDTO) throws DataAccessException {
		sqlSession.delete("mapper.mypage.deleteMember", memberDTO);
		
	}

	// 내가 쓴 동행글
	@Override
	public List<BoardDTO> listfindcompany(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.listfindcompany", pagingDTO);
	}
	// 내가 쓴 동행글 검색어 갯수
	@Override
	public int findlistCompanyCount(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.findlistCompanyCount", pagingDTO);
	}
	
	
	// 내가 쓴 정보글
	@Override
	public List<MyBoardDTO> listfindinformation(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.mypage.listfindInformation", pagingDTO);
	}
	// 내가 쓴 정보글 갯수
	@Override
	public int findlistInfoCount(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.mypage.findlistInfoCount", pagingDTO);
	}


	// 내가 찜한 제주글 총 개수
	@Override
	public int getTotalSteamedCount(PagingDTO pagingDTO) throws Exception {
		 return  sqlSession.selectOne("mapper.mypage.getTotalSteamedCount", pagingDTO);
	}

	// 내가 찜한 제주여행지 페이징
	@Override
	public List<ApiDTO> mySteamedJeju(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedJeju", pagingDTO);
	}
	
	// 내가 찜한 제주축제 개수
	@Override
	public int getTotalSteamedFestivalCount(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.mypage.getTotalSteamedFestivalCount", pagingDTO);
	}
	// 내가 찜한 제주축제 페이징
	@Override
	public List<ApiDTO> mySteamedJejuFestival(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedJejuFestival", pagingDTO);
	}
	
	
	// 내가 찜한 제주전시회 개수
	@Override
	public int getTotalSteamedExhibitionCount(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.mypage.getTotalSteamedExhibitionCount", pagingDTO);
	}
	// 내가 찜한 제주전시회 페이징
	@Override
	public List<ApiDTO> mySteamedJejuExhibition(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedJejuExhibition", pagingDTO);
	}

	
	// 내가 찜한 부산 여행지 개수
	@Override
	public int getTotalSteamedCountBusanTravel(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.getTotalTravelSteamedCount", pagingDTO);
	}
	// 내가 찜한 부산 여행지 페이징
	@Override
	public List<BusanApiDTO> mySteamedBusanTravel(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedBusanTravel", pagingDTO);
	}
	

	// 내가 찜한 부산 체험지 개수
	@Override
	public int getTotalSteamedCountBusanExperience(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.getTotalExperienceSteamedCount", pagingDTO);
	}
	// 내가 찜한 부산 체험지 페이징
	@Override
	public List<BusanApiDTO> mySteamedBusanExperience(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedBusanExperience", pagingDTO);
	}
	
	
	// 내가 찜한 부산 축제 개수
	@Override
	public int getTotalSteamedCountBusanFestival(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.getTotalFestivalSteamedCount", pagingDTO);
	}
	// 내가 찜한 부산 축제 페이징
	@Override
	public List<BusanApiDTO> mySteamedBusanFestival(PagingDTO pagingDTO) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedBusanFestival", pagingDTO);
	}



















}
