import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class init implements ServletContextListener {

    public init() {
    }

	
    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }

	
    public void contextInitialized(ServletContextEvent arg0)  { 
         ServletContext con=arg0.getServletContext();
         con.setAttribute("admin_name", "admin");
         con.setAttribute("admin_password", "password");
         ArrayList<String> userNames=new ArrayList<String>();
         ArrayList<String> userPasswords=new ArrayList<String>();
         con.setAttribute("userNames", userNames);
         con.setAttribute("userPasswords", userPasswords);
         
         if(!Records.isInitialized())
        	 Records.initialize();
         
    }
	
}