package mydb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Chef database table.
 * 
 */
@Entity
@NamedQuery(name="Chef.findAll", query="SELECT c FROM Chef c")
public class Chef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;

	//bi-directional one-to-one association to Person
	@OneToOne
	@JoinColumn(name="userName",insertable = false, updatable = false)
	private Person person;
	
	public Chef() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}