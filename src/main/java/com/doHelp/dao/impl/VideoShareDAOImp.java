package com.doHelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.doHelp.dao.VideoShareDAO;
import com.doHelp.model.User;
import com.doHelp.model.Video;
import com.doHelp.util.DBConnection;

public class VideoShareDAOImp  implements  VideoShareDAO{
   /*  12个字段   */
	/* 插入数据  */
    private static final String INSERT = "insert into video(video_publishTime,video_is_verify_code,video_title,video_info,video_seeCount,video_snapCount,video_money,video_reportCount,video_commentCount,video_address,contentType_id,user_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
    /*  返回不为空的video_is_verify_code 值为T的视频 */
    private static final String FIND = "select * from video where video_is_verify like 'F' ";

    /*   通过video_id 来查询相关视频   */
    private static final String FINDVIDEOBYID="select * form video where video_id=?";
    
    /*     通过video_id来查找用户user_id     
    private static final String FINDUSERID ="select user_id where video_id=?";
    */
    /*  通过video_id update用户上传的视频,即数据库video表中信息 */
    private static final String UPDATE = "update video set video_title=? ,video_info=? where video_id=?";
	
	/*   视频作品点赞     */
    private static final String UPDATESNAP="update video set video_snapCount=? where video_id=?";
    
    /*   保存点赞记录    */
    private static final String UPDATESNAPMESSAGE="insert into snapvideo(snap_time,video_id,user_id) valus(?,?,?)";
    
    /*   收藏视频      */
    private static final String UPDATECOLLECTVIDEO="insert into collectvideo(collectVideo_time,video_id,user_id) values(?,?,?)";
    
    /*   搜寻用户自己所收藏的视频(根据user_id)   */
    private static final String FINDCOLLECTVIDEO="select a.video_id , a.video_publishTime ,a.video_is_verify_code,a.video_title ,a.video_info ,a.video_seeCount ,a.video_snapCount ,a.video_money ,a.video_reportCount ,a.video_commentCount ,a.video_address ,a.contentType_id ,a.user_id   from video a ,collectvideo b where a.video_id=b.video_id and b.user_id=?";
    
    /*   视频观看数量   */
    private static final String UPDATESEE="update video  set video_seeCount=? where video_id=?";
    
    /*   视频举报分数   */
    private static final String UPDATEREPORT="update video SET video_reportCount=video_reportCount+(SELECT  report_codefrom reporttype where  reportType_id=(select reportType_id from reportvideo  where  video_id=?)) where video_id=?";
    	
    /*   保存举报记录    */
    private static final String UPDATEREPORTVIDEO="inset into reportvideo(reportType_id,video_id,user_id) values(?,?,?)";
    
    /*   举报分数到达规定更改视频状态  */
    private static final String UPDATEVIDEOSTATUS="update video set video_is_verify_code=? where video_id=?";
    
	/*   删除自己所上传的视频      */
	private static final String DELETE ="delete  from video  where video_id=?  ";	
	
	
	/*插入数据*/
	public void save(Video video) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS); // Statement.return_generated_keys 为获取刚刚插入语句的id值
			prep.setString(1, video.getVideo_publishTime() );  
			prep.setString(2, video.getVideo_is_verify_code());  
			prep.setString(3,video.getVideo_title());
			prep.setString(4,video.getVideo_info());
			prep.setInt(5,video.getVideo_seeCount()); //发布时有值 
			prep.setInt(6,video.getVideo_snapCount());//发布时有值 
			prep.setInt(7,video.getVideo_money());//发布时有值 
			prep.setInt(8,video.getVideo_reportCount());//发布时有值 
			prep.setInt(9,video.getVideo_commentCount());//发布时有值 
			prep.setString(10,video.getVideo_address());
			prep.setInt(11,video.getContentType_id());
			prep.setInt(11,video.getUser_id());
			prep.executeUpdate();
			ResultSet rst = prep.getGeneratedKeys();
			rst.next();
			int id = rst.getInt(1);
			video.setVideo_id(id);
		} catch(Exception e){
			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*通过video_id查询相关的视频*/
	public Video findVideoById(int  video_id) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		Video v=new Video();
		try {
			System.out.println("调用FINDVIDEOBYID方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDVIDEOBYID);
			prep.setInt(1, video_id);
			rst = prep.executeQuery();
			if (rst.next()) {
			  v.setVideo_id(rst.getInt("video_id"));
			  v.setVideo_publishTime(rst.getString("video_publishTime"));
			  v.setVideo_is_verify_code(rst.getString("video_is_verify_code"));
			  v.setVideo_title(rst.getString("video_title"));
			  v.setVideo_info(rst.getString("video_info"));
			  v.setVideo_seeCount(rst.getInt("video_seeCount"));
			  v.setVideo_snapCount(rst.getInt("video_snapCount"));
			  v.setVideo_money(rst.getInt("video_money"));
			  v.setVideo_reportCount(rst.getInt("video_reportCount"));
			  v.setVideo_commentCount(rst.getInt("video_commentCount"));
			  v.setVideo_address(rst.getString("video_address"));
			  v.setContentType_id(rst.getInt("contentType_id"));
			  v.setUser_id(rst.getInt("user_id"));
		
		}}catch(Exception e){
			System.out.println("调用FINDVIDEOBYID方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return v;
	}
	
    /*更改video数据*/
	public void update(Video  video) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATE);
			prep.setString(1, video.getVideo_title());
		    prep.setString(2, video.getVideo_info());
		    prep.setInt(3, video.getVideo_id());
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
	public void  snapCount(Video video)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAP);
			prep.setInt(1, video.getVideo_snapCount());
		    prep.setInt(2, video.getVideo_id());
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
	public void saveSnap(String time,int video_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAPMESSAGE);
			prep.setString(1, time);  
			prep.setInt(2, video_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATESNAPMESSAGE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*收藏视频*/
	public void saveCollectVideo(String time,int video_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATECOLLECTVIDEO);
			prep.setString(1, time);  
			prep.setInt(2, video_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATECOLLECTVIDEO方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*观看次数*/
	public void  seeCount(Video video)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESEE);
			prep.setInt(1, video.getVideo_seeCount());
		    prep.setInt(2, video.getVideo_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATESEE方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	/*视频举报分数 */
	public void  reportCount(Video video )throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATEREPORT方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEREPORT);
		    prep.setInt(1, video.getVideo_id());
		    prep.setInt(2, video.getVideo_id());
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
	public void saveReport(int reportType_id,int video_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEREPORTVIDEO);
			prep.setInt(1, reportType_id);  
			prep.setInt(2, video_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATEREPORTVIDEO方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*   更改视频状态      */
	public void  videoStatus(String  video_is_verify_code ,int video_id )throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATEVIDEOSTATUS方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEVIDEOSTATUS);
		    prep.setString(1, video_is_verify_code);
		    prep.setInt(2, video_id);
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATEVIDEOSTATUS方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	/*查询所有通过检查的视频*/
	public List<Video> find() throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Video>   VideoList = new ArrayList<Video>();
		Video v=new Video();
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FIND);
			rst = prep.executeQuery();
			if (rst.next()) {
				
			  v.setVideo_id(rst.getInt("video_id"));
			  v.setVideo_publishTime(rst.getString("video_publishTime"));
			  v.setVideo_is_verify_code(rst.getString("video_is_verify_code"));
			  v.setVideo_title(rst.getString("video_title"));
			  v.setVideo_info(rst.getString("video_info"));
			  v.setVideo_seeCount(rst.getInt("video_seeCount"));
			  v.setVideo_snapCount(rst.getInt("video_snapCount"));
			  v.setVideo_money(rst.getInt("video_money"));
			  v.setVideo_reportCount(rst.getInt("video_reportCount"));
			  v.setVideo_commentCount(rst.getInt("video_commentCount"));
			  v.setVideo_address(rst.getString("video_address"));
			  v.setContentType_id(rst.getInt("contentType_id"));
			  v.setUser_id(rst.getInt("user_id"));
			VideoList.add(rst.getInt("video_id"),v);
		
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
	public List<Video> findCollectVideo(User u) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Video>   VideoList = new ArrayList<Video>();
		Video v=new Video();
		try {
			System.out.println("调用FINDCOLLECTVIDEO方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDCOLLECTVIDEO);
			prep.setInt(1, u.getUser_id());
			rst = prep.executeQuery();
			if (rst.next()) {
			  v.setVideo_id(rst.getInt("video_id"));
			  v.setVideo_publishTime(rst.getString("video_publishTime"));
			  v.setVideo_is_verify_code(rst.getString("video_is_verify_code"));
			  v.setVideo_title(rst.getString("video_title"));
			  v.setVideo_info(rst.getString("video_info"));
			  v.setVideo_seeCount(rst.getInt("video_seeCount"));
			  v.setVideo_snapCount(rst.getInt("video_snapCount"));
			  v.setVideo_money(rst.getInt("video_money"));
			  v.setVideo_reportCount(rst.getInt("video_reportCount"));
			  v.setVideo_commentCount(rst.getInt("video_commentCount"));
			  v.setVideo_address(rst.getString("video_address"));
			  v.setContentType_id(rst.getInt("contentType_id"));
			  v.setUser_id(rst.getInt("user_id"));
			VideoList.add(rst.getInt("video_id"),v);
		
		}}catch(Exception e){
			System.out.println("调用FINDCOLLECTVIDEO方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return VideoList;
	}
//	public  int  findUserId(Video video) throws Exception {  
//		PreparedStatement prep = null;
//		Connection conn = null;
//		ResultSet rst = null;
//		int user_id = 0;
//		try {
//			System.out.println("调用UPDATE方法成功！！！");
//			conn = DBConnection.getConnection();
//			prep = conn.prepareStatement(FINDUSERID);
//			prep.setInt(1, video.getVideo_id());
//			rst = prep.executeQuery();
//			if (rst.next()) {
//			  user_id=(rst.getInt("user_id"));
//		}}catch(Exception e){
//			System.out.println("调用FIND方法失败！！！");
//			e.getMessage();
//			
//		}
//		finally {
//			DBConnection.close(prep, conn);
//			
//		}
//		return user_id;
//	}
	/*删除视频*/
	public void delete(Video  video) throws Exception {  
		
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用DELETE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(DELETE);
			prep.setInt(1, video.getVideo_id());
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
