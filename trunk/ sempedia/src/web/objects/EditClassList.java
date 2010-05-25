package web.objects;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassPOJO;

import dao.ClassDao;
import dao.ObjClaRelDao;

@SuppressWarnings("serial")
public class EditClassList extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException  {
		
		int objId = Integer.parseInt(request.getParameter("objId"));
		String classToAdd = request.getParameter("class-to-add");

		ClassDao cdao = new ClassDao();
		
		try {
			if(cdao.getClassId(classToAdd)!=-1){		//got to check for duplicate because the dao doesn't
				ClassPOJO classObject = new ClassPOJO();
				classObject.setClassName(classToAdd);
				classObject.setSuperClass(null);
				
			ClassDao db = new ClassDao();
				try {
					db.addClass(classObject);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				// if the class doesn't exist - add it
			}
	
		int claId = cdao.getClassId(classToAdd);
		
		ObjClaRelDao ocrdao = new ObjClaRelDao();
		ocrdao.addObjCla(objId, claId); //no need to check for duplicates, the dao does that
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//	getServletContext().getRequestDispatcher("/jsp/objectPageEdit.jsp").forward(request, response);

			//		Iterator    it;
//		Map         params;
//		String      name;
//		String[]    values;
//		int         n;
//
//		params = request.getParameterMap();
//		it = params.keySet().iterator();
//		while (it.hasNext())
//		{
//		    name = (String)it.next();
//		    values = (String[])params.get(name);
//System.out.println(name);
//		    ClassDao cdao = new ClassDao();
//		    ObjClaRelDao ocrdao = new ObjClaRelDao();
//		    for (n = 0; n < values.length; ++n)
//		    {
//	    		String className = values[n];
//		    	if(!(className.equals(request.getParameter("id")))){
//		    	try {
//		    		
//					cdao.addClass(className);
//					int claId = cdao.getClassId(className);
//					ocrdao.addObjCla(objId, claId);
//
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    
//System.out.println(values[n]);
//		    	}
//		    }
//		}

	}
}