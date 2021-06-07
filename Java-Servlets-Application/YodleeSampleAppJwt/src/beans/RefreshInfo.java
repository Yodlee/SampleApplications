/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package beans;

public class RefreshInfo {
	   private String refreshStatus;
	   
       public String getRefreshStatus() {
		return refreshStatus;
	}
	public void setRefreshStatus(String refreshStatus) {
		this.refreshStatus = refreshStatus;
	}

	private String statusCode;
       private String statusMessage;
       private String lastRefreshed;
       private String lastRefreshAttempt;
       private String nextRefreshScheduled;
       private String additionalStatus;
       
       private String status;
       public String getStatusCode() {
              return statusCode;
       }
       public void setStatusCode(String statusCode) {
              this.statusCode = statusCode;
       }
       public String getStatusMessage() {
              return statusMessage;
       }
       public void setStatusMessage(String statusMessage) {
              this.statusMessage = statusMessage;
       }
       public String getLastRefreshed() {
              return lastRefreshed;
       }
       public void setLastRefreshed(String lastRefreshed) {
              this.lastRefreshed = lastRefreshed;
       }
       public String getLastRefreshAttempt() {
              return lastRefreshAttempt;
       }
       public void setLastRefreshAttempt(String lastRefreshAttempt) {
              this.lastRefreshAttempt = lastRefreshAttempt;
       }
       public String getNextRefreshScheduled() {
              return nextRefreshScheduled;
       }
       public void setNextRefreshScheduled(String nextRefreshScheduled) {
              this.nextRefreshScheduled = nextRefreshScheduled;
       }
       
       
       public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    public String getAdditionalStatus() {
        return additionalStatus;
    }

    public void setAdditionalStatus(String additionalStatus) {
        this.additionalStatus = additionalStatus;
    }
}

