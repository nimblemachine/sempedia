package web.triples;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ObjectDao;
import dao.TripleDao;

@SuppressWarnings("serial")
public class AddTriple extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int subId = Integer.parseInt(request.getParameter("subId"));
		String predicate = (String)request.getParameter("predicate-name");
		String object = (String)request.getParameter("object-name");
		
		String revPred = predicate+" (rvs)";
		
		TripleDao tdao = new TripleDao();
			try {
				tdao.addTriple(subId, predicate, object);
				ObjectDao odao = new ObjectDao();
				int revSubId = odao.getObjectId(object);
				String revObjName = odao.getObjectName(subId);
				tdao.addTriple(revSubId, revPred, revObjName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}