This is simple web application (Servlet, HTML/CSS/JS) to demonstrate use of Yodlee Aggregation APIs.
The primary use cases and APIs used are

1. Link Account - using FastLink (how to launch FastLink in iframe) - YodleeSampleApp.java - getFastLinkToken
2. Get list of user's linked accounts to show account summary information - YodleeSampleApp.java - getUserAccounts
3. Get list of transactions for specific selected account - YodleeSampleApp.java - getTransactions

* Pre-requisites for running Sample Application

For JWT authentication private key and APIKey is required to generate the token which can get it from yodlee API dashborad page.

Sample App is designed to be minimal and simple to read. Primary objective of this application is to demonstrate use of Yodlee APIs

YodleeSampleApp

	jwt- Library used for token creation.
	Web
		- index.html - initializes with user Login.
		- accounts.html - invoking fastlink,lists user accounts, details and transactions.
	YodeeSampleApp.php-Interacts with Yodlee API. Yodlee API call usage are inside this source file.
	                   private key from yodllee API dashbooard need to configure in this file,Other configurations in myapp.ini
	myapp.ini - resource file to configure Yodlee API URL,API version,API key,CobrandName and Node Url.
    README.txt

How to run  the sample app:
1)Install xampp.
2)copy the SampleApp under folder htdocs in xamppp.
3)Run the Apache in xampp and access the below url in browser.
    http://localhost/SampleApp/web/index.html



