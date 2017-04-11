/**
 * 
 */
package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import models.Department;
import models.Dependent;
import models.Employee;
import models.Employer;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Deepak Shankar
 *
 */
public class EmployeeController extends Controller {

	public Result addEmployee() {
		System.out.println(request().body().asJson());
		String eFirstName = request().body().asJson().get("fname").textValue();
		String eLastName = request().body().asJson().get("lname").textValue();
		String email = request().body().asJson().get("email").textValue();
		String deptstr = request().body().asJson().get("department").textValue();

		Department dept = Department.find.where().ilike("name", deptstr ).findUnique();
		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setFirstName(eFirstName);
		employee.setLastName(eLastName);
		Employer employer = Employer.find.where().eq("ID", 1).findUnique();
		employee.setEmployer(employer);
		employee.save();
		return ok(Json.toJson(employee));
		
	}
	
	public Result addDependent(){
		System.out.println("ID: "+ request().body().asJson().get("id").textValue());
		System.out.println("Name: "+ request().body().asJson().get("name").textValue());
		System.out.println("Dependent To: "+ request().body().asJson().get("dependentTo").textValue());
		System.out.println("Relationship: "+ request().body().asJson().get("relationship").textValue());
		Dependent dependent = new Dependent();
		dependent.setName(request().body().asJson().get("name").textValue());
		dependent.setRelationship(request().body().asJson().get("relationship").textValue());
		
		return ok();
	}

	public Result getAllEmployees() {
		List<Employee> employees = Employee.find.all();
		return ok(Json.toJson(employees));
	}

	public Result getAllDependents(int eid) {
		List<Dependent> dependents = Dependent.find.where().eq("DEPENDENT_TO_ID", eid).findList();
		return ok(Json.toJson(dependents));
	}

}
