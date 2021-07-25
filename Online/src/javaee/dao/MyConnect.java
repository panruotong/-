package javaee.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class MyConnect {

	public MyConnect() {
		// TODO Auto-generated constructor stub
	}
	 private static  String DriverName="com.mysql.jdbc.Driver";
	 private static String url ="jdbc:mysql://localhost:3306/booksystem";
	 private static String DBusername = "root";
	 private static String DBpassword = "root";
	 private static Connection con=null;
	 
	 public static Connection getConnection() throws NamingException, SQLException{
		 Context ctx=new InitialContext();
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		 try {
			con = (Connection) DriverManager.getConnection(url, DBusername, DBpassword);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return con;
	 }

}
