package com.dropedit.model;
import java.util.Stack;

public class RootPath {
    private static Stack rootStack = new Stack();

    public static void addRootPath(String node){
					if(node != null && node != "") {
	        	rootStack.push(node);
					}
    }

    public static String getRootPath(){
        if (rootStack.empty()){
            return "";
        }
        else{
            String returnpath = (String)rootStack.pop();
            return returnpath;
        }
    }

}
