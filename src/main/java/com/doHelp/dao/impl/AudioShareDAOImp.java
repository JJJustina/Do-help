package com.doHelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.doHelp.dao.AudioShareDAO;
import com.doHelp.model.Article;
import com.doHelp.model.Audio;
import com.doHelp.model.User;
import com.doHelp.util.DBConnection;

public  class AudioShareDAOImp  implements AudioShareDAO{
	  /*  12个字段   */
	/* 插入数据  */
    private static final String INSERT = "insert into audio(audio_publishTime,audio_is_verify_code,audio_title,audio_info,audio_seeCount,audio_snapCount,audio_money,audio_reportCount,audio_commentCount,audio_address,contentType_id,user_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
    /*  返回不为空的audio_is_verify_code 值为T的视频 */
    private static final String FIND = "select * from audio where audio_is_verify like 'F' ";
   
    /*   通过audio_id 来查询相关音频   */
    private static final String FINDAUDIOBYID="select * form audio where audio_id=?";
    
    
    /*  通过audio_id update用户上传的音频,即数据库audio表中信息 */
    private static final String UPDATE = "update audio set audio_title=? ,audio_info=? where audio_id=?";
	
	/*   音频作品点赞     */
    private static final String  UPDATESNAP="update audio set audio_snapCount=? where audio_id=?";
    
    /*   保存点赞记录    */
    private static final String  UPDATESNAPMESSAGE="insert into snapaudio(snap_time,video_id,user_id) valus(?,?,?)";
   
    /*   收藏音频     */
    private static final String  UPDATECOLLECTAUDIO="insert into collectaudio(collectAudio_time,audio_id,user_id) values(?,?,?)";
    
    /*   搜寻用户自己所收藏的音频(根据user_id)   */
    private static final String FINDCOLLECTAUDIO="select a.audio_id , a.audio_publishTime ,a.audio_is_verify_code,a.audio_title ,a.audio_info ,a.audio_seeCount ,a.audio_snapCount ,a.audio_reportCount ,a.audio_commentCount ,a.audio_address ,a.contentType_id ,a.user_id   from audio a ,collectaudio b where a.audio_id=b.audio_id and b.user_id=?";
    
    /*   音频观看数量   */
    private static final String  UPDATESEE="update audio set video_seeCount=? where audio_id=?";
    
    /*   音频举报分数   */
    private static final String  UPDATEREPORT="UPDATE audio SET audio_reportCount=audio_reportCount+(SELECT  audio_code from reporttype where  reportType_id=(select reportType_id from reportaudio where  audio_id=?))where audio_id=?";
    
    /*   举报分数到达规定更改文字状态  */
    private static final String UPDATEAUDIOSTATUS="update audio set audio_is_verify_code=? where audio_id=?";
    
    /*   保存举报记录    */
    private static final String UPDATEREPORTAUDIO="inset into reportaudio(reportType_id,audio_id,user_id) values(?,?,?)";
    
	/*   删除自己所上传的音频      */
	private static final String DELETE ="delete  from audio  where audio_id=?  ";	
	
	
	/*插入数据*/
	public void save(Audio audio) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS); // Statement.return_generated_keys 为获取刚刚插入语句的id值
			prep.setString(1, audio.getAudio_publishTime() );  
			prep.setString(2, audio.getAudio_is_verify_code());  
			prep.setString(3,audio.getAudio_title());
			prep.setString(4,audio.getAudio_info());
			prep.setInt(5,audio.getAudio_seeCount()); //发布时有值 
			prep.setInt(6,audio.getAudio_snapCount());//发布时有值 
			prep.setInt(7,audio.getAudio_money());//发布时有值 
			prep.setInt(8,audio.getAudio_reportCount());//发布时有值 
			prep.setInt(9,audio.getAudio_commentCount());//发布时有值 
			prep.setString(10,audio.getAudio_address());
			prep.setInt(11,audio.getContentType_id());
			prep.setInt(11,audio.getUser_id());
			prep.executeUpdate();
			ResultSet rst = prep.getGeneratedKeys();
			rst.next();
			int id = rst.getInt(1);
			audio.setAudio_id(id);
		} catch(Exception e){
			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*通过audio_id 来查找相关的音频*/
	public   Audio findAudioById(int audio_id)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		Audio a=new Audio();
		try {
			System.out.println("调用FINDAUDIOBYID方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDAUDIOBYID);
			prep.setInt(1, audio_id);
			rst = prep.executeQuery();
			if (rst.next()) {
			  a.setAudio_id(rst.getInt("audio_id"));
			  a.setAudio_publishTime(rst.getString("audio_publishTime"));
			  a.setAudio_is_verify_code(rst.getString("audio_is_verify_code"));
			  a.setAudio_title(rst.getString("audio_title"));
			  a.setAudio_info(rst.getString("audio_info"));
			  a.setAudio_seeCount(rst.getInt("audio_seeCount"));
			  a.setAudio_snapCount(rst.getInt("audio_snapCount"));
			  a.setAudio_money(rst.getInt("audio_money"));
			  a.setAudio_reportCount(rst.getInt("audio_reportCount"));
			  a.setAudio_commentCount(rst.getInt("audio_commentCount"));
			  a.setAudio_address(rst.getString("audio_address"));
			  a.setContentType_id(rst.getInt("contentType_id"));
			  a.setUser_id(rst.getInt("user_id"));
		
		}}catch(Exception e){
			System.out.println("调用FINDAUDIOBYID方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
		return  a;
	}
	
	
	
	
	  /*更改audio数据*/
	public void update(Audio  audio) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATE);
			prep.setString(1, audio.getAudio_title());
		    prep.setString(2, audio.getAudio_info());
		    prep.setInt(3, audio.getAudio_id());
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
	public void  snapCount(Audio audio)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAP);
			prep.setInt(1, audio.getAudio_snapCount());
		    prep.setInt(2, audio.getAudio_id());
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
	public void saveSnap(String time,int audio_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAPMESSAGE); 
			prep.setString(1, time);  
			prep.setInt(2, audio_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*收藏音频*/
	public void saveCollectAudio(String time,int audio_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATECOLLECTAUDIO);
			prep.setString(1, time);  
			prep.setInt(2, audio_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATECOLLECTAUDIO方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*观看次数*/
	public void  seeCount(Audio audio)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESEE);
			prep.setInt(1, audio.getAudio_seeCount());
		    prep.setInt(2, audio.getAudio_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATESEE方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	/* 音频举报分数 */
	public void  reportCount(Audio audio)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEREPORT);
			prep.setInt(1, audio.getAudio_id());
		    prep.setInt(2, audio.getAudio_id());
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
	public void saveReport(int reportType_id,int audio_id,int user_id) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEREPORTAUDIO);
			prep.setInt(1, reportType_id);  
			prep.setInt(2, audio_id);  
			prep.setInt(3,user_id);
			prep.executeUpdate();
		} catch(Exception e){
			System.out.println("调用UPDATEREPORTAUDIO方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		
		
	}
	/*   更改视频状态      */
	public void  audioStatus(String  audio_is_verify_code ,int audio_id )throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATEAUDIOSTATUS方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEAUDIOSTATUS);
		    prep.setString(1, audio_is_verify_code);
		    prep.setInt(2, audio_id);
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATEAUDIOSTATUS方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	
	
	/*查询所有通过检查的音频*/
	public List<Audio> find() throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Audio>   VideoList = new ArrayList<Audio>();
		Audio a=new Audio();
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FIND);
			rst = prep.executeQuery();
			if (rst.next()) {
			  a.setAudio_id(rst.getInt("audio_id"));
			  a.setAudio_publishTime(rst.getString("audio_publishTime"));
			  a.setAudio_is_verify_code(rst.getString("audio_is_verify_code"));
			  a.setAudio_title(rst.getString("audio_title"));
			  a.setAudio_info(rst.getString("audio_info"));
			  a.setAudio_seeCount(rst.getInt("audio_seeCount"));
			  a.setAudio_snapCount(rst.getInt("audio_snapCount"));
			  a.setAudio_money(rst.getInt("audio_money"));
			  a.setAudio_reportCount(rst.getInt("audio_reportCount"));
			  a.setAudio_commentCount(rst.getInt("audio_commentCount"));
			  a.setAudio_address(rst.getString("audio_address"));
			  a.setContentType_id(rst.getInt("contentType_id"));
			  a.setUser_id(rst.getInt("user_id"));
			VideoList.add(rst.getInt("audio_id"),a);
		
		}}catch(Exception e){
			System.out.println("调用FIND方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return VideoList;
	}
	/*查询用户自己收藏的音频*/
	public List<Audio> findCollectAudio(User user) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Audio>   VideoList = new ArrayList<Audio>();
		Audio a=new Audio();
		try {
			System.out.println("调用FINDCOLLECTAUDIO方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDCOLLECTAUDIO);
			prep.setInt(1,user.getUser_id());
			rst = prep.executeQuery();
			if (rst.next()) {
			  a.setAudio_id(rst.getInt("audio_id"));
			  a.setAudio_publishTime(rst.getString("audio_publishTime"));
			  a.setAudio_is_verify_code(rst.getString("audio_is_verify_code"));
			  a.setAudio_title(rst.getString("audio_title"));
			  a.setAudio_info(rst.getString("audio_info"));
			  a.setAudio_seeCount(rst.getInt("audio_seeCount"));
			  a.setAudio_snapCount(rst.getInt("audio_snapCount"));
			  a.setAudio_money(rst.getInt("audio_money"));
			  a.setAudio_reportCount(rst.getInt("audio_reportCount"));
			  a.setAudio_commentCount(rst.getInt("audio_commentCount"));
			  a.setAudio_address(rst.getString("audio_address"));
			  a.setContentType_id(rst.getInt("contentType_id"));
			  a.setUser_id(rst.getInt("user_id"));
			VideoList.add(rst.getInt("audio_id"),a);
		
		}}catch(Exception e){
			System.out.println("调用FINDCOLLECTAUDIO方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return VideoList;
	}
	
	/*删除音频*/
	public void delete(Audio  audio) throws Exception {  
		
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用DELETE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(DELETE);
			prep.setInt(1, audio.getAudio_id());
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
