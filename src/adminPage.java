import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminPage")
public class adminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public adminPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("status") != null)
			if (request.getSession().getAttribute("status").toString().equals("admin")) {
				ServletContext con = getServletContext();
				ArrayList<String> userNames = (ArrayList<String>) con.getAttribute("userNames");
				ArrayList<String> userPasswords = (ArrayList<String>) con.getAttribute("userPasswords");

				if (request.getParameter("submit") != null)
					if (request.getParameter("submit").toString().equals("Add")) {
						if (request.getParameter("userName") != null && request.getParameter("userPassword") != null) {
							if (!userNames.contains(request.getParameter("userName").toString())
									&& !request.getParameter("userName").toString().equals("")
									&& !request.getParameter("userPassword").toString().equals("")) {
								userNames.add(request.getParameter("userName").toString());
								userPasswords.add(request.getParameter("userPassword").toString());
							}
						}
					} else if (request.getParameter("submit").toString().equals("Update")) {
						if (request.getParameter("userName") != null && request.getParameter("userPassword") != null) {
							String hid = request.getParameter("hid").toString();
							int index = -1;
							if (userNames.contains(hid)) {
								loop: for (int i = 0; i < userNames.size(); i++) {
									if (userNames.get(i).toString().equals(hid)) {
										index = i;
										break loop;
									}
								}
							}

							if (index != -1 && !hid.equals("")
									&& !request.getParameter("userName").toString().equals("")
									&& !request.getParameter("userPassword").toString().equals("")) {
								userNames.set(index, request.getParameter("userName").toString());
								userPasswords.set(index, request.getParameter("userPassword").toString());
							}

						}
					} else if (request.getParameter("submit").toString().equals("Remove")) {
						if (request.getParameter("userName") != null && request.getParameter("userPassword") != null) {
							String hid = request.getParameter("hid").toString();
							int index = -1;
							if (userNames.contains(hid)) {
								loop: for (int i = 0; i < userNames.size(); i++) {
									if (userNames.get(i).toString().equals(hid)) {
										index = i;
										break loop;
									}
								}
							}

							if (index != -1 && !hid.equals("")) {
								userNames.remove(index);
								userPasswords.remove(index);
							}

						}
					}

				con.setAttribute("userName", userNames);
				con.setAttribute("userPasswords", userPasswords);

				request.getRequestDispatcher("admin_page.jsp").forward(request, response);
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