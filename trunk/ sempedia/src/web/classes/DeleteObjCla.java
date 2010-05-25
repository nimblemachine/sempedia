package web.classes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ObjClaRelDao;
import dao.TripleDao;

@SuppressWarnings("serial")
public class DeleteObjCla extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int objId = Integer.parseInt(request.getParameter("objId"));
		int claId = Integer.parseInt(request.getParameter("claId"));
		
		ObjClaRelDao ocrdao = new ObjClaRelDao();
			try {
				ocrdao.deleteObjCla(objId,claId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}