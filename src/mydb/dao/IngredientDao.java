package mydb.dao;







import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class IngredientDao

{

   EntityManagerFactory factory = Persistence

         .createEntityManagerFactory("mydb");

   EntityManager em = null;



   public IngredientDao()

   {

      em = factory.createEntityManager();

   }

   //Input: the type that is already in database and ingredient name
   // Effect: add an Ingredient into the database
   // Description: the ingredients field of type will be set automatically by JPA
   // the recipes field will be automatically set when addRecipe function in RecipeDao
   // is called.
   public void createIngredientByType(String ingredientName, Type type) {
      em.getTransaction().begin();
      Ingredient ingredient = new Ingredient(ingredientName, type);
      em.persist(ingredient);
      em.getTransaction().commit();
   }

   public Ingredient findIngredient(String ingredientName){

      em.getTransaction().begin();

      Ingredient ingredient = em.find(Ingredient.class, ingredientName);

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

      Ingredient ingredient= em.find(Ingredient.class, ingredientName);

      em.remove(ingredient);

      em.getTransaction().commit();

   }

   public List<Ingredient> findAllIngredients() {
		em.getTransaction().begin();
		Query q = em.createQuery("Select i from Ingredient i");
		List<Ingredient> ll = (List<Ingredient>) q.getResultList();
		em.getTransaction().commit();
		return ll;
	}


   public static void main(String[] args)

   {

      // TODO Auto-generated method stub
	   TypeDao tdao = new TypeDao();
	   Type t1 = tdao.findType("meet");
//	   Type t1 = tdao.AddType("meet");
//	   tdao.AddType("vegi");
//	   tdao.AddType("liqued");
//	   IngredientDao idao = new IngredientDao();
//	   idao.createIngredientByType("chicken", t1);
//	   idao.createIngredientByType("beef", t1);
//	   idao.createIngredientByType("fish", t1);
	   for(Ingredient i: t1.getIngredients())
		   System.out.println(i.getIngredientName());
	   

   }



}