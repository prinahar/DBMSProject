package mydb.dao;

import java.util.LinkedList;
import java.util.List;

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
	
	//Input: person, List of Cuisines, List of restrictions that already in database.
	//Effect: add a user into database. orders field will be set to null when user created.
	// the user field of Person, the users field of Cuisine and Restriction should be 
	// set automatically by JPA
	// this function is called with addPayment in PaymentDao, the payment field of User
	// should be automatically set when addPayment is called.
	// order of the user is added in orderDao
	public void addUser(Person p, List<Cuisine> c, List<Restriction> r){
		em.getTransaction().begin();
		User u = new User(p.getUserName(), c, null, null, r, p);
		em.persist(u);
		em.getTransaction().commit();
		
	}
	public User findUser(String username){
		em.getTransaction().begin();
		User u = em.find(User.class, username);
		em.persist(u);
		em.getTransaction().commit();
		return u;
	}
	
	//Input: user and list of cuisines are already in database
	//Effect: update cuisines of that user
	public void updateCuisine(User u, List<Cuisine> lc){
		em.getTransaction().begin();
		u.setCuisines(lc);
		em.merge(u);
		em.getTransaction().commit();
		
	}
	public void updateRestriction(User u, List<Restriction> lr) {
		em.getTransaction().begin();
		u.setRestrictions(lr);
		em.merge(u);
		em.getTransaction().commit();
	}

	public void deleteUser(User u){
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PersonDao pdao = new PersonDao();
		//Person p = new Person("alice", "user", "alice", "wonderland", "123321", null, null);
		//Person p2 = new Person("bob2", "user", "bob2", "bob2", "123321", null, null);
		//Person p = pdao.findPersonByName("alice");
		//Person p2 = pdao.findPersonByName("bob2");		
		//pdao.addPerson(p);
		//pdao.addPerson(p2);
		
//		List<Cuisine> lc = new LinkedList<Cuisine>();
//		CuisineDao cdao = new CuisineDao();
//		Cuisine c1 = cdao.findCuisineByName("Chiniese");
//		Cuisine c2 = cdao.findCuisineByName("India");
//		lc.add(c1);
//		lc.add(c2);

//		List<Cuisine> lc2 = new LinkedList<Cuisine>();
//		lc2.add(c2);
		
		
//		UserDao udao = new UserDao();
//		User alice = udao.findUser("alice");
//		
//		RestrictionDao rdao = new RestrictionDao();
//		Restriction r1 = rdao.findRestrictionByName("MeatEater");
//		Restriction r2 = rdao.findRestrictionByName("noMeet");
//		List<Restriction> lr = new LinkedList<Restriction>();
//		lr.add(r1);
//		lr.add(r2);
//		udao.updateRestriction(alice, lr);
//		System.out.println(r1.getUsers().get(0).getUserName());
		
//		udao.updateCuisine(alice, lc2);
		
		//udao.addUser(p, lc, null);
		//udao.addUser(p2, lc2, null);
		//User alice = udao.findUserByName("alice");
		//System.out.println("test alice is hello "+alice.getPerson().getUser().toString());
		
//		for(User u: c2.getUsers())
//		  System.out.println("" + u.getUserName());
		
//		Person p = u.getPerson();
//		System.out.println(u.getUserName());
//		System.out.println(p.getFirstName());
	}

}
