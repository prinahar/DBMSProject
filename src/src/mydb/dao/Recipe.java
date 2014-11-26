package mydb.dao;

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

	@Lob
	private byte[] picture;

	@Lob
	private String steps;

	//bi-directional many-to-many association to Ingredient
	@ManyToMany(mappedBy="recipes")
	private List<Ingredient> ingredients;

	//bi-directional many-to-one association to Cuisine
	@ManyToOne
	@JoinColumn(name="cuisine")
	private Cuisine cuisine;

	//bi-directional many-to-one association to Recipe
	@ManyToOne
	@JoinColumn(name="parentId")
	private Recipe recipe;

	//bi-directional many-to-one association to Recipe
	@OneToMany(mappedBy="recipe")
	private List<Recipe> recipes;

	//bi-directional many-to-one association to WeeklyRecipe
	@OneToMany(mappedBy="recipe")
	private List<WeeklyRecipe> weeklyRecipes;

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

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Cuisine getCuisine() {
		return this.cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Recipe getRecipe() {
		return this.recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public List<Recipe> getRecipes() {
		return this.recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Recipe addRecipe(Recipe recipe) {
		getRecipes().add(recipe);
		recipe.setRecipe(this);

		return recipe;
	}

	public Recipe removeRecipe(Recipe recipe) {
		getRecipes().remove(recipe);
		recipe.setRecipe(null);

		return recipe;
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

}