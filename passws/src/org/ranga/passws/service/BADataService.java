package org.ranga.passws.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.ranga.passws.database.DBConnection;
import org.ranga.passws.model.BAData;
import org.ranga.passws.model.BioData;
import org.ranga.passws.model.RequestData;
import org.ranga.passws.model.UserInfo;

import sun.misc.*;

public class BADataService {
	
	
	public List<RequestData> getAllMessages()
	{
		RequestData m1 = new RequestData("1" , "Y" , "W");
		RequestData m2 = new RequestData("2" , "N", "W");
		
		List<RequestData> list = new ArrayList<>();
		
		list.add(m1);
		list.add(m2);
		return list;
		
		
		
	}
	
	public List<RequestData> getBARequest(String deviceId)
	{
		RequestData m1 = null;
		
		try
		{
			Connection conn = DBConnection.getConnection();
			String query = "SELECT * FROM babio.ba_request_trn WHERE DEVICE_ID_I=" + deviceId;
			System.out.println("Query : " + query);
			// create the java statement
		      Statement st = conn.createStatement();
		      
		    // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      
		      System.out.println("ID : in");
		      if(rs.next())
		      {
		    	  System.out.println("ID : " + rs.getString("DEVICE_ID_I"));
		    	  System.out.println("BA_REQUEST_V : " + rs.getString("BA_REQUEST_V"));
		    	  m1 = new RequestData(rs.getString("DEVICE_ID_I") , rs.getString("BA_REQUEST_V"),"W");
		      }
		      System.out.println("ID : out");
		      
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		List<RequestData> list = new ArrayList<>();
		
		list.add(m1);
		
		return list;
		
		
		
	}
	
	
	public List<RequestData> setPerformBA(String deviceId)
	{
		RequestData m1 = null;
		
		try
		{
			Connection conn = DBConnection.getConnection();
			
			
			// create the java mysql update preparedstatement
		      String query = "update babio.ba_request_trn set BA_REQUEST_V = ? where DEVICE_ID_I = ?" ;
		      
		      System.out.println();
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1, "Y");
		      preparedStmt.setString(2, deviceId);

		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();
		      
		      conn.close();
			      
			      
		      
		     
		    	  m1 = new RequestData(deviceId , "Y","W");
		     
		     
		      
		}catch(Exception e)
		{
			
		}
		
		
		
		
		List<RequestData> list = new ArrayList<>();
		
		list.add(m1);
		
		return list;
		
		
		
	}
	
	
	
	public List<RequestData> getBooking()
	{
		RequestData m1 = null;
		
		try
		{
			Connection conn = DBConnection.getConnection();
			String query = "SELECT * FROM babio.ba_request_trn WHERE BOOKING_STATUS_V='W'";
			System.out.println(query);
			// create the java statement
		      Statement st = conn.createStatement();
		      
		    // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      
		      
		      if(rs.next())
		      {
		    	  m1 = new RequestData(rs.getString("DEVICE_ID_I") , rs.getString("BA_REQUEST_V"),"W");
		      }
		     
		      
		}catch(Exception e)
		{
			
		}
		
		
		
		
		List<RequestData> list = new ArrayList<>();
		
		list.add(m1);
		
		return list;
		
		
		
	}
	
	
	
	
	public List<BAData> getBAData(String deviceId)
	{
		BAData m1 = null;
		
		try
		{
			Connection conn = DBConnection.getConnection();
			String query = "SELECT DEVICE_ID_I, DEVICE_NAME_V,  DEVICE_SR_NO_V, DATE_FORMAT(CALIBRATION_DATE_D,'%d/%m/%Y') as CALIBRATION_DATE_D, RECORD_NO_I, DATE_FORMAT(BA_DATE_D,'%d/%m/%Y %H:%i:%S') as BA_DATE_D,RESULT_V , EXHALE_VOL_V, EXHALE_TIME_V, IMAGE_B  FROM babio.ba_data_trn WHERE DEVICE_ID_I=" + deviceId;
			System.out.println(query);
			// create the java statement
		      Statement st = conn.createStatement();
		      
		    // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      
		      
		      if(rs.next())
		      {
		    	  System.out.println("cHECK 1");
		    	  m1 = new BAData(rs.getString("DEVICE_ID_I"), rs.getString("DEVICE_NAME_V") , rs.getString("DEVICE_SR_NO_V"), rs.getString("CALIBRATION_DATE_D"),rs.getString("RECORD_NO_I"),rs.getString("BA_DATE_D"),rs.getString("RESULT_V"),rs.getString("EXHALE_VOL_V"), rs.getString("EXHALE_TIME_V"));
	 				InputStream in = rs.getBinaryStream("IMAGE_B");
					OutputStream f = new FileOutputStream(new File("/var/www/html/img/" + deviceId + ".jpg"));
					int c = 0;
					while ((c = in.read()) > -1) {
						f.write(c);
					}
					f.close();
					in.close();
					 System.out.println("cHECK 2");
					 
					 
					  query = "Insert into babio.ba_data_his (SELECT *,CURRENT_TIMESTAMP FROM babio.ba_data_trn WHERE DEVICE_ID_I=?)";
				      PreparedStatement preparedStmt1 = conn.prepareStatement(query);
				      preparedStmt1.setString (1, deviceId);
				      preparedStmt1.execute();
				      
				     
				      query = "DELETE FROM babio.ba_data_trn WHERE DEVICE_ID_I=?";
				      PreparedStatement preparedStmt2 = conn.prepareStatement(query);
				      preparedStmt2.setString (1, deviceId);
				      preparedStmt2.execute();
				      
				      
				      query = "update ba_request_trn set ba_request_v='N' WHERE DEVICE_ID_I=?";
				      PreparedStatement preparedStmt3 = conn.prepareStatement(query);
				      preparedStmt3.setString (1, deviceId);
				      preparedStmt3.execute();
				      
				      
		      }
		     
		      
		   
		      
		      
		      conn.close();
		      
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		List<BAData> list = new ArrayList<>();
		
		list.add(m1);
		
		return list;
		
		
		
	}
	
	
	
	

	
	
	public List<BAData> putBAData(String result)
	{
		BAData m1 = null;
		
		System.out.println(" putBAData ...   Check 1 :");
		
		try
		{
			String deviceid="1";
			String devicesrno = result.substring(result.indexOf("devicesr.no") + 13,result.indexOf("calibratedon"));
			String devicename = "KATS";
			String caldate = result.substring(result.indexOf("calibratedon") + 13,result.indexOf("recordno"));
			String recordno = result.substring(result.indexOf("recordno") + 10,result.indexOf("date"));
			String date = result.substring(result.indexOf("date") + 5,result.indexOf("time"));
			String time = result.substring(result.indexOf("time") + 5,result.indexOf("hrs"));
			String res = result.substring(result.indexOf("result") + 7,result.indexOf("mg/100ml"));
			String exhalevol = result.substring(result.indexOf("exhalevol") + 11,result.indexOf("exhaletime"));
			String exhaletime = result.substring(result.indexOf("exhaletime") + 11,result.indexOf("seconds"));
			
			String imgstr = result.substring(result.indexOf("64start") + 7,result.indexOf("64ends"));
			
			
			int recno = Integer.parseInt(recordno);
		
			
			
			byte[] imageByte;

			
			imageByte = Base64.decodeBase64(imgstr);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			
			
			
			
			Connection conn = DBConnection.getConnection();
			
			System.out.println(" putBAData ...   Check 2 :");
			 					 
			String query = "Insert into babio.ba_data_trn VALUES (?, ?, ?, STR_TO_DATE(?,'%d/%m/%Y'), ?, STR_TO_DATE(?,'%d/%m/%Y %H:%i:%S' ), ?, ?, ?, ?)";
				      PreparedStatement preparedStmt1 = conn.prepareStatement(query);
				      preparedStmt1.setString (1, deviceid);
				      preparedStmt1.setString (2, devicename);
				      preparedStmt1.setString(3, devicesrno);
				      preparedStmt1.setString(4, caldate);
				      preparedStmt1.setInt(5, recno);
				      preparedStmt1.setString(6, date);
				      preparedStmt1.setString(7, res);
				      preparedStmt1.setString(8, exhalevol);
				      preparedStmt1.setString(9, exhaletime);
				      preparedStmt1.setBinaryStream(10, bis);
				      
				      
				      preparedStmt1.execute();
				      
				      
				    
				      bis.close();
		      conn.close();
		      
		      System.out.println(" putBAData ...   Check 3 :");
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		List<BAData> list = new ArrayList<>();
		
		list.add(m1);
		
		return list;
		
		
		
	}
	
	
	
	
	
	// MEDICAL RELATED FUNCTIONS PASS
	
	

	
	public List<BioData> getBioData(String empid)
	{
		BioData m1 = null;
		
		try
		{
			Connection conn = DBConnection.getConnection();
			String query = "SELECT EMP_ID_V ,FIRST_NAME_V,LAST_NAME_V,FATHERS_NAME_V,MOTHERS_NAME_V,"
					+ "DOB_DATE,POB_V,MOBILE_NO_V,EMAIL_V,OCCUPATION_V,NATIONALITY_V,PRESENT_ADDRESS_V,"
					+ "PERMANENT_ADDRESS_V  FROM meddb.biodata WHERE EMP_ID_V='" + empid + "'";
			System.out.println(query);
			// create the java statement
		      Statement st = conn.createStatement();
		      
		    // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      
		      
		      if(rs.next())
		      {
		    	  System.out.println("cHECK 1");
		    	  m1 = new BioData(empid, rs.getString("FIRST_NAME_V"), rs.getString("LAST_NAME_V") , 
		    			  rs.getString("FATHERS_NAME_V"), rs.getString("MOTHERS_NAME_V"),
		    			  rs.getString("DOB_DATE"),rs.getString("POB_V"),rs.getString("MOBILE_NO_V"),
		    			  rs.getString("EMAIL_V"), rs.getString("OCCUPATION_V"),rs.getString("NATIONALITY_V"), 
		    			  rs.getString("PRESENT_ADDRESS_V"),rs.getString("PERMANENT_ADDRESS_V"));
		    	  
		    	  
//	 				InputStream in = rs.getBinaryStream("IMAGE_B");
//					OutputStream f = new FileOutputStream(new File("/var/www/html/img/" + deviceId + ".jpg"));
//					int c = 0;
//					while ((c = in.read()) > -1) {
//						f.write(c);
//					}
//					f.close();
//					in.close();
		    	  
					 System.out.println("cHECK 2");
		      }		     
		      conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		List<BioData> list = new ArrayList<>();		
		list.add(m1);		
		return list;
	}
	
	
	
	
	public List<UserInfo> getUserInfo(String uid)
	{
		UserInfo m1 = null;
		String query="";
		try
		{
			Connection conn = DBConnection.getConnection();
			
			if(uid.indexOf('@') > 0)
				query = "SELECT * FROM meddb.users WHERE EMAIL_ID_V='" + uid + "'";
			else
				query = "SELECT * FROM meddb.users WHERE USER_ID_V='" + uid + "'";
				
				
			System.out.println(query);
			// create the java statement
		      Statement st = conn.createStatement();
		      
		    // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      
		      
		      if(rs.next())
		      {
		    	  System.out.println("cHECK 1");
		    	  m1 = new UserInfo(rs.getString("USER_ID_V"), rs.getString("PASSWORD_V") , 
		    			  rs.getString("ROLE_V"), rs.getString("EMAIL_ID_V"));
		    	  
					 System.out.println("cHECK 2");
		      }		     
		      conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		List<UserInfo> list = new ArrayList<>();		
		list.add(m1);		
		return list;
	}
	
	
	
	
	

	
	public List<BAData> savePersonalBiodata(String empid, String firstname,String lastname,String fathersname,String mothersname, String dob, String pob, String nationality)
	{
		BAData m1 = null;
		
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  BADataService - savePersonalBiodata  :");
		
		try
		{
			
			String updatefields="";
			
			if(!firstname.trim().equals(""))
				updatefields+="FIRST_NAME_V='" + firstname + "'";
			if(!lastname.trim().equals(""))
				updatefields+=",LAST_NAME_V='" + lastname + "'";
			if(!fathersname.trim().equals(""))
				updatefields+=",FATHERS_NAME_V='" + fathersname + "'";
			if(!mothersname.trim().equals(""))
				updatefields+=",MOTHERS_NAME_V='" + mothersname + "'";
			if(!dob.trim().equals(""))
				updatefields+=",DOB_DATE=STR_TO_DATE('"+ dob + "','%Y-%m-%d')";
			if(!pob.trim().equals(""))
				updatefields+=",POB_V='" + pob + "'";
			if(!nationality.trim().equals(""))
				updatefields+=",NATIONALITY_V='" + nationality + "'";
			
			
			
			Connection conn = DBConnection.getConnection();
			
			System.out.println(" putBAData ...   Check 2 :");
			 					 
			String query = "UPDATE meddb.biodata SET  " + updatefields + " WHERE EMP_ID_V=?";
			
			System.out.println(" QUERY :" + query);
				      PreparedStatement preparedStmt1 = conn.prepareStatement(query);
				      preparedStmt1.setString (1, empid);			     
				      preparedStmt1.execute();
				      
				      conn.close();
		      
		System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  BADataService - savePersonalBiodata  :");
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		List<BAData> list = new ArrayList<>();
		
		list.add(m1);
		
		return list;
		
		
		
	}
	
	
	
	
	
	
	

}
