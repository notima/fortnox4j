package org.notima.api.fortnox.entities3;

/**
 * Record to represent information about a vat tax.
 */
public class VatInfo {

	private String	fortnoxPredefinedCode;
	private String	vatCode;
	private String	vatReportCode;
	private Integer	defaultRevenueAccount;
	private Integer	defaultVatDueAccount;
	private Integer defaultVatClaimAccount;
	private boolean	isReverse = false;
	private double	vatRate;
	private String	taxDomicile;

	public VatInfo() {}
	
	public VatInfo(PreDefinedAccountSubset pdas) {
		fortnoxPredefinedCode = pdas.getName();
		defaultRevenueAccount = pdas.getAccount();
	}

	public VatInfo(String predefinedCode, Integer revenueAccount) {
		fortnoxPredefinedCode = predefinedCode;
		defaultRevenueAccount = revenueAccount;
	}
	
	public VatInfo(AccountSubset as) {
		enrichFrom(as);
	}
	
	public void enrichFrom(AccountSubset as) {
		if (as==null) return;
		if (as.getVATCode()!=null) {
			vatCode = as.getVATCode();
		}
	}
	
	/**
	 * The predefined code in Fortnox (if any).
	 * 
	 * @return
	 * @see org.notima.api.fortnox.FortnoxConstants.ACCT_SALES_MP1 as example
	 */
	public String getFortnoxPredefinedCode() {
		return fortnoxPredefinedCode;
	}
	public void setFortnoxPredefinedCode(String fortnoxPredefinedCode) {
		this.fortnoxPredefinedCode = fortnoxPredefinedCode;
	}
	
	/**
	 * The VAT code in the chart of accounts.
	 * @return
	 */
	public String getVatCode() {
		return vatCode;
	}
	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}
	
	/**
	 * The VAT code used for reporting to tax authorities.
	 * @return
	 */
	public String getVatReportCode() {
		return vatReportCode;
	}
	public void setVatReportCode(String vatReportCode) {
		this.vatReportCode = vatReportCode;
	}
	
	/**
	 * Default revenue account for this VAT type.
	 * 
	 * @return
	 */
	public Integer getDefaultRevenueAccount() {
		return defaultRevenueAccount;
	}
	public void setDefaultRevenueAccount(Integer defaultRevenueAccount) {
		this.defaultRevenueAccount = defaultRevenueAccount;
	}
	
	/**
	 * Default tax due for this VAT type.
	 * 
	 * @return
	 */
	public Integer getDefaultVatDueAccount() {
		return defaultVatDueAccount;
	}
	public void setDefaultVatDueAccount(Integer defaultVatDueAccount) {
		this.defaultVatDueAccount = defaultVatDueAccount;
	}
	
	/**
	 * Default tax to claim for this VAT type.
	 * 
	 * @return
	 */
	public Integer getDefaultVatClaimAccount() {
		return defaultVatClaimAccount;
	}
	public void setDefaultVatClaimAccount(Integer defaultVatClaimAccount) {
		this.defaultVatClaimAccount = defaultVatClaimAccount;
	}
	
	/**
	 * Indicator if this tax type is a reverse tax (meaning due and claim will be equal).
	 * 
	 * @return
	 */
	public boolean isReverse() {
		return isReverse;
	}
	public void setReverse(boolean isReverse) {
		this.isReverse = isReverse;
	}
	
	/**
	 * The VAT-rate in percent. 25.0 = 25%.
	 * 
	 * @return
	 */
	public double getVatRate() {
		return vatRate;
	}
	public void setVatRate(double vatRate) {
		this.vatRate = vatRate;
	}
	
	/**
	 * The tax domicile that this rate is valid for. Usually an ISO-country code.
	 * 
	 * @return
	 */
	public String getTaxDomicile() {
		return taxDomicile;
	}
	public void setTaxDomicile(String taxDomicile) {
		this.taxDomicile = taxDomicile;
	}
	
	
	
}
