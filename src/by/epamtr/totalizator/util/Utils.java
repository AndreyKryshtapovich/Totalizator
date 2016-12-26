package by.epamtr.totalizator.util;

import java.util.HashMap;
import java.util.Map;

public class Utils {
	
	public static String parseParamGameCupounId (String parameters){
		
		return parameters.substring(0, 1);
	
	} 
	
	public static String parseParamGameCupounStartDate (String parameters){
		return parameters.substring(3,25);
	}
	
	public static String parseParamGameCupounEndDate(String parameters){
		return parameters.substring(27);
	}
	
	public static String concatStringDate (String date, String hours, String minutes){
		
		return date + " " + hours + ":" + minutes + ":" + "00";
	}
	
	public static String parseDateFromFullDate(String fullDate){
		return fullDate.substring(0,fullDate.indexOf(" "));
	}
	public static String parseHoursFromFullDate(String fullDate){
		return fullDate.substring(fullDate.indexOf(" ") + 1,fullDate.indexOf(":"));
	}
	public static String parseMinutesFromFullDate(String fullDate){
		return fullDate.substring(fullDate.indexOf(":") + 1,fullDate.indexOf(":") + 3);
	}
	public static Map<String, String> parseUserResultMap(String prevUrl){
		Map<String, String> userResultMap = new HashMap<>();
		String strForMap = new String();
		
		for (int i = 1; i < 16; i++) {
			strForMap = prevUrl.substring(prevUrl.indexOf("result" + new Integer(i).toString()) + 8,prevUrl.indexOf("result" + new Integer(i).toString()) + 9);
			if(i > 9){
				strForMap = prevUrl.substring(prevUrl.indexOf("result" + new Integer(i).toString()) + 9,prevUrl.indexOf("result" + new Integer(i).toString()) + 10);
			}
			if(strForMap.equals("X")){
				strForMap="3";
			}
			userResultMap.put("result" + new Integer(i).toString(), strForMap);
		}
		return userResultMap;
	}
	
	public static String parseBetAmount(String prevUrl){
		String betAmount = prevUrl.substring(prevUrl.indexOf("bet-amount")+11,prevUrl.lastIndexOf("&"));
		return betAmount;
	}
	
	public static String parseGameCouponId (String prevUrl){
		return prevUrl.substring(prevUrl.lastIndexOf("=") + 1);
	}
}
