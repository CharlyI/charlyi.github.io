package com.math.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.math.database.StudentDBWorker;
import com.math.database.TeacherDBWorker;
import com.math.database.UserDBWorker;
import com.math.logic.Student;
import com.math.logic.Teacher;
import com.math.logic.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("doGet LoginServlet");
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("doPost LoginServlet");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		UserDBWorker userDBWorker = new UserDBWorker();
		User user = userDBWorker.getUserByEmail(email, password);
		
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			if (user.getEmail().equals("spirkost@mail.ru")) {
				response.sendRedirect("/admin");
			}else {
				TeacherDBWorker teacherDBWorker = new TeacherDBWorker();
				Teacher teacher = teacherDBWorker.getTeacherByUserId(user.getId());
				if (teacher != null) {
					session.setAttribute("teacher", teacher);
					response.sendRedirect("teacher");
				}
				StudentDBWorker studentDBWorker = new StudentDBWorker();
				Student student = studentDBWorker.getStudentByUserId(user.getId());
				if (student != null) {
					session.setAttribute("student", student);
					response.sendRedirect("student");
				}
			}
			
		} else {
			System.out.println(email.concat("   ").concat(password).concat("   Неверный логин или пароль!"));
			request.setAttribute("msg", "Неверный логин или пароль!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
