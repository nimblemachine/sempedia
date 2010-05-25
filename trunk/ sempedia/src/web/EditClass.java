package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClassDao;
import dao.ClassPropertyDao;

@SuppressWarnings("serial")
public class EditClass extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int claId = Integer.parseInt(request.getParameter("id"));
		try {
			dispPage(claId, request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private void editClassName(int claId, String newName) {
		ClassDao cdao = new ClassDao();
		try {
			cdao.editClassName(claId, newName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispPage(int claId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String className = "";
		String superClassName = "";
		ClassDao cdao = new ClassDao();
		try {
			className = cdao.getClassName(claId);
			superClassName = cdao.getSuperClassName(claId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(className);
		System.out.println(superClassName);

		HashMap<Integer, String> classProps = new HashMap<Integer, String>();
		ClassPropertyDao cpdao = new ClassPropertyDao();

		classProps = cpdao.getProperties(claId);

		
		String text = cdao.getText(claId);

		request.setAttribute("claId", claId);
		request.setAttribute("className", className);
		request.setAttribute("superClass", superClassName);
		request.setAttribute("text", text);
		request.setAttribute("classProps", classProps);

		getServletContext().getRequestDispatcher("/jsp/classPageEdit.jsp")
				.forward(request, response);
	}
}
