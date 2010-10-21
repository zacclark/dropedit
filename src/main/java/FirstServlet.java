import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FirstServlet extends HttpServlet {
    public static final String VIEW = "/WEB-INF/jsp/view.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      	req.getRequestDispatcher(VIEW).forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Twitter twitter = newTwitter();
//        try {
//            HttpSession session = req.getSession();
//            RequestToken requestToken = (RequestToken) session.getAttribute(REQUEST_TOKEN_ATTRIBUTE);
//            session.removeAttribute(REQUEST_TOKEN_ATTRIBUTE);
//            setAccessToken(session, twitter.getOAuthAccessToken(requestToken, req.getParameter(PIN_PARAM)));
//            req.getRequestDispatcher(AUTH_RESULTS_VIEW).forward(req, resp);
//        } catch (TwitterException e) {
//            resp.sendError(e.getStatusCode(), e.getMessage());
//        }
//    }
}
