package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.ImageDetails;

public class ObjDetailsDao {
	//create a method to add object details
	// create a method to modify object details
	
	public void modifyText(String text, int objId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select obj_id from obj_details where obj_id="+objId);			
		if(rs.first()){
			editText(text, objId);
		}
		else addText(text, objId);
		con.close();
	}
	
	public void addText(String text, int objId) throws SQLException{		
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		stmt.executeUpdate("insert into obj_details(TEXT, obj_id) values('"+text+"',"+objId+")");
		
	}

	public void editText(String text, int objId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
System.out.println("this is it: "+ objId);
System.out.println("this is more: "+ text);

//stmt.executeUpdate("UPDATE obj_details SET IMG_SRC = \""+src+"\", IMG_CAPTION = \""+caption+"\",IMG_HEIGHT = "+height+",IMG_WIDTH = "+width+" WHERE obj_id="+objId);	


		stmt.executeUpdate("UPDATE obj_details SET TEXT = \""+text+"\" WHERE obj_id="+objId);
		con.close();
	}
	
	public String getText(int objId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select TEXT from obj_details where obj_id="+objId);
		String text = "";
		while(rs.next()){
			text = rs.getString("TEXT");
		}
		con.close();
		return text;
	}	
	
	public void modifyImage(String src, String caption, int width, int height, int objId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select obj_id from obj_details where obj_id="+objId);
		if(rs.first()){
			editImage(src, caption, width, height, objId);
System.out.println("We're going to edit an image");
		}
		else addImage(src, caption, width, height, objId);
System.out.println("We're going to add an image");
		con.close();		
	}

	public void addImage(String src, String caption, int width, int height, int objId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
			
		stmt.executeUpdate("insert into obj_details(IMG_SRC, IMG_CAPTION, IMG_HEIGHT, IMG_WIDTH, obj_id) values(\""+src+"\",\""+caption+"\","+height+","+width+","+objId+")");	
		con.close();
	}
	
	public void editImage(String src, String caption, int width, int height, int objId) throws SQLException{
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		
System.out.println("UPDATE obj_details SET IMG_SRC = \""+src+"\", IMG_CAPTION = \""+caption+"\",IMG_HEIGHT = "+height+",IMG_WIDTH = "+width+" WHERE obj_id="+objId);
		stmt.executeUpdate("UPDATE obj_details SET IMG_SRC = \""+src+"\", IMG_CAPTION = \""+caption+"\",IMG_HEIGHT = "+height+",IMG_WIDTH = "+width+" WHERE obj_id="+objId);	
		con.close();
	}
	
	public ImageDetails getImage(int objId) throws SQLException{

		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select IMG_SRC, IMG_HEIGHT, IMG_WIDTH from obj_details where obj_id="+objId);
		ImageDetails idet = new ImageDetails();
		while(rs.next()){
			idet.setSrc(rs.getString("IMG_SRC"));
			idet.setHeight(rs.getInt("IMG_HEIGHT"));
			idet.setWidth(rs.getInt("IMG_WIDTH"));			
		}
		con.close();
		return idet;
	}
}