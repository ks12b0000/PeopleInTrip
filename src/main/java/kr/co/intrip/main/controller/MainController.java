package kr.co.intrip.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.PagingDTO;

public interface MainController {

	public ModelAndView listMain(HttpServletRequest request,Model model, ApiDTO apiDTO, BusanApiDTO busanApiDTO, HttpServletResponse response) throws Exception;

}
