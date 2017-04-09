/**
 * 
 */
package controllers;

import java.util.Map;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Deepak Shankar
 *
 */
public class AppController extends Controller {

	public Result index() {
		Map<String, String[]> headers = request().headers();
		for (Map.Entry<String, String[]> entry : headers.entrySet()) {
			System.out.print(entry.getKey()+" : ");
			for (String val : entry.getValue()) {
				System.out.print(val+" ");
			}
			System.out.println();
			//Form.Field = new 
		}
		return ok(views.html.index.render());
	}

}
