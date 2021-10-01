package org.notima.api.fortnox.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

public class FortnoxOAuthClient {

    private static Gson gson = new GsonBuilder().create();

    private static final String BASE_URL = "https://apps.fortnox.se/oauth-v1/";
    private static final String TOKEN_URL = "token";

    private static final String KEY_GRANT_TYPE = "grant_type";
    private static final String KEY_AUTH_CODE = "code";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";

    private static final String GRANT_TYPE_AUTH = "authorization_code";
    private static final String GRANT_TYPE_REFRESH = "refresh_token";

    public static OAuthResponse getAccessToken(String clientId, String clientSecret, String authorizationCode) throws Exception {
        Map<String, String> body = new HashMap<String, String>();
        body.put(KEY_GRANT_TYPE, GRANT_TYPE_AUTH);
        body.put(KEY_AUTH_CODE, authorizationCode);

        OAuthRequest request = new OAuthRequest();
        request.setUrl(TOKEN_URL);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setBody(xWWWFormURLEncode(body));

        return callApi(request);
    }

    public static OAuthResponse refreshAccessToken(String clientId, String clientSecret, String refreshToken) throws Exception {
        Map<String, String> body = new HashMap<String, String>();
        body.put(KEY_GRANT_TYPE, GRANT_TYPE_REFRESH);
        body.put(KEY_REFRESH_TOKEN, refreshToken);

        OAuthRequest request = new OAuthRequest();
        request.setUrl(TOKEN_URL);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setBody(xWWWFormURLEncode(body));

        return callApi(request);
    }

    // TODO: Implement!
    /*public static boolean revokeRefreshToken(String refreshToken) {
        return false;
    }*/

    private static OAuthResponse callApi(OAuthRequest request) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String credentials = getBasicAuthCredentials(request.getClientId(), request.getClientSecret());
        HttpPost post = new HttpPost(BASE_URL + request.getUrl());
		post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("Authorization", "Basic " + credentials);
        post.setEntity(new StringEntity(request.getBody()));
        HttpResponse response = httpClient.execute(post);
        String entity = EntityUtils.toString(response.getEntity());
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == 200) {
            return gson.fromJson(entity, OAuthResponse.class);
        } else {
            throw new Exception(String.format(
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
