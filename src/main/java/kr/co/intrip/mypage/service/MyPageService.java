package kr.co.intrip.mypage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.intrip.mypage.dto.MyPageDTO;

public interface MyPageService {

//	public List<MyPageDTO> listMember() throws Exception;
	public MyPageDTO MyPage(MyPageDTO mypageDTO, HttpSession session) throws Exception;
}
