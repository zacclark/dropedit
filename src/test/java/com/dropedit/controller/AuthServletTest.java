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
import com.dropedit.controller.AuthServlet;

@Test
public class AuthServletTest {
	
	public void getShouldReturnView() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")).thenReturn(dispatcher);
		
		new AuthServlet().doGet(req, resp);
		
		verify(dispatcher).forward(req, resp);
	}
	
	// public void postShouldAttachDropboxToSession() throws Exception {
	// 	HttpServletRequest req = mock(HttpServletRequest.class);
	// 	HttpServletResponse resp = mock(HttpServletResponse.class);
	// 	HttpSession sesh = mock(HttpSession.class); // mock the session
	// 	
	// 	new AuthServlet().doPost(req, resp);
	// 	
	// 	//verify(resp).sendRedirect("/");
	// 	assertTrue(true);
	// }
	
}