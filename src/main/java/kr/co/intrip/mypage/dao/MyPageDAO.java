package kr.co.intrip.mypage.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.mypage.dto.MyPageDTO;

public interface MyPageDAO {

//	public List<MyPageDTO> selectMemberMyPage() throws DataAccessException;
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws DataAccessException;
}
