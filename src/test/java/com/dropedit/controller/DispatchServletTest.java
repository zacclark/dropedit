package com.dropedit.controller;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.dropedit.controller.DispatchServlet;

@Test
public class DispatchServletTest {
	
	public void noSessionShouldRedirectToLogin() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		
		when(req.getSession(false)).thenReturn(null); // mock no session
		
		new DispatchServlet().doGet(req, resp);
		
		verify(resp).sendRedirect("login");
	}
	
	public void someSessionShouldRedirectToList() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		HttpSession sesh = mock(HttpSession.class); // mock the session
		
		when(req.getSession(false)).thenReturn(sesh); // use mock session
		
		new DispatchServlet().doGet(req, resp);
		
		verify(resp).sendRedirect("list");
	}
}