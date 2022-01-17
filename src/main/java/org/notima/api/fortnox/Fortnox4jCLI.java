package org.notima.api.fortnox;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientList;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.oauth2.FortnoxOAuth2Client;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Fortnox4jCLI {
    public static void main(String[] args) {
		
		if (args==null || args.length < 2) {
			System.out.println("Usage: Fortnox4jCLI configfile command orgNo");
			System.out.println("");
			System.out.println("Possible commands are: getAuthenticationCode, getAccessToken");
			System.exit(1);
		}
		
		File configFile = new File(args[0]);
		if (!configFile.exists() || configFile.isDirectory()) {
			System.out.println(args[0] + " is not a configuration file.");
			System.exit(1);
		}

		FortnoxClientList clientList = null;
		FortnoxClientInfo fc = null;
		try {
			clientList = FortnoxUtil.readFortnoxClientListFromFile(configFile.getCanonicalPath());
			if(args.length >= 3) {
				fc = clientList.getClientInfoByOrgNo(args[2]);
			} else {
				fc = clientList.getFirstClient();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if ("getAccessToken".equalsIgnoreCase(args[1])) {
			
			try {
				
				String clientSecret = fc.getClientSecret();
				String clientId = fc.getClientId();
				String authCode = fc.getApiCode();
				// TODO: Make redirectUri configurable.
				String redirectUri = null;

				getAccessToken(clientId, clientSecret, authCode, redirectUri);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if("getAuthenticationCode".equalsIgnoreCase(args[1])) {

			try {
				signIn(fc.getClientId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		} else {
			System.out.println(args[1] + ": unknown command.");
			System.exit(1);
		}
		
	}

    private static void signIn(String clientId) throws IOException {
		startWebserver();
        String url = getLoginUrl(clientId);
		openInBrowser(url);
    }

	private static void openInBrowser(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] browsers = { "google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                                        "netscape", "opera", "links", "lynx" };
        StringBuffer cmd = new StringBuffer();
        for (int i = 0; i < browsers.length; i++) {
            if(i == 0) {
                cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
			} else {
                cmd.append(String.format(" || %s \"%s\"", browsers[i], url)); 
			}
		}
        rt.exec(new String[] { "sh", "-c", cmd.toString() });
	}

	private static void startWebserver() throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8008), 0);
        server.createContext("/login", new HttpHandler() {

			@Override
			public void handle(HttpExchange ex) throws IOException {
				try {
					String authCode = getAuthCodeFromURL(ex.getRequestURI().toString());
					String response = "Login successful! You can now close this page.";
					sendResponse(ex, 200, response);
					System.out.println("Authentication Code:");
					System.out.println(authCode);
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
					sendResponse(ex, 400, e.getMessage());
					System.exit(1);
				}
			}

			private void sendResponse(HttpExchange ex, int status, String body) throws IOException {
				byte[] response = body.getBytes();
				ex.sendResponseHeaders(status, response.length);
				OutputStream os = ex.getResponseBody();
				os.write(response);
				os.close();
			}
		
		});
        server.setExecutor(null); // creates a default executor
        server.start();
	}

	private static void getAccessToken(String clientId, String clientSecret, String authCode, String redirectUri) {
		try {
			FortnoxCredentials credentials = FortnoxOAuth2Client.getAccessToken(clientId, clientSecret, authCode, redirectUri);
			System.out.println("Access Token:");
			System.out.println(credentials.getAccessToken() + "\n");
			System.out.println("Refresh Token:");
			System.out.println(credentials.getRefreshToken());
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static String getAuthCodeFromURL(String url) throws Exception {
		Pattern pattern = Pattern.compile("code=([0-9a-f-]*)");
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()) {
			return(matcher.group(1));
		} else {
			throw new Exception("No auth code found in URL \n" + url);
		}
	}

	private static String getLoginUrl(String clientId) {
		StringBuffer buf = new StringBuffer("https://apps.fortnox.se/oauth-v1/auth?");
		buf.append("client_id=");
		buf.append(clientId);
		buf.append("&redirect_uri=");
		buf.append("http%3A%2F%2Flocalhost%3A8008%2Flogin");
		buf.append("&scope=");
		buf.append("bookkeeping%20connectfile%20costcenter%20companyinformation%20currency%20customer%20inbox%20invoice%20article%20order%20payment%20profile%20project%20settings%20supplier%20supplierinvoice%20deletevoucher");
		buf.append("&access_type=offline");
		buf.append("&response_type=code");
		buf.append("&state=state");
		return buf.toString();
	}
}