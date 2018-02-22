package com.math.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.math.database.StudentDBWorker;
import com.math.database.TeacherDBWorker;
import com.math.database.UserDBWorker;
import com.math.logic.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String password = request.getParameter("password");
		String repeat_password = request.getParameter("repeat_password");
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String patronymic = request.getParameter("patronymic");
		String role = request.getParameter("role");
		
		request.setAttribute("password", password);
		request.setAttribute("repeat_password", repeat_password);
		request.setAttribute("email", email);
		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("patronymic", patronymic);
		request.setAttribute("role", role);

		
		// check password
		if ( !password.equals(repeat_password) ) {
			request.setAttribute("msg", "Пароль и введенный повторно пароль не совпадают!");
			request.setAttribute("password", "");
			request.setAttribute("repeat_password", "");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} else {
			// check strings ( html tags, <45 and ... ) !!!!!!!!!!!!!!!!!!!!!!!!!!
			// check email !!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			
			User user = new User(firstname, lastname, patronymic, email, password);
			
			UserDBWorker userDBWorker = new UserDBWorker();
			
			TeacherDBWorker teacherDBWorker = new TeacherDBWorker();
			
			StudentDBWorker studentDBWorker = new StudentDBWorker();
			
			try {
				
				Integer userID = userDBWorker.addUser(user);
				
				user.setId(userID);
				
				if (role.equals("teacher")) {
					teacherDBWorker.addTeacher(user);
				}
				if (role.equals("student")) {
					studentDBWorker.addStudent(user);
				}
				//response.sendRedirect(path); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! завершение регистрации (через email)
				// а пока:
				response.sendRedirect("login");
			} catch(MySQLIntegrityConstraintViolationException e) {
				System.out.print("User with this email already exists. ");
				System.out.println(this.getClass());
				request.setAttribute("msg", "Пользователь ".concat(email).concat(" уже существует!"));
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(this.getClass());
			}
			
			
		
		}
	}

}
