package jobcrawler;

import java.sql.*;

public class jdbc {
	public static void main(String arg[]) {
		Connection con = null;
		//jdbc driver
		String driver = "com.mysql.jdbc.Driver";
		//database url
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String username = "root";
		String password = "root";
		XmlParser xml = new XmlParser();
		try {
			//load the driver
			Class.forName(driver).newInstance();
			//connect to the database
			con = DriverManager.getConnection(url, username, password); 
			if(!con.isClosed()){
				System.out.println("Connection is success");
				Statement statement = con.createStatement();
//				ResultSet rs = statement.executeQuery(sql);
//				while(rs.next()){
//					System.out.println(rs.getString("firstname")+" "+rs.getString("lastname"));
//				}
//				rs.close();
				xml.runXmpParser();
				for(People p: xml.peoplelist){
					String sql = "insert into people values('"+p.firstName+"','"+p.lastName+"')";
					System.out.println(sql);
					statement.executeUpdate(sql);
				}
				con.close();
			}else{
				System.out.println("Connection is fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
