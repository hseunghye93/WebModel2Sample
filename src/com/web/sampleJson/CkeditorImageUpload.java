package com.web.sampleJson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.web.action.ActionJson;

public class CkeditorImageUpload implements ActionJson{
	
	private final String uploadDir = "C:/hsh/WebModel2Sample/upload_image";
	public static final String SEPERATOR = File.separator;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response){
		JSONObject json = new JSONObject();
		try{
			// 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 업로드 요청을 처리하는 ServletFileUpload 생성
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 업로드 요청 파싱해서 FileItem 목록 구함
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			
			while(iter.hasNext()){
				FileItem item = iter.next();
				
				// 파일인지 여부 확인 : isFormFile() -> type=file 이외의 폼 데이터 인지 확인
				if(!item.isFormField()){
					/*
					String name = item.getFieldName();
	                String fileName = item.getName(); //파일이름
	                String contentType = item.getContentType();
	                boolean isInMemory = item.isInMemory();
	                long sizeInBytes = item.getSize(); //파일 사이즈
	                
	                System.out.println("파일 이름 :" + fileName);
	                
					// 데이터 저장 File(위치, 파일명)
	                // 만들어놓은 웹컨텐트 /file/photo/___ <이곳에 저장하기 위해 경로를 지정한것(물리적으로)
					item.write(new File(dir, imgName));
					*/
					
					String fileName = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		    		String sub =  format.format(new Date()) + "_edi";
		    		String dir = uploadDir + SEPERATOR + sub + SEPERATOR;
		    		String fullPath = dir + fileName;
		    		
		    		File file = new File(dir, fileName); 
		    		// 디렉토리 생성
		    		if (!file.getParentFile().exists()) {
		    			if(file.getParentFile().mkdirs()){
		    				System.out.println("[file.mkdirs] file : Directory Creation Success");
		    			}else{
		    				System.out.println("[file.mkdirs] file : Directory Creation Fail");
		    			}
		    		}
		    		
		    		item.write(file);
		    		
		    		String url = "/sampleVoid/ckeditorImageSrc.sv?";
		    		url += "sub=" + sub;
		    		url += "&fileName=" + fileName;
		    		
		    		// json 데이터로 등록
		    		// {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		    		// 이런 형태로 리턴이 나가야함.
		    		json.put("uploaded", "1");
		    		json.put("fileName", fileName);
		    		json.put("url", url);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json.toString();
	}

}
