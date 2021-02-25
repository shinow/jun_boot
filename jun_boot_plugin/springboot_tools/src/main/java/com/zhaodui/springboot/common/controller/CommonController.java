package com.zhaodui.springboot.common.controller;

import com.zhaodui.springboot.common.mdoel.Result;
import lombok.extern.slf4j.Slf4j;
 import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/sys/common")
public class CommonController {

	@Value(value = "${zhaodui.path.upload}")
	private String uploadpath;

	@PostMapping(value = "/upload")
	public Result<?> upload(HttpServletRequest request, HttpServletResponse response) {
		Result<?> result = new Result<>();
		try {
			String ctxPath = uploadpath;
			String fileName = null;
			String bizPath = "files";
			String nowday = new SimpleDateFormat("yyyyMMdd").format(new Date());
			File file = new File(ctxPath + File.separator + bizPath + File.separator + nowday);
			if (!file.exists()) {
				file.mkdirs();// 创建文件根目录
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile mf = multipartRequest.getFile("file");// 获取上传文件对象
			String orgName = mf.getOriginalFilename();// 获取文件名
			fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
			String savePath = file.getPath() + File.separator + fileName;
			File savefile = new File(savePath);
			FileCopyUtils.copy(mf.getBytes(), savefile);
			String dbpath = bizPath + File.separator + nowday + File.separator + fileName;
			if (dbpath.contains("\\")) {
				dbpath = dbpath.replace("\\", "/");
			}
			result.setMessage(dbpath);
			result.setSuccess(true);
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return result;
	}

	@GetMapping(value = "/view/**")
	public void view(HttpServletRequest request, HttpServletResponse response) {
		// ISO-8859-1 ==> UTF-8 进行编码转换
		String imgPath = extractPathFromPattern(request);
		// 其余处理略
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			imgPath = imgPath.replace("..", "");
			if (imgPath.endsWith(",")) {
				imgPath = imgPath.substring(0, imgPath.length() - 1);
			}
			response.setContentType("image/jpeg;charset=utf-8");
			String localPath = uploadpath;
			String imgurl = localPath + File.separator + imgPath;
			inputStream = new BufferedInputStream(new FileInputStream(imgurl));
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			response.flushBuffer();
		} catch (IOException e) {
			log.error("预览图片失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

	}
	

	@GetMapping(value = "/download/**")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ISO-8859-1 ==> UTF-8 进行编码转换
		String filePath = extractPathFromPattern(request);
		// 其余处理略
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			filePath = filePath.replace("..", "");
			if (filePath.endsWith(",")) {
				filePath = filePath.substring(0, filePath.length() - 1);
			}
			String localPath = uploadpath;
			String downloadFilePath = localPath + File.separator + filePath;
			File file = new File(downloadFilePath);
	         if (file.exists()) {
	        	 response.setContentType("application/force-download");// 设置强制下载不打开            
	 			response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
	 			inputStream = new BufferedInputStream(new FileInputStream(file));
	 			outputStream = response.getOutputStream();
	 			byte[] buf = new byte[1024];
	 			int len;
	 			while ((len = inputStream.read(buf)) > 0) {
	 				outputStream.write(buf, 0, len);
	 			}
	 			response.flushBuffer();
	         }
			
		} catch (Exception e) {
			log.info("文件下载失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static String extractPathFromPattern(final HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}
	
}
