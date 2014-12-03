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
import mydb.dao.Payment;
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
	public User getUser(@PathParam("id") String id) {
		User u = udao.findUser(id);
		return u;
	}

	@GET
	@Path("/get/preference/{id}")
	@Produces("application/json")
	public List<Cuisine> getPreference(@PathParam("id") String id) {
		System.out.println("Getting user");
		User u = getUser(id);
		List<Cuisine> preference = u.getCuisines();
		System.out.println("Returning preferences");
		return preference;
	}

	@GET
	@Path("/get/payment/{id}")
	@Produces("application/json")
	public Payment getPaymentInfo(@PathParam("id") String id) {
		User u = getUser(id);
		Payment paymentInfo = u.getPayment();
		return paymentInfo;
	}

	@GET
	@Path("/get/preferences")
	@Produces("application/json")
	public List<Cuisine> getAllPreferences() {
		List<Cuisine> lc = cdao.findAllCuisines();
		return lc;
	}

	@Path("/createUser")
	@POST
	@Consumes("application/json")
	public String createUser(User u) {
		Person p = u.getPerson();
		pdao.addPerson(p);
		// System.out.println(p.getFirstName());
		// System.out.println(p.getDtype());
		// System.out.println(p.getLastName());
		// System.out.println(p.getPassword());
		// System.out.println(p.getUserName());
		List<Restriction> dummyRestrictionList = u.getRestrictions();
		List<Restriction> realRestrictionList = new LinkedList<Restriction>();
		if (dummyRestrictionList != null) {
			for (Restriction iter : dummyRestrictionList) {
				Restriction realRestriction = rdao.findRestrictionByName(iter
						.getRestriction());
				realRestrictionList.add(realRestriction);
			}
		}

		List<Cuisine> dummyCuisines = u.getCuisines();
		List<Cuisine> realCuisines = new LinkedList<Cuisine>();
		if (dummyCuisines != null) {
			for (Cuisine iter : dummyCuisines) {
				Cuisine realCuisine = cdao.findCuisineByName(iter
						.getCuisineName());
				realCuisines.add(realCuisine);
			}
		}

		udao.addUser(p, realCuisines, realRestrictionList);
		return u.getUserName();
	}

	public static void main(String[] args) {
//		Rest r = new Rest();
//		List<Cuisine> p = r.getPreference("UserAlice");
//		System.out.println(p);

	}

}
