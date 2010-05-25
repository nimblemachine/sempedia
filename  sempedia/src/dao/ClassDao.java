package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import model.ClassPOJO;

public class ClassDao {

	public HashMap<Integer, String> getClasses(String className)
			throws SQLException {

		HashMap<Integer, String> classes = new HashMap<Integer, String>();
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_id, class_name from classes where class_name like '%"
						+ className + "%'");

		// if the object name doesn't exist, then add the object name
		while (rs.next()) {
			classes.put((Integer.valueOf(rs.getString("class_id"))), rs
					.getString("class_name"));
		}
		con.close();
		return classes;
	}

	public HashMap<Integer, String> getClasses() throws SQLException {

		HashMap<Integer, String> classes = new HashMap<Integer, String>();
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_id, class_name FROM classes");
		// if the object name doesn't exist, then add the object name
		while (rs.next()) {
			classes.put((Integer.valueOf(rs.getString("class_id"))), rs
					.getString("class_name"));
		}
		con.close();
		return classes;
	}

	public String getClassName(int id) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_name FROM classes where class_id = "
						+ id);

		String className = "";
		while (rs.next()) {
			className = rs.getString("class_name");
		}
		con.close();

		return className;
	}

	public int getClassId(String className) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_id FROM classes WHERE class_name='"
						+ className + "'");
		// if the object name doesn't exist, then add the object name
		int classId = -1;
		while (rs.next()) {
			classId = rs.getInt("class_id");
		}
		con.close();
		return classId;
	}

	public String getSuperClassName(int id) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT super_id FROM classes WHERE class_id= "
						+ id);

		int superId = -1;
		String superClass = "";
		while (rs.next()) {
			superId = rs.getInt("super_id");
		}
		superClass = getClassName(superId);
		con.close();

		return superClass;
	}

	public ClassPOJO getClass(int id) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_name, super_id FROM classes WHERE class_id='"
						+ id + "'");
		// if the object name doesn't exist, then add the object name
		ClassPOJO aclass = new ClassPOJO();
		while (rs.next()) {
			aclass.setClassName(rs.getString("class_name"));
			int superId = rs.getInt("super_id");
			aclass.setSuperClass(getClassName(superId));
		}
		con.close();
		return aclass;
	}

	public void addClass(String className) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_name FROM classes WHERE class_name='"
						+ className + "'");
		if (!(rs.next())) { // if there is no class with the specified name
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("INSERT INTO classes(class_name) VALUES('"
					+ className + "')"); // add the class
		}
	}

	public boolean doesClassExist(String className) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT class_name FROM classes WHERE class_name='"
						+ className + "'");
		if (!(rs.next())) { // if there is no class with the specified name
			return false;
		} else {
			return true;
		}
	}

	public boolean doesSuperExist(String className) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT super_id FROM classes WHERE class_name='"
						+ className + "'");
		if (!(rs.next())) { // if there is no class with the specified name
			return false;
		} else {
			return true;
		}
	}

	public int getSuperId(String className) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT super_id FROM classes WHERE class_name='"
						+ className + "'");
		String superId;
		if (!rs.next()) { // if there is no class with the specified name
			return -1;
		} else {
			superId = rs.getString("super_id");
		}
		if (superId == null) {
			return -1;
		} else
			return Integer.parseInt(rs.getString("super_id"));
	}

	public void editClassName(int claId, String className) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		stmt.executeUpdate("UPDATE classes SET class_name = '" + className
				+ "' WHERE class_id=" + claId + "");
		con.close();
	}

	public void addSuperClass(String className, String superClass)
			throws SQLException {
		// if the super class doesn't exist as a class
		// if the super class doesn't exist add it
		// get the super class Id
		// check that the class exists
		// if it doesnt exist add it
		// add the relationship between the two

		DBCon db = new DBCon();
		Connection con = db.getCon();

		boolean superExists = doesClassExist(superClass);
		if (!superExists) {
			addClass(superClass);
		}
		int superId = getClassId(superClass);

		boolean classExists = doesClassExist(className);
		if (!classExists) {
			addClass(className);
		}
		// we know that the super class is now added as a class but
		if (!doesSuperExist(superClass)) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO classes(superId) values('"
					+ superId + "')");
		}

		if ((doesSuperExist(superClass))
				&& !(getSuperId(superClass) == superId)) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE classes SET super_id = '" + superId
					+ "' where class_name='" + className + "'");
		}
		con.close();
	}

	public void editSuper(int classId, String newSuperName) throws SQLException {

		DBCon db = new DBCon();
		Connection con = db.getCon();

		// check if class exists

		boolean doesSuperClassExist = doesClassExist(newSuperName);
		if (!doesSuperClassExist) {
			addClass(newSuperName);
			System.out.println("Just added: " + newSuperName);
		}
		int superId = getSuperId(newSuperName);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("UPDATE classes SET super_id = '" + superId
				+ "' where class_id=" + classId);
		System.out.println("Just added after: " + newSuperName);
	}

	public void addClass(ClassPOJO classToAdd) throws SQLException {

		// have to test to see if the object's are not already in the database
		String className = classToAdd.getClassName();
		String superClass = classToAdd.getSuperClass();

		DBCon db = new DBCon();
		Connection con = db.getCon();

		if (!doesClassExist(className)) {
			addClass(className);
			System.out.println("1 adding class " + className);
		}
		if (!doesClassExist(superClass)) {
			addClass(superClass);
			System.out.println("2 adding class " + superClass);
		}
		addSuperClass(className, superClass);

		con.close();
	}

	public void updateClassDetails(int classId, String text)
			throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();
System.out.println("got here into the update method");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT text FROM class_details WHERE class_id = "
						+ classId);
		if (rs.next()) { // if there is no class with the specified name
			Statement stmt2 = con.createStatement();
			stmt2.executeUpdate("UPDATE class_details SET text = " + text
					+ " where class_id=" + classId);
			//
		} else {
			Statement stmt2 = con.createStatement();
			stmt2
					.executeUpdate("INSERT INTO class_details(text,class_id) VALUES('"
							+ text + "'," + classId + ")"); // add the class
			//
		}
	}

	public String getText(int classId) throws SQLException {
		DBCon db = new DBCon();
		Connection con = db.getCon();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT text FROM class_details WHERE class_id = " + classId);
		String text="";
		if (rs.next()) { // if there is no class with the specified name
			text = rs.getString("text"); //
		}
		
		return text;
	}
}