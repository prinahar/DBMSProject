package mydb.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CuisineDao
{
   EntityManagerFactory factory = Persistence
         .createEntityManagerFactory("mydb");
   EntityManager em = null;

   public CuisineDao()
   {
      em = factory.createEntityManager();
   }
   public void createCuisine(String cuisineName) {
      em.getTransaction().begin();
      Cuisine cuisine = new Cuisine(cuisineName);
      em.persist(cuisine);
      em.getTransaction().commit();
   }
   
   
   public Cuisine findCuisineByName(String cuisineName) {
      em.getTransaction().begin();
      Query q = em.createQuery("Select c from Cuisine c where c.cuisineName = : cuisineName");
      q.setParameter("cuisineName", cuisineName);
      Cuisine cuisine = (Cuisine) q.getSingleResult();
      em.getTransaction().commit();
      return cuisine;
   }
   public void updateCuisine(Cuisine cuisine, String cuisineName) {
      em.getTransaction().begin();
      cuisine.setCuisineName(cuisineName);
      em.merge(cuisine);
      em.getTransaction().commit();
   }
   public void deleteCuisine(String cuisineName) {
      em.getTransaction().begin();
      Cuisine cuisine = findCuisineByName(cuisineName);
      em.remove(cuisine);
      em.getTransaction().commit();
   }

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }

}
