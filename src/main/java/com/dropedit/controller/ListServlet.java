package com.dropedit.controller;

import com.dropbox.client.Authenticator;
import com.dropbox.client.DropboxClient;
import com.dropbox.client.DropboxException;
import org.json.simple.JSONArray;
//import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import com.dropedit.model.*;


public class ListServlet extends HttpServlet {
    public static final String VIEW = "/WEB-INF/jsp/list.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String root = RootPath.getRootPath();
        String pathName = req.getParameter("value");

        if (pathName == null) {
            pathName = "";
        }
        //////////////////////////////////////////
        String[] currentDir = pathName.split("/");
        String parent = "";

        for(int i = 0; i < currentDir.length -1; i++){
            parent += currentDir[i] + "/";
        }

        int length = parent.length();

        if(length > 0){
            length = length - 1;
        }
        /////////////////////////////////////////////

        //RootPath.addRootPath(pathName);

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("/");
        } else {
            DropboxClient dropbox = (DropboxClient) session.getAttribute("client");

            req.setAttribute("user", session.getAttribute("uname"));

            String info = null;
            JSONObject testMap = new JSONObject();

            try {
                //info = dropbox.accountInfo(false, "").toString();
                testMap = (JSONObject) dropbox.metadata("dropbox", pathName, 10000, "", true, false, "");
            } catch (DropboxException e) {
                info = "oh shoot it failed!";
                System.out.println(info);
            }

            Set<FileDescriptor> fileDescriptors = new TreeSet<FileDescriptor>(
                    new Comparator<FileDescriptor>() {
                        @Override
                        public int compare(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor1) {
                            if(fileDescriptor.getIsDirectory() && !fileDescriptor1.getIsDirectory()){
                             return -1;
                         }
                         else if(!fileDescriptor.getIsDirectory() && fileDescriptor1.getIsDirectory()){
                            return 1;
                         }
                            return fileDescriptor.getName().compareToIgnoreCase(fileDescriptor1.getName());
                        }
                    }
            );

            //String Source = (String) testMap.get("path");
            //req.setAttribute("folder", root);
            //req.setAttribute("current_folder", pathName);

            for (Object o : (JSONArray) testMap.get("contents")) {
                JSONObject jsObject = (JSONObject) o;
                /*for(Object e : jsObject.entrySet()) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) e;
                    System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
                } */
                FileDescriptor fileDescriptor = new FileDescriptor();
                fileDescriptor.setName(StringUtils.difference(pathName, (String) jsObject.get("path")));
                fileDescriptor.setPath((String) jsObject.get("path"));
                fileDescriptor.setModifiedDate((String) jsObject.get("modified"));
                //fileDescriptor.setIsDirectory(((String) jsObject.get("path")).indexOf(".") == -1); // if there is a "." in the filename we'll assume its not a directory
                //fileDescriptor.setIsDirectory(jsObject.get("is_dir"));

                fileDescriptors.add(fileDescriptor);
            }

            req.setAttribute("parentPath", parent.substring(0,length));
            req.setAttribute("currentPath",pathName);
            //req.setAttribute("root", root);
            req.setAttribute("files", fileDescriptors);
            req.getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    // path is relative to location of root (in this case, root == "dropbox")
    // path may be the location of a file or a folder
    protected int deleteFile(String path, DropboxClient dropbox) {
        try {
            //root is dropbox - alternative is sandbox
            JSONObject testMap = (JSONObject) dropbox.fileDelete("dropbox", path, null);
            if (testMap.containsValue("400")) {
                return 400;
            } else if (testMap.containsValue("200")) {
                return 200;
            } else if (testMap.containsValue("404")) {
                return 404;
            }
            //NEED TO ADD CODE FOR RETURN VALUES

            return 1;
        }
        catch (DropboxException e) {
            System.out.println("Error deleting file " + path);
            return 0;
        }
    }
}

