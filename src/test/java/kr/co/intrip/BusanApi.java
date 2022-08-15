package kr.co.intrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.intrip.ApiTest.WeatherValue;
import kr.co.intrip.tourist.dao.TouristDAO;
import kr.co.intrip.tourist.dto.BusanApiDTO;

public class BusanApi {

	public void main(String []args) throws Exception { 
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
		
		//touristDAO.busantouristadd(list);
	}

}
