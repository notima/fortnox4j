package org.notima.api.fortnox;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientList;
import org.notima.api.fortnox.entities3.Account;
import org.notima.api.fortnox.entities3.AccountSubset;
import org.notima.api.fortnox.entities3.Accounts;
import org.notima.api.fortnox.entities3.Article;
import org.notima.api.fortnox.entities3.Articles;
import org.notima.api.fortnox.entities3.Authorization;
import org.notima.api.fortnox.entities3.CompanySetting;
import org.notima.api.fortnox.entities3.Customer;
import org.notima.api.fortnox.entities3.CustomerSubset;
import org.notima.api.fortnox.entities3.Customers;
import org.notima.api.fortnox.entities3.ErrorInformation;
import org.notima.api.fortnox.entities3.FinancialYearSubset;
import org.notima.api.fortnox.entities3.FinancialYears;
import org.notima.api.fortnox.entities3.FortnoxFile;
import org.notima.api.fortnox.entities3.Invoice;
import org.notima.api.fortnox.entities3.InvoicePayment;
import org.notima.api.fortnox.entities3.InvoiceRow;
import org.notima.api.fortnox.entities3.InvoiceSubset;
import org.notima.api.fortnox.entities3.Invoices;
import org.notima.api.fortnox.entities3.LockedPeriod;
import org.notima.api.fortnox.entities3.ModeOfPayment;
import org.notima.api.fortnox.entities3.ModeOfPaymentSubset;
import org.notima.api.fortnox.entities3.ModesOfPayments;
import org.notima.api.fortnox.entities3.Order;
import org.notima.api.fortnox.entities3.Orders;
import org.notima.api.fortnox.entities3.PreDefinedAccount;
import org.notima.api.fortnox.entities3.PreDefinedAccountSubset;
import org.notima.api.fortnox.entities3.PreDefinedAccounts;
import org.notima.api.fortnox.entities3.Supplier;
import org.notima.api.fortnox.entities3.SupplierSubset;
import org.notima.api.fortnox.entities3.Suppliers;
import org.notima.api.fortnox.entities3.Voucher;
import org.notima.api.fortnox.entities3.VoucherFileConnection;
import org.notima.api.fortnox.entities3.VoucherSeries;
import org.notima.api.fortnox.entities3.VoucherSeriesCollection;
import org.notima.api.fortnox.entities3.VoucherSeriesSubset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client class for communicating with Fortnox.
 * 
 * Copyright 2019 Notima System Integration AB (Sweden)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Daniel Tamm
 *
 */
public class FortnoxClient3 {
	/**
	 * Available markAsSent options
	 * @see markAsSent(int, String)
	 */

	/**
	 * Filters
	 */
	public static final String FILTER_CANCELLED = "cancelled";
	public static final String FILTER_FULLY_PAID = "fullypaid";
	public static final String FILTER_UNPAID = "unpaid";
	public static final String FILTER_UNPAID_OVERDUE = "unpaidoverdue";
	public static final String FILTER_UNBOOKED = "unbooked";
	public static final String	FILTER_EXPIRED = "expired";
	public static final String FILTER_INVOICECREATED = "invoicecreated";
	public static final String	FILTER_INVOICENOTCREATED = "invoicenotcreated";
	
	/**
	 * Invoice actions
	 */
	public static final String ACTION_INVOICE_BOOKKEEP = "bookkeep";
	public static final String ACTION_INVOICE_CANCEL = "cancel";
	public static final String ACTION_INVOICE_CREDIT = "credit";
	public static final String ACTION_INVOICE_EMAIL = "email";
	public static final String ACTION_INVOICE_PRINT = "print";
	public static final String ACTION_INVOICE_PRINTREMINDER = "printreminder";
	public static final String ACTION_INVOICE_EXTERNALPRINT = "externalprint";
	public static final String ACTION_INVOICE_PREVIEW = "preview";
	
	/**
	 * Order actions
	 */
	public static final String ACTION_ORDER_CREATEINVOICE = "createinvoice";
	public static final String ACTION_ORDER_CANCEL = "cancel";
	public static final String ACTION_ORDER_EMAIL = "email";
	public static final String ACTION_ORDER_PRINT = "print";
	public static final String ACTION_ORDER_EXTERNALPRINT = "externalprint";
	public static final String ACTION_ORDER_PREVIEW = "preview";
	
	/**
	 * Predefined accounts
	 */
	public static final String ACCT_SALES_MP1 = "SALES";
	public static final String ACCT_SALES_SERVICE_MP1 = "SALES2";
	public static final String ACCT_SALES_MP2 = "SALES_MP2";
	public static final String ACCT_SALES_MP3 = "SALES_MP3";
	public static final String ACCT_SALES_MP0 = "SALES_MP0";
	public static final String ACCT_SALES_NO_VAT = "SALES_NOVAT";
	public static final String ACCT_SALES_EU = "SALESEUREV";
	public static final String ACCT_SALES_EU_SERVICE = "SALESEUREV2";
	public static final String ACCT_SALES_EXPORT = "SALESEXPORT";
	public static final String ACCT_SALES_EXPORT_SERVICE = "SALESEXPORT2";
	public static final String ACCT_SALES_EU_W_VAT = "SALESEUVAT";
	public static final String ACCT_SALES_EU_W_VAT_SERVICE = "SALESEUVAT2";
	public static final String ACCT_SALES_SE_REV = "SALESCONSTR2";
	public static final String ACCT_ROUNDING = "ROUNDOFF";
	public static final String ACCT_CURRENCYGAIN = "CURRENCYWIN";
	public static final String ACCT_CURRENCYLOSS = "CURRENCYLOSS";
	public static final String ACCT_ADMFEE = "ADMFEE";
	public static final String ACCT_FREIGHT_REVENUE = "FREIGHT";
	public static final String ACCT_INVAT = "INVAT";
	public static final String ACCT_OUTVAT_MP1 = "OUTVAT_MP1";
	public static final String ACCT_OUTVAT_MP2 = "OUTVAT_MP2";
	public static final String ACCT_OUTVAT_MP3 = "OUTVAT_MP3";
	public static final String ACCT_OUTVAT_MP4 = "OUTVAT_MP4";
	public static final String ACCT_CASHBYCARD = "CASHBYCARD";
	

	/**
	 * Predefined revenue accounts
	 * These exist as predefined accounts in Fortnox
	 */
	public static String[] PREDEFINED_REV_ACCT = new String[] {
		ACCT_CURRENCYGAIN,
		ACCT_CURRENCYLOSS,
		ACCT_ADMFEE,
		ACCT_FREIGHT_REVENUE,
		ACCT_ROUNDING,
		ACCT_SALES_MP1,
		ACCT_SALES_SERVICE_MP1,
		ACCT_SALES_EU,
		ACCT_SALES_EU_SERVICE,
		ACCT_SALES_EXPORT,
		ACCT_SALES_EU_W_VAT,
		ACCT_SALES_EU_W_VAT_SERVICE,
		ACCT_SALES_SE_REV
	};

	/**
	 * Revenue accounts for other VAT that MP1
	 */
	public static String[] PREDEFINED_REVENUE_VAT_ACCT = new String[] {
		ACCT_SALES_MP2,
		ACCT_SALES_MP3,
		ACCT_SALES_MP0,		// Same as MF in Fortnox
		ACCT_SALES_NO_VAT,
		ACCT_SALES_EXPORT_SERVICE
	};
	
	/**
	 * Error Codes
	 */
	public static final String ERROR_CANT_FIND_CUSTOMER = "2000433";
	public static final String ERROR_CANT_FIND_INVOICE = "2000434";
	public static final String ERROR_NOT_AUTH_FOR_SCOPE = "2000663";
	public static final String ERROR_INVALID_LOGIN = "2000310";
	public static final String ERROR_ACCOUNT_NOT_ACTIVE = "2000550";
	public static final String ERROR_ACCOUNT_NOT_FOUND = "2000423";
	public static final String ERROR_ARTICLE_NOT_FOUND = "2000428";
	
	/**
	 * Inbox folders
	 */
	public static final String	INBOX_SUPPLIER_INVOICES = "inbox_s";
	public static final String	INBOX_VOUCHERS = "inbox_v";
	public static final String	INBOX_DAILY_TAKINGS = "inbox_d";
	public static final String	INBOX_ASSET_REGISTER = "inbox_a";
	public static final String	INBOX_BANK_FILES = "inbox_b";
	
	/**
	 * Default values
	 */
	public static final String DFortnox4JFile = "Fortnox4JFile";
	public static final String ENV_CONFIG_FILE = DFortnox4JFile.toUpperCase();
	
	private String 		m_clientSecret;
	private String		m_authCode;
	private String		m_accessToken;
	private String		m_baseUrl = "https://api.fortnox.se/3";
	
	public static DateFormat	s_dfmt = new SimpleDateFormat("yyyy-MM-dd");
	
	// A map used to quickly lookup a customer using tax id
	private Map<String, CustomerSubset>	m_customerTaxIdLookupMap;
	// A map used to quickly lookup a supplier using tax id
	private Map<String, SupplierSubset> m_supplierTaxIdLookupMap;

	// Get logger
	protected Logger	logger = LoggerFactory.getLogger(FortnoxClient3.class);
	
	// Variables for rate limiting
	protected Long		firstCall;
	protected Long		lastCall;
	protected long		totalCalls = 0;
	protected long		minMillisBetweenCalls = 250;	// Max 4 calls / second
	
	// Current client list
	private FortnoxClientList	clientList;
	
	/**
	 * Flag to say if articles should be used on invoices / orders
	 */
	private boolean		useArticles = true;
	
	/**
	 * Create FortnoxClient using default configuration file (if found).
	 * 
	 * Configuration file can be set using
	 * 
	 * -DFortnox4JFile=//file
	 * 
	 * or environment variable
	 * 
	 * export FORTNOX4JFILE=//file
	 * 
	 * NOTE: If the above environment variables are set and valid they will be used if this constructor 
	 * 		 is called. If you want to specify accessToken and clientSecret programmatically, 
	 * 		 use {@link FortnoxClient3#FortnoxClient3(String, String)}
	 * 
	 */
	public FortnoxClient3() {
		try {
			initFromFile(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Create FortnoxClient using specified accessToken and clientSecret.
	 * 
	 * @param accessToken			The accessToken to use.
	 * @param clientSecret			The clientSecret.
	 */
	public FortnoxClient3(String accessToken, String clientSecret) {
		setAccessToken(accessToken, clientSecret);
	}
	
	/**
	 * Create FortnoxClient using specified configuration file.
	 * 
	 * @param configFile		The configuration file to use.
	 * @throws IOException		If something goes wrong when reading the file. 
	 */
	public FortnoxClient3(String configFile) throws IOException {
		initFromFile(configFile);
	}

	/**
	 * 
	 * 
	 * @return	True if this client has been initialized with credentials.
	 */
	public boolean hasCredentials() {
		if (m_accessToken!=null && m_accessToken.trim().length()>0 && 
				m_clientSecret!=null && m_clientSecret.trim().length()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Read client parameters from file (if found).
	 * 
	 * @param configFile
	 * @throws IOException 
	 */
	private void initFromFile(String configFile) throws IOException {
		
		if (configFile==null || configFile.trim().length()==0) {
			// Try system properties
			String defaultFile = System.getProperty("Fortnox4JFile");
			if (defaultFile==null) {
				// Try environment
				defaultFile = System.getenv(ENV_CONFIG_FILE);
			}
			if (defaultFile!=null) {
				configFile = defaultFile;
				logger.debug("Trying config from environment: {}", configFile);
			}
		}
		
		// First check if this resolves to a file right away
		File f = configFile!=null ? new File(configFile) : null;
		if (f!=null && !f.exists()) {
			// Try to resolve from classpath
			URL url = ClassLoader.getSystemResource(configFile);
			if (url!=null) {
				f = new File(url.getFile());
			} else {
				f = null;
			}
			if (f==null || !f.exists()) {
				logger.warn("Configuration file {} not found.", configFile);
				return; // Don't configure from file.
			}
		}

		if (f==null) {
			logger.debug("Not using configuration file to init FortnoxClient");
			return;
		}
		
		try {
			logger.debug("Using configuration file {}.", configFile);
			clientList = FortnoxUtil.readFortnoxClientListFromFile(configFile);
		} catch (Exception e) {
			logger.error("Problem with reading configuration file {}.", configFile);
			e.printStackTrace();
			return;
		}

		FortnoxClientInfo fc = clientList.getFirstClient();
		
		String clientSecret = fc.getClientSecret();
		String accessToken = fc.getAccessToken();
		
		setAccessToken(accessToken, clientSecret);
		
		
	}
	
	/**
	 * 
	 * @param accessToken		The access token
	 * @param clientSecret		The client secret
	 */
	public void setAccessToken(String accessToken, String clientSecret) {
		m_accessToken = accessToken;
		m_clientSecret = clientSecret;
		if (accessToken==null || accessToken.trim().length()==0) {
			logger.warn("Empty accessToken");
		}
		if (clientSecret==null || clientSecret.trim().length()==0) {
			logger.warn("Empty clientSecret");
		}
	}
	
	/**
	 * Gets access token from an auth code (API-code) and client secret.
	 * 
	 * @param authCode			The auth code supplied by the Fortnox client.
	 * @param clientSecret		The secret to access the API.
	 * @return					The access token if successful.
	 * @throws Exception		If something goes wrong.
	 */
	public String getAccessToken(String authCode, String clientSecret) throws Exception {
		
		Map<String,String> headers = new TreeMap<String,String>();
		headers.put("Authorization-Code", authCode);
		headers.put("Client-Secret", clientSecret);
		
		StringBuffer result = callFortnox("", "", null, headers, null);
		
		StringReader strReader = new StringReader(result.toString());
		
		ErrorInformation err = checkIfError(result);
		if (err!=null) {
			throw new FortnoxException(err);
		}
		
		Authorization auth = JAXB.unmarshal(strReader, Authorization.class);
		
		m_accessToken = auth.getAccessToken();
		
		return m_accessToken;
		
	}

	/**
	 * Returns current access token.
	 * 
	 * @return	Current access token
	 */
	public String getAccessTokenCurrent() {
		return m_accessToken;
	}
	
	public StringBuffer postFortnox(String route, StringBuffer postContents) throws Exception {
		return callFortnox(route, null, postContents, null, "POST");
	}
	
	public StringBuffer putFortnox(String route, StringBuffer putContents) throws Exception {
		return callFortnox(route, null, putContents, null, "PUT");
	}
	
	public StringBuffer getFortnox(String route, StringBuffer putContents) throws Exception {
		return callFortnox(route, null, putContents, null, "GET");
	}
	
	public StringBuffer deleteFortnox(String route, StringBuffer deleteContents) throws Exception {
		return callFortnox(route, null, deleteContents, null, "DELETE");
	}
	
	private StringBuffer callFortnox(String cmd, String getStr, StringBuffer postContents) throws Exception {
		return (callFortnox(cmd, getStr, postContents, null, null));
	}
	
	/**
	 * Method for calling Fortnox web interface.
	 * NOTE! To call this method, the token and database must be set first.
	 * Returns a byte buffer as the result is treated as raw data (octetstream)
	 * 
	 * @param cmd				The command. For instance: set_contact.
	 * @param getStr			If the command is a "get" command. I e fetch information
	 * 							from Fortnox, only get-parameters are necessary.
	 * @param postContents		If an xml-struct must be posted, the xml struct is
	 * 							placed here.
	 * @param headers			Headers
	 * @return					The reply from the web server.
	 * @throws Exception
	 * 
	 * @see		setToken(String)
	 * @see		setDatabase(String)
	 */
	private ByteBuffer callFortnoxOctetStream(String cmd, String getStr, StringBuffer postContents, Map<String,String> headers, String method) throws Exception {
		ByteBuffer result = null;
		
		rateLimit();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// Create url
		String urlStr = m_baseUrl;
		if (cmd!=null)
			urlStr += cmd;
		// If we have a get command, append it to the URL.
		if (getStr!=null)
			urlStr = urlStr + getStr;
		HttpUriRequest request;
		
		// Create a headers map if it wasn't supplied
		if (headers==null) {
			headers = new TreeMap<String, String>();
		}
		
		boolean put = "PUT".equalsIgnoreCase(method);
		boolean delete = "DELETE".equalsIgnoreCase(method);
		if (postContents!=null) {
			// If there are xml content to be posted. Create a post/put request
			// and create a name value pair using 'xml' as the name.
			if (put) {
				request = new HttpPut(urlStr);
			} else if (delete) {
				request = new HttpDelete(urlStr);
			} else {
				request = new HttpPost(urlStr);
			}
			StringEntity entity = new StringEntity(postContents.toString(), HTTP.UTF_8);
			if (!put)
				((HttpPost)request).setEntity(entity);
			else 
				((HttpPut)request).setEntity(entity);
			
			headers.put("Content-Type", "application/xml");
			
			logger.debug("Posting: \n" + postContents + "\nto " + urlStr);
			
		} else {
			// If there are no xml content, just create a get/put request.
			if (put)
				request = new HttpPut(urlStr);
			else
				request = new HttpGet(urlStr);
			logger.debug((put ? "Putting" : "Getting url") + ": " + urlStr);
		}

		// Add access token and client secret if not already there
		if (m_accessToken!=null && !headers.containsKey("Access-Token")) {
			headers.put("Access-Token", m_accessToken);
		}
		if (m_clientSecret!=null && !headers.containsKey("Client-Secret")) {
			headers.put("Client-Secret", m_clientSecret);
		}

		// Set headers
		if (headers!=null) {
			for (String s : headers.keySet()) {
				request.setHeader(s, headers.get(s));
			}
		}
		
		// We want XML in return
		request.setHeader("Accept", "application/xml");
		
		// Read response
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		InputStream is = entity.getContent();
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = is.read(data, 0, data.length))!=-1) {
			buffer.write(data, 0, nRead);
		}
		
		buffer.flush();
		result = ByteBuffer.wrap(buffer.toByteArray());
		
		// Mark that the content is consumed.
		EntityUtils.consume(entity);

		logger.debug("Received octet-stream with " + buffer.size() + " bytes");
		
		// Max 3 requests per second.
		registerCall();
		
		// Max 16 requests per second. Call a sleep here
		// Thread.sleep(100);
		// Return response
		return(result);
	}
		
	
	/**
	 * Method for calling Fortnox web interface.
	 * NOTE! To call this method, the token and database must be set first.
	 * 
	 * @param cmd				The command. For instance: set_contact.
	 * @param getStr			If the command is a "get" command. I e fetch information
	 * 							from Fortnox, only get-parameters are necessary.
	 * @param postContents		If an xml-struct must be posted, the xml struct is
	 * 							placed here.
	 * @param headers			Headers
	 * @return					The reply from the web server.
	 * @throws Exception
	 * 
	 * @see		setToken(String)
	 * @see		setDatabase(String)
	 */
	private StringBuffer callFortnox(String cmd, String getStr, StringBuffer postContents, Map<String,String> headers, String method) throws Exception {
		StringBuffer result = new StringBuffer();
		
		rateLimit();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// Create url
		String urlStr = m_baseUrl;
		if (cmd!=null)
			urlStr += cmd;
		// If we have a get command, append it to the URL.
		if (getStr!=null)
			urlStr = urlStr + getStr;
		HttpUriRequest request;
		
		// Create a headers map if it wasn't supplied
		if (headers==null) {
			headers = new TreeMap<String, String>();
		}
		
		boolean put = "PUT".equalsIgnoreCase(method);
		boolean delete = "DELETE".equalsIgnoreCase(method);
		if (postContents!=null) {
			// If there are xml content to be posted. Create a post/put request
			// and create a name value pair using 'xml' as the name.
			if (put) {
				request = new HttpPut(urlStr);
			} else if (delete) {
				request = new HttpDelete(urlStr);
			} else {
				request = new HttpPost(urlStr);
			}
			StringEntity entity = new StringEntity(postContents.toString(), HTTP.UTF_8);
			if (!put)
				((HttpPost)request).setEntity(entity);
			else 
				((HttpPut)request).setEntity(entity);
			
			headers.put("Content-Type", "application/xml");
			
			logger.debug("Posting: \n" + postContents + "\nto " + urlStr);
			
		} else {
			// If there are no xml content, just create a get/put request.
			if (put)
				request = new HttpPut(urlStr);
			else
				request = new HttpGet(urlStr);
			logger.debug((put ? "Putting" : "Getting url") + ": " + urlStr);
		}

		// Add access token and client secret if not already there
		if (m_accessToken!=null && !headers.containsKey("Access-Token")) {
			headers.put("Access-Token", m_accessToken);
		}
		if (m_clientSecret!=null && !headers.containsKey("Client-Secret")) {
			headers.put("Client-Secret", m_clientSecret);
		}

		// Set headers
		if (headers!=null) {
			for (String s : headers.keySet()) {
				request.setHeader(s, headers.get(s));
			}
		}
		
		// We want XML in return
		request.setHeader("Accept", "application/xml");
		
		// Read response
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
		String line;
		while ((line=rd.readLine())!=null) {
			result.append(line + "\n");
		}
		// Mark that the content is consumed.
		EntityUtils.consume(entity);

		logger.debug("Received reply: \n" + result.toString());
		
		// Max 3 requests per second.
		registerCall();
		
		// Max 16 requests per second. Call a sleep here
		// Thread.sleep(100);
		// Return response
		return(result);
	}
	
	public org.notima.api.fortnox.entities3.Order getOrder(String orderNo) throws Exception {

		org.notima.api.fortnox.entities3.Order o = new org.notima.api.fortnox.entities3.Order();
		
		String getStr = URLEncoder.encode(orderNo, "UTF-8");
		StringBuffer result = callFortnox("/orders/" + getStr, null, null);
		
		ErrorInformation e = checkIfError(result);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
			o = JAXB.unmarshal(in, o.getClass());
	        return(o); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	public Orders getOrders(String filter) throws Exception {
		
		Orders r = getOrders(filter, 0);
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			Orders subset = getOrders(filter, currentPage+1);
			r.getOrderSubset().addAll(subset.getOrderSubset());
			currentPage = subset.getCurrentPage();
		}
		
		return r;
	}
	
	public Orders getOrders(String filter, int page) throws Exception {
		
		// Create request
		String getStr = "/orders/" + (filter!=null&&filter.trim().length()>0 ? "?filter=" + filter.trim() : "");
		String param = (page>0 ? ((filter!=null&&filter.trim().length()>0 ? "&" : "?") + "page=" + page) : null);
		
		StringBuffer result = callFortnox(getStr, param , null);
		ErrorInformation e = checkIfError(result);
		Orders r = new Orders();

		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
			r = JAXB.unmarshal(in, r.getClass());
			
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
		
	}

	/**
	 * Bookkeeps an invoice if not already bookkeeped.
	 * 
	 * @param invoiceNo			The invoice to bookkeep.
	 * @return					true if the action was successful.
	 * @throws Exception		If something goes wrong.
	 */
	public String bookkeepInvoice(String invoiceNo) throws Exception {
		
		return performAction(true, "invoice", invoiceNo, FortnoxClient3.ACTION_INVOICE_BOOKKEEP);		
		
	}
	
	/**
	 * Gets an invoice with specific invoice number
	 * 
	 * @param invoiceNo			Fortnox Invoice Number
	 * @return					The invoice, null if it doesn't exist.
	 * @throws Exception		If something fails
	 */
	public org.notima.api.fortnox.entities3.Invoice getInvoice(String invoiceNo) throws Exception { 
		
		org.notima.api.fortnox.entities3.Invoice c = new org.notima.api.fortnox.entities3.Invoice();
		// Create request
		String getStr = URLEncoder.encode(invoiceNo, "UTF-8");
		StringBuffer result = callFortnox("/invoices/" + getStr, null, null);
		
		ErrorInformation e = checkIfError(result);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        c = (org.notima.api.fortnox.entities3.Invoice)JAXB.unmarshal(in, Invoice.class); //NOI18N
	        return(c); 
		} else {
			if (FortnoxClient3.ERROR_CANT_FIND_INVOICE.equals(e.getCode())) {
				return null;
			}
			throw new FortnoxException(e);
		}
		
	}

	
	/**
	 * Read all financial years
	 * 
	 * @return				All customers.
	 * @throws Exception	If something goes wrong.
	 */
	public FinancialYears getFinancialYears() throws Exception {
		
		FinancialYears r = getFinancialYears(0);
		
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			FinancialYears subset = getFinancialYears(currentPage+1);
			r.getFinancialYearSubset().addAll(subset.getFinancialYearSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
		
	}
	
	/**
	 * A page of customers.
	 * 
	 * @param page			The page
	 * @return				A page of customers.
	 * @throws Exception	If something goes wrong.
	 */
	public FinancialYears getFinancialYears(int page) throws Exception {
		// Create request
		StringBuffer result = callFortnox("/financialyears/", (page>1 ? ("?page=" + page) : null), null);
		ErrorInformation e = checkIfError(result);
		FinancialYears r = new FinancialYears();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        r = JAXB.unmarshal(in,  r.getClass()); //NOI18N
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
	}
	
	
	/**
	 * Gets financial year for given date. 
	 * 
	 * @param forDate		If null, current date is used.
	 * @return				The financial year details for the given date.
	 * @throws Exception	If something fails.
	 */
	public FinancialYearSubset getFinancialYear(Date forDate) throws Exception {
		
		FinancialYearSubset result = null;
		
		if (forDate==null) {
			// Use current time
			forDate = Calendar.getInstance().getTime();
		}
		
		String getStr = "?date=" + URLEncoder.encode(s_dfmt.format(forDate), "UTF-8");
		StringBuffer out = callFortnox("/financialyears/", getStr, null);
		
		ErrorInformation e = checkIfError(out);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        FinancialYears list = (org.notima.api.fortnox.entities3.FinancialYears)JAXB.unmarshal(in, FinancialYears.class); //NOI18N

	        if (list.getTotalResources()<1) {
	        	throw new Exception("No fiscal year for " + forDate);
	        }
	        result = list.getFinancialYearSubset().get(0);
	        
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	/**
	 * Gets a voucher.
	 * 
	 * @param yearId				The id of the fiscal year
	 * @param series				Voucher series
	 * @param voucherNumber			Number
	 * @return						The voucher
	 * @throws Exception			If something fails
	 * @throws FortnoxException		If Fortnox returns an error
	 */
	public Voucher getVoucher(int yearId, String series, int voucherNumber) throws Exception, FortnoxException {

		Voucher result = null;
		String getStr = URLEncoder.encode(series,"UTF-8") + "/" + voucherNumber + "?financialyear=" + yearId;
		StringBuffer out = callFortnox("/vouchers/", getStr, null);
		
		ErrorInformation e = checkIfError(out);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        result = (org.notima.api.fortnox.entities3.Voucher)JAXB.unmarshal(in, Voucher.class); //NOI18N
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}

	/**
	 * Returns an account map for a given date.
	 * This map is excellent to check if a given accounts exists and is active before trying to post to
	 * it.
	 * 
	 * @param  acctDate		If null, current date is used.
	 * @return	A map of account subsets.
	 * @throws Exception If something goes wrong.
	 */
	public Map<String, AccountSubset> getAccountMap(Date acctDate) throws Exception {
		
		Map<String, AccountSubset> acctMap = new TreeMap<String, AccountSubset>();
		
		FinancialYearSubset fy = getFinancialYear(acctDate);
		Accounts accounts = getAccounts(fy.getId());
		
		for (AccountSubset a : accounts.getAccountSubset()) {
			acctMap.put(a.getNumber().toString(), a);
		}
		
		return acctMap;
		
	}
	
	
	/**
	 * Returns all accounts for a given year id
	 * 
	 * @param yearId			The id of the year.
	 * @return					The chart of accounts for the given year.
	 * @throws Exception		If something goes wrong.
	 */
	public Accounts getAccounts(int yearId) throws Exception {
		
		Accounts r = getAccounts(yearId, 0);
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			Accounts subset = getAccounts(yearId, currentPage+1);
			r.getAccountSubset().addAll(subset.getAccountSubset());
			currentPage = subset.getCurrentPage();
		}
		
		return r;
	}
	
	/**
	 * Reads accounts on page
	 * 
	 * @param yearId			The year for the chart of accounts.
	 * @param page				The page.
	 * @return					Accounts.
	 * @throws Exception		If something goes wrong.
	 */
	public Accounts getAccounts(int yearId, int page) throws Exception {

		Accounts result = null;
		
		String getStr = "?financialyear=" + yearId + (page>1 ? ("&page=" + page) : "");
		StringBuffer out = callFortnox("/accounts", getStr, null);
		
		ErrorInformation e = checkIfError(out);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        result = (org.notima.api.fortnox.entities3.Accounts)JAXB.unmarshal(in, Accounts.class); //NOI18N
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	

	/**
	 * Reads a specific account.
	 * 
	 * @param yearId			The year for the chart of accounts.
	 * @param accountNo			The account number
	 * @return					An account record.
	 * @throws Exception		If something goes wrong.
	 */
	public Account getAccount(int yearId, int accountNo) throws Exception {

		Account result = null;
		
		String getStr = accountNo + (yearId!=0 ? "?financialyear=" + yearId : "");
		StringBuffer out = callFortnox("/accounts/", getStr, null);
		
		ErrorInformation e = checkIfError(out);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        result = (org.notima.api.fortnox.entities3.Account)JAXB.unmarshal(in, Account.class); //NOI18N
	        return(result); 
		} else {
			if (ERROR_ACCOUNT_NOT_FOUND.equals(e.getCode())) {
				return null;
			} else {
				throw new FortnoxException(e);
			}
		}
		
	}

	/**
	 * Updates given account.
	 * 
	 * @param yearId			The year id to use for the chart of the accounts.
	 * @param account			The account structure.
	 * @return					The updated account structure.
	 * @throws Exception		If something goes wrong.
	 */
	public Account updateAccount(int yearId, Account account) throws Exception {
		
		Account result = null;
		
		String putStr = account.getNumber() + (yearId!=0 ? "?financialyear=" + yearId : "");
		
		StringWriter xml = new StringWriter();
        JAXB.marshal(account, xml);
        
		StringBuffer out = null;
		
		if (account.getUrl()!=null) {
			out = putFortnox("/accounts/" + putStr, xml.getBuffer());
		} else {
			out = postFortnox("/accounts" + (yearId!=0 ? "?financialyear=" + yearId : ""), xml.getBuffer());
		}
		ErrorInformation e = checkIfError(out);

		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        result = (org.notima.api.fortnox.entities3.Account)JAXB.unmarshal(in, Account.class); //NOI18N
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	
	

	/**
	 * Return preDefinedAccount with specific name.
	 * 
	 * @param pname			The name of the predefined account.
	 * @return				The predefined account itself.
	 * @throws Exception	If something goes wrong.
	 */
	public PreDefinedAccount getPreDefinedAccount(String pname) throws Exception {

		if (pname==null) return null;
		
		PreDefinedAccount result = null;
		String getStr = pname.toUpperCase();
		StringBuffer out = callFortnox("/predefinedaccounts/", getStr, null);
		
		ErrorInformation e = checkIfError(out);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        result = (org.notima.api.fortnox.entities3.PreDefinedAccount)JAXB.unmarshal(in, PreDefinedAccount.class); //NOI18N
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	
	/**
	 * Returns all predefined accounts
	 * 
	 * @return				Predefined accounts (if on many pages, they are concatinated).
	 * @throws Exception	If something goes wrong.
	 */
	public PreDefinedAccounts getPreDefinedAccounts() throws Exception {
		
		PreDefinedAccounts r = getPreDefinedAccounts(0);
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			PreDefinedAccounts subset = getPreDefinedAccounts(currentPage+1);
			r.getPreDefinedAccountSubset().addAll(subset.getPreDefinedAccountSubset());
			currentPage = subset.getCurrentPage();
		}
		
		return r;
	}
	

	/**
	 * 
	 * @return	Returns all predefined accounts as an easy to access map.
	 * @throws Exception		If something goes wrong.
	 */
	public Map<String,PreDefinedAccountSubset> getPredefinedAccountMap() throws Exception {
		
		Map<String,PreDefinedAccountSubset> result = new TreeMap<String,PreDefinedAccountSubset>();
		PreDefinedAccounts pdas = getPreDefinedAccounts();
		List<PreDefinedAccountSubset> pdaList = pdas.getPreDefinedAccountSubset();
		for (PreDefinedAccountSubset ps : pdaList) {
			result.put(ps.getName(), ps);
		}
		return result;
	}
	
	/**
	 * Reads predefined accounts on page. The predefined accounts are defined in
	 * various settings in Fortnox.
	 * 
	 * @param page			The page
	 * @return				A page of predefined accounts.
	 * @throws Exception	If something goes wrong.
	 */
	public PreDefinedAccounts getPreDefinedAccounts(int page) throws Exception {

		PreDefinedAccounts result = null;
		
		String getStr = (page>1 ? ("?page=" + page) : "");
		StringBuffer out = callFortnox("/predefinedaccounts", getStr, null);
		
		ErrorInformation e = checkIfError(out);
		
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(out.toString().getBytes()), "UTF-8"));
	        result = (org.notima.api.fortnox.entities3.PreDefinedAccounts)JAXB.unmarshal(in, PreDefinedAccounts.class); //NOI18N
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}

	/**
	 * Return default revenue account map for given accounting date.
	 * Null as acctDate will return today.
	 * 
	 * @param 				acctDate	The date we want to use to determine the accounts (ie Fiscal Year).
	 * @return				A map of revenue accounts.
	 * @throws Exception	If something goes wrong.
	 */
	public Map<String, Integer> getRevenueAccountMap(Date acctDate) throws Exception {
		FinancialYearSubset fy = getFinancialYear(acctDate);
		return getRevenueAccountMap(fy.getId());
	}
	
	/**
	 * Gets default revenue account map for supplied fiscal year.
	 * 
	 * The predefined accounts are checked first. Second the chart of accounts
	 * is looked through to find the first available accounts with VAT-codes for
	 * revenue.
	 * 
	 * @see	getPreDefinedAccount
	 * 
	 * @param 		year		The year to use for COA.
	 * @return					A map of revenue accounts.
	 * @throws Exception 		If something goes wrong.
	 */
	public Map<String, Integer> getRevenueAccountMap(int year) throws Exception {

		Map<String,Integer> result = new TreeMap<String, Integer>();
		
		PreDefinedAccount pa;
		
		for (String s : PREDEFINED_REV_ACCT) {
			pa = this.getPreDefinedAccount(s);
			if (pa!=null) {
				result.put(s, pa.getAccount());
			}
		}

		// Look for VAT accounts
		Accounts accounts = getAccounts(year);
		
		SortedSet<Integer> mp2 = new TreeSet<Integer>();
		SortedSet<Integer> mp3 = new TreeSet<Integer>();
		SortedSet<Integer> mp0 = new TreeSet<Integer>(); // VAT but zero rate 0%
		SortedSet<Integer> mpNoVAT = new TreeSet<Integer>(); // No VAT
		SortedSet<Integer> exportServices = new TreeSet<Integer>();	
		
		for (AccountSubset as : accounts.getAccountSubset()) {

			// Only check revenue accounts
			if (!as.getNumber().toString().startsWith("3")) {
				continue;
			}
			if (!as.getActive().booleanValue())
				continue;
			
			if ("MP2".equals(as.getVATCode())) {
				mp2.add(as.getNumber());
			} else if ("MP3".equals(as.getVATCode())) {
				mp3.add(as.getNumber());
			} else if ("MF".equals(as.getVATCode())) {
				mp0.add(as.getNumber());
			} else if (as.getVATCode()==null || as.getVATCode().trim().length()==0) {
				mpNoVAT.add(as.getNumber());
			} else if ("ÖTEU".equals(as.getVATCode())) {
				exportServices.add(as.getNumber());
			}
			
		}
		
		if (mp2.size()>0) {
			result.put(PREDEFINED_REVENUE_VAT_ACCT[0], mp2.first());
		}
		if (mp3.size()>0) {
			result.put(PREDEFINED_REVENUE_VAT_ACCT[1], mp3.first());
		}
		if (mp0.size()>0) {
			result.put(PREDEFINED_REVENUE_VAT_ACCT[2], mp0.first());
		}
		if (mpNoVAT.size()>0) {
			result.put(PREDEFINED_REVENUE_VAT_ACCT[3], mpNoVAT.first());
		}
		if (exportServices.size()>0) {
			result.put(PREDEFINED_REVENUE_VAT_ACCT[4], exportServices.first());
		}
		
		return result;
		
	}
	
	
	/**
	 * General action call.
	 * 
	 * @param put				Use put for the action (instead of get)
	 * @param document			The document type on which to perform the action (invoice, order, invoicepayment) 
	 * @param documentNo		The document number.
	 * @param action			The action to perform.
	 * @return					The result of the action.
	 * @throws Exception		If something goes wrong.
	 */
	public String performAction(boolean put, String document, String documentNo, String action) throws Exception {
		
		if (!document.endsWith("s"))
			document += "s";
		String getStr = URLEncoder.encode(documentNo, "UTF-8");
		String url = "/" + document + "/" + getStr + "/" + action;
		StringBuffer result = put ? 
				putFortnox(url, null)
				:
				getFortnox(url, null);

		ErrorInformation e = checkIfError(result);
		if (e==null) {
			return result.toString();
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	/**
	 * Performs action on invoice
	 * 
	 * @param invoiceNo			The invoice on which the action should be performed.
	 * @param action			The action.
	 * @return					The result of the action.
	 * @throws Exception		If something goes wrong.
	 */
	public String invoiceGetAction(String invoiceNo, String action) throws Exception {

		return performAction(false,"invoice", invoiceNo, action);
		
	}

	public Order orderPutAction(String orderNo, String action) throws Exception {
		
		String result = performAction(true, "orders", orderNo, action);

		ErrorInformation e = checkIfError(new StringBuffer(result));
		Order r = new Order();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
			r = JAXB.unmarshal(in, r.getClass());
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	/**
	 * Return customer invoices with the given filter.
	 * 
	 * @param filter			The filter to use.
	 * @return					A list of invoices. If many pages, the result is concatenated.
	 * @throws Exception		If something goes wrong.
	 */
	public Invoices getInvoices(String filter) throws Exception {
		
		Invoices r = getInvoices(filter, 0);
		
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			Invoices subset = getInvoices(filter, currentPage+1);
			r.getInvoiceSubset().addAll(subset.getInvoiceSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
	}
	
	/**
	 * Returns a page of invoices with given filter(s) applied.
	 * 
	 * @param filter		Filter
	 * @param page			The page of the result set
	 * @return				A page 
	 * @throws Exception	If something goes wrong.
	 */
	public Invoices getInvoices(String filter, int page) throws Exception {
		// Include the filter word if the filter is not a global search (containing key/values)
		String filterWord = filter!=null && filter.contains("=") ? "" : "filter=";
		
		// Create request
		StringBuffer result = callFortnox("/invoices/" + (filter!=null&&filter.trim().length()>0 ? "?" + filterWord + filter.trim() : "") , (page>1 ? ((filter!=null&&filter.trim().length()>0 ? "&" : "?") + "page=" + page) : null), null);
		ErrorInformation e = checkIfError(result);
		Invoices r = new Invoices();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
			r = JAXB.unmarshal(in, r.getClass());
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
	}

	/**
	 * Read all customers
	 * 
	 * @return				All customers.
	 * @throws Exception	If something goes wrong.
	 */
	public Customers getCustomers() throws Exception {
		
		Customers r = getCustomers(0);
		
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			Customers subset = getCustomers(currentPage+1);
			r.getCustomerSubset().addAll(subset.getCustomerSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
		
	}
	
	/**
	 * A page of customers.
	 * 
	 * @param page			The page
	 * @return				A page of customers.
	 * @throws Exception	If something goes wrong.
	 */
	public Customers getCustomers(int page) throws Exception {
		// Create request
		StringBuffer result = callFortnox("/customers/", (page>1 ? ("?page=" + page) : null), null);
		ErrorInformation e = checkIfError(result);
		Customers r = new Customers();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        r = JAXB.unmarshal(in,  r.getClass()); //NOI18N
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
	}
	
	/**
	 * Returns a specific mode of payment.
	 * 
	 * @param mode		The string representation of the mode of payment.
	 * @return			A mode of payment subset if it exists. Otherwise null
	 * @throws Exception If something goes wrong.
	 */
	public ModeOfPaymentSubset getModeOfPayment(String mode) throws Exception {

		if (mode==null) return null;
		
		ModesOfPayments modes = getModesOfPayments();
		if (modes==null || modes.getModeOfPaymentSubset()==null)
			return null;
		
		ModeOfPaymentSubset result = null;
		
		for (ModeOfPaymentSubset m : modes.getModeOfPaymentSubset()) {
			if (mode.equalsIgnoreCase(m.getCode())) {
				result = m;
				break;
			}
		}
		
		return result;
	}

	/**
	 * Sets a mode of payment. Creates it if it doesn't exist.
	 * 
	 * @param mp			The mode of payment
	 * @return				The created / updated mode of payment.
	 * @throws Exception	If something goes wrong.
	 */
	public ModeOfPayment setModeOfPayment(ModeOfPayment mp) throws Exception {
		
		if (mp==null) return null;

		boolean createNew = mp.getUrl()==null;
		
		StringWriter result = new StringWriter();
		JAXB.marshal(mp, result);
        
        StringBuffer output = callFortnox("/modesofpayments" + 
        		(!createNew ? "/" + mp.getCode() : "")
        		, null,
        		result.getBuffer(),
        		null, // Headers
        		(!createNew ? "put" : null));
        
        ErrorInformation e = checkIfError(output);

        ModeOfPayment c = new ModeOfPayment();
        
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toString().getBytes()), "UTF-8"));
	        c = JAXB.unmarshal(in, c.getClass());
	        return(c); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	
	/**
	 * Gets all modes of payments
	 * 
	 * @return		All modes of payments
	 * @throws Exception	If something goes wrong.
	 */
	public ModesOfPayments getModesOfPayments() throws Exception {

		ModesOfPayments r = getModesOfPayments(0);
		
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			ModesOfPayments subset = getModesOfPayments(currentPage+1);
			r.getModeOfPaymentSubset().addAll(subset.getModeOfPaymentSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
		
	}
	
	/**
	 * Gets a page modes of payments
	 *  
	 * @param page			The page to get
	 * @return	A ModesOfPayments struct containing a list of ModeOfPaymentSubset
	 * @throws Exception	if something fails
	 */
	public ModesOfPayments getModesOfPayments(int page) throws Exception {
		// Create request
		StringBuffer result = callFortnox("/modesofpayments/", (page>1 ? ("?page=" + page) : null), null);
		ErrorInformation e = checkIfError(result);
		ModesOfPayments r = new ModesOfPayments();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        r = JAXB.unmarshal(in,  r.getClass()); //NOI18N
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
	}
	
	
	/**
	 * Sets a voucher series. Creates it if it doesn't exist.
	 * 
	 * @param vs			The voucher series
	 * @param yearId		The yearId that the series should belong to. Null will be default year.
	 * @return				The created / updated voucher series.
	 * @throws Exception	If something goes wrong.
	 */
	public VoucherSeries setVoucherSeries(VoucherSeries vs, Integer yearId) throws Exception {
		
		if (vs==null) return null;

		boolean createNew = vs.getUrl()==null;
		
		StringWriter result = new StringWriter();
		JAXB.marshal(vs, result);
        
        StringBuffer output = callFortnox("/voucherseries" + 
        		(!createNew ? "/" + vs.getCode() : "") + (yearId!=null && yearId!=0 ? "?financialyear=" + yearId : "")
        		, null,
        		result.getBuffer(),
        		null, // Headers
        		(!createNew ? "put" : null));
        
        ErrorInformation e = checkIfError(output);

        VoucherSeries c = new VoucherSeries();
        
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toString().getBytes()), "UTF-8"));
	        c = JAXB.unmarshal(in, c.getClass());
	        return(c); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	
	/**
	 * Returns a specific voucher series
	 * 
	 * @param code		The string representation of the voucher series
	 * @return			A voucher series subset if it exists. Otherwise null
	 * @throws Exception If something goes wrong.
	 */
	public VoucherSeriesSubset getVoucherSeries(String code, Integer yearId) throws Exception {

		if (code==null) return null;
		
		VoucherSeriesCollection series = getVoucherSeriesCollection(yearId);
		if (series==null || series.getVoucherSeriesSubset()==null)
			return null;
		
		VoucherSeriesSubset result = null;
		
		for (VoucherSeriesSubset m : series.getVoucherSeriesSubset()) {
			if (code.equalsIgnoreCase(m.getCode())) {
				result = m;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Gets all voucher series collection pages
	 * 
	 * @return		All voucher series collections
	 * @throws Exception
	 */
	public VoucherSeriesCollection getVoucherSeriesCollection(Integer yearId) throws Exception {

		VoucherSeriesCollection r = getVoucherSeriesCollection(yearId, 0);
		
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			VoucherSeriesCollection subset = getVoucherSeriesCollection(yearId, currentPage+1);
			r.getVoucherSeriesSubset().addAll(subset.getVoucherSeriesSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
		
	}
	
	
	/**
	 * Gets a page of VoucherSeriesCollection
	 *  
	 * @param page			The page to get
	 * @return	A VoucherSeriesCollection struct containing a list of VoucherSeriesSubset
	 * @throws Exception	if something fails
	 */
	public VoucherSeriesCollection getVoucherSeriesCollection(Integer yearId, int page) throws Exception {
		// Create request
		String param = page > 1 ? "page=" + page : "";
		if (yearId!=null && yearId!=0) {
			if (param.length()>0) {
				param += "&";
			}
			param += "financialyear=" + yearId;
		}
		StringBuffer result = callFortnox("/voucherseries/", (param.length()>0 ? "?" + param : null), null);
		ErrorInformation e = checkIfError(result);
		VoucherSeriesCollection r = new VoucherSeriesCollection();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        r = JAXB.unmarshal(in,  r.getClass()); //NOI18N
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
	}
	
	
	
	/**
	 * Read all suppliers
	 * 
	 * @return		A suppliers struct containing a list of SupplierSubset
	 * @throws Exception	if something fails
	 */
	public Suppliers getSuppliers() throws Exception {
		
		Suppliers r = getSuppliers(0);
		
		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			// Pause not to exceed call limit
			Thread.sleep(100);
			Suppliers subset = getSuppliers(currentPage+1);
			r.getSupplierSubset().addAll(subset.getSupplierSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
		
	}

	/**
	 * Gets a page of suppliers.
	 *  
	 * @param page			The page to get
	 * @return	A suppliers struct containing a list of SupplierSubset
	 * @throws Exception	if something fails
	 */
	public Suppliers getSuppliers(int page) throws Exception {
		// Create request
		StringBuffer result = callFortnox("/suppliers/", (page>1 ? ("?page=" + page) : null), null);
		ErrorInformation e = checkIfError(result);
		Suppliers r = new Suppliers();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        r = JAXB.unmarshal(in,  r.getClass()); //NOI18N
	        return(r);
	        
		} else {
			throw new FortnoxException(e);
		}
	}
	

	/**
	 * Creates or updates a supplier
	 * 
	 * @param supplier		The supplier to persist.
	 * @return  			The supplier.
	 * @throws Exception	If something goes wrong.
	 */
	public Supplier setSupplier(Supplier supplier, boolean createNew) throws Exception {
		
		StringWriter result = new StringWriter();
		JAXB.marshal(supplier, result);
        
        StringBuffer output = callFortnox("/suppliers" + 
        		(!createNew ? "/" + supplier.getSupplierNumber() : "")
        		, null,
        		result.getBuffer(),
        		null, // Headers
        		(!createNew ? "put" : null));
        
        ErrorInformation e = checkIfError(output);

        Supplier c = new Supplier();
        
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toString().getBytes()), "UTF-8"));
	        c = JAXB.unmarshal(in, c.getClass());
	        return(c); 
		} else {
			if (ERROR_CANT_FIND_CUSTOMER.equals(e.getCode())) {
				return null;
			} else {
				throw new FortnoxException(e);
			}
		}
        
	}
	
	/**
	 * Reads a vendor / supplier from database using supplierNo.
	 * If supplier doesn't exist, null is returned.
	 * 
	 * @param supplierNo		The supplier number (Fortnox's supplier number)
	 * @return	Supplier if exists. Otherwise null.
	 * @throws Exception	If something goes wrong
	 */
	public Supplier getSupplierBySupplierNo(String supplierNo) throws Exception {
		
		if (supplierNo==null) 
			return null;
		
		Supplier c = new Supplier();
		// Create request
		String getStr = URLEncoder.encode(supplierNo, "UTF-8");
		StringBuffer result = callFortnox("/suppliers/", getStr, null);
		
		ErrorInformation e = checkIfError(result);
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        c = JAXB.unmarshal(in, c.getClass());
	        return(c); 
		} else {
			if (ERROR_CANT_FIND_CUSTOMER.equals(e.getCode())) {
				return null;
			} else {
				throw new FortnoxException(e);
			}
		}
		
	}
	
	/**
	 * Read a supplier from database using tax id. All suppliers are read at the first call.
	 * This method is synchronized to prevent other processes from running this method simultaneously. 
	 * 
	 * To reset the supplier-to-tax-id map (re-read from fortnox, call resetSupplierMaps
	 * 
	 * @param	taxId			The tax id we're searching for
	 * @param	isCompany		If we're looking for a company (not private person).
	 * @see		resetSupplierMap
	 * @return 	Supplier, null if not found.
	 * @throws 	Exception	if something goes wrong.
	 */
	public synchronized Supplier getSupplierByTaxId(String taxId, boolean isCompany) throws Exception {
		taxId = formatTaxId(taxId, isCompany);
		if (m_supplierTaxIdLookupMap==null) {
			m_supplierTaxIdLookupMap = new TreeMap<String, SupplierSubset>();
			
			Suppliers suppliers = getSuppliers();
			if (suppliers!=null && suppliers.getSupplierSubset()!=null) {
				for(SupplierSubset c: suppliers.getSupplierSubset()) {
					if (c.getOrganisationNumber()!=null && c.getOrganisationNumber().trim().length()>0) {
						m_supplierTaxIdLookupMap.put(c.getOrganisationNumber(), c);
						logger.debug("Adding '" + c.getOrganisationNumber() + "' " + c.getName() + " supplierNo " + c.getSupplierNumber());
					}
				}
			}
			while(suppliers.getTotalPages()>suppliers.getCurrentPage()) {
				// Pause not to exceed call limit
				Thread.sleep(100);
				suppliers = getSuppliers(suppliers.getCurrentPage()+1);
				
				if (suppliers!=null && suppliers.getSupplierSubset()!=null) {
					for(SupplierSubset c: suppliers.getSupplierSubset()) {

						if (c.getOrganisationNumber()!=null && c.getOrganisationNumber().trim().length()>0) {
							m_supplierTaxIdLookupMap.put(c.getOrganisationNumber(), c);
							logger.debug("Adding '" + c.getOrganisationNumber() + "' " + c.getName() + " supplierno " + c.getSupplierNumber());
						}
						
					}
				}
				
			}
			
		}
		SupplierSubset contact = m_supplierTaxIdLookupMap.get(taxId);
		if (contact==null && taxId.length()==13) {
			contact = m_supplierTaxIdLookupMap.get(taxId.substring(2));
		}
		if (contact==null) {
			logger.warn("Could not find '" + taxId + "'");
			return null;
		} else {
			return getSupplierBySupplierNo(contact.getSupplierNumber());
		}
	}
	
	
	/**
	 * Creates or updates a payment (customer)
	 * 
	 * @param payment		The payment to be updated.
	 * @return				The invoice payment.
	 * @throws Exception	If something goes wrong.
	 */
	public InvoicePayment setCustomerPayment(InvoicePayment payment) throws Exception {

		StringWriter result = new StringWriter();
		JAXB.marshal(payment, result);
		
        StringBuffer output = callFortnox("/invoicepayments" + 
        		(payment.getNumber()!=null && payment.getNumber()>0 ? "/" + payment.getNumber() : ""), 
        		null, 
        		result.getBuffer(),
        		null, // Headers
        		(payment.getNumber()!=null && payment.getNumber()>0 ? "put" : null) // method
        		);
		
        InvoicePayment out = null;
        
        ErrorInformation e = checkIfError(output);
        
        if (e!=null) {
        	logger.error(result.toString() + " : " + e.getMessage());
        	throw new FortnoxException(e);
        } else {
	        StringReader reader = new StringReader(output.toString());
	        if (output!=null && output.length()>0) {
	        	// Try to create invoice payment
	        	out = JAXB.unmarshal(reader, InvoicePayment.class);
	        }
	        return out;
        }
		
	}
	
	
	/**
	 * Creates or updates an invoice.
	 * 
	 * @param invoice			The invoice to be updated.
	 * @throws Exception		If something goes wrong.
	 * @return	The invoice.
	 */
	public Invoice setInvoice(Invoice invoice) throws Exception {
		
		StringWriter result = new StringWriter();
        
        // Remove totals from invoice lines (read only)
        for (InvoiceRow ir : invoice.getInvoiceRows().getInvoiceRow()) {
        	ir.setTotal(null);
        	// Replace non-allowed characters and truncate if description is too long
        	if (ir.getDescription()!=null) {
        		ir.setDescription(ir.getDescription().replace('|', ';'));
        		ir.setDescription(ir.getDescription().substring(0, Math.min(ir.getDescription().length(), 50)));
        		ir.setDescription(FortnoxUtil.removeNonAllowedCharacters(ir.getDescription()));
        	}
        	if (!useArticles) {
        		ir.setArticleNumber(null);
        	}
        }
        
		JAXB.marshal(invoice, result);
       
        StringBuffer output = callFortnox("/invoices" + 
        		(invoice.getDocumentNumber()!=null && invoice.getDocumentNumber().trim().length()>0 ? "/" + invoice.getDocumentNumber().trim() : ""), 
        		null, 
        		result.getBuffer(),
        		null, // Headers
        		(invoice.getDocumentNumber()!=null && invoice.getDocumentNumber().trim().length()>0 ? "put" : null) // method
        		);
		
        Invoice out = null;
        
        ErrorInformation e = checkIfError(output);
        
        if (e!=null) {
        	logger.error(result.toString() + " : " + e.getMessage());
        	throw new FortnoxException(e);
        } else {
	        StringReader reader = new StringReader(output.toString());
	        if (output!=null && output.length()>0) {
	        	// Try to create invoice
	        	out = JAXB.unmarshal(reader, Invoice.class);
	        }
	        return out;
        }
        
	}

	/**
	 * Creates or updates a voucher.
	 * 
	 * @param voucher			The voucher to be updated / created.
	 * @throws Exception		If something goes wrong.
	 * @throws FortnoxException	Fortnox error if occured.
	 * @return 					The voucher created / updated.
	 */
	public Voucher setVoucher(Voucher voucher) throws FortnoxException, Exception {
		
		StringWriter result = new StringWriter();

        JAXB.marshal(voucher, result);
       
        StringBuffer output = callFortnox("/vouchers" + 
        		(voucher.getVoucherNumber()!=null && voucher.getVoucherNumber()>0 ? "/" + voucher.getVoucherNumber() : ""), 
        		null, 
        		result.getBuffer(),
        		null, // Headers
        		(voucher.getVoucherNumber()!=null && voucher.getVoucherNumber()>0 ? "put" : null) // method
        		);
		
        ErrorInformation e = checkIfError(output);
        
        Voucher out = null;
        if (e!=null) {
        	logger.error(result.toString() + " : " + e.getMessage());
        	throw new FortnoxException(e);
        } else {
	        StringReader reader = new StringReader(output.toString());
	        if (output!=null && output.length()>0) {
	        	// Try to create invoice
	        	out = JAXB.unmarshal(reader, Voucher.class);
	        }
	        return out;
        }
        
	}

	/**
	 * Creates a file voucher connection.
	 * 
	 * @param fileId			The id of the uploaded file.
	 * @param voucherNo			The voucher number to attach to.
	 * @param voucherSeries		The voucher series of the number to attach to.
	 * @param financialYearDate	The financial year of the voucher number. If null, current year is used.
	 * @return		If successful, a voucher file connection.
	 * @throws Exception 		If something goes wrong
	 */
	public VoucherFileConnection setVoucherFileConnection(String fileId, String voucherNo, String voucherSeries, Date financialYearDate) throws Exception {
		
		VoucherFileConnection req = new VoucherFileConnection();
		
		req.setFileId(fileId);
		req.setVoucherNumber(voucherNo);
		req.setVoucherSeries(voucherSeries);
		
		StringWriter reqstr = new StringWriter();

		if (financialYearDate==null) {
			financialYearDate = Calendar.getInstance().getTime();
		}
		
		JAXB.marshal(req, reqstr);
		
        StringBuffer output = callFortnox("/voucherfileconnections?financialyeardate=" + FortnoxClient3.s_dfmt.format(financialYearDate), 
        		null, 
        		reqstr.getBuffer(),
        		null, // Headers
        		"post" // method
        		);
		
        ErrorInformation e = checkIfError(output);
        
        VoucherFileConnection out = null;
        if (e!=null) {
        	logger.error(req.toString() + " : " + e.getMessage());
        	throw new FortnoxException(e);
        } else {
	        StringReader reader = new StringReader(output.toString());
	        if (output!=null && output.length()>0) {
	        	// Try to create invoice
	        	out = JAXB.unmarshal(reader, VoucherFileConnection.class);
	        }
	        return out;
        }

		
	}
	
	
	/**
	 * Creates or updates a customer
	 * 
	 * @param customer				The customer to be created / updated.
	 * @throws Exception			If something goes wrong.
	 * @return 			The customer created / updated.
	 */
	public Customer setCustomer(Customer customer) throws Exception {
		
		StringWriter result = new StringWriter();
        JAXB.marshal(customer, result);
        
        StringBuffer output = callFortnox("/customers", null, result.getBuffer());
        
        ErrorInformation e = checkIfError(output);

        Customer c = new Customer();
        
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toString().getBytes()), "UTF-8"));
	        c = JAXB.unmarshal(in, c.getClass());
	        
	        // If there's an existing customer map, add/put this customer to that map
	        if (m_customerTaxIdLookupMap!=null) {
	        	CustomerSubset cs = m_customerTaxIdLookupMap.get(c.getOrganisationNumber());
	        	if (cs==null) {
	        		cs = new CustomerSubset(c);
	        		m_customerTaxIdLookupMap.put(c.getOrganisationNumber(), cs);
	        	}
	        }
	        
	        return(c); 
		} else {
			if (ERROR_CANT_FIND_CUSTOMER.equals(e.getCode())) {
				return null;
			} else {
				throw new FortnoxException(e);
			}
		}
        
	}
	
	/**
	 * Reads a customer / vendor from database using custNo
	 * If customer doesn't exist, null is returned.
	 * 
	 * @param custNo			The customer's customer number (in Fortnox)
	 * @return					The customer.
	 * @throws Exception		If something goes wrong.
	 */
	public Customer getCustomerByCustNo(String custNo) throws Exception {
		
		if (custNo==null) 
			return null;
		
		Customer c = new Customer();
		// Create request
		String getStr = URLEncoder.encode(custNo, "UTF-8");
		StringBuffer result = callFortnox("/customers/", getStr, null);
		
		ErrorInformation e = checkIfError(result);
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        c = JAXB.unmarshal(in, c.getClass());
	        return(c); 
		} else {
			if (ERROR_CANT_FIND_CUSTOMER.equals(e.getCode())) {
				return null;
			} else {
				throw new FortnoxException(e);
			}
		}
		
	}
	
	/**
	 * Read a customer from database using tax id. All customers are read at the first time.
	 * This method is synchronized to prevent other processes from running this method simultaneously. 
	 * 
	 * To reset the customer-to-tax-id map (re-read from fortnox, call resetContactMaps
	 * 
	 * @param	taxId			The tax id we're searching for
	 * @param	isCompany		If we're looking for a company (not private person).
	 * @see		resetCustomerMap
	 * @return 	The customer. Null if not found.
	 * @throws	Exception		If something goes wrong.
	 */
	public synchronized Customer getCustomerByTaxId(String taxId, boolean isCompany) throws Exception {
		taxId = formatTaxId(taxId, isCompany);
		if (m_customerTaxIdLookupMap==null) {
			m_customerTaxIdLookupMap = new TreeMap<String, CustomerSubset>();
			
			Customers contacts = getCustomers();
			if (contacts!=null && contacts.getCustomerSubset()!=null) {
				for(CustomerSubset c: contacts.getCustomerSubset()) {
					if (c.getOrganisationNumber()!=null && c.getOrganisationNumber().trim().length()>0) {
						m_customerTaxIdLookupMap.put(c.getOrganisationNumber(), c);
						logger.debug("Adding '" + c.getOrganisationNumber() + "' " + c.getName() + " custno " + c.getCustomerNumber());
					}
				}
			}
			while(contacts.getTotalPages()>contacts.getCurrentPage()) {
				// Pause not to exceed call limit
				Thread.sleep(100);
				contacts = getCustomers(contacts.getCurrentPage()+1);
				
				if (contacts!=null && contacts.getCustomerSubset()!=null) {
					for(CustomerSubset c: contacts.getCustomerSubset()) {

						if (c.getOrganisationNumber()!=null && c.getOrganisationNumber().trim().length()>0) {
							m_customerTaxIdLookupMap.put(c.getOrganisationNumber(), c);
							logger.debug("Adding '" + c.getOrganisationNumber() + "' " + c.getName() + " custno " + c.getCustomerNumber());
						}
						
					}
				}
				
			}
			
		}
		CustomerSubset contact = m_customerTaxIdLookupMap.get(taxId);
		if (contact==null && taxId.length()==13) {
			contact = m_customerTaxIdLookupMap.get(taxId.substring(2));
		}
		if (contact==null) {
			logger.warn("Could not find '" + taxId + "'");
			throw new Exception("Could not find '" + taxId + "'");
		} else {
			return getCustomerByCustNo(contact.getCustomerNumber());
		}
	}
	
	/**
	 * Read all articles.
	 *
	 * @return				All articles.
	 * @throws Exception	If something goes wrong.
	 */
	public Articles getArticles() throws Exception {

		Articles r = getArticles(0);

		int currentPage = 1;
		int totalPages = r.getTotalPages();
		while (currentPage<totalPages) {
			Articles subset = getArticles(currentPage+1);
			r.getArticleSubset().addAll(subset.getArticleSubset());
			currentPage = subset.getCurrentPage();
		}

		return r;
	}

	/**
	 * A page of articles.
	 *
	 * @param page			The page.
	 * @return				A page of articles.
	 * @throws Exception	If something goes wrong.
	 */
	public Articles getArticles(int page) throws Exception {
		// Create request
		StringBuffer result = callFortnox("/articles/", (page>1 ? ("?page=" + page) : null), null);
		ErrorInformation e = checkIfError(result);
		Articles r = new Articles();
		if (e==null) {

			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
			r = JAXB.unmarshal(in,  r.getClass()); //NOI18N
			return(r);

		} else {
			throw new FortnoxException(e);
		}
	}

	/**
	 * Reads an article from database using articleNo.
	 * If article doesn't exist, null is returned.
	 *
	 * @param articleNo			The article's article number (in Fortnox).
	 * @return					The article.
	 * @throws Exception		If something goes wrong.
	 */
	public Article getArticleByArticleNo(String articleNo) throws Exception {

		if (articleNo==null)
			return null;

		Article c = new Article();
		// Create request
		String getStr = URLEncoder.encode(articleNo, "UTF-8");
		StringBuffer result = callFortnox("/articles/", getStr, null);

		ErrorInformation e = checkIfError(result);
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
			c = JAXB.unmarshal(in, c.getClass());
			return(c);
		} else {
			if (ERROR_ARTICLE_NOT_FOUND.equals(e.getCode())) {
				return null;
			} else {
				throw new FortnoxException(e);
			}
		}

	}

	/**
	 * Resets the tax id / customer map
	 * @deprecated 	Use resetCustomerMap instead.
	 * @since 		1.8.7
	 */
	public void resetContactMaps() {
		resetCustomerMap();
	}
	
	/**
	 * Resets the tax id / customer map
	 */
	public void resetCustomerMap() {
		m_customerTaxIdLookupMap = null;
	}
	
	/**
	 * Resets the tax id / supplier map
	 */
	public void resetSupplierMap() {
		m_supplierTaxIdLookupMap = null;
	}
	
	public String formatTaxId(String taxId, boolean isCompany) {
		if (taxId==null) return null;
		if (!taxId.contains("-")) {
			String lastDigits = taxId.substring(taxId.length()-4, taxId.length());
			String firstDigits = taxId.substring(0, taxId.length()-4);
			taxId = firstDigits + "-" + lastDigits;
		}
		if (taxId.length()==11 && !isCompany) {
			if (taxId.startsWith("0") || taxId.startsWith("1")) {
				taxId = "00" + taxId;
			} else {
				taxId = "19" + taxId;
			}
		}
		return taxId;
		
	}
	
	/**
	 * Checks if the buffer contains a fortnox error message.
	 * 
	 * @param buf			The sting buffer to parse.
	 * @return				An ErrorInformation struct.
	 * @throws		Exception if something goes wrong.
	 */
	public ErrorInformation checkIfError(StringBuffer buf) throws Exception {
		org.notima.api.fortnox.entities3.ErrorInformation e = null;
		// Find first new line
		int n = buf.toString().indexOf('\n');
		int m = buf.toString().indexOf("<ErrorInformation>", n);
		if (m-n>0) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buf.toString().getBytes()), "UTF-8"));
			e = new org.notima.api.fortnox.entities3.ErrorInformation();
	        e = (org.notima.api.fortnox.entities3.ErrorInformation)JAXB.unmarshal(in, ErrorInformation.class); //NOI18N
		}
		return(e);
	}
	
	public String getToken() {
		return m_clientSecret;
	}

	public void setToken(String token) {
		this.m_clientSecret = token;
	}

	public String getDatabase() {
		return m_authCode;
	}

	public void setDatabase(String database) {
		this.m_authCode = database;
	}

	public String getBaseUrl() {
		return m_baseUrl;
	}

	public void setBaseUrl(String m_baseUrl) {
		this.m_baseUrl = m_baseUrl;
	}

	/**
	 * Converts a java date into an XML-date
	 * 
	 * @param date		The date to convert.
	 * @return			A date in XMLGregorianCalendar format.
	 * @throws DatatypeConfigurationException	If something goes wrong.
	 */
	public static XMLGregorianCalendar getXMLDate(java.util.Date date) throws DatatypeConfigurationException {
		GregorianCalendar cal = new GregorianCalendar();
		XMLGregorianCalendar date2 = null;
		if (date!=null) {
			cal.setTime(date);
			date2 = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		}
		return date2;
	}
	
	public CompanySetting getCompanySetting() throws Exception{
		CompanySetting cs = new CompanySetting();
		
		StringBuffer result = callFortnox("/settings/company", null, null);
		
		ErrorInformation e = checkIfError(result);
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        cs = JAXB.unmarshal(in, cs.getClass());
	        return(cs); 
		} else {
			throw new FortnoxException(e);
		}
	}
	
	public boolean markReminderAsSent(Invoice invoice) throws Exception{
		return markReminderAsSent(invoice.getDocumentNumber());
	}
	
	public boolean markReminderAsSent(String documentNo) throws Exception {
		invoiceGetAction(documentNo, ACTION_INVOICE_PRINTREMINDER);
		return true;
	}

	public static void main(String[] args) {
		
		if (args==null || args.length!=2) {
			System.out.println("Usage: FortnoxClient3 configfile command");
			System.out.println("");
			System.out.println("Possible commands are: getAccessToken");
			System.exit(1);
		}
		
		File configFile = new File(args[0]);
		if (!configFile.exists() || configFile.isDirectory()) {
			System.out.println(args[0] + " is not a configuration file.");
			System.exit(1);
		}
		
		if ("getAccessToken".equalsIgnoreCase(args[1])) {
			
			try {
			
				FortnoxClientList clientList = FortnoxUtil.readFortnoxClientListFromFile(configFile.getCanonicalPath());				

				FortnoxClientInfo fc = clientList.getFirstClient();
				
				String clientSecret = fc.getClientSecret();
				String authCode = fc.getApiCode();

				FortnoxClient3 client = new FortnoxClient3();
				String accessToken = client.getAccessToken(authCode, clientSecret);
				System.out.println("Got access token:");
				System.out.println(accessToken);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		} else {
			System.out.println(args[1] + ": unknown command.");
			System.exit(1);
		}
		
	}

	/**
	 * Search document and field.
	 * 
	 * @param document		The document (invoice etc).
	 * @param field			The field to use when searching.
	 * @param value			The value to search for.
	 * @return				The search result as a stringBuffer (must be parsed).
	 * @throws		Exception if something goes wrong.
	 */
	public StringBuffer search(String document, String field, String value) throws Exception {
		
		String fieldStr = URLEncoder.encode(field.toLowerCase(), "UTF-8");
		String valueStr = URLEncoder.encode(value, "UTF-8");
		String callStr = "/" + document + "/?" + fieldStr + "=" + valueStr;
		StringBuffer result = getFortnox(callStr, null);
		
		ErrorInformation e = checkIfError(result);
		
		if (e==null) {
	        return(result); 
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	public boolean isUseArticles() {
		return useArticles;
	}

	public void setUseArticles(boolean useArticles) {
		this.useArticles = useArticles;
	}
	

	/**
	 * Retrieve SIE file
	 * 
	 * @param sieType			1 (Year balance), 2 (Period Balance), 3 (Object Balance) or 4 (Transactions). 
	 * @param financialYear		The financial year. Use getFinancialYear(Date) to find out the actual year.
	 * @return					A ByteBuffer that can be saved to a file. The bytebuffer is encoded in IBM437 / PC437
	 * @throws Exception		If something goes wrong.
	 */
	public ByteBuffer retrieveSieFile(int sieType, int financialYear) throws Exception {
		
		ByteBuffer byteBuf = callFortnoxOctetStream("/sie/" + sieType + "?financialyear=" + financialYear, null, null, null, "GET");
		
		// StringBuffer result = getFortnox("/sie/" + sieType + "?financialyear=" + financialYear, null);
			
		// Charset charset = Charset.forName("IBM437");
		// CharsetDecoder decoder = charset.newDecoder();
		// CharBuffer dest = decoder.decode(byteBuf);
		
		return (byteBuf);
		
	}
	

	/**
	 * Retrieve SIE file and saves it to a file
	 * 
	 * @param sieType			1 (Year balance), 2 (Period Balance), 3 (Object Balance) or 4 (Transactions). 
	 * @param financialYear		The financial year. Use getFinancialYear(Date) to find out the actual year.
	 * @param dir				The directory where to save the file.
	 * @param fileName			The filename part.
	 * @return					The full pathname of the file saved.
	 * @throws Exception		If something goes wring
	 */
	public String retrieveSieAndSaveToFile(int sieType, int financialYear, String dir, String fileName) throws Exception {

		ByteBuffer result = retrieveSieFile(sieType, financialYear);
		File f = new File(dir + File.separator + fileName);
		
		FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
		fos.write(result.array());
		fos.close();
		
		return f.getAbsolutePath();
		
	}
	
	/**
	 * Upload a binary file (or at least it's uploaded as binary) to given inbox in Fortnox.
	 * 
	 * @param file			Full pathname to file to be uploaded.
	 * @param folderId		Any of 
	 * 							INBOX_SUPPLIER_INVOICES
	 * 							INBOX_VOUCHERS
	 * 							INBOX_DAILY_TAKINGS
	 * 							INBOX_ASSET_REGISTER
	 * 							INBOX_BANK_FILES
	 * @return
	 * @throws Exception 
	 */
	public FortnoxFile uploadFile(String file, String folderId) throws Exception {
		
		FortnoxFile of = null;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		File f = new File(file);
		// Build multipart upload request
		HttpEntity data = MultipartEntityBuilder.create()
				.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				.addBinaryBody("file", f, ContentType.DEFAULT_BINARY, f.getName())
				.build();
		
		// Build http request and assign multipart upload data
		HttpUriRequest request = RequestBuilder
				.post(m_baseUrl + "/inbox?folderid=" + folderId)
				.setHeader("Access-Token", m_accessToken)
				.setHeader("Client-Secret", m_clientSecret)
				.setHeader("Accept", "application/xml")
				.setEntity(data)
				.build();

		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (status >= 200 && status < 300) {
				return entity !=null ? EntityUtils.toString(entity) : null;
			} else {
				System.out.println(EntityUtils.toString(entity));
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};

		registerCall();
		
		StringBuffer result = new StringBuffer(httpclient.execute(request, responseHandler));
		
		ErrorInformation e = checkIfError(result);
		
		StringReader r = new StringReader(result.toString());
		
		if (e==null) {
			of = JAXB.unmarshal(r, FortnoxFile.class);
		} else {
			throw new FortnoxException(e);
		}
		
		return of;	
		
	}
	
	/**
	 * Returns a list of invoices with invoice date in given date.
	 * 
	 * @param fromDate
	 * @param untilDate
	 * @return		An Invoices struct.
	 */
	public Invoices getAllCustomerInvoicesByDateRange(Date fromDate, Date untilDate) throws Exception {
		
		String filter = "fromdate=" + FortnoxClient3.s_dfmt.format(fromDate);
		if (untilDate!=null) {
			filter += "&todate=" + FortnoxClient3.s_dfmt.format(untilDate);
		}
		Invoices result = getInvoices(filter);
		return result;
		
	}
	
	/**
	 * Returns a list of unpaid customer invoices
	 * 
	 * @return	A list of unpaid customer invoices.
	 * @throws Exception	if something goes wrong
	 */
	public Invoices getUnpaidCustomerInvoices() throws Exception {
		Invoices result = getInvoices(FortnoxClient3.FILTER_UNPAID);
		return result;
	}

	/**
	 * Returns a list of unpaid and unbooked invoices (union)
	 * @return 	A list of unpaid and unbooked invoices (union)
	 * @throws Exception 	If something goes wrong.
	 */
	public Invoices getUnpaidAndUnbookedCustomerInvoices() throws Exception {
		
		Invoices unpaid = getInvoices(FortnoxClient3.FILTER_UNPAID);
		Invoices unbooked = getInvoices(FortnoxClient3.FILTER_UNBOOKED);
		Invoices result = new Invoices();
		result.setInvoiceSubset(new ArrayList<InvoiceSubset>());
		if (unpaid!=null && unpaid.getInvoiceSubset()!=null) {
			result.getInvoiceSubset().addAll(unpaid.getInvoiceSubset());
		}
		if (unbooked!=null && unbooked.getInvoiceSubset()!=null) {
			result.getInvoiceSubset().addAll(unbooked.getInvoiceSubset());
		}
		return result;
	}
	
	/**
	 * Compares the access token.
	 * 
	 * @param that
	 * @return	True if the accessToken is equal.
	 */
	public boolean equals(FortnoxClient3 that) {
		if (m_accessToken==null && that.m_accessToken==null)
			return true;
		if (m_accessToken==null)
			return false;
		return (m_accessToken.equals(that.m_accessToken));
	}
	
	/**
	 * Returns the date until which accounting is locked.
	 * If no lock exists, null is returned.
	 * 
	 * @return		Date until accounting is locked. If no lock, null.
	 * @throws Exception
	 */
	public Date getLockedPeriodUntil() throws Exception {
		
		LockedPeriod lp = null;
		
		StringBuffer result = callFortnox("/settings/lockedperiod", null, null);
		
		ErrorInformation e = checkIfError(result);
		if (e==null) {
			// Convert returned result into UTF-8
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(result.toString().getBytes()), "UTF-8"));
	        lp = JAXB.unmarshal(in, LockedPeriod.class);
	        if (lp!=null) {
	        	if (lp.getEndDate()==null || lp.getEndDate().trim().length()==0)
	        		return null;
	        	return FortnoxClient3.s_dfmt.parse(lp.getEndDate());
	        } else {
	        	return null;
	        }
		} else {
			throw new FortnoxException(e);
		}
		
	}
	
	
	/**
	 *	Default value currently is 250 ms.
	 *  See https://developer.fortnox.se/general/regarding-fortnox-api-rate-limits/
	 *
	 * @return	The minimum time that has to pass between each call to the Fortnox API.
	 * 
	 * 
	 */
	public long getMinMillisBetweenCalls() {
		return minMillisBetweenCalls;
	}

	/**
	 * Sets the minimum time that has to pass between each call to the Fortnox APi.
	 * 	
	 * @param minMillisBetweenCalls		The minimum time in milliseconds.
	 */
	public void setMinMillisBetweenCalls(long minMillisBetweenCalls) {
		this.minMillisBetweenCalls = minMillisBetweenCalls;
	}

	/**
	 * Register that a HTTP-call to the Fortnox API has been made.
	 */
	private void registerCall() {
		
		lastCall = Calendar.getInstance().getTimeInMillis();
		if (firstCall==null)
			firstCall = lastCall.longValue();
		
		totalCalls++;
		
	}
	
	/**
	 * Waits if necessary to avoid hitting the rate limit.
	 */
	private synchronized void rateLimit() {
		
		if (lastCall==null) return;
		
		long now = Calendar.getInstance().getTimeInMillis();
		long diff = now - lastCall;
		if (diff < minMillisBetweenCalls) {
			try {
				Thread.sleep(minMillisBetweenCalls-diff);
				logger.debug("Rate-limit sleep for " + (minMillisBetweenCalls-diff) + " ms");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Calculates the call rate per minute for this client.
	 * 
	 * @return		The estimated calls per minute.
	 */
	public double calculateCallRate() {

		if (firstCall==null) return 0;
		
		// Calculated milliseconds
		long period = lastCall - firstCall;
		long seconds = Math.round(period / 1000);
		
		if (seconds==0) return (double)totalCalls;
		
		logger.info("{} calls in {} seconds.", totalCalls, seconds);
		
		return ((double)totalCalls / (seconds/60.0) );
		
	}
	
	
}
