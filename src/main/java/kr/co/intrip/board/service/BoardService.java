package kr.co.intrip.board.service;

import java.util.List;
import java.util.Map;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.ImageDTO;

public interface BoardService {

	public List<BoardDTO> listArticles() throws Exception;
	public List<BoardDTO> listArticles1() throws Exception;

	public Map<String, Object> viewdetail(int post_num);

	

	public int insertBoard(Map boardMap) throws Exception;

	public int insertBoard1(Map boardMap) throws Exception;
	public void modBoard(Map<String, Object> boardMap) throws Exception;
	public void removeModImage(ImageDTO imageDTO);
	void removeBoard(int post_num) throws Exception;
	

		
	
}
