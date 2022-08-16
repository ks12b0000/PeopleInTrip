package kr.co.intrip.tourist.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.intrip.tourist.controller.TouristController;
import kr.co.intrip.tourist.dao.TouristDAO;
import kr.co.intrip.tourist.dto.ApiDTO;
import kr.co.intrip.tourist.dto.BusanApiDTO;
import kr.co.intrip.tourist.dto.BusanCommentDTO;
import kr.co.intrip.tourist.dto.CommentPagingDTO;
import kr.co.intrip.tourist.dto.JejuCommentDTO;
import kr.co.intrip.tourist.dto.PagingDTO;
import kr.co.intrip.tourist.dto.weatherDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TouristServiceImpl implements TouristService {

	enum WeatherValue {
		POP, PTY, PCP, REH, SNO, SKY, TMP, TMN, TMX, UUU, VVV, WAV, VEC, WSD
	}
	@Autowired
	private TouristDAO touristDAO;
	
	// 관광지 api db에 저장용
	@Override
	public void parkApi(String schAirportCode) throws Exception {
		ArrayList<ApiDTO> list = new ArrayList<ApiDTO>();
		// url
		StringBuilder urlBuilder = new StringBuilder("http://api.visitjeju.net/vsjApi/contents/searchList?apiKey=lvg5ciolx7x4i2je&locale=kr&page=46");
		
		URL url = new URL(urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		/* System.out.println("Response code: " + conn.getResponseCode()); */
		BufferedReader rd;
		
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} 
		else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		/* System.out.println(sb.toString()); */
		// ========================= (list code) =========================
		String jsonString = sb.toString();
		// 가장 큰 JSONObject를 가져옵니다.
		JSONObject jObject = new JSONObject(jsonString);

		// (response) 0번째 JSONObject를 가져옵니다.
		String responseObject = jObject.getString("result");
		// (response -> header) 1번째 JSONObject를 가져와서 key-value를 읽습니다.
		/* log.info("(header)resultCode={}" , responseObject); */
		
		JSONArray itemArray = jObject.getJSONArray("items");
		
			for (int i = 0; i < itemArray.length(); i++) {					
				ApiDTO pvo = new ApiDTO();
				JSONObject iobj = itemArray.getJSONObject(i);					
				JSONObject iobj2 = new JSONObject(iobj);
					
				if(!(itemArray.getJSONObject(i).isNull("alltag"))) {
					pvo.setAlltag(iobj.getString("alltag"));
				}	
				
				if(!(itemArray.getJSONObject(i).isNull("contentsid"))) {
					pvo.setContentsid(iobj.getString("contentsid"));
				}
									
				JSONObject itemArray2 = iobj.getJSONObject("contentscd");
				pvo.setLabel(itemArray2.getString("label"));				
				pvo.setTitle(iobj.getString("title"));
					
				if(!(itemArray.getJSONObject(i).isNull("region1cd"))) {
					JSONObject itemArray3 = iobj.getJSONObject("region1cd");
					pvo.setLabel2(itemArray3.getString("label"));
				}

				if(!(itemArray.getJSONObject(i).isNull("address"))) {
					pvo.setAddress(iobj.getString("address"));
				}	
					
				if(!(itemArray.getJSONObject(i).isNull("tag"))) {
					pvo.setTag(iobj.getString("tag"));
				}	
				
				if(!(itemArray.getJSONObject(i).isNull("introduction"))) {
					pvo.setIntroduction(iobj.getString("introduction"));
				}
				
				if(!(itemArray.getJSONObject(i).isNull("latitude"))) {
					pvo.setLatitude(iobj.getDouble("latitude"));
				}
				
				if(!(itemArray.getJSONObject(i).isNull("longitude"))) {
					pvo.setLongitude(iobj.getDouble("longitude"));
				}
				
				if(!(itemArray.getJSONObject(i).isNull("phoneno"))) {
					pvo.setPhoneno(iobj.getString("phoneno"));
				}
					
				if(!(itemArray.getJSONObject(i).isNull("repPhoto"))) {
					JSONObject itemArray4 = iobj.getJSONObject("repPhoto");
					JSONObject itemArray5 = itemArray4.getJSONObject("photoid");
					pvo.setImgpath(itemArray5.getString("imgpath"));
				}

				/* log.info(i + "번째 item: " + pvo); */
				list.add(pvo);
			}
			/* System.out.println("list: " + list); */
			
			
			touristDAO.touristadd(list);
	}
	
	// 부산 여행지 api db에 저장용
	@Override
	public void busanApi() throws Exception {
		ArrayList<BusanApiDTO> list = new ArrayList<BusanApiDTO>();
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/AttractionService/getAttractionKr"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6FKxrGLyiJy9V0QFz5JpDYNHcC7zcCTo5K%2F%2FqA3n9WRmWUHmqVDq%2B2B6JKG8iJ%2BConwMG8s0bZKflAN2kqhtCA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
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
		JSONObject responseObject = jObject.getJSONObject("getAttractionKr");
		JSONArray itemArray = (JSONArray) responseObject.get("item");

		for (int i = 0; i < itemArray.length(); i++) {					
			BusanApiDTO pvo1 = new BusanApiDTO();
			JSONObject iobj = itemArray.getJSONObject(i);					
				
			if(!(itemArray.getJSONObject(i).isNull("LAT"))) {
				pvo1.setLAT(iobj.getDouble("LAT"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("LNG"))) {
				pvo1.setLNG(iobj.getDouble("LNG"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("UC_SEQ"))) {
				pvo1.setUC_SEQ(iobj.getInt("UC_SEQ"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("PLACE"))) {
				pvo1.setPLACE(iobj.getString("PLACE"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("ADDR1"))) {
				pvo1.setADDR1(iobj.getString("ADDR1"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("CNTCT_TEL"))) {
				pvo1.setCNTCT_TEL(iobj.getString("CNTCT_TEL"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("ITEMCNTNTS"))) {
				pvo1.setITEMCNTNTS(iobj.getString("ITEMCNTNTS"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("MAIN_IMG_NORMAL"))) {
				pvo1.setMAIN_IMG_NORMAL(iobj.getString("MAIN_IMG_NORMAL"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("SUBTITLE"))) {
				pvo1.setSUBTITLE(iobj.getString("SUBTITLE"));
			}
			
			/* log.info(i + "번째 item: " + pvo1); */
			list.add(pvo1);
		}
		/* System.out.println("list: " + list); */				
		
		touristDAO.busantouristadd(list);
	}
	
	// 부산 축제 api db에 저장용
	@Override
	public void busanApi2() throws Exception {
		ArrayList<BusanApiDTO> list = new ArrayList<BusanApiDTO>();
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/FestivalService/getFestivalKr"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6FKxrGLyiJy9V0QFz5JpDYNHcC7zcCTo5K%2F%2FqA3n9WRmWUHmqVDq%2B2B6JKG8iJ%2BConwMG8s0bZKflAN2kqhtCA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
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
		JSONObject responseObject = jObject.getJSONObject("getFestivalKr");
		JSONArray itemArray = (JSONArray) responseObject.get("item");

		for (int i = 0; i < itemArray.length(); i++) {					
			BusanApiDTO pvo1 = new BusanApiDTO();
			JSONObject iobj = itemArray.getJSONObject(i);					
				
			if(!(itemArray.getJSONObject(i).isNull("LAT"))) {
				pvo1.setLAT(iobj.getDouble("LAT"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("LNG"))) {
				pvo1.setLNG(iobj.getDouble("LNG"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("UC_SEQ"))) {
				pvo1.setUC_SEQ(iobj.getInt("UC_SEQ"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("PLACE"))) {
				pvo1.setPLACE(iobj.getString("PLACE"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("ADDR1"))) {
				pvo1.setADDR1(iobj.getString("ADDR1"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("CNTCT_TEL"))) {
				pvo1.setCNTCT_TEL(iobj.getString("CNTCT_TEL"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("ITEMCNTNTS"))) {
				pvo1.setITEMCNTNTS(iobj.getString("ITEMCNTNTS"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("MAIN_IMG_NORMAL"))) {
				pvo1.setMAIN_IMG_NORMAL(iobj.getString("MAIN_IMG_NORMAL"));
			}
			
			if(!(itemArray.getJSONObject(i).isNull("SUBTITLE"))) {
				pvo1.setSUBTITLE(iobj.getString("SUBTITLE"));
			}
			
			/* log.info(i + "번째 item: " + pvo1); */
			list.add(pvo1);
		}
		/* System.out.println("list: " + list); */				
		
		touristDAO.busantouristadd2(list);
	}
	
	// 제주도 날씨 정보 api
	@Override
	public weatherDTO weatherapi(String weather) throws Exception {
		weatherDTO wDTO = new weatherDTO();
		Date date = new Date();        
		String thistime = DateFormatUtils.format(date, "yyyyMMdd"); // 현재 날짜
		String baseTime = "0800";
		String baseTime2 = "1400";
		String baseTime3 = "2000";
		SimpleDateFormat sysdate = new SimpleDateFormat("HHmmss");	
		Date thisTime2 = new Date();
		String thisTime = sysdate.format(thisTime2); // 현재 시간
		int thisTime1 = Integer.parseInt(thisTime); // 현재 시간을 int로 변환 
		Date yDate = new Date();
		yDate = new Date(yDate.getTime()+(1000*60*60*24*-1)); // 어제 시간 구하기
		String yestertime = DateFormatUtils.format(yDate, "yyyyMMdd"); // 어제 날짜
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6FKxrGLyiJy9V0QFz5JpDYNHcC7zcCTo5K%2F%2FqA3n9WRmWUHmqVDq%2B2B6JKG8iJ%2BConwMG8s0bZKflAN2kqhtCA%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("13", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		
		if((thisTime1 - 50000) < 0) { // 현재 시간이 오전 5시 전일때 어제 날짜 나타내줌
			urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(yestertime, "UTF-8")); /*‘21년 6월 28일발표*/
		}
		 else { // 5시 이후면 오늘 날짜 나타냄
		        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(thistime, "UTF-8")); /*‘21년 6월 28일발표*/
	   	}
		if((thisTime1 - 140000) < 0) { // 현재 시간이 14시 전일때 08시 기준 날씨 가져옴
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*05시 발표*/
		}
		else if((thisTime1 - 200000) < 0) { // 현재 시간이 20시 전일때 14시 기준 날씨 가져옴
			urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime2, "UTF-8")); /*05시 발표*/
		}
		else { // 현재 시간이 14시 20시 전이 아니면 20시 기준 날씨 가져옴
			urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime3, "UTF-8")); /*05시 발표*/
		}
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("53", "UTF-8")); /*예보지점의 X 좌표값*/
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("37", "UTF-8")); /*예보지점의 Y 좌표값*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		//System.out.println("Response code: " + conn.getResponseCode());
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
		
		if((thisTime1 - 50000) < 0) {	// 현재 시간이 오전 5시 전이면 basedate 어제 날짜로 저장
			wDTO.setBaseDate(yestertime);
		}
		else { // 5시 이후면 오늘 날짜 저장
			wDTO.setBaseDate(thistime);
		}	
		
		if((thisTime1 - 140000) < 0) { // 현재 시간이 14시 전이면 8시 날씨 가져옴
			wDTO.setBaseTime(baseTime);
		}
		else if((thisTime1 - 200000) < 0) { // 현재 시간이 20시 전이면 14시 날씨 가져옴
			wDTO.setBaseTime(baseTime2);
		}
		else { // 현재 시간이 14시, 20시 전이 아니면 20시 날씨 가져옴
			wDTO.setBaseTime(baseTime3);
		}
		//System.out.println(wDTO);
		return wDTO;	
	}
	
	// 부산 날씨 정보 api
	@Override
	public weatherDTO weatherapi2(String weather) throws Exception {
		weatherDTO wDTO = new weatherDTO();
		Date date = new Date();        
		String thistime = DateFormatUtils.format(date, "yyyyMMdd"); // 현재 날짜
		String baseTime = "0800";
		String baseTime2 = "1400";
		String baseTime3 = "2000";
		SimpleDateFormat sysdate = new SimpleDateFormat("HHmmss");	
		Date thisTime2 = new Date();
		String thisTime = sysdate.format(thisTime2); // 현재 시간
		int thisTime1 = Integer.parseInt(thisTime); // 현재 시간을 int로 변환 
		Date yDate = new Date();
		yDate = new Date(yDate.getTime()+(1000*60*60*24*-1)); // 어제 시간 구하기
		String yestertime = DateFormatUtils.format(yDate, "yyyyMMdd"); // 어제 날짜
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6FKxrGLyiJy9V0QFz5JpDYNHcC7zcCTo5K%2F%2FqA3n9WRmWUHmqVDq%2B2B6JKG8iJ%2BConwMG8s0bZKflAN2kqhtCA%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("13", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		
		if((thisTime1 - 50000) < 0) { // 현재 시간이 오전 5시 전일때 어제 날짜 나타내줌
			urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(yestertime, "UTF-8")); /*‘21년 6월 28일발표*/
		}
		 else { // 5시 이후면 오늘 날짜 나타냄
		        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(thistime, "UTF-8")); /*‘21년 6월 28일발표*/
	   	}
		if((thisTime1 - 140000) < 0) { // 현재 시간이 14시 전일때 08시 기준 날씨 가져옴
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*05시 발표*/
		}
		else if((thisTime1 - 200000) < 0) { // 현재 시간이 20시 전일때 14시 기준 날씨 가져옴
			urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime2, "UTF-8")); /*05시 발표*/
		}
		else { // 현재 시간이 14시 20시 전이 아니면 20시 기준 날씨 가져옴
			urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime3, "UTF-8")); /*05시 발표*/
		}
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("97", "UTF-8")); /*예보지점의 X 좌표값*/
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("74", "UTF-8")); /*예보지점의 Y 좌표값*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		//System.out.println("Response code: " + conn.getResponseCode());
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
		
		if((thisTime1 - 50000) < 0) {	// 현재 시간이 오전 5시 전이면 basedate 어제 날짜로 저장
			wDTO.setBaseDate(yestertime);
		}
		else { // 5시 이후면 오늘 날짜 저장
			wDTO.setBaseDate(thistime);
		}	
		
		if((thisTime1 - 140000) < 0) { // 현재 시간이 14시 전이면 8시 날씨 가져옴
			wDTO.setBaseTime(baseTime);
		}
		else if((thisTime1 - 200000) < 0) { // 현재 시간이 20시 전이면 14시 날씨 가져옴
			wDTO.setBaseTime(baseTime2);
		}
		else { // 현재 시간이 14시, 20시 전이 아니면 20시 날씨 가져옴
			wDTO.setBaseTime(baseTime3);
		}
		//System.out.println(wDTO);
		return wDTO;	
	}

	// 제주도 여행지 총 개수
	public int getTotalRowCount(PagingDTO pagingDTO) throws Exception {
		return touristDAO.getTotalRowCount(pagingDTO);
	}
	
	// 제주도 여행지 페이지 리스트
	@Override
	public List<ApiDTO> jejutourist_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.jejutourist(pagingDTO);		
	}
	
	// 부산 여행지 총 개수
	public int busangetTotalRowCount(PagingDTO pagingDTO) throws Exception {
		return touristDAO.busangetTotalRowCount(pagingDTO);
	}
	
	// 부산 여행지 페이지 리스트
	@Override
	public List<BusanApiDTO> busantourist_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.busantourist(pagingDTO);		
	}
	
	// 부산 축제 총 개수
	public int busangetTotalRowCount2(PagingDTO pagingDTO) throws Exception {
		return touristDAO.busangetTotalRowCount2(pagingDTO);
	}
	
	// 부산 축제 페이지 리스트
	@Override
	public List<BusanApiDTO> busantourist_list2(PagingDTO pagingDTO) throws Exception {
		return touristDAO.busanfestival(pagingDTO);		
	}
	
	// 제주도 축제 총 개수
	public int getTotalRowCount2(PagingDTO pagingDTO) throws Exception {
		return touristDAO.getTotalRowCount2(pagingDTO);
	}

	// 제주도 축제 페이지 리스트
	@Override
	public List<ApiDTO> jejufestival_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.jejufestival(pagingDTO);	
	}
	
	// 제주도 여행지 총 개수
	public int getTotalRowCount3(PagingDTO pagingDTO) throws Exception {
		return touristDAO.getTotalRowCount3(pagingDTO);
	}

	// 제주도 전시관 페이지 리스트
	@Override
	public List<ApiDTO> jejuexhibition_list(PagingDTO pagingDTO) throws Exception {
		return touristDAO.jejuexhibition(pagingDTO);
	}

	// 제주도 통합 상세페이지
	@Override
	public ApiDTO jejutourist_detail(ApiDTO apiDTO) throws Exception {
    
		return touristDAO.jejudetail(apiDTO);
	}

	// 제주도 통합 상세페이지 조회수 증가
	@Override
	public int jejutourist_viewcount(ApiDTO apiDTO) throws Exception {
		return touristDAO.viewcount(apiDTO);
	}

	// 제주도 여행지 페이지 리스트 Sorting 기능
	@Override
	public List<ApiDTO> jejutourist_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.jejutourist(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.jejutourist_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.jejutourist_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.jejutourist_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.jejutourist_SuggestionSort(pagingDTO);		
		}
	}
	
	// 부산 여행지 상세페이지
	@Override
	public BusanApiDTO busantourist_detail(BusanApiDTO busanApiDTO) throws Exception {   
		return touristDAO.busandetail(busanApiDTO);
	}

	// 부산 여행지 상세페이지 조회수 증가
	@Override
	public int busantourist_viewcount(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busanviewcount(busanApiDTO);
	}
	
	// 부산 축제 상세페이지
	@Override
	public BusanApiDTO busantourist_detail2(BusanApiDTO busanApiDTO) throws Exception {   
		return touristDAO.busandetail2(busanApiDTO);
	}

	// 부산 축제 상세페이지 조회수 증가
	@Override
	public int busantourist_viewcount2(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busanviewcount2(busanApiDTO);
	}

	// 부산 여행지 페이지 리스트 Sorting 기능
	@Override
	public List<BusanApiDTO> busantourist_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.busantourist(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.busantourist_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.busantourist_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.busantourist_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.busantourist_SuggestionSort(pagingDTO);		
		}
	}
	
	// 제주도 축제 페이지 리스트 Sorting 기능
	@Override
	public List<ApiDTO> jejufestival_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.jejufestival(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.jejufestival_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.jejufestival_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.jejufestival_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.jejufestival_SuggestionSort(pagingDTO);		
		}
	}
	
	// 부산 축제 페이지 리스트 Sorting 기능
	@Override
	public List<BusanApiDTO> busantourist_Sort2(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.busanfestival(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.busantourist_lookupSort2(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.busantourist_commentSort2(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.busantourist_steamedSort2(pagingDTO);
		}
		else {
			return touristDAO.busantourist_SuggestionSort2(pagingDTO);		
		}
	}
		
	// 제주도 전시관 페이지 리스트 Sorting 기능
	@Override
	public List<ApiDTO> jejuexhibition_Sort(PagingDTO pagingDTO, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");
		model.addAttribute("value", value);
		log.info("value = {}", value);
		
		if (value.equals("basic")) {
			return touristDAO.jejuexhibition(pagingDTO);
		}
		else if (value.equals("lookup")) {
			return touristDAO.jejuexhibition_lookupSort(pagingDTO);
		}
		else if (value.equals("comment")) {
			return touristDAO.jejuexhibition_commentSort(pagingDTO);
		}	
		else if (value.equals("steamed")) {
			return touristDAO.jejuexhibition_steamedSort(pagingDTO);
		}
		else {
			return touristDAO.jejuexhibition_lookupSort(pagingDTO);		
		}
	}
	
	// 제주도 댓글 수 증가
	@Override
	public int jejucommentcount(ApiDTO apiDTO) throws Exception {
		return touristDAO.commentcount(apiDTO);
	}
	
	// 제주도 댓글 수 감소
	@Override
	public int jejucommentcountminus(ApiDTO apiDTO) throws Exception {
		return touristDAO.commentcountminus(apiDTO);
	}
	
	// 제주도 댓글 총 개수
	@Override
	public int getCommentTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.CommentgetTotalRowCount(commentpagingDTO);
	}

	// 제주도 댓글 조회
	@Override
	public List<JejuCommentDTO> jejureadReply (CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.jejureadReply(commentpagingDTO);
	}
	
	// 제주도 댓글 작성
	@Override
	public void jejuregister(JejuCommentDTO jejuDTO) throws Exception {
		touristDAO.jejucreate(jejuDTO);
	}
	
	// 제주도 댓글 수정
	@Override
	public void jejumodify(JejuCommentDTO jejuDTO) throws Exception {  
		touristDAO.jejuupdate(jejuDTO);		
	}
	
	// 제주도 댓글 삭제
	@Override
	public void jejuremove(JejuCommentDTO jejuDTO) throws Exception {  
		touristDAO.jejudeleteReply(jejuDTO);
	}
	
	// 제주도 선택된 댓글 조회
	@Override
	public JejuCommentDTO jejuselectReply(int com_num) throws Exception {  
		return touristDAO.jejuselectReply(com_num);
	}
	
	// 제주도 여행지 찜 중복방지 select문
	@Override
	public String steamedCheck(String contentsid, String id) throws Exception {
		return touristDAO.SteamedCheck(contentsid, id);
	}

	// 제주도 여행지 찜 시 steamed 테이블에 insert
	@Override
	public void insertSteamed(String contentsid, String id) throws Exception {
		touristDAO.insertSteamed(contentsid, id);
	}

	// 제주도 여행지 찜 수
	@Override
	public void updateSteamed(String contentsid) throws Exception {
		touristDAO.updateSteamed(contentsid);
	}

	// 제주도 여행지 찜 시 Check를 1로 만들어서 중복방지
	@Override
	public void updateSteamedCheck(String contentsid, String id) throws Exception {
		touristDAO.updateSteamedCheck(contentsid, id);
	}

	// 제주도 여행지 찜 취소 시 다시 0
	@Override
	public void updateSteamedCheckCancel(String contentsid, String id) throws Exception {
		touristDAO.updateSteamedCheckCancel(contentsid, id);
	}

	// 제주도 여행지 찜 수 취소
	@Override
	public void updateSteamedCancel(String contentsid) throws Exception {
		touristDAO.updateSteamedCancel(contentsid);
	}

	// 제주도 여행지 찜 취소 시 delete
	@Override
	public void deleteSteamed(String contentsid, String id) throws Exception {
		touristDAO.deleteSteamed(contentsid, id);
	}

	// 제주도 여행지 추천 중복방지 select문
	@Override
	public String SuggestionCheck(String contentsid, String id) throws Exception {
		return touristDAO.SuggestionCheck(contentsid, id);
	}

	// 제주도 여행지 추천 시 steamed 테이블에 insert
	@Override
	public void insertSuggestion(String contentsid, String id) throws Exception {
		touristDAO.insertSuggestion(contentsid, id);
	}

	// 제주도 여행지 추천 수
	@Override
	public void updateSuggestion(String contentsid) throws Exception {
		touristDAO.updateSuggestion(contentsid);
	}

	// 제주도 여행지 추천 시 Check를 1로 만들어서 중복방지
	@Override
	public void updateSuggestionCheck(String contentsid, String id) throws Exception {
		touristDAO.updateSuggestionCheck(contentsid, id);
	}

	// 제주도 여행지 추천 취소 시 다시 0
	@Override
	public void updateSuggestionCheckCancel(String contentsid, String id) throws Exception {
		touristDAO.updateSuggestionCheckCancel(contentsid, id);
	}

	// 제주도 여행지 추천 수 취소
	@Override
	public void updateSuggestionCancel(String contentsid) throws Exception {
		touristDAO.updateSuggestionCancel(contentsid);
	}

	// 제주도 여행지 추천 취소 시 delete
	@Override
	public void deleteSuggestion(String contentsid, String id) throws Exception {
		touristDAO.deleteSuggestion(contentsid, id);
	}

	// 제주도 여행지 메인페이지 배너
	@Override
	public List<ApiDTO> jejutourist_main(ApiDTO apiDTO) throws Exception {
		return touristDAO.jejutouristmain(apiDTO);
	}

	// 부산 댓글 수 증가
	@Override
	public int busancommentcount(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busancommentcount(busanApiDTO);
	}
	
	// 부산 댓글 수 감소
	@Override
	public int busancommentcountminus(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busancommentcountminus(busanApiDTO);
	}
	
	// 부산 댓글 총 개수
	@Override
	public int busangetCommentTotalRowCount(CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.busanCommentgetTotalRowCount(commentpagingDTO);
	}

	// 부산 댓글 조회
	@Override
	public List<BusanCommentDTO> busanreadReply (CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.busanreadReply(commentpagingDTO);
	}
	
	// 부산 댓글 작성
	@Override
	public void busanregister(BusanCommentDTO busanCommentDTO) throws Exception {
		touristDAO.busancreate(busanCommentDTO);
	}
	
	// 부산 댓글 수정
	@Override
	public void busanmodify(BusanCommentDTO busanCommentDTO) throws Exception {  
		touristDAO.busanupdate(busanCommentDTO);		
	}
	
	// 부산 댓글 삭제
	@Override
	public void busanremove(BusanCommentDTO busanCommentDTO) throws Exception {  
		touristDAO.busandeleteReply(busanCommentDTO);
	}
	
	// 부산 선택된 댓글 조회
	@Override
	public BusanCommentDTO busanselectReply(int com_num) throws Exception {  
		return touristDAO.busanselectReply(com_num);
	}
	
	// 부산 여행지 찜 중복방지 select문
	@Override
	public int busansteamedCheck(int UC_SEQ, String id) throws Exception {
		return touristDAO.busanSteamedCheck(UC_SEQ, id);
	}

	// 부산 여행지 찜 시 steamed 테이블에 insert
	@Override
	public void busaninsertSteamed(int UC_SEQ, String id) throws Exception {
		touristDAO.busaninsertSteamed(UC_SEQ, id);
	}

	// 부산 여행지 찜 수
	@Override
	public void busanupdateSteamed(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSteamed(UC_SEQ);
	}

	// 부산 여행지 찜 시 Check를 1로 만들어서 중복방지
	@Override
	public void busanupdateSteamedCheck(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSteamedCheck(UC_SEQ, id);
	}

	// 부산 여행지 찜 취소 시 다시 0
	@Override
	public void busanupdateSteamedCheckCancel(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSteamedCheckCancel(UC_SEQ, id);
	}

	// 부산 여행지 찜 수 취소
	@Override
	public void busanupdateSteamedCancel(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSteamedCancel(UC_SEQ);
	}

	// 부산 여행지 찜 취소 시 delete
	@Override
	public void busandeleteSteamed(int UC_SEQ, String id) throws Exception {
		touristDAO.busandeleteSteamed(UC_SEQ, id);
	}

	// 부산 여행지 추천 중복방지 select문
	@Override
	public int busanSuggestionCheck(int UC_SEQ, String id) throws Exception {
		return touristDAO.busanSuggestionCheck(UC_SEQ, id);
	}

	// 부산 여행지 추천 시 steamed 테이블에 insert
	@Override
	public void busaninsertSuggestion(int UC_SEQ, String id) throws Exception {
		touristDAO.busaninsertSuggestion(UC_SEQ, id);
	}

	// 부산 여행지 추천 수
	@Override
	public void busanupdateSuggestion(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSuggestion(UC_SEQ);
	}

	// 부산 여행지 추천 시 Check를 1로 만들어서 중복방지
	@Override
	public void busanupdateSuggestionCheck(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSuggestionCheck(UC_SEQ, id);
	}

	// 부산 여행지 추천 취소 시 다시 0
	@Override
	public void busanupdateSuggestionCheckCancel(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSuggestionCheckCancel(UC_SEQ, id);
	}

	// 부산 여행지 추천 수 취소
	@Override
	public void busanupdateSuggestionCancel(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSuggestionCancel(UC_SEQ);
	}

	// 부산 여행지 추천 취소 시 delete
	@Override
	public void busandeleteSuggestion(int UC_SEQ, String id) throws Exception {
		touristDAO.busandeleteSuggestion(UC_SEQ, id);
	}

	//
	// 부산 댓글 수 증가
	@Override
	public int busancommentcount2(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busancommentcount2(busanApiDTO);
	}
	
	// 부산 댓글 수 감소
	@Override
	public int busancommentcountminus2(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busancommentcountminus2(busanApiDTO);
	}
	
	// 부산 댓글 총 개수
	@Override
	public int busangetCommentTotalRowCount2(CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.busanCommentgetTotalRowCount2(commentpagingDTO);
	}

	// 부산 댓글 조회
	@Override
	public List<BusanCommentDTO> busanreadReply2 (CommentPagingDTO commentpagingDTO) throws Exception {
		return touristDAO.busanreadReply2(commentpagingDTO);
	}
	
	// 부산 댓글 작성
	@Override
	public void busanregister2(BusanCommentDTO busanCommentDTO) throws Exception {
		touristDAO.busancreate2(busanCommentDTO);
	}
	
	// 부산 댓글 수정
	@Override
	public void busanmodify2(BusanCommentDTO busanCommentDTO) throws Exception {  
		touristDAO.busanupdate2(busanCommentDTO);		
	}
	
	// 부산 댓글 삭제
	@Override
	public void busanremove2(BusanCommentDTO busanCommentDTO) throws Exception {  
		touristDAO.busandeleteReply2(busanCommentDTO);
	}
	
	// 부산 선택된 댓글 조회
	@Override
	public BusanCommentDTO busanselectReply2(int com_num) throws Exception {  
		return touristDAO.busanselectReply2(com_num);
	}
	
	// 부산 여행지 찜 중복방지 select문
	@Override
	public int busansteamedCheck2(int UC_SEQ, String id) throws Exception {
		return touristDAO.busanSteamedCheck2(UC_SEQ, id);
	}

	// 부산 여행지 찜 시 steamed 테이블에 insert
	@Override
	public void busaninsertSteamed2(int UC_SEQ, String id) throws Exception {
		touristDAO.busaninsertSteamed2(UC_SEQ, id);
	}

	// 부산 여행지 찜 수
	@Override
	public void busanupdateSteamed2(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSteamed2(UC_SEQ);
	}

	// 부산 여행지 찜 시 Check를 1로 만들어서 중복방지
	@Override
	public void busanupdateSteamedCheck2(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSteamedCheck2(UC_SEQ, id);
	}

	// 부산 여행지 찜 취소 시 다시 0
	@Override
	public void busanupdateSteamedCheckCancel2(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSteamedCheckCancel2(UC_SEQ, id);
	}

	// 부산 여행지 찜 수 취소
	@Override
	public void busanupdateSteamedCancel2(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSteamedCancel2(UC_SEQ);
	}

	// 부산 여행지 찜 취소 시 delete
	@Override
	public void busandeleteSteamed2(int UC_SEQ, String id) throws Exception {
		touristDAO.busandeleteSteamed2(UC_SEQ, id);
	}

	// 부산 여행지 추천 중복방지 select문
	@Override
	public int busanSuggestionCheck2(int UC_SEQ, String id) throws Exception {
		return touristDAO.busanSuggestionCheck2(UC_SEQ, id);
	}

	// 부산 여행지 추천 시 steamed 테이블에 insert
	@Override
	public void busaninsertSuggestion2(int UC_SEQ, String id) throws Exception {
		touristDAO.busaninsertSuggestion2(UC_SEQ, id);
	}

	// 부산 여행지 추천 수
	@Override
	public void busanupdateSuggestion2(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSuggestion2(UC_SEQ);
	}

	// 부산 여행지 추천 시 Check를 1로 만들어서 중복방지
	@Override
	public void busanupdateSuggestionCheck2(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSuggestionCheck2(UC_SEQ, id);
	}

	// 부산 여행지 추천 취소 시 다시 0
	@Override
	public void busanupdateSuggestionCheckCancel2(int UC_SEQ, String id) throws Exception {
		touristDAO.busanupdateSuggestionCheckCancel2(UC_SEQ, id);
	}

	// 부산 여행지 추천 수 취소
	@Override
	public void busanupdateSuggestionCancel2(int UC_SEQ) throws Exception {
		touristDAO.busanupdateSuggestionCancel2(UC_SEQ);
	}

	// 부산 여행지 추천 취소 시 delete
	@Override
	public void busandeleteSuggestion2(int UC_SEQ, String id) throws Exception {
		touristDAO.busandeleteSuggestion2(UC_SEQ, id);
	}
	
	// 부산 여행지 메인페이지 배너
	@Override
	public List<BusanApiDTO> busantourist_main(BusanApiDTO busanApiDTO) throws Exception {
		return touristDAO.busantouristmain(busanApiDTO);
	}
	//

}
