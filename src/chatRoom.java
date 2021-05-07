import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		boolean mobile=false;
		if(request.getParameter("mobile")!=null)
			mobile=true;

		if(mobile) {
			
			ServletContext con = getServletContext();
			ArrayList<String> users = (ArrayList) con.getAttribute("userNames");
			ArrayList<String> passwords = (ArrayList) con.getAttribute("userPasswords");
			String curentUser = "";
			String chatWith="";
			int reference=1;
			
			if (request.getParameter("userName") != null &&
					request.getParameter("password") != null && 
					request.getParameter("chatWith") != null &&
					request.getParameter("reference")!=null) {
				
				String testName = request.getParameter("userName").toString();
				String code = request.getParameter("password").toString();
				
				try {
					reference=Integer.parseInt(request.getParameter("reference").toString());
				}catch(Exception e) {e.printStackTrace();}
				
				chatWith=request.getParameter("chatWith").toString();

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
			
			int num=0;
			boolean onlyNum=false;
			
			if(request.getParameter("num")!=null)
				onlyNum=true;
			
			if (request.getParameter("submit") != null && request.getParameter("message") != null)
				Records.updateSubList(curentUser, chatWith, curentUser, request.getParameter("message").toString());
			
			ArrayList<String>log=Records.searchSubElement(curentUser, chatWith);
			String fullMessage="";
			
			
			
			if(reference!=-1) {
				
				if(request.getParameter("android") == null)
					reference+=1;
				
				num+=1;
				if(log!=null)
				if(log.size()>0 && reference<=log.size())
					for(int i=reference;i<log.size();i++) {
							fullMessage+=log.get(i)+"\n";
							num++;
					}
			}
				
			if(!onlyNum)
				response.getWriter().print(fullMessage);
			else
				response.getWriter().print(String.valueOf(num));
			
		}else {
			
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

}