package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class PredicateDao {
	
	public void addPredicate(String pred) throws SQLException{
	
		// check whether predicate exists or not
		if(!doesPredExist(pred)){
			addPredicateName(pred);
		}
	}
	
	public int getPredId(String p) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select pred_id, pred_name from predicates where pred_name ='"+p+"'");

		int predId=-1;
		while(rs.next()){
			predId = rs.getInt("pred_id");
			System.out.println(rs.getInt("pred_id")+","+rs.getString("pred_name"));
		}
		con.close();
		return predId;
	}

	public HashMap<Integer, String> getPredicates() throws SQLException {

		HashMap<Integer, String> predicates = new HashMap<Integer, String>();
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT pred_id, pred_name FROM predicates");
		// if the object name doesn't exist, then add the object name
		while (rs.next()) {
			predicates.put((Integer.valueOf(rs.getString("pred_id"))), rs.getString("pred_name"));
			}
		con.close();
		return predicates;
	}

	
	public String getPredName(int p) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select pred_name from predicates where pred_id ='"+p+"'");
		String predName="";
		while(rs.next()){
			predName = rs.getString("pred_name");
		}
		con.close();
		return predName;
	}
	
	
	public boolean doesPredExist(String p) throws SQLException{
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select pred_id from predicates where pred_name ='"+p+"'");
		if(rs.first()){
			con.close();
			return true;
			
		}
		else { 
			con.close();
			return false; 
			}

	}
	
	public void addPredicateName(String p) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		stmt.executeUpdate("insert into predicates(pred_name) VALUES('"+ p +"')");
		con.close();
	}

}
