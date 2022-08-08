package kr.co.intrip.tourist.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class JejuCommentDTO {
	private int com_num;
	private String id;
	private String contentsid;
	private String com_content;
	private Date com_date;
}
