package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDao;
import dao.ClassPropertyDao;

@SuppressWarnings("serial")
public class ClassPage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		System.out.println("Id: " + request.getParameter("classId"));
		int classId = Integer.parseInt(request.getParameter("classId"));
		HttpSession session = request.getSession();
		session.setAttribute("classId", classId);
		
		String className = "";
		String superClass = "";
		String text ="";
		ClassDao cdao = new ClassDao();
		try {
			className = (cdao.getClass(classId)).getClassName();
			superClass = (cdao.getClass(classId)).getSuperClass();
			text = cdao.getText(classId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		request.setAttribute("classId",classId);
		request.setAttribute("className",className);
		request.setAttribute("text", text);
		request.setAttribute("superClass",superClass);
		
		//get the properties
		
		ClassPropertyDao cpdao = new ClassPropertyDao();
		HashMap<Integer, String> properties = new HashMap<Integer, String>();
			try {
				properties = cpdao.getProperties(classId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		request.setAttribute("properties", properties);
		
	
//		if(!((superClass.equals()||(superClass=="")||(superClass.isEmpty())))){
			try {
				int superClassId = cdao.getClassId(superClass);
				request.setAttribute("superClassId",superClassId);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}

		getServletContext().getRequestDispatcher("/jsp/classPage.jsp").forward(request, response);
	}
}