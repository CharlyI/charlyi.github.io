package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.math.logic.Answer;
import com.math.logic.AnswerOfStudent;
import com.math.logic.Task;
import com.math.logic.TaskForStudent;

public class AnswerOfStudentDBWorker {

	public void addAnswers(List<AnswerOfStudent> answers) {
		try {
			Connection con = Connector.getInstance().getConnection();
			for(AnswerOfStudent answer : answers) {
				
				String query = "INSERT INTO AnswersOfStudents (taskForStudent_id, AnswerNum) VALUES (?, ?)";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setInt(1, answer.getAnswer().getId());
				statement.setInt(2, answer.getTaskForStudent().getId());
				statement.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<AnswerOfStudent> getTaskReport(TaskForStudent taskForStudent) {

		// возвращает ФИО учителя 
		// и ученика						эту часть можно взять и со страницы
		// название теста
		// дату и время выполнения
		
		// Вопросы				|
		// ответы				}	Task
		// Правильные ответы 	|
		
		
		// Данные учеником ответы

		
		List<AnswerOfStudent> report = new ArrayList<AnswerOfStudent>();
		try {
			
			TaskDBWorker taskDBWorker = new TaskDBWorker();
			AnswerDBWorker answerDBWorker = new AnswerDBWorker();
			
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM AnswersOfStudents WHERE taskForStudent_id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, taskForStudent.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				
				// Set given Answer
				Integer answer_id = result.getInt("AnswerNum");
				Answer givenAnswer = answerDBWorker.getAnswerById(answer_id);
				
				// Set Task
				Integer task_id = givenAnswer.getTaskId();
				Task task = taskDBWorker.getTaskById(task_id);
				
				// Set Answers of Task
				task.setAnswers(answerDBWorker.getAnswersByTaskId(task_id));
				
				// Set ID
				Integer id = result.getInt("id");
				
				// Add AnswerOfStudent to Report
				AnswerOfStudent answerOfStudent = new AnswerOfStudent(id, givenAnswer, taskForStudent, task);
				report.add(answerOfStudent);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return report;
	}
}
