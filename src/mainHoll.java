import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mainHoll")
public class mainHoll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public mainHoll() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null && request.getParameter("code") != null) {
			ServletContext con = getServletContext();
			String admin_name = con.getAttribute("admin_name").toString();
			String admin_password = con.getAttribute("admin_password").toString();

			ArrayList users = (ArrayList) con.getAttribute("userNames");
			ArrayList passwords = (ArrayList) con.getAttribute("userPasswords");

			String id = request.getParameter("id").toString();
			String code = request.getParameter("code").toString();

			boolean mobile = false;

			if (request.getParameter("mobile") != null)
				mobile = true;

			if (admin_name.equals(id) && admin_password.equals(code)) {
				if (admin_name.equals("admin") && admin_password.equals("password"))
					response.sendRedirect("/adminPassword");
				else
					response.sendRedirect("/adminPage");

				request.getSession().setAttribute("status", "admin");
			} else {
				if (users.contains(id)) {

					int index = -1;
					loop: for (int i = 0; i < users.size(); i++)
						if (users.get(i).toString().equals(id)) {
							index = i;
							break loop;
						}

					if (code.equals(passwords.get(index).toString())) {
						request.getSession().setAttribute("userName", id);

						if (mobile)
							response.getWriter().print("REQUEST ACCEPTED");
						else
							response.sendRedirect("/UserPage");

					}

					request.getSession().setAttribute("status", "client");
				} else {
					
					if (request.getSession().getAttribute("status") != null)
						request.getSession().removeAttribute("status");
					
					if(mobile)
						response.getWriter().print("REQUEST REJECTED");
					else
						response.sendRedirect("/?wrong=true");
					
				}
			}
		} else {
			response.sendRedirect("/");
		}
	}

}