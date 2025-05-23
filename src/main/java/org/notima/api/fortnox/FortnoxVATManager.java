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
	
	// Tree grouped by VAT code and sorted by number
	private Map<String, SortedSet<Integer>>		vatCodeAccounts;
	
	// Revenue account map
	private Map<String, VatInfo> revenueAccountMap;
	
	// Current fiscal year
	private	int		currentFiscalYear = 0;
	
	private boolean easyVatMp2 = false;
	private boolean easyVatMp3 = false;
	private boolean easyVatMp0 = false;
	
	
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
				addPredefinedAccountToRevenueAccountMap(pa);
			} else {
				log("Can't find predefined account for " + s);
			}
		}

		remapFromEasyVAT();
		
		// Look for VAT accounts
		initChartOfAccounts();
		
		SortedSet<Integer> accountsWithVatCodeMP2 = getAccountsWithVatCode("MP2");
		SortedSet<Integer> accountsWithVatCodeMP3 = getAccountsWithVatCode("MP3");
		SortedSet<Integer> mp0 = getAccountsWithVatCode("MF");
		SortedSet<Integer> exportServices = getAccountsWithVatCode("ÖTEU");	

		SortedSet<Integer> mpNoVAT = new TreeSet<Integer>(); // No VAT
		
		for (AccountSubset as : chartOfAccounts.getAccountSubset()) {

			// Only check revenue accounts
			if (!as.getNumber().toString().startsWith("3")) {
				continue;
			}
			if (!as.getActive().booleanValue())
				continue;
			
			if (as.getVATCode()==null || as.getVATCode().trim().length()==0) {
				mpNoVAT.add(as.getNumber());
			}
			
		}
		
		// If there are no easyVAT settings, use the first suitable accounts from the chart of accounts.
		
		if (!easyVatMp2 && accountsWithVatCodeMP2.size()>0) {
			putSEVatInfoToRevenueAccountMap(FortnoxConstants.ACCT_SALES_MP2, accountsWithVatCodeMP2.first(), FortnoxConstants.VAT_MP2_RATE);
		}
		if (!easyVatMp3 && accountsWithVatCodeMP3.size()>0) {
			putSEVatInfoToRevenueAccountMap(FortnoxConstants.ACCT_SALES_MP3, accountsWithVatCodeMP3.first(), FortnoxConstants.VAT_MP3_RATE);
		}
		if (!easyVatMp0 && mp0.size()>0) {
			putSEVatInfoToRevenueAccountMap(FortnoxConstants.ACCT_SALES_MP0, mp0.first(), FortnoxConstants.VAT_MP0_RATE);
		}
		
		if (mpNoVAT.size()>0) {
			putSEVatInfoToRevenueAccountMap(FortnoxConstants.ACCT_SALES_NO_VAT, mpNoVAT.first(), FortnoxConstants.VAT_MP0_RATE);
		}
		if (exportServices.size()>0) {
			putSEVatInfoToRevenueAccountMap(FortnoxConstants.ACCT_SALES_EXPORT_SERVICE, exportServices.first(), FortnoxConstants.VAT_MP0_RATE);
		}
		
		// Check for missing custom vat codes (MPX_KUND) for instance
		for (String vatCode : vatCodeMappings.keySet()) {
			
			if (vatCode.startsWith("MP") && !revenueAccountMap.containsKey(vatCode)) {

				List<AccountSubset> accounts = vatCodeMappings.get(vatCode);
				if (accounts!=null && accounts.size()>0) {
					putVatInfoToRevenueAccountMap(vatCode, accounts.get(0).getNumber());
				}
				
			}
			
		}
		
		return revenueAccountMap;
		
	}

	private void addPredefinedAccountToRevenueAccountMap(PreDefinedAccountSubset pa) {
		VatInfo vatInfo = createVATInfoFromPredefinedAccount(pa);
		revenueAccountMap.put(pa.getName(), vatInfo);
	}
	
	private VatInfo createVATInfoFromPredefinedAccount(PreDefinedAccountSubset pa) {
		VatInfo vatInfo = new VatInfo(pa.getName(), pa.getAccount());
		if (FortnoxConstants.PREDEFINED_SE_REVENUE_VAT_HI.contains(pa.getName())) {
			vatInfo.setVatRate(25);
		} else if (FortnoxConstants.PREDEFINED_SE_REVENUE_VAT_MEDIUM.contains(pa.getName())) {
			vatInfo.setVatRate(12);
		} else if (FortnoxConstants.PREDEFINED_SE_REVENUE_VAT_LOW.contains(pa.getName())) {
			vatInfo.setVatRate(6);
		}
		return vatInfo;
	}
	
	/**
	 * 
	 * @param predefinedCode
	 * @param accountNo
	 */
	private void putSEVatInfoToRevenueAccountMap(String predefinedCode, Integer accountNo, double vatRate) {
		VatInfo vatInfo = new VatInfo(predefinedCode, accountNo);
		vatInfo.setTaxDomicile(FortnoxConstants.DEFAULT_TAX_DOMICILE);
		vatInfo.setVatRate(vatRate);
		revenueAccountMap.put(predefinedCode, vatInfo);
	}
	
	/**
	 * 
	 * @param predefinedCode
	 * @param accountNo
	 */
	private void putVatInfoToRevenueAccountMap(String predefinedCode, Integer accountNo) {
		VatInfo vatInfo = new VatInfo(predefinedCode, accountNo);
		revenueAccountMap.put(predefinedCode, vatInfo);
	}
	
	/**
	 * If EasyVAT is activated, we'll point the easy VAT settings to the legacy codes for compatibility 
	 */
	private void remapFromEasyVAT() {
		
		easyVatMp2 = false;
		easyVatMp3 = false;
		easyVatMp0 = false;
		
		// Check if there are easy VAT settings
		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP1_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_MP1, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP1_EASY_VAT));
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_SERVICE_MP1, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP1_SERVICE_EASY_VAT));
		}
		
		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP2_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_MP2, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP2_EASY_VAT));
			easyVatMp2 = true;
		}

		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP3_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_MP3, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP3_EASY_VAT));
			easyVatMp3 = true;
		}

		if (revenueAccountMap.containsKey(FortnoxConstants.ACCT_SALES_MP0_EASY_VAT)) {
			revenueAccountMap.put(FortnoxConstants.ACCT_SALES_MP0, revenueAccountMap.get(FortnoxConstants.ACCT_SALES_MP0_EASY_VAT));
			easyVatMp0 = true;
		}
		// End of easy VAT remap
		
		
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
	

	private SortedSet<Integer> getAccountsWithVatCode(String vatCode) {
		if (vatCode==null)
			return new TreeSet<Integer>();
		SortedSet<Integer> accounts = vatCodeAccounts.get(vatCode);
		if (accounts==null)
			return new TreeSet<Integer>();
		else
			return accounts;
	}
	
	
	private void initVatCodeMappings() throws Exception {
		
		vatCodeMappings = new TreeMap<String, List<AccountSubset>>();
		vatCodeAccounts = new TreeMap<String, SortedSet<Integer>>();
		
		String vatCode;
		List<AccountSubset> accountList;
		SortedSet<Integer>  accounts; 
		for (AccountSubset a : chartOfAccounts.getAccountSubset()) {
			// Only consider active accounts
			if (Boolean.TRUE.equals(a.getActive())) {
				vatCode = a.getVATCode();
				if (vatCode!=null && vatCode.trim().length()>0) {
					// Add to account list
					accountList = vatCodeMappings.get(vatCode.trim());
					if (accountList==null) {
						accountList = new ArrayList<AccountSubset>();
						vatCodeMappings.put(vatCode.trim(), accountList);
					}
					accountList.add(a);
					// Add to set
					accounts = vatCodeAccounts.get(vatCode.trim());
					if (accounts==null) {
						accounts = new TreeSet<Integer>();
						vatCodeAccounts.put(vatCode.trim(), accounts);
					}
					accounts.add(a.getNumber());
				}
			}
		}
		
	}
	
	private void log(String message) {
		logger.info(fortnoxClient.getTenantOrgNo() + " : " + message);
	}
}
