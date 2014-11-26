package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RestrictionDao
{
   EntityManagerFactory factory = Persistence
   .createEntityManagerFactory("mydb");
   EntityManager em = null;
   public RestrictionDao()
   {
      em = factory.createEntityManager();
   }
   public void createRestriction(String strRestriction)
   {
      em.getTransaction().begin();
      Restriction restriction = new Restriction(strRestriction);
      em.persist(restriction);
      em.getTransaction().commit();
   }
   public Restriction findRestrictionByName(String restrictionName)
   {
      em.getTransaction().begin();
      Query q = em
            .createQuery("Select r from Restriction r where r.restrictionName = : restriction");
      q.setParameter("restriction", restrictionName);
      Restriction restriction = (Restriction) q.getSingleResult();
      em.getTransaction().commit();
      return restriction;
   }
   public void updateRestriction(Restriction restriction, String restrictionName)
   {
      em.getTransaction().begin();
      restriction.setRestriction(restrictionName);
      em.merge(restriction);
      em.getTransaction().commit();
   }
   public void deleteRestriction(String restrictionName)
   {
      em.getTransaction().begin();
      Restriction restriction = findRestrictionByName(restrictionName);
      em.remove(restriction);
      em.getTransaction().commit();
   }
   public static void main(String[] args)
   {

   }

}
