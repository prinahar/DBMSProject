package mydb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TypeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mydb");
	EntityManager em = null;
	
	public TypeDao(){
		em = factory.createEntityManager();
	}
	public Type addType(String typeName){
		em.getTransaction().begin();
		Type t = new Type(typeName);
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}
	public Type findType(String typeName) {
		em.getTransaction().begin();
		Type t = em.find(Type.class, typeName);
		em.getTransaction().commit();
		return t;
	}
	public List<Type> findAllTypes() {
		em.getTransaction().begin();
		Query q = em.createQuery("Select i from Type i");
		List<Type> ll = (List<Type>) q.getResultList();
		em.getTransaction().commit();
		return ll;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Type t = new Type("meet");
		//TypeDao tdao = new TypeDao();
		//tdao.AddType(t);
	}

}
