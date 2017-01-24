package by.epamtr.totalizator.bean.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This class is designed to represent the game coupon's entity. Game coupon
 * represents the drawing of the totalizator that lasts for a week. Users are
 * allowed to make bets on particular game coupons.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class GameCupoun implements Serializable {

	private static final long serialVersionUID = 1L;
	private int gameCupounId;
	private Timestamp startDate;
	private Timestamp endDate;
	private int minBetAmount;
	private int gameCuponPull;
	private int jackpot;
	private int status = 5;

	/**
	 * Initializes a newly created game coupon's object. It represents a game
	 * coupon with out any exact information about it.
	 */

	public GameCupoun() {

	}

	public int getGameCupounId() {
		return gameCupounId;
	}

	public void setGameCupounId(int gameCupounId) {
		this.gameCupounId = gameCupounId;
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

	public int getMinBetAmount() {
		return minBetAmount;
	}

	public void setMinBetAmount(int minBetAmount) {
		this.minBetAmount = minBetAmount;
	}

	public int getGameCuponPull() {
		return gameCuponPull;
	}

	public void setGameCuponPull(int gameCuponPull) {
		this.gameCuponPull = gameCuponPull;
	}

	public int getJackpot() {
		return jackpot;
	}

	public void setJackpot(int jackpot) {
		this.jackpot = jackpot;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
		result = prime * result + gameCuponPull;
		result = prime * result + gameCupounId;
		result = prime * result + jackpot;
		result = prime * result + minBetAmount;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + status;
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
		GameCupoun other = (GameCupoun) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (gameCuponPull != other.gameCuponPull)
			return false;
		if (gameCupounId != other.gameCupounId)
			return false;
		if (jackpot != other.jackpot)
			return false;
		if (minBetAmount != other.minBetAmount)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameCupoun [gameCupounId=" + gameCupounId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", minBetAmount=" + minBetAmount + ", gameCuponPull=" + gameCuponPull + ", jackpot=" + jackpot
				+ ", status=" + status + "]";
	}

}
