package mydb.dao;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Order database table.
 * 
 */
@Embeddable
public class OrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int weeklyRecipeId;

	@Column(insertable=false, updatable=false)
	private String userName;

	public OrderPK() {
	}
	public int getWeeklyRecipeId() {
		return this.weeklyRecipeId;
	}
	public void setWeeklyRecipeId(int weeklyRecipeId) {
		this.weeklyRecipeId = weeklyRecipeId;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderPK)) {
			return false;
		}
		OrderPK castOther = (OrderPK)other;
		return 
			(this.weeklyRecipeId == castOther.weeklyRecipeId)
			&& this.userName.equals(castOther.userName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.weeklyRecipeId;
		hash = hash * prime + this.userName.hashCode();
		
		return hash;
	}
}