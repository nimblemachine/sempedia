package web.objects;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ObjectDao;

public class EditObjectName extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int objId = Integer.parseInt(request.getParameter("id"));
		editObjectName(objId, request);
		
		getServletContext().getRequestDispatcher("/jsp/objectPageEdit.jsp").forward(request, response);

	}

	private void editObjectName(int objId, HttpServletRequest request) {
		
		ObjectDao odao = new ObjectDao();
		try {
			String newName = (String) request.getParameter("newObjectName");
			odao.editObjectName(objId, newName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}