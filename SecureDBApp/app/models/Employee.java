/**
 * 
 */
package models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name="USERNAME")
	private String username;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "DOB")
	private Date dateOfBirth;

	@Column(name = "WORKS_FOR")
	@OneToMany(cascade = CascadeType.ALL)
	private Employer worksFor;

	public static Finder<Long, Employee> find = new Finder<>(Employee.class);

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the worksFor
	 */
	public Employer getWorksFor() {
		return worksFor;
	}

	/**
	 * @param worksFor the worksFor to set
	 */
	public void setWorksFor(Employer worksFor) {
		this.worksFor = worksFor;
	}

	public Employee() {
		super();
	}
	
	public static Employee copy(Employee employee){
		Employee newEmployee = new Employee();
		newEmployee.setDateOfBirth(employee.getDateOfBirth());
		newEmployee.setEmail(employee.getEmail());
		newEmployee.setFirstName(employee.getFirstName());
		newEmployee.setId(employee.getId());
		newEmployee.setLastName(employee.getLastName());
		newEmployee.setUsername(employee.getUsername());
		newEmployee.setWorksFor(employee.getWorksFor());
		return newEmployee;
	}

}
