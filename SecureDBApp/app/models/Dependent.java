/**
 * 
 */
package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.avaje.ebean.Model;

/**
 * @author deepak
 *
 */
@Entity
@Table(name = "DEPENDENT")
public class Dependent extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DEPNDENT_TO")
	@OneToOne(cascade=CascadeType.ALL)
	private Employee dependentTo;

	@Column(name = "RELATIONSHIP")
	private String relationship;

	public static Finder<Long, Employee> find = new Finder<>(Employee.class);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getDependentTo() {
		return dependentTo;
	}

	public void setDependentTo(Employee dependentTo) {
		this.dependentTo = dependentTo;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}
