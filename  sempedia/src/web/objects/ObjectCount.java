package web.objects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TripleDao;
import model.PredObj;

@SuppressWarnings("serial")
public class ObjectCount extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		String count = (String)request.getParameter("count");
		int noOfVals = Integer.parseInt(count); 
		
//System.out.println(noOfVals);
		
//		HashMap<Integer,Integer> vals = new HashMap<Integer,Integer>();
		ArrayList<PredObj> vals = new ArrayList<PredObj>();
		for(int i=0; i<noOfVals;i++){

			String preIdS;
			String objIdS;
			int preId;
			int objId;
			
			preIdS = request.getParameter("preId"+i);
			objIdS = request.getParameter("objId"+i);
			
			//if((preIdS!=null)&&(preIdS!="")){
			if(((preIdS!=null)||(preIdS!=""))&&(preIdS!="-99")){	//changed evening 26th May 2010
				preId = Integer.parseInt(request.getParameter("preId"+i));
			}
			else{
				preId=-89;
			}
//if((objIdS!=null)&&(objIdS!="")){
			if(((objIdS!=null)||(objIdS!=""))&&(objIdS!="-99")){ 	//changed evening 26th May 2010
				objId = Integer.parseInt(request.getParameter("objId"+i));
			}
			else{
				objId = -89;
			}
//System.out.println("OBJECT COUNT: "+preId+", "+objId);			
//			vals.put(preId, objId);
			PredObj npo = new PredObj(preId,objId);
			if(!(preId==-99)){
				vals.add(npo);
			}
			
		}
//System.out.println(vals);		

		TripleDao tdao = new TripleDao();
		int objCount=-99; 
		try {
			objCount = tdao.countObjs(vals);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(objCount);
		
	}
	
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		response.setContentType("text/json");
//		PrintWriter out = response.getWriter();
//		
//		int varCount = Integer.parseInt(request.getParameter("count"));
//
//		HashMap<Integer,Integer> vals = new HashMap<Integer,Integer>();
//		for(int i=0; i<varCount;i++){
//			int preId = Integer.parseInt(request.getParameter("preId"+i));
//			int objId = Integer.parseInt(request.getParameter("objId"+i));
//			vals.put(preId, objId);
//			
//System.out.println(Integer.parseInt(request.getParameter("preId"+i)));
//System.out.println(Integer.parseInt(request.getParameter("objId"+i)));
//		}		
//		//for some reason selecting multiple items results in a result set of no items?
//		HashMap<Integer, String> resultMap;
//		String results="";
//		try {
//			resultMap = getResultMap(vals);
//			results = returnJSONString(resultMap);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//System.out.println(results);
//		out.print(results);
//	}
//	
//	private HashMap<Integer, String> getResultMap(ArrayList<Integer> subIds) throws SQLException, JSONException{
//		JSONObject JSONString = new JSONObject();		
//		TripleDao tdao = new TripleDao();
//		int count = 0;
//		String results = "";
//		HashMap<Integer, String> objs = new HashMap<Integer, String>();	
//			count = tdao.getPreForSub(subIds).size();
//			objs = tdao.getPreForSub(subIds);
//				
//		return objs;	
//		}
//	
//	private String returnJSONString(HashMap<Integer, String> resultMap) throws JSONException, SQLException{
//		
//		String results = "";
//		
//		Set<Integer> objIds = new HashSet<Integer>();
//		objIds = resultMap.keySet();
//		Iterator<Integer> itr = objIds.iterator();
//		ObjectDao odao = new ObjectDao();
//		JSONArray jArray = new JSONArray();
//		while (itr.hasNext()) {
//		   JSONObject json = new JSONObject();
//		   int objId = itr.next();
//		   json.put("id", Integer.toString(objId));
//		   json.put("name", odao.getObjectName(objId));
//		   jArray.put(json);
//		}
//
//		results = jArray.toString();
//		return results;
//	}
}