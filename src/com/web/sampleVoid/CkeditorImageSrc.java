package com.web.sampleVoid;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.action.ActionVoid;

public class CkeditorImageSrc implements ActionVoid{

	private final String uploadDir = "C:/hsh/WebModel2Sample/upload_image";
	public static final String SEPERATOR = File.separator;
	
	/** Buffer size */
	public static final int BUFFER_SIZE = 8192;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
    	String sub = request.getParameter("sub");
    	String fileName = request.getParameter("fileName");
    	String fullPath = uploadDir + SEPERATOR + sub + SEPERATOR + fileName;
    	
    	File file = new File(fullPath);

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		
    	try{

        	if(!file.exists()){
        		throw new FileNotFoundException(fullPath);
        	}
        	
        	if (!file.isFile()) {
    			throw new FileNotFoundException(fullPath);
    		}
        	
        	byte[] b = new byte[BUFFER_SIZE];
        	
        	String mimeType = "application/octet-stream;";
        	mimeType.replaceAll("\r", "").replaceAll("\n", "");
        	
        	response.setContentType(mimeType);
    		response.setHeader("Content-Disposition", "filename=image;");


    		fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());
			
			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
			
    	}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(outs != null) outs.close();
				if(fin != null) fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
	}

}
