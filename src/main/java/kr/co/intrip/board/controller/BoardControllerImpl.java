package kr.co.intrip.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


import kr.co.intrip.board.dto.BoardDTO;
import kr.co.intrip.board.dto.ImageDTO;
import kr.co.intrip.board.service.BoardService;
import kr.co.intrip.login_signup.dto.MemberDTO;

@Controller
public class BoardControllerImpl implements BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardDTO boardDTO;
	private static String ARTICLE_IMAGE_REPO = "null";
	
	//상세보기
	@Override
	@RequestMapping(value = "/board/community_detail.do", method = RequestMethod.GET)
	public ModelAndView viewdetail(@RequestParam(value = "post_num") int post_num, 		// 조회할 글 번호를 가져옴
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		
		Map<String, Object> boardMap = boardService.viewdetail(post_num);		//조회할 글 정보,이미지파일 정보를 articleMap에 설정
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		
		return mav;
	}
	
	

	//글쓰기
	@GetMapping("/board/community_writeInfo.do")
	public String addnewboard() {
		return "board/community_writeInfo";
	}
	
	
	
	//글쓰기
	@Override
	@RequestMapping(value = "/board/community_writeInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, 
			HttpServletResponse response) throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName = null;
		
		//글정보 저장하기 위한 Map 생성
		Map boardMap = new HashMap();
		Enumeration enun = multipartRequest.getParameterNames();
		//새글쓰기창에서 전송된 글 정보를 Map에 key/value로 저장함
		while(enun.hasMoreElements()) {
			String name = (String) enun.nextElement();
			String value = multipartRequest.getParameter(name);
			boardMap.put(name, value);
		}
		
		//로그인 시 세션에 저장된 회원정보에서 아이디(글쓴이)를 Map에 저장
		HttpSession session = multipartRequest.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("user");
		String id = memberDTO.getId();
		boardMap.put("id", id);
		
		//업로드한 이미지 파일 이름을 가져옴
		List<String> fileList = upload(multipartRequest);
		
		List<ImageDTO> imageFileList = new ArrayList<>();
		if (fileList != null && fileList.size() != 0) {
			// 전송되는 이미지 정보를 ImageDTO 객체의 속성에 차례대로 저장한 후 imageFileList에 다시 저장함
			for (String fileName : fileList) {				
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImageFileName(fileName);
				imageFileList.add(imageDTO);
			}
			// imageFileList를 다시 articleMap에 저장함
			boardMap.put("imageFileList", imageFileList);
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String message;
		ResponseEntity resEnt = null;
		
		try {
			
			int post_num = boardService.insertBoard(boardMap);		
			
			if (imageFileList != null && imageFileList.size() != 0) {
				//첨부한 이미지들을 for문을 이용해 업로드함
				for (ImageDTO imageDTO : imageFileList) {
					
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+ "temp" +"\\"+ imageFileName);
					File destFile = new File(ARTICLE_IMAGE_REPO +"\\"+ post_num);
					FileUtils.moveFileToDirectory(srcFile, destFile, true);
				}
			}
			
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/community-acco';";
			message += "</script>";
			
			// 새 글을 추가한 후 메시지를 전달함
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				//오류 발생시 temp폴더의 이미지들 모두 삭제
				for (ImageDTO imageDTO : imageFileList) {
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+ "temp" +"\\"+ imageFileName);
					srcFile.delete();
				}
			}
			
			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/community-acco';";
			message += "</script>";			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
			e.printStackTrace();
		}		
			
		return resEnt;
	}
	
	
	
	
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws ServletException, IOException {
		List<String> fileList = new ArrayList<>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFilename = mFile.getOriginalFilename();
			
			if (originalFilename != "" && originalFilename != null) {
				fileList.add(originalFilename);		//첨부한 이미지 파일의 이름들을 차례대로 저장함
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
				if (mFile.getSize() != 0) {
					if (!file.exists()) {
						file.getParentFile().mkdirs();		//경로에 해당하는 디렉토리들 생성
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+ "temp" +"\\"+ originalFilename)); //임시로
								//저장된 MultipartFile을 실제 파일로 전송
					}
				}
			}
		}
				
		return fileList;
	}



	
	
	@RequestMapping(value = "board/community_writeWith")
	public String writeWith() {
		return "board/community_writeWith";
	}
	
	@RequestMapping(value = "board/community_info")
	public String info() {
		return "board/community-info";
	}
	
	
	/*
	 * @RequestMapping(value = "board/community-acco") public String acco() { return
	 * "board/community-acco"; }
	 */
	


	@RequestMapping(value = "/board/community-info", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView infolistArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<BoardDTO> boardsList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/community-acco", method = {RequestMethod.GET, RequestMethod.POST})

	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String viewName = (String) request.getAttribute("viewName");
		List<BoardDTO> boardsList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		
		return mav;
	}

	

	

	


	
}
