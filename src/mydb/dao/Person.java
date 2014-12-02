package mydb.dao;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * The persistent class for the Person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;

	private String dtype;
	


	public Person(String userName, String dtype, String firstName,
			String lastName, String password, Chef chef, User user) {
		super();
		this.userName = userName;
		this.dtype = dtype;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.chef = chef;
		this.user = user;
	}

	private String firstName;

	private String lastName;

	private String password;

	//bi-directional one-to-one association to Chef
	@OneToOne(mappedBy="person")
	@JsonIgnore
	private Chef chef;

	//bi-directional one-to-one association to User
	@OneToOne(mappedBy="person")
	@JsonIgnore
	private User user;

	public Person() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDtype() {
		return this.dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Chef getChef() {
		return this.chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}