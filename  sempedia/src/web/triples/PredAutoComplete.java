package web.triples;

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
import dao.PredicateDao;

@SuppressWarnings("serial")
public class PredAutoComplete extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		PredicateDao pdao = new PredicateDao();	//this return a HashMap of "objects"
		String predListAsString="";
		HashMap<Integer,String> predList = new HashMap<Integer,String>();
		try {
			predList = pdao.getPredicates();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Integer> predIds = new HashSet<Integer>();
		predIds = predList.keySet();
		Iterator<Integer> itr = predIds.iterator();
		int itrCount=0;
		while(itr.hasNext()){
			int objId = itr.next();
			String predName = predList.get(objId);
			if(itrCount==0){
				predListAsString = predName;
			}
			else{
				predListAsString +="|";
				predListAsString +=predName;
			}
			itrCount++;
		}
System.out.println(predListAsString);
		out.write(predListAsString);	
	}
}