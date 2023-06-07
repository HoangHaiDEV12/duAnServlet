package com.tulamweb.common;

import java.io.File;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.mysql.cj.util.StringUtils;

public class FileCommon {
	
	public static String upLoadFile(HttpServletRequest request, String savePath) throws IOException, ServletException {
		
		String multiPart = "";
		
		
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
		
		String fileName = "";
		for (Part part : request.getParts()) {
            fileName = getFileName(part);
            if(!StringUtils.isNullOrEmpty(fileName)) {
            	
            	part.write(savePath + File.separator + fileName);
            	multiPart +=  "upload/" + fileName + ";";
            }
            
        }
		return multiPart;
	}
	
	 private static String getFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        System.out.println("content-disposition header= "+contentDisp);
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf("=") + 2, token.length()-1);
	            }
	        }
	        return "";
	    }

}
