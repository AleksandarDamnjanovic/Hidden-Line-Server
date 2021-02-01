import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chatRoom")
public class chatRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public chatRoom() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean mobile=false;
		if(request.getParameter("mobile")!=null)
			mobile=true;

		if (request.getSession().getAttribute("status") != null) {
			if (request.getSession().getAttribute("status").toString().equals("client")) {
				String user = "";
				if (request.getSession().getAttribute("userName") != null)
					user = request.getSession().getAttribute("userName").toString();
				String chatWith = "";
				if (request.getParameter("chatWith") != null) {
					chatWith = request.getParameter("chatWith").toString();
					request.getSession().setAttribute("chatWith", chatWith);
				} else {
					if (request.getSession().getAttribute("chatWith") != null)
						chatWith = request.getSession().getAttribute("chatWith").toString();
				}

				if (!user.equals("")) {

					if (request.getParameter("submit") != null && request.getParameter("message") != null)
						Records.updateSubList(user, chatWith, user, request.getParameter("message").toString());

					ArrayList<String>log=Records.searchSubElement(user, chatWith);
					String fullMessage="";
					
					if(log!=null)
						if(log.size()>0)
							for(int i=0;i<log.size();i++)
								fullMessage+=log.get(i)+"\n";
					
					if(mobile)
						response.getWriter().print(fullMessage);
					else
						request.getRequestDispatcher("chatRoom.jsp").forward(request, response);
				
				} else {
					if(request.getSession().getAttribute("status")!=null)
						request.getSession().removeAttribute("status");
					
					response.sendRedirect("/");
				}
			} else {
				if(request.getSession().getAttribute("status")!=null)
					request.getSession().removeAttribute("status");
				
				response.sendRedirect("/");
			}
		}else {
			if(request.getSession().getAttribute("status")!=null)
				request.getSession().removeAttribute("status");
			
			response.sendRedirect("/");
		}
	}

}