package java_project;

import java.sql.*;
public class conn {
		Connection c;
		Statement s;
		public conn() throws SQLException{
			try{
				String url="jdbc:mysql://127.0.0.1:3306/javaprjct";
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection conn=DriverManager.getConnection(url,"root","root");
				//System.out.println("Connected");
				s=c.createStatement();
				ResultSet rs=null;
			}
			catch(Exception e){
				System.out.println(e);
			}
		
		}
}
