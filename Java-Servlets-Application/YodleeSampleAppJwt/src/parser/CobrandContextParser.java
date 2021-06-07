/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package parser;

import java.io.IOException;

import com.google.gson.Gson;

import beans.CobrandContext;

public class CobrandContextParser implements Parser
{
	private String fqcn = this.getClass().getName();
	
	public CobrandContext parseJSON(String json) throws IOException 
	{
		
		Gson gson = new Gson();
		return (CobrandContext)gson.fromJson(json, CobrandContext.class);
	}
	
}
