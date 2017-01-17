package by.epamtr.totalizator.bean.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int eventId;
	private String eventName;
	private int gameCuponId;
	private int resultId;
	private int status;
	private String resultAbbreviation;
	private String statusDescription;
	private String teamOne;
	private String teamTwo;
	private Timestamp startDate;
	private Timestamp endDate;
	
	public Event(){
		
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getGameCuponId() {
		return gameCuponId;
	}
	public void setGameCuponId(int gameCuponId) {
		this.gameCuponId = gameCuponId;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResultAbbreviation() {
		return resultAbbreviation;
	}
	public void setResultAbbreviation(String resultAbbreviation) {
		this.resultAbbreviation = resultAbbreviation;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getTeamOne() {
		return teamOne;
	}
	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}
	public String getTeamTwo() {
		return teamTwo;
	}
	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + eventId;
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + gameCuponId;
		result = prime * result + ((resultAbbreviation == null) ? 0 : resultAbbreviation.hashCode());
		result = prime * result + resultId;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((statusDescription == null) ? 0 : statusDescription.hashCode());
		result = prime * result + ((teamOne == null) ? 0 : teamOne.hashCode());
		result = prime * result + ((teamTwo == null) ? 0 : teamTwo.hashCode());
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
		Event other = (Event) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (gameCuponId != other.gameCuponId)
			return false;
		if (resultAbbreviation == null) {
			if (other.resultAbbreviation != null)
				return false;
		} else if (!resultAbbreviation.equals(other.resultAbbreviation))
			return false;
		if (resultId != other.resultId)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (statusDescription == null) {
			if (other.statusDescription != null)
				return false;
		} else if (!statusDescription.equals(other.statusDescription))
			return false;
		if (teamOne == null) {
			if (other.teamOne != null)
				return false;
		} else if (!teamOne.equals(other.teamOne))
			return false;
		if (teamTwo == null) {
			if (other.teamTwo != null)
				return false;
		} else if (!teamTwo.equals(other.teamTwo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", gameCuponId=" + gameCuponId + ", resultId="
				+ resultId + ", status=" + status + ", resultAbbreviation=" + resultAbbreviation
				+ ", statusDescription=" + statusDescription + ", teamOne=" + teamOne + ", teamTwo=" + teamTwo
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	
	
	

	
	
	

}
