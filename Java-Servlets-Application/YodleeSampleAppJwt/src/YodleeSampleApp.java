
/*
 * Copyright (c) 2018 Yodlee, Inc. All Rights Reserved.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parser.GSONParser;
import util.HTTP;
import util.JWT;
import beans.AccessToken;
import beans.CobrandContext;
import beans.UserContext;
import util.JWT;

/**
 * YodleeSampleApp is a HTTPServlet that demonstrates a simple sample finapp
 * which allows the user to login and link financial accounts and see a list of
 * added accounts, account details and transactions. To link an account we
 * demonstrate the use of Yodlee Fastlink application that simplifies the
 * process into an iFrame/rSession post workflow.
 * 
 * Below are the list of API's demonstrated by YodleeSamleAPP. 
 * 1)GetAccounts  -This method uses the /accounts API to retieve existing accounts
 * 2)GetTransactions - This method uses /transactions API to get a list of
 * transactions for a user. 
 * 3)DeletAccount -This method uses /accounts API to delete an account.
 */
@WebServlet("/YodleeSampleApp")
public class YodleeSampleApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * To load the values from config.properties file required to authenticate
	 * and run the yodleSampleAPP.
	 */
	public static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	public static final String APIURL = resourceBundle.getString("yodlee.APIURL");
	public static final String APIKEY = resourceBundle.getString("yodlee.apiKey");
	public static final String NODEURL = resourceBundle.getString("yodlee.nodeUrl");
   
	public YodleeSampleApp() {
		super();
	}

	/**
	 * doGet() handles the 'GET' request sent from the client side ( Ajax call).
	 * YodleeSampleApp (client) is using ajax to make call to the API's.
	 * 
	 * @param request
	 *            : Request sent from client side.
	 * @param response
	 *            : response to the client.
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bearer=null;

		String action = (String) request.getParameter("action");

		if (action != null) {
			
			

				bearer= (String) request.getSession().getAttribute("Bearer");
				

				if (bearer != null && bearer.length() > 0) {

					// Get list of users accounts. Uses GET /accounts API
					if (action.equals("getAccounts")) {

						String accountsJson = getUserAccounts(bearer);
						sendAjaxResponse(response, accountsJson);
					}

					
					
					// Get list of user transactions for specific account.Uses
					// GET /transactions API
					if (action.equals("getTransactions")) {
						String accountId = (String) request.getParameter("accountId");
						String transactionsJson = getTransactions(bearer,accountId);
						sendAjaxResponse(response, transactionsJson);
					}
					
				

					// Deactivates users specific account. Uses DELETE
					// /accounts/<account id> API
					if (action.equals("deleteAccount")) {
						String accountId = (String) request.getParameter("accountId");
						String deleteAccountResponse = deleteAccount(bearer, accountId);
						sendAjaxResponse(response, deleteAccountResponse);
					}
					
				

					// Obtain NODE url and token to launch FastLink.
					if (action.equals("getFastLinkToken")) {
						String allDataSet = "";
						String tokens = "{\"jwtToken\":\"" + bearer +  "\",\"nodeUrl\":\"" + NODEURL + "\",\"dataset\":\"" + allDataSet + "\"}";
						sendAjaxResponse(response, tokens);
					}
                

		} else {
			sendAjaxResponse(response, "{'error':'true', 'message':'Missing Action parameter'}");
		}
				
				
		}

	}

	/**
	 * sendAjaxResponse() will format the returning response to client with
	 * contentType and character encoding.
	 * 
	 * @param response
	 * @param responseString
	 * @throws IOException
	 */

	private void sendAjaxResponse(HttpServletResponse response, String responseString) throws IOException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseString);

	}

	/**
	 * To Handle POST request from login screen form (index.html)
	 * 
	 * @param request
	 *            : Request sent from client side.
	 * @param response
	 *            : response to the client.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String token=null;

		String name = (String) request.getParameter("username");
		String key = APIKEY;
	
		JWT jwt = new JWT();
		
		String privateKey =jwt.readPrivKey("privateKey.txt");

		String bearer=null;
		
		
		int validity=600;

		try {
			// Calling JWT util to generate the token
			
			 token=JWT.getJWTToken(name, key, privateKey,validity);
			 
			 request.getSession().setAttribute("Bearer", "Bearer "+token);
			 
			 bearer="Bearer "+token;
			 
			 System.out.println("Generated Token " + bearer);
			 
			

		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{'error':'true', 'message':'Error in user Login, Invalid user/key details to generate the token.'}");
		}

		if (token != null) {
			
			String accountsJson = getUserAccounts(bearer);
          
			if(accountsJson!=null) {
			
			sendAjaxResponse(response, "{'error':'false', 'message':'User authentication successfull'}");
			
			}


		 else {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{'error':'true', 'message':'Error in user Login, Invalid user/key details to generate the token.'}");
		}
		}

	}

	
	/**
	 * Helper method to load user accounts from Yodlee APIs API url : GET
	 * /accounts call Refer to full API references -
	 * https://developer.yodlee.com/apidocs/index.php *
	 * 
	 * @param token
	 *            -required for authentication to access yodlee
	 *            API's.
	 * @return getAccounts API Json response in string format
	 */

	private String getUserAccounts(String token) {
		String accountURL = APIURL + "accounts";

		String accountJsonResponse = null;

		try {
			accountJsonResponse = HTTP.doGet(accountURL, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountJsonResponse;
	}

	/**
	 * Helper method to get transactions for specific account (id) API url : GET
	 * /transactions Refer to full API references -
	 * https://developer.yodlee.com/apidocs/index.php *
	 * 
	 * @param token-
	 *            required for  authentication to access yodlee
	 *            API's.
	 * @param accountId
	 *            AccountId of the user added account which is returned from
	 *            getAccounts API.
	 * @return transaction API response in string format.
	 */

	private String getTransactions(String token, String accountId) {

		String txnJson = "";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Date todate1 = cal.getTime();
		String date = dateFormat.format(todate1);

		String TransactionUrl = APIURL + "transactions" + "?fromDate=" + date + "+&accountId=" + accountId;

		try {
			
			txnJson = HTTP.doGet(TransactionUrl, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnJson;
	}
	

	/**
	 * Helper method to delete(unlink) specific user account API url : DELETE
	 * /accounts/<accountId> Refer to full API references -
	 * https://developer.yodlee.com/apidocs/index.php
	 * 
	 * @param token
	 *            -required  authentication to access yodlee
	 *            API's.
	 * @param accountId
	 *            AccountId of the user added account which is returned from
	 *            getAccounts API.
	 * @return success message for the account being deleted.
	 */

   private String deleteAccount(String token, String accountId) {
		String deleteAccountResponse = null;

		String deleteAccountURL = APIURL + "accounts/";
		try {

			HTTP.doDelete(deleteAccountURL + accountId, token);

			deleteAccountResponse = "success";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleteAccountResponse;
	}
	
	
	
	
	
	

}
