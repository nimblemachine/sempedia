package web.classes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassPropertyDao;
import dao.PredicateDao;

@SuppressWarnings("serial")
public class AddProperty extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		try {
			
			int classId = Integer.parseInt(request.getParameter("classId"));
			String prop = request.getParameter("property");

			PredicateDao pdao = new PredicateDao();
			ClassPropertyDao cpdao = new ClassPropertyDao();
			boolean doesPropExist = pdao.doesPredExist(prop);
			
			if(doesPropExist){
				// just add to list
				pdao.addPredicate(prop);
				cpdao.addClassProp(classId, prop);
			}
			else{
				// add property to predicates list first
				cpdao.addClassProp(classId, prop);				
			}
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
