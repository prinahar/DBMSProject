package mydb.dao;


import java.util.List;

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
   //Input: cuisine name
   //Effect: add a cuisine into the cuisine table.
   //Description: cuisine has many-to-many relation with user. When adding cuisine,
   // its user field set to null. When adding user or modifying preference, user need
   // to add or delete cuisines field through cuisines that are already in database.
   // The user field of cuisine will then be automatically updated.
   public Cuisine createCuisine(String cuisineName) {
      em.getTransaction().begin();
      Cuisine cuisine = new Cuisine(cuisineName);
      em.persist(cuisine);
      em.getTransaction().commit();
      return cuisine;
   }
   
   
   public Cuisine findCuisineByName(String cuisineName) {
      em.getTransaction().begin();
      Query q = em.createQuery("Select c from Cuisine c where c.cuisineName = :cuisineName");
      q.setParameter("cuisineName", cuisineName);
      Cuisine cuisine = (Cuisine) q.getSingleResult();
      em.getTransaction().commit();
      return cuisine;
   }
   public List<Cuisine> findAllCuisines() {
	      em.getTransaction().begin();
	      Query q = em.createQuery("Select c from Cuisine c");
	      List<Cuisine> lc = (List<Cuisine>) q.getResultList();
	      em.getTransaction().commit();
	      return lc;
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
	   CuisineDao cdao = new CuisineDao();
	   cdao.createCuisine("Chiniese");
	   cdao.createCuisine("India");
	   cdao.createCuisine("American");
   }

}
