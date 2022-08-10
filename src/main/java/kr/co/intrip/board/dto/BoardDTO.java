package kr.co.intrip.board.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("boardDTO")
public class BoardDTO {
	
	private int post_num;
	private String id;	
	private String post_title;
	private String post_content;
	private Date post_date;
	private int visitcount;
	private int count_comment;
	private int likehit;
	private int sinhit;

	


	public int getSinhit() {
		return sinhit;
	}

	public void setSinhit(int sinhit) {
		this.sinhit = sinhit;
	}

	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoardDTO(int post_num, String id, String post_title, String post_content, int board_num,int visitcount) {
		this.post_num = post_num;
		this.id = id;
		this.post_title = post_title;
		this.post_content= post_content;
		this.visitcount = visitcount;
		this.likehit = likehit;
	}

	public int getLikehit() {
		return likehit;
	}

	public void setLikehit(int likehit) {
		this.likehit = likehit;
	}

	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	

	public int getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}

	public int getCount_comment() {
		return count_comment;
	}

	public void setCount_comment(int count_comment) {
		this.count_comment = count_comment;
	}

	



}
