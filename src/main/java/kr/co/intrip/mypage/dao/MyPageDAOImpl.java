package kr.co.intrip.mypage.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;

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
	@Override
	public void deleteMember(MyPageDTO mypageDTO) throws DataAccessException {
		sqlSession.delete("mapper.mypage.deleteMember", mypageDTO);
		
	}

	// 내가 쓴 글 리스트
	@Override
	public List<MyBoardDTO> selectMyBoard(String id) throws DataAccessException {
		List<MyBoardDTO> boardsList = sqlSession.selectList("mapper.mypage.select_My_Board", id);
		return boardsList;
	}

	// 내가 쓴 글 보기1
	@Override
	public MyBoardDTO selectMyBoardShow1(int post_num) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.select_My_Board_Show1", post_num);
	}

	// 내가 쓴 글 보기2
	@Override
	public MyBoardDTO selectMyBoardShow2(int post_num) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.select_My_Board_Show2", post_num);
	}

	// 페이징
	@Override
	public List<MyBoardDTO> list(Criteria cri) throws Exception {
		List<MyBoardDTO> boardsList = sqlSession.selectList("mapper.mypage.my_Board_List", cri);
		return boardsList;
	}

	// 게시물 갯수
	@Override
	public int listCount() throws Exception {
		return sqlSession.selectOne("mapper.mypage.my_List_Count");
	}


	



	
	
	



}
