* Overview

This is simple web application (Servlet, HTML/CSS/JS) to demonstrate use of Yodlee Aggregation APIs.
The primary use cases and APIs used are

1. Link Account - using FastLink (how to launch FastLink in iframe) - YodleeSampleApp.java - getFastLinkToken
2. Get list of user's linked accounts to show account summary information - YodleeSampleApp.java - getUserAccounts
3. Get list of transactions for specific selected account - YodleeSampleApp.java - getTransactions

* Pre-requisites for running Sample Application

For JWT authentication privatekey and APIKey is required to generate the token which can get it from yodlee API dashborad page.
 
Sample application is built with minimal dependencies. Required for running sample application are

1. Tomcat 7 Application Server
2. Java 7
3. Eclipse or Similar IDE - optional, but recommended for understanding use of Yodlee APIs

* Third Party Dependencies for Sample Application

1. JQuery - in HTML files (index.html, accounts.html)
2. Bootstrap - in HTML files (index.html, accounts.html)s

(*These are dependencies for demo sample application, and not for use of Yodlee APIs)

Installation instructions to build war and deploy:-

1)Eclipse ->right click on project-->Export-->War file generation.
2)Install tomcat 7.0 or above and run monitor tomcat.
3)By default role is not defined to access tomcat manager, Add the below role in tomcat-users.xml:
 path for tomcat-users.xml ->C:\Program Files\Apache Software Foundation\Tomcat 7.0\conf
    <tomcat-users>
    <role rolename="manager-gui"/>
    <user username="admin" password="admin" roles="manager-gui"/>
   </tomcat-users>

3)Access the Tomcat Web Application Manager with the given port.
   Ex:localhost:8090
4)click on Manager APP, enter credentials admin/admin to authenticate.
5)Under Deploy section, upload the war file and file will be listed under applications.
  click on application to launch yodleeSampleApp.
  
Running YodleeSampleApp inside eclipse:

1)Download apache tomcat  > V7 (zip file)
2)Following below instruction to configure tomcat
https://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.stardust.docs.wst%2Fhtml%2Fwst-integration%2Fconfiguration.html 
3)Right click on project folder (yodleeSampleApp) Run as tomcat server
  
  


* Sample App Structure 

Sample App is designed to be minimal and simple to read. Primary objective of this application is to demonstrate use of Yodlee APIs
Sample App is structured as standard Web Application Structure (expanded WAR file)

YodleeSampleApp
	- src
		- YodleeSampleApp.java - Java Servlet interacting with Yodlee API. Yodlee API call usage are inside this source file.
		- config.properties - resource file to configure Yodlee API URL,API version,API key,Private Key and Node Url.
		- privateKey.txt  --configure the privatekey.
		                     
		- beans - supporting POJO objects
		- parser - supporting JSON parsers
		- util
			- HTTP.java - helper methods to make HTTP calls.
			- JWT.java -helper method to generate token.
	- WebContent
		- index.html - initializes with Cobrand Login check.
		- accounts.html - invoking fastlink,lists user accounts, details and transactions.
	- pom.xml
	- README.txt
	

	
