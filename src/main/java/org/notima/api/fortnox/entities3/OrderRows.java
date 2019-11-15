package org.notima.api.fortnox.entities3;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class OrderRows {

	private List<OrderRow> 	orderRow;

	@XmlElement(name="OrderRow")
	public List<OrderRow> getOrderRow() {
		return orderRow;
	}

	public void setOrderRow(List<OrderRow> orderRow) {
		this.orderRow = orderRow;
	}
	
}
