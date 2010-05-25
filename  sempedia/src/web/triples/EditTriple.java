package web.triples;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TripleDao;

@SuppressWarnings("serial")
public class EditTriple extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
	int subId = Integer.parseInt(request.getParameter("subId"));
	String predicate = (String)request.getParameter("predicate");
	String object = (String)request.getParameter("object");
	
	TripleDao tdao = new TripleDao();
		try {
			tdao.addTriple(subId, predicate, object);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}