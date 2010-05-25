package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TripleDao;

import model.TriplePOJO;

@SuppressWarnings("serial")
public class AddTriple extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		response.setContentType("text/html");
		
		String subject = request.getParameter("subject");
		String predicate = request.getParameter("predicate");
		String object = request.getParameter("object");
		
System.out.println("Subject: "+subject);
System.out.println("Predicate: "+predicate);
System.out.println("Object: "+object);
		
		TriplePOJO tripleToAdd = new TriplePOJO();
			tripleToAdd.setSubject(subject);
			tripleToAdd.setPredicate(predicate);
			tripleToAdd.setObject(object);
		
		TripleDao tdao = new TripleDao();
		try {
			tdao.addTriple(tripleToAdd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
