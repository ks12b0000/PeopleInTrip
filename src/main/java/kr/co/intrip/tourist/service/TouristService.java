package kr.co.intrip.tourist.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;

public interface TouristService {

	// 관광지 api db에 저장용
	public void parkApi(String schAirportCode) throws Exception;
	
	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) throws Exception;

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist_list(PagingDTO pagingDTO) throws Exception;

	// 제주도 축제 총 개수
	public int getTotalRowCount2(PagingDTO pagingDTO) throws Exception;
	
	// 제주도 축제 페이지 리스트
	public List<ApiDTO> jejufestival_list(PagingDTO pagingDTO) throws Exception;

	// 제주도 전시관 총 개수
	public int getTotalRowCount3(PagingDTO pagingDTO) throws Exception;
	
	// 제주도 전시관 페이지 리스트
	public List<ApiDTO> jejuexhibition_list(PagingDTO pagingDTO) throws Exception;

	// 제주도 통합 상세페이지
	public ApiDTO jejutourist_detail(ApiDTO apiDTO) throws Exception;

	// 제주도 통합 상세페이지 조회수 증가
	public int jejutourist_viewcount(ApiDTO apiDTO) throws Exception;

	// 제주도 여행지 페이지 리스트 Sorting 기능
	public List<ApiDTO> jejutourist_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception;

	// 제주도 축제 페이지 리스트 Sorting 기능
	public List<ApiDTO> jejufestival_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception;

	// 제주도 전시관 페이지 리스트 Sorting 기능
	public List<ApiDTO> jejuexhibition_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception;
	
	// 제주도 댓글 수 증가
	public int jejucommentcount(ApiDTO apiDTO) throws Exception;
	
	// 제주도 댓글 수 감소
	public int jejucommentcountminus(ApiDTO apiDTO) throws Exception;
	
	// 제주도 댓글 총 개수
	public int getCommentTotalRowCount(CommentPagingDTO commentpaging) throws Exception;
	
	// 제주도 댓글 조회
	public List<JejuCommentDTO> jejureadReply(CommentPagingDTO commentpaging) throws Exception;

	// 제주도 댓글 작성
	public void jejuregister(JejuCommentDTO jejuDTO) throws Exception;

	// 제주도 댓글 수정
	public void jejumodify(JejuCommentDTO jejuDTO) throws Exception; 
	
	// 제주도 댓글 삭제
	public void jejuremove(JejuCommentDTO jejuDTO) throws Exception;
	
	// 제주도 선택된 댓글 조회
	public JejuCommentDTO jejuselectReply(int com_num) throws Exception;

}
