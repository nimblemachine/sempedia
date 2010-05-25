package web.classes;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClassDao;

@SuppressWarnings("serial")
public class EditClassText extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int claId = Integer.parseInt(request.getParameter("classId"));
		String text = (String) request.getParameter("text");

		ClassDao cdao = new ClassDao();
		try {
			cdao.updateClassDetails(claId, text);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
