package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.PredObj;
import model.TriplePOJO;

public class TripleDao {

	public ArrayList<TriplePOJO> getTriples(int objId) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();
		ArrayList<TriplePOJO> triples = new ArrayList<TriplePOJO>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select triple_id, pre_id, obj_id from triples where sub_id=" + objId + "");
		while (rs.next()) {
			TriplePOJO atriple = new TriplePOJO();
			atriple.setTripleId(rs.getInt("triple_id"));
//System.out.println("Triple Id: "+atriple.getTripleId());
			atriple.setSubId(objId);
			atriple.setPreId(rs.getInt("pre_id"));
			atriple.setObjId(rs.getInt("obj_id"));
			triples.add(atriple);
		}
		return triples;
	}

	public void addTriple(int subId, String pre, String obj) throws SQLException {

		// need to check if the sub, pre and obj exist first

		// get the Id's or add the values
		ObjectDao odao = new ObjectDao();
		PredicateDao pdao = new PredicateDao();
		
		boolean predExists = pdao.doesPredExist(pre);
		boolean objExists = odao.doesObjExist(obj);
		
		int preId=-99;
		if(!predExists){
			pdao.addPredicate(pre);
		}
		preId=pdao.getPredId(pre);
		
		int objId=-99;
		if(!objExists){
			odao.addObjectName(obj);
		}
		objId=odao.getObjectId(obj);

		if (doesTripleExist(subId, preId, objId) == false) {
			addTripleIds(subId, preId, objId);
		}
	}

	public void addTriple(String sub, String pre, String obj)
			throws SQLException {

		// need to check if the sub, pre and obj exist first

		// get the Id's or add the values
		ObjectDao odao = new ObjectDao();
		int subId = odao.getObjectId(sub);
		PredicateDao pdao = new PredicateDao();
		int preId = pdao.getPredId(pre);
		
		
		if (preId == -1) {
			pdao.addPredicate(pre);
			preId = pdao.getPredId(pre);
		}
		int objId = odao.getObjectId(obj);
		if (objId == -1) {
			odao.addObjectName(obj);
			objId = odao.getObjectId(obj);
		}
		if (doesTripleExist(subId, preId, objId) == false) { // if the triple doesn't exist
			addTripleIds(subId, preId, objId);
		}
	}
	
	public void deleteTriple(int tripleId) throws SQLException{
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM triples WHERE triple_id="+tripleId);
		
		con.close();
	}
	
//	public void editTriple(int subId, String pre, String obj, int tripleId) throws SQLException {
//
//
//		DBCon db = new DBCon();
//		Connection con = db.getCon();
//		
//		
//		
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeUpdate("UPDATE triples SET sub_id="++");
//		
//		
//
//	}


	public boolean doesTripleExist(int s, int p, int o) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT triple_id FROM triples WHERE sub_id = " + s + " AND pre_id = " + p + " AND obj_id = " + o);
		if (!rs.next()) {
			return false;
		} else
			return true;
	}

	private void addTripleIds(int s, int p, int o) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		stmt.executeUpdate("insert into triples(sub_id, pre_id, obj_id) values(" + s + "," + p + "," + o + ")");
	}

	public HashMap<Integer, String> getPreds(String s) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();
		HashMap<Integer, String> predMap = new HashMap<Integer, String>();

		ObjectDao odao = new ObjectDao();
		PredicateDao pdao = new PredicateDao();
		int subjId = odao.getObjectId(s);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select pre_id from triples where sub_id = " + subjId);
		while (rs.next()) {
			Integer predId = rs.getInt("pre_id");
			String predName = pdao.getPredName(predId);
			predMap.put(predId, predName);
		}
		return predMap;
	}
	
	public HashMap<Integer, String> getPreds(int subjId) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();
		HashMap<Integer, String> predMap = new HashMap<Integer, String>();

		PredicateDao pdao = new PredicateDao();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select pre_id from triples where sub_id = " + subjId);
		while (rs.next()) {
			Integer predId = rs.getInt("pre_id");
			String predName = pdao.getPredName(predId);
			predMap.put(predId, predName);
		}
		return predMap;
	}

	public HashMap<Integer, String> getObjects(String p) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();

		HashMap<Integer, String> objMap = new HashMap<Integer, String>();

		ObjectDao odao = new ObjectDao();
		PredicateDao pdao = new PredicateDao();
		int predId = pdao.getPredId(p);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select obj_id from triples where pre_id = " + predId + ")");
		while (rs.next()) {
			Integer objId = rs.getInt("obj_id");
			String objName = odao.getObjectName(objId);
			objMap.put(objId, objName);
		}
		return objMap;
	}
	
	public ArrayList<Integer> getObjects(int subId, int preId) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();

		ArrayList<Integer> objMap = new ArrayList<Integer>();

		ObjectDao odao = new ObjectDao();
		PredicateDao pdao = new PredicateDao();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT obj_id FROM triples WHERE sub_id = "+ subId +" AND pre_id = " + preId);
		while (rs.next()) {
			Integer objId = rs.getInt("obj_id");
			objMap.add(objId);
		}
		return objMap;
	}
	
	public int countPreds(int predId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select triple_id from triples where pre_id = " + predId);
		int c=0;
		while(rs.next()){
			c++;
		}
		return c;
	}
	
	public HashMap<Integer,String> getPreForSub(ArrayList<Integer> vals) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
		String inStmt= "(";
		Iterator<Integer> itr = vals.iterator();
		while(itr.hasNext()){
		inStmt=inStmt+itr.next();
			if(itr.hasNext()){
				inStmt=inStmt+" ,";
//System.out.println(inStmt);
			}
			else inStmt = inStmt+")";
		}
//System.out.println(inStmt);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select sub_id from triples where pre_id in "+inStmt);
		int c=0;
		HashMap<Integer, String> objects = new HashMap<Integer, String>(); 
		ObjectDao odao = new ObjectDao();
		while(rs.next()){
			int objId = Integer.parseInt(rs.getString("sub_id"));
			String objName = odao.getObjectName(objId);
			objects.put(objId,objName);
		}
		return objects;
	}
	
	public HashMap<Integer,String> getPreObjForSub(HashMap<Integer,Integer> preObs) throws SQLException{
		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		String whereClause = "";
		Set<Integer> predSet = new HashSet<Integer>();
		predSet = preObs.keySet();
		Iterator<Integer> itr = predSet.iterator();
		while(itr.hasNext()){
		}

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select sub_id from triples where "+whereClause);
		int c=0;
		HashMap<Integer, String> objects = new HashMap<Integer, String>(); 
		ObjectDao odao = new ObjectDao();
		while(rs.next()){
			int objId = Integer.parseInt(rs.getString("sub_id"));
			String objName = odao.getObjectName(objId);
			objects.put(objId,objName);
		}
		return objects;
	}
	
	public List<Integer> queryObjs(ArrayList<PredObj> preObs) throws SQLException{
		ObjectDao odao = new ObjectDao();
		
			DBCon db = new DBCon();
			Connection con = db.getCon();
			
//			Set<Integer> predSet = new HashSet<Integer>();
//			predSet = preObs.keySet();
//			Iterator<Integer> itr = predSet.iterator();
//			
			Iterator<PredObj> itr = preObs.iterator();
			
			
			ArrayList<ArrayList<Integer>> queryLists = new ArrayList<ArrayList<Integer>>();		
			while(itr.hasNext()){ //iterate over the HashMap of predicate/object pairs
				PredObj po = itr.next();
				int nextPre = po.getPreId();
				Statement stmt = con.createStatement();
				//int objId = preObs.get(nextPre);
				int nextObj = po.getObjId();
				ResultSet rs;
				if((nextObj==-1)||(nextObj==-99)){
				rs = stmt.executeQuery("SELECT * FROM triples WHERE pre_id="+nextPre);
				}
				else{
					rs = stmt.executeQuery("SELECT * FROM triples WHERE pre_id="+nextPre+" AND obj_id="+nextObj);

				}
				ArrayList<Integer> listOfSubs = new ArrayList<Integer>();
				while(rs.next()){
					int sub = rs.getInt("sub_id");
					listOfSubs.add(sub);
				}
				queryLists.add(listOfSubs);	
			}//finish iterating over predicates
			
			
	//System.out.println(queryLists);

			List<Integer> finalResults = new ArrayList<Integer>();
			finalResults = intersection(queryLists);
		
			return finalResults;
		}	
	
	
	public int countObjs(ArrayList<PredObj> preObs) throws SQLException{
	ObjectDao odao = new ObjectDao();
	
		DBCon db = new DBCon();
		Connection con = db.getCon();
		
//		Set<Integer> predSet = new HashSet<Integer>();
//		predSet = preObs.keySet();
//		Iterator<Integer> itr = predSet.iterator();
		
		Iterator<PredObj> itr = preObs.iterator();
		
		
		ArrayList<ArrayList<Integer>> queryLists = new ArrayList<ArrayList<Integer>>();		
		while(itr.hasNext()){ //iterate over the HashMap of predicate/object pairs
			PredObj po = itr.next();
			int nextPre = po.getPreId();
			Statement stmt = con.createStatement();
			//int objId = preObs.get(nextPre);
			int nextObj = po.getObjId();
			ResultSet rs;
			if((nextObj==-1)||(nextObj==-99)){
			rs = stmt.executeQuery("SELECT * FROM triples WHERE pre_id="+nextPre);
			}
			else{
				rs = stmt.executeQuery("SELECT * FROM triples WHERE pre_id="+nextPre+" AND obj_id="+nextObj);

			}
			ArrayList<Integer> listOfSubs = new ArrayList<Integer>();
			while(rs.next()){
				int sub = rs.getInt("sub_id");
				listOfSubs.add(sub);
			}
			queryLists.add(listOfSubs);	
		}//finish iterating over predicates
		
		
//System.out.println(queryLists);

		List<Integer> finalResults = new ArrayList<Integer>();
		finalResults = intersection(queryLists);
		
//		System.out.println("Final results: "+finalResults);
		int size = finalResults.size();
		return size;
	}

	public static List<Integer> intersection(Collection<ArrayList<Integer>> lists){
		if (lists.size()==0)
			return Collections.emptyList();

			Iterator<ArrayList<Integer>> it = lists.iterator();
			HashSet<Integer> resSet = new HashSet<Integer>(it.next());
			while (it.hasNext())
				resSet.retainAll(new HashSet<Integer>(it.next()));
		return new ArrayList<Integer>(resSet);
		
	}
}