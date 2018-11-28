package com.doHelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.doHelp.dao.RemarksDAO;
import com.doHelp.model.Audio;
import com.doHelp.model.Remarks;
import com.doHelp.util.DBConnection;

public class RemarksDAOImp implements RemarksDAO{
	
	/* 插入数据  */
    private static final String INSERT = "insert into remarks(remarks_info,remarks_snapCount,user_id) values(?,?,?)";
	
    /*  返回不为空的remarks */
    private static final String FIND = "select * from remarks where remarks_info!=null";
   
    /*  通过user_id更新用户感言,即数据库remarks表中信息 */
    private static final String UPDATE = "update remarks set remarks_info=?  where user_id=?";
	
	/*   感悟点赞     */
    private static final String  UPDATESNAP="update remarks  set remarks_snapCount=? where remarks_id=?";
    
  
	/* 该为插入数据,也就是注册时插入的数据       */
	public void save(Remarks remarks) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS); // Statement.return_generated_keys 为获取刚刚插入语句的id值
			prep.setString(1, remarks.getRemarks_info());  
			prep.setInt(2, remarks.getSnapCount()); //注册时有值  
			prep.setInt(3,remarks.getUser_id());
			prep.executeUpdate();
			ResultSet rst = prep.getGeneratedKeys();
			rst.next();
			int id = rst.getInt(1);
			remarks.setRemarks_id(id);
		} catch(Exception e){
			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
	}
            /*  修改用户信息   */
	public void update(Remarks remarks) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATE);
			prep.setString(1, remarks.getRemarks_info());
		    prep.setInt(2, remarks.getRemarks_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATE方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
	}
	 /* 感悟点赞  */
	public void  snapCount(Remarks  remarks)throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用UPDATESNAP方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESNAP);
			prep.setInt(1, remarks.getSnapCount());
		    prep.setInt(2, remarks.getRemarks_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATESNAP方法失败！！！");
			e.getMessage();
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	 /* 搜索所有感悟   */
	public List<Remarks> find() throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		List<Remarks> RemarksList = new ArrayList<Remarks>();
		Remarks r=new Remarks();
		try {
			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FIND);
			rst = prep.executeQuery();
			if (rst.next()) {
				r= new Remarks();
			  r.setRemarks_id(rst.getInt("remarks_id"));
			  r.setRemarks_info(rst.getString("remarks_info"));
			  r.setSnapCount(rst.getInt("remarks_snapCount"));
			  r.setUser_id(rst.getInt("user_id"));
			RemarksList.add(rst.getInt("remarks_id"),r);
		
		}}catch(Exception e){
			System.out.println("调用FIND方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
		return RemarksList;
	}
}
