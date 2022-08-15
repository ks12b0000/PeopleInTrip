package kr.co.intrip.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.board.dto.CommentPagingDTO;

public interface BoardController {

	

	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;
	public ResponseEntity addNewArticle1(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;
	public ResponseEntity modBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;
	public ModelAndView update(int post_num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void removeMod(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeBoard(int post_num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeBoard1(int post_num, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	public ModelAndView viewdetail1(int post_num, HttpServletRequest request, HttpServletResponse response,Model model, CommentPagingDTO commentpagingDTO) throws Exception;
	public ResponseEntity modBoard1(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	public ModelAndView update1(int post_num, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView viewdetail(int post_num, HttpServletRequest request, HttpServletResponse response,Model model, CommentPagingDTO commentpagingDTO)
			throws Exception;
	

	
	
	

	
	
}
