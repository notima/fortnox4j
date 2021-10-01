package org.notima.api.fortnox.oauth;

public class TestApplication {
    public static void main(String... args) throws Exception {
        if(args.length != 3) {
            System.out.println("Usage: TestApplication <clientId> <clientSecret> <authorizationCode>");
            return;
        }

        OAuthResponse response;

        System.out.println("Getting access token...");
        response = FortnoxOAuthClient.getAccessToken(args[0], args[1], args[2]);
        showResponse(response);

        System.out.println("Refreshing access token...");
        response = FortnoxOAuthClient.refreshAccessToken(args[0], args[1], response.getRefreshToken());
        showResponse(response);
    }

    static void showResponse(OAuthResponse response) {
        System.out.printf("access-token: %s\nrefresh_token: %s\nscope: %s\nexpires_in: %s\ntoken_type: %s\n\n",
                response.getAccessToken(),
                response.getRefreshToken(),
                response.getScope(),
                response.getExpiresIn(),
                response.getTokenType());
    }
}
