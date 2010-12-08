package com.dropedit.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import com.dropbox.client.Authenticator;
import com.dropbox.client.DropboxClient;
import com.dropbox.client.DropboxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.simple.JSONArray;
//import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: Dec 6, 2010
 * Time: 3:18:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditFileServlet extends HttpServlet{
    public static final String VIEW = "/WEB-INF/jsp/edit.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
		/**********************************************************************/
        DropboxClient dropbox = (DropboxClient)session.getAttribute("client");
        HttpResponse response;
            HttpEntity entity;
            InputStream instream = null;
            StringBuilder fileContents = new StringBuilder();
            try{
                response = dropbox.getFile("dropbox", "/first.txt");
                entity = response.getEntity();
                
                instream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                String line;
                while((line = reader.readLine()) != null) {
                    fileContents.append(line + '\n');
                }
                System.out.println(fileContents.toString());
            }
            catch(com.dropbox.client.DropboxException e)
            {
                 System.out.println("HTTP Response failed");
            }
            finally{
                if(instream != null) instream.close();
            }
           
            //**************************************************************

            req.setAttribute("textBox", fileContents.toString());

			req.getRequestDispatcher(VIEW).forward(req, resp);
    /*************************************************************************/
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       File file = new File("/first.txt");
       RandomAccessFile fil = new RandomAccessFile(file, "rw");

       fil.writeChars(req.getParameter("editbox"));

       HttpSession session = req.getSession();
       DropboxClient dropbox = (DropboxClient)session.getAttribute("client");
      
       try{
        HttpResponse response = dropbox.putFile("dropbox", "", file);
       }
       catch(com.dropbox.client.DropboxException e)
       {
           System.out.println("HTTP Response failed");
       }

       req.getRequestDispatcher(VIEW).forward(req, resp);

    }

}
