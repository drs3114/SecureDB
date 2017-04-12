/**
 * 
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author DeepakShankar
 *
 */
public class LogoutController extends Controller {

	public Result logout(String user, String token){
		String savedtoken = CallbackController.loggedInUsers.get(user);
		
		if(savedtoken.equals(token.toString())){
			CallbackController.loggedInUsers.remove(user);
			System.out.println("logged out "+user);
		}
		return redirect("http://localhost:9495/SecureDBUI/");
	}
	
}
