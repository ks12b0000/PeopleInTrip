package kr.co.intrip.tourist.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class PagingDTO implements Serializable {

	//입력받는 데이터
	private int curPage=1;           // 현재 페이지 번호
	private int rowSizePerPage=10;   // 한 페이지당 레코드 수      기본10
	private int pageSize=10;         // 페이지 리스트에서 보여줄 페이지 갯수  이거는 보통 10 or 5 안 변함 
	private int totalRowCount ;      // 총 레코드 건수
		
		
	//입력받는 데이터를 통해 계산되는 값
	private int firstRow ;           // 시작 레크드 번호   
	private int lastRow;             // 마지막 레크드 번호 
	private int totalPageCount;      // 총 페이지 건수
	private int firstPage; 	         // 페이지 리스트에서 시작  페이지 번호 
	private int lastPage;            // 페이지 리스트에서 마지막 페이지 번호 
		

	public void pageSetting() {
		totalPageCount = (totalRowCount-1)/rowSizePerPage+ 1;
		firstRow = (curPage - 1) * rowSizePerPage + 1;
		lastRow = firstRow + rowSizePerPage-1;
		if(lastRow >= totalRowCount) { 
			lastRow = totalRowCount;
		} 

		firstPage = (  (curPage-1) / pageSize) * pageSize + 1;
			
		lastPage = firstPage + pageSize-1;
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}
	}
	
	@Override 
	public String toString() {
	return ToStringBuilder.reflectionToString(this,
			ToStringStyle.MULTI_LINE_STYLE); 
	}
	
}