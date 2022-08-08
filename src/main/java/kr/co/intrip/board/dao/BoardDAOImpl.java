package kr.co.intrip.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.ImageDTO;
import kr.co.intrip.board.dto.SearchCriteria;

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

}
