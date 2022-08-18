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
	public List<MyBoardDTO> listfindcompany(SearchCriteria scri) throws Exception {
		List<MyBoardDTO> boardsList = sqlSession.selectList("mapper.mypage.listfindcompany", scri);
		return boardsList;
	}
	// 내가 쓴 정보글
	@Override
	public List<MyBoardDTO> listfindinformation(SearchCriteria scri) throws Exception {
		List<MyBoardDTO> boardsList2 = sqlSession.selectList("mapper.mypage.listfindInformation", scri);
		return boardsList2;
	}
	// 내가 쓴 동행글 검색어 갯수
	@Override
	public int findlistCompanyCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("mapper.mypage.findlistCompanyCount");
	}
	// 내가 쓴 정보글 검색어 갯수
	@Override
	public int findlistInfoCount(SearchCriteria scri2) throws Exception {
		return sqlSession.selectOne("mapper.mypage.findlistInfoCount");
	}

	// 내가 찜 한 글
	@Override
	public List<ApiDTO> selectMyTour(String id) throws DataAccessException {
		List<ApiDTO> selectMyTour = sqlSession.selectList("mapper.mypage.mySteamed", id);
		return selectMyTour;
	}

	// 내가 찜한 글 총 개수
	@Override
	public int getTotalSteamedCount(SearchCriteria scri, String id) throws Exception {
		System.out.println("들어온 totalCount : " + scri + "들어온 id : " + id);
		 return  sqlSession.selectOne("mapper.mypage.getTotalSteamedCount");
	}
	@Override
	public Tourlist_SteamedDTO getTotalSteamedId(String id) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.getTotalSteamedCount", id);
		 
	}

	// 내가 찜한 제주여행지 페이징
	@Override
	public List<ApiDTO> mySteamedJeju(SearchCriteria scri) throws DataAccessException {
		return sqlSession.selectList("mapper.mypage.mySteamedJeju", scri);
	}














}
