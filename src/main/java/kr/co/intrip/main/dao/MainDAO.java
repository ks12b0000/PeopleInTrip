package kr.co.intrip.main.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import kr.co.intrip.main.dto.MainDTO;

public interface MainDAO {

	// 동행구해요 게시판 리스트
	public List<MainDTO> selectBoardList() throws DataAccessException;

	// 정보게시판 리스트
	public List<MainDTO> selectBoardList1() throws DataAccessException;
}
