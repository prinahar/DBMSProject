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
	//Input: person that already in database
	//Effect: crate a Chef based on the Person, the Person field of Chef and Chef 
	//  field of Person will be set automatically.
	public void addChef(Person p){
		em.getTransaction().begin();
		Chef c = new Chef(p.getUserName(), null);
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
//		ChefDao cdao = new ChefDao();
//		PersonDao pdao = new PersonDao();
//		Person p = pdao.findPersonByName("Charly");
//		cdao.addChef(p);
		
//		Chef c = cdao.findChefByName("Charly");
//		Person p = c.getPerson();
//		System.out.println(p.getChef().getUserName());
	}

}
