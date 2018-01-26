package com.hk.emi.web.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.hk.commons.files.FileHandler;
import com.hk.commons.util.ArrayUtils;
import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;

/**
 * 
 * @author kally
 * @date 2018年1月26日下午12:48:54
 */
@Controller
public class FileController {

	@Autowired
	private FileHandler fileHandler;

	/**
	 * 文件上传
	 * 
	 * @param fileList
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	public JsonResult upload(@RequestParam("files") MultipartFile[] fileList) {
		if (ArrayUtils.isEmpty(fileList)) {
			return JsonResult.error("请至少选择一个文件！");
		}
		List<FileInfo> resultData = Lists.newArrayList();
		for (MultipartFile file : fileList) {
			try {
				String path = fileHandler.upload(file.getOriginalFilename(), file.getInputStream());
				resultData.add(new FileInfo(path, file.getOriginalFilename(), file.getSize(),
						Files.getFileExtension(file.getOriginalFilename())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JsonResult.success(resultData);
	}

	/**
	 * 下载文件
	 * 
	 * @param filePath
	 * @return
	 */
	@GetMapping("/down")
	public ResponseEntity<byte[]> down(@RequestParam String filePath) {
		byte[] dataByte = fileHandler.getDownData(filePath);
		return Webs.toDownResponseEntity(filePath, dataByte);
	}

	/**
	 * 文件预览
	 * 
	 * @param filePath
	 * @param response
	 */
	@GetMapping("/view")
	public void view(@RequestParam String filePath, HttpServletResponse response) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(fileHandler.getDownData(filePath));
		} catch (IOException e) {
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public class FileInfo {

		private String filePath;

		private String originalFileName;

		private long fileSize;

		private String ext;

		/**
		 * @param filePath
		 * @param originalFileName
		 * @param fileName
		 * @param fileSize
		 * @param ext
		 */
		public FileInfo(String filePath, String originalFileName,long fileSize, String ext) {
			this.filePath = filePath;
			this.originalFileName = originalFileName;
			this.fileSize = fileSize;
			this.ext = ext;
		}

		/**
		 * @return the filePath
		 */
		public String getFilePath() {
			return filePath;
		}

		/**
		 * @param filePath
		 *            the filePath to set
		 */
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		/**
		 * @return the originalFileName
		 */
		public String getOriginalFileName() {
			return originalFileName;
		}

		/**
		 * @param originalFileName
		 *            the originalFileName to set
		 */
		public void setOriginalFileName(String originalFileName) {
			this.originalFileName = originalFileName;
		}

		/**
		 * @return the fileSize
		 */
		public long getFileSize() {
			return fileSize;
		}

		/**
		 * @param fileSize
		 *            the fileSize to set
		 */
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}

		/**
		 * @return the ext
		 */
		public String getExt() {
			return ext;
		}

		/**
		 * @param ext
		 *            the ext to set
		 */
		public void setExt(String ext) {
			this.ext = ext;
		}

	}

}
