/**
 * 
 */
package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oauth.commons.OAuth;
import com.oauth.commons.OAuthAccessor;
import com.oauth.commons.OAuthConsumer;
import com.oauth.commons.OAuthException;
import com.oauth.commons.OAuthMessage;
import com.oauth.commons.ParameterStyle;
import com.oauth.provider.server.HttpRequestMessage;

import http.SecureDBHttpRequest;
import http.SecureDBHttpResponse;
import http.SecureDBServletContext;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Deepak Shankar
 *
 */
public class LoginController extends Controller {

	private static final String NAME = "SecureDB";
	private HttpServletRequest httpRequest = null;// = new SecureDBHttpRequest(request(), null, null);
	private HttpServletResponse httpResponse = null;// new SecureDBHttpResponse(response());
	private ServletContext servletContext = null;
	
	public Result login(){
		
		OAuthConsumer consumer = null;
		try {
			httpRequest = new SecureDBHttpRequest(request(), null, null);
			 httpResponse = new SecureDBHttpResponse(response());
			servletContext = new SecureDBServletContext();
			consumer = SecureDBConsumer.getConsumer(NAME, servletContext);
			
			OAuthAccessor accessor = SecureDBConsumer.getAccessor(httpRequest, httpResponse, consumer);
			Collection<OAuth.Parameter> parameters = HttpRequestMessage.getParameters(httpRequest);


			String messageBody = invoke(accessor, parameters);
			String username = httpRequest.getParameter("username");
			System.out.println(username);
			/*request.setAttribute("access", accessor.accessToken);
			request.setAttribute("secret", accessor.tokenSecret);
			request.setAttribute("user", username);
			request.getRequestDispatcher("/welcomeUser.jsp").forward(httpRequest, response);*/
		} catch (Exception e) {
			/*try {
				//CookieConsumer.handleException(e, httpRequest, httpResponse, consumer);
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			if (e instanceof RedirectException) {
				RedirectException redirect = (RedirectException) e;
				String targetURL = redirect.getTargetURL();
				return redirect(targetURL);
				
			}
		}
		
		return ok();
	}
	
	private String invoke(OAuthAccessor accessor, Collection<? extends Map.Entry> parameters)
			throws OAuthException, IOException, URISyntaxException {
		URL baseURL = (URL) accessor.consumer.getProperty("serviceProvider.baseURL");
		if (baseURL == null) {
			baseURL = new URL("http://localhost:9090/oauth-provider/");
		}
		System.out.println("called invoke...");
		OAuthMessage request = accessor.newRequestMessage("POST", (new URL(baseURL, "echo")).toExternalForm(),
				parameters);
		System.out.println("POST to "+baseURL);
		OAuthMessage response = SecureDBConsumer.CLIENT.invoke(request, ParameterStyle.AUTHORIZATION_HEADER);
		String responseBody = response.readBodyAsString();
		System.out.println("================RESPONSE BODY=================");
		System.out.println(responseBody);
		return responseBody;
	}

	private static final long serialVersionUID = 1L;
	
	
}
