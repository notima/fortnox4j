# fortnox4j
API to communicate with Fortnox (https://www.fortnox.se)

After cloning this repository from git, use Import Existing Maven Project to work with it in eclipse.

To successfully run `mvn test`

Copy file 

src/test/resources/FortnoxClientListSample.xml 

to

src/test/resources/FortnoxClientList.xml

and set your own credentials.

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
        <version>1.8.11</version>
    </dependency>

Or karaf

	install -s mvn:org.apache.httpcomponents/httpcore-osgi/4.4.12
	install -s mvn:org.apache.httpcomponents/httpclient-osgi/4.5.10
	install -s mvn:org.notima.piggyback-tools/0.0.2
	install -s mvn:org.notima.api/fortnox4j
