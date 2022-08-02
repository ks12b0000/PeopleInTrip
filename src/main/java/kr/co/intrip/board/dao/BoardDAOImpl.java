package kr.co.intrip.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.ImageDTO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<BoardDTO> selectAllBoardList() throws DataAccessException {
		List<BoardDTO> boardsList = sqlSession.selectList("mapper.board.selectAllBoardList");
		return boardsList;
	}
	
	@Override
	public List<BoardDTO> selectAllBoardList1() throws DataAccessException {
		List<BoardDTO> boardsList = sqlSession.selectList("mapper.board.selectAllBoardList1");
		return boardsList;
	}
	
	//상세보기
	@Override
	public BoardDTO selectBoard(int post_num) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.selectBoard",post_num);
	}

	@Override
	public int insertBoard(Map boardMap) {
		int post_num = selectNewpost_num();
		boardMap.put("post_num", post_num);
		
		sqlSession.insert("mapper.board.insertBoard", boardMap);		
		return post_num;
	}
	
	
	@Override
	public int insertBoard1(Map boardMap) {
		int post_num = selectNewpost_num();
		boardMap.put("post_num", post_num);
		
		sqlSession.insert("mapper.board.insertBoard1", boardMap);		
		return post_num;
	}
	

	private int selectNewpost_num() {
		return sqlSession.selectOne("mapper.board.selectNewpost_num");
	}

	@Override
	public void insertNewImage(Map boardMap) {
		List<ImageDTO> imageFileList = (List<ImageDTO>) boardMap.get("imageFileList");
		int post_num = (Integer)boardMap.get("post_num");		// articleMap이 글 번호를 가져옴
		
		int imageFileNO = selectNewImageFileNO();					// 이미지 번호를 가져옴
		
		if (imageFileList != null && imageFileList.size() != 0) {
			// ImageDTO 객체를 차례대로 가져와 이미지번호와 글번호 속성을 설정함
			for (ImageDTO imageDTO : imageFileList) {
				imageDTO.setImageFileNO(++imageFileNO);
				imageDTO.setpost_num(post_num);
			}
			
			//T_IMAGEFILE 테이블에 INSERT
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

	

	
	
}

