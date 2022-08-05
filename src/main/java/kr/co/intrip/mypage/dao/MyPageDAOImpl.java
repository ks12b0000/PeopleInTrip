package kr.co.intrip.mypage.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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



	
	
	



}
