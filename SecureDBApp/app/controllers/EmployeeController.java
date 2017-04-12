/**
 * 
 */
package controllers;

import java.util.ArrayList;
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
		String eUName = request().body().asJson().get("uname").textValue();
		String email = request().body().asJson().get("email").textValue();
		String deptstr = request().body().asJson().get("department").textValue();
		String urs = request().body().asJson().get("user").textValue();
		String tkn = request().body().asJson().get("tkn").textValue();
		String savedtoken = CallbackController.loggedInUsers.get(urs);
		System.out.println("Saved token = " + savedtoken);

		if (!savedtoken.equals(tkn.toString())) {
			return status(UNAUTHORIZED, tkn);
		}
		List<Department> allDepts = Department.find.all();
		List<Employer> allEmplr = Employer.find.all();
		Employer employer = null;
		Department dept = null;
		for (Department department : allDepts) {
			if (department.getName().equalsIgnoreCase(deptstr)) {
				dept = department;
				break;
			}
		}

		for (Department department : allDepts) {
			if (department.getName().equalsIgnoreCase(deptstr)) {
				dept = department;
				break;
			}
		}
		for (Employer emplr_ : allEmplr) {
			if (emplr_.getId() == 1) {
				employer = emplr_;
				break;
			}
		}

		for (Department department : allDepts) {
			System.out.println(department.getName());
		}
		for (Employer emplr_ : allEmplr) {
			System.out.println(emplr_.getName());
		}

		if (employer == null) {
			employer = new Employer();
			employer.setName(urs);
			employer.setNoOfEmployees(15000);
			employer.setAddress("Bangalore, KA, India");
			employer.save();
		}
		if (dept == null) {
			dept = new Department();
			dept.setEmployer(employer);
			dept.setName(deptstr);
			dept.setEmployees(new ArrayList<Employee>());
			dept.save();
		}
		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setFirstName(eFirstName);
		employee.setLastName(eLastName);
		employee.setUsername(eUName);
		employee.setWorksFor(employer);
		dept.getEmployees().add(employee);
		dept.update();
		employee.save();
		System.out.println(employer.getName() + " added employee " + employee.getFirstName());

		return ok();

	}

	public Result addDependent() {
		String name = request().body().asJson().get("dependentName").textValue();
		String user = request().body().asJson().get("user").textValue();
		String tkn = request().body().asJson().get("tkn").textValue();
		String relnship = request().body().asJson().get("relationship").textValue();// relationship
		String savedtoken = CallbackController.loggedInUsers.get(user);
		System.out.println(request().body().asJson());
		if (!savedtoken.equals(tkn.toString())) {
			System.out.println("UNAUTHORIZED");
			return status(UNAUTHORIZED, tkn);
		}
		Employee employee = null;
		List<Employee> employees = Employee.find.all();
		for (Employee empl : employees) {
			if (empl.getUsername().equals(user.toString())) {
				employee = empl;
				break;
			}
		}
		Dependent dependent = null;
		if (employee != null) {
			dependent = new Dependent();
			dependent.setName(name);
			dependent.setRelationship(relnship);
			dependent.setDependentTo(employee);
			dependent.save();
		}
		System.out.println(employee.getUsername() + " added dependent " + dependent.getName());
		return ok();
	}

	public Result getAllEmployees(String user, String token) {
		String savedtoken = CallbackController.loggedInUsers.get(user);
		if (!savedtoken.equals(token.toString())) {
			return status(UNAUTHORIZED, token);
		}
		List<Employee> employees = Employee.find.all();

		return ok(Json.toJson(employees));
	}

	public Result getAllDependents(String user, String token) {
		String savedtoken = CallbackController.loggedInUsers.get(user);
		if (!savedtoken.equals(token.toString())) {
			return status(UNAUTHORIZED, token);
		}
		
		List<Dependent> dependents = Dependent.find.all();
		List<Dependent> validDependents = new ArrayList<>();
		for (Dependent dependent : dependents) {
			if(dependent.getDependentTo().getUsername().equals(user.toString())){
				validDependents.add(Dependent.copy(dependent));
			}
		}
		
		return ok(Json.toJson(validDependents));
	}

}
