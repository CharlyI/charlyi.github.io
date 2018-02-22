package com.math.database;

import java.sql.*;
import java.util.*;

import com.math.logic.*;

public class TaskDBWorker {
	public Integer addTask(Task task, Test test, Teacher teacher) { // Teacher object taken from session  
		Integer taskId = null;
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, test)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "INSERT INTO tasks (question, test_id) VALUES (?, ?)";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setString(1, task.getQuestion());
				statement.setInt(2, test.getId());
				statement.executeUpdate();
				
				ResultSet gk = statement.getGeneratedKeys();
				if(gk.next()) {
					taskId = gk.getInt(1);// 1 - generated id
				}
				
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
		return taskId;
	}
	
	public void updateTaskById(Task task, Integer id, Teacher teacher) {
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, task)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				
				// First check answers of this task on exists in AnswersOfStudents Table
				AnswerDBWorker answerDBWorker = new AnswerDBWorker();
				List<Answer> answers = answerDBWorker.getAnswersByTaskId(id);
				
				Boolean exists = false;
				for(Answer ans : answers) {
					// If answer exists in AnswersOfStudents Table
					if(answerDBWorker.checkAnswerInAnswersOfStudents(ans.getId())) {
						exists = true;
						break;
					}
				}
				
				// ... then call addTask() and create a new row in Tasks Table
				if(exists) {
					Test test = new TestDBWorker().getTestById(task.getTestId(), teacher);
					this.deleteTaskById(id, teacher);
					this.addTask(task, test, teacher);
					
				}
					// else update data
				else {
					String query = "UPDATE tasks SET question=?, trueanswer_id=? WHERE id=?";
					PreparedStatement statement = con.prepareStatement(query);
					statement.setString(1, task.getQuestion());
					statement.setInt(2, task.getTrueAnswerId());
					statement.setInt(3, id);
					statement.executeUpdate();	
				}
			
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
	}
	
	public void setTrueAnswerById(Task task, Integer id, Teacher teacher) {
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, task)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "UPDATE tasks SET trueanswer_id=? WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, task.getTrueAnswerId());
				statement.setInt(2, id);
				statement.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Task> getTasksByTestId(Integer test_id) {
		List<Task> tasks = new ArrayList<Task>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from tasks where test_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, test_id);
			ResultSet result = statement.executeQuery();
			tasks = this.getTasksList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return tasks;
	}
	
	public List<Task> getActiveTasksByTestId(Integer test_id) {
		List<Task> tasks = new ArrayList<Task>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from tasks where test_id=? and active=true";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, test_id);
			ResultSet result = statement.executeQuery();
			tasks = this.getTasksList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return tasks;
	}
	
	public Task getTaskById(Integer id) {
		Task task = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM tasks WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();			
			task = new Task	(result.getInt("id"), result.getString("question"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return task;
	}
	
	public void deleteTaskById(Integer id, Teacher teacher) {
		Task task = new Task(id, null);
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, task)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "UPDATE tasks SET active=false WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, id);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private List<Task> getTasksList(ResultSet result) {
		List<Task> tasks = new ArrayList<Task>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				String question = result.getString("question");
				Integer trueAnswerId = result.getInt("trueanswer_id");
				Task task = new Task(id, question);
				task.setTrueAnswerId(trueAnswerId);
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasks;
	}
}
