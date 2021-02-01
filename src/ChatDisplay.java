

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChatDisplay")
public class ChatDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatDisplay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String host=request.getSession().getAttribute("userName").toString();
		String visitor=request.getSession().getAttribute("chatWith").toString();
		
		ArrayList<String> records = Records.searchSubElement(host, visitor);
		String conversation = "";
		String element = "";
		
		if (records != null)
			if (records.size() > 1) {
				for (int i = 1; i < records.size(); i++) {

					String full[] = records.get(i).split("[|]{5}");

					String sender = "";
					String message = "";

					sender = full[0];
					message = full[1];

					String status = "";

					if (full[0].equals(host))
						status = "sender";
					else
						status = "receiver";

					element = "<div class=\"" + status + "\">" + "<p>" + sender + "</p>" + "<br/>" + "<p>" + message
							+ "</p>" + "</div>\n";
					conversation += element;
					element="";
				}
			}
		
		request.getSession().setAttribute("conversation", conversation);
		request.getRequestDispatcher("chat_display.jsp").forward(request, response);
	}

}
