package com.math.database;

import java.sql.*;
import java.util.*;

import com.math.logic.*;

public class TestDBWorker {
	public void addTest(Test test, Teacher teacher, Subsection subsection) {
		try {
			Connection con = Connector.getInstance().getConnection();
			PreparedStatement statement;
			
			// if test has subsection
			if (subsection != null) {
				String query = "INSERT INTO tests (title, teacher_id, subsection_id) VALUES (?, ?, ?)";
				statement = con.prepareStatement(query);
				statement.setString(1, test.getTitle());
				statement.setInt(2, teacher.getId());
				statement.setInt(3, subsection.getId());
			// if test hasn't subsection
			} else {
				String query = "INSERT INTO tests (title, teacher_id) VALUES (?, ?)";
				statement = con.prepareStatement(query);
				statement.setString(1, test.getTitle());
				statement.setInt(2, teacher.getId());
			}
			statement.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	public void updateTitle(Test test, Teacher teacher) {
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, test)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "UPDATE tests SET title=? WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setString(1, test.getTitle());
				statement.setInt(2, test.getId());
				statement.executeUpdate();			
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
	}
	
	public void updateSubsection(Test test, Subsection subsection, Teacher teacher) {
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, test)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				// teacher_id was added to be sure that this test belongs to this teacher. Teacher object taken from session
				String query = "UPDATE tests SET subsection_id=? WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, subsection.getId());
				statement.setInt(2, test.getId());
				statement.executeUpdate();			
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
	}
	
	public List<Test> getTestsByTeacher(Teacher teacher) {
		List<Test> tests = new ArrayList<Test>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from tests where teacher_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacher.getId());
			ResultSet result = statement.executeQuery();
			tests = this.getTestsList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return tests;
	}
	
	public List<Test> getTestsBySubsection(Subsection subsection) {		// for students
		List<Test> tests = new ArrayList<Test>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from tests where subsection_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, subsection.getId());
			ResultSet result = statement.executeQuery();
			tests = this.getTestsList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return tests;
	}
	
	public List<Test> getTestsBySubsectionAndByTeacher(Subsection subsection, Teacher teacher) {	// for teachers
		List<Test> tests = new ArrayList<Test>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from tests where subsection_id=? and teacher_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, subsection.getId());
			statement.setInt(2, teacher.getId());
			ResultSet result = statement.executeQuery();
			tests = this.getTestsList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return tests;
	}
	
	public Test getTestById(Integer id, Teacher teacher) {
		Test test = new Test(id, null);
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, test)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "SELECT * FROM tests WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				result.next();			
				test = new Test	(result.getInt("id"), result.getString("title"));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return test;
	}
	
	public void deleteTestById(Integer id, Teacher teacher) {
		Test test = new Test(id, null);
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, test)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "DELETE FROM tests WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, id);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private List<Test> getTestsList(ResultSet result) {
		List<Test> tests = new ArrayList<Test>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				String title = result.getString("title");
				Test test = new Test(id, title);
				tests.add(test);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tests;
	}
}
