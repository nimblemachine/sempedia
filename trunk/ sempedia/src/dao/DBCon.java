package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	private static final String host = "jdbc:mysql://localhost";
	private static final String port = "3306";
	private static final String db = "sempedia";
	private static final String user = "root";
	private static final String pwd = "";
//	private static final String user = "semuser";
//	private static final String pwd = "cool";
	
	public Connection getCon() {
		Connection con = null;
		try {
			String url = host + ":" + port + "/" + db;

			con = DriverManager.getConnection(url, user, pwd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}