package kr.co.intrip.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.CommentPagingDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.ImageDTO;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.board.dto.boardCommentDTO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;

	// 페이징 검색 1
	@Override
	public List<BoardDTO> listfind1(SearchCriteria scri) throws Exception {
		List<BoardDTO> boardsList = sqlSession.selectList("mapper.board.listfind1", scri);
		return boardsList;
	}

	// 게시물 갯수 검색 1
	@Override
	public int findlistCount1(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("mapper.board.findlistCount1");
	}

	// 조회수
	@Override
	public void visitcount(int post_num) {
		sqlSession.update("mapper.board.visitcount", post_num);

	}

	// 조회수
	@Override
	public void visitcount1(int post_num) {
		sqlSession.update("mapper.board.visitcount1", post_num);

	}

	// 게시물 갯수 검색
	@Override
	public int findlistCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("mapper.board.findlistCount");
	}

	// 페이징 검색
	@Override
	public List<BoardDTO> listfind(SearchCriteria scri) throws Exception {
		List<BoardDTO> boardsList = sqlSession.selectList("mapper.board.listfind", scri);
		return boardsList;
	}

	// 상세보기1
	@Override
	public BoardDTO selectBoard1(int post_num) throws DataAccessException {

		return sqlSession.selectOne("mapper.board.selectBoard1", post_num);
	}

	// 상세보기
	@Override
	public BoardDTO selectBoard(int post_num) throws DataAccessException {

		return sqlSession.selectOne("mapper.board.selectBoard", post_num);
	}

	// 글쓰기
	@Override
	public int insertBoard(Map boardMap) {
		int post_num = selectNewpost_num();
		boardMap.put("post_num", post_num);

		sqlSession.insert("mapper.board.insertBoard", boardMap);
		return post_num;
	}

	// 글쓰기1
	@Override
	public int insertBoard1(Map boardMap) {
		int post_num = selectNewpost_num1();
		boardMap.put("post_num", post_num);

		sqlSession.insert("mapper.board.insertBoard1", boardMap);
		return post_num;
	}

	// 추가하는 새글에 대한 글번호 가져옴
	private int selectNewpost_num() {
		return sqlSession.selectOne("mapper.board.selectNewpost_num");
	}

	// 추가하는 새글에 대한 글번호 가져옴
	private int selectNewpost_num1() {
		return sqlSession.selectOne("mapper.board.selectNewpost_num1");
	}

	//이미지 추가
	@Override
	public void insertNewImage(Map boardMap) {
		List<ImageDTO> imageFileList = (List<ImageDTO>) boardMap.get("imageFileList");
		int post_num = (Integer) boardMap.get("post_num"); // boardMap이 글 번호를 가져옴

		int imageFileNO = selectNewImageFileNO(); // 이미지 번호를 가져옴

		if (imageFileList != null && imageFileList.size() != 0) {
			// ImageDTO 객체를 차례대로 가져와 이미지번호와 글번호 속성을 설정함
			for (ImageDTO imageDTO : imageFileList) {
				imageDTO.setImageFileNO(++imageFileNO);
				imageDTO.setpost_num(post_num);
			}

			// T_IMAGEFILE 테이블에 INSERT
			sqlSession.insert("mapper.board.insertNewImage", imageFileList);
		}

	}
	//새 이미지파일 번호 추가
	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}

	@Override
	public BoardDTO selectpost_num(int post_num) throws DataAccessException {

		return sqlSession.selectOne("mapper.board.selectpost_num", post_num);
	}

	@Override
	public List<ImageDTO> selectImageFileList(int post_num) throws DataAccessException {

		List<ImageDTO> imageFileList = sqlSession.selectList("mapper.board.selectImageFileList", post_num);

		return imageFileList;
	}

	@Override
	public void updateImageFile(Map<String, Object> boardMap) throws DataAccessException {
		List<ImageDTO> imageFileList = (List<ImageDTO>) boardMap.get("imageFileList");
		int post_num = Integer.parseInt((String) boardMap.get("post_num"));

		for (int i = imageFileList.size() - 1; i >= 0; i--) {
			ImageDTO imageDTO = imageFileList.get(i);
			String imageFileName = imageDTO.getImageFileName();
			if (imageFileName == null) { // 기존 이미지를 수정하지 않는 경우는 수정할 필요없음
				imageFileList.remove(i);
			} else {
				imageDTO.setpost_num(post_num);
			}
		}

		if (imageFileList != null && imageFileList.size() != 0) {
			sqlSession.update("mapper.board.updateImageFile", imageFileList); // 수정한 이미지만 갱신함
		}

	}

	// 글 수정 이미지
	@Override
	public void insertModNewImage(Map<String, Object> boardMap) throws DataAccessException {

		List<ImageDTO> modAddImageFileList = (List<ImageDTO>) boardMap.get("modAddImageFileList");
		int post_num = Integer.parseInt((String) boardMap.get("post_num"));

		int imageFileNO = selectNewImageFileNO();

		for (ImageDTO imageDTO : modAddImageFileList) {
			imageDTO.setpost_num(post_num);
			imageDTO.setImageFileNO(++imageFileNO);
		}

		sqlSession.insert("mapper.board.insertModNewImage", modAddImageFileList);

	}

	// 글 수정
	@Override
	public void updateBoard(Map<String, Object> boardMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateBoard", boardMap);

	}

	// 글 수정1
	@Override
	public void updateBoard1(Map<String, Object> boardMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateBoard1", boardMap);

	}

	// 글 이미지 삭제
	@Override
	public void deleteModImage(ImageDTO imageDTO) {
		sqlSession.delete("mapper.board.deleteModImage", imageDTO);

	}

	// 글삭제
	@Override
	public void deleteBoard(int post_num) {
		sqlSession.delete("mapper.board.deleteBoard", post_num);

	}

	// 글삭제1
	@Override
	public void deleteBoard1(int post_num) {
		sqlSession.delete("mapper.board.deleteBoard1", post_num);

	}

	// 추천

	@Override
	public void updateLike(int post_num) throws Exception {
		sqlSession.update("mapper.board.updateLike", post_num);
	}

	@Override
	public void updateLikeCancel(int post_num) throws Exception {
		sqlSession.update("mapper.board.updateLikeCancel", post_num);

	}

	@Override
	public void insertLike(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.insert("mapper.board.insertLike", map);
	}

	@Override
	public void deleteLike(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.delete("mapper.board.deleteLike", map);
	}

	@Override
	public int likeCheck(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		return sqlSession.selectOne("mapper.board.likeCheck", map);
	}

	@Override
	public void updateLikeCheck(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updateLikeCheck", map);

	}

	@Override
	public void updateLikeCheckCancel(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updateLikeCheckCancel", map);
	}

	// 추천 1
	@Override
	public void updateLike1(int post_num) throws Exception {
		sqlSession.update("mapper.board.updateLike1", post_num);
	}

	@Override
	public void updateLikeCancel1(int post_num) throws Exception {
		sqlSession.update("mapper.board.updateLikeCancel1", post_num);

	}

	@Override
	public void insertLike1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.insert("mapper.board.insertLike1", map);
	}

	@Override
	public void deleteLike1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.delete("mapper.board.deleteLike1", map);
	}

	@Override
	public int likeCheck1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		return sqlSession.selectOne("mapper.board.likeCheck1", map);
	}

	@Override
	public void updateLikeCheck1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updateLikeCheck1", map);

	}

	@Override
	public void updateLikeCheckCancel1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updateLikeCheckCancel1", map);
	}

	// 신고
	@Override
	public void updatesin(int post_num) throws Exception {
		sqlSession.update("mapper.board.updatesin", post_num);
	}

	@Override
	public void updatesinCancel(int post_num) throws Exception {
		sqlSession.update("mapper.board.updatesinCancel", post_num);

	}

	@Override
	public void insertsin(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.insert("mapper.board.insertsin", map);
	}

	@Override
	public void deletesin(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.delete("mapper.board.deletesin", map);
	}

	@Override
	public int sinCheck(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		return sqlSession.selectOne("mapper.board.sinCheck", map);
	}

	@Override
	public void updatesinCheck(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updatesinCheck", map);

	}

	@Override
	public void updatesinCheckCancel(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updatesinCheckCancel", map);
	}

	// 신고2
	@Override
	public void updatesin1(int post_num) throws Exception {
		sqlSession.update("mapper.board.updatesin1", post_num);
	}

	@Override
	public void updatesinCancel1(int post_num) throws Exception {
		sqlSession.update("mapper.board.updatesinCancel1", post_num);

	}

	@Override
	public void insertsin1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.insert("mapper.board.insertsin1", map);
	}

	@Override
	public void deletesin1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.delete("mapper.board.deletesin1", map);
	}

	@Override
	public int sinCheck1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		return sqlSession.selectOne("mapper.board.sinCheck1", map);
	}

	@Override
	public void updatesinCheck1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updatesinCheck1", map);

	}

	@Override
	public void updatesinCheckCancel1(int post_num, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("post_num", post_num);
		sqlSession.update("mapper.board.updatesinCheckCancel1", map);
	}

	// 댓글 총 개수
	@Override
	public int boardCommentgetTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.board.boardCommentgetTotalRowCount", commentpagingDTO);
	}

	// 댓글 조회
	@Override
	public List<boardCommentDTO> boardreadReply(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectList("mapper.board.boardCommentselect", commentpagingDTO);
	}

	// 댓글 수 증가
	@Override
	public int boardcommentcount(BoardDTO boardDTO) throws Exception {
		return sqlSession.update("mapper.board.boardcommentcount", boardDTO);
	}

	// 댓글 수 감소
	@Override
	public int boardcommentcountminus(BoardDTO boardDTO) throws Exception {
		return sqlSession.update("mapper.board.boardcommentcountminus", boardDTO);
	}

	// 댓글 작성
	@Override
	public void boardcreate(boardCommentDTO boardCommentDTO) throws Exception {
		sqlSession.insert("mapper.board.boardWriteReply", boardCommentDTO);
	}

	// 댓글 수정
	@Override
	public void boardupdate(boardCommentDTO boardCommentDTO) throws Exception {
		sqlSession.update("mapper.board.boardupdateReply", boardCommentDTO);
	}

	// 댓글 삭제
	@Override
	public void boarddeleteReply(boardCommentDTO boardCommentDTO) throws Exception {
		sqlSession.delete("mapper.board.boarddeleteReply", boardCommentDTO);
	}

	// 선택된 댓글 조회
	@Override
	public boardCommentDTO boardselectReply(int com_num) throws Exception {
		return sqlSession.selectOne("mapper.board.boardselectReply", com_num);
	}

	// 댓글2
	// 댓글 총 개수
	@Override
	public int boardCommentgetTotalRowCount2(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.board.boardCommentgetTotalRowCount2", commentpagingDTO);
	}

	// 댓글 조회
	@Override
	public List<boardCommentDTO> boardreadReply2(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectList("mapper.board.boardCommentselect2", commentpagingDTO);
	}

	// 댓글 수 증가
	@Override
	public int boardcommentcount2(BoardDTO boardDTO) throws Exception {
		return sqlSession.update("mapper.board.boardcommentcount2", boardDTO);
	}

	// 댓글 수 감소
	@Override
	public int boardcommentcountminus2(BoardDTO boardDTO) throws Exception {
		return sqlSession.update("mapper.board.boardcommentcountminus2", boardDTO);
	}

	// 댓글 작성
	@Override
	public void boardcreate2(boardCommentDTO boardCommentDTO) throws Exception {
		sqlSession.insert("mapper.board.boardWriteReply2", boardCommentDTO);
	}

	// 댓글 수정
	@Override
	public void boardupdate2(boardCommentDTO boardCommentDTO) throws Exception {
		sqlSession.update("mapper.board.boardupdateReply2", boardCommentDTO);
	}

	// 댓글 삭제
	@Override
	public void boarddeleteReply2(boardCommentDTO boardCommentDTO) throws Exception {
		sqlSession.delete("mapper.board.boarddeleteReply2", boardCommentDTO);
	}

	// 선택된 댓글 조회
	@Override
	public boardCommentDTO boardselectReply2(int com_num) throws Exception {
		return sqlSession.selectOne("mapper.board.boardselectReply2", com_num);
	}
}
