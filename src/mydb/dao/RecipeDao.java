package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RecipeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public RecipeDao(){
		em = factory.createEntityManager();
	}
	public Recipe findPersonById(int id){
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

	}

}
