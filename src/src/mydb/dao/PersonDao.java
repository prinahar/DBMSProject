package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public PersonDao(){
		em = factory.createEntityManager();
	}
	public Person findPersonByName(String username){
		em.getTransaction().begin();
		Person p = em.find(Person.class, username);
		em.getTransaction().commit();
		return p;
	}
	public void addPerson(Person p){
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	public void updatePersonPassword(Person p, String password){
		em.getTransaction().begin();
		p.setPassword(password);
		em.merge(p);
		em.getTransaction().commit();
	}
	public void deletePerson(Person p){
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Person p = new Person("alice","user","alice","wonderland","123",null, null);
		PersonDao pdao = new PersonDao();
		Person p = pdao.findPersonByName("alice");
		pdao.updatePersonPassword(p, "321");
	}

}
