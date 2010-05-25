package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjClaRelDao {

	public HashMap<Integer, String> getObjCla(int objId) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select cla_id from object_classes where obj_id='" + objId + "'");
		HashMap<Integer,String> objClaRels = new HashMap<Integer,String>();
		
		ClassDao cdao = new ClassDao();			
		
		// if the object name doesn't exist, then add the object name
		while (rs.next()) {
			Integer classId = rs.getInt("cla_id");
			String className = cdao.getClassName(classId);
			objClaRels.put(rs.getInt("cla_id"), className);
		}
		con.close();
		return objClaRels;
	}

	public void addObjCla(int objId, int claId) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT obj_id, cla_id FROM object_classes WHERE obj_id='" + objId + "' AND cla_id='" + claId + "'");
		if (!(rs.next())) { // if there is no class with the specified name
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("INSERT INTO object_classes(obj_id, cla_id) VALUES('" + objId + "', '" + claId + "')"); // add the class
		}
		con.close();
	}
	
	public void deleteObjCla(int objId, int claId) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM object_classes WHERE obj_id="+objId+" AND cla_id="+claId);
		
		con.close();
	}

}
