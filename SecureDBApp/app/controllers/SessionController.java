/**
 * 
 */
package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author DeepakShankar
 *
 */
public class SessionController extends Controller {
	
	public Result getUserSession(){
		JsonNode json = Json.toJson(CallbackController.model);
		return ok(json);
	}

}
