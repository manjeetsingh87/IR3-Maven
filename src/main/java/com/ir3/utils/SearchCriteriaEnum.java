package com.ir3.utils;

public enum SearchCriteriaEnum {
	LANGUAGE("LANGUAGE"),
	QUERY("QUERY"),
	SINCE("SINCE");
	
	private String queryName;
	
	private SearchCriteriaEnum(String queryName) {
		this.queryName = queryName;
	}
	
	public String getQueryName() {
		return this.queryName;
	}
}
