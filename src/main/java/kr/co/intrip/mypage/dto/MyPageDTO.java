package kr.co.intrip.mypage.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("mypageDTO")
public class MyPageDTO {

	private String id;
	private String name;
	private String pwd;
	private String nick_nm;
	private String email;
	private Date join_Date;	
	private Date last_update;
	private String grade;
	
	public MyPageDTO() {
		// TODO Auto-generated constructor stub
	}

	public MyPageDTO(String id, String name, String pwd, String nick_nm, String email, Date join_Date, Date last_update,
			String grade) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.nick_nm = nick_nm;
		this.email = email;
		this.join_Date = join_Date;
		this.last_update = last_update;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNick_nm() {
		return nick_nm;
	}

	public void setNick_nm(String nick_nm) {
		this.nick_nm = nick_nm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoin_Date() {
		return join_Date;
	}

	public void setJoin_Date(Date join_Date) {
		this.join_Date = join_Date;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
