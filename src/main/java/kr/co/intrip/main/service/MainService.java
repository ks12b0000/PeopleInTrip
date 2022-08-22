package kr.co.intrip.main.service;

import java.util.List;

import kr.co.intrip.main.dto.MainDTO;

public interface MainService {

	// 동행구해요 게시판 리스트
	public List<MainDTO> listMain() throws Exception;

	// 정보게시판 리스트
	public List<MainDTO> listMain1() throws Exception;
}
