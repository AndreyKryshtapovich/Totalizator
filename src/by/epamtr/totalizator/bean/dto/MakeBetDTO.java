package by.epamtr.totalizator.bean.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.User;

public class MakeBetDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Event> eventsList;
	private Map<String, String> userResultMap;
	private String betAmount;
	private String gameCouponId;
	private User user;
	private int minBetAmount;
	public List<Event> getEventsList() {
		return eventsList;
	}
	public void setEventsList(List<Event> eventsList) {
		this.eventsList = eventsList;
	}
	public Map<String, String> getUserResultMap() {
		return userResultMap;
	}
	public void setUserResultMap(Map<String, String> userResultMap) {
		this.userResultMap = userResultMap;
	}
	public String getBetAmount() {
		return betAmount;
	}
	public void setBetAmount(String betAmount) {
		this.betAmount = betAmount;
	}
	public String getGameCouponId() {
		return gameCouponId;
	}
	public void setGameCouponId(String gameCouponId) {
		this.gameCouponId = gameCouponId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getMinBetAmount() {
		return minBetAmount;
	}
	public void setMinBetAmount(int minBetAmount) {
		this.minBetAmount = minBetAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((betAmount == null) ? 0 : betAmount.hashCode());
		result = prime * result + ((eventsList == null) ? 0 : eventsList.hashCode());
		result = prime * result + ((gameCouponId == null) ? 0 : gameCouponId.hashCode());
		result = prime * result + minBetAmount;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userResultMap == null) ? 0 : userResultMap.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MakeBetDTO other = (MakeBetDTO) obj;
		if (betAmount == null) {
			if (other.betAmount != null)
				return false;
		} else if (!betAmount.equals(other.betAmount))
			return false;
		if (eventsList == null) {
			if (other.eventsList != null)
				return false;
		} else if (!eventsList.equals(other.eventsList))
			return false;
		if (gameCouponId == null) {
			if (other.gameCouponId != null)
				return false;
		} else if (!gameCouponId.equals(other.gameCouponId))
			return false;
		if (minBetAmount != other.minBetAmount)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userResultMap == null) {
			if (other.userResultMap != null)
				return false;
		} else if (!userResultMap.equals(other.userResultMap))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MakeBetDTO [eventsList=" + eventsList + ", userResultMap=" + userResultMap + ", betAmount=" + betAmount
				+ ", gameCouponId=" + gameCouponId + ", user=" + user + ", minBetAmount=" + minBetAmount + "]";
	}

	
	
	
	

}
