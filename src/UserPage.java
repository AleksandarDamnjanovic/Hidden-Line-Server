import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserPage")
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean mobile = false;
		if (request.getParameter("mobile") != null)
			mobile = true;

		if (request.getSession().getAttribute("status") != null)
			if (request.getSession().getAttribute("status").toString().equals("client")) {

				if (mobile) {
					ServletContext con = getServletContext();
					ArrayList<String> users = (ArrayList<String>) con.getAttribute("userNames");
					String curentUser = "";
					if (request.getSession().getAttribute("userName") != null)
						curentUser = request.getSession().getAttribute("userName").toString();

					String mobileMessage="";
					if (!curentUser.equals(""))
						for (String user : users)
							if (!user.equals(curentUser))
								mobileMessage+=user+"\n";
					
					response.getWriter().print(mobileMessage);
					
				}else
					request.getRequestDispatcher("user_page.jsp").forward(request, response);
				
			} else {
				if (request.getSession().getAttribute("status") != null)
					request.getSession().removeAttribute("status");

				response.sendRedirect("/");
			}
		else {
			if (request.getSession().getAttribute("status") != null)
				request.getSession().removeAttribute("status");

			response.sendRedirect("/");
		}
	}

}