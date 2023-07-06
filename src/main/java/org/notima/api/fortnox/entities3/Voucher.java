package org.notima.api.fortnox.entities3;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.notima.api.fortnox.FortnoxClient3;
import org.notima.util.NumberUtils;

@XmlRootElement(name="Voucher")
public class Voucher extends VoucherSubset {

	private String	costCenter;
	private String	project;
	private VoucherRows voucherRows;
	private String	voucherSeries;
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	
	@XmlElement(name="VoucherRows")	
	public VoucherRows getVoucherRows() {
		return voucherRows;
	}
	public void setVoucherRows(VoucherRows voucherRows) {
		this.voucherRows = voucherRows;
	}
	
	public void addVoucherRow(VoucherRow r) {
		if (voucherRows==null) {
			voucherRows = new VoucherRows();
		}
		if (voucherRows.getVoucherRow()==null) {
			voucherRows.setVoucherRow(new ArrayList<VoucherRow>());
		}
		voucherRows.getVoucherRow().add(r);
	}
	
	/**
	 * Create a reversed voucher from the given voucher. NOTE! Any existing lines are not cleared.
	 * Note! Transaction date is not set.
	 * 
	 * @param toReverse		The voucher to reverse.
	 * @param revTxt		The text that explains that this is a reversed voucher.
	 * 
	 * @return		This voucher with reversed lines from. 
	 */
	public Voucher reverse(Voucher toReverse, String revTxt) {
		
		if (toReverse!=null && toReverse.voucherRows!=null) {

			if (description==null || description.trim().length()==0) {
				// Copy description
				description = (revTxt!=null ? (revTxt + " : ") : "") + toReverse.getVoucherSeries() + toReverse.getVoucherNumber() + " : " + toReverse.getDescription();
			}

			costCenter = toReverse.costCenter;
			project = toReverse.project;
			voucherSeries = toReverse.voucherSeries;
			
			// Iterate through the lines
			VoucherRow negR = null;
			for (VoucherRow vr : toReverse.voucherRows.getVoucherRow()) {
				if (vr.getRemoved()!=null && vr.getRemoved().equals(Boolean.TRUE)) {
					continue;
				}
				negR = vr.negated();
				addVoucherRow(negR);
			}
			
		}
		
		return this;
	}
	
	/**
	 * Converts the amounts in the voucher to accounting currency, but keeping the 
	 * original currency and conversion rate in the transaction information. 
	 * 	
	 * @param sourceCurrency
	 * @return
	 */
	public Voucher currencyConvert(Currency sourceCurrency) {

		if (sourceCurrency==null) return this;
		if (sourceCurrency.getCode().equalsIgnoreCase(FortnoxClient3.DEFAULT_ACCOUNTING_CURRENCY)) {
			return this;
		}
		
		if (voucherRows==null || voucherRows.getVoucherRow()==null) return this;
		
		for (VoucherRow vr : voucherRows.getVoucherRow()) {

			currencyConvertRow(sourceCurrency, vr);
			
		}
		
		return this;
		
	}

	/**
	 * Balances the voucher using the balance account as account.
	 * 
	 * @param balanceAccount
	 */
	public void balanceVoucher(Integer balanceAccount) {

		Double amount = calculateBalance();
		VoucherRow vr = new VoucherRow();
		vr.setAccount(balanceAccount);
		vr.setAmount(-amount);
		addVoucherRow(vr);
		
	}
	
	/**
	 * Calculates balance on the voucher. If balanced it should be 0. 
	 * 
	 * @return	The open balance
	 */
	public Double calculateBalance() {
		
		if (voucherRows==null || voucherRows.getVoucherRow()==null || voucherRows.getVoucherRow().isEmpty()) {
			return Double.valueOf(0);
		}
		
		double balance = 0d;
		for (VoucherRow vr : voucherRows.getVoucherRow()) {
			balance += vr.getAmount();
		}

		balance = NumberUtils.roundToPrecision(balance, FortnoxClient3.DEFAULT_ROUNDING_PRECISION);
		
		return Double.valueOf(balance);
	}
	
	private void currencyConvertRow(Currency srcCurrency, VoucherRow vr) {

		double rate = srcCurrency.getBuyRate();
		
		vr.appendTransactionInformation(Currency.currencyRateToString(vr.getAbsoluteAmount(), rate, srcCurrency.getCode()));
		vr.setCredit(NumberUtils.roundToPrecision(vr.getCredit() * rate, FortnoxClient3.DEFAULT_ROUNDING_PRECISION));
		vr.setDebit(NumberUtils.roundToPrecision(vr.getDebit() * rate, FortnoxClient3.DEFAULT_ROUNDING_PRECISION));
		
	}
	
}
