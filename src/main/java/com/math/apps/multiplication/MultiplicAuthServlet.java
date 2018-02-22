package com.math.apps.multiplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class MultiplicAuthServlet
 */
public class MultiplicAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiplicAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("context", request.getContextPath());
		request.getRequestDispatcher("multiplic/multiplicAuth.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		//username = username.replaceAll("\\w|\\d", "");
		
		String mode = request.getParameter("mode");
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("username", username);
		session.setAttribute("context", request.getContextPath());
		session.setAttribute("mode", mode);
		session.setAttribute("answersString", "");
		
		request.getRequestDispatcher("multiplic/multiplic.jsp").forward(request, response);


	}

}
