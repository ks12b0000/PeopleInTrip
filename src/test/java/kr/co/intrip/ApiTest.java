package kr.co.intrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.tourist.dto.weatherDTO;

public class ApiTest {
	@Test
	public void apitest() throws Exception {
		Date date = new Date();        
		String dateToStr = DateFormatUtils.format(date, "yyyyMMdd");
		String dateToStr1 = DateFormatUtils.format(date, "HHmm");
		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6FKxrGLyiJy9V0QFz5JpDYNHcC7zcCTo5K%2F%2FqA3n9WRmWUHmqVDq%2B2B6JKG8iJ%2BConwMG8s0bZKflAN2kqhtCA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("733", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(dateToStr, "UTF-8")); /*‘21년 6월 28일발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0800", "UTF-8")); /*05시 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
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
	        System.out.println(sb.toString());
	        
	        String jsonString = sb.toString();
			// 가장 큰 JSONObject를 가져옵니다.
			JSONObject jObject = new JSONObject(jsonString);

			// (response) 0번째 JSONObject를 가져옵니다.
			JSONObject responseObject = jObject.getJSONObject("response");
			// (response -> header) 1번째 JSONObject를 가져와서 key-value를 읽습니다.
			/* log.info("(header)resultCode={}" , responseObject); */
			JSONObject body = responseObject.getJSONObject("body");
			JSONObject items = body.getJSONObject("items");
			JSONArray itemArray = items.getJSONArray("item");
			
				for (int i = 0; i < itemArray.length(); i++) {					
					weatherDTO pvo = new weatherDTO();
					JSONObject iobj = itemArray.getJSONObject(i);					
						
						pvo.setBaseDate(iobj.getString("baseDate"));			
						pvo.setCategory(iobj.getString("category"));
						pvo.setFcstTime(iobj.getString("fcstTime"));
						pvo.setFcstValue(iobj.getString("fcstValue"));

					System.out.println("list는?" + pvo);
				}

    }
}
