package kr.co.intrip.board.service;

import java.util.List;
import java.util.Map;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.ImageDTO;
import kr.co.intrip.board.dto.SearchCriteria;

public interface BoardService {

	public Map<String, Object> viewdetail(int post_num);
	
	public int insertBoard(Map boardMap) throws Exception;

	public int insertBoard1(Map boardMap) throws Exception;
	
	public void modBoard(Map<String, Object> boardMap) throws Exception;
	
	public void removeModImage(ImageDTO imageDTO);
	
	public void removeBoard(int post_num) throws Exception;
	
	public void visitcount(int post_num) throws Exception;
	
	public List<BoardDTO> listfind(SearchCriteria scri) throws Exception;
	
	public int findlistCount(SearchCriteria scri) throws Exception;
	
	public List<BoardDTO> listfind1(SearchCriteria scri) throws Exception;
	
	public int findlistCount1(SearchCriteria scri) throws Exception;

	public void removeBoard1(int post_num) throws Exception;

	public void modBoard1(Map<String, Object> boardMap) throws Exception;

	public Map<String, Object> viewdetail1(int post_num);

	public void visitcount1(int post_num) throws Exception;

	public void deleteLike(int post_num, String id) throws Exception;

	public void updateLike(int post_num) throws Exception;

	public void updateLikeCancel(int post_num) throws Exception;

	public void insertLike(int post_num, String id) throws Exception;

	public int likeCheck(int post_num, String id) throws Exception;

	public void updateLikeCheck(int post_num, String id) throws Exception;

	public void updateLikeCheckCancel(int post_num, String id) throws Exception;

	public void updateLike1(int post_num) throws Exception;

	public void updateLikeCancel1(int post_num) throws Exception;

	public void updateLikeCheckCancel1(int post_num, String id) throws Exception;

	public void updateLikeCheck1(int post_num, String id) throws Exception;

	public int likeCheck1(int post_num, String id) throws Exception;

	public void deleteLike1(int post_num, String id) throws Exception;

	public void insertLike1(int post_num, String id) throws Exception;

	

	
}
