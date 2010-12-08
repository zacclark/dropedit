package com.dropedit.controller;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import com.dropedit.controller.ListServlet;
import com.dropbox.client.*;
import java.util.Map;

@Test
public class ListServletTest {
	
	public void noSessionShouldRedirect() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		
		when(req.getSession(false)).thenReturn(null); // mock no session
		
		new ListServlet().doGet(req, resp);
		
		verify(resp).sendRedirect("/");
	}
	
	public void requestWithSessionIsForwardedView() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		HttpSession sesh = mock(HttpSession.class); // mock the session
		
		when(req.getSession(false)).thenReturn(sesh); // use mock session
		when(sesh.getAttribute("uname")).thenReturn("test");
		
		// create a new dropbox client, as if it was created by the AuthServlet
		Map config = Authenticator.loadConfig("config/keys.json");
		Authenticator auth = new Authenticator(config);
		DropboxClient dropbox = new DropboxClient(config, auth);
		
		when(sesh.getAttribute("client")).thenReturn(dropbox);
		
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(req.getRequestDispatcher("/WEB-INF/jsp/list.jsp")).thenReturn(dispatcher);
		
		new ListServlet().doGet(req, resp);
		
		verify(dispatcher).forward(req, resp);
	}
	
}