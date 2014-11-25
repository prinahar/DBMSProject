package mydb.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderPK id;

	private byte accepted;

	private String comment;

	private byte confirmation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryTime;

	private byte orderComplete;

	private int quantity;

	private int rating;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userName")
	private User user;

	//bi-directional many-to-one association to WeeklyRecipe
	@ManyToOne
	@JoinColumn(name="weeklyRecipeId")
	private WeeklyRecipe weeklyRecipe;

	public Order() {
	}

	public OrderPK getId() {
		return this.id;
	}

	public void setId(OrderPK id) {
		this.id = id;
	}

	public byte getAccepted() {
		return this.accepted;
	}

	public void setAccepted(byte accepted) {
		this.accepted = accepted;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte getConfirmation() {
		return this.confirmation;
	}

	public void setConfirmation(byte confirmation) {
		this.confirmation = confirmation;
	}

	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public byte getOrderComplete() {
		return this.orderComplete;
	}

	public void setOrderComplete(byte orderComplete) {
		this.orderComplete = orderComplete;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WeeklyRecipe getWeeklyRecipe() {
		return this.weeklyRecipe;
	}

	public void setWeeklyRecipe(WeeklyRecipe weeklyRecipe) {
		this.weeklyRecipe = weeklyRecipe;
	}

}