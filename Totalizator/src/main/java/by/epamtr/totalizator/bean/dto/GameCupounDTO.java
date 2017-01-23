package by.epamtr.totalizator.bean.dto;

import java.io.Serializable;

/**
 * A Data Transfer Object representing {@link by.epamtr.totalizator.bean.entity.GameCupoun} object.
 * 
 * @author Andrey
 *
 */

public class GameCupounDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String gameCupounId;
	private String startDate;
	private String startTimeHours;
	private String startTimeMinutes;
	private String endDate;
	private String endTimeHours;
	private String endTimeMinutes;
	private String minBetAmount;
	private String gameCuponPull;
	private String jackpot;
	private String status;

	public GameCupounDTO() {

	}

	public String getGameCupounId() {
		return gameCupounId;
	}

	public void setGameCupounId(String gameCupounId) {
		this.gameCupounId = gameCupounId;
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

	public String getMinBetAmount() {
		return minBetAmount;
	}

	public void setMinBetAmount(String minBetAmount) {
		this.minBetAmount = minBetAmount;
	}

	public String getGameCuponPull() {
		return gameCuponPull;
	}

	public void setGameCuponPull(String gameCuponPull) {
		this.gameCuponPull = gameCuponPull;
	}

	public String getJackpot() {
		return jackpot;
	}

	public void setJackpot(String jackpot) {
		this.jackpot = jackpot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		result = prime * result + ((gameCuponPull == null) ? 0 : gameCuponPull.hashCode());
		result = prime * result + ((gameCupounId == null) ? 0 : gameCupounId.hashCode());
		result = prime * result + ((jackpot == null) ? 0 : jackpot.hashCode());
		result = prime * result + ((minBetAmount == null) ? 0 : minBetAmount.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((startTimeHours == null) ? 0 : startTimeHours.hashCode());
		result = prime * result + ((startTimeMinutes == null) ? 0 : startTimeMinutes.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		GameCupounDTO other = (GameCupounDTO) obj;
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
		if (gameCuponPull == null) {
			if (other.gameCuponPull != null)
				return false;
		} else if (!gameCuponPull.equals(other.gameCuponPull))
			return false;
		if (gameCupounId == null) {
			if (other.gameCupounId != null)
				return false;
		} else if (!gameCupounId.equals(other.gameCupounId))
			return false;
		if (jackpot == null) {
			if (other.jackpot != null)
				return false;
		} else if (!jackpot.equals(other.jackpot))
			return false;
		if (minBetAmount == null) {
			if (other.minBetAmount != null)
				return false;
		} else if (!minBetAmount.equals(other.minBetAmount))
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
		return true;
	}

	@Override
	public String toString() {
		return "GameCupounDTO [gameCupounId=" + gameCupounId + ", startDate=" + startDate + ", startTimeHours="
				+ startTimeHours + ", startTimeMinutes=" + startTimeMinutes + ", endDate=" + endDate + ", endTimeHours="
				+ endTimeHours + ", endTimeMinutes=" + endTimeMinutes + ", minBetAmount=" + minBetAmount
				+ ", gameCuponPull=" + gameCuponPull + ", jackpot=" + jackpot + ", status=" + status + "]";
	}

}
