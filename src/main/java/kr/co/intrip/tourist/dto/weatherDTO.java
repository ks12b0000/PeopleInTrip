package kr.co.intrip.tourist.dto;

import lombok.Data;

@Data
public class weatherDTO {

	private String baseDate;
	private String baseTime;
	private String POP;	//강수확률
	private String SKY;	//하늘상태
	private String TMP;	//기온
	private String PTY;	//강수형태
}
