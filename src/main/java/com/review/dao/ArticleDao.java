package com.review.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.review.dto.Article;
import com.review.util.Util;

@Component
public class ArticleDao {
	public List<Article> articles;
	private int articlesLastId;
	
	public ArticleDao() {
		articles = new ArrayList<>();
		articlesLastId = 0;
		TestList();
	}
	
	public void TestList() {
		for(int i = 0; i < 2; i++) {
			addArticle("제목","내용");
		}
	}
	
	public boolean modifyArticle(Integer id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return false;
		}
		article.setUpdateData(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);
		return true;
	}
	
	
	public void deleteArticle(int id) {
		Article article = getArticleById(id);
		
		articles.remove(article);
	}
	
	public Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	public int addArticle(String title, String body) {
		int id = articlesLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateData = regDate;
		
		Article article = new Article(id,regDate,updateData,title,body);
		articles.add(article);
		
		articlesLastId = id;
		return id;
	}

}
