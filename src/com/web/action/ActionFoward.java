package com.web.action;

public class ActionFoward {
	private boolean isRedirect; //true->forward, false->redirect
	private String path;
	public boolean getIsRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "ActionFoward [isRedirect=" + isRedirect + ", path=" + path + "]";
	}
}
