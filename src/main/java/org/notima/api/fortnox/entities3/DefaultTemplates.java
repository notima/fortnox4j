package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class DefaultTemplates {

    private String order;
    private String offer;
    private String invoice;
    private String cashInvoice;

	@XmlElement(name="Order")
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	@XmlElement(name="Offer")
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	
	@XmlElement(name="Invoice")
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	@XmlElement(name="CashInvoice")
	public String getCashInvoice() {
		return cashInvoice;
	}
	public void setCashInvoice(String cashInvoice) {
		this.cashInvoice = cashInvoice;
	}
	
}
