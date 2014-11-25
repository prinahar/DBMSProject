
package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mydb.entities.Type;

public class TypeDao {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	private EntityManager em = null;
	
	public TypeDao(){
		em = factory.createEntityManager();
	}
	
	public Type addType(Type t){
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TypeDao tdao = new TypeDao();
		Type t = new Type();
		t.setType("meet");
		tdao.addType(t);
	}

}
