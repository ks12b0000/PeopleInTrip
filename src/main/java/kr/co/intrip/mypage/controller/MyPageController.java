package kr.co.intrip.mypage.controller;

import java.net.http.HttpRequest;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.Criteria;
import kr.co.intrip.board.dto.PageMaker;
import kr.co.intrip.board.dto.SearchCriteria;
import kr.co.intrip.board.service.BoardService;
import kr.co.intrip.login_signup.dto.MemberDTO;
import kr.co.intrip.mypage.dto.MyBoardDTO;
import kr.co.intrip.mypage.dto.MyPageDTO;
import kr.co.intrip.mypage.service.MyPageService;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.Tourlist_SteamedDTO;
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
	
//	@GetMapping("mypage/mypage_renewal.do")
//	public String myboardtest() {
//		return "mypage/mypage_renewal";
//	}
	
	@RequestMapping(value = "mypage/modify_info")
	public ModelAndView modify_info (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		
		mav.setViewName(viewName);
		
		return mav;
	}
	
	// 비밀번호 변경
	@RequestMapping(value = "mypage/update_modify_info", method = RequestMethod.POST)
	public String mypageUpdatePwAction(@RequestParam(value= "id",defaultValue = "", required = false)
					String id, String pwd) throws Exception {
		mypageDTO.setId(id);
		mypageDTO.setPwd(pwd);
		mypageService.update_MyPage_Pw(mypageDTO);

		return "mypage/modify_info";
	}
	
	// 닉네임 변경
	@RequestMapping(value = "mypage/update_mypage_nick_nm", method = RequestMethod.POST)	
	public String mypageUpdateNickAction(@RequestParam(value= "id", defaultValue = "", required = false)
					String id, String nick_nm) throws Exception {
		mypageDTO.setId(id);
		mypageDTO.setPwd(nick_nm);
		
		System.out.println("ID : " + id);
		System.out.println("nick_nm : " + nick_nm);
		
		mypageService.update_MyPage_nick_nm(mypageDTO);		
		
		return "login_signup/login";
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
	public String deleteMember(MemberDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		// 세션에 있는 user를 가져와 user 변수에 넣어준다.
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		
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
	
	
	 //내가 쓴 글 동행
	@RequestMapping(value = "mypage/mypage_renewal", method = {RequestMethod.GET, RequestMethod.POST})
	public List<BoardDTO> showMycompany(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.findlistCompanyCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BoardDTO> myboardsList = mypageService.listfindcompany(pagingDTO);
		model.addAttribute("myboardsList", myboardsList);
		
		System.out.println(totalRowCount);
		
		return myboardsList;
	}
	
	
	 //내가 쓴 글 정보
	@RequestMapping(value = "mypage/mypage_renewal_Info", method = {RequestMethod.GET, RequestMethod.POST})
	public List<MyBoardDTO> showMyInfo(Model model, @ModelAttribute("pagingDTO") PagingDTO pagingDTO) throws Exception {
		
		int totalRowCount = mypageService.findlistInfoCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		
		List<MyBoardDTO> myboardsList = mypageService.listfindinformation(pagingDTO);
		model.addAttribute("myboardsList", myboardsList);
		
		System.out.println(totalRowCount);
		
		return myboardsList;
	}
	
	
	// 내가 찜한 제주 여행지
	@RequestMapping(value = "mypage/mypage_steamed_jeju", method = RequestMethod.GET)
	public List<ApiDTO> showMySteamed(Model model,@ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.getTotalSteamedCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<ApiDTO> boardsTour = mypageService.mySteamedJeju(pagingDTO);
		model.addAttribute("boardsTour", boardsTour);

		System.out.println(totalRowCount);
		
		return boardsTour;
	}
	
	// 내가 찜한 제주 축제
	@RequestMapping(value = "mypage/mypage_steamed_jeju_festival", method = RequestMethod.GET)
	public List<ApiDTO> showMySteamedFestival(Model model,@ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.getTotalSteamedFestivalCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<ApiDTO> boardsTour = mypageService.mySteamedJejuFestival(pagingDTO);
		model.addAttribute("boardsTour", boardsTour);

		System.out.println(totalRowCount);
		
		return boardsTour;
	}
	
	// 내가 찜한 제주 전시회
	@RequestMapping(value = "mypage/mypage_steamed_jeju_Exhibition", method = RequestMethod.GET)
	public List<ApiDTO> showMySteamedExhibition(Model model,@ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.getTotalSteamedExhibitionCount(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();		
		List<ApiDTO> boardsTour = mypageService.mySteamedJejuExhibition(pagingDTO);
		model.addAttribute("boardsTour", boardsTour);

		System.out.println(totalRowCount);
		
		return boardsTour;
	}
	
	// 내가 찜한 부산 여행
	@RequestMapping(value = "mypage/mypage_steamed_BusanTravel", method = RequestMethod.GET)
	public List<BusanApiDTO> showMySteamedBusanTravel(Model model, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.getTotalSteamedCountBusanTravel(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BusanApiDTO> boardsTour = mypageService.mySteamedBusanTravel(pagingDTO);
		model.addAttribute("boardsTour", boardsTour);
		
		System.out.println(totalRowCount);
		
		return boardsTour;
	}
	
	// 내가 찜한 부산 체험
	@RequestMapping(value = "mypage/mypage_steamed_BusanExperience", method = RequestMethod.GET)
	public List<BusanApiDTO> showMySteamedBusanExperience(Model model, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.getTotalSteamedCountBusanExperience(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BusanApiDTO> boardsTour = mypageService.mySteamedBusanExperience(pagingDTO);
		model.addAttribute("boardsTour", boardsTour);
		
		System.out.println(totalRowCount);
		
		return boardsTour;
	}
	
	// 내가 찜한 부산 축제
	@RequestMapping(value = "mypage/mypage_steamed_BusanFestival", method = RequestMethod.GET)
	public List<BusanApiDTO> showMySteamedBusanFestival(Model model, @ModelAttribute("pagingDTO")PagingDTO pagingDTO) throws Exception {
		int totalRowCount = mypageService.getTotalSteamedCountBusanFestival(pagingDTO);
		pagingDTO.setTotalRowCount(totalRowCount);
		pagingDTO.pageSetting();
		List<BusanApiDTO> boardsTour = mypageService.mySteamedBusanFestival(pagingDTO);
		model.addAttribute("boardsTour", boardsTour);
		
		System.out.println(totalRowCount);
		
		return boardsTour;
	}
	
}
