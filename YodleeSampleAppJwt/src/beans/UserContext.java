/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package beans;

public class UserContext {

	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public class User{
	private Session session;
	
	
	public class Session
	{
		private String userSession;

		public String getUserSession() {
			return userSession;
		}

		public void setUserSession(String userSession) {
			this.userSession = userSession;
		}
		
		
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}
	
	}
	
}
