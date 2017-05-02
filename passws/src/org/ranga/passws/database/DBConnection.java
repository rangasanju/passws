package org.ranga.passws.database;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;



public class DBConnection {

	
	private static Connection  con=null;
	
	
	
	public static Connection getConnection()
	{
		
		try{  
			
			Class.forName("com.mysql.jdbc.Driver");  
			//con=DriverManager.getConnection("jdbc:mysql://10.60.201.159:3306/babio","replicator","111");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root","");  
			
			
			}catch(Exception e){ System.out.println(e);}  
		return con;
	}
	
	
	public static void closeCon()
	{
		try{
		con.close();
		}catch(Exception e){ System.out.println(e);}  
	}
	
	
}
