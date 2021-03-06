package com.hk.fs.web.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.hk.fs.core.domain.FileInfo;

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
	 * @throws IOException
	 */
	@PostMapping("/upload")
	@ResponseBody
	public JsonResult upload(@RequestParam("files") MultipartFile[] fileList) throws IOException {
		if (ArrayUtils.isEmpty(fileList)) {
			return JsonResult.error("请至少选择一个文件！");
		}
		List<FileInfo> resultData = Lists.newArrayList();
		for (MultipartFile file : fileList) {
			String path = fileHandler.upload(file.getOriginalFilename(), file.getInputStream());
			FileInfo fileInfo = new FileInfo();
			fileInfo.setExt(Files.getFileExtension(file.getOriginalFilename()));
			fileInfo.setFileName(file.getOriginalFilename());
			fileInfo.setFilePath(path);
			fileInfo.setFileSize(file.getSize());
			resultData.add(fileInfo);
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
		byte[] dataByte = fileHandler.getFileData(filePath);
		return Webs.toDownResponseEntity(filePath, dataByte);
	}

	/**
	 * 文件预览
	 * 
	 * @param filePath
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/{yyyy}/{mm}/{dd}/{fileName:.+}")
	public void view(@PathVariable String yyyy,@PathVariable String mm, @PathVariable String dd, @PathVariable String fileName,
			HttpServletResponse response) throws IOException {
		String absPath = String.format("%s/%s/%s/%s", yyyy,mm, dd, fileName);
		OutputStream out = response.getOutputStream();
		response.setContentType("image/jpeg");
		out.write(fileHandler.getFileData(absPath));
		out.close();
	}
}
