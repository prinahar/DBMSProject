package mydb.dao;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the Restriction database table.
 * 
 */
@Entity
@NamedQuery(name="Restriction.findAll", query="SELECT r FROM Restriction r")
public class Restriction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String restriction;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="Diet"
		, joinColumns={
			@JoinColumn(name="restriction")
			}
		, inverseJoinColumns={
			@JoinColumn(name="userName")
			}
		)
	@JsonIgnore
	private List<User> users;

	//bi-directional many-to-many association to Type
	@JoinTable(
			name="RestrictedType"
			, joinColumns={
				@JoinColumn(name="restriction")
				}
			, inverseJoinColumns={
				@JoinColumn(name="type")
				}
			)
	@JsonIgnore
	private List<Type> types;

	public Restriction() {
	}
	

	public Restriction(String restriction, List<Type> types) {
		super();
		this.restriction = restriction;
		this.types = types;
	}


	public String getRestriction() {
		return this.restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Type> getTypes() {
		return this.types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

}