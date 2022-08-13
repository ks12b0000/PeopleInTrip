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

	// 제주도 여행지 찜 중복방지 select문
	public String steamedCheck(String contentsid, String id) throws Exception;

	// 제주도 여행지 찜 시 steamed 테이블에 insert
	public void insertSteamed(String contentsid, String id) throws Exception;

	// 제주도 여행지 찜 수
	public void updateSteamed(String contentsid) throws Exception;

	// 제주도 여행지 찜 시 Check를 1로 만들어서 중복방지
	public void updateSteamedCheck(String contentsid, String id) throws Exception;

	// 제주도 여행지 찜 취소 시 다시 0
	public void updateSteamedCheckCancel(String contentsid, String id) throws Exception;

	// 제주도 여행지 찜 수 취소
	public void updateSteamedCancel(String contentsid) throws Exception;

	// 제주도 여행지 찜 취소 시 delete
	public void deleteSteamed(String contentsid, String id) throws Exception;

	// 제주도 여행지 추천 중복방지 select문
	public String SuggestionCheck(String contentsid, String id) throws Exception;
	
	// 제주도 여행지 추천 시 steamed 테이블에 insert
	public void insertSuggestion(String contentsid, String id) throws Exception;		

	// 제주도 여행지 추천 수
	public void updateSuggestion(String contentsid) throws Exception;	

	// 제주도 여행지 추천 시 Check를 1로 만들어서 중복방지
	public void updateSuggestionCheck(String contentsid, String id) throws Exception;

	// 제주도 여행지 추천 취소 시 다시 0
	public void updateSuggestionCheckCancel(String contentsid, String id) throws Exception;

	// 제주도 여행지 추천 수 취소
	public void updateSuggestionCancel(String contentsid) throws Exception;

	// 제주도 여행지 추천 취소 시 delete
	public void deleteSuggestion(String contentsid, String id) throws Exception;

	// 제주도 여행지 메인페이지 배너
	public List<ApiDTO> jejutourist_main(ApiDTO apiDTO) throws Exception;
}
