package rest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import mydb.dao.Chef;
import mydb.dao.ChefDao;
import mydb.dao.Cuisine;
import mydb.dao.CuisineDao;
import mydb.dao.Ingredient;
import mydb.dao.IngredientDao;
import mydb.dao.Payment;
import mydb.dao.Person;
import mydb.dao.PersonDao;
import mydb.dao.Recipe;
import mydb.dao.RecipeDao;
import mydb.dao.Restriction;
import mydb.dao.RestrictionDao;
import mydb.dao.TypeDao;
import mydb.dao.User;
import mydb.dao.UserDao;
import mydb.dao.WeeklyRecipe;
import mydb.dao.WeeklyRecipeDao;
import mydb.dao.Type;

@Path("")
public class Rest {

	UserDao udao = new UserDao();
	PersonDao pdao = new PersonDao();
	RestrictionDao rdao = new RestrictionDao();
	CuisineDao cdao = new CuisineDao();
	WeeklyRecipeDao wrdao = new WeeklyRecipeDao();
	IngredientDao idao = new IngredientDao();
	ChefDao chefDao = new ChefDao();
	TypeDao tdao = new TypeDao();
	RecipeDao recipeDao = new RecipeDao();
	
	@GET
	@Path("/getAllUsers")
	@Produces("application/json")
	public List<User> getAllUsers() {
		List<User> lu = udao.findAllUsers();
		return lu;
		
	}

	
	@GET
	@Path("/login")
	@Produces("application/json")
	public String login(@QueryParam("id") String id){
		System.out.println(id);
		String[] info = id.split("&");
//		System.out.println(info.length);
		String username = info[0];
		String pass = info[1];
		Person p = pdao.findPersonByName(username);
		System.out.println(p.getFirstName());
		if(p.getPassword().equals(pass))
			return "true";
		return "false";
	}

	@GET
	@Path("/getUser/{id}")
	@Produces("application/json")
	public User getUser(@PathParam("id") String id) {
		User u = udao.findUser(id);
		return u;
	}

	@GET
	@Path("/getPayment/{id}")
	@Produces("application/json")
	public Payment getPayment(@PathParam("id") String id) {
		User u = getUser(id);
		Payment paymentInfo = u.getPayment();
		return paymentInfo;
	}

	@GET
	@Path("/getPreferences")
	@Produces("application/json")
	public List<Cuisine> getAllPreferences() {
		List<Cuisine> lc = cdao.findAllCuisines();
		return lc;
	}

	@GET
	@Path("/getRestrictions")
	@Produces("application/json")
	public List<Restriction> getAllRestrictions() {
		List<Restriction> lr = rdao.findAllRestrictions();
		return lr;
	}

	@GET
	@Path("/getPreference/{id}")
	@Produces("application/json")
	public List<Cuisine> getPreference(@PathParam("id") String id) {
		User u = getUser(id);
		List<Cuisine> preference = u.getCuisines();
		return preference;
	}

	@GET
	@Path("/getAllIngredients")
	@Produces("application/json")
	public List<Ingredient> getAllIngredients() {
		List<Ingredient> ll = idao.findAllIngredients();
		return ll;
	}
	@GET
	@Path("/getRecipe/{id}")
	@Produces("application/json")
	public Recipe getRecipe(@PathParam ("id") int id) {
		Recipe r = recipeDao.findRecipeById(id);
		return r;
	}
	@GET
	@Path("/getAllTypes")
	@Produces("application/json")
	public List<Type> getAllTypes() {
		List<Type> lt = tdao.findAllTypes();
		return lt;
	}
	
	@Path("/addIngredient")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public List<Ingredient> addIngredient(Ingredient i) {
		Type t = tdao.findType(i.getTypeBean().getType());
		idao.createIngredientByType(i.getIngredientName(), t);
		List<Ingredient> li = idao.findAllIngredients();
		return li;
	}
	
	@Path("/addRecipe")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public int addRecipe(Recipe r) {
		System.out.println("executed");
		System.out.println(r.getIngredients());
		List<Ingredient> realIngredients = new LinkedList<Ingredient>();
		for(Ingredient iter: r.getIngredients()){
			System.out.println(iter.getIngredientName());
			System.out.println(iter);
			Ingredient i = idao.findIngredient(iter.getIngredientName());
			
			realIngredients.add(i);
			System.out.println(realIngredients);
		}
		Cuisine c = cdao.findCuisineByName(r.getCuisine().getCuisineName());
		int id = recipeDao.addRecipe(r.getDescription(), realIngredients, c).getRecipeId();
		return id;
	}
	
	@GET
	@Path("/isChef")
	@Produces("application/json")
	public String isChef(@PathParam("date") String username) {
		ChefDao cdao = new ChefDao();
		Chef c = cdao.findChefByName(username);
		if(c == null){
			return "false";
		}
		return "true";
	}
	
	@GET
	@Path("/getWeeklyRecipes")
	@Produces("application/json")
	public List<WeeklyRecipe> getWeeklyRecipe() {
		Date date = new Date();
		List<WeeklyRecipe> lr = wrdao.findWeeklyRecipeByDate(date);
		return lr;
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
		//if(u.getRestrictions() == null)
			System.out.println(u.getRestrictions().get(0).getRestriction());
		udao.addUser(p, realCuisines, realRestrictionList);
		return u.getUserName();
		
		//TODO: Chef finds Orders for the given week
	}
	
	public static void main(String[] args) {
//		Rest rest = new Rest();
//		IngredientDao idao = new IngredientDao();
//		RecipeDao rdao = new RecipeDao();
//		List<Ingredient> li = new ArrayList();
//		li.add( idao.findIngredient("chicken"));
//		li.add(idao.findIngredient("butter"));
//		CuisineDao cdao = new CuisineDao();
//		Cuisine c = cdao.findCuisineByName("Chinese");
//		Recipe r = new Recipe(null, null, null, li ,c , null, null , null);
//		rest.addRecipe(r);
//		System.out.println(r);
		

		
	}

}
