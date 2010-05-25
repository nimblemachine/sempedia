package web.objects;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ObjectDao;

@SuppressWarnings("serial")
public class DeleteObject extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int objId = Integer.parseInt(request.getParameter("id"));
		
		ObjectDao odao = new ObjectDao();
		try {
			odao.deleteObject(objId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
