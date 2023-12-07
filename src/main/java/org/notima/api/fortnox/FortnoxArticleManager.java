package org.notima.api.fortnox;

import java.util.Map;
import java.util.TreeMap;

import org.notima.api.fortnox.entities3.Article;
import org.notima.api.fortnox.entities3.ArticleInterface;
import org.notima.api.fortnox.exception.ArticleNotFoundException;

/**
 * Class that caches articles for a given client as long as it's "alive".
 * 
 * 
 * @author Daniel Tamm
 *
 */
public class FortnoxArticleManager {

	private FortnoxClient3	client;
	
	private Map<String, ArticleInterface>	articleCache = new TreeMap<String, ArticleInterface>();
	

	public FortnoxArticleManager(FortnoxClient3 client) {
		this.client = client;
	}

	
	public ArticleInterface getArticle(String articleNo) throws ArticleNotFoundException, Exception {
		
		if (articleNo==null) throw new ArticleNotFoundException(null);
		
		return lookupArticle(articleNo);
		
	}
	
	private ArticleInterface lookupArticle(String articleNo) throws ArticleNotFoundException, Exception {
		
		ArticleInterface article = articleCache.get(articleNo);
		if (article==null) {
			article = refreshArticleFromFortnox(articleNo);
		}
		return article;
		
	}
	
	private ArticleInterface refreshArticleFromFortnox(String articleNo) throws Exception, ArticleNotFoundException {
		
		Article fortnoxArticle = client.getArticleByArticleNo(articleNo);
		articleCache.put(fortnoxArticle.getArticleNumber(), fortnoxArticle);

		return fortnoxArticle;
		
	}
	
	
}
