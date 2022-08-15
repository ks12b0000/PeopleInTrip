package kr.co.intrip.tourist.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.BusanCommentDTO;
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.weatherDTO;

public interface TouristService {

	// 관광지 api db에 저장용
	public void parkApi(String schAirportCode) throws Exception;
	
	// 부산 여행지 api db에 저장
	public void busanApi() throws Exception;
	
	// 제주도 날씨 api
	public weatherDTO apitest2(String weather) throws Exception;

	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) throws Exception;

	// 제주도 여행지 페이지 리스트
	public List<ApiDTO> jejutourist_list(PagingDTO pagingDTO) throws Exception;
	
	// 부산 여행지 총 개수
	public int busangetTotalRowCount(PagingDTO pagingDTO) throws Exception;

	// 부산 여행지 페이지 리스트
	public List<BusanApiDTO> busantourist_list(PagingDTO pagingDTO) throws Exception;

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
	
	// 부산 여행지 상세페이지
	public BusanApiDTO busantourist_detail(BusanApiDTO busanApiDTO) throws Exception;

	// 부산 여행지 상세페이지 조회수 증가
	public int busantourist_viewcount(BusanApiDTO busanApiDTO) throws Exception;

	// 부산 여행지 페이지 리스트 Sorting 기능
	public List<BusanApiDTO> busantourist_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception;
	
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

	//
	// 부산 댓글 수 증가
	public int busancommentcount(BusanApiDTO busanApiDTO) throws Exception;
	
	// 부산 댓글 수 감소
	public int busancommentcountminus(BusanApiDTO busanApiDTO) throws Exception;
	
	// 부산 댓글 총 개수
	public int busangetCommentTotalRowCount(CommentPagingDTO commentpaging) throws Exception;
	
	// 부산 댓글 조회
	public List<BusanCommentDTO> busanreadReply(CommentPagingDTO commentpaging) throws Exception;

	// 부산 댓글 작성
	public void busanregister(BusanCommentDTO busanCommentDTO) throws Exception;

	// 부산 댓글 수정
	public void busanmodify(BusanCommentDTO busanCommentDTO) throws Exception; 
	
	// 부산 댓글 삭제
	public void busanremove(BusanCommentDTO busanCommentDTO) throws Exception;
	
	// 부산 선택된 댓글 조회
	public BusanCommentDTO busanselectReply(int com_num) throws Exception;

	// 부산 여행지 찜 중복방지 select문
	public int busansteamedCheck(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 찜 시 steamed 테이블에 insert
	public void busaninsertSteamed(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 찜 수
	public void busanupdateSteamed(int UC_SEQ) throws Exception;

	// 부산 여행지 찜 시 Check를 1로 만들어서 중복방지
	public void busanupdateSteamedCheck(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 찜 취소 시 다시 0
	public void busanupdateSteamedCheckCancel(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 찜 수 취소
	public void busanupdateSteamedCancel(int UC_SEQ) throws Exception;

	// 부산 여행지 찜 취소 시 delete
	public void busandeleteSteamed(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 추천 중복방지 select문
	public int busanSuggestionCheck(int UC_SEQ, String id) throws Exception;
	
	// 부산 여행지 추천 시 steamed 테이블에 insert
	public void busaninsertSuggestion(int UC_SEQ, String id) throws Exception;		

	// 부산 여행지 추천 수
	public void busanupdateSuggestion(int UC_SEQ) throws Exception;	

	// 부산 여행지 추천 시 Check를 1로 만들어서 중복방지
	public void busanupdateSuggestionCheck(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 추천 취소 시 다시 0
	public void busanupdateSuggestionCheckCancel(int UC_SEQ, String id) throws Exception;

	// 부산 여행지 추천 수 취소
	public void busanupdateSuggestionCancel(int UC_SEQ) throws Exception;

	// 부산 여행지 추천 취소 시 delete
	public void busandeleteSuggestion(int UC_SEQ, String id) throws Exception;
	
	// 부산 여행지 메인페이지 배너
	public List<BusanApiDTO> busantourist_main(BusanApiDTO busanApiDTO) throws Exception;
	//

}
