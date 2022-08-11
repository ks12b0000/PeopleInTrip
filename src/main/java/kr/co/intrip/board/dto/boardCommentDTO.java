package kr.co.intrip.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class boardCommentDTO {
	private int com_num;
	private String id;
	private int post_num;
	private String com_content;
	private Date com_date;
}
