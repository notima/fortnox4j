# fortnox4j
API to communicate with Fortnox (https://www.fortnox.se). Currently written for Java 8.

See also the Karaf module [fortnoxAdapter](https://github.com/notima/businessObjectAdapters/tree/master/fortnoxAdapter).

After cloning this repository from git, use Import Existing Maven Project to work with it in eclipse.

If you're working on a non-release version, you'll also need to build the dependency [NotimaUtil](https://github.com/notima/NotimaUtil).

To successfully run `mvn test`

Copy file 

src/test/resources/FortnoxClientListSample.xml 

to

src/test/resources/FortnoxClientList.xml

and set your own credentials (such as clientId and client secret for your integration). See the Sample.xml for instructions.

When the necessary credentials are set in the FortnoxClientList.xml file you can use the [CLI guide](#cli-guide) to get access token(s).

## Usage

See the test classes in org.notima.api.fortnox.junit.* for coding examples on how to use this library.

The credentials for accessing a Fortnox entity are normally put in an xml-file and pointed to by 

* A constructor parameter pointing to the configuration file
* An empty constructor and then calling setAccessToken with the credentials
* OS Environment variable FORTNOX4JFILE pointing to configuration file
<br>ex. export FORTNOX4JFILE=/home/user/fortnoxClients.xml
* Java Environment variable Fortnox4JFile pointing to configuration file.
<br>ex. -DFortnox4JFile=/home/user/fortnoxClients.xml 

See the file [FortnoxClientListSample.xml](https://github.com/notima/fortnox4j/blob/master/src/test/resources/FortnoxClientListSample.xml) for an example of the file structure.

### Example

When you have a FortnoxClient.xml file you can easily retrieve a Fortnox client with the orgNo as a parameter.

```
String orgNo = "SOMETHING";

FortnoxClientInfo ci = null;
FortnoxClientList clist = FortnoxUtil.readFortnoxClientListFromFile("fortnoxClients.xml");
if (orgNo!=null) {
	ci = clist.getClientInfoByOrgNo(orgNo);
} else {
	ci = clist.getFirstClient();
}

FortnoxClient3 fclient = new FortnoxClient3(ci.getAccessToken(), ci.getClientSecret());

```

## Maven

Releases are found in Maven-Central repository and can easliy be used by adding a dependency to your pom.xml.

    <dependency>
        <groupId>org.notima.api</groupId>
        <artifactId>fortnox4j</artifactId>
        <version>2.0.0</version>
    </dependency>

Or karaf

	install -s mvn:org.apache.httpcomponents/httpcore-osgi/4.4.12
	install -s mvn:org.apache.httpcomponents/httpclient-osgi/4.5.10
	install -s mvn:org.notima.piggyback-tools/0.0.2
	install -s mvn:org.notima.api/fortnox4j

## CLI guide

Prerequisites

Your integration in Fortnox must have a redirect URI that matches this client. That redirect URI is

	http://localhost:8008/login

The easiest way to run the CLI is to create an assembly by running

	mvn assembly:single

Usage: `org.notima.api.fortnox.Fortnox4jCLI configfile command orgNo`

If you have created the assembly you would type something like

	java -jar target/fortnox4j-2.0.2-SNAPSHOT-jar-with-dependencies.jar src/main/resources/FortnoxClientList.xml getAuthenticationCode 555555-5555
 
 This will bring up a browser where you can login to Fortnox and choose which end client / tenant to authenticate. After that you get an access token using below command.
  
  	java -jar target/fortnox4j-2.0.2-SNAPSHOT-jar-with-dependencies.jar src/main/resources/FortnoxClientList.xml getAccessToken 555555-5555

Arguments:
- `configfile`: The Fortnox client list xml file to get client information from. The file should look like the `FortnoxClientListSample.xml` file in `src/test/resources/`
- `command`: The command to execute. Possible commands are: `getAuthenticationCode`, `getAccessToken`, `getAllTokens`. They are described below.
- `orgNo`: The organization number of the client to perform the operation on. This is optional. The first client in the list will be used if this is omitted.


### getAuthenticationCode

Used to get an authentication code that can be used to retrieve an access token.

This command will open a browser and allow the user to log in to a Fortnox account and then print the authentication code to the standard output.

The authentication code will be automatically entered in to the client list file and it will look like the example down below.

Example:

	<FortnoxClients>
        <FortnoxClientInfo>
            <apiKey>
				<authorizationCode>d44dd44d-d44d-4dd4-d44d...
			</apiKey>
			...

### getAccessToken

Used to retrieve an access token and refresh token from Fortnox.

The command will look for an authentication code in the client list file.

The credentials will be printed to the standard output and will be automatically entered in to the client list file and will look like the example down below.

Example:

	<FortnoxClients>
        <FortnoxClientInfo>
            <apiKey>
				<accessToken>eyJ0eXAiOiJKV1QiLCJhbGciOiJIU...
				<refreshToken>fa1b1541cd9c024704c24b0c8b51...
			</apiKey>
			...

### getAllTokens

Used to get an authentication token, access token and refresh token from Fortnox.

The command will first open a browser and allow the user to log in to a Fortnox account and then print the authentication code to the standard output, aswell as insert the code in to the client list file. Then, it will search the client list file for that authentication code and use it to fetch an access token and refresh token

The credentials will then be printed to the standard output and will be automatically entered in to the client list file, like you see below.

Example:

	<FortnoxClients>
        <FortnoxClientInfo>
            <apiKey>
				<authorizationCode>d44dd44d-d44d-4dd4-d44d...
				<accessToken>eyJ0eXAiOiJKV1QiLCJhbGciOiJIU...
				<refreshToken>fa1b1541cd9c024704c24b0c8b51...
			</apiKey>
			...

## Debug / logging

To see what's going on change the logger level to "DEBUG" in src/test/resources/log4j.xml
