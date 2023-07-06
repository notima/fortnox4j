package org.notima.api.fortnox;

import org.notima.api.fortnox.entities3.ErrorInformation;

public class FortnoxScopeException extends FortnoxException {

	public FortnoxScopeException(ErrorInformation e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3277095736642152786L;

}
