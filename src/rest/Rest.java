package rest;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import mydb.dao.Cuisine;
import mydb.dao.CuisineDao;
import mydb.dao.Person;
import mydb.dao.PersonDao;
import mydb.dao.Restriction;
import mydb.dao.RestrictionDao;
import mydb.dao.User;
import mydb.dao.UserDao;

@Path("/test")
public class Rest {

	UserDao udao = new UserDao();
	PersonDao pdao = new PersonDao();
	RestrictionDao rdao = new RestrictionDao();
	CuisineDao cdao = new CuisineDao();
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	public User get(@PathParam("id") String id){
		User u = udao.findUser(id);
		return u;
	}
	
	@GET
	@Path("/get/cuisines")
	@Produces("application/json")
	public List<Cuisine> get(){
		List<Cuisine> lc = cdao.findAllCuisines();
		
		return lc;
	}
	
	
	
	@Path("/createUser")
	@POST
	@Consumes("application/json")
	public String createUser(User u){
		Person p = u.getPerson();
		pdao.addPerson(p);
//		System.out.println(p.getFirstName());
//		System.out.println(p.getDtype());
//		System.out.println(p.getLastName());
//		System.out.println(p.getPassword());
//		System.out.println(p.getUserName());
		List<Restriction> dummyRestrictionList = u.getRestrictions();
		List<Restriction> realRestrictionList = new LinkedList<Restriction>();
		if(dummyRestrictionList != null){
			for(Restriction iter: dummyRestrictionList){
				Restriction realRestriction = rdao.findRestrictionByName(iter.getRestriction());
				realRestrictionList.add(realRestriction);
			}
		}
		
	
		List<Cuisine> dummyCuisines = u.getCuisines();
		List<Cuisine> realCuisines = new LinkedList<Cuisine>();
		if(dummyCuisines != null) {
			for(Cuisine iter: dummyCuisines){
				Cuisine realCuisine = cdao.findCuisineByName(iter.getCuisineName());
				realCuisines.add(realCuisine);
			}
		}
		
		
		udao.addUser(p, realCuisines, realRestrictionList);
		return u.getUserName();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}