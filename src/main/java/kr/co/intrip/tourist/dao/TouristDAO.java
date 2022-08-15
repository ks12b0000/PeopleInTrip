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
	//
	// 부산 여행지 상세페이지
	public BusanApiDTO busandetail(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.busandetail", busanApiDTO);
	}
	
	// 부산 여행지 상세페이지 조회수 증가
	public int busanviewcount(BusanApiDTO busanApiDTO) throws Exception {
		return sqlSession.update("mapper.tourist.busanviewcount", busanApiDTO);
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
	//
	
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
}
