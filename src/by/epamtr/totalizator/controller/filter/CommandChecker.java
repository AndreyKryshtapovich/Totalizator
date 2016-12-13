package by.epamtr.totalizator.controller.filter;

import java.util.HashMap;
import java.util.Map;

public class CommandChecker {
	private Map<String, String> correctCommandsMethods = new HashMap<String, String>();

	public CommandChecker(){
		correctCommandsMethods.put("sign-in", "post");
		correctCommandsMethods.put("show-events", "get"); 
		correctCommandsMethods.put("go-to-admin-page", "get");
		correctCommandsMethods.put("sign-out", "post"); 
		correctCommandsMethods.put("go-to-registration", "get");
		correctCommandsMethods.put("registration-user", "post");
		correctCommandsMethods.put("go-to-game-creation", "get");
		correctCommandsMethods.put("go-to-error-page", "get");
		correctCommandsMethods.put("game-creation", "post");
		correctCommandsMethods.put("change-language", "post");
		correctCommandsMethods.put("go-to-event-creation", "get");
		correctCommandsMethods.put("event-creation", "post");
		correctCommandsMethods.put("go-to-search-event", "get");
		correctCommandsMethods.put("search-matching-events", "get");
		correctCommandsMethods.put("event-game-matching", "post");
		correctCommandsMethods.put("go-to-edit-search-event", "get");
		correctCommandsMethods.put("search-all-events", "get");
		correctCommandsMethods.put("go-to-event-edit", "get");
		correctCommandsMethods.put("edit-event", "post");
		correctCommandsMethods.put("make-bet", "post");
		correctCommandsMethods.put("go-to-bet-submit", "get");
	}
	
	public String getMethod(String key){
		String method = null;
		method = correctCommandsMethods.get(key);
		
		return method;
	}
}
