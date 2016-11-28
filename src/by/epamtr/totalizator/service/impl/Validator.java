package by.epamtr.totalizator.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.dao.AdminDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.service.exception.ServiceException;

public class Validator {
/*	public static boolean loginValidation(String login, String password) {
		// проверка логина и пароля
		if (login.isEmpty()) {
			return false;
		}
		if (password.isEmpty()) {
			return false;
		}

		return true;
	}*/
	
	public static boolean loginValidation(String login, byte[] password) {
		// проверка логина и пароля
		if (login.isEmpty()) {
			return false;
		}
		if (password.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean userInfoValidation(String firstName, String lastName, String login, byte[] password,
			byte[] repPassword, String sex, String eMail, String country, String city, String address) {

		if (firstName.isEmpty()) {
			return false;
		}
		if (lastName.isEmpty()) {
			return false;
		}
		if (login.isEmpty()) {
			return false;
		}
		if (password.length == 0 || repPassword.length == 0) {
			return false;
		}
		if (!Arrays.equals(password, repPassword)) {
			return false;
		}
		if (sex.isEmpty()) {
			return false;
		}
		if (eMail.isEmpty()) {
			return false;
		}
		if (country.isEmpty()) {
			return false;
		}

		return true;

	}
	
	public static boolean newGameCupounInfoValidation(String startDate, String startTimeHours, String startTimeMinutes, String endDate,
			String endTimeHours, String endTimeMinutes, String minBetAmount){
		
		if(startDate.isEmpty()){
			return false;
		}
		if(startTimeHours.isEmpty()){
			return false;
		}
		if(startTimeMinutes.isEmpty()){
			return false;
		}
		if(endDate.isEmpty()){
			return false;
		}
		if(endTimeHours.isEmpty()){
			return false;
		}
		if(endTimeMinutes.isEmpty()){
			return false;
		}
		if(minBetAmount.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static boolean newEventInfoValidation(String eventName, String startDate, String startTimeHours, String startTimeMinutes, String endDate,
			String endTimeHours, String endTimeMinutes, String teamOne, String teamTwo){
		
		if(eventName.isEmpty()){
			return false;
		}
		if(startDate.isEmpty()){
			return false;
		}
		if(startTimeHours.isEmpty()){
			return false;
		}
		if(startTimeMinutes.isEmpty()){
			return false;
		}
		if(endDate.isEmpty()){
			return false;
		}
		if(endTimeHours.isEmpty()){
			return false;
		}
		if(endTimeMinutes.isEmpty()){
			return false;
		}
		if(teamOne.isEmpty()){
			return false;
		}
		if(teamTwo.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static boolean updateEventValidation(Event event){
		boolean result = true;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		List<GameCupoun> gamesList = null;
		
			try {
				gamesList = adminDAO.getGameByByGameCupounId(event.getGameCuponId());
			} catch (DAOException e) {
				result = false;
				return result;
			}
			if(event.getEndDate().before(event.getStartDate())){
				result = false;
			}
			
			GameCupoun game = gamesList.get(0);
			
			if(!(event.getStartDate().after(game.getEndDate()))){
				result = false;
			}
			
			Calendar cal = Calendar.getInstance();
			// event should end in 2 days since game cupoun end date
			cal.setTimeInMillis(game.getEndDate().getTime());
			cal.add(Calendar.DAY_OF_MONTH, 2);
			Timestamp gameEndDatePlus2 = new Timestamp(cal.getTime().getTime());  
		
		/*	if(!(game.getStartDate().before(event.getStartDate()) && game.getEndDate().after(event.getEndDate())) ){
				result = false;
			}*/
			
			if(!(event.getEndDate().before(gameEndDatePlus2))){
				result = false;
			}
			
			/*if(!game.getEndDate().after(event.getStartDate())){
				result = false;
			}*/
			
		return result;
	}
	
	public static boolean dropDownValidation(String parameters){
		boolean result = true;
		
		if(parameters.isEmpty()){
			result = false;
		}
		return result;
	}
}