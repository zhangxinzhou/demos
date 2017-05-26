package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


@Controller
@SpringBootApplication
public class TestApplication {
	
	private static final String FILE_PATH="C:\\Users\\Administrator\\Desktop\\";

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
	@RequestMapping("/index")
	@ResponseBody
	public String index(){
		return "index1";
	}
	
	@RequestMapping("/toupload")
	public String toupload(){
		return "upload";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file){
		if(!file.isEmpty()){
			try(FileOutputStream fos=new FileOutputStream("D:/temp/"+file.getOriginalFilename())){
				fos.write(file.getBytes());
			}catch (Exception e) {
				e.printStackTrace();
			}
			return file.getOriginalFilename();
		}
		return "上传失败!";
	}
	
	@RequestMapping("/filelist")
	public String filelist(ModelMap mm){
		List<String> filenames=new ArrayList<>();
		File file=new File(FILE_PATH);
		FilenameFilter fnf=(File dir, String name)->{
			return name.endsWith(".txt")||
				   name.endsWith(".png");
		};
		File[] files=file.listFiles(fnf);
		for (File f : files) {
			filenames.add(f.getName());
		}
		mm.put("data", filenames);
		return "filelist";
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String filename){
		byte[] body = downloadFile(filename);
        return downloadResponse(body, filename);
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory multipartConfigFactory=new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize("20MB");
		multipartConfigFactory.setMaxRequestSize("20MB");
		return multipartConfigFactory.createMultipartConfig();
	}

	
	
	
	
	
	//https://my.oschina.net/songxinqiang/blog/891248
    protected ResponseEntity<byte[]> downloadResponse(byte[] body, String fileName) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;
        try {
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                fileName = fileName.replace("+", "%20");    // IE下载文件名空格变+号问题
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {}

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(body.length);
        return new ResponseEntity<byte[]>(body, headers, status);
    }
	
    //https://my.oschina.net/songxinqiang/blog/891248
    public byte[] downloadFile(String fileName) {
        byte[] res = new byte[0];
        try {
            File file = new File(FILE_PATH, fileName);
            if (file.exists() && !file.isDirectory()) {
                res = FileCopyUtils.copyToByteArray(file);
            }
        } catch (IOException e) {
            System.out.println("下载文件发生错误");
        }
        return res;
    }
}
