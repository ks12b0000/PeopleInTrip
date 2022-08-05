package kr.co.intrip.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.ImageDTO;

public interface BoardDAO {
	public List<BoardDTO> selectAllBoardList() throws DataAccessException;

	public List<BoardDTO> selectAllBoardList1() throws DataAccessException;

	public BoardDTO selectBoard(int post_num) throws DataAccessException;

	public int insertBoard(Map boardMap);

	public void insertNewImage(Map boardMap);

	public List<ImageDTO> selectImageFileList(int post_num) throws DataAccessException;

	public BoardDTO selectpost_num(int post_num) throws DataAccessException;

	public int insertBoard1(Map boardMap) throws DataAccessException;

	public void updateImageFile(Map<String, Object> boardMap) throws DataAccessException;

	public void insertModNewImage(Map<String, Object> boardMap) throws DataAccessException;

	public void updateBoard(Map<String, Object> boardMap) throws DataAccessException;

	public void deleteModImage(ImageDTO imageDTO);

	public void deleteBoard(int post_num);

	public void visitcount(int post_num);
	
	public List<BoardDTO> list(Criteria cri) throws Exception;
	public int listCount() throws Exception;
}
