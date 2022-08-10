package kr.co.intrip.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.ImageDTO;
import kr.co.intrip.board.dto.SearchCriteria;

public interface BoardDAO {

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
		
	public List<BoardDTO> listfind(SearchCriteria scri) throws Exception;
	
	public int findlistCount(SearchCriteria scri) throws Exception;

	public List<BoardDTO> listfind1(SearchCriteria scri) throws Exception;

	public int findlistCount1(SearchCriteria scri) throws Exception;

	public BoardDTO selectBoard(int post_num) throws DataAccessException;
	
	public BoardDTO selectBoard1(int post_num) throws DataAccessException;

	public void updateBoard1(Map<String, Object> boardMap) throws DataAccessException;

	public void deleteBoard1(int post_num);

	public void visitcount1(int post_num);

	public void updateLikeCheck(int post_num, String id) throws Exception;

	public int likeCheck(int post_num, String id) throws Exception;

	public void updateLikeCheckCancel(int post_num, String id) throws Exception;

	public void deleteLike(int post_num, String id) throws Exception;

	public void insertLike(int post_num, String id) throws Exception;

	public void updateLikeCancel(int post_num) throws Exception;

	public void updateLike(int post_num) throws Exception;

	public void updateLike1(int post_num) throws Exception;

	public void updateLikeCancel1(int post_num) throws Exception;

	public void insertLike1(int post_num, String id) throws Exception;

	public void deleteLike1(int post_num, String id) throws Exception;

	public int likeCheck1(int post_num, String id) throws Exception;

	public void updateLikeCheck1(int post_num, String id) throws Exception;

	public void updateLikeCheckCancel1(int post_num, String id) throws Exception;

	
}
