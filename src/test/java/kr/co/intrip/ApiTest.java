package kr.co.intrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.tourist.dto.weatherDTO;

public class ApiTest {
	
	enum WeatherValue {
		POP, PTY, PCP, REH, SNO, SKY, TMP, TMN, TMX, UUU, VVV, WAV, VEC, WSD
	}
	
	public static void main(String []args) throws Exception { 
		weatherDTO wDTO = new weatherDTO();
		Date date = new Date();        
		String thistime = DateFormatUtils.format(date, "yyyyMMdd");
		System.out.println(thistime);
		String baseTime = "1400";
		SimpleDateFormat sysdate = new SimpleDateFormat("HHmmss");
		Date thisTime2 = new Date();
		String thisTime = sysdate.format(thisTime2);
		int thisTime1 = Integer.parseInt(thisTime);
		Date yDate = new Date();
		yDate = new Date(yDate.getTime()+(1000*60*60*24*-1));
		String yestertime = DateFormatUtils.format(yDate, "yyyyMMdd");
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6FKxrGLyiJy9V0QFz5JpDYNHcC7zcCTo5K%2F%2FqA3n9WRmWUHmqVDq%2B2B6JKG8iJ%2BConwMG8s0bZKflAN2kqhtCA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("13", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        if((thisTime1 - 50000) < 0) {
		        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(yestertime, "UTF-8")); /*‘21년 6월 28일발표*/
	        }
	        else {
		        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(thistime, "UTF-8")); /*‘21년 6월 28일발표*/
	        }
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*05시 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("53", "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("37", "UTF-8")); /*예보지점의 Y 좌표값*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        //System.out.println(sb.toString());
	        
	        String jsonString = sb.toString();
			// 가장 큰 JSONObject를 가져옵니다.
			JSONObject jObject = new JSONObject(jsonString);

			// (response) 0번째 JSONObject를 가져옵니다.
			JSONObject responseObject = jObject.getJSONObject("response");
			// (response -> header) 1번째 JSONObject를 가져와서 key-value를 읽습니다.
			/* log.info("(header)resultCode={}" , responseObject); */
			JSONObject body = (JSONObject) responseObject.get("body");
			JSONObject items =(JSONObject) body.get("items");
			JSONArray itemArray = (JSONArray) items.get("item");
			
				for (int i = 0; i < itemArray.length(); i++) {									
					JSONObject iobj = itemArray.getJSONObject(i);					
					String category = (String) iobj.get("category");	
					
					String value = (String) iobj.get("fcstValue");
					
					WeatherValue weatherValue = WeatherValue.valueOf(category);
				
					switch(weatherValue) {
						case POP:
							wDTO.setPOP(value);
							break;
						
						case SKY:
							wDTO.setSKY(value);
							break;
						case TMP:
							wDTO.setTMP(value);
							break;
						case PTY:
							wDTO.setTMP(value);
							break;
						default:
							break;
					}
					
				}
				if((thisTime1 - 50000) < 0) {
					wDTO.setBaseDate(yestertime);
				}
				else {
					wDTO.setBaseDate(thistime);
				}
		       			
				wDTO.setBaseTime(baseTime);
				System.out.println(wDTO);
				

    }
}
