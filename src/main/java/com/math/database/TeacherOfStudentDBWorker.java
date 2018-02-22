package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.math.logic.Student;
import com.math.logic.Teacher;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class TeacherOfStudentDBWorker {
	
	// Public
	
	public void addTeacherOfStuden(Teacher teacher, Student student) throws Exception {
		try {
			
			// First check is TeacherOfStudent already exists
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT id FROM TeachersOfStudents WHERE teacher_id=? and student_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacher.getId());
			statement.setInt(2, student.getId());
			ResultSet result = statement.executeQuery();
			// If TeacherOfStudent is not exist
			if (!result.next()) {
				query = "INSERT INTO TeachersOfStudents (teacher_id, student_id) VALUES (?, ?)";
				statement = con.prepareStatement(query);
				statement.setInt(1, teacher.getId());
				statement.setInt(2, student.getId());
				statement.executeUpdate();
			}
		} catch(MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getReadyStudentsOfTeacher(Teacher teacher) {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select student_id from TeachersOfStudents where teacher_id=? and teacherIsReady=true and studentIsReady=true";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacher.getId());
			ResultSet result = statement.executeQuery();
			students = this.getStudents(result);
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return students;
	}
	
	public List<Student> getNotReadyStudentsOfTeacher(Teacher teacher) {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select student_id from TeachersOfStudents where teacher_id=? and teacherIsReady=true and studentIsReady=false";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacher.getId());
			ResultSet result = statement.executeQuery();
			students = this.getStudents(result);
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return students;
	}
	
	public List<Teacher> getReadyTeachersOfStudent(Student student) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select teacher_id from TeachersOfStudents where student_id=? and teacherIsReady=true and studentIsReady=true";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, student.getId());
			ResultSet result = statement.executeQuery();
			teachers=this.getTeachers(result);
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return teachers;
	}
	
	public List<Teacher> getNotReadyTeachersOfStudent(Student student) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select teacher_id from TeachersOfStudents where student_id=? and teacherIsReady=false and studentIsReady=true";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, student.getId());
			ResultSet result = statement.executeQuery();
			teachers=this.getTeachers(result);
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return teachers;
	}
	
	public void setTeacherIsReady(Teacher teacher, Student student, Boolean isReady) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE TeachersOfStudents SET teacherIsReady=? where student_id=? and teacher_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setBoolean(1, isReady);
			statement.setInt(2, student.getId());
			statement.setInt(3, teacher.getId());
			statement.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	public void setStudentIsReady(Teacher teacher, Student student, Boolean isReady) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE TeachersOfStudents SET studentIsReady=? where student_id=? and teacher_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setBoolean(1, isReady);
			statement.setInt(2, student.getId());
			statement.setInt(3, teacher.getId());
			statement.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	// Protected
	
	protected Integer getID(Teacher teacher, Student student) {
		Integer id = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT id FROM TeachersOfStudents WHERE teacher_id=? and student_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacher.getId());
			statement.setInt(2, student.getId());
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getInt("id");
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return id;
	}
	
	// Private
	
	// To help getReadyStudentsOfTeacher() and getNotReadyStudentsOfTeacher() methods 
	private List<Student> getStudents(ResultSet result) {
		List<Student> students = new ArrayList<Student>();
		try {
			while (result.next()) {
				Integer student_id = result.getInt("student_id");
				StudentDBWorker studentDBWorker = new StudentDBWorker();
				Student student = studentDBWorker.getStudentById(student_id);
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
	// To help getReadyTeachersOfStudent() and getNotReadyTeachersOfStudent() methods 
	private List<Teacher> getTeachers(ResultSet result) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			while (result.next()) {
				Integer teacher_id = result.getInt("teacher_id");
				TeacherDBWorker teacherDBWorker = new TeacherDBWorker();
				Teacher teacher = teacherDBWorker.getTeacherById(teacher_id);
				teachers.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

}
