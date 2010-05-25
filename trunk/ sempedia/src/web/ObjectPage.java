package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ObjClaRelDao;
import dao.ObjDetailsDao;
import dao.ObjectDao;
import dao.PredicateDao;
import dao.TripleDao;
import model.ImageDetails;
import model.TriplePOJO;

@SuppressWarnings("serial")
public class ObjectPage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int objId = Integer.parseInt(request.getParameter("id"));

		try {
			dispPage(objId, request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String predicate = request.getParameter("predicate");
		// String object = request.getParameter("object");
		// addSemanticTag(objId, predicate, object);
	}

	private void addSemanticTag(int subId, String pre, String obj) throws SQLException {

		TripleDao tdao = new TripleDao();
		tdao.addTriple(subId, pre, obj);

	}

	public void dispPage(int objId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String objectName = "";
		HashMap<Integer, String> classes = new HashMap<Integer, String>();
		ObjectDao odao = new ObjectDao();
		ObjClaRelDao ocRel = new ObjClaRelDao();
		try {
			objectName = odao.getObjectName(objId);
			classes = ocRel.getObjCla(objId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TripleDao tdao = new TripleDao();
		ArrayList<TriplePOJO> triples = new ArrayList<TriplePOJO>();
		try {
			triples = tdao.getTriples(objId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PredicateDao pdao = new PredicateDao();

		Iterator<TriplePOJO> itr = triples.iterator();
		while (itr.hasNext()) {
			TriplePOJO somet = itr.next();
//			System.out.println(somet.getSubId() + "," + somet.getPreId() + ", " + somet.getObjId());
//			System.out.println(odao.getObjectName(somet.getSubId()) + "," + pdao.getPredName(somet.getPreId()) + ", " + odao.getObjectName(somet.getObjId()));
		}

		ObjDetailsDao oddao = new ObjDetailsDao();
		String text = oddao.getText(objId);
		ImageDetails idet = new ImageDetails();
			idet = oddao.getImage(objId);
			
			int width = idet.getWidth();
			double ratio = width/200;
			int imageWidth = 200;
			int imageHeight = (int) (idet.getHeight()/ratio);
			
			String imageSrc = getServletContext().getRealPath(idet.getSrc());

		request.setAttribute("objId", objId);
		request.setAttribute("objectName", objectName);
		request.setAttribute("classes", classes);
		request.setAttribute("text", text);
		request.setAttribute("triples", triples);
		request.setAttribute("imageSrc", idet.getSrc());
		request.setAttribute("imageHeight", idet.getHeight());
		request.setAttribute("imageWidth", idet.getWidth());

		getServletContext().getRequestDispatcher("/jsp/objectPage.jsp").forward(request, response);

	}
}
