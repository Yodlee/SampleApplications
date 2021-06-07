/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package beans;

public class ProviderAccount {
	private String id;
	private RefreshInfo refreshInfo;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RefreshInfo getRefreshInfo() {
		return refreshInfo;
	}
	public void setRefreshInfo(RefreshInfo refreshInfo) {
		this.refreshInfo = refreshInfo;
	}
	

	public String toString()
	{
		StringBuilder refreshStatus = new StringBuilder("");
		refreshStatus.append("providerAccountId = " + id).append(refreshInfo.toString());
		return refreshStatus.toString();
	}
}
