package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.ClassPOJO;

public class ClassPropertyDao {
	
	
	public void addClassProp(int classId, String prop) throws SQLException{
		
		/// check whether the prop/predicate exists
		// if so get its id
		// if not add it, and get its id
		
		PredicateDao pdao = new PredicateDao();
		boolean predExists = pdao.doesPredExist(prop);
		if(!predExists){
			pdao.addPredicate(prop);
		}
		int predId = pdao.getPredId(prop);
		
		addProperty(classId,predId);
		
		
	}
	
	public void addProperty(int classId, int propId) throws SQLException{
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select prop_id from class_properties where class_id="+classId+" and prop_class_id="+propId+"");
		if(!(rs.next())){ //if the combination doesn't exist
			stmt.executeUpdate("insert into class_properties(class_id, prop_class_id) values("+classId+","+propId+")");
		}
		con.close();
	}
	
	public HashMap<Integer, String> getProperties(int classId) throws SQLException{
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		HashMap<Integer,String> propsMap = new HashMap<Integer,String>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select prop_class_id from class_properties where class_id = "+classId+"");
		PredicateDao pdao = new PredicateDao();
		while(rs.next()){
			Integer propId = rs.getInt("prop_class_id");
			String propName = pdao.getPredName(propId);
			propsMap.put(propId, propName);
		}
		con.close();
		return propsMap;
	}
}