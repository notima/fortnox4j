package org.notima.api.fortnox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.notima.api.fortnox.entities3.AccountSubset;
import org.notima.api.fortnox.entities3.Accounts;
import org.notima.api.fortnox.entities3.FinancialYearSubset;
import org.notima.api.fortnox.entities3.PreDefinedAccountSubset;
import org.notima.api.fortnox.entities3.VatInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for easier handling of VAT settings.
 * 
 */
public class FortnoxVATManager {

	// Get logger
	private Logger	logger = LoggerFactory.getLogger(FortnoxVATManager.class);
	
	// Chart of accounts
	private Accounts 			chartOfAccounts;
	
	// Predefined accounts
	private Map<String,PreDefinedAccountSubset> 	predefinedAccounts;	
	
	// VAT code mappings
	private Map<String, List<AccountSubset>>	vatCodeMappings;
	
	// Revenue account map
	private Map<String, VatInfo> revenueAccountMap;
	// Current fiscal year
	private	int		currentFiscalYear = 0;
	
	
	
	private FortnoxClient3 fortnoxClient;
	
	public FortnoxVATManager(FortnoxClient3 theClient) {
		fortnoxClient = theClient;
	}

	/**
	 * Return default revenue account map for given accounting date.
	 * Null as acctDate will return today.
	 * 
	 * @param 				acctDate	The date we want to use to determine the accounts (ie Fiscal Year).
	 * @return				A map of revenue accounts.
	 * @throws Exception	If something goes wrong.
	 */
	public Map<String, VatInfo> getRevenueAccountMap(Date acctDate) throws Exception {
		FinancialYearSubset fy = fortnoxClient.getFinancialYear(acctDate);
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
	 * @param 		year		The year to use for COA. Consecutive calls to the same fiscal year is cached.
	 * @return					A map of revenue accounts. The key is the predefined account.
	 * 							If Easy VAT is enabled, remaps to old VAT predefined accounts are
	 * 							made for backwards compatibility.
	 * @throws Exception 		If something goes wrong.
	 */
	public Map<String, VatInfo> getRevenueAccountMap(int year) throws Exception {

		if (year!=currentFiscalYear) {
			revenueAccountMap = null;
			chartOfAccounts = null;
		}
		
		if (revenueAccountMap==null) {
			revenueAccountMap = new TreeMap<String, VatInfo>();
			currentFiscalYear = year;
		} else {
			return revenueAccountMap;
		}

		initPredefinedAccounts();
		
		PreDefinedAccountSubset pa;
		
		for (String s : FortnoxConstants.PREDEFINED_REV_ACCT) {
			pa = predefinedAccounts.get(s);
			if (pa!=null) {
				revenueAccountMap.put(s, new VatInfo(pa));
			} else {
				log("Can't find predefined account for " + s);
			}
		}

		boolean easyVatMp2 = false;
		boolean easyVatMp3 = false;
		boolean easyVatMp0 = false;
		
		// Check if there are easy VAT settings
		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP1_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_MP1, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP1_EASY_VAT));
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_SERVICE_MP1, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP1_SERVICE_EASY_VAT));
		}
		
		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP2_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[0], revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP2_EASY_VAT));
			easyVatMp2 = true;
		}

		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP3_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[1], revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP3_EASY_VAT));
			easyVatMp3 = true;
		}

		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP0_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[2], revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP0_EASY_VAT));
			easyVatMp0 = true;
		}
		// End of easy VAT remap
		
		
		// Look for VAT accounts
		initChartOfAccounts();
		
		SortedSet<Integer> mp2 = new TreeSet<Integer>();
		SortedSet<Integer> mp3 = new TreeSet<Integer>();
		SortedSet<Integer> mp0 = new TreeSet<Integer>(); // VAT but zero rate 0%
		SortedSet<Integer> mpNoVAT = new TreeSet<Integer>(); // No VAT
		SortedSet<Integer> exportServices = new TreeSet<Integer>();	
		
		for (AccountSubset as : chartOfAccounts.getAccountSubset()) {

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
		
		if (!easyVatMp2 && mp2.size()>0) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[0], new VatInfo(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[0], mp2.first()));
		}
		if (!easyVatMp3 && mp3.size()>0) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[1], new VatInfo(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[1], mp3.first()));
		}
		if (!easyVatMp0 && mp0.size()>0) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[2], new VatInfo(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[2], mp0.first()));
		}
		if (mpNoVAT.size()>0) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[3], new VatInfo(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[3], mpNoVAT.first()));
		}
		if (exportServices.size()>0) {
			revenueAccountMap.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[4], new VatInfo(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[4], exportServices.first()));
		}
		
		return revenueAccountMap;
		
	}
	
	private void initPredefinedAccounts() throws Exception {
		if (predefinedAccounts==null) {
			predefinedAccounts = fortnoxClient.getPredefinedAccountMap();			
		}
	}
	
	
	private void initChartOfAccounts() throws Exception {
		if (chartOfAccounts==null) {
			chartOfAccounts = fortnoxClient.getAccounts(currentFiscalYear);
			initVatCodeMappings();
		}
	}
	
	private void initVatCodeMappings() throws Exception {
		
		vatCodeMappings = new TreeMap<String, List<AccountSubset>>();
		String vatCode;
		List<AccountSubset> accountList;
		for (AccountSubset a : chartOfAccounts.getAccountSubset()) {
			vatCode = a.getVATCode();
			if (vatCode!=null && vatCode.trim().length()>0) {
				accountList = vatCodeMappings.get(vatCode.trim());
				if (accountList==null) {
					accountList = new ArrayList<AccountSubset>();
					vatCodeMappings.put(vatCode.trim(), accountList);
				}
				accountList.add(a);
			}
		}
		
	}
	
	private void log(String message) {
		logger.info(fortnoxClient.getTenantOrgNo() + " : " + message);
	}
}
