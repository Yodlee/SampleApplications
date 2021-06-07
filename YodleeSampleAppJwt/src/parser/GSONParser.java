/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package parser;

import java.io.IOException;


public class GSONParser 
{
	private static final String fqcn = GSONParser.class.getName();
	
	public static <T> Object handleJson(String json, Class<?> T) throws IOException
	{
		
		return ParserFactory.getParser(T).parseJSON(json);
	}
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
