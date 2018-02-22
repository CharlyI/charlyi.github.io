package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.math.logic.Student;
import com.math.logic.TaskForStudent;
import com.math.logic.Teacher;
import com.math.logic.Test;

public class TaskForStudentDBWorker {
	
	// Public
	
	public void addTaskForStudent(TaskForStudent taskForStudent) throws Exception {
		try {
			
			// Set teacherOfStudent_id
			TeacherOfStudentDBWorker teacherOfStudentDBWorker = new TeacherOfStudentDBWorker();
			Integer teacherOfStudent_id = teacherOfStudentDBWorker.getID(taskForStudent.getTeacher(), taskForStudent.getStudent());
			
			// Set test_id
			Integer test_id = taskForStudent.getTest().getId();
			Connection con = Connector.getInstance().getConnection();
			
			// Set param (date and time)
			Date date = new Date();
			Object param = new java.sql.Timestamp(date.getTime());
			
			String query = "INSERT INTO TasksForStudents (teacherOfStudent_id, test_id, DateOfReceiving) VALUES (?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacherOfStudent_id);
			statement.setInt(2, test_id);
			statement.setObject(3, param);
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TaskForStudent> getAllTasksOfTeacher(Teacher teacher) {
		List<TaskForStudent> tasksForStudents = new ArrayList<TaskForStudent>();
		try {
			Connection con = Connector.getInstance().getConnection();
			
			// Set teacher_id
			Integer teacher_id = teacher.getId();
			
			String query = 	"SELECT TasksForStudents.id, TasksForStudents.DateOfReceiving, TasksForStudents.DateOfPerformance, TasksForStudents.test_id, TeachersOfStudents.student_id"
						+	"FROM TasksForStudents INNER JOIN TeachersOfStudents "
						+ 	"ON TasksForStudents.teacherOfStudent_id = TeachersOfStudents.id "
						+ 	"WHERE TeachersOfStudents.teacher_id=?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, teacher_id);
			ResultSet result = statement.executeQuery();
			
			StudentDBWorker studentDBWorker = new StudentDBWorker();
			TestDBWorker testDBWorker = new TestDBWorker();
			
			while (result.next()) {

				// Set dateOfReceiving and dateOfPerformance
				Date dateOfReceiving = (Date)result.getObject("DateOfReceiving");
				Date dateOfPerformance = (Date)result.getObject("DateOfPerformance");
				
				
				// Set id
				Integer id = result.getInt("id");
				
				// Set student
				Integer student_id = result.getInt("student_id");
				Student student = studentDBWorker.getStudentById(student_id);
				
				// Set test
				Integer test_id = result.getInt("test_id");
				Test test = testDBWorker.getTestById(test_id, teacher);
				
				// Teacher sets from params (from session)
				
				
				TaskForStudent taskForStudent = new TaskForStudent(id, teacher, student, test, dateOfReceiving, dateOfPerformance);
				tasksForStudents.add(taskForStudent);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return tasksForStudents;
	}
	
	public List<TaskForStudent> getAllTasksOfStudent(Student student) {
		List<TaskForStudent> tasksForStudents = new ArrayList<TaskForStudent>();
		try {
			Connection con = Connector.getInstance().getConnection();
			
			// Set student_id
			Integer student_id = student.getId();
			
			String query = 	"SELECT TasksForStudents.id, TasksForStudents.DateOfReceiving, TasksForStudents.DateOfPerformance, TasksForStudents.test_id, TeachersOfStudents.teacher_id"
						+	"FROM TasksForStudents INNER JOIN TeachersOfStudents "
						+ 	"ON TasksForStudents.teacherOfStudent_id = TeachersOfStudents.id "
						+ 	"WHERE TeachersOfStudents.student_id=?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, student_id);
			ResultSet result = statement.executeQuery();
			
			TeacherDBWorker teacherDBWorker = new TeacherDBWorker();
			TestDBWorker testDBWorker = new TestDBWorker();
			
			while (result.next()) {

				// Set dateOfReceiving and dateOfPerformance
				Date dateOfReceiving = (Date)result.getObject("DateOfReceiving");
				Date dateOfPerformance = (Date)result.getObject("DateOfPerformance");
				
				
				// Set id
				Integer id = result.getInt("id");
				
				// Set teacher
				Integer teacher_id = result.getInt("teacher_id");
				Teacher teacher = teacherDBWorker.getTeacherById(teacher_id);
				
				// Set test
				Integer test_id = result.getInt("test_id");
				Test test = testDBWorker.getTestById(test_id, teacher);
				
				// Student sets from params (from session)
				
				
				TaskForStudent taskForStudent = new TaskForStudent(id, teacher, student, test, dateOfReceiving, dateOfPerformance);
				tasksForStudents.add(taskForStudent);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tasksForStudents; 
	}
	
	public void setDateOfPerformance(TaskForStudent taskForStudent) {
		// taskForStudent (i.e. task. teacher, student) gets from session
		try {
			Connection con = Connector.getInstance().getConnection();
			
			// Set param (date and time)
			Date date = new Date();
			Object param = new java.sql.Timestamp(date.getTime());
			
		// My be EDIT !			
			// Set id in TasksForStudents Table
			Integer id = taskForStudent.getId();
		// My be EDIT !
			
			String query = "UPDATE TasksForStudents SET DateOfPerformance=? WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setObject(1, param);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
