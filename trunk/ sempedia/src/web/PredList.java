package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JSON.JSONArray;
import model.JSON.JSONException;
import model.JSON.JSONObject;

import dao.PredicateDao;
import dao.TripleDao;

@SuppressWarnings("serial")
public class PredList extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		
			int objId = Integer.parseInt(request.getParameter("id"));
			TripleDao tdao = new TripleDao();
			HashMap<Integer,String> predMap = new HashMap<Integer,String>();
			String results="";
			try {
				predMap = tdao.getPreds(objId);
				results = convertToJSON(predMap);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			out.write(results);
	}
		
	
	private String convertToJSON(HashMap<Integer,String> predMap) throws SQLException, JSONException{
	
		Set<Integer> predSet = new HashSet<Integer>();
		predSet = predMap.keySet();
		
		PredicateDao pdao = new PredicateDao();
		Iterator<Integer> itr = predSet.iterator();
		JSONArray jArray = new JSONArray();
		while (itr.hasNext()) {
			   JSONObject json = new JSONObject();
			   int preId = itr.next();
			   json.put("preId", Integer.toString(preId));
			   json.put("preName", pdao.getPredName(preId));
			   jArray.put(json);
			}

		String results = jArray.toString();
		return results;
	}
}
