package com.doHelp.model;

public class Article {
	   //12个字段
		private int article_id;                              // (文字id,自增长0 int)
		private String article_publishTime;                 //(发布时间)  String
		private String article_is_verify_code;             //(被举报分数到一定程度时管理员是否审核T/F)  String
		private String article_title;                     //(文字标题) String
		private String article_info;                     //(介绍文字信息) String
		private int article_seeCount ;                  //(观看次数)  int
		private int article_snapCount ;                //(文字点赞数量) int
		private int article_reportCount ;             //(文字被举报总分数) int
		private int article_commentCount;            //(文字评论数量) int
		private String article_address;             //(存储地址)   String
		private int contentType_id ;               //(内容类型id) int
		private int user_id;                      //(不显示)(发布人id号) int
		public int getArticle_id() {
			return article_id;
		}
		public void setArticle_id(int article_id) {
			this.article_id = article_id;
		}
		public String getArticle_publishTime() {
			return article_publishTime;
		}
		public void setArticle_publishTime(String article_publishTime) {
			this.article_publishTime = article_publishTime;
		}
		public String getArticle_is_verify_code() {
			return article_is_verify_code;
		}
		public void setArticle_is_verify_code(String article_is_verify_code) {
			this.article_is_verify_code = article_is_verify_code;
		}
		public String getArticle_title() {
			return article_title;
		}
		public void setArticle_title(String article_title) {
			this.article_title = article_title;
		}
		public String getArticle_info() {
			return article_info;
		}
		public void setArticle_info(String article_info) {
			this.article_info = article_info;
		}
		public int getArticle_seeCount() {
			return article_seeCount;
		}
		public void setArticle_seeCount(int article_seeCount) {
			this.article_seeCount = article_seeCount;
		}
		public int getArticle_snapCount() {
			return article_snapCount;
		}
		public void setArticle_snapCount(int article_snapCount) {
			this.article_snapCount = article_snapCount;
		}
		public int getArticle_reportCount() {
			return article_reportCount;
		}
		public void setArticle_reportCount(int article_reportCount) {
			this.article_reportCount = article_reportCount;
		}
		public int getArticle_commentCount() {
			return article_commentCount;
		}
		public void setArticle_commentCount(int article_commentCount) {
			this.article_commentCount = article_commentCount;
		}
		public String getArticle_address() {
			return article_address;
		}
		public void setArticle_address(String article_address) {
			this.article_address = article_address;
		}
		public int getContentType_id() {
			return contentType_id;
		}
		public void setContentType_id(int contentType_id) {
			this.contentType_id = contentType_id;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		
}
