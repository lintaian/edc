package com.lps.edc.interceptor;

import java.io.File;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		String sessionId = arg0.getSession().getId();
		String dir = arg0.getSession().getServletContext().getRealPath("/") + "cvstemp";
		File filedir = new File(dir);
	    if (filedir.exists()) {
	    	File[] files = filedir.listFiles();
	    	for (File file : files) {
				if (file.getName().startsWith(sessionId)) {
					file.delete();
				}
			}
	    }
	}
	
}
