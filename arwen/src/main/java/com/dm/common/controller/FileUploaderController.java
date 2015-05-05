/**
 * 
 */
package com.dm.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dm.common.component.AnnexFilePath;
import com.dm.common.utils.DateUtils;

/**
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/file")
public class FileUploaderController {
	
	@Autowired
	private AnnexFilePath annexFilePath;
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (!file.isEmpty()) {
			BufferedOutputStream stream = null;
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				final String subDir = DateUtils.getDateStrCompact(new Date());
				File dir = new File(annexFilePath.getPhysicalPath() + subDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				String suffix = name.substring(name.indexOf("."));
				String fileName = UUID.randomUUID() + suffix;
				File upload = new File(dir, fileName);
				stream = new BufferedOutputStream(new FileOutputStream(upload));
				stream.write(bytes);
				String imageUrl = subDir + File.separator + fileName;
				map.put("success", true);
				map.put("url", imageUrl);
				map.put("fileName", name.substring(0, name.indexOf(".")));
				map.put("extension",
						name.substring(name.indexOf("."), name.length()));

			} catch (Exception e) {
				String errorMsg = "You failed to upload " + name + " => "
						+ e.getMessage();
				map.put("success", false);
				map.put("errorMsg", errorMsg);
			} finally {
				try {
					if (stream != null)
						stream.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} else {
			String errorMsg = "You failed to upload "
					+ " because the file was empty.";
			map.put("success", false);
			map.put("errorMsg", errorMsg);
		}
		return map;
	}

	/**
	 * @Title: download
	 * @Description:
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileUrl = request.getParameter("fileUrl");
		response.setContentType(request.getServletContext()
				.getMimeType(fileUrl));
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileUrl);
		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		String fullFileName = annexFilePath.getPhysicalPath() + fileUrl;
		File file = new File(fullFileName);
		// System.out.println(fullFileName);
		// 读取文件
		InputStream fis = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		// 写文件
		os.write(buffer);// 输出文件
		os.flush();
	}
}
