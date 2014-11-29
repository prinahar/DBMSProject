package mydb.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String type;

	//bi-directional many-to-one association to Ingredient
	@OneToMany(mappedBy="typeBean")
	private List<Ingredient> ingredients;

	//bi-directional many-to-many association to Restriction
	@ManyToMany
	@JoinTable(
		name="RestrictedType"
		, joinColumns={
			@JoinColumn(name="type")
			}
		, inverseJoinColumns={
			@JoinColumn(name="restriction")
			}
		)
	private List<Restriction> restrictions;

	public Type() {
	}
	
	public Type(String type){
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Ingredient addIngredient(Ingredient ingredient) {
		getIngredients().add(ingredient);
		ingredient.setTypeBean(this);

		return ingredient;
	}

	public Ingredient removeIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);
		ingredient.setTypeBean(null);

		return ingredient;
	}

	public List<Restriction> getRestrictions() {
		return this.restrictions;
	}

	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = restrictions;
	}

}