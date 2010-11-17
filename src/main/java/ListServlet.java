import com.dropbox.client.Authenticator;
import com.dropbox.client.DropboxClient;
import com.dropbox.client.DropboxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ListServlet extends HttpServlet {
    public static final String VIEW = "/WEB-INF/jsp/list.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.sendRedirect("/");
		}else{
			session.setAttribute("hht", "hht");

			DropboxClient dropbox = (DropboxClient)session.getAttribute("client");

			req.setAttribute("user", session.getAttribute("uname"));
			String info = null;
			try {
				info = dropbox.accountInfo(false,"").toString();
			} catch (DropboxException e) {
				info = "oh shit it failed!";
			}

			String files[] = {"coolness.txt", "another_file.txt", "notes.txt", "pic.jpeg", info};
			
			req.setAttribute("files", files);
			req.getRequestDispatcher(VIEW).forward(req, resp);
		}
    }
}
