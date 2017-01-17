package by.epamtr.totalizator.bean.dto;

import java.io.Serializable;

public class EventDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String eventId;
	private String eventName;
	private String gameCuponId;
	private String resultId;
	private String status;
	private String resultAbbreviation;
	private String statusDescription;
	private String teamOne;
	private String teamTwo;
	private String startDate;
	private String startTimeHours;
	private String startTimeMinutes;
	private String endDate;
	private String endTimeHours;
	private String endTimeMinutes;
	
	public EventDTO(){
		
	}
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getGameCuponId() {
		return gameCuponId;
	}
	public void setGameCuponId(String gameCuponId) {
		this.gameCuponId = gameCuponId;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTimeHours() {
		return startTimeHours;
	}
	public void setStartTimeHours(String startTimeHours) {
		this.startTimeHours = startTimeHours;
	}
	public String getStartTimeMinutes() {
		return startTimeMinutes;
	}
	public void setStartTimeMinutes(String startTimeMinutes) {
		this.startTimeMinutes = startTimeMinutes;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndTimeHours() {
		return endTimeHours;
	}
	public void setEndTimeHours(String endTimeHours) {
		this.endTimeHours = endTimeHours;
	}
	public String getEndTimeMinutes() {
		return endTimeMinutes;
	}
	public void setEndTimeMinutes(String endTimeMinutes) {
		this.endTimeMinutes = endTimeMinutes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((endTimeHours == null) ? 0 : endTimeHours.hashCode());
		result = prime * result + ((endTimeMinutes == null) ? 0 : endTimeMinutes.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((gameCuponId == null) ? 0 : gameCuponId.hashCode());
		result = prime * result + ((resultAbbreviation == null) ? 0 : resultAbbreviation.hashCode());
		result = prime * result + ((resultId == null) ? 0 : resultId.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((startTimeHours == null) ? 0 : startTimeHours.hashCode());
		result = prime * result + ((startTimeMinutes == null) ? 0 : startTimeMinutes.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		EventDTO other = (EventDTO) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (endTimeHours == null) {
			if (other.endTimeHours != null)
				return false;
		} else if (!endTimeHours.equals(other.endTimeHours))
			return false;
		if (endTimeMinutes == null) {
			if (other.endTimeMinutes != null)
				return false;
		} else if (!endTimeMinutes.equals(other.endTimeMinutes))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (gameCuponId == null) {
			if (other.gameCuponId != null)
				return false;
		} else if (!gameCuponId.equals(other.gameCuponId))
			return false;
		if (resultAbbreviation == null) {
			if (other.resultAbbreviation != null)
				return false;
		} else if (!resultAbbreviation.equals(other.resultAbbreviation))
			return false;
		if (resultId == null) {
			if (other.resultId != null)
				return false;
		} else if (!resultId.equals(other.resultId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startTimeHours == null) {
			if (other.startTimeHours != null)
				return false;
		} else if (!startTimeHours.equals(other.startTimeHours))
			return false;
		if (startTimeMinutes == null) {
			if (other.startTimeMinutes != null)
				return false;
		} else if (!startTimeMinutes.equals(other.startTimeMinutes))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		return "EventDTO [eventId=" + eventId + ", eventName=" + eventName + ", gameCuponId=" + gameCuponId
				+ ", resultId=" + resultId + ", status=" + status + ", resultAbbreviation=" + resultAbbreviation
				+ ", statusDescription=" + statusDescription + ", teamOne=" + teamOne + ", teamTwo=" + teamTwo
				+ ", startDate=" + startDate + ", startTimeHours=" + startTimeHours + ", startTimeMinutes="
				+ startTimeMinutes + ", endDate=" + endDate + ", endTimeHours=" + endTimeHours + ", endTimeMinutes="
				+ endTimeMinutes + "]";
	}
}
