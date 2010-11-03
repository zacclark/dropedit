import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import com.dropbox.client.*;
import oauth.signpost.exception.*;
import org.json.simple.parser.ParseException;


public class AuthServlet extends HttpServlet {
    public static final String VIEW = "/WEB-INF/jsp/auth.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map config = null;
		try {
			config = Authenticator.loadConfig("config/keys.json");
		} catch (ParseException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		Authenticator auth = null;
		try {
			auth = new Authenticator(config);
		} catch (OAuthException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		try {
			String url = auth.retrieveRequestToken("http://mysite.com/theyaredone?blah=blah");
		} catch (OAuthException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		try {
			auth.retrieveAccessToken("");
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (OAuthNotAuthorizedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		String access_key = auth.getTokenKey();
		String access_secret = auth.getTokenSecret();
		DropboxClient client = new DropboxClient(config, auth);
		
		req.getRequestDispatcher(VIEW).forward(req, resp);
    }
}
