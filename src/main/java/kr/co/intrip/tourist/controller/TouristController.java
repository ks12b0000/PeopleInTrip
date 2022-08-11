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
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.service.TouristService;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@Controller
public class TouristController {

	@Autowired
	private TouristService tourservice;
	
	//관광지 메인화면   
	@RequestMapping(value = "tourist/travel_page_kms")
	public ModelAndView travel_page_kms (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		   
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		   
		mav.setViewName("tourist/travel_page_kms");
		   
		return mav;
	}
	   
	// 관광지 api db에 저장용
	@GetMapping("tourist/tourist_PageList12")
	public String testList(Model model) throws Exception {
			
		String schAirportCode = "alltag";
			
		tourservice.parkApi(schAirportCode);

		return "tourist/tourist_PageList12";
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

	
}
