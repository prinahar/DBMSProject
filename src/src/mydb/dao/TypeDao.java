package mydb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TypeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public TypeDao(){
		em = factory.createEntityManager();
	}
	public void AddType(Type t){
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Type t = new Type("meet");
		TypeDao tdao = new TypeDao();
		//tdao.AddType(t);
	}

}
