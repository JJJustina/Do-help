package com.doHelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.DAOFactory;
import com.doHelp.model.Article;
import com.doHelp.model.User;
import com.doHelp.service.ArticleShareServices;

@Service
public class ArticleShareServiceImp implements ArticleShareServices{
	@Autowired
	DAOFactory  DAOFactory;
	
	@SuppressWarnings("static-access")
	@Override
	public void save(Article article) throws Exception {
		DAOFactory.getArticleShareDAO().save(article);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public Article  findArticleById(int article_id)throws Exception {
		return DAOFactory.getArticleShareDAO().findArticleById(article_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void saveSnap(String time,int article_id,int user_id) throws Exception {
		DAOFactory.getArticleShareDAO().saveSnap( time, article_id, user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void saveCollectArticle(String time,int article_id,int user_id) throws Exception  {
		DAOFactory.getArticleShareDAO().saveSnap( time, article_id, user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void seeCount(Article article) throws Exception {
		DAOFactory.getArticleShareDAO().seeCount(article);
	}

	
    @SuppressWarnings("static-access")
    @Override
	public void snapCount(Article article) throws Exception{
		 DAOFactory.getArticleShareDAO().snapCount(article);
   }
	
    
	@SuppressWarnings("static-access")
	@Override
	public void update(Article article) throws Exception {
		 DAOFactory.getArticleShareDAO().update(article);
	}

	
	@SuppressWarnings("static-access")
	@Override
	public List<Article> find() throws Exception {
		 return DAOFactory.getArticleShareDAO().find();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public List<Article> findCollectArticle(User user) throws Exception {
		 return DAOFactory.getArticleShareDAO().find();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void delete(Article article) throws Exception {
		DAOFactory.getArticleShareDAO().delete(article);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void reportCount(Article article) throws Exception {
		DAOFactory.getArticleShareDAO().reportCount(article);
	}

	
	@SuppressWarnings("static-access")
	@Override
	public void saveReport(int reportType_id,int article_id,int user_id) throws Exception {
		DAOFactory.getArticleShareDAO().saveReport(reportType_id,article_id,user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void  articleStatus(String  article_is_verify_code ,int article_id )throws Exception{
		DAOFactory.getArticleShareDAO().articleStatus(article_is_verify_code,article_id);
	}
}
