/**
 * 
 */
package controllers;

import java.util.List;

import models.Dependent;
import models.Employee;
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

		Employee employee = Json.fromJson(request().body().asJson(), Employee.class);
		employee.save();
		return ok(Json.toJson(employee));
	}
	
	public Result addDependent(int eid){
		System.out.println("ID: "+ request().body().asJson().get("id").textValue());
		System.out.println("Name: "+ request().body().asJson().get("name").textValue());
		System.out.println("Dependent To: "+ request().body().asJson().get("dependentTo").textValue());
		System.out.println("Relationship: "+ request().body().asJson().get("relationship").textValue());
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
