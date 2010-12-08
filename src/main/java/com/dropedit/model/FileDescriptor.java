package com.dropedit.controller;

public class FileDescriptor {
	private String name;
	private String path;
	private boolean directory;
	private String creationDate;
	private String modifiedDate;


	public String getPath(){
	    return path;
	}

	public void setPath(String path) {
	    this.path = path;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name.substring(1);
	}

	public void setIsDirectory(boolean directory){
	    this.directory = directory;
	}

	public boolean getIsDirectory(){
	    return directory;
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