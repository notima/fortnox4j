package org.notima.api.fortnox.entities3;

public class CostCenter extends CostCenterSubset {

	public CostCenter() {};
	
	public CostCenter(CostCenterSubset cs) {
		this.url = cs.url;
		this.code = cs.code;
		this.description = cs.description;
		this.note = cs.note;
		this.active = cs.active;
	}
	
}
