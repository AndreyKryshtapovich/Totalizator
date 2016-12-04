package by.epamtr.totalizator.bean.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;

public class EventsListDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int gameCupounId;
	private List<Event> eventList;
	private Timestamp gameStartDate;
	private Timestamp gameEndDate;
	public int getGameCupounId() {
		return gameCupounId;
	}
	public void setGameCupounId(int gameCupounId) {
		this.gameCupounId = gameCupounId;
	}
	public List<Event> getEventList() {
		return eventList;
	}
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}
	public Timestamp getGameStartDate() {
		return gameStartDate;
	}
	public void setGameStartDate(Timestamp gameStartDate) {
		this.gameStartDate = gameStartDate;
	}
	public Timestamp getGameEndDate() {
		return gameEndDate;
	}
	public void setGameEndDate(Timestamp gameEndDate) {
		this.gameEndDate = gameEndDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventList == null) ? 0 : eventList.hashCode());
		result = prime * result + gameCupounId;
		result = prime * result + ((gameEndDate == null) ? 0 : gameEndDate.hashCode());
		result = prime * result + ((gameStartDate == null) ? 0 : gameStartDate.hashCode());
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
		EventsListDTO other = (EventsListDTO) obj;
		if (eventList == null) {
			if (other.eventList != null)
				return false;
		} else if (!eventList.equals(other.eventList))
			return false;
		if (gameCupounId != other.gameCupounId)
			return false;
		if (gameEndDate == null) {
			if (other.gameEndDate != null)
				return false;
		} else if (!gameEndDate.equals(other.gameEndDate))
			return false;
		if (gameStartDate == null) {
			if (other.gameStartDate != null)
				return false;
		} else if (!gameStartDate.equals(other.gameStartDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UnmatchedEventsDTO [gameCupounId=" + gameCupounId + ", eventList=" + eventList + ", gameStartDate="
				+ gameStartDate + ", gameEndDate=" + gameEndDate + "]";
	}
	
	
	

}
