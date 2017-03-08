/**
 * 
 */
package models;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Model;

/**
 * @author DeepakShankar
 *
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String name;
	
	public static Finder<Long, Employee> find = new Finder<>(Employee.class);

	public Employee() {
		super();
	}

}
