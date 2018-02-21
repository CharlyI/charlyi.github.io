package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.math.logic.Answer;
import com.math.logic.Task;
import com.math.logic.Teacher;
import com.math.logic.Test;

public class TeacherDBChecker {
	public Boolean checkTeacher(Teacher teacher, Test test) {
		Boolean checked = false;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "Select teacher_id from tests where test_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, test.getId());
			ResultSet result = statement.executeQuery();
			result.next();			
			Integer realTeacherId = result.getInt("id");
			if (realTeacherId == teacher.getId()) {
				checked = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return checked;
	} 
	
	public Boolean checkTeacher(Teacher teacher, Task task) {
		Boolean checked = false;
		try {
			Connection con = Connector.getInstance().getConnection();
			
			// Get test_id of this task
			String query = "Select test_id from tasks where task_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, task.getId());
			ResultSet result = statement.executeQuery();
			result.next();
			Integer test_id = result.getInt("test_id");
			
			
			// Get teacher_id of given test
			query = "Select teacher_id from tests where id=?";
			statement = con.prepareStatement(query);
			statement.setInt(1, test_id);
			result = statement.executeQuery();
			result.next();			
			Integer realTeacherId = result.getInt("id");
			if (realTeacherId == teacher.getId()) {
				checked = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return checked;
	} 
	
	public Boolean checkTeacher(Teacher teacher, Answer answer) {
		Boolean checked = false;
		try {
			Connection con = Connector.getInstance().getConnection();
			
			// Get task_id of this answer
			String query = "Select task_id from answers where id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, answer.getId());
			ResultSet result = statement.executeQuery();
			result.next();
			Integer task_id = result.getInt("test_id");
			
			// Get test_id of given task
			query = "Select test_id from tasks where id=?";
			statement = con.prepareStatement(query);
			statement.setInt(1, task_id);
			result = statement.executeQuery();
			result.next();
			Integer test_id = result.getInt("test_id");
			
			
			// Get teacher_id of given test
			query = "Select teacher_id from tests where test_id=?";
			statement = con.prepareStatement(query);
			statement.setInt(1, test_id);
			result = statement.executeQuery();
			result.next();			
			Integer realTeacherId = result.getInt("id");
			if (realTeacherId == teacher.getId()) {
				checked = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return checked;
	}
}
