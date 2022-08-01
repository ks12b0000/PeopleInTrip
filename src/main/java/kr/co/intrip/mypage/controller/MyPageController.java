package kr.co.intrip.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.mypage.service.MyPageService;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService mypageService;
	
	@Autowired
	MyPageDTO mypageDTO;

//	@RequestMapping(value = "mypage/mypage_renewal")
//	public ModelAndView mypage (HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		String viewName = (String) request.getAttribute("viewName");
//		List<MyPageDTO> memberList = mypageService.listMember();
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("memberList", memberList);
//		
//		return mav;
//	}
	
	@RequestMapping(value = "mypage/mypage_renewal")
	public ModelAndView mypage(@ModelAttribute MyPageDTO mypageDTO, HttpSession session, 
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		MyPageDTO user = mypageService.MyPage(mypageDTO, session);
		ModelAndView mav = new ModelAndView();
		if (user != null) {
			session.setAttribute("user", user);
		}
		return mav;
	}
	
	@RequestMapping(value = "mypage/modify_info")
	public ModelAndView modify_info (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		
		mav.setViewName(viewName);
		
		return mav;
	}
}
