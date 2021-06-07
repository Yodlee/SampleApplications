/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package parser;

import java.io.IOException;

import beans.UserContext;

import com.google.gson.Gson;

public class UserContextParser implements Parser 
{
	private String fqcn = this.getClass().getName();
	public UserContext parseJSON(String json) throws IOException 
	{
		Gson gson = new Gson();
		return (UserContext)gson.fromJson(json, UserContext.class);
	}

}
