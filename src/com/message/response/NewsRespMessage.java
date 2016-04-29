package com.message.response;

import com.message.response.model.Articles;

public class NewsRespMessage extends BaseRespMessage {
	
	private int articleCount;
	private Articles articles;
	
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public Articles getArticles() {
		return articles;
	}
	public void setArticles(Articles articles) {
		this.articles = articles;
	}

}
