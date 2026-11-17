package org.notima.api.fortnox;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientList;
import org.notima.api.fortnox.clients.FortnoxClientManager;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.entities3.CompanySetting;
import org.notima.api.fortnox.oauth2.FileCredentialsProvider;
import org.notima.api.fortnox.oauth2.FortnoxOAuth2Client;


public class Fortnox4jCLI {

	private String clientId;
	private String clientSecret;
	private FortnoxScope scope;

	private String[] args;
	private File configFile;
	private String orgNo;
	private FortnoxClientList	clientList;
	private FortnoxClientInfo	fc;
	private FortnoxCredentials  credentials;
	private FortnoxClientManager	clientManager;
	
	private boolean getAccessToken = false;
	private boolean getAuthenticationCode = false;

	private boolean isServerRunning = false;
	
	private Boolean getAllTokens = false;

	// Redirect URI that must be registered on the integration in Fortnox Developer Portal.
	public static final String REDIRECT_URI = "http://localhost:8008/login";
	
	// Last error that occurred during the login process (null if none).
	private String lastError;

	// Default scope string, last resort if none is set.
	private final String defaultScopeStr = "bookkeeping,connectfile,costcenter,companyinformation,currency,customer,inbox,invoice,article,order,payment,profile,project,settings,supplier,supplierinvoice,deletevoucher"; 
	
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
				lastError = e.getMessage()!=null ? e.getMessage() : e.toString();
				System.out.println("Could not get access token: " + lastError);
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
	
	public FortnoxScope getScope() {
		return scope;
	}

	public void setScope(FortnoxScope scope) {
		this.scope = scope;
	}

	private void saveAccessAndRefreshToken() throws Exception {
		if (credentials!=null) {
			fc.getApiKey().setAccessToken(credentials.getAccessToken());
			fc.getApiKey().setRefreshToken(credentials.getRefreshToken());
			fc.getApiKey().setLastRefreshToNow();
			fc.getApiKey().setExpiresIn(credentials.getExpiresIn());
			fc.getApiKey().setScope(credentials.getScope());
			clientManager.updateAndSaveClientInfo(fc);
		}
	}

    private void signIn(String clientId) throws IOException {
        String url = getLoginUrl(clientId);
    	openInBrowser(url);
		startWebserver();
    }

	private void openInBrowser(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] browsers = { "firefox", "google-chrome", "mozilla", "epiphany", "konqueror",
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
		
		final HttpServer[] serverHolder = new HttpServer[1];
		final CountDownLatch latch = new CountDownLatch(1);
		
	    HttpServer server = ServerBootstrap.bootstrap()
                .setListenerPort(8008)
                .registerHandler("/login", new HttpRequestHandler() {
                    @Override
                    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws IOException {
                        try {
                            String requestUri = request.getRequestLine().getUri();
                            String redirectError = getErrorFromURL(requestUri);
                            if (redirectError!=null) {
                                lastError = redirectError;
                                System.out.println("Login failed: " + lastError);
                                sendResponse(response, 400, "Login failed: " + lastError);
                                latch.countDown();
                                scheduleShutdown(serverHolder[0], 5);
                                return;
                            }
                            String authCode = getAuthCodeFromURL(requestUri);
                            System.out.println("Authentication Code:");
                            System.out.println(authCode);
                            setAuthCode(authCode);
                            clientManager.updateAndSaveClientInfo(fc);
                            if (getAllTokens) {
                                getAccessToken(clientId, clientSecret, authCode, null);
                                if (credentials!=null) {
                                    saveAccessAndRefreshToken();
                                    updateFortnoxClientInfo();
                                }
                            }
                            if (lastError!=null) {
                                sendResponse(response, 400, "Login failed: " + lastError);
                            } else {
                                sendResponse(response, 200, "Login successful! You can now close this page.");
                            }
                            latch.countDown();
                            scheduleShutdown(serverHolder[0], 5); // Schedule server shutdown with delay
                        } catch (Exception e) {
                            lastError = e.getMessage();
                            System.out.println("Login failed: " + lastError);
                            sendResponse(response, 400, "Login failed: " + lastError);
                            latch.countDown();
                            scheduleShutdown(serverHolder[0], 5); // Schedule server shutdown with delay
                        }
                    }

                    private void sendResponse(HttpResponse response, int status, String body) {
                        response.setStatusCode(status);
                        response.setEntity(new StringEntity(body, ContentType.TEXT_PLAIN));
                    }
                    
                    private void scheduleShutdown(HttpServer server, int delaySeconds) {
                        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                        scheduler.schedule(() -> {
                            server.shutdown(0, TimeUnit.SECONDS);
                            scheduler.shutdown();
                            isServerRunning = false;
                        }, delaySeconds, TimeUnit.SECONDS);
                    }                    
                    
                })
                .create();
        
	    serverHolder[0] = server;
        server.start();
        isServerRunning = true;
        
        try {
            latch.await(); // Wait until the first request is processed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
	}

	public FortnoxClientInfo updateFortnoxClientInfo() throws Exception {

		if (fc==null) {
			System.err.println("Update/create for " + orgNo + " failed.");
			return null;
		}
		FortnoxCredentialsProvider	credentialsProvider = new FileCredentialsProvider(orgNo);
		FortnoxCredentials			cred = new FortnoxCredentials();
		cred.setOrgNo(fc.getOrgNo());
		// Fall back on the client id / secret used for this session if not set on the client.
		cred.setClientId(fc.getClientId()!=null ? fc.getClientId() : clientId);
		cred.setClientSecret(fc.getClientSecret()!=null ? fc.getClientSecret() : clientSecret);
		cred.setAccessToken(fc.getApiKey().getAccessToken());
		cred.setRefreshToken(fc.getApiKey().getRefreshToken());
		// Without expiresIn the token is considered expired and an unnecessary refresh is made.
		cred.setExpiresIn(fc.getApiKey().getExpiresIn());
		// The scope granted by Fortnox (from the token response)
		cred.setScope(credentials!=null && credentials.getScope()!=null ? credentials.getScope() : fc.getApiKey().getScope());
		cred.setLastRefreshToNow();
		credentialsProvider.setCredentials(cred);
		
		FortnoxClient3 client3 = new FortnoxClient3(credentialsProvider);
		try {
			CompanySetting cs = client3.getCompanySetting();
			fc.setOrgName(cs.getName());
		} catch (Exception ee) {
			lastError = "Tokens were received but company information could not be read for " + orgNo + ": " + ee.getMessage();
			System.out.println(lastError);
		}
		
		clientManager.updateAndSaveClientInfo(fc);
		return fc;
		
	}
	
	private void getAccessToken(String clientId, String clientSecret, String authCode, String redirectUri) {
		try {
			if (redirectUri==null) {
				redirectUri=REDIRECT_URI;
			}
			if (clientSecret==null || clientSecret.trim().length()==0 || "DONT_STORE_HERE".equals(clientSecret)) {
				lastError = "No client secret set for client id " + clientId + ".";
				System.out.println("Could not get access token: " + lastError);
				credentials = null;
				return;
			}
			credentials = FortnoxOAuth2Client.getAccessToken(clientId, clientSecret, authCode, redirectUri);
			System.out.println("Access Token:");
			System.out.println(credentials.getAccessToken() + "\n");
			System.out.println("Refresh Token:");
			System.out.println(credentials.getRefreshToken());
			printGrantedScope();
		} catch (Exception e) {
			String msg = e.getMessage()!=null ? e.getMessage() : e.toString();
			String error = extractJsonValue(msg, "error");
			String description = extractJsonValue(msg, "error_description");
			if (error!=null) {
				lastError = error + (description!=null ? " (" + description + ")" : "") + ". " + explainOAuthError(error);
			} else {
				lastError = msg;
			}
			System.out.println("Could not get access token: " + lastError);
			credentials = null;
		}
	}
	
	/**
	 * Checks if Fortnox redirected back with an error instead of an authorization code.
	 * 
	 * @param url	The request URI
	 * @return		An explained error message, or null if no error is present.
	 */
	private String getErrorFromURL(String url) {
		String error = getQueryParameter(url, "error");
		if (error==null) return null;
		String description = getQueryParameter(url, "error_description");
		return error + (description!=null ? " (" + description + ")" : "") + ". " + explainOAuthError(error);
	}
	
	private String getQueryParameter(String url, String name) {
		Matcher m = Pattern.compile("[?&]" + Pattern.quote(name) + "=([^&]*)").matcher(url);
		if (!m.find()) return null;
		try {
			return URLDecoder.decode(m.group(1), StandardCharsets.UTF_8.name());
		} catch (Exception e) {
			return m.group(1);
		}
	}
	
	private String extractJsonValue(String text, String key) {
		Matcher m = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"]*)\"").matcher(text);
		return m.find() ? m.group(1) : null;
	}
	
	/**
	 * Gives a hint on what to check for a given OAuth error code from Fortnox.
	 * 
	 * @param error		The OAuth error code.
	 * @return			A hint.
	 */
	public static String explainOAuthError(String error) {
		if (error==null) return "";
		if ("invalid_client".equals(error)) {
			return "Check that the client secret belongs to the client id and that the redirect URI " + REDIRECT_URI 
					+ " is registered on the integration in Fortnox Developer Portal (changes there may take a while to take effect).";
		}
		if ("invalid_grant".equals(error)) {
			return "The authorization code is invalid, expired or already used, or the redirect URI doesn't match " + REDIRECT_URI 
					+ ". Run again with --renew to get a new code.";
		}
		if ("invalid_scope".equals(error)) {
			return "One or more requested scopes are not enabled on the integration. Enable them in Fortnox Developer Portal or use --scope.";
		}
		if ("access_denied".equals(error) || "error_access_denied".equals(error)) {
			return "The login was cancelled or the user is not allowed to authorize the integration.";
		}
		if ("unauthorized_client".equals(error)) {
			return "The integration is not allowed to use this flow. Check the integration settings in Fortnox Developer Portal.";
		}
		if ("invalid_request".equals(error)) {
			return "The request was malformed. Check client id and that the redirect URI " + REDIRECT_URI + " is registered on the integration.";
		}
		if (error.contains("license")) {
			return "The company lacks a license required by the integration (or by the requested scopes).";
		}
		if (error.contains("admin")) {
			return "The user logging in must be system administrator in the Fortnox company.";
		}
		return "";
	}
	
	/**
	 * Prints the scope granted by Fortnox and warns for requested scopes that were not granted.
	 * Fortnox silently leaves out scopes that are not enabled on the integration.
	 */
	private void printGrantedScope() {
		if (credentials==null) return;
		if (credentials.getScope()==null) {
			System.out.println("Fortnox did not report the granted scope.");
			return;
		}
		FortnoxScope granted = new FortnoxScope(credentials.getScope());
		System.out.println("Granted scope: " + granted.getScopesSeparatedByComma());
		Set<String> missing = getMissingScopes();
		if (!missing.isEmpty()) {
			System.out.println("WARNING: Requested but NOT granted: " + String.join(",", missing)
					+ ". Enable them on the integration in Fortnox Developer Portal and run again with --renew.");
		}
	}
	
	/**
	 * @return	Requested scopes that were not granted by Fortnox. Empty if unknown.
	 */
	public Set<String> getMissingScopes() {
		Set<String> missing = new TreeSet<String>();
		if (credentials==null || credentials.getScope()==null) return missing;
		FortnoxScope requested = scope!=null && scope.hasScopes() ? scope : new FortnoxScope(defaultScopeStr);
		FortnoxScope granted = new FortnoxScope(credentials.getScope());
		for (String s : requested.getScopes()) {
			if (!granted.getScopes().contains(s)) missing.add(s);
		}
		return missing;
	}
	
	/**
	 * @return	The scope granted by Fortnox, or null if unknown.
	 */
	public String getGrantedScope() {
		return credentials!=null ? credentials.getScope() : null;
	}
	
	/**
	 * @return	The last error during the login process, or null if none.
	 */
	public String getLastError() {
		return lastError;
	}
	
	public boolean hasFailed() {
		return lastError!=null;
	}
	
	/**
	 * @return	True if an access token was received.
	 */
	public boolean hasAccessToken() {
		return credentials!=null && credentials.getAccessToken()!=null;
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
	
	private String getScopeUrlString() {
		if (scope!=null && scope.hasScopes()) {
			return scope.getScopesAsUrlParameter();
		} else {
			return new FortnoxScope(defaultScopeStr).getScopesAsUrlParameter();
		}
	}
	
	private String getLoginUrl(String clientId) {
		String scopeStr = getScopeUrlString();
		StringBuffer buf = new StringBuffer("https://apps.fortnox.se/oauth-v1/auth?");
		buf.append("client_id=");
		buf.append(clientId);
		buf.append("&redirect_uri=");
		buf.append("http%3A%2F%2Flocalhost%3A8008%2Flogin"); // REDIRECT_URI
		buf.append("&" + scopeStr);
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

	public FortnoxClientInfo getFortnoxClientInfo() {
		return fc;
	}

	public boolean isServerRunning() {
		return isServerRunning;
	}
	
	
}
