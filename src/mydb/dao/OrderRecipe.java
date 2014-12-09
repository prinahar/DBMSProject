package mydb.dao;

public class OrderRecipe {
	public Order order;
	public String recipeName;
	
	public OrderRecipe(Order o, String rname){
		this.order = o;
		this.recipeName = rname;
	}
}
