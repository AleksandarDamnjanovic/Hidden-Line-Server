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

		if (mobile) {
			ServletContext con = getServletContext();
			ArrayList<String> users = (ArrayList) con.getAttribute("userNames");
			ArrayList<String> passwords = (ArrayList) con.getAttribute("userPasswords");
			String curentUser = "";
			if (request.getParameter("userName") != null && request.getParameter("password") != null) {
				String testName = request.getParameter("userName").toString();
				String code = request.getParameter("password").toString();

				if (users.contains(testName)) {

					int index = -1;
					loop: for (int i = 0; i < users.size(); i++)
						if (users.get(i).toString().equals(testName)) {
							index = i;
							break loop;
						}

					if (code.equals(passwords.get(index).toString())) {
						curentUser = request.getParameter("userName").toString();

					}
				}

			}

			String mobileMessage = "";
			if (!curentUser.equals(""))
				for (String user : users)
					if (!user.equals(curentUser))
						mobileMessage += user + "\n";

			if (mobileMessage.equals(""))
				mobileMessage = "connection error";

			response.getWriter().print(mobileMessage);

		} else {
			if (request.getSession().getAttribute("status") != null)
				if (request.getSession().getAttribute("status").toString().equals("client")) {
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

}