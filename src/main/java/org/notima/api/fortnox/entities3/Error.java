package org.notima.api.fortnox.entities3;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="error", propOrder = {
		"code",
		"message",
		"messageeng"
})
@XmlRootElement(name = "error")
public class Error {

    @XmlElement(required = true)
    protected BigInteger code;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected String messageeng;
    
	public BigInteger getCode() {
		return code;
	}
	public void setCode(BigInteger code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageeng() {
		return messageeng;
	}
	public void setMessageeng(String messageeng) {
		this.messageeng = messageeng;
	}
	
    
    
}
