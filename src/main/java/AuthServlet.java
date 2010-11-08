import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthServlet extends HttpServlet {
    public static final String VIEW = "/WEB-INF/jsp/login.jsp";

// http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/Servlet-Tutorial-Session-Tracking.html


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      	req.getRequestDispatcher(VIEW).forward(req, resp);
    }

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		
		if (password == "test") {
			HttpSession session = req.getSession(true);
			session.setAttribute("uname", password);
			session.setAttribute("pass", password);
			resp.sendRedirect("/");
		}else{
			// HttpSession session = req.getSession(true);
			// session.setAttribute("uname", password);
			// session.setAttribute("pass", password);
			resp.sendRedirect("/");
		}
    }
}
