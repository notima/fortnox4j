package org.notima.api.fortnox;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

import org.notima.api.fortnox.clients.FortnoxClientList;
import org.notima.api.fortnox.entities3.Customer;
import org.notima.api.fortnox.entities3.Invoice;
import org.notima.api.fortnox.entities3.InvoiceRow;
import org.notima.api.fortnox.entities3.InvoiceRows;
import org.notima.api.fortnox.entities3.InvoiceSubset;
import org.notima.api.fortnox.entities3.Invoices;

/**
 * Utility class for Fortnox API.
 * 
 * Copyright 2019 Notima System Integration AB (Sweden)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Daniel Tamm
 *
 */

public class FortnoxUtil {

	public static String  allowedPatternStr = "[\\p{L}\\’\\\\\\x{0308}\\x{030a}a-zåäöéáœæøüA-ZÅÄÖÉÁÜŒÆØ0-9 –:\\.`´’,;\\^¤#%§£$€¢¥©™°&\\/\\(\\)=\\+\\-\\*_\\!?²³®½\\@\\x{00a0}\\n\\r]";
	public static Pattern allowedPattern = Pattern.compile(allowedPatternStr);
	public static Pattern allowedChars = Pattern.compile("^" + allowedPatternStr + "*$");
	
	/**
	 * Removes non-allowed characters from given string by replacing it by a white space
	 * 
	 * @param src		The string to be parsed.
	 * @return			A string cleared of non-allowed characters.
	 */
	public static String removeNonAllowedCharacters(String src) {
		
		if (src==null) return null;

		Matcher matcher = allowedPattern.matcher(src);
		if (matcher.matches())
			// No illegal characters found (ie all are allowed)
			return src;
		else {
			
			StringBuffer dst = new StringBuffer();
			Matcher cMatcher;
			// Replace all non allowed chars with space
			String c;
			for (int i=0; i<src.length(); i++) {
				c = src.substring(i, i+1);
				cMatcher = allowedChars.matcher(c);
				if (cMatcher.matches()) {
					dst.append(c);
				} else {
					dst.append(" ");
				}
			}
			
			return dst.toString();
		}
		
	}
	
	/**
	 * Reads a Fortnox Client list from XML-file
	 * 
	 * @param fileName		The file to read. If path isn't given, the classpath is searched.
	 * @return	The client list if successfully read
	 * @throws JAXBException	If XML can't be parsed. 
	 */
	public static FortnoxClientList readFortnoxClientListFromFile(String fileName) throws JAXBException {
		
		FortnoxClientList result = null;

		URL url = ClassLoader.getSystemResource(fileName);
		
		if (url!=null) {
			fileName = url.getFile();
		}
		File f = new File(fileName);
		
		result = JAXB.unmarshal(f, FortnoxClientList.class);
		
		return result;
		
	}
	
	
	/**
	 * Writes a Fortnox Client list to XML-file
	 * 
	 * @param list				The client list
	 * @param fileName			Should be absolute path to avoid saving the file in wrong location
	 * @throws JAXBException	If XML can't be parsed. 
	 */
	public static void writeFortnoxClientListToFile(FortnoxClientList list, String fileName) throws JAXBException {
		
		File f = new File(fileName);
		JAXB.marshal(list, f);
		
	}
	
	
	/**
	 * Copies customer invoices from one Fortnox client to another.
	 * 
	 * @param clSrc			The source client.
	 * @param clDst			The destination client.
	 * @param fromDate		From date
	 * @param untilDate		Until date
	 * @param os			Optional output stream to print progress.
	 * @return				The number of invoices copied.
	 * @throws 				Exception if something goes wrong.
	 */
	public static int copyCustomerInvoices(
			FortnoxClient3 clSrc, 
			FortnoxClient3 clDst, 
			Date fromDate, 
			Date untilDate,
			PrintStream os) throws Exception {
		
		int invoiceCount = 0;
		
		Invoices src = clSrc.getAllCustomerInvoicesByDateRange(fromDate, untilDate);
		List<InvoiceSubset> subset = src.getInvoiceSubset();
		Invoice i, existingInvoice;
		
		Customer customer, dstCustomer;
		String custNo, invoiceNo;
		for (InvoiceSubset is : subset) {

			i = clSrc.getInvoice(is.getDocumentNumber());
			custNo = i.getCustomerNumber();
			customer = clSrc.getCustomerByCustNo(custNo);
			dstCustomer = clDst.getCustomerByCustNo(custNo);
			if (dstCustomer==null) {
				if (os!=null)
					os.println("Creating new customer " + customer.getCustomerNumber() + " : " + customer.getName());
				// Clear read only fields
				customer.setCountry(null);
				customer.setDeliveryCountry(null);
				dstCustomer = clDst.setCustomer(customer);
			}
			i.setCustomerNumber(dstCustomer.getCustomerNumber());
			// See if invoice already exists
			invoiceNo = i.getDocumentNumber();
			existingInvoice = clDst.getInvoice(invoiceNo);
			if (existingInvoice==null) {
				// Clear invoice number, since we can't have an invoice number set when creating a new invoice
				i.setDocumentNumber(null);
				// Copy source invoice number to extInvoiceNo2
				i.setExternalInvoiceReference2(i.getDocumentNumber());
			}
			// Purge the invoice from read-only fields
			FortnoxUtil.purgeInvoice(i, true, true, true);
			// Persist the invoice
			clDst.setInvoice(i);
			invoiceCount++;
			if (os!=null)
				os.println("Invoice No " + invoiceNo + " copied");
		}
		
		return invoiceCount;
		
	}
	
	/**
	 * Purges an invoice from read-only fields and optional other fields.
	 * Handy when copying an invoice.
	 * 
	 * @param src					The invoice to be purged. This record is changed and returned.
	 * @param removeArticleNo		Remove references to articles.
	 * @param removeCostCenter		Remove references to cost center.
	 * @param removeProject			Remove references to project.
	 * @return		A purged invoice.
	 */
	public static Invoice purgeInvoice(Invoice src, boolean removeArticleNo, boolean removeCostCenter, boolean removeProject) {
		
		// Clear read only fields
		src.setAdministrationFeeVAT(null);
		src.setBasisTaxReduction(null);
		src.setBalance(null);
		src.setBooked(null);
		src.setCancelled(null);
		src.setCredit(null);
		src.setCreditInvoiceReference(null);
		src.setContributionPercent(null);
		src.setContributionValue(null);
		src.setFreightVAT(null);
		src.setGross(null);
		src.setHouseWork(null);
		src.setInvoicePeriodStart(null);
		src.setInvoicePeriodEnd(null);
		src.setLastRemindDate(null);
		src.setNet(null);
		src.setOfferReference(null);
		src.setOrderReference(null);
		src.setInvoiceReference(null);
		src.setOrganisationNumber(null);
		src.setReminders(null);
		src.setRoundOff(null);
		src.setSent(null);
		src.setTaxReduction(null);
		src.setTotal(null);
		src.setTotalVAT(null);
		src.setVoucherNumber(null);
		src.setVoucherSeries(null);
		src.setVoucherYear(null);
		src.setEdiInformation(null);
		src.setTotalToPay(null);
		if (removeProject)
			src.setProject(null);
		if (removeCostCenter)
			src.setCostCenter(null);
		
		// Clear on row level
		InvoiceRows rows = src.getInvoiceRows();
		for (InvoiceRow r : rows.getInvoiceRow()) {
			r.setContributionPercent(null);
			r.setContributionValue(null);
			// Don't use articles, costcenters or projects
			if (removeArticleNo)
				r.setArticleNumber(null);
			if (removeProject)
				r.setProject(null);
			if (removeCostCenter)
				r.setCostCenter(null);
		}
		
		return src;
		
	}
	
	
}
