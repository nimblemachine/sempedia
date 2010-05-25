package web.objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import dao.ObjDetailsDao;

@SuppressWarnings("serial")
public class UploadImage extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String name="";
		String fileLoc="";
		String caption="";
		String fileName="";
		String url="";
		int objId=-1;
		int w = -1;
		int h = -1;
		String onlyFile="";
		
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		ServletFileUpload upload = new ServletFileUpload();

			// Parse the request
			FileItemIterator iter=null;
				try {
					iter = upload.getItemIterator(request);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			InputStream stream=null; 
			int count=0;
			
			String loc = null;
			try {
				while (iter.hasNext()) {
					count++;
				    FileItemStream item = iter.next();
				    name = item.getFieldName();
				    stream = item.openStream();
//System.out.println(count +" is item form field "+item.isFormField());
				    
				    if(!item.isFormField()){
				    //  if(item==null){
				      	if(count==1){
				      		
System.out.println(item.toString());	
				      		
//System.out.println("first element");
				      	fileName = item.getName(); //set the file name
						
						File afile = new File(fileName);
						onlyFile = afile.getName();
						
						fileLoc = getServletContext().getRealPath("pics/"+onlyFile);
						File newFolder = new File(getServletContext().getRealPath("pics"));
						if(!newFolder.exists()){
							newFolder.mkdir();
							}
						//fileLoc = "C:\\tmp\\"+onlyFile;dd
						IOUtils.copy(stream, new FileOutputStream(fileLoc));
						ImageIcon img = new ImageIcon(fileLoc);
							w = img.getIconWidth();
							h = img.getIconHeight();
//System.out.println(onlyFile);
				      	}
				      }
				   // }
				    else {
//				    	if(count == 2){
//				    		url = Streams.asString(stream);
//System.out.println("This is the URL: "+url);
//
//							URL fileUrl = new URL("http://local.wasp.uwa.edu.au/~pbourke/miscellaneous/domefisheye/ladybug/fish4.jpg");
//							int startIndex = fileUrl.toString().lastIndexOf('/');
//							onlyFile = fileUrl.toString().substring(startIndex + 1);
//System.out.println(fileName);
//							InputStream imageStream = fileUrl.openStream();
//							
//							String fileLoc2 = getServletContext().getRealPath("pics/"+onlyFile);
//							File newFolder = new File(getServletContext().getRealPath("pics"));
//							if(!newFolder.exists()){
//								newFolder.mkdir();
//								}
//								IOUtils.copy(imageStream, new FileOutputStream(fileLoc2));
//				    		}
				    
				    	if(count == 2){
//System.out.println("second element");
				    		caption = Streams.asString(stream);
//System.out.println(caption);
				    	}
				    	if(count == 3){
//System.out.println("third element");
					        objId = Integer.parseInt(Streams.asString(stream));
//System.out.println(objId);				    		
				    	}
				    }
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println(fileLoc);
			System.out.println("image height= "+h+" image width = "+w);
			
	    	ObjDetailsDao oddao = new ObjDetailsDao();
	    	
	    	double ratio =((double)w)/200;
System.out.println("My ratio:"+ ratio);
	    	w = 200;
	    	h=(int) (h/ratio);
	    	
System.out.println("pics/"+onlyFile);
System.out.println(caption);
System.out.println(w);
System.out.println(h);
System.out.println(objId);

			try {
				oddao.modifyImage("pics/"+onlyFile, caption, w, h, objId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}
}