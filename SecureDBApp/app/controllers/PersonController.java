package controllers;

import static play.libs.Json.toJson;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import models.Employee;
import models.Employer;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class PersonController extends Controller {

	public Result index() {
		return ok(views.html.index.render());
	}

	public Result addPerson() {
		Employee employee = null;
		Employer employer = new Employer();
		employer.setName("TCS");
		employer.setNoOfEmployees(10000);
		employer.setAddress("Bangalore, KA, India");
		employer.save();
		if (employee == null)
			employee = new Employee();
		employee.setFirstName("Deepak");
		employee.setLastName("Shankar");
		employee.setDateOfBirth(new Date(117, 10, 20));
		employee.setEmployer(employer);
		employee.save();
		return ok(Json.toJson(employee));
	}

	public Result getPersons() {
		List<Employee> employees = Employee.find.all();
		return ok(toJson(employees));
	}

}
