package kr.co.intrip.tourist.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.intrip.tourist.dto.ApiDTO;
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
	
	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount");
	}

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejutourist", pagingDTO);	 		
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
	
	// 제주도 축제 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_lookupSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejufestival_lookupSort", pagingDTO);
	}
		
	// 제주도 전시관 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_lookupSort(PagingDTO pagingDTO) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuexhibition_lookupSort", pagingDTO);
	}

	// 제주도 댓글 조회
	public List<JejuCommentDTO> jejureadReply(String contentsid) throws Exception {
		return sqlSession.selectList("mapper.tourist.jejuCommentselect", contentsid);
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
}
