package kr.co.intrip.tourist.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.intrip.tourist.dto.ApiDTO;

public interface TouristService {

	// 관광지 api db에 저장용
	public void parkApi(String schAirportCode) throws IOException;

	public List<ApiDTO> jejutourist_list(ApiDTO apiDTO) throws IOException;

}
