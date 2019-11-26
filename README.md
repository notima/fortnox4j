# fortnox4j
API to communicate with Fortnox (https://www.fortnox.se)

After cloning this repository from git, the following command causes eclipse to recognize it as a project.

<pre>
mvn eclipse:eclipse
</pre>

To successfully run mvn test

Copy file 

src/test/resources/config3-template.xml 

to

src/test/resources/test-config3.xml

and set your own credentials.

## Usage

See the test classes in org.notima.api.fortnox.junit.* for coding examples on how to use this library.

The credentials for accessing a Fortnox entity are normally put in an xml-file and pointed to by 

* A constructor parameter pointing to the configuration file
* An empty constructor and then calling setAccessToken with the credentials
* OS Environment variable FORTNOX4JFILE pointing to configuration file
<br>ex. export FORTNOX4JFILE=/home/user/config.xml
* Java Environment variable Fortnox4JFile pointing to configuration file.
<br>ex. -DFortnox4JFile=/home/user/config.xml 

See the file src/test/resources/config3-template.xml for an example of the file structure.

## Maven

Releases are found in Maven-Central repository and can easliy be used by adding a dependency to your pom.xml.

    <dependency>
        <groupId>org.notima.api</groupId>
        <artifactId>fortnox4j</artifactId>
        <version>1.8.6</version>
    </dependency>

