package org.notima.api.fortnox;

public class FortnoxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5975880802908077519L;
	
	private org.notima.api.fortnox.entities3.Error error;
	private org.notima.api.fortnox.entities3.ErrorInformation errorInformation;
	
	public FortnoxException(org.notima.api.fortnox.entities3.Error e) {
		error = e;
	}
	
	public FortnoxException(org.notima.api.fortnox.entities3.ErrorInformation e) {
		errorInformation = e; 
	}
	
	public String toString() {
		if (error!=null)
			return(error.getCode() + " : " + error.getMessage());
		else
			return (errorInformation.getError() + " : " + errorInformation.getMessage() + " : " + errorInformation.getCode());
	}
	
}
