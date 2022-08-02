package kr.co.intrip.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.intrip.board.dao.BoardDAO;
import kr.co.intrip.board.dto.BoardDTO;



@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardDTO> listArticles() throws Exception {
		List<BoardDTO> boardsList = boardDAO.selectAllBoardList();
		return boardsList;
	}
	
	@Override
	public List<BoardDTO> listArticles1() throws Exception {
		List<BoardDTO> boardsList = boardDAO.selectAllBoardList1();
		return boardsList;
	}



	@Override
	public Map<String, Object> viewdetail(int post_num) {
		Map<String, Object> boardMap = new HashMap<>();
		
		BoardDTO boardDTO = boardDAO.selectBoard(post_num);
		
		boardMap.put("board", boardDTO);
		return boardMap;
	}


	


	@Override
	public int insertBoard(Map boardMap) throws Exception {
		
		int post_num = boardDAO.insertBoard(boardMap);		//글 정보를 저장한 후 글번호를 가져옴.
		boardMap.put("post_num", post_num);						
		
		boardDAO.insertNewImage(boardMap);						//이미지 정보를 저장함
		
		return post_num;
	}
		
		@Override
		public int insertBoard1(Map boardMap) throws Exception {
			
			int post_num = boardDAO.insertBoard1(boardMap);		//글 정보를 저장한 후 글번호를 가져옴.
			boardMap.put("post_num", post_num);						
			
			boardDAO.insertNewImage(boardMap);						//이미지 정보를 저장함
			
			return post_num;
	
	}


	

	

	
}