package tables;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Vdao {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("foodrec");
	private EntityManager em = null;
	public Vdao(){
		em = factory.createEntityManager();
	}
	public void addv(Vehicle v){
		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vdao vdao = new Vdao();
		Vehicle v = new Vehicle();
		v.setName("honda");
		vdao.addv(v);
	}

}
