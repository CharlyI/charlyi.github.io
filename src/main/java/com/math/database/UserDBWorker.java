package com.math.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.math.logic.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UserDBWorker {
	public Integer addUser(User user) throws Exception {
		Integer userID = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "INSERT INTO users (firstname, lastname,  patronymic, password, email) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getPatronymic());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
			statement.executeUpdate();
			
			ResultSet gk = statement.getGeneratedKeys();
			if(gk.next()) {
				userID = gk.getInt(1);// 1 - generated id
			}
			
		} catch(MySQLIntegrityConstraintViolationException e) {
			System.out.print("Already exists. ");
			System.out.println(this.getClass());
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(this.getClass());
		}
		return userID;
	}
	
	public void updateUser(User user) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE users SET firstname=?, lastname=?, patronymic=? WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getPatronymic());
			statement.setInt(4, user.getId());
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public void updatePassword(User user, String newPassword) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE users SET password=? WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			
			// Here 
			
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	// for admin only
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "select * from users";
					
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			users = this.getUsersList(result);
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return users;
	}
	
	public User getUserByEmail(String email, String password) {
		User user = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM users WHERE email=? and password=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			List<User> users = this.getUsersList(result);
			user = users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserById(Integer id) {
		User user = null;
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT * FROM users WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<User> users = this.getUsersList(result);
			user = users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void deleteUserById(Integer id) {
		try {
			Connection con = Connector.getInstance().getConnection();
			String query = "DELETE FROM users WHERE id=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<User> getUsersList(ResultSet result) {
		List<User> users = new ArrayList<User>();
		try {
			while (result.next()) {
				Integer id = result.getInt("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String patronymic = result.getString("patronymic");
				String password = result.getString("password");
				String email = result.getString("email");
				User user = new User(id, firstname, lastname, patronymic, email, password);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
