package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TriplePOJO;
import dao.ObjClaRelDao;
import dao.ObjDetailsDao;
import dao.ObjectDao;
import dao.PredicateDao;
import dao.TripleDao;

@SuppressWarnings("serial")
public class EditObject extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int objId = Integer.parseInt(request.getParameter("id"));
		dispPage(objId, request, response);
	}
	
	private void dispPage(int objId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String objectName = "";
			HashMap<Integer, String> classes = new HashMap<Integer,String>();
			ObjectDao odao = new ObjectDao();
			ObjClaRelDao ocRel = new ObjClaRelDao();
			try {
				objectName = odao.getObjectName(objId);
				classes = ocRel.getObjCla(objId);
				
				Set<Integer> classIds = new HashSet<Integer>();
					classIds = classes.keySet();
					Iterator<Integer> itr = classIds.iterator();
					
					while(itr.hasNext()){
						System.out.println("class = "+itr.next());			
					}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(objectName);
			//System.out.println(className);

			ArrayList<TriplePOJO> triples = new ArrayList<TriplePOJO>();
			TripleDao tdao = new TripleDao();
			triples = tdao.getTriples(objId);
			Iterator<TriplePOJO> itr2 = triples.iterator();
			PredicateDao pdao = new PredicateDao();
						
//			while (itr2.hasNext()) {
//				TriplePOJO atriple = itr2.next();
//System.out.println("this is the one you've been looking for - "+atriple.getTripleId());				
//			}
				
			ObjDetailsDao oddao = new ObjDetailsDao();
			String text = oddao.getText(objId);

			request.setAttribute("objId", objId);
			request.setAttribute("objectName", objectName);
			request.setAttribute("classes", classes);
			request.setAttribute("text", text);
			request.setAttribute("triples", triples);
			
			getServletContext().getRequestDispatcher("/jsp/objectPageEdit.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}