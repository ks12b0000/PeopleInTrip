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
import kr.co.intrip.board.dto.CommentPagingDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.ImageDTO;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.board.dto.SearchCriteria2;
import kr.co.intrip.board.dto.boardCommentDTO;
import kr.co.intrip.login_signup.dto.MemberDTO;
import kr.co.intrip.tourist.dto.ApiDTO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	// 페이징 검색 1
	@Override
	public List<BoardDTO> listfind1(SearchCriteria scri) throws Exception {
		List<BoardDTO> boardsList = boardDAO.listfind1(scri);
		return boardsList;
	}

	// 게시물 갯수 검색 1
	@Override
	public int findlistCount1(SearchCriteria scri) throws Exception {
		return boardDAO.findlistCount1(scri);
	}

	// 게시물 갯수 검색
	@Override
	public int findlistCount(SearchCriteria scri) throws Exception {
		return boardDAO.findlistCount(scri);
	}

	// 페이징 검색
	@Override
	public List<BoardDTO> listfind(SearchCriteria scri) throws Exception {
		List<BoardDTO> boardsList = boardDAO.listfind(scri);
		return boardsList;
	}

	// 상세보기
	@Override
	public Map<String, Object> viewdetail(int post_num) {
		Map<String, Object> boardMap = new HashMap<>();

		BoardDTO boardDTO = boardDAO.selectBoard(post_num);

		// 이미지 부분 정보 요청
		List<ImageDTO> imageFileList = boardDAO.selectImageFileList(post_num);

		boardMap.put("board", boardDTO);
		boardMap.put("imageFileList", imageFileList);

		return boardMap;
	}

	// 상세보기1
	@Override
	public Map<String, Object> viewdetail1(int post_num) {
		Map<String, Object> boardMap = new HashMap<>();

		BoardDTO boardDTO = boardDAO.selectBoard1(post_num);

		// 이미지 부분 정보 요청
		List<ImageDTO> imageFileList = boardDAO.selectImageFileList1(post_num);

		boardMap.put("board", boardDTO);
		boardMap.put("imageFileList", imageFileList);
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

	// 글쓰기1
	@Override
	public int insertBoard1(Map boardMap) throws Exception {

		int post_num = boardDAO.insertBoard1(boardMap); // 글 정보를 저장한 후 글번호를 가져옴.
		boardMap.put("post_num", post_num);

		boardDAO.insertNewImage1(boardMap); // 이미지 정보를 저장함

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
		else if (modAddImageFileList != null && modAddImageFileList.size() != 0) {
			boardDAO.insertModNewImage(boardMap);
		}

	}

	// 글수정1
	@Override
	public void modBoard1(Map<String, Object> boardMap) throws Exception {
		boardDAO.updateBoard1(boardMap);

		List<ImageDTO> imageFileList = (List<ImageDTO>) boardMap.get("imageFileList");
		List<ImageDTO> modAddImageFileList = (List<ImageDTO>) boardMap.get("modAddImageFileList");

		if (imageFileList != null && imageFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) boardMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) boardMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) { // 기존 이미지도 수정하고 새 이미지도 추가한 경우
				boardDAO.updateImageFile1(boardMap); // 기존 이미지 수정
				boardDAO.insertModNewImage1(boardMap); // 새 이미지 추가
			} else { // 기존 이미지를 수정만 한 경우
				boardDAO.updateImageFile1(boardMap);
			}
		}
		// 새 이미지를 추가한 경우
		else if (modAddImageFileList != null && modAddImageFileList.size() != 0) {
			boardDAO.insertModNewImage1(boardMap);
		}

	}

	// 글이미지삭제
	@Override
	public void removeModImage(ImageDTO imageDTO) {
		boardDAO.deleteModImage(imageDTO);

	}

	// 글이미지삭제
	@Override
	public void removeModImage1(ImageDTO imageDTO) {
		boardDAO.deleteModImage1(imageDTO);

	}

	// 글삭제
	@Override
	public void removeBoard(int post_num) throws Exception {
		boardDAO.deleteBoard(post_num);

	}

	// 글삭제1
	@Override
	public void removeBoard1(int post_num) throws Exception {
		boardDAO.deleteBoard1(post_num);

	}

	// 조회수
	@Override
	public void visitcount(int post_num) throws Exception {
		boardDAO.visitcount(post_num);

	}

	// 조회수1
	@Override
	public void visitcount1(int post_num) throws Exception {
		boardDAO.visitcount1(post_num);

	}

	// 추천
	@Override
	public void updateLike(int post_num) throws Exception {
		boardDAO.updateLike(post_num);
	}

	@Override
	public void updateLikeCancel(int post_num) throws Exception {
		boardDAO.updateLikeCancel(post_num);
	}

	@Override
	public void insertLike(int post_num, String id) throws Exception {
		boardDAO.insertLike(post_num, id);
	}

	@Override
	public void deleteLike(int post_num, String id) throws Exception {
		boardDAO.deleteLike(post_num, id);
	}

	@Override
	public int likeCheck(int post_num, String id) throws Exception {
		return boardDAO.likeCheck(post_num, id);
	}

	@Override
	public void updateLikeCheck(int post_num, String id) throws Exception {
		boardDAO.updateLikeCheck(post_num, id);
	}

	@Override
	public void updateLikeCheckCancel(int post_num, String id) throws Exception {
		boardDAO.updateLikeCheckCancel(post_num, id);
	}

	// 추천 1
	@Override
	public void updateLike1(int post_num) throws Exception {
		boardDAO.updateLike1(post_num);
	}

	@Override
	public void updateLikeCancel1(int post_num) throws Exception {
		boardDAO.updateLikeCancel1(post_num);
	}

	@Override
	public void insertLike1(int post_num, String id) throws Exception {
		boardDAO.insertLike1(post_num, id);
	}

	@Override
	public void deleteLike1(int post_num, String id) throws Exception {
		boardDAO.deleteLike1(post_num, id);
	}

	@Override
	public int likeCheck1(int post_num, String id) throws Exception {
		return boardDAO.likeCheck1(post_num, id);
	}

	@Override
	public void updateLikeCheck1(int post_num, String id) throws Exception {
		boardDAO.updateLikeCheck1(post_num, id);
	}

	@Override
	public void updateLikeCheckCancel1(int post_num, String id) throws Exception {
		boardDAO.updateLikeCheckCancel1(post_num, id);
	}

	// 신고
	@Override
	public void updatesin(int post_num) throws Exception {
		boardDAO.updatesin(post_num);
	}

	@Override
	public void updatesinCancel(int post_num) throws Exception {
		boardDAO.updatesinCancel(post_num);
	}

	@Override
	public void insertsin(int post_num, String id) throws Exception {
		boardDAO.insertsin(post_num, id);
	}

	@Override
	public void deletesin(int post_num, String id) throws Exception {
		boardDAO.deletesin(post_num, id);
	}

	@Override
	public int sinCheck(int post_num, String id) throws Exception {
		return boardDAO.sinCheck(post_num, id);
	}

	@Override
	public void updatesinCheck(int post_num, String id) throws Exception {
		boardDAO.updatesinCheck(post_num, id);
	}

	@Override
	public void updatesinCheckCancel(int post_num, String id) throws Exception {
		boardDAO.updatesinCheckCancel(post_num, id);
	}

	// 신고1
	@Override
	public void updatesin1(int post_num) throws Exception {
		boardDAO.updatesin1(post_num);
	}

	@Override
	public void updatesinCancel1(int post_num) throws Exception {
		boardDAO.updatesinCancel1(post_num);
	}

	@Override
	public void insertsin1(int post_num, String id) throws Exception {
		boardDAO.insertsin1(post_num, id);
	}

	@Override
	public void deletesin1(int post_num, String id) throws Exception {
		boardDAO.deletesin1(post_num, id);
	}

	@Override
	public int sinCheck1(int post_num, String id) throws Exception {
		return boardDAO.sinCheck1(post_num, id);
	}

	@Override
	public void updatesinCheck1(int post_num, String id) throws Exception {
		boardDAO.updatesinCheck1(post_num, id);
	}

	@Override
	public void updatesinCheckCancel1(int post_num, String id) throws Exception {
		boardDAO.updatesinCheckCancel1(post_num, id);
	}

	// 댓글 총 개수
	@Override
	public int getboardCommentTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return boardDAO.boardCommentgetTotalRowCount(commentpagingDTO);
	}

	// 댓글 조회
	@Override
	public List<boardCommentDTO> boardreadReply(CommentPagingDTO commentpagingDTO) throws Exception {
		return boardDAO.boardreadReply(commentpagingDTO);
	}

	// 댓글 수 증가
	@Override
	public int boardcommentcount(BoardDTO boardDTO) throws Exception {
		return boardDAO.boardcommentcount(boardDTO);
	}

	// 댓글 수 감소
	@Override
	public int boardcommentcountminus(BoardDTO boardDTO) throws Exception {
		return boardDAO.boardcommentcountminus(boardDTO);
	}

	// 댓글 작성
	@Override
	public void boardregister(boardCommentDTO boardCommentDTO) throws Exception {
		boardDAO.boardcreate(boardCommentDTO);
	}

	// 댓글 수정
	@Override
	public void boardmodify(boardCommentDTO boardCommentDTO) throws Exception {
		boardDAO.boardupdate(boardCommentDTO);
	}

	// 댓글 삭제
	@Override
	public void boardremove(boardCommentDTO boardCommentDTO) throws Exception {
		boardDAO.boarddeleteReply(boardCommentDTO);
	}

	// 선택된 댓글 조회
	@Override
	public boardCommentDTO boardselectReply(int com_num) throws Exception {
		return boardDAO.boardselectReply(com_num);
	}

	// 댓글 2
	// 댓글 총 개수
	@Override
	public int getboardCommentTotalRowCount2(CommentPagingDTO commentpagingDTO) throws Exception {
		return boardDAO.boardCommentgetTotalRowCount2(commentpagingDTO);
	}

	// 댓글 조회
	@Override
	public List<boardCommentDTO> boardreadReply2(CommentPagingDTO commentpagingDTO) throws Exception {
		return boardDAO.boardreadReply2(commentpagingDTO);
	}

	// 댓글 수 증가
	@Override
	public int boardcommentcount2(BoardDTO boardDTO) throws Exception {
		return boardDAO.boardcommentcount2(boardDTO);
	}

	// 댓글 수 감소
	@Override
	public int boardcommentcountminus2(BoardDTO boardDTO) throws Exception {
		return boardDAO.boardcommentcountminus2(boardDTO);
	}

	// 댓글 작성
	@Override
	public void boardregister2(boardCommentDTO boardCommentDTO) throws Exception {
		boardDAO.boardcreate2(boardCommentDTO);
	}

	// 댓글 수정
	@Override
	public void boardmodify2(boardCommentDTO boardCommentDTO) throws Exception {
		boardDAO.boardupdate2(boardCommentDTO);
	}

	// 댓글 삭제
	@Override
	public void boardremove2(boardCommentDTO boardCommentDTO) throws Exception {
		boardDAO.boarddeleteReply2(boardCommentDTO);
	}

	// 선택된 댓글 조회
	@Override
	public boardCommentDTO boardselectReply2(int com_num) throws Exception {
		return boardDAO.boardselectReply2(com_num);
	}

	// 관리자 페이지
	@Override
	public List<BoardDTO> listfindmaster(SearchCriteria scri) throws Exception {
		List<BoardDTO> boardsList = boardDAO.listfindmaster(scri);
		return boardsList;
	}

	// 관리자 페이지
	@Override
	public List<BoardDTO> listfindmaster1(SearchCriteria scri) throws Exception {
		List<BoardDTO> boardsList = boardDAO.listfindmaster1(scri);
		return boardsList;
	}

	// 신고 갯수 검색(관리자)
	@Override
	public int findlistCountmaster(SearchCriteria scri) throws Exception {
		return boardDAO.findlistCountmaster(scri);
	}

	// 신고 갯수 검색1(관리자)
	@Override
	public int findlistCountmaster1(SearchCriteria scri) throws Exception {
		return boardDAO.findlistCountmaster1(scri);
	}

	

}
