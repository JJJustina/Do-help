package com.doHelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.doHelp.dao.ArticleShareDAO;
import com.doHelp.model.Article;
import com.doHelp.model.User;
import com.doHelp.util.DBConnection;

public class ArticleShareDAOImp implements ArticleShareDAO{
	 /*  12个字段   */
	/* 插入数据  */
    private static final String INSERT = "insert into article(article_publishTime,article_is_verify_code,article_title,article_info,article_seeCount,article_snapCount,article_reportCount,article_commentCount,article_address,contentType_id,user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
	
    /*  返回不为空的article_is_verify_code 值为F的音频 */
    private static final String FIND = "select * from article where article_is_verify like 'F' ";
   
    /*   通过article_id 来查询相关文字   */
    private static final String FINDARTICLEBYID="select * form article where article_id=?";
    
    /*     通过article_id来查找用户user_id   
    private static final String FINDUSERID ="select user_id where article_id=?";
    */
    
    /*  通过article_id update用户上传的文字,即数据库article表中信息 */
    private static final String UPDATE = "update article set  article_title=? ,article_info=? where article_id=?";
	
	/*   文字点赞     */
    private static final String  UPDATESNAP="update article set article_snapCount=? where article_id=?";
    
    /*   保存点赞记录    */
    private static final String  UPDATESNAPMESSAGE="insert into snaparticle(snap_time,video_id,user_id) valus(?,?,?)";
   
    /*   收藏文字      */
    private static final String  UPDATECOLLECTARTICLE="insert into collectarticle(collectArticle_time,article_id,user_id) values(?,?,?)";
    
    /*   搜寻用户自己所收藏的文字(根据user_id)   */
    private static final String FINDCOLLECTARTICLE="select a.article_id , a.article_publishTime ,a.article_is_verify_code,a.article_title ,a.article_info ,a.article_seeCount ,a.article_snapCount ,a.article_reportCount ,a.article_commentCount ,a.article_address ,a.contentType_id ,a.user_id   from article a ,collectarticle b where a.article_id=b.article_id and b.user_id=?";
    
    /*   文字观看数量   */
    private static final String  UPDATESEE="update article set article_seeCount=? where article_id=?";
    
    /*   文字举报分数   */
    private static final String  UPDATEREPORT="UPDATE article SET article_reportCount=article_reportCount+(SELECT  article_code from reporttype where  reportType_id=(select reportType_id from reportarticle where  article_id=?))where article_id=?";
    
    /*   保存举报记录    */
    private static final String UPDATEREPORTARTICLE="inset into reportarticle(reportType_id,article_id,user_id) values(?,?,?)";
    
    /*   举报分数到达规定更改文字状态  */
    private static final String UPDATEARTICLESTATUS="update article set article_is_verify_code=? where article_id=?";
    
	/*   删除自己所上传的文字      */
	private static final String DELETE ="delete  from article  where article_id=?  ";	
	
	/*插入数据*/
	public void save(Article article) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS); // Statement.return_generated_keys 为获取刚刚插入语句的id值
			prep.setString(1, article.getArticle_publishTime() );  
			prep.setString(2, article.getArticle_is_verify_code());  
			prep.setString(3,article.getArticle_title());
			prep.setString(4,article.getArticle_info());
			prep.setInt(5,article.getArticle_seeCount()); //发布时有值 
			prep.setInt(6,article.getArticle_snapCount());//发布时有值 
			prep.setInt(7,article.getArticle_reportCount());//发布时有值 
			prep.setInt(8,article.getArticle_commentCount());//发布时有值 
			prep.setString(9,article.getArticle_address());
			prep.setInt(10,article.getContentType_id());
			prep.setInt(11,article.getUser_id());
			prep.executeUpdate();
			ResultSet rst = prep.getGeneratedKeys();
			rst.next();
			int id = rst.getInt(1);
			article.setArticle_id(id);
		} catch(Exception e){
			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	
	/*通过article_id 来查找相关的文字*/
	public Article  findArticleById(int article_id)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		Article a=new Article();
		try {
			System.out.println("调用FINDARTICLEBYID方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDARTICLEBYID);
			prep.setInt(1, article_id);
			rst = prep.executeQuery();
			if (rst.next()) {
			  a.setArticle_id(rst.getInt("article_id"));
			  a.setArticle_publishTime(rst.getString("article_publishTime"));
			  a.setArticle_is_verify_code(rst.getString("article_is_verify_code"));
			  a.setArticle_title(rst.getString("article_title"));
			  a.setArticle_info(rst.getString("article_info"));
			  a.setArticle_seeCount(rst.getInt("article_seeCount"));
			  a.setArticle_snapCount(rst.getInt("article_snapCount"));
			  a.setArticle_reportCount(rst.getInt("article_reportCount"));
			  a.setArticle_commentCount(rst.getInt("article_commentCount"));
			  a.setArticle_address(rst.getString("article_address"));
			  a.setContentType_id(rst.getInt("contentType_id"));
			  a.setUser_id(rst.getInt("user_id"));
		
		}}catch(Exception e){
			System.out.println("调用FINDARTICLEBYID方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
		return  a;
	}
	
	
	  /*更改article数据*/
	public void update(Article  article) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATE);
			prep.setString(1, article.getArticle_title());
		    prep.setString(2, article.getArticle_info());
		    prep.setInt(3, article.getArticle_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATE方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
	}
	/*保存点赞*/
	public void  snapCount(Article  article)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAP);
			prep.setInt(1, article.getArticle_snapCount());
		    prep.setInt(2, article.getArticle_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATESNAP方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	/*保存点赞记录*/
	public void saveSnap(String time,int article_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAPMESSAGE);
			prep.setString(1, time);  
			prep.setInt(2, article_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*收藏文字*/
	public void saveCollectArticle(String time,int article_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATECOLLECTARTICLE);
			prep.setString(1, time);  
			prep.setInt(2, article_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATECOLLECTARTICLE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*观看次数*/
	public void  seeCount(Article article)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESEE);
			prep.setInt(1, article.getArticle_seeCount());
		    prep.setInt(2, article.getArticle_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATESEE方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	/*文字举报分数 */
	public void  reportCount(Article article)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEREPORT);
			prep.setInt(1, article.getArticle_id());
		    prep.setInt(2, article.getArticle_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATEREPORT方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	/*保存举报记录*/
	public void saveReport(int reportType_id,int article_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEREPORTARTICLE);
			prep.setInt(1, reportType_id);  
			prep.setInt(2, article_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATEREPORTARTICLE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*   更改文字状态      */
	public void  articleStatus(String  article_is_verify_code ,int article_id )throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATEARTICLESTATUS方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEARTICLESTATUS);
		    prep.setString(1, article_is_verify_code);
		    prep.setInt(2, article_id);
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATEARTICLESTATUS方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	
	
	/*查询所有通过检查的文字*/
	public List<Article> find() throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Article>   VideoList = new ArrayList<Article>();
		Article a=new Article();
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FIND);
			rst = prep.executeQuery();
			if (rst.next()) {
				
			  a.setArticle_id(rst.getInt("article_id"));
			  a.setArticle_publishTime(rst.getString("article_publishTime"));
			  a.setArticle_is_verify_code(rst.getString("article_is_verify_code"));
			  a.setArticle_title(rst.getString("article_title"));
			  a.setArticle_info(rst.getString("article_info"));
			  a.setArticle_seeCount(rst.getInt("article_seeCount"));
			  a.setArticle_snapCount(rst.getInt("article_snapCount"));
			  a.setArticle_reportCount(rst.getInt("article_reportCount"));
			  a.setArticle_commentCount(rst.getInt("article_commentCount"));
			  a.setArticle_address(rst.getString("article_address"));
			  a.setContentType_id(rst.getInt("contentType_id"));
			  a.setUser_id(rst.getInt("user_id"));
			VideoList.add(rst.getInt("article_id"),a);
		
		}}catch(Exception e){
			System.out.println("调用FIND方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return VideoList;
	}
	/*查询用户自己收藏的视频*/
	public List<Article> findCollectArticle(User user) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Article>   VideoList = new ArrayList<Article>();
		Article a=new Article();
		try {
			System.out.println("调用FINDCOLLECTARTICLE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDCOLLECTARTICLE);
			prep.setInt(1,user.getUser_id());
			rst = prep.executeQuery();
			if (rst.next()) {
			  a.setArticle_id(rst.getInt("article_id"));
			  a.setArticle_publishTime(rst.getString("article_publishTime"));
			  a.setArticle_is_verify_code(rst.getString("article_is_verify_code"));
			  a.setArticle_title(rst.getString("article_title"));
			  a.setArticle_info(rst.getString("article_info"));
			  a.setArticle_seeCount(rst.getInt("article_seeCount"));
			  a.setArticle_snapCount(rst.getInt("article_snapCount"));
			  a.setArticle_reportCount(rst.getInt("article_reportCount"));
			  a.setArticle_commentCount(rst.getInt("article_commentCount"));
			  a.setArticle_address(rst.getString("article_address"));
			  a.setContentType_id(rst.getInt("contentType_id"));
			  a.setUser_id(rst.getInt("user_id"));
			VideoList.add(rst.getInt("article_id"),a);
		
		}}catch(Exception e){
			System.out.println("调用FINDCOLLECTARTICLE方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return VideoList;
	}
	
	/*删除文字*/
	public void delete(Article article) throws Exception {  
		
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用DELETE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(DELETE);
			prep.setInt(1, article.getArticle_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用DELETE方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
	}
	
	}


	
	
	
}
