package kr.co.intrip.tourist.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BusanCommentDTO {

	private int com_num;
	private String id;
	private int UC_SEQ;
	private String com_content;
	private Date com_date;
}
