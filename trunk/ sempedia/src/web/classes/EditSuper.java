package web.classes;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassPOJO;

import dao.ClassDao;

@SuppressWarnings("serial")
public class EditSuper extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("classId"));
		String newName = request.getParameter("superClass");
		
System.out.println("Class Id:"+id);
System.out.println("New Name:"+newName);

		ClassDao cdao = new ClassDao();
		try {
System.out.println("Calling classDao.editSuper(id,newName)");
			cdao.editSuper(id, newName);			

			String className = cdao.getClassName(id);
			ClassPOJO aclass = new ClassPOJO();
				aclass.setClassName(className);
				aclass.setSuperClass(newName);
			cdao.addClass(aclass);
				
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}