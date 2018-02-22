package com.math.database;

import java.sql.*;
import java.util.*;

import com.math.logic.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class StudentDBWorker {
	
	public void addStudent(User user) throws Exception {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "INSERT INTO students (user_id) VALUES (?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.executeUpdate();
		} catch(MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Student> getAll() {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from students";
					
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			students = this.getStudentsList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return students;
	}
	
	public Student getStudentById(Integer id) {
		Student student = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM students WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<Student> students = this.getStudentsList(result);
			student = students.get(0);
		} catch(IndexOutOfBoundsException e) {
			student = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public Student getStudentByUserId(Integer userId) {
		Student student = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM students WHERE user_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			List<Student> students = this.getStudentsList(result);
			student = students.get(0);
			student.setUserId(userId);
		} catch(IndexOutOfBoundsException e) {
			student = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public void deleteStudentById(Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "DELETE FROM students WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Student> getStudentsList(ResultSet result) {
		List<Student> students = new ArrayList<Student>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				Integer user_id = result.getInt("user_id");
				
				UserDBWorker userDBWorker = new UserDBWorker();
				User user = userDBWorker.getUserById(user_id);
				
				Student student = new Student(id, user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getEmail());
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
}
