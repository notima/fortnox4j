package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class EmailInformation {

    private String EmailAddressFrom;
    private String EmailAddressTo = "";
    private String EmailAddressCC;
    private String EmailAddressBCC;
    private String EmailSubject ="";
    private String EmailBody = "";
    
    @XmlElement(name="EmailAddressFrom")
	public String getEmailAddressFrom() {
		return EmailAddressFrom;
	}
	public void setEmailAddressFrom(String emailAddressFrom) {
		EmailAddressFrom = emailAddressFrom;
	}
	
	@XmlElement(name="EmailAddressTo")
	public String getEmailAddressTo() {
		return EmailAddressTo;
	}
	public void setEmailAddressTo(String emailAddressTo) {
		EmailAddressTo = emailAddressTo;
	}
	
	@XmlElement(name="EmailAddressToCC")
	public String getEmailAddressCC() {
		return EmailAddressCC;
	}
	public void setEmailAddressCC(String emailAddressCC) {
		EmailAddressCC = emailAddressCC;
	}
	
	@XmlElement(name="EmailAddressToBCC")
	public String getEmailAddressBCC() {
		return EmailAddressBCC;
	}
	public void setEmailAddressBCC(String emailAddressBCC) {
		EmailAddressBCC = emailAddressBCC;
	}
	
	@XmlElement(name="EmailSubject")
	public String getEmailSubject() {
		return EmailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		EmailSubject = emailSubject;
	}
	
	@XmlElement(name="EmailBody")
	public String getEmailBody() {
		return EmailBody;
	}
	public void setEmailBody(String emailBody) {
		EmailBody = emailBody;
	}
	
}
