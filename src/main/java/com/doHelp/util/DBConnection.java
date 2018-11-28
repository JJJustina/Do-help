package com.doHelp.util;

import java.io.IOException;  
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private static String user;
	private static String password;
	private static String url;
	static {
		
		try {
			ClassLoader classloader=DBConnection.class.getClassLoader();
			InputStream is=classloader.getResourceAsStream("config/props/db.properties");
			Properties props=new Properties();
			props.load(is);
			/*
			 * Properties props=new Properties();
			 * props.load(PropertiesTest.class.getClassLoader().getResourceAsStream(db.properties));
			 * 
               props.setProperty("user", "ttt")  设置值
               System.out.println(props.getProperty("user"));  获取值
			 * 
			 * 
			 */
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			Class.forName(props.getProperty("driver"));
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public static Connection getConnection() throws Exception{
			return DriverManager.getConnection(url, user, password);
				
		}
		public static void close(ResultSet rs,Statement stat, Connection conn) 
		 		throws Exception{
			if(rs!=null){
				rs.close();
			}
			if(stat!=null){
				stat.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
		public static void close(Statement stat,Connection conn) throws Exception{
			if(stat!=null){
				stat.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
}
