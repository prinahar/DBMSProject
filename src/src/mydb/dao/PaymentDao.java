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
	public void addPayment(Payment p){
		em.getTransaction().begin();
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

	}

}
