package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;

import model.ClassPOJO;

@SuppressWarnings("serial")
public class AddClass extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String className;
		String superClass;
		response.setContentType("text/html");
		className = (String)request.getParameter("class-name");
		superClass = (String)request.getParameter("super-class");
		
		ClassPOJO classToAdd = new ClassPOJO();
			classToAdd.setClassName(className);
			classToAdd.setSuperClass(superClass);
			
		ClassDao db = new ClassDao();
			try {
				db.addClass(classToAdd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(className+" - "+superClass);
	}
}
