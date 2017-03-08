/**
 * 
 */
package controllers;

import java.util.List;

import models.Employee;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Deepak Shankar
 *
 */
public class EmployeeController extends Controller {

	
	public Result addEmployee() {
		Employee employee = Json.fromJson(request().body().asJson(), Employee.class);
		employee.save();
		return ok(Json.toJson(employee));
	}

	
	public Result getAllEmployees() {
		List<Employee> employees = Employee.find.all();
		return ok(Json.toJson(employees));
	}

}
