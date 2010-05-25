package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JSON.JSONArray;
import model.JSON.JSONException;
import model.JSON.JSONObject;
import dao.ObjectDao;
import dao.TripleDao;
												
@SuppressWarnings("serial")						
public class ObjList extends HttpServlet{		
												
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
												
		response.setContentType("text/json");	
		PrintWriter out = response.getWriter();	
												
			int subId = Integer.parseInt(request.getParameter("subId"));
			int preId = Integer.parseInt(request.getParameter("preId"));
			
			TripleDao tdao = new TripleDao();	
			ArrayList<Integer> obsList = new ArrayList<Integer>();
			String results="";		
			try {								
				obsList = tdao.getObjects(subId, preId);
				results = convertToJSON(obsList);
			} catch (SQLException e) {			
				// TODO Auto-generated catch block
				e.printStackTrace();			
			} catch (JSONException e) {			
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}									
			out.write(results);					
	}											
	
	private String convertToJSON(ArrayList<Integer> obsList) throws SQLException, JSONException{
		
		ObjectDao odao = new ObjectDao();
		Iterator<Integer> itr = obsList.iterator();
		JSONArray jArray = new JSONArray();
		while (itr.hasNext()) {
			   JSONObject json = new JSONObject();
			   int objId = itr.next();
			   json.put("objId", Integer.toString(objId));
			   json.put("objName", odao.getObjectName(objId));
			   jArray.put(json);
			}

		String results = jArray.toString();
		return results;
	}
}