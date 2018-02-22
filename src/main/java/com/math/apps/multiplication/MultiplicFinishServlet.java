package com.math.apps.multiplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.math.util.EmailSender;

/**
 * Servlet implementation class MultiplicFinishServlet
 */
public class MultiplicFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiplicFinishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		String answerCount = request.getParameter("answerCount");
		// check
		
		String mistakeCount = request.getParameter("mistakeCount");
		// check
		
		String answersString = (String)session.getAttribute("answersString");
		
		String username = (String)session.getAttribute("username");
		
		// if user in session
		if (username != null) {
			
			String letter = "Имя: ".concat(username).concat("\n");
			letter = letter.concat("Вопросов: ").concat(answerCount).concat("\n");
			letter = letter.concat("Ошибок: ").concat(mistakeCount).concat("\n");
			letter = letter.concat("Ответы:\n");
			letter = letter.concat(answersString);
			
			EmailSender sender = new EmailSender();
			String subject = "math: Таблица умножения [".concat(username).concat("]");
			
			String msg = "";
			
			try {
				sender.sendMail("vspiri@mail.ru", subject, letter);
				msg = "Отчет отправлен";
			} catch(Exception e) {
				e.printStackTrace();
				msg = "При отправке отчета произошла ошибка";
			}
			
			request.setAttribute("answerCount", answerCount);
			request.setAttribute("mistakeCount", mistakeCount);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("multiplicFinish.jsp").forward(request, response);
			session.removeAttribute("username");
		} else  {
			response.sendRedirect(request.getContextPath().concat("/multiplic"));
		}
	}
}
