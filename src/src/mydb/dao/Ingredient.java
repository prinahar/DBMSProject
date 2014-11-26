package mydb.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Ingredient database table.
 * 
 */
@Entity
@NamedQuery(name="Ingredient.findAll", query="SELECT i FROM Ingredient i")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String ingredientName;

	//bi-directional many-to-many association to Recipe
	@ManyToMany
	@JoinTable(
		name="RecipeIngredient"
		, joinColumns={
			@JoinColumn(name="ingredientName")
			}
		, inverseJoinColumns={
			@JoinColumn(name="recipeId")
			}
		)
	private List<Recipe> recipes;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="type")
	private Type typeBean;

	public Ingredient() {
	}

	public Ingredient(String ingredientName, Type type)
   {
      this.ingredientName = ingredientName;
      this.typeBean = type;
   }

   public String getIngredientName() {
		return this.ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public List<Recipe> getRecipes() {
		return this.recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Type getTypeBean() {
		return this.typeBean;
	}

	public void setTypeBean(Type typeBean) {
		this.typeBean = typeBean;
	}

}