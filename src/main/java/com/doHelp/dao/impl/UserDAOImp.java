package com.doHelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.doHelp.model.User;
import com.doHelp.dao.UserDAO;
import com.doHelp.util.DBConnection;

public class UserDAOImp implements UserDAO{
	/*   user表  24个字段  	*/
	/* 插入数据  */
    private static final String INSERT = "insert into user(user_phone,user_passWord,user_sex,user_photoAdress,user_birthdayPlace,user_accountStatus,user_accountCount,user_violationCount,user_forbiddenTime,user_email,user_realName,user_collegeSchoolAt,user_collegeSpeciality,user_postGraduateSchoolAt,user_doctorialSchoolAt,user_graduateSchoolAt,user_is_verify_code,user_createAt,user_usingAt,user_hotCount,user_messageStatus,user_fans,user_ip) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
    /* 通过user_phone返回用户信息  */
    private static final String FINDBYPHONE ="select * from user where user_phone=?";
	
    /*  通过user_id返回user */
    private static final String FINDBYID = "select * from user where user_id=?";
   
    /*  通过user_id更新用户信息,即数据库user表中信息 */
    private static final String UPDATE = "update user  set user_sex=?, user_PhotoAdress=?,user_birthdayPlace=?,user_email=?,user_realName=?,user_collegeSchoolAt=?,user_collegeSpeciality=?,user_postGraduateSchoolAt=?,user_doctorialSchoolAt=?,user_graduateSchoolAt=?  where user_phone=?";
    
    /*   通过user_id 来更改用户信息状态        */
    private static final String UPDATESTATUS="update user set  user_messageStatus=?  where=user_id=?";
    
    /*   通过user_phone找回密码       */
    private static final String FINDPWBYPHONE="select user_password from user where user_phone=?";
   
    /* 通过user_email找回密码*/
    private static final String FINDPWBYEMAIL="select user_password where user_email=?";
    
    /*  更新用户登入时间  */
    private static final String UPDATETIME="update user set user_usingAt=?,user_ip=?   where user_phone=? ";
    
    /*  更改密码  */
    private static final String UPDATEPASSWORD="update user set user_password=? where  user_phone=?";
   
    /*  通过user_phone读取数据库中user表  */
    public User findByPhone(String user_phone) throws Exception { 
		PreparedStatement prep = null;
		Connection conn = null;
		User u = null;
		ResultSet rst = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDBYPHONE);
			prep.setString(1, user_phone);
			rst = prep.executeQuery();
			if (rst.next()) {
				u = new User();
				u.setUser_id(rst.getInt("user_id"));
				u.setUser_phone(rst.getString("user_phone"));
				u.setUser_password(rst.getString("user_passWord"));
				u.setUser_photoAdress(rst.getString("user_photoAdress"));
				u.setUser_sex(rst.getString("user_sex"));
				u.setUser_birthdayPlace(rst.getString("user_birthdayPlace"));
				u.setUser_accountStatus(rst.getString("user_accountStatus"));
				u.setUser_accountCount(rst.getInt("user_accountCount"));
				u.setUser_violationCount(rst.getInt("user_violationCount"));
				u.setUser_forbiddenTime(rst.getInt("user_forbiddenTime"));
				u.setUser_email(rst.getString("user_email"));
				u.setUser_realName(rst.getString("user_realName"));
				u.setUser_collegeSchoolAt(rst.getString("user_collegeSchoolAt"));
				u.setUser_collegeSpeciality(rst.getString("user_collegeSpeciality"));
				u.setUser_postGraduateSchoolAt(rst.getString("user_postGraduateSchoolAt"));
				u.setUser_doctorialSchoolAt(rst.getString("user_doctorialSchoolAt"));
				u.setUser_graduateSchoolAt(rst.getString("user_graduateSchoolAt"));
				u.setUser_is_verify_code(rst.getString("user_is_verify_code"));
				u.setUser_createAt(rst.getString("user_createAt"));
				u.setUser_usingAt(rst.getString("user_usingAt"));
				u.setUser_hotCount(rst.getInt("user_hotCount"));
				u.setUser_ip(rst.getString("user_ip"));
			}
		}catch(Exception e){
			System.out.println("调用FINDEBYPHONE方法失败！！！");
			 e.getMessage();
			
		}  finally {
			DBConnection.close(prep, conn);
		}
		return u;
	}
           /*  通过user_id读取数据库中user表    */
	public User findByid(int user_id) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		User u = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDBYID);
			prep.setInt(1, user_id);
			ResultSet rst = prep.executeQuery();
			if (rst.next()) {
				u = new User();
				u.setUser_id(rst.getInt("user_id"));
				u.setUser_password(rst.getString("user_passWord"));
				u.setUser_sex(rst.getString("user_sex"));
				u.setUser_photoAdress(rst.getString("user_phoneAdress"));
				u.setUser_birthdayPlace(rst.getString("user_birthdayPlace"));
				u.setUser_accountStatus(rst.getString("user_accountStatus"));
				u.setUser_accountCount(rst.getInt("user_accountCount"));
				u.setUser_violationCount(rst.getInt("user_violationCount"));
				u.setUser_forbiddenTime(rst.getInt("user_forbiddenTime"));
				u.setUser_email(rst.getString("user_email"));
				u.setUser_realName(rst.getString("user_realName"));
				u.setUser_collegeSchoolAt(rst.getString("user_collegeSchoolAt"));
				u.setUser_collegeSpeciality(rst.getString("user_collegeSpeciality"));
				u.setUser_postGraduateSchoolAt(rst.getString("user_postGraduateSchoolAt"));
				u.setUser_doctorialSchoolAt(rst.getString("user_doctorialSchoolAt"));
				u.setUser_graduateSchoolAt(rst.getString("user_graduateSchoolAt"));
				u.setUser_is_verify_code(rst.getString("user_is_verify_code"));
				u.setUser_createAt(rst.getString("user_createAt"));
				u.setUser_usingAt(rst.getString("user_usingAt"));
				u.setUser_hotCount(rst.getInt("user_hotCount"));
				u.setUser_ip(rst.getString("user_ip"));			
			}
		}catch(Exception e){

			System.out.println("调用FINDEBYID方法失败！！！");
			 e.getMessage();
			
		}  finally {
			DBConnection.close(prep, conn);
		}
		return u;
	}

	/* 该为插入数据,也就是注册时插入的数据       */
	public void save(User user) throws Exception {   //保存信息
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS); // Statement.return_generated_keys 为获取刚刚插入语句的id值
			prep.setString(1, user.getUser_phone());  //注册时有值   
			prep.setString(2, user.getUser_password()); //注册时有值  
			prep.setString(3,user.getUser_sex());
			prep.setString(4, user.getUser_photoAdress());
			prep.setString(5, user.getUser_birthdayPlace());
			prep.setString(6, user.getUser_accountStatus());//注册时有值  为 优秀
			prep.setInt(7, user.getUser_accountCount());//注册时有值   为100
			prep.setInt(8, user.getUser_violationCount());//注册时有值   为0
			prep.setInt(9, user.getUser_forbiddenTime());//注册时有值  为0
			prep.setString(10, user.getUser_email());
			prep.setString(11, user.getUser_realName());
			prep.setString(12, user.getUser_collegeSchoolAt());
			prep.setString(13, user.getUser_collegeSpeciality());
			prep.setString(14, user.getUser_postGraduateSchoolAt());
			prep.setString(15, user.getUser_doctorialSchoolAt());
			prep.setString(16, user.getUser_graduateSchoolAt());
			prep.setString(17, "T");
			prep.setString(18, user.getUser_createAt());//注册时有值  
			prep.setString(19, user.getUser_usingAt());     //登录时赋值  用隐藏的input
			prep.setInt(20,user.getUser_hotCount());//注册时有值  为0
			prep.setString(21, user.getUser_ip());  // 注册时在control里面注入
				
			prep.executeUpdate();
			ResultSet rst = prep.getGeneratedKeys();
			rst.next();
			int id = rst.getInt(1);
			user.setUser_id(id);
		} catch(Exception e){

			System.out.println("调用SAVE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
	}
	
	
	
            /*  修改用户信息   */
	public void update(User u) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		try {

			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATE);
			prep.setString(1, u.getUser_sex());
		    prep.setString(2, u.getUser_photoAdress());
			prep.setString(3, u.getUser_birthdayPlace());
			prep.setString(4, u.getUser_email());
			prep.setString(5, u.getUser_realName());
			prep.setString(6, u.getUser_collegeSchoolAt());
			prep.setString(7, u.getUser_collegeSpeciality());
			prep.setString(8, u.getUser_postGraduateSchoolAt());
			prep.setString(9, u.getUser_doctorialSchoolAt());
			prep.setString(11, u.getUser_phone());
			if(u.getUser_doctorialSchoolAt()!=null  && (u.getUser_doctorialSchoolAt().endsWith("大学")|| u.getUser_doctorialSchoolAt().endsWith("学院") ))
			{
				prep.setString(10, u.getUser_doctorialSchoolAt());
			}
			else
				if(u.getUser_postGraduateSchoolAt()!=null  && (u.getUser_postGraduateSchoolAt().endsWith("大学")|| u.getUser_postGraduateSchoolAt().endsWith("学院") ))
				{
					prep.setString(10, u.getUser_postGraduateSchoolAt());
				}
				else
					if(u.getUser_collegeSchoolAt()!=null && (u.getUser_collegeSchoolAt().endsWith("大学")|| u.getUser_collegeSchoolAt().endsWith("学院") ))
					{ 
						prep.setString(10,u.getUser_collegeSchoolAt());
					
					}
					else
					{
						prep.setString(10,  null);						
					}
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATE方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
	}
    /*  更改用户信息状态   */
	public void updateStatus(User u) throws Exception {  
		PreparedStatement prep = null;
		Connection conn = null;
		try {

			System.out.println("调用UPDATE方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATESTATUS);
			prep.setString(1, u.getUser_accountStatus());
		    prep.setInt(2, u.getUser_id());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用UPDATE方法失败！！！");
			e.getMessage();
			
		}
		finally {
			DBConnection.close(prep, conn);
			
		}
	}
	public void updateTime(User u) throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATETIME);
			prep.setString(1,u.getUser_usingAt());
			prep.setString(2, u.getUser_ip());
			prep.setString(3, u.getUser_phone());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用updateTime方法失败！！！");
			e.getMessage();
			
		}
		
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	
	public void updatePassWord(User u) throws Exception{
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			System.out.println("调用updatePassWord方法成功！！！");
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(UPDATEPASSWORD);
			prep.setString(1,u.getUser_password());
			prep.setString(2, u.getUser_phone());
			prep.executeUpdate();
		}catch(Exception e){
			System.out.println("调用updatePassWord方法失败！！！");
			e.getMessage();
			
		}
		
		finally {
			DBConnection.close(prep, conn);
			
		}
		
	}
	public String findPwByPhone(String user_phone) throws Exception {
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		String passWord = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDPWBYPHONE);
			prep.setString(1, user_phone);
			rst = prep.executeQuery();
			passWord=rst.getString("passWord");
		}catch(Exception e){

			System.out.println("调用FINDPWBYPHONE方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		return passWord;
	}
	public String findPwByEmail(String user_email) throws Exception {
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rst = null;
		String passWord = null;
		try {
			conn = DBConnection.getConnection();
			prep = conn.prepareStatement(FINDPWBYEMAIL);
			prep.setString(1, user_email);
			rst = prep.executeQuery();
			passWord=rst.getString("passWord");
		}catch(Exception e){

			System.out.println("调用FINDPWBYEMAIL方法失败！！！");
			 e.getMessage();
			
		} finally {
			DBConnection.close(prep, conn);
		}
		return passWord;
	}
}
