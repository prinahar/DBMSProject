package mydb.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OrderDao
{
   EntityManagerFactory factory = Persistence
         .createEntityManagerFactory("mydb");
   EntityManager em = null;

   public OrderDao()
   {
      em = factory.createEntityManager();
   }
   public void createOrder(Order order) {
      em.getTransaction().begin();
      em.persist(order);
      em.getTransaction().commit();
   }
   
   public Order findOrder(int weeklyRecipeId, String userName) {
      em.getTransaction().begin();
      OrderPK id = new OrderPK (weeklyRecipeId, userName);
      Query q = em.createQuery("select o from Order o where o.id= :id");
      q.setParameter("id", id);
      Order order = (Order) q.getSingleResult();
      em.getTransaction().commit();
      return order;
   }
   public void updateOrder(Order order, Date deliveryTime) {
      em.getTransaction().begin();
      order.setDeliveryTime(deliveryTime);
      em.merge(order);
      em.getTransaction().commit();
   }
   public void deleteOrder(int weeklyRecipeId, String userName) {
      em.getTransaction().begin();
      Order order = findOrder(weeklyRecipeId, userName);
      em.remove(order);
      em.getTransaction().commit();
   }

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }

}
