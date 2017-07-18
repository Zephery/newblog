package com.myblog.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @author 
 *
 */
public class StringUtil {

	/**
	 * @Description
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @Description
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @Description
	 * @param str
	 * @return
	 */
	public static String formatLike(String str) {
		if(isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}
	
	/**
	 * @Description
	 * @param list
	 * @return
	 */
	public static List<String> filterWhite(List<String> list) {
		List<String> resultList = new ArrayList<String>();
		for(String l : list) {
			if(isNotEmpty(l)) {
				resultList.add(l);
			}
		}
		return resultList;
	}

}
