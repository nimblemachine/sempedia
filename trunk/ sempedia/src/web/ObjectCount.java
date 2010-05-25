package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PredicateDao;

@SuppressWarnings("serial")
public class ObjectCount extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");
		String count = (String)request.getParameter("count");
		int noOfVals = Integer.parseInt(count);
	
		HashMap<Integer, Integer> queryVals = new HashMap<Integer, Integer>();
		
		for(int i=1;i<=noOfVals;i++){
			int preId = Integer.parseInt((String)request.getParameter("preId"+i));
			int objId = Integer.parseInt((String)request.getParameter("objId"+i));
			queryVals.put(preId, objId);
		}
		
		Set<Integer> aSet = new HashSet<Integer>();
			aSet = queryVals.keySet();
			Iterator<Integer> itr = aSet.iterator();
		
			while(itr.hasNext()){
			PredicateDao pdao = new PredicateDao();

//System.out.println(pdao.getPredName(itr.next()));

			}
		}
	}
