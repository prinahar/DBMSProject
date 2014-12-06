package mydb.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RecipeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public RecipeDao(){
		em = factory.createEntityManager();
	}
	public Recipe findRecipeById(int id){
		em.getTransaction().begin();
		Recipe r = em.find(Recipe.class, id);
		em.getTransaction().commit();
		return r;
	}
	public void addRecipe(Recipe r){
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}
	
	//Input: name of recipe(description)
	//Effect: add a recipe into database
	//Description: the picture, steps, parent, recipes, ingredients are now set to null. 
	// The weeklyRecipes should be added automatically when addWeeklyRecipe is called 
	//  in WeeklyRecipeDao. The recipes field in cuisine. This update ingredients is 
	//  called after this function
	public Recipe addRecipe(String description, List<Ingredient> ingredients, Cuisine cuisine) {
		em.getTransaction().begin();
		Recipe r = new Recipe(description, null, null, ingredients, cuisine, null, null, null);
		em.persist(r);
		em.getTransaction().commit();
		return r;
	}
	
	public Recipe findRecipeByName(String description){
		 em.getTransaction().begin();
	     Query q = em.createQuery("Select r from Recipe r where r.description = :description");
	     q.setParameter("description", description);
	     Recipe r = (Recipe) q.getSingleResult();
	     em.getTransaction().commit();
	     return r;
	}
	public void updateIngredients(Recipe r, List<Ingredient> ingredients){
		em.getTransaction().begin();
		r.setIngredients(ingredients);
		em.merge(r);
		em.getTransaction().commit();
	}
	
	public void updateCuisine(Recipe r, Cuisine c){
		em.getTransaction().begin();
		r.setCuisine(c);
		em.merge(r);
		em.getTransaction().commit();
	}
	public void deletePerson(Recipe r){
		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Recipe r = new Recipe(1,"good steak", null, "12345", null, null, null, null, null);
//		IngredientDao idao = new IngredientDao();
//		Ingredient i1 = idao.findIngredient("beef");
//		Ingredient i2 = idao.findIngredient("chicken");
//		List<Ingredient> il = new LinkedList<Ingredient>();
//		il.add(i1);
//		
//		CuisineDao cdao = new CuisineDao();
//		Cuisine c = cdao.findCuisineByName("Chiniese");
//		
//		RecipeDao rdao = new RecipeDao();
//		Recipe r = rdao.findRecipeByName("fishRose");
//		rdao.updateIngredients(r, il);
		//rdao.updateIngredients(r, il);
		
		//System.out.println(r.getIngredients().get(0).getRecipes().get(0).getDescription());
	}

}
