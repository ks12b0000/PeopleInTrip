package kr.co.intrip.tourist.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.BusanCommentDTO;
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;

@Repository
public class TouristDAO {

	@Autowired
	SqlSession sqlSession;
	
	// 관광지 api db에 저장용
	public void touristadd(ArrayList<ApiDTO> list) throws Exception {
		sqlSession.insert("mapper.tourist.touristadd", list);		
	}
	
	// 부산 여행지 api db에 저장용
	public void busantouristadd(ArrayList<BusanApiDTO> list) throws Exception {
		sqlSession.insert("mapper.tourist.busantouristadd", list);		
	}
	
	// 부산 축제 api db에 저장용
	public void busantouristadd2(ArrayList<BusanApiDTO> list) throws Exception {
		sqlSession.insert("mapper.tourist.busantouristadd2", list);		
	}

	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount");
	}

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutourist", pagingDTO);	 		
	}
	
	// 부산 여행지 총 개수
	public int busangetTotalRowCount(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busangetTotalRowCount");
	}

	// 부산 여행지 페이지 리스트
	public List<BusanApiDTO> busantourist(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busantourist", pagingDTO);	 		
	}
	
	// 부산 축제 총 개수
	public int busangetTotalRowCount2(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busangetTotalRowCount2");
	}

	// 부산 축제 페이지 리스트
	public List<BusanApiDTO> busanfestival(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanfestival", pagingDTO);	 		
	}
	
	// 제주도 축제 총 개수
	public int getTotalRowCount2(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount2");
	}

	// 제주도 축제 페이지 리스트
	public List<ApiDTO> jejufestival(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejufestival", pagingDTO);	
	}
	
	// 제주도 전시관 총 개수
	public int getTotalRowCount3(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount3");
	}

	// 제주도 전시관 페이지 리스트
	public List<ApiDTO> jejuexhibition(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuexhibition", pagingDTO);
	}

	// 제주도 통합 상세페이지
	public ApiDTO jejudetail(ApiDTO apiDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.jejudetail", apiDTO);
	}
	
	// 제주도 통합 상세페이지 조회수 증가
	public int viewcount(ApiDTO apiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.viewcount", apiDTO);
	}

	// 제주도 여행지 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejutourist_lookupSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutourist_lookupSort", pagingDTO);
	}
	
	// 제주도 여행지 페이지 댓글수별 리스트 Sorting 기능
	public List<ApiDTO> jejutourist_commentSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutourist_commentSort", pagingDTO);
	}
	
	// 제주도 여행지 페이지 찜수별 리스트 Sorting 기능
	public List<ApiDTO> jejutourist_steamedSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutourist_steamedSort", pagingDTO);
	}
	
	// 제주도 여행지 페이지 추천수별 리스트 Sorting 기능
	public List<ApiDTO> jejutourist_SuggestionSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutourist_SuggestionSort", pagingDTO);
	}
	
	// 부산 여행지 상세페이지
	public BusanApiDTO busandetail(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busandetail", busanApiDTO);
	}
	
	// 부산 여행지 상세페이지 조회수 증가
	public int busanviewcount(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busanviewcount", busanApiDTO);
	}
	
	// 부산 축제 상세페이지
	public BusanApiDTO busandetail2(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busandetail2", busanApiDTO);
	}
	
	// 부산 축제 상세페이지 조회수 증가
	public int busanviewcount2(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busanviewcount2", busanApiDTO);
	}

	// 부산 여행지 페이지 조회수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_lookupSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busantourist_lookupSort", pagingDTO);
	}
	
	// 부산 여행지 페이지 댓글수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_commentSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busantourist_commentSort", pagingDTO);
	}
	
	// 부산 여행지 페이지 찜수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_steamedSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busantourist_steamedSort", pagingDTO);
	}
	
	// 부산 여행지 페이지 추천수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_SuggestionSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busantourist_SuggestionSort", pagingDTO);
	}
	
	// 제주도 축제 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_lookupSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejufestival_lookupSort", pagingDTO);
	}
	
	// 제주도 축제 페이지 댓글수별 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_commentSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejufestival_commentSort", pagingDTO);
	}
	
	// 제주도 축제 페이지 찜수별 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_steamedSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejufestival_steamedSort", pagingDTO);
	}
	
	// 제주도 축제 페이지 추천수별 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_SuggestionSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejufestival_SuggestionSort", pagingDTO);
	}
	
	// 부산 축제 페이지 조회수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_lookupSort2(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanfestival_lookupSort", pagingDTO);
	}
	
	// 부산 축제 페이지 댓글수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_commentSort2(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanfestival_commentSort", pagingDTO);
	}
	
	// 부산 축제 페이지 찜수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_steamedSort2(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanfestival_steamedSort", pagingDTO);
	}
	
	// 부산 축제 페이지 추천수별 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_SuggestionSort2(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanfestival_SuggestionSort", pagingDTO);
	}
		
	// 제주도 전시관 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_lookupSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuexhibition_lookupSort", pagingDTO);
	}
	
	// 제주도 전시관 페이지 댓글수별 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_commentSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuexhibition_commentSort", pagingDTO);
	}
	
	// 제주도 전시관 페이지 찜수별 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_steamedSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuexhibition_steamedSort", pagingDTO);
	}
	
	// 제주도 전시관 페이지 추천수별 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_SuggestionSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuexhibition_SuggestionSort", pagingDTO);
	}
	
	// 제주도 댓글 수 증가
	public int commentcount(ApiDTO apiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.commentcount", apiDTO);
	}
	
	// 제주도 댓글 수 감소
	public int commentcountminus(ApiDTO apiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.commentcountminus", apiDTO);
	}
	
	// 제주도 댓글 총 개수
	public int CommentgetTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.CommentgetTotalRowCount", commentpagingDTO);
	}

	// 제주도 댓글 조회
	public List<JejuCommentDTO> jejureadReply(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuCommentselect", commentpagingDTO);
	}
	
	// 제주도 댓글 작성
	public void jejucreate(JejuCommentDTO jejuDTO) throws Exception {
		sqlSession.insert("mapper.tourist.jejuWriteReply", jejuDTO);
	}
	
	// 제주도 댓글 수정
	public void jejuupdate(JejuCommentDTO jejuDTO) throws Exception {
		sqlSession.update("mapper.tourist.jejuupdateReply", jejuDTO);
	}
	
	// 제주도 댓글 삭제
	public void jejudeleteReply(JejuCommentDTO jejuDTO) throws Exception {
		sqlSession.delete("mapper.tourist.jejudeleteReply", jejuDTO);
	}
	
	// 제주도 선택된 댓글 조회
	public JejuCommentDTO jejuselectReply(int com_num) throws Exception { 
		return sqlSession.selectOne("mapper.tourist.jejuselectReply", com_num);
	}
	
	// 제주도 여행지 찜 수
	public void updateSteamed(String contentsid) throws Exception {
		sqlSession.update("mapper.tourist.updateSteamed", contentsid);
	}
	
	// 제주도 여행지 찜 수 취소
	public void updateSteamedCancel(String contentsid) throws Exception {
		sqlSession.update("mapper.tourist.updateSteamedCancel", contentsid);
	}

	// 제주도 여행지 찜 시 steamed 테이블에 insert
	public void insertSteamed(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.insert("mapper.tourist.insertSteamed", map);
	}

	// 제주도 여행지 찜 취소 시 delete
	public void deleteSteamed(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.delete("mapper.tourist.deleteSteamed", map);
	}

	// 제주도 여행지 찜 중복방지 select문
	public String SteamedCheck(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		return sqlSession.selectOne("mapper.tourist.SteamedCheck", map);
	}

	// 제주도 여행지 찜 시 Check를 1로 만들어서 중복방지
	public void updateSteamedCheck(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.update("mapper.tourist.updateSteamedCheck", map);
	}

	// 제주도 여행지 찜 취소 시 다시 0 
	public void updateSteamedCheckCancel(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.update("mapper.tourist.updateSteamedCheckCancel", map);
	}

	// 제주도 여행지 추천 수
	public void updateSuggestion(String contentsid) throws Exception {
		sqlSession.update("mapper.tourist.updateSuggestion", contentsid);
	}
		
	// 제주도 여행지 추천 수 취소
	public void updateSuggestionCancel(String contentsid) throws Exception {
		sqlSession.update("mapper.tourist.updateSuggestionCancel", contentsid);
	}

	// 제주도 여행지 추천 시 steamed 테이블에 insert
	public void insertSuggestion(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.insert("mapper.tourist.insertSuggestion", map);
	}

	// 제주도 여행지 추천 취소 시 delete
	public void deleteSuggestion(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.delete("mapper.tourist.deleteSuggestion", map);
	}
	
	// 제주도 여행지 추천 중복방지 select문
	public String SuggestionCheck(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		return sqlSession.selectOne("mapper.tourist.SuggestionCheck", map);
	}

	// 제주도 여행지 추천 시 Check를 1로 만들어서 중복방지
	public void updateSuggestionCheck(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.update("mapper.tourist.updateSuggestionCheck", map);
	}

	// 제주도 여행지 추천 취소 시 다시 0 
	public void updateSuggestionCheckCancel(String contentsid, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contentsid", contentsid);
		sqlSession.update("mapper.tourist.updateSuggestionCheckCancel", map);
	}

	// 제주도 여행지 메인페이지 배너
	public List<ApiDTO> jejutouristmain(ApiDTO apiDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutouristmain", apiDTO);	 		
	}

	// 부산 댓글 수 증가
	public int busancommentcount(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busancommentcount", busanApiDTO);
	}
	
	// 부산 댓글 수 감소
	public int busancommentcountminus(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busancommentcountminus", busanApiDTO);
	}
	
	// 부산 댓글 총 개수
	public int busanCommentgetTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busanCommentgetTotalRowCount", commentpagingDTO);
	}

	// 부산 댓글 조회
	public List<BusanCommentDTO> busanreadReply(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanCommentselect", commentpagingDTO);
	}
	
	// 부산 댓글 작성
	public void busancreate(BusanCommentDTO busanCommentDTO) throws Exception {
		sqlSession.insert("mapper.tourist.busanWriteReply", busanCommentDTO);
	}
	
	// 부산 댓글 수정
	public void busanupdate(BusanCommentDTO busanCommentDTO) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateReply", busanCommentDTO);
	}
	
	// 부산 댓글 삭제
	public void busandeleteReply(BusanCommentDTO busanCommentDTO) throws Exception {
		sqlSession.delete("mapper.tourist.busandeleteReply", busanCommentDTO);
	}
	
	// 부산 선택된 댓글 조회
	public BusanCommentDTO busanselectReply(int com_num) throws Exception { 
		return sqlSession.selectOne("mapper.tourist.busanselectReply", com_num);
	}
	
	// 부산 여행지 찜 수
	public void busanupdateSteamed(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSteamed", UC_SEQ);
	}
	
	// 부산 여행지 찜 수 취소
	public void busanupdateSteamedCancel(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSteamedCancel", UC_SEQ);
	}

	// 부산 여행지 찜 시 steamed 테이블에 insert
	public void busaninsertSteamed(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.insert("mapper.tourist.busaninsertSteamed", map);
	}

	// 부산 여행지 찜 취소 시 delete
	public void busandeleteSteamed(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.delete("mapper.tourist.busandeleteSteamed", map);
	}

	// 부산 여행지 찜 중복방지 select문
	public int busanSteamedCheck(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		return sqlSession.selectOne("mapper.tourist.busanSteamedCheck", map);
	}

	// 부산 여행지 찜 시 Check를 1로 만들어서 중복방지
	public void busanupdateSteamedCheck(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSteamedCheck", map);
	}

	// 부산 여행지 찜 취소 시 다시 0 
	public void busanupdateSteamedCheckCancel(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSteamedCheckCancel", map);
	}

	// 부산 여행지 추천 수
	public void busanupdateSuggestion(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSuggestion", UC_SEQ);
	}
		
	// 부산 여행지 추천 수 취소
	public void busanupdateSuggestionCancel(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSuggestionCancel", UC_SEQ);
	}

	// 부산 여행지 추천 시 steamed 테이블에 insert
	public void busaninsertSuggestion(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.insert("mapper.tourist.busaninsertSuggestion", map);
	}

	// 부산 여행지 추천 취소 시 delete
	public void busandeleteSuggestion(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.delete("mapper.tourist.busandeleteSuggestion", map);
	}
	
	// 부산 여행지 추천 중복방지 select문
	public int busanSuggestionCheck(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		return sqlSession.selectOne("mapper.tourist.busanSuggestionCheck", map);
	}

	// 부산 여행지 추천 시 Check를 1로 만들어서 중복방지
	public void busanupdateSuggestionCheck(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSuggestionCheck", map);
	}

	// 부산 여행지 추천 취소 시 다시 0 
	public void busanupdateSuggestionCheckCancel(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSuggestionCheckCancel", map);
	}
	
	//
	// 부산 축제 댓글 수 증가
	public int busancommentcount2(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busancommentcount2", busanApiDTO);
	}
	
	// 부산 축제 댓글 수 감소
	public int busancommentcountminus2(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busancommentcountminus2", busanApiDTO);
	}
	
	// 부산 축제 댓글 총 개수
	public int busanCommentgetTotalRowCount2(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busanCommentgetTotalRowCount2", commentpagingDTO);
	}

	// 부산 축제 댓글 조회
	public List<BusanCommentDTO> busanreadReply2(CommentPagingDTO commentpagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busanCommentselect2", commentpagingDTO);
	}
	
	// 부산 축제 댓글 작성
	public void busancreate2(BusanCommentDTO busanCommentDTO) throws Exception {
		sqlSession.insert("mapper.tourist.busanWriteReply2", busanCommentDTO);
	}
	
	// 부산 축제 댓글 수정
	public void busanupdate2(BusanCommentDTO busanCommentDTO) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateReply2", busanCommentDTO);
	}
	
	// 부산 축제 댓글 삭제
	public void busandeleteReply2(BusanCommentDTO busanCommentDTO) throws Exception {
		sqlSession.delete("mapper.tourist.busandeleteReply2", busanCommentDTO);
	}
	
	// 부산 축제 선택된 댓글 조회
	public BusanCommentDTO busanselectReply2(int com_num) throws Exception { 
		return sqlSession.selectOne("mapper.tourist.busanselectReply2", com_num);
	}
	
	// 부산 축제 찜 수
	public void busanupdateSteamed2(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSteamed2", UC_SEQ);
	}
	
	// 부산 축제 찜 수 취소
	public void busanupdateSteamedCancel2(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSteamedCancel2", UC_SEQ);
	}

	// 부산 축제 찜 시 steamed 테이블에 insert
	public void busaninsertSteamed2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.insert("mapper.tourist.busaninsertSteamed2", map);
	}

	// 부산 축제 찜 취소 시 delete
	public void busandeleteSteamed2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.delete("mapper.tourist.busandeleteSteamed2", map);
	}

	// 부산 축제 찜 중복방지 select문
	public int busanSteamedCheck2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		return sqlSession.selectOne("mapper.tourist.busanSteamedCheck2", map);
	}

	// 부산 축제 찜 시 Check를 1로 만들어서 중복방지
	public void busanupdateSteamedCheck2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSteamedCheck2", map);
	}

	// 부산 축제 찜 취소 시 다시 0 
	public void busanupdateSteamedCheckCancel2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSteamedCheckCancel2", map);
	}

	// 부산 축제 추천 수
	public void busanupdateSuggestion2(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSuggestion2", UC_SEQ);
	}
		
	// 부산 축제 추천 수 취소
	public void busanupdateSuggestionCancel2(int UC_SEQ) throws Exception {
		sqlSession.update("mapper.tourist.busanupdateSuggestionCancel2", UC_SEQ);
	}

	// 부산 축제 추천 시 steamed 테이블에 insert
	public void busaninsertSuggestion2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.insert("mapper.tourist.busaninsertSuggestion2", map);
	}

	// 부산 축제 추천 취소 시 delete
	public void busandeleteSuggestion2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.delete("mapper.tourist.busandeleteSuggestion2", map);
	}
	
	// 부산 축제 추천 중복방지 select문
	public int busanSuggestionCheck2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		return sqlSession.selectOne("mapper.tourist.busanSuggestionCheck2", map);
	}

	// 부산 축제 추천 시 Check를 1로 만들어서 중복방지
	public void busanupdateSuggestionCheck2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSuggestionCheck2", map);
	}

	// 부산 축제 추천 취소 시 다시 0 
	public void busanupdateSuggestionCheckCancel2(int UC_SEQ, String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("UC_SEQ", UC_SEQ);
		sqlSession.update("mapper.tourist.busanupdateSuggestionCheckCancel2", map);
	}
	//
	
	// 부산 축제 메인페이지 배너
	public List<BusanApiDTO> busantouristmain(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.busantouristmain", busanApiDTO);	 		
	}
	
}
