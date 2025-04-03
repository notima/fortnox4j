package org.notima.api.fortnox.junit;

import java.io.File;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.oauth2.FileCredentialsProvider;

public class TestCredentialsFileManager {

    FileCredentialsProvider credentialsProvider = null;

    @Before
    public void setUp() throws Exception {
        File tempFile = File.createTempFile("test-", "-fortnox-credentials");
        System.setProperty("FortnoxCredentialsFile", tempFile.getAbsolutePath());
        credentialsProvider = new FileCredentialsProvider("1234567890");
        System.out.println("Using credentials file: " + tempFile.getAbsolutePath());
    }

    @Test
	public void testCreateLegacyCredentials() throws Exception {
        FortnoxCredentials legacyCredentials = new FortnoxCredentials();
        legacyCredentials.setLegacyToken("hello123");
        legacyCredentials.setClientId("clientId123");
        legacyCredentials.setClientSecret("clientSecret123");
        legacyCredentials.setLastRefreshToNow();
        credentialsProvider.setCredentials(legacyCredentials);

        FortnoxCredentials savedCredentials = credentialsProvider.getCredentials();
        assertEquals(legacyCredentials.getLegacyToken(), savedCredentials.getLegacyToken());
    }

    @Test
    public void testCreateOAuth2Credentials() throws Exception {
        FortnoxCredentials oAuth2Credentials = new FortnoxCredentials();
        oAuth2Credentials.setClientId("clientId123");
        oAuth2Credentials.setClientSecret("clientSecret123");
        oAuth2Credentials.setAccessToken("accessToken123");
        oAuth2Credentials.setRefreshToken("refreshToken123");
        oAuth2Credentials.setLastRefreshToNow();
        credentialsProvider.setCredentials(oAuth2Credentials);

        FortnoxCredentials savedCredentials = credentialsProvider.getCredentials();
        assertEquals(oAuth2Credentials.getLegacyToken(), savedCredentials.getLegacyToken());
    }
}
