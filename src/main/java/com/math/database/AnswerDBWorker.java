package com.math.database;

import java.sql.*;
import java.util.*;

import com.math.logic.*;


public class AnswerDBWorker {
	public Integer addAnswer(Answer answer, Task task, Teacher teacher) {
		Integer answerId = null;
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, task)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "INSERT INTO answers (answer, task_id) VALUES (?, ?)";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setString(1, answer.getAnswer());
				statement.setInt(2, task.getId());
				statement.executeUpdate();
				
				ResultSet gk = statement.getGeneratedKeys();
				if(gk.next()) {
					answerId = gk.getInt(1);// 1 - generated id
				}
				
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
		
		return answerId;
	}
	
	public void updateAnswerById(Answer answer, Integer id, Teacher teacher) {
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, answer)) {
			try {
				if (checkAnswerInAnswersOfStudents(id)) {
					TaskDBWorker taskDBWorker = new TaskDBWorker();
					Task task = taskDBWorker.getTaskById(answer.getTaskId());
					this.deleteAnswerById(answer.getId(), teacher);
					this.addAnswer(answer, task, teacher);
				} else {
					Connection con = Connector.getInstance().getConnection();
					String query = "UPDATE answers SET answer=? WHERE id=?";
					PreparedStatement statement = con.prepareStatement(query);
					statement.setString(1, answer.getAnswer());
					statement.setInt(2, id);
					statement.executeUpdate();
				}
			} catch(Exception e) {
				e.printStackTrace();			
			}
		}
	}
	
	public List<Answer> getAnswersByTaskId(Integer taskId) {
		List<Answer> answers = new ArrayList<Answer>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from answers where task_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, taskId);
			ResultSet result = statement.executeQuery();
			answers = this.getAnswersList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return answers;
	}
	
	public List<Answer> getActiveAnswersByTaskId(Integer taskId) {
		List<Answer> answers = new ArrayList<Answer>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from answers where task_id=? and active=true";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, taskId);
			ResultSet result = statement.executeQuery();
			answers = this.getAnswersList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return answers;
	}
	
	public Answer getAnswerById(Integer id) {
		Answer answer = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM answers WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();			
			answer = new Answer	(result.getInt("id"), result.getString("title"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public void deleteAnswerById(Integer id, Teacher teacher) {
		Answer answer = new Answer(id, null);
		TeacherDBChecker teacherDBChecker = new TeacherDBChecker();
		if (teacherDBChecker.checkTeacher(teacher, answer)) {
			try {
				Connection con = Connector.getInstance().getConnection();
				String query = "UPDATE answers SET active=false WHERE id=?";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, id);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected Boolean checkAnswerInAnswersOfStudents(Integer answerNum) {
		Boolean isAnswer = true;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT id FROM AnswersOfStudents WHERE AnswerNum=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, answerNum);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				isAnswer = true;
			} else {
				isAnswer = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return isAnswer;
	}
	
	private List<Answer> getAnswersList(ResultSet result) {
		List<Answer> answers = new ArrayList<Answer>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				String answerStr = result.getString("answer");
				Answer answer = new Answer(id, answerStr);
				answers.add(answer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answers;
	}
}
