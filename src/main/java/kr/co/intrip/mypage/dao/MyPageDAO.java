package kr.co.intrip.mypage.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;

public interface MyPageDAO {

	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws DataAccessException;
	
	// 비밀번호 변경
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 닉네임 변경
	public void update_MyPage_nick_nm(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 닉네임 검사
	public int selectNickChk(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 회원 탈퇴
	public void deleteMember(MyPageDTO mypageDTO) throws DataAccessException;
	
	// 내가 쓴 글 리스트
	public List<MyBoardDTO> selectMyBoard(String id) throws DataAccessException;
	
	// 내가 쓴 글 보기1
	public MyBoardDTO selectMyBoardShow1(int post_num) throws DataAccessException;
	
	// 내가 쓴 글 보기2
	public MyBoardDTO selectMyBoardShow2(int post_num) throws DataAccessException;
	
	public List<MyBoardDTO> list(Criteria cri) throws Exception;
	
	public int listCount() throws Exception;
}
