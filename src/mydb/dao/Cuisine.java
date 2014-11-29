package mydb.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Cuisines database table.
 * 
 */
@Entity
@Table(name="Cuisines")
@NamedQuery(name="Cuisine.findAll", query="SELECT c FROM Cuisine c")
public class Cuisine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cuisineName;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="Preference"
		, joinColumns={
			@JoinColumn(name="cuisineName")
			}
		, inverseJoinColumns={
			@JoinColumn(name="userName")
			}
		)
	private List<User> users;

	//bi-directional many-to-one association to Recipe
	@OneToMany(mappedBy="cuisine")
	private List<Recipe> recipes;

	public Cuisine() {
	}

	
	public Cuisine(String cuisineName) {
		super();
		this.cuisineName = cuisineName;
	}


	public String getCuisineName() {
		return this.cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Recipe> getRecipes() {
		return this.recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Recipe addRecipe(Recipe recipe) {
		getRecipes().add(recipe);
		recipe.setCuisine(this);

		return recipe;
	}

	public Recipe removeRecipe(Recipe recipe) {
		getRecipes().remove(recipe);
		recipe.setCuisine(null);

		return recipe;
	}

}