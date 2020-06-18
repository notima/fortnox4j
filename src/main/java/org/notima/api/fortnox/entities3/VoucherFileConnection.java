package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * https://developer.fortnox.se/documentation/resources/voucher-file-connections/
 * 
 * @author Daniel Tamm
 *
 */
@XmlRootElement(name="VoucherFileConnection")
public class VoucherFileConnection {

	private String	fileId;
	private String	voucherDescription;
	private String	voucherNumber;
	private String	voucherSeries;
	private Integer	voucherYear;

	@XmlElement(name="FileId")
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	@XmlElement(name="VoucherDescription")
	public String getVoucherDescription() {
		return voucherDescription;
	}
	public void setVoucherDescription(String voucherDescription) {
		this.voucherDescription = voucherDescription;
	}
	@XmlElement(name="VoucherNumber")
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	
	@XmlElement(name="VoucherSeries")
	public String getVoucherSeries() {
		return voucherSeries;
	}
	public void setVoucherSeries(String voucherSeries) {
		this.voucherSeries = voucherSeries;
	}
	
	@XmlElement(name="VoucherYear")
	public Integer getVoucherYear() {
		return voucherYear;
	}
	public void setVoucherYear(Integer voucherYear) {
		this.voucherYear = voucherYear;
	}
	
	
	
}
