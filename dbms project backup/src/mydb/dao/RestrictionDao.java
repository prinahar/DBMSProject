package mydb.dao;



import java.util.LinkedList;
import java.util.List;

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



   public void createRestriction(String strRestriction, List<Type> lc)

   {

      em.getTransaction().begin();

      Restriction restriction = new Restriction(strRestriction, lc);

      em.persist(restriction);

      em.getTransaction().commit();

   }



   public Restriction findRestrictionByName(String restrictionName)

   {

      em.getTransaction().begin();

      Query q = em

            .createQuery("Select r from Restriction r where r.restriction = :restriction");

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
//	   TypeDao tdao = new TypeDao();
//	   Type t1 = tdao.findType("meet");
//	   Type t2 = tdao.findType("vegi");
//	   List<Type> lt = new LinkedList<Type>();
//	   lt.add(t1);
//	   lt.add(t2);
//	   RestrictionDao rd = new RestrictionDao();
//	   rd.createRestriction("noMeet", lt);
//	   rd.createRestriction("MeatEater", lt);
//	   for(Restriction r: t1.getRestrictions())
//		   System.out.println(r.getRestriction());


   }



}