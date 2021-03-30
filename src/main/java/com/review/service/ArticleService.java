package com.review.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.dao.ArticleDao;
import com.review.dto.Article;
import com.review.dto.ResultData;


@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public List<Article> showList() {
		return articleDao.articles;
	}
	public ResultData modifyArticle(Integer id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", "존재하지 않는 게시물입니다.", "id",id);
		}
		
		articleDao.modifyArticle(id, title, body);
		return new ResultData("S-1", "게시물이 수정되었습니다.", "id",id);
	}
	
	
	public ResultData deleteArticle(int id) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", "게시물이 존재하지 않습니다.", "id",id);
		}
		
		articleDao.deleteArticle(id);
		
		return new ResultData("S-1", id+"번 게시물이 삭제되었습니다.", "id",id);
		
	}
	
	public Article getArticleById(int id) {
		
		return articleDao.getArticleById(id);
	}
	public ResultData addArticle(String title, String body) {
		int id = articleDao.addArticle(title, body);
		
		return new ResultData("S-1", id+"번 글이 등록되었습니다.", "id",id);
	}
}
