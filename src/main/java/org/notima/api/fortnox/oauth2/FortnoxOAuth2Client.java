package org.notima.api.fortnox.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.notima.api.fortnox.FortnoxAuthenticationException;
import org.notima.api.fortnox.clients.FortnoxCredentials;

public class FortnoxOAuth2Client {

    private static Gson gson = new GsonBuilder().create();

    private static final String BASE_URL = "https://apps.fortnox.se/oauth-v1/";
    private static final String TOKEN_URL = "token";
    private static final String MIGRATE_URL = "migrate";

    private static final String KEY_GRANT_TYPE = "grant_type";
    private static final String KEY_AUTH_CODE = "code";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_REDIRECT_URI = "redirect_uri";

    private static final String GRANT_TYPE_AUTH = "authorization_code";
    private static final String GRANT_TYPE_REFRESH = "refresh_token";

    public static FortnoxCredentials getAccessToken(String clientId, String clientSecret, String authorizationCode, String redirectUri) throws FortnoxAuthenticationException, Exception {

    	if (clientSecret==null) {
    		FortnoxCredentials errorCredentials = new FortnoxCredentials();
    		errorCredentials.setClientId(clientId);
    		errorCredentials.setAuthorizationCode(authorizationCode);
    		throw new FortnoxAuthenticationException(errorCredentials);
    	}
    	
    	Map<String, String> body = new HashMap<String, String>();
        body.put(KEY_GRANT_TYPE, GRANT_TYPE_AUTH);
        body.put(KEY_AUTH_CODE, authorizationCode);
        if (redirectUri!=null) {
        	body.put(KEY_REDIRECT_URI, redirectUri);
        }

        OAuthRequest request = new OAuthRequest();
        request.setUrl(TOKEN_URL);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setBody(xWWWFormURLEncode(body));
        
        FortnoxCredentials credentials = callApi(request, FortnoxCredentials.class);
        credentials.setLastRefresh(new Date().getTime());
        return credentials;
    }

    public static FortnoxCredentials refreshAccessToken(String clientId, String clientSecret, String refreshToken) throws FortnoxAuthenticationException, Exception {

    	if (clientSecret==null) {
    		FortnoxCredentials errorCredentials = new FortnoxCredentials();
    		errorCredentials.setClientId(clientId);
    		errorCredentials.setRefreshToken(refreshToken);
    		throw new FortnoxAuthenticationException(errorCredentials);
    	}
    	
    	Map<String, String> body = new HashMap<String, String>();
        body.put(KEY_GRANT_TYPE, GRANT_TYPE_REFRESH);
        body.put(KEY_REFRESH_TOKEN, refreshToken);

        OAuthRequest request = new OAuthRequest();
        request.setUrl(TOKEN_URL);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setBody(xWWWFormURLEncode(body));

        FortnoxCredentials credentials;
        try {
        	credentials = callApi(request, FortnoxCredentials.class);
            credentials.setLastRefresh(new Date().getTime());
            return credentials;
        } catch (FortnoxAuthenticationException fae) {
        	FortnoxCredentials failedCredentials = new FortnoxCredentials();
        	failedCredentials.setClientId(clientId);
        	failedCredentials.setClientSecret(clientSecret);
        	failedCredentials.setRefreshToken(refreshToken);
        	fae.setCredentials(failedCredentials);
        	throw fae;
        }
    }

    public FortnoxCredentials migrateLegacyToken(String clientId, String clientSecret, String legacyToken) throws FortnoxAuthenticationException, Exception {

    	if (clientSecret==null) {
    		FortnoxCredentials errorCredentials = new FortnoxCredentials();
    		errorCredentials.setClientId(clientId);
    		errorCredentials.setLegacyToken(legacyToken);
    		throw new FortnoxAuthenticationException(errorCredentials);
    	}
    	
    	Map<String, String> body = new HashMap<>();
        body.put("access_token", legacyToken);

        OAuthRequest request = new OAuthRequest();
        request.setUrl(MIGRATE_URL);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setBody(xWWWFormURLEncode(body));

        request.addHeader("ClientId", clientId);
        request.addHeader("ClientSecret", clientSecret);
        request.addHeader("Credentials", getBasicAuthCredentials(request.getClientId(), request.getClientSecret()));

        FortnoxCredentials credentials = callApi(request, FortnoxCredentials.class);
        credentials.setLastRefresh(new Date().getTime());
        return credentials;
    }

    // TODO: Implement!
    /*public static boolean revokeRefreshToken(String refreshToken) {
        return false;
    }*/

    private static <T> T callApi(OAuthRequest request, Class<T> classOfT) throws FortnoxAuthenticationException, Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String credentials = getBasicAuthCredentials(request.getClientId(), request.getClientSecret());
        HttpPost post = new HttpPost(BASE_URL + request.getUrl());
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            post.setHeader(entry.getKey(), entry.getValue());
        }
		post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("Authorization", "Basic " + credentials);
        post.setEntity(new StringEntity(request.getBody()));
        HttpResponse response = httpClient.execute(post);
        String entity = EntityUtils.toString(response.getEntity());
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode >= 200 && statusCode < 300) {
            return gson.fromJson(entity, classOfT);
        } else {
            throw new FortnoxAuthenticationException(String.format(
                "%s responded with %d %s\n%s", 
                post.getURI(), 
                statusCode, 
                response.getStatusLine().getReasonPhrase(), 
                entity));
        }
    }

    private static String getBasicAuthCredentials(String username, String password) {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    private static String xWWWFormURLEncode(Map<String, String> data) throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> iterator = data.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            buffer.append(key);
            buffer.append("=");
            buffer.append(URLEncoder.encode(data.get(key), StandardCharsets.UTF_8.toString()));
            if(iterator.hasNext())
                buffer.append("&");
        }
        return buffer.toString();
    }
    
}
