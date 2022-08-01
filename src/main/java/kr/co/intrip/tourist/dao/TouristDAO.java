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

	public List<ApiDTO> jejutourist(ApiDTO apiDTO) {
		return sqlSession.selectList("mapper.tourist.jejutourist");	 		
	}
}
