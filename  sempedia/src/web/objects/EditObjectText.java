package web.objects;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ObjDetailsDao;

@SuppressWarnings("serial")
public class EditObjectText extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		
System.out.println("Got here");		
		int objId = Integer.parseInt(request.getParameter("objId"));
		String text = (String)request.getParameter("description");
		
		ObjDetailsDao oddao = new ObjDetailsDao();
			try {
				oddao.modifyText(text, objId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				getServletContext().getRequestDispatcher("/jsp/objectPageEdit.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
