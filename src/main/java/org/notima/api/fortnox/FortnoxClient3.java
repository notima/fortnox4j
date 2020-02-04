package org.notima.api.fortnox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.notima.api.fortnox.entities3.AccountSubset;
import org.notima.api.fortnox.entities3.Accounts;
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
import org.notima.api.fortnox.entities3.Invoices;
import org.notima.api.fortnox.entities3.Order;
import org.notima.api.fortnox.entities3.Orders;
import org.notima.api.fortnox.entities3.PreDefinedAccount;
import org.notima.api.fortnox.entities3.PreDefinedAccounts;
import org.notima.api.fortnox.entities3.Supplier;
import org.notima.api.fortnox.entities3.SupplierSubset;
import org.notima.api.fortnox.entities3.Suppliers;
import org.notima.api.fortnox.entities3.Voucher;
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
	

	/**
	 * Predefined revenue accounts
	 * These exist as predefined accounts in Fortnox
	 */
	public static String[] PREDEFINED_REV_ACCT = new String[] {
		"CURRENCYWIN",
		"CURRENCYLOSS",
		"ADMFEE",
		"FREIGHT",
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
		initFromFile(null);
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
	 */
	public FortnoxClient3(String configFile) {
		initFromFile(configFile);
	}
	
	/**
	 * Read client parameters from file (if found).
	 * 
	 * @param configFile
	 */
	private void initFromFile(String configFile) {
		
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
		
		FileConfiguration fc = new XMLConfiguration();
		fc.setFile(f);
		try {
			logger.debug("Using configuration file {}.", configFile);
			fc.load();
		} catch (ConfigurationException e) {
			logger.error("Problem with reading configuration file {}.", configFile);
			e.printStackTrace();
			return;
		}
		
		String clientSecret = fc.getString("clientSecret");
		String accessToken = fc.getString("accessToken");
		
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
		
		HttpClient httpClient = new DefaultHttpClient();
		
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
		entity.consumeContent();

		logger.debug("Received reply: \n" + result.toString());
		
		// Max 16 requests per second. Call a sleep here
		Thread.sleep(100);
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
			// Pause not to exceed call limit
			Thread.sleep(100);
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
		ClassLoader cl = FortnoxClient3.class.getClassLoader();
        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(supplier.getClass().getPackage().getName(), cl);
        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(supplier, result);
        
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
		ClassLoader cl = FortnoxClient3.class.getClassLoader();
        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(payment.getClass().getPackage().getName(), cl);
        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(payment, result);
		
        StringBuffer output = callFortnox("/invoicepayments" + 
        		(payment.getNumber()!=null && payment.getNumber()>0 ? "/" + payment.getNumber() : ""), 
        		null, 
        		result.getBuffer(),
        		null, // Headers
        		(payment.getNumber()!=null && payment.getNumber()>0 ? "put" : null) // method
        		);
		
        InvoicePayment out = null;
        if (output.toString().contains("ErrorInformation")) {
        	System.err.println(output.toString());
        	return null;
        } else {
	        StringReader reader = new StringReader(output.toString());
	        if (output!=null && output.length()>0) {
	        	// Try to create invoice
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
		ClassLoader cl = FortnoxClient3.class.getClassLoader();
        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(invoice.getClass().getPackage().getName(), cl);
        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
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
        
        marshaller.marshal(invoice, result);
       
        StringBuffer output = callFortnox("/invoices" + 
        		(invoice.getDocumentNumber()!=null && invoice.getDocumentNumber().trim().length()>0 ? "/" + invoice.getDocumentNumber().trim() : ""), 
        		null, 
        		result.getBuffer(),
        		null, // Headers
        		(invoice.getDocumentNumber()!=null && invoice.getDocumentNumber().trim().length()>0 ? "put" : null) // method
        		);
		
        Invoice out = null;
        if (output.toString().contains("ErrorInformation")) {
        	System.err.println(output.toString());
        	return null;
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
		ClassLoader cl = FortnoxClient3.class.getClassLoader();
        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(voucher.getClass().getPackage().getName(), cl);
        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        JAXB.marshal(voucher, result);
        
        // marshaller.marshal(voucher, result);
       
        StringBuffer output = callFortnox("/vouchers" + 
        		(voucher.getVoucherNumber()!=null && voucher.getVoucherNumber()>0 ? "/" + voucher.getVoucherNumber() : ""), 
        		null, 
        		result.getBuffer(),
        		null, // Headers
        		(voucher.getVoucherNumber()!=null && voucher.getVoucherNumber()>0 ? "put" : null) // method
        		);
		
        Voucher out = null;
        if (output.toString().contains("ErrorInformation")) {
        	throw new FortnoxException(output.toString());
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
	 * Creates or updates a customer
	 * 
	 * @param customer				The customer to be created / updated.
	 * @throws Exception			If something goes wrong.
	 * @return 			The customer created / updated.
	 */
	public Customer setCustomer(Customer customer) throws Exception {
		
		StringWriter result = new StringWriter();
		ClassLoader cl = FortnoxClient3.class.getClassLoader();
        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(customer.getClass().getPackage().getName(), cl);
        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(customer, result);
        
        StringBuffer output = callFortnox("/customers", null, result.getBuffer());
        
        ErrorInformation e = checkIfError(output);

        Customer c = new Customer();
        
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
			
			XMLConfiguration config = new XMLConfiguration();
			try {
				
				config.load(configFile);
				
				String clientSecret = config.getString("clientSecret");
				String authCode = config.getString("authCode");

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
	 * @return					A StringBuffer that can be saved to a file.
	 * @throws Exception		If something goes wrong.
	 */
	public StringBuffer retrieveSieFile(int sieType, int financialYear) throws Exception {
		
		StringBuffer result = getFortnox("/sie/" + sieType + "?financialyear=" + financialYear, null);
		ErrorInformation e = checkIfError(result);
		
		if (e==null) {
			return (result);
		} else {
			throw new FortnoxException(e);
		}
		
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

		StringBuffer result = retrieveSieFile(sieType, financialYear);
		File f = new File(dir + File.separator + fileName);
		PrintWriter fr = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		fr.append(result);
		fr.close();
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
	
	
}
