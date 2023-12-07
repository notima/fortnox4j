package org.notima.api.fortnox.exception;

public class ArticleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6212178812382577514L;
	private String articleNo;
	
	public ArticleNotFoundException(String articleNo) {
		this.articleNo = articleNo;
	}
	
	public String toString() {
		return ("Article " + articleNo + " not found");
	}
	
}
