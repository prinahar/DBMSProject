package mydb.dao;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the Orders database table.
 * 
 */
@Entity
@Table(name="Orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderPK id;

	private boolean accepted;

	private String comment;

	private boolean confirmation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryTime;

	private boolean orderComplete;

	private int quantity;

	private int rating;

	//bi-directional many-to-one association to WeeklyRecipe
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="weeklyRecipeId")
	private WeeklyRecipe weeklyRecipe;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="userName", insertable = false, updatable = false)
	private User user;

	public Order() {
	}

	
	public Order(boolean accepted, String comment, boolean confirmation,
			Date deliveryTime, boolean orderComplete, int quantity, int rating,
			WeeklyRecipe weeklyRecipe, User user) {
		super();
		this.accepted = accepted;
		this.comment = comment;
		this.confirmation = confirmation;
		this.deliveryTime = deliveryTime;
		this.orderComplete = orderComplete;
		this.quantity = quantity;
		this.rating = rating;
		this.id = new OrderPK(weeklyRecipe.getWeeklyRecipeId(), user.getUserName());
		this.weeklyRecipe = weeklyRecipe;
		this.user = user;
	}


	public OrderPK getId() {
		return this.id;
	}

	public void setId(OrderPK id) {
		this.id = id;
	}

	public boolean getAccepted() {
		return this.accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean getConfirmation() {
		return this.confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public boolean getOrderComplete() {
		return this.orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
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

	public WeeklyRecipe getWeeklyRecipe() {
		return this.weeklyRecipe;
	}

	public void setWeeklyRecipe(WeeklyRecipe weeklyRecipe) {
		this.weeklyRecipe = weeklyRecipe;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
