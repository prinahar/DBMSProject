package mydb.dao;




import java.util.Date;




import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class OrderDao

{

   EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");

   EntityManager em = null;



   public OrderDao()

   {

      em = factory.createEntityManager();

   }

   //Input: WeeklyRecipe and User that are already in database
   //Effect: add a new order for that User and WeeklyRecipe
   //  the weeklyRecipe field will be set to given WeeklyRecipe
   //  the user will also be set to given user. The user and
   //  wr object's order field will be automatically set to this
   //  weeklyRecipe by JPA.
   //Attention: OrderPk is created automatically by JPA
   public Order createOrder(WeeklyRecipe wr, User u, Date deliveryTime, int quantity) {

      em.getTransaction().begin();
      Order order = new Order(false, null, false, deliveryTime, false, quantity, 0, wr, u);
      em.persist(order);
      em.getTransaction().commit();
      return order;
   }

   

   public Order findOrder(WeeklyRecipe wr, User u) {

      em.getTransaction().begin();

      OrderPK id = new OrderPK (wr.getWeeklyRecipeId(), u.getUserName());

      Order order = em.find(Order.class, id);

      em.getTransaction().commit();

      return order;

   }

   public List<Order> findOrderByUserAndWeek(User u, Date week) {
	   em.getTransaction().begin();
	   Query q = em.createQuery("Select o from Order o where o.user = :u and o.weeklyRecipe.week = :week");
	   q.setParameter("u", u);
	   q.setParameter("week", week);
	   List<Order> orders = q.getResultList();
	   em.getTransaction().commit();
	   return orders;
   }
   public void updateOrderState(Order order, boolean accepted, boolean confirmation, boolean orderComplete) {
	      em.getTransaction().begin();
	      order.setAccepted(accepted);
	      order.setConfirmation(confirmation);
	      order.setOrderComplete(orderComplete);
	      em.merge(order);
	      em.getTransaction().commit();

   }
   public void updateOrderComment(Order order, String comment, int rating) {

	      em.getTransaction().begin();

	      order.setComment(comment);
	      order.setRating(rating);

	      em.merge(order);

	      em.getTransaction().commit();

   }
   
   public void updateOrderDeliverTime(Order order, int quantity) {

	      em.getTransaction().begin();

	      order.setQuantity(quantity);;

	      em.merge(order);

	      em.getTransaction().commit();

	  }
   
   
   public void updateOrderDeliverTime(Order order, Date deliveryTime) {

      em.getTransaction().begin();

      order.setDeliveryTime(deliveryTime);

      em.merge(order);

      em.getTransaction().commit();

   }

   public void deleteOrder(int weeklyRecipeId, String userName) {

      em.getTransaction().begin();

      OrderPK id = new OrderPK (weeklyRecipeId, userName);

      Order order = em.find(Order.class, id);

      em.remove(order);

      em.getTransaction().commit();

   }



   public static void main(String[] args)

   {

      // TODO Auto-generated method stub
	   RecipeDao rdao = new RecipeDao();
	   Recipe r2 = rdao.findRecipeByName("bad steak");
	   
	   WeeklyRecipeDao wrdao = new WeeklyRecipeDao();
	   wrdao.createWeeklyRecipe(r2, new Date());
	   List<WeeklyRecipe> wr = wrdao.findWeeklyRecipeByDate(new Date());
	   
	   System.out.println(wr.get(0).getRecipe().getDescription());
	   System.out.println(wr.get(1).getRecipe().getDescription());
	   
	   UserDao udao = new UserDao();
	   User u = udao.findUserByName("alice");
	   
	   OrderDao odao = new OrderDao();
	   //Order order = odao.createOrder(wr.get(1), u, new Date(), 2);
	   //Order order = odao.findOrder(wr.get(0), u);
	   List<Order> orders = odao.findOrderByUserAndWeek(u, new Date());
	   odao.updateOrderState(orders.get(0), true, true, true);
	   for(Order iter: orders)
		   System.out.println(iter.getAccepted());
	   //System.out.println(order.getUser().getUserName()+" test user field of order");
	   //System.out.println(order.getWeeklyRecipe().getRecipe().getDescription()+ " test weeklyrecipe field of order");
//	   System.out.println(u.getOrders().get(0).getWeeklyRecipe().getRecipe().getDescription());
//	   System.out.println(order.getWeeklyRecipe().getOrders().get(0));

   }



}
