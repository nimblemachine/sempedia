package com.jquery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.ObjectPojo;
import com.util.Constants;

/**
 * @author viswanathp
 *
 */
public class GetAutoCompleteData
{
	private SessionFactory sessionFactory;
	private int totalDataSize;
	private List<String> data;
	public GetAutoCompleteData(String textbox) throws Exception
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.dbdetails");
		String driver = resourceBundle.getString(Constants.DATABASE_DRIVER_CLASS);
		String url = resourceBundle.getString(Constants.DATABASE_URL);
		String urserName = resourceBundle.getString(Constants.DATABASE_USERNAME);
		String password = resourceBundle.getString(Constants.DATABASE_PASSWORD);
		List objectsList = new ArrayList();
		data = new ArrayList();
		Class.forName(driver);
		Connection db = DriverManager.getConnection(url, urserName, password);
		Statement st = db.createStatement();
		ResultSet rs = null;
		if(textbox.equalsIgnoreCase("object"))
		{
			rs = st.executeQuery("SELECT object_name FROM Object ORDER BY object_name");
		}
		else if(textbox.equalsIgnoreCase("class"))
		{
			rs = st.executeQuery("SELECT class_name FROM Class ORDER BY class_name");
		}
		else if(textbox.equalsIgnoreCase("triple"))
		{
			rs = st.executeQuery("SELECT objectLik.object_name from Object objectLik " +
					"ORDER BY objectLik.object_name");
		}
		while (rs.next()) 
		{
			data.add(rs.getString(1));
		}
		totalDataSize = data.size();
		rs.close();
		st.close();
		
	}
//	public GetAutoCompleteData(String textbox)throws Exception
//	{
//		//sessionFactory = new Configuration().configure().buildSessionFactory();
//		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
//		Configuration configuration = new Configuration();
//		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
//		 sessionFactory = configuration.configure().buildSessionFactory();
//		 sessionFactory = configuration.
//		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ : "+sessionFactory);
//		Session session = sessionFactory.openSession();
//		try{
//			ClassPojo superClass = null;
//			ObjectPojo object = null;
//			Query query= null;
//			List hibernateData = null;
//			
//			if(textbox.equalsIgnoreCase("object"))
//			{
//				query = session.createQuery("select obj from ObjectPojo obj");
//				hibernateData = query.list();
//				for (Iterator iterator = hibernateData.iterator(); iterator.hasNext();) 
//				{
//					object = (ObjectPojo) iterator.next();
//					data.add(object.getObjectName().toString());
//				}
//			}
//			else if(textbox.equalsIgnoreCase("class"))
//			{
//				query = session.createQuery("select cls from ClassPojo cls");
//				hibernateData = query.list();
//				for (Iterator iterator = hibernateData.iterator(); iterator.hasNext();) 
//				{
//					superClass = (ClassPojo) iterator.next();
//					data.add(superClass.getClassName().toString());
//				}
//			}
//			totalDataSize = data.size();
//		}catch(HibernateException e){
//			e.printStackTrace();
//		}catch(Exception ee){
//			ee.printStackTrace();
//		}
//		finally{
//			session.close();
//		}
//	}
	public List<String> getData(String query) 
	{
		String country = null;
		query = query.toLowerCase();
		List<String> matched = new ArrayList<String>();
		for(int i=0; i<totalDataSize; i++) 
		{
			country = (String)data.get(i);
			country = country.toLowerCase();
			if(country.startsWith(query)) 
			{
				matched.add(data.get(i));
			}
		}
		return matched;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
