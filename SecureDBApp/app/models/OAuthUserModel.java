/**
 * 
 */
package models;

/**
 * @author DeepakShankar
 *
 */
public class OAuthUserModel {

	private String consumer;
	private String returnTo;
	private String oauth_token;
	private String username;
	private String userRole;

	public OAuthUserModel(String consumer, String returnTo, String oauth_token, String username, String userRole) {
		super();
		this.consumer = consumer;
		this.returnTo = returnTo;
		this.oauth_token = oauth_token;
		this.username = username;
		this.userRole = userRole;
	}

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	public String getReturnTo() {
		return returnTo;
	}

	public void setReturnTo(String returnTo) {
		this.returnTo = returnTo;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
