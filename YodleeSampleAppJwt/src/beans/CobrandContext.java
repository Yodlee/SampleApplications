/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package beans;


public class CobrandContext 
{
	
	private String cobrandId;
	private String applicationId;
	private Session session;
	
	
	public class Session
	{
		private String cobSession;

		public String getCobSession() {
			return cobSession;
		}

		public void setCobSession(String cobSession) {
			this.cobSession = cobSession;
		}
		
		
	}
	
	public Session getSession() {
		return session;
	}



	public void setSession(Session session) {
		this.session = session;
	}



	public void setCobrandId(String cobrandId) {
		this.cobrandId = cobrandId;
	}



	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}



	public String getCobrandId() 
	{
		return cobrandId;
	}


	
	public String getApplicationId() {
		return applicationId;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder(this.cobrandId+this.applicationId);
		return new String(sb);
	}

}
