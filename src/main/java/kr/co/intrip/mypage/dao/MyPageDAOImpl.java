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
//	
//	@Override
//	public List<MyPageDTO> selectMemberMyPage() throws DataAccessException {
//		List<MyPageDTO> mainList = sqlSession.selectList("mapper.mypage.selectMemberMyPage");
//		return mainList;
//	}

	@Override
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectMemberMyPage", mypageDTO);
	}




	


}
