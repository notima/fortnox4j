package org.notima.api.fortnox.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.notima.api.fortnox.ClientManagerCredentialsProvider;
import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.clients.FortnoxClientManager;

public class TestClientManagerKeyProvider {

    private FortnoxCredentialsProvider keyProvider;

    @Before
    public void setup() throws FileNotFoundException {
        keyProvider = new ClientManagerCredentialsProvider("555555-5555", new FortnoxClientManager("src/test/resources/FortnoxClientList.xml"));
    }
    
    @Test
    public void testGetAndUpdateKey() throws Exception {
        FortnoxCredentials key = keyProvider.getCredentials();
        assertNotNull(key);
        System.out.printf("AccessToken: %s\nRefreshToken: %s\nScope: %s\nExpiresIn: %s\nTokenType: %s\nLastRefresh: %s\n",
            key.getAccessToken(),
            key.getRefreshToken(),
            key.getScope(),
            key.getExpiresIn(),
            key.getTokenType(),
            key.getLastRefresh()
        );

        String randomString = randomString();
        key.setTokenType(randomString);
        keyProvider.setCredentials(key);
        key = keyProvider.getCredentials();
        assertEquals(randomString, key.getTokenType());
    }

    public String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    
        return generatedString;
    }
}
