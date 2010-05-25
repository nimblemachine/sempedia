package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassPOJO;

import dao.ClassDao;
import dao.ClassPropertyDao;

@SuppressWarnings("serial")
public class AddClassProperty extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html");

		ClassPropertyDao cpdao = new ClassPropertyDao();

		int classId = Integer.parseInt(request.getParameter("classId"));
		String property = (String) request.getParameter("property");
		
		
		
		int propertyId=-2;
		try {
			propertyId = doesClassExist(property);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int propId=-3;
		if (!(propertyId == -1)) {
			propId=propertyId;
			try {
				cpdao.addProperty(classId, propId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				propId = addClass(property);
				// need to set propId
				
				cpdao.addProperty(classId, propId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private int getPropertyId(String propertyName) throws SQLException {

		ClassDao cdao = new ClassDao();
		int propId = cdao.getClassId(propertyName);
		return propId;
	}

	private int doesClassExist(String className) throws SQLException {

		ClassDao cdao = new ClassDao();
		int classId = cdao.getClassName(className);
		return classId; // a return value of -1 means the class doesn't exist
	}
	
	private int addClass(String className) throws SQLException{
		ClassDao cdao = new ClassDao();
		ClassPOJO aclass = new ClassPOJO();
		aclass.setClassName(className);
		cdao.addClass(aclass);
		int id = cdao.getClassId(className);
		return id;
	}
}