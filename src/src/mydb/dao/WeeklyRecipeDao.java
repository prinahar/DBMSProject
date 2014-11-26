package mydb.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class WeeklyRecipeDao
{
   EntityManagerFactory factory = Persistence
         .createEntityManagerFactory("mydb");
   EntityManager em = null;

   public WeeklyRecipeDao()
   {
      em = factory.createEntityManager();
   }
   public void createWeeklyRecipe(Recipe recipe) {
      em.getTransaction().begin();
      WeeklyRecipe wr = new WeeklyRecipe();
      wr.setRecipe(recipe);
      em.persist(wr);
      em.getTransaction().commit();
   }
   public WeeklyRecipe findWeeklyRecipeById(int weeklyRecipeId){
      em.getTransaction().begin();
      WeeklyRecipe weeklyRecipe = em.find(WeeklyRecipe.class, weeklyRecipeId);
      em.getTransaction().commit();
      return weeklyRecipe;
   }
   public void updateWeeklyRecipe (WeeklyRecipe weeklyRecipe, Date week) {
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

   public static void main(String[] args) {

      // TODO Auto-generated method stub

   }

}