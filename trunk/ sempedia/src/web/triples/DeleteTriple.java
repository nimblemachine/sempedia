package web.triples;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TripleDao;

@SuppressWarnings("serial")
public class DeleteTriple extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int tripleId = Integer.parseInt(request.getParameter("tripleId"));
		
		TripleDao tdao = new TripleDao();
			try {
				tdao.deleteTriple(tripleId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
