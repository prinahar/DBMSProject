package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class IngredientDao
{
   EntityManagerFactory factory = Persistence
         .createEntityManagerFactory("mydb");
   EntityManager em = null;

   public IngredientDao() {
      em = factory.createEntityManager();
   }
   public void createIngredientByType(String ingredientName, String strType) {
      em.getTransaction().begin();
      Type type = new Type(strType);
      Ingredient ingredient = new Ingredient(ingredientName, type);
      em.persist(ingredient);
      em.getTransaction().commit();
   }
   public Ingredient findIngredientByName(String ingredientName){
      em.getTransaction().begin();
      Query q = em.createQuery("select i from Ingredient i where i.ingredientName = :ingredientName");
      Ingredient ingredient = (Ingredient) q.getSingleResult();
      em.getTransaction().commit();
      return ingredient;
   }
   public void updateIngredientByName (Ingredient ingredient, String ingredientName) {
      em.getTransaction().begin();
      ingredient.setIngredientName(ingredientName);
      em.merge(ingredient);
      em.getTransaction().commit();
   }
   public void updateIngredientByType (Ingredient ingredient, String strType) {
      em.getTransaction().begin();
      Type type = new Type(strType);
      ingredient.setTypeBean(type);
      em.merge(ingredient);
      em.getTransaction().commit();
   }
   public void deleteIngredient(String ingredientName) {
      em.getTransaction().begin();
      Ingredient ingredient=  findIngredientByName(ingredientName);
      em.remove(ingredient);
      em.getTransaction().commit();
   }
}