package web.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;
import dao.ObjectDao;

@SuppressWarnings("serial")
public class ClassAutoComplete extends HttpServlet{
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			

			ClassDao cdao = new ClassDao();	//this return a HashMap of "objects"
			String classListAsString="";
			HashMap<Integer,String> classList = new HashMap<Integer,String>();
			try {
				classList = cdao.getClasses();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Set<Integer> classIds = new HashSet<Integer>();
			classIds = classList.keySet();
			Iterator<Integer> itr = classIds.iterator();
			int itrCount=0;
			while(itr.hasNext()){
				int objId = itr.next();
				String objName = classList.get(objId);
				if(itrCount==0){
					classListAsString = objName;
				}
				else{
					classListAsString +="|";
					classListAsString +=objName;
				}
				itrCount++;
			}
			
			out.write(classListAsString);
		}		
}