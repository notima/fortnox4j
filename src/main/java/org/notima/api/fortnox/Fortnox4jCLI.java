package org.notima.api.fortnox;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientList;
import org.notima.api.fortnox.clients.FortnoxClientManager;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.oauth2.FortnoxOAuth2Client;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Fortnox4jCLI {

	private String clientId;
	private String clientSecret;

	private String[] args;
	private File configFile;
	private String orgNo;
	private FortnoxClientList	clientList;
	private FortnoxClientInfo	fc;
	private FortnoxCredentials  credentials;
	private FortnoxClientManager	clientManager;
	
	private boolean getAccessToken = false;
	private boolean getAuthenticationCode = false;

	private Boolean getAllTokens = false;

	
	public static void main(String[] argv) {
		
		Fortnox4jCLI cli = new Fortnox4jCLI();
		cli.readArgs(argv);

		cli.initFortnoxClientInfo();

		cli.initClientIdAndSecret();

		cli.determineCommandToRun();
		
	}
	
	private void readArgs(String [] args) {
		
		if (args==null || args.length < 2) {
			System.out.println("Usage: Fortnox4jCLI configfile command orgNo");
			System.out.println("");
			System.out.println("Possible commands are: getAuthenticationCode, getAccessToken, getAllTokens");
			System.exit(1);
		}

		this.args = args;
		
		configFile = new File(args[0]);
		if (!configFile.exists() || configFile.isDirectory()) {
			System.out.println(args[0] + " is not a configuration file.");
			System.exit(1);
		}
		
		getAccessToken = "getAccessToken".equalsIgnoreCase(args[1]);
		getAuthenticationCode = "getAuthenticationCode".equalsIgnoreCase(args[1]);
		getAllTokens = "getAllTokens".equalsIgnoreCase(args[1]);
		
	}

	public void setFortnoxClientManager(FortnoxClientManager mgr, String initialOrgNo) {
		
		clientManager = mgr;
		clientList = mgr.getClientList();
		fc = clientList.getClientInfoByOrgNo(initialOrgNo);
		orgNo = initialOrgNo;
		
	}

	/**
	 * 
	 * @return	True if authentication code is available
	 */
	public boolean hasAuthenticationCode() {
		return (fc!=null && fc.getApiKey()!=null && fc.getApiKey().hasAuthorizationCode());
	}
	
	private void initFortnoxClientInfo() {

		try {
			clientManager = new FortnoxClientManager(configFile.getCanonicalPath());
			clientList = clientManager.getClientList();
			if(args.length >= 3) {
				fc = clientList.getClientInfoByOrgNo(args[2]);
				orgNo = args[2];
			} else {
				fc = clientList.getFirstClient();
				if (fc!=null) {
					orgNo = fc.getOrgNo();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	private void initClientIdAndSecret() {
		
		if (fc!=null && fc.getClientId()!=null) {
			clientId = fc.getClientId();
		} else {
			clientId = clientList.getApiClients().getApiClient().get(0).getClientId();
			clientSecret = clientList.getApiClients().getApiClient().get(0).getClientSecret();
		}
		
		if (fc!=null && fc.getClientSecret()!=null) {
			clientSecret = fc.getClientSecret();
		} else {
			clientSecret = clientList.getApiClients().getApiClient().get(0).getClientSecret();
		}
		
	}
	
	public void determineCommandToRun() {

		if (getAccessToken) {
			
			try {
				
				String authCode = fc.getApiKey().getAuthorizationCode();
				// TODO: Make redirectUri configurable.
				String redirectUri = null;

				getAccessToken(clientId, clientSecret, authCode, redirectUri);
				saveAccessAndRefreshToken();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if(getAuthenticationCode) {
			
			try {
				signIn(clientId);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		} else if(getAllTokens){

			try {
				signIn(clientId);
			} catch (Exception e2){
				e2.printStackTrace();
			}

		} else {
			System.out.println(args[1] + ": unknown command.");
		}
		
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	private void saveAccessAndRefreshToken() throws Exception {
		if (credentials!=null) {
			fc.getApiKey().setAccessToken(credentials.getAccessToken());
			fc.getApiKey().setRefreshToken(credentials.getRefreshToken());
			fc.getApiKey().setLastRefreshToNow();
			fc.getApiKey().setExpiresIn(credentials.getExpiresIn());
			clientManager.updateAndSaveClientInfo(fc);
		}
	}

    private void signIn(String clientId) throws IOException {
		startWebserver();
        String url = getLoginUrl(clientId);
		openInBrowser(url);
    }

	private void openInBrowser(String url) throws IOException {
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

	private void setAuthCode(String authCode) {
		if (fc==null) {
			fc = new FortnoxClientInfo();
			fc.setOrgNo(orgNo);
		}
		if (fc.getApiKey()==null) {
			fc.setApiKey(new FortnoxCredentials());
		}
		fc.getApiKey().setAuthorizationCode(authCode);
	}
	
	private void startWebserver() throws IOException {
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
					setAuthCode(authCode);
					clientManager.updateAndSaveClientInfo(fc);
					if (getAllTokens){
						getAccessToken(clientId, clientSecret, authCode, null);
						saveAccessAndRefreshToken();
					}
					server.stop(0);
				} catch (Exception e) {
					e.printStackTrace();
					sendResponse(ex, 400, e.getMessage());
					server.stop(1);
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

	private void getAccessToken(String clientId, String clientSecret, String authCode, String redirectUri) {
		try {
			if (redirectUri==null) {
				redirectUri="http://localhost:8008/login";
			}
			credentials = FortnoxOAuth2Client.getAccessToken(clientId, clientSecret, authCode, redirectUri);
			System.out.println("Access Token:");
			System.out.println(credentials.getAccessToken() + "\n");
			System.out.println("Refresh Token:");
			System.out.println(credentials.getRefreshToken());
		} catch (Exception e) {
			e.printStackTrace();
			credentials = null;
		}
	}

	private String getAuthCodeFromURL(String url) throws Exception {
		Pattern pattern = Pattern.compile("code=([0-9a-f-]*)");
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()) {
			return(matcher.group(1));
		} else {
			throw new Exception("No auth code found in URL \n" + url);
		}
	}

	private String getLoginUrl(String clientId) {
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

	public void setGetAccessToken(boolean getAccessToken) {
		this.getAccessToken = getAccessToken;
	}

	public void setGetAllTokens(Boolean getAllTokens) {
		this.getAllTokens = getAllTokens;
	}
}
