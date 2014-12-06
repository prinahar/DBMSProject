
package mydb.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	private String userName;

	//bi-directional many-to-many association to Cuisine
	@ManyToMany
	@JoinTable(
		name="Preference"
		, joinColumns={
			@JoinColumn(name="userName")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cuisineName")
			}
		)
	private List<Cuisine> cuisines;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user")
	private List<Order> orders;

	//bi-directional one-to-one association to Payment
	@OneToOne(mappedBy="user")
	private Payment payment;

	

	//bi-directional many-to-many association to Restriction
	@ManyToMany
	@JoinTable(
		name="Diet"
		, joinColumns={
			@JoinColumn(name="userName")
			}
		, inverseJoinColumns={
			@JoinColumn(name="restriction")
			}
		)
	private List<Restriction> restrictions;

	//bi-directional one-to-one association to Person
	@OneToOne
	@JoinColumn(name="userName", insertable = false, updatable = false)
	private Person person;

	public User() {
	}

	public User(String userName, List<Cuisine> cuisines, List<Order> orders,
			Payment payment, List<Restriction> restrictions, Person person) {
		super();
		this.userName = userName;
		this.cuisines = cuisines;
		this.orders = orders;
		this.payment = payment;
		this.restrictions = restrictions;
		this.person = person;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Cuisine> getCuisines() {
		return this.cuisines;
	}

	public void setCuisines(List<Cuisine> cuisines) {
		this.cuisines = cuisines;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Restriction> getRestrictions() {
		return this.restrictions;
	}

	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = restrictions;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
