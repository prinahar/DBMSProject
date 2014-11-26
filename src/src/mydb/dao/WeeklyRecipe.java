package mydb.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the WeeklyRecipes database table.
 * 
 */
@Entity
@Table(name="WeeklyRecipes")
@NamedQuery(name="WeeklyRecipe.findAll", query="SELECT w FROM WeeklyRecipe w")
public class WeeklyRecipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int weeklyRecipeId;

	@Temporal(TemporalType.DATE)
	private Date week;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="weeklyRecipe")
	private List<Order> orders;

	//bi-directional many-to-one association to Recipe
	@ManyToOne
	@JoinColumn(name="recipeId")
	private Recipe recipe;

	public WeeklyRecipe() {
	}

	public int getWeeklyRecipeId() {
		return this.weeklyRecipeId;
	}

	public void setWeeklyRecipeId(int weeklyRecipeId) {
		this.weeklyRecipeId = weeklyRecipeId;
	}

	public Date getWeek() {
		return this.week;
	}

	public void setWeek(Date week) {
		this.week = week;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setWeeklyRecipe(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setWeeklyRecipe(null);

		return order;
	}

	public Recipe getRecipe() {
		return this.recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}