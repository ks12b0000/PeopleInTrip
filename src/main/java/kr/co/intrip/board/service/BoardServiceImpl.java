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
import kr.co.intrip.board.dto.ImageDTO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	// 리스트
	@Override
	public List<BoardDTO> listArticles() throws Exception {
		List<BoardDTO> boardsList = boardDAO.selectAllBoardList();
		return boardsList;
	}

	// 리스트
	@Override
	public List<BoardDTO> listArticles1() throws Exception {
		List<BoardDTO> boardsList = boardDAO.selectAllBoardList1();
		return boardsList;
	}

	// 상세보기
	@Override
	public Map<String, Object> viewdetail(int post_num){
		Map<String, Object> boardMap = new HashMap<>();

		BoardDTO boardDTO = boardDAO.selectBoard(post_num);

		boardMap.put("board", boardDTO);
		return boardMap;
	}

	// 글쓰기
	@Override
	public int insertBoard(Map boardMap) throws Exception {

		int post_num = boardDAO.insertBoard(boardMap); // 글 정보를 저장한 후 글번호를 가져옴.
		boardMap.put("post_num", post_num);

		boardDAO.insertNewImage(boardMap); // 이미지 정보를 저장함

		return post_num;
	}

	// 글쓰기
	@Override
	public int insertBoard1(Map boardMap) throws Exception {

		int post_num = boardDAO.insertBoard1(boardMap); // 글 정보를 저장한 후 글번호를 가져옴.
		boardMap.put("post_num", post_num);

		boardDAO.insertNewImage(boardMap); // 이미지 정보를 저장함

		return post_num;

	}

	// 글수정
	@Override
	public void modBoard(Map<String, Object> boardMap) throws Exception {
		boardDAO.updateBoard(boardMap);

		List<ImageDTO> imageFileList = (List<ImageDTO>) boardMap.get("imageFileList");
		List<ImageDTO> modAddImageFileList = (List<ImageDTO>) boardMap.get("modAddImageFileList");

		if (imageFileList != null && imageFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) boardMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) boardMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) { // 기존 이미지도 수정하고 새 이미지도 추가한 경우
				boardDAO.updateImageFile(boardMap); // 기존 이미지 수정
				boardDAO.insertModNewImage(boardMap); // 새 이미지 추가
			} else { // 기존 이미지를 수정만 한 경우
				boardDAO.updateImageFile(boardMap);
			}
		}
		// 새 이미지를 추가한 경우
		else if (modAddImageFileList != null && modAddImageFileList.size() != 0)  {
			boardDAO.insertModNewImage(boardMap);
		}

	}
	
	//글이미지삭제
	@Override
	public void removeModImage(ImageDTO imageDTO) {
		boardDAO.deleteModImage(imageDTO);
		
	}
	
	//글삭제
	@Override
	public void removeBoard(int post_num) throws Exception {
		boardDAO.deleteBoard(post_num);
		
	}
	//조회수
	@Override
	public void visitcount(int post_num) throws Exception {
		boardDAO.visitcount(post_num);
		
	}
}