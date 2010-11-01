import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class DispatchServlet extends HttpServlet {
    public static final String VIEW = "/WEB-INF/jsp/dispatcher.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (true) {									// do proper check
			resp.sendRedirect("login");
		}else{
			resp.sendRedirect("app"); 				// change this
		}
    }
}
