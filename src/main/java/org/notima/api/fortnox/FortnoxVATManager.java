package org.notima.api.fortnox;

import java.util.Date;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.notima.api.fortnox.entities3.AccountSubset;
import org.notima.api.fortnox.entities3.Accounts;
import org.notima.api.fortnox.entities3.FinancialYearSubset;
import org.notima.api.fortnox.entities3.PreDefinedAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for easier handling of VAT settings.
 * 
 */
public class FortnoxVATManager {

	// Get logger
	private Logger	logger = LoggerFactory.getLogger(FortnoxVATManager.class);
	
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
	public Map<String, Integer> getRevenueAccountMap(Date acctDate) throws Exception {
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
	 * @param 		year		The year to use for COA.
	 * @return					A map of revenue accounts. The key is the predefined account.
	 * 							If Easy VAT is enabled, remaps to old VAT predefined accounts are
	 * 							made for backwards compatibility.
	 * @throws Exception 		If something goes wrong.
	 */
	public Map<String, Integer> getRevenueAccountMap(int year) throws Exception {

		Map<String,Integer> result = new TreeMap<String, Integer>();
		
		PreDefinedAccount pa;
		
		for (String s : FortnoxConstants.PREDEFINED_REV_ACCT) {
			try {
				pa = fortnoxClient.getPreDefinedAccount(s);
				if (pa!=null) {
					result.put(s, pa.getAccount());
				}
			} catch (FortnoxException ee) {
				if (ee.getErrorInformation()!=null 
						&& ee.getErrorInformation().getCode()!=null 
						&& FortnoxConstants.ERROR_CANT_FIND_PREDEFINED_ACCOUNT.equals(ee.getErrorInformation().getCode().toString())) {
					log(ee.getMessage());
				} else {
					throw ee;
				}
			}
		}

		boolean easyVatMp2 = false;
		boolean easyVatMp3 = false;
		boolean easyVatMp0 = false;
		
		// Check if there are easy VAT settings
		if (result.containsKey(FortnoxConstants.ACCT_SALES_MP1_EASY_VAT)) {
			result.put(FortnoxConstants.ACCT_SALES_MP1, result.get(FortnoxConstants.ACCT_SALES_MP1_EASY_VAT));
			result.put(FortnoxConstants.ACCT_SALES_SERVICE_MP1, result.get(FortnoxConstants.ACCT_SALES_MP1_SERVICE_EASY_VAT));
		}
		
		if (result.containsKey(FortnoxConstants.ACCT_SALES_MP2_EASY_VAT)) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[0], result.get(FortnoxConstants.ACCT_SALES_MP2_EASY_VAT));
			easyVatMp2 = true;
		}

		if (result.containsKey(FortnoxConstants.ACCT_SALES_MP3_EASY_VAT)) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[1], result.get(FortnoxConstants.ACCT_SALES_MP3_EASY_VAT));
			easyVatMp3 = true;
		}

		if (result.containsKey(FortnoxConstants.ACCT_SALES_MP0_EASY_VAT)) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[2], result.get(FortnoxConstants.ACCT_SALES_MP0_EASY_VAT));
			easyVatMp0 = true;
		}
		// End of easy VAT remap
		
		
		// Look for VAT accounts
		Accounts accounts = fortnoxClient.getAccounts(year);
		
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
		
		if (!easyVatMp2 && mp2.size()>0) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[0], mp2.first());
		}
		if (!easyVatMp3 && mp3.size()>0) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[1], mp3.first());
		}
		if (!easyVatMp0 && mp0.size()>0) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[2], mp0.first());
		}
		if (mpNoVAT.size()>0) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[3], mpNoVAT.first());
		}
		if (exportServices.size()>0) {
			result.put(FortnoxConstants.PREDEFINED_REVENUE_VAT_ACCT[4], exportServices.first());
		}
		
		return result;
		
	}
	
	private void log(String message) {
		logger.info(fortnoxClient.getTenantOrgNo() + " : " + message);
	}
}
