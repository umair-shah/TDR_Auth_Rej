package Utilities;
import java.util.Date;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

 public final class utility {
	
	public static java.sql.Date toDate(String NEW_FORMAT,String date) throws ParseException
	{
		// August 12, 2010
		Date dt = new SimpleDateFormat(NEW_FORMAT).parse(date);
		return  new java.sql.Date(dt.getTime());
	}
	public static Connection db_conn()
	{
		try 
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} 
		catch (ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Connection lcl_conn = null;
		try
		{
			 //lcl_conn = java.sql.DriverManager.getConnection("jdbc:db2://10.51.41.100:50000/US27501", "db2admin", "admin123/?");
			lcl_conn = java.sql.DriverManager.getConnection("jdbc:db2://localhost:50000/US27501", "db2admin", "db2admin/?");
			if(lcl_conn != null)
			{
				return lcl_conn;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
