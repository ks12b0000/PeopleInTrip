package kr.co.intrip.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardController {

	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listArticles1(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView viewdetail(int post_num, HttpServletRequest request, HttpServletResponse response) throws Exception;


	

	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	public ResponseEntity addNewArticle1(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	
	
	

	
	
}
