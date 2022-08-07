package kr.co.intrip.tourist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.login_signup.service.MemberService;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.service.TouristService;
import lombok.extern.slf4j.Slf4j;

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
	public ApiDTO jejutourist_detail(ApiDTO apiDTO, Model model) throws Exception {
		String schAirportCode = "alltag";
		tourservice.jejutourist_viewcount(apiDTO);		
		ApiDTO plist = tourservice.jejutourist_detail(apiDTO);		
		model.addAttribute("plist", plist);
		return plist;
	}
	
	// 제주도 여행지 페이지 리스트 Sorting 기능
	@PostMapping("tourist/tourist_PageList")
	public List<ApiDTO> jejutourist_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejutourist_Sort(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		return plist;		
	}
	
	// 제주도 축제 페이지 리스트 Sorting 기능
	@PostMapping("tourist/festival_PageList")
	public List<ApiDTO> jejufestival_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount2(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejufestival_Sort(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		return plist;			
	}
	
	// 제주도 전시관 페이지 리스트 Sorting 기능
	@PostMapping("tourist/exhibition_PageList")
	public List<ApiDTO> jejuexhibition_Sort(Model model, HttpServletRequest request, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = tourservice.getTotalRowCount3(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<ApiDTO> plist = tourservice.jejuexhibition_Sort(pagingDTO, model, request);
		model.addAttribute("plist", plist);
		return plist;			
	}
	
	
}
