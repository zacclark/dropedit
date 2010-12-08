package com.dropedit.controller;

import static org.mockito.Mockito.*;

import com.dropbox.client.Authenticator;
import com.dropbox.client.DropboxClient;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import com.dropedit.controller.DispatchServlet;

@Test
public class DispatchServletTest {
	
	public void noSessionShouldRedirectToLogin() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		
		HttpSession sesh = mock(HttpSession.class); // mock the session
        when(sesh.getAttribute("client")).thenReturn(null);

		when(req.getSession(true)).thenReturn(sesh); // use mock session
        
		new DispatchServlet().doGet(req, resp);
		
		verify(resp).sendRedirect("/login");
	}
	
	public void someSessionShouldRedirectToList() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		HttpSession sesh = mock(HttpSession.class); // mock the session
		
		when(req.getSession(true)).thenReturn(sesh); // use mock session

        // create a new dropbox client, as if it was created by the AuthServlet
		Map config = Authenticator.loadConfig("config/keys.json");
		Authenticator auth = new Authenticator(config);
		DropboxClient dropbox = new DropboxClient(config, auth);

		when(sesh.getAttribute("client")).thenReturn(dropbox);
		
		new DispatchServlet().doGet(req, resp);
		
		verify(resp).sendRedirect("/list");
	}
}