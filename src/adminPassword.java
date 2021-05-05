import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminPassword")
public class adminPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public adminPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("submit")!=null)
			if (request.getParameter("submit").equals("It's OK"))
				request.getSession().setAttribute("status", "admin");
		
		if (request.getSession().getAttribute("status") != null)
			if (request.getSession().getAttribute("status").toString().equals("admin")) {

				if (request.getParameter("submit") != null) {
					if (request.getParameter("submit").equals("No go")) {
						if(request.getSession().getAttribute("status")!=null)
							request.getSession().removeAttribute("status");
						
						response.sendRedirect("/");
					} else if (request.getParameter("submit").equals("It's OK")) {

						ServletContext con = getServletContext();

						con.setAttribute("admin_name", request.getParameter("name").toString());
						con.setAttribute("admin_password", request.getParameter("password").toString());

						if(request.getSession().getAttribute("status")!=null)
							request.getSession().removeAttribute("status");
						
						response.sendRedirect("/");
					}
				} else {
					request.getRequestDispatcher("admin_password.jsp").forward(request, response);
				}

			} else {
				if(request.getSession().getAttribute("status")!=null)
					request.getSession().removeAttribute("status");
				
				response.sendRedirect("/");
			}
		else {
			if(request.getSession().getAttribute("status")!=null)
				request.getSession().removeAttribute("status");
			
			response.sendRedirect("/");
		}
	}

}