package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;
import dao.ObjectDao;

@SuppressWarnings("serial")
public class SeedSearch extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");
		String searchString = (String)request.getParameter("searchString");

//System.out.println("The search string is: "+searchString);
		
		HashMap<Integer,String> objects = new HashMap<Integer,String>();
		HashMap<Integer,String> classes = new HashMap<Integer,String>();		

		ObjectDao odao = new ObjectDao();
		ClassDao cdao = new ClassDao();
		
		try {
			if((searchString.isEmpty())||(searchString.equals(""))||searchString==null){
				objects = odao.getObjects();
				classes = cdao.getClasses();
			}
			else{
				objects = odao.getObjects(searchString);
				classes = cdao.getClasses(searchString);
			}
			
			request.setAttribute("objects", objects);
			request.setAttribute("classes", classes);
			
			getServletContext().getRequestDispatcher("/results.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<Integer> ints = classes.keySet();
		Iterator<Integer> itr = ints.iterator();
		
		while(itr.hasNext()){
			int itrInt = itr.next();
//			System.out.println(itrInt+" | "+ classes.get(itrInt));			
		}
	}
}