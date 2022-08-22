package kr.co.intrip.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.main.dto.MainDTO;
import kr.co.intrip.main.service.MainService;
import kr.co.intrip.tourist.dao.TouristDAO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.service.TouristService;

@Controller
public class MainControllerImpl implements MainController {
	
	@Autowired 
	MainService mainService;
	
	@Autowired 
	MainDTO mainDTO;

	@Autowired
	private TouristService tourservice;
	
	// 메인 페이지
	@Override
	@RequestMapping(value = "/mainpage/main" )
	public ModelAndView listMain(HttpServletRequest request,Model model,ApiDTO apiDTO, BusanApiDTO busanApiDTO, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		List<MainDTO> mainsList = mainService.listMain();
		List<MainDTO> mainsList1 = mainService.listMain1();
		List<ApiDTO> mainlist = tourservice.jejutourist_main(apiDTO);
		List<BusanApiDTO> mainlist5 = tourservice.busantourist_main(busanApiDTO);
		List<ApiDTO> festivalmain = tourservice.jejufestivalmain(apiDTO);
		List<BusanApiDTO> festivalmain2 = tourservice.busanfestivalmain(busanApiDTO);
		List<ApiDTO> exhibitionmain = tourservice.jejuexhibitionmain(apiDTO);
		List<BusanApiDTO> experiencemain = tourservice.busanexperiencemain(busanApiDTO);
		model.addAttribute("mainlist", mainlist);
		model.addAttribute("mainlist5", mainlist5);
		model.addAttribute("festivalmain", festivalmain);
		model.addAttribute("festivalmain2",festivalmain2);
		model.addAttribute("exhibitionmain",exhibitionmain);
		model.addAttribute("experiencemain",experiencemain);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("mainsList", mainsList);
		mav.addObject("mainsList1", mainsList1);
		return mav;		
	}
}
