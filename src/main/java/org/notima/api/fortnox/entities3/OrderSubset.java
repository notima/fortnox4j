package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class OrderSubset implements OrderInterface {

	private boolean	cancelled;
	private String	currency;
	private String	customerName;
	private String	customerNumber;
	private String	deliveryDate;
	private String	documentNumber;
	private String	externalInvoiceReference1;
	private String	externalInvoiceReference2;
	private String	orderDate;
	private int		project;
	private double	total;

	@XmlElement(name="Cancelled")
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@XmlElement(name="Currency")	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name="CustomerName")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@XmlElement(name="CustomerNumber")
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	@XmlElement(name="DeliveryDate")
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@XmlElement(name="DocumentNumber")
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@XmlElement(name="ExternalInvoiceReference1")
	public String getExternalInvoiceReference1() {
		return externalInvoiceReference1;
	}
	public void setExternalInvoiceReference1(String externalInvoiceReference1) {
		this.externalInvoiceReference1 = externalInvoiceReference1;
	}
	
	@XmlElement(name="ExternalInvoiceReference2")
	public String getExternalInvoiceReference2() {
		return externalInvoiceReference2;
	}
	public void setExternalInvoiceReference2(String externalInvoiceReference2) {
		this.externalInvoiceReference2 = externalInvoiceReference2;
	}
	
	@XmlElement(name="OrderDate")
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	@XmlElement(name="Project")
	public int getProject() {
		return project;
	}
	public void setProject(int project) {
		this.project = project;
	}
	
	@XmlElement(name="Total")
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
