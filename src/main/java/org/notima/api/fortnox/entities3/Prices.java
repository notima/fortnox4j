package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Prices {

	private List<PriceSubset>	subset;
	private Integer				totalResources;
	private Integer				totalPages;
	private Integer				currentPage;

	@XmlAttribute(name="TotalResources")
	public Integer getTotalResources() {
		return totalResources;
	}

	public void setTotalResources(Integer totalResources) {
		this.totalResources = totalResources;
	}

	@XmlAttribute(name="TotalPages")
	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	@XmlAttribute(name="CurrentPage")
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@XmlElement(name="PriceSubset")
	public List<PriceSubset> getPriceSubset() {
		return subset;
	}

	public void setPriceSubset(List<PriceSubset> subset) {
		this.subset = subset;
	}

}
