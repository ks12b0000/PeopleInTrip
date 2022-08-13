package kr.co.intrip.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.mypage.dao.MyPageDAO;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;

@Service("mypageService")
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO mypageDAO;

	@Override
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws Exception {
		return mypageDAO.MyPage(mypageDTO, session);
	}

	@Override
	public void update_MyPage_Pw(MyPageDTO mypageDTO) throws Exception {
		mypageDAO.update_MyPage_Pw(mypageDTO);
		
	}

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
	public void deleteMember(MyPageDTO myPageDTO) throws Exception {
		mypageDAO.deleteMember(myPageDTO);
		
	}

	// 내가 쓴 글
	@Override
	public List<MyBoardDTO> listArticles(String id) throws Exception {
		List<MyBoardDTO> boardsList = mypageDAO.selectMyBoard(id);
		return boardsList;
	}

	// 내가 쓴 글 보기1
	@Override
	public Map<String, Object> showMyBoard1(String post_title) throws Exception {
		Map<String, Object> boardMap = new HashMap<>();
		
		MyBoardDTO myboardDTO = mypageDAO.selectMyBoardShow1(post_title);
		
		boardMap.put("board", myboardDTO);
		
		return boardMap;
	}
	
	// 페이징 검색
	@Override
	public List<MyBoardDTO> listfind(SearchCriteria scri) throws Exception {
		List<MyBoardDTO> boardsList = mypageDAO.listfind(scri);
		return boardsList;
	}
	
	// 게시물 갯수 검색
	@Override
	public int findlistCount(SearchCriteria scri) throws Exception {
		return mypageDAO.findlistCount(scri);
	}

//	// 페이징
//	@Override
//	public List<MyBoardDTO> list(Criteria cri) throws Exception {
//		List<MyBoardDTO> boardsList = mypageDAO.list(cri);
//		return boardsList;
//	}
//
//	// 게시물 갯수
//	@Override
//	public int listCount() throws Exception {
//		return mypageDAO.listCount();
//	}
	
	







}
