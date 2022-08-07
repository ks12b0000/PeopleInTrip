package kr.co.intrip.tourist.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;

@Repository
public class TouristDAO {

	@Autowired
	SqlSession sqlSession;
	
	// 관광지 api db에 저장용
	public void touristadd(ArrayList<ApiDTO> list) {
		sqlSession.insert("mapper.tourist.touristadd", list);
		
	}
	
	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount");
	}

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist(PagingDTO pagingDTO) {
		return sqlSession.selectList("mapper.tourist.jejutourist", pagingDTO);	 		
	}
	
	// 제주도 축제 총 개수
	public int getTotalRowCount2(PagingDTO pagingDTO) {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount2");
	}

	// 제주도 축제 페이지 리스트
	public List<ApiDTO> jejufestival(PagingDTO pagingDTO) {
		return sqlSession.selectList("mapper.tourist.jejufestival", pagingDTO);	
	}
	
	// 제주도 전시관 총 개수
	public int getTotalRowCount3(PagingDTO pagingDTO) {
		return sqlSession.selectOne("mapper.tourist.getTotalRowCount3");
	}

	// 제주도 전시관 페이지 리스트
	public List<ApiDTO> jejuexhibition(PagingDTO pagingDTO) {
		return sqlSession.selectList("mapper.tourist.jejuexhibition", pagingDTO);
	}

	// 제주도 통합 상세페이지
	public ApiDTO jejudetail(ApiDTO apiDTO) {
		return sqlSession.selectOne("mapper.tourist.jejudetail", apiDTO);
	}
	
	// 제주도 통합 상세페이지 조회수 증가
	public int viewcount(ApiDTO apiDTO) {
		return sqlSession.update("mapper.tourist.viewcount", apiDTO);
	}

	// 제주도 여행지 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejutourist_lookupSort(PagingDTO pagingDTO) {
		return sqlSession.selectList("mapper.tourist.jejutourist_lookupSort", pagingDTO);
	}
	
	// 제주도 축제 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_lookupSort(PagingDTO pagingDTO) {
		return sqlSession.selectList("mapper.tourist.jejufestival_lookupSort", pagingDTO);
	}
		
	// 제주도 전시관 페이지 조회수별 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_lookupSort(PagingDTO pagingDTO) {
		return sqlSession.selectList("mapper.tourist.jejuexhibition_lookupSort", pagingDTO);
	}
}
