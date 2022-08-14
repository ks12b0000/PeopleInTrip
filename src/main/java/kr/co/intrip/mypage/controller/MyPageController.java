package kr.co.intrip.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.PageMaker;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.board.service.BoardService;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.mypage.service.MyPageService;
import lombok.extern.java.Log;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService mypageService;
	
	@Autowired
	MyPageDTO mypageDTO;
	
	@Autowired
	MyBoardDTO myboardDTO;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("mypage/mypage_renewal.do")
	public String myboardtest() {
		return "mypage/mypage_renewal";
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
	
	@GetMapping("mypage/mypage_renewal_test.do")
	public String showMyBoard() {
		return "mypage/mypage_renewal_test";
	}
	
	 //내가 쓴 글
	@RequestMapping(value = "mypage/mypage_renewal", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(@RequestParam(value = "id", defaultValue = "", required = false) String id,
						@ModelAttribute("scri") SearchCriteria scri, Model model,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어온 id : " + id);
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
//		List<MyBoardDTO> boardsList =  mypageService.listArticles(id);
//		mav.addObject("myboardsList", boardsList);
		
		System.out.println("들어온 scri : " + scri);
		List<MyBoardDTO> boardsList2 = mypageService.listfind(scri);
		model.addAttribute("myboardsList", boardsList2);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(mypageService.findlistCount(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		return mav;
	}
	
	// 내가 쓴 글 보기1
	@RequestMapping(value = "mypage/myview_detail", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewMyBoard1(@RequestParam(value = "post_title") String post_title,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("들어온 post_num1 : " + post_title);
		
		String viewName = (String) request.getAttribute("viewName");
		
		Map<String, Object> boardMap = mypageService.showMyBoard1(post_title);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardMap", boardMap);
		
		return mav;
	}
	
}
