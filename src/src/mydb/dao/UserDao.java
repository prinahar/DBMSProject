package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public UserDao(){
		em = factory.createEntityManager();
	}
	public User findUserByName(String username){
		em.getTransaction().begin();
		User u = em.find(User.class, username);
		em.getTransaction().commit();
		return u;
	}
	public void addUser(User u){
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	public void deleteUser(User u){
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
