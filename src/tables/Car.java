package tables;

import java.io.Serializable;
import javax.persistence.*;
import tables.Vehicle;

/**
 * Entity implementation class for Entity: Car
 *
 */
@Entity
public class Car extends Vehicle implements Serializable {

	
	private int numOfWheels;
	private static final long serialVersionUID = 1L;

	public Car() {
		super();
	}   
	public int getNumOfWheels() {
		return this.numOfWheels;
	}

	public void setNumOfWheels(int numOfWheels) {
		this.numOfWheels = numOfWheels;
	}
   
}
