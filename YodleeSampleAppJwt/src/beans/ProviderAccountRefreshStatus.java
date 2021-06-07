/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package beans;

public class ProviderAccountRefreshStatus {
	ProviderAccount[]  providerAccount;
	
	public ProviderAccount[] getProviderAccount() {
		return providerAccount;
	}

	public void setProviderAccount(ProviderAccount[] providerAccount) {
		this.providerAccount = providerAccount;
	}

	
	
	public int getProviderAccountLenght(){
        return (providerAccount.length);
    }
	
	
}
