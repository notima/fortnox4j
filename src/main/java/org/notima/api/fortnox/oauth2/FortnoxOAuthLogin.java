package org.notima.api.fortnox.oauth2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.notima.api.fortnox.Fortnox4jCLI;
import org.notima.api.fortnox.FortnoxAuthenticationException;
import org.notima.api.fortnox.FortnoxScope;
import org.notima.api.fortnox.clients.FortnoxCredentials;

/**
 * Lets a user authorize an integration for a Fortnox company (OAuth2 authorization code flow)
 * from a desktop application.
 *
 * <p>The authorization page is opened in the user's browser. Fortnox redirects back to a
 * temporary web server on {@code http://localhost:<port>/login}, which must be registered as
 * redirect URI on the integration in Fortnox Developer Portal. The code is then exchanged for
 * an access and refresh token.</p>
 *
 * <pre>
 *   FortnoxOAuthLogin login = new FortnoxOAuthLogin(clientId, clientSecret, new FortnoxScope("bookkeeping,companyinformation"));
 *   FortnoxCredentials creds = login.login(uri -&gt; Desktop.getDesktop().browse(uri));
 * </pre>
 */
public class FortnoxOAuthLogin {

	public static final int DEFAULT_PORT = 8008;
	public static final String REDIRECT_PATH = "/login";
	public static final String AUTH_URL = "https://apps.fortnox.se/oauth-v1/auth";

	private final String clientId;
	private final String clientSecret;
	private final FortnoxScope scope;
	private int port = DEFAULT_PORT;
	private long timeoutSeconds = 600;

	private volatile boolean cancelled;
	private volatile CountDownLatch latch;

	private volatile String code;
	private volatile String error;
	private FortnoxCredentials credentials;

	public FortnoxOAuthLogin(String clientId, String clientSecret, FortnoxScope scope) {
		this.clientId = clientId!=null ? clientId.trim() : null;
		this.clientSecret = clientSecret!=null ? clientSecret.trim() : null;
		this.scope = scope;
	}

	public void setPort(int port) { this.port = port; }
	public int getPort() { return port; }

	/** How long to wait for the user to log in. Default 10 minutes. */
	public void setTimeoutSeconds(long timeoutSeconds) { this.timeoutSeconds = timeoutSeconds; }

	/**
	 * @return	The redirect URI that must be registered on the integration.
	 */
	public String getRedirectUri() {
		return redirectUri(port);
	}

	public static String redirectUri(int port) {
		return "http://localhost:" + port + REDIRECT_PATH;
	}

	/**
	 * Runs the login. Blocks until the user has authorized (or refused) the integration in the
	 * browser, the login is {@link #cancel() cancelled} or times out.
	 *
	 * @param openBrowser	Opens the authorization page in the browser.
	 * @return				The credentials (access and refresh token, granted scope).
	 * @throws FortnoxAuthenticationException	If the login failed, with an explained reason.
	 * @throws Exception	If something else goes wrong.
	 */
	public FortnoxCredentials login(Consumer<URI> openBrowser) throws FortnoxAuthenticationException, Exception {

		if (clientId==null || clientId.isEmpty()) throw new FortnoxAuthenticationException("No client id set.");
		if (clientSecret==null || clientSecret.isEmpty()) throw new FortnoxAuthenticationException("No client secret set.");
		if (scope==null || !scope.hasScopes()) throw new FortnoxAuthenticationException("No valid scope selected.");

		cancelled = false;
		code = null;
		error = null;
		credentials = null;
		latch = new CountDownLatch(1);
		final String state = newState();

		HttpServer server;
		try {
			server = ServerBootstrap.bootstrap()
					.setLocalAddress(InetAddress.getLoopbackAddress())
					.setListenerPort(port)
					.registerHandler(REDIRECT_PATH, (request, response, context) -> {
						String uri = request.getRequestLine().getUri();
						String err = queryParameter(uri, "error");
						String receivedState = queryParameter(uri, "state");
						String receivedCode = queryParameter(uri, "code");
						if (err!=null) {
							String desc = queryParameter(uri, "error_description");
							error = err + (desc!=null ? " (" + desc + ")" : "") + ". " + Fortnox4jCLI.explainOAuthError(err);
						} else if (!state.equals(receivedState)) {
							error = "The response from Fortnox doesn't belong to this login (wrong state). Try again.";
						} else if (receivedCode==null || receivedCode.isEmpty()) {
							error = "No authorization code received from Fortnox.";
						} else {
							code = receivedCode;
						}
						response.setStatusCode(error==null ? HttpStatus.SC_OK : HttpStatus.SC_BAD_REQUEST);
						response.setEntity(new StringEntity(page(error), ContentType.create("text/html", StandardCharsets.UTF_8)));
						latch.countDown();
					})
					.create();
			server.start();
		} catch (IOException e) {
			throw new FortnoxAuthenticationException("Could not listen on " + getRedirectUri() + " (" + e.getMessage()
					+ "). Is the port used by another program? Choose another port and register the redirect URI on the integration.");
		}

		try {
			openBrowser.accept(getAuthorizationUri(state));
			long deadline = System.currentTimeMillis() + timeoutSeconds*1000;
			while (!latch.await(500, TimeUnit.MILLISECONDS)) {
				if (cancelled) throw new FortnoxAuthenticationException("Login cancelled.");
				if (System.currentTimeMillis() > deadline) throw new FortnoxAuthenticationException("No response from Fortnox within " + timeoutSeconds/60 + " minutes.");
			}
		} finally {
			// Let the browser receive the page before stopping
			server.shutdown(2, TimeUnit.SECONDS);
		}

		if (cancelled) throw new FortnoxAuthenticationException("Login cancelled.");
		if (error!=null) throw new FortnoxAuthenticationException(error);

		try {
			credentials = FortnoxOAuth2Client.getAccessToken(clientId, clientSecret, code, getRedirectUri());
		} catch (Exception e) {
			throw new FortnoxAuthenticationException(explain(e));
		}
		credentials.setClientId(clientId);
		return credentials;
	}

	/**
	 * Cancels a login in progress.
	 */
	public void cancel() {
		cancelled = true;
		CountDownLatch l = latch;
		if (l!=null) l.countDown();
	}

	/**
	 * @return	Requested scopes that Fortnox didn't grant (empty if all were granted or unknown).
	 * 			Fortnox leaves out scopes that are not enabled on the integration.
	 */
	public Set<String> getMissingScopes() {
		return missingScopes(scope, credentials!=null ? credentials.getScope() : null);
	}

	public static Set<String> missingScopes(FortnoxScope requested, String grantedScope) {
		Set<String> missing = new TreeSet<String>();
		if (requested==null || grantedScope==null) return missing;
		FortnoxScope granted = new FortnoxScope(grantedScope);
		for (String s : requested.getScopes()) {
			if (!granted.getScopes().contains(s)) missing.add(s);
		}
		return missing;
	}

	URI getAuthorizationUri(String state) throws Exception {
		StringBuilder sb = new StringBuilder(AUTH_URL);
		sb.append("?client_id=").append(enc(clientId));
		sb.append("&redirect_uri=").append(enc(getRedirectUri()));
		sb.append("&").append(scope.getScopesAsUrlParameter());
		sb.append("&state=").append(enc(state));
		sb.append("&access_type=offline");
		sb.append("&response_type=code");
		return new URI(sb.toString());
	}

	private static String newState() {
		return UUID.randomUUID().toString() + Long.toHexString(new SecureRandom().nextLong());
	}

	private static String enc(String s) throws Exception {
		return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
	}

	private static String queryParameter(String uri, String name) {
		Matcher m = Pattern.compile("[?&]" + Pattern.quote(name) + "=([^&]*)").matcher(uri);
		if (!m.find()) return null;
		try {
			return URLDecoder.decode(m.group(1), StandardCharsets.UTF_8.name());
		} catch (Exception e) {
			return m.group(1);
		}
	}

	private static String explain(Exception e) {
		String msg = e.getMessage()!=null ? e.getMessage() : e.toString();
		Matcher err = Pattern.compile("\"error\"\\s*:\\s*\"([^\"]*)\"").matcher(msg);
		if (!err.find()) return msg;
		Matcher desc = Pattern.compile("\"error_description\"\\s*:\\s*\"([^\"]*)\"").matcher(msg);
		return err.group(1) + (desc.find() ? " (" + desc.group(1) + ")" : "") + ". " + Fortnox4jCLI.explainOAuthError(err.group(1));
	}

	private static String page(String error) {
		String text = error==null
				? "Fortnox is connected. You can close this page and return to the application."
				: "The connection to Fortnox failed: " + escape(error);
		return "<!doctype html><html><head><meta charset=\"utf-8\"><title>Fortnox</title></head>"
				+ "<body style=\"font-family:sans-serif;margin:3em\"><p>" + text + "</p></body></html>";
	}

	private static String escape(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

}
