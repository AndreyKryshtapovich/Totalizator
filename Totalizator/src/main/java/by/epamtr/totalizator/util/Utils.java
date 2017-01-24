package by.epamtr.totalizator.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is designed to perform util operations with Strings.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class Utils {

	private static final String SPACE = " ";
	private static final String COLON = ":";
	private static final String DOUBLE_ZERO = "00";
	private static final String RESULT = "result";
	private static final String DRAW = "X";
	private static final String THREE = "3";
	private static final String BET_AMOUNT = "bet-amount";
	private final static String AMPERSAND = "&";
	private final static String EQUAL = "=";

	/**
	 * Parses Id of the game coupon from a String in format
	 * {@code 1  2016-10-10 00:00:00.0 - 2016-10-14 00:00:00.0} First digit is
	 * the game coupon's Id.
	 * 
	 * @param parameters
	 *            {@link String} in format
	 *            {@code 1  2016-10-10 00:00:00.0 - 2016-10-14 00:00:00.0}.
	 * @return game coupon's Id.
	 */
	public static String parseParamGameCupounId(String parameters) {
		return parameters.substring(0, 1);
	}

	/**
	 * Parses start date of the game coupon from a String in format
	 * {@code 1  2016-10-10 00:00:00.0 - 2016-10-14 00:00:00.0} . Start date is
	 * represented by the sequence before dash.
	 * 
	 * @param parameters
	 *            {@link String} in format
	 *            {@code 1  2016-10-10 00:00:00.0 - 2016-10-14 00:00:00.0}.
	 * @return game coupon's start date.
	 */
	public static String parseParamGameCupounStartDate(String parameters) {
		return parameters.substring(3, 25);
	}

	/**
	 * Parses end date of the game coupon from a String in format
	 * {@code 1  2016-10-10 00:00:00.0 - 2016-10-14 00:00:00.0} . End date is
	 * represented by the sequence after dash.
	 * 
	 * @param parameters
	 *            {@link String} in format
	 *            {@code 1  2016-10-10 00:00:00.0 - 2016-10-14 00:00:00.0}.
	 * @return game coupon's end date.
	 */
	public static String parseParamGameCupounEndDate(String parameters) {
		return parameters.substring(27);
	}

	/**
	 * Concatenates three separate strings to match the format
	 * {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * 
	 * Seconds are always set to {@code 00}.
	 * 
	 * @param date
	 *            {@link String} that represents date in format yyyy-[m]m-[d]d
	 * @param hours
	 *            {@link String} that represents hours in format hh
	 * @param minutes
	 *            {@link String} that represents minutes in format mm
	 * @return {@link String} in format {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 */
	public static String concatStringDate(String date, String hours, String minutes) {

		return date + SPACE + hours + COLON + minutes + COLON + DOUBLE_ZERO;
	}

	/**
	 * Parses a date from a {@link String} in format
	 * {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * 
	 * @param fullDate
	 *            {@link String} in format {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * @return a {@link String} representing a date in format
	 *         {@code yyyy-[m]m-[d]d}.
	 */
	public static String parseDateFromFullDate(String fullDate) {
		return fullDate.substring(0, fullDate.indexOf(SPACE));
	}

	/**
	 * Parses hours from a {@link String} in format
	 * {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * 
	 * @param fullDate
	 *            {@link String} in format {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * @return a {@link String} representing hours in format {@code hh}.
	 */
	public static String parseHoursFromFullDate(String fullDate) {
		return fullDate.substring(fullDate.indexOf(SPACE) + 1, fullDate.indexOf(COLON));
	}

	/**
	 * Parses minutes from a {@link String} in format
	 * {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * 
	 * @param fullDate
	 *            {@link String} in format {@code yyyy-[m]m-[d]d hh:mm:ss}.
	 * @return a {@link String} representing minutes in format {@code mm}.
	 */
	public static String parseMinutesFromFullDate(String fullDate) {
		return fullDate.substring(fullDate.indexOf(COLON) + 1, fullDate.indexOf(COLON) + 3);
	}

	/**
	 * Creates a {@link Map} with the informations about event's results
	 * suggested by user in his bet.
	 * 
	 * @param prevUrl
	 *            URL in format
	 *            {@code Controller?command=command-name&result1=/user's result(1,2 or 3)/&result2=/user's result(1,2 or 3)/&result3=/user's result(1,2 or 3)/&result4=/user's result(1,2 or 3)/&result5=/user's result(1,2 or 3)/&result6=/user's result(1,2 or 3)/&result7=/user's result(1,2 or 3)/&result8=/user's result(1,2 or 3)/&result9=/user's result(1,2 or 3)/&result10=/user's result(1,2 or 3)/&result11=/user's result(1,2 or 3)/&result12=/user's result(1,2 or 3)/&result13=/user's result(1,2 or 3)/&result14=/user's result(1,2 or 3)/&result15=/user's result(1,2 or 3)/&bet-amount=/bet amount/&game-coupon-id=/game coupon's id/}
	 * @return {@link Map}. {@code Key} is the result's number. {@code Value} is
	 *         the result suggested by user.
	 */
	public static Map<String, String> parseUserResultMap(String prevUrl) {
		Map<String, String> userResultMap = new HashMap<>();
		String strForMap = new String();

		for (int i = 1; i < 16; i++) {
			strForMap = prevUrl.substring(prevUrl.indexOf(RESULT + new Integer(i).toString()) + 8,
					prevUrl.indexOf(RESULT + new Integer(i).toString()) + 9);
			if (i > 9) {
				strForMap = prevUrl.substring(prevUrl.indexOf(RESULT + new Integer(i).toString()) + 9,
						prevUrl.indexOf(RESULT + new Integer(i).toString()) + 10);
			}
			if (strForMap.equals(DRAW)) {
				strForMap = THREE;
			}
			userResultMap.put(RESULT + new Integer(i).toString(), strForMap);
		}
		return userResultMap;
	}

	/**
	 * Parses amount of user's bet from the URL.
	 * 
	 * @param prevUrl
	 *            URL in format
	 *            {@code Controller?command=command-name&result1=/user's result(1,2 or 3)/&result2=/user's result(1,2 or 3)/&result3=/user's result(1,2 or 3)/&result4=/user's result(1,2 or 3)/&result5=/user's result(1,2 or 3)/&result6=/user's result(1,2 or 3)/&result7=/user's result(1,2 or 3)/&result8=/user's result(1,2 or 3)/&result9=/user's result(1,2 or 3)/&result10=/user's result(1,2 or 3)/&result11=/user's result(1,2 or 3)/&result12=/user's result(1,2 or 3)/&result13=/user's result(1,2 or 3)/&result14=/user's result(1,2 or 3)/&result15=/user's result(1,2 or 3)/&bet-amount=/bet amount/&game-coupon-id=/game coupon's id/}
	 * @return user's bet amount.
	 */
	public static String parseBetAmount(String prevUrl) {
		String betAmount = prevUrl.substring(prevUrl.indexOf(BET_AMOUNT) + 11, prevUrl.lastIndexOf(AMPERSAND));
		return betAmount;
	}

	/**
	 * Parses Id of the game coupon from the URL.
	 * 
	 * @param prevUrl
	 *            URL in format
	 *            {@code Controller?command=command-name&result1=/user's result(1,2 or 3)/&result2=/user's result(1,2 or 3)/&result3=/user's result(1,2 or 3)/&result4=/user's result(1,2 or 3)/&result5=/user's result(1,2 or 3)/&result6=/user's result(1,2 or 3)/&result7=/user's result(1,2 or 3)/&result8=/user's result(1,2 or 3)/&result9=/user's result(1,2 or 3)/&result10=/user's result(1,2 or 3)/&result11=/user's result(1,2 or 3)/&result12=/user's result(1,2 or 3)/&result13=/user's result(1,2 or 3)/&result14=/user's result(1,2 or 3)/&result15=/user's result(1,2 or 3)/&bet-amount=/bet amount/&game-coupon-id=/game coupon's id/}
	 * @return game coupon's Id.
	 */
	public static String parseGameCouponId(String prevUrl) {
		return prevUrl.substring(prevUrl.lastIndexOf(EQUAL) + 1);
	}
}
