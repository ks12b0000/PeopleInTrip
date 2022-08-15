package kr.co.intrip.tourist.dto;

import lombok.Data;

@Data
public class BusanApiDTO {

	private double LAT;
	private double LNG;
	private int UC_SEQ;
	private String PLACE;
	private String ADDR1;
	private String CNTCT_TEL;
	private String ITEMCNTNTS;
	private String MAIN_IMG_NORMAL;
	private String SUBTITLE;
}
