package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PaymentDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public PaymentDao(){
		em = factory.createEntityManager();
	}
	
	public Payment findPaymentByName(String username){
		em.getTransaction().begin();
		Payment p = em.find(Payment.class, username);
		em.getTransaction().commit();
		return p;
	}
	
	//Input: User already in database, Credit Card Number and Address
	//Effect: add a payment into database. payment field of User should be set automatically by JPA
	public void addPayment(User u, String cc, String ad){
		em.getTransaction().begin();
		Payment p = new Payment(u.getUserName(), ad, cc, u);
		em.persist(p);
		em.getTransaction().commit();
	}
	public void updateAddress(Payment p, String adr){
		em.getTransaction().begin();
		p.setAddress(adr);
		em.merge(p);
		em.getTransaction().commit();
	}
	public void updateCreditCard(Payment p, String cc){
		em.getTransaction().begin();
		p.setCreditCard(cc);
		em.merge(p);
		em.getTransaction().commit();
	}
	
	public void deletePayment(Payment p){
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserDao udao = new UserDao();
//		User u = udao.findUserByName("alice");
//		PaymentDao pdao = new PaymentDao();
//		pdao.addPayment(u, "123321", "boston");
//		System.out.print(b);
//		Payment p = pdao.findPaymentByName("alice");
//		System.out.println(p.getAddress());
//		System.out.println(p.getUser().getUserName());
	}

}
