package com.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.review.dto.Article;
import com.review.dto.ResultData;
import com.review.service.ArticleService;
import com.review.util.Util;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("usr/article/list")
	@ResponseBody
	public List<Article> showList() {
	
		return articleService.showList();
	}
	@RequestMapping("usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요.");
		}
		Article article = articleService.getArticleById(id);
		if(article == null) {
			return new ResultData("F-1", id+"번 글은 존재하지 않습니다.", "id",id);
		}
		return new ResultData("S-1", id+"번 글 입니다.", "article",article);
	}
	@RequestMapping("usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title,String body) {
		if(Util.isEmpty(title)) {
			return new ResultData("F-1","제목을 입력해주세요.");
		}
		
		if(Util.isEmpty(body)) {
			return new ResultData("F-1","내용을 입력해주세요.");
		}
		
		return articleService.addArticle(title, body);
	}
	
	@RequestMapping("usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "삭제할 번호를 입력해주세요.");
		}
		
		
		
		return articleService.deleteArticle(id);
	}
	
	@RequestMapping("usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "수정할 번호를 입력해주세요.");
		}
		if(Util.isEmpty(title)) {
			return new ResultData("F-2", "수정할 제목를 입력해주세요.");
		}
		if(Util.isEmpty(body)) {
			return new ResultData("F-3", "수정할 내용를 입력해주세요.");
		}
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-4", "존재하지 않는 게시물 번호입니다.");
		}
		return articleService.modifyArticle(id, title, body);
	}
	
	
}
