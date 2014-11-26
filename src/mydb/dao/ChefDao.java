package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ChefDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public ChefDao(){
		em = factory.createEntityManager();
	}
	public Chef findChefByName(String username){
		em.getTransaction().begin();
		Chef c = em.find(Chef.class, username);
		em.getTransaction().commit();
		return c;
	}
	public void addChef(Chef c){
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}
	public void deleteChef(Chef c){
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChefDao cdao = new ChefDao();
		Chef c = new Chef()
	}

}
