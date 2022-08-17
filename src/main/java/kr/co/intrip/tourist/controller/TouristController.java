package kr.co.intrip.tourist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.login_signup.service.MemberService;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.BusanCommentDTO;
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.weatherDTO;
import kr.co.intrip.tourist.service.TouristService;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@Controller
public class TouristController {

	@Autowired
	private TouristService tourservice;
	
	//제주도 메인화면   
	@GetMapping("tourist/travel_page")
	public String travel_page (Model model,ApiDTO apiDTO) throws Exception {
		weatherDTO wlist = tourservice.weatherapi();
		model.addAttribute("wlist",wlist);
		List<ApiDTO> mainlist = tourservice.jejutourist_main(apiDTO);
		model.addAttribute("mainlist", mainlist);
		
		return "tourist/travel_page";
	}
	
	//부산 메인화면   
	@GetMapping("tourist/busantravel_page")
	public String busantravel_page (Model model,BusanApiDTO busanApiDTO) throws Exception {
		weatherDTO wlist = tourservice.weatherapi2();
		model.addAttribute("wlist",wlist);
		List<BusanApiDTO> mainlist = tourservice.busantourist_main(busanApiDTO);
		model.addAttribute("mainlist", mainlist);
		
		return "tourist/busantravel_page";
	}
	   
	// 관광지 api db에 저장용
	@GetMapping("tourist/tourist_PageList12")
	public String testList(Model model) throws Exception {			
		String schAirportCode = "alltag";			
		tourservice.parkApi(schAirportCode);

		return "tourist/tourist_PageList12";
	}
	
	// 부산 여행지api db에 저장용
	@GetMapping("tourist/tourist_PageList13")
	public String busanApi() throws Exception {			
		tourservice.busanApi(); // 여행지
	
		return "tourist/tourist_PageList13";
	}
	
	// 부산 축제api db에 저장용
	@GetMapping("tourist/tourist_PageList14")
	public String busanApi2() throws Exception {			
		tourservice.busanApi2();  // 축제
		
		return "tourist/tourist_PageList14";
	}
	
	// 부산 체험api db에 저장용
	@GetMapping("tourist/tourist_PageList15")
	public String busanApi3() throws Exception {			
		tourservice.busanApi3();  // 축제
		
		return "tourist/tourist_PageList15";
	}
	
	// 제주도 여행지 페이지 리스트
	@GetMapping("tourist/tourist_PageList")
	public List<ApiDTO> jejutourist_List(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<ApiDTO> plist = tourservice.jejutourist_list(pagingDTO);
		model.addAttribute("plist", plist);

		return plist;
	}
	
	// 부산 여행지 페이지 리스트
	@GetMapping("tourist/busantourist_PageList")
	public List<BusanApiDTO> busantourist_List(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.busangetTotalRowCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<BusanApiDTO> plist = tourservice.busantourist_list(pagingDTO);
		model.addAttribute("plist", plist);

		return plist;
	}
	
	// 제주도 축제 페이지 리스트
	@GetMapping("tourist/festival_PageList")
	public List<ApiDTO> jejufestival_List(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount2(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejufestival_list(pagingDTO);
		
		model.addAttribute("plist", plist);
		return plist;
	}
	
	// 부산 축제 페이지 리스트
	@GetMapping("tourist/busanfestival_PageList")
	public List<BusanApiDTO> busanfestival_List(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.busangetTotalRowCount2(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<BusanApiDTO> plist = tourservice.busantourist_list2(pagingDTO);
		model.addAttribute("plist", plist);

		return plist;
	}
	
	// 제주도 전시관 페이지 리스트
	@GetMapping("tourist/exhibition_PageList")
	public List<ApiDTO> jejuexhibition_List(Model model, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount3(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejuexhibition_list(pagingDTO);
		model.addAttribute("plist", plist);
		
		return plist;
	}
	
	// 부산 체험 페이지 리스트
	@GetMapping("tourist/busanexperience_PageList")
	public List<BusanApiDTO> busanexperience_List(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.busangetTotalRowCount3(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<BusanApiDTO> plist = tourservice.busantourist_list3(pagingDTO);
		model.addAttribute("plist", plist);

		return plist;
	}
	
	// 제주도 여행지 상세페이지 
	@GetMapping("tourist/tourist_View")
	public String jejutourist_detail(ApiDTO apiDTO, Model model, @ModelAttribute("commentpagingDTO")CommentPagingDTO commentpagingDTO) throws Exception {		
		String schAirportCode = "alltag";
		tourservice.jejutourist_viewcount(apiDTO);		
		ApiDTO plist = tourservice.jejutourist_detail(apiDTO);		
		model.addAttribute("plist", plist);
		
		
		int totalRowCount = tourservice.getCommentTotalRowCount(commentpagingDTO);
		commentpagingDTO.setTotalRowCount(totalRowCount);
		commentpagingDTO.pageSetting();
		List<JejuCommentDTO> replyList = tourservice.jejureadReply(commentpagingDTO);
		
		model.addAttribute("replyList", replyList);
		return "tourist/tourist_View";
	}
	
	// 제주도 여행지 페이지 리스트 Sorting 기능
	@GetMapping("tourist/tourist_PageList_Sorting")
	public List<ApiDTO> jejutourist_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejutourist_Sort(pagingDTO, model, request);
		
		model.addAttribute("plist", plist);
		return plist;		
	}
	
	// 부산 여행지 상세페이지 
	@GetMapping("tourist/busantourist_View")
	public String busantourist_detail(BusanApiDTO busanApiDTO, Model model, @ModelAttribute("commentpagingDTO")CommentPagingDTO commentpagingDTO) throws Exception {		
		tourservice.busantourist_viewcount(busanApiDTO);		
		BusanApiDTO plist = tourservice.busantourist_detail(busanApiDTO);		
		model.addAttribute("plist", plist);
		
		
		int totalRowCount = tourservice.busangetCommentTotalRowCount(commentpagingDTO);
		commentpagingDTO.setTotalRowCount(totalRowCount);
		commentpagingDTO.pageSetting();
		List<BusanCommentDTO> replyList = tourservice.busanreadReply(commentpagingDTO);
		model.addAttribute("replyList", replyList);
		
		return "tourist/busantourist_View";
	}
	
	// 부산 여행지 페이지 리스트 Sorting 기능
	@GetMapping("tourist/busantourist_PageList_Sorting")
	public List<BusanApiDTO> busantourist_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.busangetTotalRowCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BusanApiDTO> plist = tourservice.busantourist_Sort(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		
		return plist;		
	}
	
	// 제주도 축제 페이지 리스트 Sorting 기능
	@GetMapping("tourist/festival_PageList_Sorting")
	public List<ApiDTO> jejufestival_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount2(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejufestival_Sort(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		
		return plist;			
	}
	
	// 부산 축제 상세페이지 
	@GetMapping("tourist/busanfestival_View")
	public String busanfestival_detail(BusanApiDTO busanApiDTO, Model model, @ModelAttribute("commentpagingDTO")CommentPagingDTO commentpagingDTO) throws Exception {		
		tourservice.busantourist_viewcount2(busanApiDTO);		
		BusanApiDTO plist = tourservice.busantourist_detail2(busanApiDTO);		
		model.addAttribute("plist", plist);
		
		
		int totalRowCount = tourservice.busangetCommentTotalRowCount2(commentpagingDTO);
		commentpagingDTO.setTotalRowCount(totalRowCount);
		commentpagingDTO.pageSetting();
		List<BusanCommentDTO> replyList = tourservice.busanreadReply2(commentpagingDTO);
		model.addAttribute("replyList", replyList);
		
		return "tourist/busanfestival_View";
	}
	
	// 부산 축제 페이지 리스트 Sorting 기능
	@GetMapping("tourist/busanfestival_PageList_Sorting")
	public List<BusanApiDTO> busanfestival_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.busangetTotalRowCount2(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BusanApiDTO> plist = tourservice.busantourist_Sort2(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		
		return plist;		
	}
	
	// 제주도 전시관 페이지 리스트 Sorting 기능
	@GetMapping("tourist/exhibition_PageList_Sorting")
	public List<ApiDTO> jejuexhibition_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount3(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejuexhibition_Sort(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		
		return plist;			
	}
	
	// 부산 체험 상세페이지 
	@GetMapping("tourist/busanexperience_View")
	public String busanexperience_detail(BusanApiDTO busanApiDTO, Model model, @ModelAttribute("commentpagingDTO")CommentPagingDTO commentpagingDTO) throws Exception {		
		tourservice.busantourist_viewcount3(busanApiDTO);		
		BusanApiDTO plist = tourservice.busantourist_detail3(busanApiDTO);		
		model.addAttribute("plist", plist);
		
		
		int totalRowCount = tourservice.busangetCommentTotalRowCount3(commentpagingDTO);
		commentpagingDTO.setTotalRowCount(totalRowCount);
		commentpagingDTO.pageSetting();
		List<BusanCommentDTO> replyList = tourservice.busanreadReply3(commentpagingDTO);
		model.addAttribute("replyList", replyList);
		
		return "tourist/busanexperience_View";
	}
	
	// 부산 체험 페이지 리스트 Sorting 기능
	@GetMapping("tourist/busanexperience_PageList_Sorting")
	public List<BusanApiDTO> busanexperience_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.busangetTotalRowCount3(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BusanApiDTO> plist = tourservice.busantourist_Sort3(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		
		return plist;		
	}
	
	// 제주도 댓글 작성
	@PostMapping("tourist/jejureplyWrite")
	public String jejureplyWrite(JejuCommentDTO jejuDTO,ApiDTO apiDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply write");
		tourservice.jejuregister(jejuDTO);
		tourservice.jejucommentcount(apiDTO);
		rttr.addAttribute("contentsid", jejuDTO.getContentsid());
		
		return "redirect:/tourist/tourist_View";
	}
	
	// 제주도 댓글 수정 페이지
	@GetMapping("tourist/jejureplyUpdateView")
	public String jejureplyUpdateView(JejuCommentDTO jejuDTO, PagingDTO pagingDTO, Model model) throws Exception {
		log.info("reply write");
			
		JejuCommentDTO reply = tourservice.jejuselectReply(jejuDTO.getCom_num());
		log.info("댓글번호 : " + reply.getCom_num());
		model.addAttribute("replyUpdate", tourservice.jejuselectReply(jejuDTO.getCom_num()));
		model.addAttribute("pagingDTO", pagingDTO);

		return "tourist/jejureplyUpdateView";
	}
		
	// 제주도 댓글 수정 폼
	@PostMapping("tourist/jejureplyUpdate")
	public String jejureplyUpdate(JejuCommentDTO jejuDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply Write");
		
		tourservice.jejumodify(jejuDTO);			
		rttr.addAttribute("contentsid", jejuDTO.getContentsid());
			
		return "redirect:/tourist/tourist_View";
	}
	
	// 제주도 댓글 삭제 폼
	@PostMapping("tourist/jejureplyDelete")
	public String jejureplyDelete(JejuCommentDTO jejuDTO,ApiDTO apiDTO, PagingDTO pagingDTO,Model model, RedirectAttributes rttr) throws Exception {
		log.info("reply delete");

		tourservice.jejuremove(jejuDTO);
		tourservice.jejucommentcountminus(apiDTO);
		rttr.addAttribute("contentsid", jejuDTO.getContentsid());
			
		return "redirect:/tourist/tourist_View";
	}
	
	// 제주도 여행지 찜하기
	@PostMapping("tourist/updatesteamed")
	@ResponseBody
	public String updateSteamed(String contentsid,  String id)throws Exception{
		
		String steamedCheck = tourservice.steamedCheck(contentsid, id);

		if(steamedCheck.equals("0")) {
			//좋아요 처음누름
			tourservice.insertSteamed(contentsid, id); //like테이블 삽입
			tourservice.updateSteamed(contentsid);	//게시판테이블 +1
			tourservice.updateSteamedCheck(contentsid, id);//like테이블 구분자 1
		}
		else if(steamedCheck.equals("1")) {
			tourservice.updateSteamedCheckCancel(contentsid, id); //like테이블 구분자0
			tourservice.updateSteamedCancel(contentsid); //게시판테이블 - 1
			tourservice.deleteSteamed(contentsid, id); //like테이블 삭제
		}
		return steamedCheck;
	}
	
	// 제주도 여행지 추천기능
	@PostMapping("tourist/updateSuggestion")
	@ResponseBody
	public String updateSuggestion(String contentsid,  String id)throws Exception{			
		String suggestionCheck = tourservice.SuggestionCheck(contentsid, id);

		if(suggestionCheck.equals("0")) {
			//추천 처음누름
			tourservice.insertSuggestion(contentsid, id); 
			tourservice.updateSuggestion(contentsid);	
			tourservice.updateSuggestionCheck(contentsid, id);
		}
		else if(suggestionCheck.equals("1")) {
			tourservice.updateSuggestionCheckCancel(contentsid, id); 
			tourservice.updateSuggestionCancel(contentsid); 
			tourservice.deleteSuggestion(contentsid, id); 
		}
		return suggestionCheck;
	}

	// 부산 댓글 작성
	@PostMapping("tourist/busanreplyWrite")
	public String busanreplyWrite(BusanCommentDTO busanCommentDTO, BusanApiDTO busanApiDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply write");
		tourservice.busanregister(busanCommentDTO);
		tourservice.busancommentcount(busanApiDTO);
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
		
		return "redirect:/tourist/busantourist_View";
	}
	
	// 부산 댓글 수정 페이지
	@GetMapping("tourist/busanreplyUpdateView")
	public String busanreplyUpdateView(BusanCommentDTO busanCommentDTO, PagingDTO pagingDTO, Model model) throws Exception {
		log.info("reply write");
			
		BusanCommentDTO reply = tourservice.busanselectReply(busanCommentDTO.getCom_num());
		log.info("댓글번호 : " + reply.getCom_num());
		model.addAttribute("replyUpdate", tourservice.busanselectReply(busanCommentDTO.getCom_num()));
		model.addAttribute("pagingDTO", pagingDTO);

		return "tourist/busanreplyUpdateView";
	}
		
	// 부산 댓글 수정 폼
	@PostMapping("tourist/busanreplyUpdate")
	public String busanreplyUpdate(BusanCommentDTO busanCommentDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply Write");
		
		tourservice.busanmodify(busanCommentDTO);		
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
			
		return "redirect:/tourist/busantourist_View";
	}
	
	// 부산 댓글 삭제 폼
	@PostMapping("tourist/busanreplyDelete")
	public String busanreplyDelete(BusanCommentDTO busanCommentDTO, BusanApiDTO busanApiDTO, PagingDTO pagingDTO,Model model, RedirectAttributes rttr) throws Exception {
		log.info("reply delete");

		tourservice.busanremove(busanCommentDTO);
		tourservice.busancommentcountminus(busanApiDTO);
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
			
		return "redirect:/tourist/busantourist_View";
	}
	
	// 부산 여행지 찜하기
	@PostMapping("tourist/busanupdatesteamed")
	@ResponseBody
	public int busanupdateSteamed(int UC_SEQ,  String id)throws Exception{
		
		int steamedCheck = tourservice.busansteamedCheck(UC_SEQ, id);

		if(steamedCheck == 0) {
			//좋아요 처음누름
			tourservice.busaninsertSteamed(UC_SEQ, id); //like테이블 삽입
			tourservice.busanupdateSteamed(UC_SEQ);	//게시판테이블 +1
			tourservice.busanupdateSteamedCheck(UC_SEQ, id);//like테이블 구분자 1
		}
		else if(steamedCheck == 1) {
			tourservice.busanupdateSteamedCheckCancel(UC_SEQ, id); //like테이블 구분자0
			tourservice.busanupdateSteamedCancel(UC_SEQ); //게시판테이블 - 1
			tourservice.busandeleteSteamed(UC_SEQ, id); //like테이블 삭제
		}
		return steamedCheck;
	}
	
	// 부산 여행지 추천기능
	@PostMapping("tourist/busanupdateSuggestion")
	@ResponseBody
	public int busanupdateSuggestion(int UC_SEQ,  String id)throws Exception{			
		int suggestionCheck = tourservice.busanSuggestionCheck(UC_SEQ, id);

		if(suggestionCheck == 0) {
			//추천 처음누름
			tourservice.busaninsertSuggestion(UC_SEQ, id); 
			tourservice.busanupdateSuggestion(UC_SEQ);	
			tourservice.busanupdateSuggestionCheck(UC_SEQ, id);
		}
		else if(suggestionCheck == 1) {
			tourservice.busanupdateSuggestionCheckCancel(UC_SEQ, id); 
			tourservice.busanupdateSuggestionCancel(UC_SEQ); 
			tourservice.busandeleteSuggestion(UC_SEQ, id); 
		}
		return suggestionCheck;
	}

	// 부산 축제 댓글 작성
	@PostMapping("tourist/busanreplyWrite2")
	public String busanreplyWrite2(BusanCommentDTO busanCommentDTO, BusanApiDTO busanApiDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply write");
		tourservice.busanregister2(busanCommentDTO);
		tourservice.busancommentcount2(busanApiDTO);
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
		
		return "redirect:/tourist/busanfestival_View";
	}
	
	// 부산 축제 댓글 수정 페이지
	@GetMapping("tourist/busanreplyUpdateView2")
	public String busanreplyUpdateView2(BusanCommentDTO busanCommentDTO, PagingDTO pagingDTO, Model model) throws Exception {
		log.info("reply write");
			
		BusanCommentDTO reply = tourservice.busanselectReply2(busanCommentDTO.getCom_num());
		log.info("댓글번호 : " + reply.getCom_num());
		model.addAttribute("replyUpdate", tourservice.busanselectReply2(busanCommentDTO.getCom_num()));
		model.addAttribute("pagingDTO", pagingDTO);

		return "tourist/busanreplyUpdateView2";
	}
		
	// 부산 축제 댓글 수정 폼
	@PostMapping("tourist/busanreplyUpdate2")
	public String busanreplyUpdate2(BusanCommentDTO busanCommentDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply Write");
		
		tourservice.busanmodify2(busanCommentDTO);			
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
			
		return "redirect:/tourist/busanfestival_View";
	}
	
	// 부산 축제 댓글 삭제 폼
	@PostMapping("tourist/busanreplyDelete2")
	public String busanreplyDelete2(BusanCommentDTO busanCommentDTO, BusanApiDTO busanApiDTO, PagingDTO pagingDTO,Model model, RedirectAttributes rttr) throws Exception {
		log.info("reply delete");

		tourservice.busanremove2(busanCommentDTO);
		tourservice.busancommentcountminus2(busanApiDTO);
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
			
		return "redirect:/tourist/busanfestival_View";
	}
	
	// 부산 축제 찜하기
	@PostMapping("tourist/busanupdatesteamed2")
	@ResponseBody
	public int busanupdateSteamed2(int UC_SEQ,  String id)throws Exception{
		
		int steamedCheck = tourservice.busansteamedCheck2(UC_SEQ, id);

		if(steamedCheck == 0) {
			//좋아요 처음누름
			tourservice.busaninsertSteamed2(UC_SEQ, id); //like테이블 삽입
			tourservice.busanupdateSteamed2(UC_SEQ);	//게시판테이블 +1
			tourservice.busanupdateSteamedCheck2(UC_SEQ, id);//like테이블 구분자 1
		}
		else if(steamedCheck == 1) {
			tourservice.busanupdateSteamedCheckCancel2(UC_SEQ, id); //like테이블 구분자0
			tourservice.busanupdateSteamedCancel2(UC_SEQ); //게시판테이블 - 1
			tourservice.busandeleteSteamed2(UC_SEQ, id); //like테이블 삭제
		}
		return steamedCheck;
	}
	
	// 부산 축제 추천기능
	@PostMapping("tourist/busanupdateSuggestion2")
	@ResponseBody
	public int busanupdateSuggestion2(int UC_SEQ,  String id)throws Exception{			
		int suggestionCheck = tourservice.busanSuggestionCheck2(UC_SEQ, id);

		if(suggestionCheck == 0) {
			//추천 처음누름
			tourservice.busaninsertSuggestion2(UC_SEQ, id); 
			tourservice.busanupdateSuggestion2(UC_SEQ);	
			tourservice.busanupdateSuggestionCheck2(UC_SEQ, id);
		}
		else if(suggestionCheck == 1) {
			tourservice.busanupdateSuggestionCheckCancel2(UC_SEQ, id); 
			tourservice.busanupdateSuggestionCancel2(UC_SEQ); 
			tourservice.busandeleteSuggestion2(UC_SEQ, id); 
		}
		return suggestionCheck;
	}
	
	// 부산 체험 댓글 작성
	@PostMapping("tourist/busanreplyWrite3")
	public String busanreplyWrite3(BusanCommentDTO busanCommentDTO, BusanApiDTO busanApiDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply write");
		tourservice.busanregister3(busanCommentDTO);
		tourservice.busancommentcount3(busanApiDTO);
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
		
		return "redirect:/tourist/busanexperience_View";
	}
	
	// 부산 체험 댓글 수정 페이지
	@GetMapping("tourist/busanreplyUpdateView3")
	public String busanreplyUpdateView3(BusanCommentDTO busanCommentDTO, PagingDTO pagingDTO, Model model) throws Exception {
		log.info("reply write");
			
		BusanCommentDTO reply = tourservice.busanselectReply3(busanCommentDTO.getCom_num());
		log.info("댓글번호 : " + reply.getCom_num());
		model.addAttribute("replyUpdate", tourservice.busanselectReply3(busanCommentDTO.getCom_num()));
		model.addAttribute("pagingDTO", pagingDTO);

		return "tourist/busanreplyUpdateView3";
	}
		
	// 부산 체험 댓글 수정 폼
	@PostMapping("tourist/busanreplyUpdate3")
	public String busanreplyUpdate3(BusanCommentDTO busanCommentDTO, PagingDTO pagingDTO, RedirectAttributes rttr) throws Exception {
		log.info("reply Write");
		
		tourservice.busanmodify3(busanCommentDTO);			
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
			
		return "redirect:/tourist/busanexperience_View";
	}
	
	// 부산 체험 댓글 삭제 폼
	@PostMapping("tourist/busanreplyDelete3")
	public String busanreplyDelete3(BusanCommentDTO busanCommentDTO, BusanApiDTO busanApiDTO, PagingDTO pagingDTO,Model model, RedirectAttributes rttr) throws Exception {
		log.info("reply delete");

		tourservice.busanremove3(busanCommentDTO);
		tourservice.busancommentcountminus3(busanApiDTO);
		rttr.addAttribute("UC_SEQ", busanCommentDTO.getUC_SEQ());
			
		return "redirect:/tourist/busanexperience_View";
	}
	
	// 부산 체험 찜하기
	@PostMapping("tourist/busanupdatesteamed3")
	@ResponseBody
	public int busanupdateSteamed3(int UC_SEQ,  String id)throws Exception{
		
		int steamedCheck = tourservice.busansteamedCheck3(UC_SEQ, id);

		if(steamedCheck == 0) {
			//좋아요 처음누름
			tourservice.busaninsertSteamed3(UC_SEQ, id); //like테이블 삽입
			tourservice.busanupdateSteamed3(UC_SEQ);	//게시판테이블 +1
			tourservice.busanupdateSteamedCheck3(UC_SEQ, id);//like테이블 구분자 1
		}
		else if(steamedCheck == 1) {
			tourservice.busanupdateSteamedCheckCancel3(UC_SEQ, id); //like테이블 구분자0
			tourservice.busanupdateSteamedCancel3(UC_SEQ); //게시판테이블 - 1
			tourservice.busandeleteSteamed3(UC_SEQ, id); //like테이블 삭제
		}
		return steamedCheck;
	}
	
	// 부산 체험 추천기능
	@PostMapping("tourist/busanupdateSuggestion3")
	@ResponseBody
	public int busanupdateSuggestion3(int UC_SEQ,  String id)throws Exception{			
		int suggestionCheck = tourservice.busanSuggestionCheck3(UC_SEQ, id);

		if(suggestionCheck == 0) {
			//추천 처음누름
			tourservice.busaninsertSuggestion3(UC_SEQ, id); 
			tourservice.busanupdateSuggestion3(UC_SEQ);	
			tourservice.busanupdateSuggestionCheck3(UC_SEQ, id);
		}
		else if(suggestionCheck == 1) {
			tourservice.busanupdateSuggestionCheckCancel3(UC_SEQ, id); 
			tourservice.busanupdateSuggestionCancel3(UC_SEQ); 
			tourservice.busandeleteSuggestion3(UC_SEQ, id); 
		}
		return suggestionCheck;
	}
	
}
