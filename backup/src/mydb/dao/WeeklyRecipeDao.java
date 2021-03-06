package mydb.dao;



import java.util.Date;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class WeeklyRecipeDao

{

   EntityManagerFactory factory = Persistence

         .createEntityManagerFactory("mydb");

   EntityManager em = null;



   public WeeklyRecipeDao()

   {

      em = factory.createEntityManager();

   }

   //Input: recipe already in database and a date
   //Description:this function is used when chef chooses weekly recipes from
   //  recipes table
   //Effect: the WeeklyRecipe's recipe field will be set to input recipe object
   //  WeeklyRecipe object should be added to recipe's WeeklyRecipes field 
   //  automatically
   public WeeklyRecipe createWeeklyRecipe(Recipe r, Date week) {
      em.getTransaction().begin();
      WeeklyRecipe rw = new WeeklyRecipe(week, null, r);
      em.persist(rw);
      em.getTransaction().commit();
      return rw;
   }

   public WeeklyRecipe findWeeklyRecipeById(int weeklyRecipeId){
      em.getTransaction().begin();
      WeeklyRecipe weeklyRecipe = em.find(WeeklyRecipe.class, weeklyRecipeId);
      em.getTransaction().commit();
      return weeklyRecipe;
   }
   public List<WeeklyRecipe> findWeeklyRecipeByDate(Date week) {
	   em.getTransaction().begin();
	   Query q = em.createQuery("select w from WeeklyRecipe w where w.week = :week");
	   q.setParameter("week", week);
	   List<WeeklyRecipe> lw = (List<WeeklyRecipe>) q.getResultList();
	   em.getTransaction().commit();
	   return lw;
   }
   public void updateWeeklyRecipeDate (WeeklyRecipe weeklyRecipe, Date week) {

      em.getTransaction().begin();

      weeklyRecipe.setWeek(week);

      em.merge(weeklyRecipe);

      em.getTransaction().commit();

   }

   public void deleteWeeklyRecipe(int weeklyRecipeId) {

      em.getTransaction().begin();

      WeeklyRecipe weeklyRecipe= em.find(WeeklyRecipe.class, weeklyRecipeId);

      em.remove(weeklyRecipe);

      em.getTransaction().commit();

   }



   public static void main(String[] args)

   {

      // TODO Auto-generated method stub
	   RecipeDao rdao = new RecipeDao();
//	   Recipe r2= rdao.findRecipeById(1);
	   Recipe r = rdao.findRecipeByName("fishRose");
//	   rdao.addRecipe(r);
	   WeeklyRecipeDao wrDao = new WeeklyRecipeDao();
	   //WeeklyRecipe wr = wrDao.createWeeklyRecipe(r, new Date());
	   List<WeeklyRecipe> wr = wrDao.findWeeklyRecipeByDate(new Date());
	   for(WeeklyRecipe iter: wr)
		   System.out.println(iter.getRecipe().getDescription());
	   //System.out.println(wr.getRecipe().getWeeklyRecipes().get(0).getWeek());

   }



}