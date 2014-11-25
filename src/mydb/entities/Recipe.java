package mydb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Recipe database table.
 * 
 */
@Entity
@NamedQuery(name="Recipe.findAll", query="SELECT r FROM Recipe r")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int recipeId;

	private String description;

	private int parientId;

	@Lob
	private byte[] picture;

	private String steps;

	//bi-directional many-to-one association to Cuisine
	@ManyToOne
	@JoinColumn(name="cuisine")
	private Cuisine cuisine;

	//bi-directional many-to-one association to Recipe
	@ManyToOne
	@JoinColumn(name="childId")
	private Recipe child;

	//bi-directional many-to-one association to Recipe
	@OneToMany(mappedBy="child")
	private List<Recipe> parients;

	//bi-directional many-to-one association to WeeklyRecipe
	@OneToMany(mappedBy="recipe")
	private List<WeeklyRecipe> weeklyRecipes;

	//bi-directional many-to-many association to Ingredient
	@ManyToMany(mappedBy="recipes")
	private List<Ingredient> ingredients;

	public Recipe() {
	}

	public int getRecipeId() {
		return this.recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParientId() {
		return this.parientId;
	}

	public void setParientId(int parientId) {
		this.parientId = parientId;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getSteps() {
		return this.steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Cuisine getCuisine() {
		return this.cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Recipe getChild() {
		return this.child;
	}

	public void setChild(Recipe child) {
		this.child = child;
	}

	public List<Recipe> getParients() {
		return this.parients;
	}

	public void setParients(List<Recipe> parients) {
		this.parients = parients;
	}

	public Recipe addParient(Recipe parient) {
		getParients().add(parient);
		parient.setChild(this);

		return parient;
	}

	public Recipe removeParient(Recipe parient) {
		getParients().remove(parient);
		parient.setChild(null);

		return parient;
	}

	public List<WeeklyRecipe> getWeeklyRecipes() {
		return this.weeklyRecipes;
	}

	public void setWeeklyRecipes(List<WeeklyRecipe> weeklyRecipes) {
		this.weeklyRecipes = weeklyRecipes;
	}

	public WeeklyRecipe addWeeklyRecipe(WeeklyRecipe weeklyRecipe) {
		getWeeklyRecipes().add(weeklyRecipe);
		weeklyRecipe.setRecipe(this);

		return weeklyRecipe;
	}

	public WeeklyRecipe removeWeeklyRecipe(WeeklyRecipe weeklyRecipe) {
		getWeeklyRecipes().remove(weeklyRecipe);
		weeklyRecipe.setRecipe(null);

		return weeklyRecipe;
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}