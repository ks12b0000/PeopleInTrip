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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.login_signup.service.MemberService;
import kr.co.intrip.tourist.dto.ApiDTO;
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
	   
//	// 관광지 api db에 저장용
//	@GetMapping("tourist/tourist_PageList12")
//	public String testList(Model model) throws Exception {
//			
//		String schAirportCode = "alltag";
//			
//		tourservice.parkApi(schAirportCode);
//
//		return "tourist/tourist_PageList12";
//	}

	
	@GetMapping("tourist/tourist_PageList")
	public List<ApiDTO> jejutourist_List(ApiDTO apiDTO, HttpServletRequest request,Model model) throws Exception {
		String schAirportCode = "alltag";
		String pname = "테스트";
			
		List<ApiDTO> plist = tourservice.jejutourist_list(apiDTO);
		model.addAttribute("plist", plist);
		model.addAttribute("pname", pname);
		return plist;
	}
	
	//관광지 상세보기
	@RequestMapping(value = "tourist/tourist_View")
	public ModelAndView tourist_View (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		   
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		   
		mav.setViewName("tourist/tourist_View");
		   
		return mav;
	}
	
	
}
