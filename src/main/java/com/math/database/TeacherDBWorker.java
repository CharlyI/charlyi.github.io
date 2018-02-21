package com.math.database;

import java.sql.*;
import java.util.*;

import com.math.logic.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class TeacherDBWorker {
	
	public void addTeacher(User user) throws Exception {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "INSERT INTO teachers (user_id) VALUES (?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.executeUpdate();
		} catch(MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Teacher> getAll() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from teachers";
					
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			teachers = this.getTeachersList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return teachers;
	}
	
	public Teacher getTeacherById(Integer id) {
		Teacher teacher = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM teachers WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<Teacher> teachers = this.getTeachersList(result);
			teacher = teachers.get(0);
		} catch(IndexOutOfBoundsException e) {
			teacher = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}
	
	public Teacher getTeacherByUserId(Integer userId) {
		Teacher teacher = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM teachers WHERE user_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			List<Teacher> teachers = this.getTeachersList(result);
			teacher = teachers.get(0);
			teacher.setUserId(userId);
		} catch(IndexOutOfBoundsException e) {
			teacher = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}
	
	public void deleteTeacherById(Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "DELETE FROM teachers WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Teacher> getTeachersList(ResultSet result) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				Integer user_id = result.getInt("user_id");
				
				UserDBWorker userDBWorker = new UserDBWorker();
				User user = userDBWorker.getUserById(user_id);
				
				Teacher teacher = new Teacher(id, user.getFirstname(), user.getLastname(), user.getPatronymic(), user.getEmail());
				teachers.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}
}
