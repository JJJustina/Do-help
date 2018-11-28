package com.doHelp.service;

import java.util.List;

import com.doHelp.model.Article;
import com.doHelp.model.User;

public interface ArticleShareServices {
	public void save(Article article) throws Exception ;
	public Article  findArticleById(int article_id)throws Exception ;
	public void saveSnap(String time,int article_id,int user_id) throws Exception;
	public void saveCollectArticle(String time,int article_id,int user_id) throws Exception;
	public void update(Article  article) throws Exception ;
	public void  snapCount(Article  article)throws Exception;
	public void  seeCount(Article article)throws Exception;
	public void  reportCount(Article article)throws Exception;
	public void saveReport(int reportType_id,int article_id,int user_id) throws Exception;
	public void  articleStatus(String  article_is_verify_code ,int article_id )throws Exception;
	public List<Article> find() throws Exception;
	public List<Article> findCollectArticle(User user) throws Exception ;
	public void delete(Article article) throws Exception;
	
}
