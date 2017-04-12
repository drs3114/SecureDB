/**
 * 
 */
package controllers;

import java.util.HashMap;
import java.util.Map;

import models.OAuthUserModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author DeepakShankar
 *
 */
public class CallbackController extends Controller {
	public static final String PATH = "/Callback";
	public static String token = "";
	public static Map<String, String> loggedInUsers = new HashMap<>();

	public Result oauthCallbck(String consumer, String returnTo, String oauth_token, String username, String userRole) {
		if (consumer == null || consumer == "" && oauth_token == null || oauth_token == "" && username == null
				|| username == "" && userRole == "" || userRole == null) {
			System.out.println("params not set");
			return redirect("http://localhost:9495/SecureDBUI/unauthorized.html");
		}
		if (oauth_token.equals(token)) {
			loggedInUsers.put(username, oauth_token);
			if (userRole.equals("ROLE_ADMIN")) {
				return redirect("http://localhost:9495/SecureDBUI/employer.html?"+"username="+username+"&role="+userRole+"&token="+oauth_token);
			}
			return redirect("http://localhost:9495/SecureDBUI/employee.html?"+"username="+username+"&role="+userRole+"&token="+oauth_token);
		} else {
			System.out.println("bad token was: "+oauth_token);
			return redirect("http://localhost:9495/SecureDBUI/unauthorized.html");
		}
	}

}
