package com.dropedit.controller;

import com.dropbox.client.Authenticator;
import com.dropbox.client.DropboxClient;
import com.dropbox.client.DropboxException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
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
            JSONObject testMap = new JSONObject();
			try {
				info = dropbox.accountInfo(false,"").toString();
                testMap = (JSONObject)dropbox.metadata("dropbox", "", 10000, "", true, false, "");
			} catch (DropboxException e) {
				info = "oh shit it failed!";
			}

            Set<FileDescriptor> fileDescriptors = new TreeSet<FileDescriptor>(new Comparator<FileDescriptor>(){
                @Override
                public int compare(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor1) {
                    return fileDescriptor.getName().compareToIgnoreCase(fileDescriptor1.getName());
                }
            } );

            for(Object o : (JSONArray) testMap.get("contents")) {
                JSONObject jsObject = (JSONObject) o;
//                for(Object e : jsObject.entrySet()) {
//                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) e;
//                    System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
//                }
                FileDescriptor fileDescriptor = new FileDescriptor();
                fileDescriptor.setName((String)jsObject.get("path"));
                fileDescriptor.setModifiedDate((String) jsObject.get("modified"));

                fileDescriptors.add(fileDescriptor);
            }

            req.setAttribute("files", fileDescriptors);
			req.getRequestDispatcher(VIEW).forward(req, resp);
		}
    }

    public class FileDescriptor {
        private String name;
        private String creationDate;
        private String modifiedDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }
    }
}

