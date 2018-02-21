package com.math.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.math.database.Connector;
import com.math.database.Init.InitDB;
import com.math.util.DateParser;

import java.util.Date;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<H1>Welcome to the OpenShift!</H1> <h2>Pr b| BET Zaaayyyaaa !</h2> <p>Il'ich rules the world</p>");
		Date date = new Date();
		Object param = new java.sql.Timestamp(date.getTime());
		System.out.println(param.toString());
		

		
		// Write DATE and TIME to DB
		/*try {
			Connection con = Connector.getInstance().getConnection();
			String query = "UPDATE users SET time=? WHERE id=1";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setObject(1, param);
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
		
		
		// Read DATE and TIME from DB
		/*try {
			Connection con = Connector.getInstance().getConnection();
			String query = "SELECT time FROM users WHERE id=1";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			Object time = result.getObject("time");
			
			// Use my class DateParser to out Time and Date in suitable format
			DateParser dateParser = new DateParser("YYYY.MM.DD HH:mm");
			System.out.println(dateParser.toString((Date)time));
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		// Test InitDB
		/*InitDB initDB = new InitDB();
		initDB.init();*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
