package kr.co.intrip.tourist.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.co.intrip.tourist.controller.TouristController;
import kr.co.intrip.tourist.dao.TouristDAO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TouristServiceImpl implements TouristService {

	@Autowired
	private TouristDAO touristDAO;
	
	// 관광지 api db에 저장용
	@Override
	public void parkApi(String schAirportCode) throws Exception {
		ArrayList<ApiDTO> list = new ArrayList<ApiDTO>();
		// url
		StringBuilder urlBuilder = new StringBuilder("http://api.visitjeju.net/vsjApi/contents/searchList?apiKey=lvg5ciolx7x4i2je&locale=kr&page=46");
		
		URL url = new URL(urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		/* System.out.println("Response code: " + conn.getResponseCode()); */
		BufferedReader rd;
		
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} 
		else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		/* System.out.println(sb.toString()); */
		// ========================= (list code) =========================
		String jsonString = sb.toString();
		// 가장 큰 JSONObject를 가져옵니다.
		JSONObject jObject = new JSONObject(jsonString);

		// (response) 0번째 JSONObject를 가져옵니다.
		String responseObject = jObject.getString("result");
		// (response -> header) 1번째 JSONObject를 가져와서 key-value를 읽습니다.
		/* log.info("(header)resultCode={}" , responseObject); */
		
		JSONArray itemArray = jObject.getJSONArray("items");
		
			for (int i = 0; i < itemArray.length(); i++) {					
				ApiDTO pvo = new ApiDTO();
				JSONObject iobj = itemArray.getJSONObject(i);					
				JSONObject iobj2 = new JSONObject(iobj);
					
				if(!(itemArray.getJSONObject(i).isNull("alltag"))) {
					pvo.setAlltag(iobj.getString("alltag"));
				}	
				
				if(!(itemArray.getJSONObject(i).isNull("contentsid"))) {
					pvo.setContentsid(iobj.getString("contentsid"));
				}
									
				JSONObject itemArray2 = iobj.getJSONObject("contentscd");
				pvo.setLabel(itemArray2.getString("label"));				
				pvo.setTitle(iobj.getString("title"));
					
				if(!(itemArray.getJSONObject(i).isNull("region1cd"))) {
					JSONObject itemArray3 = iobj.getJSONObject("region1cd");
					pvo.setLabel2(itemArray3.getString("label"));
				}

				if(!(itemArray.getJSONObject(i).isNull("address"))) {
					pvo.setAddress(iobj.getString("address"));
				}	
					
				if(!(itemArray.getJSONObject(i).isNull("tag"))) {
					pvo.setTag(iobj.getString("tag"));
				}	
				
				if(!(itemArray.getJSONObject(i).isNull("introduction"))) {
					pvo.setIntroduction(iobj.getString("introduction"));
				}
				
				if(!(itemArray.getJSONObject(i).isNull("latitude"))) {
					pvo.setLatitude(iobj.getDouble("latitude"));
				}
				
				if(!(itemArray.getJSONObject(i).isNull("longitude"))) {
					pvo.setLongitude(iobj.getDouble("longitude"));
				}
				
				if(!(itemArray.getJSONObject(i).isNull("phoneno"))) {
					pvo.setPhoneno(iobj.getString("phoneno"));
				}
					
				if(!(itemArray.getJSONObject(i).isNull("repPhoto"))) {
					JSONObject itemArray4 = iobj.getJSONObject("repPhoto");
					JSONObject itemArray5 = itemArray4.getJSONObject("photoid");
					pvo.setImgpath(itemArray5.getString("imgpath"));
				}

				/* log.info(i + "번째 item: " + pvo); */
				list.add(pvo);
			}
			/* System.out.println("list: " + list); */
			
			
			touristDAO.touristadd(list);
	}

	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) throws Exception {
		return touristDAO.getTotalRowCount(pagingDTO);
	}
	
	// 제주도 여행지 페이지 리스트
	@Override
	public List<ApiDTO> jejutourist_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.jejutourist(pagingDTO);		
	}
	
	// 제주도 축제 총 개수
	public int getTotalRowCount2(PagingDTO pagingDTO) throws Exception {
		return touristDAO.getTotalRowCount2(pagingDTO);
	}

	// 제주도 축제 페이지 리스트
	@Override
	public List<ApiDTO> jejufestival_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.jejufestival(pagingDTO);	
	}
	
	// 제주도 여행지 총 개수
	public int getTotalRowCount3(PagingDTO pagingDTO) throws Exception {
		return touristDAO.getTotalRowCount3(pagingDTO);
	}

	// 제주도 전시관 페이지 리스트
	@Override
	public List<ApiDTO> jejuexhibition_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.jejuexhibition(pagingDTO);
	}

	// 제주도 통합 상세페이지
	@Override
	public ApiDTO jejutourist_detail(ApiDTO apiDTO) throws Exception {
		return touristDAO.jejudetail(apiDTO);
	}

	// 제주도 통합 상세페이지 조회수 증가
	@Override
	public int jejutourist_viewcount(ApiDTO apiDTO) throws Exception {
		return touristDAO.viewcount(apiDTO);
	}

	// 제주도 여행지 페이지 리스트 Sorting 기능
	@Override
	public List<ApiDTO> jejutourist_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.jejutourist(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.jejutourist_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.jejutourist_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.jejutourist_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.jejutourist_SuggestionSort(pagingDTO);		
		}
	}
	
	// 제주도 축제 페이지 리스트 Sorting 기능
	@Override
	public List<ApiDTO> jejufestival_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.jejufestival(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.jejufestival_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.jejufestival_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.jejufestival_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.jejufestival_SuggestionSort(pagingDTO);		
		}
	}
		
	// 제주도 전시관 페이지 리스트 Sorting 기능
	@Override
	public List<ApiDTO> jejuexhibition_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.jejuexhibition(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.jejuexhibition_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.jejuexhibition_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.jejuexhibition_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.jejuexhibition_lookupSort(pagingDTO);		
		}
	}
	
	// 제주도 댓글 수 증가
	@Override
	public int jejucommentcount(ApiDTO apiDTO) throws Exception {
		return touristDAO.commentcount(apiDTO);
	}
	
	// 제주도 댓글 수 감소
	@Override
	public int jejucommentcountminus(ApiDTO apiDTO) throws Exception {
		return touristDAO.commentcountminus(apiDTO);
	}
	
	// 제주도 댓글 총 개수
	@Override
	public int getCommentTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.CommentgetTotalRowCount(commentpagingDTO);
	}

	// 제주도 댓글 조회
	@Override
	public List<JejuCommentDTO> jejureadReply (CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.jejureadReply(commentpagingDTO);
	}
	
	// 제주도 댓글 작성
	@Override
	public void jejuregister(JejuCommentDTO jejuDTO) throws Exception {
		touristDAO.jejucreate(jejuDTO);
	}
	
	// 제주도 댓글 수정
	@Override
	public void jejumodify(JejuCommentDTO jejuDTO) throws Exception {  
		touristDAO.jejuupdate(jejuDTO);		
	}
	
	// 제주도 댓글 삭제
	@Override
	public void jejuremove(JejuCommentDTO jejuDTO) throws Exception {  
		touristDAO.jejudeleteReply(jejuDTO);
	}
	
	// 제주도 선택된 댓글 조회
	@Override
	public JejuCommentDTO jejuselectReply(int com_num) throws Exception {  
		return touristDAO.jejuselectReply(com_num);
	}
	
	// 제주도 여행지 찜 중복방지 select문
	@Override
	public String steamedCheck(String contentsid, String id) throws Exception {
		return touristDAO.SteamedCheck(contentsid, id);
	}

	// 제주도 여행지 찜 시 steamed 테이블에 insert
	@Override
	public void insertSteamed(String contentsid, String id) throws Exception {
		touristDAO.insertSteamed(contentsid, id);
	}

	// 제주도 여행지 찜 수
	@Override
	public void updateSteamed(String contentsid) throws Exception {
		touristDAO.updateSteamed(contentsid);
	}

	// 제주도 여행지 찜 시 Check를 1로 만들어서 중복방지
	@Override
	public void updateSteamedCheck(String contentsid, String id) throws Exception {
		touristDAO.updateSteamedCheck(contentsid, id);
	}

	// 제주도 여행지 찜 취소 시 다시 0
	@Override
	public void updateSteamedCheckCancel(String contentsid, String id) throws Exception {
		touristDAO.updateSteamedCheckCancel(contentsid, id);
	}

	// 제주도 여행지 찜 수 취소
	@Override
	public void updateSteamedCancel(String contentsid) throws Exception {
		touristDAO.updateSteamedCancel(contentsid);
	}

	// 제주도 여행지 찜 취소 시 delete
	@Override
	public void deleteSteamed(String contentsid, String id) throws Exception {
		touristDAO.deleteSteamed(contentsid, id);
	}

	// 제주도 여행지 추천 중복방지 select문
	@Override
	public String SuggestionCheck(String contentsid, String id) throws Exception {
		return touristDAO.SuggestionCheck(contentsid, id);
	}

	// 제주도 여행지 추천 시 steamed 테이블에 insert
	@Override
	public void insertSuggestion(String contentsid, String id) throws Exception {
		touristDAO.insertSuggestion(contentsid, id);
	}

	// 제주도 여행지 추천 수
	@Override
	public void updateSuggestion(String contentsid) throws Exception {
		touristDAO.updateSuggestion(contentsid);
	}

	// 제주도 여행지 추천 시 Check를 1로 만들어서 중복방지
	@Override
	public void updateSuggestionCheck(String contentsid, String id) throws Exception {
		touristDAO.updateSuggestionCheck(contentsid, id);
	}

	// 제주도 여행지 추천 취소 시 다시 0
	@Override
	public void updateSuggestionCheckCancel(String contentsid, String id) throws Exception {
		touristDAO.updateSuggestionCheckCancel(contentsid, id);
	}

	// 제주도 여행지 추천 수 취소
	@Override
	public void updateSuggestionCancel(String contentsid) throws Exception {
		touristDAO.updateSuggestionCancel(contentsid);
	}

	// 제주도 여행지 추천 취소 시 delete
	@Override
	public void deleteSuggestion(String contentsid, String id) throws Exception {
		touristDAO.deleteSuggestion(contentsid, id);
	}


}
