package kr.co.intrip.mypage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.mypage.service.MyPageService;
import lombok.extern.java.Log;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService mypageService;
	
	@Autowired
	MyPageDTO mypageDTO;
	
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
	
	@RequestMapping(value = "mypage/update_modify_info", method = RequestMethod.POST)
	public String mypageUpdatePwAction(@RequestParam(value= "id",defaultValue = "", required = false)
				 String id, String pwd) throws Exception {
		mypageDTO.setId(id);
		mypageDTO.setPwd(pwd);
		mypageService.update_MyPage_Pw(mypageDTO);

		return "mypage/modify_info";
	}
	
	@RequestMapping(value = "mypage/update_mypage_nick_nm", method = RequestMethod.POST)
	public String mypageUpdateNickAction(@RequestParam(value= "id", defaultValue = "", required = false)
					String id, String nick_nm) throws Exception {
		mypageDTO.setId(id);
		mypageDTO.setPwd(nick_nm);
		
		System.out.println("ID : " + id);
		System.out.println("nick_nm : " + nick_nm);
		
		mypageService.update_MyPage_nick_nm(mypageDTO);
		
		return "mypage/modify_info";
	}
	
	// 닉네임 검사
	@ResponseBody
	@RequestMapping(value = "mypage/selectNickChk", method = RequestMethod.POST)
	public int selectNickChk(@RequestParam Map<String, Object> nick_nm) throws Exception {
		
		String nick = (String) nick_nm.get("nick_nm");
		mypageDTO.setNick_nm(nick);
		System.out.println("전달받은 nick : " + nick);
		int result = mypageService.selectNickChk(mypageDTO);
		System.out.println("확인 결과 : " + result);
		return result;
	}
	
	@GetMapping("mypage/member_delete.do")
	public String showDeleteMember() {
		return "mypage/member_delete";
	}
	
	// 회원 탈퇴
	@RequestMapping(value = "mypage/delteMember", method = RequestMethod.POST)
	public String deleteMember(MyPageDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		// 세션에 있는 user를 가져와 user 변수에 넣어준다.
		MyPageDTO user = (MyPageDTO) session.getAttribute("user");
		
		// 세션에 있는 비밀번호
		String sessionPass = user.getPwd();
		
		// dto로 들어오는 비밀번호
		String dtoPass = dto.getPwd();
		
		if(!(sessionPass.equals(dtoPass))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/mypage/member_delete.do";
		}
		mypageService.deleteMember(dto);
		session.invalidate();
		
		return "redirect:/mainpage/main";
	}
	

	
}
