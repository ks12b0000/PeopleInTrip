package kr.co.intrip.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.ImageDTO;

public interface BoardDAO {
	public List<BoardDTO> selectAllBoardList() throws DataAccessException;

	public BoardDTO selectBoard(int post_num) throws DataAccessException;

	

	public int insertBoard(Map boardMap);

	public void insertNewImage(Map boardMap);

	public List<ImageDTO> selectImageFileList(int post_num) throws DataAccessException;

	public BoardDTO selectpost_num(int post_num) throws DataAccessException;

	



	
}
