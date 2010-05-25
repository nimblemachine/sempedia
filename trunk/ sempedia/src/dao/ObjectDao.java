package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import model.ObjectPOJO;

public class ObjectDao {	
	
	
	public HashMap<Integer, String> getObjects() throws SQLException {

		HashMap<Integer, String> objects = new HashMap<Integer, String>();
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT object_id, object_name FROM objects");
		// if the object name doesn't exist, then add the object name
		while (rs.next()) {
			objects.put((Integer.valueOf(rs.getString("object_id"))), rs.getString("object_name"));
		}
		con.close();
		return objects;
	}
	
	public HashMap<Integer, String> getObjects(String objectName) throws SQLException {

		HashMap<Integer, String> objects = new HashMap<Integer, String>();
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT object_id, object_name FROM objects WHERE object_name like '%"
						+ objectName + "%'");
		// if the object name doesn't exist, then add the object name
		while (rs.next()) {
			objects.put((Integer.valueOf(rs.getString("object_id"))), rs.getString("object_name"));
		}
		con.close();
		return objects;
	}
	
	public String getObjectName(int objectId) throws SQLException {
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT object_name FROM objects WHERE object_id="+ objectId);
		// if the object name doesn't exist, then add the object name
		String objectName="";
		while (rs.next()) { objectName = rs.getString("object_name"); }
		con.close();
		return objectName;
	}
	
	public int getObjectId(String objectName) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT object_id FROM objects WHERE object_name='"+ objectName + "'");
		// if the object name doesn't exist, then add the object name
		int objectId = -1;
		while (rs.next()) { objectId = rs.getInt("object_id"); }
		con.close();
		return objectId;
	}
	
	public boolean isStringEmpty(String s){
		if(!(s.isEmpty())||!(s=="")||!(s==null)){
			return false; //if any of these is false then the whole condition is false
		} else 
			return true;
	}
	
	public void addObjectName(String n) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT object_name FROM objects WHERE object_name='" + n + "'");
		if (!(rs.next())) { // if there is no class with the specified name
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("INSERT INTO objects(object_name) VALUES('"+ n +"')"); // add the class
		}
		con.close();
	}
	
	public void editObjectName(int o, String n) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("UPDATE objects SET object_name='" + n + "' where object_id=" + o + "");
		con.close();
	}
	
	public void addObject(ObjectPOJO objectToAdd) throws SQLException {

		// have to test to see if the object's are not already in the database
		String objectName = objectToAdd.getObjectName();
		String className = objectToAdd.getClasses().get(0);
		ClassDao cdao = new ClassDao();
		
		if(isStringEmpty(objectName)==false){
			addObjectName(objectName);
		}
		if(isStringEmpty(className)==false){
			cdao.addClass(className);
		}
		// we are now guaranteed that the class and object are both in the database
		int objectId = getObjectId(objectName);
		int classId = cdao.getClassId(className);
System.out.println("Class Id - "+classId);		
		
		ObjClaRelDao ocrdao = new ObjClaRelDao();
		ocrdao.addObjCla(objectId,classId);

	}
	
	public boolean doesObjExist(String o) throws SQLException{
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select object_id from objects where object_name ='"+o+"'");
		if(!rs.next()){
			con.close();
			return false;
			
		}
		else { 
			con.close();
			return true; 
			}
	}
	
	public void deleteObject(int objId) throws SQLException{
		boolean isDeleted = false;
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM objects where object_id=" + objId);
		con.close();
	}
	
}
