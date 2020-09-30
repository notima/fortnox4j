package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Articles")
public class Articles {

	private List<ArticleSubset>	subset;
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

	@XmlElement(name="ArticleSubset")
	public List<ArticleSubset> getArticleSubset() {
		return subset;
	}

	public void setArticleSubset(List<ArticleSubset> subset) {
		this.subset = subset;
	}

}
