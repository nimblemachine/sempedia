package web.objects;

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

import dao.ObjectDao;

@SuppressWarnings("serial")
public class ObjectAutoComplete extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ObjectDao odao = new ObjectDao();	//this return a HashMap of "objects"
		String objectListAsString="";
		HashMap<Integer,String> objectList = new HashMap<Integer,String>();
		try {
			objectList = odao.getObjects();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Integer> objectIds = new HashSet<Integer>();
		objectIds = objectList.keySet();
		Iterator<Integer> itr = objectIds.iterator();
		int itrCount=0;
		while(itr.hasNext()){
			int objId = itr.next();
			String objName = objectList.get(objId);
			if(itrCount==0){
				objectListAsString = objName;
			}
			else{
				objectListAsString +="|";
				objectListAsString +=objName;
			}
			itrCount++;
		}
//System.out.println(objectListAsString);
		out.write(objectListAsString);	
	}
}