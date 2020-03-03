package org.notima.api.fortnox;

public class FortnoxConstants {

	public static final String PREDEFACCT_ROUNDOFF = "ROUNDOFF";
	
	public static final String PREDEFACCT_PG = "PG";
	public static final String PREDEFACCT_BG = "BG";
	public static final String PREDEFACCT_CUSTCLAIM = "CUSTCLAIM";
	public static final String PREDEFACCT_INVAT = "INVAT";

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
