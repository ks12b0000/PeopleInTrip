package kr.co.intrip.tourist.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.intrip.tourist.dto.ApiDTO;

public interface TouristService {

	// 관광지 api db에 저장용
	public void parkApi(String schAirportCode) throws IOException;

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist_list(ApiDTO apiDTO) throws IOException;

	// 제주도 축제 페이지 리스트
	public List<ApiDTO> jejufestival_list(ApiDTO apiDTO) throws IOException;

	// 제주도 전시관 페이지 리스트
	public List<ApiDTO> jejuexhibition_list(ApiDTO apiDTO) throws IOException;

	// 제주도 통합 상세페이지
	public ApiDTO jejutourist_detail(ApiDTO apiDTO) throws IOException;

	// 제주도 통합 상세페이지 조회수 증가
	public int jejutourist_viewcount(ApiDTO apiDTO) throws IOException;


	List<ApiDTO> jejutourist_Sort(ApiDTO apiDTO) throws IOException;
}
