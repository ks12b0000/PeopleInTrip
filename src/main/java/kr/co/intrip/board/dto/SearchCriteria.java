package kr.co.intrip.board.dto;

public class SearchCriteria extends Criteria{

	 private String searchType ="";
	 private String keyword = "";
	 
	 private String id = "";
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 

	public String getSearchType() {
	  return searchType;
	 }
	 public void setSearchType(String searchType) {
	  this.searchType = searchType;
	 }
	 public String getKeyword() {
	  return keyword;
	 }
	 public void setKeyword(String keyword) {
	  this.keyword = keyword;
	 }
	 
	 @Override
	 public String toString() {
	  return super.toString() + " SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	 }
	}
