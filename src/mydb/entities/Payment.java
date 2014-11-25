package mydb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Payment database table.
 * 
 */
@Entity
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;

	private String address;

	private String creditCard;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="userName",insertable = false, updatable = false)
	private User user;

	public Payment() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}