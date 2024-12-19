package org.notima.api.fortnox;

import java.util.HashSet;
import java.util.Set;

public class FortnoxConstants {

	public static final String PREDEFACCT_ROUNDOFF = "ROUNDOFF";
	
	public static final String PREDEFACCT_PG = "PG";
	public static final String PREDEFACCT_BG = "BG";
	public static final String PREDEFACCT_CUSTCLAIM = "CUSTCLAIM";
	public static final String PREDEFACCT_INVAT = "INVAT";

	// Constants used to describe / adress fields on a customer invoice / order
	public static final String EXTREF1 = "ExternalInvoiceReference1";
	public static final String EXTREF2 = "ExternalInvoiceReference2";
	public static final String INVOICEREF = "InvoiceReference";
	public static final String OCR = "OCR";
	public static final String ORDERREF = "OrderReference";
	public static final String OURREF = "OurReference";
	public static final String YOURORDERNUMBER = "YourOrderNumber";
	public static final String YOURREF = "YourReference";

	public static final String DEFAULT_ACCOUNTING_CURRENCY = "SEK";
	public static final String DEFAULT_TAX_DOMICILE = "SE";
	public static final int	   DEFAULT_ROUNDING_PRECISION = 2;

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
	public static final String FILTER_ACTIVE = "active";
	public static final String FILTER_INACTIVE = "inactive";

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
	public static final String ACTION_WAREHOUSE_READY = "warehouseready";

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
	public static final String ACCT_SALES_MP1_EASY_VAT = "SALES_25_SE";
	public static final String ACCT_SALES_MP1_SERVICE_EASY_VAT = "SALES_25_SE2";
	public static final String ACCT_SALES_MP2 = "SALES_MP2";
	public static final String ACCT_SALES_MP2_EASY_VAT = "SALES_12_SE";
	public static final String ACCT_SALES_MP2_SERVICE_EASY_VAT = "SALES_12_SE2";
	public static final String ACCT_SALES_MP3 = "SALES_MP3";
	public static final String ACCT_SALES_MP3_EASY_VAT = "SALES_6_SE";
	public static final String ACCT_SALES_MP3_SERVICE_EASY_VAT = "SALES_6_SE2";
	public static final String ACCT_SALES_MP0 = "SALES_MP0";
	public static final String ACCT_SALES_MP0_EASY_VAT = "SALES_0_SE";
	public static final String ACCT_SALES_MP0_SERVICE_EASY_VAT = "SALES_0_SE2";
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
	public static final String ACCT_INTEREST = "INTEREST"; 		// Normally interest income

	/**
	 * Error Codes
	 */
	public static final String ERROR_CANT_FIND_CUSTOMER = "2000433";
	public static final String ERROR_CANT_FIND_CURRENCY = "2000427";
	public static final String ERROR_CANT_FIND_INVOICE = "2000434";
	public static final String ERROR_NOT_AUTH_FOR_SCOPE = "2000663";
	public static final String ERROR_INVALID_LOGIN = "2000310";
	public static final String ERROR_INVALID_LOGIN_V2 = "2004054";
	public static final String ERROR_MISSING_CREDENTIALS = "2000311";
	public static final String ERROR_ACCOUNT_NOT_ACTIVE = "2000550";
	public static final String ERROR_ACCOUNT_NOT_FOUND = "2000423";
	public static final String ERROR_ARTICLE_NOT_FOUND = "2000428";
	public static final String ERROR_TERMS_OF_PAYMENT_FOUND = "2000429";
	public static final String ERROR_PRICE_NOT_FOUND = "2000430";
	public static final String ERROR_PRICE_LIST_NOT_FOUND = "2000431";
	public static final String ERROR_TERMS_OF_DELIVERY_NOT_FOUND = "2000435";
	public static final String ERROR_PROJECT_NOT_FOUND = "2001161";
	public static final String ERROR_NO_CUSTOMER_INVOICE_SCOPE = "2001393";
	public static final String ERROR_NO_VOUCHER_SCOPE = "2002455";
	public static final String ERROR_CANT_FIND_PREDEFINED_ACCOUNT = "2001403";

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

	/**
	 * Revenue accounts for High VAT
	 */
	@SuppressWarnings("serial")
	public static Set<String> PREDEFINED_SE_REVENUE_VAT_HI = new HashSet<String>() {
		{
		add(ACCT_SALES_MP1);
		add(ACCT_SALES_SERVICE_MP1);
		add(ACCT_SALES_MP1_EASY_VAT);
		add(ACCT_SALES_MP1_SERVICE_EASY_VAT);
		}
	};
	
	/**
	 * Revenue accounts for Medium VAT
	 */
	@SuppressWarnings("serial")
	public static Set<String> PREDEFINED_SE_REVENUE_VAT_MEDIUM = new HashSet<String>() {
		{
		add(ACCT_SALES_MP2);
		add(ACCT_SALES_MP2_EASY_VAT);
		add(ACCT_SALES_MP2_SERVICE_EASY_VAT);
		}
	};
	
	/**
	 * Revenue accounts for Low VAT
	 */
	@SuppressWarnings("serial")
	public static Set<String> PREDEFINED_SE_REVENUE_VAT_LOW = new HashSet<String>() {
		{
		add(ACCT_SALES_MP3);
		add(ACCT_SALES_MP3_EASY_VAT);
		add(ACCT_SALES_MP3_SERVICE_EASY_VAT);
		}
	};
	

	/**
	 * Predefined revenue accounts
	 * These exist as predefined accounts in Fortnox.
	 * Not all may exist at the same time (Easy VAT).
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
		ACCT_SALES_SE_REV,
		ACCT_SALES_MP1_EASY_VAT,
		ACCT_SALES_MP1_SERVICE_EASY_VAT,
		ACCT_SALES_MP2_EASY_VAT,
		ACCT_SALES_MP2_SERVICE_EASY_VAT,
		ACCT_SALES_MP3_EASY_VAT,
		ACCT_SALES_MP3_SERVICE_EASY_VAT,
		ACCT_SALES_MP0_EASY_VAT,
		ACCT_SALES_MP0_SERVICE_EASY_VAT
	};

	public static String[] VAT_DOMESTIC_SWEDEN = new String[] {
			FortnoxConstants.VAT_MP0, FortnoxConstants.VAT_MP1, FortnoxConstants.VAT_MP2, FortnoxConstants.VAT_MP3
	};

	/**
	 * VAT codes
	 */
	public static final String VAT_MP0 = "MP0";
	public static final String VAT_MP1 = "MP1";
	public static final String VAT_MP2 = "MP2";
	public static final String VAT_MP3 = "MP3";
	public static final String VAT_MF = "MF";
	
	/** 
	 * Predefined VAT rates. Shouldn't be a constant since it can change, but it seems to be constants in Fortnox
	 */
	public static final double VAT_MP0_RATE = 0.0;
	public static final double VAT_MP1_RATE = 25.0;
	public static final double VAT_MP2_RATE = 12.0;
	public static final double VAT_MP3_RATE = 6.0;
	
	/**
	 * Currency rates
	 */
	public static final double GET_RATE_FROM_FORTNOX = 0.0;

	// TODO: Add more below
	/*
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2440 : SUPDEBT
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 5710 : SUPFREIGHT
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2611 : OUTVAT_MP1
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2621 : OUTVAT_MP2
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2631 : OUTVAT_MP3
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 0 : OUTVAT_MP4
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 4011 : PURCASE
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3960 : CURRENCYWIN
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 7960 : CURRENCYLOSS
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3540 : ADMFEE
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 0 : SUPADMFEE
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3520 : FREIGHT
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 8313 : INTEREST
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3740 : ROUNDOFF
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 4730 : CASHDISCOUNTIN
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3730 : CASHDISCOUNTOUT
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3540 : DEMAND
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2990 : ACCRINVOICE
	2020-03-03 10:23:56,468 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3990 : ACCRINCOME
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 1790 : ACCRSINVOICE
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 0 : ACCRCOST
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 1910 : CASH
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 1930 : CASHBYCARD
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3001 : SALES
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3001 : SALES2
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3108 : SALESEUREV
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3308 : SALESEUREV2
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3105 : SALESEXPORT
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3305 : SALESEXPORT2
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3106 : SALESEUVAT
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3308 : SALESEUVAT2
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 3231 : SALESCONSTR2
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2645 : PRODUCT_DEB
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2615 : PRODUCT_CRE
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2645 : SERVICE_DEB
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2614 : SERVICE_CRE
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2647 : CONSTRUCTION_DEB
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 2617 : CONSTRUCTION_CRE
	2020-03-03 10:23:56,469 INFO  notima.api.fortnox.junit.TestGetPreDefinedAccounts -  - 0 : AG	
	*/
	
}
