package com.ir3.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utility {
	
	public static List<String> getQueryParams(Map<String, String> map, String key) {
		List<String> dataLst = new ArrayList<String>();
		String dataStr = map.get(key);
		String[] data = null;
		if(null != dataStr) {
			createQueryParamsList(dataLst, dataStr, data, false);
		}
		return dataLst;
	}

	private static void createQueryParamsList(List<String> dataLst,
			String dataStr, String[] data, boolean flag) {
		if(dataStr.contains(",")) {
			flag = true;
			data = dataStr.split(",");
		}	
		if(flag) {
			for(int i=0; i<data.length; i++) {
				dataLst.add(data[i]);
			}
		} else
			dataLst.add(0, dataStr);
	}
}