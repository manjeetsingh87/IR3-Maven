package com.ir3.utils;

import java.util.List;

import twitter4j.JSONArray;

import com.ir3.pojo.Tweet;

public class ArrayJSONConverter {
	
	public static JSONArray getJSON(List<Tweet> tweetsDataList) {
		return new JSONArray(tweetsDataList);
	}
}