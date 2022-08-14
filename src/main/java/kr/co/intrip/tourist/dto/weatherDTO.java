package kr.co.intrip.tourist.dto;

import lombok.Data;

@Data
public class weatherDTO {

	private String baseDate;
	private String category;
	private String fcstTime;
	private String fcstValue;
}
