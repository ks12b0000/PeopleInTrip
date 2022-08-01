package kr.co.intrip.tourist.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.intrip.login_signup.dto.MemberDTO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.TouristDTO;

@Repository
public class TouristDAO {

	@Autowired
	SqlSession sqlSession;
	
	// 관광지 api db에 저장용
	public void touristadd(ArrayList<ApiDTO> list) {
		sqlSession.insert("mapper.tourist.touristadd", list);
		
	}

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist(ApiDTO apiDTO) {
		return sqlSession.selectList("mapper.tourist.jejutourist");	 		
	}

	// 제주도 축제 페이지 리스트
	public List<ApiDTO> jejufestival(ApiDTO apiDTO) {
		return sqlSession.selectList("mapper.tourist.jejufestival");	
	}

	// 제주도 전시관 페이지 리스트
	public List<ApiDTO> jejuexhibition(ApiDTO apiDTO) {
		return sqlSession.selectList("mapper.tourist.jejuexhibition");
	}
}
