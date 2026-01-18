package org.notima.api.fortnox;

import java.util.HashSet;
import java.util.Set;

/**
 * Convenience class for handling scopes.
 */
public class FortnoxScope {

	private final Set<String> scopes = new HashSet<String>();

	public FortnoxScope() {};
	
	/**
	 * Creates a scope. A scope is defined by a comma or space separated list of strings found in
	 * FortnoxConstants.SCOPE*
	 * 
	 * @param initialScopes
	 */
	public FortnoxScope(String initialScopes) {
		if (initialScopes==null || initialScopes.trim().length()==0)
			return;
		if (initialScopes.contains(","))
			addScopesFromCommaseparatedString(initialScopes);
		else if (initialScopes.contains(" "))
			addScopesFromSpaceSeparatedString(initialScopes);
		else {
			addScope(initialScopes);
		}
	}
	
	public FortnoxScope(Set<String> initialScopes) {
		for (String s : initialScopes) {
			addScope(s);
		}
	}
	
	public void addScope(String scope) {
		if (scope==null) return;
		if (!FortnoxConstants.ALL_SCOPES.contains(scope)) {
			// Invalid scope
		} else {
			scopes.add(scope);
		}
	}
	
	public void removeScope(String scope) {
		if (scope==null) return;
		scopes.remove(scope);
	}
	
	public boolean hasScopes() {
		return !scopes.isEmpty();
	}
	
	/**
	 * Returns the scope URL parameter for this scope (including the "scope")
	 * @return
	 */
	public String getScopesAsUrlParameter() {
		return "scope=" + getScopesSeparatedBy("%20");
	}

	/**
	 * Returns the scopes separated by spaces
	 * @return
	 */
	public String getScopesSeparatedBySpace() {
		return getScopesSeparatedBy(" ");
	}
	
	/**
	 * Get scopes separated by comma
	 * 
	 * @return
	 */
	public String getScopesSeparatedByComma() {
		return getScopesSeparatedBy(",");
	}
	
	private String getScopesSeparatedBy(String separator) {
		StringBuffer buf = new StringBuffer();
		
		for (String s : scopes) {
			if (buf.length()>0) {
				buf.append(separator);
			}
			buf.append(s);
		}

		return buf.toString();
	}
	
	private void addScopesFromCommaseparatedString(String scopeStr) {
		String[] scopeArray = scopeStr.split(",");
		for (String s : scopeArray) {
			addScope(s);
		}
	}
	
	private void addScopesFromSpaceSeparatedString(String scopeStr) {
		String[] scopeArray = scopeStr.split(" ");
		for (String s : scopeArray) {
			addScope(s);
		}
	}
	
	public boolean isArchive() {
		return scopes.contains(FortnoxConstants.SCOPE_ARCHIVE);
	}
	
	public boolean isArticle() {
		return scopes.contains(FortnoxConstants.SCOPE_ARTICLE);
	}

	public boolean isAssets() {
		return scopes.contains(FortnoxConstants.SCOPE_ASSETS);
	}

	public boolean isBookkeeping() {
		return scopes.contains(FortnoxConstants.SCOPE_BOOKKEEPING);
	}
	
	public boolean isCompanyInformation() {
		return scopes.contains(FortnoxConstants.SCOPE_COMPANYINFORMATION);
	}

	public boolean isConnectFile() {
		return scopes.contains(FortnoxConstants.SCOPE_CONNECT_FILE);
	}
	
	public boolean isCostCenter() {
		return scopes.contains(FortnoxConstants.SCOPE_COST_CENTER);
	}

	public boolean isCurrency() {
		return scopes.contains(FortnoxConstants.SCOPE_CURRENCY);
	}

	public boolean isCustomer() {
		return scopes.contains(FortnoxConstants.SCOPE_CUSTOMER);
	}

	public boolean isDeletevoucher() {
		return scopes.contains(FortnoxConstants.SCOPE_DELETEVOUCHER);
	}

	public boolean isInbox() {
		return scopes.contains(FortnoxConstants.SCOPE_INBOX);
	}

	public boolean isInvoice() {
		return scopes.contains(FortnoxConstants.SCOPE_INVOICE);
	}

	public boolean isNoxFinanceInvoice() {
		return scopes.contains(FortnoxConstants.SCOPE_NOX_FINANCE);
	}

	public boolean isOffer() {
		return scopes.contains(FortnoxConstants.SCOPE_OFFER);
	}

	public boolean isOrder() {
		return scopes.contains(FortnoxConstants.SCOPE_ORDER);
	}

	public boolean isPayment() {
		return scopes.contains(FortnoxConstants.SCOPE_PAYMENT);
	}

	public boolean isPrice() {
		return scopes.contains(FortnoxConstants.SCOPE_PRICE);
	}

	public boolean isPrint() {
		return scopes.contains(FortnoxConstants.SCOPE_PRINT);
	}

	public boolean isProfile() {
		return scopes.contains(FortnoxConstants.SCOPE_PROFILE);
	}

	public boolean isProject() {
		return scopes.contains(FortnoxConstants.SCOPE_PROJECT);
	}

	public boolean isSalary() {
		return scopes.contains(FortnoxConstants.SCOPE_SALARY);
	}

	public boolean isSettings() {
		return scopes.contains(FortnoxConstants.SCOPE_SETTINGS);
	}

	public boolean isSupplier() {
		return scopes.contains(FortnoxConstants.SCOPE_SUPPLIER);
	}

	public boolean isSupplierInvoice() {
		return scopes.contains(FortnoxConstants.SCOPE_SUPPLIERINVOICE);
	}

	public boolean isTimeReporting() {
		return scopes.contains(FortnoxConstants.SCOPE_TIMEREPORTING);
	}
	
	
}
