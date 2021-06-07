/*
 * Copyright (c) 2018 Yodlee, Inc. All Rights Reserved.
 */

package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * HTTP class handles multiple HTTP requests .These include: 'POST' 'GET' 'PUT'
 * 'DELETE'
 */

public class HTTP {
	private static final String FQCN = HTTP.class.getName();
	private static final String USERAGENT = "Mozilla/5.0";
	private static final String CONTENTTYPEJSON = "application/json";
	// Authorization headers
	private static final String[] HEADERS = { "Content-Type", "Cobrand-Name", "Api-Version" };
	/**
	 * To load the values from config.properties file
	 */
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	static String cobrandName = resourceBundle.getString("yodlee.cobrandName");
	static String apiVersion = resourceBundle.getString("yodlee.apiVersion");


	/**
	 * Requests data from a specified resource doGet() method is used to call
	 * getAccounts,getTransactions,getHoldings API.
	 * 
	 * @param url
	 *            : API URL.
	 * @param token
	 *            :token is required to authenticate and access yodlee API's.
	 * @return JsonObject in string format.
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */

	public static String doGet(String url, String sessionTokens)
			throws IOException, URISyntaxException, InterruptedException {

		URL myURL = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
		conn = setRequestProperty(conn, "GET", sessionTokens);
		
		conn.setDoOutput(true);		
		if (conn.getResponseCode()>210) {
			BufferedReader in= new BufferedReader(
					new InputStreamReader(conn.getErrorStream()));

			String inputLine;
			StringBuilder jsonResponse = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				jsonResponse.append(inputLine);
			}
			in.close();

		}
		
		try {
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			return new String(JsonResponse(in));
		} catch (IOException ex) {
			ex.printStackTrace();

		}

		finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	

	/**
	 * Deletes the specified resource doDelete() method is used to call
	 * DeleteProviderAccount,DeleteCategory API etc.
	 * 
	 * @param url
	 *            : API URL.
	 * @param token
	 *            ::token is required to authenticate and access yodlee API
	 *           
	 * @return empty body.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String doDelete(String url, String sessionTokens)
			throws IOException, URISyntaxException {
		
		
		URL myURL = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
		conn = setRequestProperty(conn, "DELETE", sessionTokens.toString());
		StringBuilder jsonResponse = new StringBuilder();
		
		
		conn.setDoOutput(true);		
		
		
		if (conn.getResponseCode()>210) {
			BufferedReader in= new BufferedReader(
					new InputStreamReader(conn.getErrorStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				jsonResponse.append(inputLine);
			}
			in.close();

		}
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				jsonResponse.append(inputLine);
			}
	       
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return new String(jsonResponse);
	}
	
	
	
	
	/**
	 * Helper method to set the request property for HttpURLConnection object.
	 * Setting the Cobrand-Name and Api-Verison wich are custom headers for Yodlee 1.1 API's.
	 * 
	 * @param conn
	 *            :-HttpURLConnection object.
	 * @param requestType
	 *            :- 'POST', 'PUT','GET' and 'DELETE'
	 * @param token
	 *            :-To authenticate and access yodlee API's.
	 *            
	 * @return HttpURLConnection object with request properties.
	 */

	public static HttpURLConnection setRequestProperty(HttpURLConnection conn, String requestType,
			String token) {
		try {
			conn.setRequestMethod(requestType);
		} catch (ProtocolException ex) {

			System.out.print(ex);
		}
		

		conn.setRequestProperty("User-Agent", USERAGENT);
		conn.setRequestProperty("Content-Type", CONTENTTYPEJSON);
		if (token != null) {
			conn.setRequestProperty("Authorization", token.toString());
		}
		conn.setRequestProperty("Cobrand-Name", cobrandName);
		conn.setRequestProperty("Api-Version", apiVersion);
		conn.setDoOutput(true);

		return conn;
	}

	

	/**
	 * Helper method to handle API response
	 * 
	 * @param in
	 *            BufferedReader object
	 * @return JsonResponse
	 */
	public static StringBuilder JsonResponse(BufferedReader in) {
		String inputLine;
		StringBuilder jsonResponse = new StringBuilder();
		try {
			while ((inputLine = in.readLine()) != null) {
				jsonResponse.append(inputLine);
			}
			
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}
		try {
			in.close();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return jsonResponse;
	}

}
