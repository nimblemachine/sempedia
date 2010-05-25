package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ObjectDao;
import dao.TripleDao;
import model.ObjectPOJO;

@SuppressWarnings("serial")
public class AddObject extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String objectName;
		ArrayList<String> classes = new ArrayList<String>();
		response.setContentType("text/html");
		objectName = (String) request.getParameter("object-name");
		classes.add((String) request.getParameter("class-name"));
		// for now there will only ever be one class so this will work

		ObjectPOJO objectToAdd = new ObjectPOJO();
		objectToAdd.setObjectName(objectName);
		objectToAdd.setClasses(classes);

		ObjectDao db = new ObjectDao();
		try {
			db.addObject(objectToAdd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(objectName + " - " + classes.get(0));
	}
}