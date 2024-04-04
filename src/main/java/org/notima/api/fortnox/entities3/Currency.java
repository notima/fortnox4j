package org.notima.api.fortnox.entities3;

import java.beans.Transient;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxConstants;
import org.notima.util.NumberUtils;

public class Currency {

	private String	url;
	private String code;
	private double	buyRate;
	private double	sellRate;
	private String	date;
	private Date	dateAsDate;
	private String	description;
	private int		isAutomatic;
	private int		unit;

	public static DateFormat	s_dfmt = new SimpleDateFormat(FortnoxClient3.s_dfmtStr);	
	
	public Currency() {};
	
	public Currency(String srcCurrency) {
		code = srcCurrency;
	}

	/**
	 * We could possible use › instead of =&gt; if the gt sign causes problems.
	 * 
	 * @param amount
	 * @param rate
	 * @param currency
	 * @return
	 */
	public static String currencyRateToString(double amount, double rate, String currency) {
		
		return NumberUtils.roundToPrecision(amount, FortnoxConstants.DEFAULT_ROUNDING_PRECISION) + " " + currency + "=>" + FortnoxConstants.DEFAULT_ACCOUNTING_CURRENCY + "@" + rate;
		
	}
	
	
	@XmlAttribute(name="url")	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name="Unit")	
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	
	@XmlElement(name="Code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@XmlElement(name="BuyRate")
	public double getBuyRate() {
		return buyRate;
	}
	public void setBuyRate(double buyRate) {
		this.buyRate = buyRate;
	}
	
	@XmlElement(name="SellRate")
	public double getSellRate() {
		return sellRate;
	}
	public void setSellRate(double sellRate) {
		this.sellRate = sellRate;
	}
	
	@XmlElement(name="Date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Transient
	public Date getDateAsDate() {
		if (date==null) return null;
		try {
			dateAsDate = s_dfmt.parse(date);
			return dateAsDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateAsDate(Date dateAsDate) {
		this.dateAsDate = dateAsDate;
		if (dateAsDate!=null)
			date = s_dfmt.format(dateAsDate);
		else
			date = null;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name="IsAutomatic")
	public int getIsAutomatic() {
		return isAutomatic;
	}
	public void setIsAutomatic(int isAutomatic) {
		this.isAutomatic = isAutomatic;
	}

	
	
	
}
